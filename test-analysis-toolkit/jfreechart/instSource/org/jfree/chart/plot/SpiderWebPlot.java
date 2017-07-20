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
 * ------------------
 * SpiderWebPlot.java
 * ------------------
 * (C) Copyright 2005-2007, by Heaps of Flavour Pty Ltd and Contributors.
 *
 * Company Info:  http://www.i4-talent.com
 *
 * Original Author:  Don Elliott;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Nina Jeliazkova;
 *
 * Changes
 * -------
 * 28-Jan-2005 : First cut - missing a few features - still to do:
 *                           - needs tooltips/URL/label generator functions
 *                           - ticks on axes / background grid?
 * 31-Jan-2005 : Renamed SpiderWebPlot, added label generator support, and 
 *               reformatted for consistency with other source files in 
 *               JFreeChart (DG);
 * 20-Apr-2005 : Renamed CategoryLabelGenerator 
 *               --> CategoryItemLabelGenerator (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 10-Jun-2005 : Added equals() method and fixed serialization (DG);
 * 16-Jun-2005 : Added default constructor and get/setDataset() 
 *               methods (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Apr-2006 : Fixed bug preventing the display of zero values - see patch
 *               1462727 (DG);
 * 05-Apr-2006 : Added support for mouse clicks, tool tips and URLs - see patch
 *               1463455 (DG);
 * 01-Jun-2006 : Fix bug 1493199, NullPointerException when drawing with null
 *               info (DG);
 * 05-Feb-2007 : Added attributes for axis stroke and paint, while fixing
 *               bug 1651277, and implemented clone() properly (DG);
 * 06-Feb-2007 : Changed getPlotValue() to protected, as suggested in bug 
 *               1605202 (DG);
 * 05-Mar-2007 : Restore clip region correctly (see bug 1667750) (DG);
 * 18-May-2007 : Set dataset for LegendItem (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintList;
import org.jfree.util.PaintUtilities;
import org.jfree.util.Rotation;
import org.jfree.util.ShapeUtilities;
import org.jfree.util.StrokeList;
import org.jfree.util.TableOrder;

/**
 * A plot that displays data from a {@link CategoryDataset} in the form of a 
 * "spider web".  Multiple series can be plotted on the same axis to allow 
 * easy comparison.  This plot doesn't support negative values at present.
 */
public class SpiderWebPlot extends Plot implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -5376340422031599463L;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[1]++;
  }
    
    /** The default head radius percent (currently 1%). */
    public static final double DEFAULT_HEAD = 0.01;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[2]++;
  }

    /** The default axis label gap (currently 10%). */
    public static final double DEFAULT_AXIS_LABEL_GAP = 0.10;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[3]++;
  }
 
    /** The default interior gap. */
    public static final double DEFAULT_INTERIOR_GAP = 0.25;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[4]++;
  }

    /** The maximum interior gap (currently 40%). */
    public static final double MAX_INTERIOR_GAP = 0.40;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[5]++;
  }

    /** The default starting angle for the radar chart axes. */
    public static final double DEFAULT_START_ANGLE = 90.0;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[6]++;
  }

    /** The default series label font. */
    public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 
            Font.PLAIN, 10);
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[7]++;
  }
    
    /** The default series label paint. */
    public static final Paint  DEFAULT_LABEL_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[8]++;
  }

    /** The default series label background paint. */
    public static final Paint  DEFAULT_LABEL_BACKGROUND_PAINT 
            = new Color(255, 255, 192);
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[9]++;
  }

    /** The default series label outline paint. */
    public static final Paint  DEFAULT_LABEL_OUTLINE_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[10]++;
  }

    /** The default series label outline stroke. */
    public static final Stroke DEFAULT_LABEL_OUTLINE_STROKE 
            = new BasicStroke(0.5f);
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[11]++;
  }

    /** The default series label shadow paint. */
    public static final Paint  DEFAULT_LABEL_SHADOW_PAINT = Color.lightGray;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[12]++;
  }

    /** 
     * The default maximum value plotted - forces the plot to evaluate
     *  the maximum from the data passed in
     */
    public static final double DEFAULT_MAX_VALUE = -1.0;
  static {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[13]++;
  }

    /** The head radius as a percentage of the available drawing area. */
    protected double headPercent;

    /** The space left around the outside of the plot as a percentage. */
    private double interiorGap;

    /** The gap between the labels and the axes as a %age of the radius. */
    private double axisLabelGap;
    
    /**
     * The paint used to draw the axis lines.
     * 
     * @since 1.0.4
     */
    private transient Paint axisLinePaint;
    
    /**
     * The stroke used to draw the axis lines.
     * 
     * @since 1.0.4
     */
    private transient Stroke axisLineStroke;

    /** The dataset. */
    private CategoryDataset dataset;

    /** The maximum value we are plotting against on each category axis */
    private double maxValue;
  
    /** 
     * The data extract order (BY_ROW or BY_COLUMN). This denotes whether
     * the data series are stored in rows (in which case the category names are
     * derived from the column keys) or in columns (in which case the category
     * names are derived from the row keys).
     */
    private TableOrder dataExtractOrder;

    /** The starting angle. */
    private double startAngle;

    /** The direction for drawing the radar axis & plots. */
    private Rotation direction;

    /** The legend item shape. */
    private transient Shape legendItemShape;

    /** The paint for ALL series (overrides list). */
    private transient Paint seriesPaint;

    /** The series paint list. */
    private PaintList seriesPaintList;

    /** The base series paint (fallback). */
    private transient Paint baseSeriesPaint;

    /** The outline paint for ALL series (overrides list). */
    private transient Paint seriesOutlinePaint;

    /** The series outline paint list. */
    private PaintList seriesOutlinePaintList;

    /** The base series outline paint (fallback). */
    private transient Paint baseSeriesOutlinePaint;

    /** The outline stroke for ALL series (overrides list). */
    private transient Stroke seriesOutlineStroke;

    /** The series outline stroke list. */
    private StrokeList seriesOutlineStrokeList;

    /** The base series outline stroke (fallback). */
    private transient Stroke baseSeriesOutlineStroke;

    /** The font used to display the category labels. */
    private Font labelFont;

    /** The color used to draw the category labels. */
    private transient Paint labelPaint;
    
    /** The label generator. */
    private CategoryItemLabelGenerator labelGenerator;

    /** controls if the web polygons are filled or not */
    private boolean webFilled = true;
  {
    CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[14]++;
  }
    
    /** A tooltip generator for the plot (<code>null</code> permitted). */
    private CategoryToolTipGenerator toolTipGenerator;
    
    /** A URL generator for the plot (<code>null</code> permitted). */
    private CategoryURLGenerator urlGenerator;
  
    /**
     * Creates a default plot with no dataset.
     */
    public SpiderWebPlot() {
        this(null);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[15]++;   
    }
    
    /**
     * Creates a new spider web plot with the given dataset, with each row
     * representing a series.  
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public SpiderWebPlot(CategoryDataset dataset) {
        this(dataset, TableOrder.BY_ROW);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[16]++;
    }

    /**
     * Creates a new spider web plot with the given dataset.
     * 
     * @param dataset  the dataset.
     * @param extract  controls how data is extracted ({@link TableOrder#BY_ROW}
     *                 or {@link TableOrder#BY_COLUMN}).
     */
    public SpiderWebPlot(CategoryDataset dataset, TableOrder extract) {
        super();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[17]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[18]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((extract == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[1]++;
            throw new IllegalArgumentException("Null 'extract' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[2]++;}
        this.dataset = dataset;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[19]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[3]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[21]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[4]++;}

        this.dataExtractOrder = extract;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[22]++;
        this.headPercent = DEFAULT_HEAD;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[23]++;
        this.axisLabelGap = DEFAULT_AXIS_LABEL_GAP;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[24]++;
        this.axisLinePaint = Color.black;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[25]++;
        this.axisLineStroke = new BasicStroke(1.0f);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[26]++;
        
        this.interiorGap = DEFAULT_INTERIOR_GAP;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[27]++;
        this.startAngle = DEFAULT_START_ANGLE;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[28]++;
        this.direction = Rotation.CLOCKWISE;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[29]++;
        this.maxValue = DEFAULT_MAX_VALUE;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[30]++;

        this.seriesPaint = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[31]++;
        this.seriesPaintList = new PaintList();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[32]++;
        this.baseSeriesPaint = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[33]++;

        this.seriesOutlinePaint = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[34]++;
        this.seriesOutlinePaintList = new PaintList();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[35]++;
        this.baseSeriesOutlinePaint = DEFAULT_OUTLINE_PAINT;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[36]++;

        this.seriesOutlineStroke = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[37]++;
        this.seriesOutlineStrokeList = new StrokeList();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[38]++;
        this.baseSeriesOutlineStroke = DEFAULT_OUTLINE_STROKE;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[39]++;

        this.labelFont = DEFAULT_LABEL_FONT;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[40]++;
        this.labelPaint = DEFAULT_LABEL_PAINT;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[41]++;
        this.labelGenerator = new StandardCategoryItemLabelGenerator();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[42]++;
        
        this.legendItemShape = DEFAULT_LEGEND_ITEM_CIRCLE;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[43]++;
    }

    /**
     * Returns a short string describing the type of plot.
     * 
     * @return The plot type.
     */
    public String getPlotType() {
        // return localizationResources.getString("Radar_Plot");
        return ("Spider Web Plot");
    }
    
    /**
     * Returns the dataset.
     * 
     * @return The dataset (possibly <code>null</code>).
     * 
     * @see #setDataset(CategoryDataset)
     */
    public CategoryDataset getDataset() {
        return this.dataset;   
    }
    
    /**
     * Sets the dataset used by the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset()
     */
    public void setDataset(CategoryDataset dataset) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[44]++;
int CodeCoverConditionCoverageHelper_C3;
        // if there is an existing dataset, remove the plot from the list of 
        // change listeners...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[5]++;
            this.dataset.removeChangeListener(this);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[45]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[6]++;}

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[46]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[47]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[7]++;
            setDatasetGroup(dataset.getGroup());
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[48]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[49]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[8]++;}

        // send a dataset change event to self to trigger plot change event
        datasetChanged(new DatasetChangeEvent(this, dataset));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[50]++;
    }
    
    /**
     * Method to determine if the web chart is to be filled.
     * 
     * @return A boolean.
     * 
     * @see #setWebFilled(boolean)
     */
    public boolean isWebFilled() {
        return this.webFilled;
    }

    /**
     * Sets the webFilled flag and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param flag  the flag.
     * 
     * @see #isWebFilled()
     */
    public void setWebFilled(boolean flag) {
        this.webFilled = flag;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[51]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[52]++;
    }
  
    /**
     * Returns the data extract order (by row or by column).
     * 
     * @return The data extract order (never <code>null</code>).
     * 
     * @see #setDataExtractOrder(TableOrder)
     */
    public TableOrder getDataExtractOrder() {
        return this.dataExtractOrder;
    }

    /**
     * Sets the data extract order (by row or by column) and sends a
     * {@link PlotChangeEvent}to all registered listeners.
     * 
     * @param order the order (<code>null</code> not permitted).
     * 
     * @throws IllegalArgumentException if <code>order</code> is 
     *     <code>null</code>.
     *     
     * @see #getDataExtractOrder()
     */
    public void setDataExtractOrder(TableOrder order) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[53]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[9]++;
            throw new IllegalArgumentException("Null 'order' argument");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[10]++;}
        this.dataExtractOrder = order;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[54]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[55]++;
    }

    /**
     * Returns the head percent.
     * 
     * @return The head percent.
     * 
     * @see #setHeadPercent(double)
     */
    public double getHeadPercent() {
        return this.headPercent;   
    }
    
    /**
     * Sets the head percent and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param percent  the percent.
     * 
     * @see #getHeadPercent()
     */
    public void setHeadPercent(double percent) {
        this.headPercent = percent;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[56]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[57]++;
    }
    
    /**
     * Returns the start angle for the first radar axis.
     * <BR>
     * This is measured in degrees starting from 3 o'clock (Java Arc2D default)
     * and measuring anti-clockwise.
     * 
     * @return The start angle.
     * 
     * @see #setStartAngle(double)
     */
    public double getStartAngle() {
        return this.startAngle;
    }

    /**
     * Sets the starting angle and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * <P>
     * The initial default value is 90 degrees, which corresponds to 12 o'clock.
     * A value of zero corresponds to 3 o'clock... this is the encoding used by
     * Java's Arc2D class.
     * 
     * @param angle  the angle (in degrees).
     * 
     * @see #getStartAngle()
     */
    public void setStartAngle(double angle) {
        this.startAngle = angle;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[58]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[59]++;
    }

    /**
     * Returns the maximum value any category axis can take.
     * 
     * @return The maximum value.
     * 
     * @see #setMaxValue(double)
     */
    public double getMaxValue() {
        return this.maxValue;
    }

    /**
     * Sets the maximum value any category axis can take and sends 
     * a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param value  the maximum value.
     * 
     * @see #getMaxValue()
     */
    public void setMaxValue(double value) {
        this.maxValue = value;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[60]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[61]++;
    }

    /**
     * Returns the direction in which the radar axes are drawn
     * (clockwise or anti-clockwise).
     * 
     * @return The direction (never <code>null</code>).
     * 
     * @see #setDirection(Rotation)
     */
    public Rotation getDirection() {
        return this.direction;
    }

    /**
     * Sets the direction in which the radar axes are drawn and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param direction  the direction (<code>null</code> not permitted).
     * 
     * @see #getDirection()
     */
    public void setDirection(Rotation direction) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[62]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((direction == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[11]++;
            throw new IllegalArgumentException("Null 'direction' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[12]++;}
        this.direction = direction;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[63]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[64]++;
    }

    /**
     * Returns the interior gap, measured as a percentage of the available 
     * drawing space.
     * 
     * @return The gap (as a percentage of the available drawing space).
     * 
     * @see #setInteriorGap(double)
     */
    public double getInteriorGap() {
        return this.interiorGap;
    }

    /**
     * Sets the interior gap and sends a {@link PlotChangeEvent} to all 
     * registered listeners. This controls the space between the edges of the 
     * plot and the plot area itself (the region where the axis labels appear).
     * 
     * @param percent  the gap (as a percentage of the available drawing space).
     * 
     * @see #getInteriorGap()
     */
    public void setInteriorGap(double percent) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[65]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((percent < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((percent > MAX_INTERIOR_GAP) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[13]++;
            throw new IllegalArgumentException(
                    "Percentage outside valid range.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[14]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[66]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.interiorGap != percent) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[15]++;
            this.interiorGap = percent;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[67]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[68]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[16]++;}
    }

    /**
     * Returns the axis label gap.
     * 
     * @return The axis label gap.
     * 
     * @see #setAxisLabelGap(double)
     */
    public double getAxisLabelGap() {
        return this.axisLabelGap;   
    }
    
    /**
     * Sets the axis label gap and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param gap  the gap.
     * 
     * @see #getAxisLabelGap()
     */
    public void setAxisLabelGap(double gap) {
        this.axisLabelGap = gap;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[69]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[70]++;
    }
    
    /**
     * Returns the paint used to draw the axis lines.
     * 
     * @return The paint used to draw the axis lines (never <code>null</code>).
     * 
     * @see #setAxisLinePaint(Paint)
     * @see #getAxisLineStroke()
     * @since 1.0.4
     */
    public Paint getAxisLinePaint() {
        return this.axisLinePaint;
    }
    
    /**
     * Sets the paint used to draw the axis lines and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getAxisLinePaint()
     * @since 1.0.4
     */
    public void setAxisLinePaint(Paint paint) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[71]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[17]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[18]++;}
        this.axisLinePaint = paint;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[72]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[73]++;
    }
    
    /**
     * Returns the stroke used to draw the axis lines.
     * 
     * @return The stroke used to draw the axis lines (never <code>null</code>).
     * 
     * @see #setAxisLineStroke(Stroke)
     * @see #getAxisLinePaint()
     * @since 1.0.4
     */
    public Stroke getAxisLineStroke() {
        return this.axisLineStroke;
    }
    
    /**
     * Sets the stroke used to draw the axis lines and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getAxisLineStroke()
     * @since 1.0.4
     */
    public void setAxisLineStroke(Stroke stroke) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[74]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[19]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[20]++;}
        this.axisLineStroke = stroke;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[75]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[76]++;
    }
    
    //// SERIES PAINT /////////////////////////

    /**
     * Returns the paint for ALL series in the plot.
     * 
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setSeriesPaint(Paint)
     */
    public Paint getSeriesPaint() {
        return this.seriesPaint;
    }

    /**
     * Sets the paint for ALL series in the plot. If this is set to</code> null
     * </code>, then a list of paints is used instead (to allow different colors
     * to be used for each series of the radar group).
     * 
     * @param paint the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesPaint()
     */
    public void setSeriesPaint(Paint paint) {
        this.seriesPaint = paint;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[77]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[78]++;
    }

    /**
     * Returns the paint for the specified series.
     * 
     * @param series  the series index (zero-based).
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setSeriesPaint(int, Paint)
     */
    public Paint getSeriesPaint(int series) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[79]++;
