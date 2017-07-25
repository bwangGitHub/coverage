/*
 * Copyright 2004 The Closure Compiler Authors.
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
import com.google.common.base.Predicates;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.TernaryValue;

import javax.annotation.Nullable;

/**
 * Peephole optimization to remove useless code such as IF's with false
 * guard conditions, comma operator left hand sides with no side effects, etc.
 *
 */
class PeepholeRemoveDeadCode extends AbstractPeepholeOptimization {
  static {
    CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.ping();
  }


  // TODO(dcc): Some (all) of these can probably be better achieved
  // using the control flow graph (like CheckUnreachableCode).
  // There is an existing CFG pass (UnreachableCodeElimination) that
  // could be changed to use code from CheckUnreachableCode to do this.

  @Override
  Node optimizeSubtree(Node subtree) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[1]++;
    switch(subtree.getType()) {
      case Token.ASSIGN:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[1]++;
        return tryFoldAssignment(subtree);
      case Token.COMMA:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[2]++;
        return tryFoldComma(subtree);
      case Token.SCRIPT:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[3]++;
      case Token.BLOCK:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[4]++;
        return tryOptimizeBlock(subtree);
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[5]++;
        subtree = tryFoldExpr(subtree);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[2]++;
        return subtree;
      case Token.HOOK:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[6]++;
        return tryFoldHook(subtree);
      case Token.SWITCH:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[7]++;
        return tryOptimizeSwitch(subtree);
      case Token.IF:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[8]++;
        return tryFoldIf(subtree);
      case Token.WHILE:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[9]++;
        return tryFoldWhile(subtree);
       case Token.FOR:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[10]++; {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[3]++;
          Node condition = NodeUtil.getConditionExpression(subtree);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
          if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((condition != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[11]++;
            tryFoldForCondition(condition);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[5]++;

          } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[12]++;}
        }
        return tryFoldFor(subtree);
      case Token.DO:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[13]++;
        return tryFoldDo(subtree);
      case Token.TRY:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[14]++;
        return tryFoldTry(subtree);
      default:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[15]++;
          return subtree;
    }
  }

  /**
   * Remove try blocks without catch blocks and with empty or not
   * existent finally blocks.
   * Or, only leave the finally blocks if try body blocks are empty
   * @return the replacement node, if changed, or the original if not
   */
  private Node tryFoldTry(Node n) {
    Preconditions.checkState(n.isTry());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[6]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[7]++;
    Node body = n.getFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[8]++;
    Node catchBlock = body.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[9]++;
    Node finallyBlock = catchBlock.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;

    // Removes TRYs that had its CATCH removed and/or empty FINALLY.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((catchBlock.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((finallyBlock == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((finallyBlock.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[16]++;
      n.removeChild(body);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[11]++;
      n.getParent().replaceChild(n, body);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[12]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[13]++;
      return body;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[17]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;

    // Only leave FINALLYs if TRYs are empty
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((body.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[18]++;
      NodeUtil.redeclareVarsInsideBranch(catchBlock);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[15]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((finallyBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[20]++;
        n.removeChild(finallyBlock);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[17]++;
        n.getParent().replaceChild(n, finallyBlock);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[18]++;

      } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[21]++;
        n.getParent().removeChild(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[19]++;
      }
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[20]++;
      return finallyBlock;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[19]++;}

    return n;
  }

  /**
   * Try removing identity assignments
   * @return the replacement node, if changed, or the original if not
   */
  private Node tryFoldAssignment(Node subtree) {
    Preconditions.checkState(subtree.isAssign());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[21]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[22]++;
    Node left = subtree.getFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[23]++;
    Node right = subtree.getLastChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
    // Only names
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((left.isName()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((right.isName()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((left.getString().equals(right.getString())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[22]++;
      subtree.getParent().replaceChild(subtree, right.detachFromParent());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[25]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[26]++;
      return right;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[23]++;}
    return subtree;
  }

  /**
   * Try folding EXPR_RESULT nodes by removing useless Ops and expressions.
   * @return the replacement node, if changed, or the original if not
   */
  private Node tryFoldExpr(Node subtree) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[27]++;
    Node result = trySimplifyUnusedResult(subtree.getFirstChild());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[24]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[29]++;
      Node parent = subtree.getParent();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
      // If the EXPR_RESULT no longer has any children, remove it as well.
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((parent.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[26]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[31]++;
        Node replacement = IR.block().srcref(subtree);
        parent.replaceChild(subtree, replacement);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[32]++;
        subtree = replacement;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[33]++;

      } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[27]++;
        subtree.detachFromParent();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[34]++;
        subtree = null;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[35]++;
      }

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[25]++;}
    return subtree;
  }

  /**
   * General cascading unused operation node removal.
   * @param n The root of the expression to simplify.
   * @return The replacement node, or null if the node was is not useful.
   */
  private Node trySimplifyUnusedResult(Node n) {
    return trySimplifyUnusedResult(n, true);
  }

  /**
   * General cascading unused operation node removal.
   * @param n The root of the expression to simplify.
   * @param removeUnused If true, the node is removed from the AST if
   *     it is not useful, otherwise it replaced with an EMPTY node.
   * @return The replacement node, or null if the node was is not useful.
   */
  private Node trySimplifyUnusedResult(Node n, boolean removeUnused) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[36]++;
    Node result = n;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[37]++;

    // Simplify the results of conditional expressions
    switch (n.getType()) {
      case Token.HOOK:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[28]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[38]++;
        Node trueNode = trySimplifyUnusedResult(n.getFirstChild().getNext());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[39]++;
        Node falseNode = trySimplifyUnusedResult(n.getLastChild());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
        // If one or more of the conditional children were removed,
        // transform the HOOK to an equivalent operation:
        //    x() ? foo() : 1 --> x() && foo()
        //    x() ? 1 : foo() --> x() || foo()
        //    x() ? 1 : 1 --> x()
        //    x ? 1 : 1 --> null
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((trueNode == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((falseNode != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[29]++;
          n.setType(Token.OR);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[41]++;
          Preconditions.checkState(n.getChildCount() == 2);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[42]++;

        } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[30]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[43]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((trueNode != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((falseNode == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[31]++;
          n.setType(Token.AND);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[44]++;
          Preconditions.checkState(n.getChildCount() == 2);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[45]++;

        } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[32]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[46]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((trueNode == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((falseNode == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[33]++;
          result = trySimplifyUnusedResult(n.getFirstChild());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[47]++;

        } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[34]++;
          // The structure didn't change.
          result = n;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[48]++;
        }
}
}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[49]++;
        break;
      case Token.AND:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[35]++;
      case Token.OR:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[36]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[50]++;
        // Try to remove the second operand from a AND or OR operations:
        //    x() || f --> x()
        //    x() && f --> x()
        Node conditionalResultNode = trySimplifyUnusedResult(
            n.getLastChild());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[51]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((conditionalResultNode == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[37]++;
          Preconditions.checkState(n.hasOneChild());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[52]++;
          // The conditionally executed code was removed, so
          // replace the AND/OR with its LHS or remove it if it isn't useful.
          result = trySimplifyUnusedResult(n.getFirstChild());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[53]++;

        } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[38]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[54]++;
        break;
      case Token.FUNCTION:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[39]++;
        // A function expression isn't useful if it isn't used, remove it and
        // don't bother to look at its children.
        result = null;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[55]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[56]++;
        break;
      case Token.COMMA:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[40]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[57]++;
        // We rewrite other operations as COMMA expressions (which will later
        // get split into individual EXPR_RESULT statement, if possible), so
        // we special case COMMA (we don't want to rewrite COMMAs as new COMMAs
        // nodes.
        Node left = trySimplifyUnusedResult(n.getFirstChild());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[58]++;
        Node right = trySimplifyUnusedResult(n.getLastChild());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[59]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((left == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((right == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[41]++;
          result = null;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[60]++;

        } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[42]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[61]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((left == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[43]++;
          result = right;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[62]++;

        } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[44]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[63]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((right == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)){
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[45]++;
          result = left;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[64]++;

        } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[46]++;
          // The structure didn't change.
          result = n;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[65]++;
        }
}
}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[66]++;
        break;
      default:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[47]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[67]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((nodeTypeMayHaveSideEffects(n)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[48]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[68]++;
          // This is the meat of this function. The node itself doesn't generate
          // any side-effects but preserve any side-effects in the children.
          Node resultList = null;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[69]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[1]++;


int CodeCoverConditionCoverageHelper_C16;
          for (Node next, c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); c = next) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[1]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[2]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[3]++;
}
            next = c.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[70]++;
            c = trySimplifyUnusedResult(c);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[71]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[72]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[50]++;
              c.detachFromParent();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[73]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[74]++;
int CodeCoverConditionCoverageHelper_C18;
              if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((resultList == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false))  {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[52]++;
                // The first side-effect can be used stand-alone.
                resultList = c;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[75]++;

              } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[53]++;
                // Leave the side-effects in-place, simplifying it to a COMMA
                // expression.
                resultList = IR.comma(resultList, c).srcref(c);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[76]++;
              }

            } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[51]++;}
          }
          result = resultList;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[77]++;

        } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[49]++;}
    }
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[78]++;
int CodeCoverConditionCoverageHelper_C19;

    // Fix up the AST, replace or remove the an unused node (if requested).
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((n != result) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[54]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[79]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[80]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[56]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[81]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((removeUnused) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[58]++;
          parent.removeChild(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[82]++;

        } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[59]++;
          result = IR.empty().srcref(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[83]++;
          parent.replaceChild(n, result);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[84]++;
        }

      } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[57]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[85]++;
int CodeCoverConditionCoverageHelper_C22;
        // A new COMMA expression may not have an existing parent.
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((result.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[60]++;
          result.detachFromParent();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[86]++;

        } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[61]++;}
        n.getParent().replaceChild(n, result);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[87]++;
      }
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[88]++;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[55]++;}

    return result;
  }

  /**
   * Remove useless switches and cases.
   */
  private Node tryOptimizeSwitch(Node n) {
    Preconditions.checkState(n.isSwitch());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[89]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[90]++;

    Node defaultCase = tryOptimizeDefaultCase(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[91]++;
int CodeCoverConditionCoverageHelper_C23;

    // Removing cases when there exists a default case is not safe.
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((defaultCase == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[62]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[92]++;
      Node cond = n.getFirstChild(), prev = null, next = null, cur;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[93]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[4]++;


int CodeCoverConditionCoverageHelper_C24;

      for (cur = cond.getNext();(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); cur = next) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[4]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[5]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[6]++;
}
        next = cur.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[94]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[95]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((mayHaveSideEffects(cur.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isUselessCase(cur, prev)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[64]++;
          removeCase(n, cur);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[96]++;

        } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[65]++;
          prev = cur;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[97]++;
        }
      }
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[98]++;
int CodeCoverConditionCoverageHelper_C26;

      // Optimize switches with constant condition
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(cond, false)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[66]++;
        Node caseLabel;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[99]++;
        TernaryValue caseMatches = TernaryValue.TRUE;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[100]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[7]++;


int CodeCoverConditionCoverageHelper_C27;
        // Remove cases until you find one that may match
        for (cur = cond.getNext();(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); cur = next) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[7]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[8]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[9]++;
}
          next = cur.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[101]++;
          caseLabel = cur.getFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[102]++;
          caseMatches = PeepholeFoldConstants.evaluateComparison(
              Token.SHEQ, cond, caseLabel);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[103]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[104]++;
int CodeCoverConditionCoverageHelper_C28;
          if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((caseMatches == TernaryValue.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[68]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[105]++;
            break;

          } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[69]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[106]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((caseMatches == TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[70]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[107]++;
            break;

          } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[71]++;
            removeCase(n, cur);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[108]++;
          }
}
        }
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[109]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((caseMatches != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[72]++;
          Node block, lastStm;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[110]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[10]++;


int CodeCoverConditionCoverageHelper_C31;
          // Skip cases until you find one whose last stm is a break
          while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[10]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[11]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[12]++;
}
            block = cur.getLastChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[111]++;
            lastStm = block.getLastChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[112]++;
            cur = cur.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[113]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[114]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((lastStm != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((lastStm.isBreak()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[74]++;
              block.removeChild(lastStm);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[115]++;
              reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[116]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[117]++;
              break;

            } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[75]++;}
          }
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[118]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[13]++;


int CodeCoverConditionCoverageHelper_C33;
          // Remove any remaining cases
          for (;(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false); cur = next) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[13]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[14]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[15]++;
}
            next = cur.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[119]++;
            removeCase(n, cur);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[120]++;
          }
          // If there is one case left, we may be able to fold it
          cur = cond.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[121]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[122]++;
int CodeCoverConditionCoverageHelper_C34;
          if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((cur.getNext() == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[76]++;
            block = cur.getLastChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[123]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[124]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((NodeUtil.containsType(block, Token.BREAK,
                NodeUtil.MATCH_NOT_FUNCTION)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[78]++;
              cur.removeChild(block);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[125]++;
              n.getParent().replaceChild(n, block);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[126]++;
              reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[127]++;
              return block;

            } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[79]++;}

          } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[77]++;}

        } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[73]++;}

      } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[67]++;}

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[63]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[128]++;
int CodeCoverConditionCoverageHelper_C36;

    // Remove the switch if there are no remaining cases.
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((n.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[80]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[129]++;
      Node condition = n.removeFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[130]++;
      Node replacement = IR.exprResult(condition).srcref(n);
      n.getParent().replaceChild(n, replacement);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[131]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[132]++;
      return replacement;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[81]++;}

    return null;
  }

  /**
   * @return the default case node or null if there is no default case or
   *     if the default case is removed.
   */
  private Node tryOptimizeDefaultCase(Node n) {
    Preconditions.checkState(n.isSwitch());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[133]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[134]++;

    Node lastNonRemovable = n.getFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[135]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[16]++;


int CodeCoverConditionCoverageHelper_C37;  // The switch condition

    // The first child is the switch conditions skip it when looking for cases.
    for (Node c = n.getFirstChild().getNext();(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[16]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[17]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[18]++;
}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[136]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((c.isDefaultCase()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[82]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[137]++;
        // Remove cases that fall-through to the default case
        Node caseToRemove = lastNonRemovable.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[138]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[19]++;


int CodeCoverConditionCoverageHelper_C39;
        for (Node next;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((caseToRemove != c) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); caseToRemove = next) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[19]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[20]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[21]++;
}
          next = caseToRemove.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[139]++;
          removeCase(n, caseToRemove);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[140]++;
        }
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[141]++;

        // Don't use the switch condition as the previous case.
        Node prevCase = (lastNonRemovable == n.getFirstChild())
            ? null : lastNonRemovable;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[142]++;
int CodeCoverConditionCoverageHelper_C40;

        // Remove the default case if we can
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((isUselessCase(c, prevCase)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[84]++;
          removeCase(n, c);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[143]++;
          return null;

        } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[85]++;}
        return c;

      } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[83]++;
        Preconditions.checkState(c.isCase());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[144]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[145]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((c.getLastChild().hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(c.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[86]++;
          lastNonRemovable = c;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[146]++;

        } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[87]++;}
      }
    }
    return null;
  }

  /**
   * Remove the case from the switch redeclaring any variables declared in it.
   * @param caseNode The case to remove.
   */
  private void removeCase(Node switchNode, Node caseNode) {
    NodeUtil.redeclareVarsInsideBranch(caseNode);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[147]++;
    switchNode.removeChild(caseNode);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[148]++;
    reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[149]++;
  }

  /**
   * The function assumes that when checking a CASE node there is no
   * DEFAULT node in the SWITCH.
   * @return Whether the CASE or DEFAULT block does anything useful.
   */
  private boolean isUselessCase(Node caseNode, @Nullable Node previousCase) {
    Preconditions.checkState(
        previousCase == null || previousCase.getNext() == caseNode);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[150]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[151]++;
    // A case isn't useless can't be useless if a previous case falls
    // through to it unless it happens to be the last case in the switch.
    Node switchNode = caseNode.getParent();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[152]++;
int CodeCoverConditionCoverageHelper_C42;
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((switchNode.getLastChild() != caseNode) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((previousCase != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[88]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[153]++;
      Node previousBlock = previousCase.getLastChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[154]++;
int CodeCoverConditionCoverageHelper_C43;
      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((previousBlock.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((isExit(previousBlock.getLastChild())) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[90]++;
        return false;

      } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[91]++;}

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[89]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[155]++;

    Node executingCase = caseNode;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[156]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[22]++;


int CodeCoverConditionCoverageHelper_C44;
    while ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((executingCase != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[22]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[23]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[24]++;
}
      Preconditions.checkState(executingCase.isDefaultCase()
          || executingCase.isCase());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[157]++;
      // We only expect a DEFAULT case if the case we are checking is the
      // DEFAULT case.  Otherwise, we assume the DEFAULT case has already
      // been removed.
      Preconditions.checkState(caseNode == executingCase
          || !executingCase.isDefaultCase());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[158]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[159]++;
      Node block = executingCase.getLastChild();
      Preconditions.checkState(block.isBlock());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[160]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[161]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((block.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[92]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[162]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[25]++;


        for (Node blockChild : block.children()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[25]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[26]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[27]++;
}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[163]++;
          // If this is a block with a labelless break, it is useless.
          switch (blockChild.getType()) {
            case Token.BREAK:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[94]++;
              // A break to a different control structure isn't useless.
              return blockChild.getFirstChild() == null;
            case Token.VAR:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[95]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[164]++;
int CodeCoverConditionCoverageHelper_C46;
              if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((blockChild.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((blockChild.getFirstChild().getFirstChild() == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[96]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[165]++;
                // Variable declarations without initializations are OK.
                continue;

              } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[97]++;}
              return false;
            default:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[98]++;
              return false;
          }
        }

      } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[93]++;
        // Look at the fallthrough case
        executingCase = executingCase.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[166]++;
      }
    }
    return true;
  }

  /**
   * @return Whether the node is an obvious control flow exit.
   */
  private boolean isExit(Node n) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[167]++;
    switch (n.getType()) {
      case Token.BREAK:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[99]++;
      case Token.CONTINUE:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[100]++;
      case Token.RETURN:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[101]++;
      case Token.THROW:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[102]++;
        return true;
      default:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[103]++;
        return false;
    }
  }

  private Node tryFoldComma(Node n) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[168]++;
    // If the left side does nothing replace the comma with the result.
    Node parent = n.getParent();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[169]++;
    Node left = n.getFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[170]++;
    Node right = left.getNext();

    left = trySimplifyUnusedResult(left);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[171]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[172]++;
int CodeCoverConditionCoverageHelper_C47;
    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((left == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(left)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[104]++;
      // Fold it!
      n.removeChild(right);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[173]++;
      parent.replaceChild(n, right);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[174]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[175]++;
      return right;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[105]++;}
    return n;
  }

  /**
   * Try removing unneeded block nodes and their useless children
   */
  Node tryOptimizeBlock(Node n) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[176]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[28]++;


int CodeCoverConditionCoverageHelper_C48;
    // Remove any useless children
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false); ) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[28]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[29]--;
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.loops[30]++;
}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[177]++;
      Node next = c.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[178]++;
int CodeCoverConditionCoverageHelper_C49;  // save c.next, since 'c' may be removed
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((isUnremovableNode(c)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(c)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[106]++;
        // TODO(johnlenz): determine what this is actually removing. Candidates
        //    include: EMPTY nodes, control structures without children
        //    (removing infinite loops), empty try blocks.  What else?
        n.removeChild(c);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[179]++;  // lazy kids
        reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[180]++;

      } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[107]++;
        tryOptimizeConditionalAfterAssign(c);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[181]++;
      }
      c = next;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[182]++;
    }
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[183]++;
int CodeCoverConditionCoverageHelper_C50;

    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((n.isSyntheticBlock()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((n.getParent() == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[108]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[109]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[184]++;
int CodeCoverConditionCoverageHelper_C51;

    // Try to remove the block.
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((NodeUtil.tryMergeBlock(n)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[110]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[185]++;
      return null;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[111]++;}

    return n;
  }

  /**
   * Some nodes unremovable node don't have side-effects.
   */
  private boolean isUnremovableNode(Node n) {
    return (n.isBlock() && n.isSyntheticBlock()) || n.isScript();
  }

  // TODO(johnlenz): Consider moving this to a separate peephole pass.
  /**
   * Attempt to replace the condition of if or hook immediately that is a
   * reference to a name that is assigned immediately before.
   */
  private void tryOptimizeConditionalAfterAssign(Node n) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[186]++;
    Node next = n.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[187]++;
int CodeCoverConditionCoverageHelper_C52;

    // Look for patterns like the following and replace the if-condition with
    // a constant value so it can later be folded:
    //   var a = /a/;
    //   if (a) {foo(a)}
    // or
    //   a = 0;
    //   a ? foo(a) : c;
    // or
    //   a = 0;
    //   a || foo(a);
    // or
    //   a = 0;
    //   a && foo(a)
    //
    // TODO(johnlenz): This would be better handled by control-flow sensitive
    // constant propagation. As the other case that I want to handle is:
    //   i=0; for(;i<0;i++){}
    // as right now nothing facilitates removing a loop like that.
    // This is here simply to remove the cruft left behind goog.userAgent and
    // similar cases.

    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((isSimpleAssignment(n)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((isConditionalStatement(next)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[112]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[188]++;
      Node lhsAssign = getSimpleAssignmentName(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[189]++;

      Node condition = getConditionalStatementCondition(next);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[190]++;
int CodeCoverConditionCoverageHelper_C53;
      if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (32)) == 0 || true) &&
 ((lhsAssign.isName()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((condition.isName()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((lhsAssign.getString().equals(condition.getString())) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[114]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[191]++;
        Node rhsAssign = getSimpleAssignmentValue(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[192]++;
        TernaryValue value = NodeUtil.getImpureBooleanValue(rhsAssign);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[193]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((value != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[116]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[194]++;
          Node replacementConditionNode =
              NodeUtil.booleanNode(value.toBoolean(true));
          condition.getParent().replaceChild(condition,
              replacementConditionNode);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[195]++;
          reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[196]++;

        } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[117]++;}

      } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[115]++;}

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[113]++;}
  }

  /**
   * @return whether the node is a assignment to a simple name, or simple var
   * declaration with initialization.
   */
  private boolean isSimpleAssignment(Node n) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[197]++;
int CodeCoverConditionCoverageHelper_C55;
    // For our purposes we define a simple assignment to be a assignment
    // to a NAME node, or a VAR declaration with one child and a initializer.
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((NodeUtil.isExprAssign(n)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((n.getFirstChild().getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[118]++;
      return true;

    } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[119]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[198]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (32)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((n.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((n.getFirstChild().getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 3) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 3) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[120]++;
      return true;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[121]++;}
}

    return false;
  }

  /**
   * @return The name being assigned to.
   */
  private Node getSimpleAssignmentName(Node n) {
    Preconditions.checkState(isSimpleAssignment(n));
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[199]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[200]++;
int CodeCoverConditionCoverageHelper_C57;
    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(n)) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[122]++;
      return n.getFirstChild().getFirstChild();

    } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[123]++;
      // A var declaration.
      return n.getFirstChild();
    }
  }

  /**
   * @return The value assigned in the simple assignment
   */
  private Node getSimpleAssignmentValue(Node n) {
    Preconditions.checkState(isSimpleAssignment(n));
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[201]++;
    return n.getFirstChild().getLastChild();
  }

  /**
   * @return Whether the node is a conditional statement.
   */
  private boolean isConditionalStatement(Node n) {
    // We defined a conditional statement to be a IF or EXPR_RESULT rooted with
    // a HOOK, AND, or OR node.
    return n != null && (n.isIf() || isExprConditional(n));
  }

  /**
   * @return Whether the node is a rooted with a HOOK, AND, or OR node.
   */
  private boolean isExprConditional(Node n) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[202]++;
int CodeCoverConditionCoverageHelper_C58;
    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((n.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[124]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[203]++;
      switch (n.getFirstChild().getType()) {
        case Token.HOOK:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[126]++;
        case Token.AND:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[127]++;
        case Token.OR:
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[128]++;
          return true; default : CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[129]++;
      }

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[125]++;}
    return false;
  }

  /**
   * @return The condition of a conditional statement.
   */
  private Node getConditionalStatementCondition(Node n) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[204]++;
int CodeCoverConditionCoverageHelper_C59;
    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((n.isIf()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[130]++;
      return NodeUtil.getConditionExpression(n);

    } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[131]++;
      Preconditions.checkState(isExprConditional(n));
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[205]++;
      return n.getFirstChild().getFirstChild();
    }
  }

  /**
   * Try folding IF nodes by removing dead branches.
   * @return the replacement node, if changed, or the original if not
   */
  private Node tryFoldIf(Node n) {
    Preconditions.checkState(n.isIf());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[206]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[207]++;
    Node parent = n.getParent();
    Preconditions.checkNotNull(parent);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[208]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[209]++;
    int type = n.getType();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[210]++;
    Node cond = n.getFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[211]++;
    Node thenBody = cond.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[212]++;
    Node elseBody = thenBody.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[213]++;
int CodeCoverConditionCoverageHelper_C60;

    // if (x) { .. } else { } --> if (x) { ... }
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((elseBody != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(elseBody)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[132]++;
      n.removeChild(elseBody);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[214]++;
      elseBody = null;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[215]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[216]++;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[133]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[217]++;
int CodeCoverConditionCoverageHelper_C61;

    // if (x) { } else { ... } --> if (!x) { ... }
    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((mayHaveSideEffects(thenBody)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((elseBody != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[134]++;
      n.removeChild(elseBody);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[218]++;
      n.replaceChild(thenBody, elseBody);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[219]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[220]++;
      Node notCond = new Node(Token.NOT);
      n.replaceChild(cond, notCond);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[221]++;
      notCond.addChildToFront(cond);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[222]++;
      cond = notCond;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[223]++;
      thenBody = cond.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[224]++;
      elseBody = null;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[225]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[226]++;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[135]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[227]++;
int CodeCoverConditionCoverageHelper_C62;

    // if (x()) { }
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((mayHaveSideEffects(thenBody)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((elseBody == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[136]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[228]++;
int CodeCoverConditionCoverageHelper_C63;
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(cond)) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[138]++;
        // x() has side effects, just leave the condition on its own.
        n.removeChild(cond);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[229]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[230]++;
        Node replacement = NodeUtil.newExpr(cond);
        parent.replaceChild(n, replacement);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[231]++;
        reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[232]++;
        return replacement;

      } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[139]++;
        // x() has no side effects, the whole tree is useless now.
        NodeUtil.removeChild(parent, n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[233]++;
        reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[234]++;
        return null;
      }

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[137]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[235]++;

    // Try transforms that apply to both IF and HOOK.
    TernaryValue condValue = NodeUtil.getImpureBooleanValue(cond);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[236]++;
int CodeCoverConditionCoverageHelper_C64;
    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((condValue == TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[140]++;
      return n;
  // We can't remove branches otherwise!
    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[141]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[237]++;
int CodeCoverConditionCoverageHelper_C65;

    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(cond)) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[142]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[238]++;
      // Transform "if (a = 2) {x =2}" into "if (true) {a=2;x=2}"
      boolean newConditionValue = condValue == TernaryValue.TRUE;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[239]++;
int CodeCoverConditionCoverageHelper_C66;
      // Add an elseBody if it is needed.
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((newConditionValue) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((elseBody == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[144]++;
        elseBody = IR.block().srcref(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[240]++;
        n.addChildToBack(elseBody);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[241]++;

      } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[145]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[242]++;
      Node newCond = NodeUtil.booleanNode(newConditionValue);
      n.replaceChild(cond, newCond);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[243]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[244]++;
      Node branchToKeep = newConditionValue ? thenBody : elseBody;
      branchToKeep.addChildToFront(IR.exprResult(cond).srcref(cond));
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[245]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[246]++;
      cond = newCond;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[247]++;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[143]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[248]++;

    boolean condTrue = condValue.toBoolean(true);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[249]++;
int CodeCoverConditionCoverageHelper_C67;
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((n.getChildCount() == 2) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[146]++;
      Preconditions.checkState(type == Token.IF);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[250]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[251]++;
int CodeCoverConditionCoverageHelper_C68;

      if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((condTrue) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[148]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[252]++;
        // Replace "if (true) { X }" with "X".
        Node thenStmt = n.getFirstChild().getNext();
        n.removeChild(thenStmt);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[253]++;
        parent.replaceChild(n, thenStmt);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[254]++;
        reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[255]++;
        return thenStmt;

      } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[149]++;
        // Remove "if (false) { X }" completely.
        NodeUtil.redeclareVarsInsideBranch(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[256]++;
        NodeUtil.removeChild(parent, n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[257]++;
        reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[258]++;
        return null;
      }

    } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[147]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[259]++;
      // Replace "if (true) { X } else { Y }" with X, or
      // replace "if (false) { X } else { Y }" with Y.
      Node trueBranch = n.getFirstChild().getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[260]++;
      Node falseBranch = trueBranch.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[261]++;
      Node branchToKeep = condTrue ? trueBranch : falseBranch;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[262]++;
      Node branchToRemove = condTrue ? falseBranch : trueBranch;
      NodeUtil.redeclareVarsInsideBranch(branchToRemove);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[263]++;
      n.removeChild(branchToKeep);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[264]++;
      parent.replaceChild(n, branchToKeep);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[265]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[266]++;
      return branchToKeep;
    }
  }

  /**
   * Try folding HOOK (?:) if the condition results of the condition is known.
   * @return the replacement node, if changed, or the original if not
   */
  private Node tryFoldHook(Node n) {
    Preconditions.checkState(n.isHook());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[267]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[268]++;
    Node parent = n.getParent();
    Preconditions.checkNotNull(parent);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[269]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[270]++;
    Node cond = n.getFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[271]++;
    Node thenBody = cond.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[272]++;
    Node elseBody = thenBody.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[273]++;

    TernaryValue condValue = NodeUtil.getImpureBooleanValue(cond);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[274]++;
int CodeCoverConditionCoverageHelper_C69;
    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((condValue == TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[150]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[275]++;
int CodeCoverConditionCoverageHelper_C70;
      // If the result nodes are equivalent, then one of the nodes can be
      // removed and it doesn't matter which.
      if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((areNodesEqualForInlining(thenBody, elseBody)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[152]++;
        return n;
  // We can't remove branches otherwise!
      } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[153]++;}

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[151]++;}

    // Transform "(a = 2) ? x =2 : y" into "a=2,x=2"
    n.detachChildren();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[276]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[277]++;
    Node branchToKeep = condValue.toBoolean(true) ? thenBody : elseBody;
    Node replacement;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[278]++;
int CodeCoverConditionCoverageHelper_C71;
    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(cond)) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[154]++;
      replacement = IR.comma(cond, branchToKeep).srcref(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[279]++;

    } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[155]++;
      replacement = branchToKeep;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[280]++;
    }

    parent.replaceChild(n, replacement);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[281]++;
    reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[282]++;
    return replacement;
  }

  /**
   * Removes WHILEs that always evaluate to false.
   */
  Node tryFoldWhile(Node n) {
    Preconditions.checkArgument(n.isWhile());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[283]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[284]++;
    Node cond = NodeUtil.getConditionExpression(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[285]++;
int CodeCoverConditionCoverageHelper_C72;
    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((NodeUtil.getPureBooleanValue(cond) != TernaryValue.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[156]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[157]++;}
    NodeUtil.redeclareVarsInsideBranch(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[286]++;
    NodeUtil.removeChild(n.getParent(), n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[287]++;
    reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[288]++;

    return null;
  }

  /**
   * Removes FORs that always evaluate to false.
   */
  Node tryFoldFor(Node n) {
    Preconditions.checkArgument(n.isFor());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[289]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[290]++;
int CodeCoverConditionCoverageHelper_C73;
    // If this is a FOR-IN loop skip it.
    if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(n)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[158]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[159]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[291]++;

    Node init = n.getFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[292]++;
    Node cond = init.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[293]++;
    Node increment = cond.getNext();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[294]++;
int CodeCoverConditionCoverageHelper_C74;

    if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((init.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((init.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[160]++;
      init = trySimplifyUnusedResult(init, false);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[295]++;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[161]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[296]++;
int CodeCoverConditionCoverageHelper_C75;

    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((increment.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[162]++;
      increment = trySimplifyUnusedResult(increment, false);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[297]++;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[163]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[298]++;
int CodeCoverConditionCoverageHelper_C76;

    // There is an initializer skip it
    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[164]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[165]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[299]++;
int CodeCoverConditionCoverageHelper_C77;

    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((NodeUtil.getImpureBooleanValue(cond) != TernaryValue.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[166]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[167]++;}

    NodeUtil.redeclareVarsInsideBranch(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[300]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[301]++;
int CodeCoverConditionCoverageHelper_C78;
    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(cond)) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[168]++;
      NodeUtil.removeChild(n.getParent(), n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[302]++;

    } else {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[169]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[303]++;
      Node statement = IR.exprResult(cond.detachFromParent())
          .copyInformationFrom(cond);
      n.getParent().replaceChild(n, statement);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[304]++;
    }
    reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[305]++;
    return null;
  }

  /**
   * Removes DOs that always evaluate to false. This leaves the
   * statements that were in the loop in a BLOCK node.
   * The block will be removed in a later pass, if possible.
   */
  Node tryFoldDo(Node n) {
    Preconditions.checkArgument(n.isDo());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[306]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[307]++;

    Node cond = NodeUtil.getConditionExpression(n);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[308]++;
int CodeCoverConditionCoverageHelper_C79;
    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((NodeUtil.getImpureBooleanValue(cond) != TernaryValue.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[170]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[171]++;}
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[309]++;
int CodeCoverConditionCoverageHelper_C80;

    // TODO(johnlenz): The do-while can be turned into a label with
    // named breaks and the label optimized away (maybe).
    if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((hasBreakOrContinue(n)) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[172]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[173]++;}

    Preconditions.checkState(
        NodeUtil.isControlStructureCodeBlock(n, n.getFirstChild()));
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[310]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[311]++;
    Node block = n.removeFirstChild();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[312]++;

    Node parent =  n.getParent();
    parent.replaceChild(n, block);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[313]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[314]++;
int CodeCoverConditionCoverageHelper_C81;
    if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(cond)) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[174]++;
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[315]++;
      Node condStatement = IR.exprResult(cond.detachFromParent())
          .srcref(cond);
      parent.addChildAfter(condStatement, block);
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[316]++;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[175]++;}
    reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[317]++;

    return n;
  }

  /**
   *
   */
  boolean hasBreakOrContinue(Node n) {
    // TODO(johnlenz): This is overkill as named breaks may refer to outer
    // loops or labels, and any break my refer to an inner loop.
    // More generally, this check may be more expensive than we like.
    return NodeUtil.has(
        n,
        Predicates.<Node>or(
            new NodeUtil.MatchNodeType(Token.BREAK),
            new NodeUtil.MatchNodeType(Token.CONTINUE)),
        NodeUtil.MATCH_NOT_FUNCTION);
  }

  /**
   * Remove always true loop conditions.
   */
  private void tryFoldForCondition(Node forCondition) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[318]++;
int CodeCoverConditionCoverageHelper_C82;
    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((NodeUtil.getPureBooleanValue(forCondition) == TernaryValue.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[176]++;
      forCondition.getParent().replaceChild(forCondition,
          IR.empty());
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[319]++;
      reportCodeChange();
CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.statements[320]++;

    } else {
  CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl.branches[177]++;}
  }
}

class CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl ());
  }
    public static long[] statements = new long[321];
    public static long[] branches = new long[178];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[83];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PeepholeRemoveDeadCode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,3,1,1,3,1,1,2,2,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,2,2,2,1,1,2,2,1,2,2,1,2,3,1,2,3,1,1,1,2,2,2,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 82; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$5533np9o0t6kfsgjtuo10sm04ic4amkxx28is8r7fl () {
    super("com.google.javascript.jscomp.PeepholeRemoveDeadCode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 320; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 177; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 82; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PeepholeRemoveDeadCode.java");
      for (int i = 1; i <= 320; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 177; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 82; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

