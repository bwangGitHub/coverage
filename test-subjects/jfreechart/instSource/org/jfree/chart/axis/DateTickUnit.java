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
 * -----------------
 * DateTickUnit.java
 * -----------------
 * (C) Copyright 2000-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Chris Boek;
 *
 * Changes
 * -------
 * 08-Nov-2002 : Moved to new package com.jrefinery.chart.axis (DG);
 * 27-Nov-2002 : Added IllegalArgumentException to getMillisecondCount() 
 *               method (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 12-Nov-2003 : Added roll fields that can improve the labelling on segmented 
 *               date axes (DG);
 * 03-Dec-2003 : DateFormat constructor argument is now filled with an default 
 *               if null (TM);
 * 07-Dec-2003 : Fixed bug (null pointer exception) in constructor (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 21-Mar-2007 : Added toString() for debugging (DG);
 * 04-Apr-2007 : Added new methods addToDate(Date, TimeZone) and rollDate(Date, 
 *               TimeZone) (CB);
 *
 */

package org.jfree.chart.axis;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.jfree.util.ObjectUtilities;

/**
 * A tick unit for use by subclasses of {@link DateAxis}. Instances of this 
 * class are immutable.
 */
public class DateTickUnit extends TickUnit implements Serializable {
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -7289292157229621901L;
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[1]++;
  }
    
    /** A constant for years. */
    public static final int YEAR = 0;
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[2]++;
  }

    /** A constant for months. */
    public static final int MONTH = 1;
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[3]++;
  }

    /** A constant for days. */
    public static final int DAY = 2;
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[4]++;
  }

    /** A constant for hours. */
    public static final int HOUR = 3;
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[5]++;
  }

    /** A constant for minutes. */
    public static final int MINUTE = 4;
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[6]++;
  }

    /** A constant for seconds. */
    public static final int SECOND = 5;
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[7]++;
  }

    /** A constant for milliseconds. */
    public static final int MILLISECOND = 6;
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[8]++;
  }

    /** The unit. */
    private int unit;

    /** The unit count. */
    private int count;

    /** The roll unit. */
    private int rollUnit;

    /** The roll count. */
    private int rollCount;

    /** The date formatter. */
    private DateFormat formatter;

    /**
     * Creates a new date tick unit.  The dates will be formatted using a 
     * SHORT format for the default locale.
     *
     * @param unit  the unit.
     * @param count  the unit count.
     */
    public DateTickUnit(int unit, int count) {
        this(unit, count, null);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[9]++;
    }

    /**
     * Creates a new date tick unit.  You can specify the units using one of 
     * the constants YEAR, MONTH, DAY, HOUR, MINUTE, SECOND or MILLISECOND.  
     * In addition, you can specify a unit count, and a date format.
     *
     * @param unit  the unit.
     * @param count  the unit count.
     * @param formatter  the date formatter (defaults to DateFormat.SHORT).
     */
    public DateTickUnit(int unit, int count, DateFormat formatter) {

        this(unit, count, unit, count, formatter);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[10]++;

    }

    /**
     * Creates a new unit.
     *
     * @param unit  the unit.
     * @param count  the count.
     * @param rollUnit  the roll unit.
     * @param rollCount  the roll count.
     * @param formatter  the date formatter (defaults to DateFormat.SHORT).
     */
    public DateTickUnit(int unit, int count, int rollUnit, int rollCount, 
                        DateFormat formatter) {
        super(DateTickUnit.getMillisecondCount(unit, count));
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[11]++;
        this.unit = unit;
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[12]++;
        this.count = count;
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[13]++;
        this.rollUnit = rollUnit;
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[14]++;
        this.rollCount = rollCount;
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[15]++;
        this.formatter = formatter;
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[16]++;
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((formatter == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[1]++;
            this.formatter = DateFormat.getDateInstance(DateFormat.SHORT);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[18]++;

        } else {
  CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[2]++;}
    }

    /**
     * Returns the date unit.  This will be one of the constants 
     * <code>YEAR</code>, <code>MONTH</code>, <code>DAY</code>, 
     * <code>HOUR</code>, <code>MINUTE</code>, <code>SECOND</code> or 
     * <code>MILLISECOND</code>, defined by this class.  Note that these 
     * constants do NOT correspond to those defined in Java's 
     * <code>Calendar</code> class.
     *
     * @return The date unit.
     */
    public int getUnit() {
        return this.unit;
    }

    /**
     * Returns the unit count.
     *
     * @return The unit count.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Returns the roll unit.  This is the amount by which the tick advances if
     * it is "hidden" when displayed on a segmented date axis.  Typically the 
     * roll will be smaller than the regular tick unit (for example, a 7 day 
     * tick unit might use a 1 day roll).
     *
     * @return The roll unit.
     */
    public int getRollUnit() {
        return this.rollUnit;
    }

    /**
     * Returns the roll count.
     *
     * @return The roll count.
     */
    public int getRollCount() {
        return this.rollCount;
    }

    /**
     * Formats a value.
     *
     * @param milliseconds  date in milliseconds since 01-01-1970.
     *
     * @return The formatted date.
     */
    public String valueToString(double milliseconds) {
        return this.formatter.format(new Date((long) milliseconds));
    }

    /**
     * Formats a date using the tick unit's formatter.
     *
     * @param date  the date.
     *
     * @return The formatted date.
     */
    public String dateToString(Date date) {
        return this.formatter.format(date);
    }

    /**
     * Calculates a new date by adding this unit to the base date.
     *
     * @param base  the base date.
     *
     * @return A new date one unit after the base date.
     * 
     * @see #addToDate(Date, TimeZone)
     */
    public Date addToDate(Date base) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[19]++;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(base);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[20]++;
        calendar.add(getCalendarField(this.unit), this.count);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[21]++;
        return calendar.getTime();
    }

    /**
     * Calculates a new date by adding this unit to the base date.
     *
     * @param base  the base date.
     * @param zone  the time zone for the date calculation.
     *
     * @return A new date one unit after the base date.
     * 
     * @since 1.0.6
     * @see #addToDate(Date)
     */
    public Date addToDate(Date base, TimeZone zone) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[22]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(base);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[23]++;
        calendar.add(getCalendarField(this.unit), this.count);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[24]++;
        return calendar.getTime();
    }

    /**
     * Rolls the date forward by the amount specified by the roll unit and 
     * count.
     *
     * @param base  the base date.

     * @return The rolled date.
     * 
     * @see #rollDate(Date, TimeZone)
     */
    public Date rollDate(Date base) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[25]++;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(base);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[26]++;
        calendar.add(getCalendarField(this.rollUnit), this.rollCount);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[27]++;
        return calendar.getTime();
    }

    /**
     * Rolls the date forward by the amount specified by the roll unit and 
     * count.
     *
     * @param base  the base date.
     * @param zone  the time zone.
     * 
     * @return The rolled date.
     * 
     * @since 1.0.6
     * @see #rollDate(Date)
     */
    public Date rollDate(Date base, TimeZone zone) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[28]++;
        Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(base);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[29]++;
        calendar.add(getCalendarField(this.rollUnit), this.rollCount);
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[30]++;
        return calendar.getTime();
    }

    /**
     * Returns a field code that can be used with the <code>Calendar</code> 
     * class.
     *
     * @return The field code.
     */
    public int getCalendarField() {
        return getCalendarField(this.unit);
    }

    /**
     * Returns a field code (that can be used with the Calendar class) for a 
     * given 'unit' code.  The 'unit' is one of:  {@link #YEAR}, {@link #MONTH},
     * {@link #DAY}, {@link #HOUR}, {@link #MINUTE}, {@link #SECOND} and 
     * {@link #MILLISECOND}.
     *
     * @param tickUnit  the unit.
     *
     * @return The field code.
     */
    private int getCalendarField(int tickUnit) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[31]++;

        switch (tickUnit) {
            case (YEAR):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[3]++;
                return Calendar.YEAR;
            case (MONTH):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[4]++;
                return Calendar.MONTH;
            case (DAY):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[5]++;
                return Calendar.DATE;
            case (HOUR):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[6]++;
                return Calendar.HOUR_OF_DAY;
            case (MINUTE):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[7]++;
                return Calendar.MINUTE;
            case (SECOND):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[8]++;
                return Calendar.SECOND;
            case (MILLISECOND):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[9]++;
                return Calendar.MILLISECOND;
            default:
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[10]++;
                return Calendar.MILLISECOND;
        }

    }

    /**
     * Returns the (approximate) number of milliseconds for the given unit and 
     * unit count.
     * <P>
     * This value is an approximation some of the time (e.g. months are 
     * assumed to have 31 days) but this shouldn't matter.
     *
     * @param unit  the unit.
     * @param count  the unit count.
     *
     * @return The number of milliseconds.
     */
    private static long getMillisecondCount(int unit, int count) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[32]++;

        switch (unit) {
            case (YEAR):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[11]++;
                return (365L * 24L * 60L * 60L * 1000L) * count;
            case (MONTH):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[12]++;
                return (31L * 24L * 60L * 60L * 1000L) * count;
            case (DAY):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[13]++;
                return (24L * 60L * 60L * 1000L) * count;
            case (HOUR):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[14]++;
                return (60L * 60L * 1000L) * count;
            case (MINUTE):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[15]++;
                return (60L * 1000L) * count;
            case (SECOND):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[16]++;
                return 1000L * count;
            case (MILLISECOND):
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[17]++;
                return count;
            default:
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[18]++;
                throw new IllegalArgumentException(
                    "DateTickUnit.getMillisecondCount() : unit must "
                    + "be one of the constants YEAR, MONTH, DAY, HOUR, MINUTE, "
                    + "SECOND or MILLISECOND defined in the DateTickUnit "
                    + "class. Do *not* use the constants defined in "
                    + "java.util.Calendar."
                );
        }

    }

    /**
     * Tests this unit for equality with another object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[33]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[19]++;
            return true;

        } else {
  CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[20]++;}
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[34]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof DateTickUnit) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[21]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[22]++;}
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[35]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[23]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[24]++;}
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[36]++;
        DateTickUnit that = (DateTickUnit) obj;
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.unit != that.unit) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[25]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[26]++;}
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.count != that.count) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[27]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[28]++;}
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.formatter, that.formatter)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[29]++;
            return false;

        } else {
  CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.branches[30]++;}
        return true;
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[40]++;
        int result = 19;
        result = 37 * result + this.unit;
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[41]++;
        result = 37 * result + this.count;
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[42]++;
        result = 37 * result + this.formatter.hashCode();
CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[43]++;
        return result;
    }
    
    /**
     * Strings for use by the toString() method.
     */
    private static final String[] units = {"YEAR", "MONTH", "DAY", "HOUR", 
            "MINUTE", "SECOND", "MILLISECOND"};
  static {
    CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp.statements[44]++;
  }
    
    /**
     * Returns a string representation of this instance, primarily used for
     * debugging purposes.
     *
     * @return A string representation of this instance.
     */
    public String toString() {
        return "DateTickUnit[" + DateTickUnit.units[this.unit] + ", " 
                + this.count + "]";
    }

}

class CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp ());
  }
    public static long[] statements = new long[45];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.DateTickUnit.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$ssd5eoajdc7g0eynh0n5995kyp () {
    super("org.jfree.chart.axis.DateTickUnit.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 44; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.DateTickUnit.java");
      for (int i = 1; i <= 44; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 0; i++) {
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