int CodeCoverConditionCoverageHelper_C11;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.seriesPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[21]++;
            return this.seriesPaint;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[22]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[80]++;

        // otherwise look up the paint list
        Paint result = this.seriesPaintList.getPaint(series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[81]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[23]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[82]++;
            DrawingSupplier supplier = getDrawingSupplier();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[83]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((supplier != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[25]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[84]++;
                Paint p = supplier.getNextPaint();
                this.seriesPaintList.setPaint(series, p);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[85]++;
                result = p;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[86]++;

            }
            else {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[26]++;
                result = this.baseSeriesPaint;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[87]++;
            }

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[24]++;}
        return result;

    }

    /**
     * Sets the paint used to fill a series of the radar and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesPaint(int)
     */
    public void setSeriesPaint(int series, Paint paint) {
        this.seriesPaintList.setPaint(series, paint);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[88]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[89]++;
    }

    /**
     * Returns the base series paint. This is used when no other paint is
     * available.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setBaseSeriesPaint(Paint)
     */
    public Paint getBaseSeriesPaint() {
      return this.baseSeriesPaint;
    }

    /**
     * Sets the base series paint.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseSeriesPaint()
     */
    public void setBaseSeriesPaint(Paint paint) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[90]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[27]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[28]++;}
        this.baseSeriesPaint = paint;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[91]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[92]++;
    }

    //// SERIES OUTLINE PAINT ////////////////////////////

    /**
     * Returns the outline paint for ALL series in the plot.
     * 
     * @return The paint (possibly <code>null</code>).
     */
    public Paint getSeriesOutlinePaint() {
        return this.seriesOutlinePaint;
    }

    /**
     * Sets the outline paint for ALL series in the plot. If this is set to
     * </code> null</code>, then a list of paints is used instead (to allow
     * different colors to be used for each series).
     * 
     * @param paint  the paint (<code>null</code> permitted).
     */
    public void setSeriesOutlinePaint(Paint paint) {
        this.seriesOutlinePaint = paint;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[93]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[94]++;
    }

    /**
     * Returns the paint for the specified series.
     * 
     * @param series  the series index (zero-based).
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getSeriesOutlinePaint(int series) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[95]++;
int CodeCoverConditionCoverageHelper_C15;
        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.seriesOutlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[29]++;
            return this.seriesOutlinePaint;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[30]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[96]++;
        // otherwise look up the paint list
        Paint result = this.seriesOutlinePaintList.getPaint(series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[97]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[31]++;
            result = this.baseSeriesOutlinePaint;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[98]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[32]++;}
        return result;
    }

    /**
     * Sets the paint used to fill a series of the radar and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     */
    public void setSeriesOutlinePaint(int series, Paint paint) {
        this.seriesOutlinePaintList.setPaint(series, paint);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[99]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[100]++;  
    }

    /**
     * Returns the base series paint. This is used when no other paint is
     * available.
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getBaseSeriesOutlinePaint() {
        return this.baseSeriesOutlinePaint;
    }

    /**
     * Sets the base series paint.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setBaseSeriesOutlinePaint(Paint paint) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[101]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[33]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[34]++;}
        this.baseSeriesOutlinePaint = paint;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[102]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[103]++;
    }

    //// SERIES OUTLINE STROKE /////////////////////

    /**
     * Returns the outline stroke for ALL series in the plot.
     * 
     * @return The stroke (possibly <code>null</code>).
     */
    public Stroke getSeriesOutlineStroke() {
        return this.seriesOutlineStroke;
    }

    /**
     * Sets the outline stroke for ALL series in the plot. If this is set to
     * </code> null</code>, then a list of paints is used instead (to allow
     * different colors to be used for each series).
     * 
     * @param stroke  the stroke (<code>null</code> permitted).
     */
    public void setSeriesOutlineStroke(Stroke stroke) {
        this.seriesOutlineStroke = stroke;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[104]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[105]++;
    }

    /**
     * Returns the stroke for the specified series.
     * 
     * @param series  the series index (zero-based).
     * 
     * @return The stroke (never <code>null</code>).
     */
    public Stroke getSeriesOutlineStroke(int series) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[106]++;
