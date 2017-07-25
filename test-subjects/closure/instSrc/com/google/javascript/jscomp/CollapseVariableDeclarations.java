/*
 * Copyright 2006 The Closure Compiler Authors.
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
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;
import java.util.Set;

/**
 * Collapses multiple variable declarations into a single one. i.e the
 * following:
 *
 * <pre>
 * var a;
 * var b = 1;
 * var c = 2;
 * </pre>
 *
 * becomes:
 *
 * <pre>var a, b = 1, c = 2;</pre>
 *
 * This reduces the generated code size. More optimizations are possible:
 * <li>Group all variable declarations inside a function into one such variable.
 * declaration block.</li>
 * <li>Re-use variables instead of declaring a new one if they are used for
 * only part of a function.</li>
 *
 * Similarly, also collapses assigns like:
 *
 * <pre>
 * a = true;
 * b = true;
 * var c = true;
 * </pre>
 *
 * becomes:
 *
 * <pre>var c = b = a = true;</pre>
 *
 */
class CollapseVariableDeclarations implements CompilerPass {
  static {
    CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.ping();
  }

  /** Reference to JS Compiler */
  private final AbstractCompiler compiler;

  /** Encapsulation of information about a variable declaration collapse */
  private static class Collapse {
    /**
     * Variable declaration that any following var nodes should be
     * collapsed into
     */
    final Node startNode;

    /**
     * Last node (non-inclusive) of the chain of nodes to collapse.
     */
    final Node endNode;

    /** Parent of the nodes to the collapse */
    final Node parent;

    Collapse(Node startNode, Node endNode, Node parent) {
      this.startNode = startNode;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[1]++;
      this.endNode = endNode;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[2]++;
      this.parent = parent;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[3]++;
    }
  }

  /**
   * Collapses to do in this pass.
   */
  private final List<Collapse> collapses = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[4]++;
  }

  /**
   * Nodes we've already looked at for collapsing, so that we don't look at them
   * again (we look ahead when examining what nodes can be collapsed, and the
   * node traversal may give them to us again)
   */
  private final Set<Node> nodesToCollapse = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[5]++;
  }

  CollapseVariableDeclarations(AbstractCompiler compiler) {
    Preconditions.checkState(!compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[6]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[7]++;
  }

  @Override
  public void process(Node externs, Node root) {
    collapses.clear();
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[8]++;
    nodesToCollapse.clear();
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[9]++;

    NodeTraversal.traverse(compiler, root, new GatherCollapses());
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[10]++;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((collapses.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[1]++;
      applyCollapses();
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[12]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[13]++;

    } else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[2]++;}
  }

  /**
   * Gathers all of the variable declarations / assignments that should be
   * collapsed into one.
   *
   * We do not do the collapsing as we go since node traversal would be affected
   * by the changes we are making to the parse tree.
   */
  private class GatherCollapses extends AbstractPostOrderCallback {

    // If a VAR is declared like
    // var x;
    // then we should not create new VAR nodes for it later in the tree.
    // This is a workaround for a bug in Firefox.
    private final Set<Var> blacklistedVars = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[14]++;
  }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[3]++;
        blacklistStubVars(t, n);
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[16]++;

      } else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[4]++;}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;

      // Only care about var nodes
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((canBeRedeclared(n, t.getScope())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[5]++; return;
} else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[6]++;}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;

      // If we've already looked at this node, skip it
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((nodesToCollapse.contains(n)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[7]++; return;
} else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[8]++;}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;

      // Adjacent VAR children of an IF node are the if and else parts and can't
      // be collapsed
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((parent.isIf()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[9]++; return;
} else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[10]++;}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[20]++;

      Node varNode = n;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[21]++;

      boolean hasVar = n.isVar();

      // Find variable declarations that follow this one (if any)
      n = n.getNext();
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[22]++;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[23]++;

      boolean hasNodesToCollapse = false;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[24]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;

      while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((canBeRedeclared(n, t.getScope())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[1]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[2]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[3]++;
}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[11]++;
          blacklistStubVars(t, n);
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[26]++;
          hasVar = true;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[27]++;

        } else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[12]++;}

        nodesToCollapse.add(n);
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[28]++;
        hasNodesToCollapse = true;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[29]++;

        n = n.getNext();
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[30]++;
      }
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;

      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((hasNodesToCollapse) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((hasVar) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[13]++;
        nodesToCollapse.add(varNode);
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[32]++;
        collapses.add(new Collapse(varNode, n, parent));
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[33]++;

      } else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[14]++;}
    }

    private void blacklistStubVars(NodeTraversal t, Node varNode) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;
      for (Node child = varNode.getFirstChild();(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[4]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[5]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[6]++;
}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((child.getFirstChild() == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[15]++;
          blacklistedVars.add(t.getScope().getVar(child.getString()));
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[36]++;

        } else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[16]++;}
      }
    }

    private boolean canBeRedeclared(Node n, Scope s) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(n)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[17]++;
        return false;

      } else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[18]++;}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[38]++;
      Node assign = n.getFirstChild();
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[39]++;
      Node lhs = assign.getFirstChild();
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[40]++;
int CodeCoverConditionCoverageHelper_C12;

      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[19]++;
        return false;

      } else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[20]++;}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[41]++;

      Var var = s.getVar(lhs.getString());
      return var != null
          && var.getScope() == s
          && !isNamedParameter(var)
          && !blacklistedVars.contains(var);
    }
  }

  private boolean isNamedParameter(Var v) {
    return v.getParentNode().isParamList();
  }

  private void applyCollapses() {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[42]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[7]++;


    for (Collapse collapse : collapses) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[7]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[8]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[9]++;
}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[43]++;

      Node var = new Node(Token.VAR);
      var.copyInformationFrom(collapse.startNode);
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[44]++;
      collapse.parent.addChildBefore(var, collapse.startNode);
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[45]++;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[46]++;

      boolean redeclaration = false;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[47]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[10]++;


