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
 * CategoryPlot.java
 * -----------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Jeremy Bowman;
 *                   Arnaud Lelievre;
 *                   Richard West, Advanced Micro Devices, Inc.;
 *
 * Changes
 * -------
 * 21-Jun-2001 : Removed redundant JFreeChart parameter from constructors (DG);
 * 21-Aug-2001 : Added standard header. Fixed DOS encoding problem (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 15-Oct-2001 : Data source classes moved to com.jrefinery.data.* (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 23-Oct-2001 : Changed intro and trail gaps on bar plots to use percentage of 
 *               available space rather than a fixed number of units (DG);
 * 12-Dec-2001 : Changed constructors to protected (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Increased maximum intro and trail gap percents, plus added 
 *               some argument checking code.  Thanks to Taoufik Romdhane for 
 *               suggesting this (DG);
 * 05-Feb-2002 : Added accessor methods for the tooltip generator, incorporated
 *               alpha-transparency for Plot and subclasses (DG);
 * 06-Mar-2002 : Updated import statements (DG);
 * 14-Mar-2002 : Renamed BarPlot.java --> CategoryPlot.java, and changed code 
 *               to use the CategoryItemRenderer interface (DG);
 * 22-Mar-2002 : Dropped the getCategories() method (DG);
 * 23-Apr-2002 : Moved the dataset from the JFreeChart class to the Plot 
 *               class (DG);
 * 29-Apr-2002 : New methods to support printing values at the end of bars, 
 *               contributed by Jeremy Bowman (DG);
 * 11-May-2002 : New methods for label visibility and overlaid plot support, 
 *               contributed by Jeremy Bowman (DG);
 * 06-Jun-2002 : Removed the tooltip generator, this is now stored with the 
 *               renderer.  Moved constants into the CategoryPlotConstants 
 *               interface.  Updated Javadoc comments (DG);
 * 10-Jun-2002 : Overridden datasetChanged() method to update the upper and 
 *               lower bound on the range axis (if necessary), updated 
 *               Javadocs (DG);
 * 25-Jun-2002 : Removed redundant imports (DG);
 * 20-Aug-2002 : Changed the constructor for Marker (DG);
 * 28-Aug-2002 : Added listener notification to setDomainAxis() and 
 *               setRangeAxis() (DG);
 * 23-Sep-2002 : Added getLegendItems() method and fixed errors reported by 
 *               Checkstyle (DG);
 * 28-Oct-2002 : Changes to the CategoryDataset interface (DG);
 * 05-Nov-2002 : Base dataset is now TableDataset not CategoryDataset (DG);
 * 07-Nov-2002 : Renamed labelXXX as valueLabelXXX (DG);
 * 18-Nov-2002 : Added grid settings for both domain and range axis (previously
 *               these were set in the axes) (DG);
 * 19-Nov-2002 : Added axis location parameters to constructor (DG);
 * 17-Jan-2003 : Moved to com.jrefinery.chart.plot package (DG);
 * 14-Feb-2003 : Fixed bug in auto-range calculation for secondary axis (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 02-May-2003 : Moved render() method up from subclasses. Added secondary 
 *               range markers. Added an attribute to control the dataset 
 *               rendering order.  Added a drawAnnotations() method.  Changed 
 *               the axis location from an int to an AxisLocation (DG);
 * 07-May-2003 : Merged HorizontalCategoryPlot and VerticalCategoryPlot into 
 *               this class (DG);
 * 02-Jun-2003 : Removed check for range axis compatibility (DG);
 * 04-Jul-2003 : Added a domain gridline position attribute (DG);
 * 21-Jul-2003 : Moved DrawingSupplier to Plot superclass (DG);
 * 19-Aug-2003 : Added equals() method and implemented Cloneable (DG);
 * 01-Sep-2003 : Fixed bug 797466 (no change event when secondary dataset 
 *               changes) (DG);
 * 02-Sep-2003 : Fixed bug 795209 (wrong dataset checked in render2 method) and
 *               790407 (initialise method) (DG);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL); 
 * 08-Sep-2003 : Fixed bug (wrong secondary range axis being used).  Changed 
 *               ValueAxis API (DG);
 * 10-Sep-2003 : Fixed bug in setRangeAxis() method (DG);
 * 15-Sep-2003 : Fixed two bugs in serialization, implemented 
 *               PublicCloneable (DG);
 * 23-Oct-2003 : Added event notification for changes to renderer (DG);
 * 26-Nov-2003 : Fixed bug (849645) in clearRangeMarkers() method (DG);
 * 03-Dec-2003 : Modified draw method to accept anchor (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 10-Mar-2004 : Fixed bug in axis range calculation when secondary renderer is
 *               stacked (DG);
 * 12-May-2004 : Added fixed legend items (DG);
 * 19-May-2004 : Added check for null legend item from renderer (DG);
 * 02-Jun-2004 : Updated the DatasetRenderingOrder class (DG);
 * 05-Nov-2004 : Renamed getDatasetsMappedToRangeAxis() 
 *               --> datasetsMappedToRangeAxis(), and ensured that returned 
 *               list doesn't contain null datasets (DG);
 * 12-Nov-2004 : Implemented new Zoomable interface (DG);
 * 07-Jan-2005 : Renamed getRangeExtent() --> findRangeBounds() in 
 *               CategoryItemRenderer (DG);
 * 04-May-2005 : Fixed serialization of range markers (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 20-May-2005 : Added setDomainAxes() and setRangeAxes() methods, as per
 *               RFE 1183100 (DG);
 * 01-Jun-2005 : Upon deserialization, register plot as a listener with its
 *               axes, dataset(s) and renderer(s) - see patch 1209475 (DG);
 * 02-Jun-2005 : Added support for domain markers (DG);
 * 06-Jun-2005 : Fixed equals() method for use with GradientPaint (DG);
 * 09-Jun-2005 : Added setRenderers(), as per RFE 1183100 (DG);
 * 16-Jun-2005 : Added getDomainAxisCount() and getRangeAxisCount() methods, to
 *               match XYPlot (see RFE 1220495) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 11-Jan-2006 : Added configureRangeAxes() to rendererChanged(), since the
 *               renderer might influence the axis range (DG);
 * 27-Jan-2006 : Added various null argument checks (DG);
 * 18-Aug-2006 : Added getDatasetCount() method, plus a fix for bug drawing 
 *               category labels, thanks to Adriaan Joubert (1277726) (DG);
 * 05-Sep-2006 : Added MarkerChangeEvent support (DG);
 * 30-Oct-2006 : Added getDomainAxisIndex(), datasetsMappedToDomainAxis() and 
 *               getCategoriesForAxis() methods (DG);
 * 22-Nov-2006 : Fire PlotChangeEvent from setColumnRenderingOrder() and
 *               setRowRenderingOrder() (DG);
 * 29-Nov-2006 : Fix for bug 1605207 (IntervalMarker exceeds bounds of data 
 *               area) (DG);
 * 26-Feb-2007 : Fix for bug 1669218 (setDomainAxisLocation() notify argument
 *               ignored) (DG);
 * 13-Mar-2007 : Added null argument checks for setRangeCrosshairPaint() and
 *               setRangeCrosshairStroke(), fixed clipping for 
 *               annotations (DG);
 * 07-Jun-2007 : Override drawBackground() for new GradientPaint handling (DG);
 * 10-Jul-2007 : Added getRangeAxisIndex(ValueAxis) method (DG);
 * 24-Sep-2007 : Implemented new zoom methods (DG);
 * 25-Oct-2007 : Added some argument checks (DG);
 * 05-Nov-2007 : Applied patch 1823697, by Richard West, for removal of domain
 *               and range markers (DG);
 * 14-Nov-2007 : Added missing event notifications (DG);
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
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.annotations.CategoryAnnotation;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.AxisCollection;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.ValueTick;
import org.jfree.chart.event.ChartChangeEventType;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectList;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.SortOrder;

/**
 * A general plotting class that uses data from a {@link CategoryDataset} and 
 * renders each data item using a {@link CategoryItemRenderer}.
 */
public class CategoryPlot extends Plot implements ValueAxisPlot, 
        Zoomable, RendererChangeListener, Cloneable, PublicCloneable, 
        Serializable {
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -3537691700434728188L;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[1]++;
  }
    
    /** 
     * The default visibility of the grid lines plotted against the domain 
     * axis. 
     */
    public static final boolean DEFAULT_DOMAIN_GRIDLINES_VISIBLE = false;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[2]++;
  }

    /** 
     * The default visibility of the grid lines plotted against the range 
     * axis. 
     */
    public static final boolean DEFAULT_RANGE_GRIDLINES_VISIBLE = true;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[3]++;
  }

    /** The default grid line stroke. */
    public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0.0f, new float[] 
            {2.0f, 2.0f}, 0.0f);
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[4]++;
  }

    /** The default grid line paint. */
    public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[5]++;
  }

    /** The default value label font. */
    public static final Font DEFAULT_VALUE_LABEL_FONT = new Font("SansSerif", 
            Font.PLAIN, 10);
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[6]++;
  }

    /** 
     * The default crosshair visibility. 
     * 
     * @since 1.0.5
     */
    public static final boolean DEFAULT_CROSSHAIR_VISIBLE = false;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[7]++;
  }

    /** 
     * The default crosshair stroke. 
     * 
     * @since 1.0.5
     */
    public static final Stroke DEFAULT_CROSSHAIR_STROKE
            = DEFAULT_GRIDLINE_STROKE;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[8]++;
  }

    /** 
     * The default crosshair paint. 
     * 
     * @since 1.0.5
     */
    public static final Paint DEFAULT_CROSSHAIR_PAINT = Color.blue;
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[9]++;
  }

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources 
            = ResourceBundle.getBundle(
            "org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[10]++;
  }

    /** The plot orientation. */
    private PlotOrientation orientation;

    /** The offset between the data area and the axes. */
    private RectangleInsets axisOffset;

    /** Storage for the domain axes. */
    private ObjectList domainAxes;

    /** Storage for the domain axis locations. */
    private ObjectList domainAxisLocations;

    /**
     * A flag that controls whether or not the shared domain axis is drawn 
     * (only relevant when the plot is being used as a subplot).
     */
    private boolean drawSharedDomainAxis;

    /** Storage for the range axes. */
    private ObjectList rangeAxes;

    /** Storage for the range axis locations. */
    private ObjectList rangeAxisLocations;

    /** Storage for the datasets. */
    private ObjectList datasets;

    /** Storage for keys that map datasets to domain axes. */
    private ObjectList datasetToDomainAxisMap;
    
    /** Storage for keys that map datasets to range axes. */
    private ObjectList datasetToRangeAxisMap;

    /** Storage for the renderers. */
    private ObjectList renderers;

    /** The dataset rendering order. */
    private DatasetRenderingOrder renderingOrder 
            = DatasetRenderingOrder.REVERSE;
  {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[11]++;
  }

    /** 
     * Controls the order in which the columns are traversed when rendering the 
     * data items. 
     */
    private SortOrder columnRenderingOrder = SortOrder.ASCENDING;
  {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[12]++;
  }
    
    /** 
     * Controls the order in which the rows are traversed when rendering the 
     * data items. 
     */
    private SortOrder rowRenderingOrder = SortOrder.ASCENDING;
  {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[13]++;
  }
    
    /** 
     * A flag that controls whether the grid-lines for the domain axis are 
     * visible. 
     */
    private boolean domainGridlinesVisible;

    /** The position of the domain gridlines relative to the category. */
    private CategoryAnchor domainGridlinePosition;

    /** The stroke used to draw the domain grid-lines. */
    private transient Stroke domainGridlineStroke;

    /** The paint used to draw the domain  grid-lines. */
    private transient Paint domainGridlinePaint;

    /** 
     * A flag that controls whether the grid-lines for the range axis are 
     * visible. 
     */
    private boolean rangeGridlinesVisible;

    /** The stroke used to draw the range axis grid-lines. */
    private transient Stroke rangeGridlineStroke;

    /** The paint used to draw the range axis grid-lines. */
    private transient Paint rangeGridlinePaint;

    /** The anchor value. */
    private double anchorValue;

    /** A flag that controls whether or not a range crosshair is drawn. */
    private boolean rangeCrosshairVisible;

    /** The range crosshair value. */
    private double rangeCrosshairValue;

    /** The pen/brush used to draw the crosshair (if any). */
    private transient Stroke rangeCrosshairStroke;

    /** The color used to draw the crosshair (if any). */
    private transient Paint rangeCrosshairPaint;

    /** 
     * A flag that controls whether or not the crosshair locks onto actual 
     * data points. 
     */
    private boolean rangeCrosshairLockedOnData = true;
  {
    CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[14]++;
  }

    /** A map containing lists of markers for the domain axes. */
    private Map foregroundDomainMarkers;

    /** A map containing lists of markers for the domain axes. */
    private Map backgroundDomainMarkers;

    /** A map containing lists of markers for the range axes. */
    private Map foregroundRangeMarkers;

    /** A map containing lists of markers for the range axes. */
    private Map backgroundRangeMarkers;

    /** 
     * A (possibly empty) list of annotations for the plot.  The list should
     * be initialised in the constructor and never allowed to be 
     * <code>null</code>.
     */
    private List annotations;

    /**
     * The weight for the plot (only relevant when the plot is used as a subplot
     * within a combined plot).
     */
    private int weight;

    /** The fixed space for the domain axis. */
    private AxisSpace fixedDomainAxisSpace;

    /** The fixed space for the range axis. */
    private AxisSpace fixedRangeAxisSpace;

    /** 
     * An optional collection of legend items that can be returned by the 
     * getLegendItems() method. 
     */
    private LegendItemCollection fixedLegendItems;
    
    /**
     * Default constructor.
     */
    public CategoryPlot() {
        this(null, null, null, null);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[15]++;
    }

    /**
     * Creates a new plot.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param domainAxis  the domain axis (<code>null</code> permitted).
     * @param rangeAxis  the range axis (<code>null</code> permitted).
     * @param renderer  the item renderer (<code>null</code> permitted).
     *
     */
    public CategoryPlot(CategoryDataset dataset,
                        CategoryAxis domainAxis,
                        ValueAxis rangeAxis,
                        CategoryItemRenderer renderer) {

        super();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[16]++;

        this.orientation = PlotOrientation.VERTICAL;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[17]++;

        // allocate storage for dataset, axes and renderers
        this.domainAxes = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[18]++;
        this.domainAxisLocations = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[19]++;
        this.rangeAxes = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[20]++;
        this.rangeAxisLocations = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[21]++;
        
        this.datasetToDomainAxisMap = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[22]++;
        this.datasetToRangeAxisMap = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[23]++;

        this.renderers = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[24]++;

        this.datasets = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[25]++;
        this.datasets.set(0, dataset);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[26]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[27]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[1]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[28]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[2]++;}

        this.axisOffset = RectangleInsets.ZERO_INSETS;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[29]++;

        setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT, false);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[30]++;
        setRangeAxisLocation(AxisLocation.TOP_OR_LEFT, false);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[31]++;

        this.renderers.set(0, renderer);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[32]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[33]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[3]++;
            renderer.setPlot(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[34]++;
            renderer.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[35]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[4]++;}

        this.domainAxes.set(0, domainAxis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[36]++;
        this.mapDatasetToDomainAxis(0, 0);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[37]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[38]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[5]++;
            domainAxis.setPlot(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[39]++;
            domainAxis.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[40]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[6]++;}
        this.drawSharedDomainAxis = false;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[41]++;

        this.rangeAxes.set(0, rangeAxis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[42]++;
        this.mapDatasetToRangeAxis(0, 0);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[43]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[44]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[7]++;
            rangeAxis.setPlot(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[45]++;
            rangeAxis.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[46]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[8]++;}
        
        configureDomainAxes();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[47]++;
        configureRangeAxes();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[48]++;

        this.domainGridlinesVisible = DEFAULT_DOMAIN_GRIDLINES_VISIBLE;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[49]++;
        this.domainGridlinePosition = CategoryAnchor.MIDDLE;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[50]++;
        this.domainGridlineStroke = DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[51]++;
        this.domainGridlinePaint = DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[52]++;

        this.rangeGridlinesVisible = DEFAULT_RANGE_GRIDLINES_VISIBLE;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[53]++;
        this.rangeGridlineStroke = DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[54]++;
        this.rangeGridlinePaint = DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[55]++;

        this.foregroundDomainMarkers = new HashMap();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[56]++;
        this.backgroundDomainMarkers = new HashMap();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[57]++;
        this.foregroundRangeMarkers = new HashMap();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[58]++;
        this.backgroundRangeMarkers = new HashMap();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[59]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[60]++;

        Marker baseline = new ValueMarker(0.0, new Color(0.8f, 0.8f, 0.8f, 
                0.5f), new BasicStroke(1.0f), new Color(0.85f, 0.85f, 0.95f, 
                0.5f), new BasicStroke(1.0f), 0.6f);
        addRangeMarker(baseline, Layer.BACKGROUND);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[61]++;

        this.anchorValue = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[62]++;

        this.rangeCrosshairVisible = DEFAULT_CROSSHAIR_VISIBLE;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[63]++;
        this.rangeCrosshairValue = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[64]++;
        this.rangeCrosshairStroke = DEFAULT_CROSSHAIR_STROKE;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[65]++;
        this.rangeCrosshairPaint = DEFAULT_CROSSHAIR_PAINT;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[66]++;
        
        this.annotations = new java.util.ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[67]++;

    }
    
    /**
     * Returns a string describing the type of plot.
     *
     * @return The type.
     */
    public String getPlotType() {
        return localizationResources.getString("Category_Plot");
    }

    /**
     * Returns the orientation of the plot.
     *
     * @return The orientation of the plot (never <code>null</code>).
     * 
     * @see #setOrientation(PlotOrientation)
     */
    public PlotOrientation getOrientation() {
        return this.orientation;
    }

    /**
     * Sets the orientation for the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param orientation  the orientation (<code>null</code> not permitted).
     * 
     * @see #getOrientation()
     */
    public void setOrientation(PlotOrientation orientation) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[68]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[9]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[10]++;}
        this.orientation = orientation;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[69]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[70]++;
    }

    /**
     * Returns the axis offset.
     *
     * @return The axis offset (never <code>null</code>).
     * 
     * @see #setAxisOffset(RectangleInsets)
     */
    public RectangleInsets getAxisOffset() {
        return this.axisOffset;
    }

    /**
     * Sets the axis offsets (gap between the data area and the axes) and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param offset  the offset (<code>null</code> not permitted).
     * 
     * @see #getAxisOffset()
     */
    public void setAxisOffset(RectangleInsets offset) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[71]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((offset == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[11]++;
            throw new IllegalArgumentException("Null 'offset' argument.");
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[12]++;}
        this.axisOffset = offset;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[72]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[73]++;
    }

    /**
     * Returns the domain axis for the plot.  If the domain axis for this plot
     * is <code>null</code>, then the method will return the parent plot's 
     * domain axis (if there is a parent plot).
     *
     * @return The domain axis (<code>null</code> permitted).
     * 
     * @see #setDomainAxis(CategoryAxis)
     */
    public CategoryAxis getDomainAxis() {
        return getDomainAxis(0);
    }

    /**
     * Returns a domain axis.
     *
     * @param index  the axis index.
     *
     * @return The axis (<code>null</code> possible).
     * 
     * @see #setDomainAxis(int, CategoryAxis)
     */
    public CategoryAxis getDomainAxis(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[74]++;
        CategoryAxis result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[75]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((index < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[13]++;
            result = (CategoryAxis) this.domainAxes.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[76]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[14]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[77]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[15]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[78]++;
            Plot parent = getParent();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[79]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[17]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[80]++;
                CategoryPlot cp = (CategoryPlot) parent;
                result = cp.getDomainAxis(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[81]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[16]++;}
        return result;
    }

    /**
     * Sets the domain axis for the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param axis  the axis (<code>null</code> permitted).
     * 
     * @see #getDomainAxis()
     */
    public void setDomainAxis(CategoryAxis axis) {
        setDomainAxis(0, axis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[82]++;
    }

    /**
     * Sets a domain axis and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param index  the axis index.
     * @param axis  the axis (<code>null</code> permitted).
     * 
     * @see #getDomainAxis(int)
     */
    public void setDomainAxis(int index, CategoryAxis axis) {
        setDomainAxis(index, axis, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[83]++;
    }
 
    /**
     * Sets a domain axis and, if requested, sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     *
     * @param index  the axis index.
     * @param axis  the axis (<code>null</code> permitted).
     * @param notify  notify listeners?
     */
    public void setDomainAxis(int index, CategoryAxis axis, boolean notify) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[84]++;
        CategoryAxis existing = (CategoryAxis) this.domainAxes.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[85]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[19]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[86]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[20]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[87]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[21]++;
            axis.setPlot(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[88]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[22]++;}
        this.domainAxes.set(index, axis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[89]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[90]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[23]++;
            axis.configure();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[91]++;
            axis.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[92]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[24]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[93]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[25]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[94]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[26]++;}
    }

    /**
     * Sets the domain axes for this plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param axes  the axes (<code>null</code> not permitted).
     * 
     * @see #setRangeAxes(ValueAxis[])
     */
    public void setDomainAxes(CategoryAxis[] axes) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[95]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[1]++;


int CodeCoverConditionCoverageHelper_C14;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i < axes.length) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[1]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[2]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[3]++;
}
            setDomainAxis(i, axes[i], false);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[96]++;   
        }
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[97]++;
    }
    
    /**
     * Returns the index of the specified axis, or <code>-1</code> if the axis
     * is not assigned to the plot.
     * 
     * @param axis  the axis (<code>null</code> not permitted).
     * 
     * @return The axis index.
     * 
     * @see #getDomainAxis(int)
     * @see #getRangeAxisIndex(ValueAxis)
     * 
     * @since 1.0.3
     */
    public int getDomainAxisIndex(CategoryAxis axis) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[98]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((axis == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[27]++;
            throw new IllegalArgumentException("Null 'axis' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[28]++;}
        return this.domainAxes.indexOf(axis);
    }
    
    /**
     * Returns the domain axis location for the primary domain axis.
     *
     * @return The location (never <code>null</code>).
     * 
     * @see #getRangeAxisLocation()
     */
    public AxisLocation getDomainAxisLocation() {
        return getDomainAxisLocation(0);
    }

    /**
     * Returns the location for a domain axis.
     *
     * @param index  the axis index.
     *
     * @return The location.
     * 
     * @see #setDomainAxisLocation(int, AxisLocation)
     */
    public AxisLocation getDomainAxisLocation(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[99]++;
        AxisLocation result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[100]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((index < this.domainAxisLocations.size()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[29]++;
            result = (AxisLocation) this.domainAxisLocations.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[101]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[30]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[102]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[31]++;
            result = AxisLocation.getOpposite(getDomainAxisLocation(0));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[103]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[32]++;}
        return result;
    }

    /**
     * Sets the location of the domain axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param location  the axis location (<code>null</code> not permitted).
     * 
     * @see #getDomainAxisLocation()
     * @see #setDomainAxisLocation(int, AxisLocation)
     */
    public void setDomainAxisLocation(AxisLocation location) {
        // delegate...
        setDomainAxisLocation(0, location, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[104]++;
    }

    /**
     * Sets the location of the domain axis and, if requested, sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param location  the axis location (<code>null</code> not permitted).
     * @param notify  a flag that controls whether listeners are notified.
     */
    public void setDomainAxisLocation(AxisLocation location, boolean notify) {
        // delegate...
        setDomainAxisLocation(0, location, notify);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[105]++;
    }

    /**
     * Sets the location for a domain axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param index  the axis index.
     * @param location  the location.
     * 
     * @see #getDomainAxisLocation(int)
     * @see #setRangeAxisLocation(int, AxisLocation)
     */
    public void setDomainAxisLocation(int index, AxisLocation location) {
        // delegate...
        setDomainAxisLocation(index, location, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[106]++;
    }
    
    /**
     * Sets the location for a domain axis and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     * 
     * @param index  the axis index.
     * @param location  the location.
     * @param notify  notify listeners?
     * 
     * @since 1.0.5
     * 
     * @see #getDomainAxisLocation(int)
     * @see #setRangeAxisLocation(int, AxisLocation, boolean)
     */
    public void setDomainAxisLocation(int index, AxisLocation location, 
            boolean notify) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[107]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((location == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[33]++;
            throw new IllegalArgumentException(
                    "Null 'location' for index 0 not permitted.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[34]++;}
        this.domainAxisLocations.set(index, location);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[108]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[109]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[35]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[110]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[36]++;}
    }

    /**
     * Returns the domain axis edge.  This is derived from the axis location
     * and the plot orientation.
     *
     * @return The edge (never <code>null</code>).
     */
    public RectangleEdge getDomainAxisEdge() {
        return getDomainAxisEdge(0);
    }

    /**
     * Returns the edge for a domain axis.
     *
     * @param index  the axis index.
     *
     * @return The edge (never <code>null</code>).
     */
    public RectangleEdge getDomainAxisEdge(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[111]++;
        RectangleEdge result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[112]++;
        AxisLocation location = getDomainAxisLocation(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[113]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((location != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[37]++;
            result = Plot.resolveDomainAxisLocation(location, this.orientation);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[114]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[38]++;
            result = RectangleEdge.opposite(getDomainAxisEdge(0));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[115]++;
        }
        return result;
    }

    /**
     * Returns the number of domain axes.
     *
     * @return The axis count.
     */
    public int getDomainAxisCount() {
        return this.domainAxes.size();
    }

    /**
     * Clears the domain axes from the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     */
    public void clearDomainAxes() {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[116]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[4]++;


int CodeCoverConditionCoverageHelper_C21;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[4]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[5]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[6]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[117]++;
            CategoryAxis axis = (CategoryAxis) this.domainAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[118]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[39]++;
                axis.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[119]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[40]++;}
        }
        this.domainAxes.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[120]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[121]++;
    }

    /**
     * Configures the domain axes.
     */
    public void configureDomainAxes() {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[122]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[7]++;


int CodeCoverConditionCoverageHelper_C23;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[7]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[8]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[9]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[123]++;
            CategoryAxis axis = (CategoryAxis) this.domainAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[124]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[41]++;
                axis.configure();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[125]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[42]++;}
        }
    }

    /**
     * Returns the range axis for the plot.  If the range axis for this plot is
     * null, then the method will return the parent plot's range axis (if there
     * is a parent plot).
     *
     * @return The range axis (possibly <code>null</code>).
     */
    public ValueAxis getRangeAxis() {
        return getRangeAxis(0);
    }

    /**
     * Returns a range axis.
     *
     * @param index  the axis index.
     *
     * @return The axis (<code>null</code> possible).
     */
    public ValueAxis getRangeAxis(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[126]++;
        ValueAxis result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[127]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((index < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[43]++;
            result = (ValueAxis) this.rangeAxes.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[128]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[44]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[129]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[45]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[130]++;
            Plot parent = getParent();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[131]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((parent instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[47]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[132]++;
                CategoryPlot cp = (CategoryPlot) parent;
                result = cp.getRangeAxis(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[133]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[48]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[46]++;}
        return result;
    }

    /**
     * Sets the range axis for the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param axis  the axis (<code>null</code> permitted).
     */
    public void setRangeAxis(ValueAxis axis) {
        setRangeAxis(0, axis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[134]++;
    }

    /**
     * Sets a range axis and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param index  the axis index.
     * @param axis  the axis.
     */
    public void setRangeAxis(int index, ValueAxis axis) {
        setRangeAxis(index, axis, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[135]++;
    }
        
    /**
     * Sets a range axis and, if requested, sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     *
     * @param index  the axis index.
     * @param axis  the axis.
     * @param notify  notify listeners?
     */
    public void setRangeAxis(int index, ValueAxis axis, boolean notify) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[136]++;
        ValueAxis existing = (ValueAxis) this.rangeAxes.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[137]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[49]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[138]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[50]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[139]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[51]++;
            axis.setPlot(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[140]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[52]++;}
        this.rangeAxes.set(index, axis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[141]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[142]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[53]++;
            axis.configure();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[143]++;
            axis.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[144]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[54]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[145]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[55]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[146]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[56]++;}
    }

    /**
     * Sets the range axes for this plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param axes  the axes (<code>null</code> not permitted).
     * 
     * @see #setDomainAxes(CategoryAxis[])
     */
    public void setRangeAxes(ValueAxis[] axes) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[147]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[10]++;


int CodeCoverConditionCoverageHelper_C32;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i < axes.length) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[10]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[11]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[12]++;
}
            setRangeAxis(i, axes[i], false);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[148]++;   
        }
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[149]++;
    }

    /**
     * Returns the index of the specified axis, or <code>-1</code> if the axis
     * is not assigned to the plot.
     *
     * @param axis  the axis (<code>null</code> not permitted).
     *
     * @return The axis index.
     * 
     * @see #getRangeAxis(int)
     * @see #getDomainAxisIndex(CategoryAxis)
     * 
     * @since 1.0.7
     */
    public int getRangeAxisIndex(ValueAxis axis) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[150]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((axis == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[57]++;
            throw new IllegalArgumentException("Null 'axis' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[58]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[151]++;
        int result = this.rangeAxes.indexOf(axis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[152]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[59]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[153]++; // try the parent plot
            Plot parent = getParent();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[154]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((parent instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[61]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[155]++;
                CategoryPlot p = (CategoryPlot) parent;
                result = p.getRangeAxisIndex(axis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[156]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[62]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[60]++;}
        return result;
    }

    /**
     * Returns the range axis location.
     *
     * @return The location (never <code>null</code>).
     */
    public AxisLocation getRangeAxisLocation() {
        return getRangeAxisLocation(0);
    }

    /**
     * Returns the location for a range axis.
     *
     * @param index  the axis index.
     *
     * @return The location.
     * 
     * @see #setRangeAxisLocation(int, AxisLocation)
     */
    public AxisLocation getRangeAxisLocation(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[157]++;
        AxisLocation result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[158]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((index < this.rangeAxisLocations.size()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[63]++;
            result = (AxisLocation) this.rangeAxisLocations.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[159]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[64]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[160]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[65]++;
            result = AxisLocation.getOpposite(getRangeAxisLocation(0));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[161]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[66]++;}
        return result;
    }

    /**
     * Sets the location of the range axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param location  the location (<code>null</code> not permitted).
     * 
     * @see #setRangeAxisLocation(AxisLocation, boolean)
     * @see #setDomainAxisLocation(AxisLocation)
     */
    public void setRangeAxisLocation(AxisLocation location) {
        // defer argument checking...
        setRangeAxisLocation(location, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[162]++;
    }

    /**
     * Sets the location of the range axis and, if requested, sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param location  the location (<code>null</code> not permitted).
     * @param notify  notify listeners?
     * 
     * @see #setDomainAxisLocation(AxisLocation, boolean)
     */
    public void setRangeAxisLocation(AxisLocation location, boolean notify) {
        setRangeAxisLocation(0, location, notify);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[163]++;
    }

    /**
     * Sets the location for a range axis and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     *
     * @param index  the axis index.
     * @param location  the location.
     * 
     * @see #getRangeAxisLocation(int)
     * @see #setRangeAxisLocation(int, AxisLocation, boolean)
     */
    public void setRangeAxisLocation(int index, AxisLocation location) {
        setRangeAxisLocation(index, location, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[164]++;
    }

    /**
     * Sets the location for a range axis and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     *
     * @param index  the axis index.
     * @param location  the location.
     * @param notify  notify listeners?
     * 
     * @see #getRangeAxisLocation(int)
     * @see #setDomainAxisLocation(int, AxisLocation, boolean)
     */
    public void setRangeAxisLocation(int index, AxisLocation location, 
                                     boolean notify) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[165]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((location == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[67]++;
            throw new IllegalArgumentException(
                    "Null 'location' for index 0 not permitted.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[68]++;}
        this.rangeAxisLocations.set(index, location);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[166]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[167]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[69]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[168]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[70]++;}
    }

    /**
     * Returns the edge where the primary range axis is located.
     *
     * @return The edge (never <code>null</code>).
     */
    public RectangleEdge getRangeAxisEdge() {
        return getRangeAxisEdge(0);
    }

    /**
     * Returns the edge for a range axis.
     *
     * @param index  the axis index.
     *
     * @return The edge.
     */
    public RectangleEdge getRangeAxisEdge(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[169]++;
        AxisLocation location = getRangeAxisLocation(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[170]++;
        RectangleEdge result = Plot.resolveRangeAxisLocation(location, 
                this.orientation);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[171]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[71]++;
            result = RectangleEdge.opposite(getRangeAxisEdge(0));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[172]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[72]++;}
        return result;
    }

    /**
     * Returns the number of range axes.
     *
     * @return The axis count.
     */
    public int getRangeAxisCount() {
        return this.rangeAxes.size();
    }

    /**
     * Clears the range axes from the plot and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     */
    public void clearRangeAxes() {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[173]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[13]++;


int CodeCoverConditionCoverageHelper_C41;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[13]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[14]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[15]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[174]++;
            ValueAxis axis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[175]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[73]++;
                axis.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[176]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[74]++;}
        }
        this.rangeAxes.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[177]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[178]++;
    }

    /**
     * Configures the range axes.
     */
    public void configureRangeAxes() {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[179]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[16]++;


int CodeCoverConditionCoverageHelper_C43;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[16]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[17]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[18]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[180]++;
            ValueAxis axis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[181]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[75]++;
                axis.configure();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[182]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[76]++;}
        }
    }

    /**
     * Returns the primary dataset for the plot.
     *
     * @return The primary dataset (possibly <code>null</code>).
     * 
     * @see #setDataset(CategoryDataset)
     */
    public CategoryDataset getDataset() {
        return getDataset(0);
    }

    /**
     * Returns the dataset at the given index.
     *
     * @param index  the dataset index.
     *
     * @return The dataset (possibly <code>null</code>).
     * 
     * @see #setDataset(int, CategoryDataset)
     */
    public CategoryDataset getDataset(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[183]++;
        CategoryDataset result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[184]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.datasets.size() > index) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[77]++;
            result = (CategoryDataset) this.datasets.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[185]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[78]++;}
        return result;
    }

    /**
     * Sets the dataset for the plot, replacing the existing dataset, if there 
     * is one.  This method also calls the 
     * {@link #datasetChanged(DatasetChangeEvent)} method, which adjusts the 
     * axis ranges if necessary and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset()
     */
    public void setDataset(CategoryDataset dataset) {
        setDataset(0, dataset);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[186]++;
    }

    /**
     * Sets a dataset for the plot.
     *
     * @param index  the dataset index.
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset(int)
     */
    public void setDataset(int index, CategoryDataset dataset) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[187]++;
        
        CategoryDataset existing = (CategoryDataset) this.datasets.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[188]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[79]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[189]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[80]++;}
        this.datasets.set(index, dataset);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[190]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[191]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[81]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[192]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[82]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[193]++;
        
        // send a dataset change event to self...
        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[194]++;
        
    }

    /**
     * Returns the number of datasets.
     *
     * @return The number of datasets.
     * 
     * @since 1.0.2
     */
    public int getDatasetCount() {
        return this.datasets.size();
    }

    /**
     * Maps a dataset to a particular domain axis.
     * 
     * @param index  the dataset index (zero-based).
     * @param axisIndex  the axis index (zero-based).
     * 
     * @see #getDomainAxisForDataset(int)
     */
    public void mapDatasetToDomainAxis(int index, int axisIndex) {
        this.datasetToDomainAxisMap.set(index, new Integer(axisIndex));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[195]++;  
        // fake a dataset change event to update axes...
        datasetChanged(new DatasetChangeEvent(this, getDataset(index)));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[196]++;  
    }

    /**
     * Returns the domain axis for a dataset.  You can change the axis for a 
     * dataset using the {@link #mapDatasetToDomainAxis(int, int)} method.
     * 
     * @param index  the dataset index.
     * 
     * @return The domain axis.
     * 
     * @see #mapDatasetToDomainAxis(int, int)
     */
    public CategoryAxis getDomainAxisForDataset(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[197]++;
        CategoryAxis result = getDomainAxis();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[198]++;
        Integer axisIndex = (Integer) this.datasetToDomainAxisMap.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[199]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((axisIndex != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[83]++;
            result = getDomainAxis(axisIndex.intValue());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[200]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[84]++;}
        return result;    
    }
    
    /**
     * Maps a dataset to a particular range axis.
     * 
     * @param index  the dataset index (zero-based).
     * @param axisIndex  the axis index (zero-based).
     * 
     * @see #getRangeAxisForDataset(int)
     */
    public void mapDatasetToRangeAxis(int index, int axisIndex) {
        this.datasetToRangeAxisMap.set(index, new Integer(axisIndex));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[201]++;
        // fake a dataset change event to update axes...
        datasetChanged(new DatasetChangeEvent(this, getDataset(index)));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[202]++;  
    }

    /**
     * Returns the range axis for a dataset.  You can change the axis for a 
     * dataset using the {@link #mapDatasetToRangeAxis(int, int)} method.
     * 
     * @param index  the dataset index.
     * 
     * @return The range axis.
     * 
     * @see #mapDatasetToRangeAxis(int, int)
     */
    public ValueAxis getRangeAxisForDataset(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[203]++;
        ValueAxis result = getRangeAxis();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[204]++;
        Integer axisIndex = (Integer) this.datasetToRangeAxisMap.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[205]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((axisIndex != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[85]++;
            result = getRangeAxis(axisIndex.intValue());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[206]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[86]++;}
        return result;    
    }
    
    /**
     * Returns a reference to the renderer for the plot.
     *
     * @return The renderer.
     * 
     * @see #setRenderer(CategoryItemRenderer)
     */
    public CategoryItemRenderer getRenderer() {
        return getRenderer(0);
    }

    /**
     * Returns the renderer at the given index.
     *
     * @param index  the renderer index.
     *
     * @return The renderer (possibly <code>null</code>).
     * 
     * @see #setRenderer(int, CategoryItemRenderer)
     */
    public CategoryItemRenderer getRenderer(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[207]++;
        CategoryItemRenderer result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[208]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.renderers.size() > index) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[87]++;
            result = (CategoryItemRenderer) this.renderers.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[209]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[88]++;}
        return result;
    }
    
    /**
     * Sets the renderer at index 0 (sometimes referred to as the "primary" 
     * renderer) and sends a {@link PlotChangeEvent} to all registered 
     * listeners.
     *
     * @param renderer  the renderer (<code>null</code> permitted.
     * 
     * @see #getRenderer()
     */
    public void setRenderer(CategoryItemRenderer renderer) {
        setRenderer(0, renderer, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[210]++;
    }

    /**
     * Sets the renderer at index 0 (sometimes referred to as the "primary" 
     * renderer) and, if requested, sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * <p>
     * You can set the renderer to <code>null</code>, but this is not 
     * recommended because:
     * <ul>
     *   <li>no data will be displayed;</li>
     *   <li>the plot background will not be painted;</li>
     * </ul>
     *
     * @param renderer  the renderer (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getRenderer()
     */
    public void setRenderer(CategoryItemRenderer renderer, boolean notify) {
        setRenderer(0, renderer, notify);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[211]++;
    }

    /**
     * Sets the renderer at the specified index and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param index  the index.
     * @param renderer  the renderer (<code>null</code> permitted).
     * 
     * @see #getRenderer(int)
     * @see #setRenderer(int, CategoryItemRenderer, boolean)
     */
    public void setRenderer(int index, CategoryItemRenderer renderer) {
        setRenderer(index, renderer, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[212]++;   
    }

    /**
     * Sets a renderer.  A {@link PlotChangeEvent} is sent to all registered 
     * listeners.
     *
     * @param index  the index.
     * @param renderer  the renderer (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getRenderer(int)
     */
    public void setRenderer(int index, CategoryItemRenderer renderer, 
                            boolean notify) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[213]++;
        
        // stop listening to the existing renderer...
        CategoryItemRenderer existing 
            = (CategoryItemRenderer) this.renderers.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[214]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[89]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[215]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[90]++;}
        
        // register the new renderer...
        this.renderers.set(index, renderer);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[216]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[217]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[91]++;
            renderer.setPlot(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[218]++;
            renderer.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[219]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[92]++;}
        
        configureDomainAxes();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[220]++;
        configureRangeAxes();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[221]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[222]++;
int CodeCoverConditionCoverageHelper_C53;
        
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[93]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[223]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[94]++;}
    }

    /**
     * Sets the renderers for this plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param renderers  the renderers.
     */
    public void setRenderers(CategoryItemRenderer[] renderers) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[224]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[19]++;


int CodeCoverConditionCoverageHelper_C54;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i < renderers.length) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[19]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[20]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[21]++;
}
            setRenderer(i, renderers[i], false);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[225]++;   
        }
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[226]++;
    }
    
    /**
     * Returns the renderer for the specified dataset.  If the dataset doesn't
     * belong to the plot, this method will return <code>null</code>.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @return The renderer (possibly <code>null</code>).
     */
    public CategoryItemRenderer getRendererForDataset(CategoryDataset dataset) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[227]++;
        CategoryItemRenderer result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[228]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[22]++;


int CodeCoverConditionCoverageHelper_C55;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((i < this.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[22]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[23]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[24]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[229]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((this.datasets.get(i) == dataset) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[95]++;
                result = (CategoryItemRenderer) this.renderers.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[230]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[231]++;   
                break;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[96]++;}
        }
        return result;
    }
    
    /**
     * Returns the index of the specified renderer, or <code>-1</code> if the
     * renderer is not assigned to this plot.
     * 
     * @param renderer  the renderer (<code>null</code> permitted).
     * 
     * @return The renderer index.
     */
    public int getIndexOf(CategoryItemRenderer renderer) {
        return this.renderers.indexOf(renderer);
    }

    /**
     * Returns the dataset rendering order.
     *
     * @return The order (never <code>null</code>).
     * 
     * @see #setDatasetRenderingOrder(DatasetRenderingOrder)
     */
    public DatasetRenderingOrder getDatasetRenderingOrder() {
        return this.renderingOrder;
    }

    /**
     * Sets the rendering order and sends a {@link PlotChangeEvent} to all 
     * registered listeners.  By default, the plot renders the primary dataset 
     * last (so that the primary dataset overlays the secondary datasets).  You 
     * can reverse this if you want to.
     *
     * @param order  the rendering order (<code>null</code> not permitted).
     * 
     * @see #getDatasetRenderingOrder()
     */
    public void setDatasetRenderingOrder(DatasetRenderingOrder order) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[232]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[97]++;
            throw new IllegalArgumentException("Null 'order' argument.");
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[98]++;}
        this.renderingOrder = order;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[233]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[234]++;
    }

    /**
     * Returns the order in which the columns are rendered.  The default value
     * is <code>SortOrder.ASCENDING</code>.
     * 
     * @return The column rendering order (never <code>null</code).
     * 
     * @see #setColumnRenderingOrder(SortOrder)
     */    
    public SortOrder getColumnRenderingOrder() {
        return this.columnRenderingOrder;
    }
    
    /**
     * Sets the column order in which the items in each dataset should be 
     * rendered and sends a {@link PlotChangeEvent} to all registered 
     * listeners.  Note that this affects the order in which items are drawn, 
     * NOT their position in the chart.
     * 
     * @param order  the order (<code>null</code> not permitted).
     * 
     * @see #getColumnRenderingOrder()
     * @see #setRowRenderingOrder(SortOrder)
     */
    public void setColumnRenderingOrder(SortOrder order) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[235]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[99]++;
            throw new IllegalArgumentException("Null 'order' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[100]++;}
        this.columnRenderingOrder = order;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[236]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[237]++;
    }
    
    /**
     * Returns the order in which the rows should be rendered.  The default 
     * value is <code>SortOrder.ASCENDING</code>.
     * 
     * @return The order (never <code>null</code>).
     * 
     * @see #setRowRenderingOrder(SortOrder)
     */
    public SortOrder getRowRenderingOrder() {
        return this.rowRenderingOrder;
    }

    /**
     * Sets the row order in which the items in each dataset should be 
     * rendered and sends a {@link PlotChangeEvent} to all registered 
     * listeners.  Note that this affects the order in which items are drawn, 
     * NOT their position in the chart.
     * 
     * @param order  the order (<code>null</code> not permitted).
     * 
     * @see #getRowRenderingOrder()
     * @see #setColumnRenderingOrder(SortOrder)
     */
    public void setRowRenderingOrder(SortOrder order) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[238]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[101]++;
            throw new IllegalArgumentException("Null 'order' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[102]++;}
        this.rowRenderingOrder = order;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[239]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[240]++;
    }
    
    /**
     * Returns the flag that controls whether the domain grid-lines are visible.
     *
     * @return The <code>true</code> or <code>false</code>.
     * 
     * @see #setDomainGridlinesVisible(boolean)
     */
    public boolean isDomainGridlinesVisible() {
        return this.domainGridlinesVisible;
    }

    /**
     * Sets the flag that controls whether or not grid-lines are drawn against 
     * the domain axis.
     * <p>
     * If the flag value changes, a {@link PlotChangeEvent} is sent to all 
     * registered listeners.
     *
     * @param visible  the new value of the flag.
     * 
     * @see #isDomainGridlinesVisible()
     */
    public void setDomainGridlinesVisible(boolean visible) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[241]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((this.domainGridlinesVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[103]++;
            this.domainGridlinesVisible = visible;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[242]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[243]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[104]++;}
    }

    /**
     * Returns the position used for the domain gridlines.
     * 
     * @return The gridline position (never <code>null</code>).
     * 
     * @see #setDomainGridlinePosition(CategoryAnchor)
     */
    public CategoryAnchor getDomainGridlinePosition() {
        return this.domainGridlinePosition;
    }

    /**
     * Sets the position used for the domain gridlines and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param position  the position (<code>null</code> not permitted).
     * 
     * @see #getDomainGridlinePosition()
     */
    public void setDomainGridlinePosition(CategoryAnchor position) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[244]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[105]++;
            throw new IllegalArgumentException("Null 'position' argument.");
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[106]++;}
        this.domainGridlinePosition = position;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[245]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[246]++;
    }

    /**
     * Returns the stroke used to draw grid-lines against the domain axis.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setDomainGridlineStroke(Stroke)
     */
    public Stroke getDomainGridlineStroke() {
        return this.domainGridlineStroke;
    }

    /**
     * Sets the stroke used to draw grid-lines against the domain axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getDomainGridlineStroke()
     */
    public void setDomainGridlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[247]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[107]++;
            throw new IllegalArgumentException("Null 'stroke' not permitted.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[108]++;}
        this.domainGridlineStroke = stroke;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[248]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[249]++;
    }

    /**
     * Returns the paint used to draw grid-lines against the domain axis.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setDomainGridlinePaint(Paint)
     */
    public Paint getDomainGridlinePaint() {
        return this.domainGridlinePaint;
    }

    /**
     * Sets the paint used to draw the grid-lines (if any) against the domain 
     * axis and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getDomainGridlinePaint()
     */
    public void setDomainGridlinePaint(Paint paint) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[250]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[109]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[110]++;}
        this.domainGridlinePaint = paint;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[251]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[252]++;
    }

    /**
     * Returns the flag that controls whether the range grid-lines are visible.
     *
     * @return The flag.
     * 
     * @see #setRangeGridlinesVisible(boolean)
     */
    public boolean isRangeGridlinesVisible() {
        return this.rangeGridlinesVisible;
    }

    /**
     * Sets the flag that controls whether or not grid-lines are drawn against 
     * the range axis.  If the flag changes value, a {@link PlotChangeEvent} is 
     * sent to all registered listeners.
     *
     * @param visible  the new value of the flag.
     * 
     * @see #isRangeGridlinesVisible()
     */
    public void setRangeGridlinesVisible(boolean visible) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[253]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((this.rangeGridlinesVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[111]++;
            this.rangeGridlinesVisible = visible;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[254]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[255]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[112]++;}
    }

    /**
     * Returns the stroke used to draw the grid-lines against the range axis.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setRangeGridlineStroke(Stroke)
     */
    public Stroke getRangeGridlineStroke() {
        return this.rangeGridlineStroke;
    }

    /**
     * Sets the stroke used to draw the grid-lines against the range axis and 
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getRangeGridlineStroke()
     */
    public void setRangeGridlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[256]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[113]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[114]++;}
        this.rangeGridlineStroke = stroke;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[257]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[258]++;
    }

    /**
     * Returns the paint used to draw the grid-lines against the range axis.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setRangeGridlinePaint(Paint)
     */
    public Paint getRangeGridlinePaint() {
        return this.rangeGridlinePaint;
    }

    /**
     * Sets the paint used to draw the grid lines against the range axis and 
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getRangeGridlinePaint()
     */
    public void setRangeGridlinePaint(Paint paint) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[259]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[115]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[116]++;}
        this.rangeGridlinePaint = paint;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[260]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[261]++;
    }
    
    /**
     * Returns the fixed legend items, if any.
     * 
     * @return The legend items (possibly <code>null</code>).
     * 
     * @see #setFixedLegendItems(LegendItemCollection)
     */
    public LegendItemCollection getFixedLegendItems() {
        return this.fixedLegendItems;   
    }

    /**
     * Sets the fixed legend items for the plot.  Leave this set to 
     * <code>null</code> if you prefer the legend items to be created 
     * automatically.
     * 
     * @param items  the legend items (<code>null</code> permitted).
     * 
     * @see #getFixedLegendItems()
     */
    public void setFixedLegendItems(LegendItemCollection items) {
        this.fixedLegendItems = items;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[262]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[263]++;
    }
    
    /**
     * Returns the legend items for the plot.  By default, this method creates 
     * a legend item for each series in each of the datasets.  You can change 
     * this behaviour by overriding this method.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[264]++;
        LegendItemCollection result = this.fixedLegendItems;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[265]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[117]++;
            result = new LegendItemCollection();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[266]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[267]++;
            // get the legend items for the datasets...
            int count = this.datasets.size();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[268]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[25]++;


int CodeCoverConditionCoverageHelper_C68;
            for (int datasetIndex = 0;(((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((datasetIndex < count) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false); datasetIndex++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[25]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[26]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[27]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[269]++;
                CategoryDataset dataset = getDataset(datasetIndex);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[270]++;
int CodeCoverConditionCoverageHelper_C69;
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[119]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[271]++;
                    CategoryItemRenderer renderer = getRenderer(datasetIndex);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[272]++;
int CodeCoverConditionCoverageHelper_C70;
                    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[121]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[273]++;
                        int seriesCount = dataset.getRowCount();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[274]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[28]++;


int CodeCoverConditionCoverageHelper_C71;
                        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((i < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[28]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[29]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[30]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[275]++;
                            LegendItem item = renderer.getLegendItem(
                                    datasetIndex, i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[276]++;
int CodeCoverConditionCoverageHelper_C72;
                            if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[123]++;
                                result.add(item);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[277]++;

                            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[124]++;}
                        }

                    } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[122]++;}

                } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[120]++;}
            }

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[118]++;}
        return result;
    }

    /**
     * Handles a 'click' on the plot by updating the anchor value.
     *
     * @param x  x-coordinate of the click (in Java2D space).
     * @param y  y-coordinate of the click (in Java2D space).
     * @param info  information about the plot's dimensions.
     *
     */
    public void handleClick(int x, int y, PlotRenderingInfo info) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[278]++;

        Rectangle2D dataArea = info.getDataArea();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[279]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((dataArea.contains(x, y)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[125]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[280]++;
            // set the anchor value for the range axis...
            double java2D = 0.0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[281]++;
int CodeCoverConditionCoverageHelper_C74;
            if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[127]++;
                java2D = x;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[282]++;

            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[128]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[283]++;
int CodeCoverConditionCoverageHelper_C75; if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[129]++;
                java2D = y;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[284]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[130]++;}
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[285]++;
            RectangleEdge edge = Plot.resolveRangeAxisLocation(
                    getRangeAxisLocation(), this.orientation);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[286]++;
            double value = getRangeAxis().java2DToValue(
                    java2D, info.getDataArea(), edge);
            setAnchorValue(value);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[287]++;
            setRangeCrosshairValue(value);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[288]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[126]++;}

    }

    /**
     * Zooms (in or out) on the plot's value axis.
     * <p>
     * If the value 0.0 is passed in as the zoom percent, the auto-range
     * calculation for the axis is restored (which sets the range to include
     * the minimum and maximum data values, thus displaying all the data).
     *
     * @param percent  the zoom amount.
     */
    public void zoom(double percent) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[289]++;
