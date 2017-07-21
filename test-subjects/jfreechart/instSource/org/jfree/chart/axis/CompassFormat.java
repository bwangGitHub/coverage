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
 * ------------------
 * CompassFormat.java
 * ------------------
 * (C) Copyright 2003-2007, by Sylvain Vieujot and Contributors.
 *
 * Original Author:  Sylvain Vieujot;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 18-Feb-2004 : Version 1 contributed by Sylvain Vieujot (DG);
 *
 */

package org.jfree.chart.axis;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * A formatter that displays numbers as directions.
 */
public class CompassFormat extends NumberFormat {
  static {
    CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.ping();
  }

    
    /** North. */
    private static final String N = "N";
  static {
    CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[1]++;
  }
    
    /** East. */
    private static final String E = "E";
  static {
    CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[2]++;
  }
    
    /** South. */
    private static final String S = "S";
  static {
    CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[3]++;
  }
    
    /** West. */
    private static final String W = "W";
  static {
    CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[4]++;
  }
    
    /** The directions. */
    public static final String[] DIRECTIONS = {
        N, N + N + E, N + E, E + N + E, E, E + S + E, S + E, S + S + E, S,
        S + S + W, S + W, W + S + W, W, W + N + W, N + W, N + N + W, N
    };
  static {
    CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[5]++;
  }
    
    /**
     * Creates a new formatter.
     */
    public CompassFormat() {
        super();
CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[6]++;
    }
    
    /**
     * Returns a string representing the direction.
     * 
     * @param direction  the direction.
     * 
     * @return A string.
     */
    public String getDirectionCode(double direction) {
        
        direction = direction % 360;
CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[7]++;
CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((direction < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.branches[1]++;
            direction = direction + 360.0;
CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[9]++;

        } else {
  CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.branches[2]++;}
CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d.statements[10]++;
        int index = ((int) Math.floor(direction / 11.25) + 1) / 2; 
        return DIRECTIONS[index];
        
    }

    /* (non-Javadoc)
     * @see java.text.NumberFormat#format(double, java.lang.StringBuffer, 
     *      java.text.FieldPosition)
     */
    public StringBuffer format(double number, StringBuffer toAppendTo, 
                               FieldPosition pos) {
        return toAppendTo.append(getDirectionCode(number));
    }

    /* (non-Javadoc)
     * @see java.text.NumberFormat#format(long, java.lang.StringBuffer, 
     *      java.text.FieldPosition)
     */
    public StringBuffer format(long number, StringBuffer toAppendTo, 
                               FieldPosition pos) {
        return toAppendTo.append(getDirectionCode(number));
    }

    /**
     * This method returns <code>null</code> for all inputs.  This class cannot
     * be used for parsing.
     * 
     * @param source  the source string.
     * @param parsePosition  the parse position.
     * 
     * @return <code>null</code>.
     */
    public Number parse(String source, ParsePosition parsePosition) {
        return null;
    }
    
}

class CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.chart.axis.CompassFormat.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$5lvte4kqnjfviulmyounyib78z1d () {
    super("org.jfree.chart.axis.CompassFormat.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.CompassFormat.java");
      for (int i = 1; i <= 10; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
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

