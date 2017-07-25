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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.javascript.jscomp.MakeDeclaredNamesUnique.ContextualRenamer;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Set;

/**
 * Methods necessary for partially or full decomposing an expression.  Initially
 * this is intended to expanded the locations were inlining can occur, but has
 * other uses as well.
 *
 * For example:
 *    var x = y() + z();
 *
 * Becomes:
 *    var a = y();
 *    var b = z();
 *    x = a + b;
 *
 * @author johnlenz@google.com (John Lenz)
 */
class ExpressionDecomposer {
  static {
    CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.ping();
  }


  /**
   * @see #canExposeExpression
   */
  enum DecompositionType {
    UNDECOMPOSABLE,
    MOVABLE,
    DECOMPOSABLE
  }

  private final AbstractCompiler compiler;
  private final Supplier<String> safeNameIdSupplier;
  private final Set<String> knownConstants;

  public ExpressionDecomposer(
      AbstractCompiler compiler,
      Supplier<String> safeNameIdSupplier,
      Set<String> constNames) {
    Preconditions.checkNotNull(compiler);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[1]++;
    Preconditions.checkNotNull(safeNameIdSupplier);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[2]++;
    Preconditions.checkNotNull(constNames);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[3]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[4]++;
    this.safeNameIdSupplier = safeNameIdSupplier;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[5]++;
    this.knownConstants = constNames;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[6]++;
  }

  // An arbitrary limit to prevent catch infinite recursion.
  private static final int MAX_INTERATIONS = 100;
  static {
    CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[7]++;
  }