int CodeCoverConditionCoverageHelper_C18;

        // return the override, if there is one...
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.seriesOutlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[35]++;
            return this.seriesOutlineStroke;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[36]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[107]++;

        // otherwise look up the paint list
        Stroke result = this.seriesOutlineStrokeList.getStroke(series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[108]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[37]++;
            result = this.baseSeriesOutlineStroke;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[109]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[38]++;}
        return result;

    }

    /**
     * Sets the stroke used to fill a series of the radar and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param stroke  the stroke (<code>null</code> permitted).
     */
    public void setSeriesOutlineStroke(int series, Stroke stroke) {
        this.seriesOutlineStrokeList.setStroke(series, stroke);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[110]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[111]++;
    }

    /**
     * Returns the base series stroke. This is used when no other stroke is
     * available.
     * 
     * @return The stroke (never <code>null</code>).
     */
    public Stroke getBaseSeriesOutlineStroke() {
        return this.baseSeriesOutlineStroke;
    }

    /**
     * Sets the base series stroke.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     */
    public void setBaseSeriesOutlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[112]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[39]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[40]++;}
        this.baseSeriesOutlineStroke = stroke;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[113]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[114]++;
    }

    /**
     * Returns the shape used for legend items.
     * 
     * @return The shape (never <code>null</code>).
     * 
     * @see #setLegendItemShape(Shape)
     */
    public Shape getLegendItemShape() {
        return this.legendItemShape;
    }

    /**
     * Sets the shape used for legend items and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     * 
     * @param shape  the shape (<code>null</code> not permitted).
     * 
     * @see #getLegendItemShape()
     */
    public void setLegendItemShape(Shape shape) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[115]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((shape == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[41]++;
            throw new IllegalArgumentException("Null 'shape' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[42]++;}
        this.legendItemShape = shape;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[116]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[117]++;
    }

    /**
     * Returns the series label font.
     * 
     * @return The font (never <code>null</code>).
     * 
     * @see #setLabelFont(Font)
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Sets the series label font and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getLabelFont()
     */
    public void setLabelFont(Font font) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[118]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[43]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[44]++;}
        this.labelFont = font;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[119]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[120]++;
    }

    /**
     * Returns the series label paint.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setLabelPaint(Paint)
     */
    public Paint getLabelPaint() {
        return this.labelPaint;
    }

    /**
     * Sets the series label paint and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getLabelPaint()
     */
    public void setLabelPaint(Paint paint) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[121]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[45]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[46]++;}
        this.labelPaint = paint;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[122]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[123]++;
    }

    /**
     * Returns the label generator.
     * 
     * @return The label generator (never <code>null</code>).
     * 
     * @see #setLabelGenerator(CategoryItemLabelGenerator)
     */
    public CategoryItemLabelGenerator getLabelGenerator() {
        return this.labelGenerator;   
    }
    
    /**
     * Sets the label generator and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param generator  the generator (<code>null</code> not permitted).
     * 
     * @see #getLabelGenerator()
     */
    public void setLabelGenerator(CategoryItemLabelGenerator generator) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[124]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((generator == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[47]++;
            throw new IllegalArgumentException("Null 'generator' argument.");
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[48]++;}
        this.labelGenerator = generator;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[125]++;    
    }
    
    /**
     * Returns the tool tip generator for the plot.
     * 
     * @return The tool tip generator (possibly <code>null</code>).
     * 
     * @see #setToolTipGenerator(CategoryToolTipGenerator)
     * 
     * @since 1.0.2
     */
    public CategoryToolTipGenerator getToolTipGenerator() {
        return this.toolTipGenerator;    
    }
    
    /**
     * Sets the tool tip generator for the plot and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getToolTipGenerator()
     * 
     * @since 1.0.2
     */
    public void setToolTipGenerator(CategoryToolTipGenerator generator) {
        this.toolTipGenerator = generator;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[126]++;
        this.notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[127]++;
    }
    
    /**
     * Returns the URL generator for the plot.
     * 
     * @return The URL generator (possibly <code>null</code>).
     * 
     * @see #setURLGenerator(CategoryURLGenerator)
     * 
     * @since 1.0.2
     */
    public CategoryURLGenerator getURLGenerator() {
        return this.urlGenerator;    
    }
    
    /**
     * Sets the URL generator for the plot and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getURLGenerator()
     * 
     * @since 1.0.2
     */
    public void setURLGenerator(CategoryURLGenerator generator) {
        this.urlGenerator = generator;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[128]++;
        this.notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[129]++;
    }
    
    /**
     * Returns a collection of legend items for the radar chart.
     * 
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[130]++;
        LegendItemCollection result = new LegendItemCollection();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[131]++;

        List keys = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[132]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[49]++;
            keys = this.dataset.getRowKeys();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[133]++;

        }
        else {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[50]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[134]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_COLUMN) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[51]++;
            keys = this.dataset.getColumnKeys();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[135]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[52]++;}
}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[136]++;
int CodeCoverConditionCoverageHelper_C27;

        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[53]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[137]++;
            int series = 0;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[138]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[139]++;
            Shape shape = getLegendItemShape();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[140]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[1]++;


int CodeCoverConditionCoverageHelper_C28;

            while ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[1]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[2]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[3]++;
}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[141]++;
                String label = iterator.next().toString();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[142]++;
                String description = label;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[143]++;

                Paint paint = getSeriesPaint(series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[144]++;
                Paint outlinePaint = getSeriesOutlinePaint(series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[145]++;
                Stroke stroke = getSeriesOutlineStroke(series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[146]++;
                LegendItem item = new LegendItem(label, description, 
                        null, null, shape, paint, stroke, outlinePaint);
                item.setDataset(getDataset());
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[147]++;
                result.add(item);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[148]++;
                series++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[149]++;
            }

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[54]++;}

        return result;
    }

    /**
     * Returns a cartesian point from a polar angle, length and bounding box
     * 
     * @param bounds  the area inside which the point needs to be.
     * @param angle  the polar angle, in degrees.
     * @param length  the relative length. Given in percent of maximum extend.
     * 
     * @return The cartesian point.
     */
    protected Point2D getWebPoint(Rectangle2D bounds, 
                                  double angle, double length) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[150]++;
        
        double angrad = Math.toRadians(angle);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[151]++;
        double x = Math.cos(angrad) * length * bounds.getWidth() / 2;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[152]++;
        double y = -Math.sin(angrad) * length * bounds.getHeight() / 2;

        return new Point2D.Double(bounds.getX() + x + bounds.getWidth() / 2, 
                bounds.getY() + y + bounds.getHeight() / 2);
    }

    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a
     * printer).
     * 
     * @param g2  the graphics device.
     * @param area  the area within which the plot should be drawn.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  the state from the parent plot, if there is one.
     * @param info  collects info about the drawing.
     */
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info)
    {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[153]++;
        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[154]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[155]++;
int CodeCoverConditionCoverageHelper_C29;

        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[55]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[156]++;
            info.setDataArea(area);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[157]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[56]++;}

        drawBackground(g2, area);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[158]++;
        drawOutline(g2, area);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[159]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[160]++;

        Shape savedClip = g2.getClip();

        g2.clip(area);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[161]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[162]++;
        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                getForegroundAlpha()));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[163]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[164]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((DatasetUtilities.isEmptyOrNull(this.dataset)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[57]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[165]++;
            int seriesCount = 0, catCount = 0;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[166]++;
int CodeCoverConditionCoverageHelper_C31;

            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[59]++;
                seriesCount = this.dataset.getRowCount();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[167]++;
                catCount = this.dataset.getColumnCount();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[168]++;

            }
            else {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[60]++;
                seriesCount = this.dataset.getColumnCount();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[169]++;
                catCount = this.dataset.getRowCount();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[170]++;
            }
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[171]++;
int CodeCoverConditionCoverageHelper_C32;

            // ensure we have a maximum value to use on the axes
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.maxValue == DEFAULT_MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[61]++;
                calculateMaxValue(seriesCount, catCount);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[172]++;
} else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[62]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[173]++;

            // Next, setup the plot area 
      
            // adjust the plot area by the interior spacing value

            double gapHorizontal = area.getWidth() * getInteriorGap();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[174]++;
            double gapVertical = area.getHeight() * getInteriorGap();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[175]++;

            double X = area.getX() + gapHorizontal / 2;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[176]++;
            double Y = area.getY() + gapVertical / 2;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[177]++;
            double W = area.getWidth() - gapHorizontal;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[178]++;
            double H = area.getHeight() - gapVertical;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[179]++;

            double headW = area.getWidth() * this.headPercent;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[180]++;
            double headH = area.getHeight() * this.headPercent;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[181]++;

            // make the chart area a square
            double min = Math.min(W, H) / 2;
            X = (X + X + W) / 2 - min;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[182]++;
            Y = (Y + Y + H) / 2 - min;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[183]++;
            W = 2 * min;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[184]++;
            H = 2 * min;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[185]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[186]++;

            Point2D  centre = new Point2D.Double(X + W / 2, Y + H / 2);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[187]++;
            Rectangle2D radarArea = new Rectangle2D.Double(X, Y, W, H);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[188]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[4]++;


