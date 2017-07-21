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
 * -------------------
 * IntervalMarker.java
 * -------------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 20-Aug-2002 : Added stroke to constructor in Marker class (DG);
 * 02-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Sep-2006 : Added MarkerChangeEvent notification (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;

import org.jfree.chart.event.MarkerChangeEvent;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.util.ObjectUtilities;

/**
 * Represents an interval to be highlighted in some way.
 */
public class IntervalMarker extends Marker implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = -1762344775267627916L;
  static {
    CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[1]++;
  }
    
    /** The start value. */
    private double startValue;

    /** The end value. */
    private double endValue;

    /** The gradient paint transformer (optional). */
    private GradientPaintTransformer gradientPaintTransformer;
    
    /**
     * Constructs an interval marker.
     *
     * @param start  the start of the interval.
     * @param end  the end of the interval.
     */
    public IntervalMarker(double start, double end) {
        this(start, end, Color.gray, new BasicStroke(0.5f), Color.gray, 
                new BasicStroke(0.5f), 0.8f);
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[2]++;
    }

    /**
     * Constructs an interval marker.
     *
     * @param start  the start of the interval.
     * @param end  the end of the interval.
     * @param paint  the paint.
     * @param stroke  the stroke.
     * @param outlinePaint  the outline paint.
     * @param outlineStroke  the outline stroke.
     * @param alpha  the alpha transparency.
     */
    public IntervalMarker(double start, double end, 
                          Paint paint, Stroke stroke,
                          Paint outlinePaint, Stroke outlineStroke, 
                          float alpha) {

        super(paint, stroke, outlinePaint, outlineStroke, alpha);
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[3]++;
        this.startValue = start;
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[4]++;
        this.endValue = end;
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[5]++;
        this.gradientPaintTransformer = null;
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[6]++;
        setLabelOffsetType(LengthAdjustmentType.CONTRACT);
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[7]++;
        
    }

    /**
     * Returns the start value for the interval.
     *
     * @return The start value.
     */
    public double getStartValue() {
        return this.startValue;
    }
    
    /**
     * Sets the start value for the marker and sends a 
     * {@link MarkerChangeEvent} to all registered listeners.
     * 
     * @param value  the value.
     * 
     * @since 1.0.3
     */
    public void setStartValue(double value) {
        this.startValue = value;
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[8]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[9]++;
    }

    /**
     * Returns the end value for the interval.
     *
     * @return The end value.
     */
    public double getEndValue() {
        return this.endValue;
    }
    
    /**
     * Sets the end value for the marker and sends a 
     * {@link MarkerChangeEvent} to all registered listeners.
     * 
     * @param value  the value.
     * 
     * @since 1.0.3
     */
    public void setEndValue(double value) {
        this.endValue = value;
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[10]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[11]++;
    }

    /**
     * Returns the gradient paint transformer.
     * 
     * @return The gradient paint transformer (possibly <code>null</code>).
     */
    public GradientPaintTransformer getGradientPaintTransformer() {
        return this.gradientPaintTransformer;   
    }
    
    /**
     * Sets the gradient paint transformer and sends a 
     * {@link MarkerChangeEvent} to all registered listeners.
     * 
     * @param transformer  the transformer (<code>null</code> permitted).
     */
    public void setGradientPaintTransformer(
            GradientPaintTransformer transformer) {
        this.gradientPaintTransformer = transformer;
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[12]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[13]++;        
    }
    
    /**
     * Tests the marker for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[1]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[2]++;}
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof IntervalMarker) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[4]++;}
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[6]++;}
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[17]++;
        IntervalMarker that = (IntervalMarker) obj;
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.startValue != that.startValue) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[8]++;}
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.endValue != that.endValue) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[9]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[10]++;}
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.gradientPaintTransformer, 
                that.gradientPaintTransformer)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[11]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl.branches[12]++;}
        return true;
    }
    
    /**
     * Returns a clone of the marker.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException Not thrown by this class, but the 
     *         exception is declared for the use of subclasses.
     */
    public Object clone() throws CloneNotSupportedException {   
        return super.clone();   
    }

}

class CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.IntervalMarker.java";
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

  public CodeCoverCoverageCounter$17f9q4riafv0bam004iwwzpiz24sbl () {
    super("org.jfree.chart.plot.IntervalMarker.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.IntervalMarker.java");
      for (int i = 1; i <= 20; i++) {
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

