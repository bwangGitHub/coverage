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
 * PeriodAxis.java
 * ---------------
 * (C) Copyright 2004-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 01-Jun-2004 : Version 1 (DG);
 * 16-Sep-2004 : Fixed bug in equals() method, added clone() method and 
 *               PublicCloneable interface (DG);
 * 25-Nov-2004 : Updates to support major and minor tick marks (DG);
 * 25-Feb-2005 : Fixed some tick mark bugs (DG);
 * 15-Apr-2005 : Fixed some more tick mark bugs (DG);
 * 26-Apr-2005 : Removed LOGGER (DG);
 * 16-Jun-2005 : Fixed zooming (DG);
 * 15-Sep-2005 : Changed configure() method to check autoRange flag,
 *               and added ticks to state (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Oct-2006 : Updated for deprecations in RegularTimePeriod and 
 *               subclasses (DG);
 * 22-Mar-2007 : Use new defaultAutoRange attribute (DG);
 * 31-Jul-2007 : Fix for inverted axis labelling (see bug 1763413) (DG);
 *
 */

package org.jfree.chart.axis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.data.Range;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Year;
import org.jfree.io.SerialUtilities;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.jfree.util.PublicCloneable;

/**
 * An axis that displays a date scale based on a 
 * {@link org.jfree.data.time.RegularTimePeriod}.  This axis works when
 * displayed across the bottom or top of a plot, but is broken for display at
 * the left or right of charts.
 */
public class PeriodAxis extends ValueAxis 
                        implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 8353295532075872069L;
  static {
    CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[1]++;
  }
    
    /** The first time period in the overall range. */
    private RegularTimePeriod first;
    
    /** The last time period in the overall range. */
    private RegularTimePeriod last;
    
    /** 
     * The time zone used to convert 'first' and 'last' to absolute 
     * milliseconds. 
     */
    private TimeZone timeZone;
    
    /** 
     * A calendar used for date manipulations in the current time zone.
     */
    private Calendar calendar;
    
    /** 
     * The {@link RegularTimePeriod} subclass used to automatically determine 
     * the axis range. 
     */
    private Class autoRangeTimePeriodClass;
    
    /** 
     * Indicates the {@link RegularTimePeriod} subclass that is used to 
     * determine the spacing of the major tick marks.
     */
    private Class majorTickTimePeriodClass;
    
    /** 
     * A flag that indicates whether or not tick marks are visible for the 
     * axis. 
     */
    private boolean minorTickMarksVisible;

    /** 
     * Indicates the {@link RegularTimePeriod} subclass that is used to 
     * determine the spacing of the minor tick marks.
     */
    private Class minorTickTimePeriodClass;
    
    /** The length of the tick mark inside the data area (zero permitted). */
    private float minorTickMarkInsideLength = 0.0f;
  {
    CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[2]++;
  }

    /** The length of the tick mark outside the data area (zero permitted). */
    private float minorTickMarkOutsideLength = 2.0f;
  {
    CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[3]++;
  }

    /** The stroke used to draw tick marks. */
    private transient Stroke minorTickMarkStroke = new BasicStroke(0.5f);
  {
    CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[4]++;
  }

    /** The paint used to draw tick marks. */
    private transient Paint minorTickMarkPaint = Color.black;
  {
    CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[5]++;
  }
    
    /** Info for each labelling band. */
    private PeriodAxisLabelInfo[] labelInfo;

    /**
     * Creates a new axis.
     * 
     * @param label  the axis label.
     */
    public PeriodAxis(String label) {
        this(label, new Day(), new Day());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[6]++;
    }
    
    /**
     * Creates a new axis.
     * 
     * @param label  the axis label (<code>null</code> permitted).
     * @param first  the first time period in the axis range 
     *               (<code>null</code> not permitted).
     * @param last  the last time period in the axis range 
     *              (<code>null</code> not permitted).
     */
    public PeriodAxis(String label, 
                      RegularTimePeriod first, RegularTimePeriod last) {
        this(label, first, last, TimeZone.getDefault());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[7]++;
    }
    
    /**
     * Creates a new axis.
     * 
     * @param label  the axis label (<code>null</code> permitted).
     * @param first  the first time period in the axis range 
     *               (<code>null</code> not permitted).
     * @param last  the last time period in the axis range 
     *              (<code>null</code> not permitted).
     * @param timeZone  the time zone (<code>null</code> not permitted).
     */
    public PeriodAxis(String label, 
                      RegularTimePeriod first, RegularTimePeriod last, 
                      TimeZone timeZone) {
        
        super(label, null);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[8]++;
        this.first = first;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[9]++;
        this.last = last;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[10]++;
        this.timeZone = timeZone;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[11]++;
        this.calendar = Calendar.getInstance(timeZone);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[12]++;
        this.autoRangeTimePeriodClass = first.getClass();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[13]++;
        this.majorTickTimePeriodClass = first.getClass();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[14]++;
        this.minorTickMarksVisible = false;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[15]++;
        this.minorTickTimePeriodClass = RegularTimePeriod.downsize(
                this.majorTickTimePeriodClass);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[16]++;
        setAutoRange(true);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[17]++;
        this.labelInfo = new PeriodAxisLabelInfo[2];
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[18]++;
        this.labelInfo[0] = new PeriodAxisLabelInfo(Month.class, 
                new SimpleDateFormat("MMM"));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[19]++;
        this.labelInfo[1] = new PeriodAxisLabelInfo(Year.class, 
                new SimpleDateFormat("yyyy"));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[20]++;
        
    }
    
    /**
     * Returns the first time period in the axis range.
     * 
     * @return The first time period (never <code>null</code>).
     */
    public RegularTimePeriod getFirst() {
        return this.first;
    }
    
    /**
     * Sets the first time period in the axis range and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param first  the time period (<code>null</code> not permitted).
     */
    public void setFirst(RegularTimePeriod first) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[21]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[1]++;
            throw new IllegalArgumentException("Null 'first' argument.");
   
        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[2]++;}
        this.first = first;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[22]++;   
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[23]++;
    }
    
    /**
     * Returns the last time period in the axis range.
     * 
     * @return The last time period (never <code>null</code>).
     */
    public RegularTimePeriod getLast() {
        return this.last;
    }
    
    /**
     * Sets the last time period in the axis range and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param last  the time period (<code>null</code> not permitted).
     */
    public void setLast(RegularTimePeriod last) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((last == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[3]++;
            throw new IllegalArgumentException("Null 'last' argument.");
   
        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[4]++;}
        this.last = last;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[25]++;   
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[26]++;
    }
    
    /**
     * Returns the time zone used to convert the periods defining the axis 
     * range into absolute milliseconds.
     * 
     * @return The time zone (never <code>null</code>).
     */
    public TimeZone getTimeZone() {
        return this.timeZone;   
    }
    
    /**
     * Sets the time zone that is used to convert the time periods into 
     * absolute milliseconds.
     * 
     * @param zone  the time zone (<code>null</code> not permitted).
     */
    public void setTimeZone(TimeZone zone) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[27]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[5]++;
            throw new IllegalArgumentException("Null 'zone' argument.");
   
        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[6]++;}
        this.timeZone = zone;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[28]++;
        this.calendar = Calendar.getInstance(zone);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[29]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[30]++;
    }
    
    /**
     * Returns the class used to create the first and last time periods for 
     * the axis range when the auto-range flag is set to <code>true</code>.
     * 
     * @return The class (never <code>null</code>).
     */
    public Class getAutoRangeTimePeriodClass() {
        return this.autoRangeTimePeriodClass;   
    }
    
    /**
     * Sets the class used to create the first and last time periods for the 
     * axis range when the auto-range flag is set to <code>true</code> and 
     * sends an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param c  the class (<code>null</code> not permitted).
     */
    public void setAutoRangeTimePeriodClass(Class c) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((c == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[7]++;
            throw new IllegalArgumentException("Null 'c' argument.");
   
        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[8]++;}
        this.autoRangeTimePeriodClass = c;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[32]++;   
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[33]++;
    }
    
    /**
     * Returns the class that controls the spacing of the major tick marks.
     * 
     * @return The class (never <code>null</code>).
     */
    public Class getMajorTickTimePeriodClass() {
        return this.majorTickTimePeriodClass;
    }
    
    /**
     * Sets the class that controls the spacing of the major tick marks, and 
     * sends an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param c  the class (a subclass of {@link RegularTimePeriod} is 
     *           expected).
     */
    public void setMajorTickTimePeriodClass(Class c) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[34]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[9]++;
            throw new IllegalArgumentException("Null 'c' argument.");

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[10]++;}
        this.majorTickTimePeriodClass = c;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[35]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[36]++;
    }
    
    /**
     * Returns the flag that controls whether or not minor tick marks
     * are displayed for the axis.
     * 
     * @return A boolean.
     */
    public boolean isMinorTickMarksVisible() {
        return this.minorTickMarksVisible;
    }
    
    /**
     * Sets the flag that controls whether or not minor tick marks
     * are displayed for the axis, and sends a {@link AxisChangeEvent}
     * to all registered listeners.
     * 
     * @param visible  the flag.
     */
    public void setMinorTickMarksVisible(boolean visible) {
        this.minorTickMarksVisible = visible;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[37]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[38]++;
    }
    
    /**
     * Returns the class that controls the spacing of the minor tick marks.
     * 
     * @return The class (never <code>null</code>).
     */
    public Class getMinorTickTimePeriodClass() {
        return this.minorTickTimePeriodClass;
    }
    
    /**
     * Sets the class that controls the spacing of the minor tick marks, and 
     * sends an {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param c  the class (a subclass of {@link RegularTimePeriod} is 
     *           expected).
     */
    public void setMinorTickTimePeriodClass(Class c) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((c == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[11]++;
            throw new IllegalArgumentException("Null 'c' argument.");

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[12]++;}
        this.minorTickTimePeriodClass = c;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[40]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[41]++;
    }
    
    /**
     * Returns the stroke used to display minor tick marks, if they are 
     * visible.
     * 
     * @return A stroke (never <code>null</code>).
     */
    public Stroke getMinorTickMarkStroke() {
        return this.minorTickMarkStroke;
    }
    
    /**
     * Sets the stroke used to display minor tick marks, if they are 
     * visible, and sends a {@link AxisChangeEvent} to all registered 
     * listeners.
     * 
     * @param stroke  the stroke (<code>null</code> not permitted).
     */
    public void setMinorTickMarkStroke(Stroke stroke) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((stroke == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[13]++;
            throw new IllegalArgumentException("Null 'stroke' argument.");

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[14]++;}
        this.minorTickMarkStroke = stroke;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[43]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[44]++;
    }
    
    /**
     * Returns the paint used to display minor tick marks, if they are 
     * visible.
     * 
     * @return A paint (never <code>null</code>).
     */
    public Paint getMinorTickMarkPaint() {
        return this.minorTickMarkPaint;
    }
    
    /**
     * Sets the paint used to display minor tick marks, if they are 
     * visible, and sends a {@link AxisChangeEvent} to all registered 
     * listeners.
     * 
     * @param paint  the paint (<code>null</code> not permitted).
     */
    public void setMinorTickMarkPaint(Paint paint) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[45]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((paint == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[15]++;
            throw new IllegalArgumentException("Null 'paint' argument.");

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[16]++;}
        this.minorTickMarkPaint = paint;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[46]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[47]++;
    }
    
    /**
     * Returns the inside length for the minor tick marks.
     * 
     * @return The length.
     */
    public float getMinorTickMarkInsideLength() {
        return this.minorTickMarkInsideLength;   
    }
    
    /**
     * Sets the inside length of the minor tick marks and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param length  the length.
     */
    public void setMinorTickMarkInsideLength(float length) {
        this.minorTickMarkInsideLength = length;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[48]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[49]++;
    }
    
    /**
     * Returns the outside length for the minor tick marks.
     * 
     * @return The length.
     */
    public float getMinorTickMarkOutsideLength() {
        return this.minorTickMarkOutsideLength;   
    }
    
    /**
     * Sets the outside length of the minor tick marks and sends an 
     * {@link AxisChangeEvent} to all registered listeners.
     * 
     * @param length  the length.
     */
    public void setMinorTickMarkOutsideLength(float length) {
        this.minorTickMarkOutsideLength = length;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[50]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[51]++;
    }
    
    /**
     * Returns an array of label info records.
     * 
     * @return An array.
     */
    public PeriodAxisLabelInfo[] getLabelInfo() {
        return this.labelInfo;    
    }
    
    /**
     * Sets the array of label info records.
     * 
     * @param info  the info.
     */
    public void setLabelInfo(PeriodAxisLabelInfo[] info) {
        this.labelInfo = info;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[52]++;
        // FIXME: shouldn't this generate an event?
    }
    
    /**
     * Returns the range for the axis.
     *
     * @return The axis range (never <code>null</code>).
     */
    public Range getRange() {
        // TODO: find a cleaner way to do this...
        return new Range(this.first.getFirstMillisecond(this.calendar), 
                this.last.getLastMillisecond(this.calendar));
    }

    /**
     * Sets the range for the axis, if requested, sends an 
     * {@link AxisChangeEvent} to all registered listeners.  As a side-effect, 
     * the auto-range flag is set to <code>false</code> (optional).
     *
     * @param range  the range (<code>null</code> not permitted).
     * @param turnOffAutoRange  a flag that controls whether or not the auto 
     *                          range is turned off.         
     * @param notify  a flag that controls whether or not listeners are 
     *                notified.
     */
    public void setRange(Range range, boolean turnOffAutoRange, 
                         boolean notify) {
        super.setRange(range, turnOffAutoRange, false);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[53]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[54]++;
        long upper = Math.round(range.getUpperBound());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[55]++;
        long lower = Math.round(range.getLowerBound());
        this.first = createInstance(this.autoRangeTimePeriodClass, 
                new Date(lower), this.timeZone);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[56]++;
        this.last = createInstance(this.autoRangeTimePeriodClass, 
                new Date(upper), this.timeZone);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[57]++;        
    }

    /**
     * Configures the axis to work with the current plot.  Override this method
     * to perform any special processing (such as auto-rescaling).
     */
    public void configure() {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[58]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((this.isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[17]++;
            autoAdjustRange();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[59]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[18]++;}
    }

    /**
     * Estimates the space (height or width) required to draw the axis.
     *
     * @param g2  the graphics device.
     * @param plot  the plot that the axis belongs to.
     * @param plotArea  the area within which the plot (including axes) should 
     *                  be drawn.
     * @param edge  the axis location.
     * @param space  space already reserved.
     *
     * @return The space required to draw the axis (including pre-reserved 
     *         space).
     */
    public AxisSpace reserveSpace(Graphics2D g2, Plot plot, 
                                  Rectangle2D plotArea, RectangleEdge edge, 
                                  AxisSpace space) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[60]++;
int CodeCoverConditionCoverageHelper_C10;
        // create a new space object if one wasn't supplied...
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((space == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[19]++;
            space = new AxisSpace();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[61]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[20]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[62]++;
int CodeCoverConditionCoverageHelper_C11;
        
        // if the axis is not visible, no additional space is required...
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[21]++;
            return space;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[22]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[63]++;

        // if the axis has a fixed dimension, return it...
        double dimension = getFixedDimension();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[64]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((dimension > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[23]++;
            space.ensureAtLeast(dimension, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[65]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[24]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[66]++;
        
        // get the axis label size and update the space object...
        Rectangle2D labelEnclosure = getLabelEnclosure(g2, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[67]++;
        double labelHeight = 0.0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[68]++;
        double labelWidth = 0.0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[69]++;
        double tickLabelBandsDimension = 0.0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[70]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[1]++;


int CodeCoverConditionCoverageHelper_C13;
        
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((i < this.labelInfo.length) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[1]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[2]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[3]++;
}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[71]++;
            PeriodAxisLabelInfo info = this.labelInfo[i];
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[72]++;
            FontMetrics fm = g2.getFontMetrics(info.getLabelFont());
            tickLabelBandsDimension 
                += info.getPadding().extendHeight(fm.getHeight());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[73]++;
        }
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[74]++;
int CodeCoverConditionCoverageHelper_C14;
        
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[25]++;
            labelHeight = labelEnclosure.getHeight();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[75]++;
            space.add(labelHeight + tickLabelBandsDimension, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[76]++;

        }
        else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[26]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[77]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[27]++;
            labelWidth = labelEnclosure.getWidth();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[78]++;
            space.add(labelWidth + tickLabelBandsDimension, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[79]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[28]++;}
}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[80]++;

        // add space for the outer tick labels, if any...
        double tickMarkSpace = 0.0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[81]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isTickMarksVisible()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[29]++;
            tickMarkSpace = getTickMarkOutsideLength();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[82]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[30]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[83]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((this.minorTickMarksVisible) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[31]++;
            tickMarkSpace = Math.max(tickMarkSpace, 
                    this.minorTickMarkOutsideLength);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[84]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[32]++;}
        space.add(tickMarkSpace, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[85]++;
        return space;
    }

    /**
     * Draws the axis on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location (determines where to draw the axis).
     * @param plotArea  the area within which the axes and plot should be drawn.
     * @param dataArea  the area within which the data should be drawn.
     * @param edge  the axis location (<code>null</code> not permitted).
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
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[86]++;
        
        AxisState axisState = new AxisState(cursor);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[87]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isAxisLineVisible()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[33]++;
            drawAxisLine(g2, cursor, dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[88]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[34]++;}
        drawTickMarks(g2, axisState, dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[89]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[90]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[4]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int band = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((band < this.labelInfo.length) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); band++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[4]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[5]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[6]++;
}
            axisState = drawTickLabels(band, g2, axisState, dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[91]++;
        }
        
        // draw the axis label (note that 'state' is passed in *and* 
        // returned)...
        axisState = drawLabel(getLabel(), g2, plotArea, dataArea, edge, 
                axisState);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[92]++;
        return axisState;
        
    }
    
    /**
     * Draws the tick marks for the axis.
     * 
     * @param g2  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the data area.
     * @param edge  the edge.
     */
    protected void drawTickMarks(Graphics2D g2, AxisState state, 
                                 Rectangle2D dataArea, 
                                 RectangleEdge edge) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[93]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[35]++;
            drawTickMarksHorizontal(g2, state, dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[94]++;

        }
        else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[36]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[95]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[37]++;
            drawTickMarksVertical(g2, state, dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[96]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[38]++;}
}
    }
    
    /**
     * Draws the major and minor tick marks for an axis that lies at the top or 
     * bottom of the plot.
     * 
     * @param g2  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the data area.
     * @param edge  the edge.
     */
    protected void drawTickMarksHorizontal(Graphics2D g2, AxisState state, 
                                           Rectangle2D dataArea, 
                                           RectangleEdge edge) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[97]++;
        List ticks = new ArrayList();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[98]++;
        double x0 = dataArea.getX();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[99]++;
        double y0 = state.getCursor();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[100]++;
        double insideLength = getTickMarkInsideLength();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[101]++;
        double outsideLength = getTickMarkOutsideLength();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[102]++;
        RegularTimePeriod t = RegularTimePeriod.createInstance(
                this.majorTickTimePeriodClass, this.first.getStart(), 
                getTimeZone());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[103]++;
        long t0 = t.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[104]++;
        Line2D inside = null;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[105]++;
        Line2D outside = null;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[106]++;
        long firstOnAxis = getFirst().getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[107]++;
        long lastOnAxis = getLast().getLastMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[108]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[7]++;


int CodeCoverConditionCoverageHelper_C22;
        while ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((t0 <= lastOnAxis) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[7]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[8]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[9]++;
}
            ticks.add(new NumberTick(new Double(t0), "", TextAnchor.CENTER, 
                    TextAnchor.CENTER, 0.0));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[109]++;
            x0 = valueToJava2D(t0, dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[110]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[111]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[39]++;
                inside = new Line2D.Double(x0, y0, x0, y0 + insideLength);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[112]++;  
                outside = new Line2D.Double(x0, y0, x0, y0 - outsideLength);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[113]++;

            }
            else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[40]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[114]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[41]++;
                inside = new Line2D.Double(x0, y0, x0, y0 - insideLength);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[115]++;
                outside = new Line2D.Double(x0, y0, x0, y0 + outsideLength);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[116]++;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[42]++;}
}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[117]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((t0 > firstOnAxis) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[43]++;
                g2.setPaint(getTickMarkPaint());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[118]++;
                g2.setStroke(getTickMarkStroke());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[119]++;
                g2.draw(inside);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[120]++;
                g2.draw(outside);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[121]++;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[44]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[122]++;
int CodeCoverConditionCoverageHelper_C26;
            // draw minor tick marks
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((this.minorTickMarksVisible) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[45]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[123]++;
                RegularTimePeriod tminor = RegularTimePeriod.createInstance(
                        this.minorTickTimePeriodClass, new Date(t0), 
                        getTimeZone());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[124]++;
                long tt0 = tminor.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[125]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[10]++;


int CodeCoverConditionCoverageHelper_C27;
                while ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((tt0 < t.getLastMillisecond(this.calendar)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((tt0 < lastOnAxis) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[10]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[11]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[12]++;
}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[126]++;
                    double xx0 = valueToJava2D(tt0, dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[127]++;
int CodeCoverConditionCoverageHelper_C28;
                    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[47]++;
                        inside = new Line2D.Double(xx0, y0, xx0, 
                                y0 + this.minorTickMarkInsideLength);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[128]++;
                        outside = new Line2D.Double(xx0, y0, xx0, 
                                y0 - this.minorTickMarkOutsideLength);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[129]++;

                    }
                    else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[48]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[130]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[49]++;
                        inside = new Line2D.Double(xx0, y0, xx0, 
                                y0 - this.minorTickMarkInsideLength);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[131]++;
                        outside = new Line2D.Double(xx0, y0, xx0, 
                                y0 + this.minorTickMarkOutsideLength);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[132]++;

                    } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[50]++;}
}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[133]++;
int CodeCoverConditionCoverageHelper_C30;
                    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((tt0 >= firstOnAxis) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[51]++;
                        g2.setPaint(this.minorTickMarkPaint);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[134]++;
                        g2.setStroke(this.minorTickMarkStroke);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[135]++;
                        g2.draw(inside);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[136]++;
                        g2.draw(outside);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[137]++;

                    } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[52]++;}
                    tminor = tminor.next();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[138]++;
                    tt0 = tminor.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[139]++;
                }

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[46]++;}            
            t = t.next();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[140]++;
            t0 = t.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[141]++;
        }
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[142]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[53]++;
            state.cursorUp(Math.max(outsideLength, 
                    this.minorTickMarkOutsideLength));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[143]++;

        }
        else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[54]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[144]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[55]++;
            state.cursorDown(Math.max(outsideLength, 
                    this.minorTickMarkOutsideLength));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[145]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[56]++;}
}
        state.setTicks(ticks);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[146]++;
    }
    
    /**
     * Draws the tick marks for a vertical axis.
     * 
     * @param g2  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the data area.
     * @param edge  the edge.
     */
    protected void drawTickMarksVertical(Graphics2D g2, AxisState state, 
                                         Rectangle2D dataArea, 
                                         RectangleEdge edge) {
        // FIXME:  implement this...       
    }
    
    /**
     * Draws the tick labels for one "band" of time periods.
     * 
     * @param band  the band index (zero-based).
     * @param g2  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the data area.
     * @param edge  the edge where the axis is located.
     * 
     * @return The updated axis state.
     */
    protected AxisState drawTickLabels(int band, Graphics2D g2, AxisState state,
                                       Rectangle2D dataArea, 
                                       RectangleEdge edge) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[147]++;

        // work out the initial gap
        double delta1 = 0.0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[148]++;
        FontMetrics fm = g2.getFontMetrics(this.labelInfo[band].getLabelFont());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[149]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[57]++;
            delta1 = this.labelInfo[band].getPadding().calculateTopOutset(
                    fm.getHeight());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[150]++;
   
        }
        else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[58]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[151]++;
