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
 * CategoryMarker.java
 * -------------------
 * (C) Copyright 2005-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Nicolas Brodu;
 *
 * Changes
 * -------
 * 20-May-2005 : Version 1 (DG);
 * 19-Aug-2005 : Implemented equals(), fixed bug in constructor (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 05-Sep-2006 : Added MarkerChangeListener support (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;

import org.jfree.chart.event.MarkerChangeEvent;
import org.jfree.ui.LengthAdjustmentType;

/**
 * A marker for a category.
 * <br><br>
 * Note that for serialization to work correctly, the category key must be an
 * instance of a serializable class.
 * 
 * @see CategoryPlot#addDomainMarker(CategoryMarker)
 */
public class CategoryMarker extends Marker implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.ping();
  }


    /** The category key. */
    private Comparable key;
    
    /** 
     * A hint that the marker should be drawn as a line rather than a region. 
     */
    private boolean drawAsLine = false;
  {
    CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[1]++;
  }
    
    /**
     * Creates a new category marker for the specified category.
     * 
     * @param key  the category key.
     */
    public CategoryMarker(Comparable key) {
        this(key, Color.gray, new BasicStroke(1.0f));
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[2]++;    
    }
    
    /**
     * Creates a new category marker.
     * 
     * @param key  the key.
     * @param paint  the paint (<code>null</code> not permitted).
     * @param stroke  the stroke (<code>null</code> not permitted).
     */
    public CategoryMarker(Comparable key, Paint paint, Stroke stroke) {
        this(key, paint, stroke, paint, stroke, 1.0f);
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[3]++;
    }
    
    /**
     * Creates a new category marker.
     * 
     * @param key  the key.
     * @param paint  the paint (<code>null</code> not permitted).
     * @param stroke  the stroke (<code>null</code> not permitted).
     * @param outlinePaint  the outline paint (<code>null</code> permitted).
     * @param outlineStroke  the outline stroke (<code>null</code> permitted).
     * @param alpha  the alpha transparency.
     */
    public CategoryMarker(Comparable key, Paint paint, Stroke stroke, 
                          Paint outlinePaint, Stroke outlineStroke, 
                          float alpha) {
        super(paint, stroke, outlinePaint, outlineStroke, alpha);
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[4]++;
        this.key = key;
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[5]++;
        setLabelOffsetType(LengthAdjustmentType.EXPAND);
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[6]++;
    }
    
    /**
     * Returns the key.
     * 
     * @return The key.
     */
    public Comparable getKey() {
        return this.key;   
    }
    
    /**
     * Sets the key and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     * 
     * @param key  the key (<code>null</code> not permitted).
     * 
     * @since 1.0.3
     */
    public void setKey(Comparable key) {
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[1]++;
            throw new IllegalArgumentException("Null 'key' argument.");

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[2]++;}
        this.key = key;
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[8]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[9]++;
    }
    
    /**
     * Returns the flag that controls whether the marker is drawn as a region 
     * or a line.
     * 
     * @return A line.
     */
    public boolean getDrawAsLine() {
        return this.drawAsLine;   
    }
    
    /**
     * Sets the flag that controls whether the marker is drawn as a region or
     * as a line, and sends a {@link MarkerChangeEvent} to all registered
     * listeners.
     * 
     * @param drawAsLine  the flag.
     */
    public void setDrawAsLine(boolean drawAsLine) {
        this.drawAsLine = drawAsLine;
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[10]++;
        notifyListeners(new MarkerChangeEvent(this));
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[11]++;
    }
    
    /**
     * Tests the marker for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[4]++;}
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryMarker) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[6]++;}
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[8]++;}
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[15]++;
        CategoryMarker that = (CategoryMarker) obj;
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((this.key.equals(that.key)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[10]++;}
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this.drawAsLine != that.drawAsLine) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh.branches[12]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.jfree.chart.plot.CategoryMarker.java";
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

  public CodeCoverCoverageCounter$13ugf3fy51kl4cja7qalw15mtpbzoh () {
    super("org.jfree.chart.plot.CategoryMarker.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
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
    log.startNamedSection("org.jfree.chart.plot.CategoryMarker.java");
      for (int i = 1; i <= 17; i++) {
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