  /**
   * If required, rewrite the statement containing the expression.
   * @param expression The expression to be exposed.
   * @see #canExposeExpression
   */
  void maybeExposeExpression(Node expression) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[8]++;
    // If the expression needs to exposed.
    int i = 0;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
    while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((DecompositionType.DECOMPOSABLE == canExposeExpression(expression)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[1]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[2]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[3]++;
}
      exposeExpression(expression);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[10]++;
      i++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[11]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i > MAX_INTERATIONS) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[1]++;
        throw new IllegalStateException(
            "DecomposeExpression depth exceeded on :\n" +
            expression.toStringTree());

      } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[2]++;}
    }
  }

  /**
   * Perform any rewriting necessary so that the specified expression
   * is movable. This is a partial expression decomposition.
   * @see #canExposeExpression
   */
  void exposeExpression(Node expression) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[13]++;
    Node expressionRoot = findExpressionRoot(expression);
    Preconditions.checkState(expressionRoot != null);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[14]++;
    exposeExpression(expressionRoot, expression);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[15]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[16]++;
  }

  // TODO(johnlenz): This is not currently used by the function inliner,
  // as moving the call out of the expression before the actual function
  // results in additional variables being introduced.  As the variable
  // inliner is improved, this might be a viable option.
  /**
   * Extract the specified expression from its parent expression.
   * @see #canExposeExpression
   */
  void moveExpression(Node expression) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[17]++;
    String resultName = getResultValueName();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[18]++;
    Node injectionPoint = findInjectionPoint(expression);
    Preconditions.checkNotNull(injectionPoint);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[19]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[20]++;
    Node injectionPointParent = injectionPoint.getParent();
    Preconditions.checkNotNull(injectionPointParent);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[21]++;
    Preconditions.checkState(NodeUtil.isStatementBlock(injectionPointParent));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[22]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[23]++;

    // Replace the expression with a reference to the new name.
    Node expressionParent = expression.getParent();
    expressionParent.replaceChild(
        expression, IR.name(resultName));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[24]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[25]++;

    // Re-add the expression at the appropriate place.
    Node newExpressionRoot = NodeUtil.newVarNode(resultName, expression);
    injectionPointParent.addChildBefore(newExpressionRoot, injectionPoint);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[26]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[27]++;
  }

  /**
   * Rewrite the expression such that the sub-expression is in a movable
   * expression statement while maintaining evaluation order.
   *
   * Two types of subexpressions are extracted from the source expression:
   * 1) subexpressions with side-effects.
   * 2) conditional expressions, that contain the call, which are transformed
   * into IF statements.
   *
   * The following terms are used:
   *    expressionRoot: The top-level node before which the any extracted
   *                    expressions should be placed before.
   *    nonconditionalExpr: The node that will be extracted either expres.
   *
   */
  private void exposeExpression(Node expressionRoot, Node subExpression) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[28]++;
    Node nonconditionalExpr = findNonconditionalParent(
        subExpression, expressionRoot);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[29]++;
    // Before extraction, record whether there are side-effect
    boolean hasFollowingSideEffects = NodeUtil.mayHaveSideEffects(
        nonconditionalExpr, compiler);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[30]++;

    Node exprInjectionPoint = findInjectionPoint(nonconditionalExpr);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[31]++;
    DecompositionState state = new DecompositionState();
    state.sideEffects = hasFollowingSideEffects;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[32]++;
    state.extractBeforeStatement = exprInjectionPoint;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[33]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;

    // Extract expressions in the reverse order of their evaluation.
    for (Node grandchild = null,
            child = nonconditionalExpr,
            parent = child.getParent();(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((parent != expressionRoot) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false);
         grandchild = child,
             child = parent,
             parent = child.getParent()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[4]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[5]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[6]++;
}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[35]++;
      int parentType = parent.getType();
      Preconditions.checkState(
          !isConditionalOp(parent) || child == parent.getFirstChild());
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[36]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[37]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((parentType == Token.ASSIGN) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[3]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[38]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isSafeAssign(parent, state.sideEffects)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[5]++;

            // It is always safe to inline "foo()" for expressions such as
            // "a = b = c = foo();"
            // As the assignment is unaffected by side effect of "foo()"
            // and the names assigned-to can not influence the state before
            // the call to foo.
            //
            // This is not true of more complex LHS values, such as
            // a.x = foo();
            // next().x = foo();
            // in these cases the checks below are necessary.
          } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[6]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[39]++;
            // Alias "next()" in "next().foo"
            Node left = parent.getFirstChild();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[40]++;
            int type = left.getType();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[41]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((left != child) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[7]++;
              Preconditions.checkState(NodeUtil.isGet(left));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[42]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[43]++;
int CodeCoverConditionCoverageHelper_C7;
              if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((type == Token.GETELEM) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[9]++;
                decomposeSubExpressions(left.getLastChild(), null, state);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[44]++;

              } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[10]++;}
              decomposeSubExpressions(left.getFirstChild(), null, state);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[45]++;

            } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[8]++;}
          }

      } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[4]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[46]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((parentType == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(parent.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[11]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[47]++;
        Node functionExpression = parent.getFirstChild();
        decomposeSubExpressions(functionExpression.getNext(), child, state);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[48]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[49]++;
int CodeCoverConditionCoverageHelper_C9;
        // Now handle the call expression
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((isExpressionTreeUnsafe(functionExpression, state.sideEffects)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((functionExpression.getFirstChild() != grandchild) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[13]++;
          // TODO(johnlenz): In Internet Explorer, non-JavaScript objects such
          // as DOM objects can not be decomposed.
          Preconditions.checkState(allowObjectCallDecomposing(),
              "Object method calls can not be decomposed.");
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[50]++;
          // Either there were preexisting side-effects, or this node has
          // side-effects.
          state.sideEffects = true;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[51]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[52]++;

          // Rewrite the call so "this" is preserved.
          Node replacement = rewriteCallExpression(parent, state);
          // Continue from here.
          parent = replacement;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[53]++;

        } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[14]++;}

      } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[12]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[54]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((parentType == Token.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[15]++;
        decomposeObjectLiteralKeys(parent.getFirstChild(), child, state);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[55]++;

      } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[16]++;
        decomposeSubExpressions(parent.getFirstChild(), child, state);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[56]++;
      }
}
}
    }
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[57]++;
int CodeCoverConditionCoverageHelper_C11;

    // Now extract the expression that the decomposition is being performed to
    // to allow to be moved.  All expressions that need to be evaluated before
    // this have been extracted, so add the expression statement after the
    // other extracted expressions and the original statement (or replace
    // the original statement.
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((nonconditionalExpr == subExpression) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[17]++;

      // Don't extract the call, as that introduces an extra constant VAR
      // that will simply need to be inlined back.  It will be handled as
      // an EXPRESSION call site type.
      // Node extractedCall = extractExpression(decomposition, expressionRoot);
    } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[18]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[58]++;
      Node parent = nonconditionalExpr.getParent();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[59]++;
      boolean needResult = !parent.isExprResult();
      extractConditional(nonconditionalExpr, exprInjectionPoint, needResult);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[60]++;
    }
  }

  private static boolean allowObjectCallDecomposing() {
    return false;
  }

  /**
   * @return Whether the node may represent an external method.
   */
  private boolean maybeExternMethod(Node node) {
    // TODO(johnlenz): Provide some mechanism for determining this.
    return true;
  }

  /**
   * @return "expression" or the node closest to "expression", that does not
   * have a conditional ancestor.
   */
  private static Node findNonconditionalParent(
      Node subExpression, Node expressionRoot) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[61]++;
     Node result = subExpression;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[62]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[7]++;