int CodeCoverConditionCoverageHelper_C34; if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[59]++;
            delta1 = this.labelInfo[band].getPadding().calculateBottomOutset(
                    fm.getHeight());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[152]++;
   
        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[60]++;}
}
        state.moveCursor(delta1, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[153]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[154]++;
        long axisMin = this.first.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[155]++;
        long axisMax = this.last.getLastMillisecond(this.calendar);
        g2.setFont(this.labelInfo[band].getLabelFont());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[156]++;
        g2.setPaint(this.labelInfo[band].getLabelPaint());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[157]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[158]++;

        // work out the number of periods to skip for labelling
        RegularTimePeriod p1 = this.labelInfo[band].createInstance(
                new Date(axisMin), this.timeZone);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[159]++;
        RegularTimePeriod p2 = this.labelInfo[band].createInstance(
                new Date(axisMax), this.timeZone);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[160]++;
        String label1 = this.labelInfo[band].getDateFormat().format(
                new Date(p1.getMiddleMillisecond(this.calendar)));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[161]++;
        String label2 = this.labelInfo[band].getDateFormat().format(
                new Date(p2.getMiddleMillisecond(this.calendar)));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[162]++;
        Rectangle2D b1 = TextUtilities.getTextBounds(label1, g2, 
                g2.getFontMetrics());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[163]++;
        Rectangle2D b2 = TextUtilities.getTextBounds(label2, g2, 
                g2.getFontMetrics());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[164]++;
        double w = Math.max(b1.getWidth(), b2.getWidth());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[165]++;
        long ww = Math.round(java2DToValue(dataArea.getX() + w + 5.0, 
                dataArea, edge));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[166]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[61]++;
            ww = axisMax - ww;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[167]++;

        }
        else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[62]++;
            ww = ww - axisMin;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[168]++;
        }
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[169]++;
        long length = p1.getLastMillisecond(this.calendar) 
                      - p1.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[170]++;
        int periods = (int) (ww / length) + 1;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[171]++;
        
        RegularTimePeriod p = this.labelInfo[band].createInstance(
                new Date(axisMin), this.timeZone);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[172]++;
        Rectangle2D b = null;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[173]++;
        long lastXX = 0L;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[174]++;
        float y = (float) (state.getCursor());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[175]++;
        TextAnchor anchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[176]++;
        float yDelta = (float) b1.getHeight();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[177]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[63]++;
            anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[178]++;
            yDelta = -yDelta;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[179]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[64]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[180]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[13]++;


