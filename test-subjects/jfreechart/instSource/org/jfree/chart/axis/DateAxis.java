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
 * -------------
 * DateAxis.java
 * -------------
 * (C) Copyright 2000-2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Jonathan Nash;
 *                   David Li;
 *                   Michael Rauch;
 *                   Bill Kelemen;
 *                   Pawel Pabis;
 *                   Chris Boek;
 *
 * Changes (from 23-Jun-2001)
 * --------------------------
 * 23-Jun-2001 : Modified to work with null data source (DG);
 * 18-Sep-2001 : Updated header (DG);
 * 27-Nov-2001 : Changed constructors from public to protected, updated Javadoc 
 *               comments (DG);
 * 16-Jan-2002 : Added an optional crosshair, based on the implementation by 
 *               Jonathan Nash (DG);
 * 26-Feb-2002 : Updated import statements (DG);
 * 22-Apr-2002 : Added a setRange() method (DG);
 * 25-Jun-2002 : Removed redundant local variable (DG);
 * 25-Jul-2002 : Changed order of parameters in ValueAxis constructor (DG);
 * 21-Aug-2002 : The setTickUnit() method now turns off auto-tick unit 
 *               selection (fix for bug id 528885) (DG);
 * 05-Sep-2002 : Updated the constructors to reflect changes in the Axis 
 *               class (DG);
 * 18-Sep-2002 : Fixed errors reported by Checkstyle (DG);
 * 25-Sep-2002 : Added new setRange() methods, and deprecated 
 *               setAxisRange() (DG);
 * 04-Oct-2002 : Changed auto tick selection to parallel number axis 
 *               classes (DG);
 * 24-Oct-2002 : Added a date format override (DG);
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 14-Jan-2003 : Changed autoRangeMinimumSize from Number --> double, moved
 *               crosshair settings to the plot (DG);
 * 15-Jan-2003 : Removed anchor date (DG);
 * 20-Jan-2003 : Removed unnecessary constructors (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 02-May-2003 : Added additional units to createStandardDateTickUnits() 
 *               method, as suggested by mhilpert in bug report 723187 (DG);
 * 13-May-2003 : Merged HorizontalDateAxis and VerticalDateAxis (DG);
 * 24-May-2003 : Added support for underlying timeline for 
 *               SegmentedTimeline (BK);
 * 16-Jul-2003 : Applied patch from Pawel Pabis to fix overlapping dates (DG);
 * 22-Jul-2003 : Applied patch from Pawel Pabis for monthly ticks (DG);
 * 25-Jul-2003 : Fixed bug 777561 and 777586 (DG);
 * 13-Aug-2003 : Implemented Cloneable and added equals() method (DG);
 * 02-Sep-2003 : Fixes for bug report 790506 (DG);
 * 04-Sep-2003 : Fixed tick label alignment when axis appears at the top (DG);
 * 10-Sep-2003 : Fixes for segmented timeline (DG);
 * 17-Sep-2003 : Fixed a layout bug when multiple domain axes are used (DG);
 * 29-Oct-2003 : Added workaround for font alignment in PDF output (DG);
 * 07-Nov-2003 : Modified to use new tick classes (DG);
 * 12-Nov-2003 : Modified tick labelling to use roll unit from DateTickUnit 
 *               when a calculated tick value is hidden (which can occur in 
 *               segmented date axes) (DG);
 * 24-Nov-2003 : Fixed some problems with the auto tick unit selection, and 
 *               fixed bug 846277 (labels missing for inverted axis) (DG);
 * 30-Dec-2003 : Fixed bug in refreshTicksHorizontal() when start of time unit 
 *               (ex. 1st of month) was hidden, causing infinite loop (BK);
 * 13-Jan-2004 : Fixed bug in previousStandardDate() method (fix by Richard 
 *               Wardle) (DG);
 * 21-Jan-2004 : Renamed translateJava2DToValue --> java2DToValue, and 
 *               translateValueToJava2D --> valueToJava2D (DG); 
 * 12-Mar-2004 : Fixed bug where date format override is ignored for vertical 
 *               axis (DG);
 * 16-Mar-2004 : Added plotState to draw() method (DG);
 * 07-Apr-2004 : Changed string width calculation (DG);
 * 21-Apr-2004 : Fixed bug in estimateMaximumTickLabelWidth() method (bug id 
 *               939148) (DG);
 * 11-Jan-2005 : Removed deprecated methods in preparation for 1.0.0 
 *               release (DG);
 * 13-Jan-2005 : Fixed bug (see 
 *               http://www.jfree.org/forum/viewtopic.php?t=11330) (DG);
 * 21-Apr-2005 : Replaced Insets with RectangleInsets, removed redundant 
 *               argument from selectAutoTickUnit() (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 10-Feb-2006 : Added some API doc comments in respect of bug 821046 (DG);
 * 19-Apr-2006 : Fixed bug 1472942 in equals() method (DG);
 * 25-Sep-2006 : Fixed bug 1564977 missing tick labels (DG);
 * 15-Jan-2007 : Added get/setTimeZone() suggested by 'skunk' (DG);
 * 18-Jan-2007 : Fixed bug 1638678, time zone for calendar in 
 *               previousStandardDate() (DG);
 * 04-Apr-2007 : Use time zone in date calculations (CB);
 * 19-Apr-2007 : Fix exceptions in setMinimum/MaximumDate() (DG);
 * 03-May-2007 : Fixed minor bugs in previousStandardDate(), with new JUnit
 *               tests (DG);
 * 21-Nov-2007 : Fixed warnings from FindBugs (DG);
 * 
 */

package org.jfree.chart.axis;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.data.Range;
import org.jfree.data.time.DateRange;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Year;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectUtilities;

/**
 * The base class for axes that display dates.  You will find it easier to 
 * understand how this axis works if you bear in mind that it really 
 * displays/measures integer (or long) data, where the integers are 
 * milliseconds since midnight, 1-Jan-1970.  When displaying tick labels, the 
 * millisecond values are converted back to dates using a 
 * <code>DateFormat</code> instance.
 * <P>
 * You can also create a {@link org.jfree.chart.axis.Timeline} and supply in 
 * the constructor to create an axis that only contains certain domain values. 
 * For example, this allows you to create a date axis that only contains 
 * working days.
 */
public class DateAxis extends ValueAxis implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -1013460999649007604L;
  static {
    CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[1]++;
  }
    
    /** The default axis range. */
    public static final DateRange DEFAULT_DATE_RANGE = new DateRange();
  static {
    CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[2]++;
  }

    /** The default minimum auto range size. */
    public static final double 
            DEFAULT_AUTO_RANGE_MINIMUM_SIZE_IN_MILLISECONDS = 2.0;
  static {
    CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[3]++;
  }

    /** The default date tick unit. */
    public static final DateTickUnit DEFAULT_DATE_TICK_UNIT
            = new DateTickUnit(DateTickUnit.DAY, 1, new SimpleDateFormat());
  static {
    CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[4]++;
  }

    /** The default anchor date. */
    public static final Date DEFAULT_ANCHOR_DATE = new Date();
  static {
    CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[5]++;
  }

    /** The current tick unit. */
    private DateTickUnit tickUnit;

    /** The override date format. */
    private DateFormat dateFormatOverride;

    /** 
     * Tick marks can be displayed at the start or the middle of the time 
     * period. 
     */
    private DateTickMarkPosition tickMarkPosition = DateTickMarkPosition.START;
  {
    CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[6]++;
  }

    /**
     * A timeline that includes all milliseconds (as defined by 
     * <code>java.util.Date</code>) in the real time line.
     */
    private static class DefaultTimeline implements Timeline, Serializable {

        /**
         * Converts a millisecond into a timeline value.
         *
         * @param millisecond  the millisecond.
         *
         * @return The timeline value.
         */
        public long toTimelineValue(long millisecond) {
            return millisecond;
        }

        /**
         * Converts a date into a timeline value.
         *
         * @param date  the domain value.
         *
         * @return The timeline value.
         */
        public long toTimelineValue(Date date) {
            return date.getTime();
        }

        /**
         * Converts a timeline value into a millisecond (as encoded by 
         * <code>java.util.Date</code>).
         *
         * @param value  the value.
         *
         * @return The millisecond.
         */
        public long toMillisecond(long value) {
            return value;
        }

        /**
         * Returns <code>true</code> if the timeline includes the specified 
         * domain value.
         *
         * @param millisecond  the millisecond.
         *
         * @return <code>true</code>.
         */
        public boolean containsDomainValue(long millisecond) {
            return true;
        }

        /**
         * Returns <code>true</code> if the timeline includes the specified 
         * domain value.
         *
         * @param date  the date.
         *
         * @return <code>true</code>.
         */
        public boolean containsDomainValue(Date date) {
            return true;
        }

        /**
         * Returns <code>true</code> if the timeline includes the specified 
         * domain value range.
         *
         * @param from  the start value.
         * @param to  the end value.
         *
         * @return <code>true</code>.
         */
        public boolean containsDomainRange(long from, long to) {
            return true;
        }

        /**
         * Returns <code>true</code> if the timeline includes the specified 
         * domain value range.
         *
         * @param from  the start date.
         * @param to  the end date.
         *
         * @return <code>true</code>.
         */
        public boolean containsDomainRange(Date from, Date to) {
            return true;
        }

        /**
         * Tests an object for equality with this instance.
         *
         * @param object  the object.
         *
         * @return A boolean.
         */
        public boolean equals(Object object) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((object == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[1]++;
                return false;

            } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[2]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((object == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[3]++;
                return true;

            } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[4]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((object instanceof DefaultTimeline) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[5]++;
                return true;

            } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[6]++;}
            return false;
        }
    }

    /** A static default timeline shared by all standard DateAxis */
    private static final Timeline DEFAULT_TIMELINE = new DefaultTimeline();
  static {
    CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[10]++;
  }

    /** The time zone for the axis. */
    private TimeZone timeZone;
    
    /** Our underlying timeline. */
    private Timeline timeline;

    /**
     * Creates a date axis with no label.
     */
    public DateAxis() {
        this(null);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[11]++;
    }

    /**
     * Creates a date axis with the specified label.
     *
     * @param label  the axis label (<code>null</code> permitted).
     */
    public DateAxis(String label) {
        this(label, TimeZone.getDefault());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[12]++;
    }

    /**
     * Creates a date axis. A timeline is specified for the axis. This allows 
     * special transformations to occur between a domain of values and the 
     * values included in the axis.
     *
     * @see org.jfree.chart.axis.SegmentedTimeline
     *
     * @param label  the axis label (<code>null</code> permitted).
     * @param zone  the time zone.
     */
    public DateAxis(String label, TimeZone zone) {
        super(label, DateAxis.createStandardDateTickUnits(zone));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[13]++;
        setTickUnit(DateAxis.DEFAULT_DATE_TICK_UNIT, false, false);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[14]++;
        setAutoRangeMinimumSize(
                DEFAULT_AUTO_RANGE_MINIMUM_SIZE_IN_MILLISECONDS);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[15]++;
        setRange(DEFAULT_DATE_RANGE, false, false);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[16]++;
        this.dateFormatOverride = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[17]++;
        this.timeZone = zone;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[18]++;
        this.timeline = DEFAULT_TIMELINE;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[19]++;
    }

    /**
     * Returns the time zone for the axis.
     * 
     * @return The time zone.
     * 
     * @since 1.0.4
     * @see #setTimeZone(TimeZone)
     */
    public TimeZone getTimeZone() {
        return this.timeZone;
    }
    
    /**
     * Sets the time zone for the axis and sends an {@link AxisChangeEvent} to
     * all registered listeners.
     * 
     * @param zone  the time zone (<code>null</code> not permitted).
     * 
     * @since 1.0.4
     * @see #getTimeZone()
     */
    public void setTimeZone(TimeZone zone) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.timeZone.equals(zone)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[7]++;
            this.timeZone = zone;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[21]++;
            setStandardTickUnits(createStandardDateTickUnits(zone));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[22]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[23]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[8]++;}
    } 
    
    /**
     * Returns the underlying timeline used by this axis.
     *
     * @return The timeline.
     */
    public Timeline getTimeline() {
        return this.timeline;
    }

    /**
     * Sets the underlying timeline to use for this axis.
     * <P>
     * If the timeline is changed, an {@link AxisChangeEvent} is sent to all
     * registered listeners.
     *
     * @param timeline  the timeline.
     */
    public void setTimeline(Timeline timeline) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.timeline != timeline) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[9]++;
            this.timeline = timeline;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[25]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[26]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[10]++;}
    }

    /**
     * Returns the tick unit for the axis.
     * <p>
     * Note: if the <code>autoTickUnitSelection</code> flag is 
     * <code>true</code> the tick unit may be changed while the axis is being 
     * drawn, so in that case the return value from this method may be
     * irrelevant if the method is called before the axis has been drawn.
     *
     * @return The tick unit (possibly <code>null</code>).
     * 
     * @see #setTickUnit(DateTickUnit)
     * @see ValueAxis#isAutoTickUnitSelection()
     */
    public DateTickUnit getTickUnit() {
        return this.tickUnit;
    }

    /**
     * Sets the tick unit for the axis.  The auto-tick-unit-selection flag is 
     * set to <code>false</code>, and registered listeners are notified that 
     * the axis has been changed.
     *
     * @param unit  the tick unit.
     * 
     * @see #getTickUnit()
     * @see #setTickUnit(DateTickUnit, boolean, boolean)
     */
    public void setTickUnit(DateTickUnit unit) {
        setTickUnit(unit, true, true);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[27]++;
    }

    /**
     * Sets the tick unit attribute.
     *
     * @param unit  the new tick unit.
     * @param notify  notify registered listeners?
     * @param turnOffAutoSelection  turn off auto selection?
     * 
     * @see #getTickUnit()
     */
    public void setTickUnit(DateTickUnit unit, boolean notify, 
                            boolean turnOffAutoSelection) {

        this.tickUnit = unit;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[28]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((turnOffAutoSelection) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[11]++;
            setAutoTickUnitSelection(false, false);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[30]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[12]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((notify) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[13]++;
            notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[32]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[14]++;}

    }

    /**
     * Returns the date format override.  If this is non-null, then it will be
     * used to format the dates on the axis.
     *
     * @return The formatter (possibly <code>null</code>).
     */
    public DateFormat getDateFormatOverride() {
        return this.dateFormatOverride;
    }

    /**
     * Sets the date format override.  If this is non-null, then it will be 
     * used to format the dates on the axis.
     *
     * @param formatter  the date formatter (<code>null</code> permitted).
     */
    public void setDateFormatOverride(DateFormat formatter) {
        this.dateFormatOverride = formatter;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[33]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[34]++;
    }

    /**
     * Sets the upper and lower bounds for the axis and sends an 
     * {@link AxisChangeEvent} to all registered listeners.  As a side-effect, 
     * the auto-range flag is set to false.
     *
     * @param range  the new range (<code>null</code> not permitted).
     */
    public void setRange(Range range) {
        setRange(range, true, true);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[35]++;
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
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[15]++;
            throw new IllegalArgumentException("Null 'range' argument.");

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[16]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
        // usually the range will be a DateRange, but if it isn't do a 
        // conversion...
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((range instanceof DateRange) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[17]++;
            range = new DateRange(range);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[38]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[18]++;}
        super.setRange(range, turnOffAutoRange, notify);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[39]++;
    }

    /**
     * Sets the axis range and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     *
     * @param lower  the lower bound for the axis.
     * @param upper  the upper bound for the axis.
     */
    public void setRange(Date lower, Date upper) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[40]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((lower.getTime() >= upper.getTime()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[19]++;
            throw new IllegalArgumentException("Requires 'lower' < 'upper'.");

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[20]++;}
        setRange(new DateRange(lower, upper));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[41]++;
    }

    /**
     * Sets the axis range and sends an {@link AxisChangeEvent} to all 
     * registered listeners.
     *
     * @param lower  the lower bound for the axis.
     * @param upper  the upper bound for the axis.
     */
    public void setRange(double lower, double upper) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((lower >= upper) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[21]++;
            throw new IllegalArgumentException("Requires 'lower' < 'upper'.");

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[22]++;}
        setRange(new DateRange(lower, upper));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[43]++;
    }

    /**
     * Returns the earliest date visible on the axis.
     *
     * @return The date.
     * 
     * @see #setMinimumDate(Date)
     * @see #getMaximumDate()
     */
    public Date getMinimumDate() {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[44]++;
        Date result = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[45]++;
        Range range = getRange();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[46]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((range instanceof DateRange) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[23]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[47]++;
            DateRange r = (DateRange) range;
            result = r.getLowerDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[48]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[24]++;
            result = new Date((long) range.getLowerBound());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[49]++;
        }
        return result;
    }

    /**
     * Sets the minimum date visible on the axis and sends an 
     * {@link AxisChangeEvent} to all registered listeners.  If 
     * <code>date</code> is on or after the current maximum date for 
     * the axis, the maximum date will be shifted to preserve the current
     * length of the axis.
     *
     * @param date  the date (<code>null</code> not permitted).
     * 
     * @see #getMinimumDate()
     * @see #setMaximumDate(Date)
     */
    public void setMinimumDate(Date date) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[50]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((date == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[25]++;
            throw new IllegalArgumentException("Null 'date' argument.");

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[26]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[51]++;
        // check the new minimum date relative to the current maximum date
        Date maxDate = getMaximumDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[52]++;
        long maxMillis = maxDate.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[53]++;
        long newMinMillis = date.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((maxMillis <= newMinMillis) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[27]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[55]++;
            Date oldMin = getMinimumDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[56]++;
            long length = maxMillis - oldMin.getTime();
            maxDate = new Date(newMinMillis + length);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[57]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[28]++;}
        setRange(new DateRange(date, maxDate), true, false);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[58]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[59]++;
    }

    /**
     * Returns the latest date visible on the axis.
     *
     * @return The date.
     * 
     * @see #setMaximumDate(Date)
     * @see #getMinimumDate()
     */
    public Date getMaximumDate() {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[60]++;
        Date result = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[61]++;
        Range range = getRange();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[62]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((range instanceof DateRange) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[29]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[63]++;
            DateRange r = (DateRange) range;
            result = r.getUpperDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[64]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[30]++;
            result = new Date((long) range.getUpperBound());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[65]++;
        }
        return result;
    }

    /**
     * Sets the maximum date visible on the axis and sends an 
     * {@link AxisChangeEvent} to all registered listeners.  If 
     * <code>maximumDate</code> is on or before the current minimum date for 
     * the axis, the minimum date will be shifted to preserve the current
     * length of the axis.
     *
     * @param maximumDate  the date (<code>null</code> not permitted).
     * 
     * @see #getMinimumDate()
     * @see #setMinimumDate(Date)
     */
    public void setMaximumDate(Date maximumDate) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[66]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((maximumDate == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[31]++;
            throw new IllegalArgumentException("Null 'maximumDate' argument.");

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[32]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[67]++;
        // check the new maximum date relative to the current minimum date
        Date minDate = getMinimumDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[68]++;
        long minMillis = minDate.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[69]++;
        long newMaxMillis = maximumDate.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((minMillis >= newMaxMillis) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[33]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[71]++;
            Date oldMax = getMaximumDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[72]++;
            long length = oldMax.getTime() - minMillis;
            minDate = new Date(newMaxMillis - length);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[73]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[34]++;}
        setRange(new DateRange(minDate, maximumDate), true, false);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[74]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[75]++;
    }

    /**
     * Returns the tick mark position (start, middle or end of the time period).
     *
     * @return The position (never <code>null</code>).
     */
    public DateTickMarkPosition getTickMarkPosition() {
        return this.tickMarkPosition;
    }

    /**
     * Sets the tick mark position (start, middle or end of the time period) 
     * and sends an {@link AxisChangeEvent} to all registered listeners.
     *
     * @param position  the position (<code>null</code> not permitted).
     */
    public void setTickMarkPosition(DateTickMarkPosition position) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[76]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[35]++;
            throw new IllegalArgumentException("Null 'position' argument.");

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[36]++;}
        this.tickMarkPosition = position;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[77]++;
        notifyListeners(new AxisChangeEvent(this));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[78]++;
    }

    /**
     * Configures the axis to work with the specified plot.  If the axis has
     * auto-scaling, then sets the maximum and minimum values.
     */
    public void configure() {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[79]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((isAutoRange()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[37]++;
            autoAdjustRange();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[80]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[38]++;}
    }

    /**
     * Returns <code>true</code> if the axis hides this value, and 
     * <code>false</code> otherwise.
     *
     * @param millis  the data value.
     *
     * @return A value.
     */
    public boolean isHiddenValue(long millis) {
        return (!this.timeline.containsDomainValue(new Date(millis)));
    }

    /**
     * Translates the data value to the display coordinates (Java 2D User Space)
     * of the chart.
     *
     * @param value  the date to be plotted.
     * @param area  the rectangle (in Java2D space) where the data is to be 
     *              plotted.
     * @param edge  the axis location.
     *
     * @return The coordinate corresponding to the supplied data value.
     */
    public double valueToJava2D(double value, Rectangle2D area, 
                                RectangleEdge edge) {
        
        value = this.timeline.toTimelineValue((long) value);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[81]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[82]++;

        DateRange range = (DateRange) getRange();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[83]++;
        double axisMin = this.timeline.toTimelineValue(range.getLowerDate());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[84]++;
        double axisMax = this.timeline.toTimelineValue(range.getUpperDate());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[85]++;
        double result = 0.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[86]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[39]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[87]++;
            double minX = area.getX();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[88]++;
            double maxX = area.getMaxX();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[89]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[41]++;
                result = maxX + ((value - axisMin) / (axisMax - axisMin)) 
                         * (minX - maxX);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[90]++;

            }
            else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[42]++;
                result = minX + ((value - axisMin) / (axisMax - axisMin)) 
                         * (maxX - minX);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[91]++;
            }

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[40]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[92]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[43]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[93]++;
            double minY = area.getMinY();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[94]++;
            double maxY = area.getMaxY();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[95]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[45]++;
                result = minY + (((value - axisMin) / (axisMax - axisMin)) 
                         * (maxY - minY));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[96]++;

            }
            else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[46]++;
                result = maxY - (((value - axisMin) / (axisMax - axisMin)) 
                         * (maxY - minY));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[97]++;
            }

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[44]++;}
}
        return result;

    }

    /**
     * Translates a date to Java2D coordinates, based on the range displayed by
     * this axis for the specified data area.
     *
     * @param date  the date.
     * @param area  the rectangle (in Java2D space) where the data is to be
     *              plotted.
     * @param edge  the axis location.
     *
     * @return The coordinate corresponding to the supplied date.
     */
    public double dateToJava2D(Date date, Rectangle2D area, 
                               RectangleEdge edge) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[98]++;  
        double value = date.getTime();
        return valueToJava2D(value, area, edge);
    }

    /**
     * Translates a Java2D coordinate into the corresponding data value.  To 
     * perform this translation, you need to know the area used for plotting 
     * data, and which edge the axis is located on.
     *
     * @param java2DValue  the coordinate in Java2D space.
     * @param area  the rectangle (in Java2D space) where the data is to be 
     *              plotted.
     * @param edge  the axis location.
     *
     * @return A data value.
     */
    public double java2DToValue(double java2DValue, Rectangle2D area, 
                                RectangleEdge edge) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[99]++;
        
        DateRange range = (DateRange) getRange();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[100]++;
        double axisMin = this.timeline.toTimelineValue(range.getLowerDate());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[101]++;
        double axisMax = this.timeline.toTimelineValue(range.getUpperDate());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[102]++;

        double min = 0.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[103]++;
        double max = 0.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[104]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[47]++;
            min = area.getX();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[105]++;
            max = area.getMaxX();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[106]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[48]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[107]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[49]++;
            min = area.getMaxY();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[108]++;
            max = area.getY();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[109]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[50]++;}
}

        double result;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[110]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[51]++;
             result = axisMax - ((java2DValue - min) / (max - min) 
                      * (axisMax - axisMin));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[111]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[52]++;
             result = axisMin + ((java2DValue - min) / (max - min) 
                      * (axisMax - axisMin));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[112]++;
        }

        return this.timeline.toMillisecond((long) result); 
    }

    /**
     * Calculates the value of the lowest visible tick on the axis.
     *
     * @param unit  date unit to use.
     *
     * @return The value of the lowest visible tick on the axis.
     */
    public Date calculateLowestVisibleTickValue(DateTickUnit unit) {
        return nextStandardDate(getMinimumDate(), unit);
    }

    /**
     * Calculates the value of the highest visible tick on the axis.
     *
     * @param unit  date unit to use.
     *
     * @return The value of the highest visible tick on the axis.
     */
    public Date calculateHighestVisibleTickValue(DateTickUnit unit) {
        return previousStandardDate(getMaximumDate(), unit);
    }
    
    /**
     * Returns the previous "standard" date, for a given date and tick unit.
     *
     * @param date  the reference date.
     * @param unit  the tick unit.
     *
     * @return The previous "standard" date.
     */
    protected Date previousStandardDate(Date date, DateTickUnit unit) {

        int milliseconds;
        int seconds;
        int minutes;
        int hours;
        int days;
        int months;
        int years;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[113]++;

        Calendar calendar = Calendar.getInstance(this.timeZone);
        calendar.setTime(date);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[114]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[115]++;
        int count = unit.getCount();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[116]++;
        int current = calendar.get(unit.getCalendarField());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[117]++;
        int value = count * (current / count);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[118]++;

        switch (unit.getUnit()) {

            case (DateTickUnit.MILLISECOND) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[53]++;
                years = calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[119]++;
                months = calendar.get(Calendar.MONTH);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[120]++;
                days = calendar.get(Calendar.DATE);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[121]++;
                hours = calendar.get(Calendar.HOUR_OF_DAY);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[122]++;
                minutes = calendar.get(Calendar.MINUTE);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[123]++;
                seconds = calendar.get(Calendar.SECOND);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[124]++;
                calendar.set(years, months, days, hours, minutes, seconds);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[125]++;
                calendar.set(Calendar.MILLISECOND, value);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[126]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[127]++;
                Date mm = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[128]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((mm.getTime() >= date.getTime()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[54]++;
                    calendar.set(Calendar.MILLISECOND, value - 1);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[129]++;
                    mm = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[130]++;

                } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[55]++;}
                return mm;

            case (DateTickUnit.SECOND) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[56]++;
                years = calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[131]++;
                months = calendar.get(Calendar.MONTH);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[132]++;
                days = calendar.get(Calendar.DATE);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[133]++;
                hours = calendar.get(Calendar.HOUR_OF_DAY);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[134]++;
                minutes = calendar.get(Calendar.MINUTE);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[135]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[136]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.START) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[57]++;
                    milliseconds = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[137]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[58]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[138]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[59]++;
                    milliseconds = 500;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[139]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[60]++;
                    milliseconds = 999;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[140]++;
                }
}
                calendar.set(Calendar.MILLISECOND, milliseconds);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[141]++;
                calendar.set(years, months, days, hours, minutes, value);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[142]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[143]++;
                Date dd = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[144]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((dd.getTime() >= date.getTime()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[61]++;
                    calendar.set(Calendar.SECOND, value - 1);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[145]++;
                    dd = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[146]++;

                } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[62]++;}
                return dd;

            case (DateTickUnit.MINUTE) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[63]++;
                years = calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[147]++;
                months = calendar.get(Calendar.MONTH);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[148]++;
                days = calendar.get(Calendar.DATE);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[149]++;
                hours = calendar.get(Calendar.HOUR_OF_DAY);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[150]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[151]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.START) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[64]++;
                    seconds = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[152]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[65]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[153]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[66]++;
                    seconds = 30;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[154]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[67]++;
                    seconds = 59;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[155]++;
                }
}
                calendar.clear(Calendar.MILLISECOND);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[156]++;
                calendar.set(years, months, days, hours, value, seconds);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[157]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[158]++;
                Date d0 = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[159]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((d0.getTime() >= date.getTime()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[68]++;
                    calendar.set(Calendar.MINUTE, value - 1);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[160]++;
                    d0 = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[161]++;

                } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[69]++;}
                return d0;

            case (DateTickUnit.HOUR) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[70]++;
                years = calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[162]++;
                months = calendar.get(Calendar.MONTH);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[163]++;
                days = calendar.get(Calendar.DATE);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[164]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[165]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.START) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[71]++;
                    minutes = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[166]++;
                    seconds = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[167]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[72]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[168]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[73]++;
                    minutes = 30;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[169]++;
                    seconds = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[170]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[74]++;
                    minutes = 59;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[171]++;
                    seconds = 59;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[172]++;
                }
}
                calendar.clear(Calendar.MILLISECOND);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[173]++;
                calendar.set(years, months, days, value, minutes, seconds);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[174]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[175]++;
                Date d1 = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[176]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((d1.getTime() >= date.getTime()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[75]++;
                    calendar.set(Calendar.HOUR_OF_DAY, value - 1);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[177]++;
                    d1 = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[178]++;

                } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[76]++;}
                return d1;

            case (DateTickUnit.DAY) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[77]++;
                years = calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[179]++;
                months = calendar.get(Calendar.MONTH);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[180]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[181]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.START) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[78]++;
                    hours = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[182]++;
                    minutes = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[183]++;
                    seconds = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[184]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[79]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[185]++;
