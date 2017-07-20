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
 * FastScatterPlot.java
 * --------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Arnaud Lelievre;
 *
 * Changes
 * -------
 * 29-Oct-2002 : Added standard header (DG);
 * 07-Nov-2002 : Fixed errors reported by Checkstyle (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 19-Aug-2003 : Implemented Cloneable (DG);
 * 08-Sep-2003 : Added internationalization via use of properties 
 *               resourceBundle (RFE 690236) (AL); 
 * 16-Sep-2003 : Changed ChartRenderingInfo --> PlotRenderingInfo (DG);
 * 12-Nov-2003 : Implemented zooming (DG);
 * 21-Jan-2004 : Update for renamed method in ValueAxis (DG);
 * 26-Jan-2004 : Added domain and range grid lines (DG);
 * 25-Feb-2004 : Replaced CrosshairInfo with CrosshairState (DG);
 * 29-Sep-2004 : Removed hard-coded color (DG);
 * 04-Oct-2004 : Reworked equals() method and renamed ArrayUtils 
 *               --> ArrayUtilities (DG);
 * 12-Nov-2004 : Implemented the new Zoomable interface (DG);
 * 05-May-2005 : Updated draw() method parameters (DG);
 * 16-Jun-2005 : Added get/setData() methods (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 10-Nov-2006 : Fixed bug 1593150, by not allowing null axes, and added
 *               setDomainAxis() and setRangeAxis() methods (DG);
 * 24-Sep-2007 : Implemented new zooming methods (DG);
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
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.ValueTick;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.data.Range;
import org.jfree.io.SerialUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.ArrayUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * A fast scatter plot.
 */
public class FastScatterPlot extends Plot implements ValueAxisPlot, 
        Zoomable, Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 7871545897358563521L;
  static {
    CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[1]++;
  }
    
    /** The default grid line stroke. */
    public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0.0f, new float[] 
            {2.0f, 2.0f}, 0.0f);
  static {
    CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[2]++;
  }

    /** The default grid line paint. */
    public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
  static {
    CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[3]++;
  }

    /** The data. */
    private float[][] data;

    /** The x data range. */
    private Range xDataRange;

    /** The y data range. */
    private Range yDataRange;

    /** The domain axis (used for the x-values). */
    private ValueAxis domainAxis;

    /** The range axis (used for the y-values). */
    private ValueAxis rangeAxis;

    /** The paint used to plot data points. */
    private transient Paint paint;

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

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources = 
            ResourceBundle.getBundle(
            "org.jfree.chart.plot.LocalizationBundle");
  static {
    CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[4]++;
  }

    /**
     * Creates a new instance of <code>FastScatterPlot</code> with default 
     * axes.
     */
    public FastScatterPlot() {
        this(null, new NumberAxis("X"), new NumberAxis("Y"));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[5]++;    
    }
    
    /**
     * Creates a new fast scatter plot.
     * <p>
     * The data is an array of x, y values:  data[0][i] = x, data[1][i] = y.
     * 
     * @param data  the data (<code>null</code> permitted).
     * @param domainAxis  the domain (x) axis (<code>null</code> not permitted).
     * @param rangeAxis  the range (y) axis (<code>null</code> not permitted).
     */
    public FastScatterPlot(float[][] data, 
                           ValueAxis domainAxis, ValueAxis rangeAxis) {

        super();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[6]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((domainAxis == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[1]++;
            throw new IllegalArgumentException("Null 'domainAxis' argument.");

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[2]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((rangeAxis == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[3]++;
            throw new IllegalArgumentException("Null 'rangeAxis' argument.");

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[4]++;}
        
        this.data = data;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[9]++;
        this.xDataRange = calculateXDataRange(data);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[10]++;
        this.yDataRange = calculateYDataRange(data);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[11]++;
        this.domainAxis = domainAxis;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[12]++;
        this.domainAxis.setPlot(this);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[13]++;
        this.domainAxis.addChangeListener(this);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[14]++;
        this.rangeAxis = rangeAxis;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[15]++;
        this.rangeAxis.setPlot(this);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[16]++;
        this.rangeAxis.addChangeListener(this);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[17]++;

        this.paint = Color.red;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[18]++;
        
        this.domainGridlinesVisible = true;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[19]++;
        this.domainGridlinePaint = FastScatterPlot.DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[20]++;
        this.domainGridlineStroke = FastScatterPlot.DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[21]++;

        this.rangeGridlinesVisible = true;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[22]++;
        this.rangeGridlinePaint = FastScatterPlot.DEFAULT_GRIDLINE_PAINT;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[23]++;
        this.rangeGridlineStroke = FastScatterPlot.DEFAULT_GRIDLINE_STROKE;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[24]++;
    
    }

    /**
     * Returns a short string describing the plot type.
     *
     * @return A short string describing the plot type.
     */
    public String getPlotType() {
        return localizationResources.getString("Fast_Scatter_Plot");
    }

    /**
     * Returns the data array used by the plot.
     * 
     * @return The data array (possibly <code>null</code>).
     * 
     * @see #setData(float[][])
     */
    public float[][] getData() {
        return this.data;   
    }
    
    /**
     * Sets the data array used by the plot and sends a {@link PlotChangeEvent}
     * to all registered listeners.
     * 
     * @param data  the data array (<code>null</code> permitted).
     * 
     * @see #getData()
     */
    public void setData(float[][] data) {
        this.data = data;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[25]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[26]++;
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
     * Returns the domain axis for the plot.
     *
     * @return The domain axis (never <code>null</code>).
     * 
     * @see #setDomainAxis(ValueAxis)
     */
    public ValueAxis getDomainAxis() {
        return this.domainAxis;
    }
    
    /**
     * Sets the domain axis and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param axis  the axis (<code>null</code> not permitted).
     * 
     * @since 1.0.3
     * 
     * @see #getDomainAxis()
     */
    public void setDomainAxis(ValueAxis axis) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[27]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((axis == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[5]++;
            throw new IllegalArgumentException("Null 'axis' argument.");

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[6]++;}
        this.domainAxis = axis;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[28]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[29]++;
    }

    /**
     * Returns the range axis for the plot.
     *
     * @return The range axis (never <code>null</code>).
     * 
     * @see #setRangeAxis(ValueAxis)
     */
    public ValueAxis getRangeAxis() {
        return this.rangeAxis;
    }

    /**
     * Sets the range axis and sends a {@link PlotChangeEvent} to all 
     * registered listeners.
     * 
     * @param axis  the axis (<code>null</code> not permitted).
     * 
     * @since 1.0.3
     * 
     * @see #getRangeAxis()
     */
    public void setRangeAxis(ValueAxis axis) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((axis == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[7]++;
            throw new IllegalArgumentException("Null 'axis' argument.");

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[8]++;}
        this.rangeAxis = axis;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[31]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[32]++;
    }

    /**
     * Returns the paint used to plot data points.  The default is 
     * <code>Color.red</code>.
     *
     * @return The paint.
     * 
     * @see #setPaint(Paint)
     */
    public Paint getPaint() {
        return this.paint;
    }

    /**
     * Sets the color for the data points and sends a {@link PlotChangeEvent} 
     * to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getPaint()
     */
    public void setPaint(Paint paint) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[9]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[10]++;}
        this.paint = paint;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[34]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[35]++;
    }

    /**
     * Returns <code>true</code> if the domain gridlines are visible, and 
     * <code>false<code> otherwise.
     *
     * @return <code>true</code> or <code>false</code>.
     * 
     * @see #setDomainGridlinesVisible(boolean)
     * @see #setDomainGridlinePaint(Paint)
     */
    public boolean isDomainGridlinesVisible() {
        return this.domainGridlinesVisible;
    }

    /**
     * Sets the flag that controls whether or not the domain grid-lines are 
     * visible.  If the flag value is changed, a {@link PlotChangeEvent} is 
     * sent to all registered listeners.
     *
     * @param visible  the new value of the flag.
     * 
     * @see #getDomainGridlinePaint()
     */
    public void setDomainGridlinesVisible(boolean visible) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[36]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.domainGridlinesVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[11]++;
            this.domainGridlinesVisible = visible;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[37]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[38]++;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[12]++;}
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
     * Sets the stroke for the grid lines plotted against the domain axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getDomainGridlineStroke()
     */
    public void setDomainGridlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[13]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[14]++;}
        this.domainGridlineStroke = stroke;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[40]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[41]++;
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
     * Sets the paint for the grid lines plotted against the domain axis and
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getDomainGridlinePaint()
     */
    public void setDomainGridlinePaint(Paint paint) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[15]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[16]++;}
        this.domainGridlinePaint = paint;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[43]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[44]++;
    }

    /**
     * Returns <code>true</code> if the range axis grid is visible, and 
     * <code>false<code> otherwise.
     *
     * @return <code>true</code> or <code>false</code>.
     * 
     * @see #setRangeGridlinesVisible(boolean)
     */
    public boolean isRangeGridlinesVisible() {
        return this.rangeGridlinesVisible;
    }

    /**
     * Sets the flag that controls whether or not the range axis grid lines are
     * visible.  If the flag value is changed, a {@link PlotChangeEvent} is 
     * sent to all registered listeners.
     *
     * @param visible  the new value of the flag.
     * 
     * @see #isRangeGridlinesVisible()
     */
    public void setRangeGridlinesVisible(boolean visible) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.rangeGridlinesVisible != visible) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[17]++;
            this.rangeGridlinesVisible = visible;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[46]++;
            notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[47]++;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[18]++;}
    }

    /**
     * Returns the stroke for the grid lines (if any) plotted against the range
     * axis.
     *
     * @return The stroke (never <code>null</code>).
     * 
     * @see #setRangeGridlineStroke(Stroke)
     */
    public Stroke getRangeGridlineStroke() {
        return this.rangeGridlineStroke;
    }

    /**
     * Sets the stroke for the grid lines plotted against the range axis and 
     * sends a {@link PlotChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> permitted).
     * 
     * @see #getRangeGridlineStroke()
     */
    public void setRangeGridlineStroke(Stroke stroke) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[19]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[20]++;}
        this.rangeGridlineStroke = stroke;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[49]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[50]++;
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
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[51]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[21]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[22]++;}
        this.rangeGridlinePaint = paint;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[52]++;
        notifyListeners(new PlotChangeEvent(this));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[53]++;
    }

    /**
     * Draws the fast scatter plot on a Java 2D graphics device (such as the 
     * screen or a printer).
     *
     * @param g2  the graphics device.
     * @param area   the area within which the plot (including axis labels)
     *                   should be drawn.
     * @param anchor  the anchor point (<code>null</code> permitted).
     * @param parentState  the state from the parent plot (ignored).
     * @param info  collects chart drawing information (<code>null</code> 
     *              permitted).
     */
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor,
                     PlotState parentState,
                     PlotRenderingInfo info) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[54]++;
