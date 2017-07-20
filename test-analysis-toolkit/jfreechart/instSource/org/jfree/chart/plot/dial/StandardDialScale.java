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
 * ----------------------
 * StandardDialScale.java
 * ----------------------
 * (C) Copyright 2006-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Nov-2006 : Version 1 (DG);
 * 17-Nov-2006 : Added flags for tick label visibility (DG);
 * 24-Oct-2007 : Added tick label formatter (DG);
 * 19-Nov-2007 : Added some missing accessor methods (DG);
 * 
 */

package org.jfree.chart.plot.dial;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.util.PaintUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A scale for a {@link DialPlot}.
 * 
 * @since 1.0.7
 */
public class StandardDialScale extends AbstractDialLayer implements DialScale, 
        Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.ping();
  }

    
    /** For serialization. */
    static final long serialVersionUID = 3715644629665918516L;
  static {
    CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[1]++;
  }
    
    /** The minimum data value for the scale. */
    private double lowerBound;
    
    /** The maximum data value for the scale. */
    private double upperBound;
    
    /** 
     * The start angle for the scale display, in degrees (using the same
     * encoding as Arc2D). 
     */
    private double startAngle;
    
    /** The extent of the scale display. */
    private double extent;
    
    /** 
     * The factor (in the range 0.0 to 1.0) that determines the outside limit
     * of the tick marks.
     */
    private double tickRadius;

    /**
     * The increment (in data units) between major tick marks. 
     */
    private double majorTickIncrement;

    /**
     * The factor that is subtracted from the tickRadius to determine the
     * inner point of the major ticks.
     */
    private double majorTickLength;    
    
    /**
     * The paint to use for major tick marks.  This field is transient because
     * it requires special handling for serialization.
     */
    private transient Paint majorTickPaint;
    
    /**
     * The stroke to use for major tick marks.  This field is transient because
     * it requires special handling for serialization.
     */
    private transient Stroke majorTickStroke;

    /**
     * The number of minor ticks between each major tick.
     */
    private int minorTickCount;
    
    /**
     * The factor that is subtracted from the tickRadius to determine the
     * inner point of the minor ticks.
     */
    private double minorTickLength;
    
    /**
     * The paint to use for minor tick marks.  This field is transient because
     * it requires special handling for serialization.
     */
    private transient Paint minorTickPaint;
    
    /**
     * The stroke to use for minor tick marks.  This field is transient because
     * it requires special handling for serialization.
     */
    private transient Stroke minorTickStroke;

    /**
     * The tick label offset.
     */
    private double tickLabelOffset;
    
    /** 
     * The tick label font.
     */
    private Font tickLabelFont;
    
    /** 
     * A flag that controls whether or not the tick labels are 
     * displayed. 
     */
    private boolean tickLabelsVisible;
    
    /**
     * The number formatter for the tick labels.
     */
    private NumberFormat tickLabelFormatter;
    
    /**
     * A flag that controls whether or not the first tick label is
     * displayed.
     */
    private boolean firstTickLabelVisible;
    
    /**
     * The tick label paint.  This field is transient because it requires 
     * special handling for serialization.
     */
    private transient Paint tickLabelPaint;
    
    /** 
     * Creates a new instance of DialScale.
     */
    public StandardDialScale() {
        this(0.0, 100.0, 175, -170, 10.0, 4);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[2]++;
    }
    
    /**
     * Creates a new instance.
     * 
     * @param lowerBound  the lower bound of the scale.
     * @param upperBound  the upper bound of the scale.
     * @param startAngle  the start angle (in degrees, using the same 
     *     orientation as Java's <code>Arc2D</code> class).
     * @param extent  the extent (in degrees, counter-clockwise).
     * @param majorTickIncrement  the interval between major tick marks
     * @param minorTickCount  the number of minor ticks between major tick
     *          marks.
     */
    public StandardDialScale(double lowerBound, double upperBound, 
            double startAngle, double extent, double majorTickIncrement, 
            int minorTickCount) {
        this.startAngle = startAngle;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[3]++;
        this.extent = extent;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[4]++;
        this.lowerBound = lowerBound;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[5]++;
        this.upperBound = upperBound;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[6]++;
        this.tickRadius = 0.70;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[7]++;
        this.tickLabelsVisible = true;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[8]++;
        this.tickLabelFormatter = new DecimalFormat("0.0");
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[9]++;
        this.firstTickLabelVisible = true;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[10]++;
        this.tickLabelFont = new Font("Dialog", Font.BOLD, 16);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[11]++;
        this.tickLabelPaint = Color.blue;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[12]++;
        this.tickLabelOffset = 0.10;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[13]++;
        this.majorTickIncrement = majorTickIncrement;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[14]++;
        this.majorTickLength = 0.04;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[15]++;
        this.majorTickPaint = Color.black;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[16]++;
        this.majorTickStroke = new BasicStroke(3.0f);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[17]++;
        this.minorTickCount = minorTickCount;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[18]++;
        this.minorTickLength = 0.02;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[19]++;
        this.minorTickPaint = Color.black;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[20]++;
        this.minorTickStroke = new BasicStroke(1.0f);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[21]++;
    }
    
    /**
     * Returns the lower bound for the scale.
     * 
     * @return The lower bound for the scale.
     * 
     * @see #setLowerBound(double)
     * 
     * @since 1.0.8
     */
    public double getLowerBound() {
        return this.lowerBound;
    }
    
    /**
     * Sets the lower bound for the scale and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param lower  the lower bound.
     * 
     * @see #getLowerBound()
     * 
     * @since 1.0.8
     */
    public void setLowerBound(double lower) {
        this.lowerBound = lower;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[22]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[23]++;        
    }
    
    /**
     * Returns the upper bound for the scale.
     * 
     * @return The upper bound for the scale.
     * 
     * @see #setUpperBound(double)
     * 
     * @since 1.0.8
     */
    public double getUpperBound() {
        return this.upperBound;
    }
    
    /**
     * Sets the upper bound for the scale and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param upper  the upper bound.
     * 
     * @see #getUpperBound()
     * 
     * @since 1.0.8
     */
    public void setUpperBound(double upper) {
        this.upperBound = upper;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[24]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[25]++;        
    }

    /**
     * Returns the start angle for the scale (in degrees using the same 
     * orientation as Java's <code>Arc2D</code> class).
     * 
     * @return The start angle.
     * 
     * @see #setStartAngle(double)
     */
    public double getStartAngle() {
        return this.startAngle;
    }
    
    /**
     * Sets the start angle for the scale and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param angle  the angle (in degrees).
     * 
     * @see #getStartAngle()
     */
    public void setStartAngle(double angle) {
        this.startAngle = angle;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[26]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[27]++;
    }
    
    /**
     * Returns the extent.
     * 
     * @return The extent.
     * 
     * @see #setExtent(double)
     */
    public double getExtent() {
        return this.extent;
    }
    
    /**
     * Sets the extent and sends a {@link DialLayerChangeEvent} to all 
     * registered listeners.
     * 
     * @param extent  the extent.
     * 
     * @see #getExtent()
     */
    public void setExtent(double extent) {
        this.extent = extent;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[28]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[29]++;
    }
    
    /**
     * Returns the radius (as a percentage of the maximum space available) of
     * the outer limit of the tick marks.
     *
     * @return The tick radius.
     *
     * @see #setTickRadius(double)
     */
    public double getTickRadius() {
        return this.tickRadius;
    }
    
    /**
     * Sets the tick radius and sends a {@link DialLayerChangeEvent} to all
     * registered listeners.
     *
     * @param radius  the radius.
     *
     * @see #getTickRadius()
     */
    public void setTickRadius(double radius) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[30]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((radius <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[1]++;
            throw new IllegalArgumentException(
                    "The 'radius' must be positive.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[2]++;}
        this.tickRadius = radius;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[31]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[32]++;
    }
    
    /**
     * Returns the increment (in data units) between major tick labels.
     *
     * @return The increment between major tick labels.
     *
     * @see #setMajorTickIncrement(double)
     */
    public double getMajorTickIncrement() {
        return this.majorTickIncrement;
    }
    
    /**
     * Sets the increment (in data units) between major tick labels and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param increment  the increment.
     *
     * @see #getMajorTickIncrement()
     */
    public void setMajorTickIncrement(double increment) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[33]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((increment <= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[3]++;
            throw new IllegalArgumentException(
                    "The 'increment' must be positive.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[4]++;}
        this.majorTickIncrement = increment;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[34]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[35]++;
    }
    
    /**
     * Returns the length factor for the major tick marks.  The value is
     * subtracted from the tick radius to determine the inner starting point
     * for the tick marks.
     *
     * @return The length factor.
     *
     * @see #setMajorTickLength(double)
     */
    public double getMajorTickLength() {
        return this.majorTickLength;
    }
    
    /**
     * Sets the length factor for the major tick marks and sends a
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param length  the length.
     *
     * @see #getMajorTickLength()
     */
    public void setMajorTickLength(double length) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[36]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((length < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[5]++;
            throw new IllegalArgumentException("Negative 'length' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[6]++;}
        this.majorTickLength = length;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[37]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[38]++;
    }
    
    /**
     * Returns the major tick paint.
     *
     * @return The major tick paint (never <code>null</code>).
     *
     * @see #setMajorTickPaint(Paint)
     */
    public Paint getMajorTickPaint() {
        return this.majorTickPaint;
    }
    
    /**
     * Sets the major tick paint and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     *
     * @see #getMajorTickPaint()
     */
    public void setMajorTickPaint(Paint paint) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[39]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[7]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[8]++;}
        this.majorTickPaint = paint;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[40]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[41]++;
    }
    
    /**
     * Returns the stroke used to draw the major tick marks.
     *
     * @return The stroke (never <code>null</code>).
     *
     * @see #setMajorTickStroke(Stroke)
     */
    public Stroke getMajorTickStroke() {
        return this.majorTickStroke;
    }
    
    /**
     * Sets the stroke used to draw the major tick marks and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param stroke  the stroke (<code>null</code> not permitted).
     *
     * @see #getMajorTickStroke()
     */
    public void setMajorTickStroke(Stroke stroke) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[42]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[9]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[10]++;}
        this.majorTickStroke = stroke;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[43]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[44]++;
    }
    
    /**
     * Returns the number of minor tick marks between major tick marks.
     *
     * @return The number of minor tick marks between major tick marks.
     *
     * @see #setMinorTickCount(int)
     */
    public int getMinorTickCount() {
        return this.minorTickCount;
    }
    
    /**
     * Sets the number of minor tick marks between major tick marks and sends 
     * a {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param count  the count.
     *
     * @see #getMinorTickCount()
     */
    public void setMinorTickCount(int count) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[45]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((count < 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[11]++;
            throw new IllegalArgumentException(
                    "The 'count' cannot be negative.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[12]++;}
        this.minorTickCount = count;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[46]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[47]++;
    }
    
    /**
     * Returns the length factor for the minor tick marks.  The value is
     * subtracted from the tick radius to determine the inner starting point
     * for the tick marks.
     *
     * @return The length factor.
     *
     * @see #setMinorTickLength(double)
     */
    public double getMinorTickLength() {
        return this.minorTickLength;
    }
    
    /**
     * Sets the length factor for the minor tick marks and sends 
     * a {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param length  the length.
     *
     * @see #getMinorTickLength()
     */
    public void setMinorTickLength(double length) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[48]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((length < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[13]++; 
            throw new IllegalArgumentException("Negative 'length' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[14]++;}
        this.minorTickLength = length;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[49]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[50]++;
    }
    
    /**
     * Returns the paint used to draw the minor tick marks.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setMinorTickPaint(Paint)
     */
    public Paint getMinorTickPaint() {
        return this.minorTickPaint;
    }
    
    /**
     * Sets the paint used to draw the minor tick marks and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     * 
     * @see #getMinorTickPaint()
     */
    public void setMinorTickPaint(Paint paint) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[51]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[15]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[16]++;}
        this.minorTickPaint = paint;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[52]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[53]++;        
    }
    
    /**
     * Returns the stroke used to draw the minor tick marks.
     * 
     * @return The paint (never <code>null</code>).
     * 
     * @see #setMinorTickStroke(Stroke)
     * 
     * @since 1.0.8
     */
    public Stroke getMinorTickStroke() {
        return this.minorTickStroke;
    }
    
    /**
     * Sets the stroke used to draw the minor tick marks and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     * 
     * @see #getMinorTickStroke()
     * 
     * @since 1.0.8
     */
    public void setMinorTickStroke(Stroke stroke) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[54]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[17]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[18]++;}
        this.minorTickStroke = stroke;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[55]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[56]++;        
    }
    
    /**
     * Returns the tick label offset.
     *
     * @return The tick label offset.
     *
     * @see #setTickLabelOffset(double)
     */
    public double getTickLabelOffset() {
        return this.tickLabelOffset;
    }
    
    /**
     * Sets the tick label offset and sends a {@link DialLayerChangeEvent} to 
     * all registered listeners.
     *
     * @param offset  the offset.
     *
     * @see #getTickLabelOffset()
     */
    public void setTickLabelOffset(double offset) {
        this.tickLabelOffset = offset;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[57]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[58]++;
    }
    
    /**
     * Returns the font used to draw the tick labels.
     *
     * @return The font (never <code>null</code>).
     *
     * @see #setTickLabelFont(Font)
     */
    public Font getTickLabelFont() {
        return this.tickLabelFont;
    }
    
    /**
     * Sets the font used to display the tick labels and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param font  the font (<code>null</code> not permitted).
     *
     * @see #getTickLabelFont()
     */
    public void setTickLabelFont(Font font) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[59]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((font == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[19]++;
            throw new IllegalArgumentException("Null 'font' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[20]++;}
        this.tickLabelFont = font;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[60]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[61]++;
    }
    
    /**
     * Returns the paint used to draw the tick labels.
     *
     * @return The paint (<code>null</code> not permitted).
     * 
     * @see #setTickLabelPaint(Paint)
     */
    public Paint getTickLabelPaint() {
        return this.tickLabelPaint;
    }
    
    /**
     * Sets the paint used to draw the tick labels and sends a 
     * {@link DialLayerChangeEvent} to all registered listeners.
     *
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setTickLabelPaint(Paint paint) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[62]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[21]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[22]++;}
        this.tickLabelPaint = paint;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[63]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[64]++;
    }
    
    /**
     * Returns <code>true</code> if the tick labels should be displayed,
     * and <code>false</code> otherwise.
     * 
     * @return A boolean.
     * 
     * @see #setTickLabelsVisible(boolean)
     */
    public boolean getTickLabelsVisible() {
        return this.tickLabelsVisible;
    }
    
    /**
     * Sets the flag that controls whether or not the tick labels are
     * displayed, and sends a {@link DialLayerChangeEvent} to all registered
     * listeners.
     * 
     * @param visible  the new flag value.
     * 
     * @see #getTickLabelsVisible()
     */
    public void setTickLabelsVisible(boolean visible) {
        this.tickLabelsVisible = visible;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[65]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[66]++;
    }
    
    /**
     * Returns the number formatter used to convert the tick label values to
     * strings.
     * 
     * @return The formatter (never <code>null</code>).
     * 
     * @see #setTickLabelFormatter(NumberFormat)
     */
    public NumberFormat getTickLabelFormatter() {
        return this.tickLabelFormatter;
    }
    
    /**
     * Sets the number formatter used to convert the tick label values to 
     * strings, and sends a {@link DialLayerChangeEvent} to all registered
     * listeners.
     * 
     * @param formatter  the formatter (<code>null</code> not permitted).
     * 
     * @see #getTickLabelFormatter()
     */
    public void setTickLabelFormatter(NumberFormat formatter) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[67]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[23]++;
            throw new IllegalArgumentException("Null 'formatter' argument.");

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[24]++;}
        this.tickLabelFormatter = formatter;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[68]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[69]++;        
    }
    
    /**
     * Returns a flag that controls whether or not the first tick label is
     * visible.
     * 
     * @return A boolean.
     * 
     * @see #setFirstTickLabelVisible(boolean)
     */
    public boolean getFirstTickLabelVisible() {
        return this.firstTickLabelVisible;
    }
    
    /**
     * Sets a flag that controls whether or not the first tick label is 
     * visible, and sends a {@link DialLayerChangeEvent} to all registered
     * listeners.
     * 
     * @param visible  the new flag value.
     * 
     * @see #getFirstTickLabelVisible()
     */
    public void setFirstTickLabelVisible(boolean visible) {
        this.firstTickLabelVisible = visible;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[70]++;
        notifyListeners(new DialLayerChangeEvent(this));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[71]++;
    }
    
    /**
     * Returns <code>true</code> to indicate that this layer should be 
     * clipped within the dial window. 
     * 
     * @return <code>true</code>.
     */
    public boolean isClippedToWindow() {
        return true;
    }
    
    /**
     * Draws the scale on the dial plot.
     *
     * @param g2  the graphics target (<code>null</code> not permitted).
     * @param plot  the dial plot (<code>null</code> not permitted).
     * @param frame  the reference frame that is used to construct the
     *     geometry of the plot (<code>null</code> not permitted).
     * @param view  the visible part of the plot (<code>null</code> not 
     *     permitted).
     */
    public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, 
            Rectangle2D view) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[72]++;
        
        Rectangle2D arcRect = DialPlot.rectangleByRadius(frame, 
                this.tickRadius, this.tickRadius);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[73]++;
        Rectangle2D arcRectMajor = DialPlot.rectangleByRadius(frame, 
                this.tickRadius - this.majorTickLength, 
                this.tickRadius - this.majorTickLength);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[74]++;
        Rectangle2D arcRectMinor = arcRect;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[75]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((this.minorTickCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((this.minorTickLength > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[25]++;
            arcRectMinor = DialPlot.rectangleByRadius(frame, 
                    this.tickRadius - this.minorTickLength, 
                    this.tickRadius - this.minorTickLength);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[76]++;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[26]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[77]++;
        Rectangle2D arcRectForLabels = DialPlot.rectangleByRadius(frame, 
                this.tickRadius - this.tickLabelOffset, 
                this.tickRadius - this.tickLabelOffset);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[78]++;
        
        boolean firstLabel = true;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[79]++;
        
        Arc2D arc = new Arc2D.Double();
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[80]++;
        Line2D workingLine = new Line2D.Double();
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[81]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[1]++;


int CodeCoverConditionCoverageHelper_C14;
        for (double v = this.lowerBound;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((v <= this.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); 
                v += this.majorTickIncrement) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[1]--;
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[2]--;
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[3]++;
}
            arc.setArc(arcRect, this.startAngle, valueToAngle(v) 
                    - this.startAngle, Arc2D.OPEN);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[82]++;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[83]++;
            Point2D pt0 = arc.getEndPoint();
            arc.setArc(arcRectMajor, this.startAngle, valueToAngle(v) 
                    - this.startAngle, Arc2D.OPEN);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[84]++;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[85]++;
            Point2D pt1 = arc.getEndPoint();
            g2.setPaint(this.majorTickPaint);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[86]++;
            g2.setStroke(this.majorTickStroke);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[87]++;
            workingLine.setLine(pt0, pt1);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[88]++;
            g2.draw(workingLine);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[89]++;
            arc.setArc(arcRectForLabels, this.startAngle, valueToAngle(v) 
                    - this.startAngle, Arc2D.OPEN);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[90]++;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[91]++;
            Point2D pt2 = arc.getEndPoint();
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[92]++;
int CodeCoverConditionCoverageHelper_C15;
            
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((this.tickLabelsVisible) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[27]++;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[93]++;
int CodeCoverConditionCoverageHelper_C16;
                if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((firstLabel) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((this.firstTickLabelVisible) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[29]++;
                    g2.setFont(this.tickLabelFont);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[94]++;
                    TextUtilities.drawAlignedString(
                            this.tickLabelFormatter.format(v), g2, 
                            (float) pt2.getX(), (float) pt2.getY(), 
                            TextAnchor.CENTER);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[95]++;

                } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[30]++;}

            } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[28]++;}
            firstLabel = false;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[96]++;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[97]++;
int CodeCoverConditionCoverageHelper_C17;
            
            // now do the minor tick marks
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((this.minorTickCount > 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.minorTickLength > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[31]++;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[98]++;
                double minorTickIncrement = this.majorTickIncrement 
                        / (this.minorTickCount + 1);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[99]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[4]++;


int CodeCoverConditionCoverageHelper_C18;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < this.minorTickCount) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[4]--;
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[5]--;
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.loops[6]++;
}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[100]++;
                    double vv = v + ((i + 1) * minorTickIncrement);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[101]++;
int CodeCoverConditionCoverageHelper_C19;
                    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((vv >= this.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[33]++;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[102]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[34]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[103]++;
                    double angle = valueToAngle(vv);
                   
                    arc.setArc(arcRect, this.startAngle, angle 
                            - this.startAngle, Arc2D.OPEN);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[104]++;
                    pt0 = arc.getEndPoint();
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[105]++;
                    arc.setArc(arcRectMinor, this.startAngle, angle 
                            - this.startAngle, Arc2D.OPEN);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[106]++;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[107]++;
                    Point2D pt3 = arc.getEndPoint();
                    g2.setStroke(this.minorTickStroke);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[108]++;
                    g2.setPaint(this.minorTickPaint);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[109]++;
                    workingLine.setLine(pt0, pt3);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[110]++;
                    g2.draw(workingLine);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[111]++;
                }

            } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[32]++;}
            
        }
    }
    
    /**
     * Converts a data value to an angle against this scale.
     *
     * @param value  the data value.
     *
     * @return The angle (in degrees, using the same specification as Java's
     *     Arc2D class).
     *     
     * @see #angleToValue(double)
     */
    public double valueToAngle(double value) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[112]++;
        double range = this.upperBound - this.lowerBound;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[113]++;
        double unit = this.extent / range;
        return this.startAngle + unit * (value - this.lowerBound);        
    }

    /** 
     * Converts the given angle to a data value, based on this scale.
     * 
     * @param angle  the angle.
     * 
     * @return The data value.
     * 
     * @see #valueToAngle(double)
     */
    public double angleToValue(double angle) {
        return Double.NaN;  // FIXME
    }

    /**
     * Tests this <code>StandardDialScale</code> for equality with an arbitrary
     * object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[114]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[35]++;
            return true;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[36]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[115]++;
int CodeCoverConditionCoverageHelper_C21;    
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((obj instanceof StandardDialScale) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[37]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[38]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[116]++;
        StandardDialScale that = (StandardDialScale) obj;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[117]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((this.lowerBound != that.lowerBound) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[39]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[40]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[118]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((this.upperBound != that.upperBound) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[41]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[42]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[119]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((this.startAngle != that.startAngle) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[43]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[44]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[120]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((this.extent != that.extent) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[45]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[46]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[121]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.tickRadius != that.tickRadius) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[47]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[48]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[122]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((this.majorTickIncrement != that.majorTickIncrement) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[49]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[50]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[123]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.majorTickLength != that.majorTickLength) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[51]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[52]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[124]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.majorTickPaint, that.majorTickPaint)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[53]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[54]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[125]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((this.majorTickStroke.equals(that.majorTickStroke)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[55]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[56]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[126]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.minorTickCount != that.minorTickCount) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[57]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[58]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[127]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.minorTickLength != that.minorTickLength) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[59]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[60]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[128]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.minorTickPaint, that.minorTickPaint)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[61]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[62]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[129]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.minorTickStroke.equals(that.minorTickStroke)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[63]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[64]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[130]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.tickLabelsVisible != that.tickLabelsVisible) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[65]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[66]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[131]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((this.tickLabelOffset != that.tickLabelOffset) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[67]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[68]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[132]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.tickLabelFont.equals(that.tickLabelFont)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[69]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[70]++;}
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[133]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[71]++;
            return false;

        } else {
  CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.branches[72]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[134]++;
        int result = 193;
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[135]++;
        // lowerBound
        long temp = Double.doubleToLongBits(this.lowerBound);
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[136]++;
        // upperBound
        temp = Double.doubleToLongBits(this.upperBound);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[137]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[138]++;
        // startAngle
        temp = Double.doubleToLongBits(this.startAngle);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[139]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[140]++;        
        // extent
        temp = Double.doubleToLongBits(this.extent);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[141]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[142]++;        
        // tickRadius
        temp = Double.doubleToLongBits(this.tickRadius);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[143]++;
        result = 37 * result + (int) (temp ^ (temp >>> 32));
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[144]++;        
        // majorTickIncrement
        // majorTickLength
        // majorTickPaint
        // majorTickStroke
        // minorTickCount
        // minorTickLength
        // minorTickPaint
        // minorTickStroke
        // tickLabelOffset
        // tickLabelFont
        // tickLabelsVisible
        // tickLabelFormatter
        // firstTickLabelsVisible
        return result; 
    }

    /**
     * Returns a clone of this instance.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if this instance is not cloneable.
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
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[145]++;
        SerialUtilities.writePaint(this.majorTickPaint, stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[146]++;
        SerialUtilities.writeStroke(this.majorTickStroke, stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[147]++;
        SerialUtilities.writePaint(this.minorTickPaint, stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[148]++;
        SerialUtilities.writeStroke(this.minorTickStroke, stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[149]++;
        SerialUtilities.writePaint(this.tickLabelPaint, stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[150]++;
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
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[151]++;
        this.majorTickPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[152]++;
        this.majorTickStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[153]++;
        this.minorTickPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[154]++;
        this.minorTickStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[155]++;
        this.tickLabelPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht.statements[156]++;
    }

}

class CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht ());
  }
    public static long[] statements = new long[157];
    public static long[] branches = new long[73];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[39];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.dial.StandardDialScale.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 38; i++) {
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

  public CodeCoverCoverageCounter$doyh2wigzhi2vfxttj6vw7p1y2dnjsguht () {
    super("org.jfree.chart.plot.dial.StandardDialScale.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 156; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 72; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.dial.StandardDialScale.java");
      for (int i = 1; i <= 156; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 72; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 38; i++) {
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