int CodeCoverConditionCoverageHelper_C38; if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[80]++;
                    hours = 12;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[186]++;
                    minutes = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[187]++;
                    seconds = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[188]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[81]++;
                    hours = 23;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[189]++;
                    minutes = 59;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[190]++;
                    seconds = 59;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[191]++;
                }
}
                calendar.clear(Calendar.MILLISECOND);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[192]++;
                calendar.set(years, months, value, hours, 0, 0);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[193]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[194]++;
                // long result = calendar.getTimeInMillis();  
                    // won't work with JDK 1.3
                Date d2 = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[195]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((d2.getTime() >= date.getTime()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[82]++;
                    calendar.set(Calendar.DATE, value - 1);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[196]++;
                    d2 = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[197]++;

                } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[83]++;}
                return d2;

            case (DateTickUnit.MONTH) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[84]++;
                years = calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[198]++;
                calendar.clear(Calendar.MILLISECOND);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[199]++;
                calendar.set(years, value, 1, 0, 0, 0);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[200]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[201]++;
                Month month = new Month(calendar.getTime(), this.timeZone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[202]++;
                Date standardDate = calculateDateForPosition(
                        month, this.tickMarkPosition);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[203]++;
                long millis = standardDate.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[204]++;
int CodeCoverConditionCoverageHelper_C40;
                if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((millis >= date.getTime()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[85]++;
                    month = (Month) month.previous();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[205]++;
                    standardDate = calculateDateForPosition(
                            month, this.tickMarkPosition);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[206]++;

                } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[86]++;}
                return standardDate;

            case(DateTickUnit.YEAR) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[87]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[207]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.START) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[88]++;
                    months = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[208]++;
                    days = 1;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[209]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[89]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[210]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((this.tickMarkPosition == DateTickMarkPosition.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[90]++;
                    months = 6;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[211]++;
                    days = 1;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[212]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[91]++;
                    months = 11;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[213]++;
                    days = 31;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[214]++;
                }
}
                calendar.clear(Calendar.MILLISECOND);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[215]++;
                calendar.set(value, months, days, 0, 0, 0);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[216]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[217]++;
                Date d3 = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[218]++;
