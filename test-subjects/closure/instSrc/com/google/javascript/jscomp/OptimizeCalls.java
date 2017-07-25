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

package com.google.javascript.jscomp;

import com.google.common.collect.Lists;
import com.google.javascript.rhino.Node;

import java.util.List;

/**
 * A root pass that container for other passes that should run on
 * with a single call graph (currently a SimpleDefinitionFinder).
 * Expected passes include:
 *   - optimize parameters
 *   - optimize returns
 *   - devirtualize prototype methods
 *
 * @author johnlenz@google.com (John Lenz)
 */
class OptimizeCalls implements CompilerPass {
  static {
    CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.ping();
  }

  List<CallGraphCompilerPass> passes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.statements[1]++;
  }
  private AbstractCompiler compiler;

  OptimizeCalls(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.statements[2]++;
  }

  OptimizeCalls addPass(CallGraphCompilerPass pass) {
    passes.add(pass);
CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.statements[3]++;
    return this;
  }

  interface CallGraphCompilerPass {
    void process(Node externs, Node root, SimpleDefinitionFinder definitions);
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((passes.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.branches[1]++;
CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.statements[5]++;
      SimpleDefinitionFinder defFinder = new SimpleDefinitionFinder(compiler);
      defFinder.process(externs, root);
CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.statements[6]++;
CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.loops[1]++;


      for (CallGraphCompilerPass pass : passes) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.loops[1]--;
  CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.loops[2]--;
  CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.loops[3]++;
}
        pass.process(externs, root, defFinder);
CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.statements[8]++;
      }

    } else {
  CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h.branches[2]++;}
  }
}

class CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.OptimizeCalls.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$6ltjmzvesznyaoic3xixcjftyb8h () {
    super("com.google.javascript.jscomp.OptimizeCalls.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
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
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.OptimizeCalls.java");
      for (int i = 1; i <= 8; i++) {
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

