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
 * ----------------
 * CompassPlot.java
 * ----------------
 * (C) Copyright 2002-2007, by the Australian Antarctic Division and 
 * Contributors.
 *
 * Original Author:  Bryan Scott (for the Australian Antarctic Division);
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *                   Arnaud Lelievre;
 *
 * Changes:
 * --------
 * 25-Sep-2002 : Version 1, contributed by Bryan Scott (DG);
 * 23-Jan-2003 : Removed one constructor (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 27-Mar-2003 : Changed MeterDataset to ValueDataset (DG);
 * 21-Aug-2003 : Implemented Cloneable (DG);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL);
 * 09-Sep-2003 : Changed Color --> Paint (DG);
 * 15-Sep-2003 : Added null data value check (bug report 805009) (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 16-Mar-2004 : Added support for revolutionDistance to enable support for
 *               other units than degrees.
 * 16-Mar-2004 : Enabled LongNeedle to rotate about center.
 * 11-Jan-2005 : Removed deprecated code in preparation for 1.0.0 release (DG);
 * 17-Apr-2005 : Fixed bug in clone() method (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 08-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 * 16-Jun-2005 : Renamed getData() --> getDatasets() and 
 *               addData() --> addDataset() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 20-Mar-2007 : Fixed serialization (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.needle.ArrowNeedle;
import org.jfree.chart.needle.LineNeedle;
import org.jfree.chart.needle.LongNeedle;
import org.jfree.chart.needle.MeterNeedle;
import org.jfree.chart.needle.MiddlePinNeedle;
import org.jfree.chart.needle.PinNeedle;
import org.jfree.chart.needle.PlumNeedle;
import org.jfree.chart.needle.PointerNeedle;
import org.jfree.chart.needle.ShipNeedle;
import org.jfree.chart.needle.WindNeedle;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * A specialised plot that draws a compass to indicate a direction based on the
 * value from a {@link ValueDataset}.
 */
public class CompassPlot extends Plot implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 6924382802125527395L;
  static {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[1]++;
  }
    
    /** The default label font. */
    public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 
            Font.BOLD, 10);
  static {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[2]++;
  }

    /** A constant for the label type. */
    public static final int NO_LABELS = 0;
  static {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[3]++;
  }

    /** A constant for the label type. */
    public static final int VALUE_LABELS = 1;
  static {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[4]++;
  }

    /** The label type (NO_LABELS, VALUE_LABELS). */
    private int labelType;

    /** The label font. */
    private Font labelFont;

    /** A flag that controls whether or not a border is drawn. */
    private boolean drawBorder = false;
  {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[5]++;
  }

    /** The rose highlight paint. */
    private transient Paint roseHighlightPaint = Color.black;
  {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[6]++;
  }

    /** The rose paint. */
    private transient Paint rosePaint = Color.yellow;
  {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[7]++;
  }

    /** The rose center paint. */
    private transient Paint roseCenterPaint = Color.white;
  {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[8]++;
  }

    /** The compass font. */
    private Font compassFont = new Font("Arial", Font.PLAIN, 10);
  {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[9]++;
  }

    /** A working shape. */
    private transient Ellipse2D circle1;

    /** A working shape. */
    private transient Ellipse2D circle2;

    /** A working area. */
    private transient Area a1;

    /** A working area. */
    private transient Area a2;

    /** A working shape. */
    private transient Rectangle2D rect1;

    /** An array of value datasets. */
    private ValueDataset[] datasets = new ValueDataset[1];
  {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[10]++;
  }

    /** An array of needles. */
    private MeterNeedle[] seriesNeedle = new MeterNeedle[1];
  {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[11]++;
  }

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources 
            = ResourceBundle.getBundle(
                    "org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[12]++;
  }

    /** 
     * The count to complete one revolution.  Can be arbitrarily set
     * For degrees (the default) it is 360, for radians this is 2*Pi, etc
     */
    protected double revolutionDistance = 360;
  {
    CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[13]++;
  }

    /**
     * Default constructor.
     */
    public CompassPlot() {
        this(new DefaultValueDataset());
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[14]++;
    }

    /**
     * Constructs a new compass plot.
     *
     * @param dataset  the dataset for the plot (<code>null</code> permitted).
     */
    public CompassPlot(ValueDataset dataset) {
        super();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[15]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[1]++;
            this.datasets[0] = dataset;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[17]++;
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[18]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[2]++;}
        this.circle1 = new Ellipse2D.Double();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[19]++;
        this.circle2 = new Ellipse2D.Double();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[20]++;
        this.rect1   = new Rectangle2D.Double();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[21]++;
        setSeriesNeedle(0);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[22]++;
    }

    /**
     * Returns the label type.  Defined by the constants: {@link #NO_LABELS}
     * and {@link #VALUE_LABELS}.
     *
     * @return The label type.
     * 
     * @see #setLabelType(int)
     */
    public int getLabelType() {
        // FIXME: this attribute is never used - deprecate?
        return this.labelType;
    }

    /**
     * Sets the label type (either {@link #NO_LABELS} or {@link #VALUE_LABELS}.
     *
     * @param type  the type.
     * 
     * @see #getLabelType()
     */
    public void setLabelType(int type) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[23]++;
int CodeCoverConditionCoverageHelper_C2;
        // FIXME: this attribute is never used - deprecate?
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((type != NO_LABELS) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type != VALUE_LABELS) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[3]++;
            throw new IllegalArgumentException(
                    "MeterPlot.setLabelType(int): unrecognised type.");

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[4]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.labelType != type) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[5]++;
            this.labelType = type;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[25]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[26]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[6]++;}
    }

    /**
     * Returns the label font.
     *
     * @return The label font.
     * 
     * @see #setLabelFont(Font)
     */
    public Font getLabelFont() {
        // FIXME: this attribute is not used - deprecate?
        return this.labelFont;
    }

    /**
     * Sets the label font and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     *
     * @param font  the new label font.
     * 
     * @see #getLabelFont()
     */
    public void setLabelFont(Font font) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;
        // FIXME: this attribute is not used - deprecate?
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[7]++;
            throw new IllegalArgumentException("Null 'font' not allowed.");

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[8]++;}
        this.labelFont = font;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[28]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[29]++;
    }

    /**
     * Returns the paint used to fill the outer circle of the compass.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setRosePaint(Paint)
     */
    public Paint getRosePaint() {
        return this.rosePaint;   
    }
    
    /**
     * Sets the paint used to fill the outer circle of the compass, 
     * and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getRosePaint()
     */
    public void setRosePaint(Paint paint) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[30]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[9]++;   
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[10]++;}
        this.rosePaint = paint;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[31]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[32]++;        
    }

    /**
     * Returns the paint used to fill the inner background area of the 
     * compass.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setRoseCenterPaint(Paint)
     */
    public Paint getRoseCenterPaint() {
        return this.roseCenterPaint;   
    }
    
    /**
     * Sets the paint used to fill the inner background area of the compass, 
     * and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getRoseCenterPaint()
     */
    public void setRoseCenterPaint(Paint paint) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[11]++;   
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[12]++;}
        this.roseCenterPaint = paint;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[34]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[35]++;        
    }
    
    /**
     * Returns the paint used to draw the circles, symbols and labels on the
     * compass.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setRoseHighlightPaint(Paint)
     */
    public Paint getRoseHighlightPaint() {
        return this.roseHighlightPaint;   
    }
    
    /**
     * Sets the paint used to draw the circles, symbols and labels of the 
     * compass, and sends a {@link PlotChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getRoseHighlightPaint()
     */
    public void setRoseHighlightPaint(Paint paint) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[13]++;   
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[14]++;}
        this.roseHighlightPaint = paint;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[37]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[38]++;        
    }
    
    /**
     * Returns a flag that controls whether or not a border is drawn.
     *
     * @return The flag.
     * 
     * @see #setDrawBorder(boolean)
     */
    public boolean getDrawBorder() {
        return this.drawBorder;
    }

    /**
     * Sets a flag that controls whether or not a border is drawn.
     *
     * @param status  the flag status.
     * 
     * @see #getDrawBorder()
     */
    public void setDrawBorder(boolean status) {
        this.drawBorder = status;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[39]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[40]++;
    }

    /**
     * Sets the series paint.
     *
     * @param series  the series index.
     * @param paint  the paint.
     * 
     * @see #setSeriesOutlinePaint(int, Paint)
     */
    public void setSeriesPaint(int series, Paint paint) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
       // super.setSeriesPaint(series, paint);
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((series >= 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((series < this.seriesNeedle.length) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[15]++;
            this.seriesNeedle[series].setFillPaint(paint);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[42]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[16]++;}
    }

    /**
     * Sets the series outline paint.
     *
     * @param series  the series index.
     * @param p  the paint.
     * 
     * @see #setSeriesPaint(int, Paint)
     */
    public void setSeriesOutlinePaint(int series, Paint p) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((series >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((series < this.seriesNeedle.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[17]++;
            this.seriesNeedle[series].setOutlinePaint(p);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[44]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[18]++;}

    }

    /**
     * Sets the series outline stroke.
     *
     * @param series  the series index.
     * @param stroke  the stroke.
     * 
     * @see #setSeriesOutlinePaint(int, Paint)
     */
    public void setSeriesOutlineStroke(int series, Stroke stroke) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((series >= 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((series < this.seriesNeedle.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[19]++;
            this.seriesNeedle[series].setOutlineStroke(stroke);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[46]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[20]++;}

    }

    /**
     * Sets the needle type.
     *
     * @param type  the type.
     * 
     * @see #setSeriesNeedle(int, int)
     */
    public void setSeriesNeedle(int type) {
        setSeriesNeedle(0, type);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[47]++;
    }

    /**
     * Sets the needle for a series.  The needle type is one of the following:
     * <ul>
     * <li>0 = {@link ArrowNeedle};</li>
     * <li>1 = {@link LineNeedle};</li>
     * <li>2 = {@link LongNeedle};</li>
     * <li>3 = {@link PinNeedle};</li>
     * <li>4 = {@link PlumNeedle};</li>
     * <li>5 = {@link PointerNeedle};</li>
     * <li>6 = {@link ShipNeedle};</li>
     * <li>7 = {@link WindNeedle};</li>
     * <li>8 = {@link ArrowNeedle};</li>
     * <li>9 = {@link MiddlePinNeedle};</li>
     * </ul>
     * @param index  the series index.
     * @param type  the needle type.
     * 
     * @see #setSeriesNeedle(int)
     */
    public void setSeriesNeedle(int index, int type) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[48]++;
        switch (type) {
            case 0:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[21]++;
                setSeriesNeedle(index, new ArrowNeedle(true));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[49]++;
                setSeriesPaint(index, Color.red);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[50]++;
                this.seriesNeedle[index].setHighlightPaint(Color.white);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[51]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[52]++;
                break;
            case 1:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[22]++;
                setSeriesNeedle(index, new LineNeedle());
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[53]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[54]++;
                break;
            case 2:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[23]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[55]++;
                MeterNeedle longNeedle = new LongNeedle();
                longNeedle.setRotateY(0.5);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[56]++;
                setSeriesNeedle(index, longNeedle);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[57]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[58]++;
                break;
            case 3:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[24]++;
                setSeriesNeedle(index, new PinNeedle());
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[59]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[60]++;
                break;
            case 4:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[25]++;
                setSeriesNeedle(index, new PlumNeedle());
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[61]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[62]++;
                break;
            case 5:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[26]++;
                setSeriesNeedle(index, new PointerNeedle());
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[63]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[64]++;
                break;
            case 6:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[27]++;
                setSeriesPaint(index, null);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[65]++;
                setSeriesOutlineStroke(index, new BasicStroke(3));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[66]++;
                setSeriesNeedle(index, new ShipNeedle());
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[67]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[68]++;
                break;
            case 7:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[28]++;
                setSeriesPaint(index, Color.blue);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[69]++;
                setSeriesNeedle(index, new WindNeedle());
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[70]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[71]++;
                break;
            case 8:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[29]++;
                setSeriesNeedle(index, new ArrowNeedle(true));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[72]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[73]++;
                break;
            case 9:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[30]++;
                setSeriesNeedle(index, new MiddlePinNeedle());
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[74]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[75]++;
                break;

            default:
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[31]++;
                throw new IllegalArgumentException("Unrecognised type.");
        }

    }

    /**
     * Sets the needle for a series and sends a {@link PlotChangeEvent} to all
     * registered listeners.
     *
     * @param index  the series index.
     * @param needle  the needle.
     */
    public void setSeriesNeedle(int index, MeterNeedle needle) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[76]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((needle != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((index < this.seriesNeedle.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[32]++;
            this.seriesNeedle[index] = needle;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[77]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[33]++;}
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[78]++;

    }

    /**
     * Returns an array of dataset references for the plot.
     *
     * @return The dataset for the plot, cast as a ValueDataset.
     * 
     * @see #addDataset(ValueDataset)
     */
    public ValueDataset[] getDatasets() {
        return this.datasets;
    }

    /**
     * Adds a dataset to the compass.
     *
     * @param dataset  the new dataset (<code>null</code> ignored).
     * 
     * @see #addDataset(ValueDataset, MeterNeedle)
     */
    public void addDataset(ValueDataset dataset) {
        addDataset(dataset, null);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[79]++;
    }

    /**
     * Adds a dataset to the compass.
     *
     * @param dataset  the new dataset (<code>null</code> ignored).
     * @param needle  the needle (<code>null</code> permitted).
     */
    public void addDataset(ValueDataset dataset, MeterNeedle needle) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[80]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((dataset != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[34]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[81]++;
            int i = this.datasets.length + 1;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[82]++;
            ValueDataset[] t = new ValueDataset[i];
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[83]++;
            MeterNeedle[] p = new MeterNeedle[i];
            i = i - 2;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[84]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[85]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
            for (;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); --i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[1]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[2]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[3]++;
}
                t[i] = this.datasets[i];
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[86]++;
                p[i] = this.seriesNeedle[i];
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[87]++;
            }
            i = this.datasets.length;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[88]++;
            t[i] = dataset;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[89]++;
            p[i] = ((needle != null) ? needle : p[i - 1]);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[90]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[91]++;

            ValueDataset[] a = this.datasets;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[92]++;
            MeterNeedle[] b = this.seriesNeedle;
            this.datasets = t;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[93]++;
            this.seriesNeedle = p;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[94]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[95]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[4]++;


int CodeCoverConditionCoverageHelper_C14;

            for (--i;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); --i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[4]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[5]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[6]++;
}
                a[i] = null;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[96]++;
                b[i] = null;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[97]++;
            }
            dataset.addChangeListener(this);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[98]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[35]++;}
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
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[99]++;

        int outerRadius = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[100]++;
        int innerRadius = 0;
        int x1, y1, x2, y2;
        double a;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[101]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[36]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[102]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[37]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[103]++;

        // adjust for insets...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[104]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[105]++;
int CodeCoverConditionCoverageHelper_C16;

        // draw the background
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.drawBorder) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[38]++;
            drawBackground(g2, area);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[106]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[39]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[107]++;

        int midX = (int) (area.getWidth() / 2);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[108]++;
        int midY = (int) (area.getHeight() / 2);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[109]++;
        int radius = midX;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[110]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((midY < midX) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[40]++;
            radius = midY;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[111]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[41]++;}
        --radius;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[112]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[113]++;
        int diameter = 2 * radius;

        midX += (int) area.getMinX();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[114]++;
        midY += (int) area.getMinY();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[115]++;

        this.circle1.setFrame(midX - radius, midY - radius, diameter, diameter);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[116]++;
        this.circle2.setFrame(
            midX - radius + 15, midY - radius + 15, 
            diameter - 30, diameter - 30
        );
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[117]++;
        g2.setPaint(this.rosePaint);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[118]++;
        this.a1 = new Area(this.circle1);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[119]++;
        this.a2 = new Area(this.circle2);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[120]++;
        this.a1.subtract(this.a2);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[121]++;
        g2.fill(this.a1);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[122]++;

        g2.setPaint(this.roseCenterPaint);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[123]++;
        x1 = diameter - 30;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[124]++;
        g2.fillOval(midX - radius + 15, midY - radius + 15, x1, x1);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[125]++;
        g2.setPaint(this.roseHighlightPaint);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[126]++;
        g2.drawOval(midX - radius, midY - radius, diameter, diameter);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[127]++;
        x1 = diameter - 20;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[128]++;
        g2.drawOval(midX - radius + 10, midY - radius + 10, x1, x1);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[129]++;
        x1 = diameter - 30;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[130]++;
        g2.drawOval(midX - radius + 15, midY - radius + 15, x1, x1);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[131]++;
        x1 = diameter - 80;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[132]++;
        g2.drawOval(midX - radius + 40, midY - radius + 40, x1, x1);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[133]++;

        outerRadius = radius - 20;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[134]++;
        innerRadius = radius - 32;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[135]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[136]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[7]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int w = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((w < 360) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); w += 15) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[7]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[8]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[9]++;
}
            a = Math.toRadians(w);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[137]++;
            x1 = midX - ((int) (Math.sin(a) * innerRadius));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[138]++;
            x2 = midX - ((int) (Math.sin(a) * outerRadius));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[139]++;
            y1 = midY - ((int) (Math.cos(a) * innerRadius));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[140]++;
            y2 = midY - ((int) (Math.cos(a) * outerRadius));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[141]++;
            g2.drawLine(x1, y1, x2, y2);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[142]++;
        }

        g2.setPaint(this.roseHighlightPaint);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[143]++;
        innerRadius = radius - 26;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[144]++;
        outerRadius = 7;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[145]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[146]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int w = 45;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((w < 360) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); w += 90) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[10]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[11]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[12]++;
}
            a = Math.toRadians(w);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[147]++;
            x1 = midX - ((int) (Math.sin(a) * innerRadius));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[148]++;
            y1 = midY - ((int) (Math.cos(a) * innerRadius));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[149]++;
            g2.fillOval(x1 - outerRadius, y1 - outerRadius, 2 * outerRadius, 
                    2 * outerRadius);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[150]++;
        }
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[151]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[13]++;


