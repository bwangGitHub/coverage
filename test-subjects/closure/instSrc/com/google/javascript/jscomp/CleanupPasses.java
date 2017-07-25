/*
 * Copyright 2012 The Closure Compiler Authors.
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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.DefaultPassConfig.HotSwapPassFactory;
import com.google.javascript.jscomp.GlobalVarReferenceMap.GlobalVarRefCleanupPass;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;

import java.util.List;


/**
 * Provides passes that should be run before hot-swap/incremental builds.
 *
 * @author tylerg@google.com (Tyler Goodwin)
 */
class CleanupPasses extends PassConfig {
  static {
    CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.ping();
  }


  private State state;

  public CleanupPasses(CompilerOptions options) {
    super(options);
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[1]++;
  }

  @Override
  protected List<PassFactory> getChecks() {
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[2]++;
    List<PassFactory> checks = Lists.newArrayList();
    checks.add(fieldCleanupPassFactory);
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[3]++;
    checks.add(scopeCleanupPassFactory);
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[4]++;
    checks.add(globalVarRefCleanupPassFactory);
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[5]++;
    return checks;
  }

  @Override
  protected State getIntermediateState() {
    return state;
  }

  @Override
  protected List<PassFactory> getOptimizations() {
    return ImmutableList.of();
  }

  @Override
  protected void setIntermediateState(State state) {
    this.state = state;
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[6]++;
  }

  final PassFactory fieldCleanupPassFactory =
      new HotSwapPassFactory("FieldCleaupPassFactory", false) {
        @Override
        protected HotSwapCompilerPass create(
            AbstractCompiler compiler) {
          return new FieldCleanupPass(compiler);
        }
      };
  {
    CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[7]++;
  }

  final PassFactory scopeCleanupPassFactory =
      new HotSwapPassFactory("ScopeCleanupPassFactory", false) {
        @Override
        protected HotSwapCompilerPass create(
            AbstractCompiler compiler) {
          return new MemoizedScopeCleanupPass(compiler);
        }
      };
  {
    CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[8]++;
  }

  final PassFactory globalVarRefCleanupPassFactory =
      new HotSwapPassFactory("GlobalVarRefCleanupPassFactory", false) {
        @Override
        protected HotSwapCompilerPass create(
            AbstractCompiler compiler) {
          return new GlobalVarRefCleanupPass(compiler);
        }
  };
  {
    CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[9]++;
  }

  /**
   * A CleanupPass implementation that will remove stored scopes from the
   * MemoizedScopeCreator of the compiler instance for a the hot swapped script.
   * <p>
   * This pass will also clear out Source Nodes of Function Types declared on
   * Vars tracked by MemoizedScopeCreator
   */
  static class MemoizedScopeCleanupPass implements HotSwapCompilerPass {

    private final AbstractCompiler compiler;

    public MemoizedScopeCleanupPass(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[10]++;
    }

    @Override
    public void hotSwapScript(Node scriptRoot, Node originalRoot) {
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[11]++;
      ScopeCreator creator = compiler.getTypedScopeCreator();
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((creator instanceof MemoizedScopeCreator) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.branches[1]++;
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[13]++;
        MemoizedScopeCreator scopeCreator = (MemoizedScopeCreator) creator;
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[14]++;
        String newSrc = scriptRoot.getSourceFileName();
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.loops[1]++;


        for (Var var : scopeCreator.getAllSymbols()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.loops[1]--;
  CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.loops[2]--;
  CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.loops[3]++;
}
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[16]++;
          JSType type = var.getType();
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.branches[3]++;
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[18]++;
            FunctionType fnType = type.toMaybeFunctionType();
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((fnType != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((newSrc.equals(NodeUtil.getSourceName(fnType.getSource()))) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.branches[5]++;
              fnType.setSource(null);
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[20]++;

            } else {
  CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.branches[6]++;}

          } else {
  CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.branches[4]++;}
        }
        scopeCreator.removeScopesForScript(originalRoot.getSourceFileName());
CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.statements[21]++;

      } else {
  CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht.branches[2]++;}
    }

    @Override
    public void process(Node externs, Node root) {
      // MemoizedScopeCleanupPass should not do work during process.
    }
  }
}

class CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CleanupPasses.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$5lujg4gi5qz3r8i5e4wk4526e6ht () {
    super("com.google.javascript.jscomp.CleanupPasses.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
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
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CleanupPasses.java");
      for (int i = 1; i <= 21; i++) {
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