int CodeCoverConditionCoverageHelper_C76;

        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((percent > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[131]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[290]++;
            double range = getRangeAxis().getRange().getLength();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[291]++;
            double scaledRange = range * percent;
            getRangeAxis().setRange(this.anchorValue - scaledRange / 2.0,
                    this.anchorValue + scaledRange / 2.0);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[292]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[132]++;
            getRangeAxis().setAutoRange(true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[293]++;
        }

    }

    /**
     * Receives notification of a change to the plot's dataset.
     * <P>
     * The range axis bounds will be recalculated if necessary.
     *
     * @param event  information about the event (not used here).
     */
    public void datasetChanged(DatasetChangeEvent event) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[294]++;

        int count = this.rangeAxes.size();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[295]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[31]++;


int CodeCoverConditionCoverageHelper_C77;
        for (int axisIndex = 0;(((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((axisIndex < count) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false); axisIndex++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[31]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[32]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[33]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[296]++;
            ValueAxis yAxis = getRangeAxis(axisIndex);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[297]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((yAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[133]++;
                yAxis.configure();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[298]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[134]++;}
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[299]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[135]++;
            getParent().datasetChanged(event);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[300]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[136]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[301]++;
            PlotChangeEvent e = new PlotChangeEvent(this);
            e.setType(ChartChangeEventType.DATASET_UPDATED);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[302]++;
            notifyListeners(e);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[303]++;
        }

    }

    /**
     * Receives notification of a renderer change event.
     *
     * @param event  the event.
     */
    public void rendererChanged(RendererChangeEvent event) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[304]++;
        Plot parent = getParent();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[305]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[137]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[306]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((parent instanceof RendererChangeListener) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[139]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[307]++;
                RendererChangeListener rcl = (RendererChangeListener) parent;
                rcl.rendererChanged(event);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[308]++;

            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[140]++;
                // this should never happen with the existing code, but throw 
                // an exception in case future changes make it possible...
                throw new RuntimeException(
                    "The renderer has changed and I don't know what to do!");
            }

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[138]++;
            configureRangeAxes();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[309]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[310]++;
            PlotChangeEvent e = new PlotChangeEvent(this);
            notifyListeners(e);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[311]++;
        }
    }
    
    /**
     * Adds a marker for display (in the foreground) against the domain axis and
     * sends a {@link PlotChangeEvent} to all registered listeners. Typically a 
     * marker will be drawn by the renderer as a line perpendicular to the 
     * domain axis, however this is entirely up to the renderer.
     *
     * @param marker  the marker (<code>null</code> not permitted).
     */
    public void addDomainMarker(CategoryMarker marker) {
        addDomainMarker(marker, Layer.FOREGROUND);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[312]++; 
    }
        
    /**
     * Adds a marker for display against the domain axis and sends a 
     * {@link PlotChangeEvent} to all registered listeners.  Typically a marker 
     * will be drawn by the renderer as a line perpendicular to the domain 
     * axis, however this is entirely up to the renderer.
     *
     * @param marker  the marker (<code>null</code> not permitted).
     * @param layer  the layer (foreground or background) (<code>null</code> 
     *               not permitted).
     */
    public void addDomainMarker(CategoryMarker marker, Layer layer) {
        addDomainMarker(0, marker, layer);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[313]++;
    }

    /**
     * Adds a marker for display by a particular renderer.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to a domain axis, however this is entirely up to the renderer.
     *
     * @param index  the renderer index.
     * @param marker  the marker (<code>null</code> not permitted).
     * @param layer  the layer (<code>null</code> not permitted).
     */
    public void addDomainMarker(int index, CategoryMarker marker, Layer layer) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[314]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((marker == null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[141]++;
            throw new IllegalArgumentException("Null 'marker' not permitted.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[142]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[315]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((layer == null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[143]++;
            throw new IllegalArgumentException("Null 'layer' not permitted.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[144]++;}
        Collection markers;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[316]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[145]++;
            markers = (Collection) this.foregroundDomainMarkers.get(
                    new Integer(index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[317]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[318]++;
int CodeCoverConditionCoverageHelper_C85;
            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((markers == null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[147]++;
                markers = new java.util.ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[319]++;
                this.foregroundDomainMarkers.put(new Integer(index), markers);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[320]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[148]++;}
            markers.add(marker);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[321]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[146]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[322]++;
int CodeCoverConditionCoverageHelper_C86; if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((layer == Layer.BACKGROUND) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[149]++;
            markers = (Collection) this.backgroundDomainMarkers.get(
                    new Integer(index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[323]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[324]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((markers == null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[151]++;
                markers = new java.util.ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[325]++;
                this.backgroundDomainMarkers.put(new Integer(index), markers);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[326]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[152]++;}
            markers.add(marker);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[327]++;
            
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[150]++;}
}
        marker.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[328]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[329]++;
    }

    /**
     * Clears all the domain markers for the plot and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @see #clearRangeMarkers()
     */
    public void clearDomainMarkers() {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[330]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((this.backgroundDomainMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[153]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[331]++;
            Set keys = this.backgroundDomainMarkers.keySet();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[332]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[333]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[34]++;


int CodeCoverConditionCoverageHelper_C89;
            while ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[34]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[35]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[36]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[334]++;
                Integer key = (Integer) iterator.next();
                clearDomainMarkers(key.intValue());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[335]++;
            }
            this.backgroundDomainMarkers.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[336]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[154]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[337]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((this.foregroundDomainMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[155]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[338]++;
            Set keys = this.foregroundDomainMarkers.keySet();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[339]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[340]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[37]++;


int CodeCoverConditionCoverageHelper_C91;
            while ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[37]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[38]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[39]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[341]++;
                Integer key = (Integer) iterator.next();
                clearDomainMarkers(key.intValue());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[342]++;
            }
            this.foregroundDomainMarkers.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[343]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[156]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[344]++;
    }

    /**
     * Returns the list of domain markers (read only) for the specified layer.
     *
     * @param layer  the layer (foreground or background).
     * 
     * @return The list of domain markers.
     */
    public Collection getDomainMarkers(Layer layer) {
        return getDomainMarkers(0, layer);
    }

    /**
     * Returns a collection of domain markers for a particular renderer and 
     * layer.
     * 
     * @param index  the renderer index.
     * @param layer  the layer.
     * 
     * @return A collection of markers (possibly <code>null</code>).
     */
    public Collection getDomainMarkers(int index, Layer layer) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[345]++;
        Collection result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[346]++;
        Integer key = new Integer(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[347]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[157]++;
            result = (Collection) this.foregroundDomainMarkers.get(key);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[348]++;

        }    
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[158]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[349]++;
int CodeCoverConditionCoverageHelper_C93; if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((layer == Layer.BACKGROUND) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[159]++;
            result = (Collection) this.backgroundDomainMarkers.get(key);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[350]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[160]++;}
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[351]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[161]++;
            result = Collections.unmodifiableCollection(result);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[352]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[162]++;}
        return result;
    }
    
    /**
     * Clears all the domain markers for the specified renderer.
     * 
     * @param index  the renderer index.
     * 
     * @see #clearRangeMarkers(int)
     */
    public void clearDomainMarkers(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[353]++;
        Integer key = new Integer(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[354]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((this.backgroundDomainMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[163]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[355]++;
            Collection markers 
                = (Collection) this.backgroundDomainMarkers.get(key);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[356]++;
int CodeCoverConditionCoverageHelper_C96;
            if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[165]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[357]++;
                Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[358]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[40]++;


int CodeCoverConditionCoverageHelper_C97;
                while ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[40]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[41]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[42]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[359]++;
                    Marker m = (Marker) iterator.next();
                    m.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[360]++;
                }
                markers.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[361]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[166]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[164]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[362]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((this.foregroundDomainMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[167]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[363]++;
            Collection markers 
                = (Collection) this.foregroundDomainMarkers.get(key);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[364]++;
int CodeCoverConditionCoverageHelper_C99;
            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[169]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[365]++;
                Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[366]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[43]++;


int CodeCoverConditionCoverageHelper_C100;
                while ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[43]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[44]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[45]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[367]++;
                    Marker m = (Marker) iterator.next();
                    m.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[368]++;
                }
                markers.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[369]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[170]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[168]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[370]++;
    }
    
    /**
     * Removes a marker for the domain axis and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     *
     * @param marker  the marker.
     *
     * @return A boolean indicating whether or not the marker was actually 
     *         removed.
     *
     * @since 1.0.7
     */
    public boolean removeDomainMarker(Marker marker) {
        return removeDomainMarker(marker, Layer.FOREGROUND);
    }

    /**
     * Removes a marker for the domain axis in the specified layer and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param marker the marker (<code>null</code> not permitted).
     * @param layer the layer (foreground or background).
     *
     * @return A boolean indicating whether or not the marker was actually 
     *         removed.
     *
     * @since 1.0.7
     */
    public boolean removeDomainMarker(Marker marker, Layer layer) {
        return removeDomainMarker(0, marker, layer);
    }

    /**
     * Removes a marker for a specific dataset/renderer and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param index the dataset/renderer index.
     * @param marker the marker.
     * @param layer the layer (foreground or background).
     *
     * @return A boolean indicating whether or not the marker was actually 
     *         removed.
     *
     * @since 1.0.7
     */
    public boolean removeDomainMarker(int index, Marker marker, Layer layer) {
        ArrayList markers;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[371]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[171]++;
            markers = (ArrayList) this.foregroundDomainMarkers.get(new Integer(
                    index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[372]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[172]++;
            markers = (ArrayList) this.backgroundDomainMarkers.get(new Integer(
                    index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[373]++;
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[374]++;
        boolean removed = markers.remove(marker);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[375]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[173]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[376]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[174]++;}
        return removed;
    }
    
    /**
     * Adds a marker for display (in the foreground) against the range axis and
     * sends a {@link PlotChangeEvent} to all registered listeners. Typically a 
     * marker will be drawn by the renderer as a line perpendicular to the 
     * range axis, however this is entirely up to the renderer.
     *
     * @param marker  the marker (<code>null</code> not permitted).
     */
    public void addRangeMarker(Marker marker) {
        addRangeMarker(marker, Layer.FOREGROUND);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[377]++; 
    }
        
    /**
     * Adds a marker for display against the range axis and sends a 
     * {@link PlotChangeEvent} to all registered listeners.  Typically a marker 
     * will be drawn by the renderer as a line perpendicular to the range axis, 
     * however this is entirely up to the renderer.
     *
     * @param marker  the marker (<code>null</code> not permitted).
     * @param layer  the layer (foreground or background) (<code>null</code> 
     *               not permitted).
     */
    public void addRangeMarker(Marker marker, Layer layer) {
        addRangeMarker(0, marker, layer);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[378]++;
    }

    /**
     * Adds a marker for display by a particular renderer.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to a range axis, however this is entirely up to the renderer.
     *
     * @param index  the renderer index.
     * @param marker  the marker.
     * @param layer  the layer.
     */
    public void addRangeMarker(int index, Marker marker, Layer layer) {
        Collection markers;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[379]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[175]++;
            markers = (Collection) this.foregroundRangeMarkers.get(
                    new Integer(index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[380]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[381]++;
int CodeCoverConditionCoverageHelper_C104;
            if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((markers == null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[177]++;
                markers = new java.util.ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[382]++;
                this.foregroundRangeMarkers.put(new Integer(index), markers);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[383]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[178]++;}
            markers.add(marker);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[384]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[176]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[385]++;
int CodeCoverConditionCoverageHelper_C105; if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((layer == Layer.BACKGROUND) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[179]++;
            markers = (Collection) this.backgroundRangeMarkers.get(
                    new Integer(index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[386]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[387]++;
int CodeCoverConditionCoverageHelper_C106;
            if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((markers == null) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[181]++;
                markers = new java.util.ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[388]++;
                this.backgroundRangeMarkers.put(new Integer(index), markers);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[389]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[182]++;}
            markers.add(marker);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[390]++;
            
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[180]++;}
}
        marker.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[391]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[392]++;
    }

    /**
     * Clears all the range markers for the plot and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @see #clearDomainMarkers()
     */
    public void clearRangeMarkers() {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[393]++;
int CodeCoverConditionCoverageHelper_C107;
        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((this.backgroundRangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[183]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[394]++;
            Set keys = this.backgroundRangeMarkers.keySet();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[395]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[396]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[46]++;


int CodeCoverConditionCoverageHelper_C108;
            while ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[46]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[47]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[48]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[397]++;
                Integer key = (Integer) iterator.next();
                clearRangeMarkers(key.intValue());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[398]++;
            }
            this.backgroundRangeMarkers.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[399]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[184]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[400]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((this.foregroundRangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[185]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[401]++;
            Set keys = this.foregroundRangeMarkers.keySet();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[402]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[403]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[49]++;


int CodeCoverConditionCoverageHelper_C110;
            while ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[49]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[50]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[51]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[404]++;
                Integer key = (Integer) iterator.next();
                clearRangeMarkers(key.intValue());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[405]++;
            }
            this.foregroundRangeMarkers.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[406]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[186]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[407]++;
    }

    /**
     * Returns the list of range markers (read only) for the specified layer.
     *
     * @param layer  the layer (foreground or background).
     * 
     * @return The list of range markers.
     * 
     * @see #getRangeMarkers(int, Layer)
     */
    public Collection getRangeMarkers(Layer layer) {
        return getRangeMarkers(0, layer);
    }

    /**
     * Returns a collection of range markers for a particular renderer and 
     * layer.
     * 
     * @param index  the renderer index.
     * @param layer  the layer.
     * 
     * @return A collection of markers (possibly <code>null</code>).
     */
    public Collection getRangeMarkers(int index, Layer layer) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[408]++;
        Collection result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[409]++;
        Integer key = new Integer(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[410]++;
int CodeCoverConditionCoverageHelper_C111;
        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[187]++;
            result = (Collection) this.foregroundRangeMarkers.get(key);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[411]++;

        }    
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[188]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[412]++;
int CodeCoverConditionCoverageHelper_C112; if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((layer == Layer.BACKGROUND) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[189]++;
            result = (Collection) this.backgroundRangeMarkers.get(key);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[413]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[190]++;}
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[414]++;
int CodeCoverConditionCoverageHelper_C113;
        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[191]++;
            result = Collections.unmodifiableCollection(result);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[415]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[192]++;}
        return result;
    }
    
    /**
     * Clears all the range markers for the specified renderer.
     * 
     * @param index  the renderer index.
     * 
     * @see #clearDomainMarkers(int)
     */
    public void clearRangeMarkers(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[416]++;
        Integer key = new Integer(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[417]++;
int CodeCoverConditionCoverageHelper_C114;
        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((this.backgroundRangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[193]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[418]++;
            Collection markers 
                = (Collection) this.backgroundRangeMarkers.get(key);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[419]++;
int CodeCoverConditionCoverageHelper_C115;
            if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[195]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[420]++;
                Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[421]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[52]++;


int CodeCoverConditionCoverageHelper_C116;
                while ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[52]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[53]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[54]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[422]++;
                    Marker m = (Marker) iterator.next();
                    m.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[423]++;
                }
                markers.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[424]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[196]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[194]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[425]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((this.foregroundRangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[197]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[426]++;
            Collection markers 
                = (Collection) this.foregroundRangeMarkers.get(key);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[427]++;
int CodeCoverConditionCoverageHelper_C118;
            if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[199]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[428]++;
                Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[429]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[55]++;


int CodeCoverConditionCoverageHelper_C119;
                while ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[55]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[56]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[57]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[430]++;
                    Marker m = (Marker) iterator.next();
                    m.removeChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[431]++;
                }
                markers.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[432]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[200]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[198]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[433]++;
    }

    /**
     * Removes a marker for the range axis and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     *
     * @param marker the marker.
     *
     * @return A boolean indicating whether or not the marker was actually 
     *         removed.
     *
     * @since 1.0.7
     */
    public boolean removeRangeMarker(Marker marker) {
        return removeRangeMarker(marker, Layer.FOREGROUND);
    }

    /**
     * Removes a marker for the range axis in the specified layer and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param marker the marker (<code>null</code> not permitted).
     * @param layer the layer (foreground or background).
     *
     * @return A boolean indicating whether or not the marker was actually 
     *         removed.
     *
     * @since 1.0.7
     */
    public boolean removeRangeMarker(Marker marker, Layer layer) {
        return removeRangeMarker(0, marker, layer);
    }

    /**
     * Removes a marker for a specific dataset/renderer and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param index the dataset/renderer index.
     * @param marker the marker.
     * @param layer the layer (foreground or background).
     *
     * @return A boolean indicating whether or not the marker was actually 
     *         removed.
     *
     * @since 1.0.7
     */
    public boolean removeRangeMarker(int index, Marker marker, Layer layer) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[434]++;
int CodeCoverConditionCoverageHelper_C120;
        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((marker == null) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[201]++;
            throw new IllegalArgumentException("Null 'marker' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[202]++;}
        ArrayList markers;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[435]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[203]++;
            markers = (ArrayList) this.foregroundRangeMarkers.get(new Integer(
                    index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[436]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[204]++;
            markers = (ArrayList) this.backgroundRangeMarkers.get(new Integer(
                    index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[437]++;
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[438]++;

        boolean removed = markers.remove(marker);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[439]++;
int CodeCoverConditionCoverageHelper_C122;
        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[205]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[440]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[206]++;}
        return removed;
    }

    /**
     * Returns a flag indicating whether or not the range crosshair is visible.
     *
     * @return The flag.
     * 
     * @see #setRangeCrosshairVisible(boolean)
     */
    public boolean isRangeCrosshairVisible() {
        return this.rangeCrosshairVisible;
    }

    /**
     * Sets the flag indicating whether or not the range crosshair is visible.
     *
     * @param flag  the new value of the flag.
     * 
     * @see #isRangeCrosshairVisible()
     */
    public void setRangeCrosshairVisible(boolean flag) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[441]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairVisible != flag) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[207]++;
            this.rangeCrosshairVisible = flag;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[442]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[443]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[208]++;}
    }

    /**
     * Returns a flag indicating whether or not the crosshair should "lock-on"
     * to actual data values.
     *
     * @return The flag.
     * 
     * @see #setRangeCrosshairLockedOnData(boolean)
     */
    public boolean isRangeCrosshairLockedOnData() {
        return this.rangeCrosshairLockedOnData;
    }

    /**
     * Sets the flag indicating whether or not the range crosshair should 
     * "lock-on" to actual data values.
     *
     * @param flag  the flag.
     * 
     * @see #isRangeCrosshairLockedOnData()
     */
    public void setRangeCrosshairLockedOnData(boolean flag) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[444]++;
int CodeCoverConditionCoverageHelper_C124;

        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairLockedOnData != flag) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[209]++;
            this.rangeCrosshairLockedOnData = flag;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[445]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[446]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[210]++;}

    }

    /**
     * Returns the range crosshair value.
     *
     * @return The value.
     * 
     * @see #setRangeCrosshairValue(double)
     */
    public double getRangeCrosshairValue() {
        return this.rangeCrosshairValue;
    }

    /**
     * Sets the domain crosshair value.
     * <P>
     * Registered listeners are notified that the plot has been modified, but
     * only if the crosshair is visible.
     *
     * @param value  the new value.
     * 
     * @see #getRangeCrosshairValue()
     */
    public void setRangeCrosshairValue(double value) {
        setRangeCrosshairValue(value, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[447]++;
    }

    /**
     * Sets the range crosshair value and, if requested, sends a 
     * {@link PlotChangeEvent} to all registered listeners (but only if the 
     * crosshair is visible).
     *
     * @param value  the new value.
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     *                
     * @see #getRangeCrosshairValue()
     */
    public void setRangeCrosshairValue(double value, boolean notify) {
        this.rangeCrosshairValue = value;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[448]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[449]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (8)) == 0 || true) &&
 ((isRangeCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[211]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[450]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[212]++;}
    }

    /**
     * Returns the pen-style (<code>Stroke</code>) used to draw the crosshair 
     * (if visible).
     *
     * @return The crosshair stroke (never <code>null</code>).
     * 
     * @see #setRangeCrosshairStroke(Stroke)
     * @see #isRangeCrosshairVisible()
     * @see #getRangeCrosshairPaint()
     */
    public Stroke getRangeCrosshairStroke() {
        return this.rangeCrosshairStroke;
    }

    /**
     * Sets the pen-style (<code>Stroke</code>) used to draw the range 
     * crosshair (if visible), and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param stroke  the new crosshair stroke (<code>null</code> not 
     *         permitted).
     * 
     * @see #getRangeCrosshairStroke()
     */
    public void setRangeCrosshairStroke(Stroke stroke) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[451]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[213]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[214]++;}
        this.rangeCrosshairStroke = stroke;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[452]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[453]++;
    }

    /**
     * Returns the paint used to draw the range crosshair.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setRangeCrosshairPaint(Paint)
     * @see #isRangeCrosshairVisible()
     * @see #getRangeCrosshairStroke()
     */
    public Paint getRangeCrosshairPaint() {
        return this.rangeCrosshairPaint;
    }

    /**
     * Sets the paint used to draw the range crosshair (if visible) and 
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getRangeCrosshairPaint()
     */
    public void setRangeCrosshairPaint(Paint paint) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[454]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[215]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[216]++;}
        this.rangeCrosshairPaint = paint;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[455]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[456]++;
    }

    /**
     * Returns the list of annotations.
     *
     * @return The list of annotations.
     */
    public List getAnnotations() {
        return this.annotations;
    }

    /**
     * Adds an annotation to the plot and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param annotation  the annotation (<code>null</code> not permitted).
     * 
     * @see #removeAnnotation(CategoryAnnotation)
     */
    public void addAnnotation(CategoryAnnotation annotation) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[457]++;
int CodeCoverConditionCoverageHelper_C128;
        if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((annotation == null) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[217]++;
            throw new IllegalArgumentException("Null 'annotation' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[218]++;}
        this.annotations.add(annotation);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[458]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[459]++;
    }

    /**
     * Removes an annotation from the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param annotation  the annotation (<code>null</code> not permitted).
     *
     * @return A boolean (indicates whether or not the annotation was removed).
     * 
     * @see #addAnnotation(CategoryAnnotation)
     */
    public boolean removeAnnotation(CategoryAnnotation annotation) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[460]++;
int CodeCoverConditionCoverageHelper_C129;
        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((annotation == null) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[219]++;
            throw new IllegalArgumentException("Null 'annotation' argument.");

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[220]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[461]++;
        boolean removed = this.annotations.remove(annotation);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[462]++;
int CodeCoverConditionCoverageHelper_C130;
        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[221]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[463]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[222]++;}
        return removed;
    }

    /**
     * Clears all the annotations and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     */
    public void clearAnnotations() {
        this.annotations.clear();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[464]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[465]++;
    }

    /**
     * Calculates the space required for the domain axis/axes.
     * 
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param space  a carrier for the result (<code>null</code> permitted).
     * 
     * @return The required space.
     */
    protected AxisSpace calculateDomainAxisSpace(Graphics2D g2, 
                                                 Rectangle2D plotArea, 
                                                 AxisSpace space) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[466]++;
int CodeCoverConditionCoverageHelper_C131;
                                                     
        if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((space == null) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[223]++;
            space = new AxisSpace();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[467]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[224]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[468]++;
int CodeCoverConditionCoverageHelper_C132;
        
        // reserve some space for the domain axis...
        if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((this.fixedDomainAxisSpace != null) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[225]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[469]++;
int CodeCoverConditionCoverageHelper_C133;
            if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[227]++;
                space.ensureAtLeast(
                    this.fixedDomainAxisSpace.getLeft(), RectangleEdge.LEFT);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[470]++;
                space.ensureAtLeast(this.fixedDomainAxisSpace.getRight(), 
                        RectangleEdge.RIGHT);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[471]++;

            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[228]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[472]++;
int CodeCoverConditionCoverageHelper_C134; if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[229]++;
                space.ensureAtLeast(this.fixedDomainAxisSpace.getTop(), 
                        RectangleEdge.TOP);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[473]++;
                space.ensureAtLeast(this.fixedDomainAxisSpace.getBottom(), 
                        RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[474]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[230]++;}
}

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[226]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[475]++;
            // reserve space for the primary domain axis...
            RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                    getDomainAxisLocation(), this.orientation);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[476]++;
int CodeCoverConditionCoverageHelper_C135;
            if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((this.drawSharedDomainAxis) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[231]++;
                space = getDomainAxis().reserveSpace(g2, this, plotArea, 
                        domainEdge, space);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[477]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[232]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[478]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[58]++;


int CodeCoverConditionCoverageHelper_C136;
            
            // reserve space for any domain axes...
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[58]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[59]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[60]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[479]++;
                Axis xAxis = (Axis) this.domainAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[480]++;
int CodeCoverConditionCoverageHelper_C137;
                if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((xAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[233]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[481]++;
                    RectangleEdge edge = getDomainAxisEdge(i);
                    space = xAxis.reserveSpace(g2, this, plotArea, edge, space);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[482]++;

                } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[234]++;}
            }
        }

        return space;
                                                     
    }
    
    /**
     * Calculates the space required for the range axis/axes.
     * 
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param space  a carrier for the result (<code>null</code> permitted).
     * 
     * @return The required space.
     */
    protected AxisSpace calculateRangeAxisSpace(Graphics2D g2, 
                                                Rectangle2D plotArea, 
                                                AxisSpace space) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[483]++;
int CodeCoverConditionCoverageHelper_C138;
                                                  
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((space == null) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[235]++;
            space = new AxisSpace();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[484]++;
 
        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[236]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[485]++;
int CodeCoverConditionCoverageHelper_C139;
        
        // reserve some space for the range axis...
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((this.fixedRangeAxisSpace != null) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[237]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[486]++;
int CodeCoverConditionCoverageHelper_C140;
            if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[239]++;
                space.ensureAtLeast(this.fixedRangeAxisSpace.getTop(), 
                        RectangleEdge.TOP);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[487]++;
                space.ensureAtLeast(this.fixedRangeAxisSpace.getBottom(), 
                        RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[488]++;

            }
            else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[240]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[489]++;
int CodeCoverConditionCoverageHelper_C141; if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[241]++;
                space.ensureAtLeast(this.fixedRangeAxisSpace.getLeft(), 
                        RectangleEdge.LEFT);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[490]++;
                space.ensureAtLeast(this.fixedRangeAxisSpace.getRight(), 
                        RectangleEdge.RIGHT);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[491]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[242]++;}
}

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[238]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[492]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[61]++;


int CodeCoverConditionCoverageHelper_C142;
            // reserve space for the range axes (if any)...
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[61]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[62]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[63]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[493]++;
                Axis yAxis = (Axis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[494]++;
int CodeCoverConditionCoverageHelper_C143;
                if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((yAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[243]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[495]++;
                    RectangleEdge edge = getRangeAxisEdge(i);
                    space = yAxis.reserveSpace(g2, this, plotArea, edge, space);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[496]++;

                } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[244]++;}
            }
        }
        return space;
                                                    
    }

    /**
     * Calculates the space required for the axes.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     *
     * @return The space required for the axes.
     */
    protected AxisSpace calculateAxisSpace(Graphics2D g2, 
                                           Rectangle2D plotArea) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[497]++;
        AxisSpace space = new AxisSpace();
        space = calculateRangeAxisSpace(g2, plotArea, space);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[498]++;
        space = calculateDomainAxisSpace(g2, plotArea, space);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[499]++;
        return space;
    }
    
    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).
     * <P>
     * At your option, you may supply an instance of {@link PlotRenderingInfo}.
     * If you do, it will be populated with information about the drawing,
     * including various plot dimensions and tooltip info.
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot (including axes) should 
     *              be drawn.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  the state from the parent plot, if there is one.
     * @param state  collects info as the chart is drawn (possibly 
     *               <code>null</code>).
     */
    public void draw(Graphics2D g2, Rectangle2D area, 
                     Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo state) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[500]++;

        // if the plot area is too small, just return...
        boolean b1 = (area.getWidth() <= MINIMUM_WIDTH_TO_DRAW);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[501]++;
        boolean b2 = (area.getHeight() <= MINIMUM_HEIGHT_TO_DRAW);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[502]++;
int CodeCoverConditionCoverageHelper_C144;
        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (8)) == 0 || true) &&
 ((b1) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((b2) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[245]++;
            return;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[246]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[503]++;
int CodeCoverConditionCoverageHelper_C145;

        // record the plot area...
        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((state == null) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[247]++;
            // if the incoming state is null, no information will be passed
            // back to the caller - but we create a temporary state to record
            // the plot area, since that is used later by the axes
            state = new PlotRenderingInfo(null);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[504]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[248]++;}
        state.setPlotArea(area);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[505]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[506]++;

        // adjust the drawing area for the plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[507]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[508]++;

        // calculate the data area...
        AxisSpace space = calculateAxisSpace(g2, area);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[509]++;
        Rectangle2D dataArea = space.shrink(area, null);
        this.axisOffset.trim(dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[510]++;

        state.setDataArea(dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[511]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[512]++;
int CodeCoverConditionCoverageHelper_C146;

        // if there is a renderer, it draws the background, otherwise use the 
        // default background...
        if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((getRenderer() != null) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[249]++;
            getRenderer().drawBackground(g2, this, dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[513]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[250]++;
            drawBackground(g2, dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[514]++;
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[515]++;
       
        Map axisStateMap = drawAxes(g2, area, dataArea, state);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[516]++;

        // don't let anyone draw outside the data area
        Shape savedClip = g2.getClip();
        g2.clip(dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[517]++;

        drawDomainGridlines(g2, dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[518]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[519]++;

        AxisState rangeAxisState = (AxisState) axisStateMap.get(getRangeAxis());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[520]++;
int CodeCoverConditionCoverageHelper_C147;
        if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((rangeAxisState == null) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[251]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[521]++;
int CodeCoverConditionCoverageHelper_C148;
            if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((parentState != null) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[253]++;
                rangeAxisState = (AxisState) parentState.getSharedAxisStates()
                        .get(getRangeAxis());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[522]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[254]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[252]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[523]++;
int CodeCoverConditionCoverageHelper_C149;
        if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((rangeAxisState != null) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[255]++;
            drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[524]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[256]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[525]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[64]++;


int CodeCoverConditionCoverageHelper_C150;
        
        // draw the markers...
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((i < this.renderers.size()) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[64]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[65]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[66]++;
}
            drawDomainMarkers(g2, dataArea, i, Layer.BACKGROUND);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[526]++;
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[527]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[67]++;


int CodeCoverConditionCoverageHelper_C151;        
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((i < this.renderers.size()) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[67]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[68]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[69]++;
}
            drawRangeMarkers(g2, dataArea, i, Layer.BACKGROUND);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[528]++;
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[529]++;

        // now render data items...
        boolean foundData = false;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[530]++;

        // set up the alpha-transparency...
        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, getForegroundAlpha()));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[531]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[532]++;

        DatasetRenderingOrder order = getDatasetRenderingOrder();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[533]++;
int CodeCoverConditionCoverageHelper_C152;
        if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((order == DatasetRenderingOrder.FORWARD) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[257]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[534]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[70]++;


int CodeCoverConditionCoverageHelper_C153;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((i < this.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[70]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[71]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[72]++;
}
                foundData = render(g2, dataArea, i, state) || foundData;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[535]++;
            }

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[258]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[536]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[73]++;


int CodeCoverConditionCoverageHelper_C154;  // DatasetRenderingOrder.REVERSE
            for (int i = this.datasets.size() - 1;(((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[73]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[74]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[75]++;
}
                foundData = render(g2, dataArea, i, state) || foundData;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[537]++;   
            }
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[538]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[76]++;


int CodeCoverConditionCoverageHelper_C155;
        // draw the foreground markers...
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((i < this.renderers.size()) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[76]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[77]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[78]++;
}
            drawDomainMarkers(g2, dataArea, i, Layer.FOREGROUND);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[539]++;
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[540]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[79]++;


int CodeCoverConditionCoverageHelper_C156;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((i < this.renderers.size()) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[79]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[80]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[81]++;
}
            drawRangeMarkers(g2, dataArea, i, Layer.FOREGROUND);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[541]++;
        }

        // draw the annotations (if any)...
        drawAnnotations(g2, dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[542]++;

        g2.setClip(savedClip);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[543]++;
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[544]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[545]++;
int CodeCoverConditionCoverageHelper_C157;

        if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((foundData) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[259]++;
            drawNoDataMessage(g2, dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[546]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[260]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[547]++;
int CodeCoverConditionCoverageHelper_C158;

        // draw range crosshair if required...
        if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((isRangeCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[261]++;
            // FIXME: this doesn't handle multiple range axes
            drawRangeCrosshair(g2, dataArea, getOrientation(), 
                    getRangeCrosshairValue(), getRangeAxis(),
                    getRangeCrosshairStroke(), getRangeCrosshairPaint());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[548]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[262]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[549]++;
int CodeCoverConditionCoverageHelper_C159;

        // draw an outline around the plot area...
        if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((getRenderer() != null) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[263]++;
            getRenderer().drawOutline(g2, this, dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[550]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[264]++;
            drawOutline(g2, dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[551]++;
        }

    }

    /**
     * Draws the plot background (the background color and/or image).
     * <P>
     * This method will be called during the chart drawing process and is 
     * declared public so that it can be accessed by the renderers used by 
     * certain subclasses.  You shouldn't need to call this method directly.
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot should be drawn.
     */
    public void drawBackground(Graphics2D g2, Rectangle2D area) {
        fillBackground(g2, area, this.orientation);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[552]++;
        drawBackgroundImage(g2, area);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[553]++;
    }

    /**
     * A utility method for drawing the plot's axes.
     * 
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param dataArea  the data area.
     * @param plotState  collects information about the plot (<code>null</code>
     *                   permitted).
     * 
     * @return A map containing the axis states.
     */
    protected Map drawAxes(Graphics2D g2, 
                           Rectangle2D plotArea, 
                           Rectangle2D dataArea,
                           PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[554]++;

        AxisCollection axisCollection = new AxisCollection();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[555]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[82]++;


int CodeCoverConditionCoverageHelper_C160;

        // add domain axes to lists...
        for (int index = 0;(((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((index < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[82]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[83]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[84]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[556]++;
            CategoryAxis xAxis = (CategoryAxis) this.domainAxes.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[557]++;
int CodeCoverConditionCoverageHelper_C161;
            if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((xAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[265]++;
                axisCollection.add(xAxis, getDomainAxisEdge(index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[558]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[266]++;}
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[559]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[85]++;


int CodeCoverConditionCoverageHelper_C162;

        // add range axes to lists...
        for (int index = 0;(((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((index < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[85]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[86]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[87]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[560]++;
            ValueAxis yAxis = (ValueAxis) this.rangeAxes.get(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[561]++;
int CodeCoverConditionCoverageHelper_C163;
            if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((yAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[267]++;
                axisCollection.add(yAxis, getRangeAxisEdge(index));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[562]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[268]++;}
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[563]++;

        Map axisStateMap = new HashMap();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[564]++;
        
        // draw the top axes
        double cursor = dataArea.getMinY() - this.axisOffset.calculateTopOutset(
                dataArea.getHeight());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[565]++;
        Iterator iterator = axisCollection.getAxesAtTop().iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[566]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[88]++;


int CodeCoverConditionCoverageHelper_C164;
        while ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[88]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[89]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[90]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[567]++;
            Axis axis = (Axis) iterator.next();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[568]++;
int CodeCoverConditionCoverageHelper_C165;
            if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[269]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[569]++;
                AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, 
                        RectangleEdge.TOP, plotState);
                cursor = axisState.getCursor();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[570]++;
                axisStateMap.put(axis, axisState);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[571]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[270]++;}
        }

        // draw the bottom axes
        cursor = dataArea.getMaxY() 
                 + this.axisOffset.calculateBottomOutset(dataArea.getHeight());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[572]++;
        iterator = axisCollection.getAxesAtBottom().iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[573]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[574]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[91]++;


int CodeCoverConditionCoverageHelper_C166;
        while ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[91]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[92]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[93]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[575]++;
            Axis axis = (Axis) iterator.next();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[576]++;
int CodeCoverConditionCoverageHelper_C167;
            if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[271]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[577]++;
                AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea,
                        RectangleEdge.BOTTOM, plotState);
                cursor = axisState.getCursor();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[578]++;
                axisStateMap.put(axis, axisState);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[579]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[272]++;}
        }

        // draw the left axes
        cursor = dataArea.getMinX() 
                 - this.axisOffset.calculateLeftOutset(dataArea.getWidth());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[580]++;
        iterator = axisCollection.getAxesAtLeft().iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[581]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[582]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[94]++;


int CodeCoverConditionCoverageHelper_C168;
        while ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[94]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[95]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[96]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[583]++;
            Axis axis = (Axis) iterator.next();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[584]++;
int CodeCoverConditionCoverageHelper_C169;
            if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[273]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[585]++;
                AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea,
                        RectangleEdge.LEFT, plotState);
                cursor = axisState.getCursor();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[586]++;
                axisStateMap.put(axis, axisState);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[587]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[274]++;}
        }

        // draw the right axes
        cursor = dataArea.getMaxX() 
                 + this.axisOffset.calculateRightOutset(dataArea.getWidth());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[588]++;
        iterator = axisCollection.getAxesAtRight().iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[589]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[590]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[97]++;


int CodeCoverConditionCoverageHelper_C170;
        while ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[97]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[98]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[99]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[591]++;
            Axis axis = (Axis) iterator.next();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[592]++;
int CodeCoverConditionCoverageHelper_C171;
            if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[275]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[593]++;
                AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, 
                        RectangleEdge.RIGHT, plotState);
                cursor = axisState.getCursor();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[594]++;
                axisStateMap.put(axis, axisState);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[595]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[276]++;}
        }
        
        return axisStateMap;
        
    }

    /**
     * Draws a representation of a dataset within the dataArea region using the
     * appropriate renderer.
     *
     * @param g2  the graphics device.
     * @param dataArea  the region in which the data is to be drawn.
     * @param index  the dataset and renderer index.
     * @param info  an optional object for collection dimension information.
     * 
     * @return A boolean that indicates whether or not real data was found.
     */
    public boolean render(Graphics2D g2, Rectangle2D dataArea, int index, 
                          PlotRenderingInfo info) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[596]++;

        boolean foundData = false;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[597]++;
        CategoryDataset currentDataset = getDataset(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[598]++;
        CategoryItemRenderer renderer = getRenderer(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[599]++;
        CategoryAxis domainAxis = getDomainAxisForDataset(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[600]++;
        ValueAxis rangeAxis = getRangeAxisForDataset(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[601]++;
        boolean hasData = !DatasetUtilities.isEmptyOrNull(currentDataset);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[602]++;
int CodeCoverConditionCoverageHelper_C172;
        if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (8)) == 0 || true) &&
 ((hasData) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[277]++;
            
            foundData = true;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[603]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[604]++;
            CategoryItemRendererState state = renderer.initialise(g2, dataArea,
                    this, index, info);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[605]++;
            int columnCount = currentDataset.getColumnCount();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[606]++;
            int rowCount = currentDataset.getRowCount();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[607]++;
            int passCount = renderer.getPassCount();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[608]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[100]++;


int CodeCoverConditionCoverageHelper_C173;
            for (int pass = 0;(((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((pass < passCount) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false); pass++) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[100]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[101]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[102]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[609]++;
int CodeCoverConditionCoverageHelper_C174;            
                if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((this.columnRenderingOrder == SortOrder.ASCENDING) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[279]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[610]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[103]++;


int CodeCoverConditionCoverageHelper_C175;
                    for (int column = 0;(((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((column < columnCount) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false); column++) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[103]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[104]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[105]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[611]++;
int CodeCoverConditionCoverageHelper_C176;
                        if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((this.rowRenderingOrder == SortOrder.ASCENDING) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[281]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[612]++;
byte CodeCoverTryBranchHelper_L36 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[106]++;


int CodeCoverConditionCoverageHelper_C177;
                            for (int row = 0;(((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((row < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false); row++) {
if (CodeCoverTryBranchHelper_L36 == 0) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[106]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[107]++;
} else if (CodeCoverTryBranchHelper_L36 == 1) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[107]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[108]++;
}
                                renderer.drawItem(g2, state, dataArea, this, 
                                        domainAxis, rangeAxis, currentDataset, 
                                        row, column, pass);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[613]++;
                            }

                        }
                        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[282]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[614]++;
byte CodeCoverTryBranchHelper_L37 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[109]++;


int CodeCoverConditionCoverageHelper_C178;
                            for (int row = rowCount - 1;(((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((row >= 0) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false); row--) {
if (CodeCoverTryBranchHelper_L37 == 0) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[109]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[110]++;
} else if (CodeCoverTryBranchHelper_L37 == 1) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[110]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[111]++;
}
                                renderer.drawItem(g2, state, dataArea, this, 
                                        domainAxis, rangeAxis, currentDataset, 
                                        row, column, pass);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[615]++;
                            }                        
                        }
                    }

                }
                else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[280]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[616]++;
byte CodeCoverTryBranchHelper_L38 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[112]++;


int CodeCoverConditionCoverageHelper_C179;
                    for (int column = columnCount - 1;(((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((column >= 0) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false); column--) {
if (CodeCoverTryBranchHelper_L38 == 0) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[112]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[113]++;
} else if (CodeCoverTryBranchHelper_L38 == 1) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[113]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[114]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[617]++;
int CodeCoverConditionCoverageHelper_C180;
                        if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((this.rowRenderingOrder == SortOrder.ASCENDING) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[283]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[618]++;
byte CodeCoverTryBranchHelper_L39 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[115]++;


int CodeCoverConditionCoverageHelper_C181;
                            for (int row = 0;(((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((row < rowCount) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false); row++) {
if (CodeCoverTryBranchHelper_L39 == 0) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[115]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[116]++;
} else if (CodeCoverTryBranchHelper_L39 == 1) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[116]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[117]++;
}
                                renderer.drawItem(g2, state, dataArea, this, 
                                        domainAxis, rangeAxis, currentDataset, 
                                        row, column, pass);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[619]++;
                            }

                        }
                        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[284]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[620]++;
byte CodeCoverTryBranchHelper_L40 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[118]++;


int CodeCoverConditionCoverageHelper_C182;
                            for (int row = rowCount - 1;(((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((row >= 0) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false); row--) {
if (CodeCoverTryBranchHelper_L40 == 0) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[118]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[119]++;
} else if (CodeCoverTryBranchHelper_L40 == 1) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[119]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[120]++;
}
                                renderer.drawItem(g2, state, dataArea, this, 
                                        domainAxis, rangeAxis, currentDataset, 
                                        row, column, pass);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[621]++;
                            }                        
                        }
                    }
                }
            }

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[278]++;}
        return foundData;
        
    }

    /**
     * Draws the gridlines for the plot.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * 
     * @see #drawRangeGridlines(Graphics2D, Rectangle2D, List)
     */
    protected void drawDomainGridlines(Graphics2D g2, Rectangle2D dataArea) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[622]++;
int CodeCoverConditionCoverageHelper_C183;

        // draw the domain grid lines, if any...
        if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((isDomainGridlinesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[285]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[623]++;
            CategoryAnchor anchor = getDomainGridlinePosition();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[624]++;
            RectangleEdge domainAxisEdge = getDomainAxisEdge();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[625]++;
            Stroke gridStroke = getDomainGridlineStroke();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[626]++;
            Paint gridPaint = getDomainGridlinePaint();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[627]++;
int CodeCoverConditionCoverageHelper_C184;
            if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C184 |= (8)) == 0 || true) &&
 ((gridStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((gridPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[287]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[628]++;
                // iterate over the categories
                CategoryDataset data = getDataset();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[629]++;
int CodeCoverConditionCoverageHelper_C185;
                if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[289]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[630]++;
                    CategoryAxis axis = getDomainAxis();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[631]++;
int CodeCoverConditionCoverageHelper_C186;
                    if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[291]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[632]++;
                        int columnCount = data.getColumnCount();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[633]++;
byte CodeCoverTryBranchHelper_L41 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[121]++;


int CodeCoverConditionCoverageHelper_C187;
                        for (int c = 0;(((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((c < columnCount) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false); c++) {
if (CodeCoverTryBranchHelper_L41 == 0) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[121]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[122]++;
} else if (CodeCoverTryBranchHelper_L41 == 1) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[122]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[123]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[634]++;
                            double xx = axis.getCategoryJava2DCoordinate(
                                    anchor, c, columnCount, dataArea, 
                                    domainAxisEdge);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[635]++;
                            CategoryItemRenderer renderer1 = getRenderer();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[636]++;
int CodeCoverConditionCoverageHelper_C188;
                            if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((renderer1 != null) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[293]++;
                                renderer1.drawDomainGridline(g2, this, 
                                        dataArea, xx);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[637]++;

                            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[294]++;}
                        }

                    } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[292]++;}

                } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[290]++;}

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[288]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[286]++;}
    }
 
    /**
     * Draws the gridlines for the plot.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param ticks  the ticks.
     * 
     * @see #drawDomainGridlines(Graphics2D, Rectangle2D)
     */
    protected void drawRangeGridlines(Graphics2D g2, Rectangle2D dataArea, 
                                      List ticks) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[638]++;
int CodeCoverConditionCoverageHelper_C189;
        // draw the range grid lines, if any...
        if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((isRangeGridlinesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[295]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[639]++;
            Stroke gridStroke = getRangeGridlineStroke();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[640]++;
            Paint gridPaint = getRangeGridlinePaint();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[641]++;
int CodeCoverConditionCoverageHelper_C190;
            if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C190 |= (8)) == 0 || true) &&
 ((gridStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((gridPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[297]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[642]++;
                ValueAxis axis = getRangeAxis();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[643]++;
int CodeCoverConditionCoverageHelper_C191;
                if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[299]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[644]++;
                    Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[645]++;
byte CodeCoverTryBranchHelper_L42 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[124]++;


int CodeCoverConditionCoverageHelper_C192;
                    while ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
if (CodeCoverTryBranchHelper_L42 == 0) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[124]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[125]++;
} else if (CodeCoverTryBranchHelper_L42 == 1) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[125]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[126]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[646]++;
                        ValueTick tick = (ValueTick) iterator.next();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[647]++;
                        CategoryItemRenderer renderer1 = getRenderer();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[648]++;
int CodeCoverConditionCoverageHelper_C193;
                        if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((renderer1 != null) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[301]++;
                            renderer1.drawRangeGridline(g2, this, 
                                    getRangeAxis(), dataArea, tick.getValue());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[649]++;

                        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[302]++;}
                    }

                } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[300]++;}

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[298]++;}

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[296]++;}
    }

    /**
     * Draws the annotations...
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     */
    protected void drawAnnotations(Graphics2D g2, Rectangle2D dataArea) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[650]++;
int CodeCoverConditionCoverageHelper_C194;

        if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((getAnnotations() != null) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[303]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[651]++;
            Iterator iterator = getAnnotations().iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[652]++;
byte CodeCoverTryBranchHelper_L43 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[127]++;


int CodeCoverConditionCoverageHelper_C195;
            while ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
if (CodeCoverTryBranchHelper_L43 == 0) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[127]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[128]++;
} else if (CodeCoverTryBranchHelper_L43 == 1) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[128]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[129]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[653]++;
                CategoryAnnotation annotation 
                        = (CategoryAnnotation) iterator.next();
                annotation.draw(g2, this, dataArea, getDomainAxis(), 
                        getRangeAxis());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[654]++;
            }

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[304]++;}

    }

    /**
     * Draws the domain markers (if any) for an axis and layer.  This method is 
     * typically called from within the draw() method.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param index  the renderer index.
     * @param layer  the layer (foreground or background).
     * 
     * @see #drawRangeMarkers(Graphics2D, Rectangle2D, int, Layer)
     */
    protected void drawDomainMarkers(Graphics2D g2, Rectangle2D dataArea, 
                                     int index, Layer layer) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[655]++;
                                                 
        CategoryItemRenderer r = getRenderer(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[656]++;
int CodeCoverConditionCoverageHelper_C196;
        if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[305]++;
            return;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[306]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[657]++;
        
        Collection markers = getDomainMarkers(index, layer);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[658]++;
        CategoryAxis axis = getDomainAxisForDataset(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[659]++;
int CodeCoverConditionCoverageHelper_C197;
        if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (8)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[307]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[660]++;
            Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[661]++;
byte CodeCoverTryBranchHelper_L44 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[130]++;


int CodeCoverConditionCoverageHelper_C198;
            while ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
if (CodeCoverTryBranchHelper_L44 == 0) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[130]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[131]++;
} else if (CodeCoverTryBranchHelper_L44 == 1) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[131]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[132]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[662]++;
                CategoryMarker marker = (CategoryMarker) iterator.next();
                r.drawDomainMarker(g2, this, axis, marker, dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[663]++;
            }

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[308]++;}
        
    }

    /**
     * Draws the range markers (if any) for an axis and layer.  This method is 
     * typically called from within the draw() method.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param index  the renderer index.
     * @param layer  the layer (foreground or background).
     * 
     * @see #drawDomainMarkers(Graphics2D, Rectangle2D, int, Layer)
     */
    protected void drawRangeMarkers(Graphics2D g2, Rectangle2D dataArea, 
                                    int index, Layer layer) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[664]++;
                                                 
        CategoryItemRenderer r = getRenderer(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[665]++;
int CodeCoverConditionCoverageHelper_C199;
        if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[309]++;
            return;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[310]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[666]++;
        
        Collection markers = getRangeMarkers(index, layer);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[667]++;
        ValueAxis axis = getRangeAxisForDataset(index);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[668]++;
int CodeCoverConditionCoverageHelper_C200;
        if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (8)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 2) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 2) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[311]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[669]++;
            Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[670]++;
byte CodeCoverTryBranchHelper_L45 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[133]++;


int CodeCoverConditionCoverageHelper_C201;
            while ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
if (CodeCoverTryBranchHelper_L45 == 0) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[133]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[134]++;
} else if (CodeCoverTryBranchHelper_L45 == 1) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[134]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[135]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[671]++;
                Marker marker = (Marker) iterator.next();
                r.drawRangeMarker(g2, this, axis, marker, dataArea);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[672]++;
            }

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[312]++;}
        
    }

    /**
     * Utility method for drawing a line perpendicular to the range axis (used
     * for crosshairs).
     *
     * @param g2  the graphics device.
     * @param dataArea  the area defined by the axes.
     * @param value  the data value.
     * @param stroke  the line stroke (<code>null</code> not permitted).
     * @param paint  the line paint (<code>null</code> not permitted).
     */
    protected void drawRangeLine(Graphics2D g2, Rectangle2D dataArea,
            double value, Stroke stroke, Paint paint) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[673]++;

        double java2D = getRangeAxis().valueToJava2D(value, dataArea, 
                getRangeAxisEdge());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[674]++;
        Line2D line = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[675]++;
int CodeCoverConditionCoverageHelper_C202;
        if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[313]++;
            line = new Line2D.Double(java2D, dataArea.getMinY(), java2D, 
                    dataArea.getMaxY());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[676]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[314]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[677]++;
int CodeCoverConditionCoverageHelper_C203; if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[315]++;
            line = new Line2D.Double(dataArea.getMinX(), java2D, 
                    dataArea.getMaxX(), java2D);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[678]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[316]++;}
}
        g2.setStroke(stroke);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[679]++;
        g2.setPaint(paint);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[680]++;
        g2.draw(line);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[681]++;

    }

    /**
     * Draws a range crosshair.
     * 
     * @param g2  the graphics target.
     * @param dataArea  the data area.
     * @param orientation  the plot orientation.
     * @param value  the crosshair value.
     * @param axis  the axis against which the value is measured.
     * @param stroke  the stroke used to draw the crosshair line.
     * @param paint  the paint used to draw the crosshair line.
     * 
     * @since 1.0.5
     */
    protected void drawRangeCrosshair(Graphics2D g2, Rectangle2D dataArea, 
            PlotOrientation orientation, double value, ValueAxis axis, 
            Stroke stroke, Paint paint) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[682]++;
int CodeCoverConditionCoverageHelper_C204;
        
        if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((axis.getRange().contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[317]++;
            return;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[318]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[683]++;
        Line2D line = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[684]++;
int CodeCoverConditionCoverageHelper_C205;
        if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[319]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[685]++;
            double xx = axis.valueToJava2D(value, dataArea, 
                    RectangleEdge.BOTTOM);
            line = new Line2D.Double(xx, dataArea.getMinY(), xx, 
                    dataArea.getMaxY());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[686]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[320]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[687]++;
            double yy = axis.valueToJava2D(value, dataArea, 
                    RectangleEdge.LEFT);
            line = new Line2D.Double(dataArea.getMinX(), yy, 
                    dataArea.getMaxX(), yy);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[688]++;
        }
        g2.setStroke(stroke);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[689]++;
        g2.setPaint(paint);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[690]++;
        g2.draw(line);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[691]++;
       
    }
    
    /**
     * Returns the range of data values that will be plotted against the range 
     * axis.  If the dataset is <code>null</code>, this method returns 
     * <code>null</code>.
     *
     * @param axis  the axis.
     *
     * @return The data range.
     */
    public Range getDataRange(ValueAxis axis) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[692]++;

        Range result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[693]++;
        List mappedDatasets = new ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[694]++;
        
        int rangeIndex = this.rangeAxes.indexOf(axis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[695]++;
int CodeCoverConditionCoverageHelper_C206;
        if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((rangeIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[321]++;
            mappedDatasets.addAll(datasetsMappedToRangeAxis(rangeIndex));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[696]++;

        }
        else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[322]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[697]++;
int CodeCoverConditionCoverageHelper_C207; if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((axis == getRangeAxis()) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[323]++;
            mappedDatasets.addAll(datasetsMappedToRangeAxis(0));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[698]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[324]++;}
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[699]++;

        // iterate through the datasets that map to the axis and get the union 
        // of the ranges.
        Iterator iterator = mappedDatasets.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[700]++;
byte CodeCoverTryBranchHelper_L46 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[136]++;


int CodeCoverConditionCoverageHelper_C208;
        while ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
if (CodeCoverTryBranchHelper_L46 == 0) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[136]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[137]++;
} else if (CodeCoverTryBranchHelper_L46 == 1) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[137]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[138]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[701]++;
            CategoryDataset d = (CategoryDataset) iterator.next();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[702]++;
            CategoryItemRenderer r = getRendererForDataset(d);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[703]++;
int CodeCoverConditionCoverageHelper_C209;
            if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[325]++;
                result = Range.combine(result, r.findRangeBounds(d));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[704]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[326]++;}
        }
        return result;

    }

    /**
     * Returns a list of the datasets that are mapped to the axis with the
     * specified index.
     * 
     * @param axisIndex  the axis index.
     * 
     * @return The list (possibly empty, but never <code>null</code>).
     * 
     * @since 1.0.3
     */
    private List datasetsMappedToDomainAxis(int axisIndex) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[705]++;
        List result = new ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[706]++;
byte CodeCoverTryBranchHelper_L47 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[139]++;


int CodeCoverConditionCoverageHelper_C210;
        for (int datasetIndex = 0;(((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((datasetIndex < this.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false); 
                datasetIndex++) {
if (CodeCoverTryBranchHelper_L47 == 0) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[139]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[140]++;
} else if (CodeCoverTryBranchHelper_L47 == 1) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[140]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[141]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[707]++;
            Object dataset = this.datasets.get(datasetIndex);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[708]++;
int CodeCoverConditionCoverageHelper_C211;
            if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[327]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[709]++;
                Integer m = (Integer) this.datasetToDomainAxisMap.get(
                        datasetIndex);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[710]++;
int CodeCoverConditionCoverageHelper_C212;
                if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((m == null) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[329]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[711]++;
int CodeCoverConditionCoverageHelper_C213;  // a dataset with no mapping is assigned to 
                                  // axis 0
                    if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((axisIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[331]++;
                        result.add(dataset);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[712]++;

                    } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[332]++;}

                }
                else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[330]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[713]++;
int CodeCoverConditionCoverageHelper_C214;
                    if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((m.intValue() == axisIndex) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[333]++;
                        result.add(dataset);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[714]++;

                    } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[334]++;}
                }

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[328]++;}
        }
        return result;
    }
    
    /**
     * A utility method that returns a list of datasets that are mapped to a 
     * given range axis.
     * 
     * @param index  the axis index.
     * 
     * @return A list of datasets.
     */
    private List datasetsMappedToRangeAxis(int index) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[715]++;
        List result = new ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[716]++;
byte CodeCoverTryBranchHelper_L48 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[142]++;


int CodeCoverConditionCoverageHelper_C215;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((i < this.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L48 == 0) {
  CodeCoverTryBranchHelper_L48++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[142]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[143]++;
} else if (CodeCoverTryBranchHelper_L48 == 1) {
  CodeCoverTryBranchHelper_L48++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[143]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[144]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[717]++;
            Object dataset = this.datasets.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[718]++;
int CodeCoverConditionCoverageHelper_C216;
            if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[335]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[719]++;
                Integer m = (Integer) this.datasetToRangeAxisMap.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[720]++;
int CodeCoverConditionCoverageHelper_C217;
                if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((m == null) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[337]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[721]++;
int CodeCoverConditionCoverageHelper_C218;  // a dataset with no mapping is assigned to 
                                  // axis 0
                    if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[339]++; 
                        result.add(dataset);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[722]++;

                    } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[340]++;}

                }
                else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[338]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[723]++;
int CodeCoverConditionCoverageHelper_C219;
                    if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((m.intValue() == index) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[341]++;
                        result.add(dataset);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[724]++;

                    } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[342]++;}
                }

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[336]++;}
        }
        return result;    
    }

    /**
     * Returns the weight for this plot when it is used as a subplot within a 
     * combined plot.
     *
     * @return The weight.
     * 
     * @see #setWeight(int)
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * Sets the weight for the plot and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param weight  the weight.
     * 
     * @see #getWeight()
     */
    public void setWeight(int weight) {
        this.weight = weight;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[725]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[726]++;
    }
    
    /**
     * Returns the fixed domain axis space.
     *
     * @return The fixed domain axis space (possibly <code>null</code>).
     * 
     * @see #setFixedDomainAxisSpace(AxisSpace)
     */
    public AxisSpace getFixedDomainAxisSpace() {
        return this.fixedDomainAxisSpace;
    }

    /**
     * Sets the fixed domain axis space and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param space  the space (<code>null</code> permitted).
     * 
     * @see #getFixedDomainAxisSpace()
     */
    public void setFixedDomainAxisSpace(AxisSpace space) {
        setFixedDomainAxisSpace(space, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[727]++;
    }

    /**
     * Sets the fixed domain axis space and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param space  the space (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getFixedDomainAxisSpace()
     * 
     * @since 1.0.7
     */
    public void setFixedDomainAxisSpace(AxisSpace space, boolean notify) {
        this.fixedDomainAxisSpace = space;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[728]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[729]++;
int CodeCoverConditionCoverageHelper_C220;
        if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[343]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[730]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[344]++;}
    }

    /**
     * Returns the fixed range axis space.
     *
     * @return The fixed range axis space (possibly <code>null</code>).
     * 
     * @see #setFixedRangeAxisSpace(AxisSpace)
     */
    public AxisSpace getFixedRangeAxisSpace() {
        return this.fixedRangeAxisSpace;
    }

    /**
     * Sets the fixed range axis space and sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     *
     * @param space  the space (<code>null</code> permitted).
     * 
     * @see #getFixedRangeAxisSpace()
     */
    public void setFixedRangeAxisSpace(AxisSpace space) {
        setFixedRangeAxisSpace(space, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[731]++;
    }

    /**
     * Sets the fixed range axis space and sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     *
     * @param space  the space (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getFixedRangeAxisSpace()
     *
     * @since 1.0.7
     */
    public void setFixedRangeAxisSpace(AxisSpace space, boolean notify) {
        this.fixedRangeAxisSpace = space;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[732]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[733]++;
int CodeCoverConditionCoverageHelper_C221;
        if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[345]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[734]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[346]++;}
    }

    /**
     * Returns a list of the categories in the plot's primary dataset.
     * 
     * @return A list of the categories in the plot's primary dataset.
     * 
     * @see #getCategoriesForAxis(CategoryAxis)
     */
    public List getCategories() {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[735]++;
        List result = null;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[736]++;
int CodeCoverConditionCoverageHelper_C222;
        if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((getDataset() != null) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[347]++;
            result = Collections.unmodifiableList(getDataset().getColumnKeys());
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[737]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[348]++;}
        return result;
    }
    
    /**
     * Returns a list of the categories that should be displayed for the
     * specified axis.
     * 
     * @param axis  the axis (<code>null</code> not permitted)
     * 
     * @return The categories.
     * 
     * @since 1.0.3
     */
    public List getCategoriesForAxis(CategoryAxis axis) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[738]++;
        List result = new ArrayList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[739]++;
        int axisIndex = this.domainAxes.indexOf(axis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[740]++;
        List datasets = datasetsMappedToDomainAxis(axisIndex);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[741]++;
        Iterator iterator = datasets.iterator();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[742]++;
byte CodeCoverTryBranchHelper_L49 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[145]++;


int CodeCoverConditionCoverageHelper_C223;
        while ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false)) {
if (CodeCoverTryBranchHelper_L49 == 0) {
  CodeCoverTryBranchHelper_L49++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[145]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[146]++;
} else if (CodeCoverTryBranchHelper_L49 == 1) {
  CodeCoverTryBranchHelper_L49++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[146]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[147]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[743]++;
            CategoryDataset dataset = (CategoryDataset) iterator.next();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[744]++;
byte CodeCoverTryBranchHelper_L50 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[148]++;


int CodeCoverConditionCoverageHelper_C224;
            // add the unique categories from this dataset
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((i < dataset.getColumnCount()) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L50 == 0) {
  CodeCoverTryBranchHelper_L50++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[148]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[149]++;
} else if (CodeCoverTryBranchHelper_L50 == 1) {
  CodeCoverTryBranchHelper_L50++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[149]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[150]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[745]++;
                Comparable category = dataset.getColumnKey(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[746]++;
int CodeCoverConditionCoverageHelper_C225;
                if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((result.contains(category)) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[349]++;
                    result.add(category);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[747]++;

                } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[350]++;}
            }
        }
        return result;
    }

    /**
     * Returns the flag that controls whether or not the shared domain axis is 
     * drawn for each subplot.
     * 
     * @return A boolean.
     * 
     * @see #setDrawSharedDomainAxis(boolean)
     */
    public boolean getDrawSharedDomainAxis() {
        return this.drawSharedDomainAxis;
    }
    
    /**
     * Sets the flag that controls whether the shared domain axis is drawn when
     * this plot is being used as a subplot.
     * 
     * @param draw  a boolean.
     * 
     * @see #getDrawSharedDomainAxis()
     */
    public void setDrawSharedDomainAxis(boolean draw) {
        this.drawSharedDomainAxis = draw;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[748]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[749]++;
    }

    /**
     * Returns <code>false</code> to indicate that the domain axes are not
     * zoomable.
     * 
     * @return A boolean.
     * 
     * @see #isRangeZoomable()
     */
    public boolean isDomainZoomable() {
        return false;
    }
    
    /**
     * Returns <code>true</code> to indicate that the range axes are zoomable.
     * 
     * @return A boolean.
     * 
     * @see #isDomainZoomable()
     */
    public boolean isRangeZoomable() {
        return true;
    }

    /**
     * This method does nothing, because <code>CategoryPlot</code> doesn't 
     * support zooming on the domain.
     *
     * @param factor  the zoom factor.
     * @param state  the plot state.
     * @param source  the source point (in Java2D space) for the zoom.
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo state, 
                               Point2D source) {
        // can't zoom domain axis
    }

    /**
     * This method does nothing, because <code>CategoryPlot</code> doesn't 
     * support zooming on the domain.
     * 
     * @param lowerPercent  the lower bound.
     * @param upperPercent  the upper bound.
     * @param state  the plot state.
     * @param source  the source point (in Java2D space) for the zoom.
     */
    public void zoomDomainAxes(double lowerPercent, double upperPercent, 
                               PlotRenderingInfo state, Point2D source) {
        // can't zoom domain axis
    }
    
    /**
     * This method does nothing, because <code>CategoryPlot</code> doesn't 
     * support zooming on the domain.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info.
     * @param source  the source point (in Java2D space).
     * @param useAnchor  use source point as zoom anchor?
     * 
     * @see #zoomRangeAxes(double, PlotRenderingInfo, Point2D, boolean)
     * 
     * @since 1.0.7
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo info,
                               Point2D source, boolean useAnchor) {
        // can't zoom domain axis
    }

    /**
     * Multiplies the range on the range axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param state  the plot state.
     * @param source  the source point (in Java2D space) for the zoom.
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo state, 
                              Point2D source) {
        // delegate to other method
        zoomRangeAxes(factor, state, source, false);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[750]++;    
    }

    /**
     * Multiplies the range on the range axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info.
     * @param source  the source point.
     * @param useAnchor  a flag that controls whether or not the source point
     *         is used for the zoom anchor.
     * 
     * @see #zoomDomainAxes(double, PlotRenderingInfo, Point2D, boolean)
     * 
     * @since 1.0.7
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo info,
                              Point2D source, boolean useAnchor) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[751]++;
byte CodeCoverTryBranchHelper_L51 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[151]++;


int CodeCoverConditionCoverageHelper_C226;
                
        // perform the zoom on each range axis
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L51 == 0) {
  CodeCoverTryBranchHelper_L51++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[151]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[152]++;
} else if (CodeCoverTryBranchHelper_L51 == 1) {
  CodeCoverTryBranchHelper_L51++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[152]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[153]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[752]++;
            ValueAxis rangeAxis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[753]++;
int CodeCoverConditionCoverageHelper_C227;
            if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[351]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[754]++;
int CodeCoverConditionCoverageHelper_C228;
                if ((((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((useAnchor) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[353]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[755]++;
                    // get the relevant source coordinate given the plot 
                    // orientation
                    double sourceY = source.getY();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[756]++;
int CodeCoverConditionCoverageHelper_C229;
                    if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[355]++;
                        sourceY = source.getX();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[757]++;

                    } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[356]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[758]++;
                    double anchorY = rangeAxis.java2DToValue(sourceY, 
                            info.getDataArea(), getRangeAxisEdge());
                    rangeAxis.resizeRange(factor, anchorY);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[759]++;

                }
                else {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[354]++;
                    rangeAxis.resizeRange(factor);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[760]++;
                }

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[352]++;}
        }
    }

    /**
     * Zooms in on the range axes.
     * 
     * @param lowerPercent  the lower bound.
     * @param upperPercent  the upper bound.
     * @param state  the plot state.
     * @param source  the source point (in Java2D space) for the zoom.
     */
    public void zoomRangeAxes(double lowerPercent, double upperPercent, 
                              PlotRenderingInfo state, Point2D source) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[761]++;
byte CodeCoverTryBranchHelper_L52 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[154]++;


int CodeCoverConditionCoverageHelper_C230;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L52 == 0) {
  CodeCoverTryBranchHelper_L52++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[154]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[155]++;
} else if (CodeCoverTryBranchHelper_L52 == 1) {
  CodeCoverTryBranchHelper_L52++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[155]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[156]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[762]++;
            ValueAxis rangeAxis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[763]++;
int CodeCoverConditionCoverageHelper_C231;
            if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[357]++;
                rangeAxis.zoomRange(lowerPercent, upperPercent);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[764]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[358]++;}
        }
    }
    
    /**
     * Returns the anchor value.
     * 
     * @return The anchor value.
     * 
     * @see #setAnchorValue(double)
     */
    public double getAnchorValue() {
        return this.anchorValue;
    }

    /**
     * Sets the anchor value and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param value  the anchor value.
     * 
     * @see #getAnchorValue()
     */
    public void setAnchorValue(double value) {
        setAnchorValue(value, true);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[765]++;
    }

    /**
     * Sets the anchor value and, if requested, sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param value  the value.
     * @param notify  notify listeners?
     * 
     * @see #getAnchorValue()
     */
    public void setAnchorValue(double value, boolean notify) {
        this.anchorValue = value;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[766]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[767]++;
int CodeCoverConditionCoverageHelper_C232;
        if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[359]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[768]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[360]++;}
    }
    
    /** 
     * Tests the plot for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[769]++;
int CodeCoverConditionCoverageHelper_C233;
    
        if ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[361]++;
            return true;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[362]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[770]++;
int CodeCoverConditionCoverageHelper_C234;
        if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryPlot) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[363]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[364]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[771]++;
int CodeCoverConditionCoverageHelper_C235;
        if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[365]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[366]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[772]++;

        CategoryPlot that = (CategoryPlot) obj;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[773]++;
int CodeCoverConditionCoverageHelper_C236;
            
        if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((this.orientation != that.orientation) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[367]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[368]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[774]++;
int CodeCoverConditionCoverageHelper_C237;
        if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.axisOffset, that.axisOffset)) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[369]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[370]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[775]++;
int CodeCoverConditionCoverageHelper_C238;
        if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((this.domainAxes.equals(that.domainAxes)) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[371]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[372]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[776]++;
int CodeCoverConditionCoverageHelper_C239;
        if ((((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((this.domainAxisLocations.equals(that.domainAxisLocations)) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[373]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[374]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[777]++;
int CodeCoverConditionCoverageHelper_C240;
        if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((this.drawSharedDomainAxis != that.drawSharedDomainAxis) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[375]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[376]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[778]++;
int CodeCoverConditionCoverageHelper_C241;
        if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((this.rangeAxes.equals(that.rangeAxes)) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[377]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[378]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[779]++;
int CodeCoverConditionCoverageHelper_C242;
        if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((this.rangeAxisLocations.equals(that.rangeAxisLocations)) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[379]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[380]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[780]++;
int CodeCoverConditionCoverageHelper_C243;
        if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.datasetToDomainAxisMap, 
                that.datasetToDomainAxisMap)) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[381]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[382]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[781]++;
int CodeCoverConditionCoverageHelper_C244;
        if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.datasetToRangeAxisMap, 
                that.datasetToRangeAxisMap)) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[383]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[384]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[782]++;
int CodeCoverConditionCoverageHelper_C245;
        if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.renderers, that.renderers)) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[385]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[386]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[783]++;
int CodeCoverConditionCoverageHelper_C246;
        if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((this.renderingOrder != that.renderingOrder) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[387]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[388]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[784]++;
int CodeCoverConditionCoverageHelper_C247;
        if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((this.columnRenderingOrder != that.columnRenderingOrder) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[389]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[390]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[785]++;
int CodeCoverConditionCoverageHelper_C248;
        if ((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((this.rowRenderingOrder != that.rowRenderingOrder) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[391]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[392]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[786]++;
int CodeCoverConditionCoverageHelper_C249;
        if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((this.domainGridlinesVisible != that.domainGridlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[393]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[394]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[787]++;
int CodeCoverConditionCoverageHelper_C250;
        if ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((this.domainGridlinePosition != that.domainGridlinePosition) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[395]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[396]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[788]++;
int CodeCoverConditionCoverageHelper_C251;
        if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.domainGridlineStroke, 
                that.domainGridlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[397]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[398]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[789]++;
int CodeCoverConditionCoverageHelper_C252;
        if ((((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.domainGridlinePaint, 
                that.domainGridlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[399]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[400]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[790]++;
int CodeCoverConditionCoverageHelper_C253;
        if ((((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((this.rangeGridlinesVisible != that.rangeGridlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[401]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[402]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[791]++;
int CodeCoverConditionCoverageHelper_C254;
        if ((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rangeGridlineStroke, 
                that.rangeGridlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[403]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[404]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[792]++;
int CodeCoverConditionCoverageHelper_C255;
        if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.rangeGridlinePaint, 
                that.rangeGridlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[405]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[406]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[793]++;
int CodeCoverConditionCoverageHelper_C256;
        if ((((((CodeCoverConditionCoverageHelper_C256 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C256 |= (2)) == 0 || true) &&
 ((this.anchorValue != that.anchorValue) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[407]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[408]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[794]++;
int CodeCoverConditionCoverageHelper_C257;
        if ((((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairVisible != that.rangeCrosshairVisible) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[409]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[410]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[795]++;
int CodeCoverConditionCoverageHelper_C258;
        if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairValue != that.rangeCrosshairValue) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[411]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[412]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[796]++;
int CodeCoverConditionCoverageHelper_C259;
        if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rangeCrosshairStroke, 
                that.rangeCrosshairStroke)) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[413]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[414]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[797]++;
int CodeCoverConditionCoverageHelper_C260;
        if ((((((CodeCoverConditionCoverageHelper_C260 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C260 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.rangeCrosshairPaint, 
                that.rangeCrosshairPaint)) && 
  ((CodeCoverConditionCoverageHelper_C260 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[415]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[416]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[798]++;
int CodeCoverConditionCoverageHelper_C261;
        if ((((((CodeCoverConditionCoverageHelper_C261 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C261 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairLockedOnData 
                != that.rangeCrosshairLockedOnData) && 
  ((CodeCoverConditionCoverageHelper_C261 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[417]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[418]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[799]++;
int CodeCoverConditionCoverageHelper_C262;      
        if ((((((CodeCoverConditionCoverageHelper_C262 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C262 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.foregroundRangeMarkers, 
                that.foregroundRangeMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C262 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[419]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[420]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[800]++;
int CodeCoverConditionCoverageHelper_C263;
        if ((((((CodeCoverConditionCoverageHelper_C263 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C263 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.backgroundRangeMarkers, 
                that.backgroundRangeMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C263 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[421]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[422]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[801]++;
int CodeCoverConditionCoverageHelper_C264;
        if ((((((CodeCoverConditionCoverageHelper_C264 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C264 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.annotations, that.annotations)) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[423]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[424]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[802]++;
int CodeCoverConditionCoverageHelper_C265;
        if ((((((CodeCoverConditionCoverageHelper_C265 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C265 |= (2)) == 0 || true) &&
 ((this.weight != that.weight) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[425]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[426]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[803]++;
int CodeCoverConditionCoverageHelper_C266;
        if ((((((CodeCoverConditionCoverageHelper_C266 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C266 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.fixedDomainAxisSpace, 
                that.fixedDomainAxisSpace)) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[427]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[428]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[804]++;
int CodeCoverConditionCoverageHelper_C267;    
        if ((((((CodeCoverConditionCoverageHelper_C267 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C267 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.fixedRangeAxisSpace, 
                that.fixedRangeAxisSpace)) && 
  ((CodeCoverConditionCoverageHelper_C267 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[429]++;
            return false;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[430]++;}    
        
        return true;
        
    }
    
    /**
     * Returns a clone of the plot.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[805]++;
        
        CategoryPlot clone = (CategoryPlot) super.clone();
        
        clone.domainAxes = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[806]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[807]++;
byte CodeCoverTryBranchHelper_L53 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[157]++;


int CodeCoverConditionCoverageHelper_C268;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C268 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C268 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C268 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L53 == 0) {
  CodeCoverTryBranchHelper_L53++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[157]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[158]++;
} else if (CodeCoverTryBranchHelper_L53 == 1) {
  CodeCoverTryBranchHelper_L53++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[158]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[159]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[808]++;
            CategoryAxis xAxis = (CategoryAxis) this.domainAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[809]++;
int CodeCoverConditionCoverageHelper_C269;
            if ((((((CodeCoverConditionCoverageHelper_C269 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C269 |= (2)) == 0 || true) &&
 ((xAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C269 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[431]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[810]++;
                CategoryAxis clonedAxis = (CategoryAxis) xAxis.clone();
                clone.setDomainAxis(i, clonedAxis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[811]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[432]++;}
        }
        clone.domainAxisLocations 
            = (ObjectList) this.domainAxisLocations.clone();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[812]++;

        clone.rangeAxes = new ObjectList();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[813]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[814]++;
byte CodeCoverTryBranchHelper_L54 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[160]++;


int CodeCoverConditionCoverageHelper_C270;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C270 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C270 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L54 == 0) {
  CodeCoverTryBranchHelper_L54++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[160]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[161]++;
} else if (CodeCoverTryBranchHelper_L54 == 1) {
  CodeCoverTryBranchHelper_L54++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[161]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[162]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[815]++;
            ValueAxis yAxis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[816]++;
int CodeCoverConditionCoverageHelper_C271;
            if ((((((CodeCoverConditionCoverageHelper_C271 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C271 |= (2)) == 0 || true) &&
 ((yAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C271 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[433]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[817]++;
                ValueAxis clonedAxis = (ValueAxis) yAxis.clone();
                clone.setRangeAxis(i, clonedAxis);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[818]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[434]++;}
        }
        clone.rangeAxisLocations = (ObjectList) this.rangeAxisLocations.clone();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[819]++;

        clone.datasets = (ObjectList) this.datasets.clone();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[820]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[821]++;
byte CodeCoverTryBranchHelper_L55 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[163]++;


int CodeCoverConditionCoverageHelper_C272;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C272 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C272 |= (2)) == 0 || true) &&
 ((i < clone.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C272 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L55 == 0) {
  CodeCoverTryBranchHelper_L55++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[163]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[164]++;
} else if (CodeCoverTryBranchHelper_L55 == 1) {
  CodeCoverTryBranchHelper_L55++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[164]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[165]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[822]++;
            CategoryDataset dataset = clone.getDataset(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[823]++;
int CodeCoverConditionCoverageHelper_C273;
            if ((((((CodeCoverConditionCoverageHelper_C273 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C273 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C273 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[435]++;
                dataset.addChangeListener(clone);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[824]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[436]++;}
        }
        clone.datasetToDomainAxisMap 
            = (ObjectList) this.datasetToDomainAxisMap.clone();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[825]++;
        clone.datasetToRangeAxisMap 
            = (ObjectList) this.datasetToRangeAxisMap.clone();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[826]++;
        clone.renderers = (ObjectList) this.renderers.clone();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[827]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[828]++;
int CodeCoverConditionCoverageHelper_C274;
        if ((((((CodeCoverConditionCoverageHelper_C274 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C274 |= (2)) == 0 || true) &&
 ((this.fixedDomainAxisSpace != null) && 
  ((CodeCoverConditionCoverageHelper_C274 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[437]++;
            clone.fixedDomainAxisSpace = (AxisSpace) ObjectUtilities.clone(
                    this.fixedDomainAxisSpace);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[829]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[438]++;}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[830]++;
int CodeCoverConditionCoverageHelper_C275;
        if ((((((CodeCoverConditionCoverageHelper_C275 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C275 |= (2)) == 0 || true) &&
 ((this.fixedRangeAxisSpace != null) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[439]++;
            clone.fixedRangeAxisSpace = (AxisSpace) ObjectUtilities.clone(
                    this.fixedRangeAxisSpace);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[831]++;

        } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[440]++;}
        
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
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[832]++;
        SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[833]++;
        SerialUtilities.writePaint(this.domainGridlinePaint, stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[834]++;
        SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[835]++;
        SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[836]++;
        SerialUtilities.writeStroke(this.rangeCrosshairStroke, stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[837]++;
        SerialUtilities.writePaint(this.rangeCrosshairPaint, stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[838]++;
    }

    /**
     * Provides serialization support.
     *
     * @param stream  the input stream.
     *
     * @throws IOException  if there is an I/O error.
     * @throws ClassNotFoundException  if there is a classpath problem.
     */
    private void readObject(ObjectInputStream stream) 
        throws IOException, ClassNotFoundException {

        stream.defaultReadObject();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[839]++;
        this.domainGridlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[840]++;
        this.domainGridlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[841]++;
        this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[842]++;
        this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[843]++;
        this.rangeCrosshairStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[844]++;
        this.rangeCrosshairPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[845]++;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[846]++;
byte CodeCoverTryBranchHelper_L56 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[166]++;


int CodeCoverConditionCoverageHelper_C276;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C276 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C276 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L56 == 0) {
  CodeCoverTryBranchHelper_L56++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[166]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[167]++;
} else if (CodeCoverTryBranchHelper_L56 == 1) {
  CodeCoverTryBranchHelper_L56++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[167]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[168]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[847]++;
            CategoryAxis xAxis = (CategoryAxis) this.domainAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[848]++;
int CodeCoverConditionCoverageHelper_C277;
            if ((((((CodeCoverConditionCoverageHelper_C277 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C277 |= (2)) == 0 || true) &&
 ((xAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C277 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[441]++;
                xAxis.setPlot(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[849]++;
                xAxis.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[850]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[442]++;}
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[851]++;
byte CodeCoverTryBranchHelper_L57 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[169]++;


int CodeCoverConditionCoverageHelper_C278; 
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C278 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C278 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L57 == 0) {
  CodeCoverTryBranchHelper_L57++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[169]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[170]++;
} else if (CodeCoverTryBranchHelper_L57 == 1) {
  CodeCoverTryBranchHelper_L57++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[170]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[171]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[852]++;
            ValueAxis yAxis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[853]++;
int CodeCoverConditionCoverageHelper_C279;
            if ((((((CodeCoverConditionCoverageHelper_C279 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C279 |= (2)) == 0 || true) &&
 ((yAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C279 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[443]++;
                yAxis.setPlot(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[854]++;   
                yAxis.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[855]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[444]++;}
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[856]++;
        int datasetCount = this.datasets.size();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[857]++;
byte CodeCoverTryBranchHelper_L58 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[172]++;


int CodeCoverConditionCoverageHelper_C280;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C280 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C280 |= (2)) == 0 || true) &&
 ((i < datasetCount) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L58 == 0) {
  CodeCoverTryBranchHelper_L58++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[172]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[173]++;
} else if (CodeCoverTryBranchHelper_L58 == 1) {
  CodeCoverTryBranchHelper_L58++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[173]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[174]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[858]++;
            Dataset dataset = (Dataset) this.datasets.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[859]++;
int CodeCoverConditionCoverageHelper_C281;
            if ((((((CodeCoverConditionCoverageHelper_C281 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C281 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C281 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[445]++;
                dataset.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[860]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[446]++;}
        }
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[861]++;
        int rendererCount = this.renderers.size();
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[862]++;
byte CodeCoverTryBranchHelper_L59 = 0;
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[175]++;


int CodeCoverConditionCoverageHelper_C282;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C282 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C282 |= (2)) == 0 || true) &&
 ((i < rendererCount) && 
  ((CodeCoverConditionCoverageHelper_C282 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L59 == 0) {
  CodeCoverTryBranchHelper_L59++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[175]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[176]++;
} else if (CodeCoverTryBranchHelper_L59 == 1) {
  CodeCoverTryBranchHelper_L59++;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[176]--;
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.loops[177]++;
}
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[863]++;
            CategoryItemRenderer renderer 
                = (CategoryItemRenderer) this.renderers.get(i);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[864]++;
int CodeCoverConditionCoverageHelper_C283;
            if ((((((CodeCoverConditionCoverageHelper_C283 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C283 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C283 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) || true)) || (CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) && false)) {
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[447]++;
                renderer.addChangeListener(this);
CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.statements[865]++;

            } else {
  CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t.branches[448]++;}
        }

    }

}

class CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t ());
  }
    public static long[] statements = new long[866];
    public static long[] branches = new long[449];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[284];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.CategoryPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 283; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[178];

  public CodeCoverCoverageCounter$sd7jgrcliqd2fpzvg6382lc95t () {
    super("org.jfree.chart.plot.CategoryPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 865; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 448; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 283; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 177; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.CategoryPlot.java");
      for (int i = 1; i <= 865; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 448; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 283; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 59; i++) {
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

