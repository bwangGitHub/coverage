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
 * ------------------------
 * CandlestickRenderer.java
 * ------------------------
 * (C) Copyright 2001-2007, by Object Refinery Limited.
 *
 * Original Authors:  David Gilbert (for Object Refinery Limited);
 *                    Sylvain Vieujot;
 * Contributor(s):    Richard Atkinson;
 *                    Christian W. Zuckschwerdt;
 *                    Jerome Fisher;
 *
 * Changes
 * -------
 * 13-Dec-2001 : Version 1.  Based on code in the (now redundant) 
 *               CandlestickPlot class, written by Sylvain Vieujot (DG);
 * 23-Jan-2002 : Added DrawInfo parameter to drawItem() method (DG);
 * 28-Mar-2002 : Added a property change listener mechanism so that renderers 
 *               no longer need to be immutable.  Added properties for up and 
 *               down colors (DG);
 * 04-Apr-2002 : Updated with new automatic width calculation and optional 
 *               volume display, contributed by Sylvain Vieujot (DG);
 * 09-Apr-2002 : Removed translatedRangeZero from the drawItem() method, and 
 *               changed the return type of the drawItem method to void, 
 *               reflecting a change in the XYItemRenderer interface.  Added 
 *               tooltip code to drawItem() method (DG);
 * 25-Jun-2002 : Removed redundant code (DG);
 * 05-Aug-2002 : Small modification to drawItem method to support URLs for HTML 
 *               image maps (RA);
 * 19-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 25-Mar-2003 : Implemented Serializable (DG);
 * 01-May-2003 : Modified drawItem() method signature (DG);
 * 30-Jun-2003 : Added support for PlotOrientation (for completeness, this 
 *               renderer is unlikely to be used with a HORIZONTAL 
 *               orientation) (DG);
 * 30-Jul-2003 : Modified entity constructor (CZ);
 * 20-Aug-2003 : Implemented Cloneable and PublicCloneable (DG);
 * 29-Aug-2003 : Moved maxVolume calculation to initialise method (see bug 
 *               report 796619) (DG);
 * 02-Sep-2003 : Added maxCandleWidthInMilliseconds as workaround for bug 
 *               796621 (DG);
 * 08-Sep-2003 : Changed ValueAxis API (DG);
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 13-Oct-2003 : Applied patch from Jerome Fisher to improve auto width 
 *               calculations (DG);
 * 23-Dec-2003 : Fixed bug where up and down paint are used incorrectly (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 15-Jul-2004 : Switched getX() with getXValue() and getY() with 
 *               getYValue() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Jul-2006 : Swapped calls to getX() --> getXValue(), and the same for the
 *               other data values (DG);
 * 17-Aug-2006 : Corrections to the equals() method (DG);
 * 05-Mar-2007 : Added flag to allow optional use of outline paint (DG);
 * 08-Oct-2007 : Added new volumePaint field (DG);
 * 
 */

package org.jfree.chart.renderer.xy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.labels.HighLowItemLabelGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A renderer that draws candlesticks on an {@link XYPlot} (requires a 
 * {@link OHLCDataset}).
 * <P>
 * This renderer does not include code to calculate the crosshair point for the 
 * plot.
 */
public class CandlestickRenderer extends AbstractXYItemRenderer 
                                 implements XYItemRenderer, 
                                            Cloneable,
                                            PublicCloneable,
                                            Serializable {
  static {
    CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.ping();
  }

            
    /** For serialization. */
    private static final long serialVersionUID = 50390395841817121L;
  static {
    CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[1]++;
  }
    
    /** The average width method. */                                          
    public static final int WIDTHMETHOD_AVERAGE = 0;
  static {
    CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[2]++;
  }
    
    /** The smallest width method. */
    public static final int WIDTHMETHOD_SMALLEST = 1;
  static {
    CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[3]++;
  }
    
    /** The interval data method. */
    public static final int WIDTHMETHOD_INTERVALDATA = 2;
  static {
    CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[4]++;
  }

    /** The method of automatically calculating the candle width. */
    private int autoWidthMethod = WIDTHMETHOD_AVERAGE;
  {
    CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[5]++;
  }

    /** 
     * The number (generally between 0.0 and 1.0) by which the available space 
     * automatically calculated for the candles will be multiplied to determine
     * the actual width to use. 
     */
    private double autoWidthFactor = 4.5 / 7;
  {
    CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[6]++;
  }

    /** The minimum gap between one candle and the next */
    private double autoWidthGap = 0.0;
  {
    CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[7]++;
  }

    /** The candle width. */
    private double candleWidth;
    
    /** The maximum candlewidth in milliseconds. */
    private double maxCandleWidthInMilliseconds = 1000.0 * 60.0 * 60.0 * 20.0;
  {
    CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[8]++;
  }
    
    /** Temporary storage for the maximum candle width. */
    private double maxCandleWidth;

    /** 
     * The paint used to fill the candle when the price moved up from open to 
     * close. 
     */
    private transient Paint upPaint;

    /** 
     * The paint used to fill the candle when the price moved down from open 
     * to close. 
     */
    private transient Paint downPaint;

    /** A flag controlling whether or not volume bars are drawn on the chart. */
    private boolean drawVolume;
    
    /** 
     * The paint used to fill the volume bars (if they are visible).  Once 
     * initialised, this field should never be set to <code>null</code>.
     *
     * @since 1.0.7
     */
    private transient Paint volumePaint;
    
    /** Temporary storage for the maximum volume. */
    private transient double maxVolume;
    
    /** 
     * A flag that controls whether or not the renderer's outline paint is
     * used to draw the outline of the candlestick.  The default value is
     * <code>false</code> to avoid a change of behaviour for existing code.
     * 
     * @since 1.0.5
     */
    private boolean useOutlinePaint;

    /**
     * Creates a new renderer for candlestick charts.
     */
    public CandlestickRenderer() {
        this(-1.0);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[9]++;
    }

    /**
     * Creates a new renderer for candlestick charts.
     * <P>
     * Use -1 for the candle width if you prefer the width to be calculated 
     * automatically.
     *
     * @param candleWidth  The candle width.
     */
    public CandlestickRenderer(double candleWidth) {
        this(candleWidth, true, new HighLowItemLabelGenerator());
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[10]++;
    }

    /**
     * Creates a new renderer for candlestick charts.
     * <P>
     * Use -1 for the candle width if you prefer the width to be calculated 
     * automatically.
     *
     * @param candleWidth  the candle width.
     * @param drawVolume  a flag indicating whether or not volume bars should 
     *                    be drawn.
     * @param toolTipGenerator  the tool tip generator. <code>null</code> is 
     *                          none.
     */
    public CandlestickRenderer(double candleWidth, boolean drawVolume,
                               XYToolTipGenerator toolTipGenerator) {
        super();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[11]++;
        setBaseToolTipGenerator(toolTipGenerator);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[12]++;
        this.candleWidth = candleWidth;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[13]++;
        this.drawVolume = drawVolume;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[14]++;
        this.volumePaint = Color.gray;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[15]++;
        this.upPaint = Color.green;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[16]++;
        this.downPaint = Color.red;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[17]++;
        this.useOutlinePaint = false;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[18]++;  // false preserves the old behaviour
                                       // prior to introducing this flag
    }

    /**
     * Returns the width of each candle.
     *
     * @return The candle width.
     * 
     * @see #setCandleWidth(double)
     */
    public double getCandleWidth() {
        return this.candleWidth;
    }

    /**
     * Sets the candle width and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     * <P>
     * If you set the width to a negative value, the renderer will calculate
     * the candle width automatically based on the space available on the chart.
     *
     * @param width  The width.
     * @see #setAutoWidthMethod(int)
     * @see #setAutoWidthGap(double)
     * @see #setAutoWidthFactor(double)
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public void setCandleWidth(double width) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((width != this.candleWidth) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[1]++;
            this.candleWidth = width;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[20]++;
            fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[21]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[2]++;}
    }

    /**
     * Returns the maximum width (in milliseconds) of each candle.
     *
     * @return The maximum candle width in milliseconds.
     * 
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public double getMaxCandleWidthInMilliseconds() {
        return this.maxCandleWidthInMilliseconds;
    }

    /**
     * Sets the maximum candle width (in milliseconds) and sends a 
     * {@link RendererChangeEvent} to all registered listeners.  
     *
     * @param millis  The maximum width.
     * 
     * @see #getMaxCandleWidthInMilliseconds()
     * @see #setCandleWidth(double)
     * @see #setAutoWidthMethod(int)
     * @see #setAutoWidthGap(double)
     * @see #setAutoWidthFactor(double)
     */
    public void setMaxCandleWidthInMilliseconds(double millis) {
        this.maxCandleWidthInMilliseconds = millis;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[22]++;
        fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[23]++;
    }

    /**
     * Returns the method of automatically calculating the candle width.
     *
     * @return The method of automatically calculating the candle width.
     * 
     * @see #setAutoWidthMethod(int)
     */
    public int getAutoWidthMethod() {
        return this.autoWidthMethod;
    }

    /**
     * Sets the method of automatically calculating the candle width and 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * <p>
     * <code>WIDTHMETHOD_AVERAGE</code>: Divides the entire display (ignoring 
     * scale factor) by the number of items, and uses this as the available 
     * width.<br>
     * <code>WIDTHMETHOD_SMALLEST</code>: Checks the interval between each 
     * item, and uses the smallest as the available width.<br>
     * <code>WIDTHMETHOD_INTERVALDATA</code>: Assumes that the dataset supports
     * the IntervalXYDataset interface, and uses the startXValue - endXValue as 
     * the available width.
     * <br>
     *
     * @param autoWidthMethod  The method of automatically calculating the 
     * candle width.
     *
     * @see #WIDTHMETHOD_AVERAGE
     * @see #WIDTHMETHOD_SMALLEST
     * @see #WIDTHMETHOD_INTERVALDATA
     * @see #getAutoWidthMethod()
     * @see #setCandleWidth(double)
     * @see #setAutoWidthGap(double)
     * @see #setAutoWidthFactor(double)
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public void setAutoWidthMethod(int autoWidthMethod) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.autoWidthMethod != autoWidthMethod) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[3]++;
            this.autoWidthMethod = autoWidthMethod;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[25]++;
            fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[26]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[4]++;}
    }

    /**
     * Returns the factor by which the available space automatically 
     * calculated for the candles will be multiplied to determine the actual 
     * width to use.
     *
     * @return The width factor (generally between 0.0 and 1.0).
     * 
     * @see #setAutoWidthFactor(double)
     */
    public double getAutoWidthFactor() {
        return this.autoWidthFactor;
    }

    /**
     * Sets the factor by which the available space automatically calculated 
     * for the candles will be multiplied to determine the actual width to use.
     *
     * @param autoWidthFactor The width factor (generally between 0.0 and 1.0).
     * 
     * @see #getAutoWidthFactor()
     * @see #setCandleWidth(double)
     * @see #setAutoWidthMethod(int)
     * @see #setAutoWidthGap(double)
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public void setAutoWidthFactor(double autoWidthFactor) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[27]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.autoWidthFactor != autoWidthFactor) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[5]++;
            this.autoWidthFactor = autoWidthFactor;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[28]++;
            fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[29]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[6]++;}
    }

    /**
     * Returns the amount of space to leave on the left and right of each 
     * candle when automatically calculating widths.
     *
     * @return The gap.
     * 
     * @see #setAutoWidthGap(double)
     */
    public double getAutoWidthGap() {
        return this.autoWidthGap;
    }

    /**
     * Sets the amount of space to leave on the left and right of each candle 
     * when automatically calculating widths and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param autoWidthGap The gap.
     * 
     * @see #getAutoWidthGap()
     * @see #setCandleWidth(double)
     * @see #setAutoWidthMethod(int)
     * @see #setAutoWidthFactor(double)
     * @see #setMaxCandleWidthInMilliseconds(double)
     */
    public void setAutoWidthGap(double autoWidthGap) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.autoWidthGap != autoWidthGap) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[7]++;
            this.autoWidthGap = autoWidthGap;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[31]++;
            fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[32]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[8]++;}
    }

    /**
     * Returns the paint used to fill candles when the price moves up from open
     * to close.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setUpPaint(Paint)
     */
    public Paint getUpPaint() {
        return this.upPaint;
    }

    /**
     * Sets the paint used to fill candles when the price moves up from open
     * to close and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getUpPaint()
     */
    public void setUpPaint(Paint paint) {
        this.upPaint = paint;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[33]++;
        fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[34]++;
    }

    /**
     * Returns the paint used to fill candles when the price moves down from
     * open to close.
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setDownPaint(Paint)
     */
    public Paint getDownPaint() {
        return this.downPaint;
    }

    /**
     * Sets the paint used to fill candles when the price moves down from open
     * to close and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param paint  The paint (<code>null</code> permitted).
     */
    public void setDownPaint(Paint paint) {
        this.downPaint = paint;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[35]++;
        fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[36]++;
    }

    /**
     * Returns a flag indicating whether or not volume bars are drawn on the
     * chart.
     * 
     * @return A boolean.
     * 
     * @since 1.0.5
     * 
     * @see #setDrawVolume(boolean)
     */
    public boolean getDrawVolume() {
        return this.drawVolume;
    }

    /**
     * Sets a flag that controls whether or not volume bars are drawn in the
     * background and sends a {@link RendererChangeEvent} to all registered
     * listeners.
     *
     * @param flag  the flag.
     * 
     * @see #getDrawVolume()
     */
    public void setDrawVolume(boolean flag) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.drawVolume != flag) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[9]++;
            this.drawVolume = flag;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[38]++;
            fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[39]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[10]++;}
    }
    
    /**
     * Returns the paint that is used to fill the volume bars if they are
     * visible.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setVolumePaint(Paint)
     * 
     * @since 1.0.7
     */
    public Paint getVolumePaint() {
        return this.volumePaint;    
    }
    
    /**
     * Sets the paint used to fill the volume bars, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getVolumePaint()
     * @see #getDrawVolume()
     * 
     * @since 1.0.7
     */
    public void setVolumePaint(Paint paint) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[40]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[11]++; 
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[12]++;}
        this.volumePaint = paint;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[41]++;
        fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[42]++;
    }

    /**
     * Returns the flag that controls whether or not the renderer's outline
     * paint is used to draw the candlestick outline.  The default value is
     * <code>false</code>.
     * 
     * @return A boolean.
     * 
     * @since 1.0.5
     * 
     * @see #setUseOutlinePaint(boolean)
     */
    public boolean getUseOutlinePaint() {
        return this.useOutlinePaint;
    }
    
    /**
     * Sets the flag that controls whether or not the renderer's outline
     * paint is used to draw the candlestick outline, and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param use  the new flag value.
     * 
     * @since 1.0.5
     * 
     * @see #getUseOutlinePaint()
     */
    public void setUseOutlinePaint(boolean use) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[43]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint != use) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[13]++;
            this.useOutlinePaint = use;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[44]++;
            fireChangeEvent();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[45]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[14]++;}
    }
    
    /**
     * Initialises the renderer then returns the number of 'passes' through the
     * data that the renderer will require (usually just one).  This method 
     * will be called before the first item is rendered, giving the renderer 
     * an opportunity to initialise any state information it wants to maintain.
     * The renderer can do nothing if it chooses.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area inside the axes.
     * @param plot  the plot.
     * @param dataset  the data.
     * @param info  an optional info collection object to return data back to 
     *              the caller.
     *
     * @return The number of passes the renderer requires.
     */
    public XYItemRendererState initialise(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          XYPlot plot,
                                          XYDataset dataset,
                                          PlotRenderingInfo info) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[46]++;
          
        // calculate the maximum allowed candle width from the axis...
        ValueAxis axis = plot.getDomainAxis();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[47]++;
        double x1 = axis.getLowerBound();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[48]++;
        double x2 = x1 + this.maxCandleWidthInMilliseconds;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[49]++;
        RectangleEdge edge = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[50]++;
        double xx1 = axis.valueToJava2D(x1, dataArea, edge);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[51]++;
        double xx2 = axis.valueToJava2D(x2, dataArea, edge);
        this.maxCandleWidth = Math.abs(xx2 - xx1);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[52]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[53]++;
