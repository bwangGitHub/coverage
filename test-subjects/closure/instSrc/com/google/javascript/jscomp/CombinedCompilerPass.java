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

import com.google.common.collect.Lists;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.rhino.Node;

import java.util.List;

/**
 * <p>A compiler pass combining multiple {@link Callback}
 * and {@link ScopedCallback} objects. This pass can be used to separate
 * logically different verifications without incurring any additional traversal
 * and CFG generation costs.</p>
 *
 * <p>Due to this compiler pass' nature, none of the callbacks may mutate
 * the parse tree.</p>
 *
 * <p>TODO(user):
 * This combined pass is currently limited in the type of callbacks it can
 * combine due to the difficulty of handling NodeTraversal's methods that
 * initiate more recursion (e.g., {@link NodeTraversal#traverse(Node)} and
 * {@link NodeTraversal#traverseInnerNode(Node, Node, Scope)}). The
 * {@link NodeTraversal} object passed to the individual callbacks should
 * be instrumented to emulate the correct behavior. For instance,
 * one could create a {@link NodeTraversal} whose
 * {@link NodeTraversal#traverseInnerNode(Node, Node, Scope)} ties
 * back into this compiler pass to give it context about what combined
 * passes are doing.</p>
 *
 */
final class CombinedCompilerPass implements HotSwapCompilerPass,
    ScopedCallback {
  static {
    CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.ping();
  }


  /** The callbacks that this pass combines. */
  private final CallbackWrapper[] callbacks;
  private final AbstractCompiler compiler;

  /**
   * Creates a combined compiler pass.
   * @param compiler the compiler
   */
  CombinedCompilerPass(
      AbstractCompiler compiler, Callback... callbacks) {
    this(compiler, Lists.<Callback>newArrayList(callbacks));
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[1]++;
  }

  CombinedCompilerPass(
      AbstractCompiler compiler, List<Callback> callbacks) {
    this.compiler = compiler;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[2]++;
    this.callbacks = new CallbackWrapper[callbacks.size()];
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[3]++;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < callbacks.size()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[1]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[2]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[3]++;
}
      this.callbacks[i] = new CallbackWrapper(callbacks.get(i));
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[5]++;
    }
  }

  static void traverse(AbstractCompiler compiler, Node root,
      List<Callback> callbacks) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((callbacks.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[1]++;
      NodeTraversal.traverse(compiler, root, callbacks.get(0));
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[7]++;

    } else {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[2]++;
      (new CombinedCompilerPass(compiler, callbacks)).process(null, root);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[8]++;
    }
  }

  /**
   * Maintains information about a callback in order to simulate it being the
   * exclusive client of the shared {@link NodeTraversal}. In particular, this
   * class simulates abbreviating the traversal when the wrapped callback
   * returns false for
   * {@link Callback#shouldTraverse(NodeTraversal, Node, Node)}.
   * The callback becomes inactive (i.e., traversal messages are not sent to it)
   * until the main traversal revisits the node during the post-order visit.
   *
   */
  private static class CallbackWrapper {
    /** The callback being wrapped. Never null. */
    private final Callback callback;
    /**
     * if (callback instanceof ScopedCallback), then scopedCallback points
     * to an instance of ScopedCallback, otherwise scopedCallback points to null
     */
    private final ScopedCallback scopedCallback;

    /**
     * The node that {@link Callback#shouldTraverse(NodeTraversal, Node, Node)}
     * returned false for. The wrapped callback doesn't receive messages until
     * after this node is revisited in the post-order traversal.
     */
    private Node waiting = null;
  {
    CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[9]++;
  }

    private CallbackWrapper(Callback callback) {
      this.callback = callback;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[10]++;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((callback instanceof ScopedCallback) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[3]++;
        scopedCallback = (ScopedCallback) callback;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[12]++;

      } else {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[4]++;
        scopedCallback = null;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[13]++;
      }
    }

    /**
     * Visits the node unless the wrapped callback is inactive. Activates the
     * callback if appropriate.
     */
    void visitOrMaybeActivate(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isActive()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[5]++;
        callback.visit(t, n, parent);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[15]++;

      } else {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[6]++;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[16]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((waiting == n) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[7]++;
        waiting = null;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[17]++;

      } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[8]++;}
}
    }

    void shouldTraverseIfActive(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((isActive()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((callback.shouldTraverse(t, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[9]++;
        waiting = n;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[19]++;

      } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[10]++;}
    }

    void enterScopeIfActive(NodeTraversal t) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((isActive()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((scopedCallback != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[11]++;
        scopedCallback.enterScope(t);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[21]++;

      } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[12]++;}
    }

    void exitScopeIfActive(NodeTraversal t) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[22]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((isActive()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((scopedCallback != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[13]++;
        scopedCallback.exitScope(t);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[23]++;

      } else {
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.branches[14]++;}
    }

    boolean isActive() {
      return waiting == null;
    }
  }

  @Override
  public final void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[24]++;
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
    NodeTraversal.traverse(compiler, scriptRoot, this);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[25]++;
  }

  @Override
  public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[4]++;


    for (CallbackWrapper callback : callbacks) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[4]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[5]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[6]++;
}
      callback.shouldTraverseIfActive(t, n, parent);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[27]++;
    }
    // Note that this method could return false if all callbacks are inactive.
    // This apparent optimization would make this method more expensive
    // in the typical case where not all nodes are inactive. It is
    // very unlikely that many all callbacks would be inactive at the same
    // time (indeed, there are several checking passes that never return false).
    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[28]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[7]++;


    for (CallbackWrapper callback : callbacks) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[7]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[8]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[9]++;
}
      callback.visitOrMaybeActivate(t, n, parent);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[29]++;
    }
  }

  @Override
  public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[30]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[10]++;


    for (CallbackWrapper callback : callbacks) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[10]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[11]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[12]++;
}
      callback.enterScopeIfActive(t);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[31]++;
    }
  }

  @Override
  public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[32]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[13]++;


    for (CallbackWrapper callback : callbacks) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[13]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[14]--;
  CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.loops[15]++;
}
      callback.exitScopeIfActive(t);
CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9.statements[33]++;
    }
  }
}

class CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9 ());
  }
    public static long[] statements = new long[34];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CombinedCompilerPass.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,2,2};
    for (int i = 1; i <= 8; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$32iu5axgdu6pf4o33bg9nclny296pnesgbgbqe9 () {
    super("com.google.javascript.jscomp.CombinedCompilerPass.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 33; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CombinedCompilerPass.java");
      for (int i = 1; i <= 33; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

