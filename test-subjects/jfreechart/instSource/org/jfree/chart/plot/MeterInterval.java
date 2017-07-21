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
 * MeterInterval.java
 * ------------------
 * (C) Copyright 2005, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 22-Mar-2005 : Version 1 (DG);
 * 29-Mar-2005 : Fixed serialization (DG);
 * 
 */

package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jfree.data.Range;
import org.jfree.io.SerialUtilities;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PaintUtilities;

/**
 * An interval to be highlighted on a {@link MeterPlot}.  Instances of this
 * class are immutable.
 */
public class MeterInterval implements Serializable {
  static {
    CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1530982090622488257L;
  static {
    CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[1]++;
  }
    
    /** The interval label. */
    private String label;
    
    /** The interval range. */
    private Range range;
    
    /** The outline paint (used for the arc marking the interval). */
    private transient Paint outlinePaint;
    
    /** The outline stroke (used for the arc marking the interval). */
    private transient Stroke outlineStroke;
    
    /** The background paint for the interval. */
    private transient Paint backgroundPaint;
    
    /**
     * Creates a new interval.
     * 
     * @param label  the label (<code>null</code> not permitted).
     * @param range  the range (<code>null</code> not permitted).
     */
    public MeterInterval(String label, Range range) {
        this(label, range, Color.yellow, new BasicStroke(2.0f), null);
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[2]++;
    }
    
    /**
     * Creates a new interval.
     * 
     * @param label  the label (<code>null</code> not permitted).
     * @param range  the range (<code>null</code> not permitted).
     * @param outlinePaint  the outline paint (<code>null</code> permitted).
     * @param outlineStroke  the outline stroke (<code>null</code> permitted).
     * @param backgroundPaint  the background paint (<code>null</code> 
     *                         permitted).
     */
    public MeterInterval(String label, Range range, Paint outlinePaint, 
                         Stroke outlineStroke, Paint backgroundPaint) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[1]++;
            throw new IllegalArgumentException("Null 'label' argument.");

        } else {
  CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[2]++;}
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((range == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[3]++;
            throw new IllegalArgumentException("Null 'range' argument.");

        } else {
  CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[4]++;}
        this.label = label;
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[5]++;
        this.range = range;
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[6]++;
        this.outlinePaint = outlinePaint;
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[7]++;
        this.outlineStroke = outlineStroke;
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[8]++;
        this.backgroundPaint = backgroundPaint;
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[9]++;
    }
    
    /**
     * Returns the label.
     * 
     * @return The label (never <code>null</code>).
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Returns the range.
     * 
     * @return The range (never <code>null</code>).
     */
    public Range getRange() {
        return this.range;
    }

    /**
     * Returns the background paint.  If <code>null</code>, the background
     * should remain unfilled.
     * 
     * @return The background paint (possibly <code>null</code>).
     */
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    /**
     * Returns the outline paint.
     * 
     * @return The outline paint (possibly <code>null</code>).
     */
    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }
    
    /**
     * Returns the outline stroke.
     * 
     * @return The outline stroke (possibly <code>null</code>).
     */
    public Stroke getOutlineStroke() {
        return this.outlineStroke;
    }
    
    /**
     * Checks this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[6]++;}
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj instanceof MeterInterval) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[8]++;}
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[12]++;
        MeterInterval that = (MeterInterval) obj;
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.label.equals(that.label)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[10]++;}
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.range.equals(that.range)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[12]++;}
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[15]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[14]++;}
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[16]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[16]++;}
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[17]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.branches[18]++;}
        return true;
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
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[18]++;
        SerialUtilities.writePaint(this.outlinePaint, stream);
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[19]++;
        SerialUtilities.writeStroke(this.outlineStroke, stream);
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[20]++;
        SerialUtilities.writePaint(this.backgroundPaint, stream);
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[21]++;
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
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[22]++;
        this.outlinePaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[23]++;
        this.outlineStroke = SerialUtilities.readStroke(stream);
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[24]++;
        this.backgroundPaint = SerialUtilities.readPaint(stream);
CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41.statements[25]++;
    }

}

class CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41 ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.MeterInterval.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$6fpd56x8g3t693fo0mze93tjkd41 () {
    super("org.jfree.chart.plot.MeterInterval.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.plot.MeterInterval.java");
      for (int i = 1; i <= 25; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

