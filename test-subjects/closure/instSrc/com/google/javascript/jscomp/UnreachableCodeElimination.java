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
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.AbstractShallowCallback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;
import com.google.javascript.jscomp.graph.GraphReachability;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Removes dead code from a parse tree. The kinds of dead code that this pass
 * removes are:
 *  - Any code following a return statement, such as the <code>alert</code>
 *    call in: <code>if (x) { return; alert('unreachable'); }</code>.
 *  - Statements that have no side effects, such as:
 *    <code>a.b.MyClass.prototype.propertyName;</code> or <code>true;</code>.
 *    That first kind of statement sometimes appears intentionally, so that
 *    prototype properties can be annotated using JSDoc without actually
 *    being initialized.
 *
 */

// TODO(user): Besides dead code after returns, this pass removes useless live
// code such as breaks/continues/returns and stms w/out side effects.
// These things don't require reachability info, consider making them their own
// pass or putting them in some other, more related pass.

class UnreachableCodeElimination extends AbstractPostOrderCallback
    implements CompilerPass, ScopedCallback  {
  static {
    CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.ping();
  }

  private static final Logger logger =
    Logger.getLogger(UnreachableCodeElimination.class.getName());
  static {
    CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final boolean removeNoOpStatements;
  private boolean codeChanged;

  UnreachableCodeElimination(AbstractCompiler compiler,
      boolean removeNoOpStatements) {
    this.compiler = compiler;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[2]++;
    this.removeNoOpStatements = removeNoOpStatements;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[3]++;
  }

  @Override
  public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[4]++;
    Scope scope = t.getScope();
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[5]++;
    Node root = scope.getRootNode();
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[6]++;

    // Computes the control flow graph.
    ControlFlowAnalysis cfa = new ControlFlowAnalysis(compiler, false, false);
    cfa.process(null, root);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[7]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[8]++;
    ControlFlowGraph<Node> cfg = cfa.getCfg();

    new GraphReachability<Node, ControlFlowGraph.Branch>(cfg)
        .compute(cfg.getEntry().getValue());
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[9]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[1]++;
      root = root.getLastChild();
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[11]++;

    } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[2]++;}
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;

    do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[1]--;
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[2]--;
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[3]++;
}
      codeChanged = false;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[13]++;
      NodeTraversal.traverse(compiler, root, new EliminationPass(cfg));
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[14]++;
    } while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((codeChanged) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false));
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[15]++;
  }

  private class EliminationPass extends AbstractShallowCallback {
    private final ControlFlowGraph<Node> cfg;
    private EliminationPass(ControlFlowGraph<Node> cfg) {
      this.cfg = cfg;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[16]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[3]++;
        return;

      } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[4]++;}
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[18]++;
      DiGraphNode<Node, Branch> gNode = cfg.getDirectedGraphNode(n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((gNode == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[5]++; // Not in CFG.
        return;

      } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[6]++;}
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((gNode.getAnnotation() != GraphReachability.REACHABLE) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((removeNoOpStatements) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(n, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[7]++;
        removeDeadExprStatementSafely(n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[21]++;
        return;

      } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[8]++;}
      tryRemoveUnconditionalBranching(n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[22]++;
    }

    /**
     * Tries to remove n if it is an unconditional branch node (break, continue,
     * or return) and the target of n is the same as the the follow of n.
     * That is, if removing n preserves the control flow. Also if n targets
     * another unconditional branch, this function will recursively try to
     * remove the target branch as well. The reason why we want to cascade this
     * removal is because we only run this pass once. If we have code such as
     *
     * break -> break -> break
     *
     * where all 3 breaks are useless, then the order of removal matters. When
     * we first look at the first break, we see that it branches to the 2nd
     * break. However, if we remove the last break, the 2nd break becomes
     * useless and finally the first break becomes useless as well.
     *
     * @returns The target of this jump. If the target is also useless jump,
     *     the target of that useless jump recursively.
     */
    @SuppressWarnings("fallthrough")
    private void tryRemoveUnconditionalBranching(Node n) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
      /*
       * For each unconditional branching control flow node, check to see
       * if the ControlFlowAnalysis.computeFollowNode of that node is same as
       * the branching target. If it is, the branch node is safe to be removed.
       *
       * This is not as clever as MinimizeExitPoints because it doesn't do any
       * if-else conversion but it handles more complicated switch statements
       * much more nicely.
       */

      // If n is null the target is the end of the function, nothing to do.
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[9]++;
         return;

      } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[10]++;}
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[24]++;

      DiGraphNode<Node, Branch> gNode = cfg.getDirectedGraphNode(n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((gNode == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[11]++;
        return;

      } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[12]++;}
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[26]++;

      switch (n.getType()) {
        case Token.RETURN:
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[13]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[14]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[28]++;
            break;

          } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[15]++;}
        case Token.BREAK:
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[16]++;
        case Token.CONTINUE:
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[17]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[29]++;
          // We are looking for a control flow changing statement that always
          // branches to the same node. If after removing it control still
          // branches to the same node, it is safe to remove.
          List<DiGraphEdge<Node, Branch>> outEdges = gNode.getOutEdges();
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[30]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((outEdges.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((n.getNext() == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n.getNext().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[18]++;

            Preconditions.checkState(
                outEdges.get(0).getValue() == Branch.UNCOND);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[31]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[32]++;
            Node fallThrough = computeFollowing(n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[33]++;
            Node nextCfgNode = outEdges.get(0).getDestination().getValue();
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[34]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((nextCfgNode == fallThrough) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[20]++;
              removeNode(n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[35]++;

            } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[21]++;}

          } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[19]++;} default : CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[22]++;
      }
    }

    private Node computeFollowing(Node n) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[36]++;
      Node next = ControlFlowAnalysis.computeFollowNode(n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[37]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[4]++;


int CodeCoverConditionCoverageHelper_C11;
      while ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((next.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[4]--;
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[5]--;
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.loops[6]++;
}
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((next.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[23]++;
          next = next.getFirstChild();
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[39]++;

        } else {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[24]++;
          next = computeFollowing(next);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[40]++;
        }
      }
      return next;
    }

    private void removeDeadExprStatementSafely(Node n) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[41]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[42]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((n.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[25]++;
        // Not always trivial to remove, let FoldConstants work its magic later.
        return;

      } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[26]++;}
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[43]++;
int CodeCoverConditionCoverageHelper_C14;

      // TODO(user): This is a problem with removeNoOpStatements.
      // Every expression in a FOR-IN header looks side effect free on its own.
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(parent)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[27]++;
        return;

      } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[28]++;}
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[44]++;

      switch (n.getType()) {
        // Removing an unreachable DO node is messy b/c it means we still have
        // to execute one iteration. If the DO's body has breaks in the middle,
        // it can get even more tricky and code size might actually increase.
        case Token.DO:
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[29]++;
          return;

        case Token.BLOCK:
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[30]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[45]++;
int CodeCoverConditionCoverageHelper_C15;
          // BLOCKs are used in several ways including wrapping CATCH
          // blocks in TRYs
          if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((parent.isTry()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((NodeUtil.isTryCatchNodeContainer(n)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[31]++;
            return;

          } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[32]++;}
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[46]++;
          break;

        case Token.CATCH:
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[33]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[47]++;
          Node tryNode = parent.getParent();
          NodeUtil.maybeAddFinally(tryNode);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[48]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[49]++;
          break; default : CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[34]++;
      }
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[50]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((n.getFirstChild().hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[35]++;
        // Very unlikely case, Consider this:
        // File 1: {throw 1}
        // File 2: {var x}
        // The node var x is unreachable in the global scope.
        // Before we remove the node, redeclareVarsInsideBranch
        // would basically move var x to the beginning of File 2,
        // which resulted in zero changes to the AST but triggered
        // reportCodeChange().
        // Instead, we should just ignore dead variable declarations.
        return;

      } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[36]++;}

      removeNode(n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[51]++;
    }

    private void removeNode(Node n) {
      codeChanged = true;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[52]++;
      NodeUtil.redeclareVarsInsideBranch(n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[53]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[54]++;
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((logger.isLoggable(Level.FINE)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[37]++;
        logger.fine("Removing " + n.toString());
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[56]++;

      } else {
  CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.branches[38]++;}
      NodeUtil.removeChild(n.getParent(), n);
CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx.statements[57]++;
    }
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {}

  @Override
  public void enterScope(NodeTraversal t) {}
}

class CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.UnreachableCodeElimination.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,1,3,1,1,1,3,1,2,1,3,1,2,2,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$as28xfb6g877zfeq9mz76kdz29o9y3d6woeqztpi00ro74gx () {
    super("com.google.javascript.jscomp.UnreachableCodeElimination.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.UnreachableCodeElimination.java");
      for (int i = 1; i <= 57; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