int CodeCoverConditionCoverageHelper_C12;

     for (Node child = subExpression, parent = child.getParent();(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((parent != expressionRoot) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false);
          child = parent, parent = child.getParent()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[7]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[8]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[9]++;
}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[63]++;
int CodeCoverConditionCoverageHelper_C13;
       if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isConditionalOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[19]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[64]++;
int CodeCoverConditionCoverageHelper_C14;
         // Only the first child is always executed, if the function may never
         // be called, don't inline it.
         if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((child != parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[21]++;
           result = parent;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[65]++;

         } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[22]++;}

       } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[20]++;}
     }

     return result;
  }

  /**
   * A simple class to track two things:
   *   - whether side effects have been seen.
   *   - the last statement inserted
   */
  private static class DecompositionState {
    boolean sideEffects;
    Node extractBeforeStatement;
  }

  /**
   * Decompose an object literal.
   * @param key The object literal key.
   * @param stopNode A node after which to stop iterating.
   */
  private void decomposeObjectLiteralKeys(
      Node key, Node stopNode, DecompositionState state) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((key == stopNode) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[23]++;
      return;

    } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[24]++;}
    decomposeObjectLiteralKeys(key.getNext(), stopNode, state);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[67]++;
    decomposeSubExpressions(key.getFirstChild(), stopNode, state);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[68]++;
  }

  /**
   * @param n The node with which to start iterating.
   * @param stopNode A node after which to stop iterating.
   */
  private void decomposeSubExpressions(
      Node n, Node stopNode, DecompositionState state) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[69]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((n == stopNode) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[25]++;
      return;

    } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[26]++;}

    // Never try to decompose an object literal key.
    Preconditions.checkState(!NodeUtil.isObjectLitKey(n));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[70]++;

    // Decompose the children in reverse evaluation order.  This simplifies
    // determining if the any of the children following have side-effects.
    // If they do we need to be more aggressive about removing values
    // from the expression.
    decomposeSubExpressions(
        n.getNext(), stopNode, state);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[71]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[72]++;
