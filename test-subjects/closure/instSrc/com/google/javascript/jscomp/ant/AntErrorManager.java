/*
 * Copyright 2010 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp.ant;

import com.google.javascript.jscomp.BasicErrorManager;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.JSError;
import com.google.javascript.jscomp.MessageFormatter;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * An error manager that pipes warnings and errors properly into the Ant
 * task infrastructure.
 */
public final class AntErrorManager
    extends BasicErrorManager {
  static {
    CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.ping();
  }

  private final MessageFormatter formatter;
  private final Task task;

  public AntErrorManager(MessageFormatter formatter, Task task) {
    this.formatter = formatter;
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[1]++;
    this.task = task;
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[2]++;
  }

  @Override
  public void println(CheckLevel level, JSError error) {
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[3]++;
    switch (level) {
      case ERROR:
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.branches[1]++;
        this.task.log(error.format(level, this.formatter), Project.MSG_ERR);
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[4]++;
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[5]++;
        break;
      case WARNING:
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.branches[2]++;
        this.task.log(error.format(level, this.formatter), Project.MSG_WARN);
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[6]++;
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[7]++;
        break;
      case OFF:
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.branches[3]++;
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[8]++;
        break; default : CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.branches[4]++;
    }
  }

  @Override
  protected void printSummary() {
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[9]++;
    String message =
        getErrorCount() + " error(s), " + getWarningCount() + " warning(s)";
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((getTypedPercent() > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.branches[5]++;
      message += ", " + getTypedPercent() + " typed";
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[11]++;

    } else {
  CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.branches[6]++;}
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[12]++;

    int level = (getErrorCount() + getWarningCount() == 0) ?
        Project.MSG_INFO : Project.MSG_WARN;
    this.task.log(message, level);
CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5.statements[13]++;
  }
}

class CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5 ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ant.AntErrorManager.java";
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

  public CodeCoverCoverageCounter$7n5hmdky8wm3q56ag7t71gyeyxc78f5 () {
    super("com.google.javascript.jscomp.ant.AntErrorManager.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.ant.AntErrorManager.java");
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

