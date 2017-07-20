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
 * --------------
 * MeterPlot.java
 * --------------
 * (C) Copyright 2000-2007, by Hari and Contributors.
 *
 * Original Author:  Hari (ourhari@hotmail.com);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Bob Orchard;
 *                   Arnaud Lelievre;
 *                   Nicolas Brodu;
 *                   David Bastend;
 *
 * Changes
 * -------
 * 01-Apr-2002 : Version 1, contributed by Hari (DG);
 * 23-Apr-2002 : Moved dataset from JFreeChart to Plot (DG);
 * 22-Aug-2002 : Added changes suggest by Bob Orchard, changed Color to Paint 
 *               for consistency, plus added Javadoc comments (DG);
 * 01-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 23-Jan-2003 : Removed one constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 20-Aug-2003 : Changed dataset from MeterDataset --> ValueDataset, added 
 *               equals() method,
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL); 
 *               implemented Cloneable, and various other changes (DG);
 * 08-Sep-2003 : Added serialization methods (NB);
 * 11-Sep-2003 : Added cloning support (NB);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 25-Sep-2003 : Fix useless cloning. Correct dataset listener registration in 
 *               constructor. (NB)
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 17-Jan-2004 : Changed to allow dialBackgroundPaint to be set to null - see 
 *               bug 823628 (DG);
 * 07-Apr-2004 : Changed string bounds calculation (DG);
 * 12-May-2004 : Added tickLabelFormat attribute - see RFE 949566.  Also 
 *               updated the equals() method (DG);
 * 02-Nov-2004 : Added sanity checks for range, and only draw the needle if the 
 *               value is contained within the overall range - see bug report 
 *               1056047 (DG);
 * 11-Jan-2005 : Removed deprecated code in preparation for the 1.0.0 
 *               release (DG);
 * 02-Feb-2005 : Added optional background paint for each region (DG);
 * 22-Mar-2005 : Removed 'normal', 'warning' and 'critical' regions and put in
 *               facility to define an arbitrary number of MeterIntervals,
 *               based on a contribution by David Bastend (DG);
 * 20-Apr-2005 : Small update for change to LegendItem constructors (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 08-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * 10-Nov-2005 : Added tickPaint, tickSize and valuePaint attributes, and
 *               put value label drawing code into a separate method (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
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
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
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
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.data.Range;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.ValueDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * A plot that displays a single value in the form of a needle on a dial.  
 * Defined ranges (for example, 'normal', 'warning' and 'critical') can be
 * highlighted on the dial.
 */
public class MeterPlot extends Plot implements Serializable, Cloneable {
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 2987472457734470962L;
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[1]++;
  }
    
    /** The default background paint. */
    static final Paint DEFAULT_DIAL_BACKGROUND_PAINT = Color.black;
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[2]++;
  }

    /** The default needle paint. */
    static final Paint DEFAULT_NEEDLE_PAINT = Color.green;
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[3]++;
  }

    /** The default value font. */
    static final Font DEFAULT_VALUE_FONT = new Font("SansSerif", Font.BOLD, 12);
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[4]++;
  }

    /** The default value paint. */
    static final Paint DEFAULT_VALUE_PAINT = Color.yellow;
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[5]++;
  }

    /** The default meter angle. */
    public static final int DEFAULT_METER_ANGLE = 270;
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[6]++;
  }

    /** The default border size. */
    public static final float DEFAULT_BORDER_SIZE = 3f;
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[7]++;
  }

    /** The default circle size. */
    public static final float DEFAULT_CIRCLE_SIZE = 10f;
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[8]++;
  }

    /** The default label font. */
    public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 
            Font.BOLD, 10);
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[9]++;
  }

    /** The dataset (contains a single value). */
    private ValueDataset dataset;

    /** The dial shape (background shape). */
    private DialShape shape;

    /** The dial extent (measured in degrees). */
    private int meterAngle;
    
    /** The overall range of data values on the dial. */
    private Range range;
    
    /** The tick size. */
    private double tickSize;
    
    /** The paint used to draw the ticks. */
    private transient Paint tickPaint;
    
    /** The units displayed on the dial. */    
    private String units;
    
    /** The font for the value displayed in the center of the dial. */
    private Font valueFont;

    /** The paint for the value displayed in the center of the dial. */
    private transient Paint valuePaint;

    /** A flag that controls whether or not the border is drawn. */
    private boolean drawBorder;

    /** The outline paint. */
    private transient Paint dialOutlinePaint;

    /** The paint for the dial background. */
    private transient Paint dialBackgroundPaint;

    /** The paint for the needle. */
    private transient Paint needlePaint;

    /** A flag that controls whether or not the tick labels are visible. */
    private boolean tickLabelsVisible;

    /** The tick label font. */
    private Font tickLabelFont;

    /** The tick label paint. */
    private transient Paint tickLabelPaint;
    
    /** The tick label format. */
    private NumberFormat tickLabelFormat;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources = 
        ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[10]++;
  }

    /** 
     * A (possibly empty) list of the {@link MeterInterval}s to be highlighted 
     * on the dial. 
     */
    private List intervals;

    /**
     * Creates a new plot with a default range of <code>0</code> to 
     * <code>100</code> and no value to display.
     */
    public MeterPlot() {
        this(null);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[11]++;   
    }
    
    /**
     * Creates a new plot that displays the value from the supplied dataset.
     *
     * @param dataset  the dataset (<code>null</code> permitted).
     */
    public MeterPlot(ValueDataset dataset) {
        super();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[12]++;
        this.shape = DialShape.CIRCLE;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[13]++;
        this.meterAngle = DEFAULT_METER_ANGLE;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[14]++;
        this.range = new Range(0.0, 100.0);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[15]++;
        this.tickSize = 10.0;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[16]++;
        this.tickPaint = Color.white;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[17]++;
        this.units = "Units";
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[18]++;
        this.needlePaint = MeterPlot.DEFAULT_NEEDLE_PAINT;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[19]++;
        this.tickLabelsVisible = true;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[20]++;
        this.tickLabelFont = MeterPlot.DEFAULT_LABEL_FONT;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[21]++;
        this.tickLabelPaint = Color.black;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[22]++;
        this.tickLabelFormat = NumberFormat.getInstance();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[23]++;
        this.valueFont = MeterPlot.DEFAULT_VALUE_FONT;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[24]++;
        this.valuePaint = MeterPlot.DEFAULT_VALUE_PAINT;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[25]++;
        this.dialBackgroundPaint = MeterPlot.DEFAULT_DIAL_BACKGROUND_PAINT;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[26]++;
        this.intervals = new java.util.ArrayList();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[27]++;
        setDataset(dataset);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[28]++;
    }

    /**
     * Returns the dial shape.  The default is {@link DialShape#CIRCLE}).
     * 
     * @return The dial shape (never <code>null</code>).
     * 
     * @see #setDialShape(DialShape)
     */
    public DialShape getDialShape() {
        return this.shape;
    }
    
    /**
     * Sets the dial shape and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param shape  the shape (<code>null</code> not permitted).
     * 
     * @see #getDialShape()
     */
    public void setDialShape(DialShape shape) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[29]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((shape == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[1]++;
            throw new IllegalArgumentException("Null 'shape' argument.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[2]++;}
        this.shape = shape;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[30]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[31]++;
    }
    
    /**
     * Returns the meter angle in degrees.  This defines, in part, the shape
     * of the dial.  The default is 270 degrees.
     *
     * @return The meter angle (in degrees).
     * 
     * @see #setMeterAngle(int)
     */
    public int getMeterAngle() {
        return this.meterAngle;
    }

    /**
     * Sets the angle (in degrees) for the whole range of the dial and sends 
     * a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param angle  the angle (in degrees, in the range 1-360).
     * 
     * @see #getMeterAngle()
     */
    public void setMeterAngle(int angle) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[32]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((angle < 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((angle > 360) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[3]++;
            throw new IllegalArgumentException("Invalid 'angle' (" + angle 
                    + ")");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[4]++;}
        this.meterAngle = angle;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[33]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[34]++;
    }

    /**
     * Returns the overall range for the dial.
     * 
     * @return The overall range (never <code>null</code>).
     * 
     * @see #setRange(Range)
     */
    public Range getRange() {
        return this.range;    
    }
    
    /**
     * Sets the range for the dial and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param range  the range (<code>null</code> not permitted and zero-length
     *               ranges not permitted).
     *             
     * @see #getRange()
     */
    public void setRange(Range range) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[35]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[5]++;
            throw new IllegalArgumentException("Null 'range' argument.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[6]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((range.getLength() > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[7]++;
            throw new IllegalArgumentException(
                    "Range length must be positive.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[8]++;}
        this.range = range;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[37]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[38]++;
    }
    
    /**
     * Returns the tick size (the interval between ticks on the dial).
     * 
     * @return The tick size.
     * 
     * @see #setTickSize(double)
     */
    public double getTickSize() {
        return this.tickSize;
    }
    
    /**
     * Sets the tick size and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param size  the tick size (must be > 0).
     * 
     * @see #getTickSize()
     */
    public void setTickSize(double size) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[39]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((size <= 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[9]++;
            throw new IllegalArgumentException("Requires 'size' > 0.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[10]++;}
        this.tickSize = size;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[40]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[41]++;
    }
    
    /**
     * Returns the paint used to draw the ticks around the dial. 
     * 
     * @return The paint used to draw the ticks around the dial (never 
     *         <code>null</code>).
     *         
     * @see #setTickPaint(Paint)
     */
    public Paint getTickPaint() {
        return this.tickPaint;
    }
    
    /**
     * Sets the paint used to draw the tick labels around the dial and sends
     * a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getTickPaint()
     */
    public void setTickPaint(Paint paint) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[42]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[11]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[12]++;}
        this.tickPaint = paint;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[43]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[44]++;
    }

    /**
     * Returns a string describing the units for the dial.
     * 
     * @return The units (possibly <code>null</code>).
     * 
     * @see #setUnits(String)
     */
    public String getUnits() {
        return this.units;
    }
    
    /**
     * Sets the units for the dial and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     * 
     * @param units  the units (<code>null</code> permitted).
     * 
     * @see #getUnits()
     */
    public void setUnits(String units) {
        this.units = units;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[45]++;    
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[46]++;
    }
        
    /**
     * Returns the paint for the needle.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setNeedlePaint(Paint)
     */
    public Paint getNeedlePaint() {
        return this.needlePaint;
    }

    /**
     * Sets the paint used to display the needle and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getNeedlePaint()
     */
    public void setNeedlePaint(Paint paint) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[47]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[13]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[14]++;}
        this.needlePaint = paint;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[48]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[49]++;
    }

    /**
     * Returns the flag that determines whether or not tick labels are visible.
     *
     * @return The flag.
     * 
     * @see #setTickLabelsVisible(boolean)
     */
    public boolean getTickLabelsVisible() {
        return this.tickLabelsVisible;
    }

    /**
     * Sets the flag that controls whether or not the tick labels are visible
     * and sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param visible  the flag.
     * 
     * @see #getTickLabelsVisible()
     */
    public void setTickLabelsVisible(boolean visible) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[50]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.tickLabelsVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[15]++;
            this.tickLabelsVisible = visible;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[51]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[52]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[16]++;}
    }

    /**
     * Returns the tick label font.
     *
     * @return The font (never <code>null</code>).
     * 
     * @see #setTickLabelFont(Font)
     */
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }

    /**
     * Sets the tick label font and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getTickLabelFont()
     */
    public void setTickLabelFont(Font font) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[53]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[17]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[18]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[54]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((this.tickLabelFont.equals(font)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[19]++;
            this.tickLabelFont = font;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[55]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[56]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[20]++;}
    }

    /**
     * Returns the tick label paint.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setTickLabelPaint(Paint)
     */
    public Paint getTickLabelPaint() {
        return this.tickLabelPaint;
    }

    /**
     * Sets the tick label paint and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getTickLabelPaint()
     */
    public void setTickLabelPaint(Paint paint) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[21]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[22]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[58]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((this.tickLabelPaint.equals(paint)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[23]++;
            this.tickLabelPaint = paint;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[59]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[60]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[24]++;}
    }

    /**
     * Returns the tick label format.
     * 
     * @return The tick label format (never <code>null</code>).
     * 
     * @see #setTickLabelFormat(NumberFormat)
     */
    public NumberFormat getTickLabelFormat() {
        return this.tickLabelFormat;    
    }
    
    /**
     * Sets the format for the tick labels and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     * 
     * @param format  the format (<code>null</code> not permitted).
     * 
     * @see #getTickLabelFormat()
     */
    public void setTickLabelFormat(NumberFormat format) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[61]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((format == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[25]++;
            throw new IllegalArgumentException("Null 'format' argument.");
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[26]++;}
        this.tickLabelFormat = format;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[62]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[63]++;
    }
    
    /**
     * Returns the font for the value label.
     *
     * @return The font (never <code>null</code>).
     * 
     * @see #setValueFont(Font)
     */
    public Font getValueFont() {
        return this.valueFont;
    }

    /**
     * Sets the font used to display the value label and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param font  the font (<code>null</code> not permitted).
     * 
     * @see #getValueFont()
     */
    public void setValueFont(Font font) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[27]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[28]++;}
        this.valueFont = font;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[65]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[66]++;
    }

    /**
     * Returns the paint for the value label.
     *
     * @return The paint (never <code>null</code>).
     * 
     * @see #setValuePaint(Paint)
     */
    public Paint getValuePaint() {
        return this.valuePaint;
    }

    /**
     * Sets the paint used to display the value label and sends a 
     * {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getValuePaint()
     */
    public void setValuePaint(Paint paint) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[67]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[29]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[30]++;}
        this.valuePaint = paint;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[68]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[69]++;
    }

    /**
     * Returns the paint for the dial background.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setDialBackgroundPaint(Paint)
     */
    public Paint getDialBackgroundPaint() {
        return this.dialBackgroundPaint;
    }

    /**
     * Sets the paint used to fill the dial background.  Set this to 
     * <code>null</code> for no background.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getDialBackgroundPaint()
     */
    public void setDialBackgroundPaint(Paint paint) {
        this.dialBackgroundPaint = paint;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[70]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[71]++;
    }

    /**
     * Returns a flag that controls whether or not a rectangular border is 
     * drawn around the plot area.
     *
     * @return A flag.
     * 
     * @see #setDrawBorder(boolean)
     */
    public boolean getDrawBorder() {
        return this.drawBorder;
    }

    /**
     * Sets the flag that controls whether or not a rectangular border is drawn
     * around the plot area and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param draw  the flag.
     * 
     * @see #getDrawBorder()
     */
    public void setDrawBorder(boolean draw) {
        // TODO: fix output when this flag is set to true
        this.drawBorder = draw;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[72]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[73]++;
    }

    /**
     * Returns the dial outline paint.
     *
     * @return The paint.
     * 
     * @see #setDialOutlinePaint(Paint)
     */
    public Paint getDialOutlinePaint() {
        return this.dialOutlinePaint;
    }

    /**
     * Sets the dial outline paint and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param paint  the paint.
     * 
     * @see #getDialOutlinePaint()
     */
    public void setDialOutlinePaint(Paint paint) {
        this.dialOutlinePaint = paint;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[74]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[75]++;        
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
     * is one, and triggers a {@link PlotChangeEvent}.
     * 
     * @param dataset  the dataset (<code>null</code> permitted).
     * 
     * @see #getDataset()
     */
    public void setDataset(ValueDataset dataset) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[76]++;
        
        // if there is an existing dataset, remove the plot from the list of 
        // change listeners...
        ValueDataset existing = this.dataset;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[77]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((existing != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[31]++;
            existing.removeChangeListener(this);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[78]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[32]++;}

        // set the new dataset, and register the chart as a change listener...
        this.dataset = dataset;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[79]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[80]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[33]++;
            setDatasetGroup(dataset.getGroup());
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[81]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[82]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[34]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[83]++;

        // send a dataset change event to self...
        DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
        datasetChanged(event);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[84]++;
        
    }

    /**
     * Returns an unmodifiable list of the intervals for the plot.
     * 
     * @return A list.
     * 
     * @see #addInterval(MeterInterval)
     */
    public List getIntervals() {
        return Collections.unmodifiableList(this.intervals);
    }
    
    /**
     * Adds an interval and sends a {@link PlotChangeEvent} to all registered
     * listeners.
     * 
     * @param interval  the interval (<code>null</code> not permitted).
     * 
     * @see #getIntervals()
     * @see #clearIntervals()
     */
    public void addInterval(MeterInterval interval) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[85]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((interval == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[35]++;
            throw new IllegalArgumentException("Null 'interval' argument.");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[36]++;}
        this.intervals.add(interval);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[86]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[87]++;
    }
    
    /**
     * Clears the intervals for the plot and sends a {@link PlotChangeEvent} to
     * all registered listeners.
     * 
     * @see #addInterval(MeterInterval)
     */
    public void clearIntervals() {
        this.intervals.clear();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[88]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[89]++;
    }
    
    /**
     * Returns an item for each interval.
     *
     * @return A collection of legend items.
     */
    public LegendItemCollection getLegendItems() {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[90]++;
        LegendItemCollection result = new LegendItemCollection();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[91]++;
        Iterator iterator = this.intervals.iterator();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[92]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[1]++;


int CodeCoverConditionCoverageHelper_C19;
        while ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[1]--;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[2]--;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[3]++;
}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[93]++;
            MeterInterval mi = (MeterInterval) iterator.next();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[94]++;
            Paint color = mi.getBackgroundPaint();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[95]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((color == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[37]++;
                color = mi.getOutlinePaint();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[96]++;

            } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[38]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[97]++;
            LegendItem item = new LegendItem(mi.getLabel(), mi.getLabel(),
                    null, null, new Rectangle2D.Double(-4.0, -4.0, 8.0, 8.0), 
                    color);
            item.setDataset(getDataset());
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[98]++;
            result.add(item);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[99]++;
        }
        return result;
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
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[100]++;
int CodeCoverConditionCoverageHelper_C21;

        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[39]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[101]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[40]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[102]++;

        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[103]++;

        area.setRect(area.getX() + 4, area.getY() + 4, area.getWidth() - 8, 
                area.getHeight() - 8);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[104]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[105]++;
int CodeCoverConditionCoverageHelper_C22;

        // draw the background
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.drawBorder) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[41]++;
            drawBackground(g2, area);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[106]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[42]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[107]++;

        // adjust the plot area by the interior spacing value
        double gapHorizontal = (2 * DEFAULT_BORDER_SIZE);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[108]++;
        double gapVertical = (2 * DEFAULT_BORDER_SIZE);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[109]++;
        double meterX = area.getX() + gapHorizontal / 2;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[110]++;
        double meterY = area.getY() + gapVertical / 2;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[111]++;
        double meterW = area.getWidth() - gapHorizontal;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[112]++;
        double meterH = area.getHeight() - gapVertical
                + ((this.meterAngle <= 180) && (this.shape != DialShape.CIRCLE)
                ? area.getHeight() / 1.25 : 0);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[113]++;

        double min = Math.min(meterW, meterH) / 2;
        meterX = (meterX + meterX + meterW) / 2 - min;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[114]++;
        meterY = (meterY + meterY + meterH) / 2 - min;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[115]++;
        meterW = 2 * min;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[116]++;
        meterH = 2 * min;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[117]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[118]++;

        Rectangle2D meterArea = new Rectangle2D.Double(meterX, meterY, meterW, 
                meterH);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[119]++;

        Rectangle2D.Double originalArea = new Rectangle2D.Double(
                meterArea.getX() - 4, meterArea.getY() - 4, 
                meterArea.getWidth() + 8, meterArea.getHeight() + 8);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[120]++;

        double meterMiddleX = meterArea.getCenterX();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[121]++;
        double meterMiddleY = meterArea.getCenterY();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[122]++;

        // plot the data (unless the dataset is null)...
        ValueDataset data = getDataset();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[123]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[43]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[124]++;
            double dataMin = this.range.getLowerBound();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[125]++;
            double dataMax = this.range.getUpperBound();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[126]++;

            Shape savedClip = g2.getClip();
            g2.clip(originalArea);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[127]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[128]++;
            Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    getForegroundAlpha()));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[129]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[130]++;
