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
 * ---------------------
 * CyclicNumberAxis.java
 * ---------------------
 * (C) Copyright 2003-2007, by Nicolas Brodu and Contributors.
 *
 * Original Author:  Nicolas Brodu;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 19-Nov-2003 : Initial import to JFreeChart from the JSynoptic project (NB);
 * 16-Mar-2004 : Added plotState to draw() method (DG);
 * 07-Apr-2004 : Modifed text bounds calculation (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets, removed redundant
 *               argument in selectAutoTickUnit() (DG);
 * 22-Apr-2005 : Renamed refreshHorizontalTicks() --> refreshTicksHorizontal
 *               (for consistency with other classes) and removed unused
 *               parameters (DG);
 * 08-Jun-2005 : Fixed equals() method to handle GradientPaint (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.List;

import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.Range;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
This class extends NumberAxis and handles cycling.
 
Traditional representation of data in the range x0..x1
<pre>
|-------------------------|
x0                       x1
</pre> 

Here, the range bounds are at the axis extremities.
With cyclic axis, however, the time is split in 
"cycles", or "time frames", or the same duration : the period.

A cycle axis cannot by definition handle a larger interval 
than the period : <pre>x1 - x0 >= period</pre>. Thus, at most a full 
period can be represented with such an axis.

The cycle bound is the number between x0 and x1 which marks 
the beginning of new time frame:
<pre>
|---------------------|----------------------------|
x0                   cb                           x1
<---previous cycle---><-------current cycle-------->
</pre>

It is actually a multiple of the period, plus optionally 
a start offset: <pre>cb = n * period + offset</pre>

Thus, by definition, two consecutive cycle bounds 
period apart, which is precisely why it is called a 
period.

The visual representation of a cyclic axis is like that:
<pre>
|----------------------------|---------------------|
cb                         x1|x0                  cb
<-------current cycle--------><---previous cycle--->
</pre>

The cycle bound is at the axis ends, then current 
cycle is shown, then the last cycle. When using 
dynamic data, the visual effect is the current cycle 
erases the last cycle as x grows. Then, the next cycle 
bound is reached, and the process starts over, erasing 
the previous cycle.

A Cyclic item renderer is provided to do exactly this.

 */
public class CyclicNumberAxis extends NumberAxis {
  static {
    CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.ping();
  }


    /** For serialization. */
    static final long serialVersionUID = -7514160997164582554L;
  static {
    CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[1]++;
  }

    /** The default axis line stroke. */
    public static Stroke DEFAULT_ADVANCE_LINE_STROKE = new BasicStroke(1.0f);
  static {
    CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[2]++;
  }
    
    /** The default axis line paint. */
    public static final Paint DEFAULT_ADVANCE_LINE_PAINT = Color.gray;
  static {
    CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[3]++;
  }
    
    /** The offset. */
    protected double offset;
    
    /** The period.*/
    protected double period;
    
    /** ??. */
    protected boolean boundMappedToLastCycle;
    
    /** A flag that controls whether or not the advance line is visible. */
    protected boolean advanceLineVisible;

    /** The advance line stroke. */
    protected transient Stroke advanceLineStroke = DEFAULT_ADVANCE_LINE_STROKE;
  {
    CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[4]++;
  }
    
    /** The advance line paint. */
    protected transient Paint advanceLinePaint;
    
    private transient boolean internalMarkerWhenTicksOverlap;
    private transient Tick internalMarkerCycleBoundTick;
    
    /** 
     * Creates a CycleNumberAxis with the given period.
     * 
     * @param period  the period.
     */
    public CyclicNumberAxis(double period) {
        this(period, 0.0);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[5]++;
    }

    /** 
     * Creates a CycleNumberAxis with the given period and offset.
     * 
     * @param period  the period.
     * @param offset  the offset.
     */
    public CyclicNumberAxis(double period, double offset) {
        this(period, offset, null);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[6]++;
    }

    /** 
     * Creates a named CycleNumberAxis with the given period.
     * 
     * @param period  the period.
     * @param label  the label.
     */
    public CyclicNumberAxis(double period, String label) {
        this(0, period, label);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[7]++;
    }
    
    /** 
     * Creates a named CycleNumberAxis with the given period and offset.
     * 
     * @param period  the period.
     * @param offset  the offset.
     * @param label  the label.
     */
    public CyclicNumberAxis(double period, double offset, String label) {
        super(label);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[8]++;
        this.period = period;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[9]++;
        this.offset = offset;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[10]++;
        setFixedAutoRange(period);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[11]++;
        this.advanceLineVisible = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[12]++;
        this.advanceLinePaint = DEFAULT_ADVANCE_LINE_PAINT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[13]++;
    }
        
    /**
     * The advance line is the line drawn at the limit of the current cycle, 
     * when erasing the previous cycle. 
     * 
     * @return A boolean.
     */
    public boolean isAdvanceLineVisible() {
        return this.advanceLineVisible;
    }
    
    /**
     * The advance line is the line drawn at the limit of the current cycle, 
     * when erasing the previous cycle. 
     * 
     * @param visible  the flag.
     */
    public void setAdvanceLineVisible(boolean visible) {
        this.advanceLineVisible = visible;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[14]++;
    }
    
    /**
     * The advance line is the line drawn at the limit of the current cycle, 
     * when erasing the previous cycle. 
     * 
     * @return The paint (never <code>null</code>).
     */
    public Paint getAdvanceLinePaint() {
        return this.advanceLinePaint;
    }

    /**
     * The advance line is the line drawn at the limit of the current cycle, 
     * when erasing the previous cycle. 
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setAdvanceLinePaint(Paint paint) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[1]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[2]++;}
        this.advanceLinePaint = paint;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[16]++;
    }
    
    /**
     * The advance line is the line drawn at the limit of the current cycle, 
     * when erasing the previous cycle. 
     * 
     * @return The stroke (never <code>null</code>).
     */
    public Stroke getAdvanceLineStroke() {
        return this.advanceLineStroke;
    }
    /**
     * The advance line is the line drawn at the limit of the current cycle, 
     * when erasing the previous cycle. 
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     */
    public void setAdvanceLineStroke(Stroke stroke) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[3]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[4]++;}
        this.advanceLineStroke = stroke;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[18]++;
    }
    
    /**
     * The cycle bound can be associated either with the current or with the 
     * last cycle.  It's up to the user's choice to decide which, as this is 
     * just a convention.  By default, the cycle bound is mapped to the current
     * cycle.
     * <br>
     * Note that this has no effect on visual appearance, as the cycle bound is
     * mapped successively for both axis ends. Use this function for correct 
     * results in translateValueToJava2D. 
     *  
     * @return <code>true</code> if the cycle bound is mapped to the last 
     *         cycle, <code>false</code> if it is bound to the current cycle 
     *         (default)
     */
    public boolean isBoundMappedToLastCycle() {
        return this.boundMappedToLastCycle;
    }
    
    /**
     * The cycle bound can be associated either with the current or with the 
     * last cycle.  It's up to the user's choice to decide which, as this is 
     * just a convention. By default, the cycle bound is mapped to the current 
     * cycle. 
     * <br>
     * Note that this has no effect on visual appearance, as the cycle bound is
     * mapped successively for both axis ends. Use this function for correct 
     * results in valueToJava2D.
     *  
     * @param boundMappedToLastCycle Set it to true to map the cycle bound to 
     *        the last cycle.
     */
    public void setBoundMappedToLastCycle(boolean boundMappedToLastCycle) {
        this.boundMappedToLastCycle = boundMappedToLastCycle;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[19]++;
    }
    
    /**
     * Selects a tick unit when the axis is displayed horizontally.
     * 
     * @param g2  the graphics device.
     * @param drawArea  the drawing area.
     * @param dataArea  the data area.
     * @param edge  the side of the rectangle on which the axis is displayed.
     */
    protected void selectHorizontalAutoTickUnit(Graphics2D g2,
                                                Rectangle2D drawArea, 
                                                Rectangle2D dataArea,
                                                RectangleEdge edge) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[20]++;

        double tickLabelWidth 
            = estimateMaximumTickLabelWidth(g2, getTickUnit());
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[21]++;
        
        // Compute number of labels
        double n = getRange().getLength() 
                   * tickLabelWidth / dataArea.getWidth();

        setTickUnit(
            (NumberTickUnit) getStandardTickUnits().getCeilingTickUnit(n), 
            false, false
        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[22]++;
        
     }

    /**
     * Selects a tick unit when the axis is displayed vertically.
     * 
     * @param g2  the graphics device.
     * @param drawArea  the drawing area.
     * @param dataArea  the data area.
     * @param edge  the side of the rectangle on which the axis is displayed.
     */
    protected void selectVerticalAutoTickUnit(Graphics2D g2,
                                                Rectangle2D drawArea, 
                                                Rectangle2D dataArea,
                                                RectangleEdge edge) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[23]++;

        double tickLabelWidth 
            = estimateMaximumTickLabelWidth(g2, getTickUnit());
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[24]++;

        // Compute number of labels
        double n = getRange().getLength() 
                   * tickLabelWidth / dataArea.getHeight();

        setTickUnit(
            (NumberTickUnit) getStandardTickUnits().getCeilingTickUnit(n), 
            false, false
        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[25]++;
        
     }

    /** 
     * A special Number tick that also hold information about the cycle bound 
     * mapping for this tick.  This is especially useful for having a tick at 
     * each axis end with the cycle bound value.  See also 
     * isBoundMappedToLastCycle()
     */
    protected static class CycleBoundTick extends NumberTick {
        
        /** Map to last cycle. */
        public boolean mapToLastCycle;
        
        /**
         * Creates a new tick.
         * 
         * @param mapToLastCycle  map to last cycle?
         * @param number  the number.
         * @param label  the label.
         * @param textAnchor  the text anchor.
         * @param rotationAnchor  the rotation anchor.
         * @param angle  the rotation angle.
         */
        public CycleBoundTick(boolean mapToLastCycle, Number number, 
                              String label, TextAnchor textAnchor,
                              TextAnchor rotationAnchor, double angle) {
            super(number, label, textAnchor, rotationAnchor, angle);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[26]++;
            this.mapToLastCycle = mapToLastCycle;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[27]++;
        }
    }
    
    /**
     * Calculates the anchor point for a tick.
     * 
     * @param tick  the tick.
     * @param cursor  the cursor.
     * @param dataArea  the data area.
     * @param edge  the side on which the axis is displayed.
     * 
     * @return The anchor point.
     */
    protected float[] calculateAnchorPoint(ValueTick tick, double cursor, 
                                           Rectangle2D dataArea, 
                                           RectangleEdge edge) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((tick instanceof CycleBoundTick) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[5]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[29]++;
            boolean mapsav = this.boundMappedToLastCycle;
            this.boundMappedToLastCycle 
                = ((CycleBoundTick) tick).mapToLastCycle;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[30]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[31]++;
            float[] ret = super.calculateAnchorPoint(
                tick, cursor, dataArea, edge
            );
            this.boundMappedToLastCycle = mapsav;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[32]++;
            return ret;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[6]++;}
        return super.calculateAnchorPoint(tick, cursor, dataArea, edge);
    }
    
    
    
    /**
     * Builds a list of ticks for the axis.  This method is called when the 
     * axis is at the top or bottom of the chart (so the axis is "horizontal").
     * 
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param edge  the edge.
     * 
     * @return A list of ticks.
     */
    protected List refreshTicksHorizontal(Graphics2D g2, 
                                          Rectangle2D dataArea, 
                                          RectangleEdge edge) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[33]++;

        List result = new java.util.ArrayList();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[34]++;

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[35]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;
        
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isAutoTickUnitSelection()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[7]++;
            selectAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[37]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[8]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[38]++;

        double unit = getTickUnit().getSize();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[39]++;
        double cycleBound = getCycleBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[40]++;
        double currentTickValue = Math.ceil(cycleBound / unit) * unit;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[41]++;
        double upperValue = getRange().getUpperBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[42]++;
        boolean cycled = false;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[43]++;

        boolean boundMapping = this.boundMappedToLastCycle; 
        this.boundMappedToLastCycle = false;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[44]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[45]++; 
        
        CycleBoundTick lastTick = null;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[46]++; 
        float lastX = 0.0f;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[47]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((upperValue == cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[9]++;
            currentTickValue = calculateLowestVisibleTickValue();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[48]++;
            cycled = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[49]++;
            this.boundMappedToLastCycle = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[50]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[10]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[51]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
        
        while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((currentTickValue <= upperValue) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[1]--;
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[2]--;
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[3]++;
}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[52]++;
            
            // Cycle when necessary
            boolean cyclenow = false;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[53]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((currentTickValue + unit > upperValue) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((cycled) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[11]++;
                cyclenow = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[54]++;

            } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[12]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[55]++;
            
            double xx = valueToJava2D(currentTickValue, dataArea, edge);
            String tickLabel;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[56]++;
            NumberFormat formatter = getNumberFormatOverride();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[57]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[13]++;
                tickLabel = formatter.format(currentTickValue);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[58]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[14]++;
                tickLabel = getTickUnit().valueToString(currentTickValue);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[59]++;
            }
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[60]++;
            float x = (float) xx;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[61]++;
            TextAnchor anchor = null;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[62]++;
            TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[63]++;
            double angle = 0.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[64]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[15]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[65]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[17]++;
                    angle = Math.PI / 2.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[66]++;

                }
                else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[18]++;
                    angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[67]++;
                }
                anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[68]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[69]++;