int CodeCoverConditionCoverageHelper_C17;

    // Now this node.
    // TODO(johnlenz): Move "safety" code to a shared class.
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((isExpressionTreeUnsafe(n, state.sideEffects)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[27]++;
      // Either there were preexisting side-effects, or this node has
      // side-effects.
      state.sideEffects = true;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[73]++;
      state.extractBeforeStatement = extractExpression(
          n, state.extractBeforeStatement);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[74]++;

    } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[28]++;}
  }

  /**
   *
   * @param expr The conditional expression to extract.
   * @param injectionPoint The before which extracted expression, would be
   *     injected.
   * @param needResult  Whether the result of the expression is required.
   * @return The node that contains the logic of the expression after
   *     extraction.
   */
  private Node extractConditional(
      Node expr, Node injectionPoint, boolean needResult) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[75]++;
    Node parent = expr.getParent();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[76]++;
    String tempName = getTempValueName();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[77]++;

    // Break down the conditional.
    Node first = expr.getFirstChild();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[78]++;
    Node second = first.getNext();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[79]++;
    Node last = expr.getLastChild();

    // Isolate the children nodes.
    expr.detachChildren();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[80]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[81]++;

    // Transform the conditional to an IF statement.
    Node cond = null;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[82]++;
    Node trueExpr = IR.block().srcref(expr);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[83]++;
    Node falseExpr = IR.block().srcref(expr);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[84]++;
    switch (expr.getType()) {
      case Token.HOOK:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[29]++;
        // a = x?y:z --> if (x) {a=y} else {a=z}
        cond = first;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[85]++;
        trueExpr.addChildToFront(NodeUtil.newExpr(
            buildResultExpression(second, needResult, tempName)));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[86]++;
        falseExpr.addChildToFront(NodeUtil.newExpr(
            buildResultExpression(last, needResult, tempName)));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[87]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[88]++;
        break;
      case Token.AND:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[30]++;
        // a = x&&y --> if (a=x) {a=y} else {}
        cond = buildResultExpression(first, needResult, tempName);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[89]++;
        trueExpr.addChildToFront(NodeUtil.newExpr(
            buildResultExpression(last, needResult, tempName)));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[90]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[91]++;
        break;
      case Token.OR:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[31]++;
        // a = x||y --> if (a=x) {} else {a=y}
        cond = buildResultExpression(first, needResult, tempName);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[92]++;
        falseExpr.addChildToFront(NodeUtil.newExpr(
            buildResultExpression(last, needResult, tempName)));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[93]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[94]++;
        break;
      default:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[32]++;
        // With a valid tree we should never get here.
        throw new IllegalStateException("Unexpected.");
    }

    Node ifNode;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[95]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((falseExpr.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[33]++;
      ifNode = IR.ifNode(cond, trueExpr, falseExpr);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[96]++;

    } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[34]++;
      ifNode = IR.ifNode(cond, trueExpr);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[97]++;
    }
    ifNode.copyInformationFrom(expr);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[98]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[99]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((needResult) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[35]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[100]++;
      Node tempVarNode = NodeUtil.newVarNode(tempName, null)
          .copyInformationFromForTree(expr);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[101]++;
      Node injectionPointParent = injectionPoint.getParent();
      injectionPointParent.addChildBefore(tempVarNode, injectionPoint);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[102]++;
      injectionPointParent.addChildAfter(ifNode, tempVarNode);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[103]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[104]++;

      // Replace the expression with the temporary name.
      Node replacementValueNode = IR.name(tempName);
      parent.replaceChild(expr, replacementValueNode);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[105]++;

    } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[36]++;
      // Only conditionals that are the direct child of an expression statement
      // don't need results, for those simply replace the expression statement.
      Preconditions.checkArgument(parent.isExprResult());
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[106]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[107]++;
      Node gramps = parent.getParent();
      gramps.replaceChild(parent, ifNode);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[108]++;
    }

    return ifNode;
  }

  /**
   * Create an expression tree for an expression.
   * If the result of the expression is needed, then:
   *    ASSIGN
   *       tempName
   *       expr
   * otherwise, simply:
   *       expr
   */
  private static Node buildResultExpression(
      Node expr, boolean needResult, String tempName) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[109]++;
int CodeCoverConditionCoverageHelper_C20;
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((needResult) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[37]++;
      return IR.assign(
          IR.name(tempName),
          expr).srcrefTree(expr);

    } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[38]++;
      return expr;
    }
  }

  private boolean isConstantName(Node n, Set<String> knownConstants) {
    // Non-constant names values may have been changed.
    return n.isName() && (NodeUtil.isConstantName(n)
        || knownConstants.contains(n.getString()));
  }

  /**
   * @param expr The expression to extract.
   * @param injectionPoint The node before which to added the extracted
   *     expression.
   * @return The extract statement node.
   */
  private Node extractExpression(Node expr, Node injectionPoint) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[110]++;
    Node parent = expr.getParent();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[111]++;

    boolean isLhsOfAssignOp = NodeUtil.isAssignmentOp(parent)
        && !parent.isAssign()
        && parent.getFirstChild() == expr;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[112]++;

    Node firstExtractedNode = null;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[113]++;
int CodeCoverConditionCoverageHelper_C21;

    // Expressions on the LHS of an assignment-op must have any possible
    // side-effects extracted as the value must be duplicated:
    //    next().foo += 2;
    // becomes:
    //    var t1 = next();
    //    t1.foo = t1.foo + 2;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((isLhsOfAssignOp) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(expr)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[39]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[114]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[10]++;


      for (Node n : expr.children()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[10]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[11]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[12]++;
}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[115]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((n.isString()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((isConstantName(n, knownConstants)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[41]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[116]++;
          Node extractedNode = extractExpression(n, injectionPoint);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[117]++;
int CodeCoverConditionCoverageHelper_C23;
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((firstExtractedNode == null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[43]++;
            firstExtractedNode = extractedNode;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[118]++;

          } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[44]++;}

        } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[42]++;}
      }

    } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[40]++;}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[119]++;

    // The temp is known to be constant.
    String tempName = getTempConstantValueName();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[120]++;
    Node replacementValueNode = IR.name(tempName).srcref(expr);

    Node tempNameValue;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[121]++;
