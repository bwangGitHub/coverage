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
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.CodingConvention.SubclassRelationship;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;
import java.util.Set;

/**
 * Callback that gathers subexpressions that may have side effects
 * and appends copies of those subexpressions to the replacements
 * list.  In the case of branching subexpressions, it simplifies the
 * subexpression before adding it to the replacement list.
 *
 */
class GatherSideEffectSubexpressionsCallback implements Callback {
  static {
    CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.ping();
  }


  /**
   * Used by GatherSideEffectSubexpressionsCallback to notify client
   * code about side effect expressions that should be kept.
   */
  interface SideEffectAccumulator {

    /**
     * Returns true if the "mixin" and "inherits" function calls
     * should be treated as if they had side effects.
     */
    boolean classDefiningCallsHaveSideEffects();

    /**
     * Adds subtree to the list of nodes that have side effects.
     *
     * @param original - root of the tree.
     */
    void keepSubTree(Node original);

    /**
     * Simplifies a subtree whose root node is an AND or OR expression
     * and adds the resulting subtree to the list of nodes that have
     * side effects.
     *
     * @param original - root of the and/or expression.
     */
    void keepSimplifiedShortCircuitExpression(Node original);

    /**
     * Simplifies a subtree whose root node is a HOOK expression
     * and adds the resulting subtree to the list of nodes that have
     * side effects.
     *
     * @param hook - root of the hook expression.
     * @param thenHasSideEffects - then branch has side effects
     * @param elseHasSideEffects - else branch has side effects
     */
    void keepSimplifiedHookExpression(Node hook,
                                      boolean thenHasSideEffects,
                                      boolean elseHasSideEffects);
  }

  /**
   * Populates the provided replacement list by appending copies of
   * subtrees that have side effects.
   *
   * It is OK if this class tears up the original tree, because
   * we're going to throw the tree out anyway.
   */
  static final class GetReplacementSideEffectSubexpressions
      implements SideEffectAccumulator {
    private final AbstractCompiler compiler;
    private final List<Node> replacements;

    /**
     * Creates the accumulator.
     *
     * @param compiler - the AbstractCompiler
     * @param replacements - list to accumulate into
     */
    GetReplacementSideEffectSubexpressions(AbstractCompiler compiler,
        List<Node> replacements) {
      this.compiler = compiler;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[1]++;
      this.replacements = replacements;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[2]++;
    }

    @Override
    public boolean classDefiningCallsHaveSideEffects() {
      return true;
    }

    @Override
    public void keepSubTree(Node original) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((original.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[1]++;
        original.detachFromParent();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[4]++;

      } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[2]++;}
      replacements.add(original);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[5]++;
    }

    @Override
    public void keepSimplifiedShortCircuitExpression(Node original) {
      Preconditions.checkArgument(
          (original.isAnd()) || (original.isOr()),
          "Expected: AND or OR, Got: %s", Token.name(original.getType()));
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[6]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[7]++;
      Node left = original.getFirstChild();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[8]++;
      Node right = left.getNext();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[9]++;
      Node simplifiedRight = simplifyShortCircuitBranch(right);
      original.detachChildren();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[10]++;
      original.addChildToBack(left);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[11]++;
      original.addChildToBack(simplifiedRight);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[12]++;
      keepSubTree(original);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[13]++;
    }