int CodeCoverConditionCoverageHelper_C11;
                // If tick overlap when cycling, update last tick too
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C11 |= (32)) == 0 || true) &&
 ((lastTick != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((lastX == x) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((currentTickValue != cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[19]++;
                    anchor = isInverted() 
                        ? TextAnchor.TOP_RIGHT : TextAnchor.BOTTOM_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[70]++;
                    result.remove(result.size() - 1);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[71]++;
                    result.add(new CycleBoundTick(
                        this.boundMappedToLastCycle, lastTick.getNumber(), 
                        lastTick.getText(), anchor, anchor, 
                        lastTick.getAngle())
                    );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[72]++;
                    this.internalMarkerWhenTicksOverlap = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[73]++;
                    anchor = isInverted() 
                        ? TextAnchor.BOTTOM_RIGHT : TextAnchor.TOP_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[74]++;

                } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[20]++;}
                rotationAnchor = anchor;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[75]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[16]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[76]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[21]++;
                    anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[77]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[78]++;
int CodeCoverConditionCoverageHelper_C13; 
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((lastTick != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((lastX == x) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((currentTickValue != cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[23]++;
                        anchor = isInverted() 
                            ? TextAnchor.BOTTOM_LEFT : TextAnchor.BOTTOM_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[79]++;
                        result.remove(result.size() - 1);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[80]++;
                        result.add(new CycleBoundTick(
                            this.boundMappedToLastCycle, lastTick.getNumber(),
                            lastTick.getText(), anchor, anchor, 
                            lastTick.getAngle())
                        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[81]++;
                        this.internalMarkerWhenTicksOverlap = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[82]++;
                        anchor = isInverted() 
                            ? TextAnchor.BOTTOM_RIGHT : TextAnchor.BOTTOM_LEFT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[83]++;

                    } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[24]++;}
                    rotationAnchor = anchor;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[84]++;

                }
                else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[22]++;
                    anchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[85]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[86]++;
int CodeCoverConditionCoverageHelper_C14; 
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((lastTick != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((lastX == x) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((currentTickValue != cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[25]++;
                        anchor = isInverted() 
                            ? TextAnchor.TOP_LEFT : TextAnchor.TOP_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[87]++;
                        result.remove(result.size() - 1);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[88]++;
                        result.add(new CycleBoundTick(
                            this.boundMappedToLastCycle, lastTick.getNumber(),
                            lastTick.getText(), anchor, anchor, 
                            lastTick.getAngle())
                        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[89]++;
                        this.internalMarkerWhenTicksOverlap = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[90]++;
                        anchor = isInverted() 
                            ? TextAnchor.TOP_RIGHT : TextAnchor.TOP_LEFT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[91]++;

                    } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[26]++;}
                    rotationAnchor = anchor;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[92]++;
                }
            }
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[93]++;

            CycleBoundTick tick = new CycleBoundTick(
                this.boundMappedToLastCycle, 
                new Double(currentTickValue), tickLabel, anchor, 
                rotationAnchor, angle
            );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[94]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((currentTickValue == cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[27]++;
                this.internalMarkerCycleBoundTick = tick;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[95]++;
 
            } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[28]++;}
            result.add(tick);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[96]++;
            lastTick = tick;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[97]++;
            lastX = x;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[98]++;
            
            currentTickValue += unit;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[99]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[100]++;
int CodeCoverConditionCoverageHelper_C16;
            
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((cyclenow) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[29]++;
                currentTickValue = calculateLowestVisibleTickValue();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[101]++;
                upperValue = cycleBound;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[102]++;
                cycled = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[103]++;
                this.boundMappedToLastCycle = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[104]++;
 
            } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[30]++;}

        }
        this.boundMappedToLastCycle = boundMapping;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[105]++; 
        return result;
        
    }

    /**
     * Builds a list of ticks for the axis.  This method is called when the 
     * axis is at the left or right of the chart (so the axis is "vertical").
     * 
     * @param g2  the graphics device.
     * @param dataArea  the data area.
     * @param edge  the edge.
     * 
     * @return A list of ticks.
     */
    protected List refreshVerticalTicks(Graphics2D g2, 
                                        Rectangle2D dataArea, 
                                        RectangleEdge edge) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[106]++;
        
        List result = new java.util.ArrayList();
        result.clear();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[107]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[108]++;

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[109]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[110]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((isAutoTickUnitSelection()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[31]++;
            selectAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[111]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[32]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[112]++;

        double unit = getTickUnit().getSize();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[113]++;
        double cycleBound = getCycleBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[114]++;
        double currentTickValue = Math.ceil(cycleBound / unit) * unit;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[115]++;
        double upperValue = getRange().getUpperBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[116]++;
        boolean cycled = false;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[117]++;

        boolean boundMapping = this.boundMappedToLastCycle; 
        this.boundMappedToLastCycle = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[118]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[119]++; 

        NumberTick lastTick = null;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[120]++;
        float lastY = 0.0f;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[121]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((upperValue == cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[33]++;
            currentTickValue = calculateLowestVisibleTickValue();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[122]++;
            cycled = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[123]++;
            this.boundMappedToLastCycle = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[124]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[34]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[125]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[4]++;


int CodeCoverConditionCoverageHelper_C19;
        
        while ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((currentTickValue <= upperValue) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[4]--;
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[5]--;
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.loops[6]++;
}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[126]++;
            
            // Cycle when necessary
            boolean cyclenow = false;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[127]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((currentTickValue + unit > upperValue) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((cycled) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[35]++;
                cyclenow = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[128]++;

            } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[36]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[129]++;

            double yy = valueToJava2D(currentTickValue, dataArea, edge);
            String tickLabel;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[130]++;
            NumberFormat formatter = getNumberFormatOverride();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[131]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[37]++;
                tickLabel = formatter.format(currentTickValue);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[132]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[38]++;
                tickLabel = getTickUnit().valueToString(currentTickValue);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[133]++;
            }
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[134]++;

            float y = (float) yy;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[135]++;
            TextAnchor anchor = null;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[136]++;
            TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[137]++;
            double angle = 0.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[138]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[39]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[139]++;
int CodeCoverConditionCoverageHelper_C23;

                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[41]++;
                    anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[140]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[141]++;
int CodeCoverConditionCoverageHelper_C24; 
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C24 |= (32)) == 0 || true) &&
 ((lastTick != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((lastY == y) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((currentTickValue != cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[43]++;
                        anchor = isInverted() 
                            ? TextAnchor.BOTTOM_LEFT : TextAnchor.BOTTOM_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[142]++;
                        result.remove(result.size() - 1);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[143]++;
                        result.add(new CycleBoundTick(
                            this.boundMappedToLastCycle, lastTick.getNumber(),
                            lastTick.getText(), anchor, anchor, 
                            lastTick.getAngle())
                        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[144]++;
                        this.internalMarkerWhenTicksOverlap = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[145]++;
                        anchor = isInverted() 
                            ? TextAnchor.BOTTOM_RIGHT : TextAnchor.BOTTOM_LEFT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[146]++;

                    } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[44]++;}
                    rotationAnchor = anchor;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[147]++;
                    angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[148]++;

                }
                else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[42]++;
                    anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[149]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[150]++;
int CodeCoverConditionCoverageHelper_C25; 
                    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((lastTick != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((lastY == y) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((currentTickValue != cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[45]++;
                        anchor = isInverted() 
                            ? TextAnchor.BOTTOM_RIGHT : TextAnchor.BOTTOM_LEFT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[151]++;
                        result.remove(result.size() - 1);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[152]++;
                        result.add(new CycleBoundTick(
                            this.boundMappedToLastCycle, lastTick.getNumber(),
                            lastTick.getText(), anchor, anchor, 
                            lastTick.getAngle())
                        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[153]++;
                        this.internalMarkerWhenTicksOverlap = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[154]++;
                        anchor = isInverted() 
                            ? TextAnchor.BOTTOM_LEFT : TextAnchor.BOTTOM_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[155]++;

                    } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[46]++;}
                    rotationAnchor = anchor;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[156]++;
                    angle = Math.PI / 2.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[157]++;
                }

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[40]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[158]++;
int CodeCoverConditionCoverageHelper_C26;
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[47]++;
                    anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[159]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[160]++;
int CodeCoverConditionCoverageHelper_C27; 
                    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C27 |= (32)) == 0 || true) &&
 ((lastTick != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((lastY == y) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((currentTickValue != cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 3) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 3) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[49]++;
                        anchor = isInverted() 
                            ? TextAnchor.BOTTOM_RIGHT : TextAnchor.TOP_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[161]++;
                        result.remove(result.size() - 1);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[162]++;
                        result.add(new CycleBoundTick(
                            this.boundMappedToLastCycle, lastTick.getNumber(),
                            lastTick.getText(), anchor, anchor, 
                            lastTick.getAngle())
                        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[163]++;
                        this.internalMarkerWhenTicksOverlap = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[164]++;
                        anchor = isInverted() 
                            ? TextAnchor.TOP_RIGHT : TextAnchor.BOTTOM_RIGHT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[165]++;

                    } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[50]++;}
                    rotationAnchor = anchor;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[166]++;

                }
                else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[48]++;
                    anchor = TextAnchor.CENTER_LEFT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[167]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[168]++;
int CodeCoverConditionCoverageHelper_C28; 
                    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C28 |= (32)) == 0 || true) &&
 ((lastTick != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((lastY == y) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((currentTickValue != cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 3) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 3) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[51]++;
                        anchor = isInverted() 
                            ? TextAnchor.BOTTOM_LEFT : TextAnchor.TOP_LEFT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[169]++;
                        result.remove(result.size() - 1);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[170]++;
                        result.add(new CycleBoundTick(
                            this.boundMappedToLastCycle, lastTick.getNumber(),
                            lastTick.getText(), anchor, anchor, 
                            lastTick.getAngle())
                        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[171]++;
                        this.internalMarkerWhenTicksOverlap = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[172]++;
                        anchor = isInverted() 
                            ? TextAnchor.TOP_LEFT : TextAnchor.BOTTOM_LEFT;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[173]++;

                    } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[52]++;}
                    rotationAnchor = anchor;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[174]++;
                }
            }
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[175]++;

            CycleBoundTick tick = new CycleBoundTick(
                this.boundMappedToLastCycle, new Double(currentTickValue), 
                tickLabel, anchor, rotationAnchor, angle
            );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[176]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((currentTickValue == cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[53]++;
                this.internalMarkerCycleBoundTick = tick;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[177]++;
 
            } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[54]++;}
            result.add(tick);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[178]++;
            lastTick = tick;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[179]++;
            lastY = y;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[180]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[181]++;
int CodeCoverConditionCoverageHelper_C30;
            
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((currentTickValue == cycleBound) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[55]++;
                this.internalMarkerCycleBoundTick = tick;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[182]++;

            } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[56]++;}

            currentTickValue += unit;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[183]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[184]++;
int CodeCoverConditionCoverageHelper_C31;
            
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((cyclenow) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[57]++;
                currentTickValue = calculateLowestVisibleTickValue();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[185]++;
                upperValue = cycleBound;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[186]++;
                cycled = true;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[187]++;
                this.boundMappedToLastCycle = false;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[188]++;
 
            } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[58]++;}

        }
        this.boundMappedToLastCycle = boundMapping;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[189]++; 
        return result;
    }
    
    /**
     * Converts a coordinate from Java 2D space to data space.
     * 
     * @param java2DValue  the coordinate in Java2D space.
     * @param dataArea  the data area.
     * @param edge  the edge.
     * 
     * @return The data value.
     */
    public double java2DToValue(double java2DValue, Rectangle2D dataArea, 
                                RectangleEdge edge) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[190]++;
        Range range = getRange();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[191]++;
        
        double vmax = range.getUpperBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[192]++;
        double vp = getCycleBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[193]++;

        double jmin = 0.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[194]++;
        double jmax = 0.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[195]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[59]++;
            jmin = dataArea.getMinX();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[196]++;
            jmax = dataArea.getMaxX();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[197]++;

        }
        else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[60]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[198]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[61]++;
            jmin = dataArea.getMaxY();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[199]++;
            jmax = dataArea.getMinY();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[200]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[62]++;}
}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[201]++;
int CodeCoverConditionCoverageHelper_C34;
        
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[63]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[202]++;
            double jbreak = jmax - (vmax - vp) * (jmax - jmin) / this.period;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[203]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((java2DValue >= jbreak) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[65]++; 
                return vp + (jmax - java2DValue) * this.period / (jmax - jmin);

            } 
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[66]++;
                return vp - (java2DValue - jmin) * this.period / (jmax - jmin);
            }

        }
        else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[64]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[204]++;
            double jbreak = (vmax - vp) * (jmax - jmin) / this.period + jmin;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[205]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((java2DValue <= jbreak) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[67]++; 
                return vp + (java2DValue - jmin) * this.period / (jmax - jmin);

            } 
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[68]++;
                return vp - (jmax - java2DValue) * this.period / (jmax - jmin);
            }
        }
    }
    
    /**
     * Translates a value from data space to Java 2D space.
     * 
     * @param value  the data value.
     * @param dataArea  the data area.
     * @param edge  the edge.
     * 
     * @return The Java 2D value.
     */
    public double valueToJava2D(double value, Rectangle2D dataArea, 
                                RectangleEdge edge) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[206]++;
        Range range = getRange();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[207]++;
        
        double vmin = range.getLowerBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[208]++;
        double vmax = range.getUpperBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[209]++;
        double vp = getCycleBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[210]++;