int CodeCoverConditionCoverageHelper_C20;

        /// Squares
        for (int w = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((w < 360) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); w += 90) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[13]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[14]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[15]++;
}
            a = Math.toRadians(w);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[152]++;
            x1 = midX - ((int) (Math.sin(a) * innerRadius));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[153]++;
            y1 = midY - ((int) (Math.cos(a) * innerRadius));
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[154]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[155]++;

            Polygon p = new Polygon();
            p.addPoint(x1 - outerRadius, y1);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[156]++;
            p.addPoint(x1, y1 + outerRadius);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[157]++;
            p.addPoint(x1 + outerRadius, y1);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[158]++;
            p.addPoint(x1, y1 - outerRadius);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[159]++;
            g2.fillPolygon(p);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[160]++;
        }

        /// Draw N, S, E, W
        innerRadius = radius - 42;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[161]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[162]++;
        Font f = getCompassFont(radius);
        g2.setFont(f);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[163]++;
        g2.drawString("N", midX - 5, midY - innerRadius + f.getSize());
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[164]++;
        g2.drawString("S", midX - 5, midY + innerRadius - 5);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[165]++;
        g2.drawString("W", midX - innerRadius + 5, midY + 5);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[166]++;
        g2.drawString("E", midX + innerRadius - f.getSize(), midY + 5);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[167]++;

        // plot the data (unless the dataset is null)...
        y1 = radius / 2;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[168]++;
        x1 = radius / 6;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[169]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[170]++;
        Rectangle2D needleArea = new Rectangle2D.Double(
            (midX - x1), (midY - y1), (2 * x1), (2 * y1)
        );
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[171]++;
        int x = this.seriesNeedle.length;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[172]++;
        int current = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[173]++;
        double value = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[174]++;
        int i = (this.datasets.length - 1);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[175]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[16]++;


