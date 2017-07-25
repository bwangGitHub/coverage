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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.javascript.rhino.Node;

/**
 * An abstract class whose implementations run peephole optimizations:
 * optimizations that look at a small section of code and either remove
 * that code (if it is not needed) or replaces it with smaller code.
 *
 */
abstract class AbstractPeepholeOptimization {
  static {
    CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.ping();
  }


  private AbstractCompiler compiler;

  /**
   * Given a node to optimize and a traversal, optimize the node. Subclasses
   * should override to provide their own peephole optimization.
   *
   * @param subtree The subtree that will be optimized.
   * @return The new version of the subtree (or null if the subtree or one of
   * its parents was removed from the AST). If the subtree has not changed,
   * this method must return {@code subtree}.
   */
  abstract Node optimizeSubtree(Node subtree);

  /**
   * Helper method for reporting an error to the compiler when applying a
   * peephole optimization.
   *
   * @param diagnostic The error type
   * @param n The node for which the error should be reported
   */
  protected void report(DiagnosticType diagnostic, Node n) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[1]++;
    JSError error =
        JSError.make(NodeUtil.getSourceName(n), n, diagnostic, n.toString());
    compiler.report(error);
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[2]++;
  }

  /**
   * Helper method for telling the compiler that something has changed.
   * Subclasses must call these if they have changed the AST.
   */
  protected void reportCodeChange() {
    Preconditions.checkNotNull(compiler);
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[3]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[4]++;
  }

  /**
   * Are the nodes equal for the purpose of inlining?
   * If type aware optimizations are on, type equality is checked.
   */
  protected boolean areNodesEqualForInlining(Node n1, Node n2) {
    /* Our implementation delegates to the compiler. We provide this
     * method because we don't want to expose Compiler to PeepholeOptimizations.
     */
    Preconditions.checkNotNull(compiler);
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[5]++;
    return compiler.areNodesEqualForInlining(n1, n2);
  }

  /**
   *  Is the current AST normalized? (e.g. has the Normalize pass been run
   *  and has the Denormalize pass not yet been run?)
   */
  protected boolean isASTNormalized() {
    Preconditions.checkNotNull(compiler);
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[6]++;

    return compiler.getLifeCycleStage().isNormalized();
  }

  /**
   * Informs the optimization that a traversal will begin.
   */
  void beginTraversal(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[7]++;
  }

  /**
   * Informs the optimization that a traversal has completed.
   * @param compiler The current compiler.
   */
  void endTraversal(AbstractCompiler compiler) {
    this.compiler = null;
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[8]++;
  }

  // NodeUtil's mayEffectMutableState and mayHaveSideEffects need access to the
  // compiler object, route them through here to give them access.

  /**
   * @return Whether the node may create new mutable state, or change existing
   * state.
   */
  boolean mayEffectMutableState(Node n) {
    return NodeUtil.mayEffectMutableState(n, compiler);
  }

  /**
   * @return Whether the node may have side effects when executed.
   */
  boolean mayHaveSideEffects(Node n) {
    return NodeUtil.mayHaveSideEffects(n, compiler);
  }

  /**
   * Returns true if the current node's type implies side effects.
   *
   * This is a non-recursive version of the may have side effects
   * check; used to check wherever the current node's type is one of
   * the reason's why a subtree has side effects.
   */
  boolean nodeTypeMayHaveSideEffects(Node n) {
    return NodeUtil.nodeTypeMayHaveSideEffects(n, compiler);
  }

  /**
   * @return Whether the source code version is ECMAScript 5 or later.
   *     Workarounds for quirks in browsers that do not support ES5 can be
   *     ignored when this is true.
   */
  boolean isEcmaScript5OrGreater() {
    return compiler != null
        && compiler.acceptEcmaScript5();
  }

  /**
   * @return the current coding convention.
   */
  CodingConvention getCodingConvention() {
    // Note: this assumes a thread safe coding convention object.
    return compiler.getCodingConvention();
  }

  /**
   * Check if the specified node is null or is still in the AST.
   */
  @VisibleForTesting
  static Node validateResult(Node n) {
    done: {
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && (!
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isSyntheticBlock()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.branches[1]++;
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.loops[1]++;


        for (Node parent : n.getAncestors()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.loops[1]--;
  CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.loops[2]--;
  CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.loops[3]++;
}
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.branches[3]++;
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[12]++;
            break done;

          } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.branches[4]++;}
        }
        Preconditions.checkState(false);
CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.statements[13]++;

      } else {
  CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5.branches[2]++;}
    }
    return n;
  }
}

class CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5 ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AbstractPeepholeOptimization.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1};
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

  public CodeCoverCoverageCounter$bl6rgcoxr7ya00yyntslrpk0nc04xhz47jsquirkj21hzij0rj5 () {
    super("com.google.javascript.jscomp.AbstractPeepholeOptimization.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.AbstractPeepholeOptimization.java");
      for (int i = 1; i <= 13; i++) {
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