int CodeCoverConditionCoverageHelper_C8; 
            // Absolute value, since the relative x 
            // positions are reversed for horizontal orientation
        
        // calculate the highest volume in the dataset... 
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.drawVolume) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[15]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[54]++;
            OHLCDataset highLowDataset = (OHLCDataset) dataset;
            this.maxVolume = 0.0;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[55]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[56]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[1]++;


int CodeCoverConditionCoverageHelper_C9;
            for (int series = 0;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((series < highLowDataset.getSeriesCount()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); 
                 series++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[1]--;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[2]--;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[3]++;
}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[57]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[4]++;


int CodeCoverConditionCoverageHelper_C10;
                for (int item = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((item < highLowDataset.getItemCount(series)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); 
                     item++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[4]--;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[5]--;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[6]++;
}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[58]++;
                    double volume = highLowDataset.getVolumeValue(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[59]++;
int CodeCoverConditionCoverageHelper_C11;
                    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((volume > this.maxVolume) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[17]++;
                        this.maxVolume = volume;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[60]++;

                    } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[18]++;}
                    
                }    
            }

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[16]++;}
        
        return new XYItemRendererState(info);
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the plot is being drawn.
     * @param info  collects info about the drawing.
     * @param plot  the plot (can be used to obtain standard color 
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot 
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    public void drawItem(Graphics2D g2, 
                         XYItemRendererState state,
                         Rectangle2D dataArea,
                         PlotRenderingInfo info,
                         XYPlot plot, 
                         ValueAxis domainAxis, 
                         ValueAxis rangeAxis,
                         XYDataset dataset, 
                         int series, 
                         int item,
                         CrosshairState crosshairState,
                         int pass) {

        boolean horiz;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[61]++;
        PlotOrientation orientation = plot.getOrientation();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[62]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.HORIZONTAL) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[19]++;
            horiz = true;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[63]++;

        }
        else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[20]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[64]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((orientation == PlotOrientation.VERTICAL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[21]++;
            horiz = false;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[65]++;

        }
        else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[22]++;
            return;
        }
}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[66]++;
        
        // setup for collecting optional entity info...
        EntityCollection entities = null;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[67]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[23]++;
            entities = info.getOwner().getEntityCollection();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[68]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[24]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[69]++;

        OHLCDataset highLowData = (OHLCDataset) dataset;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[70]++;

        double x = highLowData.getXValue(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[71]++;
        double yHigh = highLowData.getHighValue(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[72]++;
        double yLow = highLowData.getLowValue(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[73]++;
        double yOpen = highLowData.getOpenValue(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[74]++;
        double yClose = highLowData.getCloseValue(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[75]++;

        RectangleEdge domainEdge = plot.getDomainAxisEdge();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[76]++;
        double xx = domainAxis.valueToJava2D(x, dataArea, domainEdge);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[77]++;

        RectangleEdge edge = plot.getRangeAxisEdge();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[78]++;
        double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, edge);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[79]++;
        double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, edge);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[80]++;
        double yyOpen = rangeAxis.valueToJava2D(yOpen, dataArea, edge);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[81]++;
        double yyClose = rangeAxis.valueToJava2D(yClose, dataArea, edge);

        double volumeWidth;
        double stickWidth;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[82]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.candleWidth > 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[25]++;
            // These are deliberately not bounded to minimums/maxCandleWidth to
            //  retain old behaviour.
            volumeWidth = this.candleWidth;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[83]++;
            stickWidth = this.candleWidth;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[84]++;

        }
        else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[26]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[85]++;
            double xxWidth = 0;
            int itemCount;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[86]++;
            switch (this.autoWidthMethod) {
            
                case WIDTHMETHOD_AVERAGE:
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[27]++;
                    itemCount = highLowData.getItemCount(series);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[87]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[88]++;
int CodeCoverConditionCoverageHelper_C16;
                    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((horiz) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[28]++;
                        xxWidth = dataArea.getHeight() / itemCount;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[89]++;

                    }
                    else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[29]++;
                        xxWidth = dataArea.getWidth() / itemCount;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[90]++;
                    }
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[91]++;
                    break;
            
                case WIDTHMETHOD_SMALLEST:
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[30]++;
                    // Note: It would be nice to pre-calculate this per series
                    itemCount = highLowData.getItemCount(series);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[92]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[93]++;
                    double lastPos = -1;
                    xxWidth = dataArea.getWidth();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[94]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[95]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[7]++;


int CodeCoverConditionCoverageHelper_C17;
                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < itemCount) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[7]--;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[8]--;
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.loops[9]++;
}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[96]++;
                        double pos = domainAxis.valueToJava2D(
                                highLowData.getXValue(series, i), dataArea, 
                                domainEdge);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[97]++;