int CodeCoverConditionCoverageHelper_C33;

            // draw the axis and category label
            for (int cat = 0;(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((cat < catCount) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false); cat++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[4]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[5]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[6]++;
}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[189]++;
                double angle = getStartAngle()
                        + (getDirection().getFactor() * cat * 360 / catCount);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[190]++;
                
                Point2D endPoint = getWebPoint(radarArea, angle, 1);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[191]++; 
                                                     // 1 = end of axis
                Line2D  line = new Line2D.Double(centre, endPoint);
                g2.setPaint(this.axisLinePaint);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[192]++;
                g2.setStroke(this.axisLineStroke);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[193]++;
                g2.draw(line);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[194]++;
                drawLabel(g2, radarArea, 0.0, cat, angle, 360.0 / catCount);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[195]++;
            }
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[196]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[7]++;


int CodeCoverConditionCoverageHelper_C34;
            
            // Now actually plot each of the series polygons..
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[7]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[8]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[9]++;
}
                drawRadarPoly(g2, radarArea, centre, info, series, catCount, 
                        headH, headW);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[197]++;
            }

        }
        else {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[58]++; 
            drawNoDataMessage(g2, area);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[198]++;
        }
        g2.setClip(savedClip);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[199]++;
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[200]++;
        drawOutline(g2, area);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[201]++;
    }

    /**
     * loop through each of the series to get the maximum value
     * on each category axis
     *
     * @param seriesCount  the number of series
     * @param catCount  the number of categories
     */
    private void calculateMaxValue(int seriesCount, int catCount) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[202]++;
        double v = 0;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[203]++;
        Number nV = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[204]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[10]++;


