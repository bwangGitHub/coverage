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
 * ---------
 * Task.java
 * ---------
 * (C) Copyright 2003-2007, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 10-Jan-2003 : Version 1 (DG);
 * 16-Sep-2003 : Added percentage complete (DG);
 * 30-Jul-2004 : Added clone() and equals() methods and implemented 
 *               Serializable (DG);
 *
 */

package org.jfree.data.gantt;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriod;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;

/**
 * A simple representation of a task.  The task has a description and a 
 * duration.  You can add sub-tasks to the task.
 */
public class Task implements Cloneable, PublicCloneable, Serializable {
  static {
    CodeCoverCoverageCounter$94hxcjzpr20u3l.ping();
  }


    /** For serialization. */
    private static final long serialVersionUID = 1094303785346988894L;
  static {
    CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[1]++;
  }
    
    /** The task description. */
    private String description;

    /** The time period for the task (estimated or actual). */
    private TimePeriod duration;
    
    /** The percent complete (<code>null</code> is permitted). */
    private Double percentComplete;

    /** Storage for the sub-tasks (if any). */
    private List subtasks;

    /**
     * Creates a new task.
     *
     * @param description  the task description (<code>null</code> not 
     *                     permitted).
     * @param duration  the task duration (<code>null</code> permitted).
     */
    public Task(String description, TimePeriod duration) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((description == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[1]++;
            throw new IllegalArgumentException("Null 'description' argument.");

        } else {
  CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[2]++;}
        this.description = description;
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[3]++;
        this.duration = duration;
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[4]++;
        this.percentComplete = null;
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[5]++;
        this.subtasks = new java.util.ArrayList();
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[6]++;
    }
    
    /**
     * Creates a new task.
     * 
     * @param description  the task description (<code>null</code> not 
     *                     permitted).
     * @param start  the start date (<code>null</code> not permitted).
     * @param end  the end date (<code>null</code> not permitted).
     */
    public Task(String description, Date start, Date end) {
        this(description, new SimpleTimePeriod(start, end));
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[7]++;
    }

    /**
     * Returns the task description.
     *
     * @return The task description (never <code>null</code>).
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the task description.
     *
     * @param description  the description (<code>null</code> not permitted).
     */
    public void setDescription(String description) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((description == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[3]++;
            throw new IllegalArgumentException("Null 'description' argument.");

        } else {
  CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[4]++;}
        this.description = description;
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[9]++;
    }

    /**
     * Returns the duration (actual or estimated) of the task.
     *
     * @return The task duration (possibly <code>null</code>).
     */
    public TimePeriod getDuration() {
        return this.duration;
    }

    /**
     * Sets the task duration (actual or estimated).
     *
     * @param duration  the duration (<code>null</code> permitted).
     */
    public void setDuration(TimePeriod duration) {
        this.duration = duration;
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[10]++;
    }
    
    /**
     * Returns the percentage complete for this task.
     * 
     * @return The percentage complete (possibly <code>null</code>).
     */
    public Double getPercentComplete() {
        return this.percentComplete;
    }
    
    /**
     * Sets the percentage complete for the task.
     * 
     * @param percent  the percentage (<code>null</code> permitted).
     */
    public void setPercentComplete(Double percent) {
        this.percentComplete = percent;
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[11]++;
    }

    /**
     * Sets the percentage complete for the task.
     * 
     * @param percent  the percentage.
     */
    public void setPercentComplete(double percent) {
        setPercentComplete(new Double(percent));
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[12]++;
    }
    
    /**
     * Adds a sub-task to the task.
     *
     * @param subtask  the subtask (<code>null</code> not permitted).
     */
    public void addSubtask(Task subtask) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((subtask == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[5]++;
            throw new IllegalArgumentException("Null 'subtask' argument.");

        } else {
  CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[6]++;}
        this.subtasks.add(subtask);
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[14]++;
    }

    /**
     * Removes a sub-task from the task.
     *
     * @param subtask  the subtask.
     */
    public void removeSubtask(Task subtask) {
        this.subtasks.remove(subtask);
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[15]++;
    }

    /**
     * Returns the sub-task count.
     *
     * @return The sub-task count.
     */
    public int getSubtaskCount() {
        return this.subtasks.size();
    }

    /**
     * Returns a sub-task.
     *
     * @param index  the index.
     *
     * @return The sub-task.
     */
    public Task getSubtask(int index) {
        return (Task) this.subtasks.get(index);
    }
    
    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param object  the other object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    public boolean equals(Object object) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((object == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[7]++;
            return true;

        } else {
  CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[8]++;}
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((object instanceof Task) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[9]++;
            return false;

        } else {
  CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[10]++;}
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[18]++;
        Task that = (Task) object;
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.description, that.description)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[11]++;
            return false;

        } else {
  CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[12]++;}
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.duration, that.duration)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[13]++;
            return false;

        } else {
  CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[14]++;}
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.percentComplete, 
                that.percentComplete)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[15]++;
            return false;

        } else {
  CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[16]++;}
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[22]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((ObjectUtilities.equal(this.subtasks, that.subtasks)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$94hxcjzpr20u3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[17]++;
            return false;

        } else {
  CodeCoverCoverageCounter$94hxcjzpr20u3l.branches[18]++;}
        return true;
    }

    /**
     * Returns a clone of the task.
     * 
     * @return A clone.
     * 
     * @throws CloneNotSupportedException  never thrown by this class, but 
     *         subclasses may not support cloning.
     */
    public Object clone() throws CloneNotSupportedException {
CodeCoverCoverageCounter$94hxcjzpr20u3l.statements[23]++;
        Task clone = (Task) super.clone();
        return clone;      
    }

}

class CodeCoverCoverageCounter$94hxcjzpr20u3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$94hxcjzpr20u3l ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "org.jfree.data.gantt.Task.java";
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

  public CodeCoverCoverageCounter$94hxcjzpr20u3l () {
    super("org.jfree.data.gantt.Task.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
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
    log.startNamedSection("org.jfree.data.gantt.Task.java");
      for (int i = 1; i <= 23; i++) {
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