int CodeCoverConditionCoverageHelper_C18;
                        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((lastPos != -1) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[31]++;
                            xxWidth = Math.min(xxWidth, 
                                    Math.abs(pos - lastPos));
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[98]++;

                        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[32]++;}
                        lastPos = pos;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[99]++;
                    }
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[100]++;
                    break;
            
                case WIDTHMETHOD_INTERVALDATA:
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[33]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[101]++;
                    IntervalXYDataset intervalXYData 
                            = (IntervalXYDataset) dataset;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[102]++;
                    double startPos = domainAxis.valueToJava2D(
                            intervalXYData.getStartXValue(series, item), 
                            dataArea, plot.getDomainAxisEdge());
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[103]++;
                    double endPos = domainAxis.valueToJava2D(
                            intervalXYData.getEndXValue(series, item), 
                            dataArea, plot.getDomainAxisEdge());
                    xxWidth = Math.abs(endPos - startPos);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[104]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[105]++;
                    break; default : CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[34]++;
                
            }
            xxWidth -= 2 * this.autoWidthGap;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[106]++;
            xxWidth *= this.autoWidthFactor;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[107]++;
            xxWidth = Math.min(xxWidth, this.maxCandleWidth);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[108]++;
            volumeWidth = Math.max(Math.min(1, this.maxCandleWidth), xxWidth);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[109]++;
            stickWidth = Math.max(Math.min(3, this.maxCandleWidth), xxWidth);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[110]++;
        }
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[111]++;

        Paint p = getItemPaint(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[112]++;
        Paint outlinePaint = null;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[113]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[35]++;
            outlinePaint = getItemOutlinePaint(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[114]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[36]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[115]++;
        Stroke s = getItemStroke(series, item);

        g2.setStroke(s);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[116]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[117]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((this.drawVolume) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[37]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[118]++;
            int volume = (int) highLowData.getVolumeValue(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[119]++;
            double volumeHeight = volume / this.maxVolume;

            double min, max;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[120]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((horiz) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[39]++;
                min = dataArea.getMinX();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[121]++;
                max = dataArea.getMaxX();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[122]++;

            }
            else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[40]++;
                min = dataArea.getMinY();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[123]++;
                max = dataArea.getMaxY();
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[124]++;
            }
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[125]++;

            double zzVolume = volumeHeight * (max - min);

            g2.setPaint(getVolumePaint());
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[126]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[127]++;
            Composite originalComposite = g2.getComposite();
            g2.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)
            );
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[128]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[129]++;
int CodeCoverConditionCoverageHelper_C22;

            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((horiz) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[41]++;
                g2.fill(new Rectangle2D.Double(min, xx - volumeWidth / 2,
                        zzVolume, volumeWidth));
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[130]++;

            }
            else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[42]++;
                g2.fill(new Rectangle2D.Double(xx - volumeWidth / 2,
                        max - zzVolume, volumeWidth, zzVolume));
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[131]++;
            }

            g2.setComposite(originalComposite);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[132]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[38]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[133]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[43]++;
            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[134]++;

        }
        else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[44]++;
            g2.setPaint(p);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[135]++;
        }
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[136]++;

        double yyMaxOpenClose = Math.max(yyOpen, yyClose);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[137]++;
        double yyMinOpenClose = Math.min(yyOpen, yyClose);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[138]++;
        double maxOpenClose = Math.max(yOpen, yClose);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[139]++;
        double minOpenClose = Math.min(yOpen, yClose);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[140]++;
