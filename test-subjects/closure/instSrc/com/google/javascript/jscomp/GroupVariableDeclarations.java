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

import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.Iterator;
import java.util.Set;

/**
 * Groups multiple variable declarations (that may or may not be contiguous)
 * in the same scope into a single one. i.e.
 *
 * <pre>
 * var a, b = 10;
 * f1();
 * var c, d;
 * ... some other code ...
 * var x, y, z = 100;
 * ... some other code ...
 * var p = 200, q = 300;
 * </pre>
 *
 * becomes:
 *
 * <pre>
 * var a, b = 10, c, d, x, y, z;
 * f1();
 * ... some other code ...
 * z = 100;
 * ... some other code ...
 * var p = 200, q = 300;
 * </pre>
 *
 * This reduces the generated uncompressed code size.
 *
 * For any scope, we use the first VAR statement as the statement to collapse
 * the other declarations into. For other VAR statements in the scope, we only
 * consider ones that either (a) have no variable that is initialized in the
 * the statement, or (b) have exactly one variable that is initialized (e.g.
 * the "var x, y, z = 100;" statement in the example above. VAR statements
 * with more than one variable initialization are not collapsed. This is
 * because doing so would increase uncompressed code size.
 *
 */
class GroupVariableDeclarations implements CompilerPass, ScopedCallback {
  static {
    CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.ping();
  }

  private final AbstractCompiler compiler;

  GroupVariableDeclarations(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[1]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[2]++;
  }

  @Override
  public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[3]++;
    Set<Node> varNodes = Sets.newLinkedHashSet();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[4]++;
    Iterator<Var> scopeVarIter = t.getScope().getVars();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
    while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scopeVarIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[1]--;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[2]--;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[3]++;
}
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[6]++;
      Node parentNode = scopeVarIter.next().getParentNode();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parentNode.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[1]++;
        varNodes.add(parentNode);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[8]++;

      } else {
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[2]++;}
    }
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((varNodes.size() <= 1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[4]++;}
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[10]++;
    Iterator<Node> varNodeIter = varNodes.iterator();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[11]++;
    Node firstVarNode = varNodeIter.next();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[12]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
    while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((varNodeIter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[4]--;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[5]--;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[6]++;
}
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[13]++;
      Node varNode = varNodeIter.next();
      applyGroupingToVar(firstVarNode, varNode);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[14]++;
    }
  }

  @Override
  public void exitScope(NodeTraversal t) {
  }

  @Override
  public boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
                                Node parent) {
    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
  }

  /**
   * Attempts to collapse groupVar. This can only happen if groupVar has at most
   * one variable initialization (it may have multiple variable declarations).
   * If successful, then detaches groupVar's children and appends them to
   * firstVar
   *
   * @param firstVar The first VAR {@code Node} in that scope. This is the node
   *                 that we want to collapse groupVar into
   * @param groupVar The VAR {@code Node} that we want to try collapsing
   *                 into the first VAR node of that scope
   */
  private void applyGroupingToVar(Node firstVar, Node groupVar) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[15]++;
    Node child = groupVar.getFirstChild();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[16]++;
    // if some variable is initialized, then the corresponding NAME node will be
    // stored here
    Node initializedName = null;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[17]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[7]++;


int CodeCoverConditionCoverageHelper_C5;
    while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[7]--;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[8]--;
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.loops[9]++;
}
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((child.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[5]++;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
        // check that no more than one var is initialized
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((initializedName != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[7]++;
          return;

        } else {
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[8]++;}
        initializedName = child;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[20]++;

      } else {
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[6]++;}
      child = child.getNext();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[21]++;
    }
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[22]++;

    // we will be modifying the groupVar subtree so get its parent
    Node groupVarParent = groupVar.getParent();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;


    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((initializedName != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[9]++;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[24]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(groupVarParent)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[11]++;
        // The target of the for-in expression must be an assignable expression.
        return;

      } else {
  CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[12]++;}
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[25]++;

      // we have an initialized var in the VAR node. We will replace the
      // VAR node with an assignment.

      // first create a detached childless clone of initializedName.
      Node clone = initializedName.cloneNode();
      // replace
      groupVar.replaceChild(initializedName, clone);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[26]++;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[27]++;
      // add the assignment now.
      Node initializedVal = initializedName.removeFirstChild();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[28]++;
      Node assignmentNode = IR.assign(initializedName, initializedVal);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((groupVarParent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[13]++;
        // Handle For and For-In Loops specially. For these, we do not need
        // to construct an EXPR_RESULT node.
        groupVarParent.replaceChild(groupVar, assignmentNode);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[30]++;

      } else {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[14]++;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[31]++;
        Node exprNode = NodeUtil.newExpr(assignmentNode);
        groupVarParent.replaceChild(groupVar, exprNode);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[32]++;
      }

    } else {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[10]++;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[33]++;
int CodeCoverConditionCoverageHelper_C11;
      // There is no initialized var. But we need to handle FOR and
      // FOR-IN loops specially
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((groupVarParent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[15]++;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[34]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(groupVarParent)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[17]++;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[35]++;
          // In For-In loop, we replace the VAR node with a NAME node
          Node nameNodeClone = groupVar.getFirstChild().cloneNode();
          groupVarParent.replaceChild(groupVar, nameNodeClone);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[36]++;

        } else {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[18]++;
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[37]++;
          // In For loop, we replace the VAR node with an EMPTY node
          Node emptyNode = IR.empty();
          groupVarParent.replaceChild(groupVar, emptyNode);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[38]++;
        }

      } else {
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.branches[16]++;
        // we can safely remove the VAR node
        groupVarParent.removeChild(groupVar);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[39]++;
      }
    }
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[40]++;

    Node children = groupVar.removeChildren();
    firstVar.addChildrenToBack(children);
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[41]++;

    compiler.reportCodeChange();
CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl.statements[42]++;
  }
}

class CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.GroupVariableDeclarations.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$19mzdtbs9b2b6lz22h774nk514kzhqr4u1rq7k0r8rj2rfl () {
    super("com.google.javascript.jscomp.GroupVariableDeclarations.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.GroupVariableDeclarations.java");
      for (int i = 1; i <= 42; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