int CodeCoverConditionCoverageHelper_C35;

        for (int seriesIndex = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((seriesIndex < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); seriesIndex++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[10]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[11]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[12]++;
}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[205]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[13]++;


int CodeCoverConditionCoverageHelper_C36;
            for (int catIndex = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((catIndex < catCount) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); catIndex++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[13]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[14]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[15]++;
}
                nV = getPlotValue(seriesIndex, catIndex);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[206]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[207]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((nV != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[63]++;
                    v = nV.doubleValue();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[208]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[209]++;
int CodeCoverConditionCoverageHelper_C38;
                    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((v > this.maxValue) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[65]++; 
                        this.maxValue = v;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[210]++;

                    } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[66]++;}
   
                } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[64]++;}
            }
        }
    }

    /**
     * Draws a radar plot polygon.
     * 
     * @param g2 the graphics device.
     * @param plotArea the area we are plotting in (already adjusted).
     * @param centre the centre point of the radar axes
     * @param info chart rendering info.
     * @param series the series within the dataset we are plotting
     * @param catCount the number of categories per radar plot
     * @param headH the data point height
     * @param headW the data point width
     */
    protected void drawRadarPoly(Graphics2D g2, 
                                 Rectangle2D plotArea,
                                 Point2D centre,
                                 PlotRenderingInfo info,
                                 int series, int catCount,
                                 double headH, double headW) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[211]++;

        Polygon polygon = new Polygon();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[212]++;

        EntityCollection entities = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[213]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[67]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[214]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[68]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[215]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[16]++;