int CodeCoverConditionCoverageHelper_C24;

        // draw the upper shadow
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((yHigh > maxOpenClose) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[45]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[141]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((horiz) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[47]++;
                g2.draw(new Line2D.Double(yyHigh, xx, yyMaxOpenClose, xx));
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[142]++;

            }
            else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[48]++;
                g2.draw(new Line2D.Double(xx, yyHigh, xx, yyMaxOpenClose));
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[143]++;
            }

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[46]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[144]++;
int CodeCoverConditionCoverageHelper_C26;

        // draw the lower shadow
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((yLow < minOpenClose) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[49]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[145]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((horiz) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[51]++;
                g2.draw(new Line2D.Double(yyLow, xx, yyMinOpenClose, xx));
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[146]++;

            }
            else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[52]++;
                g2.draw(new Line2D.Double(xx, yyLow, xx, yyMinOpenClose));
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[147]++;
            }

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[50]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[148]++;

        // draw the body
        Shape body = null;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[149]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((horiz) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[53]++;
            body = new Rectangle2D.Double(yyMinOpenClose, xx - stickWidth / 2, 
                    yyMaxOpenClose - yyMinOpenClose, stickWidth);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[150]++;

        } 
        else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[54]++;
            body = new Rectangle2D.Double(xx - stickWidth / 2, yyMinOpenClose,
                    stickWidth, yyMaxOpenClose - yyMinOpenClose);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[151]++;
        }
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[152]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((yClose > yOpen) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[55]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[153]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.upPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[57]++;
                g2.setPaint(this.upPaint);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[154]++;

            }
            else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[58]++;
                g2.setPaint(p);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[155]++;
            }
            g2.fill(body);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[156]++;

        }
        else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[56]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[157]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.downPaint != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[59]++;
                g2.setPaint(this.downPaint);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[158]++;

            }
            else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[60]++;
                g2.setPaint(p);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[159]++;
            }
            g2.fill(body);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[160]++;
        }
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[161]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[61]++;
            g2.setPaint(outlinePaint);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[162]++;

        }
        else {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[62]++;
            g2.setPaint(p);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[163]++;
        }
        g2.draw(body);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[164]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[165]++;