int CodeCoverConditionCoverageHelper_C37;

        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((value < vmin) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((value > vmax) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[69]++;
            return Double.NaN;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[70]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[211]++;
        
        
        double jmin = 0.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[212]++;
        double jmax = 0.0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[213]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[71]++;
            jmin = dataArea.getMinX();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[214]++;
            jmax = dataArea.getMaxX();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[215]++;

        }
        else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[72]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[216]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[73]++;
            jmax = dataArea.getMinY();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[217]++;
            jmin = dataArea.getMaxY();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[218]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[74]++;}
}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[219]++;
int CodeCoverConditionCoverageHelper_C40;

        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[75]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[220]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((value == vp) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[77]++;
                return this.boundMappedToLastCycle ? jmin : jmax;
 
            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[78]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[221]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((value > vp) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[79]++;
                return jmax - (value - vp) * (jmax - jmin) / this.period;

            } 
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[80]++;
                return jmin + (vp - value) * (jmax - jmin) / this.period;
            }
}

        }
        else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[76]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[222]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((value == vp) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[81]++;
                return this.boundMappedToLastCycle ? jmax : jmin;
 
            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[82]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[223]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((value >= vp) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[83]++;
                return jmin + (value - vp) * (jmax - jmin) / this.period;

            } 
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[84]++;
                return jmax - (vp - value) * (jmax - jmin) / this.period;
            }
}
        }
    }
    
    /**
     * Centers the range about the given value.
     * 
     * @param value  the data value.
     */
    public void centerRange(double value) {
        setRange(value - this.period / 2.0, value + this.period / 2.0);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[224]++;
    }

    /** 
     * This function is nearly useless since the auto range is fixed for this 
     * class to the period.  The period is extended if necessary to fit the 
     * minimum size.
     * 
     * @param size  the size.
     * @param notify  notify?
     * 
     * @see org.jfree.chart.axis.ValueAxis#setAutoRangeMinimumSize(double, 
     *      boolean)
     */
    public void setAutoRangeMinimumSize(double size, boolean notify) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[225]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((size > this.period) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[85]++;
            this.period = size;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[226]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[86]++;}
        super.setAutoRangeMinimumSize(size, notify);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[227]++;
    }

    /** 
     * The auto range is fixed for this class to the period by default. 
     * This function will thus set a new period.
     * 
     * @param length  the length.
     * 
     * @see org.jfree.chart.axis.ValueAxis#setFixedAutoRange(double)
     */
    public void setFixedAutoRange(double length) {
        this.period = length;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[228]++;
        super.setFixedAutoRange(length);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[229]++;
    }

    /** 
     * Sets a new axis range. The period is extended to fit the range size, if 
     * necessary.
     * 
     * @param range  the range.
     * @param turnOffAutoRange  switch off the auto range.
     * @param notify notify?
     * 
     * @see org.jfree.chart.axis.ValueAxis#setRange(Range, boolean, boolean) 
     */
    public void setRange(Range range, boolean turnOffAutoRange, 
                         boolean notify) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[230]++;
        double size = range.getUpperBound() - range.getLowerBound();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[231]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((size > this.period) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[87]++;
            this.period = size;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[232]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[88]++;}
        super.setRange(range, turnOffAutoRange, notify);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[233]++;
    }
    
    /**
     * The cycle bound is defined as the higest value x such that 
     * "offset + period * i = x", with i and integer and x &lt; 
     * range.getUpperBound() This is the value which is at both ends of the 
     * axis :  x...up|low...x
     * The values from x to up are the valued in the current cycle.
     * The values from low to x are the valued in the previous cycle.
     * 
     * @return The cycle bound.
     */
    public double getCycleBound() {
        return Math.floor(
            (getRange().getUpperBound() - this.offset) / this.period
        ) * this.period + this.offset;
    }
    
    /**
     * The cycle bound is a multiple of the period, plus optionally a start 
     * offset.
     * <P>
     * <pre>cb = n * period + offset</pre><br>
     * 
     * @return The current offset.
     * 
     * @see #getCycleBound()
     */
    public double getOffset() {
        return this.offset;
    }
    
    /**
     * The cycle bound is a multiple of the period, plus optionally a start 
     * offset.
     * <P>
     * <pre>cb = n * period + offset</pre><br>
     * 
     * @param offset The offset to set.
     *
     * @see #getCycleBound() 
     */
    public void setOffset(double offset) {
        this.offset = offset;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[234]++;
    }
    
    /**
     * The cycle bound is a multiple of the period, plus optionally a start 
     * offset.
     * <P>
     * <pre>cb = n * period + offset</pre><br>
     * 
     * @return The current period.
     * 
     * @see #getCycleBound()
     */
    public double getPeriod() {
        return this.period;
    }
    
    /**
     * The cycle bound is a multiple of the period, plus optionally a start 
     * offset.
     * <P>
     * <pre>cb = n * period + offset</pre><br>
     * 
     * @param period The period to set.
     * 
     * @see #getCycleBound()
     */
    public void setPeriod(double period) {
        this.period = period;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[235]++;
    }

    /**
     * Draws the tick marks and labels.
     * 
     * @param g2  the graphics device.
     * @param cursor  the cursor.
     * @param plotArea  the plot area.
     * @param dataArea  the area inside the axes.
     * @param edge  the side on which the axis is displayed.
     * 
     * @return The axis state.
     */
    protected AxisState drawTickMarksAndLabels(Graphics2D g2, double cursor, 
                                               Rectangle2D plotArea, 
                                               Rectangle2D dataArea, 
                                               RectangleEdge edge) {
        this.internalMarkerWhenTicksOverlap = false;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[236]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[237]++;
        AxisState ret = super.drawTickMarksAndLabels(
            g2, cursor, plotArea, dataArea, edge
        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[238]++;
int CodeCoverConditionCoverageHelper_C47;
        
        // continue and separate the labels only if necessary
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((this.internalMarkerWhenTicksOverlap) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[89]++;
            return ret;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[90]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[239]++;
        
        double ol = getTickMarkOutsideLength();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[240]++;
        FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[241]++;
int CodeCoverConditionCoverageHelper_C48;
        
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[91]++;
            ol = fm.getMaxAdvance();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[242]++;
 
        }
        else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[92]++;
            ol = fm.getHeight();
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[243]++;
        }
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[244]++;
        
        double il = 0;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[245]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((isTickMarksVisible()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[93]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[246]++;
            float xx = (float) valueToJava2D(
                getRange().getUpperBound(), dataArea, edge
            );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[247]++;
            Line2D mark = null;
            g2.setStroke(getTickMarkStroke());
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[248]++;
            g2.setPaint(getTickMarkPaint());
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[249]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[250]++;
int CodeCoverConditionCoverageHelper_C50;
            if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[95]++;
                mark = new Line2D.Double(cursor - ol, xx, cursor + il, xx);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[251]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[96]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[252]++;
int CodeCoverConditionCoverageHelper_C51; if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[97]++;
                mark = new Line2D.Double(cursor + ol, xx, cursor - il, xx);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[253]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[98]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[254]++;
int CodeCoverConditionCoverageHelper_C52; if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[99]++;
                mark = new Line2D.Double(xx, cursor - ol, xx, cursor + il);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[255]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[100]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[256]++;
int CodeCoverConditionCoverageHelper_C53; if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[101]++;
                mark = new Line2D.Double(xx, cursor + ol, xx, cursor - il);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[257]++;

            } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[102]++;}
}
}
}
            g2.draw(mark);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[258]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[94]++;}
        return ret;
    }
    
    /**
     * Draws the axis.
     * 
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor position.
     * @param plotArea  the plot area (<code>null</code> not permitted).
     * @param dataArea  the data area (<code>null</code> not permitted).
     * @param edge  the edge (<code>null</code> not permitted).
     * @param plotState  collects information about the plot 
     *                   (<code>null</code> permitted).
     * 
     * @return The axis state (never <code>null</code>).
     */
    public AxisState draw(Graphics2D g2, 
                          double cursor,
                          Rectangle2D plotArea, 
                          Rectangle2D dataArea, 
                          RectangleEdge edge,
                          PlotRenderingInfo plotState) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[259]++;
        
        AxisState ret = super.draw(
            g2, cursor, plotArea, dataArea, edge, plotState
        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[260]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((isAdvanceLineVisible()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[103]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[261]++;
            double xx = valueToJava2D(
                getRange().getUpperBound(), dataArea, edge
            );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[262]++;
            Line2D mark = null;
            g2.setStroke(getAdvanceLineStroke());
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[263]++;
            g2.setPaint(getAdvanceLinePaint());
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[264]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[265]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[105]++;
                mark = new Line2D.Double(
                    cursor, xx, cursor + dataArea.getWidth(), xx
                );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[266]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[106]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[267]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.RIGHT) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[107]++;
                mark = new Line2D.Double(
                    cursor - dataArea.getWidth(), xx, cursor, xx
                );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[268]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[108]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[269]++;
int CodeCoverConditionCoverageHelper_C57; if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[109]++;
                mark = new Line2D.Double(
                    xx, cursor + dataArea.getHeight(), xx, cursor
                );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[270]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[110]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[271]++;
int CodeCoverConditionCoverageHelper_C58; if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[111]++;
                mark = new Line2D.Double(
                    xx, cursor, xx, cursor - dataArea.getHeight()
                );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[272]++;

            } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[112]++;}
}
}
}
            g2.draw(mark);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[273]++;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[104]++;}
        return ret;
    }

    /**
     * Reserve some space on each axis side because we draw a centered label at
     * each extremity. 
     * 
     * @param g2  the graphics device.
     * @param plot  the plot.
     * @param plotArea  the plot area.
     * @param edge  the edge.
     * @param space  the space already reserved.
     * 
     * @return The reserved space.
     */
    public AxisSpace reserveSpace(Graphics2D g2, 
                                  Plot plot, 
                                  Rectangle2D plotArea, 
                                  RectangleEdge edge, 
                                  AxisSpace space) {
        
        this.internalMarkerCycleBoundTick = null;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[274]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[275]++;
        AxisSpace ret = super.reserveSpace(g2, plot, plotArea, edge, space);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[276]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((this.internalMarkerCycleBoundTick == null) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[113]++;
            return ret;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[114]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[277]++;

        FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[278]++;
        Rectangle2D r = TextUtilities.getTextBounds(
            this.internalMarkerCycleBoundTick.getText(), g2, fm
        );
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[279]++;
int CodeCoverConditionCoverageHelper_C60;

        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[115]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[280]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[117]++;
                space.add(r.getHeight() / 2, RectangleEdge.RIGHT);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[281]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[118]++;
                space.add(r.getWidth() / 2, RectangleEdge.RIGHT);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[282]++;
            }

        }
        else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[116]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[283]++;
