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
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * Prepare the AST before we do any checks or optimizations on it.
 *
 * This pass must run. It should bring the AST into a consistent state,
 * and add annotations where necessary. It should not make any transformations
 * on the tree that would lose source information, since we need that source
 * information for checks.
 *
 * @author johnlenz@google.com (John Lenz)
 */
class PrepareAst implements CompilerPass {
  static {
    CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.ping();
  }


  private final AbstractCompiler compiler;
  private final boolean checkOnly;

  PrepareAst(AbstractCompiler compiler) {
    this(compiler, false);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[1]++;
  }

  PrepareAst(AbstractCompiler compiler, boolean checkOnly) {
    this.compiler = compiler;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[2]++;
    this.checkOnly = checkOnly;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[3]++;
  }

  private void reportChange() {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((checkOnly) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[1]++;
      Preconditions.checkState(false, "normalizeNodeType constraints violated");
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[5]++;

    } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[2]++;}
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((checkOnly) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[3]++;
      normalizeNodeTypes(root);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[7]++;

    } else {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[4]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
      // Don't perform "PrepareAnnotations" when doing checks as
      // they currently aren't valid during sanity checks.  In particular,
      // they DIRECT_EVAL shouldn't be applied after inlining has been
      // performed.
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((externs != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[5]++;
        NodeTraversal.traverse(
            compiler, externs, new PrepareAnnotations());
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[9]++;

      } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[6]++;}
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((root != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[7]++;
        NodeTraversal.traverse(
            compiler, root, new PrepareAnnotations());
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[11]++;

      } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[8]++;}
    }
  }

  /**
   * Covert EXPR_VOID to EXPR_RESULT to simplify the rest of the code.
   */
  private void normalizeNodeTypes(Node n) {
    normalizeBlocks(n);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[12]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;

    for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[1]--;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[2]--;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[3]++;
}
      // This pass is run during the CompilerTestCase validation, so this
      // parent pointer check serves as a more general check.
      Preconditions.checkState(child.getParent() == n);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[14]++;

      normalizeNodeTypes(child);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[15]++;
    }
  }

  /**
   * Add blocks to IF, WHILE, DO, etc.
   */
  private void normalizeBlocks(Node n) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((NodeUtil.isControlStructure(n)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((n.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.isSwitch()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[9]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[17]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
      for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[4]--;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[5]--;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[6]++;
}
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[18]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((NodeUtil.isControlStructureCodeBlock(n,c)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((c.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[11]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[19]++;
          Node newBlock = IR.block().srcref(n);
          n.replaceChild(c, newBlock);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[20]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((c.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[13]++;
            newBlock.addChildrenToFront(c);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[22]++;

          } else {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[14]++;
            newBlock.setWasEmptyNode(true);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[23]++;
          }
          c = newBlock;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[24]++;
          reportChange();
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[25]++;

        } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[12]++;}
      }

    } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[10]++;}
  }

  /**
   * Normalize where annotations appear on the AST. Copies
   * around existing JSDoc annotations as well as internal annotations.
   */
  static class PrepareAnnotations
      implements NodeTraversal.Callback {

    PrepareAnnotations() {
    }

    @Override
    public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[15]++;
        normalizeObjectLiteralAnnotations(n);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[27]++;

      } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[16]++;}
      return true;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[28]++;
      switch (n.getType()) {
        case Token.CALL:
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[17]++;
          annotateCalls(n);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[29]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[30]++;
          break;

        case Token.FUNCTION:
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[18]++;
          annotateDispatchers(n, parent);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[31]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[32]++;
          break; default : CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[19]++;
      }
    }

    private void normalizeObjectLiteralAnnotations(Node objlit) {
      Preconditions.checkState(objlit.isObjectLit());
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[33]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[34]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[7]++;


int CodeCoverConditionCoverageHelper_C11;
      for (Node key = objlit.getFirstChild();(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); key = key.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[7]--;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[8]--;
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.loops[9]++;
}
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[35]++;
        Node value = key.getFirstChild();
        normalizeObjectLiteralKeyAnnotations(objlit, key, value);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[36]++;
      }
    }

    /**
     * There are two types of calls we are interested in calls without explicit
     * "this" values (what we are call "free" calls) and direct call to eval.
     */
    private void annotateCalls(Node n) {
      Preconditions.checkState(n.isCall());
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[37]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[38]++;

      // Keep track of of the "this" context of a call.  A call without an
      // explicit "this" is a free call.
      Node first = n.getFirstChild();
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[39]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(first)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[20]++;
        n.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[40]++;

      } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[21]++;}
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[41]++;
int CodeCoverConditionCoverageHelper_C13;

      // Keep track of the context in which eval is called. It is important
      // to distinguish between "(0, eval)()" and "eval()".
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((first.isName()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 (("eval".equals(first.getString())) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[22]++;
        first.putBooleanProp(Node.DIRECT_EVAL, true);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[42]++;

      } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[23]++;}
    }

    /**
     * Translate dispatcher info into the property expected node.
     */
    private void annotateDispatchers(Node n, Node parent) {
      Preconditions.checkState(n.isFunction());
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[43]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[44]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((parent.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((parent.getJSDocInfo().isJavaDispatch()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[24]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[45]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[26]++;
          Preconditions.checkState(parent.getLastChild() == n);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[46]++;
          n.putBooleanProp(Node.IS_DISPATCHER, true);
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[47]++;

        } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[27]++;}

      } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[25]++;}
    }

    /**
     * In the AST that Rhino gives us, it needs to make a distinction
     * between JsDoc on the object literal node and JsDoc on the object literal
     * value. For example,
     * <pre>
     * var x = {
     *   / JSDOC /
     *   a: 'b',
     *   c: / JSDOC / 'd'
     * };
     * </pre>
     *
     * But in few narrow cases (in particular, function literals), it's
     * a lot easier for us if the doc is attached to the value.
     */
    private void normalizeObjectLiteralKeyAnnotations(
        Node objlit, Node key, Node value) {
      Preconditions.checkState(objlit.isObjectLit());
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[48]++;
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[49]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((key.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((value.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[28]++;
        value.setJSDocInfo(key.getJSDocInfo());
CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.statements[50]++;

      } else {
  CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl.branches[29]++;}
    }
  }
}

class CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl ());
  }
    public static long[] statements = new long[51];
    public static long[] branches = new long[30];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PrepareAst.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,3,1,2,1,1,1,1,2,2,1,2};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$o3zqlpz4kz5oxw7csh4k3fl () {
    super("com.google.javascript.jscomp.PrepareAst.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 50; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 29; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PrepareAst.java");
      for (int i = 1; i <= 50; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 29; i++) {
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

