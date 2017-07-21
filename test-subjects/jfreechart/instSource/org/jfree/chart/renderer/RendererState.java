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
 * RendererState.java
 * ------------------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 07-Oct-2003 : Version 1 (DG);
 * 09-Jun-2005 : Added a convenience method to access the entity 
 *               collection (DG);
 *
 */

package org.jfree.chart.renderer;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.PlotRenderingInfo;

/**
 * Represents the current state of a renderer.
 */
public class RendererState {
  static {
    CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.ping();
  }

    
    /** The plot rendering info. */
    private PlotRenderingInfo info;
    
    /**
     * Creates a new state object.
     * 
     * @param info  the plot rendering info.
     */
    public RendererState(PlotRenderingInfo info) {
        this.info = info;
CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.statements[1]++;
    }
    
    /**
     * Returns the plot rendering info.
     * 
     * @return The info.
     */
    public PlotRenderingInfo getInfo() {
        return this.info;
    }
    
    /**
     * A convenience method that returns a reference to the entity
     * collection (may be <code>null</code>) being used to record
     * chart entities.
     * 
     * @return The entity collection (possibly <code>null</code>).
     */
    public EntityCollection getEntityCollection() {
CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.statements[2]++;
        EntityCollection result = null;
CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.info != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.branches[1]++;
CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.statements[4]++;
            ChartRenderingInfo owner = this.info.getOwner();
CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((owner != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.branches[3]++;
                result = owner.getEntityCollection();
CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.statements[6]++;
 
            } else {
  CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.branches[4]++;}

        } else {
  CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl.branches[2]++;}
        return result;
    }
    
}

class CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.jfree.chart.renderer.RendererState.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$6uo88k8bms59mi075rti7y5n6wrl () {
    super("org.jfree.chart.renderer.RendererState.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.chart.renderer.RendererState.java");
      for (int i = 1; i <= 6; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