int CodeCoverConditionCoverageHelper_C62; if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[119]++;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[284]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[121]++;
                space.add(r.getWidth() / 2, RectangleEdge.TOP);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[285]++;

            }
            else {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[122]++;
                space.add(r.getHeight() / 2, RectangleEdge.TOP);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[286]++;
            }

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[120]++;}
}
        
        return ret;
        
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
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[287]++;
        SerialUtilities.writePaint(this.advanceLinePaint, stream);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[288]++;
        SerialUtilities.writeStroke(this.advanceLineStroke, stream);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[289]++;
    
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
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[290]++;
        this.advanceLinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[291]++;
        this.advanceLineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[292]++;
    
    }
     
    
    /**
     * Tests the axis for equality with another object.
     * 
     * @param obj  the object to test against.
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[293]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[123]++;
            return true;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[124]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[294]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((obj instanceof CyclicNumberAxis) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[125]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[126]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[295]++;
int CodeCoverConditionCoverageHelper_C66;
        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[127]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[128]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[296]++;
        CyclicNumberAxis that = (CyclicNumberAxis) obj;
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[297]++;
int CodeCoverConditionCoverageHelper_C67;      
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((this.period != that.period) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[129]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[130]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[298]++;
int CodeCoverConditionCoverageHelper_C68;
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.offset != that.offset) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[131]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[132]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[299]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.advanceLinePaint, 
                that.advanceLinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[133]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[134]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[300]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.advanceLineStroke, 
                that.advanceLineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[135]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[136]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[301]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((this.advanceLineVisible != that.advanceLineVisible) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[137]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[138]++;}
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.statements[302]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((this.boundMappedToLastCycle != that.boundMappedToLastCycle) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[139]++;
            return false;

        } else {
  CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29.branches[140]++;}
        return true;
    }
}

class CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29 ());
  }
    public static long[] statements = new long[303];
    public static long[] branches = new long[141];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[73];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.CyclicNumberAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,3,1,3,3,1,1,1,1,1,2,1,1,1,3,3,1,3,3,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 72; i++) {
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

  public CodeCoverCoverageCounter$1k1q2vq7yfysyvo03p0jeyjq3626jgx29 () {
    super("org.jfree.chart.axis.CyclicNumberAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 302; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 140; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 72; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.CyclicNumberAxis.java");
      for (int i = 1; i <= 302; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 140; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 72; i++) {
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