int CodeCoverConditionCoverageHelper_C37;
        while ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((p.getFirstMillisecond(this.calendar) <= axisMax) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[13]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[14]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[15]++;
}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[181]++;
            float x = (float) valueToJava2D(p.getMiddleMillisecond(
                    this.calendar), dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[182]++;
            DateFormat df = this.labelInfo[band].getDateFormat();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[183]++;
            String label = df.format(new Date(p.getMiddleMillisecond(
                    this.calendar)));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[184]++;
            long first = p.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[185]++;
            long last = p.getLastMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[186]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((last > axisMax) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[65]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[187]++;
                // this is the last period, but it is only partially visible 
                // so check that the label will fit before displaying it...
                Rectangle2D bb = TextUtilities.getTextBounds(label, g2, 
                        g2.getFontMetrics());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[188]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 (((x + bb.getWidth() / 2) > dataArea.getMaxX()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[67]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[189]++;
                    float xstart = (float) valueToJava2D(Math.max(first, 
                            axisMin), dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[190]++;
int CodeCoverConditionCoverageHelper_C40;
                    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((bb.getWidth() < (dataArea.getMaxX() - xstart)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[69]++;
                        x = ((float) dataArea.getMaxX() + xstart) / 2.0f;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[191]++;
   
                    }
                    else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[70]++;
                        label = null;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[192]++;
                    }

                } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[68]++;}

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[66]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[193]++;
int CodeCoverConditionCoverageHelper_C41;
            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((first < axisMin) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[71]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[194]++;
                // this is the first period, but it is only partially visible 
                // so check that the label will fit before displaying it...
                Rectangle2D bb = TextUtilities.getTextBounds(label, g2, 
                        g2.getFontMetrics());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[195]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 (((x - bb.getWidth() / 2) < dataArea.getX()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[73]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[196]++;
                    float xlast = (float) valueToJava2D(Math.min(last, 
                            axisMax), dataArea, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[197]++;
int CodeCoverConditionCoverageHelper_C43;
                    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((bb.getWidth() < (xlast - dataArea.getX())) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[75]++;
                        x = (xlast + (float) dataArea.getX()) / 2.0f;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[198]++;
   
                    }
                    else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[76]++;
                        label = null;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[199]++;
                    }

                } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[74]++;}

                
            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[72]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[200]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[77]++;
                g2.setPaint(this.labelInfo[band].getLabelPaint());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[201]++;
                b = TextUtilities.drawAlignedString(label, g2, x, y, anchor);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[202]++;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[78]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[203]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((lastXX > 0L) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[79]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[204]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((this.labelInfo[band].getDrawDividers()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[81]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[205]++;
                    long nextXX = p.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[206]++;
                    long mid = (lastXX + nextXX) / 2;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[207]++;
                    float mid2d = (float) valueToJava2D(mid, dataArea, edge);
                    g2.setStroke(this.labelInfo[band].getDividerStroke());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[208]++;
                    g2.setPaint(this.labelInfo[band].getDividerPaint());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[209]++;
                    g2.draw(new Line2D.Float(mid2d, y, mid2d, y + yDelta));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[210]++;

                } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[82]++;}

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[80]++;}
            lastXX = last;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[211]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[212]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[16]++;


int CodeCoverConditionCoverageHelper_C47;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((i < periods) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[16]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[17]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[18]++;
}
                p = p.next();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[213]++;   
            }
        }
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[214]++;
        double used = 0.0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[215]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((b != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[83]++;
            used = b.getHeight();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[216]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[217]++;
int CodeCoverConditionCoverageHelper_C49;
            // work out the trailing gap
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.BOTTOM) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[85]++;
                used += this.labelInfo[band].getPadding().calculateBottomOutset(
                        fm.getHeight());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[218]++;
   
            }
            else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[86]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[219]++;
int CodeCoverConditionCoverageHelper_C50; if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[87]++;
                used += this.labelInfo[band].getPadding().calculateTopOutset(
                        fm.getHeight());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[220]++;
   
            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[88]++;}
}

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[84]++;}
        state.moveCursor(used, edge);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[221]++;        
        return state;    
    }

    /**
     * Calculates the positions of the ticks for the axis, storing the results
     * in the tick list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the area inside the axes.
     * @param edge  the edge on which the axis is located.
     * 
     * @return The list of ticks.
     */
    public List refreshTicks(Graphics2D g2, 
                             AxisState state,
                             Rectangle2D dataArea,
                             RectangleEdge edge) {
        return Collections.EMPTY_LIST;
    }
    
    /**
     * Converts a data value to a coordinate in Java2D space, assuming that the
     * axis runs along one edge of the specified dataArea.
     * <p>
     * Note that it is possible for the coordinate to fall outside the area.
     *
     * @param value  the data value.
     * @param area  the area for plotting the data.
     * @param edge  the edge along which the axis lies.
     *
     * @return The Java2D coordinate.
     */
    public double valueToJava2D(double value,
                                Rectangle2D area,
                                RectangleEdge edge) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[222]++;
        
        double result = Double.NaN;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[223]++;
        double axisMin = this.first.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[224]++;
        double axisMax = this.last.getLastMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[225]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[89]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[226]++;
            double minX = area.getX();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[227]++;
            double maxX = area.getMaxX();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[228]++;
