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

import java.util.List;

/**
 * An implementation of a {@link WarningsGuard} that can modify the
 * {@link CheckLevel} based on the file that caused the warning, and whether
 * this file matches a set of paths (specified either as include or exclude
 * of path name parts).
 *
 * <p>For example:
 * <pre>
 * List<String> paths = new ArrayList<String>();
 * paths.add("foo");
 * WarningsGuard guard =
 *     ByPathWarningsGuard.forPath(paths, CheckLevel.ERROR, 1);
 * </pre>
 *
 * This guard will convert any warning that came from a file that contains "foo"
 * in its path to an error.
 *
 */
public class ByPathWarningsGuard extends WarningsGuard {
  static {
    CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[1]++;
  }

  private final List<String> paths;
  private final boolean include;
  private final int priority;
  private CheckLevel level;

  /**
   * Constructs a new instance. The priority is determined by the
   * {@link CheckLevel}: ERROR have Priority.STRICT, and OFF have priority
   * FILTER_BY_PATH.
   *
   * Use {@link #forPath} or {@link #exceptPath} to actually create a new
   * instance.
   */
  private ByPathWarningsGuard(
      List<String> paths, boolean include, CheckLevel level) {
    Preconditions.checkArgument(paths != null);
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[2]++;
    Preconditions.checkArgument(
        level == CheckLevel.OFF || level == CheckLevel.ERROR);
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[3]++;
    this.paths = paths;
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[4]++;
    this.include = include;
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[5]++;
    this.level = level;
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[6]++;
    this.priority = level == CheckLevel.ERROR ?
        WarningsGuard.Priority.STRICT.value :
        WarningsGuard.Priority.FILTER_BY_PATH.value;
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[7]++;
  }

  /**
   * @param paths Paths for matching.
   * @param level The {@link CheckLevel} to apply on affected files.
   * @return a new {@link ByPathWarningsGuard} that would affect any file in the
   *     given set of paths.
   */
  public static ByPathWarningsGuard forPath(
      List<String> paths, CheckLevel level) {
    return new ByPathWarningsGuard(paths, true, level);
  }

  /**
   * @param paths Paths for matching.
   * @param level The {@link CheckLevel} to apply on affected files.
   * @return a new {@link ByPathWarningsGuard} that would affect any file not
   *     in the given set of paths.
   */
  public static ByPathWarningsGuard exceptPath(
      List<String> paths, CheckLevel level) {
    return new ByPathWarningsGuard(paths, false, level);
  }

  @Override
  public CheckLevel level(JSError error) {
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[8]++;
    final String errorPath = error.sourceName;
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[9]++;
    CheckLevel defaultLevel = error.getDefaultLevel();
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((defaultLevel != CheckLevel.ERROR) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((errorPath != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.branches[1]++;
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[11]++;
      boolean inPath = false;
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.loops[1]++;


      for (String path : paths) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.loops[1]--;
  CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.loops[2]--;
  CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.loops[3]++;
}
        inPath |= errorPath.contains(path);
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[13]++;
      }
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((inPath == include) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.branches[3]++;
        return level;

      } else {
  CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.branches[4]++;}

    } else {
  CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529.branches[2]++;}
    return null;
  }

  @Override
  protected int getPriority() {
    return priority;
  }
}

class CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529 ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ByPathWarningsGuard.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$fbjp8et11k2crxocpzyiis629deedgt54b529 () {
    super("com.google.javascript.jscomp.ByPathWarningsGuard.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ByPathWarningsGuard.java");
      for (int i = 1; i <= 14; i++) {
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

