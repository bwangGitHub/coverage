/*
 * Copyright 2008 The Closure Compiler Authors.
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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Control whether warnings should be restricted or suppressed for specified
 * paths.
 *
 * @author anatol@google.com (Anatol Pomazau)
 */
public class ShowByPathWarningsGuard extends WarningsGuard {
  static {
    CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[1]++;
  }

  /**
   * Controls whether warnings should be restricted to a specified path or
   * suppressed within the specified path.
   */
  public enum ShowType {
    INCLUDE,  // Suppress warnings outside the path.
    EXCLUDE;  // Suppress warnings within the path.
  }

  private final ByPathWarningsGuard warningsGuard;

  public ShowByPathWarningsGuard(String checkWarningsOnlyForPath) {
    this(checkWarningsOnlyForPath, ShowType.INCLUDE);
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[2]++;
  }

  public ShowByPathWarningsGuard(String[] checkWarningsOnlyForPath) {
    this(checkWarningsOnlyForPath, ShowType.INCLUDE);
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[3]++;
  }

  public ShowByPathWarningsGuard(String path, ShowType showType) {
    this(new String[] { path }, showType);
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[4]++;
  }

  public ShowByPathWarningsGuard(String[] paths, ShowType showType) {
    Preconditions.checkArgument(paths != null);
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[5]++;
    Preconditions.checkArgument(showType != null);
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[6]++;
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[7]++;
    List<String> pathList = Lists.newArrayList(paths);
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((showType == ShowType.INCLUDE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.branches[1]++;
      warningsGuard = ByPathWarningsGuard.exceptPath(pathList, CheckLevel.OFF);
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[9]++;

    } else {
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.branches[2]++;
      warningsGuard = ByPathWarningsGuard.forPath(pathList, CheckLevel.OFF);
CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx.statements[10]++;
    }
  }

  @Override
  public CheckLevel level(JSError error) {
    return warningsGuard.level(error);
  }

  @Override
  protected int getPriority() {
    return warningsGuard.getPriority();
  }
}

class CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ShowByPathWarningsGuard.java";
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

  public CodeCoverCoverageCounter$11xh4je032fcvbbsykx4zkp124sn8g28n34iyz4widsx () {
    super("com.google.javascript.jscomp.ShowByPathWarningsGuard.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.ShowByPathWarningsGuard.java");
      for (int i = 1; i <= 10; i++) {
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