int CodeCoverConditionCoverageHelper_C52;
            if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[91]++;
                result = maxX + ((value - axisMin) / (axisMax - axisMin)) 
                         * (minX - maxX);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[229]++;

            }
            else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[92]++;
                result = minX + ((value - axisMin) / (axisMax - axisMin)) 
                         * (maxX - minX);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[230]++;
            }

        }
        else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[90]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[231]++;
int CodeCoverConditionCoverageHelper_C53; if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[93]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[232]++;
            double minY = area.getMinY();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[233]++;
            double maxY = area.getMaxY();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[234]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[95]++;
                result = minY + (((value - axisMin) / (axisMax - axisMin)) 
                         * (maxY - minY));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[235]++;

            }
            else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[96]++;
                result = maxY - (((value - axisMin) / (axisMax - axisMin)) 
                         * (maxY - minY));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[236]++;
            }

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[94]++;}
}
        return result;
        
    }

    /**
     * Converts a coordinate in Java2D space to the corresponding data value,
     * assuming that the axis runs along one edge of the specified dataArea.
     *
     * @param java2DValue  the coordinate in Java2D space.
     * @param area  the area in which the data is plotted.
     * @param edge  the edge along which the axis lies.
     *
     * @return The data value.
     */
    public double java2DToValue(double java2DValue,
                                Rectangle2D area,
                                RectangleEdge edge) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[237]++;

        double result = Double.NaN;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[238]++;
        double min = 0.0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[239]++;
        double max = 0.0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[240]++;
        double axisMin = this.first.getFirstMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[241]++;
        double axisMax = this.last.getLastMillisecond(this.calendar);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[242]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[97]++;
            min = area.getX();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[243]++;
            max = area.getMaxX();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[244]++;

        }
        else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[98]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[245]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[99]++;
            min = area.getMaxY();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[246]++;
            max = area.getY();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[247]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[100]++;}
}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[248]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[101]++;
             result = axisMax - ((java2DValue - min) / (max - min) 
                      * (axisMax - axisMin));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[249]++;

        }
        else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[102]++;
             result = axisMin + ((java2DValue - min) / (max - min) 
                      * (axisMax - axisMin));
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[250]++;
        }
        return result;
    }

    /**
     * Rescales the axis to ensure that all data is visible.
     */
    protected void autoAdjustRange() {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[251]++;

        Plot plot = getPlot();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[252]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[103]++;
            return;
  // no plot, no data
        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[104]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[253]++;
int CodeCoverConditionCoverageHelper_C59;

        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((plot instanceof ValueAxisPlot) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[105]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[254]++;
            ValueAxisPlot vap = (ValueAxisPlot) plot;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[255]++;

            Range r = vap.getDataRange(this);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[256]++;
int CodeCoverConditionCoverageHelper_C60;
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[107]++;
                r = getDefaultAutoRange();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[257]++;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[108]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[258]++;
            
            long upper = Math.round(r.getUpperBound());
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[259]++;
            long lower = Math.round(r.getLowerBound());
            this.first = createInstance(this.autoRangeTimePeriodClass, 
                    new Date(lower), this.timeZone);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[260]++;
            this.last = createInstance(this.autoRangeTimePeriodClass, 
                    new Date(upper), this.timeZone);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[261]++;
            setRange(r, false, false);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[262]++;

        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[106]++;}

    }
    
    /**
     * Tests the axis for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[263]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[109]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[110]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[264]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((obj instanceof PeriodAxis) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[111]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[265]++;
            PeriodAxis that = (PeriodAxis) obj;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[266]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((this.first.equals(that.first)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[113]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[114]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[267]++;
int CodeCoverConditionCoverageHelper_C64;
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((this.last.equals(that.last)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[115]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[116]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[268]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((this.timeZone.equals(that.timeZone)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[117]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[118]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[269]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((this.autoRangeTimePeriodClass.equals(
                    that.autoRangeTimePeriodClass)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[119]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[120]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[270]++;
int CodeCoverConditionCoverageHelper_C67;
            if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((isMinorTickMarksVisible() 
                    == that.isMinorTickMarksVisible()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[121]++;
                return false;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[122]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[271]++;
int CodeCoverConditionCoverageHelper_C68;
            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((this.majorTickTimePeriodClass.equals(
                    that.majorTickTimePeriodClass)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[123]++;
                return false;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[124]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[272]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((this.minorTickTimePeriodClass.equals(
                    that.minorTickTimePeriodClass)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[125]++;
                return false;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[126]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[273]++;
int CodeCoverConditionCoverageHelper_C70;
            if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((this.minorTickMarkPaint.equals(that.minorTickMarkPaint)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[127]++;
                return false;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[128]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[274]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((this.minorTickMarkStroke.equals(that.minorTickMarkStroke)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[129]++;
                return false;

            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[130]++;}
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[275]++;
int CodeCoverConditionCoverageHelper_C72;
            if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.labelInfo, that.labelInfo)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[131]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[132]++;}
            return true;
   
        } else {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[112]++;}
        return false;
    }

    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[276]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((getLabel() != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[133]++;
            return getLabel().hashCode();

        }
        else {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[134]++;
            return 0;
        }
    }
    
    /**
     * Returns a clone of the axis.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  this class is cloneable, but 
     *         subclasses may not be.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[277]++;
        PeriodAxis clone = (PeriodAxis) super.clone();
        clone.timeZone = (TimeZone) this.timeZone.clone();
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[278]++;
        clone.labelInfo = new PeriodAxisLabelInfo[this.labelInfo.length];
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[279]++;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[280]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[19]++;


int CodeCoverConditionCoverageHelper_C74;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((i < this.labelInfo.length) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[19]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[20]--;
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.loops[21]++;
}
            clone.labelInfo[i] = this.labelInfo[i];
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[281]++;  // copy across references 
                                                     // to immutable objs 
        }
        return clone;
    }
    
    /**
     * A utility method used to create a particular subclass of the 
     * {@link RegularTimePeriod} class that includes the specified millisecond, 
     * assuming the specified time zone.
     * 
     * @param periodClass  the class.
     * @param millisecond  the time.
     * @param zone  the time zone.
     * 
     * @return The time period.
     */
    private RegularTimePeriod createInstance(Class periodClass, 
                                             Date millisecond, TimeZone zone) {
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[282]++;
        RegularTimePeriod result = null;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[283]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[284]++;
            Constructor c = periodClass.getDeclaredConstructor(new Class[] {
                    Date.class, TimeZone.class});
            result = (RegularTimePeriod) c.newInstance(new Object[] {
                    millisecond, zone});
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[285]++;   
        }
        catch (Exception e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[136]++;
            // do nothing            
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.branches[135]++;
}
  }
        return result;
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
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[286]++;
        SerialUtilities.writeStroke(this.minorTickMarkStroke, stream);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[287]++;
        SerialUtilities.writePaint(this.minorTickMarkPaint, stream);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[288]++;
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
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[289]++;
        this.minorTickMarkStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[290]++;
        this.minorTickMarkPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p.statements[291]++;
    }

}

class CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p ());
  }
    public static long[] statements = new long[292];
    public static long[] branches = new long[137];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[75];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.PeriodAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 74; i++) {
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

  public CodeCoverCoverageCounter$o3g3a0goeiw1sa5tb174w2p () {
    super("org.jfree.chart.axis.PeriodAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 291; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 136; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 74; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.PeriodAxis.java");
      for (int i = 1; i <= 291; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 136; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 74; i++) {
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