int CodeCoverConditionCoverageHelper_C12;

        // set up info collection...
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[23]++;
            info.setPlotArea(area);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[55]++;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[24]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[56]++;

        // adjust the drawing area for plot insets (if any)...
        RectangleInsets insets = getInsets();
        insets.trim(area);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[57]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[58]++;

        AxisSpace space = new AxisSpace();
        space = this.domainAxis.reserveSpace(g2, this, area, 
                RectangleEdge.BOTTOM, space);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[59]++;
        space = this.rangeAxis.reserveSpace(g2, this, area, RectangleEdge.LEFT, 
                space);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[60]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[61]++;
        Rectangle2D dataArea = space.shrink(area, null);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[62]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[25]++;
            info.setDataArea(dataArea);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[63]++;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[26]++;}

        // draw the plot background and axes...
        drawBackground(g2, dataArea);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[64]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[65]++;

        AxisState domainAxisState = this.domainAxis.draw(g2, 
                dataArea.getMaxY(), area, dataArea, RectangleEdge.BOTTOM, info);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[66]++;
        AxisState rangeAxisState = this.rangeAxis.draw(g2, dataArea.getMinX(), 
                area, dataArea, RectangleEdge.LEFT, info);
        drawDomainGridlines(g2, dataArea, domainAxisState.getTicks());
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[67]++;
        drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[68]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[69]++;
        
        Shape originalClip = g2.getClip();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[70]++;
        Composite originalComposite = g2.getComposite();

        g2.clip(dataArea);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[71]++;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
                getForegroundAlpha()));
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[72]++;

        render(g2, dataArea, info, null);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[73]++;

        g2.setClip(originalClip);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[74]++;
        g2.setComposite(originalComposite);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[75]++;
        drawOutline(g2, dataArea);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[76]++;

    }

    /**
     * Draws a representation of the data within the dataArea region.  The 
     * <code>info</code> and <code>crosshairState</code> arguments may be 
     * <code>null</code>.
     *
     * @param g2  the graphics device.
     * @param dataArea  the region in which the data is to be drawn.
     * @param info  an optional object for collection dimension information.
     * @param crosshairState  collects crosshair information (<code>null</code>
     *                        permitted).
     */
    public void render(Graphics2D g2, Rectangle2D dataArea,
                       PlotRenderingInfo info, CrosshairState crosshairState) {
    
 
        //long start = System.currentTimeMillis();
        //System.out.println("Start: " + start);
        g2.setPaint(this.paint);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[77]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[78]++;
int CodeCoverConditionCoverageHelper_C14;

        // if the axes use a linear scale, you can uncomment the code below and
        // switch to the alternative transX/transY calculation inside the loop 
        // that follows - it is a little bit faster then.
        // 
        // int xx = (int) dataArea.getMinX();
        // int ww = (int) dataArea.getWidth();
        // int yy = (int) dataArea.getMaxY();
        // int hh = (int) dataArea.getHeight();
        // double domainMin = this.domainAxis.getLowerBound();
        // double domainLength = this.domainAxis.getUpperBound() - domainMin;
        // double rangeMin = this.rangeAxis.getLowerBound();
        // double rangeLength = this.rangeAxis.getUpperBound() - rangeMin;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((this.data != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[27]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[79]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[1]++;


int CodeCoverConditionCoverageHelper_C15;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((i < this.data[0].length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[1]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[2]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[3]++;
}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[80]++;
                float x = this.data[0][i];
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[81]++;
                float y = this.data[1][i];
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[82]++;
                
                //int transX = (int) (xx + ww * (x - domainMin) / domainLength);
                //int transY = (int) (yy - hh * (y - rangeMin) / rangeLength); 
                int transX = (int) this.domainAxis.valueToJava2D(x, dataArea, 
                        RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[83]++;
                int transY = (int) this.rangeAxis.valueToJava2D(y, dataArea, 
                        RectangleEdge.LEFT);
                g2.fillRect(transX, transY, 1, 1);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[84]++;
            }

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[28]++;}
        //long finish = System.currentTimeMillis();
        //System.out.println("Finish: " + finish);
        //System.out.println("Time: " + (finish - start));

    }

    /**
     * Draws the gridlines for the plot, if they are visible.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param ticks  the ticks.
     */
    protected void drawDomainGridlines(Graphics2D g2, Rectangle2D dataArea, 
                                       List ticks) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[85]++;
