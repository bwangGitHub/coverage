/*
 * Copyright 2009 The Closure Compiler Authors.
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
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.TernaryValue;

/**
 * Transform the structure of the AST so that the number of explicit exits
 * are minimized.
 *
 * @author johnlenz@google.com (John Lenz)
 */
class MinimizeExitPoints
    extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.ping();
  }


  AbstractCompiler compiler;

  MinimizeExitPoints(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[1]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[2]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[3]++;
    switch (n.getType()) {
      case Token.LABEL:
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[1]++;
        tryMinimizeExits(
            n.getLastChild(), Token.BREAK, n.getFirstChild().getString());
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[4]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[5]++;
        break;

      case Token.FOR:
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[2]++;
      case Token.WHILE:
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[3]++;
        tryMinimizeExits(NodeUtil.getLoopCodeBlock(n), Token.CONTINUE, null);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[6]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[7]++;
        break;

      case Token.DO:
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[4]++;
        tryMinimizeExits(NodeUtil.getLoopCodeBlock(n), Token.CONTINUE, null);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[8]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[9]++;

        Node cond = NodeUtil.getConditionExpression(n);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((NodeUtil.getImpureBooleanValue(cond) == TernaryValue.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[5]++;
          // Normally, we wouldn't be able to optimize BREAKs inside a loop
          // but as we know the condition will always false, we can treat them
          // as we would a CONTINUE.
          tryMinimizeExits(n.getFirstChild(), Token.BREAK, null);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[11]++;

        } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[6]++;}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[12]++;
        break;

      case Token.FUNCTION:
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[7]++;
        tryMinimizeExits(n.getLastChild(), Token.RETURN, null);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[13]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[14]++;
        break; default : CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[8]++;
    }
  }

  /**
   * Attempts to minimize the number of explicit exit points in a control
   * structure to take advantage of the implied exit at the end of the
   * structure.  This is accomplished by removing redundant statements, and
   * moving statements following a qualifying IF node into that node.
   * For example:
   *
   * function () {
   *   if (x) return;
   *   else blah();
   *   foo();
   * }
   *
   * becomes:
   *
   * function () {
   *  if (x) ;
   *  else {
   *    blah();
   *    foo();
   *  }
   *
   * @param n The execution node of a parent to inspect.
   * @param exitType The type of exit to look for.
   * @param labelName If parent is a label the name of the label to look for,
   *   null otherwise.
   * @nullable labelName non-null only for breaks within labels.
   */
  void tryMinimizeExits(Node n, int exitType, String labelName) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;

    // Just an 'exit'.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((matchingExitNode(n, exitType, labelName)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[9]++;
      NodeUtil.removeChild(n.getParent(), n);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[16]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[17]++;
      return;

    } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[10]++;}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;

    // Just an 'if'.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.isIf()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[11]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[19]++;
      Node ifBlock = n.getFirstChild().getNext();
      tryMinimizeExits(ifBlock, exitType, labelName);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[20]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[21]++;
      Node elseBlock = ifBlock.getNext();
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[22]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((elseBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[13]++;
        tryMinimizeExits(elseBlock, exitType, labelName);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[23]++;

      } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[14]++;}
      return;

    } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[12]++;}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;

    // Just a 'try/catch/finally'.
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.isTry()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[15]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[25]++;
      Node tryBlock = n.getFirstChild();
      tryMinimizeExits(tryBlock, exitType, labelName);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[26]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[27]++;
      Node allCatchNodes = NodeUtil.getCatchBlock(n);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.hasCatchHandler(allCatchNodes)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[17]++;
        Preconditions.checkState(allCatchNodes.hasOneChild());
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[29]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[30]++;
        Node catchNode = allCatchNodes.getFirstChild();
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[31]++;
        Node catchCodeBlock = catchNode.getLastChild();
        tryMinimizeExits(catchCodeBlock, exitType, labelName);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[32]++;

      } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[18]++;}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((NodeUtil.hasFinally(n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[19]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[34]++;
        Node finallyBlock = n.getLastChild();
        tryMinimizeExits(finallyBlock, exitType, labelName);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[35]++;

      } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[20]++;}

    } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[16]++;}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;

    // Just a 'label'.
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[21]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[37]++;
      Node labelBlock = n.getLastChild();
      tryMinimizeExits(labelBlock, exitType, labelName);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[38]++;

    } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[22]++;}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[39]++;