    @Override
    public void keepSimplifiedHookExpression(Node hook,
                                             boolean thenHasSideEffects,
                                             boolean elseHasSideEffects) {
      Preconditions.checkArgument(hook.isHook(),
          "Expected: HOOK, Got: %s", Token.name(hook.getType()));
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[14]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[15]++;
      Node condition = hook.getFirstChild();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[16]++;
      Node thenBranch = condition.getNext();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[17]++;
      Node elseBranch = thenBranch.getNext();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((thenHasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((elseHasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[3]++;
        hook.detachChildren();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[19]++;
        hook.addChildToBack(condition);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[20]++;
        hook.addChildToBack(simplifyShortCircuitBranch(thenBranch));
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[21]++;
        hook.addChildToBack(simplifyShortCircuitBranch(elseBranch));
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[22]++;
        keepSubTree(hook);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[23]++;

      } else {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[4]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[24]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((thenHasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((elseHasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[5]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[25]++;
        int type = thenHasSideEffects ? Token.AND : Token.OR;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[26]++;
        Node body = thenHasSideEffects ? thenBranch : elseBranch;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[27]++;
        Node simplified = new Node(
            type, condition.detachFromParent(),
            simplifyShortCircuitBranch(body))
            .copyInformationFrom(hook);
        keepSubTree(simplified);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[28]++;

      } else {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[6]++;
        throw new IllegalArgumentException(
            "keepSimplifiedHookExpression must keep at least 1 branch");
      }
}
    }

    private Node simplifyShortCircuitBranch(Node node) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[29]++;
      List<Node> parts = Lists.newArrayList();
      NodeTraversal.traverse(
          compiler, node,
          new GatherSideEffectSubexpressionsCallback(
              compiler,
              new GetReplacementSideEffectSubexpressions(compiler, parts)));
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[30]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[31]++;

      Node ret = null;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[32]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.loops[1]++;


      for (Node part : parts) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.loops[1]--;
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.loops[2]--;
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.loops[3]++;
}
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ret != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[7]++;
          ret = IR.comma(ret, part).srcref(node);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[34]++;

        } else {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[8]++;
          ret = part;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[35]++;
        }
      }
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[36]++;
int CodeCoverConditionCoverageHelper_C5;

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((ret == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[9]++;
        throw new IllegalArgumentException(
            "expected at least one side effect subexpression in short "
            + "circuit branch.");

      } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[10]++;}

      return ret;
    }
  }

  private static final Set<Integer> FORBIDDEN_TYPES = ImmutableSet.of(
      Token.BLOCK, Token.SCRIPT, Token.VAR, Token.EXPR_RESULT, Token.RETURN);
  static {
    CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[37]++;
  }
  private final AbstractCompiler compiler;
  private final SideEffectAccumulator accumulator;

  /**
   * @param compiler - AbstractCompiler object
   * @param accumulator - object that will accumulate roots of
   *                      subtrees that have side effects.
   */
  GatherSideEffectSubexpressionsCallback(AbstractCompiler compiler,
                                         SideEffectAccumulator accumulator) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[38]++;
    this.accumulator = accumulator;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[39]++;
  }

