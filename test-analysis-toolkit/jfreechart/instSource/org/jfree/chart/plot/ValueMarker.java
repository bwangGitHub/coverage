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
 * ----------------
 * ValueMarker.java
 * ----------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 09-Feb-2004 : Version 1 (DG);
 * 16-Feb-2005 : Added new constructor (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Sep-2006 : Added setValue() method (DG);
 * 08-Oct-2007 : Fixed bug 1808376, constructor calling super with incorrect
 *               values (DG);
 * 
 */

package org.jfree.chart.plot;

import java.awt.Paint;
import java.awt.Stroke;

import org.jfree.chart.event.MarkerChangeEvent;

/**
 * A marker that represents a single value.  Markers can be added to plots to
 * highlight specific values.
 */
public class ValueMarker extends Marker {
  static {
    CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.ping();
  }

    
    /** The value. */
    private double value;

    /**
     * Creates a new marker.
     * 
     * @param value  the value.
     */
    public ValueMarker(double value) {
        super();
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[1]++;
        this.value = value;
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[2]++;
    }
    
    /**
     * Creates a new marker.
     * 
     * @param value  the value.
     * @param paint  the paint (<code>null</code> not permitted).
     * @param stroke  the stroke (<code>null</code> not permitted).
     */
    public ValueMarker(double value, Paint paint, Stroke stroke) {
        this(value, paint, stroke, paint, stroke, 1.0f);
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[3]++;
    }
    
    /**
     * Creates a new value marker.
     * 
     * @param value  the value.
     * @param paint  the paint (<code>null</code> not permitted).
     * @param stroke  the stroke (<code>null</code> not permitted).
     * @param outlinePaint  the outline paint (<code>null</code> permitted).
     * @param outlineStroke  the outline stroke (<code>null</code> permitted).
     * @param alpha  the alpha transparency (in the range 0.0f to 1.0f).
     */
    public ValueMarker(double value, Paint paint, Stroke stroke, 
                       Paint outlinePaint, Stroke outlineStroke, float alpha) {
        super(paint, stroke, outlinePaint, outlineStroke, alpha);
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[4]++;
        this.value = value;
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[5]++;
    }
    
    /**
     * Returns the value.
     *
     * @return The value.
     * 
     * @see #setValue(double)
     */
    public double getValue() {
        return this.value;
    }
    
    /**
     * Sets the value for the marker and sends a {@link MarkerChangeEvent} to 
     * all registered listeners.
     * 
     * @param value  the value.
     * 
     * @see #getValue()
     * 
     * @since 1.0.3
     */
    public void setValue(double value) { 
        this.value = value;
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[6]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[7]++;
    }

    /**
     * Tests this marker for equality with an arbitrary object.  This method
     * returns <code>true</code> if:
     * 
     * <ul>
     * <li><code>obj</code> is not <code>null</code>;</li>
     * <li><code>obj</code> is an instance of <code>ValueMarker</code>;</li>
     * <li><code>obj</code> has the same value as this marker;</li>
     * <li><code>super.equals(obj)</code> returns <code>true</code>.</li>
     * </ul>
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.branches[2]++;}
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.branches[4]++;}
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof ValueMarker) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.branches[6]++;}
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[11]++;
        ValueMarker that = (ValueMarker) obj;
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.value != that.value) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5.branches[8]++;}
        return true;
    }
}

class CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5 ());
  }
    public static long[] statements = new long[13];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.ValueMarker.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$543nwkaa13oypb14xvhpt7kv5 () {
    super("org.jfree.chart.plot.ValueMarker.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 12; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.ValueMarker.java");
      for (int i = 1; i <= 12; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