int CodeCoverConditionCoverageHelper_C13;
      for (Node n = collapse.startNode;(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n != collapse.endNode) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false);) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[10]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[11]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[12]++;
}
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[48]++;
        Node next = n.getNext();

        Preconditions.checkState(var.getNext() == n);
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[49]++;
        collapse.parent.removeChildAfter(var);
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[50]++;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;

        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[21]++;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[52]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[13]++;


int CodeCoverConditionCoverageHelper_C15;
          while((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[13]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[14]--;
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.loops[15]++;
}
            var.addChildToBack(n.removeFirstChild());
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[53]++;
          }

        } else {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[22]++;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[54]++;
          Node assign = n.getFirstChild();
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[55]++;
          Node lhs = assign.getFirstChild();
          Preconditions.checkState(lhs.isName());
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[56]++;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[57]++;
          Node rhs = assign.getLastChild();
          lhs.addChildToBack(rhs.detachFromParent());
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[58]++;
          var.addChildToBack(lhs.detachFromParent());
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[59]++;
          redeclaration = true;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[60]++;
        }
        n = next;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[61]++;
      }
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[62]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((redeclaration) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[23]++;
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[63]++;
        JSDocInfo info = new JSDocInfo();
        info.addSuppression("duplicate");
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[64]++;
        var.setJSDocInfo(info);
CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.statements[65]++;

      } else {
  CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx.branches[24]++;}
    }
  }
}

class CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx ());
  }
    public static long[] statements = new long[66];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CollapseVariableDeclarations.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,3,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 16; i++) {
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

  public CodeCoverCoverageCounter$by9ssxl0mbkt6ax7pffh2mv4zqicqzsk9eehk8csti86ylhc7kx () {
    super("com.google.javascript.jscomp.CollapseVariableDeclarations.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 65; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CollapseVariableDeclarations.java");
      for (int i = 1; i <= 65; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 16; i++) {
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

