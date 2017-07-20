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
 * ContourEntity.java
 * ------------------
 * (C) Copyright 2002-2007, by David M. O'Donnell and Contributors.
 *
 * Original Author:  David M. O'Donnell;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 26-Nov-2002 : Version 1 contributed by David M. O'Donnell (DG);
 * 20-May-2004 : Added equals() and clone() methods and implemented 
 *               Serializable (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.jfree.chart.entity;

import java.awt.Shape;
import java.io.Serializable;

import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;

/**
 * Represents an item on a contour chart.
 *
 * @deprecated This class is no longer supported (as of version 1.0.4).  If 
 *     you are creating contour plots, please try to use {@link XYPlot} and 
 *     {@link XYBlockRenderer}.
 */
public class ContourEntity extends ChartEntity 
                           implements Cloneable, Serializable {
  static {
    CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1249570520505992847L;
  static {
    CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.statements[1]++;
  }
    
    /** Holds the index into the dataset for this entity. */
    private int index = -1;
  {
    CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.statements[2]++;
  }

    /**
     * Constructor for ContourEntity.
     *
     * @param area  the area.
     * @param toolTipText  the tooltip text.
     */
    public ContourEntity(Shape area, String toolTipText) {
        super(area, toolTipText);
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.statements[3]++;
    }

    /**
     * Constructor for ContourEntity.
     *
     * @param area  the area.
     * @param toolTipText  the tooltip text.
     * @param urlText  the URL text.
     */
    public ContourEntity(Shape area, String toolTipText, String urlText) {
        super(area, toolTipText, urlText);
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.statements[4]++;
    }

    /**
     * Returns the index.
     *
     * @return The index.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Sets the index.
     *
     * @param index  the index.
     */
    public void setIndex(int index) {
        this.index = index;
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.statements[5]++;
    }

    /**
     * Tests the entity for equality with an arbitrary object.
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.branches[1]++;
            return true;
   
        } else {
  CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.branches[2]++;}
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((obj instanceof ContourEntity) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.branches[3]++;
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.statements[8]++;
            ContourEntity ce = (ContourEntity) obj;
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.index != ce.index) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.branches[5]++;
                return false;
   
            } else {
  CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.branches[6]++;}
            return true;

        } else {
  CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5.branches[4]++;}
        return false;
    }
    
    /**
     * Returns a clone of the entity.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException if cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}

class CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5 ());
  }
    public static long[] statements = new long[10];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.jfree.chart.entity.ContourEntity.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1};
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

  public CodeCoverCoverageCounter$5lvtgaimy5448eg6evw26oll9jz5 () {
    super("org.jfree.chart.entity.ContourEntity.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 9; i++) {
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
    log.startNamedSection("org.jfree.chart.entity.ContourEntity.java");
      for (int i = 1; i <= 9; i++) {
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

