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
 * ---------------
 * ChartPanel.java
 * ---------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Andrzej Porebski;
 *                   Soren Caspersen;
 *                   Jonathan Nash;
 *                   Hans-Jurgen Greiner;
 *                   Andreas Schneider;
 *                   Daniel van Enckevort;
 *                   David M O'Donnell;
 *                   Arnaud Lelievre;
 *                   Matthias Rose;
 *                   Onno vd Akker;
 *                   Sergei Ivanov;
 *
 * Changes (from 28-Jun-2001)
 * --------------------------
 * 28-Jun-2001 : Integrated buffering code contributed by S???ren 
 *               Caspersen (DG);
 * 18-Sep-2001 : Updated header and fixed DOS encoding problem (DG);
 * 22-Nov-2001 : Added scaling to improve display of charts in small sizes (DG);
 * 26-Nov-2001 : Added property editing, saving and printing (DG);
 * 11-Dec-2001 : Transferred saveChartAsPNG method to new ChartUtilities 
 *               class (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Added an optional crosshair, based on the implementation by 
 *               Jonathan Nash. Renamed the tooltips class (DG);
 * 23-Jan-2002 : Implemented zooming based on code by Hans-Jurgen Greiner (DG);
 * 05-Feb-2002 : Improved tooltips setup.  Renamed method attemptSaveAs() 
 *               --> doSaveAs() and made it public rather than private (DG);
 * 28-Mar-2002 : Added a new constructor (DG);
 * 09-Apr-2002 : Changed initialisation of tooltip generation, as suggested by 
 *               Hans-Jurgen Greiner (DG);
 * 27-May-2002 : New interactive zooming methods based on code by Hans-Jurgen 
 *               Greiner. Renamed JFreeChartPanel --> ChartPanel, moved 
 *               constants to ChartPanelConstants interface (DG);
 * 31-May-2002 : Fixed a bug with interactive zooming and added a way to 
 *               control if the zoom rectangle is filled in or drawn as an 
 *               outline. A mouse drag gesture towards the top left now causes 
 *               an autoRangeBoth() and is a way to undo zooms (AS);
 * 11-Jun-2002 : Reinstated handleClick method call in mouseClicked() to get 
 *               crosshairs working again (DG);
 * 13-Jun-2002 : Added check for null popup menu in mouseDragged method (DG);
 * 18-Jun-2002 : Added get/set methods for minimum and maximum chart 
 *               dimensions (DG);
 * 25-Jun-2002 : Removed redundant code (DG);
 * 27-Aug-2002 : Added get/set methods for popup menu (DG);
 * 26-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 22-Oct-2002 : Added translation methods for screen <--> Java2D, contributed
 *               by Daniel van Enckevort (DG);
 * 05-Nov-2002 : Added a chart reference to the ChartMouseEvent class (DG);
 * 22-Nov-2002 : Added test in zoom method for inverted axes, supplied by 
 *               David M O'Donnell (DG);
 * 14-Jan-2003 : Implemented ChartProgressListener interface (DG);
 * 14-Feb-2003 : Removed deprecated setGenerateTooltips method (DG);
 * 12-Mar-2003 : Added option to enforce filename extension (see bug id 
 *               643173) (DG);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL);
 * 18-Sep-2003 : Added getScaleX() and getScaleY() methods (protected) as 
 *               requested by Irv Thomae (DG);
 * 12-Nov-2003 : Added zooming support for the FastScatterPlot class (DG);
 * 24-Nov-2003 : Minor Javadoc updates (DG);
 * 04-Dec-2003 : Added anchor point for crosshair calculation (DG);
 * 17-Jan-2004 : Added new methods to set tooltip delays to be used in this 
 *               chart panel. Refer to patch 877565 (MR);
 * 02-Feb-2004 : Fixed bug in zooming trigger and added zoomTriggerDistance 
 *               attribute (DG);
 * 08-Apr-2004 : Changed getScaleX() and getScaleY() from protected to 
 *               public (DG);
 * 15-Apr-2004 : Added zoomOutFactor and zoomInFactor (DG);
 * 21-Apr-2004 : Fixed zooming bug in mouseReleased() method (DG);
 * 13-Jul-2004 : Added check for null chart (DG);
 * 04-Oct-2004 : Renamed ShapeUtils --> ShapeUtilities (DG); 
 * 11-Nov-2004 : Moved constants back in from ChartPanelConstants (DG);
 * 12-Nov-2004 : Modified zooming mechanism to support zooming within 
 *               subplots (DG);
 * 26-Jan-2005 : Fixed mouse zooming for horizontal category plots (DG);
 * 11-Apr-2005 : Added getFillZoomRectangle() method, renamed 
 *               setHorizontalZoom() --> setDomainZoomable(), 
 *               setVerticalZoom() --> setRangeZoomable(), added 
 *               isDomainZoomable() and isRangeZoomable(), added 
 *               getHorizontalAxisTrace() and getVerticalAxisTrace(),
 *               renamed autoRangeBoth() --> restoreAutoBounds(),
 *               autoRangeHorizontal() --> restoreAutoDomainBounds(),
 *               autoRangeVertical() --> restoreAutoRangeBounds() (DG);
 * 12-Apr-2005 : Removed working areas, added getAnchorPoint() method,
 *               added protected accessors for tracelines (DG);
 * 18-Apr-2005 : Made constants final (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 01-Jun-2005 : Fixed zooming for combined plots - see bug report 
 *               1212039, fix thanks to Onno vd Akker (DG);
 * 25-Nov-2005 : Reworked event listener mechanism (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 01-Aug-2006 : Fixed minor bug in restoreAutoRangeBounds() (DG);
 * 04-Sep-2006 : Renamed attemptEditChartProperties() --> 
 *               doEditChartProperties() and made public (DG);
 * 13-Sep-2006 : Don't generate ChartMouseEvents if the panel's chart is null
 *               (fixes bug 1556951) (DG);
 * 05-Mar-2007 : Applied patch 1672561 by Sergei Ivanov, to fix zoom rectangle
 *               drawing for dynamic charts (DG);
 * 17-Apr-2007 : Fix NullPointerExceptions in zooming for combined plots (DG);
 * 24-May-2007 : When the look-and-feel changes, update the popup menu if there 
 *               is one (DG);
 * 06-Jun-2007 : Fixed coordinates for drawing buffer image (DG);
 * 24-Sep-2007 : Added zoomAroundAnchor flag, and handle clearing of chart
 *               buffer (DG);
 * 25-Oct-2007 : Added default directory attribute (DG);
 * 07-Nov-2007 : Fixed (rare) bug in refreshing off-screen image (DG);
 *               
 */

package org.jfree.chart;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.EventListener;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.EventListenerList;

import org.jfree.chart.editor.ChartEditor;
import org.jfree.chart.editor.ChartEditorManager;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.Zoomable;
import org.jfree.ui.ExtensionFileFilter;

/**
 * A Swing GUI component for displaying a {@link JFreeChart} object.
 * <P>
 * The panel registers with the chart to receive notification of changes to any
 * component of the chart.  The chart is redrawn automatically whenever this 
 * notification is received.
 */
public class ChartPanel extends JPanel implements ChartChangeListener,
        ChartProgressListener, ActionListener, MouseListener, 
        MouseMotionListener, Printable, Serializable {
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 6046366297214274674L;
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[1]++;
  }
    
    /** Default setting for buffer usage. */
    public static final boolean DEFAULT_BUFFER_USED = false;
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[2]++;
  }

    /** The default panel width. */
    public static final int DEFAULT_WIDTH = 680;
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[3]++;
  }

    /** The default panel height. */
    public static final int DEFAULT_HEIGHT = 420;
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[4]++;
  }

    /** The default limit below which chart scaling kicks in. */
    public static final int DEFAULT_MINIMUM_DRAW_WIDTH = 300;
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[5]++;
  }

    /** The default limit below which chart scaling kicks in. */
    public static final int DEFAULT_MINIMUM_DRAW_HEIGHT = 200;
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[6]++;
  }

    /** The default limit below which chart scaling kicks in. */
    public static final int DEFAULT_MAXIMUM_DRAW_WIDTH = 800;
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[7]++;
  }

    /** The default limit below which chart scaling kicks in. */
    public static final int DEFAULT_MAXIMUM_DRAW_HEIGHT = 600;
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[8]++;
  }

    /** The minimum size required to perform a zoom on a rectangle */
    public static final int DEFAULT_ZOOM_TRIGGER_DISTANCE = 10;
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[9]++;
  }

    /** Properties action command. */
    public static final String PROPERTIES_COMMAND = "PROPERTIES";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[10]++;
  }

    /** Save action command. */
    public static final String SAVE_COMMAND = "SAVE";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[11]++;
  }

    /** Print action command. */
    public static final String PRINT_COMMAND = "PRINT";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[12]++;
  }

    /** Zoom in (both axes) action command. */
    public static final String ZOOM_IN_BOTH_COMMAND = "ZOOM_IN_BOTH";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[13]++;
  }

    /** Zoom in (domain axis only) action command. */
    public static final String ZOOM_IN_DOMAIN_COMMAND = "ZOOM_IN_DOMAIN";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[14]++;
  }

    /** Zoom in (range axis only) action command. */
    public static final String ZOOM_IN_RANGE_COMMAND = "ZOOM_IN_RANGE";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[15]++;
  }

    /** Zoom out (both axes) action command. */
    public static final String ZOOM_OUT_BOTH_COMMAND = "ZOOM_OUT_BOTH";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[16]++;
  }

    /** Zoom out (domain axis only) action command. */
    public static final String ZOOM_OUT_DOMAIN_COMMAND = "ZOOM_DOMAIN_BOTH";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[17]++;
  }

    /** Zoom out (range axis only) action command. */
    public static final String ZOOM_OUT_RANGE_COMMAND = "ZOOM_RANGE_BOTH";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[18]++;
  }

    /** Zoom reset (both axes) action command. */
    public static final String ZOOM_RESET_BOTH_COMMAND = "ZOOM_RESET_BOTH";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[19]++;
  }

    /** Zoom reset (domain axis only) action command. */
    public static final String ZOOM_RESET_DOMAIN_COMMAND = "ZOOM_RESET_DOMAIN";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[20]++;
  }

    /** Zoom reset (range axis only) action command. */
    public static final String ZOOM_RESET_RANGE_COMMAND = "ZOOM_RESET_RANGE";
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[21]++;
  }

    /** The chart that is displayed in the panel. */
    private JFreeChart chart;

    /** Storage for registered (chart) mouse listeners. */
    private EventListenerList chartMouseListeners;

    /** A flag that controls whether or not the off-screen buffer is used. */
    private boolean useBuffer;

    /** A flag that indicates that the buffer should be refreshed. */
    private boolean refreshBuffer;

    /** A buffer for the rendered chart. */
    private Image chartBuffer;

    /** The height of the chart buffer. */
    private int chartBufferHeight;

    /** The width of the chart buffer. */
    private int chartBufferWidth;

    /** 
     * The minimum width for drawing a chart (uses scaling for smaller widths). 
     */
    private int minimumDrawWidth;

    /** 
     * The minimum height for drawing a chart (uses scaling for smaller 
     * heights). 
     */
    private int minimumDrawHeight;

    /** 
     * The maximum width for drawing a chart (uses scaling for bigger 
     * widths). 
     */
    private int maximumDrawWidth;

    /** 
     * The maximum height for drawing a chart (uses scaling for bigger 
     * heights). 
     */
    private int maximumDrawHeight;

    /** The popup menu for the frame. */
    private JPopupMenu popup;

    /** The drawing info collected the last time the chart was drawn. */
    private ChartRenderingInfo info;
    
    /** The chart anchor point. */
    private Point2D anchor;

    /** The scale factor used to draw the chart. */
    private double scaleX;

    /** The scale factor used to draw the chart. */
    private double scaleY;

    /** The plot orientation. */
    private PlotOrientation orientation = PlotOrientation.VERTICAL;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[22]++;
  }
    
    /** A flag that controls whether or not domain zooming is enabled. */
    private boolean domainZoomable = false;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[23]++;
  }

    /** A flag that controls whether or not range zooming is enabled. */
    private boolean rangeZoomable = false;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[24]++;
  }

    /** 
     * The zoom rectangle starting point (selected by the user with a mouse 
     * click).  This is a point on the screen, not the chart (which may have
     * been scaled up or down to fit the panel).  
     */
    private Point zoomPoint = null;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[25]++;
  }

    /** The zoom rectangle (selected by the user with the mouse). */
    private transient Rectangle2D zoomRectangle = null;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[26]++;
  }

    /** Controls if the zoom rectangle is drawn as an outline or filled. */
    private boolean fillZoomRectangle = false;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[27]++;
  }

    /** The minimum distance required to drag the mouse to trigger a zoom. */
    private int zoomTriggerDistance;
    
    /** A flag that controls whether or not horizontal tracing is enabled. */
    private boolean horizontalAxisTrace = false;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[28]++;
  }

    /** A flag that controls whether or not vertical tracing is enabled. */
    private boolean verticalAxisTrace = false;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[29]++;
  }

    /** A vertical trace line. */
    private transient Line2D verticalTraceLine;

    /** A horizontal trace line. */
    private transient Line2D horizontalTraceLine;

    /** Menu item for zooming in on a chart (both axes). */
    private JMenuItem zoomInBothMenuItem;

    /** Menu item for zooming in on a chart (domain axis). */
    private JMenuItem zoomInDomainMenuItem;

    /** Menu item for zooming in on a chart (range axis). */
    private JMenuItem zoomInRangeMenuItem;

    /** Menu item for zooming out on a chart. */
    private JMenuItem zoomOutBothMenuItem;

    /** Menu item for zooming out on a chart (domain axis). */
    private JMenuItem zoomOutDomainMenuItem;

    /** Menu item for zooming out on a chart (range axis). */
    private JMenuItem zoomOutRangeMenuItem;

    /** Menu item for resetting the zoom (both axes). */
    private JMenuItem zoomResetBothMenuItem;

    /** Menu item for resetting the zoom (domain axis only). */
    private JMenuItem zoomResetDomainMenuItem;

    /** Menu item for resetting the zoom (range axis only). */
    private JMenuItem zoomResetRangeMenuItem;

    /**
     * The default directory for saving charts to file.
     * 
     * @since 1.0.7
     */
    private File defaultDirectoryForSaveAs;
    
    /** A flag that controls whether or not file extensions are enforced. */
    private boolean enforceFileExtensions;

    /** A flag that indicates if original tooltip delays are changed. */
    private boolean ownToolTipDelaysActive;  
    
    /** Original initial tooltip delay of ToolTipManager.sharedInstance(). */
    private int originalToolTipInitialDelay;

    /** Original reshow tooltip delay of ToolTipManager.sharedInstance(). */
    private int originalToolTipReshowDelay;  

    /** Original dismiss tooltip delay of ToolTipManager.sharedInstance(). */
    private int originalToolTipDismissDelay;

    /** Own initial tooltip delay to be used in this chart panel. */
    private int ownToolTipInitialDelay;
    
    /** Own reshow tooltip delay to be used in this chart panel. */
    private int ownToolTipReshowDelay;  

    /** Own dismiss tooltip delay to be used in this chart panel. */
    private int ownToolTipDismissDelay;    

    /** The factor used to zoom in on an axis range. */
    private double zoomInFactor = 0.5;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[30]++;
  }
    
    /** The factor used to zoom out on an axis range. */
    private double zoomOutFactor = 2.0;
  {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[31]++;
  }
    
    /**
     * A flag that controls whether zoom operations are centred on the
     * current anchor point, or the centre point of the relevant axis.
     *
     * @since 1.0.7
     */
    private boolean zoomAroundAnchor;
    
    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources 
            = ResourceBundle.getBundle("org.jfree.chart.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[32]++;
  }

    /**
     * Constructs a panel that displays the specified chart.
     *
     * @param chart  the chart.
     */
    public ChartPanel(JFreeChart chart) {

        this(
            chart,
            DEFAULT_WIDTH,
            DEFAULT_HEIGHT,
            DEFAULT_MINIMUM_DRAW_WIDTH,
            DEFAULT_MINIMUM_DRAW_HEIGHT,
            DEFAULT_MAXIMUM_DRAW_WIDTH,
            DEFAULT_MAXIMUM_DRAW_HEIGHT,
            DEFAULT_BUFFER_USED,
            true,  // properties
            true,  // save
            true,  // print
            true,  // zoom
            true   // tooltips
        );
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[33]++;

    }

    /**
     * Constructs a panel containing a chart.
     *
     * @param chart  the chart.
     * @param useBuffer  a flag controlling whether or not an off-screen buffer
     *                   is used.
     */
    public ChartPanel(JFreeChart chart, boolean useBuffer) {

        this(chart,
             DEFAULT_WIDTH,
             DEFAULT_HEIGHT,
             DEFAULT_MINIMUM_DRAW_WIDTH,
             DEFAULT_MINIMUM_DRAW_HEIGHT,
             DEFAULT_MAXIMUM_DRAW_WIDTH,
             DEFAULT_MAXIMUM_DRAW_HEIGHT,
             useBuffer,
             true,  // properties
             true,  // save
             true,  // print
             true,  // zoom
             true   // tooltips
             );
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[34]++;

    }

    /**
     * Constructs a JFreeChart panel.
     *
     * @param chart  the chart.
     * @param properties  a flag indicating whether or not the chart property
     *                    editor should be available via the popup menu.
     * @param save  a flag indicating whether or not save options should be
     *              available via the popup menu.
     * @param print  a flag indicating whether or not the print option
     *               should be available via the popup menu.
     * @param zoom  a flag indicating whether or not zoom options should
     *              be added to the popup menu.
     * @param tooltips  a flag indicating whether or not tooltips should be
     *                  enabled for the chart.
     */
    public ChartPanel(JFreeChart chart,
                      boolean properties,
                      boolean save,
                      boolean print,
                      boolean zoom,
                      boolean tooltips) {

        this(chart,
             DEFAULT_WIDTH,
             DEFAULT_HEIGHT,
             DEFAULT_MINIMUM_DRAW_WIDTH,
             DEFAULT_MINIMUM_DRAW_HEIGHT,
             DEFAULT_MAXIMUM_DRAW_WIDTH,
             DEFAULT_MAXIMUM_DRAW_HEIGHT,
             DEFAULT_BUFFER_USED,
             properties,
             save,
             print,
             zoom,
             tooltips
             );
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[35]++;

    }

    /**
     * Constructs a JFreeChart panel.
     *
     * @param chart  the chart.
     * @param width  the preferred width of the panel.
     * @param height  the preferred height of the panel.
     * @param minimumDrawWidth  the minimum drawing width.
     * @param minimumDrawHeight  the minimum drawing height.
     * @param maximumDrawWidth  the maximum drawing width.
     * @param maximumDrawHeight  the maximum drawing height.
     * @param useBuffer  a flag that indicates whether to use the off-screen
     *                   buffer to improve performance (at the expense of 
     *                   memory).
     * @param properties  a flag indicating whether or not the chart property
     *                    editor should be available via the popup menu.
     * @param save  a flag indicating whether or not save options should be
     *              available via the popup menu.
     * @param print  a flag indicating whether or not the print option
     *               should be available via the popup menu.
     * @param zoom  a flag indicating whether or not zoom options should be 
     *              added to the popup menu.
     * @param tooltips  a flag indicating whether or not tooltips should be 
     *                  enabled for the chart.
     */
    public ChartPanel(JFreeChart chart,
                      int width,
                      int height,
                      int minimumDrawWidth,
                      int minimumDrawHeight,
                      int maximumDrawWidth,
                      int maximumDrawHeight,
                      boolean useBuffer,
                      boolean properties,
                      boolean save,
                      boolean print,
                      boolean zoom,
                      boolean tooltips) {

        this.setChart(chart);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[36]++;
        this.chartMouseListeners = new EventListenerList();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[37]++;
        this.info = new ChartRenderingInfo();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[38]++;
        setPreferredSize(new Dimension(width, height));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[39]++;
        this.useBuffer = useBuffer;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[40]++;
        this.refreshBuffer = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[41]++;
        this.minimumDrawWidth = minimumDrawWidth;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[42]++;
        this.minimumDrawHeight = minimumDrawHeight;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[43]++;
        this.maximumDrawWidth = maximumDrawWidth;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[44]++;
        this.maximumDrawHeight = maximumDrawHeight;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[45]++;
        this.zoomTriggerDistance = DEFAULT_ZOOM_TRIGGER_DISTANCE;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[46]++;

        // set up popup menu...
        this.popup = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[47]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[48]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((properties) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((save) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((print) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zoom) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[1]++;
            this.popup = createPopupMenu(properties, save, print, zoom);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[49]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[2]++;}

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[50]++;
        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[51]++;
        setDisplayToolTips(tooltips);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[52]++;
        addMouseListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[53]++;
        addMouseMotionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[54]++;

        this.defaultDirectoryForSaveAs = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[55]++;
        this.enforceFileExtensions = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[56]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[57]++;

        // initialize ChartPanel-specific tool tip delays with
        // values the from ToolTipManager.sharedInstance()
        ToolTipManager ttm = ToolTipManager.sharedInstance();       
        this.ownToolTipInitialDelay = ttm.getInitialDelay();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[58]++;
        this.ownToolTipDismissDelay = ttm.getDismissDelay();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[59]++;
        this.ownToolTipReshowDelay = ttm.getReshowDelay();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[60]++;

        this.zoomAroundAnchor = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[61]++;
    }

    /**
     * Returns the chart contained in the panel.
     *
     * @return The chart (possibly <code>null</code>).
     */
    public JFreeChart getChart() {
        return this.chart;
    }

    /**
     * Sets the chart that is displayed in the panel.
     *
     * @param chart  the chart (<code>null</code> permitted).
     */
    public void setChart(JFreeChart chart) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[62]++;
int CodeCoverConditionCoverageHelper_C2;

        // stop listening for changes to the existing chart
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.chart != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[3]++;
            this.chart.removeChangeListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[63]++;
            this.chart.removeProgressListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[64]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[4]++;}

        // add the new chart
        this.chart = chart;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[65]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[66]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((chart != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[5]++;
            this.chart.addChangeListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[67]++;
            this.chart.addProgressListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[68]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[69]++;
            Plot plot = chart.getPlot();
            this.domainZoomable = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[70]++;
            this.rangeZoomable = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[71]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[72]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((plot instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[7]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[73]++;
                Zoomable z = (Zoomable) plot;
                this.domainZoomable = z.isDomainZoomable();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[74]++;
                this.rangeZoomable = z.isRangeZoomable();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[75]++;
                this.orientation = z.getOrientation();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[76]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[8]++;}

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[6]++;
            this.domainZoomable = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[77]++;
            this.rangeZoomable = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[78]++;
        }
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[79]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.useBuffer) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[9]++;
            this.refreshBuffer = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[80]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[10]++;}
        repaint();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[81]++;

    }

    /**
     * Returns the minimum drawing width for charts.
     * <P>
     * If the width available on the panel is less than this, then the chart is
     * drawn at the minimum width then scaled down to fit.
     *
     * @return The minimum drawing width.
     */
    public int getMinimumDrawWidth() {
        return this.minimumDrawWidth;
    }

    /**
     * Sets the minimum drawing width for the chart on this panel.
     * <P>
     * At the time the chart is drawn on the panel, if the available width is
     * less than this amount, the chart will be drawn using the minimum width
     * then scaled down to fit the available space.
     *
     * @param width  The width.
     */
    public void setMinimumDrawWidth(int width) {
        this.minimumDrawWidth = width;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[82]++;
    }

    /**
     * Returns the maximum drawing width for charts.
     * <P>
     * If the width available on the panel is greater than this, then the chart
     * is drawn at the maximum width then scaled up to fit.
     *
     * @return The maximum drawing width.
     */
    public int getMaximumDrawWidth() {
        return this.maximumDrawWidth;
    }

    /**
     * Sets the maximum drawing width for the chart on this panel.
     * <P>
     * At the time the chart is drawn on the panel, if the available width is
     * greater than this amount, the chart will be drawn using the maximum
     * width then scaled up to fit the available space.
     *
     * @param width  The width.
     */
    public void setMaximumDrawWidth(int width) {
        this.maximumDrawWidth = width;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[83]++;
    }

    /**
     * Returns the minimum drawing height for charts.
     * <P>
     * If the height available on the panel is less than this, then the chart
     * is drawn at the minimum height then scaled down to fit.
     *
     * @return The minimum drawing height.
     */
    public int getMinimumDrawHeight() {
        return this.minimumDrawHeight;
    }

    /**
     * Sets the minimum drawing height for the chart on this panel.
     * <P>
     * At the time the chart is drawn on the panel, if the available height is
     * less than this amount, the chart will be drawn using the minimum height
     * then scaled down to fit the available space.
     *
     * @param height  The height.
     */
    public void setMinimumDrawHeight(int height) {
        this.minimumDrawHeight = height;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[84]++;
    }

    /**
     * Returns the maximum drawing height for charts.
     * <P>
     * If the height available on the panel is greater than this, then the
     * chart is drawn at the maximum height then scaled up to fit.
     *
     * @return The maximum drawing height.
     */
    public int getMaximumDrawHeight() {
        return this.maximumDrawHeight;
    }

    /**
     * Sets the maximum drawing height for the chart on this panel.
     * <P>
     * At the time the chart is drawn on the panel, if the available height is
     * greater than this amount, the chart will be drawn using the maximum
     * height then scaled up to fit the available space.
     *
     * @param height  The height.
     */
    public void setMaximumDrawHeight(int height) {
        this.maximumDrawHeight = height;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[85]++;
    }

    /**
     * Returns the X scale factor for the chart.  This will be 1.0 if no 
     * scaling has been used.
     * 
     * @return The scale factor.
     */
    public double getScaleX() {
        return this.scaleX;
    }
    
    /**
     * Returns the Y scale factory for the chart.  This will be 1.0 if no 
     * scaling has been used.
     * 
     * @return The scale factor.
     */
    public double getScaleY() {
        return this.scaleY;
    }
    
    /**
     * Returns the anchor point.
     * 
     * @return The anchor point (possibly <code>null</code>).
     */
    public Point2D getAnchor() {
        return this.anchor;   
    }
    
    /**
     * Sets the anchor point.  This method is provided for the use of 
     * subclasses, not end users.
     * 
     * @param anchor  the anchor point (<code>null</code> permitted).
     */
    protected void setAnchor(Point2D anchor) {
        this.anchor = anchor;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[86]++;   
    }
    
    /**
     * Returns the popup menu.
     *
     * @return The popup menu.
     */
    public JPopupMenu getPopupMenu() {
        return this.popup;
    }

    /**
     * Sets the popup menu for the panel.
     *
     * @param popup  the popup menu (<code>null</code> permitted).
     */
    public void setPopupMenu(JPopupMenu popup) {
        this.popup = popup;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[87]++;
    }

    /**
     * Returns the chart rendering info from the most recent chart redraw.
     *
     * @return The chart rendering info.
     */
    public ChartRenderingInfo getChartRenderingInfo() {
        return this.info;
    }

    /**
     * A convenience method that switches on mouse-based zooming.
     *
     * @param flag  <code>true</code> enables zooming and rectangle fill on 
     *              zoom.
     */
    public void setMouseZoomable(boolean flag) {
        setMouseZoomable(flag, true);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[88]++;
    }

    /**
     * A convenience method that switches on mouse-based zooming.
     *
     * @param flag  <code>true</code> if zooming enabled
     * @param fillRectangle  <code>true</code> if zoom rectangle is filled,
     *                       false if rectangle is shown as outline only.
     */
    public void setMouseZoomable(boolean flag, boolean fillRectangle) {
        setDomainZoomable(flag);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[89]++;
        setRangeZoomable(flag);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[90]++;
        setFillZoomRectangle(fillRectangle);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[91]++;
    }

    /**
     * Returns the flag that determines whether or not zooming is enabled for 
     * the domain axis.
     * 
     * @return A boolean.
     */
    public boolean isDomainZoomable() {
        return this.domainZoomable;
    }
    
    /**
     * Sets the flag that controls whether or not zooming is enable for the 
     * domain axis.  A check is made to ensure that the current plot supports
     * zooming for the domain values.
     *
     * @param flag  <code>true</code> enables zooming if possible.
     */
    public void setDomainZoomable(boolean flag) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[92]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((flag) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[11]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[93]++;
            Plot plot = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[94]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((plot instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[13]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[95]++;
                Zoomable z = (Zoomable) plot;
                this.domainZoomable = flag && (z.isDomainZoomable());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[96]++;
  
            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[14]++;}

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[12]++;
            this.domainZoomable = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[97]++;
        }
    }

    /**
     * Returns the flag that determines whether or not zooming is enabled for 
     * the range axis.
     * 
     * @return A boolean.
     */
    public boolean isRangeZoomable() {
        return this.rangeZoomable;
    }
    
    /**
     * A flag that controls mouse-based zooming on the vertical axis.
     *
     * @param flag  <code>true</code> enables zooming.
     */
    public void setRangeZoomable(boolean flag) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[98]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((flag) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[15]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[99]++;
            Plot plot = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[100]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((plot instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[17]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[101]++;
                Zoomable z = (Zoomable) plot;
                this.rangeZoomable = flag && (z.isRangeZoomable());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[102]++;
  
            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[18]++;}

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[16]++;
            this.rangeZoomable = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[103]++;
        }
    }

    /**
     * Returns the flag that controls whether or not the zoom rectangle is
     * filled when drawn.
     * 
     * @return A boolean.
     */
    public boolean getFillZoomRectangle() {
        return this.fillZoomRectangle;
    }
    
    /**
     * A flag that controls how the zoom rectangle is drawn.
     *
     * @param flag  <code>true</code> instructs to fill the rectangle on
     *              zoom, otherwise it will be outlined.
     */
    public void setFillZoomRectangle(boolean flag) {
        this.fillZoomRectangle = flag;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[104]++;
    }

    /**
     * Returns the zoom trigger distance.  This controls how far the mouse must
     * move before a zoom action is triggered.
     * 
     * @return The distance (in Java2D units).
     */
    public int getZoomTriggerDistance() {
        return this.zoomTriggerDistance;
    }
    
    /**
     * Sets the zoom trigger distance.  This controls how far the mouse must 
     * move before a zoom action is triggered.
     * 
     * @param distance  the distance (in Java2D units).
     */
    public void setZoomTriggerDistance(int distance) {
        this.zoomTriggerDistance = distance;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[105]++;
    }
    
    /**
     * Returns the flag that controls whether or not a horizontal axis trace
     * line is drawn over the plot area at the current mouse location.
     * 
     * @return A boolean.
     */
    public boolean getHorizontalAxisTrace() {
        return this.horizontalAxisTrace;    
    }
    
    /**
     * A flag that controls trace lines on the horizontal axis.
     *
     * @param flag  <code>true</code> enables trace lines for the mouse
     *      pointer on the horizontal axis.
     */
    public void setHorizontalAxisTrace(boolean flag) {
        this.horizontalAxisTrace = flag;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[106]++;
    }
    
    /**
     * Returns the horizontal trace line.
     * 
     * @return The horizontal trace line (possibly <code>null</code>).
     */
    protected Line2D getHorizontalTraceLine() {
        return this.horizontalTraceLine;   
    }
    
    /**
     * Sets the horizontal trace line.
     * 
     * @param line  the line (<code>null</code> permitted).
     */
    protected void setHorizontalTraceLine(Line2D line) {
        this.horizontalTraceLine = line;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[107]++;   
    }

    /**
     * Returns the flag that controls whether or not a vertical axis trace
     * line is drawn over the plot area at the current mouse location.
     * 
     * @return A boolean.
     */
    public boolean getVerticalAxisTrace() {
        return this.verticalAxisTrace;    
    }
    
    /**
     * A flag that controls trace lines on the vertical axis.
     *
     * @param flag  <code>true</code> enables trace lines for the mouse
     *              pointer on the vertical axis.
     */
    public void setVerticalAxisTrace(boolean flag) {
        this.verticalAxisTrace = flag;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[108]++;
    }

    /**
     * Returns the vertical trace line.
     * 
     * @return The vertical trace line (possibly <code>null</code>).
     */
    protected Line2D getVerticalTraceLine() {
        return this.verticalTraceLine;   
    }
    
    /**
     * Sets the vertical trace line.
     * 
     * @param line  the line (<code>null</code> permitted).
     */
    protected void setVerticalTraceLine(Line2D line) {
        this.verticalTraceLine = line;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[109]++;   
    }
    
    /**
     * Returns the default directory for the "save as" option.
     * 
     * @return The default directory (possibly <code>null</code>).
     * 
     * @since 1.0.7
     */
    public File getDefaultDirectoryForSaveAs() {
        return this.defaultDirectoryForSaveAs;
    }

    /**
     * Sets the default directory for the "save as" option.  If you set this
     * to <code>null</code>, the user's default directory will be used.
     * 
     * @param directory  the directory (<code>null</code> permitted).
     * 
     * @since 1.0.7
     */
    public void setDefaultDirectoryForSaveAs(File directory) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[110]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((directory != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[19]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[111]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((directory.isDirectory()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[21]++;
                throw new IllegalArgumentException(
                        "The 'directory' argument is not a directory.");

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[22]++;}

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[20]++;}
        this.defaultDirectoryForSaveAs = directory;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[112]++;
    }
    
    /**
     * Returns <code>true</code> if file extensions should be enforced, and 
     * <code>false</code> otherwise.
     *
     * @return The flag.
     * 
     * @see #setEnforceFileExtensions(boolean)
     */
    public boolean isEnforceFileExtensions() {
        return this.enforceFileExtensions;
    }

    /**
     * Sets a flag that controls whether or not file extensions are enforced.
     *
     * @param enforce  the new flag value.
     * 
     * @see #isEnforceFileExtensions()
     */
    public void setEnforceFileExtensions(boolean enforce) {
        this.enforceFileExtensions = enforce;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[113]++;
    }
    
    /**
     * Returns the flag that controls whether or not zoom operations are 
     * centered around the current anchor point.
     * 
     * @return A boolean.
     * 
     * @since 1.0.7
     * 
     * @see #setZoomAroundAnchor(boolean)
     */
    public boolean getZoomAroundAnchor() {
        return this.zoomAroundAnchor;
    }
    
    /**
     * Sets the flag that controls whether or not zoom operations are
     * centered around the current anchor point.
     * 
     * @param zoomAroundAnchor  the new flag value.
     * 
     * @since 1.0.7
     * 
     * @see #getZoomAroundAnchor()
     */
    public void setZoomAroundAnchor(boolean zoomAroundAnchor) {
        this.zoomAroundAnchor = zoomAroundAnchor;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[114]++;
    }

    /**
     * Switches the display of tooltips for the panel on or off.  Note that 
     * tooltips can only be displayed if the chart has been configured to
     * generate tooltip items.
     *
     * @param flag  <code>true</code> to enable tooltips, <code>false</code> to
     *              disable tooltips.
     */
    public void setDisplayToolTips(boolean flag) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[115]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((flag) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[23]++;
            ToolTipManager.sharedInstance().registerComponent(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[116]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[24]++;
            ToolTipManager.sharedInstance().unregisterComponent(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[117]++;
        }
    }

    /**
     * Returns a string for the tooltip.
     *
     * @param e  the mouse event.
     *
     * @return A tool tip or <code>null</code> if no tooltip is available.
     */
    public String getToolTipText(MouseEvent e) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[118]++;

        String result = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[119]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.info != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[25]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[120]++;
            EntityCollection entities = this.info.getEntityCollection();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[121]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[27]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[122]++;
                Insets insets = getInsets();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[123]++;
                ChartEntity entity = entities.getEntity(
                        (int) ((e.getX() - insets.left) / this.scaleX),
                        (int) ((e.getY() - insets.top) / this.scaleY));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[124]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((entity != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[29]++;
                    result = entity.getToolTipText();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[125]++;

                } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[30]++;}

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[28]++;}

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[26]++;}
        return result;

    }

    /**
     * Translates a Java2D point on the chart to a screen location.
     *
     * @param java2DPoint  the Java2D point.
     *
     * @return The screen location.
     */
    public Point translateJava2DToScreen(Point2D java2DPoint) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[126]++;
        Insets insets = getInsets();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[127]++;
        int x = (int) (java2DPoint.getX() * this.scaleX + insets.left);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[128]++;
        int y = (int) (java2DPoint.getY() * this.scaleY + insets.top);
        return new Point(x, y);
    }

    /**
     * Translates a panel (component) location to a Java2D point.
     *
     * @param screenPoint  the screen location (<code>null</code> not 
     *                     permitted).
     *
     * @return The Java2D coordinates.
     */
    public Point2D translateScreenToJava2D(Point screenPoint) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[129]++;
        Insets insets = getInsets();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[130]++;
        double x = (screenPoint.getX() - insets.left) / this.scaleX;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[131]++;
        double y = (screenPoint.getY() - insets.top) / this.scaleY;
        return new Point2D.Double(x, y);
    }

    /**
     * Applies any scaling that is in effect for the chart drawing to the
     * given rectangle.
     *  
     * @param rect  the rectangle.
     * 
     * @return A new scaled rectangle.
     */
    public Rectangle2D scale(Rectangle2D rect) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[132]++;
        Insets insets = getInsets();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[133]++;
        double x = rect.getX() * getScaleX() + insets.left;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[134]++;
        double y = rect.getY() * this.getScaleY() + insets.top;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[135]++;
        double w = rect.getWidth() * this.getScaleX();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[136]++;
        double h = rect.getHeight() * this.getScaleY();
        return new Rectangle2D.Double(x, y, w, h);
    }

    /**
     * Returns the chart entity at a given point.
     * <P>
     * This method will return null if there is (a) no entity at the given 
     * point, or (b) no entity collection has been generated.
     *
     * @param viewX  the x-coordinate.
     * @param viewY  the y-coordinate.
     *
     * @return The chart entity (possibly <code>null</code>).
     */
    public ChartEntity getEntityForPoint(int viewX, int viewY) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[137]++;

        ChartEntity result = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[138]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.info != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[31]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[139]++;
            Insets insets = getInsets();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[140]++;
            double x = (viewX - insets.left) / this.scaleX;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[141]++;
            double y = (viewY - insets.top) / this.scaleY;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[142]++;
            EntityCollection entities = this.info.getEntityCollection();
            result = entities != null ? entities.getEntity(x, y) : null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[143]++;
 
        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[32]++;}
        return result;

    }

    /**
     * Returns the flag that controls whether or not the offscreen buffer
     * needs to be refreshed.
     * 
     * @return A boolean.
     */
    public boolean getRefreshBuffer() {
        return this.refreshBuffer;
    }
    
    /**
     * Sets the refresh buffer flag.  This flag is used to avoid unnecessary
     * redrawing of the chart when the offscreen image buffer is used.
     *
     * @param flag  <code>true</code> indicates that the buffer should be 
     *              refreshed.
     */
    public void setRefreshBuffer(boolean flag) {
        this.refreshBuffer = flag;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[144]++;
    }

    /**
     * Paints the component by drawing the chart to fill the entire component,
     * but allowing for the insets (which will be non-zero if a border has been
     * set for this component).  To increase performance (at the expense of
     * memory), an off-screen buffer image can be used.
     *
     * @param g  the graphics device for drawing on.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[145]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[146]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.chart == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[33]++;
            return;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[34]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[147]++;
        Graphics2D g2 = (Graphics2D) g.create();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[148]++;

        // first determine the size of the chart rendering area...
        Dimension size = getSize();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[149]++;
        Insets insets = getInsets();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[150]++;
        Rectangle2D available = new Rectangle2D.Double(insets.left, insets.top,
                size.getWidth() - insets.left - insets.right,
                size.getHeight() - insets.top - insets.bottom);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[151]++;

        // work out if scaling is required...
        boolean scale = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[152]++;
        double drawWidth = available.getWidth();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[153]++;
        double drawHeight = available.getHeight();
        this.scaleX = 1.0;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[154]++;
        this.scaleY = 1.0;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[155]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[156]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((drawWidth < this.minimumDrawWidth) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[35]++;
            this.scaleX = drawWidth / this.minimumDrawWidth;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[157]++;
            drawWidth = this.minimumDrawWidth;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[158]++;
            scale = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[159]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[36]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[160]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((drawWidth > this.maximumDrawWidth) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[37]++;
            this.scaleX = drawWidth / this.maximumDrawWidth;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[161]++;
            drawWidth = this.maximumDrawWidth;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[162]++;
            scale = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[163]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[38]++;}
}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[164]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((drawHeight < this.minimumDrawHeight) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[39]++;
            this.scaleY = drawHeight / this.minimumDrawHeight;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[165]++;
            drawHeight = this.minimumDrawHeight;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[166]++;
            scale = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[167]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[40]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[168]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((drawHeight > this.maximumDrawHeight) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[41]++;
            this.scaleY = drawHeight / this.maximumDrawHeight;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[169]++;
            drawHeight = this.maximumDrawHeight;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[170]++;
            scale = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[171]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[42]++;}
}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[172]++;

        Rectangle2D chartArea = new Rectangle2D.Double(0.0, 0.0, drawWidth, 
                drawHeight);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[173]++;
int CodeCoverConditionCoverageHelper_C22;

        // are we using the chart buffer?
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.useBuffer) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[43]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[174]++;

            // if buffer is being refreshed, it needs clearing unless it is
            // new - use the following flag to track this...
            boolean clearBuffer = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[175]++;
int CodeCoverConditionCoverageHelper_C23;
            
            // do we need to resize the buffer?
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C23 |= (32)) == 0 || true) &&
 ((this.chartBuffer == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((this.chartBufferWidth != available.getWidth()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.chartBufferHeight != available.getHeight()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[45]++;
                this.chartBufferWidth = (int) available.getWidth();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[176]++;
                this.chartBufferHeight = (int) available.getHeight();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[177]++;
                this.chartBuffer = createImage(this.chartBufferWidth, 
                        this.chartBufferHeight);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[178]++;
//                GraphicsConfiguration gc = g2.getDeviceConfiguration();
//                this.chartBuffer = gc.createCompatibleImage(
//                        this.chartBufferWidth, this.chartBufferHeight, 
//                        Transparency.TRANSLUCENT);
                this.refreshBuffer = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[179]++;
                clearBuffer = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[180]++;
  // buffer is new, no clearing required
            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[46]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[181]++;
int CodeCoverConditionCoverageHelper_C24;

            // do we need to redraw the buffer?
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.refreshBuffer) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[47]++;

                this.refreshBuffer = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[182]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[183]++; // clear the flag

                Rectangle2D bufferArea = new Rectangle2D.Double(
                        0, 0, this.chartBufferWidth, this.chartBufferHeight);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[184]++;

                Graphics2D bufferG2 = (Graphics2D) 
                        this.chartBuffer.getGraphics();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[185]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((clearBuffer) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[49]++;
                    bufferG2.clearRect(0, 0, this.chartBufferWidth, 
                            this.chartBufferHeight);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[186]++;

                } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[50]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[187]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((scale) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[51]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[188]++;
                    AffineTransform saved = bufferG2.getTransform();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[189]++;
                    AffineTransform st = AffineTransform.getScaleInstance(
                            this.scaleX, this.scaleY);
                    bufferG2.transform(st);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[190]++;
                    this.chart.draw(bufferG2, chartArea, this.anchor, 
                            this.info);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[191]++;
                    bufferG2.setTransform(saved);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[192]++;

                }
                else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[52]++;
                    this.chart.draw(bufferG2, bufferArea, this.anchor, 
                            this.info);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[193]++;
                }


            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[48]++;}

            // zap the buffer onto the panel...
            g2.drawImage(this.chartBuffer, insets.left, insets.top, this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[194]++;


        }

        // or redrawing the chart every time...
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[44]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[195]++;

            AffineTransform saved = g2.getTransform();
            g2.translate(insets.left, insets.top);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[196]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[197]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((scale) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[53]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[198]++;
                AffineTransform st = AffineTransform.getScaleInstance(
                        this.scaleX, this.scaleY);
                g2.transform(st);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[199]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[54]++;}
            this.chart.draw(g2, chartArea, this.anchor, this.info);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[200]++;
            g2.setTransform(saved);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[201]++;

        }
        
        // Redraw the zoom rectangle (if present)
        drawZoomRectangle(g2);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[202]++;
        
        g2.dispose();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[203]++;

        this.anchor = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[204]++;
        this.verticalTraceLine = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[205]++;
        this.horizontalTraceLine = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[206]++;

    }

    /**
     * Receives notification of changes to the chart, and redraws the chart.
     *
     * @param event  details of the chart change event.
     */
    public void chartChanged(ChartChangeEvent event) {
        this.refreshBuffer = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[207]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[208]++;
        Plot plot = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[209]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((plot instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[55]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[210]++;
            Zoomable z = (Zoomable) plot;
            this.orientation = z.getOrientation();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[211]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[56]++;}
        repaint();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[212]++;
    }

    /**
     * Receives notification of a chart progress event.
     *
     * @param event  the event.
     */
    public void chartProgress(ChartProgressEvent event) {
        // does nothing - override if necessary
    }

    /**
     * Handles action events generated by the popup menu.
     *
     * @param event  the event.
     */
    public void actionPerformed(ActionEvent event) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[213]++;

        String command = event.getActionCommand();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[214]++;

        // many of the zoom methods need a screen location - all we have is 
        // the zoomPoint, but it might be null.  Here we grab the x and y
        // coordinates, or use defaults...
        double screenX = -1.0;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[215]++;
        double screenY = -1.0;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[216]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.zoomPoint != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[57]++;
            screenX = this.zoomPoint.getX();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[217]++;
            screenY = this.zoomPoint.getY();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[218]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[58]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[219]++;
int CodeCoverConditionCoverageHelper_C30;
        
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((command.equals(PROPERTIES_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[59]++;
            doEditChartProperties();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[220]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[60]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[221]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((command.equals(SAVE_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[61]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[222]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                doSaveAs();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[223]++;
            }
            catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[64]++;
                e.printStackTrace();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[224]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[63]++;
}
  }

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[62]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[225]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((command.equals(PRINT_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[65]++;
            createChartPrintJob();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[226]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[66]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[227]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((command.equals(ZOOM_IN_BOTH_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[67]++;
            zoomInBoth(screenX, screenY);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[228]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[68]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[229]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((command.equals(ZOOM_IN_DOMAIN_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[69]++;
            zoomInDomain(screenX, screenY);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[230]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[70]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[231]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((command.equals(ZOOM_IN_RANGE_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[71]++;
            zoomInRange(screenX, screenY);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[232]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[72]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[233]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((command.equals(ZOOM_OUT_BOTH_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[73]++;
            zoomOutBoth(screenX, screenY);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[234]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[74]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[235]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((command.equals(ZOOM_OUT_DOMAIN_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[75]++;
            zoomOutDomain(screenX, screenY);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[236]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[76]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[237]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((command.equals(ZOOM_OUT_RANGE_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[77]++;
            zoomOutRange(screenX, screenY);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[238]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[78]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[239]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((command.equals(ZOOM_RESET_BOTH_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[79]++;
            restoreAutoBounds();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[240]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[80]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[241]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((command.equals(ZOOM_RESET_DOMAIN_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[81]++;
            restoreAutoDomainBounds();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[242]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[82]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[243]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((command.equals(ZOOM_RESET_RANGE_COMMAND)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[83]++;
            restoreAutoRangeBounds();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[244]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[84]++;}
}
}
}
}
}
}
}
}
}
}
}

    }

    /**
     * Handles a 'mouse entered' event. This method changes the tooltip delays
     * of ToolTipManager.sharedInstance() to the possibly different values set 
     * for this chart panel. 
     *
     * @param e  the mouse event.
     */
    public void mouseEntered(MouseEvent e) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[245]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.ownToolTipDelaysActive) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[85]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[246]++;
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            
            this.originalToolTipInitialDelay = ttm.getInitialDelay();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[247]++;
            ttm.setInitialDelay(this.ownToolTipInitialDelay);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[248]++;
    
            this.originalToolTipReshowDelay = ttm.getReshowDelay();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[249]++;
            ttm.setReshowDelay(this.ownToolTipReshowDelay);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[250]++;
            
            this.originalToolTipDismissDelay = ttm.getDismissDelay();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[251]++;
            ttm.setDismissDelay(this.ownToolTipDismissDelay);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[252]++;
    
            this.ownToolTipDelaysActive = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[253]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[86]++;}
    }

    /**
     * Handles a 'mouse exited' event. This method resets the tooltip delays of
     * ToolTipManager.sharedInstance() to their
     * original values in effect before mouseEntered()
     *
     * @param e  the mouse event.
     */
    public void mouseExited(MouseEvent e) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[254]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.ownToolTipDelaysActive) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[87]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[255]++;
            // restore original tooltip dealys 
            ToolTipManager ttm = ToolTipManager.sharedInstance();       
            ttm.setInitialDelay(this.originalToolTipInitialDelay);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[256]++;
            ttm.setReshowDelay(this.originalToolTipReshowDelay);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[257]++;
            ttm.setDismissDelay(this.originalToolTipDismissDelay);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[258]++;
            this.ownToolTipDelaysActive = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[259]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[88]++;}
    }

    /**
     * Handles a 'mouse pressed' event.
     * <P>
     * This event is the popup trigger on Unix/Linux.  For Windows, the popup
     * trigger is the 'mouse released' event.
     *
     * @param e  The mouse event.
     */
    public void mousePressed(MouseEvent e) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[260]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.zoomRectangle == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[89]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[261]++;
            Rectangle2D screenDataArea = getScreenDataArea(e.getX(), e.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[262]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((screenDataArea != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[91]++;
                this.zoomPoint = getPointInRectangle(e.getX(), e.getY(), 
                        screenDataArea);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[263]++;

            }
            else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[92]++;
                this.zoomPoint = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[264]++;
            }
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[265]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((e.isPopupTrigger()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[93]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[266]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.popup != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[95]++;
                    displayPopupMenu(e.getX(), e.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[267]++;

                } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[96]++;}

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[94]++;}

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[90]++;}
    }
    
    /**
     * Returns a point based on (x, y) but constrained to be within the bounds
     * of the given rectangle.  This method could be moved to JCommon.
     * 
     * @param x  the x-coordinate.
     * @param y  the y-coordinate.
     * @param area  the rectangle (<code>null</code> not permitted).
     * 
     * @return A point within the rectangle.
     */
    private Point getPointInRectangle(int x, int y, Rectangle2D area) {
        x = (int) Math.max(Math.ceil(area.getMinX()), Math.min(x, 
                Math.floor(area.getMaxX())));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[268]++;   
        y = (int) Math.max(Math.ceil(area.getMinY()), Math.min(y, 
                Math.floor(area.getMaxY())));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[269]++;
        return new Point(x, y);
    }

    /**
     * Handles a 'mouse dragged' event.
     *
     * @param e  the mouse event.
     */
    public void mouseDragged(MouseEvent e) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[270]++;
int CodeCoverConditionCoverageHelper_C48;

        // if the popup menu has already been triggered, then ignore dragging...
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((this.popup != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((this.popup.isShowing()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[97]++;
            return;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[98]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[271]++;
int CodeCoverConditionCoverageHelper_C49;
        // if no initial zoom point was set, ignore dragging...
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((this.zoomPoint == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[99]++;
            return;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[100]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[272]++;
        Graphics2D g2 = (Graphics2D) getGraphics();

        // Erase the previous zoom rectangle (if any)...
        drawZoomRectangle(g2);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[273]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[274]++;

        boolean hZoom = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[275]++;
        boolean vZoom = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[276]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[101]++;
            hZoom = this.rangeZoomable;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[277]++;
            vZoom = this.domainZoomable;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[278]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[102]++;
            hZoom = this.domainZoomable;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[279]++;              
            vZoom = this.rangeZoomable;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[280]++;
        }
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[281]++;
        Rectangle2D scaledDataArea = getScreenDataArea(
                (int) this.zoomPoint.getX(), (int) this.zoomPoint.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[282]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((hZoom) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((vZoom) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[103]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[283]++;
            // selected rectangle shouldn't extend outside the data area...
            double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[284]++;
            double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
            this.zoomRectangle = new Rectangle2D.Double(
                    this.zoomPoint.getX(), this.zoomPoint.getY(),
                    xmax - this.zoomPoint.getX(), ymax - this.zoomPoint.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[285]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[104]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[286]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((hZoom) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[105]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[287]++;
            double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
            this.zoomRectangle = new Rectangle2D.Double(
                    this.zoomPoint.getX(), scaledDataArea.getMinY(),
                    xmax - this.zoomPoint.getX(), scaledDataArea.getHeight());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[288]++;

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[106]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[289]++;
int CodeCoverConditionCoverageHelper_C53; if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((vZoom) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[107]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[290]++;
            double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
            this.zoomRectangle = new Rectangle2D.Double(
                    scaledDataArea.getMinX(), this.zoomPoint.getY(),
                    scaledDataArea.getWidth(), ymax - this.zoomPoint.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[291]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[108]++;}
}
}

        // Draw the new zoom rectangle...
        drawZoomRectangle(g2);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[292]++;
        
        g2.dispose();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[293]++;

    }

    /**
     * Handles a 'mouse released' event.  On Windows, we need to check if this 
     * is a popup trigger, but only if we haven't already been tracking a zoom
     * rectangle.
     *
     * @param e  information about the event.
     */
    public void mouseReleased(MouseEvent e) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[294]++;
int CodeCoverConditionCoverageHelper_C54;

        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((this.zoomRectangle != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[109]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[295]++;
            boolean hZoom = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[296]++;
            boolean vZoom = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[297]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((this.orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[111]++;
                hZoom = this.rangeZoomable;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[298]++;
                vZoom = this.domainZoomable;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[299]++;

            }
            else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[112]++;
                hZoom = this.domainZoomable;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[300]++;              
                vZoom = this.rangeZoomable;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[301]++;
            }
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[302]++;
            
            boolean zoomTrigger1 = hZoom && Math.abs(e.getX() 
                - this.zoomPoint.getX()) >= this.zoomTriggerDistance;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[303]++;
            boolean zoomTrigger2 = vZoom && Math.abs(e.getY() 
                - this.zoomPoint.getY()) >= this.zoomTriggerDistance;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[304]++;
int CodeCoverConditionCoverageHelper_C56;
            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((zoomTrigger1) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((zoomTrigger2) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[113]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[305]++;
int CodeCoverConditionCoverageHelper_C57;
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C57 |= (128)) == 0 || true) &&
 ((hZoom) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C57 |= (32)) == 0 || true) &&
 ((e.getX() < this.zoomPoint.getX()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (16)) == 0 || true)))
)) || (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((vZoom) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((e.getY() < this.zoomPoint.getY()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 4) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 4) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[115]++;
                    restoreAutoBounds();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[306]++;

                }
                else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[116]++;
                    double x, y, w, h;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[307]++;
                    Rectangle2D screenDataArea = getScreenDataArea(
                            (int) this.zoomPoint.getX(), 
                            (int) this.zoomPoint.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[308]++;
int CodeCoverConditionCoverageHelper_C58;
                    // for mouseReleased event, (horizontalZoom || verticalZoom)
                    // will be true, so we can just test for either being false;
                    // otherwise both are true
                    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((vZoom) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[117]++;
                        x = this.zoomPoint.getX();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[309]++;
                        y = screenDataArea.getMinY();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[310]++;
                        w = Math.min(this.zoomRectangle.getWidth(),
                                screenDataArea.getMaxX() 
                                - this.zoomPoint.getX());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[311]++;
                        h = screenDataArea.getHeight();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[312]++;

                    }
                    else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[118]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[313]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((hZoom) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[119]++;
                        x = screenDataArea.getMinX();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[314]++;
                        y = this.zoomPoint.getY();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[315]++;
                        w = screenDataArea.getWidth();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[316]++;
                        h = Math.min(this.zoomRectangle.getHeight(),
                                screenDataArea.getMaxY() 
                                - this.zoomPoint.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[317]++;

                    }
                    else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[120]++;
                        x = this.zoomPoint.getX();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[318]++;
                        y = this.zoomPoint.getY();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[319]++;
                        w = Math.min(this.zoomRectangle.getWidth(),
                                screenDataArea.getMaxX() 
                                - this.zoomPoint.getX());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[320]++;
                        h = Math.min(this.zoomRectangle.getHeight(),
                                screenDataArea.getMaxY() 
                                - this.zoomPoint.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[321]++;
                    }
}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[322]++;
                    Rectangle2D zoomArea = new Rectangle2D.Double(x, y, w, h);
                    zoom(zoomArea);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[323]++;
                }
                this.zoomPoint = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[324]++;
                this.zoomRectangle = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[325]++;

            }
            else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[114]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[326]++;
                // Erase the zoom rectangle
                Graphics2D g2 = (Graphics2D) getGraphics();
                drawZoomRectangle(g2);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[327]++;
                g2.dispose();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[328]++;
                this.zoomPoint = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[329]++;
                this.zoomRectangle = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[330]++;
            }


        }

        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[110]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[331]++;
int CodeCoverConditionCoverageHelper_C60; if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((e.isPopupTrigger()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[121]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[332]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((this.popup != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[123]++;
                displayPopupMenu(e.getX(), e.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[333]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[124]++;}

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[122]++;}
}

    }

    /**
     * Receives notification of mouse clicks on the panel. These are
     * translated and passed on to any registered chart mouse click listeners.
     *
     * @param event  Information about the mouse event.
     */
    public void mouseClicked(MouseEvent event) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[334]++;

        Insets insets = getInsets();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[335]++;
        int x = (int) ((event.getX() - insets.left) / this.scaleX);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[336]++;
        int y = (int) ((event.getY() - insets.top) / this.scaleY);

        this.anchor = new Point2D.Double(x, y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[337]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[338]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((this.chart == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[125]++;
            return;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[126]++;}
        this.chart.setNotify(true);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[339]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[340]++;  // force a redraw 
        // new entity code...
        Object[] listeners = this.chartMouseListeners.getListeners(
                ChartMouseListener.class);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[341]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((listeners.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[127]++;
            return;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[128]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[342]++;

        ChartEntity entity = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[343]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((this.info != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[129]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[344]++;
            EntityCollection entities = this.info.getEntityCollection();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[345]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[131]++;
                entity = entities.getEntity(x, y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[346]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[132]++;}

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[130]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[347]++;
        ChartMouseEvent chartEvent = new ChartMouseEvent(getChart(), event, 
                entity);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[348]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[1]++;


int CodeCoverConditionCoverageHelper_C66;
        for (int i = listeners.length - 1;(((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false); i -= 1) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[1]--;
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[2]--;
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[3]++;
}
            ((ChartMouseListener) listeners[i]).chartMouseClicked(chartEvent);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[349]++;
        }

    }

    /**
     * Implementation of the MouseMotionListener's method.
     *
     * @param e  the event.
     */
    public void mouseMoved(MouseEvent e) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[350]++;
        Graphics2D g2 = (Graphics2D) getGraphics();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[351]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((this.horizontalAxisTrace) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[133]++;
            drawHorizontalAxisTrace(g2, e.getX());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[352]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[134]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[353]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.verticalAxisTrace) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[135]++;
            drawVerticalAxisTrace(g2, e.getY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[354]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[136]++;}
        g2.dispose();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[355]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[356]++;
        
        Object[] listeners = this.chartMouseListeners.getListeners(
                ChartMouseListener.class);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[357]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((listeners.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[137]++;
            return;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[138]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[358]++;
        Insets insets = getInsets();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[359]++;
        int x = (int) ((e.getX() - insets.left) / this.scaleX);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[360]++;
        int y = (int) ((e.getY() - insets.top) / this.scaleY);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[361]++;

        ChartEntity entity = null;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[362]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((this.info != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[139]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[363]++;
            EntityCollection entities = this.info.getEntityCollection();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[364]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[141]++;
                entity = entities.getEntity(x, y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[365]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[142]++;}

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[140]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[366]++;
int CodeCoverConditionCoverageHelper_C72;
        
        // we can only generate events if the panel's chart is not null
        // (see bug report 1556951)
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((this.chart != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[143]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[367]++;
            ChartMouseEvent event = new ChartMouseEvent(getChart(), e, entity);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[368]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[4]++;


int CodeCoverConditionCoverageHelper_C73;
            for (int i = listeners.length - 1;(((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false); i -= 1) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[4]--;
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[5]--;
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.loops[6]++;
}
                ((ChartMouseListener) listeners[i]).chartMouseMoved(event);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[369]++;
            }

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[144]++;}

    }

    /**
     * Zooms in on an anchor point (specified in screen coordinate space).
     *
     * @param x  the x value (in screen coordinates).
     * @param y  the y value (in screen coordinates).
     */
    public void zoomInBoth(double x, double y) {
        zoomInDomain(x, y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[370]++;
        zoomInRange(x, y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[371]++;
    }

    /**
     * Decreases the length of the domain axis, centered about the given
     * coordinate on the screen.  The length of the domain axis is reduced
     * by the value of {@link #getZoomInFactor()}.
     *
     * @param x  the x coordinate (in screen coordinates).
     * @param y  the y-coordinate (in screen coordinates).
     */
    public void zoomInDomain(double x, double y) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[372]++;
        Plot p = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[373]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((p instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[145]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[374]++;
            Zoomable plot = (Zoomable) p;
            plot.zoomDomainAxes(this.zoomInFactor, this.info.getPlotInfo(), 
                    translateScreenToJava2D(new Point((int) x, (int) y)),
                    this.zoomAroundAnchor);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[375]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[146]++;}
    }

    /**
     * Decreases the length of the range axis, centered about the given
     * coordinate on the screen.  The length of the range axis is reduced by
     * the value of {@link #getZoomInFactor()}.
     *
     * @param x  the x-coordinate (in screen coordinates).
     * @param y  the y coordinate (in screen coordinates).
     */
    public void zoomInRange(double x, double y) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[376]++;
        Plot p = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[377]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((p instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[147]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[378]++;
            Zoomable z = (Zoomable) p;
            z.zoomRangeAxes(this.zoomInFactor, this.info.getPlotInfo(), 
                    translateScreenToJava2D(new Point((int) x, (int) y)), 
                    this.zoomAroundAnchor);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[379]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[148]++;}
    }

    /**
     * Zooms out on an anchor point (specified in screen coordinate space).
     *
     * @param x  the x value (in screen coordinates).
     * @param y  the y value (in screen coordinates).
     */
    public void zoomOutBoth(double x, double y) {
        zoomOutDomain(x, y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[380]++;
        zoomOutRange(x, y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[381]++;
    }

    /**
     * Increases the length of the domain axis, centered about the given
     * coordinate on the screen.  The length of the domain axis is increased
     * by the value of {@link #getZoomOutFactor()}.
     *
     * @param x  the x coordinate (in screen coordinates).
     * @param y  the y-coordinate (in screen coordinates).
     */
    public void zoomOutDomain(double x, double y) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[382]++;
        Plot p = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[383]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((p instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[149]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[384]++;
            Zoomable z = (Zoomable) p;
            z.zoomDomainAxes(this.zoomOutFactor, this.info.getPlotInfo(), 
                    translateScreenToJava2D(new Point((int) x, (int) y)),
                    this.zoomAroundAnchor);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[385]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[150]++;}
    }

    /**
     * Increases the length the range axis, centered about the given
     * coordinate on the screen.  The length of the range axis is increased
     * by the value of {@link #getZoomOutFactor()}.
     *
     * @param x  the x coordinate (in screen coordinates).
     * @param y  the y-coordinate (in screen coordinates).
     */
    public void zoomOutRange(double x, double y) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[386]++;
        Plot p = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[387]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((p instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[151]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[388]++;
            Zoomable z = (Zoomable) p;
            z.zoomRangeAxes(this.zoomOutFactor, this.info.getPlotInfo(), 
                    translateScreenToJava2D(new Point((int) x, (int) y)),
                    this.zoomAroundAnchor);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[389]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[152]++;}
    }

    /**
     * Zooms in on a selected region.
     *
     * @param selection  the selected region.
     */
    public void zoom(Rectangle2D selection) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[390]++;

        // get the origin of the zoom selection in the Java2D space used for
        // drawing the chart (that is, before any scaling to fit the panel)
        Point2D selectOrigin = translateScreenToJava2D(new Point(
                (int) Math.ceil(selection.getX()), 
                (int) Math.ceil(selection.getY())));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[391]++;
        PlotRenderingInfo plotInfo = this.info.getPlotInfo();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[392]++;
        Rectangle2D scaledDataArea = getScreenDataArea(
                (int) selection.getCenterX(), (int) selection.getCenterY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[393]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((selection.getHeight() > 0) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((selection.getWidth() > 0) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[153]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[394]++;

            double hLower = (selection.getMinX() - scaledDataArea.getMinX()) 
                / scaledDataArea.getWidth();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[395]++;
            double hUpper = (selection.getMaxX() - scaledDataArea.getMinX()) 
                / scaledDataArea.getWidth();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[396]++;
            double vLower = (scaledDataArea.getMaxY() - selection.getMaxY()) 
                / scaledDataArea.getHeight();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[397]++;
            double vUpper = (scaledDataArea.getMaxY() - selection.getMinY()) 
                / scaledDataArea.getHeight();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[398]++;

            Plot p = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[399]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((p instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[155]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[400]++;
                Zoomable z = (Zoomable) p;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[401]++;
int CodeCoverConditionCoverageHelper_C80;
                if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((z.getOrientation() == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[157]++;
                    z.zoomDomainAxes(vLower, vUpper, plotInfo, selectOrigin);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[402]++;
                    z.zoomRangeAxes(hLower, hUpper, plotInfo, selectOrigin);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[403]++;

                }
                else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[158]++;
                    z.zoomDomainAxes(hLower, hUpper, plotInfo, selectOrigin);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[404]++;
                    z.zoomRangeAxes(vLower, vUpper, plotInfo, selectOrigin);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[405]++;
                }

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[156]++;}


        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[154]++;}

    }

    /**
     * Restores the auto-range calculation on both axes.
     */
    public void restoreAutoBounds() {
        restoreAutoDomainBounds();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[406]++;
        restoreAutoRangeBounds();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[407]++;
    }

    /**
     * Restores the auto-range calculation on the domain axis.
     */
    public void restoreAutoDomainBounds() {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[408]++;
        Plot p = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[409]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((p instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[159]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[410]++;
            Zoomable z = (Zoomable) p;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[411]++;
            // we need to guard against this.zoomPoint being null
            Point zp = (this.zoomPoint != null ? this.zoomPoint : new Point());
            z.zoomDomainAxes(0.0, this.info.getPlotInfo(), zp);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[412]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[160]++;}
    }

    /**
     * Restores the auto-range calculation on the range axis.
     */
    public void restoreAutoRangeBounds() {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[413]++;
        Plot p = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[414]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((p instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[161]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[415]++;
            Zoomable z = (Zoomable) p;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[416]++;
            // we need to guard against this.zoomPoint being null
            Point zp = (this.zoomPoint != null ? this.zoomPoint : new Point());
            z.zoomRangeAxes(0.0, this.info.getPlotInfo(), zp);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[417]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[162]++;}
    }

    /**
     * Returns the data area for the chart (the area inside the axes) with the
     * current scaling applied (that is, the area as it appears on screen).
     *
     * @return The scaled data area.
     */
    public Rectangle2D getScreenDataArea() {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[418]++;
        Rectangle2D dataArea = this.info.getPlotInfo().getDataArea();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[419]++;
        Insets insets = getInsets();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[420]++;
        double x = dataArea.getX() * this.scaleX + insets.left;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[421]++;
        double y = dataArea.getY() * this.scaleY + insets.top;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[422]++;
        double w = dataArea.getWidth() * this.scaleX;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[423]++;
        double h = dataArea.getHeight() * this.scaleY;
        return new Rectangle2D.Double(x, y, w, h);
    }
    
    /**
     * Returns the data area (the area inside the axes) for the plot or subplot,
     * with the current scaling applied.
     *
     * @param x  the x-coordinate (for subplot selection).
     * @param y  the y-coordinate (for subplot selection).
     * 
     * @return The scaled data area.
     */
    public Rectangle2D getScreenDataArea(int x, int y) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[424]++;
        PlotRenderingInfo plotInfo = this.info.getPlotInfo();
        Rectangle2D result;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[425]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((plotInfo.getSubplotCount() == 0) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[163]++;
            result = getScreenDataArea();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[426]++;

        } 
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[164]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[427]++;
            // get the origin of the zoom selection in the Java2D space used for
            // drawing the chart (that is, before any scaling to fit the panel)
            Point2D selectOrigin = translateScreenToJava2D(new Point(x, y));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[428]++;
            int subplotIndex = plotInfo.getSubplotIndex(selectOrigin);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[429]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((subplotIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[165]++;
                return null;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[166]++;}
            result = scale(plotInfo.getSubplotInfo(subplotIndex).getDataArea());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[430]++;
        }
        return result;
    }
    
    /**
     * Returns the initial tooltip delay value used inside this chart panel.
     *
     * @return An integer representing the initial delay value, in milliseconds.
     * 
     * @see javax.swing.ToolTipManager#getInitialDelay()
     */
    public int getInitialDelay() {
        return this.ownToolTipInitialDelay;
    }
    
    /**
     * Returns the reshow tooltip delay value used inside this chart panel.
     *
     * @return An integer representing the reshow  delay value, in milliseconds.
     * 
     * @see javax.swing.ToolTipManager#getReshowDelay()
     */
    public int getReshowDelay() {
        return this.ownToolTipReshowDelay;  
    }

    /**
     * Returns the dismissal tooltip delay value used inside this chart panel.
     *
     * @return An integer representing the dismissal delay value, in 
     *         milliseconds.
     * 
     * @see javax.swing.ToolTipManager#getDismissDelay()
     */
    public int getDismissDelay() {
        return this.ownToolTipDismissDelay; 
    }
    
    /**
     * Specifies the initial delay value for this chart panel.
     *
     * @param delay  the number of milliseconds to delay (after the cursor has 
     *               paused) before displaying. 
     * 
     * @see javax.swing.ToolTipManager#setInitialDelay(int)
     */
    public void setInitialDelay(int delay) {
        this.ownToolTipInitialDelay = delay;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[431]++;
    }
    
    /**
     * Specifies the amount of time before the user has to wait initialDelay 
     * milliseconds before a tooltip will be shown.
     *
     * @param delay  time in milliseconds
     * 
     * @see javax.swing.ToolTipManager#setReshowDelay(int)
     */
    public void setReshowDelay(int delay) {
        this.ownToolTipReshowDelay = delay;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[432]++;  
    }

    /**
     * Specifies the dismissal delay value for this chart panel.
     *
     * @param delay the number of milliseconds to delay before taking away the 
     *              tooltip
     * 
     * @see javax.swing.ToolTipManager#setDismissDelay(int)
     */
    public void setDismissDelay(int delay) {
        this.ownToolTipDismissDelay = delay;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[433]++; 
    }
    
    /**
     * Returns the zoom in factor.
     * 
     * @return The zoom in factor.
     * 
     * @see #setZoomInFactor(double)
     */
    public double getZoomInFactor() {
        return this.zoomInFactor;   
    }
    
    /**
     * Sets the zoom in factor.
     * 
     * @param factor  the factor.
     * 
     * @see #getZoomInFactor()
     */
    public void setZoomInFactor(double factor) {
        this.zoomInFactor = factor;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[434]++;
    }
    
    /**
     * Returns the zoom out factor.
     * 
     * @return The zoom out factor.
     * 
     * @see #setZoomOutFactor(double)
     */
    public double getZoomOutFactor() {
        return this.zoomOutFactor;   
    }
    
    /**
     * Sets the zoom out factor.
     * 
     * @param factor  the factor.
     * 
     * @see #getZoomOutFactor()
     */
    public void setZoomOutFactor(double factor) {
        this.zoomOutFactor = factor;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[435]++;
    }
    
    /**
     * Draws zoom rectangle (if present).
     * The drawing is performed in XOR mode, therefore
     * when this method is called twice in a row,
     * the second call will completely restore the state
     * of the canvas.
     * 
     * @param g2 the graphics device. 
     */
    private void drawZoomRectangle(Graphics2D g2) {
        // Set XOR mode to draw the zoom rectangle
        g2.setXORMode(Color.gray);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[436]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[437]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((this.zoomRectangle != null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[167]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[438]++;
int CodeCoverConditionCoverageHelper_C86;
            if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((this.fillZoomRectangle) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[169]++;
                g2.fill(this.zoomRectangle);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[439]++;

            }
            else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[170]++;
                g2.draw(this.zoomRectangle);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[440]++;
            }

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[168]++;}
        // Reset to the default 'overwrite' mode
        g2.setPaintMode();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[441]++;
    }
    
    /**
     * Draws a vertical line used to trace the mouse position to the horizontal 
     * axis.
     *
     * @param g2 the graphics device.
     * @param x  the x-coordinate of the trace line.
     */
    private void drawHorizontalAxisTrace(Graphics2D g2, int x) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[442]++;

        Rectangle2D dataArea = getScreenDataArea();

        g2.setXORMode(Color.orange);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[443]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[444]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 (((int) dataArea.getMinX() < x) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((x < (int) dataArea.getMaxX()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[171]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[445]++;
int CodeCoverConditionCoverageHelper_C88;

            if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((this.verticalTraceLine != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[173]++;
                g2.draw(this.verticalTraceLine);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[446]++;
                this.verticalTraceLine.setLine(x, (int) dataArea.getMinY(), x, 
                        (int) dataArea.getMaxY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[447]++;

            }
            else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[174]++;
                this.verticalTraceLine = new Line2D.Float(x, 
                        (int) dataArea.getMinY(), x, (int) dataArea.getMaxY());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[448]++;
            }
            g2.draw(this.verticalTraceLine);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[449]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[172]++;}

        // Reset to the default 'overwrite' mode
        g2.setPaintMode();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[450]++;
    }

    /**
     * Draws a horizontal line used to trace the mouse position to the vertical
     * axis.
     *
     * @param g2 the graphics device.
     * @param y  the y-coordinate of the trace line.
     */
    private void drawVerticalAxisTrace(Graphics2D g2, int y) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[451]++;

        Rectangle2D dataArea = getScreenDataArea();

        g2.setXORMode(Color.orange);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[452]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[453]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C89 |= (8)) == 0 || true) &&
 (((int) dataArea.getMinY() < y) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((y < (int) dataArea.getMaxY()) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 2) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 2) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[175]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[454]++;
int CodeCoverConditionCoverageHelper_C90;

            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((this.horizontalTraceLine != null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[177]++;
                g2.draw(this.horizontalTraceLine);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[455]++;
                this.horizontalTraceLine.setLine((int) dataArea.getMinX(), y, 
                        (int) dataArea.getMaxX(), y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[456]++;

            }
            else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[178]++;
                this.horizontalTraceLine = new Line2D.Float(
                        (int) dataArea.getMinX(), y, (int) dataArea.getMaxX(), 
                        y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[457]++;
            }
            g2.draw(this.horizontalTraceLine);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[458]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[176]++;}

        // Reset to the default 'overwrite' mode
        g2.setPaintMode();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[459]++;
    }

    /**
     * Displays a dialog that allows the user to edit the properties for the
     * current chart.
     * 
     * @since 1.0.3
     */
    public void doEditChartProperties() {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[460]++;

        ChartEditor editor = ChartEditorManager.getChartEditor(this.chart);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[461]++;
        int result = JOptionPane.showConfirmDialog(this, editor, 
                localizationResources.getString("Chart_Properties"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[462]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((result == JOptionPane.OK_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[179]++;
            editor.updateChart(this.chart);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[463]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[180]++;}

    }

    /**
     * Opens a file chooser and gives the user an opportunity to save the chart
     * in PNG format.
     *
     * @throws IOException if there is an I/O error.
     */
    public void doSaveAs() throws IOException {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[464]++;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(this.defaultDirectoryForSaveAs);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[465]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[466]++;
        ExtensionFileFilter filter = new ExtensionFileFilter(
                localizationResources.getString("PNG_Image_Files"), ".png");
        fileChooser.addChoosableFileFilter(filter);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[467]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[468]++;

        int option = fileChooser.showSaveDialog(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[469]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((option == JFileChooser.APPROVE_OPTION) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[181]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[470]++;
            String filename = fileChooser.getSelectedFile().getPath();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[471]++;
int CodeCoverConditionCoverageHelper_C93;
            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((isEnforceFileExtensions()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[183]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[472]++;
int CodeCoverConditionCoverageHelper_C94;
                if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((filename.endsWith(".png")) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[185]++;
                    filename = filename + ".png";
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[473]++;

                } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[186]++;}

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[184]++;}
            ChartUtilities.saveChartAsPNG(new File(filename), this.chart, 
                    getWidth(), getHeight());
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[474]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[182]++;}

    }

    /**
     * Creates a print job for the chart.
     */
    public void createChartPrintJob() {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[475]++;

        PrinterJob job = PrinterJob.getPrinterJob();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[476]++;
        PageFormat pf = job.defaultPage();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[477]++;
        PageFormat pf2 = job.pageDialog(pf);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[478]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((pf2 != pf) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[187]++;
            job.setPrintable(this, pf2);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[479]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[480]++;
int CodeCoverConditionCoverageHelper_C96;
            if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((job.printDialog()) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[189]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[481]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
                try {
CodeCoverTryBranchHelper_Try2 = true;
                    job.print();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[482]++;
                }
                catch (PrinterException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[192]++;
                    JOptionPane.showMessageDialog(this, e);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[483]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[191]++;
}
  }

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[190]++;}

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[188]++;}

    }

    /**
     * Prints the chart on a single page.
     *
     * @param g  the graphics context.
     * @param pf  the page format to use.
     * @param pageIndex  the index of the page. If not <code>0</code>, nothing 
     *                   gets print.
     *
     * @return The result of printing.
     */
    public int print(Graphics g, PageFormat pf, int pageIndex) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[484]++;
int CodeCoverConditionCoverageHelper_C97;

        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((pageIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[193]++;
            return NO_SUCH_PAGE;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[194]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[485]++;
        Graphics2D g2 = (Graphics2D) g;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[486]++;
        double x = pf.getImageableX();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[487]++;
        double y = pf.getImageableY();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[488]++;
        double w = pf.getImageableWidth();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[489]++;
        double h = pf.getImageableHeight();
        this.chart.draw(g2, new Rectangle2D.Double(x, y, w, h), this.anchor, 
                null);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[490]++;
        return PAGE_EXISTS;

    }

    /**
     * Adds a listener to the list of objects listening for chart mouse events.
     *
     * @param listener  the listener (<code>null</code> not permitted).
     */
    public void addChartMouseListener(ChartMouseListener listener) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[491]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((listener == null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[195]++;
            throw new IllegalArgumentException("Null 'listener' argument.");

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[196]++;}
        this.chartMouseListeners.add(ChartMouseListener.class, listener);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[492]++;
    }

    /**
     * Removes a listener from the list of objects listening for chart mouse 
     * events.
     *
     * @param listener  the listener.
     */
    public void removeChartMouseListener(ChartMouseListener listener) {
        this.chartMouseListeners.remove(ChartMouseListener.class, listener);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[493]++;
    }

    /**
     * Returns an array of the listeners of the given type registered with the
     * panel.
     * 
     * @param listenerType  the listener type.
     * 
     * @return An array of listeners.
     */
    public EventListener[] getListeners(Class listenerType) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[494]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((listenerType == ChartMouseListener.class) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[197]++;
            // fetch listeners from local storage
            return this.chartMouseListeners.getListeners(listenerType);

        }
        else {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[198]++;
            return super.getListeners(listenerType);
        }
    }

    /**
     * Creates a popup menu for the panel.
     *
     * @param properties  include a menu item for the chart property editor.
     * @param save  include a menu item for saving the chart.
     * @param print  include a menu item for printing the chart.
     * @param zoom  include menu items for zooming.
     *
     * @return The popup menu.
     */
    protected JPopupMenu createPopupMenu(boolean properties, 
                                         boolean save, 
                                         boolean print,
                                         boolean zoom) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[495]++;

        JPopupMenu result = new JPopupMenu("Chart:");
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[496]++;
        boolean separator = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[497]++;
int CodeCoverConditionCoverageHelper_C100;

        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((properties) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[199]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[498]++;
            JMenuItem propertiesItem = new JMenuItem(
                    localizationResources.getString("Properties..."));
            propertiesItem.setActionCommand(PROPERTIES_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[499]++;
            propertiesItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[500]++;
            result.add(propertiesItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[501]++;
            separator = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[502]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[200]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[503]++;
int CodeCoverConditionCoverageHelper_C101;

        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((save) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[201]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[504]++;
int CodeCoverConditionCoverageHelper_C102;
            if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((separator) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[203]++;
                result.addSeparator();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[505]++;
                separator = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[506]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[204]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[507]++;
            JMenuItem saveItem = new JMenuItem(
                    localizationResources.getString("Save_as..."));
            saveItem.setActionCommand(SAVE_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[508]++;
            saveItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[509]++;
            result.add(saveItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[510]++;
            separator = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[511]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[202]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[512]++;
int CodeCoverConditionCoverageHelper_C103;

        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((print) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[205]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[513]++;
int CodeCoverConditionCoverageHelper_C104;
            if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((separator) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[207]++;
                result.addSeparator();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[514]++;
                separator = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[515]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[208]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[516]++;
            JMenuItem printItem = new JMenuItem(
                    localizationResources.getString("Print..."));
            printItem.setActionCommand(PRINT_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[517]++;
            printItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[518]++;
            result.add(printItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[519]++;
            separator = true;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[520]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[206]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[521]++;
int CodeCoverConditionCoverageHelper_C105;

        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((zoom) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[209]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[522]++;
int CodeCoverConditionCoverageHelper_C106;
            if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((separator) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[211]++;
                result.addSeparator();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[523]++;
                separator = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[524]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[212]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[525]++;

            JMenu zoomInMenu = new JMenu(
                    localizationResources.getString("Zoom_In"));

            this.zoomInBothMenuItem = new JMenuItem(
                    localizationResources.getString("All_Axes"));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[526]++;
            this.zoomInBothMenuItem.setActionCommand(ZOOM_IN_BOTH_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[527]++;
            this.zoomInBothMenuItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[528]++;
            zoomInMenu.add(this.zoomInBothMenuItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[529]++;

            zoomInMenu.addSeparator();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[530]++;

            this.zoomInDomainMenuItem = new JMenuItem(
                    localizationResources.getString("Domain_Axis"));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[531]++;
            this.zoomInDomainMenuItem.setActionCommand(ZOOM_IN_DOMAIN_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[532]++;
            this.zoomInDomainMenuItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[533]++;
            zoomInMenu.add(this.zoomInDomainMenuItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[534]++;

            this.zoomInRangeMenuItem = new JMenuItem(
                    localizationResources.getString("Range_Axis"));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[535]++;
            this.zoomInRangeMenuItem.setActionCommand(ZOOM_IN_RANGE_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[536]++;
            this.zoomInRangeMenuItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[537]++;
            zoomInMenu.add(this.zoomInRangeMenuItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[538]++;

            result.add(zoomInMenu);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[539]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[540]++;

            JMenu zoomOutMenu = new JMenu(
                    localizationResources.getString("Zoom_Out"));

            this.zoomOutBothMenuItem = new JMenuItem(
                    localizationResources.getString("All_Axes"));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[541]++;
            this.zoomOutBothMenuItem.setActionCommand(ZOOM_OUT_BOTH_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[542]++;
            this.zoomOutBothMenuItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[543]++;
            zoomOutMenu.add(this.zoomOutBothMenuItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[544]++;

            zoomOutMenu.addSeparator();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[545]++;

            this.zoomOutDomainMenuItem = new JMenuItem(
                    localizationResources.getString("Domain_Axis"));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[546]++;
            this.zoomOutDomainMenuItem.setActionCommand(
                    ZOOM_OUT_DOMAIN_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[547]++;
            this.zoomOutDomainMenuItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[548]++;
            zoomOutMenu.add(this.zoomOutDomainMenuItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[549]++;

            this.zoomOutRangeMenuItem = new JMenuItem(
                    localizationResources.getString("Range_Axis"));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[550]++;
            this.zoomOutRangeMenuItem.setActionCommand(ZOOM_OUT_RANGE_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[551]++;
            this.zoomOutRangeMenuItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[552]++;
            zoomOutMenu.add(this.zoomOutRangeMenuItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[553]++;

            result.add(zoomOutMenu);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[554]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[555]++;

            JMenu autoRangeMenu = new JMenu(
                    localizationResources.getString("Auto_Range"));

            this.zoomResetBothMenuItem = new JMenuItem(
                    localizationResources.getString("All_Axes"));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[556]++;
            this.zoomResetBothMenuItem.setActionCommand(
                    ZOOM_RESET_BOTH_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[557]++;
            this.zoomResetBothMenuItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[558]++;
            autoRangeMenu.add(this.zoomResetBothMenuItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[559]++;

            autoRangeMenu.addSeparator();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[560]++;
            this.zoomResetDomainMenuItem = new JMenuItem(
                    localizationResources.getString("Domain_Axis"));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[561]++;
            this.zoomResetDomainMenuItem.setActionCommand(
                    ZOOM_RESET_DOMAIN_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[562]++;
            this.zoomResetDomainMenuItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[563]++;
            autoRangeMenu.add(this.zoomResetDomainMenuItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[564]++;

            this.zoomResetRangeMenuItem = new JMenuItem(
                    localizationResources.getString("Range_Axis"));
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[565]++;
            this.zoomResetRangeMenuItem.setActionCommand(
                    ZOOM_RESET_RANGE_COMMAND);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[566]++;
            this.zoomResetRangeMenuItem.addActionListener(this);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[567]++;
            autoRangeMenu.add(this.zoomResetRangeMenuItem);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[568]++;

            result.addSeparator();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[569]++;
            result.add(autoRangeMenu);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[570]++;


        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[210]++;}

        return result;

    }

    /**
     * The idea is to modify the zooming options depending on the type of chart 
     * being displayed by the panel.
     *
     * @param x  horizontal position of the popup.
     * @param y  vertical position of the popup.
     */
    protected void displayPopupMenu(int x, int y) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[571]++;
int CodeCoverConditionCoverageHelper_C107;

        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((this.popup != null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[213]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[572]++;

            // go through each zoom menu item and decide whether or not to 
            // enable it...
            Plot plot = this.chart.getPlot();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[573]++;
            boolean isDomainZoomable = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[574]++;
            boolean isRangeZoomable = false;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[575]++;
int CodeCoverConditionCoverageHelper_C108;
            if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((plot instanceof Zoomable) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[215]++;
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[576]++;
                Zoomable z = (Zoomable) plot;
                isDomainZoomable = z.isDomainZoomable();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[577]++;
                isRangeZoomable = z.isRangeZoomable();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[578]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[216]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[579]++;
int CodeCoverConditionCoverageHelper_C109;
            
            if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((this.zoomInDomainMenuItem != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[217]++;
                this.zoomInDomainMenuItem.setEnabled(isDomainZoomable);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[580]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[218]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[581]++;
int CodeCoverConditionCoverageHelper_C110;
            if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((this.zoomOutDomainMenuItem != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[219]++;
                this.zoomOutDomainMenuItem.setEnabled(isDomainZoomable);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[582]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[220]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[583]++;
int CodeCoverConditionCoverageHelper_C111; 
            if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((this.zoomResetDomainMenuItem != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[221]++;
                this.zoomResetDomainMenuItem.setEnabled(isDomainZoomable);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[584]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[222]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[585]++;
int CodeCoverConditionCoverageHelper_C112;

            if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((this.zoomInRangeMenuItem != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[223]++;
                this.zoomInRangeMenuItem.setEnabled(isRangeZoomable);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[586]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[224]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[587]++;
int CodeCoverConditionCoverageHelper_C113;
            if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((this.zoomOutRangeMenuItem != null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[225]++;
                this.zoomOutRangeMenuItem.setEnabled(isRangeZoomable);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[588]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[226]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[589]++;
int CodeCoverConditionCoverageHelper_C114;

            if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((this.zoomResetRangeMenuItem != null) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[227]++;
                this.zoomResetRangeMenuItem.setEnabled(isRangeZoomable);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[590]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[228]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[591]++;
int CodeCoverConditionCoverageHelper_C115;

            if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((this.zoomInBothMenuItem != null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[229]++;
                this.zoomInBothMenuItem.setEnabled(isDomainZoomable 
                        && isRangeZoomable);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[592]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[230]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[593]++;
int CodeCoverConditionCoverageHelper_C116;
            if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((this.zoomOutBothMenuItem != null) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[231]++;
                this.zoomOutBothMenuItem.setEnabled(isDomainZoomable 
                        && isRangeZoomable);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[594]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[232]++;}
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[595]++;
int CodeCoverConditionCoverageHelper_C117;
            if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((this.zoomResetBothMenuItem != null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[233]++;
                this.zoomResetBothMenuItem.setEnabled(isDomainZoomable 
                        && isRangeZoomable);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[596]++;

            } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[234]++;}

            this.popup.show(this, x, y);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[597]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[214]++;}

    }
    
    /* (non-Javadoc)
     * @see javax.swing.JPanel#updateUI()
     */
    public void updateUI() {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[598]++;
int CodeCoverConditionCoverageHelper_C118;
        // here we need to update the UI for the popup menu, if the panel
        // has one...
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((this.popup != null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[235]++;
            SwingUtilities.updateComponentTreeUI(this.popup);
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[599]++;

        } else {
  CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.branches[236]++;}
        super.updateUI();
CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox.statements[600]++;
    }

}

class CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox ());
  }
    public static long[] statements = new long[601];
    public static long[] branches = new long[237];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[119];
  static {
    final String SECTION_NAME = "org.jfree.chart.ChartPanel.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 118; i++) {
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

  public CodeCoverCoverageCounter$k7aznibtev57t9trgon2mox () {
    super("org.jfree.chart.ChartPanel.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 600; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 236; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 118; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.ChartPanel.java");
      for (int i = 1; i <= 600; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 236; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 118; i++) {
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

