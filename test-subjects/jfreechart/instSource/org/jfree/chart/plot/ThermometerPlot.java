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
 * --------------------
 * ThermometerPlot.java
 * --------------------
 *
 * (C) Copyright 2000-2007, by Bryan Scott and Contributors.
 *
 * Original Author:  Bryan Scott (based on MeterPlot by Hari).
 * Contributor(s):   David Gilbert (for Object Refinery Limited).
 *                   Arnaud Lelievre;
 *                   Julien Henry (see patch 1769088) (DG);
 *
 * Changes
 * -------
 * 11-Apr-2002 : Version 1, contributed by Bryan Scott;
 * 15-Apr-2002 : Changed to implement VerticalValuePlot;
 * 29-Apr-2002 : Added getVerticalValueAxis() method (DG);
 * 25-Jun-2002 : Removed redundant imports (DG);
 * 17-Sep-2002 : Reviewed with Checkstyle utility (DG);
 * 18-Sep-2002 : Extensive changes made to API, to iron out bugs and 
 *               inconsistencies (DG);
 * 13-Oct-2002 : Corrected error datasetChanged which would generate exceptions
 *               when value set to null (BRS).
 * 23-Jan-2003 : Removed one constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 02-Jun-2003 : Removed test for compatible range axis (DG);
 * 01-Jul-2003 : Added additional check in draw method to ensure value not 
 *               null (BRS);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 29-Sep-2003 : Updated draw to set value of cursor to non-zero and allow 
 *               painting of axis.  An incomplete fix and needs to be set for 
 *               left or right drawing (BRS);
 * 19-Nov-2003 : Added support for value labels to be displayed left of the 
 *               thermometer
 * 19-Nov-2003 : Improved axis drawing (now default axis does not draw axis line
 *               and is closer to the bulb).  Added support for the positioning
 *               of the axis to the left or right of the bulb. (BRS);
 * 03-Dec-2003 : Directly mapped deprecated setData()/getData() method to 
 *               get/setDataset() (TM);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 07-Apr-2004 : Changed string width calculation (DG);
 * 12-Nov-2004 : Implemented the new Zoomable interface (DG);
 * 06-Jan-2004 : Added getOrientation() method (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 29-Mar-2005 : Fixed equals() method (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 09-Jun-2005 : Fixed more bugs in equals() method (DG);
 * 10-Jun-2005 : Fixed minor bug in setDisplayRange() method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 14-Nov-2006 : Fixed margin when drawing (DG);
 * 03-May-2007 : Fixed datasetChanged() to handle null dataset, added null 
 *               argument check and event notification to setRangeAxis(), 
 *               added null argument check to setPadding(), setValueFont(),
 *               setValuePaint(), setValueFormat() and setMercuryPaint(), 
 *               deprecated get/setShowValueLines(), deprecated 
 *               getMinimum/MaximumVerticalDataValue(), and fixed serialization 
 *               bug (DG);
 * 24-Sep-2007 : Implemented new methods in Zoomable interface (DG);
 * 08-Oct-2007 : Added attributes for thermometer dimensions - see patch 1769088
 *               by Julien Henry (DG);
 * 
 */

package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;
import org.jfree.util.UnitType;

/**
 * A plot that displays a single value (from a {@link ValueDataset}) in a 
 * thermometer type display.
 * <p>
 * This plot supports a number of options:
 * <ol>
 * <li>three sub-ranges which could be viewed as 'Normal', 'Warning' 
 *   and 'Critical' ranges.</li>
 * <li>the thermometer can be run in two modes:
 *      <ul>
 *      <li>fixed range, or</li>
 *      <li>range adjusts to current sub-range.</li>
 *      </ul>
 * </li>
 * <li>settable units to be displayed.</li>
 * <li>settable display location for the value text.</li>
 * </ol>
 */
public class ThermometerPlot extends Plot implements ValueAxisPlot,
        Zoomable, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 4087093313147984390L;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[1]++;
  }
    
    /** A constant for unit type 'None'. */
    public static final int UNITS_NONE = 0;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[2]++;
  }

    /** A constant for unit type 'Fahrenheit'. */
    public static final int UNITS_FAHRENHEIT = 1;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[3]++;
  }

    /** A constant for unit type 'Celcius'. */
    public static final int UNITS_CELCIUS = 2;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[4]++;
  }

    /** A constant for unit type 'Kelvin'. */
    public static final int UNITS_KELVIN = 3;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[5]++;
  }

    /** A constant for the value label position (no label). */
    public static final int NONE = 0;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[6]++;
  }

    /** A constant for the value label position (right of the thermometer). */
    public static final int RIGHT = 1;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[7]++;
  }

    /** A constant for the value label position (left of the thermometer). */
    public static final int LEFT = 2;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[8]++;
  }

    /** A constant for the value label position (in the thermometer bulb). */
    public static final int BULB = 3;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[9]++;
  }

    /** A constant for the 'normal' range. */
    public static final int NORMAL = 0;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[10]++;
  }

    /** A constant for the 'warning' range. */
    public static final int WARNING = 1;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[11]++;
  }

    /** A constant for the 'critical' range. */
    public static final int CRITICAL = 2;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[12]++;
  }

    /** 
     * The bulb radius. 
     * 
     * @deprecated As of 1.0.7, use {@link #getBulbRadius()}.
     */
    protected static final int BULB_RADIUS = 40;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[13]++;
  }

    /** 
     * The bulb diameter. 
     * 
     * @deprecated As of 1.0.7, use {@link #getBulbDiameter()}.
     */
    protected static final int BULB_DIAMETER = BULB_RADIUS * 2;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[14]++;
  }

    /** 
     * The column radius. 
     * 
     * @deprecated As of 1.0.7, use {@link #getColumnRadius()}.
     */
    protected static final int COLUMN_RADIUS = 20;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[15]++;
  }

    /** 
     * The column diameter.
     * 
     * @deprecated As of 1.0.7, use {@link #getColumnDiameter()}.
     */
    protected static final int COLUMN_DIAMETER = COLUMN_RADIUS * 2;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[16]++;
  }

    /** 
     * The gap radius. 
     *
     * @deprecated As of 1.0.7, use {@link #getGap()}.
     */
    protected static final int GAP_RADIUS = 5;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[17]++;
  }

    /** 
     * The gap diameter. 
     *
     * @deprecated As of 1.0.7, use {@link #getGap()} times two.
     */
    protected static final int GAP_DIAMETER = GAP_RADIUS * 2;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[18]++;
  }

    /** The axis gap. */
    protected static final int AXIS_GAP = 10;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[19]++;
  }

    /** The unit strings. */
    protected static final String[] UNITS = {"", "\u00B0F", "\u00B0C", 
            "\u00B0K"};
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[20]++;
  }

    /** Index for low value in subrangeInfo matrix. */
    protected static final int RANGE_LOW = 0;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[21]++;
  }

    /** Index for high value in subrangeInfo matrix. */
    protected static final int RANGE_HIGH = 1;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[22]++;
  }

    /** Index for display low value in subrangeInfo matrix. */
    protected static final int DISPLAY_LOW = 2;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[23]++;
  }

    /** Index for display high value in subrangeInfo matrix. */
    protected static final int DISPLAY_HIGH = 3;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[24]++;
  }

    /** The default lower bound. */
    protected static final double DEFAULT_LOWER_BOUND = 0.0;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[25]++;
  }

    /** The default upper bound. */
    protected static final double DEFAULT_UPPER_BOUND = 100.0;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[26]++;
  }

    /** 
     * The default bulb radius.
     *
     * @since 1.0.7
     */
    protected static final int DEFAULT_BULB_RADIUS = 40;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[27]++;
  }

    /** 
     * The default column radius.
     *
     * @since 1.0.7
     */
    protected static final int DEFAULT_COLUMN_RADIUS = 20;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[28]++;
  }

    /** 
     * The default gap between the outlines representing the thermometer.
     *
     * @since 1.0.7
     */
    protected static final int DEFAULT_GAP = 5;
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[29]++;
  }

    /** The dataset for the plot. */
    private ValueDataset dataset;

    /** The range axis. */
    private ValueAxis rangeAxis;

    /** The lower bound for the thermometer. */
    private double lowerBound = DEFAULT_LOWER_BOUND;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[30]++;
  }

    /** The upper bound for the thermometer. */
    private double upperBound = DEFAULT_UPPER_BOUND;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[31]++;
  }

    /** 
     * The value label position.
     *
     * @since 1.0.7
     */
    private int bulbRadius = DEFAULT_BULB_RADIUS;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[32]++;
  }

    /** 
     * The column radius.
     *
     * @since 1.0.7
     */
    private int columnRadius = DEFAULT_COLUMN_RADIUS;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[33]++;
  }

    /** 
     * The gap between the two outlines the represent the thermometer.
     *
     * @since 1.0.7
     */
    private int gap = DEFAULT_GAP;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[34]++;
  }

    /** 
     * Blank space inside the plot area around the outside of the thermometer. 
     */
    private RectangleInsets padding;

    /** Stroke for drawing the thermometer */
    private transient Stroke thermometerStroke = new BasicStroke(1.0f);
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[35]++;
  }

    /** Paint for drawing the thermometer */
    private transient Paint thermometerPaint = Color.black;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[36]++;
  }

    /** The display units */
    private int units = UNITS_CELCIUS;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[37]++;
  }

    /** The value label position. */
    private int valueLocation = BULB;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[38]++;
  }

    /** The position of the axis **/
    private int axisLocation = LEFT;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[39]++;
  }

    /** The font to write the value in */
    private Font valueFont = new Font("SansSerif", Font.BOLD, 16);
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[40]++;
  }

    /** Colour that the value is written in */
    private transient Paint valuePaint = Color.white;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[41]++;
  }

    /** Number format for the value */
    private NumberFormat valueFormat = new DecimalFormat();
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[42]++;
  }

    /** The default paint for the mercury in the thermometer. */
    private transient Paint mercuryPaint = Color.lightGray;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[43]++;
  }

    /** A flag that controls whether value lines are drawn. */
    private boolean showValueLines = false;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[44]++;
  }

    /** The display sub-range. */
    private int subrange = -1;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[45]++;
  }

    /** The start and end values for the subranges. */
    private double[][] subrangeInfo = {
        {0.0, 50.0, 0.0, 50.0}, 
        {50.0, 75.0, 50.0, 75.0}, 
        {75.0, 100.0, 75.0, 100.0}
    };
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[46]++;
  }

    /** 
     * A flag that controls whether or not the axis range adjusts to the 
     * sub-ranges. 
     */
    private boolean followDataInSubranges = false;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[47]++;
  }

    /** 
     * A flag that controls whether or not the mercury paint changes with 
     * the subranges. 
     */
    private boolean useSubrangePaint = true;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[48]++;
  }

    /** Paint for each range */
    private transient Paint[] subrangePaint = {Color.green, Color.orange, 
            Color.red};
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[49]++;
  }

    /** A flag that controls whether the sub-range indicators are visible. */
    private boolean subrangeIndicatorsVisible = true;
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[50]++;
  }

    /** The stroke for the sub-range indicators. */
    private transient Stroke subrangeIndicatorStroke = new BasicStroke(2.0f);
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[51]++;
  }

    /** The range indicator stroke. */
    private transient Stroke rangeIndicatorStroke = new BasicStroke(3.0f);
  {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[52]++;
  }

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources =
        ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[53]++;
  }

    /**
     * Creates a new thermometer plot.
     */
    public ThermometerPlot() {
        this(new DefaultValueDataset());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[54]++;
    }

    /**
     * Creates a new thermometer plot, using default attributes where necessary.
     *
     * @param dataset  the data set.
     */
    public ThermometerPlot(ValueDataset dataset) {

        super();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[55]++;

        this.padding = new RectangleInsets(UnitType.RELATIVE, 0.05, 0.05, 0.05, 
                0.05);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[56]++;
        this.dataset = dataset;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[57]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[58]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[1]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[59]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[2]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[60]++;
        NumberAxis axis = new NumberAxis(null);
        axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[61]++;
        axis.setAxisLineVisible(false);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[62]++;
        axis.setPlot(this);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[63]++;
        axis.addChangeListener(this);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[64]++;
        this.rangeAxis = axis;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[65]++;
        setAxisRange();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[66]++;
    }

    /**
     * Returns the dataset for the plot.
     *
     * @return The dataset (possibly <code>null</code>).
     * 
     * @see #setDataset(ValueDataset)
     */
    public ValueDataset getDataset() {
        return this.dataset;
    }

    /**
     * Sets the dataset for the plot, replacing the existing dataset if there 
     * is one, and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset()
     */
    public void setDataset(ValueDataset dataset) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[67]++;

        // if there is an existing dataset, remove the plot from the list 
        // of change listeners...
        ValueDataset existing = this.dataset;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[68]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[3]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[69]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[4]++;}

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[70]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[71]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[5]++;
            setDatasetGroup(dataset.getGroup());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[72]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[73]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[6]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[74]++;

        // send a dataset change event to self...
        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[75]++;

    }

    /**
     * Returns the range axis.
     *
     * @return The range axis (never <code>null</code>).
     * 
     * @see #setRangeAxis(ValueAxis)
     */
    public ValueAxis getRangeAxis() {
        return this.rangeAxis;
    }

    /**
     * Sets the range axis for the plot and sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     *
     * @param axis  the new axis (<code>null</code> not permitted).
     * 
     * @see #getRangeAxis()
     */
    public void setRangeAxis(ValueAxis axis) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[76]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((axis == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[7]++;
            throw new IllegalArgumentException("Null 'axis' argument.");

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[8]++;}
        // plot is registered as a listener with the existing axis...
        this.rangeAxis.removeChangeListener(this);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[77]++;

        axis.setPlot(this);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[78]++;
        axis.addChangeListener(this);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[79]++;
        this.rangeAxis = axis;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[80]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[81]++;

    }

    /**
     * Returns the lower bound for the thermometer.  The data value can be set 
     * lower than this, but it will not be shown in the thermometer.
     *
     * @return The lower bound.
     * 
     * @see #setLowerBound(double)
     */
    public double getLowerBound() {
        return this.lowerBound;
    }

    /**
     * Sets the lower bound for the thermometer.
     *
     * @param lower the lower bound.
     * 
     * @see #getLowerBound()
     */
    public void setLowerBound(double lower) {
        this.lowerBound = lower;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[82]++;
        setAxisRange();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[83]++;
    }

    /**
     * Returns the upper bound for the thermometer.  The data value can be set 
     * higher than this, but it will not be shown in the thermometer.
     *
     * @return The upper bound.
     * 
     * @see #setUpperBound(double)
     */
    public double getUpperBound() {
        return this.upperBound;
    }

    /**
     * Sets the upper bound for the thermometer.
     *
     * @param upper the upper bound.
     * 
     * @see #getUpperBound()
     */
    public void setUpperBound(double upper) {
        this.upperBound = upper;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[84]++;
        setAxisRange();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[85]++;
    }

    /**
     * Sets the lower and upper bounds for the thermometer.
     *
     * @param lower  the lower bound.
     * @param upper  the upper bound.
     */
    public void setRange(double lower, double upper) {
        this.lowerBound = lower;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[86]++;
        this.upperBound = upper;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[87]++;
        setAxisRange();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[88]++;
    }

    /**
     * Returns the padding for the thermometer.  This is the space inside the 
     * plot area.
     *
     * @return The padding (never <code>null</code>).
     * 
     * @see #setPadding(RectangleInsets)
     */
    public RectangleInsets getPadding() {
        return this.padding;
    }

    /**
     * Sets the padding for the thermometer and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     *
     * @param padding  the padding (<code>null</code> not permitted).
     * 
     * @see #getPadding()
     */
    public void setPadding(RectangleInsets padding) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[89]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((padding == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[9]++;
            throw new IllegalArgumentException("Null 'padding' argument.");

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[10]++;}
        this.padding = padding;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[90]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[91]++;
    }

    /**
     * Returns the stroke used to draw the thermometer outline.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setThermometerStroke(Stroke)
     * @see #getThermometerPaint()
     */
    public Stroke getThermometerStroke() {
        return this.thermometerStroke;
    }

    /**
     * Sets the stroke used to draw the thermometer outline and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param s  the new stroke (<code>null</code> ignored).
     * 
     * @see #getThermometerStroke()
     */
    public void setThermometerStroke(Stroke s) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[92]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((s != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[11]++;
            this.thermometerStroke = s;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[93]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[94]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[12]++;}
    }

    /**
     * Returns the paint used to draw the thermometer outline.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setThermometerPaint(Paint)
     * @see #getThermometerStroke()
     */
    public Paint getThermometerPaint() {
        return this.thermometerPaint;
    }

    /**
     * Sets the paint used to draw the thermometer outline and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the new paint (<code>null</code> ignored).
     * 
     * @see #getThermometerPaint()
     */
    public void setThermometerPaint(Paint paint) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[95]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[13]++;
            this.thermometerPaint = paint;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[96]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[97]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[14]++;}
    }

    /**
     * Returns a code indicating the unit display type.  This is one of
     * {@link #UNITS_NONE}, {@link #UNITS_FAHRENHEIT}, {@link #UNITS_CELCIUS} 
     * and {@link #UNITS_KELVIN}.
     *
     * @return The units type.
     * 
     * @see #setUnits(int)
     */
    public int getUnits() {
        return this.units;
    }

    /**
     * Sets the units to be displayed in the thermometer. Use one of the 
     * following constants:
     *
     * <ul>
     * <li>UNITS_NONE : no units displayed.</li>
     * <li>UNITS_FAHRENHEIT : units displayed in Fahrenheit.</li>
     * <li>UNITS_CELCIUS : units displayed in Celcius.</li>
     * <li>UNITS_KELVIN : units displayed in Kelvin.</li>
     * </ul>
     *
     * @param u  the new unit type.
     * 
     * @see #getUnits()
     */
    public void setUnits(int u) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[98]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((u >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((u < UNITS.length) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[15]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[99]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.units != u) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[17]++;
                this.units = u;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[100]++;
                notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[101]++;

            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[16]++;}
    }

    /**
     * Sets the unit type.
     *
     * @param u  the unit type (<code>null</code> ignored).
     * 
     * @deprecated Use setUnits(int) instead.  Deprecated as of version 1.0.6,
     *     because this method is a little obscure and redundant anyway.
     */
    public void setUnits(String u) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[102]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((u == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[19]++;
            return;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[20]++;}

        u = u.toUpperCase().trim();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[103]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[104]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < UNITS.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[1]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[2]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[3]++;
}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[105]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((u.equals(UNITS[i].toUpperCase().trim())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[21]++;
                setUnits(i);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[106]++;
                i = UNITS.length;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[107]++;

            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[22]++;}
        }
    }

    /**
     * Returns a code indicating the location at which the value label is
     * displayed.
     *
     * @return The location (one of {@link #NONE}, {@link #RIGHT}, 
     *         {@link #LEFT} and {@link #BULB}.).
     */
    public int getValueLocation() {
        return this.valueLocation;
    }

    /**
     * Sets the location at which the current value is displayed and sends a
     * {@link PlotChangeEvent} to all registered listeners.
     * <P>
     * The location can be one of the constants:
     * <code>NONE</code>,
     * <code>RIGHT</code>
     * <code>LEFT</code> and
     * <code>BULB</code>.
     *
     * @param location  the location.
     */
    public void setValueLocation(int location) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[108]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((location >= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((location < 4) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[23]++;
            this.valueLocation = location;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[109]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[110]++;

        }
        else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[24]++;
            throw new IllegalArgumentException("Location not recognised.");
        }
    }

    /**
     * Returns the axis location.
     *
     * @return The location (one of {@link #NONE}, {@link #LEFT} and 
     *         {@link #RIGHT}).
     *         
     * @see #setAxisLocation(int)
     */
    public int getAxisLocation() {
        return this.axisLocation;
    }

    /**
     * Sets the location at which the axis is displayed relative to the 
     * thermometer, and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     *
     * @param location  the location (one of {@link #NONE}, {@link #LEFT} and 
     *         {@link #RIGHT}).
     * 
     * @see #getAxisLocation()
     */
    public void setAxisLocation(int location) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[111]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((location >= 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((location < 3) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[25]++;
            this.axisLocation = location;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[112]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[113]++;

        }
        else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[26]++;
            throw new IllegalArgumentException("Location not recognised.");
        }
    }

    /**
     * Gets the font used to display the current value.
     *
     * @return The font.
     * 
     * @see #setValueFont(Font)
     */
    public Font getValueFont() {
        return this.valueFont;
    }

    /**
     * Sets the font used to display the current value.
     *
     * @param f  the new font (<code>null</code> not permitted).
     * 
     * @see #getValueFont()
     */
    public void setValueFont(Font f) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[114]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[27]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[28]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[115]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.valueFont.equals(f)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[29]++;
            this.valueFont = f;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[116]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[117]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[30]++;}
    }

    /**
     * Gets the paint used to display the current value.
    *
     * @return The paint.
     * 
     * @see #setValuePaint(Paint)
     */
    public Paint getValuePaint() {
        return this.valuePaint;
    }

    /**
     * Sets the paint used to display the current value and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the new paint (<code>null</code> not permitted).
     * 
     * @see #getValuePaint()
     */
    public void setValuePaint(Paint paint) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[118]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[31]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[32]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[119]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((this.valuePaint.equals(paint)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[33]++;
            this.valuePaint = paint;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[120]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[121]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[34]++;}
    }

    // FIXME: No getValueFormat() method?
    
    /**
     * Sets the formatter for the value label and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param formatter  the new formatter (<code>null</code> not permitted).
     */
    public void setValueFormat(NumberFormat formatter) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[122]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[35]++;
            throw new IllegalArgumentException("Null 'formatter' argument.");

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[36]++;}
        this.valueFormat = formatter;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[123]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[124]++;
    }

    /**
     * Returns the default mercury paint.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setMercuryPaint(Paint)
     */
    public Paint getMercuryPaint() {
        return this.mercuryPaint;
    }

    /**
     * Sets the default mercury paint and sends a {@link PlotChangeEvent} to 
     * all registered listeners.
     *
     * @param paint  the new paint (<code>null</code> not permitted).
     * 
     * @see #getMercuryPaint()
     */
    public void setMercuryPaint(Paint paint) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[125]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[37]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[38]++;}
        this.mercuryPaint = paint;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[126]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[127]++;
    }

    /**
     * Returns the flag that controls whether not value lines are displayed.
     *
     * @return The flag.
     * 
     * @see #setShowValueLines(boolean)
     * 
     * @deprecated This flag doesn't do anything useful/visible.  Deprecated 
     *     as of version 1.0.6.
     */
    public boolean getShowValueLines() {
        return this.showValueLines;
    }

    /**
     * Sets the display as to whether to show value lines in the output.
     *
     * @param b Whether to show value lines in the thermometer
     * 
     * @see #getShowValueLines()
     * 
     * @deprecated This flag doesn't do anything useful/visible.  Deprecated 
     *     as of version 1.0.6.
     */
    public void setShowValueLines(boolean b) {
        this.showValueLines = b;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[128]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[129]++;
    }

    /**
     * Sets information for a particular range.
     *
     * @param range  the range to specify information about.
     * @param low  the low value for the range
     * @param hi  the high value for the range
     */
    public void setSubrangeInfo(int range, double low, double hi) {
        setSubrangeInfo(range, low, hi, low, hi);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[130]++;
    }

    /**
     * Sets the subrangeInfo attribute of the ThermometerPlot object
     *
     * @param range  the new rangeInfo value.
     * @param rangeLow  the new rangeInfo value
     * @param rangeHigh  the new rangeInfo value
     * @param displayLow  the new rangeInfo value
     * @param displayHigh  the new rangeInfo value
     */
    public void setSubrangeInfo(int range,
                                double rangeLow, double rangeHigh,
                                double displayLow, double displayHigh) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[131]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((range >= 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((range < 3) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[39]++;
            setSubrange(range, rangeLow, rangeHigh);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[132]++;
            setDisplayRange(range, displayLow, displayHigh);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[133]++;
            setAxisRange();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[134]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[135]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[40]++;}

    }

    /**
     * Sets the bounds for a subrange.
     *
     * @param range  the range type.
     * @param low  the low value.
     * @param high  the high value.
     */
    public void setSubrange(int range, double low, double high) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[136]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((range >= 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((range < 3) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[41]++;
            this.subrangeInfo[range][RANGE_HIGH] = high;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[137]++;
            this.subrangeInfo[range][RANGE_LOW] = low;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[138]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[42]++;}
    }

    /**
     * Sets the displayed bounds for a sub range.
     *
     * @param range  the range type.
     * @param low  the low value.
     * @param high  the high value.
     */
    public void setDisplayRange(int range, double low, double high) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[139]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C23 |= (128)) == 0 || true) &&
 ((range >= 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (64)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C23 |= (32)) == 0 || true) &&
 ((range < this.subrangeInfo.length) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (16)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((isValidNumber(high)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((isValidNumber(low)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 4) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 4) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[43]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[140]++;
int CodeCoverConditionCoverageHelper_C24;
 
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((high > low) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[45]++;
                this.subrangeInfo[range][DISPLAY_HIGH] = high;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[141]++;
                this.subrangeInfo[range][DISPLAY_LOW] = low;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[142]++;

            }
            else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[46]++;
                this.subrangeInfo[range][DISPLAY_HIGH] = low;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[143]++;
                this.subrangeInfo[range][DISPLAY_LOW] = high;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[144]++;
            }


        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[44]++;}

    }

    /**
     * Gets the paint used for a particular subrange.
     *
     * @param range  the range (.
     *
     * @return The paint.
     * 
     * @see #setSubrangePaint(int, Paint)
     */
    public Paint getSubrangePaint(int range) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[145]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((range >= 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((range < this.subrangePaint.length) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[47]++;
            return this.subrangePaint[range];

        }
        else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[48]++;
            return this.mercuryPaint;
        }
    }

    /**
     * Sets the paint to be used for a subrange and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param range  the range (0, 1 or 2).
     * @param paint  the paint to be applied (<code>null</code> not permitted).
     * 
     * @see #getSubrangePaint(int)
     */
    public void setSubrangePaint(int range, Paint paint) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[146]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C26 |= (32)) == 0 || true) &&
 ((range >= 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((range < this.subrangePaint.length) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[49]++;
            this.subrangePaint[range] = paint;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[147]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[148]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[50]++;}
    }

    /**
     * Returns a flag that controls whether or not the thermometer axis zooms 
     * to display the subrange within which the data value falls.
     *
     * @return The flag.
     */
    public boolean getFollowDataInSubranges() {
        return this.followDataInSubranges;
    }

    /**
     * Sets the flag that controls whether or not the thermometer axis zooms 
     * to display the subrange within which the data value falls.
     *
     * @param flag  the flag.
     */
    public void setFollowDataInSubranges(boolean flag) {
        this.followDataInSubranges = flag;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[149]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[150]++;
    }

    /**
     * Returns a flag that controls whether or not the mercury color changes 
     * for each subrange.
     *
     * @return The flag.
     * 
     * @see #setUseSubrangePaint(boolean)
     */
    public boolean getUseSubrangePaint() {
        return this.useSubrangePaint;
    }

    /**
     * Sets the range colour change option.
     *
     * @param flag the new range colour change option
     * 
     * @see #getUseSubrangePaint()
     */
    public void setUseSubrangePaint(boolean flag) {
        this.useSubrangePaint = flag;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[151]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[152]++;
    }

    /**
     * Returns the bulb radius, in Java2D units.

     * @return The bulb radius.
     * 
     * @since 1.0.7
     */
    public int getBulbRadius() {
        return this.bulbRadius;
    }

    /**
     * Sets the bulb radius (in Java2D units) and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param r  the new radius (in Java2D units).
     * 
     * @see #getBulbRadius()
     * 
     * @since 1.0.7
     */
    public void setBulbRadius(int r) {
        this.bulbRadius = r;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[153]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[154]++;
    }

    /**
     * Returns the bulb diameter, which is always twice the value returned
     * by {@link #getBulbRadius()}.
     * 
     * @return The bulb diameter.
     * 
     * @since 1.0.7
     */
    public int getBulbDiameter() {
        return getBulbRadius() * 2;
    }

    /**
     * Returns the column radius, in Java2D units.
     * 
     * @return The column radius.
     * 
     * @see #setColumnRadius(int)
     * 
     * @since 1.0.7
     */
    public int getColumnRadius() {
        return this.columnRadius;
    }

    /**
     * Sets the column radius (in Java2D units) and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param r  the new radius.
     * 
     * @see #getColumnRadius()
     * 
     * @since 1.0.7
     */
    public void setColumnRadius(int r) {
        this.columnRadius = r;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[155]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[156]++;
    }

    /**
     * Returns the column diameter, which is always twice the value returned
     * by {@link #getColumnRadius()}.
     * 
     * @return The column diameter.
     * 
     * @since 1.0.7
     */
    public int getColumnDiameter() {
        return getColumnRadius() * 2;
    }

    /**
     * Returns the gap, in Java2D units, between the two outlines that 
     * represent the thermometer.
     * 
     * @return The gap.
     * 
     * @see #setGap(int)
     * 
     * @since 1.0.7
     */
    public int getGap() {
        return this.gap;
    }

    /**
     * Sets the gap (in Java2D units) between the two outlines that represent
     * the thermometer, and sends a {@link PlotChangeEvent} to all registered 
     * listeners.
     * 
     * @param gap  the new gap.
     * 
     * @see #getGap()
     * 
     * @since 1.0.7
     */
    public void setGap(int gap) {
        this.gap = gap;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[157]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[158]++;
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
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[159]++;

        RoundRectangle2D outerStem = new RoundRectangle2D.Double();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[160]++;
        RoundRectangle2D innerStem = new RoundRectangle2D.Double();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[161]++;
        RoundRectangle2D mercuryStem = new RoundRectangle2D.Double();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[162]++;
        Ellipse2D outerBulb = new Ellipse2D.Double();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[163]++;
        Ellipse2D innerBulb = new Ellipse2D.Double();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[164]++;
        String temp = null;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[165]++;
        FontMetrics metrics = null;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[166]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[51]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[167]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[52]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[168]++;

        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[169]++;
        drawBackground(g2, area);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[170]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[171]++;

        // adjust for padding...
        Rectangle2D interior = (Rectangle2D) area.clone();
        this.padding.trim(interior);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[172]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[173]++;
        int midX = (int) (interior.getX() + (interior.getWidth() / 2));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[174]++;
        int midY = (int) (interior.getY() + (interior.getHeight() / 2));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[175]++;
        int stemTop = (int) (interior.getMinY() + getBulbRadius());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[176]++;
        int stemBottom = (int) (interior.getMaxY() - getBulbDiameter());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[177]++;
        Rectangle2D dataArea = new Rectangle2D.Double(midX - getColumnRadius(), 
                stemTop, getColumnRadius(), stemBottom - stemTop);

        outerBulb.setFrame(midX - getBulbRadius(), stemBottom, 
                getBulbDiameter(), getBulbDiameter());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[178]++;

        outerStem.setRoundRect(midX - getColumnRadius(), interior.getMinY(), 
                getColumnDiameter(), stemBottom + getBulbDiameter() - stemTop,
                getColumnDiameter(), getColumnDiameter());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[179]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[180]++;

        Area outerThermometer = new Area(outerBulb);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[181]++;
        Area tempArea = new Area(outerStem);
        outerThermometer.add(tempArea);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[182]++;

        innerBulb.setFrame(midX - getBulbRadius() + getGap(), stemBottom 
                + getGap(), getBulbDiameter() - getGap() * 2, getBulbDiameter()
                - getGap() * 2);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[183]++;

        innerStem.setRoundRect(midX - getColumnRadius() + getGap(), 
                interior.getMinY() + getGap(), getColumnDiameter() 
                - getGap() * 2, stemBottom + getBulbDiameter() - getGap() * 2 
                - stemTop, getColumnDiameter() - getGap() * 2, 
                getColumnDiameter() - getGap() * 2);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[184]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[185]++;

        Area innerThermometer = new Area(innerBulb);
        tempArea = new Area(innerStem);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[186]++;
        innerThermometer.add(tempArea);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[187]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[188]++;
int CodeCoverConditionCoverageHelper_C28;
   
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.dataset.getValue() != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[53]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[189]++;
            double current = this.dataset.getValue().doubleValue();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[190]++;
            double ds = this.rangeAxis.valueToJava2D(current, dataArea, 
                    RectangleEdge.LEFT);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[191]++;

            int i = getColumnDiameter() - getGap() * 2;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[192]++; // already calculated
            int j = getColumnRadius() - getGap();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[193]++; // already calculated
            int l = (i / 2);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[194]++;
            int k = (int) Math.round(ds);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[195]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((k < (getGap() + interior.getMinY())) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[55]++;
                k = (int) (getGap() + interior.getMinY());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[196]++;
                l = getBulbRadius();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[197]++;

            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[56]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[198]++;

            Area mercury = new Area(innerBulb);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[199]++;
int CodeCoverConditionCoverageHelper_C30;

            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((k < (stemBottom + getBulbRadius())) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[57]++;
                mercuryStem.setRoundRect(midX - j, k, i, 
                        (stemBottom + getBulbRadius()) - k, l, l);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[200]++;
                tempArea = new Area(mercuryStem);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[201]++;
                mercury.add(tempArea);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[202]++;

            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[58]++;}

            g2.setPaint(getCurrentPaint());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[203]++;
            g2.fill(mercury);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[204]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[205]++;
int CodeCoverConditionCoverageHelper_C31;

            // draw range indicators...
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.subrangeIndicatorsVisible) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[59]++;
                g2.setStroke(this.subrangeIndicatorStroke);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[206]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[207]++;
                Range range = this.rangeAxis.getRange();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[208]++;

                // draw start of normal range
                double value = this.subrangeInfo[NORMAL][RANGE_LOW];
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[209]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[61]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[210]++;
                    double x = midX + getColumnRadius() + 2;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[211]++;
                    double y = this.rangeAxis.valueToJava2D(value, dataArea, 
                            RectangleEdge.LEFT);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[212]++;
                    Line2D line = new Line2D.Double(x, y, x + 10, y);
                    g2.setPaint(this.subrangePaint[NORMAL]);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[213]++;
                    g2.draw(line);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[214]++;

                } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[62]++;}

                // draw start of warning range
                value = this.subrangeInfo[WARNING][RANGE_LOW];
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[215]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[216]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[63]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[217]++;
                    double x = midX + getColumnRadius() + 2;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[218]++;
                    double y = this.rangeAxis.valueToJava2D(value, dataArea, 
                            RectangleEdge.LEFT);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[219]++;
                    Line2D line = new Line2D.Double(x, y, x + 10, y);
                    g2.setPaint(this.subrangePaint[WARNING]);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[220]++;
                    g2.draw(line);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[221]++;

                } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[64]++;}

                // draw start of critical range
                value = this.subrangeInfo[CRITICAL][RANGE_LOW];
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[222]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[223]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[65]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[224]++;
                    double x = midX + getColumnRadius() + 2;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[225]++;
                    double y = this.rangeAxis.valueToJava2D(value, dataArea, 
                            RectangleEdge.LEFT);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[226]++;
                    Line2D line = new Line2D.Double(x, y, x + 10, y);
                    g2.setPaint(this.subrangePaint[CRITICAL]);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[227]++;
                    g2.draw(line);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[228]++;

                } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[66]++;}

            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[60]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[229]++;