int CodeCoverConditionCoverageHelper_C24;

    // If it is ASSIGN_XXX, keep the assignment in place and extract the
    // original value of the LHS operand.
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((isLhsOfAssignOp) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[45]++;
      Preconditions.checkState(expr.isName() || NodeUtil.isGet(expr));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[122]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[123]++;
      // Transform "x += 2" into "x = temp + 2"
      Node opNode = new Node(NodeUtil.getOpFromAssignmentOp(parent))
          .copyInformationFrom(parent);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[124]++;

      Node rightOperand = parent.getLastChild();

      parent.setType(Token.ASSIGN);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[125]++;
      parent.replaceChild(rightOperand, opNode);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[126]++;
      opNode.addChildToFront(replacementValueNode);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[127]++;
      opNode.addChildToBack(rightOperand);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[128]++;

      // The original expression is still being used, so make a clone.
      tempNameValue = expr.cloneTree();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[129]++;

    } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[46]++;
      // Replace the expression with the temporary name.
      parent.replaceChild(expr, replacementValueNode);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[130]++;

      // Keep the original node so that CALL expressions can still be found
      // and inlined properly.
      tempNameValue = expr;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[131]++;
    }
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[132]++;

    // Re-add the expression in the declaration of the temporary name.
    Node tempVarNode = NodeUtil.newVarNode(tempName, tempNameValue);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[133]++;

    Node injectionPointParent = injectionPoint.getParent();
    injectionPointParent.addChildBefore(tempVarNode, injectionPoint);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[134]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[135]++;
int CodeCoverConditionCoverageHelper_C25;

    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((firstExtractedNode == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[47]++;
      firstExtractedNode = tempVarNode;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[136]++;

    } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[48]++;}
    return firstExtractedNode;
  }

  /**
   * Rewrite the call so "this" is preserved.
   *   a.b(c);
   * becomes:
   *   var temp1 = a;
   *   var temp0 = temp1.b;
   *   temp0.call(temp1,c);
   *
   * @return The replacement node.
   */
  private Node rewriteCallExpression(Node call, DecompositionState state) {
    Preconditions.checkArgument(call.isCall());
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[137]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[138]++;
    Node first = call.getFirstChild();
    Preconditions.checkArgument(NodeUtil.isGet(first));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[139]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[140]++;

    // Extracts the expression representing the function to call. For example:
    //   "a['b'].c" from "a['b'].c()"
    Node getVarNode = extractExpression(
        first, state.extractBeforeStatement);
    state.extractBeforeStatement = getVarNode;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[141]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[142]++;

    // Extracts the object reference to be used as "this". For example:
    //   "a['b']" from "a['b'].c"
    Node getExprNode = getVarNode.getFirstChild().getFirstChild();
    Preconditions.checkArgument(NodeUtil.isGet(getExprNode));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[143]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[144]++;
    Node thisVarNode = extractExpression(
        getExprNode.getFirstChild(), state.extractBeforeStatement);
    state.extractBeforeStatement = thisVarNode;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[145]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[146]++;

    // Rewrite the CALL expression.
    Node thisNameNode = thisVarNode.getFirstChild();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[147]++;
    Node functionNameNode = getVarNode.getFirstChild();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[148]++;

    // CALL
    //   GETPROP
    //     functionName
    //     "call"
    //   thisName
    //   original-parameter1
    //   original-parameter2
    //   ...
    Node newCall = IR.call(
        IR.getprop(
            functionNameNode.cloneNode(),
            IR.string("call")),
        thisNameNode.cloneNode()).srcref(call);

    // Throw away the call name
    call.removeFirstChild();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[149]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[150]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((call.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[49]++;
      // Add the call parameters to the new call.
      newCall.addChildrenToBack(call.removeChildren());
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[151]++;

    } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[50]++;}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[152]++;

    // Replace the call.
    Node callParent = call.getParent();
    callParent.replaceChild(call, newCall);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[153]++;

    return newCall;
  }

  private String tempNamePrefix = "JSCompiler_temp";
  {
    CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[154]++;
  }
  private String resultNamePrefix = "JSCompiler_inline_result";
  {
    CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[155]++;
  }

  /**
   * Allow the temp name to be overridden to make tests more readable.
   */
  @VisibleForTesting
  public void setTempNamePrefix(String prefix) {
    this.tempNamePrefix = prefix;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[156]++;
  }

  /**
   * Create a unique temp name.
   */
  private String getTempValueName(){
    return tempNamePrefix + ContextualRenamer.UNIQUE_ID_SEPARATOR
        + safeNameIdSupplier.get();
  }

  /**
   * Allow the temp name to be overridden to make tests more readable.
   */
  @VisibleForTesting
  public void setResultNamePrefix(String prefix) {
    this.resultNamePrefix = prefix;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[157]++;
  }

  /**
   * Create a unique name for call results.
   */
  private String getResultValueName() {
    return resultNamePrefix
        + ContextualRenamer.UNIQUE_ID_SEPARATOR + safeNameIdSupplier.get();
  }

  /**
   * Create a constant unique temp name.
   */
  private String getTempConstantValueName(){
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[158]++;
    String name = tempNamePrefix + "_const"
        + ContextualRenamer.UNIQUE_ID_SEPARATOR
        + safeNameIdSupplier.get();
    this.knownConstants.add(name);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[159]++;
    return name;
  }

  /**
   * @return For the subExpression, find the nearest statement Node before which
   * it can be inlined.  Null if no such location can be found.
   */
  static Node findInjectionPoint(Node subExpression) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[160]++;
    Node expressionRoot = findExpressionRoot(subExpression);
    Preconditions.checkNotNull(expressionRoot);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[161]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[162]++;

    Node injectionPoint = expressionRoot;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[163]++;

    Node parent = injectionPoint.getParent();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[164]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[13]++;


