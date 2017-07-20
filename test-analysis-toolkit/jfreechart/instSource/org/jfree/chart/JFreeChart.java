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
 * JFreeChart.java
 * ---------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Andrzej Porebski;
 *                   David Li;
 *                   Wolfgang Irler;
 *                   Christian W. Zuckschwerdt;
 *                   Klaus Rheinwald;
 *                   Nicolas Brodu;
 *                   
 * NOTE: The above list of contributors lists only the people that have
 * contributed to this source file (JFreeChart.java) - for a list of ALL
 * contributors to the project, please see the README.txt file.
 *
 * Changes (from 20-Jun-2001)
 * --------------------------
 * 20-Jun-2001 : Modifications submitted by Andrzej Porebski for legend 
 *               placement;
 * 21-Jun-2001 : Removed JFreeChart parameter from Plot constructors (DG);
 * 22-Jun-2001 : Multiple titles added (original code by David Berry, with 
 *               reworkings by DG);
 * 18-Sep-2001 : Updated header (DG);
 * 15-Oct-2001 : Moved data source classes into new package 
 *               com.jrefinery.data.* (DG);
 * 18-Oct-2001 : New factory method for creating VerticalXYBarChart (DG);
 * 19-Oct-2001 : Moved series paint and stroke methods to the Plot class (DG);
 *               Moved static chart creation methods to new ChartFactory 
 *               class (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 *               Fixed bug where chart isn't registered with the dataset (DG);
 * 07-Nov-2001 : Fixed bug where null title in constructor causes 
 *               exception (DG);
 *               Tidied up event notification code (DG);
 * 17-Nov-2001 : Added getLegendItemCount() method (DG);
 * 21-Nov-2001 : Set clipping in draw method to ensure that nothing gets drawn 
 *               outside the chart area (DG);
 * 11-Dec-2001 : Added the createBufferedImage() method, taken from the 
 *               JFreeChartServletDemo class (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Added handleClick() method (DG);
 * 22-Jan-2002 : Fixed bug correlating legend labels with pie data (DG);
 * 05-Feb-2002 : Removed redundant tooltips code (DG);
 * 19-Feb-2002 : Added accessor methods for the backgroundImage and 
 *               backgroundImageAlpha attributes (DG);
 * 21-Feb-2002 : Added static fields for INFO, COPYRIGHT, LICENCE, CONTRIBUTORS
 *               and LIBRARIES.  These can be used to display information about
 *               JFreeChart (DG);
 * 06-Mar-2002 : Moved constants to JFreeChartConstants interface (DG);
 * 18-Apr-2002 : PieDataset is no longer sorted (oldman);
 * 23-Apr-2002 : Moved dataset to the Plot class (DG);
 * 13-Jun-2002 : Added an extra draw() method (DG);
 * 25-Jun-2002 : Implemented the Drawable interface and removed redundant 
 *               imports (DG);
 * 26-Jun-2002 : Added another createBufferedImage() method (DG);
 * 18-Sep-2002 : Fixed issues reported by Checkstyle (DG);
 * 23-Sep-2002 : Added new contributor (DG);
 * 28-Oct-2002 : Created main title and subtitle list to replace existing title
 *               list (DG);
 * 08-Jan-2003 : Added contributor (DG);
 * 17-Jan-2003 : Added new constructor (DG);
 * 22-Jan-2003 : Added ChartColor class by Cameron Riley, and background image 
 *               alignment code by Christian W. Zuckschwerdt (DG);
 * 11-Feb-2003 : Added flag to allow suppression of chart change events, based 
 *               on a suggestion by Klaus Rheinwald (DG);
 * 04-Mar-2003 : Added small fix for suppressed chart change events (see bug id
 *               690865) (DG);
 * 10-Mar-2003 : Added Benoit Xhenseval to contributors (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 15-Jul-2003 : Added an optional border for the chart (DG);
 * 11-Sep-2003 : Took care of listeners while cloning (NB);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 22-Sep-2003 : Added nullpointer checks.
 * 25-Sep-2003 : Added nullpointer checks too (NB).
 * 03-Dec-2003 : Legends are now registered by this class instead of using the 
 *               old constructor way (TM);
 * 03-Dec-2003 : Added anchorPoint to draw() method (DG);
 * 08-Jan-2004 : Reworked title code, introducing line wrapping (DG);
 * 09-Feb-2004 : Created additional createBufferedImage() method (DG);
 * 05-Apr-2004 : Added new createBufferedImage() method (DG);
 * 27-May-2004 : Moved constants from JFreeChartConstants.java back to this 
 *               class (DG);
 * 25-Nov-2004 : Updates for changes to Title class (DG);
 * 06-Jan-2005 : Change lookup for default background color (DG);
 * 31-Jan-2005 : Added Don Elliott to contributors (DG);
 * 02-Feb-2005 : Added clearSubtitles() method (DG);
 * 03-Feb-2005 : Added Mofeed Shahin to contributors (DG);
 * 08-Feb-2005 : Updated for RectangleConstraint changes (DG);
 * 28-Mar-2005 : Renamed Legend --> OldLegend (DG);
 * 12-Apr-2005 : Added methods to access legend(s) in subtitle list (DG);
 * 13-Apr-2005 : Added removeLegend() and removeSubtitle() methods (DG);
 * 20-Apr-2005 : Modified to collect chart entities from titles and 
 *               subtitles (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 06-Jun-2005 : Added addLegend() method and padding attribute, fixed equals() 
 *               method (DG);
 * 24-Nov-2005 : Removed OldLegend and related code - don't want to support
 *               this in 1.0.0 final (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 27-Jan-2006 : Updated version number (DG);
 * 07-Dec-2006 : Added some missing credits (DG);
 * 17-Jan-2007 : Added Darren Jung to contributor list (DG);
 * 05-Mar-2007 : Added Sergei Ivanov to the contributor list (DG);
 * 16-Mar-2007 : Modified initial legend border (DG);
 * 22-Mar-2007 : New methods for text anti-aliasing (DG);
 * 16-May-2007 : Fixed argument check in getSubtitle(), copy list in 
 *               get/setSubtitles(), and added new addSubtitle(int, Title) 
 *               method (DG);
 * 05-Jun-2007 : Add change listener to default legend (DG);
 * 
 */

package org.jfree.chart;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;

import org.jfree.JCommon;
import org.jfree.chart.block.BlockParams;
import org.jfree.chart.block.EntityBlockResult;
import org.jfree.chart.block.LengthConstraintType;
import org.jfree.chart.block.LineBorder;
import org.jfree.chart.block.RectangleConstraint;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.chart.event.TitleChangeListener;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.Range;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.Align;
import org.jfree.ui.Drawable;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Size2D;
import org.jfree.ui.VerticalAlignment;
import org.jfree.ui.about.Contributor;
import org.jfree.ui.about.Licences;
import org.jfree.ui.about.ProjectInfo;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * A chart class implemented using the Java 2D APIs.  The current version
 * supports bar charts, line charts, pie charts and xy plots (including time
 * series data).
 * <P>
 * JFreeChart coordinates several objects to achieve its aim of being able to
 * draw a chart on a Java 2D graphics device: a list of {@link Title} objects
 * (which often includes the chart's legend), a {@link Plot} and a 
 * {@link org.jfree.data.general.Dataset} (the plot in turn manages a 
 * domain axis and a range axis).
 * <P>
 * You should use a {@link ChartPanel} to display a chart in a GUI.
 * <P>
 * The {@link ChartFactory} class contains static methods for creating 
 * 'ready-made' charts.
 *
 * @see ChartPanel
 * @see ChartFactory
 * @see Title
 * @see Plot
 */
public class JFreeChart implements Drawable,
                                   TitleChangeListener,
                                   PlotChangeListener,
                                   Serializable,
                                   Cloneable {
  static {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.ping();
  }


    /** For serialization. */    
    private static final long serialVersionUID = -3470703747817429120L;
  static {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[1]++;
  }
    
    /** Information about the project. */
    public static final ProjectInfo INFO = new JFreeChartInfo();
  static {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[2]++;
  }

    /** The default font for titles. */
    public static final Font DEFAULT_TITLE_FONT 
            = new Font("SansSerif", Font.BOLD, 18);
  static {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[3]++;
  }

    /** The default background color. */
    public static final Paint DEFAULT_BACKGROUND_PAINT 
            = UIManager.getColor("Panel.background");
  static {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[4]++;
  }

    /** The default background image. */
    public static final Image DEFAULT_BACKGROUND_IMAGE = null;
  static {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[5]++;
  }

    /** The default background image alignment. */
    public static final int DEFAULT_BACKGROUND_IMAGE_ALIGNMENT = Align.FIT;
  static {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[6]++;
  }

    /** The default background image alpha. */
    public static final float DEFAULT_BACKGROUND_IMAGE_ALPHA = 0.5f;
  static {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[7]++;
  }

    /** 
     * Rendering hints that will be used for chart drawing.  This should never
     * be <code>null</code>. 
     */
    private transient RenderingHints renderingHints;

    /** A flag that controls whether or not the chart border is drawn. */
    private boolean borderVisible;

    /** The stroke used to draw the chart border (if visible). */
    private transient Stroke borderStroke;

    /** The paint used to draw the chart border (if visible). */
    private transient Paint borderPaint;

    /** The padding between the chart border and the chart drawing area. */
    private RectangleInsets padding;
    
    /** The chart title (optional). */
    private TextTitle title;

    /** 
     * The chart subtitles (zero, one or many).  This field should never be
     * <code>null</code>.
     */
    private List subtitles;

    /** Draws the visual representation of the data. */
    private Plot plot;

    /** Paint used to draw the background of the chart. */
    private transient Paint backgroundPaint;

    /** An optional background image for the chart. */
    private transient Image backgroundImage;  // todo: not serialized yet

    /** The alignment for the background image. */
    private int backgroundImageAlignment = Align.FIT;
  {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[8]++;
  }

    /** The alpha transparency for the background image. */
    private float backgroundImageAlpha = 0.5f;
  {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[9]++;
  }

    /** Storage for registered change listeners. */
    private transient EventListenerList changeListeners;

    /** Storage for registered progress listeners. */
    private transient EventListenerList progressListeners;

    /** 
     * A flag that can be used to enable/disable notification of chart change 
     * events. 
     */
    private boolean notify;
    
    /**
     * Creates a new chart based on the supplied plot.  The chart will have
     * a legend added automatically, but no title (although you can easily add
     * one later).  
     * <br><br>
     * Note that the  {@link ChartFactory} class contains a range 
     * of static methods that will return ready-made charts, and often this
     * is a more convenient way to create charts than using this constructor.
     *
     * @param plot  the plot (<code>null</code> not permitted).
     */
    public JFreeChart(Plot plot) {
        this(null, null, plot, true);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[10]++;
    }

    /**
     * Creates a new chart with the given title and plot.  A default font 
     * (@link DEFAULT_TITLE_FONT) is used for the title, and the chart will 
     * have a legend added automatically.  
     * <br><br>
     * Note that the  {@link ChartFactory} class contains a range 
     * of static methods that will return ready-made charts, and often this
     * is a more convenient way to create charts than using this constructor.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param plot  the plot (<code>null</code> not permitted).
     */
    public JFreeChart(String title, Plot plot) {
        this(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[11]++;
    }

    /**
     * Creates a new chart with the given title and plot.  The 
     * <code>createLegend</code> argument specifies whether or not a legend
     * should be added to the chart.  
     * <br><br>
     * Note that the  {@link ChartFactory} class contains a range 
     * of static methods that will return ready-made charts, and often this
     * is a more convenient way to create charts than using this constructor.
     *
     * @param title  the chart title (<code>null</code> permitted).
     * @param titleFont  the font for displaying the chart title 
     *                   (<code>null</code> permitted).
     * @param plot  controller of the visual representation of the data 
     *              (<code>null</code> not permitted).
     * @param createLegend  a flag indicating whether or not a legend should   
     *                      be created for the chart.
     */
    public JFreeChart(String title, Font titleFont, Plot plot, 
                      boolean createLegend) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[1]++;
            throw new NullPointerException("Null 'plot' argument.");

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[2]++;}

        // create storage for listeners...
        this.progressListeners = new EventListenerList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[13]++;
        this.changeListeners = new EventListenerList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[14]++;
        this.notify = true;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[15]++;  // default is to notify listeners when the 
                             // chart changes

        this.renderingHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[16]++;

        this.borderVisible = false;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[17]++;
        this.borderStroke = new BasicStroke(1.0f);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[18]++;
        this.borderPaint = Color.black;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[19]++;

        this.padding = RectangleInsets.ZERO_INSETS;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[20]++;
        
        this.plot = plot;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[21]++;
        plot.addChangeListener(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[22]++;

        this.subtitles = new ArrayList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[23]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;

        // create a legend, if requested...
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((createLegend) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[3]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[25]++;
            LegendTitle legend = new LegendTitle(this.plot);
            legend.setMargin(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[26]++;
            legend.setFrame(new LineBorder());
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[27]++;
            legend.setBackgroundPaint(Color.white);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[28]++;
            legend.setPosition(RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[29]++;
            this.subtitles.add(legend);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[30]++;
            legend.addChangeListener(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[31]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[4]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[32]++;
int CodeCoverConditionCoverageHelper_C3;

        // add the chart title, if one has been specified...
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((title != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[5]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((titleFont == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[7]++;
                titleFont = DEFAULT_TITLE_FONT;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[34]++;

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[8]++;}
            this.title = new TextTitle(title, titleFont);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[35]++;
            this.title.addChangeListener(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[36]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[6]++;}

        this.backgroundPaint = DEFAULT_BACKGROUND_PAINT;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[37]++;

        this.backgroundImage = DEFAULT_BACKGROUND_IMAGE;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[38]++;
        this.backgroundImageAlignment = DEFAULT_BACKGROUND_IMAGE_ALIGNMENT;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[39]++;
        this.backgroundImageAlpha = DEFAULT_BACKGROUND_IMAGE_ALPHA;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[40]++;

    }

    /**
     * Returns the collection of rendering hints for the chart.
     *
     * @return The rendering hints for the chart (never <code>null</code>).
     * 
     * @see #setRenderingHints(RenderingHints)
     */
    public RenderingHints getRenderingHints() {
        return this.renderingHints;
    }

    /**
     * Sets the rendering hints for the chart.  These will be added (using the 
     * Graphics2D.addRenderingHints() method) near the start of the 
     * JFreeChart.draw() method.
     *
     * @param renderingHints  the rendering hints (<code>null</code> not 
     *                        permitted).
     *                        
     * @see #getRenderingHints()
     */
    public void setRenderingHints(RenderingHints renderingHints) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[41]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((renderingHints == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[9]++;
            throw new NullPointerException("RenderingHints given are null");

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[10]++;}
        this.renderingHints = renderingHints;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[42]++;
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[43]++;
    }

    /**
     * Returns a flag that controls whether or not a border is drawn around the
     * outside of the chart.
     *
     * @return A boolean.
     * 
     * @see #setBorderVisible(boolean)
     */
    public boolean isBorderVisible() {
        return this.borderVisible;
    }

    /**
     * Sets a flag that controls whether or not a border is drawn around the 
     * outside of the chart.
     *
     * @param visible  the flag.
     * 
     * @see #isBorderVisible()
     */
    public void setBorderVisible(boolean visible) {
        this.borderVisible = visible;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[44]++;
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[45]++;
    }

    /**
     * Returns the stroke used to draw the chart border (if visible).
     *
     * @return The border stroke.
     * 
     * @see #setBorderStroke(Stroke)
     */
    public Stroke getBorderStroke() {
        return this.borderStroke;
    }

    /**
     * Sets the stroke used to draw the chart border (if visible).
     *
     * @param stroke  the stroke.
     * 
     * @see #getBorderStroke()
     */
    public void setBorderStroke(Stroke stroke) {
        this.borderStroke = stroke;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[46]++;
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[47]++;
    }

    /**
     * Returns the paint used to draw the chart border (if visible).
     *
     * @return The border paint.
     * 
     * @see #setBorderPaint(Paint)
     */
    public Paint getBorderPaint() {
        return this.borderPaint;
    }

    /**
     * Sets the paint used to draw the chart border (if visible).
     *
     * @param paint  the paint.
     * 
     * @see #getBorderPaint()
     */
    public void setBorderPaint(Paint paint) {
        this.borderPaint = paint;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[48]++;
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[49]++;
    }
    
    /**
     * Returns the padding between the chart border and the chart drawing area.
     * 
     * @return The padding (never <code>null</code>).
     * 
     * @see #setPadding(RectangleInsets)
     */
    public RectangleInsets getPadding() {
        return this.padding;   
    }

    /**
     * Sets the padding between the chart border and the chart drawing area,
     * and sends a {@link ChartChangeEvent} to all registered listeners.
     * 
     * @param padding  the padding (<code>null</code> not permitted).
     * 
     * @see #getPadding()
     */
    public void setPadding(RectangleInsets padding) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[50]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((padding == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[11]++;
            throw new IllegalArgumentException("Null 'padding' argument.");
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[12]++;}
        this.padding = padding;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[51]++;
        notifyListeners(new ChartChangeEvent(this));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[52]++;
    }
    
    /**
     * Returns the main chart title.  Very often a chart will have just one
     * title, so we make this case simple by providing accessor methods for
     * the main title.  However, multiple titles are supported - see the
     * {@link #addSubtitle(Title)} method.
     *
     * @return The chart title (possibly <code>null</code>).
     * 
     * @see #setTitle(TextTitle)
     */
    public TextTitle getTitle() {
        return this.title;
    }

    /**
     * Sets the main title for the chart and sends a {@link ChartChangeEvent} 
     * to all registered listeners.  If you do not want a title for the 
     * chart, set it to <code>null</code>.  If you want more than one title on
     * a chart, use the {@link #addSubtitle(Title)} method.
     *
     * @param title  the title (<code>null</code> permitted).
     * 
     * @see #getTitle()
     */
    public void setTitle(TextTitle title) {
        this.title = title;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[53]++;
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[54]++;
    }

    /**
     * Sets the chart title and sends a {@link ChartChangeEvent} to all 
     * registered listeners.  This is a convenience method that ends up calling 
     * the {@link #setTitle(TextTitle)} method.  If there is an existing title,
     * its text is updated, otherwise a new title using the default font is 
     * added to the chart.  If <code>text</code> is <code>null</code> the chart
     * title is set to <code>null</code>.
     *
     * @param text  the title text (<code>null</code> permitted).
     * 
     * @see #getTitle()
     */
    public void setTitle(String text) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[55]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((text != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[13]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[56]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.title == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[15]++;
                setTitle(new TextTitle(text, JFreeChart.DEFAULT_TITLE_FONT));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[57]++;

            }
            else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[16]++;
                this.title.setText(text);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[58]++;
            }

        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[14]++;
            setTitle((TextTitle) null);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[59]++;
        }
    }

    /**
     * Adds a legend to the plot and sends a {@link ChartChangeEvent} to all
     * registered listeners.
     * 
     * @param legend  the legend (<code>null</code> not permitted).
     * 
     * @see #removeLegend()
     */
    public void addLegend(LegendTitle legend) {
        addSubtitle(legend);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[60]++;    
    }
    
    /**
     * Returns the legend for the chart, if there is one.  Note that a chart
     * can have more than one legend - this method returns the first.
     * 
     * @return The legend (possibly <code>null</code>).
     * 
     * @see #getLegend(int)
     */
    public LegendTitle getLegend() {
        return getLegend(0);
    }
    
    /**
     * Returns the nth legend for a chart, or <code>null</code>.
     * 
     * @param index  the legend index (zero-based).
     * 
     * @return The legend (possibly <code>null</code>).
     * 
     * @see #addLegend(LegendTitle)
     */
    public LegendTitle getLegend(int index) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[61]++;
        int seen = 0;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[62]++;
        Iterator iterator = this.subtitles.iterator();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[63]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
        while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[1]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[2]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[3]++;
}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[64]++;
            Title subtitle = (Title) iterator.next();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[65]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((subtitle instanceof LegendTitle) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[17]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[66]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((seen == index) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[19]++;
                    return (LegendTitle) subtitle;

                }
                else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[20]++;
                    seen++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[67]++;   
                }

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[18]++;}
        }
        return null;        
    }
    
    /**
     * Removes the first legend in the chart and sends a 
     * {@link ChartChangeEvent} to all registered listeners.
     * 
     * @see #getLegend()
     */
    public void removeLegend() {
        removeSubtitle(getLegend());
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[68]++;
    }
    
    /**
     * Returns the list of subtitles for the chart.
     *
     * @return The subtitle list (possibly empty, but never <code>null</code>).
     * 
     * @see #setSubtitles(List)
     */
    public List getSubtitles() {
        return new ArrayList(this.subtitles);
    }

    /**
     * Sets the title list for the chart (completely replaces any existing 
     * titles) and sends a {@link ChartChangeEvent} to all registered 
     * listeners.
     *
     * @param subtitles  the new list of subtitles (<code>null</code> not 
     *                   permitted).
     *                   
     * @see #getSubtitles()
     */
    public void setSubtitles(List subtitles) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[69]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((subtitles == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[21]++;
            throw new NullPointerException("Null 'subtitles' argument.");

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[22]++;}
        setNotify(false);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[70]++;
        clearSubtitles();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[71]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[72]++;
        Iterator iterator = subtitles.iterator();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[73]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;
        while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[4]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[5]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[6]++;
}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[74]++;
            Title t = (Title) iterator.next();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[75]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((t != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[23]++;
                addSubtitle(t);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[76]++;

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[24]++;}
        }
        setNotify(true);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[77]++;  // this fires a ChartChangeEvent
    }

    /**
     * Returns the number of titles for the chart.
     *
     * @return The number of titles for the chart.
     * 
     * @see #getSubtitles()
     */
    public int getSubtitleCount() {
        return this.subtitles.size();
    }

    /**
     * Returns a chart subtitle.
     *
     * @param index  the index of the chart subtitle (zero based).
     *
     * @return A chart subtitle.
     * 
     * @see #addSubtitle(Title)
     */
    public Title getSubtitle(int index) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[78]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((index >= getSubtitleCount()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[25]++;
            throw new IllegalArgumentException("Index out of range.");

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[26]++;}
        return (Title) this.subtitles.get(index);
    }

    /**
     * Adds a chart subtitle, and notifies registered listeners that the chart 
     * has been modified.
     *
     * @param subtitle  the subtitle (<code>null</code> not permitted).
     * 
     * @see #getSubtitle(int)
     */
    public void addSubtitle(Title subtitle) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[79]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((subtitle == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[27]++;
            throw new IllegalArgumentException("Null 'subtitle' argument.");

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[28]++;}
        this.subtitles.add(subtitle);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[80]++;
        subtitle.addChangeListener(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[81]++;
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[82]++;
    }
    
    /**
     * Adds a subtitle at a particular position in the subtitle list, and sends
     * a {@link ChartChangeEvent} to all registered listeners.
     * 
     * @param index  the index (in the range 0 to {@link #getSubtitleCount()}).
     * @param subtitle  the subtitle to add (<code>null</code> not permitted).
     * 
     * @since 1.0.6
     */
    public void addSubtitle(int index, Title subtitle) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[83]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((index > getSubtitleCount()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[29]++;
            throw new IllegalArgumentException(
                    "The 'index' argument is out of range.");

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[30]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[84]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((subtitle == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[31]++;
            throw new IllegalArgumentException("Null 'subtitle' argument.");

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[32]++;}
        this.subtitles.add(index, subtitle);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[85]++;
        subtitle.addChangeListener(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[86]++;
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[87]++;
    }
    
    /**
     * Clears all subtitles from the chart and sends a {@link ChartChangeEvent}
     * to all registered listeners.
     * 
     * @see #addSubtitle(Title)
     */
    public void clearSubtitles() {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[88]++;
        Iterator iterator = this.subtitles.iterator();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[89]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[7]++;


int CodeCoverConditionCoverageHelper_C19;
        while ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[7]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[8]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[9]++;
}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[90]++;
            Title t = (Title) iterator.next();
            t.removeChangeListener(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[91]++;
        }
        this.subtitles.clear();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[92]++;
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[93]++;
    }

    /**
     * Removes the specified subtitle and sends a {@link ChartChangeEvent} to
     * all registered listeners.
     * 
     * @param title  the title.
     * 
     * @see #addSubtitle(Title)
     */
    public void removeSubtitle(Title title) {
        this.subtitles.remove(title);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[94]++;
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[95]++;
    }
    
    /**
     * Returns the plot for the chart.  The plot is a class responsible for
     * coordinating the visual representation of the data, including the axes
     * (if any).
     *
     * @return The plot.
     */
    public Plot getPlot() {
        return this.plot;
    }

    /**
     * Returns the plot cast as a {@link CategoryPlot}.
     * <p>
     * NOTE: if the plot is not an instance of {@link CategoryPlot}, then a
     * <code>ClassCastException</code> is thrown.
     *
     * @return The plot.
     * 
     * @see #getPlot()
     */
    public CategoryPlot getCategoryPlot() {
        return (CategoryPlot) this.plot;
    }

    /**
     * Returns the plot cast as an {@link XYPlot}.
     * <p>
     * NOTE: if the plot is not an instance of {@link XYPlot}, then a
     * <code>ClassCastException</code> is thrown.
     *
     * @return The plot.
     * 
     * @see #getPlot()
     */
    public XYPlot getXYPlot() {
        return (XYPlot) this.plot;
    }

    /**
     * Returns a flag that indicates whether or not anti-aliasing is used when
     * the chart is drawn.
     *
     * @return The flag.
     * 
     * @see #setAntiAlias(boolean)
     */
    public boolean getAntiAlias() {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[96]++;
        Object val = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
        return RenderingHints.VALUE_ANTIALIAS_ON.equals(val);
    }
    
    /**
     * Sets a flag that indicates whether or not anti-aliasing is used when the
     * chart is drawn.
     * <P>
     * Anti-aliasing usually improves the appearance of charts, but is slower.
     *
     * @param flag  the new value of the flag.
     * 
     * @see #getAntiAlias()
     */
    public void setAntiAlias(boolean flag) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[97]++;

        Object val = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[98]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((val == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[33]++;
            val = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[99]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[34]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[100]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (128)) == 0 || true) &&
 ((flag) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((RenderingHints.VALUE_ANTIALIAS_OFF.equals(val)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((flag) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((RenderingHints.VALUE_ANTIALIAS_ON.equals(val)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 4) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 4) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[35]++;
            // no change, do nothing
            return;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[36]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[101]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((flag) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[37]++;
            this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, 
                                    RenderingHints.VALUE_ANTIALIAS_ON);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[102]++;

        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[38]++;
            this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, 
                                    RenderingHints.VALUE_ANTIALIAS_OFF);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[103]++;
        }
        fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[104]++;

    }

    /**
     * Returns the current value stored in the rendering hints table for
     * {@link RenderingHints#KEY_TEXT_ANTIALIASING}.
     * 
     * @return The hint value (possibly <code>null</code>).
     * 
     * @since 1.0.5
     * 
     * @see #setTextAntiAlias(Object)
     */
    public Object getTextAntiAlias() {
        return this.renderingHints.get(RenderingHints.KEY_TEXT_ANTIALIASING); 
    }
    
    /**
     * Sets the value in the rendering hints table for 
     * {@link RenderingHints#KEY_TEXT_ANTIALIASING} to either
     * {@link RenderingHints#VALUE_TEXT_ANTIALIAS_ON} or
     * {@link RenderingHints#VALUE_TEXT_ANTIALIAS_OFF}, then sends a 
     * {@link ChartChangeEvent} to all registered listeners.
     * 
     * @param flag  the new value of the flag.
     * 
     * @since 1.0.5
     * 
     * @see #getTextAntiAlias()
     * @see #setTextAntiAlias(Object)
     */
    public void setTextAntiAlias(boolean flag) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[105]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((flag) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[39]++;
            setTextAntiAlias(RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[106]++;

        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[40]++;
            setTextAntiAlias(RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[107]++;
        }
    }

    /**
     * Sets the value in the rendering hints table for 
     * {@link RenderingHints#KEY_TEXT_ANTIALIASING} and sends a 
     * {@link ChartChangeEvent} to all registered listeners.
     * 
     * @param val  the new value (<code>null</code> permitted).
     * 
     * @since 1.0.5
     * 
     * @see #getTextAntiAlias()
     * @see #setTextAntiAlias(boolean)
     */
    public void setTextAntiAlias(Object val) {
        this.renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, val);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[108]++;
        this.notifyListeners(new ChartChangeEvent(this));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[109]++;
    }
    
    /**
     * Returns the paint used for the chart background.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setBackgroundPaint(Paint)
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }

    /**
     * Sets the paint used to fill the chart background and sends a 
     * {@link ChartChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getBackgroundPaint()
     */
    public void setBackgroundPaint(Paint paint) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[110]++;
int CodeCoverConditionCoverageHelper_C24;

        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[41]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[111]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.backgroundPaint.equals(paint)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[43]++;
                this.backgroundPaint = paint;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[112]++;
                fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[113]++;

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[44]++;}

        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[42]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[114]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[45]++;
                this.backgroundPaint = paint;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[115]++;
                fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[116]++;

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[46]++;}
        }

    }

    /**
     * Returns the background image for the chart, or <code>null</code> if 
     * there is no image.
     *
     * @return The image (possibly <code>null</code>).
     * 
     * @see #setBackgroundImage(Image)
     */
    public Image getBackgroundImage() {
        return this.backgroundImage;
    }

    /**
     * Sets the background image for the chart and sends a 
     * {@link ChartChangeEvent} to all registered listeners.
     *
     * @param image  the image (<code>null</code> permitted).
     * 
     * @see #getBackgroundImage()
     */
    public void setBackgroundImage(Image image) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[117]++;
int CodeCoverConditionCoverageHelper_C27;

        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.backgroundImage != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[47]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[118]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.backgroundImage.equals(image)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[49]++;
                this.backgroundImage = image;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[119]++;
                fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[120]++;

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[50]++;}

        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[48]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[121]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((image != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[51]++;
                this.backgroundImage = image;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[122]++;
                fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[123]++;

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[52]++;}
        }

    }

    /**
     * Returns the background image alignment. Alignment constants are defined 
     * in the <code>org.jfree.ui.Align</code> class in the JCommon class 
     * library.
     *
     * @return The alignment.
     * 
     * @see #setBackgroundImageAlignment(int)
     */
    public int getBackgroundImageAlignment() {
        return this.backgroundImageAlignment;
    }

    /**
     * Sets the background alignment.  Alignment options are defined by the 
     * {@link org.jfree.ui.Align} class.
     *
     * @param alignment  the alignment.
     * 
     * @see #getBackgroundImageAlignment()
     */
    public void setBackgroundImageAlignment(int alignment) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[124]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.backgroundImageAlignment != alignment) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[53]++;
            this.backgroundImageAlignment = alignment;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[125]++;
            fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[126]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[54]++;}
    }

    /**
     * Returns the alpha-transparency for the chart's background image.
     *
     * @return The alpha-transparency.
     * 
     * @see #setBackgroundImageAlpha(float)
     */
    public float getBackgroundImageAlpha() {
        return this.backgroundImageAlpha;
    }

    /**
     * Sets the alpha-transparency for the chart's background image.
     * Registered listeners are notified that the chart has been changed.
     *
     * @param alpha  the alpha value.
     * 
     * @see #getBackgroundImageAlpha()
     */
    public void setBackgroundImageAlpha(float alpha) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[127]++;
int CodeCoverConditionCoverageHelper_C31;

        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.backgroundImageAlpha != alpha) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[55]++;
            this.backgroundImageAlpha = alpha;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[128]++;
            fireChartChanged();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[129]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[56]++;}

    }

    /**
     * Returns a flag that controls whether or not change events are sent to 
     * registered listeners.
     *
     * @return A boolean.
     * 
     * @see #setNotify(boolean)
     */
    public boolean isNotify() {
        return this.notify;
    }

    /**
     * Sets a flag that controls whether or not listeners receive 
     * {@link ChartChangeEvent} notifications.
     *
     * @param notify  a boolean.
     * 
     * @see #isNotify()
     */
    public void setNotify(boolean notify) {
        this.notify = notify;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[130]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[131]++;
int CodeCoverConditionCoverageHelper_C32;
        // if the flag is being set to true, there may be queued up changes...
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[57]++;
            notifyListeners(new ChartChangeEvent(this));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[132]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[58]++;}
    }

    /**
     * Draws the chart on a Java 2D graphics device (such as the screen or a
     * printer).
     * <P>
     * This method is the focus of the entire JFreeChart library.
     *
     * @param g2  the graphics device.
     * @param area  the area within which the chart should be drawn.
     */
    public void draw(Graphics2D g2, Rectangle2D area) {
        draw(g2, area, null, null);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[133]++;
    }

    /**
     * Draws the chart on a Java 2D graphics device (such as the screen or a
     * printer).  This method is the focus of the entire JFreeChart library.
     *
     * @param g2  the graphics device.
     * @param area  the area within which the chart should be drawn.
     * @param info  records info about the drawing (null means collect no info).
     */
    public void draw(Graphics2D g2, Rectangle2D area, ChartRenderingInfo info) {
        draw(g2, area, null, info);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[134]++;
    }
    
    /**
     * Draws the chart on a Java 2D graphics device (such as the screen or a
     * printer).
     * <P>
     * This method is the focus of the entire JFreeChart library.
     *
     * @param g2  the graphics device.
     * @param chartArea  the area within which the chart should be drawn.
     * @param anchor  the anchor point (in Java2D space) for the chart 
     *                (<code>null</code> permitted).
     * @param info  records info about the drawing (null means collect no info).
     */
    public void draw(Graphics2D g2, 
                     Rectangle2D chartArea, Point2D anchor, 
                     ChartRenderingInfo info) {

        notifyListeners(new ChartProgressEvent(this, this, 
                ChartProgressEvent.DRAWING_STARTED, 0));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[135]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[136]++;
int CodeCoverConditionCoverageHelper_C33;

        // record the chart area, if info is requested...
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[59]++;
            info.clear();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[137]++;
            info.setChartArea(chartArea);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[138]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[60]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[139]++;

        // ensure no drawing occurs outside chart area...
        Shape savedClip = g2.getClip();
        g2.clip(chartArea);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[140]++;

        g2.addRenderingHints(this.renderingHints);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[141]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[142]++;
int CodeCoverConditionCoverageHelper_C34;

        // draw the chart background...
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[61]++;
            g2.setPaint(this.backgroundPaint);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[143]++;
            g2.fill(chartArea);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[144]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[62]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[145]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.backgroundImage != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[63]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[146]++;
            Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                    this.backgroundImageAlpha));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[147]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[148]++;
            Rectangle2D dest = new Rectangle2D.Double(0.0, 0.0, 
                    this.backgroundImage.getWidth(null), 
                    this.backgroundImage.getHeight(null));
            Align.align(dest, chartArea, this.backgroundImageAlignment);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[149]++;
            g2.drawImage(this.backgroundImage, (int) dest.getX(), 
                    (int) dest.getY(), (int) dest.getWidth(), 
                    (int) dest.getHeight(), null);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[150]++;
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[151]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[64]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[152]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((isBorderVisible()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[65]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[153]++;
            Paint paint = getBorderPaint();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[154]++;
            Stroke stroke = getBorderStroke();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[155]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[67]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[156]++;
                Rectangle2D borderArea = new Rectangle2D.Double(
                        chartArea.getX(), chartArea.getY(), 
                        chartArea.getWidth() - 1.0, chartArea.getHeight() 
                        - 1.0);
                g2.setPaint(paint);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[157]++;
                g2.setStroke(stroke);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[158]++;
                g2.draw(borderArea);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[159]++;

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[68]++;}

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[66]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[160]++;

        // draw the title and subtitles...
        Rectangle2D nonTitleArea = new Rectangle2D.Double();
        nonTitleArea.setRect(chartArea);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[161]++;
        this.padding.trim(nonTitleArea);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[162]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[163]++;
        
        EntityCollection entities = null;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[164]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[69]++;
            entities = info.getEntityCollection();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[165]++;
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[70]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[166]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((this.title != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[71]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[167]++;
            EntityCollection e = drawTitle(this.title, g2, nonTitleArea, 
                    (entities != null));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[168]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((e != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[73]++;
                entities.addAll(e);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[169]++;
   
            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[74]++;}

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[72]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[170]++;

        Iterator iterator = this.subtitles.iterator();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[171]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[10]++;


int CodeCoverConditionCoverageHelper_C41;
        while ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[10]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[11]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[12]++;
}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[172]++;
            Title currentTitle = (Title) iterator.next();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[173]++;
            EntityCollection e = drawTitle(currentTitle, g2, nonTitleArea, 
                    (entities != null));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[174]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((e != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[75]++;
                entities.addAll(e);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[175]++;
   
            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[76]++;}
        }
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[176]++;

        Rectangle2D plotArea = nonTitleArea;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[177]++;
 
        // draw the plot (axes and data visualisation)
        PlotRenderingInfo plotInfo = null;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[178]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[77]++;
            plotInfo = info.getPlotInfo();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[179]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[78]++;}
        this.plot.draw(g2, plotArea, anchor, null, plotInfo);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[180]++;

        g2.setClip(savedClip);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[181]++;

        notifyListeners(new ChartProgressEvent(this, this, 
                ChartProgressEvent.DRAWING_FINISHED, 100));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[182]++;
    }

    /**
     * Creates a rectangle that is aligned to the frame.
     * 
     * @param dimensions  the dimensions for the rectangle.
     * @param frame  the frame to align to.
     * @param hAlign  the horizontal alignment.
     * @param vAlign  the vertical alignment.
     * 
     * @return A rectangle.
     */
    private Rectangle2D createAlignedRectangle2D(Size2D dimensions, 
            Rectangle2D frame, HorizontalAlignment hAlign, 
            VerticalAlignment vAlign) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[183]++;
        double x = Double.NaN;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[184]++;
        double y = Double.NaN;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[185]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((hAlign == HorizontalAlignment.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[79]++;
            x = frame.getX();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[186]++;
   
        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[80]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[187]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((hAlign == HorizontalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[81]++;
            x = frame.getCenterX() - (dimensions.width / 2.0);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[188]++;
   
        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[82]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[189]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((hAlign == HorizontalAlignment.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[83]++;
            x = frame.getMaxX() - dimensions.width;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[190]++;
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[84]++;}
}
}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[191]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((vAlign == VerticalAlignment.TOP) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[85]++;
            y = frame.getY();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[192]++;
   
        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[86]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[193]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((vAlign == VerticalAlignment.CENTER) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[87]++;
            y = frame.getCenterY() - (dimensions.height / 2.0);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[194]++;
   
        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[88]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[195]++;
int CodeCoverConditionCoverageHelper_C49; if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((vAlign == VerticalAlignment.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[89]++;
            y = frame.getMaxY() - dimensions.height;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[196]++;
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[90]++;}
}
}
        
        return new Rectangle2D.Double(x, y, dimensions.width, 
                dimensions.height);
    }
    
    /**
     * Draws a title.  The title should be drawn at the top, bottom, left or 
     * right of the specified area, and the area should be updated to reflect 
     * the amount of space used by the title.
     *
     * @param t  the title (<code>null</code> not permitted).
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param area  the chart area, excluding any existing titles 
     *              (<code>null</code> not permitted).
     * @param entities  a flag that controls whether or not an entity 
     *                  collection is returned for the title.
     * 
     * @return An entity collection for the title (possibly <code>null</code>).
     */
    protected EntityCollection drawTitle(Title t, Graphics2D g2, 
                                         Rectangle2D area, boolean entities) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[197]++;
int CodeCoverConditionCoverageHelper_C50;

        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((t == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[91]++;
            throw new IllegalArgumentException("Null 't' argument.");
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[92]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[198]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((area == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[93]++;
            throw new IllegalArgumentException("Null 'area' argument.");
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[94]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[199]++;
        Rectangle2D titleArea = new Rectangle2D.Double();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[200]++;
        RectangleEdge position = t.getPosition();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[201]++;
        double ww = area.getWidth();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[202]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((ww <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[95]++;
            return null;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[96]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[203]++;
        double hh = area.getHeight();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[204]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((hh <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[97]++;
            return null;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[98]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[205]++;
        RectangleConstraint constraint = new RectangleConstraint(ww, 
                new Range(0.0, ww), LengthConstraintType.RANGE, hh, 
                new Range(0.0, hh), LengthConstraintType.RANGE);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[206]++;
        Object retValue = null;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[207]++;
        BlockParams p = new BlockParams();
        p.setGenerateEntities(entities);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[208]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[209]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[99]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[210]++;
            Size2D size = t.arrange(g2, constraint);
            titleArea = createAlignedRectangle2D(size, area, 
                    t.getHorizontalAlignment(), VerticalAlignment.TOP);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[211]++;
            retValue = t.draw(g2, titleArea, p);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[212]++;
            area.setRect(area.getX(), Math.min(area.getY() + size.height, 
                    area.getMaxY()), area.getWidth(), Math.max(area.getHeight()
                    - size.height, 0));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[213]++;

        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[100]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[214]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[101]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[215]++;
            Size2D size = t.arrange(g2, constraint);
            titleArea = createAlignedRectangle2D(size, area, 
                    t.getHorizontalAlignment(), VerticalAlignment.BOTTOM);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[216]++;
            retValue = t.draw(g2, titleArea, p);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[217]++;
            area.setRect(area.getX(), area.getY(), area.getWidth(), 
                    area.getHeight() - size.height);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[218]++;

        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[102]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[219]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[103]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[220]++;
            Size2D size = t.arrange(g2, constraint);
            titleArea = createAlignedRectangle2D(size, area, 
                    HorizontalAlignment.RIGHT, t.getVerticalAlignment());
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[221]++;
            retValue = t.draw(g2, titleArea, p);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[222]++;
            area.setRect(area.getX(), area.getY(), area.getWidth() 
                    - size.width, area.getHeight());
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[223]++;

        }

        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[104]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[224]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((position == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[105]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[225]++;
            Size2D size = t.arrange(g2, constraint);
            titleArea = createAlignedRectangle2D(size, area, 
                    HorizontalAlignment.LEFT, t.getVerticalAlignment());
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[226]++;
            retValue = t.draw(g2, titleArea, p);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[227]++;
            area.setRect(area.getX() + size.width, area.getY(), area.getWidth() 
                    - size.width, area.getHeight());
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[228]++;

        }
        else {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[106]++;
            throw new RuntimeException("Unrecognised title position.");
        }
}
}
}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[229]++;
        EntityCollection result = null;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[230]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((retValue instanceof EntityBlockResult) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[107]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[231]++;
            EntityBlockResult ebr = (EntityBlockResult) retValue;
            result = ebr.getEntityCollection();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[232]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[108]++;}
        return result;   
    }

    /**
     * Creates and returns a buffered image into which the chart has been drawn.
     *
     * @param width  the width.
     * @param height  the height.
     *
     * @return A buffered image.
     */
    public BufferedImage createBufferedImage(int width, int height) {
        return createBufferedImage(width, height, null);
    }

    /**
     * Creates and returns a buffered image into which the chart has been drawn.
     *
     * @param width  the width.
     * @param height  the height.
     * @param info  carries back chart state information (<code>null</code> 
     *              permitted).
     *
     * @return A buffered image.
     */
    public BufferedImage createBufferedImage(int width, int height, 
                                             ChartRenderingInfo info) {
        return createBufferedImage(width, height, BufferedImage.TYPE_INT_RGB, 
                info);
    }

    /**
     * Creates and returns a buffered image into which the chart has been drawn.
     *
     * @param width  the width.
     * @param height  the height.
     * @param imageType  the image type.
     * @param info  carries back chart state information (<code>null</code> 
     *              permitted).
     *
     * @return A buffered image.
     */
    public BufferedImage createBufferedImage(int width, int height, 
                                             int imageType, 
                                             ChartRenderingInfo info) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[233]++;
        BufferedImage image = new BufferedImage(width, height, imageType);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[234]++;
        Graphics2D g2 = image.createGraphics();
        draw(g2, new Rectangle2D.Double(0, 0, width, height), null, info);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[235]++;
        g2.dispose();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[236]++;
        return image;
    }

    /**
     * Creates and returns a buffered image into which the chart has been drawn.
     *
     * @param imageWidth  the image width.
     * @param imageHeight  the image height.
     * @param drawWidth  the width for drawing the chart (will be scaled to 
     *                   fit image).
     * @param drawHeight  the height for drawing the chart (will be scaled to 
     *                    fit image).
     * @param info  optional object for collection chart dimension and entity 
     *              information.
     *
     * @return A buffered image.
     */
    public BufferedImage createBufferedImage(int imageWidth, 
                                             int imageHeight,
                                             double drawWidth, 
                                             double drawHeight, 
                                             ChartRenderingInfo info) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[237]++;

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, 
                BufferedImage.TYPE_INT_RGB);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[238]++;
        Graphics2D g2 = image.createGraphics();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[239]++;
        double scaleX = imageWidth / drawWidth;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[240]++;
        double scaleY = imageHeight / drawHeight;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[241]++;
        AffineTransform st = AffineTransform.getScaleInstance(scaleX, scaleY);
        g2.transform(st);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[242]++;
        draw(g2, new Rectangle2D.Double(0, 0, drawWidth, drawHeight), null, 
                info);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[243]++;
        g2.dispose();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[244]++;
        return image;

    }

    /**
     * Handles a 'click' on the chart.
     * <P>
     * JFreeChart is not a UI component, so some other object (e.g. ChartPanel)
     * needs to capture the click event and pass it onto the JFreeChart object.
     * If you are not using JFreeChart in a client application, then this
     * method is not required (and hopefully it doesn't get in the way).
     *
     * @param x  x-coordinate of the click (in Java2D space).
     * @param y  y-coordinate of the click (in Java2D space).
     * @param info  contains chart dimension and entity information.
     */
    public void handleClick(int x, int y, ChartRenderingInfo info) {

        // pass the click on to the plot...
        // rely on the plot to post a plot change event and redraw the chart...
        this.plot.handleClick(x, y, info.getPlotInfo());
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[245]++;

    }

    /**
     * Registers an object for notification of changes to the chart.
     *
     * @param listener  the listener (<code>null</code> not permitted).
     * 
     * @see #removeChangeListener(ChartChangeListener)
     */
    public void addChangeListener(ChartChangeListener listener) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[246]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((listener == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[109]++;
            throw new IllegalArgumentException("Null 'listener' argument.");

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[110]++;}
        this.changeListeners.add(ChartChangeListener.class, listener);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[247]++;
    }

    /**
     * Deregisters an object for notification of changes to the chart.
     *
     * @param listener  the listener (<code>null</code> not permitted)
     * 
     * @see #addChangeListener(ChartChangeListener)
     */
    public void removeChangeListener(ChartChangeListener listener) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[248]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((listener == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[111]++;
            throw new IllegalArgumentException("Null 'listener' argument.");

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[112]++;}
        this.changeListeners.remove(ChartChangeListener.class, listener);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[249]++;
    }

    /**
     * Sends a default {@link ChartChangeEvent} to all registered listeners.
     * <P>
     * This method is for convenience only.
     */
    public void fireChartChanged() {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[250]++;
        ChartChangeEvent event = new ChartChangeEvent(this);
        notifyListeners(event);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[251]++;
    }

    /**
     * Sends a {@link ChartChangeEvent} to all registered listeners.
     *
     * @param event  information about the event that triggered the 
     *               notification.
     */
    protected void notifyListeners(ChartChangeEvent event) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[252]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((this.notify) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[113]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[253]++;
            Object[] listeners = this.changeListeners.getListenerList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[254]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[13]++;


int CodeCoverConditionCoverageHelper_C62;
            for (int i = listeners.length - 2;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[13]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[14]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[15]++;
}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[255]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((listeners[i] == ChartChangeListener.class) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[115]++;
                    ((ChartChangeListener) listeners[i + 1]).chartChanged(
                            event);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[256]++;

                } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[116]++;}
            }

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[114]++;}
    }

    /**
     * Registers an object for notification of progress events relating to the 
     * chart.
     *
     * @param listener  the object being registered.
     * 
     * @see #removeProgressListener(ChartProgressListener)
     */
    public void addProgressListener(ChartProgressListener listener) {
        this.progressListeners.add(ChartProgressListener.class, listener);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[257]++;
    }

    /**
     * Deregisters an object for notification of changes to the chart.
     *
     * @param listener  the object being deregistered.
     * 
     * @see #addProgressListener(ChartProgressListener)
     */
    public void removeProgressListener(ChartProgressListener listener) {
        this.progressListeners.remove(ChartProgressListener.class, listener);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[258]++;
    }

    /**
     * Sends a {@link ChartProgressEvent} to all registered listeners.
     *
     * @param event  information about the event that triggered the 
     *               notification.
     */
    protected void notifyListeners(ChartProgressEvent event) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[259]++;

        Object[] listeners = this.progressListeners.getListenerList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[260]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[16]++;


int CodeCoverConditionCoverageHelper_C64;
        for (int i = listeners.length - 2;(((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[16]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[17]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[18]++;
}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[261]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((listeners[i] == ChartProgressListener.class) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[117]++;
                ((ChartProgressListener) listeners[i + 1]).chartProgress(event);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[262]++;

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[118]++;}
        }

    }

    /**
     * Receives notification that a chart title has changed, and passes this
     * on to registered listeners.
     *
     * @param event  information about the chart title change.
     */
    public void titleChanged(TitleChangeEvent event) {
        event.setChart(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[263]++;
        notifyListeners(event);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[264]++;
    }

    /**
     * Receives notification that the plot has changed, and passes this on to
     * registered listeners.
     *
     * @param event  information about the plot change.
     */
    public void plotChanged(PlotChangeEvent event) {
        event.setChart(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[265]++;
        notifyListeners(event);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[266]++;
    }

    /**
     * Tests this chart for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[267]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[119]++;
            return true;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[120]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[268]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((obj instanceof JFreeChart) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[121]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[122]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[269]++;
        JFreeChart that = (JFreeChart) obj;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[270]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.renderingHints.equals(that.renderingHints)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[123]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[124]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[271]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((this.borderVisible != that.borderVisible) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[125]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[126]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[272]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.borderStroke, that.borderStroke)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[127]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[128]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[273]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.borderPaint, that.borderPaint)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[129]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[130]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[274]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((this.padding.equals(that.padding)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[131]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[132]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[275]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.title, that.title)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[133]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[134]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[276]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.subtitles, that.subtitles)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[135]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[136]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[277]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.plot, that.plot)) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[137]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[138]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[278]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(
            this.backgroundPaint, that.backgroundPaint
        )) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[139]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[140]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[279]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.backgroundImage, 
                that.backgroundImage)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[141]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[142]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[280]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((this.backgroundImageAlignment != that.backgroundImageAlignment) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[143]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[144]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[281]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((this.backgroundImageAlpha != that.backgroundImageAlpha) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[145]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[146]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[282]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((this.notify != that.notify) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[147]++;
            return false;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[148]++;}
        return true;
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
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[283]++;
        SerialUtilities.writeStroke(this.borderStroke, stream);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[284]++;
        SerialUtilities.writePaint(this.borderPaint, stream);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[285]++;
        SerialUtilities.writePaint(this.backgroundPaint, stream);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[286]++;
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
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[287]++;
        this.borderStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[288]++;
        this.borderPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[289]++;
        this.backgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[290]++;
        this.progressListeners = new EventListenerList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[291]++;
        this.changeListeners = new EventListenerList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[292]++;
        this.renderingHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[293]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[294]++;
int CodeCoverConditionCoverageHelper_C81;

        // register as a listener with sub-components...
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((this.title != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[149]++;
            this.title.addChangeListener(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[295]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[150]++;}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[296]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[19]++;


int CodeCoverConditionCoverageHelper_C82;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((i < getSubtitleCount()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[19]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[20]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[21]++;
}
            getSubtitle(i).addChangeListener(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[297]++;
        }
        this.plot.addChangeListener(this);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[298]++;
    }

    /**
     * Prints information about JFreeChart to standard output.
     *
     * @param args  no arguments are honored.
     */
    public static void main(String[] args) {
        System.out.println(JFreeChart.INFO.toString());
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[299]++;
    }

    /**
     * Clones the object, and takes care of listeners.
     * Note: caller shall register its own listeners on cloned graph.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if the chart is not cloneable.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[300]++;
        JFreeChart chart = (JFreeChart) super.clone();

        chart.renderingHints = (RenderingHints) this.renderingHints.clone();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[301]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[302]++;
int CodeCoverConditionCoverageHelper_C83;
        // private boolean borderVisible;
        // private transient Stroke borderStroke;
        // private transient Paint borderPaint;

        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((this.title != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[151]++;
            chart.title = (TextTitle) this.title.clone();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[303]++;
            chart.title.addChangeListener(chart);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[304]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[152]++;}

        chart.subtitles = new ArrayList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[305]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[306]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[22]++;


int CodeCoverConditionCoverageHelper_C84;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((i < getSubtitleCount()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[22]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[23]--;
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.loops[24]++;
}
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[307]++;
            Title subtitle = (Title) getSubtitle(i).clone();
            chart.subtitles.add(subtitle);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[308]++;
            subtitle.addChangeListener(chart);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[309]++;
        }
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[310]++;
int CodeCoverConditionCoverageHelper_C85;

        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((this.plot != null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[153]++;
            chart.plot = (Plot) this.plot.clone();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[311]++;
            chart.plot.addChangeListener(chart);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[312]++;

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[154]++;}

        chart.progressListeners = new EventListenerList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[313]++;
        chart.changeListeners = new EventListenerList();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[314]++;
        return chart;
    }

}

/**
 * Information about the JFreeChart project.  One instance of this class is 
 * assigned to <code>JFreeChart.INFO<code>.
 */
class JFreeChartInfo extends ProjectInfo {
  static {
    CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.ping();
  }


    /** 
     * Default constructor. 
     */
    public JFreeChartInfo() {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[315]++;

        // get a locale-specific resource bundle...
        String baseResourceClass 
                = "org.jfree.chart.resources.JFreeChartResources";
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[316]++;
        ResourceBundle resources = ResourceBundle.getBundle(baseResourceClass);

        setName(resources.getString("project.name"));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[317]++;
        setVersion(resources.getString("project.version"));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[318]++;
        setInfo(resources.getString("project.info"));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[319]++;
        setCopyright(resources.getString("project.copyright"));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[320]++;
        setLogo(null);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[321]++;  // load only when required
        setLicenceName("LGPL");
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[322]++;
        setLicenceText(Licences.getInstance().getLGPL());
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[323]++;

        setContributors(Arrays.asList(
            new Contributor[]{
                new Contributor("Eric Alexander", "-"),
                new Contributor("Richard Atkinson", 
                        "richard_c_atkinson@ntlworld.com"),
                new Contributor("David Basten", "-"),
                new Contributor("David Berry", "-"),
                new Contributor("Chris Boek", "-"),
                new Contributor("Zoheb Borbora", "-"),
                new Contributor("Anthony Boulestreau", "-"),
                new Contributor("Jeremy Bowman", "-"),
                new Contributor("Nicolas Brodu", "-"),
                new Contributor("Jody Brownell", "-"),
                new Contributor("David Browning", "-"),
                new Contributor("Soren Caspersen", "-"),
                new Contributor("Chuanhao Chiu", "-"),
                new Contributor("Brian Cole", "-"),
                new Contributor("Pascal Collet", "-"),
                new Contributor("Martin Cordova", "-"),
                new Contributor("Paolo Cova", "-"),
                new Contributor("Mike Duffy", "-"),
                new Contributor("Don Elliott", "-"),
                new Contributor("David Forslund", "-"),
                new Contributor("Jonathan Gabbai", "-"),
                new Contributor("David Gilbert", 
                        "david.gilbert@object-refinery.com"),
                new Contributor("Serge V. Grachov", "-"),
                new Contributor("Daniel Gredler", "-"),
                new Contributor("Hans-Jurgen Greiner", "-"),
                new Contributor("Joao Guilherme Del Valle", "-"),
                new Contributor("Aiman Han", "-"),
                new Contributor("Cameron Hayne", "-"),
                new Contributor("Jon Iles", "-"),
                new Contributor("Wolfgang Irler", "-"),
                new Contributor("Sergei Ivanov", "-"),
                new Contributor("Adriaan Joubert", "-"),
                new Contributor("Darren Jung", "-"),
                new Contributor("Xun Kang", "-"),
                new Contributor("Bill Kelemen", "-"),
                new Contributor("Norbert Kiesel", "-"),
                new Contributor("Gideon Krause", "-"),
                new Contributor("Pierre-Marie Le Biot", "-"),
                new Contributor("Arnaud Lelievre", "-"),
                new Contributor("Wolfgang Lenhard", "-"),
                new Contributor("David Li", "-"),
                new Contributor("Yan Liu", "-"),
                new Contributor("Tin Luu", "-"),
                new Contributor("Craig MacFarlane", "-"),
                new Contributor("Achilleus Mantzios", "-"),
                new Contributor("Thomas Meier", "-"),
                new Contributor("Jim Moore", "-"),
                new Contributor("Jonathan Nash", "-"),
                new Contributor("Barak Naveh", "-"),
                new Contributor("David M. O'Donnell", "-"),
                new Contributor("Krzysztof Paz", "-"),
                new Contributor("Tomer Peretz", "-"),
                new Contributor("Xavier Poinsard", "-"),
                new Contributor("Andrzej Porebski", "-"),
                new Contributor("Viktor Rajewski", "-"),
                new Contributor("Eduardo Ramalho", "-"),
                new Contributor("Michael Rauch", "-"),
                new Contributor("Cameron Riley", "-"),
                new Contributor("Klaus Rheinwald", "-"),
                new Contributor("Dan Rivett", "d.rivett@ukonline.co.uk"),
                new Contributor("Scott Sams", "-"),
                new Contributor("Michel Santos", "-"),
                new Contributor("Thierry Saura", "-"),
                new Contributor("Andreas Schneider", "-"),
                new Contributor("Jean-Luc SCHWAB", "-"),
                new Contributor("Bryan Scott", "-"),
                new Contributor("Tobias Selb", "-"),
                new Contributor("Mofeed Shahin", "-"),
                new Contributor("Pady Srinivasan", "-"),
                new Contributor("Greg Steckman", "-"),
                new Contributor("Roger Studner", "-"),
                new Contributor("Irv Thomae", "-"),
                new Contributor("Eric Thomas", "-"),
                new Contributor("Rich Unger", "-"),
                new Contributor("Daniel van Enckevort", "-"),
                new Contributor("Laurence Vanhelsuwe", "-"),
                new Contributor("Sylvain Vieujot", "-"),
                new Contributor("Jelai Wang", "-"),
                new Contributor("Mark Watson", "www.markwatson.com"),
                new Contributor("Alex Weber", "-"),
                new Contributor("Matthew Wright", "-"),
                new Contributor("Benoit Xhenseval", "-"),
                new Contributor("Christian W. Zuckschwerdt", 
                        "Christian.Zuckschwerdt@Informatik.Uni-Oldenburg.de"),
                new Contributor("Hari", "-"),
                new Contributor("Sam (oldman)", "-"),
            }
        ));
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[324]++;

        addLibrary(JCommon.INFO);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[325]++;

    }

    /**
     * Returns the JFreeChart logo (a picture of a gorilla).
     *
     * @return The JFreeChart logo.
     */
    public Image getLogo() {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[326]++;

        Image logo = super.getLogo();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[327]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((logo == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[155]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[328]++;
            URL imageURL = this.getClass().getClassLoader().getResource(
                    "org/jfree/chart/gorilla.jpg");
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[329]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((imageURL != null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[157]++;
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[330]++;
                ImageIcon temp = new ImageIcon(imageURL);  
                    // use ImageIcon because it waits for the image to load...
                logo = temp.getImage();
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[331]++;
                setLogo(logo);
CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.statements[332]++;

            } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[158]++;}

        } else {
  CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69.branches[156]++;}
        return logo;

    }

}

class CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69 ());
  }
    public static long[] statements = new long[333];
    public static long[] branches = new long[159];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[88];
  static {
    final String SECTION_NAME = "org.jfree.chart.JFreeChart.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 87; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$m9ehq5e9lj5akxb6jmp5k69 () {
    super("org.jfree.chart.JFreeChart.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 332; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 158; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 87; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.JFreeChart.java");
      for (int i = 1; i <= 332; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 158; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 87; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