int CodeCoverConditionCoverageHelper_C35;

            // draw the axis...
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((this.rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.axisLocation != NONE) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[67]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[230]++;
                int drawWidth = AXIS_GAP;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[231]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.showValueLines) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[69]++;
                    drawWidth += getColumnDiameter();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[232]++;

                } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[70]++;}
                Rectangle2D drawArea;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[233]++;
                double cursor = 0;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[234]++;

                switch (this.axisLocation) {
                    case RIGHT:
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[71]++;
                        cursor = midX + getColumnRadius();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[235]++;
                        drawArea = new Rectangle2D.Double(cursor,
                                stemTop, drawWidth, (stemBottom - stemTop + 1));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[236]++;
                        this.rangeAxis.draw(g2, cursor, area, drawArea, 
                                RectangleEdge.RIGHT, null);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[237]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[238]++;
                        break;

                    case LEFT:
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[72]++;
                    default:
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[73]++;
                        //cursor = midX - COLUMN_RADIUS - AXIS_GAP;
                        cursor = midX - getColumnRadius();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[239]++;
                        drawArea = new Rectangle2D.Double(cursor, stemTop,
                                drawWidth, (stemBottom - stemTop + 1));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[240]++;
                        this.rangeAxis.draw(g2, cursor, area, drawArea, 
                                RectangleEdge.LEFT, null);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[241]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[242]++;
                        break;
                }

                   
            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[68]++;}

            // draw text value on screen
            g2.setFont(this.valueFont);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[243]++;
            g2.setPaint(this.valuePaint);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[244]++;
            metrics = g2.getFontMetrics();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[245]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[246]++;
            switch (this.valueLocation) {
                case RIGHT:
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[74]++;
                    g2.drawString(this.valueFormat.format(current), 
                            midX + getColumnRadius() + getGap(), midY);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[247]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[248]++;
                    break;
                case LEFT:
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[75]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[249]++;
                    String valueString = this.valueFormat.format(current);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[250]++;
                    int stringWidth = metrics.stringWidth(valueString);
                    g2.drawString(valueString, midX - getColumnRadius() 
                            - getGap() - stringWidth, midY);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[251]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[252]++;
                    break;
                case BULB:
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[76]++;
                    temp = this.valueFormat.format(current);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[253]++;
                    i = metrics.stringWidth(temp) / 2;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[254]++;
                    g2.drawString(temp, midX - i, 
                            stemBottom + getBulbRadius() + getGap());
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[255]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[256]++;
                    break;
                default:
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[77]++;
            }

            /***/
        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[54]++;}

        g2.setPaint(this.thermometerPaint);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[257]++;
        g2.setFont(this.valueFont);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[258]++;

        //  draw units indicator
        metrics = g2.getFontMetrics();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[259]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[260]++;
        int tickX1 = midX - getColumnRadius() - getGap() * 2
                     - metrics.stringWidth(UNITS[this.units]);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[261]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((tickX1 > area.getMinX()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[78]++;
            g2.drawString(UNITS[this.units], tickX1, 
                    (int) (area.getMinY() + 20));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[262]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[79]++;}

        // draw thermometer outline
        g2.setStroke(this.thermometerStroke);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[263]++;
        g2.draw(outerThermometer);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[264]++;
        g2.draw(innerThermometer);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[265]++;

        drawOutline(g2, area);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[266]++;
    }

    /**
     * A zoom method that does nothing.  Plots are required to support the 
     * zoom operation.  In the case of a thermometer chart, it doesn't make 
     * sense to zoom in or out, so the method is empty.
     *
     * @param percent  the zoom percentage.
     */
    public void zoom(double percent) {
        // intentionally blank
   }

    /**
     * Returns a short string describing the type of plot.
     *
     * @return A short string describing the type of plot.
     */
    public String getPlotType() {
        return localizationResources.getString("Thermometer_Plot");
    }

    /**
     * Checks to see if a new value means the axis range needs adjusting.
     *
     * @param event  the dataset change event.
     */
    public void datasetChanged(DatasetChangeEvent event) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[267]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[80]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[268]++;
            Number vn = this.dataset.getValue();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[269]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((vn != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[82]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[270]++;
                double value = vn.doubleValue();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[271]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((inSubrange(NORMAL, value)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[84]++;
                    this.subrange = NORMAL;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[272]++;

                }
                else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[85]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[273]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((inSubrange(WARNING, value)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[86]++;
                   this.subrange = WARNING;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[274]++;

                }
                else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[87]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[275]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((inSubrange(CRITICAL, value)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[88]++;
                    this.subrange = CRITICAL;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[276]++;

                }
                else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[89]++;
                    this.subrange = -1;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[277]++;
                }
}
}
                setAxisRange();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[278]++;

            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[83]++;}

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[81]++;}
        super.datasetChanged(event);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[279]++;
    }

    /**
     * Returns the minimum value in either the domain or the range, whichever
     * is displayed against the vertical axis for the particular type of plot
     * implementing this interface.
     *
     * @return The minimum value in either the domain or the range.
     * 
     * @deprecated This method is not used.  Officially deprecated in version 
     *         1.0.6.
     */
    public Number getMinimumVerticalDataValue() {
        return new Double(this.lowerBound);
    }

    /**
     * Returns the maximum value in either the domain or the range, whichever
     * is displayed against the vertical axis for the particular type of plot
     * implementing this interface.
     *
     * @return The maximum value in either the domain or the range
     * 
     * @deprecated This method is not used.  Officially deprecated in version 
     *         1.0.6.
     */
    public Number getMaximumVerticalDataValue() {
        return new Double(this.upperBound);
    }

    /**
     * Returns the data range.
     *
     * @param axis  the axis.
     *
     * @return The range of data displayed.
     */
    public Range getDataRange(ValueAxis axis) {
       return new Range(this.lowerBound, this.upperBound);
    }

    /**
     * Sets the axis range to the current values in the rangeInfo array.
     */
    protected void setAxisRange() {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[280]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((this.subrange >= 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.followDataInSubranges) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[90]++;
            this.rangeAxis.setRange(
                    new Range(this.subrangeInfo[this.subrange][DISPLAY_LOW],
                    this.subrangeInfo[this.subrange][DISPLAY_HIGH]));
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[281]++;

        }
        else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[91]++;
            this.rangeAxis.setRange(this.lowerBound, this.upperBound);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[282]++;
        }
    }

    /**
     * Returns the legend items for the plot.
     *
     * @return <code>null</code>.
     */
    public LegendItemCollection getLegendItems() {
        return null;
    }

    /**
     * Returns the orientation of the plot.
     * 
     * @return The orientation (always {@link PlotOrientation#VERTICAL}).
     */
    public PlotOrientation getOrientation() {
        return PlotOrientation.VERTICAL;    
    }

    /**
     * Determine whether a number is valid and finite.
     *
     * @param d  the number to be tested.
     *
     * @return <code>true</code> if the number is valid and finite, and 
     *         <code>false</code> otherwise.
     */
    protected static boolean isValidNumber(double d) {
        return (!(Double.isNaN(d) || Double.isInfinite(d)));
    }

    /**
     * Returns true if the value is in the specified range, and false otherwise.
     *
     * @param subrange  the subrange.
     * @param value  the value to check.
     *
     * @return A boolean.
     */
    private boolean inSubrange(int subrange, double value) {
        return (value > this.subrangeInfo[subrange][RANGE_LOW]
            && value <= this.subrangeInfo[subrange][RANGE_HIGH]);
    }

    /**
     * Returns the mercury paint corresponding to the current data value.
     * Called from the {@link #draw(Graphics2D, Rectangle2D, Point2D, 
     * PlotState, PlotRenderingInfo)} method.
     *
     * @return The paint (never <code>null</code>).
     */
    private Paint getCurrentPaint() {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[283]++;
        Paint result = this.mercuryPaint;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[284]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.useSubrangePaint) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[92]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[285]++;
            double value = this.dataset.getValue().doubleValue();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[286]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((inSubrange(NORMAL, value)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[94]++;
                result = this.subrangePaint[NORMAL];
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[287]++;

            }
            else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[95]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[288]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((inSubrange(WARNING, value)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[96]++;
                result = this.subrangePaint[WARNING];
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[289]++;

            }
            else {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[97]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[290]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((inSubrange(CRITICAL, value)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[98]++;
                result = this.subrangePaint[CRITICAL];
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[291]++;

            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[99]++;}
}
}

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[93]++;}
        return result;
    }

    /**
     * Tests this plot for equality with another object.  The plot's dataset
     * is not considered in the test.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[292]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[100]++;
            return true;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[101]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[293]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((obj instanceof ThermometerPlot) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[102]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[103]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[294]++;
        ThermometerPlot that = (ThermometerPlot) obj;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[295]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[104]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[105]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[296]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rangeAxis, that.rangeAxis)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[106]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[107]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[297]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((this.axisLocation != that.axisLocation) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[108]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[109]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[298]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((this.lowerBound != that.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[110]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[111]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[299]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((this.upperBound != that.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[112]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[113]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[300]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.padding, that.padding)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[114]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[115]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[301]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.thermometerStroke, 
                that.thermometerStroke)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[116]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[117]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[302]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.thermometerPaint, 
                that.thermometerPaint)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[118]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[119]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[303]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((this.units != that.units) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[120]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[121]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[304]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((this.valueLocation != that.valueLocation) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[122]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[123]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[305]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.valueFont, that.valueFont)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[124]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[125]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[306]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.valuePaint, that.valuePaint)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[126]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[127]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[307]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.valueFormat, that.valueFormat)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[128]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[129]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[308]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.mercuryPaint, that.mercuryPaint)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[130]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[131]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[309]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((this.showValueLines != that.showValueLines) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[132]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[133]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[310]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((this.subrange != that.subrange) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[134]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[135]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[311]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((this.followDataInSubranges != that.followDataInSubranges) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[136]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[137]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[312]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((equal(this.subrangeInfo, that.subrangeInfo)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[138]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[139]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[313]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.useSubrangePaint != that.useSubrangePaint) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[140]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[141]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[314]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((this.bulbRadius != that.bulbRadius) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[142]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[143]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[315]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((this.columnRadius != that.columnRadius) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[144]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[145]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[316]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((this.gap != that.gap) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[146]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[147]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[317]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[4]++;


int CodeCoverConditionCoverageHelper_C72;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((i < this.subrangePaint.length) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[4]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[5]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[6]++;
}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[318]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.subrangePaint[i], 
                    that.subrangePaint[i])) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[148]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[149]++;}
        }
        return true;
    }

    /**
     * Tests two double[][] arrays for equality.
     * 
     * @param array1  the first array (<code>null</code> permitted).
     * @param array2  the second arrray (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    private static boolean equal(double[][] array1, double[][] array2) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[319]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((array1 == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[150]++;
            return (array2 == null);

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[151]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[320]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((array2 == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[152]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[153]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[321]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((array1.length != array2.length) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[154]++;
            return false;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[155]++;}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[322]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[7]++;


int CodeCoverConditionCoverageHelper_C77;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((i < array1.length) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[7]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[8]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[9]++;
}
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[323]++;
int CodeCoverConditionCoverageHelper_C78;
            if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((Arrays.equals(array1[i], array2[i])) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[156]++;
                return false;

            } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[157]++;}
        }
        return true;
    }

    /**
     * Returns a clone of the plot.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the plot cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[324]++;

        ThermometerPlot clone = (ThermometerPlot) super.clone();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[325]++;
int CodeCoverConditionCoverageHelper_C79;

        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((clone.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[158]++;
            clone.dataset.addChangeListener(clone);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[326]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[159]++;}
        clone.rangeAxis = (ValueAxis) ObjectUtilities.clone(this.rangeAxis);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[327]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[328]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((clone.rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[160]++;
            clone.rangeAxis.setPlot(clone);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[329]++;
            clone.rangeAxis.addChangeListener(clone);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[330]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[161]++;}
        clone.valueFormat = (NumberFormat) this.valueFormat.clone();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[331]++;
        clone.subrangePaint = (Paint[]) this.subrangePaint.clone();
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[332]++;

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
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[333]++;
        SerialUtilities.writeStroke(this.thermometerStroke, stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[334]++;
        SerialUtilities.writePaint(this.thermometerPaint, stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[335]++;
        SerialUtilities.writePaint(this.valuePaint, stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[336]++;
        SerialUtilities.writePaint(this.mercuryPaint, stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[337]++;
        SerialUtilities.writeStroke(this.subrangeIndicatorStroke, stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[338]++;
        SerialUtilities.writeStroke(this.rangeIndicatorStroke, stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[339]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[340]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[10]++;


int CodeCoverConditionCoverageHelper_C81;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((i < 3) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[10]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[11]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[12]++;
}
            SerialUtilities.writePaint(this.subrangePaint[i], stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[341]++;
        }
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
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[342]++;
        this.thermometerStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[343]++;
        this.thermometerPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[344]++;
        this.valuePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[345]++;
        this.mercuryPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[346]++;
        this.subrangeIndicatorStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[347]++;
        this.rangeIndicatorStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[348]++;
        this.subrangePaint = new Paint[3];
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[349]++;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[350]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[13]++;


int CodeCoverConditionCoverageHelper_C82;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((i < 3) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[13]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[14]--;
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.loops[15]++;
}
            this.subrangePaint[i] = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[351]++;
        }
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[352]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((this.rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[162]++;
            this.rangeAxis.addChangeListener(this);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[353]++;

        } else {
  CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.branches[163]++;}
    }

    /**
     * Multiplies the range on the domain axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param state  the plot state.
     * @param source  the source point.
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo state, 
                               Point2D source) {
        // no domain axis to zoom
    }

    /**
     * Multiplies the range on the domain axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param state  the plot state.
     * @param source  the source point.
     * @param useAnchor  a flag that controls whether or not the source point
     *         is used for the zoom anchor.
     *         
     * @since 1.0.7
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo state, 
                               Point2D source, boolean useAnchor) {
        // no domain axis to zoom
    }
    
    /**
     * Multiplies the range on the range axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param state  the plot state.
     * @param source  the source point.
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo state, 
                              Point2D source) {
        this.rangeAxis.resizeRange(factor);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[354]++;
    }

    /**
     * Multiplies the range on the range axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param state  the plot state.
     * @param source  the source point.
     * @param useAnchor  a flag that controls whether or not the source point
     *         is used for the zoom anchor.
     *         
     * @since 1.0.7
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo state, 
                              Point2D source, boolean useAnchor) {
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[355]++;
        double anchorY = this.getRangeAxis().java2DToValue(source.getY(), 
                state.getDataArea(), RectangleEdge.LEFT);
        this.rangeAxis.resizeRange(factor, anchorY);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[356]++;
    }
    
    /**
     * This method does nothing.
     *
     * @param lowerPercent  the lower percent.
     * @param upperPercent  the upper percent.
     * @param state  the plot state.
     * @param source  the source point.
     */
    public void zoomDomainAxes(double lowerPercent, double upperPercent, 
                               PlotRenderingInfo state, Point2D source) {
        // no domain axis to zoom
    }

    /**
     * Zooms the range axes.
     *
     * @param lowerPercent  the lower percent.
     * @param upperPercent  the upper percent.
     * @param state  the plot state.
     * @param source  the source point.
     */
    public void zoomRangeAxes(double lowerPercent, double upperPercent, 
                              PlotRenderingInfo state, Point2D source) {
        this.rangeAxis.zoomRange(lowerPercent, upperPercent);
CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx.statements[357]++;
    }
  
    /**
     * Returns <code>false</code>.
     * 
     * @return A boolean.
     */
    public boolean isDomainZoomable() {
        return false;
    }
    
    /**
     * Returns <code>true</code>.
     * 
     * @return A boolean.
     */
    public boolean isRangeZoomable() {
        return true;
    }

}

class CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx ());
  }
    public static long[] statements = new long[358];
    public static long[] branches = new long[164];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[84];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.ThermometerPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,1,1,1,2,2,1,1,1,1,1,1,2,2,3,1,2,3,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 83; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$9uy9jf51maqi7no4t5r74sh77ycutsx () {
    super("org.jfree.chart.plot.ThermometerPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 357; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 163; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 83; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.ThermometerPlot.java");
      for (int i = 1; i <= 357; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 163; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 83; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

