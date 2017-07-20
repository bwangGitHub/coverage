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
 * ---------
 * Plot.java
 * ---------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Sylvain Vieujot;
 *                   Jeremy Bowman;
 *                   Andreas Schneider;
 *                   Gideon Krause;
 *                   Nicolas Brodu;
 *                   Michal Krause;
 *
 * Changes
 * -------
 * 21-Jun-2001 : Removed redundant JFreeChart parameter from constructors (DG);
 * 18-Sep-2001 : Updated header info and fixed DOS encoding problem (DG);
 * 19-Oct-2001 : Moved series paint and stroke methods from JFreeChart 
 *               class (DG);
 * 23-Oct-2001 : Created renderer for LinePlot class (DG);
 * 07-Nov-2001 : Changed type names for ChartChangeEvent (DG);
 *               Tidied up some Javadoc comments (DG);
 * 13-Nov-2001 : Changes to allow for null axes on plots such as PiePlot (DG);
 *               Added plot/axis compatibility checks (DG);
 * 12-Dec-2001 : Changed constructors to protected, and removed unnecessary 
 *               'throws' clauses (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 22-Jan-2002 : Added handleClick() method, as part of implementation for 
 *               crosshairs (DG);
 *               Moved tooltips reference into ChartInfo class (DG);
 * 23-Jan-2002 : Added test for null axes in chartChanged() method, thanks 
 *               to Barry Evans for the bug report (number 506979 on 
 *               SourceForge) (DG);
 *               Added a zoom() method (DG);
 * 05-Feb-2002 : Updated setBackgroundPaint(), setOutlineStroke() and 
 *               setOutlinePaint() to better handle null values, as suggested 
 *               by Sylvain Vieujot (DG);
 * 06-Feb-2002 : Added background image, plus alpha transparency for background
 *               and foreground (DG);
 * 06-Mar-2002 : Added AxisConstants interface (DG);
 * 26-Mar-2002 : Changed zoom method from empty to abstract (DG);
 * 23-Apr-2002 : Moved dataset from JFreeChart class (DG);
 * 11-May-2002 : Added ShapeFactory interface for getShape() methods, 
 *               contributed by Jeremy Bowman (DG);
 * 28-May-2002 : Fixed bug in setSeriesPaint(int, Paint) for subplots (AS);
 * 25-Jun-2002 : Removed redundant imports (DG);
 * 30-Jul-2002 : Added 'no data' message for charts with null or empty 
 *               datasets (DG);
 * 21-Aug-2002 : Added code to extend series array if necessary (refer to 
 *               SourceForge bug id 594547 for details) (DG);
 * 17-Sep-2002 : Fixed bug in getSeriesOutlineStroke() method, reported by 
 *               Andreas Schroeder (DG);
 * 23-Sep-2002 : Added getLegendItems() abstract method (DG);
 * 24-Sep-2002 : Removed firstSeriesIndex, subplots now use their own paint 
 *               settings, there is a new mechanism for the legend to collect 
 *               the legend items (DG);
 * 27-Sep-2002 : Added dataset group (DG);
 * 14-Oct-2002 : Moved listener storage into EventListenerList.  Changed some 
 *               abstract methods to empty implementations (DG);
 * 28-Oct-2002 : Added a getBackgroundImage() method (DG);
 * 21-Nov-2002 : Added a plot index for identifying subplots in combined and 
 *               overlaid charts (DG);
 * 22-Nov-2002 : Changed all attributes from 'protected' to 'private'.  Added 
 *               dataAreaRatio attribute from David M O'Donnell's code (DG);
 * 09-Jan-2003 : Integrated fix for plot border contributed by Gideon 
 *               Krause (DG);
 * 17-Jan-2003 : Moved to com.jrefinery.chart.plot (DG);
 * 23-Jan-2003 : Removed one constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 14-Jul-2003 : Moved the dataset and secondaryDataset attributes to the 
 *               CategoryPlot and XYPlot classes (DG);
 * 21-Jul-2003 : Moved DrawingSupplier from CategoryPlot and XYPlot up to this 
 *               class (DG);
 * 20-Aug-2003 : Implemented Cloneable (DG);
 * 11-Sep-2003 : Listeners and clone (NB);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 03-Dec-2003 : Modified draw method to accept anchor (DG);
 * 12-Mar-2004 : Fixed clipping bug in drawNoDataMessage() method (DG);
 * 07-Apr-2004 : Modified string bounds calculation (DG);
 * 04-Nov-2004 : Added default shapes for legend items (DG);
 * 25-Nov-2004 : Some changes to the clone() method implementation (DG);
 * 23-Feb-2005 : Implemented new LegendItemSource interface (and also
 *               PublicCloneable) (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets (DG);
 * 05-May-2005 : Removed unused draw() method (DG);
 * 06-Jun-2005 : Fixed bugs in equals() method (DG);
 * 01-Sep-2005 : Moved dataAreaRatio from here to ContourPlot (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 30-Jun-2006 : Added background image alpha - see bug report 1514904 (DG);
 * 05-Sep-2006 : Implemented the MarkerChangeListener interface (DG);
 * 11-Jan-2007 : Added some argument checks, event notifications, and many
 *               API doc updates (DG);
 * 03-Apr-2007 : Made drawBackgroundImage() public (DG);
 * 07-Jun-2007 : Added new fillBackground() method to handle GradientPaint 
 *               taking into account orientation (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.event.EventListenerList;

import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.event.AxisChangeListener;
import org.jfree.chart.event.ChartChangeEventType;
import org.jfree.chart.event.MarkerChangeEvent;
import org.jfree.chart.event.MarkerChangeListener;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.io.SerialUtilities;
import org.jfree.text.G2TextMeasurer;
import org.jfree.text.TextBlock;
import org.jfree.text.TextBlockAnchor;
import org.jfree.text.TextUtilities;
import org.jfree.ui.Align;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * The base class for all plots in JFreeChart.  The 
 * {@link org.jfree.chart.JFreeChart} class delegates the drawing of axes and 
 * data to the plot.  This base class provides facilities common to most plot 
 * types.
 */
public abstract class Plot implements AxisChangeListener,
                                      DatasetChangeListener,
                                      MarkerChangeListener,
                                      LegendItemSource,
                                      PublicCloneable,
                                      Cloneable,
                                      Serializable {
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -8831571430103671324L;
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[1]++;
  }
    
    /** Useful constant representing zero. */
    public static final Number ZERO = new Integer(0);
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[2]++;
  }

    /** The default insets. */
    public static final RectangleInsets DEFAULT_INSETS 
        = new RectangleInsets(4.0, 8.0, 4.0, 8.0);
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[3]++;
  }

    /** The default outline stroke. */
    public static final Stroke DEFAULT_OUTLINE_STROKE = new BasicStroke(0.5f);
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[4]++;
  }

    /** The default outline color. */
    public static final Paint DEFAULT_OUTLINE_PAINT = Color.gray;
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[5]++;
  }

    /** The default foreground alpha transparency. */
    public static final float DEFAULT_FOREGROUND_ALPHA = 1.0f;
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[6]++;
  }

    /** The default background alpha transparency. */
    public static final float DEFAULT_BACKGROUND_ALPHA = 1.0f;
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[7]++;
  }

    /** The default background color. */
    public static final Paint DEFAULT_BACKGROUND_PAINT = Color.white;
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[8]++;
  }

    /** The minimum width at which the plot should be drawn. */
    public static final int MINIMUM_WIDTH_TO_DRAW = 10;
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[9]++;
  }

    /** The minimum height at which the plot should be drawn. */
    public static final int MINIMUM_HEIGHT_TO_DRAW = 10;
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[10]++;
  }
    
    /** A default box shape for legend items. */
    public static final Shape DEFAULT_LEGEND_ITEM_BOX 
        = new Rectangle2D.Double(-4.0, -4.0, 8.0, 8.0);
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[11]++;
  }
    
    /** A default circle shape for legend items. */
    public static final Shape DEFAULT_LEGEND_ITEM_CIRCLE 
        = new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0);
  static {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[12]++;
  }

    /** The parent plot (<code>null</code> if this is the root plot). */
    private Plot parent;

    /** The dataset group (to be used for thread synchronisation). */
    private DatasetGroup datasetGroup;

    /** The message to display if no data is available. */
    private String noDataMessage;

    /** The font used to display the 'no data' message. */
    private Font noDataMessageFont;

    /** The paint used to draw the 'no data' message. */
    private transient Paint noDataMessagePaint;

    /** Amount of blank space around the plot area. */
    private RectangleInsets insets;

    /** 
     * A flag that controls whether or not the plot outline is drawn. 
     *
     * @since 1.0.6
     */
    private boolean outlineVisible;

    /** The Stroke used to draw an outline around the plot. */
    private transient Stroke outlineStroke;

    /** The Paint used to draw an outline around the plot. */
    private transient Paint outlinePaint;
    
    /** An optional color used to fill the plot background. */
    private transient Paint backgroundPaint;

    /** An optional image for the plot background. */
    private transient Image backgroundImage;  // not currently serialized

    /** The alignment for the background image. */
    private int backgroundImageAlignment = Align.FIT;
  {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[13]++;
  }

    /** The alpha value used to draw the background image. */
    private float backgroundImageAlpha = 0.5f;
  {
    CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[14]++;
  }
    
    /** The alpha-transparency for the plot. */
    private float foregroundAlpha;

    /** The alpha transparency for the background paint. */
    private float backgroundAlpha;

    /** The drawing supplier. */
    private DrawingSupplier drawingSupplier;

    /** Storage for registered change listeners. */
    private transient EventListenerList listenerList;

    /**
     * Creates a new plot.
     */
    protected Plot() {

        this.parent = null;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[15]++;
        this.insets = DEFAULT_INSETS;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[16]++;
        this.backgroundPaint = DEFAULT_BACKGROUND_PAINT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[17]++;
        this.backgroundAlpha = DEFAULT_BACKGROUND_ALPHA;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[18]++;
        this.backgroundImage = null;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[19]++;
        this.outlineVisible = true;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[20]++;
        this.outlineStroke = DEFAULT_OUTLINE_STROKE;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[21]++;
        this.outlinePaint = DEFAULT_OUTLINE_PAINT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[22]++;
        this.foregroundAlpha = DEFAULT_FOREGROUND_ALPHA;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[23]++;

        this.noDataMessage = null;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[24]++;
        this.noDataMessageFont = new Font("SansSerif", Font.PLAIN, 12);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[25]++;
        this.noDataMessagePaint = Color.black;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[26]++;

        this.drawingSupplier = new DefaultDrawingSupplier();
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[27]++;

        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[28]++;

    }

    /**
     * Returns the dataset group for the plot (not currently used).
     *
     * @return The dataset group.
     * 
     * @see #setDatasetGroup(DatasetGroup)
     */
    public DatasetGroup getDatasetGroup() {
        return this.datasetGroup;
    }

    /**
     * Sets the dataset group (not currently used).
     *
     * @param group  the dataset group (<code>null</code> permitted).
     * 
     * @see #getDatasetGroup()
     */
    protected void setDatasetGroup(DatasetGroup group) {
        this.datasetGroup = group;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[29]++;
    }

    /**
     * Returns the string that is displayed when the dataset is empty or 
     * <code>null</code>.
     *
     * @return The 'no data' message (<code>null</code> possible).
     * 
     * @see #setNoDataMessage(String)
     * @see #getNoDataMessageFont()
     * @see #getNoDataMessagePaint()
     */
    public String getNoDataMessage() {
        return this.noDataMessage;
    }

    /**
     * Sets the message that is displayed when the dataset is empty or 
     * <code>null</code>, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param message  the message (<code>null</code> permitted).
     * 
     * @see #getNoDataMessage()
     */
    public void setNoDataMessage(String message) {
        this.noDataMessage = message;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[30]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[31]++;
    }

    /**
     * Returns the font used to display the 'no data' message.
     *
     * @return The font (never <code>null</code>).
     * 
     * @see #setNoDataMessageFont(Font)
     * @see #getNoDataMessage()
     */
    public Font getNoDataMessageFont() {
        return this.noDataMessageFont;
    }

    /**
     * Sets the font used to display the 'no data' message and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getNoDataMessageFont()
     */
    public void setNoDataMessageFont(Font font) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[32]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[1]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[2]++;}
        this.noDataMessageFont = font;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[33]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[34]++;
    }

    /**
     * Returns the paint used to display the 'no data' message.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setNoDataMessagePaint(Paint)
     * @see #getNoDataMessage()
     */
    public Paint getNoDataMessagePaint() {
        return this.noDataMessagePaint;
    }

    /**
     * Sets the paint used to display the 'no data' message and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getNoDataMessagePaint()
     */
    public void setNoDataMessagePaint(Paint paint) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[35]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[3]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[4]++;}
        this.noDataMessagePaint = paint;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[36]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[37]++;
    }

    /**
     * Returns a short string describing the plot type.
     * <P>
     * Note: this gets used in the chart property editing user interface,
     * but there needs to be a better mechanism for identifying the plot type.
     *
     * @return A short string describing the plot type (never 
     *     <code>null</code>).
     */
    public abstract String getPlotType();

    /**
     * Returns the parent plot (or <code>null</code> if this plot is not part 
     * of a combined plot).
     *
     * @return The parent plot.
     * 
     * @see #setParent(Plot)
     * @see #getRootPlot()
     */
    public Plot getParent() {
        return this.parent;
    }

    /**
     * Sets the parent plot.  This method is intended for internal use, you 
     * shouldn't need to call it directly.
     *
     * @param parent  the parent plot (<code>null</code> permitted).
     * 
     * @see #getParent()
     */
    public void setParent(Plot parent) {
        this.parent = parent;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[38]++;
    }

    /**
     * Returns the root plot.
     *
     * @return The root plot.
     * 
     * @see #getParent()
     */
    public Plot getRootPlot() {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[39]++;

        Plot p = getParent();
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[40]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((p == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[5]++;
            return this;

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[6]++;
            return p.getRootPlot();
        }

    }

    /**
     * Returns <code>true</code> if this plot is part of a combined plot 
     * structure (that is, {@link #getParent()} returns a non-<code>null</code>
     * value), and <code>false</code> otherwise.
     *
     * @return <code>true</code> if this plot is part of a combined plot 
     *         structure.
     *         
     * @see #getParent()
     */
    public boolean isSubplot() {
        return (getParent() != null);
    }

    /**
     * Returns the insets for the plot area.
     *
     * @return The insets (never <code>null</code>).
     * 
     * @see #setInsets(RectangleInsets)
     */
    public RectangleInsets getInsets() {
        return this.insets;
    }

    /**
     * Sets the insets for the plot and sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     *
     * @param insets  the new insets (<code>null</code> not permitted).
     * 
     * @see #getInsets()
     * @see #setInsets(RectangleInsets, boolean)
     */
    public void setInsets(RectangleInsets insets) {
        setInsets(insets, true);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[41]++;
    }

    /**
     * Sets the insets for the plot and, if requested,  and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param insets  the new insets (<code>null</code> not permitted).
     * @param notify  a flag that controls whether the registered listeners are
     *                notified.
     *                
     * @see #getInsets()
     * @see #setInsets(RectangleInsets)
     */
    public void setInsets(RectangleInsets insets, boolean notify) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[42]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((insets == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[7]++;
            throw new IllegalArgumentException("Null 'insets' argument.");

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[8]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[43]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.insets.equals(insets)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[9]++;
            this.insets = insets;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[44]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[45]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[11]++;
                notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[46]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[10]++;}

    }

    /**
     * Returns the background color of the plot area.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setBackgroundPaint(Paint)
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }

    /**
     * Sets the background color of the plot area and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getBackgroundPaint()
     */
    public void setBackgroundPaint(Paint paint) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[13]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[48]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[15]++;
                this.backgroundPaint = null;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[49]++;
                notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[50]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[16]++;}

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[14]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[51]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[17]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[52]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.backgroundPaint.equals(paint)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[19]++;
                    return;
  // nothing to do
                } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[20]++;}

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[18]++;}
            this.backgroundPaint = paint;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[53]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[54]++;
        }

    }

    /**
     * Returns the alpha transparency of the plot area background.
     *
     * @return The alpha transparency.
     * 
     * @see #setBackgroundAlpha(float)
     */
    public float getBackgroundAlpha() {
        return this.backgroundAlpha;
    }

    /**
     * Sets the alpha transparency of the plot area background, and notifies
     * registered listeners that the plot has been modified.
     *
     * @param alpha the new alpha value (in the range 0.0f to 1.0f).
     * 
     * @see #getBackgroundAlpha()
     */
    public void setBackgroundAlpha(float alpha) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[55]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((this.backgroundAlpha != alpha) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[21]++;
            this.backgroundAlpha = alpha;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[56]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[57]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[22]++;}
    }

    /**
     * Returns the drawing supplier for the plot.
     *
     * @return The drawing supplier (possibly <code>null</code>).
     * 
     * @see #setDrawingSupplier(DrawingSupplier)
     */
    public DrawingSupplier getDrawingSupplier() {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[58]++;
        DrawingSupplier result = null;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[59]++;
        Plot p = getParent();
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[60]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((p != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[23]++;
            result = p.getDrawingSupplier();
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[61]++;

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[24]++;
            result = this.drawingSupplier;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[62]++;
        }
        return result;
    }

    /**
     * Sets the drawing supplier for the plot.  The drawing supplier is 
     * responsible for supplying a limitless (possibly repeating) sequence of 
     * <code>Paint</code>, <code>Stroke</code> and <code>Shape</code> objects 
     * that the plot's renderer(s) can use to populate its (their) tables.
     *
     * @param supplier  the new supplier.
     * 
     * @see #getDrawingSupplier()
     */
    public void setDrawingSupplier(DrawingSupplier supplier) {
        this.drawingSupplier = supplier;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[63]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[64]++;
    }

    /**
     * Returns the background image that is used to fill the plot's background 
     * area.
     *
     * @return The image (possibly <code>null</code>).
     * 
     * @see #setBackgroundImage(Image)
     */
    public Image getBackgroundImage() {
        return this.backgroundImage;
    }

    /**
     * Sets the background image for the plot and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param image  the image (<code>null</code> permitted).
     * 
     * @see #getBackgroundImage()
     */
    public void setBackgroundImage(Image image) {
        this.backgroundImage = image;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[65]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[66]++;
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
     * Sets the alignment for the background image and sends a 
     * {@link PlotChangeEvent} to all registered listeners.  Alignment options 
     * are defined by the {@link org.jfree.ui.Align} class in the JCommon 
     * class library.
     *
     * @param alignment  the alignment.
     * 
     * @see #getBackgroundImageAlignment()
     */
    public void setBackgroundImageAlignment(int alignment) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[67]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.backgroundImageAlignment != alignment) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[25]++;
            this.backgroundImageAlignment = alignment;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[68]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[69]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[26]++;}
    }

    /**
     * Returns the alpha transparency used to draw the background image.  This
     * is a value in the range 0.0f to 1.0f, where 0.0f is fully transparent
     * and 1.0f is fully opaque.
     * 
     * @return The alpha transparency.
     * 
     * @see #setBackgroundImageAlpha(float)
     */
    public float getBackgroundImageAlpha() {
        return this.backgroundImageAlpha;
    }
    
    /**
     * Sets the alpha transparency used when drawing the background image.
     * 
     * @param alpha  the alpha transparency (in the range 0.0f to 1.0f, where
     *     0.0f is fully transparent, and 1.0f is fully opaque).
     *     
     * @throws IllegalArgumentException if <code>alpha</code> is not within
     *     the specified range.
     *     
     * @see #getBackgroundImageAlpha()
     */
    public void setBackgroundImageAlpha(float alpha) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[70]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((alpha < 0.0f) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((alpha > 1.0f) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[27]++;
            throw new IllegalArgumentException(
                    "The 'alpha' value must be in the range 0.0f to 1.0f.");
} else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[28]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[71]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.backgroundImageAlpha != alpha) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[29]++;
            this.backgroundImageAlpha = alpha;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[72]++;
            this.notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[73]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[30]++;}
    }
    
    /**
     * Returns the flag that controls whether or not the plot outline is
     * drawn.  The default value is <code>true</code>.  Note that for 
     * historical reasons, the plot's outline paint and stroke can take on
     * <code>null</code> values, in which case the outline will not be drawn
     * even if this flag is set to <code>true</code>.
     * 
     * @return The outline visibility flag.
     * 
     * @since 1.0.6
     * 
     * @see #setOutlineVisible(boolean)
     */
    public boolean isOutlineVisible() {
        return this.outlineVisible;    
    }
    
    /**
     * Sets the flag that controls whether or not the plot's outline is
     * drawn, and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param visible  the new flag value.
     * 
     * @since 1.0.6
     * 
     * @see #isOutlineVisible()
     */
    public void setOutlineVisible(boolean visible) {
        this.outlineVisible = visible;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[74]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[75]++;
    }
    
    /**
     * Returns the stroke used to outline the plot area.
     *
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setOutlineStroke(Stroke)
     */
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }

    /**
     * Sets the stroke used to outline the plot area and sends a 
     * {@link PlotChangeEvent} to all registered listeners. If you set this 
     * attribute to <code>null</code>, no outline will be drawn.
     *
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getOutlineStroke()
     */
    public void setOutlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[76]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[31]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[77]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.outlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[33]++;
                this.outlineStroke = null;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[78]++;
                notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[79]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[34]++;}

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[32]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[80]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.outlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[35]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[81]++;
int CodeCoverConditionCoverageHelper_C19;
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.outlineStroke.equals(stroke)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[37]++;
                    return;
  // nothing to do
                } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[36]++;}
            this.outlineStroke = stroke;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[82]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[83]++;
        }
    }

    /**
     * Returns the color used to draw the outline of the plot area.
     *
     * @return The color (possibly <code>null<code>).
     * 
     * @see #setOutlinePaint(Paint)
     */
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }

    /**
     * Sets the paint used to draw the outline of the plot area and sends a 
     * {@link PlotChangeEvent} to all registered listeners.  If you set this 
     * attribute to <code>null</code>, no outline will be drawn.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getOutlinePaint()
     */
    public void setOutlinePaint(Paint paint) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[84]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[39]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[85]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((this.outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[41]++;
                this.outlinePaint = null;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[86]++;
                notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[87]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[42]++;}

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[40]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[88]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[43]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[89]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.outlinePaint.equals(paint)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[45]++;
                    return;
  // nothing to do
                } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[46]++;}

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[44]++;}
            this.outlinePaint = paint;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[90]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[91]++;
        }
    }

    /**
     * Returns the alpha-transparency for the plot foreground.
     *
     * @return The alpha-transparency.
     * 
     * @see #setForegroundAlpha(float)
     */
    public float getForegroundAlpha() {
        return this.foregroundAlpha;
    }

    /**
     * Sets the alpha-transparency for the plot and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param alpha  the new alpha transparency.
     * 
     * @see #getForegroundAlpha()
     */
    public void setForegroundAlpha(float alpha) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[92]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.foregroundAlpha != alpha) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[47]++;
            this.foregroundAlpha = alpha;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[93]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[94]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[48]++;}
    }

    /**
     * Returns the legend items for the plot.  By default, this method returns 
     * <code>null</code>.  Subclasses should override to return a 
     * {@link LegendItemCollection}.
     *
     * @return The legend items for the plot (possibly <code>null</code>).
     */
    public LegendItemCollection getLegendItems() {
        return null;
    }

    /**
     * Registers an object for notification of changes to the plot.
     *
     * @param listener  the object to be registered.
     * 
     * @see #removeChangeListener(PlotChangeListener)
     */
    public void addChangeListener(PlotChangeListener listener) {
        this.listenerList.add(PlotChangeListener.class, listener);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[95]++;
    }

    /**
     * Unregisters an object for notification of changes to the plot.
     *
     * @param listener  the object to be unregistered.
     * 
     * @see #addChangeListener(PlotChangeListener)
     */
    public void removeChangeListener(PlotChangeListener listener) {
        this.listenerList.remove(PlotChangeListener.class, listener);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[96]++;
    }

    /**
     * Notifies all registered listeners that the plot has been modified.
     *
     * @param event  information about the change event.
     */
    public void notifyListeners(PlotChangeEvent event) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[97]++;
        Object[] listeners = this.listenerList.getListenerList();
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[98]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.loops[1]++;


