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
 * LogFormat.java
 * --------------
 * (C) Copyright 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 02-Aug-2007 : Version 1 (DG);
 * 
 */

package org.jfree.chart.util;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * A number formatter for logarithmic values.  This formatter does not support
 * parsing.
 * 
 * @since 1.0.7
 */
public class LogFormat extends NumberFormat {
  static {
    CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.ping();
  }

    
    /** The log base value. */
    private double base;
    
    /** The natural logarithm of the base value. */
    private double baseLog;
    
    /** The label for the log base (for example, "e"). */
    private String baseLabel;
    
    /** A flag that controls whether or not the base is shown. */
    private boolean showBase;
    
    /** The number formatter for the exponent. */
    private NumberFormat formatter = new DecimalFormat("0.0");
  {
    CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[1]++;
  }
    
    /**
     * Creates a new instance.
     * 
     * @param base  the base.
     * @param baseLabel  the base label.
     * @param showBase  a flag that controls whether or not the base value is
     *                  shown.
     */
    public LogFormat(double base, String baseLabel, boolean showBase) {
        this.base = base;
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[2]++;
        this.baseLog = Math.log(this.base);
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[3]++;
        this.baseLabel = baseLabel;
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[4]++;
        this.showBase = showBase;
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[5]++;
    }
    
    /**
     * Calculates the log of a given value.
     * 
     * @param value  the value.
     * 
     * @return The log of the value.
     */
    private double calculateLog(double value) {
        return Math.log(value) / this.baseLog;
    }
    
    /**
     * Returns a formatted representation of the specified number.
     * 
     * @param number  the number.
     * @param toAppendTo  the string buffer to append to.
     * @param pos  the position.
     * 
     * @return A string buffer containing the formatted value.
     */
    public StringBuffer format(double number, StringBuffer toAppendTo,
            FieldPosition pos) {
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[6]++;
        StringBuffer result = new StringBuffer();
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.showBase) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.branches[1]++;
            result.append(this.baseLabel);
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[8]++;
            result.append("^");
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[9]++;

        } else {
  CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.branches[2]++;}
        result.append(this.formatter.format(calculateLog(number)));
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[10]++;
        return result;
    }

    /**
     * Formats the specified number as a hexadecimal string.  The decimal 
     * fraction is ignored.
     * 
     * @param number  the number to format.
     * @param toAppendTo  the buffer to append to (ignored here).
     * @param pos  the field position (ignored here).
     * 
     * @return The string buffer.
     */
    public StringBuffer format(long number, StringBuffer toAppendTo, 
            FieldPosition pos) {
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[11]++;
        StringBuffer result = new StringBuffer();
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.showBase) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.branches[3]++;
            result.append(this.baseLabel);
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[13]++;
            result.append("^");
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[14]++;

        } else {
  CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.branches[4]++;}
        result.append(this.formatter.format(calculateLog(number)));
CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x.statements[15]++;
        return result;
    }

    /**
     * Parsing is not implemented, so this method always returns 
     * <code>null</code>.
     * 
     * @param source  ignored.
     * @param parsePosition  ignored.
     * 
     * @return Always <code>null</code>.
     */
    public Number parse (String source, ParsePosition parsePosition) {
        return null; // don't bother with parsing
    }

}

class CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.chart.util.LogFormat.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$37z31hpwmak1lyja5hij4x () {
    super("org.jfree.chart.util.LogFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.util.LogFormat.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

