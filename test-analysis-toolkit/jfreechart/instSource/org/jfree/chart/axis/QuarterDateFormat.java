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
 * QuarterDateFormat.java
 * ----------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 01-Mar-2005 : Version 1 (DG);
 * 10-May-2005 : Added equals() method, and implemented Cloneable and 
 *               Serializable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 08-Jun-2007 : Added Greek symbols, and support for reversing the date - see
 *               patch 1577221 (DG);
 *
 */

package org.jfree.chart.axis;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * A formatter that formats dates to show the year and quarter (for example,
 * '2004 IV' for the last quarter of 2004.
 */
public class QuarterDateFormat extends DateFormat 
                               implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = -6738465248529797176L;
  static {
    CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[1]++;
  }
    
    /** Symbols for regular quarters. */
    public static final String[] REGULAR_QUARTERS = new String[] {"1", "2", 
            "3", "4"};
  static {
    CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[2]++;
  }
    
    /** Symbols for roman numbered quarters. */
    public static final String[] ROMAN_QUARTERS  = new String[] {"I", "II", 
            "III", "IV"};
  static {
    CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[3]++;
  }
    
    /** 
     * Symbols for greek numbered quarters. 
     *
     * @since 1.0.6
     */
    public static final String[] GREEK_QUARTERS = new String[] {"\u0391", 
            "\u0392", "\u0393", "\u0394"};
  static {
    CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[4]++;
  }

    /** The strings. */
    private String[] quarters = REGULAR_QUARTERS;
  {
    CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[5]++;
  }
    
    /** A flag that controls whether the quarter or the year goes first. */
    private boolean quarterFirst;
    
    /**
     * Creates a new instance for the default time zone.
     */
    public QuarterDateFormat() {
        this(TimeZone.getDefault());
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[6]++;  
    }
    
    /**
     * Creates a new instance for the specified time zone.
     * 
     * @param zone  the time zone (<code>null</code> not permitted).
     */
    public QuarterDateFormat(TimeZone zone) {
        this(zone, REGULAR_QUARTERS);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[7]++;
    }
    
    /**
     * Creates a new instance for the specified time zone.
     * 
     * @param zone  the time zone (<code>null</code> not permitted).
     * @param quarterSymbols  the quarter symbols.
     */
    public QuarterDateFormat(TimeZone zone, String[] quarterSymbols) {
        this(zone, quarterSymbols, false);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[8]++;
    }
    
    /**
     * Creates a new instance for the specified time zone.
     * 
     * @param zone  the time zone (<code>null</code> not permitted).
     * @param quarterSymbols  the quarter symbols.
     * @param quarterFirst  a flag that controls whether the quarter or the 
     *         year is displayed first.
     *         
     * @since 1.0.6
     */
    public QuarterDateFormat(TimeZone zone, String[] quarterSymbols,
            boolean quarterFirst) {
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((zone == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[1]++;
            throw new IllegalArgumentException("Null 'zone' argument.");
   
        } else {
  CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[2]++;}
        this.calendar = new GregorianCalendar(zone);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[10]++;
        this.quarters = quarterSymbols;
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[11]++;
        this.quarterFirst = quarterFirst;
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[12]++;
        
        // the following is never used, but it seems that DateFormat requires
        // it to be non-null.  It isn't well covered in the spec, refer to 
        // bug parade 5061189 for more info.
        this.numberFormat = NumberFormat.getNumberInstance();
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[13]++;
        
    }
    
    /**
     * Formats the given date.
     * 
     * @param date  the date.
     * @param toAppendTo  the string buffer.
     * @param fieldPosition  the field position.
     * 
     * @return The formatted date.
     */
    public StringBuffer format(Date date, StringBuffer toAppendTo,
                               FieldPosition fieldPosition) {
        this.calendar.setTime(date);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[14]++;
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[15]++;
        int year = this.calendar.get(Calendar.YEAR);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[16]++;
        int month = this.calendar.get(Calendar.MONTH);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[17]++;
        int quarter = month / 3;
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.quarterFirst) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[3]++;
            toAppendTo.append(this.quarters[quarter]);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[19]++;
            toAppendTo.append(" ");
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[20]++;
            toAppendTo.append(year);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[21]++;
            
        }
        else {
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[4]++;
            toAppendTo.append(year);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[22]++;
            toAppendTo.append(" ");
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[23]++;
            toAppendTo.append(this.quarters[quarter]);
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[24]++;
        }
        return toAppendTo;   
    }

    /**
     * Parses the given string (not implemented).
     * 
     * @param source  the date string.
     * @param pos  the parse position.
     * 
     * @return <code>null</code>, as this method has not been implemented.
     */
    public Date parse(String source, ParsePosition pos) {
        return null;   
    }

    /**
     * Tests this formatter for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[6]++;}
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof QuarterDateFormat) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[8]++;}
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[27]++;
        QuarterDateFormat that = (QuarterDateFormat) obj;
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((Arrays.equals(this.quarters, that.quarters)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[10]++;}
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.quarterFirst != that.quarterFirst) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd.branches[12]++;}
        return super.equals(obj);
    }
    
}

class CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.QuarterDateFormat.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$dd60bbj3o3xpor7f99hhlsddvxa1h8tgtd () {
    super("org.jfree.chart.axis.QuarterDateFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 29; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.QuarterDateFormat.java");
      for (int i = 1; i <= 29; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

