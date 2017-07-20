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
 * HexNumberFormat.java
 * --------------------
 * (C) Copyright 2007, by Richard West and Contributors.
 *
 * Original Author:  Richard West, Advanced Micro Devices, Inc.;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes:
 * --------
 * 14-Jun-2007 : Version 1 (RW);
 *
 */

package org.jfree.chart.util;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * A custom number formatter that formats numbers as hexadecimal strings.
 * There are some limitations, so be careful using this class.
 * 
 * @since 1.0.6
 */
public class HexNumberFormat extends NumberFormat {
  static {
    CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.ping();
  }


    /** Number of hexadecimal digits for a byte. */
    public static final int BYTE = 2;
  static {
    CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[1]++;
  }
    
    /** Number of hexadecimal digits for a word. */
    public static final int WORD = 4;
  static {
    CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[2]++;
  }

    /** Number of hexadecimal digits for a double word. */
    public static final int DWORD = 8;
  static {
    CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[3]++;
  }

    /** Number of hexadecimal digits for a quad word. */
    public static final int QWORD = 16;
  static {
    CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[4]++;
  }

    /** The number of digits (shorter strings will be left padded). */
    private int m_numDigits = DWORD;
  {
    CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[5]++;
  }

    /**
     * Creates a new instance with 8 digits.
     */
    public HexNumberFormat() {
        this(DWORD);
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[6]++;
    }

    /**
     * Creates a new instance with the specified number of digits.

     * @param digits  the digits.
     */
    public HexNumberFormat(int digits) {
        super();
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[7]++;
        this.m_numDigits = digits;
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[8]++;
    }

    /**
     * Returns the number of digits.
     * 
     * @return The number of digits.
     */
    public final int getNumberOfDigits() {
        return this.m_numDigits;
    }

    /**
     * Sets the number of digits.
     * 
     * @param digits  the number of digits.
     */
    public void setNumberOfDigits(int digits) {
        this.m_numDigits = digits;
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[9]++;
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
    public StringBuffer format(double number, StringBuffer toAppendTo,
            FieldPosition pos) {
        return format((long) number, toAppendTo, pos);
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
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[10]++;
        String l_hex = Long.toHexString(number).toUpperCase();
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[11]++;

        int l_pad = this.m_numDigits - l_hex.length();
        l_pad = (0 < l_pad) ? l_pad : 0;
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[12]++;
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[13]++;

        StringBuffer l_extended = new StringBuffer("0x");
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < l_pad) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.loops[1]--;
  CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.loops[2]--;
  CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.loops[3]++;
}
            l_extended.append(0);
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[15]++;
        }
        l_extended.append(l_hex);
CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh.statements[16]++;

        return l_extended;
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

class CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[0];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.chart.util.HexNumberFormat.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$8gfvy2hxencc9hcjj0jafk3951m67oh () {
    super("org.jfree.chart.util.HexNumberFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.util.HexNumberFormat.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