int CodeCoverConditionCoverageHelper_C24;

            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.dialBackgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[45]++;
                fillArc(g2, originalArea, dataMin, dataMax, 
                        this.dialBackgroundPaint, true);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[131]++;

            } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[46]++;}
            drawTicks(g2, meterArea, dataMin, dataMax);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[132]++;
            drawArcForInterval(g2, meterArea, new MeterInterval("", this.range,
                    this.dialOutlinePaint, new BasicStroke(1.0f), null));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[133]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[134]++;
            
            Iterator iterator = this.intervals.iterator();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[135]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[4]++;


int CodeCoverConditionCoverageHelper_C25;
            while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[4]--;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[5]--;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[6]++;
}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[136]++;
                MeterInterval interval = (MeterInterval) iterator.next();
                drawArcForInterval(g2, meterArea, interval);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[137]++;
            }
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[138]++;

            Number n = data.getValue();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[139]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[47]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[140]++;
                double value = n.doubleValue();
                drawValueLabel(g2, meterArea);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[141]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[142]++;
int CodeCoverConditionCoverageHelper_C27;
  
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.range.contains(value)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[49]++;
                    g2.setPaint(this.needlePaint);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[143]++;
                    g2.setStroke(new BasicStroke(2.0f));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[144]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[145]++;

                    double radius = (meterArea.getWidth() / 2) 
                                    + DEFAULT_BORDER_SIZE + 15;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[146]++;
                    double valueAngle = valueToAngle(value);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[147]++;
                    double valueP1 = meterMiddleX 
                            + (radius * Math.cos(Math.PI * (valueAngle / 180)));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[148]++;
                    double valueP2 = meterMiddleY 
                            - (radius * Math.sin(Math.PI * (valueAngle / 180)));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[149]++;

                    Polygon arrow = new Polygon();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[150]++;