int CodeCoverConditionCoverageHelper_C25;
        for (int i = listeners.length - 2;(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); i -= 2) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.loops[1]--;
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.loops[2]--;
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.loops[3]++;
}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[99]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((listeners[i] == PlotChangeListener.class) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[49]++;
                ((PlotChangeListener) listeners[i + 1]).plotChanged(event);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[100]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[50]++;}
        }
    }

    /**
     * Draws the plot within the specified area.  The anchor is a point on the
     * chart that is specified externally (for instance, it may be the last
     * point of the last mouse click performed by the user) - plots can use or
     * ignore this value as they see fit. 
     * <br><br>
     * Subclasses need to provide an implementation of this method, obviously.
     * 
     * @param g2  the graphics device.
     * @param area  the plot area.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  the parent state (if any).
     * @param info  carries back plot rendering info.
     */
    public abstract void draw(Graphics2D g2,
                              Rectangle2D area,
                              Point2D anchor,
                              PlotState parentState,
                              PlotRenderingInfo info);
                              
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
        // some subclasses override this method completely, so don't put 
        // anything here that *must* be done
        fillBackground(g2, area);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[101]++;
        drawBackgroundImage(g2, area);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[102]++;
    }

    /**
     * Fills the specified area with the background paint.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * 
     * @see #getBackgroundPaint()
     * @see #getBackgroundAlpha()
     * @see #fillBackground(Graphics2D, Rectangle2D, PlotOrientation)
     */
    protected void fillBackground(Graphics2D g2, Rectangle2D area) {
        fillBackground(g2, area, PlotOrientation.VERTICAL);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[103]++;
    }
    
    /**
     * Fills the specified area with the background paint.  If the background
     * paint is an instance of <code>GradientPaint</code>, the gradient will
     * run in the direction suggested by the plot's orientation.
     * 
     * @param g2  the graphics target.
     * @param area  the plot area.
     * @param orientation  the plot orientation (<code>null</code> not 
     *         permitted).
     * 
     * @since 1.0.6
     */
    protected void fillBackground(Graphics2D g2, Rectangle2D area, 
            PlotOrientation orientation) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[104]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[51]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[52]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[105]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.backgroundPaint == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[53]++;
            return;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[54]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[106]++;
        Paint p = this.backgroundPaint;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[107]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((p instanceof GradientPaint) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[55]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[108]++;
            GradientPaint gp = (GradientPaint) p;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[109]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[57]++;
                p = new GradientPaint((float) area.getCenterX(), 
                        (float) area.getMaxY(), gp.getColor1(), 
                        (float) area.getCenterX(), (float) area.getMinY(), 
                        gp.getColor2());
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[110]++;

            }
            else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[58]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[111]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[59]++;
                p = new GradientPaint((float) area.getMinX(), 
                        (float) area.getCenterY(), gp.getColor1(), 
                        (float) area.getMaxX(), (float) area.getCenterY(), 
                        gp.getColor2());
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[112]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[60]++;}
}

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[56]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[113]++;            
        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                this.backgroundAlpha));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[114]++;
        g2.setPaint(p);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[115]++;
        g2.fill(area);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[116]++;
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[117]++;        
    }
    
    /**
     * Draws the background image (if there is one) aligned within the 
     * specified area.
     * 
     * @param g2  the graphics device.
     * @param area  the area.
     * 
     * @see #getBackgroundImage()
     * @see #getBackgroundImageAlignment()
     * @see #getBackgroundImageAlpha()
     */
    public void drawBackgroundImage(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[118]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.backgroundImage != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[61]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[119]++;
            Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                    this.backgroundImageAlpha));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[120]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[121]++;
            Rectangle2D dest = new Rectangle2D.Double(0.0, 0.0,
                    this.backgroundImage.getWidth(null), 
                    this.backgroundImage.getHeight(null));
            Align.align(dest, area, this.backgroundImageAlignment);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[122]++;
            g2.drawImage(this.backgroundImage, (int) dest.getX(), 
                    (int) dest.getY(), (int) dest.getWidth() + 1, 
                    (int) dest.getHeight() + 1, null);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[123]++;
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[124]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[62]++;}
    }
    
    /**
     * Draws the plot outline.  This method will be called during the chart 
     * drawing process and is declared public so that it can be accessed by the
     * renderers used by certain subclasses. You shouldn't need to call this 
     * method directly.
     * 
     * @param g2  the graphics device.
     * @param area  the area within which the plot should be drawn.
     */
    public void drawOutline(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[125]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((this.outlineVisible) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[63]++;
            return;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[64]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[126]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((this.outlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[65]++;
            g2.setStroke(this.outlineStroke);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[127]++;
            g2.setPaint(this.outlinePaint);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[128]++;
            g2.draw(area);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[129]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[66]++;}
    }

    /**
     * Draws a message to state that there is no data to plot.
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot should be drawn.
     */
    protected void drawNoDataMessage(Graphics2D g2, Rectangle2D area) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[130]++;
        Shape savedClip = g2.getClip();
        g2.clip(area);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[131]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[132]++;
        String message = this.noDataMessage;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[133]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((message != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[67]++;
            g2.setFont(this.noDataMessageFont);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[134]++;
            g2.setPaint(this.noDataMessagePaint);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[135]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[136]++;
            TextBlock block = TextUtilities.createTextBlock(
                    this.noDataMessage, this.noDataMessageFont, 
                    this.noDataMessagePaint, 0.9f * (float) area.getWidth(), 
                    new G2TextMeasurer(g2));
            block.draw(g2, (float) area.getCenterX(), 
                    (float) area.getCenterY(), TextBlockAnchor.CENTER);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[137]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[68]++;}
        g2.setClip(savedClip);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[138]++;
    }

    /**
     * Handles a 'click' on the plot.  Since the plot does not maintain any
     * information about where it has been drawn, the plot rendering info is 
     * supplied as an argument.
     *
     * @param x  the x coordinate (in Java2D space).
     * @param y  the y coordinate (in Java2D space).
     * @param info  an object containing information about the dimensions of 
     *              the plot.
     */
    public void handleClick(int x, int y, PlotRenderingInfo info) {
        // provides a 'no action' default
    }

    /**
     * Performs a zoom on the plot.  Subclasses should override if zooming is 
     * appropriate for the type of plot.
     *
     * @param percent  the zoom percentage.
     */
    public void zoom(double percent) {
        // do nothing by default.
    }

    /**
     * Receives notification of a change to one of the plot's axes.
     *
     * @param event  information about the event (not used here).
     */
    public void axisChanged(AxisChangeEvent event) {
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[139]++;
    }

    /**
     * Receives notification of a change to the plot's dataset.
     * <P>
     * The plot reacts by passing on a plot change event to all registered 
     * listeners.
     *
     * @param event  information about the event (not used here).
     */
    public void datasetChanged(DatasetChangeEvent event) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[140]++;
        PlotChangeEvent newEvent = new PlotChangeEvent(this);
        newEvent.setType(ChartChangeEventType.DATASET_UPDATED);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[141]++;
        notifyListeners(newEvent);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[142]++;
    }
    
    /**
     * Receives notification of a change to a marker that is assigned to the
     * plot.
     * 
     * @param event  the event.
     * 
     * @since 1.0.3
     */
    public void markerChanged(MarkerChangeEvent event) {
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[143]++;
    }

    /**
     * Adjusts the supplied x-value.
     *
     * @param x  the x-value.
     * @param w1  width 1.
     * @param w2  width 2.
     * @param edge  the edge (left or right).
     *
     * @return The adjusted x-value.
     */
    protected double getRectX(double x, double w1, double w2, 
                              RectangleEdge edge) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[144]++;

        double result = x;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[145]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[69]++;
            result = result + w1;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[146]++;

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[70]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[147]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[71]++;
            result = result + w2;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[148]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[72]++;}
}
        return result;

    }

    /**
     * Adjusts the supplied y-value.
     *
     * @param y  the x-value.
     * @param h1  height 1.
     * @param h2  height 2.
     * @param edge  the edge (top or bottom).
     *
     * @return The adjusted y-value.
     */
    protected double getRectY(double y, double h1, double h2, 
                              RectangleEdge edge) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[149]++;

        double result = y;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[150]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[73]++;
            result = result + h1;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[151]++;

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[74]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[152]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[75]++;
            result = result + h2;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[153]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[76]++;}
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
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[154]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[77]++;
            return true;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[78]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[155]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((obj instanceof Plot) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[80]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[156]++;
        Plot that = (Plot) obj;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[157]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.noDataMessage, that.noDataMessage)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[81]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[82]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[158]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            this.noDataMessageFont, that.noDataMessageFont
        )) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[84]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[159]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.noDataMessagePaint, 
                that.noDataMessagePaint)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[86]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[160]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.insets, that.insets)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[87]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[88]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[161]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.outlineVisible != that.outlineVisible) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[89]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[90]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[162]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[92]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[163]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[93]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[94]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[164]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[95]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[96]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[165]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.backgroundImage, 
                that.backgroundImage)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[97]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[98]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[166]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.backgroundImageAlignment != that.backgroundImageAlignment) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[99]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[100]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[167]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((this.backgroundImageAlpha != that.backgroundImageAlpha) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[101]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[102]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[168]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((this.foregroundAlpha != that.foregroundAlpha) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[103]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[104]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[169]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((this.backgroundAlpha != that.backgroundAlpha) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[105]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[106]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[170]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((this.drawingSupplier.equals(that.drawingSupplier)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[107]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[108]++;}
        return true;
    }

    /**
     * Creates a clone of the plot.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if some component of the plot does not
     *         support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[171]++;

        Plot clone = (Plot) super.clone();
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[172]++;
int CodeCoverConditionCoverageHelper_C56;
        // private Plot parent <-- don't clone the parent plot, but take care 
        // childs in combined plots instead
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((this.datasetGroup != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[109]++;
            clone.datasetGroup 
                = (DatasetGroup) ObjectUtilities.clone(this.datasetGroup);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[173]++;

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[110]++;}
        clone.drawingSupplier 
            = (DrawingSupplier) ObjectUtilities.clone(this.drawingSupplier);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[174]++;
        clone.listenerList = new EventListenerList();
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[175]++;
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
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[176]++;
        SerialUtilities.writePaint(this.noDataMessagePaint, stream);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[177]++;
        SerialUtilities.writeStroke(this.outlineStroke, stream);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[178]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[179]++;
        // backgroundImage
        SerialUtilities.writePaint(this.backgroundPaint, stream);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[180]++;
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
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[181]++;
        this.noDataMessagePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[182]++;
        this.outlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[183]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[184]++;
        // backgroundImage
        this.backgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[185]++;

        this.listenerList = new EventListenerList();
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[186]++;

    }

    /**
     * Resolves a domain axis location for a given plot orientation.
     *
     * @param location  the location (<code>null</code> not permitted).
     * @param orientation  the orientation (<code>null</code> not permitted).
     *
     * @return The edge (never <code>null</code>).
     */
    public static RectangleEdge resolveDomainAxisLocation(
            AxisLocation location, PlotOrientation orientation) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[187]++;
int CodeCoverConditionCoverageHelper_C57;
        
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((location == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[111]++;
            throw new IllegalArgumentException("Null 'location' argument.");
   
        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[112]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[188]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[113]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[114]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[189]++;

        RectangleEdge result = null;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[190]++;
int CodeCoverConditionCoverageHelper_C59;
        
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((location == AxisLocation.TOP_OR_RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[115]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[191]++;
int CodeCoverConditionCoverageHelper_C60;
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[117]++;
                result = RectangleEdge.RIGHT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[192]++;

            }
            else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[118]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[193]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[119]++;
                result = RectangleEdge.TOP;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[194]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[120]++;}
}

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[116]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[195]++;
int CodeCoverConditionCoverageHelper_C62; if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((location == AxisLocation.TOP_OR_LEFT) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[121]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[196]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[123]++;
                result = RectangleEdge.LEFT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[197]++;

            }
            else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[124]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[198]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[125]++;
                result = RectangleEdge.TOP;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[199]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[126]++;}
}

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[122]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[200]++;
int CodeCoverConditionCoverageHelper_C65; if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((location == AxisLocation.BOTTOM_OR_RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[127]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[201]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[129]++;
                result = RectangleEdge.RIGHT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[202]++;

            }
            else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[130]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[203]++;
int CodeCoverConditionCoverageHelper_C67; if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[131]++;
                result = RectangleEdge.BOTTOM;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[204]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[132]++;}
}

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[128]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[205]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((location == AxisLocation.BOTTOM_OR_LEFT) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[133]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[206]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[135]++;
                result = RectangleEdge.LEFT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[207]++;

            }
            else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[136]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[208]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[137]++;
                result = RectangleEdge.BOTTOM;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[209]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[138]++;}
}

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[134]++;}
}
}
}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[210]++;
int CodeCoverConditionCoverageHelper_C71;
        // the above should cover all the options...
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[139]++;
            throw new IllegalStateException("resolveDomainAxisLocation()");

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[140]++;}
        return result;
        
    }

    /**
     * Resolves a range axis location for a given plot orientation.
     *
     * @param location  the location (<code>null</code> not permitted).
     * @param orientation  the orientation (<code>null</code> not permitted).
     *
     * @return The edge (never <code>null</code>).
     */
    public static RectangleEdge resolveRangeAxisLocation(
            AxisLocation location, PlotOrientation orientation) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[211]++;