int CodeCoverConditionCoverageHelper_C16;

        // draw the domain grid lines, if the flag says they're visible...
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isDomainGridlinesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[29]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[86]++;
            Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[87]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[4]++;


int CodeCoverConditionCoverageHelper_C17;
            while ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[4]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[5]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[6]++;
}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[88]++;
                ValueTick tick = (ValueTick) iterator.next();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[89]++;
                double v = this.domainAxis.valueToJava2D(tick.getValue(), 
                        dataArea, RectangleEdge.BOTTOM);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[90]++;
                Line2D line = new Line2D.Double(v, dataArea.getMinY(), v, 
                        dataArea.getMaxY());
                g2.setPaint(getDomainGridlinePaint());
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[91]++;
                g2.setStroke(getDomainGridlineStroke());
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[92]++;
                g2.draw(line);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[93]++;                
            }

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[30]++;}
    }
    
    /**
     * Draws the gridlines for the plot, if they are visible.
     *
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param ticks  the ticks.
     */
    protected void drawRangeGridlines(Graphics2D g2, Rectangle2D dataArea, 
                                      List ticks) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[94]++;
int CodeCoverConditionCoverageHelper_C18;

        // draw the range grid lines, if the flag says they're visible...
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isRangeGridlinesVisible()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[31]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[95]++;
            Iterator iterator = ticks.iterator();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[96]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[7]++;