int CodeCoverConditionCoverageHelper_C28;
                    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C28 |= (128)) == 0 || true) &&
 ((valueAngle > 135) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (32)) == 0 || true) &&
 ((valueAngle < 225) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((valueAngle < 45) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((valueAngle > -45) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 4) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 4) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[51]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[151]++;

                        double valueP3 = (meterMiddleY 
                                - DEFAULT_CIRCLE_SIZE / 4);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[152]++;
                        double valueP4 = (meterMiddleY 
                                + DEFAULT_CIRCLE_SIZE / 4);
                        arrow.addPoint((int) meterMiddleX, (int) valueP3);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[153]++;
                        arrow.addPoint((int) meterMiddleX, (int) valueP4);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[154]++;

 
                    }
                    else {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[52]++;
                        arrow.addPoint((int) (meterMiddleX 
                                - DEFAULT_CIRCLE_SIZE / 4), (int) meterMiddleY);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[155]++;
                        arrow.addPoint((int) (meterMiddleX 
                                + DEFAULT_CIRCLE_SIZE / 4), (int) meterMiddleY);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[156]++;
                    }
                    arrow.addPoint((int) valueP1, (int) valueP2);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[157]++;
                    g2.fill(arrow);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[158]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[159]++;

                    Ellipse2D circle = new Ellipse2D.Double(meterMiddleX 
                            - DEFAULT_CIRCLE_SIZE / 2, meterMiddleY 
                            - DEFAULT_CIRCLE_SIZE / 2, DEFAULT_CIRCLE_SIZE, 
                            DEFAULT_CIRCLE_SIZE);
                    g2.fill(circle);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[160]++;

                } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[50]++;}

            } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[48]++;}
                
            g2.setClip(savedClip);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[161]++;
            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[162]++;


        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[44]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[163]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.drawBorder) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[53]++;
            drawOutline(g2, area);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[164]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[54]++;}

    }

    /**
     * Draws the arc to represent an interval.
     *
     * @param g2  the graphics device.
     * @param meterArea  the drawing area.
     * @param interval  the interval.
     */
    protected void drawArcForInterval(Graphics2D g2, Rectangle2D meterArea, 
                                      MeterInterval interval) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[165]++;

        double minValue = interval.getRange().getLowerBound();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[166]++;
        double maxValue = interval.getRange().getUpperBound();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[167]++;
        Paint outlinePaint = interval.getOutlinePaint();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[168]++;
        Stroke outlineStroke = interval.getOutlineStroke();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[169]++;
        Paint backgroundPaint = interval.getBackgroundPaint();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[170]++;