int CodeCoverConditionCoverageHelper_C72;

        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((location == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[141]++;
            throw new IllegalArgumentException("Null 'location' argument.");
   
        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[142]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[212]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((orientation == null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[143]++;
            throw new IllegalArgumentException("Null 'orientation' argument.");

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[144]++;}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[213]++;

        RectangleEdge result = null;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[214]++;
int CodeCoverConditionCoverageHelper_C74;
        
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((location == AxisLocation.TOP_OR_RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[145]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[215]++;
int CodeCoverConditionCoverageHelper_C75;
            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[147]++;
                result = RectangleEdge.TOP;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[216]++;

            }
            else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[148]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[217]++;
int CodeCoverConditionCoverageHelper_C76; if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[149]++;
                result = RectangleEdge.RIGHT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[218]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[150]++;}
}

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[146]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[219]++;
int CodeCoverConditionCoverageHelper_C77; if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((location == AxisLocation.TOP_OR_LEFT) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[151]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[220]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[153]++;
                result = RectangleEdge.TOP;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[221]++;

            }
            else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[154]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[222]++;
int CodeCoverConditionCoverageHelper_C79; if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[155]++;
                result = RectangleEdge.LEFT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[223]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[156]++;}
}

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[152]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[224]++;
int CodeCoverConditionCoverageHelper_C80; if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((location == AxisLocation.BOTTOM_OR_RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[157]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[225]++;
int CodeCoverConditionCoverageHelper_C81;
            if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[159]++;
                result = RectangleEdge.BOTTOM;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[226]++;

            }
            else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[160]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[227]++;
int CodeCoverConditionCoverageHelper_C82; if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[161]++;
                result = RectangleEdge.RIGHT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[228]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[162]++;}
}

        }
        else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[158]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[229]++;
