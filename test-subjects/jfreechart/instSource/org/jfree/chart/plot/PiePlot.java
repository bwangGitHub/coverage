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
 * ------------
 * PiePlot.java
 * ------------
 * (C) Copyright 2000-2007, by Andrzej Porebski and Contributors.
 *
 * Original Author:  Andrzej Porebski;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Martin Cordova (percentages in labels);
 *                   Richard Atkinson (URL support for image maps);
 *                   Christian W. Zuckschwerdt;
 *                   Arnaud Lelievre;
 *                   Andreas Schroeder (very minor);
 *
 * Changes
 * -------
 * 21-Jun-2001 : Removed redundant JFreeChart parameter from constructors (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 15-Oct-2001 : Data source classes moved to com.jrefinery.data.* (DG);
 * 19-Oct-2001 : Moved series paint and stroke methods from JFreeChart.java to 
 *               Plot.java (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 * 13-Nov-2001 : Modified plot subclasses so that null axes are possible for 
 *               pie plot (DG);
 * 17-Nov-2001 : Added PieDataset interface and amended this class accordingly,
 *               and completed removal of BlankAxis class as it is no longer 
 *               required (DG);
 * 19-Nov-2001 : Changed 'drawCircle' property to 'circular' property (DG);
 * 21-Nov-2001 : Added options for exploding pie sections and filled out range 
 *               of properties (DG);
 *               Added option for percentages in chart labels, based on code
 *               by Martin Cordova (DG);
 * 30-Nov-2001 : Changed default font from "Arial" --> "SansSerif" (DG);
 * 12-Dec-2001 : Removed unnecessary 'throws' clause in constructor (DG);
 * 13-Dec-2001 : Added tooltips (DG);
 * 16-Jan-2002 : Renamed tooltips class (DG);
 * 22-Jan-2002 : Fixed bug correlating legend labels with pie data (DG);
 * 05-Feb-2002 : Added alpha-transparency to plot class, and updated 
 *               constructors accordingly (DG);
 * 06-Feb-2002 : Added optional background image and alpha-transparency to Plot
 *               and subclasses.  Clipped drawing within plot area (DG);
 * 26-Mar-2002 : Added an empty zoom method (DG);
 * 18-Apr-2002 : PieDataset is no longer sorted (oldman);
 * 23-Apr-2002 : Moved dataset from JFreeChart to Plot.  Added 
 *               getLegendItemLabels() method (DG);
 * 19-Jun-2002 : Added attributes to control starting angle and direction 
 *               (default is now clockwise) (DG);
 * 25-Jun-2002 : Removed redundant imports (DG);
 * 02-Jul-2002 : Fixed sign of percentage bug introduced in 0.9.2 (DG);
 * 16-Jul-2002 : Added check for null dataset in getLegendItemLabels() (DG);
 * 30-Jul-2002 : Moved summation code to DatasetUtilities (DG);
 * 05-Aug-2002 : Added URL support for image maps - new member variable for
 *               urlGenerator, modified constructor and minor change to the 
 *               draw method (RA);
 * 18-Sep-2002 : Modified the percent label creation and added setters for the
 *               formatters (AS);
 * 24-Sep-2002 : Added getLegendItems() method (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 09-Oct-2002 : Added check for null entity collection (DG);
 * 30-Oct-2002 : Changed PieDataset interface (DG);
 * 18-Nov-2002 : Changed CategoryDataset to TableDataset (DG);
 * 02-Jan-2003 : Fixed "no data" message (DG);
 * 23-Jan-2003 : Modified to extract data from rows OR columns in 
 *               CategoryDataset (DG);
 * 14-Feb-2003 : Fixed label drawing so that foreground alpha does not apply 
 *               (bug id 685536) (DG);
 * 07-Mar-2003 : Modified to pass pieIndex on to PieSectionEntity and tooltip 
 *               and URL generators (DG);
 * 21-Mar-2003 : Added a minimum angle for drawing arcs 
 *               (see bug id 620031) (DG);
 * 24-Apr-2003 : Switched around PieDataset and KeyedValuesDataset (DG);
 * 02-Jun-2003 : Fixed bug 721733 (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 29-Aug-2003 : Fixed bug 796936 (null pointer on setOutlinePaint()) (DG);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 05-Nov-2003 : Fixed missing legend bug (DG);
 * 10-Nov-2003 : Re-added the DatasetChangeListener to constructors (CZ);
 * 29-Jan-2004 : Fixed clipping bug in draw() method (DG);
 * 11-Mar-2004 : Major overhaul to improve labelling (DG);
 * 31-Mar-2004 : Made an adjustment for the plot area when the label generator 
 *               is null.  Fixed null pointer exception when the label 
 *               generator returns null for a label (DG);
 * 06-Apr-2004 : Added getter, setter, serialization and draw support for 
 *               labelBackgroundPaint (AS);
 * 08-Apr-2004 : Added flag to control whether null values are ignored or 
 *               not (DG);
 * 15-Apr-2004 : Fixed some minor warnings from Eclipse (DG);
 * 26-Apr-2004 : Added attributes for label outline and shadow (DG);
 * 04-Oct-2004 : Renamed ShapeUtils --> ShapeUtilities (DG);
 * 04-Nov-2004 : Fixed null pointer exception with new LegendTitle class (DG);
 * 09-Nov-2004 : Added user definable legend item shape (DG);
 * 25-Nov-2004 : Added new legend label generator (DG);
 * 20-Apr-2005 : Added a tool tip generator for legend labels (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 10-May-2005 : Added flag to control visibility of label linking lines, plus
 *               another flag to control the handling of zero values (DG);
 * 08-Jun-2005 : Fixed bug in getLegendItems() method (not respecting flags
 *               for ignoring null and zero values), and fixed equals() method 
 *               to handle GradientPaint (DG);
 * 15-Jul-2005 : Added sectionOutlinesVisible attribute (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 09-Jan-2006 : Fixed bug 1400442, inconsistent treatment of null and zero
 *               values in dataset (DG);
 * 28-Feb-2006 : Fixed bug 1440415, bad distribution of pie section 
 *               labels (DG);
 * 27-Sep-2006 : Initialised baseSectionPaint correctly, added lookup methods
 *               for section paint, outline paint and outline stroke (DG);
 * 27-Sep-2006 : Refactored paint and stroke methods to use keys rather than
 *               section indices (DG);
 * 03-Oct-2006 : Replaced call to JRE 1.5 method (DG);
 * 23-Nov-2006 : Added support for URLs for the legend items (DG);
 * 24-Nov-2006 : Cloning fixes (DG);
 * 17-Apr-2007 : Check for null label in legend items (DG);
 * 19-Apr-2007 : Deprecated override settings (DG);
 * 18-May-2007 : Set dataset for LegendItem (DG);
 * 14-Jun-2007 : Added label distributor attribute (DG);
 * 18-Jul-2007 : Added simple label option (DG);
 * 21-Nov-2007 : Fixed labelling bugs, added debug code, restored default
 *               white background (DG); 
 *    
 */

package org.jfree.chart.plot;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
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
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.PaintMap;
import org.jfree.chart.StrokeMap;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.urls.PieURLGenerator;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.PieDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.text.G2TextMeasurer;
import org.jfree.text.TextBlock;
import org.jfree.text.TextBox;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.util.Rotation;
import org.jfree.util.ShapeUtilities;
import org.jfree.util.UnitType;

/**
 * A plot that displays data in the form of a pie chart, using data from any 
 * class that implements the {@link PieDataset} interface.
 * <P>
 * Special notes:
 * <ol>
 * <li>the default starting point is 12 o'clock and the pie sections proceed
 * in a clockwise direction, but these settings can be changed;</li>
 * <li>negative values in the dataset are ignored;</li>
 * <li>there are utility methods for creating a {@link PieDataset} from a
 * {@link org.jfree.data.category.CategoryDataset};</li>
 * </ol>
 *
 * @see Plot
 * @see PieDataset
 */
public class PiePlot extends Plot implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -795612466005590431L;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[1]++;
  }
    
    /** The default interior gap. */
    public static final double DEFAULT_INTERIOR_GAP = 0.08;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[2]++;
  }

    /** The maximum interior gap (currently 40%). */
    public static final double MAX_INTERIOR_GAP = 0.40;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[3]++;
  }

    /** The default starting angle for the pie chart. */
    public static final double DEFAULT_START_ANGLE = 90.0;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[4]++;
  }

    /** The default section label font. */
    public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 
            Font.PLAIN, 10);
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[5]++;
  }

    /** The default section label paint. */
    public static final Paint DEFAULT_LABEL_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[6]++;
  }
    
    /** The default section label background paint. */
    public static final Paint DEFAULT_LABEL_BACKGROUND_PAINT = new Color(255, 
            255, 192);
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[7]++;
  }

    /** The default section label outline paint. */
    public static final Paint DEFAULT_LABEL_OUTLINE_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[8]++;
  }
    
    /** The default section label outline stroke. */
    public static final Stroke DEFAULT_LABEL_OUTLINE_STROKE = new BasicStroke(
            0.5f);
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[9]++;
  }
    
    /** The default section label shadow paint. */
    public static final Paint DEFAULT_LABEL_SHADOW_PAINT = new Color(151, 151, 
            151, 128);
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[10]++;
  }
    
    /** The default minimum arc angle to draw. */
    public static final double DEFAULT_MINIMUM_ARC_ANGLE_TO_DRAW = 0.00001;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[11]++;
  }

    /** The dataset for the pie chart. */
    private PieDataset dataset;

    /** The pie index (used by the {@link MultiplePiePlot} class). */
    private int pieIndex;

    /** 
     * The amount of space left around the outside of the pie plot, expressed 
     * as a percentage of the plot area width and height. 
     */
    private double interiorGap;

    /** Flag determining whether to draw an ellipse or a perfect circle. */
    private boolean circular;

    /** The starting angle. */
    private double startAngle;

    /** The direction for the pie segments. */
    private Rotation direction;

    /** 
     * The paint for ALL sections (overrides list).
     * 
     * @deprecated This field is redundant, it is sufficient to use 
     *     sectionPaintMap and baseSectionPaint.  Deprecated as of version 
     *     1.0.6.
     */
    private transient Paint sectionPaint;

    /** The section paint map. */
    private PaintMap sectionPaintMap;

    /** The base section paint (fallback). */
    private transient Paint baseSectionPaint;

    /** 
     * A flag that controls whether or not an outline is drawn for each
     * section in the plot.
     */
    private boolean sectionOutlinesVisible;

    /** 
     * The outline paint for ALL sections (overrides list). 
     * 
     * @deprecated This field is redundant, it is sufficient to use 
     *     sectionOutlinePaintMap and baseSectionOutlinePaint.  Deprecated as 
     *     of version 1.0.6.
     */
    private transient Paint sectionOutlinePaint;

    /** The section outline paint map. */
    private PaintMap sectionOutlinePaintMap;

    /** The base section outline paint (fallback). */
    private transient Paint baseSectionOutlinePaint;

    /** 
     * The outline stroke for ALL sections (overrides list). 
     * 
     * @deprecated This field is redundant, it is sufficient to use 
     *     sectionOutlineStrokeMap and baseSectionOutlineStroke.  Deprecated as 
     *     of version 1.0.6.
     */
    private transient Stroke sectionOutlineStroke;

    /** The section outline stroke map. */
    private StrokeMap sectionOutlineStrokeMap;

    /** The base section outline stroke (fallback). */
    private transient Stroke baseSectionOutlineStroke;

    /** The shadow paint. */
    private transient Paint shadowPaint = Color.gray;
  {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[12]++;
  }

    /** The x-offset for the shadow effect. */
    private double shadowXOffset = 4.0f;
  {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[13]++;
  }
    
    /** The y-offset for the shadow effect. */
    private double shadowYOffset = 4.0f;
  {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[14]++;
  }
    
    /** The percentage amount to explode each pie section. */
    private Map explodePercentages;
    
    /** The section label generator. */
    private PieSectionLabelGenerator labelGenerator;

    /** The font used to display the section labels. */
    private Font labelFont;

    /** The color used to draw the section labels. */
    private transient Paint labelPaint;
    
    /** 
     * The color used to draw the background of the section labels.  If this
     * is <code>null</code>, the background is not filled.
     */
    private transient Paint labelBackgroundPaint;

    /** 
     * The paint used to draw the outline of the section labels 
     * (<code>null</code> permitted). 
     */
    private transient Paint labelOutlinePaint;
    
    /** 
     * The stroke used to draw the outline of the section labels 
     * (<code>null</code> permitted). 
     */
    private transient Stroke labelOutlineStroke;
    
    /** 
     * The paint used to draw the shadow for the section labels 
     * (<code>null</code> permitted). 
     */
    private transient Paint labelShadowPaint;
    
    /**
     * A flag that controls whether simple or extended labels are used.
     * 
     * @since 1.0.7
     */
    private boolean simpleLabels = true;
  {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[15]++;
  }
    
    /**
     * The padding between the labels and the label outlines.  This is not
     * allowed to be <code>null</code>.
     * 
     * @since 1.0.7
     */
    private RectangleInsets labelPadding;
    
    /**
     * The simple label offset.
     * 
     * @since 1.0.7
     */
    private RectangleInsets simpleLabelOffset;
    
    /** The maximum label width as a percentage of the plot width. */
    private double maximumLabelWidth = 0.14;
  {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[16]++;
  }
    
    /** 
     * The gap between the labels and the link corner, as a percentage of the 
     * plot width. 
     */
    private double labelGap = 0.025;
  {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[17]++;
  }

    /** A flag that controls whether or not the label links are drawn. */
    private boolean labelLinksVisible;
    
    /** The link margin. */
    private double labelLinkMargin = 0.025;
  {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[18]++;
  }
    
    /** The paint used for the label linking lines. */
    private transient Paint labelLinkPaint = Color.black;
  {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[19]++;
  }
    
    /** The stroke used for the label linking lines. */
    private transient Stroke labelLinkStroke = new BasicStroke(0.5f);
  {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[20]++;
  }
    
    /** 
     * The pie section label distributor.
     * 
     * @since 1.0.6
     */
    private AbstractPieLabelDistributor labelDistributor;
    
    /** The tooltip generator. */
    private PieToolTipGenerator toolTipGenerator;

    /** The URL generator. */
    private PieURLGenerator urlGenerator;
    
    /** The legend label generator. */
    private PieSectionLabelGenerator legendLabelGenerator;
    
    /** A tool tip generator for the legend. */
    private PieSectionLabelGenerator legendLabelToolTipGenerator;
    
    /** 
     * A URL generator for the legend items (optional).  
     *
     * @since 1.0.4. 
     */
    private PieURLGenerator legendLabelURLGenerator;
    
    /** 
     * A flag that controls whether <code>null</code> values are ignored.  
     */
    private boolean ignoreNullValues;
    
    /**
     * A flag that controls whether zero values are ignored.
     */
    private boolean ignoreZeroValues;

    /** The legend item shape. */
    private transient Shape legendItemShape;
    
    /**
     * The smallest arc angle that will get drawn (this is to avoid a bug in 
     * various Java implementations that causes the JVM to crash).  See this 
     * link for details:
     *
     * http://www.jfree.org/phpBB2/viewtopic.php?t=2707
     *
     * ...and this bug report in the Java Bug Parade:
     *
     * http://developer.java.sun.com/developer/bugParade/bugs/4836495.html
     */
    private double minimumArcAngleToDraw;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources =
            ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[21]++;
  }

    /** 
     * This debug flag controls whether or not an outline is drawn showing the 
     * interior of the plot region.  This is drawn as a lightGray rectangle 
     * showing the padding provided by the 'interiorGap' setting.
     */
    static final boolean DEBUG_DRAW_INTERIOR = false;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[22]++;
  }
    
    /** 
     * This debug flag controls whether or not an outline is drawn showing the 
     * link area (in blue) and link ellipse (in yellow).  This controls where 
     * the label links have 'elbow' points.
     */
    static final boolean DEBUG_DRAW_LINK_AREA = false;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[23]++;
  }
    
    /**
     * This debug flag controls whether or not an outline is drawn showing
     * the pie area (in green).
     */
    static final boolean DEBUG_DRAW_PIE_AREA = false;
  static {
    CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[24]++;
  }
    
    /**
     * Creates a new plot.  The dataset is initially set to <code>null</code>.
     */
    public PiePlot() {
        this(null);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[25]++;
    }

    /**
     * Creates a plot that will draw a pie chart for the specified dataset.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public PiePlot(PieDataset dataset) {
        super();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[26]++;
        this.dataset = dataset;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[27]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[28]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[1]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[29]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[2]++;}
        this.pieIndex = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[30]++;
        
        this.interiorGap = DEFAULT_INTERIOR_GAP;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[31]++;
        this.circular = true;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[32]++;
        this.startAngle = DEFAULT_START_ANGLE;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[33]++;
        this.direction = Rotation.CLOCKWISE;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[34]++;
        this.minimumArcAngleToDraw = DEFAULT_MINIMUM_ARC_ANGLE_TO_DRAW;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[35]++;

        this.sectionPaint = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[36]++;
        this.sectionPaintMap = new PaintMap();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[37]++;
        this.baseSectionPaint = Color.gray;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[38]++;

        this.sectionOutlinesVisible = true;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[39]++;
        this.sectionOutlinePaint = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[40]++;
        this.sectionOutlinePaintMap = new PaintMap();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[41]++;
        this.baseSectionOutlinePaint = DEFAULT_OUTLINE_PAINT;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[42]++;

        this.sectionOutlineStroke = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[43]++;
        this.sectionOutlineStrokeMap = new StrokeMap();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[44]++;
        this.baseSectionOutlineStroke = DEFAULT_OUTLINE_STROKE;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[45]++;
        
        this.explodePercentages = new TreeMap();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[46]++;

        this.labelGenerator = new StandardPieSectionLabelGenerator();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[47]++;
        this.labelFont = DEFAULT_LABEL_FONT;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[48]++;
        this.labelPaint = DEFAULT_LABEL_PAINT;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[49]++;
        this.labelBackgroundPaint = DEFAULT_LABEL_BACKGROUND_PAINT;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[50]++;
        this.labelOutlinePaint = DEFAULT_LABEL_OUTLINE_PAINT;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[51]++;
        this.labelOutlineStroke = DEFAULT_LABEL_OUTLINE_STROKE;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[52]++;
        this.labelShadowPaint = DEFAULT_LABEL_SHADOW_PAINT;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[53]++;
        this.labelLinksVisible = true;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[54]++;
        this.labelDistributor = new PieLabelDistributor(0);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[55]++;
        
        this.simpleLabels = false;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[56]++;
        this.simpleLabelOffset = new RectangleInsets(UnitType.RELATIVE, 0.18, 
                0.18, 0.18, 0.18);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[57]++;
        this.labelPadding = new RectangleInsets(2, 2, 2, 2);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[58]++;
        
        this.toolTipGenerator = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[59]++;
        this.urlGenerator = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[60]++;
        this.legendLabelGenerator = new StandardPieSectionLabelGenerator();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[61]++;
        this.legendLabelToolTipGenerator = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[62]++;
        this.legendLabelURLGenerator = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[63]++;
        this.legendItemShape = Plot.DEFAULT_LEGEND_ITEM_CIRCLE;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[64]++;
        
        this.ignoreNullValues = false;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[65]++;
        this.ignoreZeroValues = false;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[66]++;
    }

    /**
     * Returns the dataset.
     *
     * @return The dataset (possibly <code>null</code>).
     * 
     * @see #setDataset(PieDataset)
     */
    public PieDataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets the dataset and sends a {@link DatasetChangeEvent} to 'this'.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset()
     */
    public void setDataset(PieDataset dataset) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[67]++;
        // if there is an existing dataset, remove the plot from the list of 
        // change listeners...
        PieDataset existing = this.dataset;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[68]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[3]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[69]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[4]++;}

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[70]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[71]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[5]++;
            setDatasetGroup(dataset.getGroup());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[72]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[73]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[6]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[74]++;

        // send a dataset change event to self...
        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[75]++;
    }
    
    /**
     * Returns the pie index (this is used by the {@link MultiplePiePlot} class
     * to track subplots).
     * 
     * @return The pie index.
     * 
     * @see #setPieIndex(int)
     */
    public int getPieIndex() {
        return this.pieIndex;
    }
    
    /**
     * Sets the pie index (this is used by the {@link MultiplePiePlot} class to 
     * track subplots).
     * 
     * @param index  the index.
     * 
     * @see #getPieIndex()
     */
    public void setPieIndex(int index) {
        this.pieIndex = index;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[76]++;
    }
    
    /**
     * Returns the start angle for the first pie section.  This is measured in 
     * degrees starting from 3 o'clock and measuring anti-clockwise.
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
     * registered listeners.  The initial default value is 90 degrees, which 
     * corresponds to 12 o'clock.  A value of zero corresponds to 3 o'clock...
     * this is the encoding used by Java's Arc2D class.
     *
     * @param angle  the angle (in degrees).
     * 
     * @see #getStartAngle()
     */
    public void setStartAngle(double angle) {
        this.startAngle = angle;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[77]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[78]++;
    }

    /**
     * Returns the direction in which the pie sections are drawn (clockwise or 
     * anti-clockwise).
     *
     * @return The direction (never <code>null</code>).
     * 
     * @see #setDirection(Rotation)
     */
    public Rotation getDirection() {
        return this.direction;
    }

    /**
     * Sets the direction in which the pie sections are drawn and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param direction  the direction (<code>null</code> not permitted).
     * 
     * @see #getDirection()
     */
    public void setDirection(Rotation direction) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[79]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((direction == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[7]++;
            throw new IllegalArgumentException("Null 'direction' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[8]++;}
        this.direction = direction;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[80]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[81]++;

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
     * registered listeners.  This controls the space between the edges of the 
     * pie plot and the plot area itself (the region where the section labels 
     * appear).
     *
     * @param percent  the gap (as a percentage of the available drawing space).
     * 
     * @see #getInteriorGap()
     */
    public void setInteriorGap(double percent) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[82]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((percent < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((percent > MAX_INTERIOR_GAP) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[9]++;
            throw new IllegalArgumentException(
                "Invalid 'percent' (" + percent + ") argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[10]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[83]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.interiorGap != percent) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[11]++;
            this.interiorGap = percent;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[84]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[85]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[12]++;}

    }

    /**
     * Returns a flag indicating whether the pie chart is circular, or
     * stretched into an elliptical shape.
     *
     * @return A flag indicating whether the pie chart is circular.
     * 
     * @see #setCircular(boolean)
     */
    public boolean isCircular() {
        return this.circular;
    }

    /**
     * A flag indicating whether the pie chart is circular, or stretched into
     * an elliptical shape.
     *
     * @param flag  the new value.
     * 
     * @see #isCircular()
     */
    public void setCircular(boolean flag) {
        setCircular(flag, true);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[86]++;
    }

    /**
     * Sets the circular attribute and, if requested, sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param circular  the new value of the flag.
     * @param notify  notify listeners?
     * 
     * @see #isCircular()
     */
    public void setCircular(boolean circular, boolean notify) {
        this.circular = circular;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[87]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[88]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[13]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[89]++;
   
        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[14]++;}
    }

    /**
     * Returns the flag that controls whether <code>null</code> values in the 
     * dataset are ignored.  
     * 
     * @return A boolean.
     * 
     * @see #setIgnoreNullValues(boolean)
     */
    public boolean getIgnoreNullValues() {
        return this.ignoreNullValues;   
    }
    
    /**
     * Sets a flag that controls whether <code>null</code> values are ignored, 
     * and sends a {@link PlotChangeEvent} to all registered listeners.  At 
     * present, this only affects whether or not the key is presented in the 
     * legend.
     * 
     * @param flag  the flag.
     * 
     * @see #getIgnoreNullValues()
     * @see #setIgnoreZeroValues(boolean)
     */
    public void setIgnoreNullValues(boolean flag) {
        this.ignoreNullValues = flag;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[90]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[91]++;
    }
    
    /**
     * Returns the flag that controls whether zero values in the 
     * dataset are ignored.  
     * 
     * @return A boolean.
     * 
     * @see #setIgnoreZeroValues(boolean)
     */
    public boolean getIgnoreZeroValues() {
        return this.ignoreZeroValues;   
    }
    
    /**
     * Sets a flag that controls whether zero values are ignored, 
     * and sends a {@link PlotChangeEvent} to all registered listeners.  This 
     * only affects whether or not a label appears for the non-visible
     * pie section.
     * 
     * @param flag  the flag.
     * 
     * @see #getIgnoreZeroValues()
     * @see #setIgnoreNullValues(boolean)
     */
    public void setIgnoreZeroValues(boolean flag) {
        this.ignoreZeroValues = flag;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[92]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[93]++;
    }
    
    //// SECTION PAINT ////////////////////////////////////////////////////////

    /**
     * Returns the paint for the specified section.  This is equivalent to
     * <code>lookupSectionPaint(section, false)</code>.
     * 
     * @param key  the section key.
     * 
     * @return The paint for the specified section.
     * 
     * @since 1.0.3
     * 
     * @see #lookupSectionPaint(Comparable, boolean)
     */
    protected Paint lookupSectionPaint(Comparable key) {
        return lookupSectionPaint(key, false);        
    }
    
    /**
     * Returns the paint for the specified section.  The lookup involves these
     * steps:
     * <ul>
     * <li>if {@link #getSectionPaint()} is non-<code>null</code>, return 
     *         it;</li>
     * <li>if {@link #getSectionPaint(int)} is non-<code>null</code> return 
     *         it;</li>
     * <li>if {@link #getSectionPaint(int)} is <code>null</code> but 
     *         <code>autoPopulate</code> is <code>true</code>, attempt to fetch
     *         a new paint from the drawing supplier 
     *         ({@link #getDrawingSupplier()});
     * <li>if all else fails, return {@link #getBaseSectionPaint()}.
     * </ul> 
     * 
     * @param key  the section key.
     * @param autoPopulate  a flag that controls whether the drawing supplier 
     *     is used to auto-populate the section paint settings.
     *     
     * @return The paint.
     * 
     * @since 1.0.3
     */
    protected Paint lookupSectionPaint(Comparable key, boolean autoPopulate) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[94]++;
        
        // is there an override?
        Paint result = getSectionPaint();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[95]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[15]++;
            return result;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[16]++;}
        
        // if not, check if there is a paint defined for the specified key
        result = this.sectionPaintMap.getPaint(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[96]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[97]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[17]++;
            return result;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[18]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[98]++;
int CodeCoverConditionCoverageHelper_C10;
        
        // nothing defined - do we autoPopulate?
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((autoPopulate) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[19]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[99]++;
            DrawingSupplier ds = getDrawingSupplier();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[100]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((ds != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[21]++;
                result = ds.getNextPaint();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[101]++;
                this.sectionPaintMap.put(key, result);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[102]++;

            }
            else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[22]++;
                result = this.baseSectionPaint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[103]++;
            }

        }
        else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[20]++;
            result = this.baseSectionPaint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[104]++;
        }
        return result;
    }
    
    /**
     * Returns the paint for ALL sections in the plot.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setSectionPaint(Paint)
     * 
     * @deprecated Use {@link #getSectionPaint(Comparable)} and 
     *     {@link #getBaseSectionPaint()}.  Deprecated as of version 1.0.6.
     */
    public Paint getSectionPaint() {
        return this.sectionPaint;
    }

    /**
     * Sets the paint for ALL sections in the plot.  If this is set to
     * </code>null</code>, then a list of paints is used instead (to allow
     * different colors to be used for each section).
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getSectionPaint()
     * 
     * @deprecated Use {@link #setSectionPaint(Comparable, Paint)} and 
     *     {@link #setBaseSectionPaint(Paint)}.  Deprecated as of version 1.0.6.
     */
    public void setSectionPaint(Paint paint) {
        this.sectionPaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[105]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[106]++;
    }

    /**
     * Returns a key for the specified section.  If there is no such section 
     * in the dataset, we generate a key.  This is to provide some backward
     * compatibility for the (now deprecated) methods that get/set attributes 
     * based on section indices.  The preferred way of doing this now is to
     * link the attributes directly to the section key (there are new methods
     * for this, starting from version 1.0.3).  
     * 
     * @param section  the section index.
     * 
     * @return The key.
     *
     * @since 1.0.3
     */
    protected Comparable getSectionKey(int section) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[107]++;
        Comparable key = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[108]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[23]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[109]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((section >= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((section < this.dataset.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[25]++;
                key = this.dataset.getKey(section);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[110]++;

            } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[26]++;}

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[24]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[111]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[27]++;
            key = new Integer(section);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[112]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[28]++;}
        return key;
    }
    
    /**
     * Returns the paint associated with the specified key, or 
     * <code>null</code> if there is no paint associated with the key.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @return The paint associated with the specified key, or 
     *     <code>null</code>.
     *     
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     * 
     * @see #setSectionPaint(Comparable, Paint)
     * 
     * @since 1.0.3
     */
    public Paint getSectionPaint(Comparable key) {
        // null argument check delegated...
        return this.sectionPaintMap.getPaint(key);
    }
    
    /**
     * Sets the paint associated with the specified key, and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * @param paint  the paint.
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     *     
     * @see #getSectionPaint(Comparable)
     * 
     * @since 1.0.3
     */
    public void setSectionPaint(Comparable key, Paint paint) {
        // null argument check delegated...
        this.sectionPaintMap.put(key, paint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[113]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[114]++;
    }
    
    /**
     * Returns the base section paint.  This is used when no other paint is 
     * defined, which is rare.  The default value is <code>Color.gray</code>.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setBaseSectionPaint(Paint)
     */
    public Paint getBaseSectionPaint() {
        return this.baseSectionPaint;   
    }
    
    /**
     * Sets the base section paint and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseSectionPaint()
     */
    public void setBaseSectionPaint(Paint paint) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[115]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[29]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[30]++;}
        this.baseSectionPaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[116]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[117]++;
    }
    
    //// SECTION OUTLINE PAINT ////////////////////////////////////////////////

    /**
     * Returns the flag that controls whether or not the outline is drawn for
     * each pie section.
     * 
     * @return The flag that controls whether or not the outline is drawn for
     *         each pie section.
     *         
     * @see #setSectionOutlinesVisible(boolean)
     */
    public boolean getSectionOutlinesVisible() {
        return this.sectionOutlinesVisible;
    }
    
    /**
     * Sets the flag that controls whether or not the outline is drawn for 
     * each pie section, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     * 
     * @param visible  the flag.
     * 
     * @see #getSectionOutlinesVisible()
     */
    public void setSectionOutlinesVisible(boolean visible) {
        this.sectionOutlinesVisible = visible;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[118]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[119]++;
    }

    /**
     * Returns the outline paint for the specified section.  This is equivalent 
     * to <code>lookupSectionPaint(section, false)</code>.
     * 
     * @param key  the section key.
     * 
     * @return The paint for the specified section.
     * 
     * @since 1.0.3
     * 
     * @see #lookupSectionOutlinePaint(Comparable, boolean)
     */
    protected Paint lookupSectionOutlinePaint(Comparable key) {
        return lookupSectionOutlinePaint(key, false);        
    }
    
    /**
     * Returns the outline paint for the specified section.  The lookup 
     * involves these steps:
     * <ul>
     * <li>if {@link #getSectionOutlinePaint()} is non-<code>null</code>, 
     *         return it;</li>
     * <li>otherwise, if {@link #getSectionOutlinePaint(int)} is 
     *         non-<code>null</code> return it;</li>
     * <li>if {@link #getSectionOutlinePaint(int)} is <code>null</code> but 
     *         <code>autoPopulate</code> is <code>true</code>, attempt to fetch
     *         a new outline paint from the drawing supplier 
     *         ({@link #getDrawingSupplier()});
     * <li>if all else fails, return {@link #getBaseSectionOutlinePaint()}.
     * </ul> 
     * 
     * @param key  the section key.
     * @param autoPopulate  a flag that controls whether the drawing supplier 
     *     is used to auto-populate the section outline paint settings.
     *     
     * @return The paint.
     * 
     * @since 1.0.3
     */
    protected Paint lookupSectionOutlinePaint(Comparable key, 
            boolean autoPopulate) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[120]++;
        
        // is there an override?
        Paint result = getSectionOutlinePaint();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[121]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[31]++;
            return result;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[32]++;}
        
        // if not, check if there is a paint defined for the specified key
        result = this.sectionOutlinePaintMap.getPaint(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[122]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[123]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[33]++;
            return result;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[34]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[124]++;
int CodeCoverConditionCoverageHelper_C18;
        
        // nothing defined - do we autoPopulate?
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((autoPopulate) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[35]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[125]++;
            DrawingSupplier ds = getDrawingSupplier();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[126]++;
int CodeCoverConditionCoverageHelper_C19;
            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((ds != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[37]++;
                result = ds.getNextOutlinePaint();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[127]++;
                this.sectionOutlinePaintMap.put(key, result);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[128]++;

            }
            else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[38]++;
                result = this.baseSectionOutlinePaint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[129]++;
            }

        }
        else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[36]++;
            result = this.baseSectionOutlinePaint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[130]++;
        }
        return result;
    }
    
    /**
     * Returns the outline paint for ALL sections in the plot.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setSectionOutlinePaint(Paint)
     * 
     * @deprecated Use {@link #getSectionOutlinePaint(Comparable)} and 
     *     {@link #getBaseSectionOutlinePaint()}.  Deprecated as of version 
     *     1.0.6.
     */
    public Paint getSectionOutlinePaint() {
        return this.sectionOutlinePaint;
    }

    /**
     * Sets the outline paint for ALL sections in the plot.  If this is set to
     * </code>null</code>, then a list of paints is used instead (to allow
     * different colors to be used for each section).
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getSectionOutlinePaint()
     * 
     * @deprecated Use {@link #setSectionOutlinePaint(Comparable, Paint)} and 
     *     {@link #setBaseSectionOutlinePaint(Paint)}.  Deprecated as of 
     *     version 1.0.6.
     */
    public void setSectionOutlinePaint(Paint paint) {
        this.sectionOutlinePaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[131]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[132]++;
    }

    /**
     * Returns the outline paint associated with the specified key, or 
     * <code>null</code> if there is no paint associated with the key.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @return The paint associated with the specified key, or 
     *     <code>null</code>.
     *     
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     * 
     * @see #setSectionOutlinePaint(Comparable, Paint)
     * 
     * @since 1.0.3
     */
    public Paint getSectionOutlinePaint(Comparable key) {
        // null argument check delegated...
        return this.sectionOutlinePaintMap.getPaint(key);
    }
    
    /**
     * Sets the outline paint associated with the specified key, and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * @param paint  the paint.
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     *     
     * @see #getSectionOutlinePaint(Comparable)
     * 
     * @since 1.0.3
     */
    public void setSectionOutlinePaint(Comparable key, Paint paint) {
        // null argument check delegated...
        this.sectionOutlinePaintMap.put(key, paint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[133]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[134]++;
    }
    
    /**
     * Returns the base section paint.  This is used when no other paint is 
     * available.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setBaseSectionOutlinePaint(Paint)
     */
    public Paint getBaseSectionOutlinePaint() {
        return this.baseSectionOutlinePaint;   
    }
    
    /**
     * Sets the base section paint.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getBaseSectionOutlinePaint()
     */
    public void setBaseSectionOutlinePaint(Paint paint) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[135]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[39]++;
            throw new IllegalArgumentException("Null 'paint' argument.");
   
        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[40]++;}
        this.baseSectionOutlinePaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[136]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[137]++;
    }
    
    //// SECTION OUTLINE STROKE ///////////////////////////////////////////////

    /**
     * Returns the outline stroke for the specified section.  This is 
     * equivalent to <code>lookupSectionOutlineStroke(section, false)</code>.
     * 
     * @param key  the section key.
     * 
     * @return The stroke for the specified section.
     * 
     * @since 1.0.3
     * 
     * @see #lookupSectionOutlineStroke(Comparable, boolean)
     */
    protected Stroke lookupSectionOutlineStroke(Comparable key) {
        return lookupSectionOutlineStroke(key, false);        
    }
    
    /**
     * Returns the outline stroke for the specified section.  The lookup 
     * involves these steps:
     * <ul>
     * <li>if {@link #getSectionOutlineStroke()} is non-<code>null</code>, 
     *         return it;</li>
     * <li>otherwise, if {@link #getSectionOutlineStroke(int)} is 
     *         non-<code>null</code> return it;</li>
     * <li>if {@link #getSectionOutlineStroke(int)} is <code>null</code> but 
     *         <code>autoPopulate</code> is <code>true</code>, attempt to fetch
     *         a new outline stroke from the drawing supplier 
     *         ({@link #getDrawingSupplier()});
     * <li>if all else fails, return {@link #getBaseSectionOutlineStroke()}.
     * </ul> 
     * 
     * @param key  the section key.
     * @param autoPopulate  a flag that controls whether the drawing supplier 
     *     is used to auto-populate the section outline stroke settings.
     *     
     * @return The stroke.
     * 
     * @since 1.0.3
     */
    protected Stroke lookupSectionOutlineStroke(Comparable key, 
            boolean autoPopulate) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[138]++;
        
        // is there an override?
        Stroke result = getSectionOutlineStroke();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[139]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[41]++;
            return result;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[42]++;}
        
        // if not, check if there is a stroke defined for the specified key
        result = this.sectionOutlineStrokeMap.getStroke(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[140]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[141]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[43]++;
            return result;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[44]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[142]++;
int CodeCoverConditionCoverageHelper_C23;
        
        // nothing defined - do we autoPopulate?
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((autoPopulate) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[45]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[143]++;
            DrawingSupplier ds = getDrawingSupplier();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[144]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((ds != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[47]++;
                result = ds.getNextOutlineStroke();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[145]++;
                this.sectionOutlineStrokeMap.put(key, result);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[146]++;

            }
            else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[48]++;
                result = this.baseSectionOutlineStroke;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[147]++;
            }

        }
        else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[46]++;
            result = this.baseSectionOutlineStroke;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[148]++;
        }
        return result;
    }
    
    /**
     * Returns the outline stroke for ALL sections in the plot.
     *
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setSectionOutlineStroke(Stroke)
     * 
     * @deprecated Use {@link #getSectionOutlineStroke(Comparable)} and 
     *     {@link #getBaseSectionOutlineStroke()}.  Deprecated as of version 
     *     1.0.6.
     */
    public Stroke getSectionOutlineStroke() {
        return this.sectionOutlineStroke;
    }

    /**
     * Sets the outline stroke for ALL sections in the plot.  If this is set to
     * </code>null</code>, then a list of paints is used instead (to allow
     * different colors to be used for each section).
     *
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getSectionOutlineStroke()
     * 
     * @deprecated Use {@link #setSectionOutlineStroke(Comparable, Stroke)} and 
     *     {@link #setBaseSectionOutlineStroke(Stroke)}.  Deprecated as of 
     *     version 1.0.6.
     */
    public void setSectionOutlineStroke(Stroke stroke) {
        this.sectionOutlineStroke = stroke;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[149]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[150]++;
    }

    /**
     * Returns the outline stroke associated with the specified key, or 
     * <code>null</code> if there is no stroke associated with the key.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @return The stroke associated with the specified key, or 
     *     <code>null</code>.
     *     
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     * 
     * @see #setSectionOutlineStroke(Comparable, Stroke)
     * 
     * @since 1.0.3
     */
    public Stroke getSectionOutlineStroke(Comparable key) {
        // null argument check delegated...
        return this.sectionOutlineStrokeMap.getStroke(key);
    }
    
    /**
     * Sets the outline stroke associated with the specified key, and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * @param stroke  the stroke.
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     *     
     * @see #getSectionOutlineStroke(Comparable)
     * 
     * @since 1.0.3
     */
    public void setSectionOutlineStroke(Comparable key, Stroke stroke) {
        // null argument check delegated...
        this.sectionOutlineStrokeMap.put(key, stroke);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[151]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[152]++;
    }
    
    /**
     * Returns the base section stroke.  This is used when no other stroke is 
     * available.
     * 
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setBaseSectionOutlineStroke(Stroke)
     */
    public Stroke getBaseSectionOutlineStroke() {
        return this.baseSectionOutlineStroke;   
    }
    
    /**
     * Sets the base section stroke.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getBaseSectionOutlineStroke()
     */
    public void setBaseSectionOutlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[153]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[49]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");
   
        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[50]++;}
        this.baseSectionOutlineStroke = stroke;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[154]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[155]++;
    }

    /**
     * Returns the shadow paint.
     * 
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setShadowPaint(Paint)
     */
    public Paint getShadowPaint() {
        return this.shadowPaint;   
    }
    
    /**
     * Sets the shadow paint and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getShadowPaint()
     */
    public void setShadowPaint(Paint paint) {
        this.shadowPaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[156]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[157]++;
    }
    
    /**
     * Returns the x-offset for the shadow effect.
     * 
     * @return The offset (in Java2D units).
     * 
     * @see #setShadowXOffset(double)
     */
    public double getShadowXOffset() {
        return this.shadowXOffset;
    }
    
    /**
     * Sets the x-offset for the shadow effect and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param offset  the offset (in Java2D units).
     * 
     * @see #getShadowXOffset()
     */
    public void setShadowXOffset(double offset) {
        this.shadowXOffset = offset;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[158]++;   
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[159]++;
    }
    
    /**
     * Returns the y-offset for the shadow effect.
     * 
     * @return The offset (in Java2D units).
     * 
     * @see #setShadowYOffset(double)
     */
    public double getShadowYOffset() {
        return this.shadowYOffset;
    }
    
    /**
     * Sets the y-offset for the shadow effect and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param offset  the offset (in Java2D units).
     * 
     * @see #getShadowYOffset()
     */
    public void setShadowYOffset(double offset) {
        this.shadowYOffset = offset;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[160]++;   
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[161]++;
    }
    
    /**
     * Returns the amount that the section with the specified key should be
     * exploded.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @return The amount that the section with the specified key should be
     *     exploded.
     * 
     * @throws IllegalArgumentException if <code>key</code> is 
     *     <code>null</code>.
     *
     * @since 1.0.3
     * 
     * @see #setExplodePercent(Comparable, double)
     */
    public double getExplodePercent(Comparable key) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[162]++;
        double result = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[163]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.explodePercentages != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[51]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[164]++;
            Number percent = (Number) this.explodePercentages.get(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[165]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((percent != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[53]++;
                result = percent.doubleValue();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[166]++;

            } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[54]++;}

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[52]++;}
        return result;
    }
    
    /**
     * Sets the amount that a pie section should be exploded and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param key  the section key (<code>null</code> not permitted).
     * @param percent  the explode percentage (0.30 = 30 percent).
     * 
     * @since 1.0.3
     * 
     * @see #getExplodePercent(Comparable)
     */
    public void setExplodePercent(Comparable key, double percent) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[167]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[55]++; 
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[56]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[168]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.explodePercentages == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[57]++;
            this.explodePercentages = new TreeMap();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[169]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[58]++;}
        this.explodePercentages.put(key, new Double(percent));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[170]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[171]++;
    }
    
    /**
     * Returns the maximum explode percent.
     * 
     * @return The percent.
     */
    public double getMaximumExplodePercent() {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[172]++;
        double result = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[173]++;
        Iterator iterator = this.dataset.getKeys().iterator();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[174]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[1]++;


int CodeCoverConditionCoverageHelper_C30;
        while ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[1]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[2]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[3]++;
}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[175]++;
            Comparable key = (Comparable) iterator.next();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[176]++;
            Number explode = (Number) this.explodePercentages.get(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[177]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((explode != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[59]++;
                result = Math.max(result, explode.doubleValue());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[178]++;
   
            } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[60]++;}
        }
        return result;
    }
    
    /**
     * Returns the section label generator. 
     * 
     * @return The generator (possibly <code>null</code>).
     * 
     * @see #setLabelGenerator(PieSectionLabelGenerator)
     */
    public PieSectionLabelGenerator getLabelGenerator() {
        return this.labelGenerator;   
    }
    
    /**
     * Sets the section label generator and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     * 
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getLabelGenerator()
     */
    public void setLabelGenerator(PieSectionLabelGenerator generator) {
        this.labelGenerator = generator;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[179]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[180]++;
    }
    
    /**
     * Returns the gap between the edge of the pie and the labels, expressed as 
     * a percentage of the plot width.
     * 
     * @return The gap (a percentage, where 0.05 = five percent).
     * 
     * @see #setLabelGap(double)
     */
    public double getLabelGap() {
        return this.labelGap;   
    }
    
    /**
     * Sets the gap between the edge of the pie and the labels (expressed as a 
     * percentage of the plot width) and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param gap  the gap (a percentage, where 0.05 = five percent).
     * 
     * @see #getLabelGap()
     */
    public void setLabelGap(double gap) {
        this.labelGap = gap;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[181]++;   
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[182]++;
    }
    
    /**
     * Returns the maximum label width as a percentage of the plot width.
     * 
     * @return The width (a percentage, where 0.20 = 20 percent).
     * 
     * @see #setMaximumLabelWidth(double)
     */
    public double getMaximumLabelWidth() {
        return this.maximumLabelWidth;   
    }
    
    /**
     * Sets the maximum label width as a percentage of the plot width and sends
     * a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param width  the width (a percentage, where 0.20 = 20 percent).
     * 
     * @see #getMaximumLabelWidth()
     */
    public void setMaximumLabelWidth(double width) {
        this.maximumLabelWidth = width;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[183]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[184]++;
    }
    
    /**
     * Returns the flag that controls whether or not label linking lines are
     * visible.
     * 
     * @return A boolean.
     * 
     * @see #setLabelLinksVisible(boolean)
     */
    public boolean getLabelLinksVisible() {
        return this.labelLinksVisible;
    }
    
    /**
     * Sets the flag that controls whether or not label linking lines are 
     * visible and sends a {@link PlotChangeEvent} to all registered listeners.
     * Please take care when hiding the linking lines - depending on the data 
     * values, the labels can be displayed some distance away from the
     * corresponding pie section.
     * 
     * @param visible  the flag.
     * 
     * @see #getLabelLinksVisible()
     */
    public void setLabelLinksVisible(boolean visible) {
        this.labelLinksVisible = visible;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[185]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[186]++;
    }
    
    /**
     * Returns the margin (expressed as a percentage of the width or height) 
     * between the edge of the pie and the link point.
     * 
     * @return The link margin (as a percentage, where 0.05 is five percent).
     * 
     * @see #setLabelLinkMargin(double)
     */
    public double getLabelLinkMargin() {
        return this.labelLinkMargin;   
    }
    
    /**
     * Sets the link margin and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param margin  the margin.
     * 
     * @see #getLabelLinkMargin()
     */
    public void setLabelLinkMargin(double margin) {
        this.labelLinkMargin = margin;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[187]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[188]++;
    }
    
    /**
     * Returns the paint used for the lines that connect pie sections to their 
     * corresponding labels.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setLabelLinkPaint(Paint)
     */
    public Paint getLabelLinkPaint() {
        return this.labelLinkPaint;   
    }
    
    /**
     * Sets the paint used for the lines that connect pie sections to their 
     * corresponding labels, and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getLabelLinkPaint()
     */
    public void setLabelLinkPaint(Paint paint) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[189]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[61]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[62]++;}
        this.labelLinkPaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[190]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[191]++;
    }
    
    /**
     * Returns the stroke used for the label linking lines.
     * 
     * @return The stroke.
     * 
     * @see #setLabelLinkStroke(Stroke)
     */
    public Stroke getLabelLinkStroke() {
        return this.labelLinkStroke;   
    }
    
    /**
     * Sets the link stroke and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param stroke  the stroke.
     * 
     * @see #getLabelLinkStroke()
     */
    public void setLabelLinkStroke(Stroke stroke) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[192]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[63]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[64]++;}
        this.labelLinkStroke = stroke;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[193]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[194]++;
    }
    
    /**
     * Returns the section label font.
     *
     * @return The font (never <code>null</code>).
     * 
     * @see #setLabelFont(Font)
     */
    public Font getLabelFont() {
        return this.labelFont;
    }

    /**
     * Sets the section label font and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getLabelFont()
     */
    public void setLabelFont(Font font) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[195]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[65]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[66]++;}
        this.labelFont = font;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[196]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[197]++;
    }

    /**
     * Returns the section label paint.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setLabelPaint(Paint)
     */
    public Paint getLabelPaint() {
        return this.labelPaint;
    }

    /**
     * Sets the section label paint and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getLabelPaint()
     */
    public void setLabelPaint(Paint paint) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[198]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[67]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[68]++;}
        this.labelPaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[199]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[200]++;
    }

    /**
     * Returns the section label background paint.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setLabelBackgroundPaint(Paint)
     */
    public Paint getLabelBackgroundPaint() {
        return this.labelBackgroundPaint;
    }

    /**
     * Sets the section label background paint and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getLabelBackgroundPaint()
     */
    public void setLabelBackgroundPaint(Paint paint) {
        this.labelBackgroundPaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[201]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[202]++;
    }

    /**
     * Returns the section label outline paint.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setLabelOutlinePaint(Paint)
     */
    public Paint getLabelOutlinePaint() {
        return this.labelOutlinePaint;
    }

    /**
     * Sets the section label outline paint and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getLabelOutlinePaint()
     */
    public void setLabelOutlinePaint(Paint paint) {
        this.labelOutlinePaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[203]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[204]++;
    }

    /**
     * Returns the section label outline stroke.
     *
     * @return The stroke (possibly <code>null</code>).
     * 
     * @see #setLabelOutlineStroke(Stroke)
     */
    public Stroke getLabelOutlineStroke() {
        return this.labelOutlineStroke;
    }

    /**
     * Sets the section label outline stroke and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getLabelOutlineStroke()
     */
    public void setLabelOutlineStroke(Stroke stroke) {
        this.labelOutlineStroke = stroke;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[205]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[206]++;
    }

    /**
     * Returns the section label shadow paint.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setLabelShadowPaint(Paint)
     */
    public Paint getLabelShadowPaint() {
        return this.labelShadowPaint;
    }

    /**
     * Sets the section label shadow paint and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getLabelShadowPaint()
     */
    public void setLabelShadowPaint(Paint paint) {
        this.labelShadowPaint = paint;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[207]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[208]++;
    }
    
    /**
     * Returns the label padding.
     * 
     * @return The label padding (never <code>null</code>).
     * 
     * @since 1.0.7
     * 
     * @see #setLabelPadding(RectangleInsets)
     */
    public RectangleInsets getLabelPadding() {
        return this.labelPadding;
    }
    
    /**
     * Sets the padding between each label and its outline and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param padding  the padding (<code>null</code> not permitted).
     * 
     * @since 1.0.7
     * 
     * @see #getLabelPadding()
     */
    public void setLabelPadding(RectangleInsets padding) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[209]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((padding == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[69]++;
            throw new IllegalArgumentException("Null 'padding' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[70]++;}
        this.labelPadding = padding;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[210]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[211]++;
    }

    /**
     * Returns the flag that controls whether simple or extended labels are
     * displayed on the plot.
     * 
     * @return A boolean.
     * 
     * @since 1.0.7
     */
    public boolean getSimpleLabels() {
        return this.simpleLabels;
    }
    
    /**
     * Sets the flag that controls whether simple or extended labels are 
     * displayed on the plot, and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param simple  the new flag value.
     * 
     * @since 1.0.7
     */
    public void setSimpleLabels(boolean simple) {
        this.simpleLabels = simple;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[212]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[213]++;
    }
    
    /**
     * Returns the offset used for the simple labels, if they are displayed.
     * 
     * @return The offset (never <code>null</code>).
     * 
     * @since 1.0.7
     * 
     * @see #setSimpleLabelOffset(RectangleInsets)
     */
    public RectangleInsets getSimpleLabelOffset() {
        return this.simpleLabelOffset;
    }
    
    /**
     * Sets the offset for the simple labels and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param offset  the offset (<code>null</code> not permitted).
     * 
     * @since 1.0.7
     * 
     * @see #getSimpleLabelOffset()
     */
    public void setSimpleLabelOffset(RectangleInsets offset) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[214]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((offset == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[71]++;
            throw new IllegalArgumentException("Null 'offset' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[72]++;}
        this.simpleLabelOffset = offset;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[215]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[216]++;        
    }
    
    /**
     * Returns the object responsible for the vertical layout of the pie 
     * section labels.
     * 
     * @return The label distributor (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public AbstractPieLabelDistributor getLabelDistributor() {
        return this.labelDistributor;
    }
    
    /**
     * Sets the label distributor and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param distributor  the distributor (<code>null</code> not permitted).
     *
     * @since 1.0.6
     */
    public void setLabelDistributor(AbstractPieLabelDistributor distributor) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[217]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((distributor == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[73]++;
            throw new IllegalArgumentException("Null 'distributor' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[74]++;}
        this.labelDistributor = distributor;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[218]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[219]++;
    }
    
    /**
     * Returns the tool tip generator, an object that is responsible for 
     * generating the text items used for tool tips by the plot.  If the 
     * generator is <code>null</code>, no tool tips will be created.
     *
     * @return The generator (possibly <code>null</code>).
     * 
     * @see #setToolTipGenerator(PieToolTipGenerator)
     */
    public PieToolTipGenerator getToolTipGenerator() {
        return this.toolTipGenerator;
    }

    /**
     * Sets the tool tip generator and sends a {@link PlotChangeEvent} to all 
     * registered listeners.  Set the generator to <code>null</code> if you 
     * don't want any tool tips.
     *
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getToolTipGenerator()
     */
    public void setToolTipGenerator(PieToolTipGenerator generator) {
        this.toolTipGenerator = generator;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[220]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[221]++;
    }

    /**
     * Returns the URL generator.
     *
     * @return The generator (possibly <code>null</code>).
     * 
     * @see #setURLGenerator(PieURLGenerator)
     */
    public PieURLGenerator getURLGenerator() {
        return this.urlGenerator;
    }

    /**
     * Sets the URL generator and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getURLGenerator()
     */
    public void setURLGenerator(PieURLGenerator generator) {
        this.urlGenerator = generator;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[222]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[223]++;
    }

    /**
     * Returns the minimum arc angle that will be drawn.  Pie sections for an 
     * angle smaller than this are not drawn, to avoid a JDK bug.
     *
     * @return The minimum angle.
     * 
     * @see #setMinimumArcAngleToDraw(double)
     */
    public double getMinimumArcAngleToDraw() {
        return this.minimumArcAngleToDraw;
    }

    /**
     * Sets the minimum arc angle that will be drawn.  Pie sections for an 
     * angle smaller than this are not drawn, to avoid a JDK bug.  See this 
     * link for details:
     * <br><br>
     * <a href="http://www.jfree.org/phpBB2/viewtopic.php?t=2707">
     * http://www.jfree.org/phpBB2/viewtopic.php?t=2707</a>
     * <br><br>
     * ...and this bug report in the Java Bug Parade:
     * <br><br>
     * <a href=
     * "http://developer.java.sun.com/developer/bugParade/bugs/4836495.html">
     * http://developer.java.sun.com/developer/bugParade/bugs/4836495.html</a>
     *
     * @param angle  the minimum angle.
     * 
     * @see #getMinimumArcAngleToDraw()
     */
    public void setMinimumArcAngleToDraw(double angle) {
        this.minimumArcAngleToDraw = angle;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[224]++;
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
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[225]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((shape == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[75]++;
            throw new IllegalArgumentException("Null 'shape' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[76]++;}
        this.legendItemShape = shape;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[226]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[227]++;
    }
    
    /**
     * Returns the legend label generator.
     * 
     * @return The legend label generator (never <code>null</code>).
     * 
     * @see #setLegendLabelGenerator(PieSectionLabelGenerator)
     */
    public PieSectionLabelGenerator getLegendLabelGenerator() {
        return this.legendLabelGenerator;
    }
    
    /**
     * Sets the legend label generator and sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     * 
     * @param generator  the generator (<code>null</code> not permitted).
     * 
     * @see #getLegendLabelGenerator()
     */
    public void setLegendLabelGenerator(PieSectionLabelGenerator generator) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[228]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((generator == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[77]++;
            throw new IllegalArgumentException("Null 'generator' argument.");

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[78]++;}
        this.legendLabelGenerator = generator;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[229]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[230]++;
    }
    
    /**
     * Returns the legend label tool tip generator.
     * 
     * @return The legend label tool tip generator (possibly <code>null</code>).
     * 
     * @see #setLegendLabelToolTipGenerator(PieSectionLabelGenerator)
     */
    public PieSectionLabelGenerator getLegendLabelToolTipGenerator() {
        return this.legendLabelToolTipGenerator;
    }
    
    /**
     * Sets the legend label tool tip generator and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getLegendLabelToolTipGenerator()
     */
    public void setLegendLabelToolTipGenerator(
            PieSectionLabelGenerator generator) {
        this.legendLabelToolTipGenerator = generator;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[231]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[232]++;
    }
    
    /**
     * Returns the legend label URL generator.
     * 
     * @return The legend label URL generator (possibly <code>null</code>).
     * 
     * @see #setLegendLabelURLGenerator(PieURLGenerator)
     * 
     * @since 1.0.4
     */
    public PieURLGenerator getLegendLabelURLGenerator() {
        return this.legendLabelURLGenerator;
    }
    
    /**
     * Sets the legend label URL generator and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param generator  the generator (<code>null</code> permitted).
     * 
     * @see #getLegendLabelURLGenerator()
     * 
     * @since 1.0.4
     */
    public void setLegendLabelURLGenerator(PieURLGenerator generator) {
        this.legendLabelURLGenerator = generator;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[233]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[234]++;
    }
    
    /**
     * Initialises the drawing procedure.  This method will be called before 
     * the first item is rendered, giving the plot an opportunity to initialise
     * any state information it wants to maintain.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area (<code>null</code> not permitted).
     * @param plot  the plot.
     * @param index  the secondary index (<code>null</code> for primary 
     *               renderer).
     * @param info  collects chart rendering information for return to caller.
     * 
     * @return A state object (maintains state information relevant to one 
     *         chart drawing).
     */
    public PiePlotState initialise(Graphics2D g2, Rectangle2D plotArea,
            PiePlot plot, Integer index, PlotRenderingInfo info) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[235]++;
     
        PiePlotState state = new PiePlotState(info);
        state.setPassesRequired(2);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[236]++;
        state.setTotal(DatasetUtilities.calculatePieDatasetTotal(
                plot.getDataset()));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[237]++;
        state.setLatestAngle(plot.getStartAngle());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[238]++;
        return state;
        
    }
    
    /**
     * Draws the plot on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device.
     * @param area  the area within which the plot should be drawn.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  the state from the parent plot, if there is one.
     * @param info  collects info about the drawing 
     *              (<code>null</code> permitted).
     */
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor,
                     PlotState parentState, PlotRenderingInfo info) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[239]++;

        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[240]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[241]++;
int CodeCoverConditionCoverageHelper_C41;

        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[79]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[242]++;
            info.setDataArea(area);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[243]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[80]++;}

        drawBackground(g2, area);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[244]++;
        drawOutline(g2, area);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[245]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[246]++;

        Shape savedClip = g2.getClip();
        g2.clip(area);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[247]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[248]++;

        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                getForegroundAlpha()));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[249]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[250]++;
int CodeCoverConditionCoverageHelper_C42;

        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((DatasetUtilities.isEmptyOrNull(this.dataset)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[81]++;
            drawPie(g2, area, info);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[251]++;

        }
        else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[82]++;
            drawNoDataMessage(g2, area);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[252]++;
        }

        g2.setClip(savedClip);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[253]++;
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[254]++;

        drawOutline(g2, area);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[255]++;

    }

    /**
     * Draws the pie.
     *
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param info  chart rendering info.
     */
    protected void drawPie(Graphics2D g2, Rectangle2D plotArea, 
                           PlotRenderingInfo info) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[256]++;

        PiePlotState state = initialise(g2, plotArea, this, null, info);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[257]++;

        // adjust the plot area for interior spacing and labels...
        double labelReserve = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[258]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((this.labelGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.simpleLabels) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[83]++;
            labelReserve = this.labelGap + this.maximumLabelWidth;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[259]++;
    
        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[84]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[260]++;
        double gapHorizontal = plotArea.getWidth() * (this.interiorGap 
                + labelReserve) * 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[261]++;
        double gapVertical = plotArea.getHeight() * this.interiorGap * 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[262]++;
int CodeCoverConditionCoverageHelper_C44;

        
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((DEBUG_DRAW_INTERIOR) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[85]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[263]++;
            double hGap = plotArea.getWidth() * this.interiorGap;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[264]++;
            double vGap = plotArea.getHeight() * this.interiorGap;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[265]++;
        
            double igx1 = plotArea.getX() + hGap;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[266]++;
            double igx2 = plotArea.getMaxX() - hGap;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[267]++;
            double igy1 = plotArea.getY() + vGap;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[268]++;
            double igy2 = plotArea.getMaxY() - vGap;
            g2.setPaint(Color.gray);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[269]++;
            g2.draw(new Rectangle2D.Double(igx1, igy1, igx2 - igx1, 
                    igy2 - igy1));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[270]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[86]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[271]++;
        
        double linkX = plotArea.getX() + gapHorizontal / 2;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[272]++;
        double linkY = plotArea.getY() + gapVertical / 2;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[273]++;
        double linkW = plotArea.getWidth() - gapHorizontal;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[274]++;
        double linkH = plotArea.getHeight() - gapVertical;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[275]++;
int CodeCoverConditionCoverageHelper_C45;
        
        // make the link area a square if the pie chart is to be circular...
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.circular) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[87]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[276]++;
            double min = Math.min(linkW, linkH) / 2;
            linkX = (linkX + linkX + linkW) / 2 - min;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[277]++;
            linkY = (linkY + linkY + linkH) / 2 - min;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[278]++;
            linkW = 2 * min;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[279]++;
            linkH = 2 * min;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[280]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[88]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[281]++;

        // the link area defines the dog leg points for the linking lines to 
        // the labels
        Rectangle2D linkArea = new Rectangle2D.Double(linkX, linkY, linkW, 
                linkH);
        state.setLinkArea(linkArea);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[282]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[283]++;
int CodeCoverConditionCoverageHelper_C46;

        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((DEBUG_DRAW_LINK_AREA) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[89]++;
            g2.setPaint(Color.blue);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[284]++;
            g2.draw(linkArea);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[285]++;
            g2.setPaint(Color.yellow);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[286]++;
            g2.draw(new Ellipse2D.Double(linkArea.getX(), linkArea.getY(), 
                    linkArea.getWidth(), linkArea.getHeight()));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[287]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[90]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[288]++;
        
        // the explode area defines the max circle/ellipse for the exploded 
        // pie sections.  it is defined by shrinking the linkArea by the 
        // linkMargin factor.
        double lm = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[289]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.simpleLabels) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[91]++;
            lm = this.labelLinkMargin;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[290]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[92]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[291]++;
        double hh = linkArea.getWidth() * lm * 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[292]++;
        double vv = linkArea.getHeight() * lm * 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[293]++;
        Rectangle2D explodeArea = new Rectangle2D.Double(linkX + hh / 2.0, 
                linkY + vv / 2.0, linkW - hh, linkH - vv);
       
        state.setExplodedPieArea(explodeArea);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[294]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[295]++;
        
        // the pie area defines the circle/ellipse for regular pie sections.
        // it is defined by shrinking the explodeArea by the explodeMargin 
        // factor. 
        double maximumExplodePercent = getMaximumExplodePercent();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[296]++;
        double percent = maximumExplodePercent / (1.0 + maximumExplodePercent);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[297]++;
        
        double h1 = explodeArea.getWidth() * percent;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[298]++;
        double v1 = explodeArea.getHeight() * percent;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[299]++;
        Rectangle2D pieArea = new Rectangle2D.Double(explodeArea.getX() 
                + h1 / 2.0, explodeArea.getY() + v1 / 2.0, 
                explodeArea.getWidth() - h1, explodeArea.getHeight() - v1);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[300]++;
int CodeCoverConditionCoverageHelper_C48;

        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((DEBUG_DRAW_PIE_AREA) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[93]++;
            g2.setPaint(Color.green);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[301]++;
            g2.draw(pieArea);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[302]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[94]++;}
        state.setPieArea(pieArea);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[303]++;
        state.setPieCenterX(pieArea.getCenterX());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[304]++;
        state.setPieCenterY(pieArea.getCenterY());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[305]++;
        state.setPieWRadius(pieArea.getWidth() / 2.0);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[306]++;
        state.setPieHRadius(pieArea.getHeight() / 2.0);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[307]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[308]++;
int CodeCoverConditionCoverageHelper_C49;
        
        // plot the data (unless the dataset is null)...
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((this.dataset.getKeys().size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[95]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[309]++;

            List keys = this.dataset.getKeys();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[310]++;
            double totalValue = DatasetUtilities.calculatePieDatasetTotal(
                    this.dataset);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[311]++;

            int passesRequired = state.getPassesRequired();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[312]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[4]++;


int CodeCoverConditionCoverageHelper_C50;
            for (int pass = 0;(((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((pass < passesRequired) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false); pass++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[4]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[5]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[6]++;
}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[313]++;
                double runningTotal = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[314]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[7]++;


int CodeCoverConditionCoverageHelper_C51;
                for (int section = 0;(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((section < keys.size()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false); section++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[7]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[8]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[9]++;
}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[315]++;
                    Number n = this.dataset.getValue(section);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[316]++;
int CodeCoverConditionCoverageHelper_C52;
                    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[97]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[317]++;
                        double value = n.doubleValue();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[318]++;
int CodeCoverConditionCoverageHelper_C53;
                        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((value > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[99]++;
                            runningTotal += value;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[319]++;
                            drawItem(g2, section, explodeArea, state, pass);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[320]++;

                        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[100]++;}

                    } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[98]++;} 
                }
            }
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[321]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((this.simpleLabels) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[101]++;
                drawSimpleLabels(g2, keys, totalValue, plotArea, linkArea, 
                        state);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[322]++;

            }
            else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[102]++;
                drawLabels(g2, keys, totalValue, plotArea, linkArea, state);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[323]++;
            }


        }
        else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[96]++;
            drawNoDataMessage(g2, plotArea);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[324]++;
        }
    }
    
    /**
     * Draws a single data item.
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param section  the section index.
     * @param dataArea  the data plot area.
     * @param state  state information for one chart.
     * @param currentPass  the current pass index.
     */
    protected void drawItem(Graphics2D g2, int section, Rectangle2D dataArea,
                            PiePlotState state, int currentPass) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[325]++;
    
        Number n = this.dataset.getValue(section);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[326]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[103]++;
            return;
   
        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[104]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[327]++;
        double value = n.doubleValue();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[328]++;
        double angle1 = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[329]++;
        double angle2 = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[330]++;
int CodeCoverConditionCoverageHelper_C56;
        
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((this.direction == Rotation.CLOCKWISE) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[105]++;
            angle1 = state.getLatestAngle();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[331]++;
            angle2 = angle1 - value / state.getTotal() * 360.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[332]++;

        }
        else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[106]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[333]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((this.direction == Rotation.ANTICLOCKWISE) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[107]++;
            angle1 = state.getLatestAngle();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[334]++;
            angle2 = angle1 + value / state.getTotal() * 360.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[335]++;
         
        }
        else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[108]++;
            throw new IllegalStateException("Rotation type not recognised.");   
        }
}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[336]++;
        
        double angle = (angle2 - angle1);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[337]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((Math.abs(angle) > getMinimumArcAngleToDraw()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[109]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[338]++;
            double ep = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[339]++;
            double mep = getMaximumExplodePercent();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[340]++;
int CodeCoverConditionCoverageHelper_C59;
            if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((mep > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[111]++;
                ep = getExplodePercent(section) / mep;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[341]++;
                
            } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[112]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[342]++;
            Rectangle2D arcBounds = getArcBounds(state.getPieArea(), 
                    state.getExplodedPieArea(), angle1, angle, ep);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[343]++;
            Arc2D.Double arc = new Arc2D.Double(arcBounds, angle1, angle, 
                    Arc2D.PIE);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[344]++;
int CodeCoverConditionCoverageHelper_C60;
            
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((currentPass == 0) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[113]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[345]++;
int CodeCoverConditionCoverageHelper_C61;
                if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((this.shadowPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[115]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[346]++;
                    Shape shadowArc = ShapeUtilities.createTranslatedShape(
                            arc, (float) this.shadowXOffset, 
                            (float) this.shadowYOffset);
                    g2.setPaint(this.shadowPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[347]++;
                    g2.fill(shadowArc);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[348]++;

                } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[116]++;}

            }
            else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[114]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[349]++;
int CodeCoverConditionCoverageHelper_C62; if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((currentPass == 1) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[117]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[350]++;
                Comparable key = getSectionKey(section);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[351]++;
                Paint paint = lookupSectionPaint(key, true);
                g2.setPaint(paint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[352]++;
                g2.fill(arc);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[353]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[354]++;

                Paint outlinePaint = lookupSectionOutlinePaint(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[355]++;
                Stroke outlineStroke = lookupSectionOutlineStroke(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[356]++;
int CodeCoverConditionCoverageHelper_C63;
                if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((this.sectionOutlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[119]++;
                    g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[357]++;
                    g2.setStroke(outlineStroke);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[358]++;
                    g2.draw(arc);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[359]++;

                } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[120]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[360]++;
int CodeCoverConditionCoverageHelper_C64;
                
                // update the linking line target for later
                // add an entity for the pie section
                if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((state.getInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[121]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[361]++;
                    EntityCollection entities = state.getEntityCollection();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[362]++;
int CodeCoverConditionCoverageHelper_C65;
                    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[123]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[363]++;
                        String tip = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[364]++;
int CodeCoverConditionCoverageHelper_C66;
                        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((this.toolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[125]++;
                            tip = this.toolTipGenerator.generateToolTip(
                                    this.dataset, key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[365]++;

                        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[126]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[366]++;
                        String url = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[367]++;
int CodeCoverConditionCoverageHelper_C67;
                        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((this.urlGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[127]++;
                            url = this.urlGenerator.generateURL(this.dataset, 
                                    key, this.pieIndex);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[368]++;

                        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[128]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[369]++;
                        PieSectionEntity entity = new PieSectionEntity(
                                arc, this.dataset, this.pieIndex, section, key,
                                tip, url);
                        entities.add(entity);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[370]++;

                    } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[124]++;}

                } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[122]++;}

            } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[118]++;}
}

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[110]++;}    
        state.setLatestAngle(angle2);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[371]++;
    }
    
    /**
     * Draws the pie section labels in the simple form.
     * 
     * @param g2  the graphics device.
     * @param keys  the section keys.
     * @param totalValue  the total value for all sections in the pie.
     * @param plotArea  the plot area.
     * @param pieArea  the area containing the pie.
     * @param state  the plot state.
     *
     * @since 1.0.7
     */
    protected void drawSimpleLabels(Graphics2D g2, List keys, 
            double totalValue, Rectangle2D plotArea, Rectangle2D pieArea, 
            PiePlotState state) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[372]++;
        
        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                1.0f));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[373]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[374]++;

        RectangleInsets labelInsets = new RectangleInsets(UnitType.RELATIVE, 
                0.18, 0.18, 0.18, 0.18);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[375]++;
        Rectangle2D labelsArea = labelInsets.createInsetRectangle(pieArea);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[376]++;
        double runningTotal = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[377]++;
        Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[378]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[10]++;


int CodeCoverConditionCoverageHelper_C68;
        while ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[10]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[11]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[12]++;
}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[379]++;
            Comparable key = (Comparable) iterator.next();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[380]++;
            boolean include = true;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[381]++;
            double v = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[382]++;
            Number n = getDataset().getValue(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[383]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[129]++;
                include = !getIgnoreNullValues();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[384]++;

            }
            else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[130]++;
                v = n.doubleValue();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[385]++;
                include = getIgnoreZeroValues() ? v > 0.0 : v >= 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[386]++;
            }
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[387]++;
int CodeCoverConditionCoverageHelper_C70;

            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((include) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[131]++;
                runningTotal = runningTotal + v;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[388]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[389]++;
                // work out the mid angle (0 - 90 and 270 - 360) = right, 
                // otherwise left
                double mid = getStartAngle() + (getDirection().getFactor()
                        * ((runningTotal - v / 2.0) * 360) / totalValue);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[390]++;
                
                Arc2D arc = new Arc2D.Double(labelsArea, getStartAngle(), 
                        mid - getStartAngle(), Arc2D.OPEN);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[391]++;
                int x = (int) arc.getEndPoint().getX();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[392]++;
                int y = (int) arc.getEndPoint().getY();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[393]++;
                
                PieSectionLabelGenerator labelGenerator = getLabelGenerator();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[394]++;
int CodeCoverConditionCoverageHelper_C71;
                if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((labelGenerator == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[133]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[395]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[134]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[396]++;
                String label = labelGenerator.generateSectionLabel(
                        this.dataset, key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[397]++;
int CodeCoverConditionCoverageHelper_C72;
                if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[135]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[398]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[136]++;}
                g2.setFont(this.labelFont);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[399]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[400]++;
                FontMetrics fm = g2.getFontMetrics();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[401]++;
                Rectangle2D bounds = TextUtilities.getTextBounds(label, g2, fm);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[402]++;
                Rectangle2D out = this.labelPadding.createOutsetRectangle(
                        bounds);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[403]++;
                Shape bg = ShapeUtilities.createTranslatedShape(out, 
                        x - bounds.getCenterX(), y - bounds.getCenterY());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[404]++;
int CodeCoverConditionCoverageHelper_C73;
                if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((this.labelShadowPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[137]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[405]++;
                    Shape shadow = ShapeUtilities.createTranslatedShape(bg, 
                            this.shadowXOffset, this.shadowYOffset);
                    g2.setPaint(this.labelShadowPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[406]++;
                    g2.fill(shadow);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[407]++;

                } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[138]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[408]++;
int CodeCoverConditionCoverageHelper_C74;
                if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((this.labelBackgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[139]++;
                    g2.setPaint(this.labelBackgroundPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[409]++;
                    g2.fill(bg);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[410]++;

                } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[140]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[411]++;
int CodeCoverConditionCoverageHelper_C75;
                if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((this.labelOutlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((this.labelOutlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[141]++;
                    g2.setPaint(this.labelOutlinePaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[412]++;
                    g2.setStroke(this.labelOutlineStroke);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[413]++;
                    g2.draw(bg);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[414]++;

                } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[142]++;}
                
                g2.setPaint(this.labelPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[415]++;
                g2.setFont(this.labelFont);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[416]++;
                TextUtilities.drawAlignedString(getLabelGenerator()
                        .generateSectionLabel(getDataset(), key), g2, x, y, 
                        TextAnchor.CENTER);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[417]++;

                
            } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[132]++;}
        }
       
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[418]++;

    }

    /**
     * Draws the labels for the pie sections.
     * 
     * @param g2  the graphics device.
     * @param keys  the keys.
     * @param totalValue  the total value.
     * @param plotArea  the plot area.
     * @param linkArea  the link area.
     * @param state  the state.
     */
    protected void drawLabels(Graphics2D g2, List keys, double totalValue, 
                              Rectangle2D plotArea, Rectangle2D linkArea, 
                              PiePlotState state) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[419]++;   

        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                1.0f));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[420]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[421]++;

        // classify the keys according to which side the label will appear...
        DefaultKeyedValues leftKeys = new DefaultKeyedValues();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[422]++;
        DefaultKeyedValues rightKeys = new DefaultKeyedValues();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[423]++;
       
        double runningTotal = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[424]++;
        Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[425]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[13]++;


int CodeCoverConditionCoverageHelper_C76;
        while ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[13]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[14]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[15]++;
}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[426]++;
            Comparable key = (Comparable) iterator.next();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[427]++;
            boolean include = true;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[428]++;
            double v = 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[429]++;
            Number n = this.dataset.getValue(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[430]++;
int CodeCoverConditionCoverageHelper_C77;
            if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[143]++;
                include = !this.ignoreNullValues;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[431]++;

            }
            else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[144]++;
                v = n.doubleValue();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[432]++;
                include = this.ignoreZeroValues ? v > 0.0 : v >= 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[433]++;
            }
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[434]++;
int CodeCoverConditionCoverageHelper_C78;

            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((include) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[145]++;
                runningTotal = runningTotal + v;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[435]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[436]++;
                // work out the mid angle (0 - 90 and 270 - 360) = right, 
                // otherwise left
                double mid = this.startAngle + (this.direction.getFactor()
                        * ((runningTotal - v / 2.0) * 360) / totalValue);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[437]++;
int CodeCoverConditionCoverageHelper_C79;
                if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((Math.cos(Math.toRadians(mid)) < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[147]++;
                    leftKeys.addValue(key, new Double(mid));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[438]++;

                }
                else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[148]++;
                    rightKeys.addValue(key, new Double(mid));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[439]++;
                }

            } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[146]++;}
        }
       
        g2.setFont(getLabelFont());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[440]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[441]++;
        
        // calculate the max label width from the plot dimensions, because
        // a circular pie can leave a lot more room for labels...
        double marginX = plotArea.getX() + this.interiorGap * plotArea.getWidth();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[442]++;
        double gap = plotArea.getWidth() * this.labelGap;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[443]++;
        double ww = linkArea.getX() - gap - marginX;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[444]++;
        float labelWidth = (float) this.labelPadding.trimWidth(ww);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[445]++;
int CodeCoverConditionCoverageHelper_C80;
        
        // draw the labels...
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((this.labelGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[149]++;
            drawLeftLabels(leftKeys, g2, plotArea, linkArea, labelWidth, 
                    state);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[446]++;
            drawRightLabels(rightKeys, g2, plotArea, linkArea, labelWidth, 
                    state);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[447]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[150]++;}
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[448]++;

    }

    /**
     * Draws the left labels.
     * 
     * @param leftKeys  a collection of keys and angles (to the middle of the
     *         section, in degrees) for the sections on the left side of the 
     *         plot.
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param linkArea  the link area.
     * @param maxLabelWidth  the maximum label width.
     * @param state  the state.
     */
    protected void drawLeftLabels(KeyedValues leftKeys, Graphics2D g2, 
                                  Rectangle2D plotArea, Rectangle2D linkArea, 
                                  float maxLabelWidth, PiePlotState state) {
        
        this.labelDistributor.clear();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[449]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[450]++;
        double lGap = plotArea.getWidth() * this.labelGap;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[451]++;
        double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[452]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[16]++;


int CodeCoverConditionCoverageHelper_C81;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((i < leftKeys.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[16]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[17]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[18]++;
}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[453]++;   
            String label = this.labelGenerator.generateSectionLabel(
                    this.dataset, leftKeys.getKey(i));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[454]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[151]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[455]++;
                TextBlock block = TextUtilities.createTextBlock(label, 
                        this.labelFont, this.labelPaint, maxLabelWidth, 
                        new G2TextMeasurer(g2));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[456]++;
                TextBox labelBox = new TextBox(block);
                labelBox.setBackgroundPaint(this.labelBackgroundPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[457]++;
                labelBox.setOutlinePaint(this.labelOutlinePaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[458]++;
                labelBox.setOutlineStroke(this.labelOutlineStroke);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[459]++;
                labelBox.setShadowPaint(this.labelShadowPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[460]++;
                labelBox.setInteriorGap(this.labelPadding);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[461]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[462]++;
                double theta = Math.toRadians(
                        leftKeys.getValue(i).doubleValue());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[463]++;
                double baseY = state.getPieCenterY() - Math.sin(theta) 
                               * verticalLinkRadius;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[464]++;
                double hh = labelBox.getHeight(g2);

                this.labelDistributor.addPieLabelRecord(new PieLabelRecord(
                        leftKeys.getKey(i), theta, baseY, labelBox, hh,
                        lGap / 2.0 + lGap / 2.0 * -Math.cos(theta), 0.9 
                        + getExplodePercent(leftKeys.getKey(i))));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[465]++;

            } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[152]++;}
        }
        this.labelDistributor.distributeLabels(plotArea.getMinY(), 
                plotArea.getHeight());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[466]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[467]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[19]++;


int CodeCoverConditionCoverageHelper_C83;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((i < this.labelDistributor.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[19]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[20]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[21]++;
}
            drawLeftLabel(g2, state, 
                    this.labelDistributor.getPieLabelRecord(i));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[468]++;
        }
    }
    
    /**
     * Draws the right labels.
     * 
     * @param keys  the keys.
     * @param g2  the graphics device.
     * @param plotArea  the plot area.
     * @param linkArea  the link area.
     * @param maxLabelWidth  the maximum label width.
     * @param state  the state.
     */
    protected void drawRightLabels(KeyedValues keys, Graphics2D g2, 
                                   Rectangle2D plotArea, Rectangle2D linkArea, 
                                   float maxLabelWidth, PiePlotState state) {

        // draw the right labels...
        this.labelDistributor.clear();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[469]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[470]++;
        double lGap = plotArea.getWidth() * this.labelGap;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[471]++;
        double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[472]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[22]++;


int CodeCoverConditionCoverageHelper_C84;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((i < keys.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[22]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[23]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[24]++;
}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[473]++;
            String label = this.labelGenerator.generateSectionLabel(
                    this.dataset, keys.getKey(i));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[474]++;
int CodeCoverConditionCoverageHelper_C85;

            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[153]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[475]++;
                TextBlock block = TextUtilities.createTextBlock(label, 
                        this.labelFont, this.labelPaint, maxLabelWidth, 
                        new G2TextMeasurer(g2));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[476]++;
                TextBox labelBox = new TextBox(block);
                labelBox.setBackgroundPaint(this.labelBackgroundPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[477]++;
                labelBox.setOutlinePaint(this.labelOutlinePaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[478]++;
                labelBox.setOutlineStroke(this.labelOutlineStroke);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[479]++;
                labelBox.setShadowPaint(this.labelShadowPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[480]++;
                labelBox.setInteriorGap(this.labelPadding);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[481]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[482]++;
                double theta = Math.toRadians(keys.getValue(i).doubleValue());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[483]++;
                double baseY = state.getPieCenterY() 
                              - Math.sin(theta) * verticalLinkRadius;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[484]++;
                double hh = labelBox.getHeight(g2);
                this.labelDistributor.addPieLabelRecord(new PieLabelRecord(
                        keys.getKey(i), theta, baseY, labelBox, hh,
                        lGap / 2.0 + lGap / 2.0 * Math.cos(theta), 
                        0.9 + getExplodePercent(keys.getKey(i))));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[485]++;

            } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[154]++;}
        }
        this.labelDistributor.distributeLabels(plotArea.getMinY(), 
                plotArea.getHeight());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[486]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[487]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[25]++;


int CodeCoverConditionCoverageHelper_C86;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((i < this.labelDistributor.getItemCount()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[25]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[26]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[27]++;
}
            drawRightLabel(g2, state, 
                    this.labelDistributor.getPieLabelRecord(i));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[488]++;
        }

    }
    
    /**
     * Returns a collection of legend items for the pie chart.
     *
     * @return The legend items (never <code>null</code>).
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[489]++;

        LegendItemCollection result = new LegendItemCollection();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[490]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((this.dataset == null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[155]++;
            return result;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[156]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[491]++;
        List keys = this.dataset.getKeys();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[492]++;
        int section = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[493]++;
        Shape shape = getLegendItemShape();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[494]++;
        Iterator iterator = keys.iterator();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[495]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[28]++;


int CodeCoverConditionCoverageHelper_C88;
        while ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[28]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[29]--;
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.loops[30]++;
}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[496]++;
            Comparable key = (Comparable) iterator.next();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[497]++;
            Number n = this.dataset.getValue(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[498]++;
            boolean include = true;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[499]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[157]++;
                include = !this.ignoreNullValues;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[500]++;
   
            }
            else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[158]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[501]++;
                double v = n.doubleValue();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[502]++;
int CodeCoverConditionCoverageHelper_C90;
                if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((v == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[159]++;
                    include = !this.ignoreZeroValues;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[503]++;
   
                }
                else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[160]++;
                    include = v > 0.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[504]++;   
                }
            }
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[505]++;
int CodeCoverConditionCoverageHelper_C91;
            if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((include) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[161]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[506]++;
                String label = this.legendLabelGenerator.generateSectionLabel(
                        this.dataset, key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[507]++;
int CodeCoverConditionCoverageHelper_C92;
                if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[163]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[508]++;
                    String description = label;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[509]++;
                    String toolTipText = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[510]++;
int CodeCoverConditionCoverageHelper_C93;
                    if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((this.legendLabelToolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[165]++;
                        toolTipText = this.legendLabelToolTipGenerator
                                .generateSectionLabel(this.dataset, key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[511]++;

                    } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[166]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[512]++;
                    String urlText = null;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[513]++;
int CodeCoverConditionCoverageHelper_C94;
                    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((this.legendLabelURLGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[167]++;
                        urlText = this.legendLabelURLGenerator.generateURL(
                                this.dataset, key, this.pieIndex);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[514]++;

                    } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[168]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[515]++;
                    Paint paint = lookupSectionPaint(key, true);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[516]++;
                    Paint outlinePaint = lookupSectionOutlinePaint(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[517]++;
                    Stroke outlineStroke = lookupSectionOutlineStroke(key);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[518]++;
                    LegendItem item = new LegendItem(label, description, 
                            toolTipText, urlText, true, shape, true, paint, 
                            true, outlinePaint, outlineStroke, 
                            false,          // line not visible
                            new Line2D.Float(), new BasicStroke(), Color.black);
                    item.setDataset(getDataset());
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[519]++;
                    result.add(item);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[520]++;

                } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[164]++;}
                section++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[521]++;

            }
            else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[162]++;
                section++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[522]++;
            }
        }
        return result;
    }

    /**
     * Returns a short string describing the type of plot.
     *
     * @return The plot type.
     */
    public String getPlotType() {
        return localizationResources.getString("Pie_Plot");
    }

    /**
     * Returns a rectangle that can be used to create a pie section (taking
     * into account the amount by which the pie section is 'exploded').
     *
     * @param unexploded  the area inside which the unexploded pie sections are
     *                    drawn.
     * @param exploded  the area inside which the exploded pie sections are 
     *                  drawn.
     * @param angle  the start angle.
     * @param extent  the extent of the arc.
     * @param explodePercent  the amount by which the pie section is exploded.
     *
     * @return A rectangle that can be used to create a pie section.
     */
    protected Rectangle2D getArcBounds(Rectangle2D unexploded, 
                                       Rectangle2D exploded,
                                       double angle, double extent, 
                                       double explodePercent) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[523]++;
int CodeCoverConditionCoverageHelper_C95;

        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((explodePercent == 0.0) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[169]++;
            return unexploded;

        }
        else {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[170]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[524]++;
            Arc2D arc1 = new Arc2D.Double(unexploded, angle, extent / 2, 
                    Arc2D.OPEN);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[525]++;
            Point2D point1 = arc1.getEndPoint();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[526]++;
            Arc2D.Double arc2 = new Arc2D.Double(exploded, angle, extent / 2, 
                    Arc2D.OPEN);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[527]++;
            Point2D point2 = arc2.getEndPoint();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[528]++;
            double deltaX = (point1.getX() - point2.getX()) * explodePercent;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[529]++;
            double deltaY = (point1.getY() - point2.getY()) * explodePercent;
            return new Rectangle2D.Double(unexploded.getX() - deltaX, 
                    unexploded.getY() - deltaY, unexploded.getWidth(), 
                    unexploded.getHeight());
        }
    }
    
    /**
     * Draws a section label on the left side of the pie chart.
     * 
     * @param g2  the graphics device.
     * @param state  the state.
     * @param record  the label record.
     */
    protected void drawLeftLabel(Graphics2D g2, PiePlotState state, 
                                 PieLabelRecord record) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[530]++;

        double anchorX = state.getLinkArea().getMinX();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[531]++;
        double targetX = anchorX - record.getGap();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[532]++;
        double targetY = record.getAllocatedY();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[533]++;
int CodeCoverConditionCoverageHelper_C96;
        
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((this.labelLinksVisible) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[171]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[534]++;
            double theta = record.getAngle();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[535]++;
            double linkX = state.getPieCenterX() + Math.cos(theta) 
                    * state.getPieWRadius() * record.getLinkPercent();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[536]++;
            double linkY = state.getPieCenterY() - Math.sin(theta) 
                    * state.getPieHRadius() * record.getLinkPercent();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[537]++;
            double elbowX = state.getPieCenterX() + Math.cos(theta) 
                    * state.getLinkArea().getWidth() / 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[538]++;
            double elbowY = state.getPieCenterY() - Math.sin(theta) 
                    * state.getLinkArea().getHeight() / 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[539]++;
            double anchorY = elbowY;
            g2.setPaint(this.labelLinkPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[540]++;
            g2.setStroke(this.labelLinkStroke);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[541]++;
            g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[542]++;
            g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[543]++;
            g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[544]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[172]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[545]++;
        TextBox tb = record.getLabel();
        tb.draw(g2, (float) targetX, (float) targetY, RectangleAnchor.RIGHT);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[546]++;
        
    }

    /**
     * Draws a section label on the right side of the pie chart.
     * 
     * @param g2  the graphics device.
     * @param state  the state.
     * @param record  the label record.
     */
    protected void drawRightLabel(Graphics2D g2, PiePlotState state, 
                                  PieLabelRecord record) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[547]++;
        
        double anchorX = state.getLinkArea().getMaxX();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[548]++;
        double targetX = anchorX + record.getGap();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[549]++;
        double targetY = record.getAllocatedY();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[550]++;
int CodeCoverConditionCoverageHelper_C97;
        
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((this.labelLinksVisible) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[173]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[551]++;
            double theta = record.getAngle();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[552]++;
            double linkX = state.getPieCenterX() + Math.cos(theta) 
                    * state.getPieWRadius() * record.getLinkPercent();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[553]++;
            double linkY = state.getPieCenterY() - Math.sin(theta) 
                    * state.getPieHRadius() * record.getLinkPercent();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[554]++;
            double elbowX = state.getPieCenterX() + Math.cos(theta) 
                    * state.getLinkArea().getWidth() / 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[555]++;
            double elbowY = state.getPieCenterY() - Math.sin(theta) 
                    * state.getLinkArea().getHeight() / 2.0;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[556]++;
            double anchorY = elbowY;
            g2.setPaint(this.labelLinkPaint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[557]++;
            g2.setStroke(this.labelLinkStroke);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[558]++;
            g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[559]++;
            g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[560]++;
            g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[561]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[174]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[562]++;
        
        TextBox tb = record.getLabel();
        tb.draw(g2, (float) targetX, (float) targetY, RectangleAnchor.LEFT);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[563]++;
    
    }

    /**
     * Tests this plot for equality with an arbitrary object.  Note that the 
     * plot's dataset is NOT included in the test for equality.
     *
     * @param obj  the object to test against (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[564]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[175]++;
            return true;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[176]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[565]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((obj instanceof PiePlot) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[177]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[178]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[566]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[179]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[180]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[567]++;
        PiePlot that = (PiePlot) obj;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[568]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((this.pieIndex != that.pieIndex) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[181]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[182]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[569]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((this.interiorGap != that.interiorGap) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[183]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[184]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[570]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((this.circular != that.circular) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[185]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[186]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[571]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((this.startAngle != that.startAngle) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[187]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[188]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[572]++;
int CodeCoverConditionCoverageHelper_C105;
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((this.direction != that.direction) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[189]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[190]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[573]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((this.ignoreZeroValues != that.ignoreZeroValues) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[191]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[192]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[574]++;
int CodeCoverConditionCoverageHelper_C107;
        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((this.ignoreNullValues != that.ignoreNullValues) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[193]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[194]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[575]++;
int CodeCoverConditionCoverageHelper_C108;
        if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.sectionPaint, that.sectionPaint)) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[195]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[196]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[576]++;
int CodeCoverConditionCoverageHelper_C109;
        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.sectionPaintMap, 
                that.sectionPaintMap)) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[197]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[198]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[577]++;
int CodeCoverConditionCoverageHelper_C110;
        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.baseSectionPaint, 
                that.baseSectionPaint)) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[199]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[200]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[578]++;
int CodeCoverConditionCoverageHelper_C111;
        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((this.sectionOutlinesVisible != that.sectionOutlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[201]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[202]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[579]++;
int CodeCoverConditionCoverageHelper_C112;
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.sectionOutlinePaint, 
                that.sectionOutlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[203]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[204]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[580]++;
int CodeCoverConditionCoverageHelper_C113;
        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.sectionOutlinePaintMap, 
                that.sectionOutlinePaintMap)) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[205]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[206]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[581]++;
int CodeCoverConditionCoverageHelper_C114;
        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(
            this.baseSectionOutlinePaint, that.baseSectionOutlinePaint
        )) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[207]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[208]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[582]++;
int CodeCoverConditionCoverageHelper_C115;
        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.sectionOutlineStroke, 
                that.sectionOutlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[209]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[210]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[583]++;
int CodeCoverConditionCoverageHelper_C116;
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.sectionOutlineStrokeMap, 
                that.sectionOutlineStrokeMap)) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[211]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[212]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[584]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(
            this.baseSectionOutlineStroke, that.baseSectionOutlineStroke
        )) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[213]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[214]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[585]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.shadowPaint, that.shadowPaint)) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[215]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[216]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[586]++;
int CodeCoverConditionCoverageHelper_C119;
        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((this.shadowXOffset == that.shadowXOffset) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[217]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[218]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[587]++;
int CodeCoverConditionCoverageHelper_C120;
        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((this.shadowYOffset == that.shadowYOffset) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[219]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[220]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[588]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.explodePercentages, 
                that.explodePercentages)) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[221]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[222]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[589]++;
int CodeCoverConditionCoverageHelper_C122;
        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelGenerator, 
                that.labelGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[223]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[224]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[590]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelFont, that.labelFont)) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[225]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[226]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[591]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.labelPaint, that.labelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[227]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[228]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[592]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.labelBackgroundPaint, 
                that.labelBackgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[229]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[230]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[593]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.labelOutlinePaint, 
                that.labelOutlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[231]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[232]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[594]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelOutlineStroke, 
                that.labelOutlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[233]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[234]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[595]++;
int CodeCoverConditionCoverageHelper_C128;
        if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.labelShadowPaint, 
                that.labelShadowPaint)) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[235]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[236]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[596]++;
int CodeCoverConditionCoverageHelper_C129;
        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((this.simpleLabels != that.simpleLabels) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[237]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[238]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[597]++;
int CodeCoverConditionCoverageHelper_C130;
        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((this.simpleLabelOffset.equals(that.simpleLabelOffset)) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[239]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[240]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[598]++;
int CodeCoverConditionCoverageHelper_C131;
        if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((this.labelPadding.equals(that.labelPadding)) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[241]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[242]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[599]++;
int CodeCoverConditionCoverageHelper_C132;
        if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((this.maximumLabelWidth == that.maximumLabelWidth) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[243]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[244]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[600]++;
int CodeCoverConditionCoverageHelper_C133;
        if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((this.labelGap == that.labelGap) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[245]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[246]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[601]++;
int CodeCoverConditionCoverageHelper_C134;
        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((this.labelLinkMargin == that.labelLinkMargin) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[247]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[248]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[602]++;
int CodeCoverConditionCoverageHelper_C135;
        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((this.labelLinksVisible != that.labelLinksVisible) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[249]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[250]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[603]++;
int CodeCoverConditionCoverageHelper_C136;
        if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.labelLinkPaint, that.labelLinkPaint)) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[251]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[252]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[604]++;
int CodeCoverConditionCoverageHelper_C137;
        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelLinkStroke, 
                that.labelLinkStroke)) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[253]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[254]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[605]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.toolTipGenerator, 
                that.toolTipGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[255]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[256]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[606]++;
int CodeCoverConditionCoverageHelper_C139;
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.urlGenerator, that.urlGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[257]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[258]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[607]++;
int CodeCoverConditionCoverageHelper_C140;
        if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((this.minimumArcAngleToDraw == that.minimumArcAngleToDraw) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[259]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[260]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[608]++;
int CodeCoverConditionCoverageHelper_C141;
        if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((ShapeUtilities.equal(this.legendItemShape, that.legendItemShape)) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[261]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[262]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[609]++;
int CodeCoverConditionCoverageHelper_C142;
        if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.legendLabelGenerator, 
                that.legendLabelGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[263]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[264]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[610]++;
int CodeCoverConditionCoverageHelper_C143;
        if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.legendLabelToolTipGenerator,
                that.legendLabelToolTipGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[265]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[266]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[611]++;
int CodeCoverConditionCoverageHelper_C144;
        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.legendLabelURLGenerator,
                that.legendLabelURLGenerator)) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[267]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[268]++;}
        // can't find any difference...
        return true;
    }

    /**
     * Returns a clone of the plot.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if some component of the plot does 
     *         not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[612]++;
        PiePlot clone = (PiePlot) super.clone();
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[613]++;
int CodeCoverConditionCoverageHelper_C145;
        if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((clone.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[269]++;
            clone.dataset.addChangeListener(clone);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[614]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[270]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[615]++;
int CodeCoverConditionCoverageHelper_C146;
        if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((this.urlGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[271]++;
            clone.urlGenerator = (PieURLGenerator) ObjectUtilities.clone(
                    this.urlGenerator);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[616]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[272]++;}
        clone.legendItemShape = ShapeUtilities.clone(this.legendItemShape);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[617]++;
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[618]++;
int CodeCoverConditionCoverageHelper_C147;
        if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((this.legendLabelGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[273]++;
            clone.legendLabelGenerator = (PieSectionLabelGenerator) 
                    ObjectUtilities.clone(this.legendLabelGenerator);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[619]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[274]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[620]++;
int CodeCoverConditionCoverageHelper_C148;
        if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((this.legendLabelToolTipGenerator != null) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[275]++;
            clone.legendLabelToolTipGenerator = (PieSectionLabelGenerator) 
                    ObjectUtilities.clone(this.legendLabelToolTipGenerator);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[621]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[276]++;}
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[622]++;
int CodeCoverConditionCoverageHelper_C149;
        if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((this.legendLabelURLGenerator instanceof PublicCloneable) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[277]++;
            clone.legendLabelURLGenerator = (PieURLGenerator) 
                    ObjectUtilities.clone(this.legendLabelURLGenerator);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[623]++;

        } else {
  CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.branches[278]++;}
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
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[624]++;
        SerialUtilities.writePaint(this.sectionPaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[625]++;
        SerialUtilities.writePaint(this.baseSectionPaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[626]++;
        SerialUtilities.writePaint(this.sectionOutlinePaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[627]++;
        SerialUtilities.writePaint(this.baseSectionOutlinePaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[628]++;
        SerialUtilities.writeStroke(this.sectionOutlineStroke, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[629]++;
        SerialUtilities.writeStroke(this.baseSectionOutlineStroke, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[630]++;
        SerialUtilities.writePaint(this.shadowPaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[631]++;
        SerialUtilities.writePaint(this.labelPaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[632]++;
        SerialUtilities.writePaint(this.labelBackgroundPaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[633]++;
        SerialUtilities.writePaint(this.labelOutlinePaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[634]++;
        SerialUtilities.writeStroke(this.labelOutlineStroke, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[635]++;
        SerialUtilities.writePaint(this.labelShadowPaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[636]++;
        SerialUtilities.writePaint(this.labelLinkPaint, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[637]++;
        SerialUtilities.writeStroke(this.labelLinkStroke, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[638]++;
        SerialUtilities.writeShape(this.legendItemShape, stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[639]++;
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
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[640]++;
        this.sectionPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[641]++;
        this.baseSectionPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[642]++;
        this.sectionOutlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[643]++;
        this.baseSectionOutlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[644]++;
        this.sectionOutlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[645]++;
        this.baseSectionOutlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[646]++;
        this.shadowPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[647]++;
        this.labelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[648]++;
        this.labelBackgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[649]++;
        this.labelOutlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[650]++;
        this.labelOutlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[651]++;
        this.labelShadowPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[652]++;
        this.labelLinkPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[653]++;
        this.labelLinkStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[654]++;
        this.legendItemShape = SerialUtilities.readShape(stream);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[655]++;
    }
    
    // DEPRECATED METHODS...
    
    /**
     * Returns the paint for the specified section.
     * 
     * @param section  the section index (zero-based).
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @deprecated Use {@link #getSectionPaint(Comparable)} instead.
     */
    public Paint getSectionPaint(int section) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[656]++;
        Comparable key = getSectionKey(section);
        return getSectionPaint(key);       
    }
    
    /**
     * Sets the paint used to fill a section of the pie and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param section  the section index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @deprecated Use {@link #setSectionPaint(Comparable, Paint)} instead.
     */
    public void setSectionPaint(int section, Paint paint) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[657]++;
        Comparable key = getSectionKey(section);
        setSectionPaint(key, paint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[658]++;
    }
    
    /**
     * Returns the paint for the specified section.
     * 
     * @param section  the section index (zero-based).
     * 
     * @return The paint (possibly <code>null</code>).
     * 
     * @deprecated Use {@link #getSectionOutlinePaint(Comparable)} instead.
     */
    public Paint getSectionOutlinePaint(int section) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[659]++;
        Comparable key = getSectionKey(section);
        return getSectionOutlinePaint(key);
    }
    
    /**
     * Sets the paint used to fill a section of the pie and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param section  the section index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @deprecated Use {@link #setSectionOutlinePaint(Comparable, Paint)} 
     *     instead.
     */
    public void setSectionOutlinePaint(int section, Paint paint) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[660]++;
        Comparable key = getSectionKey(section);
        setSectionOutlinePaint(key, paint);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[661]++;
    }
    
    /**
     * Returns the stroke for the specified section.
     * 
     * @param section  the section index (zero-based).
     * 
     * @return The stroke (possibly <code>null</code>).
     *
     * @deprecated Use {@link #getSectionOutlineStroke(Comparable)} instead.
     */
    public Stroke getSectionOutlineStroke(int section) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[662]++;
        Comparable key = getSectionKey(section);
        return getSectionOutlineStroke(key);
    }
    
    /**
     * Sets the stroke used to fill a section of the pie and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param section  the section index (zero-based).
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @deprecated Use {@link #setSectionOutlineStroke(Comparable, Stroke)} 
     *     instead.
     */
    public void setSectionOutlineStroke(int section, Stroke stroke) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[663]++;
        Comparable key = getSectionKey(section);
        setSectionOutlineStroke(key, stroke);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[664]++;
    }
    
    /**
     * Returns the amount that a section should be 'exploded'.
     *
     * @param section  the section number.
     *
     * @return The amount that a section should be 'exploded'.
     * 
     * @deprecated Use {@link #getExplodePercent(Comparable)} instead.
     */
    public double getExplodePercent(int section) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[665]++;
        Comparable key = getSectionKey(section);
        return getExplodePercent(key);
    }

    /**
     * Sets the amount that a pie section should be exploded and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param section  the section index.
     * @param percent  the explode percentage (0.30 = 30 percent).
     * 
     * @deprecated Use {@link #setExplodePercent(Comparable, double)} instead.
     */
    public void setExplodePercent(int section, double percent) {
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[666]++;
        Comparable key = getSectionKey(section);
        setExplodePercent(key, percent);
CodeCoverCoverageCounter$2euxnu2cuum9brdnyap.statements[667]++;
    }

}

class CodeCoverCoverageCounter$2euxnu2cuum9brdnyap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2euxnu2cuum9brdnyap ());
  }
    public static long[] statements = new long[668];
    public static long[] branches = new long[279];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[150];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.PiePlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 149; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$2euxnu2cuum9brdnyap () {
    super("org.jfree.chart.plot.PiePlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 667; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 278; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 149; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.PiePlot.java");
      for (int i = 1; i <= 667; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 278; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 149; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

