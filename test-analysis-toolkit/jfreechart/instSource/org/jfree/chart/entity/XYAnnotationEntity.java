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
 * -----------------------
 * XYAnnotationEntity.java
 * -----------------------
 * (C) Copyright 2004, 2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 29-Sep-2004 : Version 1 (DG);
 *
 */

package org.jfree.chart.entity;

import java.awt.Shape;
import java.io.Serializable;

/**
 * A chart entity that represents an annotation on an 
 * {@link org.jfree.chart.plot.XYPlot}.
 */
public class XYAnnotationEntity extends ChartEntity
                                implements Serializable {
  static {
    CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.ping();
  }

    
    /** For serialization. */
    private static final long serialVersionUID = 2340334068383660799L;
  static {
    CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.statements[1]++;
  }
    
    /** The renderer index. */
    private int rendererIndex;

    /**
     * Creates a new entity.
     *
     * @param hotspot  the area.
     * @param rendererIndex  the rendererIndex (zero-based index).
     * @param toolTipText  the tool tip text.
     * @param urlText  the URL text for HTML image maps.
     */
    public XYAnnotationEntity(Shape hotspot, int rendererIndex,
                              String toolTipText, String urlText) {
        super(hotspot, toolTipText, urlText);
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.statements[2]++;
        this.rendererIndex = rendererIndex;
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.statements[3]++;
    }

    /**
     * Returns the renderer index.
     *
     * @return The renderer index.
     */
    public int getRendererIndex() {
        return this.rendererIndex;
    }

    /**
     * Sets the renderer index.
     *
     * @param index  the item index (zero-based).
     */
    public void setRendererIndex(int index) {
        this.rendererIndex = index;
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.statements[4]++;
    }

    /**
     * Tests the entity for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.branches[1]++;
            return true;
      
        } else {
  CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.branches[2]++;}
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.branches[3]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.branches[4]++;}
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof XYAnnotationEntity) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.branches[5]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.branches[6]++;}
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.statements[8]++;
        XYAnnotationEntity that = (XYAnnotationEntity) obj;
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.rendererIndex != that.rendererIndex) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.branches[7]++;
            return false;
   
        } else {
  CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp.branches[8]++;}
        return true;
    }

}

class CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.jfree.chart.entity.XYAnnotationEntity.java";
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

  public CodeCoverCoverageCounter$2v31r3jvc75r8mmo1e1jazntt3dgon826iqp () {
    super("org.jfree.chart.entity.XYAnnotationEntity.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("org.jfree.chart.entity.XYAnnotationEntity.java");
      for (int i = 1; i <= 9; i++) {
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