int CodeCoverConditionCoverageHelper_C27;
    while ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((parent.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[13]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[14]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[15]++;
}
      injectionPoint = parent;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[165]++;
      parent = injectionPoint.getParent();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[166]++;
    }

    Preconditions.checkState(
        NodeUtil.isStatementBlock(injectionPoint.getParent()));
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[167]++;
    return injectionPoint;
  }

  /**
   * @return Whether the node is a conditional op.
   */
  private static boolean isConditionalOp(Node n) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[168]++;
    switch(n.getType()) {
      case Token.HOOK:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[51]++;
      case Token.AND:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[52]++;
      case Token.OR:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[53]++;
        return true;
      default:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[54]++;
        return false;
    }
  }

  /**
   * @return The statement containing the expression. null if subExpression
   *     is not contain by in by a Node where inlining is known to be possible.
   *     For example, a WHILE node condition expression.
   */
  static Node findExpressionRoot(Node subExpression) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[169]++;
    Node child = subExpression;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[170]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[16]++;


    for (Node parent : child.getAncestors()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[16]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[17]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[18]++;
}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[171]++;
      int parentType = parent.getType();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[172]++;
      switch (parentType) {
        // Supported expression roots:
        // SWITCH and IF can have multiple children, but the CASE, DEFAULT,
        // or BLOCK will be encountered first for any of the children other
        // than the condition.
        case Token.EXPR_RESULT:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[55]++;
        case Token.IF:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[56]++;
        case Token.SWITCH:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[57]++;
        case Token.RETURN:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[58]++;
        case Token.VAR:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[59]++;
          Preconditions.checkState(child == parent.getFirstChild());
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[173]++;
          return parent;
        // Any of these indicate an unsupported expression:
        case Token.SCRIPT:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[60]++;
        case Token.BLOCK:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[61]++;
        case Token.LABEL:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[62]++;
        case Token.CASE:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[63]++;
        case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[64]++;
          return null; default : CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[65]++;
      }
      child = parent;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[174]++;
    }

    throw new IllegalStateException("Unexpected AST structure.");
  }

  /**
   * Determine whether a expression is movable, or can be be made movable be
   * decomposing the containing expression.
   *
   * An subExpression is MOVABLE if it can be replaced with a temporary holding
   * its results and moved to immediately before the root of the expression.
   * There are three conditions that must be met for this to occur:
   * 1) There must be a location to inject a statement for the expression.  For
   * example, this condition can not be met if the expression is a loop
   * condition or CASE condition.
   * 2) If the expression can be affect by side-effects, there can not be a
   * side-effect between original location and the expression root.
   * 3) If the expression has side-effects, there can not be any other
   * expression that can be effected between the original location and the
   * expression root.
   *
   * An expression is DECOMPOSABLE if it can be rewritten so that an
   * subExpression is MOVABLE.
   *
   * An expression is decomposed by moving any other sub-expressions that
   * preventing an subExpression from being MOVABLE.
   *
   * @return Whether This is a call that can be moved to an new point in the
   * AST to allow it to be inlined.
   */
  DecompositionType canExposeExpression(Node subExpression) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[175]++;
    Node expressionRoot = findExpressionRoot(subExpression);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[176]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((expressionRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[66]++;
      return isSubexpressionMovable(expressionRoot, subExpression);

    } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[67]++;}
    return DecompositionType.UNDECOMPOSABLE;
  }

  /**
   * Walk the AST from the call site to the expression root and verify that
   * the portions of the expression that are evaluated before the call are:
   * 1) Unaffected by the the side-effects, if any, of the call.
   * 2) That there are no side-effects, that may influence the call.
   *
   * For example, if x has side-effects:
   *   a = 1 + x();
   * the call to x can be moved because "a" final value of a can not be
   * influenced by x(), but in:
   *   a = b + x();
   * the call to x can not be moved because the value of b may be modified
   * by the call to x.
   *
   * If x is without side-effects in:
   *   a = b + x();
   * the call to x can be moved, but in:
   *   a = (b.foo = c) + x();
   * the call to x can not be moved because the value of b.foo may be referenced
   * by x().  Note: this is true even if b is a local variable; the object that
   * b refers to may have a global alias.
   *
   * @return UNDECOMPOSABLE if the expression can not be moved, DECOMPOSABLE if
   * decomposition is required before the expression can be moved, otherwise
   * MOVABLE.
   */
  private DecompositionType isSubexpressionMovable(
      Node expressionRoot, Node subExpression) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[177]++;
    boolean requiresDecomposition = false;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[178]++;
    boolean seenSideEffects = NodeUtil.mayHaveSideEffects(
        subExpression, compiler);
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[179]++;

    Node child = subExpression;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[180]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[19]++;


    for (Node parent : child.getAncestors()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[19]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[20]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[21]++;
}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[181]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((parent == expressionRoot) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[68]++;
        // Done. The walk back to the root of the expression is complete, and
        // nothing was encountered that blocks the call from being moved.
        return requiresDecomposition
            ? DecompositionType.DECOMPOSABLE
            : DecompositionType.MOVABLE;

      } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[69]++;}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[182]++;
