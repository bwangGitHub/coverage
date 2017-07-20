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
 * ------------------------
 * CategoryLabelEntity.java
 * ------------------------
 * (C) Copyright 2006, 2007, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 02-Oct-2006 : Version 1 (DG);
 * 13-Nov-2007 : Added equals() and hashCode() methods (DG);
 *
 */

package org.jfree.chart.entity;

import java.awt.Shape;

import org.jfree.chart.HashUtilities;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.util.ObjectUtilities;

/**
 * An entity to represent the labels on a {@link CategoryAxis}.
 * 
 * @since 1.0.3
 */
public class CategoryLabelEntity extends TickLabelEntity {
  static {
    CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.ping();
  }

    
    /** The category key. */
    private Comparable key;
    
    /**
     * Creates a new entity.
     * 
     * @param key  the category key.
     * @param area  the hotspot.
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text.
     */
    public CategoryLabelEntity(Comparable key, Shape area, 
            String toolTipText, String urlText) {
        super(area, toolTipText, urlText);
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[1]++;
        this.key = key;
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[2]++;
    }
    
    /**
     * Returns the category key.
     * 
     * @return The category key.
     */
    public Comparable getKey() {
        return this.key;
    }
    
    /**
     * Tests this instance for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.branches[2]++;}
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((obj instanceof CategoryLabelEntity) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.branches[4]++;}
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[5]++;
        CategoryLabelEntity that = (CategoryLabelEntity) obj;
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.key, that.key)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.branches[6]++;}
        return super.equals(obj);
    }
    
    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    public int hashCode() {
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[7]++;
        int result = super.hashCode();
        result = HashUtilities.hashCode(result, this.key);
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[8]++;
        return result;
    }
    
    /**
     * Returns a string representation of this entity.  This is primarily 
     * useful for debugging.
     * 
     * @return A string representation of this entity.
     */
    public String toString() {
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[9]++;
        StringBuffer buf = new StringBuffer("CategoryLabelEntity: ");
        buf.append("category=");
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[10]++;
        buf.append(this.key);
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[11]++;
        buf.append(", tooltip=" + getToolTipText());
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[12]++;
        buf.append(", url=" + getURLText());
CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335.statements[13]++;
        return buf.toString();
    }
}

class CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335 ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.jfree.chart.entity.CategoryLabelEntity.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$fj2k7ge0cjkapal4ul34yaz7e9lzp4a103335 () {
    super("org.jfree.chart.entity.CategoryLabelEntity.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.entity.CategoryLabelEntity.java");
      for (int i = 1; i <= 13; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