int CodeCoverConditionCoverageHelper_C9;

    // TODO(johnlenz): The last case of SWITCH statement?

    // The rest assumes a block with at least one child, bail on anything else.
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n.getLastChild() == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[23]++;
      return;

    } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[24]++;}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[40]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[1]++;



    // Multiple if-exits can be converted in a single pass.
    // Convert "if (blah) break;  if (blah2) break; other_stmt;" to
    // become "if (blah); else { if (blah2); else { other_stmt; } }"
    // which will get converted to "if (!blah && !blah2) { other_stmt; }".
    for (Node c : n.children()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[1]--;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[2]--;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[3]++;
}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;

      // An 'if' block to process below.
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((c.isIf()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[25]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[42]++;
        Node ifTree = c;
        Node trueBlock, falseBlock;

        // First, the true condition block.
        trueBlock = ifTree.getFirstChild().getNext();
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[43]++;
        falseBlock = trueBlock.getNext();
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[44]++;
        tryMinimizeIfBlockExits(trueBlock, falseBlock,
            ifTree, exitType, labelName);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[45]++;

        // Now the else block.
        // The if blocks may have changed, get them again.
        trueBlock = ifTree.getFirstChild().getNext();
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[46]++;
        falseBlock = trueBlock.getNext();
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[47]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[48]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((falseBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[27]++;
          tryMinimizeIfBlockExits(falseBlock, trueBlock,
              ifTree, exitType, labelName);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[49]++;

        } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[28]++;}

      } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[26]++;}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;

      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((c == n.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[29]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[51]++;
        break;

      } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[30]++;}
    }
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[52]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;

    // Now try to minimize the exits of the last child, if it is removed
    // look at what has become the last child.
    for (Node c = n.getLastChild();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); c = n.getLastChild()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[4]--;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[5]--;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[6]++;
}
      tryMinimizeExits(c, exitType, labelName);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[53]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;
      // If the node is still the last child, we are done.
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c == n.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[31]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[55]++;
        break;

      } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[32]++;}
    }
  }

  /**
   * Look for exits (returns, breaks, or continues, depending on the context) at
   * the end of a block and removes them by moving the if node's siblings,
   * if any, into the opposite condition block.
   *
   * @param srcBlock The block to inspect.
   * @param destBlock The block to move sibling nodes into.
   * @param ifNode The if node to work with.
   * @param exitType The type of exit to look for.
   * @param labelName The name associated with the exit, if any.
   * @nullable labelName null for anything excepted for named-break associated
   *           with a label.
   */
  private void tryMinimizeIfBlockExits(Node srcBlock, Node destBlock,
      Node ifNode, int exitType, String labelName) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[56]++;
    Node exitNodeParent = null;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[57]++;
    Node exitNode = null;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[58]++;
int CodeCoverConditionCoverageHelper_C15;

    // Pick an exit node candidate.
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((srcBlock.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[33]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[59]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((srcBlock.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[35]++;
        return;

      } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[36]++;}
      exitNodeParent = srcBlock;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[60]++;
      exitNode = exitNodeParent.getLastChild();
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[61]++;

    } else {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[34]++;
      // Just a single statement, if it isn't an exit bail.
      exitNodeParent = ifNode;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[62]++;
      exitNode = srcBlock;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[63]++;
    }
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[64]++;
int CodeCoverConditionCoverageHelper_C17;

    // Verify the candidate.
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((matchingExitNode(exitNode, exitType, labelName)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[37]++;
      return;

    } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[38]++;}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[65]++;
int CodeCoverConditionCoverageHelper_C18;

    // Take case of the if nodes siblings, if any.
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((ifNode.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[39]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[66]++;
      // Move siblings of the if block into the opposite
      // logic block of the exit.
      Node newDestBlock = IR.block().srcref(ifNode);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[67]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((destBlock == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[41]++;
        // Only possible if this is the false block.
        ifNode.addChildToBack(newDestBlock);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[68]++;

      } else {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[42]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[69]++;
int CodeCoverConditionCoverageHelper_C20; if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((destBlock.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[43]++;
        // Use the new block.
        ifNode.replaceChild(destBlock, newDestBlock);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[70]++;

      } else {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[44]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[71]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((destBlock.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[45]++;
        // Reuse the existing block.
        newDestBlock = destBlock;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[72]++;

      } else {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[46]++;
        // Add the existing statement to the new block.
        ifNode.replaceChild(destBlock, newDestBlock);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[73]++;
        newDestBlock.addChildToBack(destBlock);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[74]++;
      }
}
}

      // Move all the if node's following siblings.
      moveAllFollowing(ifNode, ifNode.getParent(), newDestBlock);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[75]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[76]++;

    } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[40]++;}
  }

  /**
   * Determines if n matches the type and name for the following types of
   * "exits":
   *    - return without values
   *    - continues and breaks with or without names.
   * @param n The node to inspect.
   * @param type The Token type to look for.
   * @param labelName The name that must be associated with the exit type.
   * @nullable labelName non-null only for breaks associated with labels.
   * @return Whether the node matches the specified block-exit type.
   */
  private static boolean matchingExitNode(Node n, int type, String labelName) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[77]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((n.getType() == type) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[47]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[78]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((type == Token.RETURN) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[49]++;
        // only returns without expressions.
        return !n.hasChildren();

      } else {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[50]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[79]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((labelName == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[51]++;
          return !n.hasChildren();

        } else {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[52]++;
          return n.hasChildren()
            && labelName.equals(n.getFirstChild().getString());
        }
      }

    } else {
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[48]++;}
    return false;
  }

  /**
   * Move all the child nodes following start in srcParent to the end of
   * destParent's child list.
   * @param start The start point in the srcParent child list.
   * @param srcParent The parent node of start.
   * @param destParent The destination node.
   */
  private static void moveAllFollowing(
      Node start, Node srcParent, Node destParent) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[80]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[7]++;


int CodeCoverConditionCoverageHelper_C25;
    for (Node n = start.getNext();(((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false); n = start.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[7]--;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[8]--;
  CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.loops[9]++;
}
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[81]++;
      boolean isFunctionDeclaration = NodeUtil.isFunctionDeclaration(n);
      srcParent.removeChild(n);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[82]++;
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[83]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((isFunctionDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[53]++;
        destParent.addChildToFront(n);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[84]++;

      } else {
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.branches[54]++;
        destParent.addChildToBack(n);
CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash.statements[85]++;
      }
    }
  }
}

class CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash ());
  }
    public static long[] statements = new long[86];
    public static long[] branches = new long[55];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[27];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.MinimizeExitPoints.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 26; i++) {
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

  public CodeCoverCoverageCounter$2ibnhg939va3xs31sqyrxad8m5pbcrs7rash () {
    super("com.google.javascript.jscomp.MinimizeExitPoints.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 85; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 54; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 26; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.MinimizeExitPoints.java");
      for (int i = 1; i <= 85; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 54; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 26; i++) {
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