int CodeCoverConditionCoverageHelper_C40;

        // plot the data...
        for (int cat = 0;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((cat < catCount) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); cat++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[16]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[17]--;
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.loops[18]++;
}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[216]++;

            Number dataValue = getPlotValue(series, cat);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[217]++;
int CodeCoverConditionCoverageHelper_C41;

            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((dataValue != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[69]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[218]++;
                double value = dataValue.doubleValue();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[219]++;
int CodeCoverConditionCoverageHelper_C42;
  
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((value >= 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[71]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[220]++; // draw the polygon series...
              
                    // Finds our starting angle from the centre for this axis

                    double angle = getStartAngle()
                        + (getDirection().getFactor() * cat * 360 / catCount);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[221]++;

                    // The following angle calc will ensure there isn't a top 
                    // vertical axis - this may be useful if you don't want any 
                    // given criteria to 'appear' move important than the 
                    // others..
                    //  + (getDirection().getFactor() 
                    //        * (cat + 0.5) * 360 / catCount);

                    // find the point at the appropriate distance end point 
                    // along the axis/angle identified above and add it to the
                    // polygon

                    Point2D point = getWebPoint(plotArea, angle, 
                            value / this.maxValue);
                    polygon.addPoint((int) point.getX(), (int) point.getY());
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[222]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[223]++;

                    // put an elipse at the point being plotted..

                    Paint paint = getSeriesPaint(series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[224]++;
                    Paint outlinePaint = getSeriesOutlinePaint(series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[225]++;
                    Stroke outlineStroke = getSeriesOutlineStroke(series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[226]++;

                    Ellipse2D head = new Ellipse2D.Double(point.getX() 
                            - headW / 2, point.getY() - headH / 2, headW, 
                            headH);
                    g2.setPaint(paint);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[227]++;
                    g2.fill(head);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[228]++;
                    g2.setStroke(outlineStroke);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[229]++;
                    g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[230]++;
                    g2.draw(head);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[231]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[232]++;
int CodeCoverConditionCoverageHelper_C43;

                    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[73]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[233]++;
                        String tip = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[234]++;
int CodeCoverConditionCoverageHelper_C44;
                        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.toolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[75]++;
                            tip = this.toolTipGenerator.generateToolTip(
                                    this.dataset, series, cat);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[235]++;

                        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[76]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[236]++;

                        String url = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[237]++;
int CodeCoverConditionCoverageHelper_C45;
                        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.urlGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[77]++;
                            url = this.urlGenerator.generateURL(this.dataset, 
                                   series, cat);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[238]++;

                        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[78]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[239]++; 
                   
                        Shape area = new Rectangle(
                                (int) (point.getX() - headW),
                                (int) (point.getY() - headH), 
                                (int) (headW * 2), (int) (headH * 2));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[240]++;
                        CategoryItemEntity entity = new CategoryItemEntity(
                                area, tip, url, this.dataset, 
                                this.dataset.getRowKey(series),
                                this.dataset.getColumnKey(cat)); 
                        entities.add(entity);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[241]++;
                                
                    } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[74]++;}


                } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[72]++;}

            } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[70]++;}
        }
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[242]++;
        // Plot the polygon
    
        Paint paint = getSeriesPaint(series);
        g2.setPaint(paint);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[243]++;
        g2.setStroke(getSeriesOutlineStroke(series));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[244]++;
        g2.draw(polygon);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[245]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[246]++;