int CodeCoverConditionCoverageHelper_C33;

        // add an entity for the item...
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((entities != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[63]++;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[166]++;
            String tip = null;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[167]++;
            XYToolTipGenerator generator = getToolTipGenerator(series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[168]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((generator != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[65]++;
                tip = generator.generateToolTip(dataset, series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[169]++;

            } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[66]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[170]++;
            String url = null;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[171]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((getURLGenerator() != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[67]++;
                url = getURLGenerator().generateURL(dataset, series, item);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[172]++;

            } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[68]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[173]++;
            XYItemEntity entity = new XYItemEntity(body, dataset, series, item, 
                    tip, url);
            entities.add(entity);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[174]++;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[64]++;}

    }

    /**
     * Tests this renderer for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[175]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[69]++;
            return true;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[70]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[176]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((obj instanceof CandlestickRenderer) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[72]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[177]++;
        CandlestickRenderer that = (CandlestickRenderer) obj;
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[178]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.candleWidth != that.candleWidth) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[73]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[74]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[179]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.upPaint, that.upPaint)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[76]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[180]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.downPaint, that.downPaint)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[78]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[181]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this.drawVolume != that.drawVolume) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[80]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[182]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.maxCandleWidthInMilliseconds 
                != that.maxCandleWidthInMilliseconds) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[81]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[82]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[183]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((this.autoWidthMethod != that.autoWidthMethod) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[83]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[84]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[184]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((this.autoWidthFactor != that.autoWidthFactor) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[85]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[86]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[185]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((this.autoWidthGap != that.autoWidthGap) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[87]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[88]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[186]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.useOutlinePaint != that.useOutlinePaint) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[89]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[90]++;}
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[187]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.volumePaint, that.volumePaint)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[91]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.branches[92]++;}
        return super.equals(obj);
    }

    /**
     * Returns a clone of the renderer.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[188]++;
        SerialUtilities.writePaint(this.upPaint, stream);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[189]++;
        SerialUtilities.writePaint(this.downPaint, stream);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[190]++;
        SerialUtilities.writePaint(this.volumePaint, stream);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[191]++;
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
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[192]++;
        this.upPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[193]++;
        this.downPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[194]++;
        this.volumePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl.statements[195]++;
    }

    // --- DEPRECATED CODE ----------------------------------------------------
    
    /**
     * Returns a flag indicating whether or not volume bars are drawn on the
     * chart.
     *
     * @return <code>true</code> if volume bars are drawn on the chart.
     * 
     * @deprecated As of 1.0.5, you should use the {@link #getDrawVolume()} 
     *         method.
     */
    public boolean drawVolume() {
        return this.drawVolume;
    }

}

class CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl ());
  }
    public static long[] statements = new long[196];
    public static long[] branches = new long[93];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[48];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.xy.CandlestickRenderer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 47; i++) {
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

  public CodeCoverCoverageCounter$fj2j7zoo4v22lo5dqn4fe9kxn4lothac8kvvl () {
    super("org.jfree.chart.renderer.xy.CandlestickRenderer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 195; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 92; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 47; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.xy.CandlestickRenderer.java");
      for (int i = 1; i <= 195; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 92; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 47; i++) {
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