int CodeCoverConditionCoverageHelper_C43;
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((d3.getTime() >= date.getTime()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[92]++;
                    calendar.set(Calendar.YEAR, value - 1);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[219]++;
                    d3 = calendar.getTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[220]++;

                } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[93]++;}
                return d3;

            default:
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[94]++; return null;

        }

    }

    /**
     * Returns a {@link java.util.Date} corresponding to the specified position
     * within a {@link RegularTimePeriod}.
     *
     * @param period  the period.
     * @param position  the position (<code>null</code> not permitted).
     *
     * @return A date.
     */
    private Date calculateDateForPosition(RegularTimePeriod period, 
                                          DateTickMarkPosition position) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[221]++;
int CodeCoverConditionCoverageHelper_C44;
        
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((position == null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[95]++;
            throw new IllegalArgumentException("Null 'position' argument.");
   
        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[96]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[222]++;
        Date result = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[223]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((position == DateTickMarkPosition.START) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[97]++;
            result = new Date(period.getFirstMillisecond());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[224]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[98]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[225]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((position == DateTickMarkPosition.MIDDLE) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[99]++;
            result = new Date(period.getMiddleMillisecond());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[226]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[100]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[227]++;
int CodeCoverConditionCoverageHelper_C47; if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((position == DateTickMarkPosition.END) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[101]++;
            result = new Date(period.getLastMillisecond());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[228]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[102]++;}
}
}
        return result;

    }

    /**
     * Returns the first "standard" date (based on the specified field and 
     * units).
     *
     * @param date  the reference date.
     * @param unit  the date tick unit.
     *
     * @return The next "standard" date.
     */
    protected Date nextStandardDate(Date date, DateTickUnit unit) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[229]++;
        Date previous = previousStandardDate(date, unit);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[230]++;
        Calendar calendar = Calendar.getInstance(this.timeZone);
        calendar.setTime(previous);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[231]++;
        calendar.add(unit.getCalendarField(), unit.getCount());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[232]++;
        return calendar.getTime();
    }

    /**
     * Returns a collection of standard date tick units that uses the default 
     * time zone.  This collection will be used by default, but you are free 
     * to create your own collection if you want to (see the 
     * {@link ValueAxis#setStandardTickUnits(TickUnitSource)} method inherited 
     * from the {@link ValueAxis} class).
     *
     * @return A collection of standard date tick units.
     */
    public static TickUnitSource createStandardDateTickUnits() {
        return createStandardDateTickUnits(TimeZone.getDefault());
    }

    /**
     * Returns a collection of standard date tick units.  This collection will 
     * be used by default, but you are free to create your own collection if 
     * you want to (see the 
     * {@link ValueAxis#setStandardTickUnits(TickUnitSource)} method inherited 
     * from the {@link ValueAxis} class).
     *
     * @param zone  the time zone (<code>null</code> not permitted).
     * 
     * @return A collection of standard date tick units.
     */
    public static TickUnitSource createStandardDateTickUnits(TimeZone zone) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[233]++;