  /**
   * Determines if a call defines a class inheritance or mixing
   * relation, according to the current coding convention.
   */
  private boolean isClassDefiningCall(Node callNode) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[40]++;
    SubclassRelationship classes =
        compiler.getCodingConvention().getClassesDefinedByCall(callNode);
    return classes != null;
  }

  /**
   * Computes the list of subtrees whose root nodes have side effects.
   *
   * <p>If the current subtree's root has side effects this method should
   * call accumulator.keepSubTree and return 'false' to add the
   * subtree to the result list and avoid avoid traversing the nodes children.
   *
   * <p>Branching nodes whose then or else branch contain side effects
   * must be simplified by doing a recursive traversal; this method
   * should call the appropriate accumulator 'keepSimplified' method
   * and return 'false' to stop the regular traversal.
   */
  @Override
  public boolean shouldTraverse(
      NodeTraversal traversal, Node node, Node parent) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[41]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((FORBIDDEN_TYPES.contains(node.getType())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isControlStructure(node)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[11]++;
      throw new IllegalArgumentException(
          Token.name(node.getType()) + " nodes are not supported.");

    } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[12]++;}
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;

    // Do not recurse into nested functions.
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[13]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[14]++;}
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[43]++;
int CodeCoverConditionCoverageHelper_C8;

    // simplify and maybe keep hook expression.
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((node.isHook()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[15]++;
      return processHook(node);

    } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[16]++;}
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;

    // simplify and maybe keep AND/OR expression.
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((node.isAnd()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((node.isOr()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[17]++;
      return processShortCircuitExpression(node);

    } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[18]++;}
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((NodeUtil.nodeTypeMayHaveSideEffects(node, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[19]++;
      return true;

    } else {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[20]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[46]++;
int CodeCoverConditionCoverageHelper_C11;

      // Node type suggests that the expression has side effects.

      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((node.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[21]++;
        return processFunctionCall(node);

      } else {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[22]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[47]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((node.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[23]++;
        return processConstructorCall(node);

      } else {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[24]++;
        accumulator.keepSubTree(node);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[48]++;
        return false;
      }
}
    }
  }

  /**
   * Processes an AND or OR expression.
   *
   * @return true to continue traversal, false otherwise
   */
  boolean processShortCircuitExpression(Node node) {
    Preconditions.checkArgument(
        (node.isAnd()) || (node.isOr()),
        "Expected: AND or OR, Got: %s", Token.name(node.getType()));
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[49]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[50]++;

    // keep whole expression if RHS of the branching expression
    // contains a call.
    Node left = node.getFirstChild();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[51]++;
    Node right = left.getNext();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(right, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[25]++;
      accumulator.keepSimplifiedShortCircuitExpression(node);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[53]++;
      return false;

    } else {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[26]++;
      return true;
    }
  }

  /**
   * Processes a HOOK expression.
   *
   * @return true to continue traversal, false otherwise
   */
  boolean processHook(Node node) {
    Preconditions.checkArgument(node.isHook(),
        "Expected: HOOK, Got: %s", Token.name(node.getType()));
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[54]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[55]++;

    Node condition = node.getFirstChild();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[56]++;
    Node ifBranch = condition.getNext();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[57]++;
    Node elseBranch = ifBranch.getNext();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[58]++;
    boolean thenHasSideEffects = NodeUtil.mayHaveSideEffects(
        ifBranch, compiler);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[59]++;
    boolean elseHasSideEffects = NodeUtil.mayHaveSideEffects(
        elseBranch, compiler);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[60]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((thenHasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((elseHasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[27]++;
      accumulator.keepSimplifiedHookExpression(
          node, thenHasSideEffects, elseHasSideEffects);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[61]++;
      return false;

    } else {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[28]++;
      return true;
    }
  }

  /**
   * Processes a CALL expression.
   *
   * @return true to continue traversal, false otherwise
   */
  boolean processFunctionCall(Node node) {
    Preconditions.checkArgument(node.isCall(),
        "Expected: CALL, Got: %s", Token.name(node.getType()));
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[62]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[63]++;

    // Calls to functions that are known to be "pure" have no side
    // effects.
    Node functionName = node.getFirstChild();
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[64]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((functionName.isName()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((functionName.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[29]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[65]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((accumulator.classDefiningCallsHaveSideEffects()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isClassDefiningCall(node)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[31]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[32]++;}

    } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[30]++;}
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[66]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((NodeUtil.functionCallHasSideEffects(node)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[33]++;
      return true;

    } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[34]++;}

    accumulator.keepSubTree(node);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[67]++;
    return false;
  }

  /**
   * Processes a NEW expression.
   *
   * @return true to continue traversal, false otherwise
   */
  boolean processConstructorCall(Node node) {
    Preconditions.checkArgument(node.isNew(),
        "Expected: NEW, Got: %s", Token.name(node.getType()));
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[68]++;
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[69]++;
int CodeCoverConditionCoverageHelper_C18;

    // Calls to constructors that are known to be "pure" have no
    // side effects.
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((NodeUtil.constructorCallHasSideEffects(node)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[35]++;
      return true;

    } else {
  CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.branches[36]++;}

    accumulator.keepSubTree(node);
CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5.statements[70]++;
    return false;
  }

  @Override
  public void visit(NodeTraversal traversal, Node node, Node parent) {}
}

class CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5 ());
  }
    public static long[] statements = new long[71];
    public static long[] branches = new long[37];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.GatherSideEffectSubexpressionsCallback.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,1,1,2,1,1,2,1,1,1,1,2,2,2,1,1};
    for (int i = 1; i <= 18; i++) {
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

  public CodeCoverCoverageCounter$1x6klyzezzuh7eoje55oph2e5n9pu607o08wq1hhx3eqcsy8a67kwvxk5j9glcmylb5 () {
    super("com.google.javascript.jscomp.GatherSideEffectSubexpressionsCallback.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 70; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 36; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.GatherSideEffectSubexpressionsCallback.java");
      for (int i = 1; i <= 70; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 36; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 18; i++) {
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

