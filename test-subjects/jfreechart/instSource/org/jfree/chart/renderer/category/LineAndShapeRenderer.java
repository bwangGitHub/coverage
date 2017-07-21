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
 * LineAndShapeRenderer.java
 * -------------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Mark Watson (www.markwatson.com);
 *                   Jeremy Bowman;
 *                   Richard Atkinson;
 *                   Christian W. Zuckschwerdt;
 *
 * Changes
 * -------
 * 23-Oct-2001 : Version 1 (DG);
 * 15-Nov-2001 : Modified to allow for null data values (DG);
 * 16-Jan-2002 : Renamed HorizontalCategoryItemRenderer.java 
 *               --> CategoryItemRenderer.java (DG);
 * 05-Feb-2002 : Changed return type of the drawCategoryItem method from void 
 *               to Shape, as part of the tooltips implementation (DG);
 * 11-May-2002 : Support for value label drawing (JB);
 * 29-May-2002 : Now extends AbstractCategoryItemRenderer (DG);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 05-Aug-2002 : Small modification to drawCategoryItem method to support URLs 
 *               for HTML image maps (RA);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 11-Oct-2002 : Added new constructor to incorporate tool tip and URL 
 *               generators (DG);
 * 24-Oct-2002 : Amendments for changes in CategoryDataset interface and 
 *               CategoryToolTipGenerator interface (DG);
 * 05-Nov-2002 : Base dataset is now TableDataset not CategoryDataset (DG);
 * 06-Nov-2002 : Renamed drawCategoryItem() --> drawItem() and now using axis 
 *               for category spacing (DG);
 * 17-Jan-2003 : Moved plot classes to a separate package (DG);
 * 10-Apr-2003 : Changed CategoryDataset to KeyedValues2DDataset in drawItem()
 *               method (DG);
 * 12-May-2003 : Modified to take into account the plot orientation (DG);
 * 29-Jul-2003 : Amended code that doesn't compile with JDK 1.2.2 (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 22-Sep-2003 : Fixed cloning (DG);
 * 10-Feb-2004 : Small change to drawItem() method to make cut-and-paste 
 *               override easier (DG);
 * 16-Jun-2004 : Fixed bug (id=972454) with label positioning on horizontal 
 *               charts (DG);
 * 15-Oct-2004 : Updated equals() method (DG);
 * 05-Nov-2004 : Modified drawItem() signature (DG);
 * 11-Nov-2004 : Now uses ShapeUtilities class to translate shapes (DG);
 * 27-Jan-2005 : Changed attribute names, modified constructor and removed 
 *               constants (DG);
 * 01-Feb-2005 : Removed unnecessary constants (DG);
 * 15-Mar-2005 : Fixed bug 1163897, concerning outlines for shapes (DG);
 * 13-Apr-2005 : Check flags that control series visibility (DG);
 * 20-Apr-2005 : Use generators for legend labels, tooltips and URLs (DG);
 * 09-Jun-2005 : Use addItemEntity() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 25-May-2006 : Added check to drawItem() to detect when both the line and
 *               the shape are not visible (DG);
 * 20-Apr-2007 : Updated getLegendItem() for renderer change (DG);
 * 17-May-2007 : Set datasetIndex and seriesIndex in getLegendItem() (DG);
 * 18-May-2007 : Set dataset and seriesKey for LegendItem (DG);
 * 24-Sep-2007 : Deprecated redundant fields/methods (DG);
 * 27-Sep-2007 : Added option to offset series x-position within category (DG);
 *
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
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
import org.jfree.data.category.CategoryDataset;
import org.jfree.util.BooleanList;
import org.jfree.util.BooleanUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.ShapeUtilities;

/**
 * A renderer that draws shapes for each data item, and lines between data 
 * items (for use with the {@link CategoryPlot} class).
 */