int CodeCoverConditionCoverageHelper_C21;
        for (;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); --i) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[16]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[17]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[18]++;
}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[176]++;
            ValueDataset data = this.datasets[i];
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[177]++;
int CodeCoverConditionCoverageHelper_C22;

            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((data.getValue() != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[42]++;
                value = (data.getValue().doubleValue()) 
                    % this.revolutionDistance;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[178]++;
                value = value / this.revolutionDistance * 360;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[179]++;
                current = i % x;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[180]++;
                this.seriesNeedle[current].draw(g2, needleArea, value);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[181]++;

            } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[43]++;}
        }
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[182]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.drawBorder) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[44]++;
            drawOutline(g2, area);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[183]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[45]++;}

    }

    /**
     * Returns a short string describing the type of plot.
     *
     * @return A string describing the plot.
     */
    public String getPlotType() {
        return localizationResources.getString("Compass_Plot");
    }

    /**
     * Returns the legend items for the plot.  For now, no legend is available 
     * - this method returns null.
     *
     * @return The legend items.
     */
    public LegendItemCollection getLegendItems() {
        return null;
    }

    /**
     * No zooming is implemented for compass plot, so this method is empty.
     *
     * @param percent  the zoom amount.
     */
    public void zoom(double percent) {
        // no zooming possible
    }

    /**
     * Returns the font for the compass, adjusted for the size of the plot.
     *
     * @param radius the radius.
     *
     * @return The font.
     */
    protected Font getCompassFont(int radius) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[184]++;
        float fontSize = radius / 10.0f;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[185]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((fontSize < 8) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[46]++;
            fontSize = 8;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[186]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[47]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[187]++;
        Font newFont = this.compassFont.deriveFont(fontSize);
        return newFont;
    }

    /**
     * Tests an object for equality with this plot.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[188]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[48]++;
            return true;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[49]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[189]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((obj instanceof CompassPlot) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[50]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[51]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[190]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[52]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[53]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[191]++;
        CompassPlot that = (CompassPlot) obj;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[192]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.labelType != that.labelType) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[54]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[55]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[193]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.labelFont, that.labelFont)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[56]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[57]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[194]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.drawBorder != that.drawBorder) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[58]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[59]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[195]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.roseHighlightPaint, 
                that.roseHighlightPaint)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[60]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[61]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[196]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.rosePaint, that.rosePaint)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[62]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[63]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[197]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.roseCenterPaint, 
                that.roseCenterPaint)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[64]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[65]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[198]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.compassFont, that.compassFont)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[66]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[67]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[199]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.seriesNeedle, that.seriesNeedle)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[68]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[69]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[200]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((getRevolutionDistance() != that.getRevolutionDistance()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[70]++;
            return false;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[71]++;}
        return true;

    }

    /**
     * Returns a clone of the plot.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  this class will not throw this 
     *         exception, but subclasses (if any) might.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[201]++;

        CompassPlot clone = (CompassPlot) super.clone();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[202]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.circle1 != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[72]++;
            clone.circle1 = (Ellipse2D) this.circle1.clone();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[203]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[73]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[204]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.circle2 != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[74]++;
            clone.circle2 = (Ellipse2D) this.circle2.clone();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[205]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[75]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[206]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((this.a1 != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[76]++;
            clone.a1 = (Area) this.a1.clone();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[207]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[77]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[208]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((this.a2 != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[78]++;
            clone.a2 = (Area) this.a2.clone();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[209]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[79]++;}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[210]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this.rect1 != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[80]++;
            clone.rect1 = (Rectangle2D) this.rect1.clone();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[211]++;
            
        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[81]++;}
        clone.datasets = (ValueDataset[]) this.datasets.clone();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[212]++;
        clone.seriesNeedle = (MeterNeedle[]) this.seriesNeedle.clone();
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[213]++;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[214]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[19]++;


int CodeCoverConditionCoverageHelper_C42;

        // clone share data sets => add the clone as listener to the dataset
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((i < this.datasets.length) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[19]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[20]--;
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.loops[21]++;
}
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[215]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((clone.datasets[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[82]++;
                clone.datasets[i].addChangeListener(clone);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[216]++;

            } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[83]++;}
        }
        return clone;

    }

    /**
     * Sets the count to complete one revolution.  Can be arbitrarily set
     * For degrees (the default) it is 360, for radians this is 2*Pi, etc
     *
     * @param size the count to complete one revolution.
     * 
     * @see #getRevolutionDistance()
     */
    public void setRevolutionDistance(double size) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[217]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((size > 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[84]++;
            this.revolutionDistance = size;
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[218]++;

        } else {
  CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.branches[85]++;}
    }

    /**
     * Gets the count to complete one revolution.
     *
     * @return The count to complete one revolution.
     * 
     * @see #setRevolutionDistance(double)
     */
    public double getRevolutionDistance() {
        return this.revolutionDistance;
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
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[219]++;
        SerialUtilities.writePaint(this.rosePaint, stream);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[220]++;
        SerialUtilities.writePaint(this.roseCenterPaint, stream);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[221]++;
        SerialUtilities.writePaint(this.roseHighlightPaint, stream);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[222]++;
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
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[223]++;
        this.rosePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[224]++;
        this.roseCenterPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[225]++;
        this.roseHighlightPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox.statements[226]++;
    }

}

class CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox ());
  }
    public static long[] statements = new long[227];
    public static long[] branches = new long[86];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[45];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.CompassPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 44; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$3zq26ptt8o7n2ci164ji0niox () {
    super("org.jfree.chart.plot.CompassPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 226; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 85; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 44; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.CompassPlot.java");
      for (int i = 1; i <= 226; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 85; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 44; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