int CodeCoverConditionCoverageHelper_C30;
 
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((backgroundPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[55]++;
            fillArc(g2, meterArea, minValue, maxValue, backgroundPaint, false);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[171]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[56]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[172]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((outlinePaint != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[57]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[173]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((outlineStroke != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[59]++;
                drawArc(g2, meterArea, minValue, maxValue, outlinePaint, 
                        outlineStroke);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[174]++;

            } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[60]++;}
            drawTick(g2, meterArea, minValue, true);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[175]++;
            drawTick(g2, meterArea, maxValue, true);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[176]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[58]++;}
    }

    /**
     * Draws an arc.
     *
     * @param g2  the graphics device.
     * @param area  the plot area.
     * @param minValue  the minimum value.
     * @param maxValue  the maximum value.
     * @param paint  the paint.
     * @param stroke  the stroke.
     */
    protected void drawArc(Graphics2D g2, Rectangle2D area, double minValue, 
                           double maxValue, Paint paint, Stroke stroke) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[177]++;

        double startAngle = valueToAngle(maxValue);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[178]++;
        double endAngle = valueToAngle(minValue);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[179]++;
        double extent = endAngle - startAngle;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[180]++;

        double x = area.getX();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[181]++;
        double y = area.getY();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[182]++;
        double w = area.getWidth();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[183]++;
        double h = area.getHeight();
        g2.setPaint(paint);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[184]++;
        g2.setStroke(stroke);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[185]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[186]++;
int CodeCoverConditionCoverageHelper_C33;

        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((paint != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((stroke != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[61]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[187]++;
            Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, 
                    extent, Arc2D.OPEN);
            g2.setPaint(paint);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[188]++; 
            g2.setStroke(stroke);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[189]++;
            g2.draw(arc);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[190]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[62]++;}

    }

    /**
     * Fills an arc on the dial between the given values.
     *
     * @param g2  the graphics device.
     * @param area  the plot area.
     * @param minValue  the minimum data value.
     * @param maxValue  the maximum data value.
     * @param paint  the background paint (<code>null</code> not permitted).
     * @param dial  a flag that indicates whether the arc represents the whole 
     *              dial.
     */
    protected void fillArc(Graphics2D g2, Rectangle2D area, 
                           double minValue, double maxValue, Paint paint,
                           boolean dial) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[191]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[63]++;
            throw new IllegalArgumentException("Null 'paint' argument");

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[64]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[192]++;
        double startAngle = valueToAngle(maxValue);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[193]++;
        double endAngle = valueToAngle(minValue);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[194]++;
        double extent = endAngle - startAngle;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[195]++;

        double x = area.getX();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[196]++;
        double y = area.getY();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[197]++;
        double w = area.getWidth();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[198]++;
        double h = area.getHeight();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[199]++;
        int joinType = Arc2D.OPEN;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[200]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.shape == DialShape.PIE) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[65]++;
            joinType = Arc2D.PIE;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[201]++;

        }
        else {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[66]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[202]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.shape == DialShape.CHORD) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[67]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[203]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((dial) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.meterAngle > 180) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[69]++;
                joinType = Arc2D.CHORD;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[204]++;

            }
            else {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[70]++;
                joinType = Arc2D.PIE;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[205]++;
            }

        }
        else {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[68]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[206]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.shape == DialShape.CIRCLE) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[71]++;
            joinType = Arc2D.PIE;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[207]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[208]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((dial) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[73]++;
                extent = 360;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[209]++;

            } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[74]++;}

        }
        else {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[72]++;
            throw new IllegalStateException("DialShape not recognised.");
        }
}
}

        g2.setPaint(paint);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[210]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[211]++;
        Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, extent, 
                joinType);
        g2.fill(arc);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[212]++;
    }
    
    /**
     * Translates a data value to an angle on the dial.
     *
     * @param value  the value.
     *
     * @return The angle on the dial.
     */
    public double valueToAngle(double value) {
        value = value - this.range.getLowerBound();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[213]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[214]++;
        double baseAngle = 180 + ((this.meterAngle - 180) / 2);
        return baseAngle - ((value / this.range.getLength()) * this.meterAngle);
    }

    /**
     * Draws the ticks that subdivide the overall range.
     *
     * @param g2  the graphics device.
     * @param meterArea  the meter area.
     * @param minValue  the minimum value.
     * @param maxValue  the maximum value.
     */
    protected void drawTicks(Graphics2D g2, Rectangle2D meterArea, 
                             double minValue, double maxValue) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[215]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[7]++;