public class LineAndShapeRenderer extends AbstractCategoryItemRenderer 
                                  implements Cloneable, PublicCloneable, 
                                             Serializable {
  static {
    CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -197749519869226398L;
  static {
    CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[1]++;
  }
    
    /** 
     * A flag that controls whether or not lines are visible for ALL series. 
     * 
     * @deprecated As of 1.0.7 (this override flag is unnecessary).
     */
    private Boolean linesVisible;

    /** 
     * A table of flags that control (per series) whether or not lines are 
     * visible. 
     */
    private BooleanList seriesLinesVisible;

    /** 
     * A flag indicating whether or not lines are drawn between non-null 
     * points. 
     */
    private boolean baseLinesVisible;

    /** 
     * A flag that controls whether or not shapes are visible for ALL series.
     * 
     * @deprecated As of 1.0.7 (this override flag is unnecessary).
     */
    private Boolean shapesVisible;

    /** 
     * A table of flags that control (per series) whether or not shapes are 
     * visible. 
     */
    private BooleanList seriesShapesVisible;

    /** The default value returned by the getShapeVisible() method. */
    private boolean baseShapesVisible;

    /** 
     * A flag that controls whether or not shapes are filled for ALL series. 
     * 
     * @deprecated As of 1.0.7 (this override flag is unnecessary).
     */
    private Boolean shapesFilled;
    
    /** 
     * A table of flags that control (per series) whether or not shapes are 
     * filled. 
     */
    private BooleanList seriesShapesFilled;
    
    /** The default value returned by the getShapeFilled() method. */
    private boolean baseShapesFilled;
    
    /** 
     * A flag that controls whether the fill paint is used for filling 
     * shapes. 
     */
    private boolean useFillPaint;

    /** A flag that controls whether outlines are drawn for shapes. */
    private boolean drawOutlines;
        
    /** 
     * A flag that controls whether the outline paint is used for drawing shape 
     * outlines - if not, the regular series paint is used. 
     */
    private boolean useOutlinePaint;
    
    /**
     * A flag that controls whether or not the x-position for each item is
     * offset within the category according to the series.
     * 
     * @since 1.0.7
     */
    private boolean useSeriesOffset;

    /**
     * The item margin used for series offsetting - this allows the positioning
     * to match the bar positions of the {@link BarRenderer} class.
     * 
     * @since 1.0.7
     */
    private double itemMargin;
    
    /**
     * Creates a renderer with both lines and shapes visible by default.
     */
    public LineAndShapeRenderer() {
        this(true, true);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[2]++;
    }

    /**
     * Creates a new renderer with lines and/or shapes visible.
     * 
     * @param lines  draw lines?
     * @param shapes  draw shapes?
     */
    public LineAndShapeRenderer(boolean lines, boolean shapes) {
        super();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[3]++;
        this.linesVisible = null;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[4]++;
        this.seriesLinesVisible = new BooleanList();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[5]++;
        this.baseLinesVisible = lines;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[6]++;
        this.shapesVisible = null;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[7]++;
        this.seriesShapesVisible = new BooleanList();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[8]++;
        this.baseShapesVisible = shapes;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[9]++;
        this.shapesFilled = null;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[10]++;
        this.seriesShapesFilled = new BooleanList();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[11]++;
        this.baseShapesFilled = true;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[12]++;
        this.useFillPaint = false;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[13]++;
        this.drawOutlines = true;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[14]++;
        this.useOutlinePaint = false;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[15]++;
        this.useSeriesOffset = false;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[16]++;  // preserves old behaviour
        this.itemMargin = 0.0;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[17]++;
    }
    
    // LINES VISIBLE

    /**
     * Returns the flag used to control whether or not the line for an item is 
     * visible.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return A boolean.
     */
    public boolean getItemLineVisible(int series, int item) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[18]++;
        Boolean flag = this.linesVisible;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((flag == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[1]++;
            flag = getSeriesLinesVisible(series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[20]++;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[2]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((flag != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[3]++;
            return flag.booleanValue();

        }
        else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[4]++;
            return this.baseLinesVisible;   
        }
    }

    /**
     * Returns a flag that controls whether or not lines are drawn for ALL 
     * series.  If this flag is <code>null</code>, then the "per series" 
     * settings will apply.
     * 
     * @return A flag (possibly <code>null</code>).
     * 
     * @see #setLinesVisible(Boolean)
     * 
     * @deprecated As of 1.0.7 (the override facility is unnecessary, just
     *     use the per-series and base (default) settings).
     */
    public Boolean getLinesVisible() {
        return this.linesVisible;   
    }
    
    /**
     * Sets a flag that controls whether or not lines are drawn between the 
     * items in ALL series, and sends a {@link RendererChangeEvent} to all 
     * registered listeners.  You need to set this to <code>null</code> if you 
     * want the "per series" settings to apply.
     *
     * @param visible  the flag (<code>null</code> permitted).
     * 
     * @see #getLinesVisible()
     * 
     * @deprecated As of 1.0.7 (the override facility is unnecessary, just
     *     use the per-series and base (default) settings).
     */
    public void setLinesVisible(Boolean visible) {
        this.linesVisible = visible;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[22]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[23]++;
    }

    /**
     * Sets a flag that controls whether or not lines are drawn between the 
     * items in ALL series, and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     *
     * @param visible  the flag.
     * 
     * @see #getLinesVisible()
     * 
     * @deprecated As of 1.0.7 (the override facility is unnecessary, just
     *     use the per-series and base (default) settings).
     */
    public void setLinesVisible(boolean visible) {
        setLinesVisible(BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[24]++;
    }

    /**
     * Returns the flag used to control whether or not the lines for a series 
     * are visible.
     *
     * @param series  the series index (zero-based).
     *
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setSeriesLinesVisible(int, Boolean)
     */
    public Boolean getSeriesLinesVisible(int series) {
        return this.seriesLinesVisible.getBoolean(series);
    }

    /**
     * Sets the 'lines visible' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param flag  the flag (<code>null</code> permitted).
     * 
     * @see #getSeriesLinesVisible(int)
     */
    public void setSeriesLinesVisible(int series, Boolean flag) {
        this.seriesLinesVisible.setBoolean(series, flag);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[25]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[26]++;
    }

    /**
     * Sets the 'lines visible' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param visible  the flag.
     * 
     * @see #getSeriesLinesVisible(int)
     */
    public void setSeriesLinesVisible(int series, boolean visible) {
        setSeriesLinesVisible(series, BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[27]++;
    }
    
    /**
     * Returns the base 'lines visible' attribute.
     *
     * @return The base flag.
     * 
     * @see #getBaseLinesVisible()
     */
    public boolean getBaseLinesVisible() {
        return this.baseLinesVisible;
    }

    /**
     * Sets the base 'lines visible' flag and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getBaseLinesVisible()
     */
    public void setBaseLinesVisible(boolean flag) {
        this.baseLinesVisible = flag;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[28]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[29]++;
    }

    // SHAPES VISIBLE

    /**
     * Returns the flag used to control whether or not the shape for an item is 
     * visible.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return A boolean.
     */
    public boolean getItemShapeVisible(int series, int item) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[30]++;
        Boolean flag = this.shapesVisible;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[31]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((flag == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[5]++;
            flag = getSeriesShapesVisible(series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[32]++;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[6]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((flag != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[7]++;
            return flag.booleanValue();

        }
        else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[8]++;
            return this.baseShapesVisible;   
        }
    }

    /**
     * Returns the flag that controls whether the shapes are visible for the 
     * items in ALL series.
     * 
     * @return The flag (possibly <code>null</code>).
     * 
     * @see #setShapesVisible(Boolean)
     * 
     * @deprecated As of 1.0.7 (the override facility is unnecessary, just
     *     use the per-series and base (default) settings).
     */
    public Boolean getShapesVisible() {
        return this.shapesVisible;    
    }
    
    /**
     * Sets the 'shapes visible' for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param visible  the flag (<code>null</code> permitted).
     * 
     * @see #getShapesVisible()
     * 
     * @deprecated As of 1.0.7 (the override facility is unnecessary, just
     *     use the per-series and base (default) settings).
     */
    public void setShapesVisible(Boolean visible) {
        this.shapesVisible = visible;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[34]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[35]++;
    }

    /**
     * Sets the 'shapes visible' for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #getShapesVisible()
     * 
     * @deprecated As of 1.0.7 (the override facility is unnecessary, just
     *     use the per-series and base (default) settings).
     */
    public void setShapesVisible(boolean visible) {
        setShapesVisible(BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[36]++;
    }

    /**
     * Returns the flag used to control whether or not the shapes for a series
     * are visible.
     *
     * @param series  the series index (zero-based).
     *
     * @return A boolean.
     * 
     * @see #setSeriesShapesVisible(int, Boolean)
     */
    public Boolean getSeriesShapesVisible(int series) {
        return this.seriesShapesVisible.getBoolean(series);
    }

    /**
     * Sets the 'shapes visible' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param visible  the flag.
     * 
     * @see #getSeriesShapesVisible(int)
     */
    public void setSeriesShapesVisible(int series, boolean visible) {
        setSeriesShapesVisible(series, BooleanUtilities.valueOf(visible));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[37]++;
    }
    
    /**
     * Sets the 'shapes visible' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param flag  the flag.
     * 
     * @see #getSeriesShapesVisible(int)
     */
    public void setSeriesShapesVisible(int series, Boolean flag) {
        this.seriesShapesVisible.setBoolean(series, flag);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[38]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[39]++;
    }

    /**
     * Returns the base 'shape visible' attribute.
     *
     * @return The base flag.
     * 
     * @see #setBaseShapesVisible(boolean)
     */
    public boolean getBaseShapesVisible() {
        return this.baseShapesVisible;
    }

    /**
     * Sets the base 'shapes visible' flag and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getBaseShapesVisible()
     */
    public void setBaseShapesVisible(boolean flag) {
        this.baseShapesVisible = flag;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[40]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[41]++;
    }

    /**
     * Returns <code>true</code> if outlines should be drawn for shapes, and 
     * <code>false</code> otherwise.
     * 
     * @return A boolean.
     * 
     * @see #setDrawOutlines(boolean)
     */
    public boolean getDrawOutlines() {
        return this.drawOutlines;
    }
    
    /**
     * Sets the flag that controls whether outlines are drawn for 
     * shapes, and sends a {@link RendererChangeEvent} to all registered 
     * listeners. 
     * <P>
     * In some cases, shapes look better if they do NOT have an outline, but 
     * this flag allows you to set your own preference.
     * 
     * @param flag  the flag.
     * 
     * @see #getDrawOutlines()
     */
    public void setDrawOutlines(boolean flag) {
        this.drawOutlines = flag;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[42]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[43]++;
    }
    
    /**
     * Returns the flag that controls whether the outline paint is used for 
     * shape outlines.  If not, the regular series paint is used.
     * 
     * @return A boolean.
     * 
     * @see #setUseOutlinePaint(boolean)
     */
    public boolean getUseOutlinePaint() {
        return this.useOutlinePaint;   
    }
    
    /**
     * Sets the flag that controls whether the outline paint is used for shape 
     * outlines, and sends a {@link RendererChangeEvent} to all registered 
     * listeners. 
     * 
     * @param use  the flag.
     * 
     * @see #getUseOutlinePaint()
     */
    public void setUseOutlinePaint(boolean use) {
        this.useOutlinePaint = use;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[44]++;   
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[45]++;
    }

    // SHAPES FILLED
    
    /**
     * Returns the flag used to control whether or not the shape for an item 
     * is filled. The default implementation passes control to the 
     * <code>getSeriesShapesFilled</code> method. You can override this method
     * if you require different behaviour.
     *
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     *
     * @return A boolean.
     */
    public boolean getItemShapeFilled(int series, int item) {
        return getSeriesShapesFilled(series);
    }

    /**
     * Returns the flag used to control whether or not the shapes for a series 
     * are filled. 
     *
     * @param series  the series index (zero-based).
     *
     * @return A boolean.
     */
    public boolean getSeriesShapesFilled(int series) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[46]++;
int CodeCoverConditionCoverageHelper_C5;

        // return the overall setting, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.shapesFilled != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[9]++;
            return this.shapesFilled.booleanValue();

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[10]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[47]++;

        // otherwise look up the paint table
        Boolean flag = this.seriesShapesFilled.getBoolean(series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[48]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((flag != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[11]++;
            return flag.booleanValue();

        }
        else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[12]++;
            return this.baseShapesFilled;
        } 

    }
    
    /**
     * Returns the flag that controls whether or not shapes are filled for 
     * ALL series.
     * 
     * @return A Boolean.
     * 
     * @see #setShapesFilled(Boolean)
     * 
     * @deprecated As of 1.0.7 (the override facility is unnecessary, just
     *     use the per-series and base (default) settings).
     */
    public Boolean getShapesFilled() {
        return this.shapesFilled;
    }

    /**
     * Sets the 'shapes filled' for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param filled  the flag.
     * 
     * @see #getShapesFilled()
     * 
     * @deprecated As of 1.0.7 (the override facility is unnecessary, just
     *     use the per-series and base (default) settings).
     */
    public void setShapesFilled(boolean filled) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[49]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((filled) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[13]++;
            setShapesFilled(Boolean.TRUE);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[50]++;

        }
        else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[14]++;
            setShapesFilled(Boolean.FALSE);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[51]++;
        }
    }
    
    /**
     * Sets the 'shapes filled' for ALL series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param filled  the flag (<code>null</code> permitted).
     * 
     * @see #getShapesFilled()
     * 
     * @deprecated As of 1.0.7 (the override facility is unnecessary, just
     *     use the per-series and base (default) settings).
     */
    public void setShapesFilled(Boolean filled) {
        this.shapesFilled = filled;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[52]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[53]++;
    }
    
    /**
     * Sets the 'shapes filled' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param filled  the flag.
     * 
     * @see #getSeriesShapesFilled(int)
     */
    public void setSeriesShapesFilled(int series, Boolean filled) {
        this.seriesShapesFilled.setBoolean(series, filled);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[54]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[55]++;
    }

    /**
     * Sets the 'shapes filled' flag for a series and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param filled  the flag.
     * 
     * @see #getSeriesShapesFilled(int)
     */
    public void setSeriesShapesFilled(int series, boolean filled) {
        // delegate
        setSeriesShapesFilled(series, BooleanUtilities.valueOf(filled));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[56]++;
    }

    /**
     * Returns the base 'shape filled' attribute.
     *
     * @return The base flag.
     * 
     * @see #setBaseShapesFilled(boolean)
     */
    public boolean getBaseShapesFilled() {
        return this.baseShapesFilled;
    }

    /**
     * Sets the base 'shapes filled' flag and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getBaseShapesFilled()
     */
    public void setBaseShapesFilled(boolean flag) {
        this.baseShapesFilled = flag;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[57]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[58]++;
    }

    /**
     * Returns <code>true</code> if the renderer should use the fill paint 
     * setting to fill shapes, and <code>false</code> if it should just
     * use the regular paint.
     * 
     * @return A boolean.
     * 
     * @see #setUseFillPaint(boolean)
     */
    public boolean getUseFillPaint() {
        return this.useFillPaint;
    }
    
    /**
     * Sets the flag that controls whether the fill paint is used to fill 
     * shapes, and sends a {@link RendererChangeEvent} to all 
     * registered listeners.
     * 
     * @param flag  the flag.
     * 
     * @see #getUseFillPaint()
     */
    public void setUseFillPaint(boolean flag) {
        this.useFillPaint = flag;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[59]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[60]++;
    }
    
    /**
     * Returns the flag that controls whether or not the x-position for each
     * data item is offset within the category according to the series.
     * 
     * @return A boolean.
     * 
     * @see #setUseSeriesOffset(boolean)
     * 
     * @since 1.0.7
     */
    public boolean getUseSeriesOffset() {
        return this.useSeriesOffset;
    }
    
    /**
     * Sets the flag that controls whether or not the x-position for each 
     * data item is offset within its category according to the series, and
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param offset  the offset.
     * 
     * @see #getUseSeriesOffset()
     * 
     * @since 1.0.7
     */
    public void setUseSeriesOffset(boolean offset) {
        this.useSeriesOffset = offset;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[61]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[62]++;
    }
    
    /**
     * Returns the item margin, which is the gap between items within a 
     * category (expressed as a percentage of the overall category width).  
     * This can be used to match the offset alignment with the bars drawn by 
     * a {@link BarRenderer}).
     * 
     * @return The item margin.
     * 
     * @see #setItemMargin(double)
     * @see #getUseSeriesOffset()
     * 
     * @since 1.0.7
     */
    public double getItemMargin() {
        return this.itemMargin;
    }
    
    /**
     * Sets the item margin, which is the gap between items within a category
     * (expressed as a percentage of the overall category width), and sends
     * a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param margin  the margin (0.0 <= margin < 1.0).
     * 
     * @see #getItemMargin()
     * @see #getUseSeriesOffset()
     * 
     * @since 1.0.7
     */
    public void setItemMargin(double margin) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[63]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((margin < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((margin >= 1.0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[15]++;
            throw new IllegalArgumentException("Requires 0.0 <= margin < 1.0.");

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[16]++;}
        this.itemMargin = margin;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[64]++;
        fireChangeEvent();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[65]++;
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
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[66]++;

        CategoryPlot cp = getPlot();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[67]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((cp == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[17]++;
            return null;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[18]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[68]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((isSeriesVisible(series)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isSeriesVisibleInLegend(series)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[19]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[69]++;
            CategoryDataset dataset = cp.getDataset(datasetIndex);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[70]++;
            String label = getLegendItemLabelGenerator().generateLabel(
                    dataset, series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[71]++;
            String description = label;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[72]++;
            String toolTipText = null;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[73]++;
int CodeCoverConditionCoverageHelper_C11; 
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((getLegendItemToolTipGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[21]++;
                toolTipText = getLegendItemToolTipGenerator().generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[74]++;
   
            } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[22]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[75]++;
            String urlText = null;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[76]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((getLegendItemURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[23]++;
                urlText = getLegendItemURLGenerator().generateLabel(
                        dataset, series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[77]++;
   
            } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[24]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[78]++;
            Shape shape = lookupSeriesShape(series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[79]++;
            Paint paint = lookupSeriesPaint(series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[80]++;
            Paint fillPaint = (this.useFillPaint 
                    ? getItemFillPaint(series, 0) : paint);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[81]++;
            boolean shapeOutlineVisible = this.drawOutlines;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[82]++;
            Paint outlinePaint = (this.useOutlinePaint 
                    ? getItemOutlinePaint(series, 0) : paint);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[83]++;
            Stroke outlineStroke = lookupSeriesOutlineStroke(series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[84]++;
            boolean lineVisible = getItemLineVisible(series, 0);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[85]++;
            boolean shapeVisible = getItemShapeVisible(series, 0);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[86]++;
            LegendItem result = new LegendItem(label, description, toolTipText, 
                    urlText, shapeVisible, shape, getItemShapeFilled(series, 0),
                    fillPaint, shapeOutlineVisible, outlinePaint, outlineStroke,
                    lineVisible, new Line2D.Double(-7.0, 0.0, 7.0, 0.0),
                    getItemStroke(series, 0), getItemPaint(series, 0));
            result.setDataset(dataset);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[87]++;
            result.setDatasetIndex(datasetIndex);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[88]++;
            result.setSeriesKey(dataset.getRowKey(series));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[89]++;
            result.setSeriesIndex(series);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[90]++;
            return result;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[20]++;}
        return null;

    }

    /**
     * This renderer uses two passes to draw the data.
     * 
     * @return The pass count (<code>2</code> for this renderer).
     */
    public int getPassCount() {
        return 2;   
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
    public void drawItem(Graphics2D g2, CategoryItemRendererState state,
            Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
            ValueAxis rangeAxis, CategoryDataset dataset, int row, int column,
            int pass) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[91]++;
int CodeCoverConditionCoverageHelper_C13;

        // do nothing if item is not visible
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((getItemVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[25]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[26]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[92]++;
int CodeCoverConditionCoverageHelper_C14;
        
        // do nothing if both the line and shape are not visible
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((getItemLineVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((getItemShapeVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[27]++;
            return;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[28]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[93]++;

        // nothing is drawn for null...
        Number v = dataset.getValue(row, column);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[94]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((v == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[29]++;
            return;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[30]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[95]++;

        PlotOrientation orientation = plot.getOrientation();

        // current data point...
        double x1;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[96]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.useSeriesOffset) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[31]++;
            x1 = domainAxis.getCategorySeriesMiddle(dataset.getColumnKey(
                    column), dataset.getRowKey(row), dataset, this.itemMargin, 
                    dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[97]++;
            
        }
        else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[32]++;
            x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), 
                    dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[98]++;
        }
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[99]++;
        double value = v.doubleValue();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[100]++;
        double y1 = rangeAxis.valueToJava2D(value, dataArea, 
                plot.getRangeAxisEdge());
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[101]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((pass == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((getItemLineVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[33]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[102]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((column != 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[35]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[103]++;
                Number previousValue = dataset.getValue(row, column - 1);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[104]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((previousValue != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[37]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[105]++;
                    // previous data point...
                    double previous = previousValue.doubleValue();
                    double x0;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[106]++;
int CodeCoverConditionCoverageHelper_C20;
                    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.useSeriesOffset) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[39]++;
                        x0 = domainAxis.getCategorySeriesMiddle(
                                dataset.getColumnKey(column - 1), 
                                dataset.getRowKey(row), dataset, 
                                this.itemMargin, dataArea, 
                                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[107]++;

                    }
                    else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[40]++;
                        x0 = domainAxis.getCategoryMiddle(column - 1, 
                                getColumnCount(), dataArea, 
                                plot.getDomainAxisEdge());
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[108]++;
                    }
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[109]++;
                    double y0 = rangeAxis.valueToJava2D(previous, dataArea, 
                            plot.getRangeAxisEdge());
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[110]++;

                    Line2D line = null;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[111]++;
int CodeCoverConditionCoverageHelper_C21;
                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[41]++;
                        line = new Line2D.Double(y0, x0, y1, x1);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[112]++;

                    }
                    else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[42]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[113]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[43]++;
                        line = new Line2D.Double(x0, y0, x1, y1);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[114]++;

                    } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[44]++;}
}
                    g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[115]++;
                    g2.setStroke(getItemStroke(row, column));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[116]++;
                    g2.draw(line);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[117]++;

                } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[36]++;}

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[34]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[118]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((pass == 1) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[45]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[119]++;
            Shape shape = getItemShape(row, column);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[120]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[47]++;
                shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[121]++;

            }
            else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[48]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[122]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[49]++;
                shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[123]++;

            } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[50]++;}
}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[124]++;
int CodeCoverConditionCoverageHelper_C26;

            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((getItemShapeVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[51]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[125]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((getItemShapeFilled(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[53]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[126]++;
int CodeCoverConditionCoverageHelper_C28;
                    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.useFillPaint) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[55]++;
                        g2.setPaint(getItemFillPaint(row, column));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[127]++;

                    }
                    else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[56]++;
                        g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[128]++;   
                    }
                    g2.fill(shape);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[129]++;

                } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[54]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[130]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.drawOutlines) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[57]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[131]++;
int CodeCoverConditionCoverageHelper_C30;
                    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[59]++;
                        g2.setPaint(getItemOutlinePaint(row, column));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[132]++;
   
                    }
                    else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[60]++;
                        g2.setPaint(getItemPaint(row, column));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[133]++;
                    }
                    g2.setStroke(getItemOutlineStroke(row, column));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[134]++;
                    g2.draw(shape);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[135]++;

                } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[58]++;}

            } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[52]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[136]++;
int CodeCoverConditionCoverageHelper_C31;

            // draw the item label if there is one...
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isItemLabelVisible(row, column)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[61]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[137]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[63]++;
                    drawItemLabel(g2, orientation, dataset, row, column, y1, 
                            x1, (value < 0.0));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[138]++;

                }
                else {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[64]++;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[139]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[65]++;
                    drawItemLabel(g2, orientation, dataset, row, column, x1, 
                            y1, (value < 0.0));
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[140]++;

                } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[66]++;}
}

            } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[62]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[141]++;

            // add an item entity, if this information is being collected
            EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[142]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[67]++;
                addItemEntity(entities, dataset, row, column, shape);
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[143]++;

            } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[68]++;}

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[46]++;}

    }
    
    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[144]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[69]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[70]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[145]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((obj instanceof LineAndShapeRenderer) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[72]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[146]++;
        
        LineAndShapeRenderer that = (LineAndShapeRenderer) obj;
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[147]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.baseLinesVisible != that.baseLinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[73]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[74]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[148]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesLinesVisible, 
                that.seriesLinesVisible)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[76]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[149]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.linesVisible, that.linesVisible)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[78]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[150]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((this.baseShapesVisible != that.baseShapesVisible) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[80]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[151]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesShapesVisible, 
                that.seriesShapesVisible)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[81]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[82]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[152]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.shapesVisible, that.shapesVisible)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[84]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[153]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.shapesFilled, that.shapesFilled)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[86]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[154]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesShapesFilled, 
                that.seriesShapesFilled)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[87]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[88]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[155]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.baseShapesFilled != that.baseShapesFilled) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[89]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[90]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[156]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint != that.useOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[92]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[157]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.useSeriesOffset != that.useSeriesOffset) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[93]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[94]++;}
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[158]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((this.itemMargin != that.itemMargin) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[95]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.branches[96]++;}
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
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[159]++;
        LineAndShapeRenderer clone = (LineAndShapeRenderer) super.clone();
        clone.seriesLinesVisible 
            = (BooleanList) this.seriesLinesVisible.clone();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[160]++;
        clone.seriesShapesVisible 
            = (BooleanList) this.seriesShapesVisible.clone();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[161]++;
        clone.seriesShapesFilled 
            = (BooleanList) this.seriesShapesFilled.clone();
CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9.statements[162]++;
        return clone;
    }
    
}

class CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9 ());
  }
    public static long[] statements = new long[163];
    public static long[] branches = new long[97];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[49];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.category.LineAndShapeRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,2,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 48; i++) {
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

  public CodeCoverCoverageCounter$3h8h5fgdgjrddsgef85hb52i6vuoywwzhrmg6e9 () {
    super("org.jfree.chart.renderer.category.LineAndShapeRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 162; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 96; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 48; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.category.LineAndShapeRenderer.java");
      for (int i = 1; i <= 162; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 96; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 48; i++) {
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

