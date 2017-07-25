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
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.DataFlowAnalysis.FlowState;
import com.google.javascript.jscomp.LiveVariablesAnalysis.LiveVariableLattice;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * Removes local variable assignments that are useless based on information from
 * {@link LiveVariablesAnalysis}. If there is an assignment to variable
 * {@code x} and {@code x} is dead after this assignment, we know that the
 * current content of {@code x} will not be read and this assignment is useless.
 *
 */
class DeadAssignmentsElimination extends AbstractPostOrderCallback implements
    CompilerPass, ScopedCallback {
  static {
    CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.ping();
  }


  private final AbstractCompiler compiler;
  private LiveVariablesAnalysis liveness;

  // Matches all assignment operators and increment/decrement operators.
  // Does *not* match VAR initialization, since RemoveUnusedVariables
  // will already remove variables that are initialized but unused.
  private static final Predicate<Node> matchRemovableAssigns =
      new Predicate<Node>() {
    @Override
    public boolean apply(Node n) {
      return (NodeUtil.isAssignmentOp(n) &&
              n.getFirstChild().isName()) ||
          n.isInc() || n.isDec();
    }
  };
  static {
    CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[1]++;
  }

  public DeadAssignmentsElimination(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
    Preconditions.checkNotNull(externs);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[3]++;
    Preconditions.checkNotNull(root);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[4]++;
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[5]++;
  }

  @Override
  public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[6]++;
    Scope scope = t.getScope();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    // Global scope _SHOULD_ work, however, liveness won't finish without
    // -Xmx1024 in closure. We might have to look at coding conventions for
    // exported variables as well.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scope.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[1]++;
      return;

    } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[2]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((LiveVariablesAnalysis.MAX_VARIABLES_TO_ANALYZE <
        t.getScope().getVarCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[4]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[9]++;

    // We are not going to do any dead assignment elimination in when there is
    // at least one inner function because in most browsers, when there is a
    // closure, ALL the variables are saved (escaped).
    Node fnBlock = t.getScopeRoot().getLastChild();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.containsFunction(fnBlock)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[5]++;
      return;

    } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[6]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;

    // We don't do any dead assignment elimination if there are no assigns
    // to eliminate. :)
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.has(fnBlock, matchRemovableAssigns,
            Predicates.<Node>alwaysTrue())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[7]++;
      return;

    } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[8]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[12]++;

    // Computes liveness information first.
    ControlFlowGraph<Node> cfg = t.getControlFlowGraph();
    liveness = new LiveVariablesAnalysis(cfg, scope, compiler);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[13]++;
    liveness.analyze();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[14]++;
    tryRemoveDeadAssignments(t, cfg);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[15]++;
  }

  @Override
  public void exitScope(NodeTraversal t) {
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
  }

  /**
   * Try to remove useless assignments from a control flow graph that has been
   * annotated with liveness information.
   *
   * @param t The node traversal.
   * @param cfg The control flow graph of the program annotated with liveness
   *        information.
   */
  private void tryRemoveDeadAssignments(NodeTraversal t,
      ControlFlowGraph<Node> cfg) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[16]++;
    Iterable<DiGraphNode<Node, Branch>> nodes = cfg.getDirectedGraphNodes();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[17]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[1]++;



    for (DiGraphNode<Node, Branch> cfgNode : nodes) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[1]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[2]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[3]++;
}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[18]++;
      FlowState<LiveVariableLattice> state =
          cfgNode.getAnnotation();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[19]++;
      Node n = cfgNode.getValue();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[9]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[21]++;
        continue;

      } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[10]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[22]++;
      switch (n.getType()) {
        case Token.IF:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[11]++;
        case Token.WHILE:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[12]++;
        case Token.DO:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[13]++;
          tryRemoveAssignment(t, NodeUtil.getConditionExpression(n), state);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[23]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[24]++;
          continue;
        case Token.FOR:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[14]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(n)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[15]++;
            tryRemoveAssignment(
                t, NodeUtil.getConditionExpression(n), state);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[26]++;

          } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[16]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[27]++;
          continue;
        case Token.SWITCH:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[17]++;
        case Token.CASE:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[18]++;
        case Token.RETURN:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[19]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[20]++;
            tryRemoveAssignment(t, n.getFirstChild(), state);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[29]++;

          } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[21]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[30]++;
          continue; default : CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[22]++;
        // TODO(user): case Token.VAR: Remove var a=1;a=2;.....
      }

      tryRemoveAssignment(t, n, state);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[31]++;
    }
  }

  private void tryRemoveAssignment(NodeTraversal t, Node n,
      FlowState<LiveVariableLattice> state) {
    tryRemoveAssignment(t, n, n, state);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[32]++;
  }

  /**
   * Determines if any local variables are dead after the instruction {@code n}
   * and are assigned within the subtree of {@code n}. Removes those assignments
   * if there are any.
   *
   * @param n Target instruction.
   * @param exprRoot The CFG node where the liveness information in state is
   *     still correct.
   * @param state The liveness information at {@code n}.
   */
  private void tryRemoveAssignment(NodeTraversal t, Node n, Node exprRoot,
      FlowState<LiveVariableLattice> state) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[33]++;

    Node parent = n.getParent();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;

    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((n.isInc()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n.isDec()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[23]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[35]++;

      Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[36]++;
      Node rhs = lhs.getNext();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;

      // Recurse first. Example: dead_x = dead_y = 1; We try to clean up dead_y
      // first.
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((rhs != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[25]++;
        tryRemoveAssignment(t, rhs, exprRoot, state);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[38]++;
        rhs = lhs.getNext();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[39]++;

      } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[26]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[40]++;

      Scope scope = t.getScope();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[27]++;
        return;
 // Not a local variable assignment.
      } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[28]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[42]++;
      String name = lhs.getString();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[43]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((scope.isDeclared(name, false)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[29]++;
        return;

      } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[30]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[44]++;
      Var var = scope.getVar(name);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;

      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((liveness.getEscapedLocals().contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[31]++;
        return;
 // Local variable that might be escaped due to closures.
      } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[32]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[46]++;
int CodeCoverConditionCoverageHelper_C13;

      // If we have an identity assignment such as a=a, always remove it
      // regardless of what the liveness results because it
      // does not change the result afterward.
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (128)) == 0 || true) &&
 ((rhs != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((rhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((rhs.getString().equals(var.name)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 4) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 4) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[33]++;
        n.removeChild(rhs);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[47]++;
        n.getParent().replaceChild(n, rhs);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[48]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[49]++;
        return;

      } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[34]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[50]++;
int CodeCoverConditionCoverageHelper_C14;

      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((state.getOut().isLive(var)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[35]++;
        return;
 // Variable not dead.
      } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[36]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[51]++;
int CodeCoverConditionCoverageHelper_C15;

      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((state.getIn().isLive(var)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isVariableStillLiveWithinExpression(n, exprRoot, var.name)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[37]++;
        // The variable is killed here but it is also live before it.
        // This is possible if we have say:
        //    if (X = a && a = C) {..} ; .......; a = S;
        // In this case we are safe to remove "a = C" because it is dead.
        // However if we have:
        //    if (a = C && X = a) {..} ; .......; a = S;
        // removing "a = C" is NOT correct, although the live set at the node
        // is exactly the same.
        // TODO(user): We need more fine grain CFA or we need to keep track
        // of GEN sets when we recurse here.
        return;

      } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[38]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[52]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[39]++;
        n.removeChild(rhs);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[53]++;
        n.getParent().replaceChild(n, rhs);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[54]++;

      } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[40]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[55]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(n)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[41]++;
        n.removeChild(rhs);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[56]++;
        n.removeChild(lhs);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[57]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[58]++;
        Node op = new Node(NodeUtil.getOpFromAssignmentOp(n), lhs, rhs);
        parent.replaceChild(n, op);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[59]++;

      } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[42]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[60]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((n.isInc()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((n.isDec()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[43]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[61]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[45]++;
          parent.replaceChild(n,
              IR.voidNode(IR.number(0).srcref(n)));
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[62]++;

        } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[46]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[63]++;
int CodeCoverConditionCoverageHelper_C20; if((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((n.isComma()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((n != parent.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[47]++;
          parent.removeChild(n);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[64]++;

        } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[48]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[65]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (32)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((NodeUtil.isForIn(parent)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((NodeUtil.getConditionExpression(parent) != n) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 3) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[49]++;
          parent.replaceChild(n, IR.empty());
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[66]++;

        } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[50]++;
          // Cannot replace x = a++ with x = a because that's not valid
          // when a is not a number.
          return;
        }
}
}

      } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[44]++;
        // Not reachable.
        Preconditions.checkState(false, "Unknown statement");
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[67]++;
      }
}
}

      compiler.reportCodeChange();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[68]++;
      return;


    } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[24]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[69]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[4]++;


int CodeCoverConditionCoverageHelper_C22;
      for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[4]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[5]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[6]++;
}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[70]++;
        Node next = c.getNext();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[71]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((ControlFlowGraph.isEnteringNewCfgNode(c)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[51]++;
          tryRemoveAssignment(t, c, exprRoot, state);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[72]++;

        } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[52]++;}
        c = next;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[73]++;
      }
      return;
    }
  }

  /**
   * Given a variable, node n in the tree and a sub-tree denoted by exprRoot as
   * the root, this function returns true if there exists a read of that
   * variable before a write to that variable that is on the right side of n.
   *
   * For example, suppose the node is x = 1:
   *
   * y = 1, x = 1; // false, there is no reads at all.
   * y = 1, x = 1, print(x) // true, there is a read right of n.
   * y = 1, x = 1, x = 2, print(x) // false, there is a read right of n but
   *                               // it is after a write.
   *
   * @param n The current node we should look at.
   * @param exprRoot The node
   */
  private boolean isVariableStillLiveWithinExpression(
      Node n, Node exprRoot, String variable) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[74]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[7]++;


int CodeCoverConditionCoverageHelper_C24;
    while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((n != exprRoot) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[7]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[8]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[9]++;
}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[75]++;
      VariableLiveness state = VariableLiveness.MAYBE_LIVE;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[76]++;
      switch (n.getParent().getType()) {
        case Token.OR:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[53]++;
        case Token.AND:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[54]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[77]++;
int CodeCoverConditionCoverageHelper_C25;
          // If the currently node is the first child of
          // AND/OR, be conservative only consider the READs
          // of the second operand.
          if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((n.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[55]++;
            state = isVariableReadBeforeKill(
                n.getNext(), variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[78]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[79]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((state == VariableLiveness.KILL) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[57]++;
              state = VariableLiveness.MAYBE_LIVE;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[80]++;

            } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[58]++;}

          } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[56]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[81]++;
          break;

        case Token.HOOK:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[59]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[82]++;
int CodeCoverConditionCoverageHelper_C27;
          // If current node is the condition, check each following
          // branch, otherwise it is a conditional branch and the
          // other branch can be ignored.
          if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((n.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((n.getNext().getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[60]++;
            state = checkHookBranchReadBeforeKill(
                n.getNext(), n.getNext().getNext(), variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[83]++;

          } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[61]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[84]++;
          break;

        default:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[62]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[85]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[10]++;


int CodeCoverConditionCoverageHelper_C28;
          for(Node sibling = n.getNext();(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((sibling != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false);
              sibling = sibling.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[10]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[11]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[12]++;
}
            state = isVariableReadBeforeKill(sibling, variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[86]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[87]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((state != VariableLiveness.MAYBE_LIVE) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[63]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[88]++;
              break;

            } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[64]++;}
          }
      }
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[89]++;
int CodeCoverConditionCoverageHelper_C30;

      // If we see a READ or KILL there is no need to continue.
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((state == VariableLiveness.READ) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[65]++;
        return true;

      } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[66]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[90]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((state == VariableLiveness.KILL) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[67]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[68]++;}
}
      n = n.getParent();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[91]++;
    }
    return false;
  }

  // The current liveness of the variable
  private enum VariableLiveness {
    MAYBE_LIVE, // May be still live in the current expression tree.
    READ, // Known there is a read left of it.
    KILL, // Known there is a write before any read.
  }

  /**
   * Give an expression and a variable. It returns READ, if the first
   * reference of that variable is a read. It returns KILL, if the first
   * reference of that variable is an assignment. It returns MAY_LIVE otherwise.
   */
  private VariableLiveness isVariableReadBeforeKill(
      Node n, String variable) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[92]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((ControlFlowGraph.isEnteringNewCfgNode(n)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[69]++; // Not a FUNCTION
      return VariableLiveness.MAYBE_LIVE;

    } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[70]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[93]++;
int CodeCoverConditionCoverageHelper_C33;

    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((variable.equals(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[71]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[94]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarOrSimpleAssignLhs(n, n.getParent())) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[73]++;
        Preconditions.checkState(n.getParent().isAssign());
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[95]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[96]++;
        // The expression to which the assignment is made is evaluated before
        // the RHS is evaluated (normal left to right evaluation) but the KILL
        // occurs after the RHS is evaluated.
        Node rhs = n.getNext();
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[97]++;
        VariableLiveness state = isVariableReadBeforeKill(rhs, variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[98]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((state == VariableLiveness.READ) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[75]++;
          return state;

        } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[76]++;}
        return VariableLiveness.KILL;

      } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[74]++;
        return VariableLiveness.READ;
      }

    } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[72]++;}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[99]++;

    switch (n.getType()) {
      // Conditionals
      case Token.OR:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[77]++;
      case Token.AND:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[78]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[100]++;
        VariableLiveness v1 = isVariableReadBeforeKill(
          n.getFirstChild(), variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[101]++;
        VariableLiveness v2 = isVariableReadBeforeKill(
          n.getLastChild(), variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[102]++;
int CodeCoverConditionCoverageHelper_C36;
        // With a AND/OR the first branch always runs, but the second is
        // may not.
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((v1 != VariableLiveness.MAYBE_LIVE) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[79]++;
          return v1;

        } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[80]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[103]++;
int CodeCoverConditionCoverageHelper_C37; if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((v2 == VariableLiveness.READ) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[81]++;
          return VariableLiveness.READ;

        } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[82]++;
          return VariableLiveness.MAYBE_LIVE;
        }
}
      case Token.HOOK:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[83]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[104]++;
        VariableLiveness first = isVariableReadBeforeKill(
            n.getFirstChild(), variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[105]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((first != VariableLiveness.MAYBE_LIVE) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[84]++;
          return first;

        } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[85]++;}
        return checkHookBranchReadBeforeKill(
            n.getFirstChild().getNext(), n.getLastChild(), variable);

      default:
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[86]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[106]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[13]++;


int CodeCoverConditionCoverageHelper_C39;
        // Expressions are evaluated left-right, depth first.
        for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[13]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[14]--;
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.loops[15]++;
}
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[107]++;
          VariableLiveness state = isVariableReadBeforeKill(child, variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[108]++;
int CodeCoverConditionCoverageHelper_C40;
          if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((state != VariableLiveness.MAYBE_LIVE) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[87]++;
            return state;

          } else {
  CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[88]++;}
        }
    }

    return VariableLiveness.MAYBE_LIVE;
  }

  private VariableLiveness checkHookBranchReadBeforeKill(
      Node trueCase, Node falseCase, String variable) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[109]++;
    VariableLiveness v1 = isVariableReadBeforeKill(
      trueCase, variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[110]++;
    VariableLiveness v2 = isVariableReadBeforeKill(
      falseCase, variable);
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[111]++;
int CodeCoverConditionCoverageHelper_C41;
    // With a hook it is unknown which branch will run, so
    // we must be conservative.  A read by either is a READ, and
    // a KILL is only considered if both KILL.
    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((v1 == VariableLiveness.READ) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((v2 == VariableLiveness.READ) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[89]++;
      return VariableLiveness.READ;

    } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[90]++;
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.statements[112]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((v1 == VariableLiveness.KILL) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((v2 == VariableLiveness.KILL) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[91]++;
      return VariableLiveness.KILL;

    } else {
CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01.branches[92]++;
      return VariableLiveness.MAYBE_LIVE;
    }
}
  }
}

class CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01 ());
  }
    public static long[] statements = new long[113];
    public static long[] branches = new long[93];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[43];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.DeadAssignmentsElimination.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,3,1,1,1,1,3,1,2,1,1,2,1,2,3,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,2};
    for (int i = 1; i <= 42; i++) {
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

  public CodeCoverCoverageCounter$8moim6lmxq88zkg6xv1e0534ibeiawh9m0tkn5bvr3s32e01 () {
    super("com.google.javascript.jscomp.DeadAssignmentsElimination.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 112; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 92; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 42; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.DeadAssignmentsElimination.java");
      for (int i = 1; i <= 112; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 92; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 42; i++) {
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

