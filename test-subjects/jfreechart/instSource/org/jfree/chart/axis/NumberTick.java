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
 * NumberTick.java
 * ---------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 07-Nov-2003 : Version 1 (DG);
 * 02-Aug-2007 : Added new constructor with tick type (DG);
 * 
 */

package org.jfree.chart.axis;

import org.jfree.ui.TextAnchor;

/**
 * A numerical tick.
 */
public class NumberTick extends ValueTick {
  static {
    CodeCoverCoverageCounter$nijhezzg6k0wgvzc28dwcwx.ping();
  }

    
    /** The number. */
    private Number number;
    
    /**
     * Creates a new tick.
     * 
     * @param number  the number (<code>null</code> not permitted).
     * @param label  the label.
     * @param textAnchor  the part of the label that is aligned with the anchor 
     *                    point.
     * @param rotationAnchor  defines the rotation point relative to the text.
     * @param angle  the rotation angle (in radians).
     */
    public NumberTick(Number number, String label,
                      TextAnchor textAnchor, 
                      TextAnchor rotationAnchor, double angle) {
                        
        super(number.doubleValue(), label, textAnchor, rotationAnchor, angle);
CodeCoverCoverageCounter$nijhezzg6k0wgvzc28dwcwx.statements[1]++;
        this.number = number;
CodeCoverCoverageCounter$nijhezzg6k0wgvzc28dwcwx.statements[2]++;
            
    }

    /**
     * Creates a new tick.
     * 
     * @param tickType  the tick type.
     * @param value  the value.
     * @param label  the label.
     * @param textAnchor  the part of the label that is aligned with the anchor 
     *                    point.
     * @param rotationAnchor  defines the rotation point relative to the text.
     * @param angle  the rotation angle (in radians).
     * 
     * @since 1.0.7
     */
    public NumberTick(TickType tickType, double value, String label,
                      TextAnchor textAnchor, 
                      TextAnchor rotationAnchor, double angle) {
                        
        super(tickType, value, label, textAnchor, rotationAnchor, angle);
CodeCoverCoverageCounter$nijhezzg6k0wgvzc28dwcwx.statements[3]++;
        this.number = new Double(value);
CodeCoverCoverageCounter$nijhezzg6k0wgvzc28dwcwx.statements[4]++;
            
    }
    
    /**
     * Returns the number.
     * 
     * @return The number.
     */
    public Number getNumber() {
        return this.number;    
    }
    
}

class CodeCoverCoverageCounter$nijhezzg6k0wgvzc28dwcwx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$nijhezzg6k0wgvzc28dwcwx ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$nijhezzg6k0wgvzc28dwcwx () {
    super("org.jfree.chart.axis.NumberTick.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.axis.NumberTick.java");
      for (int i = 1; i <= 4; i++) {
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