int CodeCoverConditionCoverageHelper_C48;

        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[103]++;
            throw new IllegalArgumentException("Null 'zone' argument.");

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[104]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[234]++;
        TickUnits units = new TickUnits();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[235]++;

        // date formatters
        DateFormat f1 = new SimpleDateFormat("HH:mm:ss.SSS");
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[236]++;
        DateFormat f2 = new SimpleDateFormat("HH:mm:ss");
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[237]++;
        DateFormat f3 = new SimpleDateFormat("HH:mm");
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[238]++;
        DateFormat f4 = new SimpleDateFormat("d-MMM, HH:mm");
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[239]++;
        DateFormat f5 = new SimpleDateFormat("d-MMM");
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[240]++;
        DateFormat f6 = new SimpleDateFormat("MMM-yyyy");
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[241]++;
        DateFormat f7 = new SimpleDateFormat("yyyy");
        
        f1.setTimeZone(zone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[242]++;
        f2.setTimeZone(zone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[243]++;
        f3.setTimeZone(zone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[244]++;
        f4.setTimeZone(zone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[245]++;
        f5.setTimeZone(zone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[246]++;
        f6.setTimeZone(zone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[247]++;
        f7.setTimeZone(zone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[248]++;
        
        // milliseconds
        units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 1, f1));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[249]++;
        units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 5, 
                DateTickUnit.MILLISECOND, 1, f1));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[250]++;
        units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 10, 
                DateTickUnit.MILLISECOND, 1, f1));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[251]++;
        units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 25, 
                DateTickUnit.MILLISECOND, 5, f1));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[252]++;
        units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 50, 
                DateTickUnit.MILLISECOND, 10, f1));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[253]++;
        units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 100, 
                DateTickUnit.MILLISECOND, 10, f1));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[254]++;
        units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 250, 
                DateTickUnit.MILLISECOND, 10, f1));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[255]++;
        units.add(new DateTickUnit(DateTickUnit.MILLISECOND, 500, 
                DateTickUnit.MILLISECOND, 50, f1));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[256]++;

        // seconds
        units.add(new DateTickUnit(DateTickUnit.SECOND, 1, 
                DateTickUnit.MILLISECOND, 50, f2));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[257]++;
        units.add(new DateTickUnit(DateTickUnit.SECOND, 5, 
                DateTickUnit.SECOND, 1, f2));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[258]++;
        units.add(new DateTickUnit(DateTickUnit.SECOND, 10, 
                DateTickUnit.SECOND, 1, f2));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[259]++;
        units.add(new DateTickUnit(DateTickUnit.SECOND, 30, 
                DateTickUnit.SECOND, 5, f2));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[260]++;

        // minutes
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 1, 
                DateTickUnit.SECOND, 5, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[261]++;
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 2, 
                DateTickUnit.SECOND, 10, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[262]++;
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 5, 
                DateTickUnit.MINUTE, 1, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[263]++;
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 10, 
                DateTickUnit.MINUTE, 1, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[264]++;
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 15, 
                DateTickUnit.MINUTE, 5, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[265]++;
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 20, 
                DateTickUnit.MINUTE, 5, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[266]++;
        units.add(new DateTickUnit(DateTickUnit.MINUTE, 30, 
                DateTickUnit.MINUTE, 5, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[267]++;

        // hours
        units.add(new DateTickUnit(DateTickUnit.HOUR, 1, 
                DateTickUnit.MINUTE, 5, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[268]++;
        units.add(new DateTickUnit(DateTickUnit.HOUR, 2, 
                DateTickUnit.MINUTE, 10, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[269]++;
        units.add(new DateTickUnit(DateTickUnit.HOUR, 4, 
                DateTickUnit.MINUTE, 30, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[270]++;
        units.add(new DateTickUnit(DateTickUnit.HOUR, 6, 
                DateTickUnit.HOUR, 1, f3));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[271]++;
        units.add(new DateTickUnit(DateTickUnit.HOUR, 12, 
                DateTickUnit.HOUR, 1, f4));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[272]++;

        // days
        units.add(new DateTickUnit(DateTickUnit.DAY, 1, 
                DateTickUnit.HOUR, 1, f5));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[273]++;
        units.add(new DateTickUnit(DateTickUnit.DAY, 2, 
                DateTickUnit.HOUR, 1, f5));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[274]++;
        units.add(new DateTickUnit(DateTickUnit.DAY, 7, 
                DateTickUnit.DAY, 1, f5));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[275]++;
        units.add(new DateTickUnit(DateTickUnit.DAY, 15, 
                DateTickUnit.DAY, 1, f5));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[276]++;

        // months
        units.add(new DateTickUnit(DateTickUnit.MONTH, 1, 
                DateTickUnit.DAY, 1, f6));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[277]++;
        units.add(new DateTickUnit(DateTickUnit.MONTH, 2, 
                DateTickUnit.DAY, 1, f6));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[278]++;
        units.add(new DateTickUnit(DateTickUnit.MONTH, 3, 
                DateTickUnit.MONTH, 1, f6));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[279]++;
        units.add(new DateTickUnit(DateTickUnit.MONTH, 4,  
                DateTickUnit.MONTH, 1, f6));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[280]++;
        units.add(new DateTickUnit(DateTickUnit.MONTH, 6,  
                DateTickUnit.MONTH, 1, f6));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[281]++;

        // years
        units.add(new DateTickUnit(DateTickUnit.YEAR, 1,  
                DateTickUnit.MONTH, 1, f7));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[282]++;
        units.add(new DateTickUnit(DateTickUnit.YEAR, 2,  
                DateTickUnit.MONTH, 3, f7));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[283]++;
        units.add(new DateTickUnit(DateTickUnit.YEAR, 5,  
                DateTickUnit.YEAR, 1, f7));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[284]++;
        units.add(new DateTickUnit(DateTickUnit.YEAR, 10,  
                DateTickUnit.YEAR, 1, f7));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[285]++;
        units.add(new DateTickUnit(DateTickUnit.YEAR, 25, 
                DateTickUnit.YEAR, 5, f7));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[286]++;
        units.add(new DateTickUnit(DateTickUnit.YEAR, 50, 
                DateTickUnit.YEAR, 10, f7));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[287]++;
        units.add(new DateTickUnit(DateTickUnit.YEAR, 100, 
                DateTickUnit.YEAR, 20, f7));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[288]++;

        return units;

    }

    /**
     * Rescales the axis to ensure that all data is visible.
     */
    protected void autoAdjustRange() {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[289]++;

        Plot plot = getPlot();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[290]++;
int CodeCoverConditionCoverageHelper_C49;

        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((plot == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[105]++;
            return;
  // no plot, no data
        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[106]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[291]++;
int CodeCoverConditionCoverageHelper_C50;

        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((plot instanceof ValueAxisPlot) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[107]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[292]++;
            ValueAxisPlot vap = (ValueAxisPlot) plot;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[293]++;

            Range r = vap.getDataRange(this);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[294]++;
int CodeCoverConditionCoverageHelper_C51;
            if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((r == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[109]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[295]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((this.timeline instanceof SegmentedTimeline) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[111]++; 
                    //Timeline hasn't method getStartTime()
                    r = new DateRange((
                            (SegmentedTimeline) this.timeline).getStartTime(),
                            ((SegmentedTimeline) this.timeline).getStartTime() 
                            + 1);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[296]++;

                } 
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[112]++;
                    r = new DateRange();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[297]++;
                }

            } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[110]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[298]++;

            long upper = this.timeline.toTimelineValue(
                    (long) r.getUpperBound());
            long lower;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[299]++;
            long fixedAutoRange = (long) getFixedAutoRange();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[300]++;
int CodeCoverConditionCoverageHelper_C53;
            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((fixedAutoRange > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[113]++;
                lower = upper - fixedAutoRange;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[301]++;

            }
            else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[114]++;
                lower = this.timeline.toTimelineValue((long) r.getLowerBound());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[302]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[303]++;
                double range = upper - lower;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[304]++;
                long minRange = (long) getAutoRangeMinimumSize();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[305]++;
int CodeCoverConditionCoverageHelper_C54;
                if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((range < minRange) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[115]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[306]++;
                    long expand = (long) (minRange - range) / 2;
                    upper = upper + expand;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[307]++;
                    lower = lower - expand;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[308]++;

                } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[116]++;}
                upper = upper + (long) (range * getUpperMargin());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[309]++;
                lower = lower - (long) (range * getLowerMargin());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[310]++;
            }

            upper = this.timeline.toMillisecond(upper);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[311]++;
            lower = this.timeline.toMillisecond(lower);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[312]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[313]++;
            DateRange dr = new DateRange(new Date(lower), new Date(upper));
            setRange(dr, false, false);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[314]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[108]++;}

    }

    /**
     * Selects an appropriate tick value for the axis.  The strategy is to
     * display as many ticks as possible (selected from an array of 'standard'
     * tick units) without the labels overlapping.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area defined by the axes.
     * @param edge  the axis location.
     */
    protected void selectAutoTickUnit(Graphics2D g2, 
                                      Rectangle2D dataArea,
                                      RectangleEdge edge) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[315]++;
int CodeCoverConditionCoverageHelper_C55;

        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[117]++;
            selectHorizontalAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[316]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[118]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[317]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[119]++;
            selectVerticalAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[318]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[120]++;}
}

    }

    /**
     * Selects an appropriate tick size for the axis.  The strategy is to
     * display as many ticks as possible (selected from a collection of 
     * 'standard' tick units) without the labels overlapping.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area defined by the axes.
     * @param edge  the axis location.
     */
    protected void selectHorizontalAutoTickUnit(Graphics2D g2, 
                                                Rectangle2D dataArea, 
                                                RectangleEdge edge) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[319]++;

        long shift = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[320]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((this.timeline instanceof SegmentedTimeline) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[121]++;
            shift = ((SegmentedTimeline) this.timeline).getStartTime();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[321]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[122]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[322]++;
        double zero = valueToJava2D(shift + 0.0, dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[323]++;
        double tickLabelWidth 
            = estimateMaximumTickLabelWidth(g2, getTickUnit());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[324]++;

        // start with the current tick unit...
        TickUnitSource tickUnits = getStandardTickUnits();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[325]++;
        TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[326]++;
        double x1 = valueToJava2D(shift + unit1.getSize(), dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[327]++;
        double unit1Width = Math.abs(x1 - zero);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[328]++;

        // then extrapolate...
        double guess = (tickLabelWidth / unit1Width) * unit1.getSize();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[329]++;
        DateTickUnit unit2 = (DateTickUnit) tickUnits.getCeilingTickUnit(guess);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[330]++;
        double x2 = valueToJava2D(shift + unit2.getSize(), dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[331]++;
        double unit2Width = Math.abs(x2 - zero);
        tickLabelWidth = estimateMaximumTickLabelWidth(g2, unit2);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[332]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[333]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((tickLabelWidth > unit2Width) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[123]++;
            unit2 = (DateTickUnit) tickUnits.getLargerTickUnit(unit2);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[334]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[124]++;}
        setTickUnit(unit2, false, false);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[335]++;
    }
    
    /**
     * Selects an appropriate tick size for the axis.  The strategy is to
     * display as many ticks as possible (selected from a collection of 
     * 'standard' tick units) without the labels overlapping.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the axis location.
     */
    protected void selectVerticalAutoTickUnit(Graphics2D g2,
                                              Rectangle2D dataArea,
                                              RectangleEdge edge) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[336]++;

        // start with the current tick unit...
        TickUnitSource tickUnits = getStandardTickUnits();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[337]++;
        double zero = valueToJava2D(0.0, dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[338]++;

        // start with a unit that is at least 1/10th of the axis length
        double estimate1 = getRange().getLength() / 10.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[339]++;
        DateTickUnit candidate1 
            = (DateTickUnit) tickUnits.getCeilingTickUnit(estimate1);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[340]++;
        double labelHeight1 = estimateMaximumTickLabelHeight(g2, candidate1);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[341]++;
        double y1 = valueToJava2D(candidate1.getSize(), dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[342]++;
        double candidate1UnitHeight = Math.abs(y1 - zero);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[343]++;

        // now extrapolate based on label height and unit height...
        double estimate2 
            = (labelHeight1 / candidate1UnitHeight) * candidate1.getSize();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[344]++;
        DateTickUnit candidate2 
            = (DateTickUnit) tickUnits.getCeilingTickUnit(estimate2);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[345]++;
        double labelHeight2 = estimateMaximumTickLabelHeight(g2, candidate2);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[346]++;
        double y2 = valueToJava2D(candidate2.getSize(), dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[347]++;
        double unit2Height = Math.abs(y2 - zero);

       // make final selection...
       DateTickUnit finalUnit;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[348]++;
int CodeCoverConditionCoverageHelper_C59;
       if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((labelHeight2 < unit2Height) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[125]++;
           finalUnit = candidate2;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[349]++;

       }
       else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[126]++;
           finalUnit = (DateTickUnit) tickUnits.getLargerTickUnit(candidate2);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[350]++;
       }
       setTickUnit(finalUnit, false, false);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[351]++;

    }

    /**
     * Estimates the maximum width of the tick labels, assuming the specified 
     * tick unit is used.
     * <P>
     * Rather than computing the string bounds of every tick on the axis, we
     * just look at two values: the lower bound and the upper bound for the 
     * axis.  These two values will usually be representative.
     *
     * @param g2  the graphics device.
     * @param unit  the tick unit to use for calculation.
     *
     * @return The estimated maximum width of the tick labels.
     */
    private double estimateMaximumTickLabelWidth(Graphics2D g2, 
                                                 DateTickUnit unit) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[352]++;

        RectangleInsets tickLabelInsets = getTickLabelInsets();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[353]++;
        double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[354]++;

        Font tickLabelFont = getTickLabelFont();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[355]++;
        FontRenderContext frc = g2.getFontRenderContext();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[356]++;
        LineMetrics lm = tickLabelFont.getLineMetrics("ABCxyz", frc);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[357]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[127]++;
            // all tick labels have the same width (equal to the height of 
            // the font)...
            result += lm.getHeight();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[358]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[128]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[359]++;
            // look at lower and upper bounds...
            DateRange range = (DateRange) getRange();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[360]++;
            Date lower = range.getLowerDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[361]++;
            Date upper = range.getUpperDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[362]++;
            String lowerStr = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[363]++;
            String upperStr = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[364]++;
            DateFormat formatter = getDateFormatOverride();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[365]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[129]++;
                lowerStr = formatter.format(lower);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[366]++;
                upperStr = formatter.format(upper);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[367]++;

            }
            else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[130]++;
                lowerStr = unit.dateToString(lower);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[368]++;
                upperStr = unit.dateToString(upper);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[369]++;
            }
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[370]++;
            FontMetrics fm = g2.getFontMetrics(tickLabelFont);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[371]++;
            double w1 = fm.stringWidth(lowerStr);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[372]++;
            double w2 = fm.stringWidth(upperStr);
            result += Math.max(w1, w2);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[373]++;
        }

        return result;

    }

    /**
     * Estimates the maximum width of the tick labels, assuming the specified 
     * tick unit is used.
     * <P>
     * Rather than computing the string bounds of every tick on the axis, we 
     * just look at two values: the lower bound and the upper bound for the 
     * axis.  These two values will usually be representative.
     *
     * @param g2  the graphics device.
     * @param unit  the tick unit to use for calculation.
     *
     * @return The estimated maximum width of the tick labels.
     */
    private double estimateMaximumTickLabelHeight(Graphics2D g2, 
                                                  DateTickUnit unit) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[374]++;

        RectangleInsets tickLabelInsets = getTickLabelInsets();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[375]++;
        double result = tickLabelInsets.getTop() + tickLabelInsets.getBottom();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[376]++;

        Font tickLabelFont = getTickLabelFont();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[377]++;
        FontRenderContext frc = g2.getFontRenderContext();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[378]++;
        LineMetrics lm = tickLabelFont.getLineMetrics("ABCxyz", frc);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[379]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[131]++;
            // all tick labels have the same width (equal to the height of 
            // the font)...
            result += lm.getHeight();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[380]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[132]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[381]++;
            // look at lower and upper bounds...
            DateRange range = (DateRange) getRange();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[382]++;
            Date lower = range.getLowerDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[383]++;
            Date upper = range.getUpperDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[384]++;
            String lowerStr = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[385]++;
            String upperStr = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[386]++;
            DateFormat formatter = getDateFormatOverride();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[387]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[133]++;
                lowerStr = formatter.format(lower);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[388]++;
                upperStr = formatter.format(upper);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[389]++;

            }
            else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[134]++;
                lowerStr = unit.dateToString(lower);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[390]++;
                upperStr = unit.dateToString(upper);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[391]++;
            }
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[392]++;
            FontMetrics fm = g2.getFontMetrics(tickLabelFont);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[393]++;
            double w1 = fm.stringWidth(lowerStr);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[394]++;
            double w2 = fm.stringWidth(upperStr);
            result += Math.max(w1, w2);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[395]++;
        }

        return result;

    }

    /**
     * Calculates the positions of the tick labels for the axis, storing the 
     * results in the tick label list (ready for drawing).
     *
     * @param g2  the graphics device.
     * @param state  the axis state.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the location of the axis.
     *
     * @return A list of ticks.
     */
    public List refreshTicks(Graphics2D g2,
                             AxisState state,
                             Rectangle2D dataArea,
                             RectangleEdge edge) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[396]++;

        List result = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[397]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((RectangleEdge.isTopOrBottom(edge)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[135]++;
            result = refreshTicksHorizontal(g2, dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[398]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[136]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[399]++;
int CodeCoverConditionCoverageHelper_C65; if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((RectangleEdge.isLeftOrRight(edge)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[137]++;
            result = refreshTicksVertical(g2, dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[400]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[138]++;}
}
        return result;

    }

    /**
     * Recalculates the ticks for the date axis.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the data is to be drawn.
     * @param edge  the location of the axis.
     *
     * @return A list of ticks.
     */
    protected List refreshTicksHorizontal(Graphics2D g2,
                                          Rectangle2D dataArea,
                                          RectangleEdge edge) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[401]++;

        List result = new java.util.ArrayList();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[402]++;

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[403]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[404]++;
int CodeCoverConditionCoverageHelper_C66;

        if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((isAutoTickUnitSelection()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[139]++;
            selectAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[405]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[140]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[406]++;

        DateTickUnit unit = getTickUnit();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[407]++;
        Date tickDate = calculateLowestVisibleTickValue(unit);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[408]++;
        Date upperDate = getMaximumDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[409]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[1]++;


int CodeCoverConditionCoverageHelper_C67;

        while ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((tickDate.before(upperDate)) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[1]--;
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[2]--;
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[3]++;
}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[410]++;
int CodeCoverConditionCoverageHelper_C68;

            if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((isHiddenValue(tickDate.getTime())) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[141]++;
                // work out the value, label and position
                String tickLabel;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[411]++;
                DateFormat formatter = getDateFormatOverride();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[412]++;
int CodeCoverConditionCoverageHelper_C69;
                if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[143]++;
                    tickLabel = formatter.format(tickDate);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[413]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[144]++;
                    tickLabel = this.tickUnit.dateToString(tickDate);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[414]++;
                }
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[415]++;
                TextAnchor anchor = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[416]++;
                TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[417]++;
                double angle = 0.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[418]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[145]++;
                    anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[419]++;
                    rotationAnchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[420]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[421]++;
int CodeCoverConditionCoverageHelper_C71;
                    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[147]++;
                        angle = Math.PI / 2.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[422]++;

                    }
                    else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[148]++;
                        angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[423]++;
                    }

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[146]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[424]++;
int CodeCoverConditionCoverageHelper_C72;
                    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.TOP) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[149]++;
                        anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[425]++;
                        rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[426]++;

                    }
                    else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[150]++;
                        anchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[427]++;
                        rotationAnchor = TextAnchor.TOP_CENTER;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[428]++;
                    }
                }
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[429]++;

                Tick tick = new DateTick(tickDate, tickLabel, anchor, 
                        rotationAnchor, angle);
                result.add(tick);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[430]++;
                tickDate = unit.addToDate(tickDate, this.timeZone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[431]++;

            }
            else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[142]++;
                tickDate = unit.rollDate(tickDate, this.timeZone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[432]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[433]++;
                continue;
            }
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[434]++;

            // could add a flag to make the following correction optional...
            switch (unit.getUnit()) {

                case (DateTickUnit.MILLISECOND) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[151]++;
                case (DateTickUnit.SECOND) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[152]++;
                case (DateTickUnit.MINUTE) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[153]++;
                case (DateTickUnit.HOUR) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[154]++;
                case (DateTickUnit.DAY) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[155]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[435]++;
                    break;
                case (DateTickUnit.MONTH) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[156]++;
                    tickDate = calculateDateForPosition(new Month(tickDate,
                            this.timeZone), this.tickMarkPosition);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[436]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[437]++;
                    break;
                case(DateTickUnit.YEAR) :
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[157]++;
                    tickDate = calculateDateForPosition(new Year(tickDate, 
                            this.timeZone), this.tickMarkPosition);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[438]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[439]++;
                    break;

                default:
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[158]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[440]++; break;

            }

        }
        return result;

    }

    /**
     * Recalculates the ticks for the date axis.
     *
     * @param g2  the graphics device.
     * @param dataArea  the area in which the plot should be drawn.
     * @param edge  the location of the axis.
     *
     * @return A list of ticks.
     */
    protected List refreshTicksVertical(Graphics2D g2,
                                        Rectangle2D dataArea,
                                        RectangleEdge edge) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[441]++;

        List result = new java.util.ArrayList();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[442]++;

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[443]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[444]++;
int CodeCoverConditionCoverageHelper_C73;

        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((isAutoTickUnitSelection()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[159]++;
            selectAutoTickUnit(g2, dataArea, edge);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[445]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[160]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[446]++;
        DateTickUnit unit = getTickUnit();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[447]++;
        Date tickDate = calculateLowestVisibleTickValue(unit);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[448]++;
        //Date upperDate = calculateHighestVisibleTickValue(unit);
        Date upperDate = getMaximumDate();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[449]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[4]++;


int CodeCoverConditionCoverageHelper_C74;
        while ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((tickDate.before(upperDate)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[4]--;
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[5]--;
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.loops[6]++;
}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[450]++;
int CodeCoverConditionCoverageHelper_C75;

            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((isHiddenValue(tickDate.getTime())) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[161]++;
                // work out the value, label and position
                String tickLabel;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[451]++;
                DateFormat formatter = getDateFormatOverride();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[452]++;
int CodeCoverConditionCoverageHelper_C76;
                if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((formatter != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[163]++;
                    tickLabel = formatter.format(tickDate);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[453]++;

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[164]++;
                    tickLabel = this.tickUnit.dateToString(tickDate);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[454]++;
                }
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[455]++;
                TextAnchor anchor = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[456]++;
                TextAnchor rotationAnchor = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[457]++;
                double angle = 0.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[458]++;
int CodeCoverConditionCoverageHelper_C77;
                if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((isVerticalTickLabels()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[165]++;
                    anchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[459]++;
                    rotationAnchor = TextAnchor.BOTTOM_CENTER;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[460]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[461]++;
int CodeCoverConditionCoverageHelper_C78;
                    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[167]++;
                        angle = -Math.PI / 2.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[462]++;

                    }
                    else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[168]++;
                        angle = Math.PI / 2.0;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[463]++;
                    }

                }
                else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[166]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[464]++;
int CodeCoverConditionCoverageHelper_C79;
                    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((edge == RectangleEdge.LEFT) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[169]++;
                        anchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[465]++;
                        rotationAnchor = TextAnchor.CENTER_RIGHT;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[466]++;

                    }
                    else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[170]++;
                        anchor = TextAnchor.CENTER_LEFT;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[467]++;
                        rotationAnchor = TextAnchor.CENTER_LEFT;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[468]++;
                    }
                }
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[469]++;

                Tick tick = new DateTick(tickDate, tickLabel, anchor, 
                        rotationAnchor, angle);
                result.add(tick);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[470]++;
                tickDate = unit.addToDate(tickDate, this.timeZone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[471]++;

            }
            else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[162]++;
                tickDate = unit.rollDate(tickDate, this.timeZone);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[472]++;
            }
        }
        return result;
    }

    /**
     * Draws the axis on a Java 2D graphics device (such as the screen or a 
     * printer).
     *
     * @param g2  the graphics device (<code>null</code> not permitted).
     * @param cursor  the cursor location.
     * @param plotArea  the area within which the axes and data should be 
     *                  drawn (<code>null</code> not permitted).
     * @param dataArea  the area within which the data should be drawn 
     *                  (<code>null</code> not permitted).
     * @param edge  the location of the axis (<code>null</code> not permitted).
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
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[473]++;
int CodeCoverConditionCoverageHelper_C80;

        // if the axis is not visible, don't draw it...
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((isVisible()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[171]++;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[474]++;
            AxisState state = new AxisState(cursor);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[475]++;
            // even though the axis is not visible, we need to refresh ticks in
            // case the grid is being drawn...
            List ticks = refreshTicks(g2, state, dataArea, edge);
            state.setTicks(ticks);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[476]++;
            return state;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[172]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[477]++;

        // draw the tick marks and labels...
        AxisState state = drawTickMarksAndLabels(g2, cursor, plotArea, 
                dataArea, edge);

        // draw the axis label (note that 'state' is passed in *and* 
        // returned)...
        state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[478]++;

        return state;

    }

    /**
     * Zooms in on the current range.
     *
     * @param lowerPercent  the new lower bound.
     * @param upperPercent  the new upper bound.
     */
    public void zoomRange(double lowerPercent, double upperPercent) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[479]++;
        double start = this.timeline.toTimelineValue(
            (long) getRange().getLowerBound()
        );
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[480]++;
        double length = (this.timeline.toTimelineValue(
                (long) getRange().getUpperBound()) 
                - this.timeline.toTimelineValue(
                    (long) getRange().getLowerBound()));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[481]++;
        Range adjusted = null;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[482]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((isInverted()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[173]++;
            adjusted = new DateRange(this.timeline.toMillisecond((long) (start 
                    + (length * (1 - upperPercent)))),
                    this.timeline.toMillisecond((long) (start + (length 
                    * (1 - lowerPercent)))));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[483]++;

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[174]++;
            adjusted = new DateRange(this.timeline.toMillisecond(
                    (long) (start + length * lowerPercent)), 
                    this.timeline.toMillisecond((long) (start + length 
                    * upperPercent)));
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[484]++;
        }
        setRange(adjusted);
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[485]++;
    } 
    
    /**
     * Tests this axis for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[486]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[175]++;
            return true;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[176]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[487]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((obj instanceof DateAxis) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[177]++;
            return false;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[178]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[488]++;
        DateAxis that = (DateAxis) obj;
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[489]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.tickUnit, that.tickUnit)) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[179]++;
            return false;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[180]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[490]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.dateFormatOverride, 
                that.dateFormatOverride)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[181]++;
            return false;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[182]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[491]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.tickMarkPosition, 
                that.tickMarkPosition)) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[183]++;
            return false;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[184]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[492]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.timeline, that.timeline)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[185]++;
            return false;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[186]++;}
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[493]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[187]++;
            return false;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[188]++;}
        return true;
    }

    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[494]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((getLabel() != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[189]++;
            return getLabel().hashCode();

        }
        else {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[190]++;
            return 0;
        }
    }

    /**
     * Returns a clone of the object.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if some component of the axis does 
     *         not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[495]++;

        DateAxis clone = (DateAxis) super.clone();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[496]++;
int CodeCoverConditionCoverageHelper_C90;

        // 'dateTickUnit' is immutable : no need to clone
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((this.dateFormatOverride != null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[191]++;
            clone.dateFormatOverride 
                = (DateFormat) this.dateFormatOverride.clone();
CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.statements[497]++;

        } else {
  CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox.branches[192]++;}
        // 'tickMarkPosition' is immutable : no need to clone

        return clone;

    }
            
}

class CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox ());
  }
    public static long[] statements = new long[498];
    public static long[] branches = new long[193];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[91];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.DateAxis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 90; i++) {
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

  public CodeCoverCoverageCounter$el931ebzz1wpgq9jv2ox () {
    super("org.jfree.chart.axis.DateAxis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 497; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 192; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 90; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.DateAxis.java");
      for (int i = 1; i <= 497; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 192; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 90; i++) {
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

