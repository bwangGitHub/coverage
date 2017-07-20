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
 * -----------
 * XYPlot.java
 * -----------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Craig MacFarlane;
 *                   Mark Watson (www.markwatson.com);
 *                   Jonathan Nash;
 *                   Gideon Krause;
 *                   Klaus Rheinwald;
 *                   Xavier Poinsard;
 *                   Richard Atkinson;
 *                   Arnaud Lelievre;
 *                   Nicolas Brodu;
 *                   Eduardo Ramalho;
 *                   Sergei Ivanov;
 *                   Richard West, Advanced Micro Devices, Inc.;
 *
 * Changes (from 21-Jun-2001)
 * --------------------------
 * 21-Jun-2001 : Removed redundant JFreeChart parameter from constructors (DG);
 * 18-Sep-2001 : Updated header and fixed DOS encoding problem (DG);
 * 15-Oct-2001 : Data source classes moved to com.jrefinery.data.* (DG);
 * 19-Oct-2001 : Removed the code for drawing the visual representation of each
 *               data point into a separate class StandardXYItemRenderer.
 *               This will make it easier to add variations to the way the
 *               charts are drawn.  Based on code contributed by Mark
 *               Watson (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 20-Nov-2001 : Fixed clipping bug that shows up when chart is displayed
 *               inside JScrollPane (DG);
 * 12-Dec-2001 : Removed unnecessary 'throws' clauses from constructor (DG);
 * 13-Dec-2001 : Added skeleton code for tooltips.  Added new constructor. (DG);
 * 16-Jan-2002 : Renamed the tooltips class (DG);
 * 22-Jan-2002 : Added DrawInfo class, incorporating tooltips and crosshairs.
 *               Crosshairs based on code by Jonathan Nash (DG);
 * 05-Feb-2002 : Added alpha-transparency setting based on code by Sylvain
 *               Vieujot (DG);
 * 26-Feb-2002 : Updated getMinimumXXX() and getMaximumXXX() methods to handle
 *               special case when chart is null (DG);
 * 28-Feb-2002 : Renamed Datasets.java --> DatasetUtilities.java (DG);
 * 28-Mar-2002 : The plot now registers with the renderer as a property change
 *               listener.  Also added a new constructor (DG);
 * 09-Apr-2002 : Removed the transRangeZero from the renderer.drawItem()
 *               method.  Moved the tooltip generator into the renderer (DG);
 * 23-Apr-2002 : Fixed bug in methods for drawing horizontal and vertical
 *               lines (DG);
 * 13-May-2002 : Small change to the draw() method so that it works for
 *               OverlaidXYPlot also (DG);
 * 25-Jun-2002 : Removed redundant import (DG);
 * 20-Aug-2002 : Renamed getItemRenderer() --> getRenderer(), and
 *               setXYItemRenderer() --> setRenderer() (DG);
 * 28-Aug-2002 : Added mechanism for (optional) plot annotations (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 18-Nov-2002 : Added grid settings for both domain and range axis (previously
 *               these were set in the axes) (DG);
 * 09-Jan-2003 : Further additions to the grid settings, plus integrated plot
 *               border bug fix contributed by Gideon Krause (DG);
 * 22-Jan-2003 : Removed monolithic constructor (DG);
 * 04-Mar-2003 : Added 'no data' message, see bug report 691634.  Added
 *               secondary range markers using code contributed by Klaus
 *               Rheinwald (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 03-Apr-2003 : Added setDomainAxisLocation() method (DG);
 * 30-Apr-2003 : Moved annotation drawing into a separate method (DG);
 * 01-May-2003 : Added multi-pass mechanism for renderers (DG);
 * 02-May-2003 : Changed axis locations from int to AxisLocation (DG);
 * 15-May-2003 : Added an orientation attribute (DG);
 * 02-Jun-2003 : Removed range axis compatibility test (DG);
 * 05-Jun-2003 : Added domain and range grid bands (sponsored by Focus Computer
 *               Services Ltd) (DG);
 * 26-Jun-2003 : Fixed bug (757303) in getDataRange() method (DG);
 * 02-Jul-2003 : Added patch from bug report 698646 (secondary axes for
 *               overlaid plots) (DG);
 * 23-Jul-2003 : Added support for multiple secondary datasets, axes and
 *               renderers (DG);
 * 27-Jul-2003 : Added support for stacked XY area charts (RA);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 01-Sep-2003 : Fixed bug where change to secondary datasets didn't generate
 *               change event (797466) (DG)
 * 08-Sep-2003 : Added internationalization via use of properties
 *               resourceBundle (RFE 690236) (AL);
 * 08-Sep-2003 : Changed ValueAxis API (DG);
 * 08-Sep-2003 : Fixes for serialization (NB);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 17-Sep-2003 : Fixed zooming to include secondary domain axes (DG);
 * 18-Sep-2003 : Added getSecondaryDomainAxisCount() and
 *               getSecondaryRangeAxisCount() methods suggested by Eduardo
 *               Ramalho (RFE 808548) (DG);
 * 23-Sep-2003 : Split domain and range markers into foreground and
 *               background (DG);
 * 06-Oct-2003 : Fixed bug in clearDomainMarkers() and clearRangeMarkers()
 *               methods.  Fixed bug (815876) in addSecondaryRangeMarker()
 *               method.  Added new addSecondaryDomainMarker methods (see bug
 *               id 815869) (DG);
 * 10-Nov-2003 : Added getSecondaryDomain/RangeAxisMappedToDataset() methods
 *               requested by Eduardo Ramalho (DG);
 * 24-Nov-2003 : Removed unnecessary notification when updating axis anchor
 *               values (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 12-Mar-2004 : Fixed bug where primary renderer is always used to determine
 *               range type (DG);
 * 22-Mar-2004 : Fixed cloning bug (DG);
 * 23-Mar-2004 : Fixed more cloning bugs (DG);
 * 07-Apr-2004 : Fixed problem with axis range when the secondary renderer is
 *               stacked, see this post in the forum:
 *               http://www.jfree.org/phpBB2/viewtopic.php?t=8204 (DG);
 * 07-Apr-2004 : Added get/setDatasetRenderingOrder() methods (DG);
 * 26-Apr-2004 : Added option to fill quadrant areas in the background of the
 *               plot (DG);
 * 27-Apr-2004 : Removed major distinction between primary and secondary
 *               datasets, renderers and axes (DG);
 * 30-Apr-2004 : Modified to make use of the new getRangeExtent() method in the
 *               renderer interface (DG);
 * 13-May-2004 : Added optional fixedLegendItems attribute (DG);
 * 19-May-2004 : Added indexOf() method (DG);
 * 03-Jun-2004 : Fixed zooming bug (DG);
 * 18-Aug-2004 : Added removedAnnotation() method (by tkram01) (DG);
 * 05-Oct-2004 : Modified storage type for dataset-to-axis maps (DG);
 * 06-Oct-2004 : Modified getDataRange() method to use renderer to determine
 *               the x-value range (now matches behaviour for y-values).  Added
 *               getDomainAxisIndex() method (DG);
 * 12-Nov-2004 : Implemented new Zoomable interface (DG);
 * 25-Nov-2004 : Small update to clone() implementation (DG);
 * 22-Feb-2005 : Changed axis offsets from Spacer --> RectangleInsets (DG);
 * 24-Feb-2005 : Added indexOf(XYItemRenderer) method (DG);
 * 21-Mar-2005 : Register plot as change listener in setRenderer() method (DG);
 * 21-Apr-2005 : Added get/setSeriesRenderingOrder() methods (ET);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 04-May-2005 : Fixed serialization of domain and range markers (DG);
 * 05-May-2005 : Removed unused draw() method (DG);
 * 20-May-2005 : Added setDomainAxes() and setRangeAxes() methods, as per
 *               RFE 1183100 (DG);
 * 01-Jun-2005 : Upon deserialization, register plot as a listener with its
 *               axes, dataset(s) and renderer(s) - see patch 1209475 (DG);
 * 01-Jun-2005 : Added clearDomainMarkers(int) method to match 
 *               clearRangeMarkers(int) (DG);
 * 06-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * 09-Jun-2005 : Added setRenderers(), as per RFE 1183100 (DG);
 * 06-Jul-2005 : Fixed crosshair bug (id = 1233336) (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 26-Jan-2006 : Added getAnnotations() method (DG);
 * 05-Sep-2006 : Added MarkerChangeEvent support (DG);
 * 13-Oct-2006 : Fixed initialisation of CrosshairState - see bug report 
 *               1565168 (DG);
 * 22-Nov-2006 : Fixed equals() and cloning() for quadrant attributes, plus 
 *               API doc updates (DG);
 * 29-Nov-2006 : Added argument checks (DG);
 * 15-Jan-2007 : Fixed bug in drawRangeMarkers() (DG);
 * 07-Feb-2007 : Fixed bug 1654215, renderer with no dataset (DG);
 * 26-Feb-2007 : Added missing setDomainAxisLocation() and 
 *               setRangeAxisLocation() methods (DG);
 * 02-Mar-2007 : Fix for crosshair positioning with horizontal orientation
 *               (see patch 1671648 by Sergei Ivanov) (DG);
 * 13-Mar-2007 : Added null argument checks for crosshair attributes (DG);
 * 23-Mar-2007 : Added domain zero base line facility (DG);
 * 04-May-2007 : Render only visible data items if possible (DG);
 * 24-May-2007 : Fixed bug in render method for an empty series (DG);
 * 07-Jun-2007 : Modified drawBackground() to pass orientation to 
 *               fillBackground() for handling GradientPaint (DG);
 * 24-Sep-2007 : Added new zoom methods (DG);
 * 26-Sep-2007 : Include index value in IllegalArgumentExceptions (DG);
 * 05-Nov-2007 : Applied patch 1823697, by Richard West, for removal of domain
 *               and range markers (DG);
 * 12-Nov-2007 : Fixed bug in equals() method for domain and range tick
 *               band paint attributes (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
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
import java.util.TreeMap;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.AxisCollection;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.ValueTick;
import org.jfree.chart.event.ChartChangeEventType;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.renderer.RendererUtilities;
import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.data.Range;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectList;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A general class for plotting data in the form of (x, y) pairs.  This plot can
 * use data from any class that implements the {@link XYDataset} interface.
 * <P>
 * <code>XYPlot</code> makes use of an {@link XYItemRenderer} to draw each point
 * on the plot.  By using different renderers, various chart types can be
 * produced.
 * <p>
 * The {@link org.jfree.chart.ChartFactory} class contains static methods for
 * creating pre-configured charts.
 */
public class XYPlot extends Plot implements ValueAxisPlot,
                                            Zoomable,
                                            RendererChangeListener,
                                            Cloneable, PublicCloneable,
                                            Serializable {
  static {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7044148245716569264L;
  static {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1]++;
  }
    
    /** The default grid line stroke. */
    public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0.0f, 
            new float[] {2.0f, 2.0f}, 0.0f);
  static {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[2]++;
  }

    /** The default grid line paint. */
    public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
  static {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[3]++;
  }

    /** The default crosshair visibility. */
    public static final boolean DEFAULT_CROSSHAIR_VISIBLE = false;
  static {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[4]++;
  }

    /** The default crosshair stroke. */
    public static final Stroke DEFAULT_CROSSHAIR_STROKE
            = DEFAULT_GRIDLINE_STROKE;
  static {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[5]++;
  }

    /** The default crosshair paint. */
    public static final Paint DEFAULT_CROSSHAIR_PAINT = Color.blue;
  static {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[6]++;
  }

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources 
            = ResourceBundle.getBundle(
                    "org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[7]++;
  }

    /** The plot orientation. */
    private PlotOrientation orientation;

    /** The offset between the data area and the axes. */
    private RectangleInsets axisOffset;

    /** The domain axis / axes (used for the x-values). */
    private ObjectList domainAxes;

    /** The domain axis locations. */
    private ObjectList domainAxisLocations;

    /** The range axis (used for the y-values). */
    private ObjectList rangeAxes;

    /** The range axis location. */
    private ObjectList rangeAxisLocations;

    /** Storage for the datasets. */
    private ObjectList datasets;

    /** Storage for the renderers. */
    private ObjectList renderers;

    /**
     * Storage for keys that map datasets/renderers to domain axes.  If the
     * map contains no entry for a dataset, it is assumed to map to the
     * primary domain axis (index = 0).
     */
    private Map datasetToDomainAxisMap;

    /**
     * Storage for keys that map datasets/renderers to range axes. If the
     * map contains no entry for a dataset, it is assumed to map to the
     * primary domain axis (index = 0).
     */
    private Map datasetToRangeAxisMap;

    /** The origin point for the quadrants (if drawn). */
    private transient Point2D quadrantOrigin = new Point2D.Double(0.0, 0.0);
  {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[8]++;
  }

    /** The paint used for each quadrant. */
    private transient Paint[] quadrantPaint
            = new Paint[] {null, null, null, null};
  {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[9]++;
  }

    /** A flag that controls whether the domain grid-lines are visible. */
    private boolean domainGridlinesVisible;

    /** The stroke used to draw the domain grid-lines. */
    private transient Stroke domainGridlineStroke;

    /** The paint used to draw the domain grid-lines. */
    private transient Paint domainGridlinePaint;

    /** A flag that controls whether the range grid-lines are visible. */
    private boolean rangeGridlinesVisible;

    /** The stroke used to draw the range grid-lines. */
    private transient Stroke rangeGridlineStroke;

    /** The paint used to draw the range grid-lines. */
    private transient Paint rangeGridlinePaint;

    /** 
     * A flag that controls whether or not the zero baseline against the domain
     * axis is visible.
     * 
     * @since 1.0.5
     */
    private boolean domainZeroBaselineVisible;

    /** 
     * The stroke used for the zero baseline against the domain axis. 
     * 
     * @since 1.0.5
     */
    private transient Stroke domainZeroBaselineStroke;

    /** 
     * The paint used for the zero baseline against the domain axis. 
     * 
     * @since 1.0.5
     */
    private transient Paint domainZeroBaselinePaint;

    /** 
     * A flag that controls whether or not the zero baseline against the range
     * axis is visible.
     */
    private boolean rangeZeroBaselineVisible;

    /** The stroke used for the zero baseline against the range axis. */
    private transient Stroke rangeZeroBaselineStroke;

    /** The paint used for the zero baseline against the range axis. */
    private transient Paint rangeZeroBaselinePaint;

    /** A flag that controls whether or not a domain crosshair is drawn..*/
    private boolean domainCrosshairVisible;

    /** The domain crosshair value. */
    private double domainCrosshairValue;

    /** The pen/brush used to draw the crosshair (if any). */
    private transient Stroke domainCrosshairStroke;

    /** The color used to draw the crosshair (if any). */
    private transient Paint domainCrosshairPaint;

    /**
     * A flag that controls whether or not the crosshair locks onto actual
     * data points.
     */
    private boolean domainCrosshairLockedOnData = true;
  {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[10]++;
  }

    /** A flag that controls whether or not a range crosshair is drawn..*/
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
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[11]++;
  }

    /** A map of lists of foreground markers (optional) for the domain axes. */
    private Map foregroundDomainMarkers;

    /** A map of lists of background markers (optional) for the domain axes. */
    private Map backgroundDomainMarkers;

    /** A map of lists of foreground markers (optional) for the range axes. */
    private Map foregroundRangeMarkers;

    /** A map of lists of background markers (optional) for the range axes. */
    private Map backgroundRangeMarkers;

    /** 
     * A (possibly empty) list of annotations for the plot.  The list should
     * be initialised in the constructor and never allowed to be 
     * <code>null</code>.
     */
    private List annotations;

    /** The paint used for the domain tick bands (if any). */
    private transient Paint domainTickBandPaint;

    /** The paint used for the range tick bands (if any). */
    private transient Paint rangeTickBandPaint;

    /** The fixed domain axis space. */
    private AxisSpace fixedDomainAxisSpace;

    /** The fixed range axis space. */
    private AxisSpace fixedRangeAxisSpace;

    /**
     * The order of the dataset rendering (REVERSE draws the primary dataset
     * last so that it appears to be on top).
     */
    private DatasetRenderingOrder datasetRenderingOrder
            = DatasetRenderingOrder.REVERSE;
  {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[12]++;
  }

    /**
     * The order of the series rendering (REVERSE draws the primary series
     * last so that it appears to be on top).
     */
    private SeriesRenderingOrder seriesRenderingOrder
            = SeriesRenderingOrder.REVERSE;
  {
    CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[13]++;
  }

    /**
     * The weight for this plot (only relevant if this is a subplot in a
     * combined plot).
     */
    private int weight;

    /**
     * An optional collection of legend items that can be returned by the
     * getLegendItems() method.
     */
    private LegendItemCollection fixedLegendItems;

    /**
     * Creates a new <code>XYPlot</code> instance with no dataset, no axes and
     * no renderer.  You should specify these items before using the plot.
     */
    public XYPlot() {
        this(null, null, null, null);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[14]++;
    }

    /**
     * Creates a new plot with the specified dataset, axes and renderer.  Any
     * of the arguments can be <code>null</code>, but in that case you should
     * take care to specify the value before using the plot (otherwise a
     * <code>NullPointerException</code> may be thrown).
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * @param domainAxis  the domain axis (<code>null</code> permitted).
     * @param rangeAxis  the range axis (<code>null</code> permitted).
     * @param renderer  the renderer (<code>null</code> permitted).
     */
    public XYPlot(XYDataset dataset,
                  ValueAxis domainAxis,
                  ValueAxis rangeAxis,
                  XYItemRenderer renderer) {

        super();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[15]++;

        this.orientation = PlotOrientation.VERTICAL;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[16]++;
        this.weight = 1;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[17]++;  // only relevant when this is a subplot
        this.axisOffset = RectangleInsets.ZERO_INSETS;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[18]++;

        // allocate storage for datasets, axes and renderers (all optional)
        this.domainAxes = new ObjectList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[19]++;
        this.domainAxisLocations = new ObjectList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[20]++;
        this.foregroundDomainMarkers = new HashMap();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[21]++;
        this.backgroundDomainMarkers = new HashMap();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[22]++;

        this.rangeAxes = new ObjectList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[23]++;
        this.rangeAxisLocations = new ObjectList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[24]++;
        this.foregroundRangeMarkers = new HashMap();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[25]++;
        this.backgroundRangeMarkers = new HashMap();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[26]++;

        this.datasets = new ObjectList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[27]++;
        this.renderers = new ObjectList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[28]++;

        this.datasetToDomainAxisMap = new TreeMap();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[29]++;
        this.datasetToRangeAxisMap = new TreeMap();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[30]++;

        this.datasets.set(0, dataset);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[31]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[32]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[1]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[33]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[2]++;}

        this.renderers.set(0, renderer);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[34]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[35]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[3]++;
            renderer.setPlot(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[36]++;
            renderer.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[37]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[4]++;}

        this.domainAxes.set(0, domainAxis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[38]++;
        this.mapDatasetToDomainAxis(0, 0);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[39]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[40]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[5]++;
            domainAxis.setPlot(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[41]++;
            domainAxis.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[42]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[6]++;}
        this.domainAxisLocations.set(0, AxisLocation.BOTTOM_OR_LEFT);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[43]++;

        this.rangeAxes.set(0, rangeAxis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[44]++;
        this.mapDatasetToRangeAxis(0, 0);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[45]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[46]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[7]++;
            rangeAxis.setPlot(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[47]++;
            rangeAxis.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[48]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[8]++;}
        this.rangeAxisLocations.set(0, AxisLocation.BOTTOM_OR_LEFT);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[49]++;

        configureDomainAxes();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[50]++;
        configureRangeAxes();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[51]++;

        this.domainGridlinesVisible = true;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[52]++;
        this.domainGridlineStroke = DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[53]++;
        this.domainGridlinePaint = DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[54]++;

        this.domainZeroBaselineVisible = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[55]++;
        this.domainZeroBaselinePaint = Color.black;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[56]++;
        this.domainZeroBaselineStroke = new BasicStroke(0.5f);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[57]++;

        this.rangeGridlinesVisible = true;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[58]++;
        this.rangeGridlineStroke = DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[59]++;
        this.rangeGridlinePaint = DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[60]++;

        this.rangeZeroBaselineVisible = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[61]++;
        this.rangeZeroBaselinePaint = Color.black;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[62]++;
        this.rangeZeroBaselineStroke = new BasicStroke(0.5f);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[63]++;

        this.domainCrosshairVisible = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[64]++;
        this.domainCrosshairValue = 0.0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[65]++;
        this.domainCrosshairStroke = DEFAULT_CROSSHAIR_STROKE;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[66]++;
        this.domainCrosshairPaint = DEFAULT_CROSSHAIR_PAINT;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[67]++;

        this.rangeCrosshairVisible = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[68]++;
        this.rangeCrosshairValue = 0.0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[69]++;
        this.rangeCrosshairStroke = DEFAULT_CROSSHAIR_STROKE;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[70]++;
        this.rangeCrosshairPaint = DEFAULT_CROSSHAIR_PAINT;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[71]++;

        this.annotations = new java.util.ArrayList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[72]++;

    }

    /**
     * Returns the plot type as a string.
     *
     * @return A short string describing the type of plot.
     */
    public String getPlotType() {
        return localizationResources.getString("XY_Plot");
    }

    /**
     * Returns the orientation of the plot.
     *
     * @return The orientation (never <code>null</code>).
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
     * @param orientation  the orientation (<code>null</code> not allowed).
     * 
     * @see #getOrientation()
     */
    public void setOrientation(PlotOrientation orientation) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[73]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[9]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[10]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[74]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((orientation != this.orientation) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[11]++;
            this.orientation = orientation;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[75]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[76]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[12]++;}
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
     * Sets the axis offsets (gap between the data area and the axes) and sends
     * a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param offset  the offset (<code>null</code> not permitted).
     * 
     * @see #getAxisOffset()
     */
    public void setAxisOffset(RectangleInsets offset) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[77]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((offset == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[13]++;
            throw new IllegalArgumentException("Null 'offset' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[14]++;}
        this.axisOffset = offset;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[78]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[79]++;
    }

    /**
     * Returns the domain axis with index 0.  If the domain axis for this plot
     * is <code>null</code>, then the method will return the parent plot's 
     * domain axis (if there is a parent plot).
     *
     * @return The domain axis (possibly <code>null</code>).
     * 
     * @see #getDomainAxis(int)
     * @see #setDomainAxis(ValueAxis)
     */
    public ValueAxis getDomainAxis() {
        return getDomainAxis(0);
    }

    /**
     * Returns the domain axis with the specified index, or <code>null</code>.
     *
     * @param index  the axis index.
     *
     * @return The axis (<code>null</code> possible).
     * 
     * @see #setDomainAxis(int, ValueAxis)
     */
    public ValueAxis getDomainAxis(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[80]++;
        ValueAxis result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[81]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((index < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[15]++;
            result = (ValueAxis) this.domainAxes.get(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[82]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[16]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[83]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[17]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[84]++;
            Plot parent = getParent();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[85]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((parent instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[19]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[86]++;
                XYPlot xy = (XYPlot) parent;
                result = xy.getDomainAxis(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[87]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[20]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[18]++;}
        return result;
    }

    /**
     * Sets the domain axis for the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param axis  the new axis (<code>null</code> permitted).
     * 
     * @see #getDomainAxis()
     * @see #setDomainAxis(int, ValueAxis)
     */
    public void setDomainAxis(ValueAxis axis) {
        setDomainAxis(0, axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[88]++;
    }

    /**
     * Sets a domain axis and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param index  the axis index.
     * @param axis  the axis (<code>null</code> permitted).
     * 
     * @see #getDomainAxis(int)
     * @see #setRangeAxis(int, ValueAxis)
     */
    public void setDomainAxis(int index, ValueAxis axis) {
        setDomainAxis(index, axis, true);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[89]++;
    }
    
    /**
     * Sets a domain axis and, if requested, sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param index  the axis index.
     * @param axis  the axis.
     * @param notify  notify listeners?
     * 
     * @see #getDomainAxis(int)
     */
    public void setDomainAxis(int index, ValueAxis axis, boolean notify) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[90]++;
        ValueAxis existing = getDomainAxis(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[91]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[21]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[92]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[22]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[93]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[23]++;
            axis.setPlot(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[94]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[24]++;}
        this.domainAxes.set(index, axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[95]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[96]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[25]++;
            axis.configure();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[97]++;
            axis.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[98]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[26]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[99]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[27]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[100]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[28]++;}
    }

    /**
     * Sets the domain axes for this plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param axes  the axes (<code>null</code> not permitted).
     * 
     * @see #setRangeAxes(ValueAxis[])
     */
    public void setDomainAxes(ValueAxis[] axes) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[101]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[1]++;


int CodeCoverConditionCoverageHelper_C15;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < axes.length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[1]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[2]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[3]++;
}
            setDomainAxis(i, axes[i], false);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[102]++;   
        }
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[103]++;
    }
    
    /**
     * Returns the location of the primary domain axis.
     *
     * @return The location (never <code>null</code>).
     * 
     * @see #setDomainAxisLocation(AxisLocation)
     */
    public AxisLocation getDomainAxisLocation() {
        return (AxisLocation) this.domainAxisLocations.get(0);
    }

    /**
     * Sets the location of the primary domain axis and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param location  the location (<code>null</code> not permitted).
     * 
     * @see #getDomainAxisLocation()
     */
    public void setDomainAxisLocation(AxisLocation location) {
        // delegate...
        setDomainAxisLocation(0, location, true);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[104]++;
    }

    /**
     * Sets the location of the domain axis and, if requested, sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param location  the location (<code>null</code> not permitted).
     * @param notify  notify listeners?
     * 
     * @see #getDomainAxisLocation()
     */
    public void setDomainAxisLocation(AxisLocation location, boolean notify) {
        // delegate...
        setDomainAxisLocation(0, location, notify);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[105]++;
    }

    /**
     * Returns the edge for the primary domain axis (taking into account the
     * plot's orientation).
     *
     * @return The edge.
     * 
     * @see #getDomainAxisLocation()
     * @see #getOrientation()
     */
    public RectangleEdge getDomainAxisEdge() {
        return Plot.resolveDomainAxisLocation(getDomainAxisLocation(), 
                this.orientation);
    }

    /**
     * Returns the number of domain axes.
     *
     * @return The axis count.
     * 
     * @see #getRangeAxisCount()
     */
    public int getDomainAxisCount() {
        return this.domainAxes.size();
    }

    /**
     * Clears the domain axes from the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @see #clearRangeAxes()
     */
    public void clearDomainAxes() {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[106]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[4]++;


int CodeCoverConditionCoverageHelper_C16;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[4]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[5]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[6]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[107]++;
            ValueAxis axis = (ValueAxis) this.domainAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[108]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[29]++;
                axis.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[109]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[30]++;}
        }
        this.domainAxes.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[110]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[111]++;
    }

    /**
     * Configures the domain axes. 
     */
    public void configureDomainAxes() {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[112]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[7]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[7]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[8]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[9]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[113]++;
            ValueAxis axis = (ValueAxis) this.domainAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[114]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[31]++;
                axis.configure();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[115]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[32]++;}
        }
    }

    /**
     * Returns the location for a domain axis.  If this hasn't been set
     * explicitly, the method returns the location that is opposite to the
     * primary domain axis location.
     *
     * @param index  the axis index.
     *
     * @return The location (never <code>null</code>).
     * 
     * @see #setDomainAxisLocation(int, AxisLocation)
     */
    public AxisLocation getDomainAxisLocation(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[116]++;
        AxisLocation result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[117]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((index < this.domainAxisLocations.size()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[33]++;
            result = (AxisLocation) this.domainAxisLocations.get(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[118]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[34]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[119]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[35]++;
            result = AxisLocation.getOpposite(getDomainAxisLocation());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[120]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[36]++;}
        return result;
    }

    /**
     * Sets the location for a domain axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param index  the axis index.
     * @param location  the location (<code>null</code> not permitted for index
     *     0).
     * 
     * @see #getDomainAxisLocation(int)
     */
    public void setDomainAxisLocation(int index, AxisLocation location) {
        // delegate...
        setDomainAxisLocation(index, location, true);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[121]++;
    }

    /**
     * Sets the axis location for a domain axis and, if requested, sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param index  the axis index.
     * @param location  the location (<code>null</code> not permitted for 
     *     index 0).
     * @param notify  notify listeners?
     * 
     * @since 1.0.5
     * 
     * @see #getDomainAxisLocation(int)
     * @see #setRangeAxisLocation(int, AxisLocation, boolean)
     */
    public void setDomainAxisLocation(int index, AxisLocation location, 
            boolean notify) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[122]++;
int CodeCoverConditionCoverageHelper_C22;
        
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((location == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[37]++;
            throw new IllegalArgumentException(
                    "Null 'location' for index 0 not permitted.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[38]++;}
        this.domainAxisLocations.set(index, location);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[123]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[124]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[39]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[125]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[40]++;}        
    }

    /**
     * Returns the edge for a domain axis.
     *
     * @param index  the axis index.
     *
     * @return The edge.
     * 
     * @see #getRangeAxisEdge(int)
     */
    public RectangleEdge getDomainAxisEdge(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[126]++;
        AxisLocation location = getDomainAxisLocation(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[127]++;
        RectangleEdge result = Plot.resolveDomainAxisLocation(location, 
                this.orientation);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[128]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[41]++;
            result = RectangleEdge.opposite(getDomainAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[129]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[42]++;}
        return result;
    }

    /**
     * Returns the range axis for the plot.  If the range axis for this plot is
     * <code>null</code>, then the method will return the parent plot's range 
     * axis (if there is a parent plot).
     *
     * @return The range axis.
     * 
     * @see #getRangeAxis(int)
     * @see #setRangeAxis(ValueAxis)
     */
    public ValueAxis getRangeAxis() {
        return getRangeAxis(0);
    }

    /**
     * Sets the range axis for the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param axis  the axis (<code>null</code> permitted).
     *
     * @see #getRangeAxis()
     * @see #setRangeAxis(int, ValueAxis)
     */
    public void setRangeAxis(ValueAxis axis)  {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[130]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[43]++;
            axis.setPlot(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[131]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[44]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[132]++;

        // plot is likely registered as a listener with the existing axis...
        ValueAxis existing = getRangeAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[133]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[45]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[134]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[46]++;}

        this.rangeAxes.set(0, axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[135]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[136]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[47]++;
            axis.configure();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[137]++;
            axis.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[138]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[48]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[139]++;

    }

    /**
     * Returns the location of the primary range axis.
     *
     * @return The location (never <code>null</code>).
     * 
     * @see #setRangeAxisLocation(AxisLocation)
     */
    public AxisLocation getRangeAxisLocation() {
        return (AxisLocation) this.rangeAxisLocations.get(0);
    }

    /**
     * Sets the location of the primary range axis and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param location  the location (<code>null</code> not permitted).
     * 
     * @see #getRangeAxisLocation()
     */
    public void setRangeAxisLocation(AxisLocation location) {
        // delegate...
        setRangeAxisLocation(0, location, true);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[140]++;
    }

    /**
     * Sets the location of the primary range axis and, if requested, sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param location  the location (<code>null</code> not permitted).
     * @param notify  notify listeners?
     * 
     * @see #getRangeAxisLocation()
     */
    public void setRangeAxisLocation(AxisLocation location, boolean notify) {
        // delegate...
        setRangeAxisLocation(0, location, notify);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[141]++;
    }

    /**
     * Returns the edge for the primary range axis.
     *
     * @return The range axis edge.
     * 
     * @see #getRangeAxisLocation()
     * @see #getOrientation()
     */
    public RectangleEdge getRangeAxisEdge() {
        return Plot.resolveRangeAxisLocation(getRangeAxisLocation(), 
                this.orientation);
    }

    /**
     * Returns a range axis.
     *
     * @param index  the axis index.
     *
     * @return The axis (<code>null</code> possible).
     * 
     * @see #setRangeAxis(int, ValueAxis)
     */
    public ValueAxis getRangeAxis(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[142]++;
        ValueAxis result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[143]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((index < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[49]++;
            result = (ValueAxis) this.rangeAxes.get(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[144]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[50]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[145]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[51]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[146]++;
            Plot parent = getParent();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[147]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((parent instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[53]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[148]++;
                XYPlot xy = (XYPlot) parent;
                result = xy.getRangeAxis(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[149]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[54]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[52]++;}
        return result;
    }

    /**
     * Sets a range axis and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param index  the axis index.
     * @param axis  the axis (<code>null</code> permitted).
     * 
     * @see #getRangeAxis(int)
     */
    public void setRangeAxis(int index, ValueAxis axis) {
        setRangeAxis(index, axis, true);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[150]++;
    } 
    
    /**
     * Sets a range axis and, if requested, sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     *
     * @param index  the axis index.
     * @param axis  the axis (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getRangeAxis(int)
     */
    public void setRangeAxis(int index, ValueAxis axis, boolean notify) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[151]++;
        ValueAxis existing = getRangeAxis(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[152]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[55]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[153]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[56]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[154]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[57]++;
            axis.setPlot(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[155]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[58]++;}
        this.rangeAxes.set(index, axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[156]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[157]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[59]++;
            axis.configure();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[158]++;
            axis.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[159]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[60]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[160]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[61]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[161]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[62]++;}
    }

    /**
     * Sets the range axes for this plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param axes  the axes (<code>null</code> not permitted).
     * 
     * @see #setDomainAxes(ValueAxis[])
     */
    public void setRangeAxes(ValueAxis[] axes) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[162]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[10]++;


int CodeCoverConditionCoverageHelper_C35;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i < axes.length) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[10]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[11]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[12]++;
}
            setRangeAxis(i, axes[i], false);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[163]++;   
        }
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[164]++;
    }
    
    /**
     * Returns the number of range axes.
     *
     * @return The axis count.
     * 
     * @see #getDomainAxisCount()
     */
    public int getRangeAxisCount() {
        return this.rangeAxes.size();
    }

    /**
     * Clears the range axes from the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @see #clearDomainAxes()
     */
    public void clearRangeAxes() {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[165]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[13]++;


int CodeCoverConditionCoverageHelper_C36;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[13]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[14]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[15]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[166]++;
            ValueAxis axis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[167]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[63]++;
                axis.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[168]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[64]++;}
        }
        this.rangeAxes.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[169]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[170]++;
    }

    /**
     * Configures the range axes.
     * 
     * @see #configureDomainAxes()
     */
    public void configureRangeAxes() {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[171]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[16]++;


int CodeCoverConditionCoverageHelper_C38;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[16]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[17]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[18]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[172]++;
            ValueAxis axis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[173]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[65]++;
                axis.configure();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[174]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[66]++;}
        }
    }

    /**
     * Returns the location for a range axis.  If this hasn't been set
     * explicitly, the method returns the location that is opposite to the
     * primary range axis location.
     *
     * @param index  the axis index.
     *
     * @return The location (never <code>null</code>).
     * 
     * @see #setRangeAxisLocation(int, AxisLocation)
     */
    public AxisLocation getRangeAxisLocation(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[175]++;
        AxisLocation result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[176]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((index < this.rangeAxisLocations.size()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[67]++;
            result = (AxisLocation) this.rangeAxisLocations.get(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[177]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[68]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[178]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[69]++;
            result = AxisLocation.getOpposite(getRangeAxisLocation());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[179]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[70]++;}
        return result;
    }

    /**
     * Sets the location for a range axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param index  the axis index.
     * @param location  the location (<code>null</code> permitted).
     * 
     * @see #getRangeAxisLocation(int)
     */
    public void setRangeAxisLocation(int index, AxisLocation location) {
        // delegate...
        setRangeAxisLocation(index, location, true);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[180]++;
    }
    
    /**
     * Sets the axis location for a domain axis and, if requested, sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param index  the axis index.
     * @param location  the location (<code>null</code> not permitted for 
     *     index 0).
     * @param notify  notify listeners?
     * 
     * @since 1.0.5
     * 
     * @see #getRangeAxisLocation(int)
     * @see #setDomainAxisLocation(int, AxisLocation, boolean)
     */
    public void setRangeAxisLocation(int index, AxisLocation location, 
            boolean notify) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[181]++;
int CodeCoverConditionCoverageHelper_C42;
        
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((location == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[71]++;
            throw new IllegalArgumentException(
                    "Null 'location' for index 0 not permitted.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[72]++;}
        this.rangeAxisLocations.set(index, location);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[182]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[183]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[73]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[184]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[74]++;}   
    }

    /**
     * Returns the edge for a range axis.
     *
     * @param index  the axis index.
     *
     * @return The edge.
     * 
     * @see #getRangeAxisLocation(int)
     * @see #getOrientation()
     */
    public RectangleEdge getRangeAxisEdge(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[185]++;
        AxisLocation location = getRangeAxisLocation(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[186]++;
        RectangleEdge result = Plot.resolveRangeAxisLocation(location, 
                this.orientation);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[187]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[75]++;
            result = RectangleEdge.opposite(getRangeAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[188]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[76]++;}
        return result;
    }

    /**
     * Returns the primary dataset for the plot.
     *
     * @return The primary dataset (possibly <code>null</code>).
     * 
     * @see #getDataset(int)
     * @see #setDataset(XYDataset)
     */
    public XYDataset getDataset() {
        return getDataset(0);
    }

    /**
     * Returns a dataset.
     *
     * @param index  the dataset index.
     *
     * @return The dataset (possibly <code>null</code>).
     * 
     * @see #setDataset(int, XYDataset)
     */
    public XYDataset getDataset(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[189]++;
        XYDataset result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[190]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.datasets.size() > index) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[77]++;
            result = (XYDataset) this.datasets.get(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[191]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[78]++;}
        return result;
    }

    /**
     * Sets the primary dataset for the plot, replacing the existing dataset if
     * there is one.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset()
     * @see #setDataset(int, XYDataset)
     */
    public void setDataset(XYDataset dataset) {
        setDataset(0, dataset);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[192]++;
    }

    /**
     * Sets a dataset for the plot.
     *
     * @param index  the dataset index.
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset(int)
     */
    public void setDataset(int index, XYDataset dataset) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[193]++;
        XYDataset existing = getDataset(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[194]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[79]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[195]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[80]++;}
        this.datasets.set(index, dataset);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[196]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[197]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[81]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[198]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[82]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[199]++;

        // send a dataset change event to self...
        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[200]++;
    }

    /**
     * Returns the number of datasets.
     *
     * @return The number of datasets.
     */
    public int getDatasetCount() {
        return this.datasets.size();
    }

    /**
     * Returns the index of the specified dataset, or <code>-1</code> if the
     * dataset does not belong to the plot.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     *
     * @return The index.
     */
    public int indexOf(XYDataset dataset) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[201]++;
        int result = -1;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[202]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[19]++;


int CodeCoverConditionCoverageHelper_C48;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((i < this.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[19]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[20]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[21]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[203]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((dataset == this.datasets.get(i)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[83]++;
                result = i;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[204]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[205]++;
                break;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[84]++;}
        }
        return result;
    }

    /**
     * Maps a dataset to a particular domain axis.  All data will be plotted
     * against axis zero by default, no mapping is required for this case.
     *
     * @param index  the dataset index (zero-based).
     * @param axisIndex  the axis index.
     * 
     * @see #mapDatasetToRangeAxis(int, int)
     */
    public void mapDatasetToDomainAxis(int index, int axisIndex) {
        this.datasetToDomainAxisMap.put(new Integer(index), 
                new Integer(axisIndex));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[206]++;
        // fake a dataset change event to update axes...
        datasetChanged(new DatasetChangeEvent(this, getDataset(index)));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[207]++;
    }

    /**
     * Maps a dataset to a particular range axis.  All data will be plotted
     * against axis zero by default, no mapping is required for this case.
     *
     * @param index  the dataset index (zero-based).
     * @param axisIndex  the axis index.
     * 
     * @see #mapDatasetToDomainAxis(int, int)
     */
    public void mapDatasetToRangeAxis(int index, int axisIndex) {
        this.datasetToRangeAxisMap.put(new Integer(index), 
                new Integer(axisIndex));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[208]++;
        // fake a dataset change event to update axes...
        datasetChanged(new DatasetChangeEvent(this, getDataset(index)));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[209]++;
    }

    /**
     * Returns the renderer for the primary dataset.
     *
     * @return The item renderer (possibly <code>null</code>).
     * 
     * @see #setRenderer(XYItemRenderer)
     */
    public XYItemRenderer getRenderer() {
        return getRenderer(0);
    }

    /**
     * Returns the renderer for a dataset, or <code>null</code>.
     *
     * @param index  the renderer index.
     *
     * @return The renderer (possibly <code>null</code>).
     * 
     * @see #setRenderer(int, XYItemRenderer)
     */
    public XYItemRenderer getRenderer(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[210]++;
        XYItemRenderer result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[211]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.renderers.size() > index) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[85]++;
            result = (XYItemRenderer) this.renderers.get(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[212]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[86]++;}
        return result;

    }

    /**
     * Sets the renderer for the primary dataset and sends a
     * {@link PlotChangeEvent} to all registered listeners.  If the renderer
     * is set to <code>null</code>, no data will be displayed.
     *
     * @param renderer  the renderer (<code>null</code> permitted).
     * 
     * @see #getRenderer()
     */
    public void setRenderer(XYItemRenderer renderer) {
        setRenderer(0, renderer);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[213]++;
    }

    /**
     * Sets a renderer and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param index  the index.
     * @param renderer  the renderer.
     * 
     * @see #getRenderer(int)
     */
    public void setRenderer(int index, XYItemRenderer renderer) {
        setRenderer(index, renderer, true);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[214]++;
    }

    /**
     * Sets a renderer and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param index  the index.
     * @param renderer  the renderer.
     * @param notify  notify listeners?
     * 
     * @see #getRenderer(int)
     */
    public void setRenderer(int index, XYItemRenderer renderer, 
                            boolean notify) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[215]++;
        XYItemRenderer existing = getRenderer(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[216]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[87]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[217]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[88]++;}
        this.renderers.set(index, renderer);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[218]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[219]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[89]++;
            renderer.setPlot(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[220]++;
            renderer.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[221]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[90]++;}
        configureDomainAxes();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[222]++;
        configureRangeAxes();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[223]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[224]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[91]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[225]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[92]++;}
    }

    /**
     * Sets the renderers for this plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param renderers  the renderers (<code>null</code> not permitted).
     */
    public void setRenderers(XYItemRenderer[] renderers) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[226]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[22]++;


int CodeCoverConditionCoverageHelper_C54;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i < renderers.length) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[22]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[23]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[24]++;
}
            setRenderer(i, renderers[i], false);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[227]++;   
        }
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[228]++;
    }
    
    /**
     * Returns the dataset rendering order.
     *
     * @return The order (never <code>null</code>).
     * 
     * @see #setDatasetRenderingOrder(DatasetRenderingOrder)
     */
    public DatasetRenderingOrder getDatasetRenderingOrder() {
        return this.datasetRenderingOrder;
    }

    /**
     * Sets the rendering order and sends a {@link PlotChangeEvent} to all
     * registered listeners.  By default, the plot renders the primary dataset
     * last (so that the primary dataset overlays the secondary datasets).
     * You can reverse this if you want to.
     *
     * @param order  the rendering order (<code>null</code> not permitted).
     * 
     * @see #getDatasetRenderingOrder()
     */
    public void setDatasetRenderingOrder(DatasetRenderingOrder order) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[229]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[93]++;
            throw new IllegalArgumentException("Null 'order' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[94]++;}
        this.datasetRenderingOrder = order;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[230]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[231]++;
    }

    /**
     * Returns the series rendering order.
     *
     * @return the order (never <code>null</code>).
     * 
     * @see #setSeriesRenderingOrder(SeriesRenderingOrder)
     */
    public SeriesRenderingOrder getSeriesRenderingOrder() {
        return this.seriesRenderingOrder;
    }

    /**
     * Sets the series order and sends a {@link PlotChangeEvent} to all
     * registered listeners.  By default, the plot renders the primary series
     * last (so that the primary series appears to be on top).
     * You can reverse this if you want to.
     *
     * @param order  the rendering order (<code>null</code> not permitted).
     * 
     * @see #getSeriesRenderingOrder()
     */
    public void setSeriesRenderingOrder(SeriesRenderingOrder order) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[232]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((order == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[95]++;
            throw new IllegalArgumentException("Null 'order' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[96]++;}
        this.seriesRenderingOrder = order;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[233]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[234]++;
    }

    /**
     * Returns the index of the specified renderer, or <code>-1</code> if the
     * renderer is not assigned to this plot.
     *
     * @param renderer  the renderer (<code>null</code> permitted).
     *
     * @return The renderer index.
     */
    public int getIndexOf(XYItemRenderer renderer) {
        return this.renderers.indexOf(renderer);
    }

    /**
     * Returns the renderer for the specified dataset.  The code first
     * determines the index of the dataset, then checks if there is a
     * renderer with the same index (if not, the method returns renderer(0).
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     *
     * @return The renderer (possibly <code>null</code>).
     */
    public XYItemRenderer getRendererForDataset(XYDataset dataset) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[235]++;
        XYItemRenderer result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[236]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[25]++;


int CodeCoverConditionCoverageHelper_C57;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((i < this.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[25]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[26]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[27]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[237]++;
int CodeCoverConditionCoverageHelper_C58;
            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((this.datasets.get(i) == dataset) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[97]++;
                result = (XYItemRenderer) this.renderers.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[238]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[239]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[99]++;
                    result = getRenderer();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[240]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[100]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[241]++;
                break;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[98]++;}
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[242]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[243]++;
    }

    /**
     * Returns <code>true</code> if the domain gridlines are visible, and
     * <code>false<code> otherwise.
     *
     * @return <code>true</code> or <code>false</code>.
     * 
     * @see #setDomainGridlinesVisible(boolean)
     */
    public boolean isDomainGridlinesVisible() {
        return this.domainGridlinesVisible;
    }

    /**
     * Sets the flag that controls whether or not the domain grid-lines are
     * visible.
     * <p>
     * If the flag value is changed, a {@link PlotChangeEvent} is sent to all
     * registered listeners.
     *
     * @param visible  the new value of the flag.
     * 
     * @see #isDomainGridlinesVisible()
     */
    public void setDomainGridlinesVisible(boolean visible) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[244]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((this.domainGridlinesVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[101]++;
            this.domainGridlinesVisible = visible;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[245]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[246]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[102]++;}
    }

    /**
     * Returns the stroke for the grid-lines (if any) plotted against the
     * domain axis.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setDomainGridlineStroke(Stroke)
     */
    public Stroke getDomainGridlineStroke() {
        return this.domainGridlineStroke;
    }

    /**
     * Sets the stroke for the grid lines plotted against the domain axis, and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     * <p>
     * If you set this to <code>null</code>, no grid lines will be drawn.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @throws IllegalArgumentException if <code>stroke</code> is 
     *     <code>null</code>.
     *
     * @see #getDomainGridlineStroke()
     */
    public void setDomainGridlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[247]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[103]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[104]++;}
        this.domainGridlineStroke = stroke;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[248]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[249]++;
    }

    /**
     * Returns the paint for the grid lines (if any) plotted against the domain
     * axis.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setDomainGridlinePaint(Paint)
     */
    public Paint getDomainGridlinePaint() {
        return this.domainGridlinePaint;
    }

    /**
     * Sets the paint for the grid lines plotted against the domain axis, and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @throws IllegalArgumentException if <code>paint</code> is 
     *     <code>null</code>.
     * 
     * @see #getDomainGridlinePaint()
     */
    public void setDomainGridlinePaint(Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[250]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[105]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[106]++;}
        this.domainGridlinePaint = paint;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[251]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[252]++;
    }

    /**
     * Returns <code>true</code> if the range axis grid is visible, and
     * <code>false<code> otherwise.
     *
     * @return A boolean.
     * 
     * @see #setRangeGridlinesVisible(boolean)
     */
    public boolean isRangeGridlinesVisible() {
        return this.rangeGridlinesVisible;
    }

    /**
     * Sets the flag that controls whether or not the range axis grid lines
     * are visible.
     * <p>
     * If the flag value is changed, a {@link PlotChangeEvent} is sent to all
     * registered listeners.
     *
     * @param visible  the new value of the flag.
     * 
     * @see #isRangeGridlinesVisible()
     */
    public void setRangeGridlinesVisible(boolean visible) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[253]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((this.rangeGridlinesVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[107]++;
            this.rangeGridlinesVisible = visible;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[254]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[255]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[108]++;}
    }

    /**
     * Returns the stroke for the grid lines (if any) plotted against the
     * range axis.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setRangeGridlineStroke(Stroke)
     */
    public Stroke getRangeGridlineStroke() {
        return this.rangeGridlineStroke;
    }

    /**
     * Sets the stroke for the grid lines plotted against the range axis,
     * and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getRangeGridlineStroke()
     */
    public void setRangeGridlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[256]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[109]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[110]++;}
        this.rangeGridlineStroke = stroke;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[257]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[258]++;
    }

    /**
     * Returns the paint for the grid lines (if any) plotted against the range
     * axis.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setRangeGridlinePaint(Paint)
     */
    public Paint getRangeGridlinePaint() {
        return this.rangeGridlinePaint;
    }

    /**
     * Sets the paint for the grid lines plotted against the range axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getRangeGridlinePaint()
     */
    public void setRangeGridlinePaint(Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[259]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[111]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[112]++;}
        this.rangeGridlinePaint = paint;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[260]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[261]++;
    }

    /**
     * Returns a flag that controls whether or not a zero baseline is
     * displayed for the domain axis.
     *
     * @return A boolean.
     * 
     * @since 1.0.5
     * 
     * @see #setDomainZeroBaselineVisible(boolean)
     */
    public boolean isDomainZeroBaselineVisible() {
        return this.domainZeroBaselineVisible;
    }

    /**
     * Sets the flag that controls whether or not the zero baseline is
     * displayed for the domain axis, and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param visible  the flag.
     * 
     * @since 1.0.5
     * 
     * @see #isDomainZeroBaselineVisible()
     */
    public void setDomainZeroBaselineVisible(boolean visible) {
        this.domainZeroBaselineVisible = visible;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[262]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[263]++;
    }

    /**
     * Returns the stroke used for the zero baseline against the domain axis.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @since 1.0.5
     * 
     * @see #setDomainZeroBaselineStroke(Stroke)
     */
    public Stroke getDomainZeroBaselineStroke() {
        return this.domainZeroBaselineStroke;
    }

    /**
     * Sets the stroke for the zero baseline for the domain axis,
     * and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @since 1.0.5
     * 
     * @see #getRangeZeroBaselineStroke()
     */
    public void setDomainZeroBaselineStroke(Stroke stroke) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[264]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[113]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[114]++;}
        this.domainZeroBaselineStroke = stroke;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[265]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[266]++;
    }

    /**
     * Returns the paint for the zero baseline (if any) plotted against the
     * domain axis.
     * 
     * @since 1.0.5
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setDomainZeroBaselinePaint(Paint)
     */
    public Paint getDomainZeroBaselinePaint() {
        return this.domainZeroBaselinePaint;
    }

    /**
     * Sets the paint for the zero baseline plotted against the domain axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @since 1.0.5
     * 
     * @see #getDomainZeroBaselinePaint()
     */
    public void setDomainZeroBaselinePaint(Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[267]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[115]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[116]++;}
        this.domainZeroBaselinePaint = paint;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[268]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[269]++;
    }
    
    /**
     * Returns a flag that controls whether or not a zero baseline is
     * displayed for the range axis.
     *
     * @return A boolean.
     * 
     * @see #setRangeZeroBaselineVisible(boolean)
     */
    public boolean isRangeZeroBaselineVisible() {
        return this.rangeZeroBaselineVisible;
    }

    /**
     * Sets the flag that controls whether or not the zero baseline is
     * displayed for the range axis, and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     *
     * @param visible  the flag.
     * 
     * @see #isRangeZeroBaselineVisible()
     */
    public void setRangeZeroBaselineVisible(boolean visible) {
        this.rangeZeroBaselineVisible = visible;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[270]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[271]++;
    }

    /**
     * Returns the stroke used for the zero baseline against the range axis.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setRangeZeroBaselineStroke(Stroke)
     */
    public Stroke getRangeZeroBaselineStroke() {
        return this.rangeZeroBaselineStroke;
    }

    /**
     * Sets the stroke for the zero baseline for the range axis,
     * and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getRangeZeroBaselineStroke()
     */
    public void setRangeZeroBaselineStroke(Stroke stroke) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[272]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[117]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[118]++;}
        this.rangeZeroBaselineStroke = stroke;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[273]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[274]++;
    }

    /**
     * Returns the paint for the zero baseline (if any) plotted against the
     * range axis.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setRangeZeroBaselinePaint(Paint)
     */
    public Paint getRangeZeroBaselinePaint() {
        return this.rangeZeroBaselinePaint;
    }

    /**
     * Sets the paint for the zero baseline plotted against the range axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getRangeZeroBaselinePaint()
     */
    public void setRangeZeroBaselinePaint(Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[275]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[119]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[120]++;}
        this.rangeZeroBaselinePaint = paint;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[276]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[277]++;
    }

    /**
     * Returns the paint used for the domain tick bands.  If this is
     * <code>null</code>, no tick bands will be drawn.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setDomainTickBandPaint(Paint)
     */
    public Paint getDomainTickBandPaint() {
        return this.domainTickBandPaint;
    }

    /**
     * Sets the paint for the domain tick bands.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getDomainTickBandPaint()
     */
    public void setDomainTickBandPaint(Paint paint) {
        this.domainTickBandPaint = paint;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[278]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[279]++;
    }

    /**
     * Returns the paint used for the range tick bands.  If this is
     * <code>null</code>, no tick bands will be drawn.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setRangeTickBandPaint(Paint)
     */
    public Paint getRangeTickBandPaint() {
        return this.rangeTickBandPaint;
    }

    /**
     * Sets the paint for the range tick bands.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getRangeTickBandPaint()
     */
    public void setRangeTickBandPaint(Paint paint) {
        this.rangeTickBandPaint = paint;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[280]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[281]++;
    }

    /**
     * Returns the origin for the quadrants that can be displayed on the plot.
     * This defaults to (0, 0).
     *
     * @return The origin point (never <code>null</code>).
     * 
     * @see #setQuadrantOrigin(Point2D)
     */
    public Point2D getQuadrantOrigin() {
        return this.quadrantOrigin;
    }

    /**
     * Sets the quadrant origin and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param origin  the origin (<code>null</code> not permitted).
     * 
     * @see #getQuadrantOrigin()
     */
    public void setQuadrantOrigin(Point2D origin) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[282]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((origin == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[121]++;
            throw new IllegalArgumentException("Null 'origin' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[122]++;}
        this.quadrantOrigin = origin;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[283]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[284]++;
    }

    /**
     * Returns the paint used for the specified quadrant.
     *
     * @param index  the quadrant index (0-3).
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setQuadrantPaint(int, Paint)
     */
    public Paint getQuadrantPaint(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[285]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((index > 3) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[123]++;
            throw new IllegalArgumentException("The index value (" + index 
                    + ") should be in the range 0 to 3.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[124]++;}
        return this.quadrantPaint[index];
    }

    /**
     * Sets the paint used for the specified quadrant and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param index  the quadrant index (0-3).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getQuadrantPaint(int)
     */
    public void setQuadrantPaint(int index, Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[286]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((index > 3) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[125]++;
            throw new IllegalArgumentException("The index value (" + index 
                    + ") should be in the range 0 to 3.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[126]++;}
        this.quadrantPaint[index] = paint;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[287]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[288]++;
    }

    /**
     * Adds a marker for the domain axis and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param marker  the marker (<code>null</code> not permitted).
     * 
     * @see #addDomainMarker(Marker, Layer)
     * @see #clearDomainMarkers()
     */
    public void addDomainMarker(Marker marker) {
        // defer argument checking...
        addDomainMarker(marker, Layer.FOREGROUND);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[289]++;
    }

    /**
     * Adds a marker for the domain axis in the specified layer and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param marker  the marker (<code>null</code> not permitted).
     * @param layer  the layer (foreground or background).
     * 
     * @see #addDomainMarker(int, Marker, Layer)
     */
    public void addDomainMarker(Marker marker, Layer layer) {
        addDomainMarker(0, marker, layer);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[290]++;
    }

    /**
     * Clears all the (foreground and background) domain markers and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @see #addDomainMarker(int, Marker, Layer)
     */
    public void clearDomainMarkers() {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[291]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((this.backgroundDomainMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[127]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[292]++;
            Set keys = this.backgroundDomainMarkers.keySet();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[293]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[294]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[28]++;


int CodeCoverConditionCoverageHelper_C74;
            while ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[28]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[29]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[30]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[295]++;
                Integer key = (Integer) iterator.next();
                clearDomainMarkers(key.intValue());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[296]++;
            }
            this.backgroundDomainMarkers.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[297]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[128]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[298]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((this.foregroundDomainMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[129]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[299]++;
            Set keys = this.foregroundDomainMarkers.keySet();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[300]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[301]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[31]++;


int CodeCoverConditionCoverageHelper_C76;
            while ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[31]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[32]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[33]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[302]++;
                Integer key = (Integer) iterator.next();
                clearDomainMarkers(key.intValue());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[303]++;
            }
            this.foregroundDomainMarkers.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[304]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[130]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[305]++;
    }

    /**
     * Clears the (foreground and background) domain markers for a particular
     * renderer.
     *
     * @param index  the renderer index.
     * 
     * @see #clearRangeMarkers(int)
     */
    public void clearDomainMarkers(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[306]++;
        Integer key = new Integer(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[307]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((this.backgroundDomainMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[131]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[308]++;
            Collection markers
                = (Collection) this.backgroundDomainMarkers.get(key);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[309]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[133]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[310]++;
                Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[311]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[34]++;


int CodeCoverConditionCoverageHelper_C79;
                while ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[34]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[35]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[36]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[312]++;
                    Marker m = (Marker) iterator.next();
                    m.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[313]++;
                }
                markers.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[314]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[134]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[132]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[315]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((this.foregroundRangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[135]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[316]++;
            Collection markers
                = (Collection) this.foregroundDomainMarkers.get(key);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[317]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[137]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[318]++;
                Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[319]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[37]++;


int CodeCoverConditionCoverageHelper_C82;
                while ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[37]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[38]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[39]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[320]++;
                    Marker m = (Marker) iterator.next();
                    m.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[321]++;
                }
                markers.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[322]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[138]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[136]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[323]++;
    }

    /**
     * Adds a marker for a specific dataset/renderer and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the domain axis (that the renderer is mapped to), however this is
     * entirely up to the renderer.
     *
     * @param index  the dataset/renderer index.
     * @param marker  the marker.
     * @param layer  the layer (foreground or background).
     * 
     * @see #clearDomainMarkers(int)
     * @see #addRangeMarker(int, Marker, Layer)
     */
    public void addDomainMarker(int index, Marker marker, Layer layer) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[324]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((marker == null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[139]++;
            throw new IllegalArgumentException("Null 'marker' not permitted.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[140]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[325]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((layer == null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[141]++;
            throw new IllegalArgumentException("Null 'layer' not permitted.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[142]++;}
        Collection markers;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[326]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[143]++;
            markers = (Collection) this.foregroundDomainMarkers.get(
                    new Integer(index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[327]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[328]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((markers == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[145]++;
                markers = new java.util.ArrayList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[329]++;
                this.foregroundDomainMarkers.put(new Integer(index), markers);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[330]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[146]++;}
            markers.add(marker);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[331]++;

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[144]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[332]++;
int CodeCoverConditionCoverageHelper_C87; if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((layer == Layer.BACKGROUND) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[147]++;
            markers = (Collection) this.backgroundDomainMarkers.get(
                    new Integer(index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[333]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[334]++;
int CodeCoverConditionCoverageHelper_C88;
            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((markers == null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[149]++;
                markers = new java.util.ArrayList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[335]++;
                this.backgroundDomainMarkers.put(new Integer(index), markers);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[336]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[150]++;}
            markers.add(marker);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[337]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[148]++;}
}
        marker.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[338]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[339]++;
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[340]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[151]++;
            markers = (ArrayList) this.foregroundDomainMarkers.get(new Integer(
                    index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[341]++;

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[152]++;
            markers = (ArrayList) this.backgroundDomainMarkers.get(new Integer(
                    index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[342]++;
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[343]++;
        boolean removed = markers.remove(marker);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[344]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[153]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[345]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[154]++;}
        return removed;
    }
    
    /**
     * Adds a marker for the range axis and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param marker  the marker (<code>null</code> not permitted).
     * 
     * @see #addRangeMarker(Marker, Layer)
     */
    public void addRangeMarker(Marker marker) {
        addRangeMarker(marker, Layer.FOREGROUND);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[346]++;
    }

    /**
     * Adds a marker for the range axis in the specified layer and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param marker  the marker (<code>null</code> not permitted).
     * @param layer  the layer (foreground or background).
     * 
     * @see #addRangeMarker(int, Marker, Layer)
     */
    public void addRangeMarker(Marker marker, Layer layer) {
        addRangeMarker(0, marker, layer);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[347]++;
    }

    /**
     * Clears all the range markers and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @see #clearRangeMarkers()
     */
    public void clearRangeMarkers() {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[348]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((this.backgroundRangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[155]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[349]++;
            Set keys = this.backgroundRangeMarkers.keySet();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[350]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[351]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[40]++;


int CodeCoverConditionCoverageHelper_C92;
            while ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[40]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[41]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[42]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[352]++;
                Integer key = (Integer) iterator.next();
                clearRangeMarkers(key.intValue());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[353]++;
            }
            this.backgroundRangeMarkers.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[354]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[156]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[355]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((this.foregroundRangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[157]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[356]++;
            Set keys = this.foregroundRangeMarkers.keySet();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[357]++;
            Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[358]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[43]++;


int CodeCoverConditionCoverageHelper_C94;
            while ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[43]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[44]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[45]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[359]++;
                Integer key = (Integer) iterator.next();
                clearRangeMarkers(key.intValue());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[360]++;
            }
            this.foregroundRangeMarkers.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[361]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[158]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[362]++;
    }

    /**
     * Adds a marker for a specific dataset/renderer and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * <P>
     * Typically a marker will be drawn by the renderer as a line perpendicular
     * to the range axis, however this is entirely up to the renderer.
     *
     * @param index  the dataset/renderer index.
     * @param marker  the marker.
     * @param layer  the layer (foreground or background).
     * 
     * @see #clearRangeMarkers(int)
     * @see #addDomainMarker(int, Marker, Layer)
     */
    public void addRangeMarker(int index, Marker marker, Layer layer) {
        Collection markers;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[363]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[159]++;
            markers = (Collection) this.foregroundRangeMarkers.get(
                    new Integer(index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[364]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[365]++;
int CodeCoverConditionCoverageHelper_C96;
            if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((markers == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[161]++;
                markers = new java.util.ArrayList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[366]++;
                this.foregroundRangeMarkers.put(new Integer(index), markers);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[367]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[162]++;}
            markers.add(marker);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[368]++;

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[160]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[369]++;
int CodeCoverConditionCoverageHelper_C97; if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((layer == Layer.BACKGROUND) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[163]++;
            markers = (Collection) this.backgroundRangeMarkers.get(
                    new Integer(index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[370]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[371]++;
int CodeCoverConditionCoverageHelper_C98;
            if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((markers == null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[165]++;
                markers = new java.util.ArrayList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[372]++;
                this.backgroundRangeMarkers.put(new Integer(index), markers);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[373]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[166]++;}
            markers.add(marker);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[374]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[164]++;}
}
        marker.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[375]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[376]++;
    }

    /**
     * Clears the (foreground and background) range markers for a particular
     * renderer.
     *
     * @param index  the renderer index.
     */
    public void clearRangeMarkers(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[377]++;
        Integer key = new Integer(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[378]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((this.backgroundRangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[167]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[379]++;
            Collection markers
                = (Collection) this.backgroundRangeMarkers.get(key);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[380]++;
int CodeCoverConditionCoverageHelper_C100;
            if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[169]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[381]++;
                Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[382]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[46]++;


int CodeCoverConditionCoverageHelper_C101;
                while ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[46]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[47]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[48]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[383]++;
                    Marker m = (Marker) iterator.next();
                    m.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[384]++;
                }
                markers.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[385]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[170]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[168]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[386]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((this.foregroundRangeMarkers != null) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[171]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[387]++;
            Collection markers
                = (Collection) this.foregroundRangeMarkers.get(key);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[388]++;
int CodeCoverConditionCoverageHelper_C103;
            if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[173]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[389]++;
                Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[390]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[49]++;


int CodeCoverConditionCoverageHelper_C104;
                while ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[49]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[50]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[51]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[391]++;
                    Marker m = (Marker) iterator.next();
                    m.removeChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[392]++;
                }
                markers.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[393]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[174]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[172]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[394]++;
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[395]++;
int CodeCoverConditionCoverageHelper_C105;
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((marker == null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[175]++;
            throw new IllegalArgumentException("Null 'marker' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[176]++;}
        ArrayList markers;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[396]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[177]++;
            markers = (ArrayList) this.foregroundRangeMarkers.get(new Integer(
                    index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[397]++;

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[178]++;
            markers = (ArrayList) this.backgroundRangeMarkers.get(new Integer(
                    index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[398]++;
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[399]++;

        boolean removed = markers.remove(marker);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[400]++;
int CodeCoverConditionCoverageHelper_C107;
        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[179]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[401]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[180]++;}
        return removed;
    }

    /**
     * Adds an annotation to the plot and sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     *
     * @param annotation  the annotation (<code>null</code> not permitted).
     * 
     * @see #getAnnotations()
     * @see #removeAnnotation(XYAnnotation)
     */
    public void addAnnotation(XYAnnotation annotation) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[402]++;
int CodeCoverConditionCoverageHelper_C108;
        if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((annotation == null) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[181]++;
            throw new IllegalArgumentException("Null 'annotation' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[182]++;}
        this.annotations.add(annotation);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[403]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[404]++;
    }

    /**
     * Removes an annotation from the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param annotation  the annotation (<code>null</code> not permitted).
     *
     * @return A boolean (indicates whether or not the annotation was removed).
     * 
     * @see #addAnnotation(XYAnnotation)
     * @see #getAnnotations()
     */
    public boolean removeAnnotation(XYAnnotation annotation) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[405]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((annotation == null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[183]++;
            throw new IllegalArgumentException("Null 'annotation' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[184]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[406]++;
        boolean removed = this.annotations.remove(annotation);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[407]++;
int CodeCoverConditionCoverageHelper_C110;
        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((removed) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[185]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[408]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[186]++;}
        return removed;
    }

    /**
     * Returns the list of annotations.
     *
     * @return The list of annotations.
     * 
     * @since 1.0.1
     * 
     * @see #addAnnotation(XYAnnotation)
     */
    public List getAnnotations() {
        return new ArrayList(this.annotations);
    }

    /**
     * Clears all the annotations and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @see #addAnnotation(XYAnnotation)
     */
    public void clearAnnotations() {
        this.annotations.clear();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[409]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[410]++;
    }
    
    /**
     * Calculates the space required for all the axes in the plot.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     *
     * @return The required space.
     */
    protected AxisSpace calculateAxisSpace(Graphics2D g2,
                                           Rectangle2D plotArea) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[411]++;
        AxisSpace space = new AxisSpace();
        space = calculateDomainAxisSpace(g2, plotArea, space);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[412]++;
        space = calculateRangeAxisSpace(g2, plotArea, space);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[413]++;
        return space;
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[414]++;
int CodeCoverConditionCoverageHelper_C111;

        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((space == null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[187]++;
            space = new AxisSpace();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[415]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[188]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[416]++;
int CodeCoverConditionCoverageHelper_C112;

        // reserve some space for the domain axis...
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((this.fixedDomainAxisSpace != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[189]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[417]++;
int CodeCoverConditionCoverageHelper_C113;
            if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[191]++;
                space.ensureAtLeast(this.fixedDomainAxisSpace.getLeft(), 
                        RectangleEdge.LEFT);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[418]++;
                space.ensureAtLeast(this.fixedDomainAxisSpace.getRight(), 
                        RectangleEdge.RIGHT);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[419]++;

            }
            else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[192]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[420]++;
int CodeCoverConditionCoverageHelper_C114; if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[193]++;
                space.ensureAtLeast(this.fixedDomainAxisSpace.getTop(), 
                        RectangleEdge.TOP);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[421]++;
                space.ensureAtLeast(this.fixedDomainAxisSpace.getBottom(), 
                        RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[422]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[194]++;}
}

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[190]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[423]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[52]++;


int CodeCoverConditionCoverageHelper_C115;
            // reserve space for the domain axes...
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[52]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[53]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[54]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[424]++;
                Axis axis = (Axis) this.domainAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[425]++;
int CodeCoverConditionCoverageHelper_C116;
                if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[195]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[426]++;
                    RectangleEdge edge = getDomainAxisEdge(i);
                    space = axis.reserveSpace(g2, this, plotArea, edge, space);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[427]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[196]++;}
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[428]++;
int CodeCoverConditionCoverageHelper_C117;

        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((space == null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[197]++;
            space = new AxisSpace();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[429]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[198]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[430]++;
int CodeCoverConditionCoverageHelper_C118;

        // reserve some space for the range axis...
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((this.fixedRangeAxisSpace != null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[199]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[431]++;
int CodeCoverConditionCoverageHelper_C119;
            if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[201]++;
                space.ensureAtLeast(this.fixedRangeAxisSpace.getTop(), 
                        RectangleEdge.TOP);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[432]++;
                space.ensureAtLeast(this.fixedRangeAxisSpace.getBottom(), 
                        RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[433]++;

            }
            else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[202]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[434]++;
int CodeCoverConditionCoverageHelper_C120; if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[203]++;
                space.ensureAtLeast(this.fixedRangeAxisSpace.getLeft(), 
                        RectangleEdge.LEFT);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[435]++;
                space.ensureAtLeast(this.fixedRangeAxisSpace.getRight(), 
                        RectangleEdge.RIGHT);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[436]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[204]++;}
}

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[200]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[437]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[55]++;


int CodeCoverConditionCoverageHelper_C121;
            // reserve space for the range axes...
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[55]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[56]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[57]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[438]++;
                Axis axis = (Axis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[439]++;
int CodeCoverConditionCoverageHelper_C122;
                if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[205]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[440]++;
                    RectangleEdge edge = getRangeAxisEdge(i);
                    space = axis.reserveSpace(g2, this, plotArea, edge, space);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[441]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[206]++;}
            }
        }
        return space;

    }

    /**
     * Draws the plot within the specified area on a graphics device.
     *
     * @param g2  the graphics device.
     * @param area  the plot area (in Java2D space).
     * @param anchor  an anchor point in Java2D space (<code>null</code>
     *                permitted).
     * @param parentState  the state from the parent plot, if there is one
     *                     (<code>null</code> permitted).
     * @param info  collects chart drawing information (<code>null</code>
     *              permitted).
     */
    public void draw(Graphics2D g2,
                     Rectangle2D area,
                     Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[442]++;

        // if the plot area is too small, just return...
        boolean b1 = (area.getWidth() <= MINIMUM_WIDTH_TO_DRAW);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[443]++;
        boolean b2 = (area.getHeight() <= MINIMUM_HEIGHT_TO_DRAW);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[444]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (8)) == 0 || true) &&
 ((b1) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((b2) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[207]++;
            return;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[208]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[445]++;
int CodeCoverConditionCoverageHelper_C124;

        // record the plot area...
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[209]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[446]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[210]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[447]++;

        // adjust the drawing area for the plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[448]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[449]++;

        AxisSpace space = calculateAxisSpace(g2, area);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[450]++;
        Rectangle2D dataArea = space.shrink(area, null);
        this.axisOffset.trim(dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[451]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[452]++;
int CodeCoverConditionCoverageHelper_C125;

        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[211]++;
            info.setDataArea(dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[453]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[212]++;}

        // draw the plot background and axes...
        drawBackground(g2, dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[454]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[455]++;
        Map axisStateMap = drawAxes(g2, area, dataArea, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[456]++;

        PlotOrientation orient = getOrientation();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[457]++;
int CodeCoverConditionCoverageHelper_C126;

        // the anchor point is typically the point where the mouse last
        // clicked - the crosshairs will be driven off this point...
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (8)) == 0 || true) &&
 ((anchor != null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((dataArea.contains(anchor)) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[213]++;
            anchor = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[458]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[214]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[459]++;
        CrosshairState crosshairState = new CrosshairState();
        crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[460]++;
        crosshairState.setAnchor(anchor);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[461]++;
        
        crosshairState.setAnchorX(Double.NaN);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[462]++;
        crosshairState.setAnchorY(Double.NaN);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[463]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[464]++;
int CodeCoverConditionCoverageHelper_C127;            
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((anchor != null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[215]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[465]++;
            ValueAxis domainAxis = getDomainAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[466]++;
int CodeCoverConditionCoverageHelper_C128;
            if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[217]++;
                double x;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[467]++;
int CodeCoverConditionCoverageHelper_C129;
                if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((orient == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[219]++;
                    x = domainAxis.java2DToValue(anchor.getX(), dataArea, 
                            getDomainAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[468]++;

                } 
                else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[220]++;
                    x = domainAxis.java2DToValue(anchor.getY(), dataArea, 
                            getDomainAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[469]++;
                }
                crosshairState.setAnchorX(x);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[470]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[218]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[471]++;
            ValueAxis rangeAxis = getRangeAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[472]++;
int CodeCoverConditionCoverageHelper_C130;
            if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[221]++;
                double y;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[473]++;
int CodeCoverConditionCoverageHelper_C131;
                if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((orient == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[223]++;
                    y = rangeAxis.java2DToValue(anchor.getY(), dataArea, 
                            getRangeAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[474]++;

                } 
                else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[224]++;
                    y = rangeAxis.java2DToValue(anchor.getX(), dataArea, 
                            getRangeAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[475]++;
                }
                crosshairState.setAnchorY(y);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[476]++;
                
            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[222]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[216]++;}
        crosshairState.setCrosshairX(getDomainCrosshairValue());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[477]++;
        crosshairState.setCrosshairY(getRangeCrosshairValue());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[478]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[479]++;
        Shape originalClip = g2.getClip();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[480]++;
        Composite originalComposite = g2.getComposite();

        g2.clip(dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[481]++;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                getForegroundAlpha()));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[482]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[483]++;

        AxisState domainAxisState = (AxisState) axisStateMap.get(
                getDomainAxis());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[484]++;
int CodeCoverConditionCoverageHelper_C132;
        if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((domainAxisState == null) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[225]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[485]++;
int CodeCoverConditionCoverageHelper_C133;
            if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((parentState != null) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[227]++;
                domainAxisState = (AxisState) parentState.getSharedAxisStates()
                        .get(getDomainAxis());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[486]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[228]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[226]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[487]++;

        AxisState rangeAxisState = (AxisState) axisStateMap.get(getRangeAxis());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[488]++;
int CodeCoverConditionCoverageHelper_C134;
        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((rangeAxisState == null) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[229]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[489]++;
int CodeCoverConditionCoverageHelper_C135;
            if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((parentState != null) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[231]++;
                rangeAxisState = (AxisState) parentState.getSharedAxisStates()
                        .get(getRangeAxis());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[490]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[232]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[230]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[491]++;
int CodeCoverConditionCoverageHelper_C136;
        if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((domainAxisState != null) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[233]++;
            drawDomainTickBands(g2, dataArea, domainAxisState.getTicks());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[492]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[234]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[493]++;
int CodeCoverConditionCoverageHelper_C137;
        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((rangeAxisState != null) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[235]++;
            drawRangeTickBands(g2, dataArea, rangeAxisState.getTicks());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[494]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[236]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[495]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((domainAxisState != null) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[237]++;
            drawDomainGridlines(g2, dataArea, domainAxisState.getTicks());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[496]++;
            drawZeroDomainBaseline(g2, dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[497]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[238]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[498]++;
int CodeCoverConditionCoverageHelper_C139;
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((rangeAxisState != null) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[239]++;
            drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[499]++;
            drawZeroRangeBaseline(g2, dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[500]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[240]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[501]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[58]++;


int CodeCoverConditionCoverageHelper_C140;

        // draw the markers that are associated with a specific renderer...
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((i < this.renderers.size()) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[58]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[59]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[60]++;
}
            drawDomainMarkers(g2, dataArea, i, Layer.BACKGROUND);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[502]++;
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[503]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[61]++;


int CodeCoverConditionCoverageHelper_C141;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((i < this.renderers.size()) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[61]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[62]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[63]++;
}
            drawRangeMarkers(g2, dataArea, i, Layer.BACKGROUND);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[504]++;
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[505]++;

        // now draw annotations and render data items...
        boolean foundData = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[506]++;
        DatasetRenderingOrder order = getDatasetRenderingOrder();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[507]++;
int CodeCoverConditionCoverageHelper_C142;
        if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((order == DatasetRenderingOrder.FORWARD) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[241]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[508]++;

            // draw background annotations
            int rendererCount = this.renderers.size();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[509]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[64]++;


int CodeCoverConditionCoverageHelper_C143;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((i < rendererCount) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[64]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[65]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[66]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[510]++;
                XYItemRenderer r = getRenderer(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[511]++;
int CodeCoverConditionCoverageHelper_C144;
                if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[243]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[512]++;
                    ValueAxis domainAxis = getDomainAxisForDataset(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[513]++;
                    ValueAxis rangeAxis = getRangeAxisForDataset(i);
                    r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis,
                            Layer.BACKGROUND, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[514]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[244]++;}
            }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[515]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[67]++;


int CodeCoverConditionCoverageHelper_C145;

            // render data items...
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((i < getDatasetCount()) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[67]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[68]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[69]++;
}
                foundData = render(g2, dataArea, i, info, crosshairState)
                    || foundData;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[516]++;
            }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[517]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[70]++;


int CodeCoverConditionCoverageHelper_C146;

            // draw foreground annotations
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((i < rendererCount) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[70]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[71]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[72]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[518]++;
                XYItemRenderer r = getRenderer(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[519]++;
int CodeCoverConditionCoverageHelper_C147;
                if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[245]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[520]++;
                    ValueAxis domainAxis = getDomainAxisForDataset(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[521]++;
                    ValueAxis rangeAxis = getRangeAxisForDataset(i);
                    r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis,
                            Layer.FOREGROUND, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[522]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[246]++;}
            }


        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[242]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[523]++;
int CodeCoverConditionCoverageHelper_C148; if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((order == DatasetRenderingOrder.REVERSE) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[247]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[524]++;

            // draw background annotations
            int rendererCount = this.renderers.size();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[525]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[73]++;


int CodeCoverConditionCoverageHelper_C149;
            for (int i = rendererCount - 1;(((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[73]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[74]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[75]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[526]++;
                XYItemRenderer r = getRenderer(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[527]++;
int CodeCoverConditionCoverageHelper_C150;
                if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((i >= getDatasetCount()) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[249]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[528]++; // we need the dataset to make
                    continue;
                 // a link to the axes
                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[250]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[529]++;
int CodeCoverConditionCoverageHelper_C151;
                if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[251]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[530]++;
                    ValueAxis domainAxis = getDomainAxisForDataset(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[531]++;
                    ValueAxis rangeAxis = getRangeAxisForDataset(i);
                    r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis,
                            Layer.BACKGROUND, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[532]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[252]++;}
            }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[533]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[76]++;


int CodeCoverConditionCoverageHelper_C152;

            for (int i = getDatasetCount() - 1;(((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[76]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[77]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[78]++;
}
                foundData = render(g2, dataArea, i, info, crosshairState)
                    || foundData;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[534]++;
            }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[535]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[79]++;


int CodeCoverConditionCoverageHelper_C153;

            // draw foreground annotations
            for (int i = rendererCount - 1;(((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[79]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[80]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[81]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[536]++;
                XYItemRenderer r = getRenderer(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[537]++;
int CodeCoverConditionCoverageHelper_C154;
                if ((((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((i >= getDatasetCount()) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[253]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[538]++; // we need the dataset to make
                    continue;
                 // a link to the axes
                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[254]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[539]++;
int CodeCoverConditionCoverageHelper_C155;
                if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[255]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[540]++;
                    ValueAxis domainAxis = getDomainAxisForDataset(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[541]++;
                    ValueAxis rangeAxis = getRangeAxisForDataset(i);
                    r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis,
                            Layer.FOREGROUND, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[542]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[256]++;}
            }


        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[248]++;}
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[543]++;

        // draw domain crosshair if required...
        int xAxisIndex = crosshairState.getDomainAxisIndex();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[544]++;
        ValueAxis xAxis = getDomainAxis(xAxisIndex);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[545]++;
        RectangleEdge xAxisEdge = getDomainAxisEdge(xAxisIndex);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[546]++;
int CodeCoverConditionCoverageHelper_C156;
        if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C156 |= (8)) == 0 || true) &&
 ((this.domainCrosshairLockedOnData) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((anchor != null) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[257]++;
            double xx;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[547]++;
int CodeCoverConditionCoverageHelper_C157;
            if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((orient == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[259]++;
                xx = xAxis.java2DToValue(anchor.getX(), dataArea, xAxisEdge);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[548]++;

            } 
            else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[260]++;
                xx = xAxis.java2DToValue(anchor.getY(), dataArea, xAxisEdge);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[549]++;
            }
            crosshairState.setCrosshairX(xx);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[550]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[258]++;}
        setDomainCrosshairValue(crosshairState.getCrosshairX(), false);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[551]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[552]++;
int CodeCoverConditionCoverageHelper_C158;
        if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((isDomainCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[261]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[553]++;
            double x = getDomainCrosshairValue();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[554]++;
            Paint paint = getDomainCrosshairPaint();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[555]++;
            Stroke stroke = getDomainCrosshairStroke();
            drawDomainCrosshair(g2, dataArea, orient, x, xAxis, stroke, paint);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[556]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[262]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[557]++;

        // draw range crosshair if required...
        int yAxisIndex = crosshairState.getRangeAxisIndex();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[558]++;
        ValueAxis yAxis = getRangeAxis(yAxisIndex);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[559]++;
        RectangleEdge yAxisEdge = getRangeAxisEdge(yAxisIndex);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[560]++;
int CodeCoverConditionCoverageHelper_C159;
        if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C159 |= (8)) == 0 || true) &&
 ((this.rangeCrosshairLockedOnData) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((anchor != null) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[263]++;
            double yy;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[561]++;
int CodeCoverConditionCoverageHelper_C160;
            if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((orient == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[265]++;
                yy = yAxis.java2DToValue(anchor.getY(), dataArea, yAxisEdge);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[562]++;

            } else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[266]++;
                yy = yAxis.java2DToValue(anchor.getX(), dataArea, yAxisEdge);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[563]++;
            }
            crosshairState.setCrosshairY(yy);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[564]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[264]++;}
        setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[565]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[566]++;
int CodeCoverConditionCoverageHelper_C161;
        if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((isRangeCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[267]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[567]++;
            double y = getRangeCrosshairValue();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[568]++;
            Paint paint = getRangeCrosshairPaint();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[569]++;
            Stroke stroke = getRangeCrosshairStroke();
            drawRangeCrosshair(g2, dataArea, orient, y, yAxis, stroke, paint);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[570]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[268]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[571]++;
int CodeCoverConditionCoverageHelper_C162;

        if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((foundData) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[269]++;
            drawNoDataMessage(g2, dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[572]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[270]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[573]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[82]++;


int CodeCoverConditionCoverageHelper_C163;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((i < this.renderers.size()) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[82]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[83]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[84]++;
}
            drawDomainMarkers(g2, dataArea, i, Layer.FOREGROUND);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[574]++;
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[575]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[85]++;


int CodeCoverConditionCoverageHelper_C164;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((i < this.renderers.size()) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[85]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[86]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[87]++;
}
            drawRangeMarkers(g2, dataArea, i, Layer.FOREGROUND);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[576]++;
        }

        drawAnnotations(g2, dataArea, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[577]++;
        g2.setClip(originalClip);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[578]++;
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[579]++;

        drawOutline(g2, dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[580]++;

    }

    /**
     * Draws the background for the plot.
     *
     * @param g2  the graphics device.
     * @param area  the area.
     */
    public void drawBackground(Graphics2D g2, Rectangle2D area) {
        fillBackground(g2, area, this.orientation);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[581]++;
        drawQuadrants(g2, area);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[582]++;
        drawBackgroundImage(g2, area);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[583]++;
    }

    /**
     * Draws the quadrants.
     *
     * @param g2  the graphics device.
     * @param area  the area.
     * 
     * @see #setQuadrantOrigin(Point2D)
     * @see #setQuadrantPaint(int, Paint)
     */
    protected void drawQuadrants(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[584]++;
        //  0 | 1
        //  --+--
        //  2 | 3
        boolean somethingToDraw = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[585]++;

        ValueAxis xAxis = getDomainAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[586]++;
        double x = this.quadrantOrigin.getX();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[587]++;
        double xx = xAxis.valueToJava2D(x, area, getDomainAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[588]++;

        ValueAxis yAxis = getRangeAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[589]++;
        double y = this.quadrantOrigin.getY();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[590]++;
        double yy = yAxis.valueToJava2D(y, area, getRangeAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[591]++;

        double xmin = xAxis.getLowerBound();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[592]++;
        double xxmin = xAxis.valueToJava2D(xmin, area, getDomainAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[593]++;

        double xmax = xAxis.getUpperBound();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[594]++;
        double xxmax = xAxis.valueToJava2D(xmax, area, getDomainAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[595]++;

        double ymin = yAxis.getLowerBound();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[596]++;
        double yymin = yAxis.valueToJava2D(ymin, area, getRangeAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[597]++;

        double ymax = yAxis.getUpperBound();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[598]++;
        double yymax = yAxis.valueToJava2D(ymax, area, getRangeAxisEdge());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[599]++;

        Rectangle2D[] r = new Rectangle2D[] {null, null, null, null};
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[600]++;
int CodeCoverConditionCoverageHelper_C165;
        if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((this.quadrantPaint[0] != null) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[271]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[601]++;
int CodeCoverConditionCoverageHelper_C166;
            if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (8)) == 0 || true) &&
 ((x > xmin) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((y < ymax) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[273]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[602]++;
int CodeCoverConditionCoverageHelper_C167;
                if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[275]++;
                    r[0] = new Rectangle2D.Double(Math.min(yymax, yy), 
                            Math.min(xxmin, xx), Math.abs(yy - yymax), 
                            Math.abs(xx - xxmin)
                    );
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[603]++;

                }
                else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[276]++;  // PlotOrientation.VERTICAL
                    r[0] = new Rectangle2D.Double(Math.min(xxmin, xx), 
                            Math.min(yymax, yy), Math.abs(xx - xxmin), 
                            Math.abs(yy - yymax));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[604]++;
                }
                somethingToDraw = true;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[605]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[274]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[272]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[606]++;
int CodeCoverConditionCoverageHelper_C168;
        if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((this.quadrantPaint[1] != null) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[277]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[607]++;
int CodeCoverConditionCoverageHelper_C169;
            if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (8)) == 0 || true) &&
 ((x < xmax) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((y < ymax) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[279]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[608]++;
int CodeCoverConditionCoverageHelper_C170;
                if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[281]++;
                    r[1] = new Rectangle2D.Double(Math.min(yymax, yy), 
                            Math.min(xxmax, xx), Math.abs(yy - yymax), 
                            Math.abs(xx - xxmax));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[609]++;

                }
                else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[282]++;  // PlotOrientation.VERTICAL
                    r[1] = new Rectangle2D.Double(Math.min(xx, xxmax), 
                            Math.min(yymax, yy), Math.abs(xx - xxmax), 
                            Math.abs(yy - yymax));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[610]++;
                }
                somethingToDraw = true;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[611]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[280]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[278]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[612]++;
int CodeCoverConditionCoverageHelper_C171;
        if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((this.quadrantPaint[2] != null) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[283]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[613]++;
int CodeCoverConditionCoverageHelper_C172;
            if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (8)) == 0 || true) &&
 ((x > xmin) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((y > ymin) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[285]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[614]++;
int CodeCoverConditionCoverageHelper_C173;
                if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[287]++;
                    r[2] = new Rectangle2D.Double(Math.min(yymin, yy), 
                            Math.min(xxmin, xx), Math.abs(yy - yymin), 
                            Math.abs(xx - xxmin));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[615]++;

                }
                else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[288]++;  // PlotOrientation.VERTICAL
                    r[2] = new Rectangle2D.Double(Math.min(xxmin, xx), 
                            Math.min(yymin, yy), Math.abs(xx - xxmin), 
                            Math.abs(yy - yymin));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[616]++;
                }
                somethingToDraw = true;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[617]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[286]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[284]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[618]++;
int CodeCoverConditionCoverageHelper_C174;
        if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((this.quadrantPaint[3] != null) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[289]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[619]++;
int CodeCoverConditionCoverageHelper_C175;
            if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (8)) == 0 || true) &&
 ((x < xmax) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((y > ymin) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[291]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[620]++;
int CodeCoverConditionCoverageHelper_C176;
                if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[293]++;
                    r[3] = new Rectangle2D.Double(Math.min(yymin, yy), 
                            Math.min(xxmax, xx), Math.abs(yy - yymin), 
                            Math.abs(xx - xxmax));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[621]++;

                }
                else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[294]++;  // PlotOrientation.VERTICAL
                    r[3] = new Rectangle2D.Double(Math.min(xx, xxmax), 
                            Math.min(yymin, yy), Math.abs(xx - xxmax), 
                            Math.abs(yy - yymin));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[622]++;
                }
                somethingToDraw = true;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[623]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[292]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[290]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[624]++;
int CodeCoverConditionCoverageHelper_C177;
        if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((somethingToDraw) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[295]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[625]++;
            Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    getBackgroundAlpha()));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[626]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[627]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[88]++;


int CodeCoverConditionCoverageHelper_C178;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((i < 4) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[88]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[89]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[90]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[628]++;
int CodeCoverConditionCoverageHelper_C179;
                if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (8)) == 0 || true) &&
 ((this.quadrantPaint[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((r[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[297]++;
                    g2.setPaint(this.quadrantPaint[i]);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[629]++;
                    g2.fill(r[i]);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[630]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[298]++;}
            }
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[631]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[296]++;}
    }

    /**
     * Draws the domain tick bands, if any.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param ticks  the ticks.
     * 
     * @see #setDomainTickBandPaint(Paint)
     */
    public void drawDomainTickBands(Graphics2D g2, Rectangle2D dataArea,
                                    List ticks) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[632]++;
        Paint bandPaint = getDomainTickBandPaint();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[633]++;
int CodeCoverConditionCoverageHelper_C180;
        if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((bandPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[299]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[634]++;
            boolean fillBand = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[635]++;
            ValueAxis xAxis = getDomainAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[636]++;
            double previous = xAxis.getLowerBound();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[637]++;
            Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[638]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[91]++;


int CodeCoverConditionCoverageHelper_C181;
            while ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[91]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[92]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[93]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[639]++;
                ValueTick tick = (ValueTick) iterator.next();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[640]++;
                double current = tick.getValue();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[641]++;
int CodeCoverConditionCoverageHelper_C182;
                if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 ((fillBand) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[301]++;
                    getRenderer().fillDomainGridBand(g2, this, xAxis, dataArea,
                            previous, current);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[642]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[302]++;}
                previous = current;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[643]++;
                fillBand = !fillBand;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[644]++;
            }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[645]++;
            double end = xAxis.getUpperBound();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[646]++;
int CodeCoverConditionCoverageHelper_C183;
            if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 ((fillBand) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[303]++;
                getRenderer().fillDomainGridBand(g2, this, xAxis, dataArea, 
                        previous, end);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[647]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[304]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[300]++;}
    }

    /**
     * Draws the range tick bands, if any.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param ticks  the ticks.
     * 
     * @see #setRangeTickBandPaint(Paint)
     */
    public void drawRangeTickBands(Graphics2D g2, Rectangle2D dataArea,
                                   List ticks) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[648]++;
        Paint bandPaint = getRangeTickBandPaint();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[649]++;
int CodeCoverConditionCoverageHelper_C184;
        if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((bandPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[305]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[650]++;
            boolean fillBand = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[651]++;
            ValueAxis axis = getRangeAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[652]++;
            double previous = axis.getLowerBound();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[653]++;
            Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[654]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[94]++;


int CodeCoverConditionCoverageHelper_C185;
            while ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[94]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[95]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[96]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[655]++;
                ValueTick tick = (ValueTick) iterator.next();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[656]++;
                double current = tick.getValue();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[657]++;
int CodeCoverConditionCoverageHelper_C186;
                if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((fillBand) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[307]++;
                    getRenderer().fillRangeGridBand(g2, this, axis, dataArea, 
                            previous, current);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[658]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[308]++;}
                previous = current;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[659]++;
                fillBand = !fillBand;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[660]++;
            }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[661]++;
            double end = axis.getUpperBound();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[662]++;
int CodeCoverConditionCoverageHelper_C187;
            if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 ((fillBand) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[309]++;
                getRenderer().fillRangeGridBand(g2, this, axis, dataArea, 
                        previous, end);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[663]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[310]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[306]++;}
    }

    /**
     * A utility method for drawing the axes.
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param plotArea  the plot area (<code>null</code> not permitted).
     * @param dataArea  the data area (<code>null</code> not permitted).
     * @param plotState  collects information about the plot (<code>null</code>
     *                   permitted).
     *
     * @return A map containing the state for each axis drawn.
     */
    protected Map drawAxes(Graphics2D g2,
                           Rectangle2D plotArea,
                           Rectangle2D dataArea,
                           PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[664]++;

        AxisCollection axisCollection = new AxisCollection();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[665]++;
byte CodeCoverTryBranchHelper_L33 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[97]++;


int CodeCoverConditionCoverageHelper_C188;

        // add domain axes to lists...
        for (int index = 0;(((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((index < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L33 == 0) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[97]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[98]++;
} else if (CodeCoverTryBranchHelper_L33 == 1) {
  CodeCoverTryBranchHelper_L33++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[98]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[99]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[666]++;
            ValueAxis axis = (ValueAxis) this.domainAxes.get(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[667]++;
int CodeCoverConditionCoverageHelper_C189;
            if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[311]++;
                axisCollection.add(axis, getDomainAxisEdge(index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[668]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[312]++;}
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[669]++;
byte CodeCoverTryBranchHelper_L34 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[100]++;


int CodeCoverConditionCoverageHelper_C190;

        // add range axes to lists...
        for (int index = 0;(((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((index < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L34 == 0) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[100]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[101]++;
} else if (CodeCoverTryBranchHelper_L34 == 1) {
  CodeCoverTryBranchHelper_L34++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[101]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[102]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[670]++;
            ValueAxis yAxis = (ValueAxis) this.rangeAxes.get(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[671]++;
int CodeCoverConditionCoverageHelper_C191;
            if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((yAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[313]++;
                axisCollection.add(yAxis, getRangeAxisEdge(index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[672]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[314]++;}
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[673]++;

        Map axisStateMap = new HashMap();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[674]++;

        // draw the top axes
        double cursor = dataArea.getMinY() - this.axisOffset.calculateTopOutset(
                dataArea.getHeight());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[675]++;
        Iterator iterator = axisCollection.getAxesAtTop().iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[676]++;
byte CodeCoverTryBranchHelper_L35 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[103]++;


int CodeCoverConditionCoverageHelper_C192;
        while ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
if (CodeCoverTryBranchHelper_L35 == 0) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[103]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[104]++;
} else if (CodeCoverTryBranchHelper_L35 == 1) {
  CodeCoverTryBranchHelper_L35++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[104]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[105]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[677]++;
            ValueAxis axis = (ValueAxis) iterator.next();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[678]++;
            AxisState info = axis.draw(g2, cursor, plotArea, dataArea, 
                    RectangleEdge.TOP, plotState);
            cursor = info.getCursor();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[679]++;
            axisStateMap.put(axis, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[680]++;
        }

        // draw the bottom axes
        cursor = dataArea.getMaxY()
                 + this.axisOffset.calculateBottomOutset(dataArea.getHeight());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[681]++;
        iterator = axisCollection.getAxesAtBottom().iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[682]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[683]++;
byte CodeCoverTryBranchHelper_L36 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[106]++;


int CodeCoverConditionCoverageHelper_C193;
        while ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
if (CodeCoverTryBranchHelper_L36 == 0) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[106]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[107]++;
} else if (CodeCoverTryBranchHelper_L36 == 1) {
  CodeCoverTryBranchHelper_L36++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[107]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[108]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[684]++;
            ValueAxis axis = (ValueAxis) iterator.next();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[685]++;
            AxisState info = axis.draw(g2, cursor, plotArea, dataArea, 
                    RectangleEdge.BOTTOM, plotState);
            cursor = info.getCursor();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[686]++;
            axisStateMap.put(axis, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[687]++;
        }

        // draw the left axes
        cursor = dataArea.getMinX()
                 - this.axisOffset.calculateLeftOutset(dataArea.getWidth());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[688]++;
        iterator = axisCollection.getAxesAtLeft().iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[689]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[690]++;
byte CodeCoverTryBranchHelper_L37 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[109]++;


int CodeCoverConditionCoverageHelper_C194;
        while ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
if (CodeCoverTryBranchHelper_L37 == 0) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[109]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[110]++;
} else if (CodeCoverTryBranchHelper_L37 == 1) {
  CodeCoverTryBranchHelper_L37++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[110]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[111]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[691]++;
            ValueAxis axis = (ValueAxis) iterator.next();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[692]++;
            AxisState info = axis.draw(g2, cursor, plotArea, dataArea, 
                    RectangleEdge.LEFT, plotState);
            cursor = info.getCursor();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[693]++;
            axisStateMap.put(axis, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[694]++;
        }

        // draw the right axes
        cursor = dataArea.getMaxX()
                 + this.axisOffset.calculateRightOutset(dataArea.getWidth());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[695]++;
        iterator = axisCollection.getAxesAtRight().iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[696]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[697]++;
byte CodeCoverTryBranchHelper_L38 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[112]++;


int CodeCoverConditionCoverageHelper_C195;
        while ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
if (CodeCoverTryBranchHelper_L38 == 0) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[112]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[113]++;
} else if (CodeCoverTryBranchHelper_L38 == 1) {
  CodeCoverTryBranchHelper_L38++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[113]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[114]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[698]++;
            ValueAxis axis = (ValueAxis) iterator.next();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[699]++;
            AxisState info = axis.draw(g2, cursor, plotArea, dataArea, 
                    RectangleEdge.RIGHT, plotState);
            cursor = info.getCursor();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[700]++;
            axisStateMap.put(axis, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[701]++;
        }

        return axisStateMap;
    }

    /**
     * Draws a representation of the data within the dataArea region, using the
     * current renderer.
     * <P>
     * The <code>info</code> and <code>crosshairState</code> arguments may be
     * <code>null</code>.
     *
     * @param g2  the graphics device.
     * @param dataArea  the region in which the data is to be drawn.
     * @param index  the dataset index.
     * @param info  an optional object for collection dimension information.
     * @param crosshairState  collects crosshair information
     *                        (<code>null</code> permitted).
     *
     * @return A flag that indicates whether any data was actually rendered.
     */
    public boolean render(Graphics2D g2,
                          Rectangle2D dataArea,
                          int index,
                          PlotRenderingInfo info,
                          CrosshairState crosshairState) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[702]++;

        boolean foundData = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[703]++;
        XYDataset dataset = getDataset(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[704]++;
int CodeCoverConditionCoverageHelper_C196;
        if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((DatasetUtilities.isEmptyOrNull(dataset)) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[315]++;
            foundData = true;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[705]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[706]++;
            ValueAxis xAxis = getDomainAxisForDataset(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[707]++;
            ValueAxis yAxis = getRangeAxisForDataset(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[708]++;
            XYItemRenderer renderer = getRenderer(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[709]++;
int CodeCoverConditionCoverageHelper_C197;
            if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((renderer == null) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[317]++;
                renderer = getRenderer();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[710]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[711]++;
int CodeCoverConditionCoverageHelper_C198;
                if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((renderer == null) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[319]++; // no default renderer available
                    return foundData;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[320]++;}

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[318]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[712]++;

            XYItemRendererState state = renderer.initialise(g2, dataArea, this,
                    dataset, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[713]++;
            int passCount = renderer.getPassCount();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[714]++;

            SeriesRenderingOrder seriesOrder = getSeriesRenderingOrder();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[715]++;
int CodeCoverConditionCoverageHelper_C199;
            if ((((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((seriesOrder == SeriesRenderingOrder.REVERSE) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[321]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[716]++;
byte CodeCoverTryBranchHelper_L39 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[115]++;


int CodeCoverConditionCoverageHelper_C200;
                //render series in reverse order
                for (int pass = 0;(((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((pass < passCount) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false); pass++) {
if (CodeCoverTryBranchHelper_L39 == 0) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[115]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[116]++;
} else if (CodeCoverTryBranchHelper_L39 == 1) {
  CodeCoverTryBranchHelper_L39++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[116]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[117]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[717]++;
                    int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[718]++;
byte CodeCoverTryBranchHelper_L40 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[118]++;


int CodeCoverConditionCoverageHelper_C201;
                    for (int series = seriesCount - 1;(((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((series >= 0) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false); series--) {
if (CodeCoverTryBranchHelper_L40 == 0) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[118]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[119]++;
} else if (CodeCoverTryBranchHelper_L40 == 1) {
  CodeCoverTryBranchHelper_L40++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[119]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[120]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[719]++;
                        int firstItem = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[720]++;
                        int lastItem = dataset.getItemCount(series) - 1;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[721]++;
int CodeCoverConditionCoverageHelper_C202;
                        if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((lastItem == -1) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[323]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[722]++;
                            continue;

                        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[324]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[723]++;
int CodeCoverConditionCoverageHelper_C203;
                        if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((state.getProcessVisibleItemsOnly()) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[325]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[724]++;
                            int[] itemBounds = RendererUtilities.findLiveItems(
                                    dataset, series, xAxis.getLowerBound(), 
                                    xAxis.getUpperBound());
                            firstItem = itemBounds[0];
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[725]++;
                            lastItem = itemBounds[1];
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[726]++;

                        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[326]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[727]++;
byte CodeCoverTryBranchHelper_L41 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[121]++;


int CodeCoverConditionCoverageHelper_C204;
                        for (int item = firstItem;(((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((item <= lastItem) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L41 == 0) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[121]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[122]++;
} else if (CodeCoverTryBranchHelper_L41 == 1) {
  CodeCoverTryBranchHelper_L41++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[122]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[123]++;
}
                            renderer.drawItem(g2, state, dataArea, info,
                                    this, xAxis, yAxis, dataset, series, item,
                                    crosshairState, pass);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[728]++;
                        }
                    }
                }

            }
            else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[322]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[729]++;
byte CodeCoverTryBranchHelper_L42 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[124]++;


int CodeCoverConditionCoverageHelper_C205;
                //render series in forward order
                for (int pass = 0;(((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((pass < passCount) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false); pass++) {
if (CodeCoverTryBranchHelper_L42 == 0) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[124]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[125]++;
} else if (CodeCoverTryBranchHelper_L42 == 1) {
  CodeCoverTryBranchHelper_L42++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[125]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[126]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[730]++;
                    int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[731]++;
byte CodeCoverTryBranchHelper_L43 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[127]++;


int CodeCoverConditionCoverageHelper_C206;
                    for (int series = 0;(((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((series < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false); series++) {
if (CodeCoverTryBranchHelper_L43 == 0) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[127]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[128]++;
} else if (CodeCoverTryBranchHelper_L43 == 1) {
  CodeCoverTryBranchHelper_L43++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[128]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[129]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[732]++;
                        int firstItem = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[733]++;
                        int lastItem = dataset.getItemCount(series) - 1;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[734]++;
int CodeCoverConditionCoverageHelper_C207;
                        if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((state.getProcessVisibleItemsOnly()) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[327]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[735]++;
                            int[] itemBounds = RendererUtilities.findLiveItems(
                                    dataset, series, xAxis.getLowerBound(), 
                                    xAxis.getUpperBound());
                            firstItem = itemBounds[0];
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[736]++;
                            lastItem = itemBounds[1];
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[737]++;

                        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[328]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[738]++;
byte CodeCoverTryBranchHelper_L44 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[130]++;


int CodeCoverConditionCoverageHelper_C208;
                        for (int item = firstItem;(((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((item <= lastItem) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false); item++) {
if (CodeCoverTryBranchHelper_L44 == 0) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[130]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[131]++;
} else if (CodeCoverTryBranchHelper_L44 == 1) {
  CodeCoverTryBranchHelper_L44++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[131]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[132]++;
}
                            renderer.drawItem(g2, state, dataArea, info,
                                    this, xAxis, yAxis, dataset, series, item,
                                    crosshairState, pass);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[739]++;
                        }
                    }
                }
            }

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[316]++;}
        return foundData;
    }

    /**
     * Returns the domain axis for a dataset.
     *
     * @param index  the dataset index.
     *
     * @return The axis.
     */
    public ValueAxis getDomainAxisForDataset(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[740]++;
int CodeCoverConditionCoverageHelper_C209;

        if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((index >= getDatasetCount()) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[329]++;
            throw new IllegalArgumentException("Index " + index 
                    + " out of bounds.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[330]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[741]++;

        ValueAxis valueAxis = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[742]++;
        Integer axisIndex = (Integer) this.datasetToDomainAxisMap.get(
                new Integer(index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[743]++;
int CodeCoverConditionCoverageHelper_C210;
        if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((axisIndex != null) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[331]++;
            valueAxis = getDomainAxis(axisIndex.intValue());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[744]++;

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[332]++;
            valueAxis = getDomainAxis(0);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[745]++;
        }
        return valueAxis;

    }

    /**
     * Returns the range axis for a dataset.
     *
     * @param index  the dataset index.
     *
     * @return The axis.
     */
    public ValueAxis getRangeAxisForDataset(int index) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[746]++;
int CodeCoverConditionCoverageHelper_C211;

        if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((index >= getDatasetCount()) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[333]++;
            throw new IllegalArgumentException("Index " + index 
                    + " out of bounds.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[334]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[747]++;

        ValueAxis valueAxis = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[748]++;
        Integer axisIndex
            = (Integer) this.datasetToRangeAxisMap.get(new Integer(index));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[749]++;
int CodeCoverConditionCoverageHelper_C212;
        if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((axisIndex != null) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[335]++;
            valueAxis = getRangeAxis(axisIndex.intValue());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[750]++;

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[336]++;
            valueAxis = getRangeAxis(0);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[751]++;
        }
        return valueAxis;

    }

    /**
     * Draws the gridlines for the plot, if they are visible.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param ticks  the ticks.
     * 
     * @see #drawRangeGridlines(Graphics2D, Rectangle2D, List)
     */
    protected void drawDomainGridlines(Graphics2D g2, Rectangle2D dataArea,
                                       List ticks) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[752]++;
int CodeCoverConditionCoverageHelper_C213;

        // no renderer, no gridlines...
        if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((getRenderer() == null) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[337]++;
            return;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[338]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[753]++;
int CodeCoverConditionCoverageHelper_C214;

        // draw the domain grid lines, if any...
        if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((isDomainGridlinesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[339]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[754]++;
            Stroke gridStroke = getDomainGridlineStroke();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[755]++;
            Paint gridPaint = getDomainGridlinePaint();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[756]++;
int CodeCoverConditionCoverageHelper_C215;
            if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C215 |= (8)) == 0 || true) &&
 ((gridStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((gridPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[341]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[757]++;
                Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[758]++;
byte CodeCoverTryBranchHelper_L45 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[133]++;


int CodeCoverConditionCoverageHelper_C216;
                while ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
if (CodeCoverTryBranchHelper_L45 == 0) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[133]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[134]++;
} else if (CodeCoverTryBranchHelper_L45 == 1) {
  CodeCoverTryBranchHelper_L45++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[134]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[135]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[759]++;
                    ValueTick tick = (ValueTick) iterator.next();
                    getRenderer().drawDomainGridLine(g2, this, getDomainAxis(),
                            dataArea, tick.getValue());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[760]++;
                }

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[342]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[340]++;}
    }

    /**
     * Draws the gridlines for the plot's primary range axis, if they are
     * visible.
     *
     * @param g2  the graphics device.
     * @param area  the data area.
     * @param ticks  the ticks.
     * 
     * @see #drawDomainGridlines(Graphics2D, Rectangle2D, List)
     */
    protected void drawRangeGridlines(Graphics2D g2, Rectangle2D area,
                                      List ticks) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[761]++;
int CodeCoverConditionCoverageHelper_C217;

        // no renderer, no gridlines...
        if ((((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((getRenderer() == null) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[343]++;
            return;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[344]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[762]++;
int CodeCoverConditionCoverageHelper_C218;

        // draw the range grid lines, if any...
        if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((isRangeGridlinesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[345]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[763]++;
            Stroke gridStroke = getRangeGridlineStroke();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[764]++;
            Paint gridPaint = getRangeGridlinePaint();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[765]++;
            ValueAxis axis = getRangeAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[766]++;
int CodeCoverConditionCoverageHelper_C219;
            if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[347]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[767]++;
                Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[768]++;
byte CodeCoverTryBranchHelper_L46 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[136]++;


int CodeCoverConditionCoverageHelper_C220;
                while ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false)) {
if (CodeCoverTryBranchHelper_L46 == 0) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[136]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[137]++;
} else if (CodeCoverTryBranchHelper_L46 == 1) {
  CodeCoverTryBranchHelper_L46++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[137]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[138]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[769]++;
                    ValueTick tick = (ValueTick) iterator.next();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[770]++;
int CodeCoverConditionCoverageHelper_C221;
                    if ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (8)) == 0 || true) &&
 ((tick.getValue() != 0.0) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((isRangeZeroBaselineVisible()) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[349]++;
                        getRenderer().drawRangeLine(g2, this, getRangeAxis(), 
                                area, tick.getValue(), gridPaint, gridStroke);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[771]++;

                    } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[350]++;}
                }

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[348]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[346]++;}
    }

    /**
     * Draws a base line across the chart at value zero on the domain axis.
     *
     * @param g2  the graphics device.
     * @param area  the data area.
     * 
     * @see #setDomainZeroBaselineVisible(boolean)
     * 
     * @since 1.0.5
     */
    protected void drawZeroDomainBaseline(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[772]++;
int CodeCoverConditionCoverageHelper_C222;
        if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((isDomainZeroBaselineVisible()) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[351]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[773]++;
            XYItemRenderer r = getRenderer();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[774]++;
int CodeCoverConditionCoverageHelper_C223;
            // FIXME: the renderer interface doesn't have the drawDomainLine()
            // method, so we have to rely on the renderer being a subclass of
            // AbstractXYItemRenderer (which is lame)
            if ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((r instanceof AbstractXYItemRenderer) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[353]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[775]++;
                AbstractXYItemRenderer renderer = (AbstractXYItemRenderer) r;
                renderer.drawDomainLine(g2, this, getDomainAxis(), area, 0.0, 
                        this.domainZeroBaselinePaint, 
                        this.domainZeroBaselineStroke);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[776]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[354]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[352]++;}
    }

    /**
     * Draws a base line across the chart at value zero on the range axis.
     *
     * @param g2  the graphics device.
     * @param area  the data area.
     * 
     * @see #setRangeZeroBaselineVisible(boolean)
     */
    protected void drawZeroRangeBaseline(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[777]++;
int CodeCoverConditionCoverageHelper_C224;
        if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((isRangeZeroBaselineVisible()) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[355]++;
            getRenderer().drawRangeLine(g2, this, getRangeAxis(), area, 0.0, 
                    this.rangeZeroBaselinePaint, this.rangeZeroBaselineStroke);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[778]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[356]++;}
    }

    /**
     * Draws the annotations for the plot.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param info  the chart rendering info.
     */
    public void drawAnnotations(Graphics2D g2,
                                Rectangle2D dataArea,
                                PlotRenderingInfo info) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[779]++;

        Iterator iterator = this.annotations.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[780]++;
byte CodeCoverTryBranchHelper_L47 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[139]++;


int CodeCoverConditionCoverageHelper_C225;
        while ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
if (CodeCoverTryBranchHelper_L47 == 0) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[139]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[140]++;
} else if (CodeCoverTryBranchHelper_L47 == 1) {
  CodeCoverTryBranchHelper_L47++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[140]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[141]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[781]++;
            XYAnnotation annotation = (XYAnnotation) iterator.next();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[782]++;
            ValueAxis xAxis = getDomainAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[783]++;
            ValueAxis yAxis = getRangeAxis();
            annotation.draw(g2, this, dataArea, xAxis, yAxis, 0, info);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[784]++;
        }

    }

    /**
     * Draws the domain markers (if any) for an axis and layer.  This method is
     * typically called from within the draw() method.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param index  the renderer index.
     * @param layer  the layer (foreground or background).
     */
    protected void drawDomainMarkers(Graphics2D g2, Rectangle2D dataArea,
                                     int index, Layer layer) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[785]++;

        XYItemRenderer r = getRenderer(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[786]++;
int CodeCoverConditionCoverageHelper_C226;
        if ((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[357]++;
            return;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[358]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[787]++;
int CodeCoverConditionCoverageHelper_C227;
        // check that the renderer has a corresponding dataset (it doesn't
        // matter if the dataset is null)
        if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((index >= getDatasetCount()) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[359]++;
            return;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[360]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[788]++;    
        Collection markers = getDomainMarkers(index, layer);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[789]++;
        ValueAxis axis = getDomainAxisForDataset(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[790]++;
int CodeCoverConditionCoverageHelper_C228;
        if ((((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (8)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[361]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[791]++;
            Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[792]++;
byte CodeCoverTryBranchHelper_L48 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[142]++;


int CodeCoverConditionCoverageHelper_C229;
            while ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
if (CodeCoverTryBranchHelper_L48 == 0) {
  CodeCoverTryBranchHelper_L48++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[142]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[143]++;
} else if (CodeCoverTryBranchHelper_L48 == 1) {
  CodeCoverTryBranchHelper_L48++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[143]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[144]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[793]++;
                Marker marker = (Marker) iterator.next();
                r.drawDomainMarker(g2, this, axis, marker, dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[794]++;
            }

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[362]++;}

    }

    /**
     * Draws the range markers (if any) for a renderer and layer.  This method
     * is typically called from within the draw() method.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param index  the renderer index.
     * @param layer  the layer (foreground or background).
     */
    protected void drawRangeMarkers(Graphics2D g2, Rectangle2D dataArea,
                                    int index, Layer layer) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[795]++;

        XYItemRenderer r = getRenderer(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[796]++;
int CodeCoverConditionCoverageHelper_C230;
        if ((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[363]++;
            return;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[364]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[797]++;
int CodeCoverConditionCoverageHelper_C231;
        // check that the renderer has a corresponding dataset (it doesn't
        // matter if the dataset is null)
        if ((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((index >= getDatasetCount()) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[365]++;
            return;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[366]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[798]++;
        Collection markers = getRangeMarkers(index, layer);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[799]++;
        ValueAxis axis = getRangeAxisForDataset(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[800]++;
int CodeCoverConditionCoverageHelper_C232;
        if ((((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (8)) == 0 || true) &&
 ((markers != null) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[367]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[801]++;
            Iterator iterator = markers.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[802]++;
byte CodeCoverTryBranchHelper_L49 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[145]++;


int CodeCoverConditionCoverageHelper_C233;
            while ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
if (CodeCoverTryBranchHelper_L49 == 0) {
  CodeCoverTryBranchHelper_L49++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[145]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[146]++;
} else if (CodeCoverTryBranchHelper_L49 == 1) {
  CodeCoverTryBranchHelper_L49++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[146]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[147]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[803]++;
                Marker marker = (Marker) iterator.next();
                r.drawRangeMarker(g2, this, axis, marker, dataArea);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[804]++;
            }

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[368]++;}
    }

    /**
     * Returns the list of domain markers (read only) for the specified layer.
     *
     * @param layer  the layer (foreground or background).
     *
     * @return The list of domain markers.
     * 
     * @see #getRangeMarkers(Layer)
     */
    public Collection getDomainMarkers(Layer layer) {
        return getDomainMarkers(0, layer);
    }

    /**
     * Returns the list of range markers (read only) for the specified layer.
     *
     * @param layer  the layer (foreground or background).
     *
     * @return The list of range markers.
     * 
     * @see #getDomainMarkers(Layer)
     */
    public Collection getRangeMarkers(Layer layer) {
        return getRangeMarkers(0, layer);
    }

    /**
     * Returns a collection of domain markers for a particular renderer and
     * layer.
     *
     * @param index  the renderer index.
     * @param layer  the layer.
     *
     * @return A collection of markers (possibly <code>null</code>).
     * 
     * @see #getRangeMarkers(int, Layer)
     */
    public Collection getDomainMarkers(int index, Layer layer) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[805]++;
        Collection result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[806]++;
        Integer key = new Integer(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[807]++;
int CodeCoverConditionCoverageHelper_C234;
        if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[369]++;
            result = (Collection) this.foregroundDomainMarkers.get(key);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[808]++;

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[370]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[809]++;
int CodeCoverConditionCoverageHelper_C235; if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((layer == Layer.BACKGROUND) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[371]++;
            result = (Collection) this.backgroundDomainMarkers.get(key);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[810]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[372]++;}
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[811]++;
int CodeCoverConditionCoverageHelper_C236;
        if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[373]++;
            result = Collections.unmodifiableCollection(result);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[812]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[374]++;}
        return result;
    }

    /**
     * Returns a collection of range markers for a particular renderer and
     * layer.
     *
     * @param index  the renderer index.
     * @param layer  the layer.
     *
     * @return A collection of markers (possibly <code>null</code>).
     * 
     * @see #getDomainMarkers(int, Layer)
     */
    public Collection getRangeMarkers(int index, Layer layer) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[813]++;
        Collection result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[814]++;
        Integer key = new Integer(index);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[815]++;
int CodeCoverConditionCoverageHelper_C237;
        if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((layer == Layer.FOREGROUND) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[375]++;
            result = (Collection) this.foregroundRangeMarkers.get(key);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[816]++;

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[376]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[817]++;
int CodeCoverConditionCoverageHelper_C238; if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((layer == Layer.BACKGROUND) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[377]++;
            result = (Collection) this.backgroundRangeMarkers.get(key);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[818]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[378]++;}
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[819]++;
int CodeCoverConditionCoverageHelper_C239;
        if ((((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[379]++;
            result = Collections.unmodifiableCollection(result);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[820]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[380]++;}
        return result;
    }

    /**
     * Utility method for drawing a horizontal line across the data area of the
     * plot.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param value  the coordinate, where to draw the line.
     * @param stroke  the stroke to use.
     * @param paint  the paint to use.
     */
    protected void drawHorizontalLine(Graphics2D g2, Rectangle2D dataArea,
                                      double value, Stroke stroke,
                                      Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[821]++;

        ValueAxis axis = getRangeAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[822]++;
int CodeCoverConditionCoverageHelper_C240;
        if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[381]++;
            axis = getDomainAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[823]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[382]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[824]++;
int CodeCoverConditionCoverageHelper_C241;
        if ((((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((axis.getRange().contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[383]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[825]++;
            double yy = axis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[826]++;
            Line2D line = new Line2D.Double(dataArea.getMinX(), yy, 
                    dataArea.getMaxX(), yy);
            g2.setStroke(stroke);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[827]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[828]++;
            g2.draw(line);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[829]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[384]++;}

    }
    
    /**
     * Draws a domain crosshair.
     * 
     * @param g2  the graphics target.
     * @param dataArea  the data area.
     * @param orientation  the plot orientation.
     * @param value  the crosshair value.
     * @param axis  the axis against which the value is measured.
     * @param stroke  the stroke used to draw the crosshair line.
     * @param paint  the paint used to draw the crosshair line.
     * 
     * @since 1.0.4
     */
    protected void drawDomainCrosshair(Graphics2D g2, Rectangle2D dataArea, 
            PlotOrientation orientation, double value, ValueAxis axis, 
            Stroke stroke, Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[830]++;
int CodeCoverConditionCoverageHelper_C242;
        
        if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((axis.getRange().contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[385]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[831]++;
            Line2D line = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[832]++;
int CodeCoverConditionCoverageHelper_C243;
            if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[387]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[833]++;
                double xx = axis.valueToJava2D(value, dataArea, 
                        RectangleEdge.BOTTOM);
                line = new Line2D.Double(xx, dataArea.getMinY(), xx, 
                        dataArea.getMaxY());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[834]++;

            }
            else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[388]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[835]++;
                double yy = axis.valueToJava2D(value, dataArea, 
                        RectangleEdge.LEFT);
                line = new Line2D.Double(dataArea.getMinX(), yy, 
                        dataArea.getMaxX(), yy);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[836]++;
            }
            g2.setStroke(stroke);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[837]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[838]++;
            g2.draw(line);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[839]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[386]++;}
        
    }

    /**
     * Utility method for drawing a vertical line on the data area of the plot.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param value  the coordinate, where to draw the line.
     * @param stroke  the stroke to use.
     * @param paint  the paint to use.
     */
    protected void drawVerticalLine(Graphics2D g2, Rectangle2D dataArea,
                                    double value, Stroke stroke, Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[840]++;

        ValueAxis axis = getDomainAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[841]++;
int CodeCoverConditionCoverageHelper_C244;
        if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[389]++;
            axis = getRangeAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[842]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[390]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[843]++;
int CodeCoverConditionCoverageHelper_C245;
        if ((((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((axis.getRange().contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[391]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[844]++;
            double xx = axis.valueToJava2D(value, dataArea, 
                    RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[845]++;
            Line2D line = new Line2D.Double(xx, dataArea.getMinY(), xx, 
                    dataArea.getMaxY());
            g2.setStroke(stroke);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[846]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[847]++;
            g2.draw(line);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[848]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[392]++;}

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
     * @since 1.0.4
     */
    protected void drawRangeCrosshair(Graphics2D g2, Rectangle2D dataArea, 
            PlotOrientation orientation, double value, ValueAxis axis, 
            Stroke stroke, Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[849]++;
int CodeCoverConditionCoverageHelper_C246;
        
        if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((axis.getRange().contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[393]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[850]++;
            Line2D line = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[851]++;
int CodeCoverConditionCoverageHelper_C247;
            if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[395]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[852]++;
                double xx = axis.valueToJava2D(value, dataArea, 
                        RectangleEdge.BOTTOM);
                line = new Line2D.Double(xx, dataArea.getMinY(), xx, 
                        dataArea.getMaxY());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[853]++;

            }
            else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[396]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[854]++;
                double yy = axis.valueToJava2D(value, dataArea, 
                        RectangleEdge.LEFT);
                line = new Line2D.Double(dataArea.getMinX(), yy, 
                        dataArea.getMaxX(), yy);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[855]++;
            }
            g2.setStroke(stroke);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[856]++;
            g2.setPaint(paint);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[857]++;
            g2.draw(line);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[858]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[394]++;}
        
    }

    /**
     * Handles a 'click' on the plot by updating the anchor values.
     *
     * @param x  the x-coordinate, where the click occurred, in Java2D space.
     * @param y  the y-coordinate, where the click occurred, in Java2D space.
     * @param info  object containing information about the plot dimensions.
     */
    public void handleClick(int x, int y, PlotRenderingInfo info) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[859]++;

        Rectangle2D dataArea = info.getDataArea();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[860]++;
int CodeCoverConditionCoverageHelper_C248;
        if ((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((dataArea.contains(x, y)) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[397]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[861]++;
            // set the anchor value for the horizontal axis...
            ValueAxis da = getDomainAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[862]++;
int CodeCoverConditionCoverageHelper_C249;
            if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((da != null) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[399]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[863]++;
                double hvalue = da.java2DToValue(x, info.getDataArea(), 
                        getDomainAxisEdge());
                setDomainCrosshairValue(hvalue);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[864]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[400]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[865]++;

            // set the anchor value for the vertical axis...
            ValueAxis ra = getRangeAxis();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[866]++;
int CodeCoverConditionCoverageHelper_C250;
            if ((((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((ra != null) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[401]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[867]++;
                double vvalue = ra.java2DToValue(y, info.getDataArea(), 
                        getRangeAxisEdge());
                setRangeCrosshairValue(vvalue);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[868]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[402]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[398]++;}
    }

    /**
     * A utility method that returns a list of datasets that are mapped to a
     * particular axis.
     *
     * @param axisIndex  the axis index (<code>null</code> not permitted).
     *
     * @return A list of datasets.
     */
    private List getDatasetsMappedToDomainAxis(Integer axisIndex) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[869]++;
int CodeCoverConditionCoverageHelper_C251;
        if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((axisIndex == null) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[403]++;
            throw new IllegalArgumentException("Null 'axisIndex' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[404]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[870]++;
        List result = new ArrayList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[871]++;
byte CodeCoverTryBranchHelper_L50 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[148]++;


int CodeCoverConditionCoverageHelper_C252;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((i < this.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L50 == 0) {
  CodeCoverTryBranchHelper_L50++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[148]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[149]++;
} else if (CodeCoverTryBranchHelper_L50 == 1) {
  CodeCoverTryBranchHelper_L50++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[149]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[150]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[872]++;
            Integer mappedAxis = (Integer) this.datasetToDomainAxisMap.get(
                    new Integer(i));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[873]++;
int CodeCoverConditionCoverageHelper_C253;
            if ((((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((mappedAxis == null) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[405]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[874]++;
int CodeCoverConditionCoverageHelper_C254;
                if ((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((axisIndex.equals(ZERO)) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[407]++;
                    result.add(this.datasets.get(i));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[875]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[408]++;}

            }
            else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[406]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[876]++;
int CodeCoverConditionCoverageHelper_C255;
                if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((mappedAxis.equals(axisIndex)) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[409]++;
                    result.add(this.datasets.get(i));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[877]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[410]++;}
            }
        }
        return result;
    }

    /**
     * A utility method that returns a list of datasets that are mapped to a
     * particular axis.
     *
     * @param axisIndex  the axis index (<code>null</code> not permitted).
     *
     * @return A list of datasets.
     */
    private List getDatasetsMappedToRangeAxis(Integer axisIndex) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[878]++;
int CodeCoverConditionCoverageHelper_C256;
        if ((((((CodeCoverConditionCoverageHelper_C256 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C256 |= (2)) == 0 || true) &&
 ((axisIndex == null) && 
  ((CodeCoverConditionCoverageHelper_C256 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[256].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C256, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[411]++;
            throw new IllegalArgumentException("Null 'axisIndex' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[412]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[879]++;
        List result = new ArrayList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[880]++;
byte CodeCoverTryBranchHelper_L51 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[151]++;


int CodeCoverConditionCoverageHelper_C257;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((i < this.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L51 == 0) {
  CodeCoverTryBranchHelper_L51++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[151]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[152]++;
} else if (CodeCoverTryBranchHelper_L51 == 1) {
  CodeCoverTryBranchHelper_L51++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[152]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[153]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[881]++;
            Integer mappedAxis = (Integer) this.datasetToRangeAxisMap.get(
                    new Integer(i));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[882]++;
int CodeCoverConditionCoverageHelper_C258;
            if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((mappedAxis == null) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[413]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[883]++;
int CodeCoverConditionCoverageHelper_C259;
                if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((axisIndex.equals(ZERO)) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[415]++;
                    result.add(this.datasets.get(i));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[884]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[416]++;}

            }
            else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[414]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[885]++;
int CodeCoverConditionCoverageHelper_C260;
                if ((((((CodeCoverConditionCoverageHelper_C260 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C260 |= (2)) == 0 || true) &&
 ((mappedAxis.equals(axisIndex)) && 
  ((CodeCoverConditionCoverageHelper_C260 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[417]++;
                    result.add(this.datasets.get(i));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[886]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[418]++;}
            }
        }
        return result;
    }

    /**
     * Returns the index of the given domain axis.
     *
     * @param axis  the axis.
     *
     * @return The axis index.
     * 
     * @see #getRangeAxisIndex(ValueAxis)
     */
    public int getDomainAxisIndex(ValueAxis axis) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[887]++;
        int result = this.domainAxes.indexOf(axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[888]++;
int CodeCoverConditionCoverageHelper_C261;
        if ((((((CodeCoverConditionCoverageHelper_C261 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C261 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C261 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[419]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[889]++;
            // try the parent plot
            Plot parent = getParent();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[890]++;
int CodeCoverConditionCoverageHelper_C262;
            if ((((((CodeCoverConditionCoverageHelper_C262 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C262 |= (2)) == 0 || true) &&
 ((parent instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C262 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[421]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[891]++;
                XYPlot p = (XYPlot) parent;
                result = p.getDomainAxisIndex(axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[892]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[422]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[420]++;}
        return result;
    }

    /**
     * Returns the index of the given range axis.
     *
     * @param axis  the axis.
     *
     * @return The axis index.
     * 
     * @see #getDomainAxisIndex(ValueAxis)
     */
    public int getRangeAxisIndex(ValueAxis axis) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[893]++;
        int result = this.rangeAxes.indexOf(axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[894]++;
int CodeCoverConditionCoverageHelper_C263;
        if ((((((CodeCoverConditionCoverageHelper_C263 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C263 |= (2)) == 0 || true) &&
 ((result < 0) && 
  ((CodeCoverConditionCoverageHelper_C263 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[423]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[895]++;
            // try the parent plot
            Plot parent = getParent();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[896]++;
int CodeCoverConditionCoverageHelper_C264;
            if ((((((CodeCoverConditionCoverageHelper_C264 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C264 |= (2)) == 0 || true) &&
 ((parent instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[425]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[897]++;
                XYPlot p = (XYPlot) parent;
                result = p.getRangeAxisIndex(axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[898]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[426]++;}

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[424]++;}
        return result;
    }

    /**
     * Returns the range for the specified axis.
     *
     * @param axis  the axis.
     *
     * @return The range.
     */
    public Range getDataRange(ValueAxis axis) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[899]++;

        Range result = null;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[900]++;
        List mappedDatasets = new ArrayList();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[901]++;
        boolean isDomainAxis = true;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[902]++;

        // is it a domain axis?
        int domainIndex = getDomainAxisIndex(axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[903]++;
int CodeCoverConditionCoverageHelper_C265;
        if ((((((CodeCoverConditionCoverageHelper_C265 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C265 |= (2)) == 0 || true) &&
 ((domainIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[427]++;
            isDomainAxis = true;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[904]++;
            mappedDatasets.addAll(getDatasetsMappedToDomainAxis(
                    new Integer(domainIndex)));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[905]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[428]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[906]++;

        // or is it a range axis?
        int rangeIndex = getRangeAxisIndex(axis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[907]++;
int CodeCoverConditionCoverageHelper_C266;
        if ((((((CodeCoverConditionCoverageHelper_C266 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C266 |= (2)) == 0 || true) &&
 ((rangeIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[429]++;
            isDomainAxis = false;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[908]++;
            mappedDatasets.addAll(getDatasetsMappedToRangeAxis(
                    new Integer(rangeIndex)));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[909]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[430]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[910]++;

        // iterate through the datasets that map to the axis and get the union
        // of the ranges.
        Iterator iterator = mappedDatasets.iterator();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[911]++;
byte CodeCoverTryBranchHelper_L52 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[154]++;


int CodeCoverConditionCoverageHelper_C267;
        while ((((((CodeCoverConditionCoverageHelper_C267 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C267 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C267 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) && false)) {
if (CodeCoverTryBranchHelper_L52 == 0) {
  CodeCoverTryBranchHelper_L52++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[154]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[155]++;
} else if (CodeCoverTryBranchHelper_L52 == 1) {
  CodeCoverTryBranchHelper_L52++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[155]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[156]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[912]++;
            XYDataset d = (XYDataset) iterator.next();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[913]++;
int CodeCoverConditionCoverageHelper_C268;
            if ((((((CodeCoverConditionCoverageHelper_C268 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C268 |= (2)) == 0 || true) &&
 ((d != null) && 
  ((CodeCoverConditionCoverageHelper_C268 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[431]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[914]++;
                XYItemRenderer r = getRendererForDataset(d);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[915]++;
int CodeCoverConditionCoverageHelper_C269;
                if ((((((CodeCoverConditionCoverageHelper_C269 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C269 |= (2)) == 0 || true) &&
 ((isDomainAxis) && 
  ((CodeCoverConditionCoverageHelper_C269 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[433]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[916]++;
int CodeCoverConditionCoverageHelper_C270;
                    if ((((((CodeCoverConditionCoverageHelper_C270 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C270 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[435]++;
                        result = Range.combine(result, r.findDomainBounds(d));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[917]++;

                    }
                    else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[436]++;
                        result = Range.combine(result, 
                                DatasetUtilities.findDomainBounds(d));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[918]++;
                    }

                }
                else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[434]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[919]++;
int CodeCoverConditionCoverageHelper_C271;
                    if ((((((CodeCoverConditionCoverageHelper_C271 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C271 |= (2)) == 0 || true) &&
 ((r != null) && 
  ((CodeCoverConditionCoverageHelper_C271 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[437]++;
                        result = Range.combine(result, r.findRangeBounds(d));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[920]++;

                    }
                    else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[438]++;
                        result = Range.combine(result, 
                                DatasetUtilities.findRangeBounds(d));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[921]++;
                    }
                }

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[432]++;}
        }
        return result;

    }

    /**
     * Receives notification of a change to the plot's dataset.
     * <P>
     * The axis ranges are updated if necessary.
     *
     * @param event  information about the event (not used here).
     */
    public void datasetChanged(DatasetChangeEvent event) {
        configureDomainAxes();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[922]++;
        configureRangeAxes();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[923]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[924]++;
int CodeCoverConditionCoverageHelper_C272;
        if ((((((CodeCoverConditionCoverageHelper_C272 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C272 |= (2)) == 0 || true) &&
 ((getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C272 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[439]++;
            getParent().datasetChanged(event);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[925]++;

        }
        else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[440]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[926]++;
            PlotChangeEvent e = new PlotChangeEvent(this);
            e.setType(ChartChangeEventType.DATASET_UPDATED);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[927]++;
            notifyListeners(e);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[928]++;
        }
    }

    /**
     * Receives notification of a renderer change event.
     *
     * @param event  the event.
     */
    public void rendererChanged(RendererChangeEvent event) {
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[929]++;
    }

    /**
     * Returns a flag indicating whether or not the domain crosshair is visible.
     *
     * @return The flag.
     * 
     * @see #setDomainCrosshairVisible(boolean)
     */
    public boolean isDomainCrosshairVisible() {
        return this.domainCrosshairVisible;
    }

    /**
     * Sets the flag indicating whether or not the domain crosshair is visible 
     * and, if the flag changes, sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param flag  the new value of the flag.
     * 
     * @see #isDomainCrosshairVisible()
     */
    public void setDomainCrosshairVisible(boolean flag) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[930]++;
int CodeCoverConditionCoverageHelper_C273;
        if ((((((CodeCoverConditionCoverageHelper_C273 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C273 |= (2)) == 0 || true) &&
 ((this.domainCrosshairVisible != flag) && 
  ((CodeCoverConditionCoverageHelper_C273 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[441]++;
            this.domainCrosshairVisible = flag;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[931]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[932]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[442]++;}
    }

    /**
     * Returns a flag indicating whether or not the crosshair should "lock-on"
     * to actual data values.
     *
     * @return The flag.
     * 
     * @see #setDomainCrosshairLockedOnData(boolean)
     */
    public boolean isDomainCrosshairLockedOnData() {
        return this.domainCrosshairLockedOnData;
    }

    /**
     * Sets the flag indicating whether or not the domain crosshair should
     * "lock-on" to actual data values.  If the flag value changes, this
     * method sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #isDomainCrosshairLockedOnData()
     */
    public void setDomainCrosshairLockedOnData(boolean flag) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[933]++;
int CodeCoverConditionCoverageHelper_C274;
        if ((((((CodeCoverConditionCoverageHelper_C274 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C274 |= (2)) == 0 || true) &&
 ((this.domainCrosshairLockedOnData != flag) && 
  ((CodeCoverConditionCoverageHelper_C274 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[443]++;
            this.domainCrosshairLockedOnData = flag;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[934]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[935]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[444]++;}
    }

    /**
     * Returns the domain crosshair value.
     *
     * @return The value.
     * 
     * @see #setDomainCrosshairValue(double)
     */
    public double getDomainCrosshairValue() {
        return this.domainCrosshairValue;
    }

    /**
     * Sets the domain crosshair value and sends a {@link PlotChangeEvent} to
     * all registered listeners (provided that the domain crosshair is visible).
     *
     * @param value  the value.
     * 
     * @see #getDomainCrosshairValue()
     */
    public void setDomainCrosshairValue(double value) {
        setDomainCrosshairValue(value, true);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[936]++;
    }

    /**
     * Sets the domain crosshair value and, if requested, sends a
     * {@link PlotChangeEvent} to all registered listeners (provided that the
     * domain crosshair is visible).
     *
     * @param value  the new value.
     * @param notify  notify listeners?
     * 
     * @see #getDomainCrosshairValue()
     */
    public void setDomainCrosshairValue(double value, boolean notify) {
        this.domainCrosshairValue = value;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[937]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[938]++;
int CodeCoverConditionCoverageHelper_C275;
        if ((((((CodeCoverConditionCoverageHelper_C275 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C275 |= (8)) == 0 || true) &&
 ((isDomainCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C275 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[445]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[939]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[446]++;}
    }

    /**
     * Returns the {@link Stroke} used to draw the crosshair (if visible).
     *
     * @return The crosshair stroke (never <code>null</code>).
     * 
     * @see #setDomainCrosshairStroke(Stroke)
     * @see #isDomainCrosshairVisible()
     * @see #getDomainCrosshairPaint()
     */
    public Stroke getDomainCrosshairStroke() {
        return this.domainCrosshairStroke;
    }

    /**
     * Sets the Stroke used to draw the crosshairs (if visible) and notifies
     * registered listeners that the axis has been modified.
     *
     * @param stroke  the new crosshair stroke (<code>null</code> not 
     *     permitted).
     *     
     * @see #getDomainCrosshairStroke()
     */
    public void setDomainCrosshairStroke(Stroke stroke) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[940]++;
int CodeCoverConditionCoverageHelper_C276;
        if ((((((CodeCoverConditionCoverageHelper_C276 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C276 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[447]++; 
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[448]++;}
        this.domainCrosshairStroke = stroke;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[941]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[942]++;
    }

    /**
     * Returns the domain crosshair paint.
     *
     * @return The crosshair paint (never <code>null</code>).
     * 
     * @see #setDomainCrosshairPaint(Paint)
     * @see #isDomainCrosshairVisible()
     * @see #getDomainCrosshairStroke()
     */
    public Paint getDomainCrosshairPaint() {
        return this.domainCrosshairPaint;
    }

    /**
     * Sets the paint used to draw the crosshairs (if visible) and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the new crosshair paint (<code>null</code> not permitted).
     * 
     * @see #getDomainCrosshairPaint()
     */
    public void setDomainCrosshairPaint(Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[943]++;
int CodeCoverConditionCoverageHelper_C277;
        if ((((((CodeCoverConditionCoverageHelper_C277 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C277 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C277 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[449]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[450]++;}
        this.domainCrosshairPaint = paint;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[944]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[945]++;
    }

    /**
     * Returns a flag indicating whether or not the range crosshair is visible.
     *
     * @return The flag.
     * 
     * @see #setRangeCrosshairVisible(boolean)
     * @see #isDomainCrosshairVisible()
     */
    public boolean isRangeCrosshairVisible() {
        return this.rangeCrosshairVisible;
    }

    /**
     * Sets the flag indicating whether or not the range crosshair is visible.
     * If the flag value changes, this method sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param flag  the new value of the flag.
     * 
     * @see #isRangeCrosshairVisible()
     */
    public void setRangeCrosshairVisible(boolean flag) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[946]++;
int CodeCoverConditionCoverageHelper_C278;
        if ((((((CodeCoverConditionCoverageHelper_C278 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C278 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairVisible != flag) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[451]++;
            this.rangeCrosshairVisible = flag;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[947]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[948]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[452]++;}
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
     * "lock-on" to actual data values.  If the flag value changes, this method
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param flag  the flag.
     * 
     * @see #isRangeCrosshairLockedOnData()
     */
    public void setRangeCrosshairLockedOnData(boolean flag) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[949]++;
int CodeCoverConditionCoverageHelper_C279;
        if ((((((CodeCoverConditionCoverageHelper_C279 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C279 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairLockedOnData != flag) && 
  ((CodeCoverConditionCoverageHelper_C279 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[453]++;
            this.rangeCrosshairLockedOnData = flag;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[950]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[951]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[454]++;}
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
     * Sets the range crosshair value.
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[952]++;
    }

    /**
     * Sets the range crosshair value and sends a {@link PlotChangeEvent} to
     * all registered listeners, but only if the crosshair is visible.
     *
     * @param value  the new value.
     * @param notify  a flag that controls whether or not listeners are
     *                notified.
     *                
     * @see #getRangeCrosshairValue()
     */
    public void setRangeCrosshairValue(double value, boolean notify) {
        this.rangeCrosshairValue = value;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[953]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[954]++;
int CodeCoverConditionCoverageHelper_C280;
        if ((((((CodeCoverConditionCoverageHelper_C280 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C280 |= (8)) == 0 || true) &&
 ((isRangeCrosshairVisible()) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C280 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[455]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[955]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[456]++;}
    }

    /**
     * Returns the stroke used to draw the crosshair (if visible).
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
     * Sets the stroke used to draw the crosshairs (if visible) and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the new crosshair stroke (<code>null</code> not 
     *         permitted).
     * 
     * @see #getRangeCrosshairStroke()
     */
    public void setRangeCrosshairStroke(Stroke stroke) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[956]++;
int CodeCoverConditionCoverageHelper_C281;
        if ((((((CodeCoverConditionCoverageHelper_C281 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C281 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C281 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[457]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[458]++;}
        this.rangeCrosshairStroke = stroke;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[957]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[958]++;
    }

    /**
     * Returns the range crosshair paint.
     *
     * @return The crosshair paint (never <code>null</code>).
     * 
     * @see #setRangeCrosshairPaint(Paint)
     * @see #isRangeCrosshairVisible()
     * @see #getRangeCrosshairStroke()
     */
    public Paint getRangeCrosshairPaint() {
        return this.rangeCrosshairPaint;
    }

    /**
     * Sets the paint used to color the crosshairs (if visible) and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint the new crosshair paint (<code>null</code> not permitted).
     * 
     * @see #getRangeCrosshairPaint()
     */
    public void setRangeCrosshairPaint(Paint paint) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[959]++;
int CodeCoverConditionCoverageHelper_C282;
        if ((((((CodeCoverConditionCoverageHelper_C282 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C282 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C282 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[459]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[460]++;}
        this.rangeCrosshairPaint = paint;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[960]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[961]++;
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
        this.fixedDomainAxisSpace = space;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[962]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[963]++;
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
        this.fixedRangeAxisSpace = space;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[964]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[965]++;
    }

    /**
     * Multiplies the range on the domain axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info.
     * @param source  the source point (in Java2D space).
     * 
     * @see #zoomRangeAxes(double, PlotRenderingInfo, Point2D)
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo info,
                               Point2D source) {
        // delegate to other method
        zoomDomainAxes(factor, info, source, false);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[966]++;
    }

    /**
     * Multiplies the range on the domain axis/axes by the specified factor.
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[967]++;
byte CodeCoverTryBranchHelper_L53 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[157]++;


int CodeCoverConditionCoverageHelper_C283;
                
        // perform the zoom on each domain axis
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C283 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C283 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C283 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L53 == 0) {
  CodeCoverTryBranchHelper_L53++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[157]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[158]++;
} else if (CodeCoverTryBranchHelper_L53 == 1) {
  CodeCoverTryBranchHelper_L53++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[158]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[159]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[968]++;
            ValueAxis domainAxis = (ValueAxis) this.domainAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[969]++;
int CodeCoverConditionCoverageHelper_C284;
            if ((((((CodeCoverConditionCoverageHelper_C284 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C284 |= (2)) == 0 || true) &&
 ((domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C284 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[461]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[970]++;
int CodeCoverConditionCoverageHelper_C285;
                if ((((((CodeCoverConditionCoverageHelper_C285 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C285 |= (2)) == 0 || true) &&
 ((useAnchor) && 
  ((CodeCoverConditionCoverageHelper_C285 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[463]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[971]++;
                    // get the relevant source coordinate given the plot 
                    // orientation
                    double sourceX = source.getX();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[972]++;
int CodeCoverConditionCoverageHelper_C286;
                    if ((((((CodeCoverConditionCoverageHelper_C286 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C286 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[465]++;
                        sourceX = source.getY();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[973]++;

                    } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[466]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[974]++;
                    double anchorX = domainAxis.java2DToValue(sourceX, 
                            info.getDataArea(), getDomainAxisEdge());
                    domainAxis.resizeRange(factor, anchorX);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[975]++;

                }
                else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[464]++;
                    domainAxis.resizeRange(factor);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[976]++;
                }

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[462]++;}
        }
    }

    /**
     * Zooms in on the domain axis/axes.  The new lower and upper bounds are
     * specified as percentages of the current axis range, where 0 percent is
     * the current lower bound and 100 percent is the current upper bound.
     *
     * @param lowerPercent  a percentage that determines the new lower bound
     *                      for the axis (e.g. 0.20 is twenty percent).
     * @param upperPercent  a percentage that determines the new upper bound
     *                      for the axis (e.g. 0.80 is eighty percent).
     * @param info  the plot rendering info.
     * @param source  the source point (ignored).
     * 
     * @see #zoomRangeAxes(double, double, PlotRenderingInfo, Point2D)
     */
    public void zoomDomainAxes(double lowerPercent, double upperPercent,
                               PlotRenderingInfo info, Point2D source) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[977]++;
byte CodeCoverTryBranchHelper_L54 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[160]++;


int CodeCoverConditionCoverageHelper_C287;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C287 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C287 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C287 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L54 == 0) {
  CodeCoverTryBranchHelper_L54++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[160]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[161]++;
} else if (CodeCoverTryBranchHelper_L54 == 1) {
  CodeCoverTryBranchHelper_L54++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[161]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[162]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[978]++;
            ValueAxis domainAxis = (ValueAxis) this.domainAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[979]++;
int CodeCoverConditionCoverageHelper_C288;
            if ((((((CodeCoverConditionCoverageHelper_C288 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C288 |= (2)) == 0 || true) &&
 ((domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C288 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[467]++;
                domainAxis.zoomRange(lowerPercent, upperPercent);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[980]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[468]++;}
        }
    }

    /**
     * Multiplies the range on the range axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info.
     * @param source  the source point.
     * 
     * @see #zoomDomainAxes(double, PlotRenderingInfo, Point2D, boolean)
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo info,
                              Point2D source) {
        // delegate to other method
        zoomRangeAxes(factor, info, source, false);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[981]++;    
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[982]++;
byte CodeCoverTryBranchHelper_L55 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[163]++;


int CodeCoverConditionCoverageHelper_C289;
                
        // perform the zoom on each range axis
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C289 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C289 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C289 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L55 == 0) {
  CodeCoverTryBranchHelper_L55++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[163]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[164]++;
} else if (CodeCoverTryBranchHelper_L55 == 1) {
  CodeCoverTryBranchHelper_L55++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[164]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[165]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[983]++;
            ValueAxis rangeAxis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[984]++;
int CodeCoverConditionCoverageHelper_C290;
            if ((((((CodeCoverConditionCoverageHelper_C290 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C290 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[469]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[985]++;
int CodeCoverConditionCoverageHelper_C291;
                if ((((((CodeCoverConditionCoverageHelper_C291 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C291 |= (2)) == 0 || true) &&
 ((useAnchor) && 
  ((CodeCoverConditionCoverageHelper_C291 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[471]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[986]++;
                    // get the relevant source coordinate given the plot 
                    // orientation
                    double sourceY = source.getY();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[987]++;
int CodeCoverConditionCoverageHelper_C292;
                    if ((((((CodeCoverConditionCoverageHelper_C292 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C292 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C292 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[473]++;
                        sourceY = source.getX();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[988]++;

                    } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[474]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[989]++;
                    double anchorY = rangeAxis.java2DToValue(sourceY, 
                            info.getDataArea(), getRangeAxisEdge());
                    rangeAxis.resizeRange(factor, anchorY);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[990]++;

                }
                else {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[472]++;
                    rangeAxis.resizeRange(factor);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[991]++;
                }

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[470]++;}
        }
    }

    /**
     * Zooms in on the range axes.
     *
     * @param lowerPercent  the lower bound.
     * @param upperPercent  the upper bound.
     * @param info  the plot rendering info.
     * @param source  the source point.
     * 
     * @see #zoomDomainAxes(double, double, PlotRenderingInfo, Point2D)
     */
    public void zoomRangeAxes(double lowerPercent, double upperPercent,
                              PlotRenderingInfo info, Point2D source) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[992]++;
byte CodeCoverTryBranchHelper_L56 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[166]++;


int CodeCoverConditionCoverageHelper_C293;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C293 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C293 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C293 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L56 == 0) {
  CodeCoverTryBranchHelper_L56++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[166]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[167]++;
} else if (CodeCoverTryBranchHelper_L56 == 1) {
  CodeCoverTryBranchHelper_L56++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[167]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[168]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[993]++;
            ValueAxis rangeAxis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[994]++;
int CodeCoverConditionCoverageHelper_C294;
            if ((((((CodeCoverConditionCoverageHelper_C294 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C294 |= (2)) == 0 || true) &&
 ((rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C294 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[475]++;
                rangeAxis.zoomRange(lowerPercent, upperPercent);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[995]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[476]++;}
        }
    }

    /**
     * Returns <code>true</code>, indicating that the domain axis/axes for this
     * plot are zoomable.
     *
     * @return A boolean.
     * 
     * @see #isRangeZoomable()
     */
    public boolean isDomainZoomable() {
        return true;
    }

    /**
     * Returns <code>true</code>, indicating that the range axis/axes for this
     * plot are zoomable.
     *
     * @return A boolean.
     * 
     * @see #isDomainZoomable()
     */
    public boolean isRangeZoomable() {
        return true;
    }

    /**
     * Returns the number of series in the primary dataset for this plot.  If
     * the dataset is <code>null</code>, the method returns 0.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[996]++;
        int result = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[997]++;
        XYDataset dataset = getDataset();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[998]++;
int CodeCoverConditionCoverageHelper_C295;
        if ((((((CodeCoverConditionCoverageHelper_C295 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C295 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C295 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[477]++;
            result = dataset.getSeriesCount();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[999]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[478]++;}
        return result;
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1000]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1001]++;
    }

    /**
     * Returns the legend items for the plot.  Each legend item is generated by
     * the plot's renderer, since the renderer is responsible for the visual
     * representation of the data.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1002]++;
int CodeCoverConditionCoverageHelper_C296;
        if ((((((CodeCoverConditionCoverageHelper_C296 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C296 |= (2)) == 0 || true) &&
 ((this.fixedLegendItems != null) && 
  ((CodeCoverConditionCoverageHelper_C296 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[479]++;
            return this.fixedLegendItems;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[480]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1003]++;
        LegendItemCollection result = new LegendItemCollection();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1004]++;
        int count = this.datasets.size();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1005]++;
byte CodeCoverTryBranchHelper_L57 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[169]++;


int CodeCoverConditionCoverageHelper_C297;
        for (int datasetIndex = 0;(((((CodeCoverConditionCoverageHelper_C297 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C297 |= (2)) == 0 || true) &&
 ((datasetIndex < count) && 
  ((CodeCoverConditionCoverageHelper_C297 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) && false); datasetIndex++) {
if (CodeCoverTryBranchHelper_L57 == 0) {
  CodeCoverTryBranchHelper_L57++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[169]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[170]++;
} else if (CodeCoverTryBranchHelper_L57 == 1) {
  CodeCoverTryBranchHelper_L57++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[170]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[171]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1006]++;
            XYDataset dataset = getDataset(datasetIndex);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1007]++;
int CodeCoverConditionCoverageHelper_C298;
            if ((((((CodeCoverConditionCoverageHelper_C298 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C298 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C298 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[481]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1008]++;
                XYItemRenderer renderer = getRenderer(datasetIndex);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1009]++;
int CodeCoverConditionCoverageHelper_C299;
                if ((((((CodeCoverConditionCoverageHelper_C299 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C299 |= (2)) == 0 || true) &&
 ((renderer == null) && 
  ((CodeCoverConditionCoverageHelper_C299 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[299].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C299, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[483]++;
                    renderer = getRenderer(0);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1010]++;

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[484]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1011]++;
int CodeCoverConditionCoverageHelper_C300;
                if ((((((CodeCoverConditionCoverageHelper_C300 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C300 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C300 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[300].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C300, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[485]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1012]++;
                    int seriesCount = dataset.getSeriesCount();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1013]++;
byte CodeCoverTryBranchHelper_L58 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[172]++;


int CodeCoverConditionCoverageHelper_C301;
                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C301 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C301 |= (2)) == 0 || true) &&
 ((i < seriesCount) && 
  ((CodeCoverConditionCoverageHelper_C301 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[301].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C301, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L58 == 0) {
  CodeCoverTryBranchHelper_L58++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[172]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[173]++;
} else if (CodeCoverTryBranchHelper_L58 == 1) {
  CodeCoverTryBranchHelper_L58++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[173]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[174]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1014]++;
int CodeCoverConditionCoverageHelper_C302;
                        if ((((((CodeCoverConditionCoverageHelper_C302 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C302 |= (8)) == 0 || true) &&
 ((renderer.isSeriesVisible(i)) && 
  ((CodeCoverConditionCoverageHelper_C302 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C302 |= (2)) == 0 || true) &&
 ((renderer.isSeriesVisibleInLegend(i)) && 
  ((CodeCoverConditionCoverageHelper_C302 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[302].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C302, 2) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[302].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C302, 2) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[487]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1015]++;
                            LegendItem item = renderer.getLegendItem(
                                    datasetIndex, i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1016]++;
int CodeCoverConditionCoverageHelper_C303;
                            if ((((((CodeCoverConditionCoverageHelper_C303 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C303 |= (2)) == 0 || true) &&
 ((item != null) && 
  ((CodeCoverConditionCoverageHelper_C303 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[303].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C303, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[303].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C303, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[489]++;
                                result.add(item);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1017]++;

                            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[490]++;}

                        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[488]++;}
                    }

                } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[486]++;}

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[482]++;}
        }
        return result;
    }

    /**
     * Tests this plot for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1018]++;
int CodeCoverConditionCoverageHelper_C304;

        if ((((((CodeCoverConditionCoverageHelper_C304 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C304 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C304 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[304].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C304, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[304].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C304, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[491]++;
            return true;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[492]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1019]++;
int CodeCoverConditionCoverageHelper_C305;
        if ((((((CodeCoverConditionCoverageHelper_C305 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C305 |= (2)) == 0 || true) &&
 ((obj instanceof XYPlot) && 
  ((CodeCoverConditionCoverageHelper_C305 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[305].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C305, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[305].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C305, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[493]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[494]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1020]++;

        XYPlot that = (XYPlot) obj;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1021]++;
int CodeCoverConditionCoverageHelper_C306;
        if ((((((CodeCoverConditionCoverageHelper_C306 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C306 |= (2)) == 0 || true) &&
 ((this.weight != that.weight) && 
  ((CodeCoverConditionCoverageHelper_C306 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[306].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C306, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[306].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C306, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[495]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[496]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1022]++;
int CodeCoverConditionCoverageHelper_C307;
        if ((((((CodeCoverConditionCoverageHelper_C307 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C307 |= (2)) == 0 || true) &&
 ((this.orientation != that.orientation) && 
  ((CodeCoverConditionCoverageHelper_C307 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[307].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C307, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[307].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C307, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[497]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[498]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1023]++;
int CodeCoverConditionCoverageHelper_C308;
        if ((((((CodeCoverConditionCoverageHelper_C308 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C308 |= (2)) == 0 || true) &&
 ((this.domainAxes.equals(that.domainAxes)) && 
  ((CodeCoverConditionCoverageHelper_C308 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[308].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C308, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[308].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C308, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[499]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[500]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1024]++;
int CodeCoverConditionCoverageHelper_C309;
        if ((((((CodeCoverConditionCoverageHelper_C309 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C309 |= (2)) == 0 || true) &&
 ((this.domainAxisLocations.equals(that.domainAxisLocations)) && 
  ((CodeCoverConditionCoverageHelper_C309 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[309].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C309, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[309].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C309, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[501]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[502]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1025]++;
int CodeCoverConditionCoverageHelper_C310;
        if ((((((CodeCoverConditionCoverageHelper_C310 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C310 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairLockedOnData
                != that.rangeCrosshairLockedOnData) && 
  ((CodeCoverConditionCoverageHelper_C310 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[310].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C310, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[310].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C310, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[503]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[504]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1026]++;
int CodeCoverConditionCoverageHelper_C311;
        if ((((((CodeCoverConditionCoverageHelper_C311 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C311 |= (2)) == 0 || true) &&
 ((this.domainGridlinesVisible != that.domainGridlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C311 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[311].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C311, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[311].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C311, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[505]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[506]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1027]++;
int CodeCoverConditionCoverageHelper_C312;
        if ((((((CodeCoverConditionCoverageHelper_C312 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C312 |= (2)) == 0 || true) &&
 ((this.rangeGridlinesVisible != that.rangeGridlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C312 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[312].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C312, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[312].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C312, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[507]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[508]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1028]++;
int CodeCoverConditionCoverageHelper_C313;
        if ((((((CodeCoverConditionCoverageHelper_C313 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C313 |= (2)) == 0 || true) &&
 ((this.domainZeroBaselineVisible != that.domainZeroBaselineVisible) && 
  ((CodeCoverConditionCoverageHelper_C313 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[313].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C313, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[313].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C313, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[509]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[510]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1029]++;
int CodeCoverConditionCoverageHelper_C314;
        if ((((((CodeCoverConditionCoverageHelper_C314 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C314 |= (2)) == 0 || true) &&
 ((this.rangeZeroBaselineVisible != that.rangeZeroBaselineVisible) && 
  ((CodeCoverConditionCoverageHelper_C314 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[314].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C314, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[314].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C314, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[511]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[512]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1030]++;
int CodeCoverConditionCoverageHelper_C315;
        if ((((((CodeCoverConditionCoverageHelper_C315 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C315 |= (2)) == 0 || true) &&
 ((this.domainCrosshairVisible != that.domainCrosshairVisible) && 
  ((CodeCoverConditionCoverageHelper_C315 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[315].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C315, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[315].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C315, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[513]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[514]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1031]++;
int CodeCoverConditionCoverageHelper_C316;
        if ((((((CodeCoverConditionCoverageHelper_C316 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C316 |= (2)) == 0 || true) &&
 ((this.domainCrosshairValue != that.domainCrosshairValue) && 
  ((CodeCoverConditionCoverageHelper_C316 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[316].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C316, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[316].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C316, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[515]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[516]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1032]++;
int CodeCoverConditionCoverageHelper_C317;
        if ((((((CodeCoverConditionCoverageHelper_C317 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C317 |= (2)) == 0 || true) &&
 ((this.domainCrosshairLockedOnData
                != that.domainCrosshairLockedOnData) && 
  ((CodeCoverConditionCoverageHelper_C317 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[317].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C317, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[317].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C317, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[517]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[518]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1033]++;
int CodeCoverConditionCoverageHelper_C318;
        if ((((((CodeCoverConditionCoverageHelper_C318 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C318 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairVisible != that.rangeCrosshairVisible) && 
  ((CodeCoverConditionCoverageHelper_C318 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[318].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C318, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[318].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C318, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[519]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[520]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1034]++;
int CodeCoverConditionCoverageHelper_C319;
        if ((((((CodeCoverConditionCoverageHelper_C319 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C319 |= (2)) == 0 || true) &&
 ((this.rangeCrosshairValue != that.rangeCrosshairValue) && 
  ((CodeCoverConditionCoverageHelper_C319 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[319].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C319, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[319].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C319, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[521]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[522]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1035]++;
int CodeCoverConditionCoverageHelper_C320;
        if ((((((CodeCoverConditionCoverageHelper_C320 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C320 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.axisOffset, that.axisOffset)) && 
  ((CodeCoverConditionCoverageHelper_C320 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[320].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C320, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[320].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C320, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[523]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[524]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1036]++;
int CodeCoverConditionCoverageHelper_C321;
        if ((((((CodeCoverConditionCoverageHelper_C321 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C321 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.renderers, that.renderers)) && 
  ((CodeCoverConditionCoverageHelper_C321 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[321].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C321, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[321].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C321, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[525]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[526]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1037]++;
int CodeCoverConditionCoverageHelper_C322;
        if ((((((CodeCoverConditionCoverageHelper_C322 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C322 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rangeAxes, that.rangeAxes)) && 
  ((CodeCoverConditionCoverageHelper_C322 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[322].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C322, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[322].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C322, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[527]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[528]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1038]++;
int CodeCoverConditionCoverageHelper_C323;
        if ((((((CodeCoverConditionCoverageHelper_C323 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C323 |= (2)) == 0 || true) &&
 ((this.rangeAxisLocations.equals(that.rangeAxisLocations)) && 
  ((CodeCoverConditionCoverageHelper_C323 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[323].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C323, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[323].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C323, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[529]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[530]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1039]++;
int CodeCoverConditionCoverageHelper_C324;
        if ((((((CodeCoverConditionCoverageHelper_C324 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C324 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.datasetToDomainAxisMap, 
                that.datasetToDomainAxisMap)) && 
  ((CodeCoverConditionCoverageHelper_C324 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[324].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C324, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[324].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C324, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[531]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[532]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1040]++;
int CodeCoverConditionCoverageHelper_C325;
        if ((((((CodeCoverConditionCoverageHelper_C325 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C325 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.datasetToRangeAxisMap, 
                that.datasetToRangeAxisMap)) && 
  ((CodeCoverConditionCoverageHelper_C325 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[325].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C325, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[325].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C325, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[533]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[534]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1041]++;
int CodeCoverConditionCoverageHelper_C326;
        if ((((((CodeCoverConditionCoverageHelper_C326 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C326 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.domainGridlineStroke, 
                that.domainGridlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C326 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[326].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C326, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[326].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C326, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[535]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[536]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1042]++;
int CodeCoverConditionCoverageHelper_C327;
        if ((((((CodeCoverConditionCoverageHelper_C327 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C327 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.domainGridlinePaint, 
                that.domainGridlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C327 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[327].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C327, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[327].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C327, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[537]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[538]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1043]++;
int CodeCoverConditionCoverageHelper_C328;
        if ((((((CodeCoverConditionCoverageHelper_C328 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C328 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rangeGridlineStroke, 
                that.rangeGridlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C328 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[328].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C328, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[328].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C328, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[539]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[540]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1044]++;
int CodeCoverConditionCoverageHelper_C329;
        if ((((((CodeCoverConditionCoverageHelper_C329 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C329 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.rangeGridlinePaint, 
                that.rangeGridlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C329 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[329].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C329, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[329].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C329, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[541]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[542]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1045]++;
int CodeCoverConditionCoverageHelper_C330;
        if ((((((CodeCoverConditionCoverageHelper_C330 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C330 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.domainZeroBaselinePaint, 
                that.domainZeroBaselinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C330 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[330].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C330, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[330].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C330, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[543]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[544]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1046]++;
int CodeCoverConditionCoverageHelper_C331;
        if ((((((CodeCoverConditionCoverageHelper_C331 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C331 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.domainZeroBaselineStroke, 
                that.domainZeroBaselineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C331 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[331].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C331, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[331].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C331, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[545]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[546]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1047]++;
int CodeCoverConditionCoverageHelper_C332;
        if ((((((CodeCoverConditionCoverageHelper_C332 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C332 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.rangeZeroBaselinePaint, 
                that.rangeZeroBaselinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C332 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[332].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C332, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[332].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C332, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[547]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[548]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1048]++;
int CodeCoverConditionCoverageHelper_C333;
        if ((((((CodeCoverConditionCoverageHelper_C333 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C333 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rangeZeroBaselineStroke, 
                that.rangeZeroBaselineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C333 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[333].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C333, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[333].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C333, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[549]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[550]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1049]++;
int CodeCoverConditionCoverageHelper_C334;
        if ((((((CodeCoverConditionCoverageHelper_C334 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C334 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.domainCrosshairStroke, 
                that.domainCrosshairStroke)) && 
  ((CodeCoverConditionCoverageHelper_C334 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[334].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C334, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[334].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C334, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[551]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[552]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1050]++;
int CodeCoverConditionCoverageHelper_C335;
        if ((((((CodeCoverConditionCoverageHelper_C335 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C335 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.domainCrosshairPaint, 
                that.domainCrosshairPaint)) && 
  ((CodeCoverConditionCoverageHelper_C335 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[335].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C335, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[335].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C335, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[553]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[554]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1051]++;
int CodeCoverConditionCoverageHelper_C336;
        if ((((((CodeCoverConditionCoverageHelper_C336 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C336 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rangeCrosshairStroke, 
                that.rangeCrosshairStroke)) && 
  ((CodeCoverConditionCoverageHelper_C336 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[336].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C336, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[336].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C336, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[555]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[556]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1052]++;
int CodeCoverConditionCoverageHelper_C337;
        if ((((((CodeCoverConditionCoverageHelper_C337 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C337 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.rangeCrosshairPaint, 
                that.rangeCrosshairPaint)) && 
  ((CodeCoverConditionCoverageHelper_C337 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[337].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C337, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[337].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C337, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[557]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[558]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1053]++;
int CodeCoverConditionCoverageHelper_C338;
        if ((((((CodeCoverConditionCoverageHelper_C338 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C338 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.foregroundDomainMarkers, 
                that.foregroundDomainMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C338 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[338].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C338, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[338].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C338, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[559]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[560]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1054]++;
int CodeCoverConditionCoverageHelper_C339;
        if ((((((CodeCoverConditionCoverageHelper_C339 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C339 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.backgroundDomainMarkers, 
                that.backgroundDomainMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C339 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[339].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C339, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[339].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C339, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[561]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[562]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1055]++;
int CodeCoverConditionCoverageHelper_C340;
        if ((((((CodeCoverConditionCoverageHelper_C340 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C340 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.foregroundRangeMarkers, 
                that.foregroundRangeMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C340 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[340].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C340, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[340].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C340, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[563]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[564]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1056]++;
int CodeCoverConditionCoverageHelper_C341;
        if ((((((CodeCoverConditionCoverageHelper_C341 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C341 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.backgroundRangeMarkers, 
                that.backgroundRangeMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C341 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[341].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C341, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[341].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C341, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[565]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[566]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1057]++;
int CodeCoverConditionCoverageHelper_C342;
        if ((((((CodeCoverConditionCoverageHelper_C342 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C342 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.foregroundDomainMarkers, 
                that.foregroundDomainMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C342 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[342].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C342, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[342].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C342, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[567]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[568]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1058]++;
int CodeCoverConditionCoverageHelper_C343;
        if ((((((CodeCoverConditionCoverageHelper_C343 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C343 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.backgroundDomainMarkers, 
                that.backgroundDomainMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C343 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[343].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C343, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[343].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C343, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[569]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[570]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1059]++;
int CodeCoverConditionCoverageHelper_C344;
        if ((((((CodeCoverConditionCoverageHelper_C344 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C344 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.foregroundRangeMarkers, 
                that.foregroundRangeMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C344 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[344].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C344, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[344].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C344, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[571]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[572]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1060]++;
int CodeCoverConditionCoverageHelper_C345;
        if ((((((CodeCoverConditionCoverageHelper_C345 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C345 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.backgroundRangeMarkers, 
                that.backgroundRangeMarkers)) && 
  ((CodeCoverConditionCoverageHelper_C345 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[345].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C345, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[345].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C345, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[573]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[574]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1061]++;
int CodeCoverConditionCoverageHelper_C346;
        if ((((((CodeCoverConditionCoverageHelper_C346 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C346 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.annotations, that.annotations)) && 
  ((CodeCoverConditionCoverageHelper_C346 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[346].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C346, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[346].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C346, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[575]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[576]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1062]++;
int CodeCoverConditionCoverageHelper_C347;
        if ((((((CodeCoverConditionCoverageHelper_C347 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C347 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.domainTickBandPaint, 
                that.domainTickBandPaint)) && 
  ((CodeCoverConditionCoverageHelper_C347 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[347].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C347, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[347].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C347, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[577]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[578]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1063]++;
int CodeCoverConditionCoverageHelper_C348;
        if ((((((CodeCoverConditionCoverageHelper_C348 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C348 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.rangeTickBandPaint, 
                that.rangeTickBandPaint)) && 
  ((CodeCoverConditionCoverageHelper_C348 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[348].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C348, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[348].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C348, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[579]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[580]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1064]++;
int CodeCoverConditionCoverageHelper_C349;
        if ((((((CodeCoverConditionCoverageHelper_C349 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C349 |= (2)) == 0 || true) &&
 ((this.quadrantOrigin.equals(that.quadrantOrigin)) && 
  ((CodeCoverConditionCoverageHelper_C349 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[349].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C349, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[349].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C349, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[581]++;
            return false;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[582]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1065]++;
byte CodeCoverTryBranchHelper_L59 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[175]++;


int CodeCoverConditionCoverageHelper_C350;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C350 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C350 |= (2)) == 0 || true) &&
 ((i < 4) && 
  ((CodeCoverConditionCoverageHelper_C350 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[350].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C350, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[350].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C350, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L59 == 0) {
  CodeCoverTryBranchHelper_L59++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[175]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[176]++;
} else if (CodeCoverTryBranchHelper_L59 == 1) {
  CodeCoverTryBranchHelper_L59++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[176]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[177]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1066]++;
int CodeCoverConditionCoverageHelper_C351;
            if ((((((CodeCoverConditionCoverageHelper_C351 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C351 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.quadrantPaint[i], 
                    that.quadrantPaint[i])) && 
  ((CodeCoverConditionCoverageHelper_C351 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[351].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C351, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[351].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C351, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[583]++;
                return false;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[584]++;}
        }
        return super.equals(obj);
    }

    /**
     * Returns a clone of the plot.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  this can occur if some component of
     *         the plot cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1067]++;

        XYPlot clone = (XYPlot) super.clone();
        clone.domainAxes = (ObjectList) ObjectUtilities.clone(this.domainAxes);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1068]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1069]++;
byte CodeCoverTryBranchHelper_L60 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[178]++;


int CodeCoverConditionCoverageHelper_C352;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C352 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C352 |= (2)) == 0 || true) &&
 ((i < this.domainAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C352 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[352].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C352, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[352].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C352, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L60 == 0) {
  CodeCoverTryBranchHelper_L60++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[178]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[179]++;
} else if (CodeCoverTryBranchHelper_L60 == 1) {
  CodeCoverTryBranchHelper_L60++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[179]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[180]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1070]++;
            ValueAxis axis = (ValueAxis) this.domainAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1071]++;
int CodeCoverConditionCoverageHelper_C353;
            if ((((((CodeCoverConditionCoverageHelper_C353 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C353 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C353 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[353].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C353, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[353].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C353, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[585]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1072]++;
                ValueAxis clonedAxis = (ValueAxis) axis.clone();
                clone.domainAxes.set(i, clonedAxis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1073]++;
                clonedAxis.setPlot(clone);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1074]++;
                clonedAxis.addChangeListener(clone);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1075]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[586]++;}
        }
        clone.domainAxisLocations = (ObjectList) 
                this.domainAxisLocations.clone();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1076]++;

        clone.rangeAxes = (ObjectList) ObjectUtilities.clone(this.rangeAxes);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1077]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1078]++;
byte CodeCoverTryBranchHelper_L61 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[181]++;


int CodeCoverConditionCoverageHelper_C354;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C354 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C354 |= (2)) == 0 || true) &&
 ((i < this.rangeAxes.size()) && 
  ((CodeCoverConditionCoverageHelper_C354 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[354].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C354, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[354].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C354, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L61 == 0) {
  CodeCoverTryBranchHelper_L61++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[181]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[182]++;
} else if (CodeCoverTryBranchHelper_L61 == 1) {
  CodeCoverTryBranchHelper_L61++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[182]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[183]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1079]++;
            ValueAxis axis = (ValueAxis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1080]++;
int CodeCoverConditionCoverageHelper_C355;
            if ((((((CodeCoverConditionCoverageHelper_C355 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C355 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C355 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[355].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C355, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[355].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C355, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[587]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1081]++;
                ValueAxis clonedAxis = (ValueAxis) axis.clone();
                clone.rangeAxes.set(i, clonedAxis);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1082]++;
                clonedAxis.setPlot(clone);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1083]++;
                clonedAxis.addChangeListener(clone);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1084]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[588]++;}
        }
        clone.rangeAxisLocations = (ObjectList) ObjectUtilities.clone(
                this.rangeAxisLocations);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1085]++;

        // the datasets are not cloned, but listeners need to be added...
        clone.datasets = (ObjectList) ObjectUtilities.clone(this.datasets);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1086]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1087]++;
byte CodeCoverTryBranchHelper_L62 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[184]++;


int CodeCoverConditionCoverageHelper_C356;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C356 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C356 |= (2)) == 0 || true) &&
 ((i < clone.datasets.size()) && 
  ((CodeCoverConditionCoverageHelper_C356 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[356].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C356, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[356].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C356, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L62 == 0) {
  CodeCoverTryBranchHelper_L62++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[184]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[185]++;
} else if (CodeCoverTryBranchHelper_L62 == 1) {
  CodeCoverTryBranchHelper_L62++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[185]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[186]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1088]++;
            XYDataset d = getDataset(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1089]++;
int CodeCoverConditionCoverageHelper_C357;
            if ((((((CodeCoverConditionCoverageHelper_C357 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C357 |= (2)) == 0 || true) &&
 ((d != null) && 
  ((CodeCoverConditionCoverageHelper_C357 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[357].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C357, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[357].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C357, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[589]++;
                d.addChangeListener(clone);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1090]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[590]++;}
        }

        clone.datasetToDomainAxisMap = new TreeMap();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1091]++;
        clone.datasetToDomainAxisMap.putAll(this.datasetToDomainAxisMap);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1092]++;
        clone.datasetToRangeAxisMap = new TreeMap();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1093]++;
        clone.datasetToRangeAxisMap.putAll(this.datasetToRangeAxisMap);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1094]++;

        clone.renderers = (ObjectList) ObjectUtilities.clone(this.renderers);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1095]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1096]++;
byte CodeCoverTryBranchHelper_L63 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[187]++;


int CodeCoverConditionCoverageHelper_C358;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C358 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C358 |= (2)) == 0 || true) &&
 ((i < this.renderers.size()) && 
  ((CodeCoverConditionCoverageHelper_C358 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[358].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C358, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[358].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C358, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L63 == 0) {
  CodeCoverTryBranchHelper_L63++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[187]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[188]++;
} else if (CodeCoverTryBranchHelper_L63 == 1) {
  CodeCoverTryBranchHelper_L63++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[188]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[189]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1097]++;
            XYItemRenderer renderer2 = (XYItemRenderer) this.renderers.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1098]++;
int CodeCoverConditionCoverageHelper_C359;
            if ((((((CodeCoverConditionCoverageHelper_C359 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C359 |= (2)) == 0 || true) &&
 ((renderer2 instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C359 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[359].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C359, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[359].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C359, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[591]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1099]++;
                PublicCloneable pc = (PublicCloneable) renderer2;
                clone.renderers.set(i, pc.clone());
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1100]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[592]++;}
        }
        clone.foregroundDomainMarkers = (Map) ObjectUtilities.clone(
                this.foregroundDomainMarkers);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1101]++;
        clone.backgroundDomainMarkers = (Map) ObjectUtilities.clone(
                this.backgroundDomainMarkers);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1102]++;
        clone.foregroundRangeMarkers = (Map) ObjectUtilities.clone(
                this.foregroundRangeMarkers);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1103]++;
        clone.backgroundRangeMarkers = (Map) ObjectUtilities.clone(
                this.backgroundRangeMarkers);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1104]++;
        clone.annotations = (List) ObjectUtilities.deepClone(this.annotations);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1105]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1106]++;
int CodeCoverConditionCoverageHelper_C360;
        if ((((((CodeCoverConditionCoverageHelper_C360 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C360 |= (2)) == 0 || true) &&
 ((this.fixedDomainAxisSpace != null) && 
  ((CodeCoverConditionCoverageHelper_C360 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[360].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C360, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[360].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C360, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[593]++;
            clone.fixedDomainAxisSpace = (AxisSpace) ObjectUtilities.clone(
                    this.fixedDomainAxisSpace);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1107]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[594]++;}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1108]++;
int CodeCoverConditionCoverageHelper_C361;
        if ((((((CodeCoverConditionCoverageHelper_C361 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C361 |= (2)) == 0 || true) &&
 ((this.fixedRangeAxisSpace != null) && 
  ((CodeCoverConditionCoverageHelper_C361 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[361].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C361, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[361].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C361, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[595]++;
            clone.fixedRangeAxisSpace = (AxisSpace) ObjectUtilities.clone(
                    this.fixedRangeAxisSpace);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1109]++;

        } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[596]++;}

        clone.quadrantOrigin = (Point2D) ObjectUtilities.clone(
                this.quadrantOrigin);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1110]++;
        clone.quadrantPaint = (Paint[]) this.quadrantPaint.clone();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1111]++;
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1112]++;
        SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1113]++;
        SerialUtilities.writePaint(this.domainGridlinePaint, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1114]++;
        SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1115]++;
        SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1116]++;
        SerialUtilities.writeStroke(this.rangeZeroBaselineStroke, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1117]++;
        SerialUtilities.writePaint(this.rangeZeroBaselinePaint, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1118]++;
        SerialUtilities.writeStroke(this.domainCrosshairStroke, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1119]++;
        SerialUtilities.writePaint(this.domainCrosshairPaint, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1120]++;
        SerialUtilities.writeStroke(this.rangeCrosshairStroke, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1121]++;
        SerialUtilities.writePaint(this.rangeCrosshairPaint, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1122]++;
        SerialUtilities.writePaint(this.domainTickBandPaint, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1123]++;
        SerialUtilities.writePaint(this.rangeTickBandPaint, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1124]++;
        SerialUtilities.writePoint2D(this.quadrantOrigin, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1125]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1126]++;
byte CodeCoverTryBranchHelper_L64 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[190]++;


int CodeCoverConditionCoverageHelper_C362;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C362 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C362 |= (2)) == 0 || true) &&
 ((i < 4) && 
  ((CodeCoverConditionCoverageHelper_C362 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[362].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C362, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[362].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C362, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L64 == 0) {
  CodeCoverTryBranchHelper_L64++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[190]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[191]++;
} else if (CodeCoverTryBranchHelper_L64 == 1) {
  CodeCoverTryBranchHelper_L64++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[191]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[192]++;
}
            SerialUtilities.writePaint(this.quadrantPaint[i], stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1127]++;
        }
        SerialUtilities.writeStroke(this.domainZeroBaselineStroke, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1128]++;
        SerialUtilities.writePaint(this.domainZeroBaselinePaint, stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1129]++;
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
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1130]++;
        this.domainGridlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1131]++;
        this.domainGridlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1132]++;
        this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1133]++;
        this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1134]++;
        this.rangeZeroBaselineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1135]++;
        this.rangeZeroBaselinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1136]++;
        this.domainCrosshairStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1137]++;
        this.domainCrosshairPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1138]++;
        this.rangeCrosshairStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1139]++;
        this.rangeCrosshairPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1140]++;
        this.domainTickBandPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1141]++;
        this.rangeTickBandPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1142]++;
        this.quadrantOrigin = SerialUtilities.readPoint2D(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1143]++;
        this.quadrantPaint = new Paint[4];
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1144]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1145]++;
byte CodeCoverTryBranchHelper_L65 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[193]++;


int CodeCoverConditionCoverageHelper_C363;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C363 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C363 |= (2)) == 0 || true) &&
 ((i < 4) && 
  ((CodeCoverConditionCoverageHelper_C363 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[363].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C363, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[363].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C363, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L65 == 0) {
  CodeCoverTryBranchHelper_L65++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[193]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[194]++;
} else if (CodeCoverTryBranchHelper_L65 == 1) {
  CodeCoverTryBranchHelper_L65++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[194]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[195]++;
}
            this.quadrantPaint[i] = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1146]++;
        }

        this.domainZeroBaselineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1147]++;
        this.domainZeroBaselinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1148]++;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1149]++;

        // register the plot as a listener with its axes, datasets, and 
        // renderers...
        int domainAxisCount = this.domainAxes.size();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1150]++;
byte CodeCoverTryBranchHelper_L66 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[196]++;


int CodeCoverConditionCoverageHelper_C364;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C364 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C364 |= (2)) == 0 || true) &&
 ((i < domainAxisCount) && 
  ((CodeCoverConditionCoverageHelper_C364 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[364].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C364, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[364].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C364, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L66 == 0) {
  CodeCoverTryBranchHelper_L66++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[196]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[197]++;
} else if (CodeCoverTryBranchHelper_L66 == 1) {
  CodeCoverTryBranchHelper_L66++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[197]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[198]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1151]++;
            Axis axis = (Axis) this.domainAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1152]++;
int CodeCoverConditionCoverageHelper_C365;
            if ((((((CodeCoverConditionCoverageHelper_C365 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C365 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C365 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[365].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C365, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[365].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C365, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[597]++;
                axis.setPlot(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1153]++;
                axis.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1154]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[598]++;}
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1155]++;
        int rangeAxisCount = this.rangeAxes.size();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1156]++;
byte CodeCoverTryBranchHelper_L67 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[199]++;


int CodeCoverConditionCoverageHelper_C366;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C366 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C366 |= (2)) == 0 || true) &&
 ((i < rangeAxisCount) && 
  ((CodeCoverConditionCoverageHelper_C366 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[366].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C366, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[366].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C366, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L67 == 0) {
  CodeCoverTryBranchHelper_L67++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[199]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[200]++;
} else if (CodeCoverTryBranchHelper_L67 == 1) {
  CodeCoverTryBranchHelper_L67++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[200]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[201]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1157]++;
            Axis axis = (Axis) this.rangeAxes.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1158]++;
int CodeCoverConditionCoverageHelper_C367;
            if ((((((CodeCoverConditionCoverageHelper_C367 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C367 |= (2)) == 0 || true) &&
 ((axis != null) && 
  ((CodeCoverConditionCoverageHelper_C367 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[367].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C367, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[367].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C367, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[599]++;
                axis.setPlot(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1159]++;
                axis.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1160]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[600]++;}
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1161]++;
        int datasetCount = this.datasets.size();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1162]++;
byte CodeCoverTryBranchHelper_L68 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[202]++;


int CodeCoverConditionCoverageHelper_C368;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C368 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C368 |= (2)) == 0 || true) &&
 ((i < datasetCount) && 
  ((CodeCoverConditionCoverageHelper_C368 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[368].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C368, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[368].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C368, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L68 == 0) {
  CodeCoverTryBranchHelper_L68++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[202]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[203]++;
} else if (CodeCoverTryBranchHelper_L68 == 1) {
  CodeCoverTryBranchHelper_L68++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[203]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[204]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1163]++;
            Dataset dataset = (Dataset) this.datasets.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1164]++;
int CodeCoverConditionCoverageHelper_C369;
            if ((((((CodeCoverConditionCoverageHelper_C369 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C369 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C369 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[369].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C369, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[369].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C369, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[601]++;
                dataset.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1165]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[602]++;}
        }
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1166]++;
        int rendererCount = this.renderers.size();
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1167]++;
byte CodeCoverTryBranchHelper_L69 = 0;
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[205]++;


int CodeCoverConditionCoverageHelper_C370;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C370 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C370 |= (2)) == 0 || true) &&
 ((i < rendererCount) && 
  ((CodeCoverConditionCoverageHelper_C370 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[370].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C370, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[370].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C370, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L69 == 0) {
  CodeCoverTryBranchHelper_L69++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[205]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[206]++;
} else if (CodeCoverTryBranchHelper_L69 == 1) {
  CodeCoverTryBranchHelper_L69++;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[206]--;
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.loops[207]++;
}
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1168]++;
            XYItemRenderer renderer = (XYItemRenderer) this.renderers.get(i);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1169]++;
int CodeCoverConditionCoverageHelper_C371;
            if ((((((CodeCoverConditionCoverageHelper_C371 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C371 |= (2)) == 0 || true) &&
 ((renderer != null) && 
  ((CodeCoverConditionCoverageHelper_C371 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[371].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C371, 1) || true)) || (CodeCoverCoverageCounter$df4nbsvexn1gr4swx.conditionCounters[371].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C371, 1) && false)) {
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[603]++;
                renderer.addChangeListener(this);
CodeCoverCoverageCounter$df4nbsvexn1gr4swx.statements[1170]++;

            } else {
  CodeCoverCoverageCounter$df4nbsvexn1gr4swx.branches[604]++;}
        }
    
    }

}

class CodeCoverCoverageCounter$df4nbsvexn1gr4swx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$df4nbsvexn1gr4swx ());
  }
    public static long[] statements = new long[1171];
    public static long[] branches = new long[605];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[372];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.XYPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 371; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[208];

  public CodeCoverCoverageCounter$df4nbsvexn1gr4swx () {
    super("org.jfree.chart.plot.XYPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1170; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 604; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 371; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 207; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.XYPlot.java");
      for (int i = 1; i <= 1170; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 604; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 371; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 69; i++) {
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