int CodeCoverConditionCoverageHelper_C30;

      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((isConditionalOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[70]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[183]++;
int CodeCoverConditionCoverageHelper_C31;
        // Only the first child is always executed, otherwise it must be
        // decomposed.
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((child != parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[72]++;
          requiresDecomposition = true;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[184]++;

        } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[73]++;}

      } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[71]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[185]++;
int CodeCoverConditionCoverageHelper_C32;
        // Only inline the call if none of the preceding siblings in the
        // expression have side-effects, and are unaffected by the side-effects,
        // if any, of the call in question.
        // NOTE: This depends on the siblings being in the same order as they
        // are evaluated.

        // SPECIAL CASE: Assignment to a simple name
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isSafeAssign(parent, seenSideEffects)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[74]++;

          // It is always safe to inline "foo()" for expressions such as
          //   "a = b = c = foo();"
          // As the assignment is unaffected by side effect of "foo()"
          // and the names assigned-to can not influence the state before
          // the call to foo.
          //
          // This is not true of more complex LHS values, such as
          //    a.x = foo();
          //    next().x = foo();
          // in these cases the checks below are necessary.
        } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[75]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[186]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[22]++;


          // Everything else.
          for (Node n : parent.children()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[22]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[23]--;
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.loops[24]++;
}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[187]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((n == child) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[76]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[188]++;
              // None of the preceding siblings have side-effects.
              // This is OK.
              break;

            } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[77]++;}
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[189]++;
int CodeCoverConditionCoverageHelper_C34;

            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((isExpressionTreeUnsafe(
                n, seenSideEffects)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[78]++;
              seenSideEffects = true;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[190]++;
              requiresDecomposition = true;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[191]++;

            } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[79]++;}
          }
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[192]++;

          // In Internet Explorer, DOM objects and other external objects
          // methods can not be called indirectly, as is required when the
          // object or its property can be side-effected.  For example,
          // when exposing expression f() (with side-effects) in: x.m(f())
          // either the value of x or its property m might have changed, so
          // both the 'this' value ('x') and the function to be called ('x.m')
          // need to be preserved. Like so:
          //   var t1 = x, t2 = x.m, t3 = f();
          //   t2.call(t1, t3);
          // As IE doesn't support the call to these non-JavaScript objects
          // methods in this way. We can't do this.
          // We don't currently distinguish between these types of objects
          // in the extern definitions and if we did we would need accurate
          // type information.
          //
          Node first = parent.getFirstChild();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[193]++;
