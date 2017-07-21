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
 * ChartEditorManager.java
 * -----------------------
 * (C) Copyright 2005, 2007,by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * Changes
 * -------
 * 24-Nov-2005 : Version 1 (DG);
 *
 */

package org.jfree.chart.editor;

import org.jfree.chart.JFreeChart;

/**
 * The central point for obtaining {@link ChartEditor} instances for editing
 * charts.  Right now, the API is minimal - the plan is to extend this class 
 * to provide customisation options for chart editors (for example, make some 
 * editor items read-only).  
 */
public class ChartEditorManager {
  static {
    CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5.ping();
  }


    /** This factory creates new {@link ChartEditor} instances as required. */
    static ChartEditorFactory factory = new DefaultChartEditorFactory();
  static {
    CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5.statements[1]++;
  }
    
    /**
     * Private constructor prevents instantiation.
     */
    private ChartEditorManager() {
        // nothing to do   
    }
    
    /**
     * Returns the current factory.
     * 
     * @return The current factory (never <code>null</code>).
     */
    public static ChartEditorFactory getChartEditorFactory() {
        return factory;
    }
    
    /**
     * Sets the chart editor factory.
     * 
     * @param f  the new factory (<code>null</code> not permitted).
     */
    public static void setChartEditorFactory(ChartEditorFactory f) {
CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((f == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5.branches[1]++;
            throw new IllegalArgumentException("Null 'f' argument.");

        } else {
  CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5.branches[2]++;}
        factory = f;
CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5.statements[3]++;
    }
    
    /**
     * Returns a component that can be used to edit the given chart.
     * 
     * @param chart  the chart.
     * 
     * @return The chart editor.
     */    
    public static ChartEditor getChartEditor(JFreeChart chart) {
        return factory.createEditor(chart);
    }
}

class CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5 ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.jfree.chart.editor.ChartEditorManager.java";
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

  public CodeCoverCoverageCounter$26nfmlg4recrkzc09qz6b4o7ny41306el5r5 () {
    super("org.jfree.chart.editor.ChartEditorManager.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
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
    log.startNamedSection("org.jfree.chart.editor.ChartEditorManager.java");
      for (int i = 1; i <= 3; i++) {
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