int CodeCoverConditionCoverageHelper_C19;
            while ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[7]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[8]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[9]++;
}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[97]++;
                ValueTick tick = (ValueTick) iterator.next();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[98]++;
                double v = this.rangeAxis.valueToJava2D(tick.getValue(), 
                        dataArea, RectangleEdge.LEFT);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[99]++;
                Line2D line = new Line2D.Double(dataArea.getMinX(), v, 
                        dataArea.getMaxX(), v);
                g2.setPaint(getRangeGridlinePaint());
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[100]++;
                g2.setStroke(getRangeGridlineStroke());
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[101]++;
                g2.draw(line);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[102]++;                
            }

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[32]++;}

    }

    /**
     * Returns the range of data values to be plotted along the axis, or
     * <code>null</code> if the specified axis isn't the domain axis or the
     * range axis for the plot.
     *
     * @param axis  the axis (<code>null</code> permitted).
     *
     * @return The range (possibly <code>null</code>).
     */
    public Range getDataRange(ValueAxis axis) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[103]++;
        Range result = null;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[104]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((axis == this.domainAxis) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[33]++;
            result = this.xDataRange;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[105]++;

        }
        else {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[34]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[106]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((axis == this.rangeAxis) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[35]++;
            result = this.yDataRange;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[107]++;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[36]++;}
}
        return result;
    }

    /**
     * Calculates the X data range.
     *
     * @param data  the data (<code>null</code> permitted).
     *
     * @return The range.
     */
    private Range calculateXDataRange(float[][] data) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[108]++;
        
        Range result = null;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[109]++;
