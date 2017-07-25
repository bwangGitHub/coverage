/*
 * Copyright 2007 The Closure Compiler Authors.
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

package com.google.javascript.jscomp;

import com.google.javascript.jscomp.CheckLevel;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * An error manager that logs errors and warnings using a logger in addition to
 * collecting them in memory. Errors are logged at the SEVERE level and warnings
 * are logged at the WARNING level.
 *
 */
public class LoggerErrorManager extends BasicErrorManager {
  static {
    CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.ping();
  }

  private final MessageFormatter formatter;
  private final Logger logger;

  /**
   * Creates an instance.
   */
  public LoggerErrorManager(MessageFormatter formatter, Logger logger) {
    this.formatter = formatter;
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[1]++;
    this.logger = logger;
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[2]++;
  }

  /**
   * Creates an instance with a source-less error formatter.
   */
  public LoggerErrorManager(Logger logger) {
    this(ErrorFormat.SOURCELESS.toFormatter(null, false), logger);
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[3]++;
  }

  @Override
  public void println(CheckLevel level, JSError error) {
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[4]++;
    switch (level) {
      case ERROR:
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.branches[1]++;
        logger.severe(error.format(level, formatter));
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[5]++;
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[6]++;
        break;
      case WARNING:
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.branches[2]++;
        logger.warning(error.format(level, formatter));
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[7]++;
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[8]++;
        break;
      case OFF:
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.branches[3]++;
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[9]++;
        break; default : CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.branches[4]++;
    }
  }

  @Override
  protected void printSummary() {
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[10]++;
    Level level = (getErrorCount() + getWarningCount() == 0) ?
        Level.INFO : Level.WARNING;
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((getTypedPercent() > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.branches[5]++;
      logger.log(level, "{0} error(s), {1} warning(s), {2,number,#.#}% typed",
          new Object[] {getErrorCount(), getWarningCount(), getTypedPercent()});
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[12]++;

    } else {
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.branches[6]++;
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getErrorCount() + getWarningCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.branches[7]++;
        logger.log(level, "{0} error(s), {1} warning(s)",
            new Object[] {getErrorCount(), getWarningCount()});
CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.statements[14]++;

      } else {
  CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01.branches[8]++;}
    }
  }
}

class CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01 ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.LoggerErrorManager.java";
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

  public CodeCoverCoverageCounter$2h6mlkq6hqfvuuysu6rltz9ia3m20mem4m01 () {
    super("com.google.javascript.jscomp.LoggerErrorManager.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.LoggerErrorManager.java");
      for (int i = 1; i <= 14; i++) {
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