int CodeCoverConditionCoverageHelper_C46;

        // Lastly, fill the web polygon if this is required
    
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.webFilled) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[79]++;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                    0.1f));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[247]++;
            g2.fill(polygon);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[248]++;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                    getForegroundAlpha()));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[249]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[80]++;}
    }

    /**
     * Returns the value to be plotted at the interseries of the 
     * series and the category.  This allows us to plot
     * <code>BY_ROW</code> or <code>BY_COLUMN</code> which basically is just 
     * reversing the definition of the categories and data series being 
     * plotted.
     * 
     * @param series the series to be plotted.
     * @param cat the category within the series to be plotted.
     * 
     * @return The value to be plotted (possibly <code>null</code>).
     * 
     * @see #getDataExtractOrder()
     */
    protected Number getPlotValue(int series, int cat) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[250]++;
        Number value = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[251]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[81]++;
            value = this.dataset.getValue(series, cat);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[252]++;

        }
        else {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[82]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[253]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_COLUMN) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[83]++;
            value = this.dataset.getValue(cat, series);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[254]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[84]++;}
}
        return value;
    }

    /**
     * Draws the label for one axis.
     * 
     * @param g2  the graphics device.
     * @param plotArea  the plot area
     * @param value  the value of the label (ignored).
     * @param cat  the category (zero-based index).
     * @param startAngle  the starting angle.
     * @param extent  the extent of the arc.
     */
    protected void drawLabel(Graphics2D g2, Rectangle2D plotArea, double value, 
                             int cat, double startAngle, double extent) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[255]++;
        FontRenderContext frc = g2.getFontRenderContext();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[256]++;
 
        String label = null;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[257]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder == TableOrder.BY_ROW) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[85]++;
            // if series are in rows, then the categories are the column keys
            label = this.labelGenerator.generateColumnLabel(this.dataset, cat);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[258]++;

        }
        else {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[86]++;
            // if series are in columns, then the categories are the row keys
            label = this.labelGenerator.generateRowLabel(this.dataset, cat);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[259]++;
        }
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[260]++;
 
        Rectangle2D labelBounds = getLabelFont().getStringBounds(label, frc);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[261]++;
        LineMetrics lm = getLabelFont().getLineMetrics(label, frc);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[262]++;
        double ascent = lm.getAscent();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[263]++;

        Point2D labelLocation = calculateLabelLocation(labelBounds, ascent, 
                plotArea, startAngle);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[264]++;

        Composite saveComposite = g2.getComposite();
    
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                1.0f));
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[265]++;
        g2.setPaint(getLabelPaint());
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[266]++;
        g2.setFont(getLabelFont());
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[267]++;
        g2.drawString(label, (float) labelLocation.getX(), 
                (float) labelLocation.getY());
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[268]++;
        g2.setComposite(saveComposite);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[269]++;
    }

    /**
     * Returns the location for a label
     * 
     * @param labelBounds the label bounds.
     * @param ascent the ascent (height of font).
     * @param plotArea the plot area
     * @param startAngle the start angle for the pie series.
     * 
     * @return The location for a label.
     */
    protected Point2D calculateLabelLocation(Rectangle2D labelBounds, 
                                             double ascent,
                                             Rectangle2D plotArea, 
                                             double startAngle)
    {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[270]++;
        Arc2D arc1 = new Arc2D.Double(plotArea, startAngle, 0, Arc2D.OPEN);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[271]++;
        Point2D point1 = arc1.getEndPoint();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[272]++;

        double deltaX = -(point1.getX() - plotArea.getCenterX()) 
                        * this.axisLabelGap;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[273]++;
        double deltaY = -(point1.getY() - plotArea.getCenterY()) 
                        * this.axisLabelGap;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[274]++;

        double labelX = point1.getX() - deltaX;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[275]++;
        double labelY = point1.getY() - deltaY;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[276]++;
int CodeCoverConditionCoverageHelper_C50;

        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((labelX < plotArea.getCenterX()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[87]++;
            labelX -= labelBounds.getWidth();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[277]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[88]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[278]++;
int CodeCoverConditionCoverageHelper_C51;
    
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((labelX == plotArea.getCenterX()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[89]++;
            labelX -= labelBounds.getWidth() / 2;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[279]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[90]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[280]++;
int CodeCoverConditionCoverageHelper_C52;

        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((labelY > plotArea.getCenterY()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[91]++;
            labelY += ascent;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[281]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[92]++;}

        return new Point2D.Double(labelX, labelY);
    }
    
    /**
     * Tests this plot for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[282]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[93]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[94]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[283]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((obj instanceof SpiderWebPlot) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[95]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[96]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[284]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[97]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[98]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[285]++;
        SpiderWebPlot that = (SpiderWebPlot) obj;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[286]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((this.dataExtractOrder.equals(that.dataExtractOrder)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[99]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[100]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[287]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((this.headPercent != that.headPercent) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[101]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[102]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[288]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((this.interiorGap != that.interiorGap) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[103]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[104]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[289]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((this.startAngle != that.startAngle) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[105]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[106]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[290]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((this.direction.equals(that.direction)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[107]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[108]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[291]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((this.maxValue != that.maxValue) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[109]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[110]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[292]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((this.webFilled != that.webFilled) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[111]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[112]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[293]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((this.axisLabelGap != that.axisLabelGap) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[113]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[114]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[294]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.axisLinePaint, that.axisLinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[115]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[116]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[295]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((this.axisLineStroke.equals(that.axisLineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[117]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[118]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[296]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.legendItemShape, that.legendItemShape)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[119]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[120]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[297]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.seriesPaint, that.seriesPaint)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[121]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[122]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[298]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.seriesPaintList.equals(that.seriesPaintList)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[123]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[124]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[299]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.baseSeriesPaint, that.baseSeriesPaint)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[125]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[126]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[300]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.seriesOutlinePaint, 
                that.seriesOutlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[127]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[128]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[301]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((this.seriesOutlinePaintList.equals(that.seriesOutlinePaintList)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[129]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[130]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[302]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.baseSeriesOutlinePaint, 
                that.baseSeriesOutlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[131]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[132]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[303]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.seriesOutlineStroke, 
                that.seriesOutlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[133]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[134]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[304]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((this.seriesOutlineStrokeList.equals(
                that.seriesOutlineStrokeList)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[135]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[136]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[305]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((this.baseSeriesOutlineStroke.equals(
                that.baseSeriesOutlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[137]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[138]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[306]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((this.labelFont.equals(that.labelFont)) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[139]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[140]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[307]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.labelPaint, that.labelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[141]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[142]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[308]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((this.labelGenerator.equals(that.labelGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[143]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[144]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[309]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.toolTipGenerator, 
                that.toolTipGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[145]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[146]++;}
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[310]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.urlGenerator,
                that.urlGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[147]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[148]++;}
        return true;
    }
    
    /**
     * Returns a clone of this plot.
     * 
     * @return A clone of this plot.
     * 
     * @throws CloneNotSupportedException if the plot cannot be cloned for 
     *         any reason.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[311]++;
        SpiderWebPlot clone = (SpiderWebPlot) super.clone();
        clone.legendItemShape = ShapeUtilities.clone(this.legendItemShape);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[312]++;
        clone.seriesPaintList = (PaintList) this.seriesPaintList.clone();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[313]++;
        clone.seriesOutlinePaintList 
                = (PaintList) this.seriesOutlinePaintList.clone();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[314]++;
        clone.seriesOutlineStrokeList 
                = (StrokeList) this.seriesOutlineStrokeList.clone();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[315]++;
        return clone;
    }
    
    /**
     * Provides serialization support.
     *
     * @param stream  the output stream.
     *
     * @throws IOException  if there is an I/O error.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[316]++;

        SerialUtilities.writeShape(this.legendItemShape, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[317]++;
        SerialUtilities.writePaint(this.seriesPaint, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[318]++;
        SerialUtilities.writePaint(this.baseSeriesPaint, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[319]++;
        SerialUtilities.writePaint(this.seriesOutlinePaint, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[320]++;
        SerialUtilities.writePaint(this.baseSeriesOutlinePaint, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[321]++;
        SerialUtilities.writeStroke(this.seriesOutlineStroke, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[322]++;
        SerialUtilities.writeStroke(this.baseSeriesOutlineStroke, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[323]++;
        SerialUtilities.writePaint(this.labelPaint, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[324]++;
        SerialUtilities.writePaint(this.axisLinePaint, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[325]++;
        SerialUtilities.writeStroke(this.axisLineStroke, stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[326]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        stream.defaultReadObject();
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[327]++;

        this.legendItemShape = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[328]++;
        this.seriesPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[329]++;
        this.baseSeriesPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[330]++;
        this.seriesOutlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[331]++;
        this.baseSeriesOutlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[332]++;
        this.seriesOutlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[333]++;
        this.baseSeriesOutlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[334]++;
        this.labelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[335]++;
        this.axisLinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[336]++;
        this.axisLineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[337]++;
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[338]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[149]++;
            this.dataset.addChangeListener(this);
CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.statements[339]++;

        } else {
  CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd.branches[150]++;}
    } 

}

class CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd ());
  }
    public static long[] statements = new long[340];
    public static long[] branches = new long[151];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[82];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.SpiderWebPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 81; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$6xsmjn5qssilmvqr3brvjunyxbhd () {
    super("org.jfree.chart.plot.SpiderWebPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 339; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 150; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 81; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.SpiderWebPlot.java");
      for (int i = 1; i <= 339; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 150; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 81; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

