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
 * TaskSeries.java
 * ---------------
 * (C) Copyright 2002-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Jun-2002 : Version 1 (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 24-Oct-2002 : Added methods to get TimeAllocation by task index (DG);
 * 10-Jan-2003 : Renamed GanttSeries --> TaskSeries (DG);
 * 30-Jul-2004 : Added equals() method (DG);
 *
 */

package org.jfree.data.gantt;

import java.util.Collections;
import java.util.List;

import org.jfree.data.general.Series;

/**
 * A series that contains zero, one or many {@link Task} objects.
 * <P>
 * This class is used as a building block for the {@link TaskSeriesCollection}
 * class that can be used to construct basic Gantt charts.
 */
public class TaskSeries extends Series {
  static {
    CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.ping();
  }


    /** Storage for the tasks in the series. */
    private List tasks;

    /**
     * Constructs a new series with the specified name.
     *
     * @param name  the series name (<code>null</code> not permitted).
     */
    public TaskSeries(String name) {
        super(name);
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[1]++;
        this.tasks = new java.util.ArrayList();
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[2]++;
    }

    /**
     * Adds a task to the series and sends a 
     * {@link org.jfree.data.general.SeriesChangeEvent} to all registered 
     * listeners.
     *
     * @param task  the task (<code>null</code> not permitted).
     */
    public void add(Task task) {
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((task == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[1]++;
            throw new IllegalArgumentException("Null 'task' argument.");

        } else {
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[2]++;}
        this.tasks.add(task);
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[4]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[5]++;
    }

    /**
     * Removes a task from the series and sends 
     * a {@link org.jfree.data.general.SeriesChangeEvent} 
     * to all registered listeners.
     *
     * @param task  the task.
     */
    public void remove(Task task) {
        this.tasks.remove(task);
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[6]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[7]++;
    }

    /**
     * Removes all tasks from the series and sends 
     * a {@link org.jfree.data.general.SeriesChangeEvent} 
     * to all registered listeners.
     */
    public void removeAll() {
        this.tasks.clear();
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[8]++;
        fireSeriesChanged();
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[9]++;
    }

    /**
     * Returns the number of items in the series.
     *
     * @return The item count.
     */
    public int getItemCount() {
        return this.tasks.size();
    }

    /**
     * Returns a task from the series.
     *
     * @param index  the task index (zero-based).
     *
     * @return The task.
     */
    public Task get(int index) {
        return (Task) this.tasks.get(index);
    }
    
    /**
     * Returns the task in the series that has the specified description.
     * 
     * @param description  the name (<code>null</code> not permitted).
     * 
     * @return The task (possibly <code>null</code>).
     */
    public Task get(String description) {
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[10]++;
        Task result = null;
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[11]++;
        int count = this.tasks.size();
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < count) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.loops[1]--;
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.loops[2]--;
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.loops[3]++;
}
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[13]++;
            Task t = (Task) this.tasks.get(i);
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((t.getDescription().equals(description)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[3]++;
                result = t;
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[15]++;
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[16]++;
                break;

            } else {
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[4]++;}
        }
        return result;
    }

    /**
     * Returns an unmodifialble list of the tasks in the series.
     *
     * @return The tasks.
     */
    public List getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    /**
     * Tests this object for equality with an arbitrary object.
     * 
     * @param obj  the object to test against (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    public boolean equals(Object obj) {
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((obj == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[5]++;
            return true;

        } else {
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[6]++;}
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj instanceof TaskSeries) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[7]++;
            return false;

        } else {
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[8]++;}
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((super.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[10]++;}
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[20]++;
        TaskSeries that = (TaskSeries) obj;
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this.tasks.equals(that.tasks)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd.branches[12]++;}
        return true;
    }
    
}

class CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.jfree.data.gantt.TaskSeries.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
    for (int i = 1; i <= 7; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$pafqi2wf1412zx2ebeh7vxd () {
    super("org.jfree.data.gantt.TaskSeries.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.jfree.data.gantt.TaskSeries.java");
      for (int i = 1; i <= 21; i++) {
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
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