int CodeCoverConditionCoverageHelper_C83; if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((location == AxisLocation.BOTTOM_OR_LEFT) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[163]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[230]++;
int CodeCoverConditionCoverageHelper_C84;
            if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[165]++;
                result = RectangleEdge.BOTTOM;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[231]++;

            }
            else {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[166]++;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[232]++;
int CodeCoverConditionCoverageHelper_C85; if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[167]++;
                result = RectangleEdge.LEFT;
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[233]++;

            } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[168]++;}
}

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[164]++;}
}
}
}
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.statements[234]++;
int CodeCoverConditionCoverageHelper_C86;

        // the above should cover all the options...
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$8p3ca1f6lkbo0x.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[169]++;
            throw new IllegalStateException("resolveRangeAxisLocation()");

        } else {
  CodeCoverCoverageCounter$8p3ca1f6lkbo0x.branches[170]++;}
        return result;
        
    }

}

class CodeCoverCoverageCounter$8p3ca1f6lkbo0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8p3ca1f6lkbo0x ());
  }
    public static long[] statements = new long[235];
    public static long[] branches = new long[171];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[87];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.Plot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 86; i++) {
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

  public CodeCoverCoverageCounter$8p3ca1f6lkbo0x () {
    super("org.jfree.chart.plot.Plot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 234; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 170; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 86; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.Plot.java");
      for (int i = 1; i <= 234; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 170; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 86; i++) {
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