int CodeCoverConditionCoverageHelper_C40;
        for (double v = minValue;(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((v <= maxValue) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false); v += this.tickSize) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[7]--;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[8]--;
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.loops[9]++;
}
            drawTick(g2, meterArea, v);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[216]++;
        }
    }

    /**
     * Draws a tick.
     *
     * @param g2  the graphics device.
     * @param meterArea  the meter area.
     * @param value  the value.
     */
    protected void drawTick(Graphics2D g2, Rectangle2D meterArea, 
            double value) {
        drawTick(g2, meterArea, value, false);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[217]++;
    }

    /**
     * Draws a tick on the dial.
     *
     * @param g2  the graphics device.
     * @param meterArea  the meter area.
     * @param value  the tick value.
     * @param label  a flag that controls whether or not a value label is drawn.
     */
    protected void drawTick(Graphics2D g2, Rectangle2D meterArea,
                            double value, boolean label) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[218]++;

        double valueAngle = valueToAngle(value);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[219]++;

        double meterMiddleX = meterArea.getCenterX();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[220]++;
        double meterMiddleY = meterArea.getCenterY();

        g2.setPaint(this.tickPaint);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[221]++;
        g2.setStroke(new BasicStroke(2.0f));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[222]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[223]++;

        double valueP2X = 0;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[224]++;
        double valueP2Y = 0;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[225]++;

        double radius = (meterArea.getWidth() / 2) + DEFAULT_BORDER_SIZE;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[226]++;
        double radius1 = radius - 15;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[227]++;

        double valueP1X = meterMiddleX 
                + (radius * Math.cos(Math.PI * (valueAngle / 180)));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[228]++;
        double valueP1Y = meterMiddleY 
                - (radius * Math.sin(Math.PI * (valueAngle / 180)));

        valueP2X = meterMiddleX 
                + (radius1 * Math.cos(Math.PI * (valueAngle / 180)));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[229]++;
        valueP2Y = meterMiddleY 
                - (radius1 * Math.sin(Math.PI * (valueAngle / 180)));
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[230]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[231]++;

        Line2D.Double line = new Line2D.Double(valueP1X, valueP1Y, valueP2X, 
                valueP2Y);
        g2.draw(line);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[232]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[233]++;