int CodeCoverConditionCoverageHelper_C35;
          if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (32)) == 0 || true) &&
 ((requiresDecomposition) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((parent.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(first)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 3) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 3) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[80]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[194]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((maybeExternMethod(first)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[82]++;
              return DecompositionType.UNDECOMPOSABLE;

            } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[83]++;
              return DecompositionType.DECOMPOSABLE;
            }

          } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[81]++;}
        }
      }
      // Continue looking up the expression tree.
      child = parent;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[195]++;
    }

    // With a valid tree we should never get here.
    throw new IllegalStateException("Unexpected.");
  }

  /**
   * It is always safe to inline "foo()" for expressions such as
   *    "a = b = c = foo();"
   * As the assignment is unaffected by side effect of "foo()"
   * and the names assigned-to can not influence the state before
   * the call to foo.
   *
   * It is also safe in cases like where the object is constant:
   *    CONST_NAME.a = foo()
   *    CONST_NAME[CONST_VALUE] = foo();
   *
   * This is not true of more complex LHS values, such as
   *     a.x = foo();
   *     next().x = foo();
   * in these cases the checks below are necessary.
   *
   * @param seenSideEffects If true, check to see if node-tree maybe affected by
   * side-effects, otherwise if the tree has side-effects. @see
   * isExpressionTreeUnsafe
   * @return Whether the assignment is safe from side-effects.
   */
  private boolean isSafeAssign(Node n, boolean seenSideEffects) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[196]++;
int CodeCoverConditionCoverageHelper_C37;
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[84]++;
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[197]++;
      Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[198]++;
      switch (lhs.getType()) {
        case Token.NAME:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[86]++;
          return true;
        case Token.GETPROP:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[87]++;
          return !isExpressionTreeUnsafe(lhs.getFirstChild(), seenSideEffects);
        case Token.GETELEM:
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[88]++;
          return !isExpressionTreeUnsafe(lhs.getFirstChild(), seenSideEffects)
              && !isExpressionTreeUnsafe(lhs.getLastChild(), seenSideEffects); default : CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[89]++;
      }

    } else {
  CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[85]++;}
    return false;
  }

  /**
   * @return Whether anything in the expression tree prevents a call from
   * being moved.
   */
  private boolean isExpressionTreeUnsafe(
      Node n, boolean followingSideEffectsExist) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.statements[199]++;
int CodeCoverConditionCoverageHelper_C38;
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((followingSideEffectsExist) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[90]++;
      // If the call to be inlined has side-effects, check to see if this
      // expression tree can be affected by any side-effects.

      // This is a superset of "NodeUtil.mayHaveSideEffects".
      return NodeUtil.canBeSideEffected(n, this.knownConstants);

    } else {
CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl.branches[91]++;
      // The function called doesn't have side-effects but check to see if there
      // are side-effects that that may affect it.
      return NodeUtil.mayHaveSideEffects(n, compiler);
    }
  }
}

class CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl ());
  }
    public static long[] statements = new long[200];
    public static long[] branches = new long[92];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[39];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ExpressionDecomposer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1};
    for (int i = 1; i <= 38; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$35ux379kbd0vpxgsp25yhjqs28q1rxxfsgscsbl () {
    super("com.google.javascript.jscomp.ExpressionDecomposer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 199; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 91; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ExpressionDecomposer.java");
      for (int i = 1; i <= 199; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 91; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 38; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