int CodeCoverConditionCoverageHelper_C22;
        
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[37]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[110]++;
            float lowest = Float.POSITIVE_INFINITY;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[111]++;
            float highest = Float.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[112]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[10]++;


int CodeCoverConditionCoverageHelper_C23;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((i < data[0].length) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[10]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[11]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[12]++;
}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[113]++;
                float v = data[0][i];
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[114]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((v < lowest) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[39]++;
                    lowest = v;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[115]++;

                } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[40]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[116]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((v > highest) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[41]++;
                    highest = v;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[117]++;

                } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[42]++;}
            }
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[118]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((lowest <= highest) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[43]++;
                result = new Range(lowest, highest);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[119]++;

            } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[38]++;}
        
        return result;
        
    }

    /**
     * Calculates the Y data range.
     *
     * @param data  the data (<code>null</code> permitted).
     *
     * @return The range.
     */
    private Range calculateYDataRange(float[][] data) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[120]++;
        
        Range result = null;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[121]++;
int CodeCoverConditionCoverageHelper_C27;
        
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((data != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[45]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[122]++;
            float lowest = Float.POSITIVE_INFINITY;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[123]++;
            float highest = Float.NEGATIVE_INFINITY;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[124]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[13]++;


int CodeCoverConditionCoverageHelper_C28;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((i < data[0].length) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[13]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[14]--;
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.loops[15]++;
}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[125]++;
                float v = data[1][i];
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[126]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((v < lowest) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[47]++;
                    lowest = v;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[127]++;

                } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[48]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[128]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((v > highest) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[49]++;
                    highest = v;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[129]++;

                } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[50]++;}
            }
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[130]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((lowest <= highest) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[51]++;
                result = new Range(lowest, highest);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[131]++;

            } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[52]++;}

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[46]++;}
        return result;
        
    }

    /**
     * Multiplies the range on the domain axis by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info.
     * @param source  the source point.
     */
    public void zoomDomainAxes(double factor, PlotRenderingInfo info, 
                               Point2D source) {
        this.domainAxis.resizeRange(factor);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[132]++;
    }
    
    /**
     * Multiplies the range on the domain axis by the specified factor.
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
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[133]++;
int CodeCoverConditionCoverageHelper_C32;
                
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((useAnchor) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[53]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[134]++;
            // get the source coordinate - this plot has always a VERTICAL
            // orientation
            double sourceX = source.getX();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[135]++;
            double anchorX = this.domainAxis.java2DToValue(sourceX, 
                    info.getDataArea(), RectangleEdge.BOTTOM);
            this.domainAxis.resizeRange(factor, anchorX);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[136]++;

        }
        else {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[54]++;
            this.domainAxis.resizeRange(factor);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[137]++;
        }
        
    }

    /**
     * Zooms in on the domain axes.
     * 
     * @param lowerPercent  the new lower bound as a percentage of the current 
     *                      range.
     * @param upperPercent  the new upper bound as a percentage of the current
     *                      range.
     * @param info  the plot rendering info.
     * @param source  the source point.
     */
    public void zoomDomainAxes(double lowerPercent, double upperPercent, 
                               PlotRenderingInfo info, Point2D source) {
        this.domainAxis.zoomRange(lowerPercent, upperPercent);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[138]++;
    }

    /**
     * Multiplies the range on the range axis/axes by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info.
     * @param source  the source point.
     */
    public void zoomRangeAxes(double factor,
                              PlotRenderingInfo info, Point2D source) {
        this.rangeAxis.resizeRange(factor);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[139]++;
    }

    /**
     * Multiplies the range on the range axis by the specified factor.
     *
     * @param factor  the zoom factor.
     * @param info  the plot rendering info.
     * @param source  the source point (in Java2D space).
     * @param useAnchor  use source point as zoom anchor?
     * 
     * @see #zoomDomainAxes(double, PlotRenderingInfo, Point2D, boolean)
     * 
     * @since 1.0.7
     */
    public void zoomRangeAxes(double factor, PlotRenderingInfo info,
                              Point2D source, boolean useAnchor) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[140]++;