int CodeCoverConditionCoverageHelper_C41;

        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((this.tickLabelsVisible) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((label) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[75]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[234]++;

            String tickLabel =  this.tickLabelFormat.format(value);
            g2.setFont(this.tickLabelFont);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[235]++;
            g2.setPaint(this.tickLabelPaint);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[236]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[237]++;

            FontMetrics fm = g2.getFontMetrics();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[238]++;
            Rectangle2D tickLabelBounds 
                = TextUtilities.getTextBounds(tickLabel, g2, fm);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[239]++;

            double x = valueP2X;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[240]++;
            double y = valueP2Y;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[241]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((valueAngle == 90) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((valueAngle == 270) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[77]++;
                x = x - tickLabelBounds.getWidth() / 2;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[242]++;

            }
            else {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[78]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[243]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((valueAngle < 90) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((valueAngle > 270) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[79]++;
                x = x - tickLabelBounds.getWidth();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[244]++;

            } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[80]++;}
}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[245]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C44 |= (128)) == 0 || true) &&
 ((valueAngle > 135) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (32)) == 0 || true) &&
 ((valueAngle < 225) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (16)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((valueAngle > 315) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((valueAngle < 45) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 4) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 4) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[81]++;
                y = y - tickLabelBounds.getHeight() / 2;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[246]++;

            }
            else {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[82]++;
                y = y + tickLabelBounds.getHeight() / 2;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[247]++;
            }
            g2.drawString(tickLabel, (float) x, (float) y);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[248]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[76]++;}
    }
    
    /**
     * Draws the value label just below the center of the dial.
     * 
     * @param g2  the graphics device.
     * @param area  the plot area.
     */
    protected void drawValueLabel(Graphics2D g2, Rectangle2D area) {
        g2.setFont(this.valueFont);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[249]++;
        g2.setPaint(this.valuePaint);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[250]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[251]++;
        String valueStr = "No value";
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[252]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[83]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[253]++;
            Number n = this.dataset.getValue();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[254]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[85]++;
                valueStr = this.tickLabelFormat.format(n.doubleValue()) + " " 
                         + this.units;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[255]++;

            } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[86]++;}

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[84]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[256]++;
        float x = (float) area.getCenterX();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[257]++;
        float y = (float) area.getCenterY() + DEFAULT_CIRCLE_SIZE;
        TextUtilities.drawAlignedString(valueStr, g2, x, y, 
                TextAnchor.TOP_CENTER);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[258]++;
    }

    /**
     * Returns a short string describing the type of plot.
     *
     * @return A string describing the type of plot.
     */
    public String getPlotType() {
        return localizationResources.getString("Meter_Plot");
    }

    /**
     * A zoom method that does nothing.  Plots are required to support the 
     * zoom operation.  In the case of a meter plot, it doesn't make sense to 
     * zoom in or out, so the method is empty.
     *
     * @param percent   The zoom percentage.
     */
    public void zoom(double percent) {
        // intentionally blank
    }
    
    /**
     * Tests the plot for equality with an arbitrary object.  Note that the 
     * dataset is ignored for the purposes of testing equality.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[259]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[87]++;
            return true;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[88]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[260]++;
int CodeCoverConditionCoverageHelper_C48;   
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((obj instanceof MeterPlot) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[89]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[90]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[261]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[92]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[262]++;
        MeterPlot that = (MeterPlot) obj;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[263]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.units, that.units)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[93]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[94]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[264]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.range, that.range)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[95]++;
            return false;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[96]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[265]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.intervals, that.intervals)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[97]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[98]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[266]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.dialOutlinePaint, 
                that.dialOutlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[99]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[100]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[267]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((this.shape != that.shape) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[101]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[102]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[268]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.dialBackgroundPaint, 
                that.dialBackgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[103]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[104]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[269]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.needlePaint, that.needlePaint)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[105]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[106]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[270]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.valueFont, that.valueFont)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[107]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[108]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[271]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.valuePaint, that.valuePaint)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[109]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[110]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[272]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.tickPaint, that.tickPaint)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[111]++;
            return false;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[112]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[273]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((this.tickSize != that.tickSize) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[113]++;
            return false;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[114]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[274]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((this.tickLabelsVisible != that.tickLabelsVisible) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[115]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[116]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[275]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.tickLabelFont, that.tickLabelFont)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[117]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[118]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[276]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[119]++;
            return false;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[120]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[277]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.tickLabelFormat, 
                that.tickLabelFormat)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[121]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[122]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[278]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((this.drawBorder != that.drawBorder) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[123]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[124]++;}
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[279]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((this.meterAngle != that.meterAngle) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[125]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[126]++;}
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
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[280]++;
        SerialUtilities.writePaint(this.dialBackgroundPaint, stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[281]++;
        SerialUtilities.writePaint(this.needlePaint, stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[282]++;
        SerialUtilities.writePaint(this.valuePaint, stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[283]++;
        SerialUtilities.writePaint(this.tickPaint, stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[284]++;
        SerialUtilities.writePaint(this.tickLabelPaint, stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[285]++;
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
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[286]++;
        this.dialBackgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[287]++;
        this.needlePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[288]++;
        this.valuePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[289]++;
        this.tickPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[290]++;
        this.tickLabelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[291]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[292]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((this.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[127]++;
            this.dataset.addChangeListener(this);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[293]++;

        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[128]++;}
    }

    /** 
     * Returns an independent copy (clone) of the plot.  The dataset is NOT 
     * cloned - both the original and the clone will have a reference to the
     * same dataset.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if some component of the plot cannot
     *         be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[294]++;
        MeterPlot clone = (MeterPlot) super.clone();
        clone.tickLabelFormat = (NumberFormat) this.tickLabelFormat.clone();
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[295]++;
        // the following relies on the fact that the intervals are immutable
        clone.intervals = new java.util.ArrayList(this.intervals);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[296]++;
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[297]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((clone.dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[129]++;
            clone.dataset.addChangeListener(clone);
CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.statements[298]++;
 
        } else {
  CodeCoverCoverageCounter$39fl18equ5246xouj71f8h.branches[130]++;}
        return clone;
    }

}

class CodeCoverCoverageCounter$39fl18equ5246xouj71f8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$39fl18equ5246xouj71f8h ());
  }
    public static long[] statements = new long[299];
    public static long[] branches = new long[131];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[69];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.MeterPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,2,1,1,1,2,1,1,1,2,2,2,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 68; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$39fl18equ5246xouj71f8h () {
    super("org.jfree.chart.plot.MeterPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 298; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 130; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 68; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.MeterPlot.java");
      for (int i = 1; i <= 298; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 130; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 68; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