int CodeCoverConditionCoverageHelper_C33;
                
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((useAnchor) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[55]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[141]++;
            // get the source coordinate - this plot has always a VERTICAL
            // orientation
            double sourceX = source.getX();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[142]++;
            double anchorX = this.rangeAxis.java2DToValue(sourceX, 
                    info.getDataArea(), RectangleEdge.LEFT);
            this.rangeAxis.resizeRange(factor, anchorX);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[143]++;

        }
        else {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[56]++;
            this.rangeAxis.resizeRange(factor);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[144]++;
        }
        
    }
    
    /**
     * Zooms in on the range axes.
     * 
     * @param lowerPercent  the new lower bound as a percentage of the current 
     *                      range.
     * @param upperPercent  the new upper bound as a percentage of the current 
     *                      range.
     * @param info  the plot rendering info.
     * @param source  the source point.
     */
    public void zoomRangeAxes(double lowerPercent, double upperPercent,
                              PlotRenderingInfo info, Point2D source) {
        this.rangeAxis.zoomRange(lowerPercent, upperPercent);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[145]++;
    }

    /**
     * Returns <code>true</code>.
     * 
     * @return A boolean.
     */
    public boolean isDomainZoomable() {
        return true;
    }
    
    /**
     * Returns <code>true</code>.
     * 
     * @return A boolean.
     */
    public boolean isRangeZoomable() {
        return true;
    }

    /**
     * Tests an object for equality with this instance.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[146]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[57]++;
            return true;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[58]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[147]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[59]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[60]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[148]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((obj instanceof FastScatterPlot) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[62]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[149]++;
        FastScatterPlot that = (FastScatterPlot) obj;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[150]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((ArrayUtilities.equal(this.data, that.data)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[63]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[64]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[151]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.domainAxis, that.domainAxis)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[65]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[66]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[152]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rangeAxis, that.rangeAxis)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[68]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[153]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.paint, that.paint)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[70]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[154]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this.domainGridlinesVisible != that.domainGridlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[72]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[155]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.domainGridlinePaint, 
                that.domainGridlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[73]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[74]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[156]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.domainGridlineStroke, 
                that.domainGridlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[75]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[76]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[157]++;
int CodeCoverConditionCoverageHelper_C44;  
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((!this.rangeGridlinesVisible == that.rangeGridlinesVisible) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[77]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[78]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[158]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.rangeGridlinePaint, 
                that.rangeGridlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[79]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[80]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[159]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.rangeGridlineStroke, 
                that.rangeGridlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[81]++;
            return false;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[82]++;}              
        return true;
    }
    
    /**
     * Returns a clone of the plot.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if some component of the plot does 
     *                                    not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[160]++;
    
        FastScatterPlot clone = (FastScatterPlot) super.clone();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[161]++;
int CodeCoverConditionCoverageHelper_C47;    
        
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.data != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[83]++;
            clone.data = ArrayUtilities.clone(this.data);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[162]++;
    
        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[84]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[163]++;
int CodeCoverConditionCoverageHelper_C48;
        
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((this.domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[85]++;
            clone.domainAxis = (ValueAxis) this.domainAxis.clone();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[164]++;
            clone.domainAxis.setPlot(clone);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[165]++;
            clone.domainAxis.addChangeListener(clone);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[166]++;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[86]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[167]++;
int CodeCoverConditionCoverageHelper_C49;
        
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((this.rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[87]++;
            clone.rangeAxis = (ValueAxis) this.rangeAxis.clone();
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[168]++;
            clone.rangeAxis.setPlot(clone);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[169]++;
            clone.rangeAxis.addChangeListener(clone);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[170]++;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[88]++;}
            
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
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[171]++;
        SerialUtilities.writePaint(this.paint, stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[172]++;
        SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[173]++;
        SerialUtilities.writePaint(this.domainGridlinePaint, stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[174]++;
        SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[175]++;
        SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[176]++;
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
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[177]++;

        this.paint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[178]++;
        this.domainGridlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[179]++;
        this.domainGridlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[180]++;

        this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[181]++;
        this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[182]++;
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[183]++;
int CodeCoverConditionCoverageHelper_C50;

        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((this.domainAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[89]++;
            this.domainAxis.addChangeListener(this);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[184]++;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[90]++;}
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[185]++;
int CodeCoverConditionCoverageHelper_C51;

        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((this.rangeAxis != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[91]++;
            this.rangeAxis.addChangeListener(this);
CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.statements[186]++;

        } else {
  CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35.branches[92]++;}
    }
    
}

class CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35 ());
  }
    public static long[] statements = new long[187];
    public static long[] branches = new long[93];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[52];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.FastScatterPlot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 51; i++) {
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

  public CodeCoverCoverageCounter$87yqi6jsrethnayv3anuxauz9d8dr35 () {
    super("org.jfree.chart.plot.FastScatterPlot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 186; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 92; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 51; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.FastScatterPlot.java");
      for (int i = 1; i <= 186; i++) {
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
    for (int i = 1; i <= 51; i++) {
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

