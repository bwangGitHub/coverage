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
import com.google.common.base.Supplier;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ExpressionDecomposer.DecompositionType;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A set of utility functions that replaces CALL with a specified
 * FUNCTION body, replacing and aliasing function parameters as
 * necessary.
 *
 * @author johnlenz@google.com (John Lenz)
 */
class FunctionInjector {
  static {
    CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.ping();
  }


  private final AbstractCompiler compiler;
  private final Supplier<String> safeNameIdSupplier;
  private final boolean allowDecomposition;
  private Set<String> knownConstants = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[1]++;
  }
  private final boolean assumeStrictThis;
  private final boolean assumeMinimumCapture;

  /**
   * @param allowDecomposition Whether an effort should be made to break down
   * expressions into simpler expressions to allow functions to be injected
   * where they would otherwise be disallowed.
   */
  public FunctionInjector(
      AbstractCompiler compiler,
      Supplier<String> safeNameIdSupplier,
      boolean allowDecomposition,
      boolean assumeStrictThis,
      boolean assumeMinimumCapture) {
    Preconditions.checkNotNull(compiler);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[2]++;
    Preconditions.checkNotNull(safeNameIdSupplier);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[3]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[4]++;
    this.safeNameIdSupplier = safeNameIdSupplier;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[5]++;
    this.allowDecomposition = allowDecomposition;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[6]++;
    this.assumeStrictThis = assumeStrictThis;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[7]++;
    this.assumeMinimumCapture = assumeMinimumCapture;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[8]++;
  }

  /** The type of inlining to perform. */
  enum InliningMode {
    /**
     * Directly replace the call expression. Only functions of meeting
     * strict preconditions can be inlined.
     */
    DIRECT,

    /**
     * Replaces the call expression with a block of statements. Conditions
     * on the function are looser in mode, but stricter on the call site.
     */
    BLOCK
  }

  /** Holds a reference to the call node of a function call */
  static class Reference {
    final Node callNode;
    final JSModule module;
    final InliningMode mode;

    Reference(Node callNode, JSModule module, InliningMode mode){
      this.callNode = callNode;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[9]++;
      this.module = module;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[10]++;
      this.mode = mode;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[11]++;
    }
  }

  /**
   * In order to estimate the cost of lining, we make the assumption that
   * Identifiers are reduced 2 characters. For the call arguments, the important
   * thing is that the cost is assumed to be the same in the call and the
   * function, so the actual length doesn't matter in most cases.
   */
  private static final int NAME_COST_ESTIMATE =
      InlineCostEstimator.ESTIMATED_IDENTIFIER_COST;
  static {
    CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[12]++;
  }

  /** The cost of a argument separator (a comma). */
  private static final int COMMA_COST = 1;
  static {
    CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[13]++;
  }

  /** The cost of the parentheses needed to make a call.*/
  private static final int PAREN_COST = 2;
  static {
    CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[14]++;
  }


  /**
   * @param fnName The name of this function. This either the name of the
   *  variable to which the function is assigned or the name from the FUNCTION
   *  node.
   * @param fnNode The FUNCTION node of the function to inspect.
   * @return Whether the function node meets the minimum requirements for
   * inlining.
   */
  boolean doesFunctionMeetMinimumRequirements(
      final String fnName, Node fnNode) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[15]++;
    Node block = NodeUtil.getFunctionBody(fnNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;

    // Basic restrictions on functions that can be inlined:
    // 0) The function is inlinable by convention
    // 1) It contains a reference to itself.
    // 2) It uses its parameters indirectly using "arguments" (it isn't
    //    handled yet.
    // 3) It references "eval". Inline a function containing eval can have
    //    large performance implications.

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isInlinableFunction(fnNode)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[1]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[2]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[17]++;

    final String fnRecursionName = fnNode.getFirstChild().getString();
    Preconditions.checkState(fnRecursionName != null);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[18]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[19]++;

    // If the function references "arguments" directly in the function
    boolean referencesArguments = NodeUtil.isNameReferenced(
        block, "arguments", NodeUtil.MATCH_NOT_FUNCTION);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[20]++;

    // or it references "eval" or one of its names anywhere.
    Predicate<Node> p = new Predicate<Node>(){
      @Override
      public boolean apply(Node n) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[3]++;
          return n.getString().equals("eval")
            || (!fnName.isEmpty()
                && n.getString().equals(fnName))
            || (!fnRecursionName.isEmpty()
                && n.getString().equals(fnRecursionName));

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[4]++;}
        return false;
      }
    };

    return !referencesArguments
        && !NodeUtil.has(block, p, Predicates.<Node>alwaysTrue());
  }

  /**
   * @param t  The traversal use to reach the call site.
   * @param callNode The CALL node.
   * @param fnNode The function to evaluate for inlining.
   * @param needAliases A set of function parameter names that can not be
   *     used without aliasing. Returned by getUnsafeParameterNames().
   * @param mode Inlining mode to be used.
   * @param referencesThis Whether fnNode contains references to its this
   *     object.
   * @param containsFunctions Whether fnNode contains inner functions.
   * @return Whether the inlining can occur.
   */
  CanInlineResult canInlineReferenceToFunction(NodeTraversal t,
      Node callNode, Node fnNode, Set<String> needAliases,
      InliningMode mode, boolean referencesThis, boolean containsFunctions) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
    // TODO(johnlenz): This function takes too many parameter, without
    // context.  Modify the API to take a structure describing the function.

    // Allow direct function calls or "fn.call" style calls.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isSupportedCallType(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[5]++;
      return CanInlineResult.NO;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[6]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;

    // Limit where functions that contain functions can be inline.  Introducing
    // an inner function into another function can capture a variable and cause
    // a memory leak.  This isn't a problem in the global scope as those values
    // last until explicitly cleared.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((containsFunctions) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[7]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((assumeMinimumCapture) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[9]++;
        // TODO(johnlenz): Allow inlining into any scope without local names or
        // inner functions.
        return CanInlineResult.NO;

      } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[10]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[25]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isWithinLoop(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[11]++;
        // An inner closure maybe relying on a local value holding a value for a
        // single iteration through a loop.
        return CanInlineResult.NO;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[12]++;}
}

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[8]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[26]++;
int CodeCoverConditionCoverageHelper_C7;

    // TODO(johnlenz): Add support for 'apply'
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((referencesThis) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[13]++;
      // TODO(johnlenz): Allow 'this' references to be replaced with a
      // global 'this' object.
      return CanInlineResult.NO;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[14]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;

    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((mode == InliningMode.DIRECT) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[15]++;
      return canInlineReferenceDirectly(callNode, fnNode);

    } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[16]++;
      return canInlineReferenceAsStatementBlock(
          t, callNode, fnNode, needAliases);
    }
  }

  /**
   * Only ".call" calls and direct calls to functions are supported.
   * @param callNode The call evaluate.
   * @return Whether the call is of a type that is supported.
   */
  private boolean isSupportedCallType(Node callNode) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((callNode.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[17]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[19]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[30]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((assumeStrictThis) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[21]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[31]++;
          Node thisValue = callNode.getFirstChild().getNext();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[32]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((thisValue == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((thisValue.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[23]++;
            return false;

          } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[24]++;}

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[22]++;}

      } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[20]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[33]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectApply(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[25]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[26]++;}
}

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[18]++;}

    return true;
  }

  /**
   * Inline a function into the call site.
   */
  Node inline(
      Node callNode, String fnName, Node fnNode, InliningMode mode) {
    Preconditions.checkState(compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[34]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[35]++;
int CodeCoverConditionCoverageHelper_C14;

    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((mode == InliningMode.DIRECT) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[27]++;
      return inlineReturnValue(callNode, fnNode);

    } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[28]++;
      return inlineFunction(callNode, fnNode, fnName);
    }
  }

  /**
   * Inline a function that fulfills the requirements of
   * canInlineReferenceDirectly into the call site, replacing only the CALL
   * node.
   */
  private Node inlineReturnValue(Node callNode, Node fnNode) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[36]++;
    Node block = fnNode.getLastChild();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[37]++;
    Node callParentNode = callNode.getParent();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[38]++;

    // NOTE: As the normalize pass guarantees globals aren't being
    // shadowed and an expression can't introduce new names, there is
    // no need to check for conflicts.

    // Create an argName -> expression map, checking for side effects.
    Map<String, Node> argMap =
        FunctionArgumentInjector.getFunctionCallParameterMap(
            fnNode, callNode, this.safeNameIdSupplier);

    Node newExpression;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[39]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((block.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[29]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[40]++;
      Node srcLocation = block;
      newExpression = NodeUtil.newUndefinedNode(srcLocation);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[41]++;

    } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[30]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[42]++;
      Node returnNode = block.getFirstChild();
      Preconditions.checkArgument(returnNode.isReturn());
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[43]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[44]++;

      // Clone the return node first.
      Node safeReturnNode = returnNode.cloneTree();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[45]++;
      Node inlineResult = FunctionArgumentInjector.inject(
          null, safeReturnNode, null, argMap);
      Preconditions.checkArgument(safeReturnNode == inlineResult);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[46]++;
      newExpression = safeReturnNode.removeFirstChild();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[47]++;
    }

    callParentNode.replaceChild(callNode, newExpression);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[48]++;
    return newExpression;
  }

  /**
   * Supported call site types.
   */
  private enum CallSiteType {

    /**
     * Used for a call site for which there does not exist a method
     * to inline it.
     */
    UNSUPPORTED() {
      @Override
      public void prepare(FunctionInjector injector, Node callNode) {
        throw new IllegalStateException("unexpected");
      }
    },

    /**
     * A call as a statement. For example: "foo();".
     *   EXPR_RESULT
     *     CALL
     */
    SIMPLE_CALL() {
      @Override
      public void prepare(FunctionInjector injector, Node callNode) {
        // Nothing to do.
      }
    },

    /**
     * An assignment, where the result of the call is assigned to a simple
     * name. For example: "a = foo();".
     *   EXPR_RESULT
     *     NAME A
     *     CALL
     *       FOO
     */
    SIMPLE_ASSIGNMENT() {
      @Override
      public void prepare(FunctionInjector injector, Node callNode) {
        // Nothing to do.
      }
    },
    /**
     * An var declaration and initialization, where the result of the call is
     * assigned to the declared name
     * name. For example: "a = foo();".
     *   VAR
     *     NAME A
     *       CALL
     *         FOO
     */
    VAR_DECL_SIMPLE_ASSIGNMENT() {
      @Override
      public void prepare(FunctionInjector injector, Node callNode) {
        // Nothing to do.
      }
    },
    /**
     * An arbitrary expression, the root of which is a EXPR_RESULT, IF,
     * RETURN, SWITCH or VAR.  The call must be the first side-effect in
     * the expression.
     *
     * Examples include:
     *   "if (foo()) {..."
     *   "return foo();"
     *   "var a = 1 + foo();"
     *   "a = 1 + foo()"
     *   "foo() ? 1:0"
     *   "foo() && x"
     */
    EXPRESSION() {
      @Override
      public void prepare(FunctionInjector injector, Node callNode) {
        injector.getDecomposer().moveExpression(callNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[49]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[50]++;

        // Reclassify after move
        CallSiteType callSiteType = injector.classifyCallSite(callNode);
        Preconditions.checkState(this != callSiteType);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[51]++;
        callSiteType.prepare(injector, callNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[52]++;
      }
    },

    /**
     * An arbitrary expression, the root of which is a EXPR_RESULT, IF,
     * RETURN, SWITCH or VAR.  Where the call is not the first side-effect in
     * the expression.
     */
    DECOMPOSABLE_EXPRESSION() {
      @Override
      public void prepare(FunctionInjector injector, Node callNode) {
        injector.getDecomposer().maybeExposeExpression(callNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[53]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[54]++;

        // Reclassify after decomposition
        CallSiteType callSiteType = injector.classifyCallSite(callNode);
        Preconditions.checkState(this != callSiteType);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[55]++;
        callSiteType.prepare(injector, callNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[56]++;
      }
    };

    public abstract void prepare(FunctionInjector injector, Node callNode);
  }

  /**
   * Determine which, if any, of the supported types the call site is.
   */
  private CallSiteType classifyCallSite(Node callNode) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[57]++;
    Node parent = callNode.getParent();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[58]++;
    Node grandParent = parent.getParent();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[59]++;
int CodeCoverConditionCoverageHelper_C16;

    // Verify the call site:
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprCall(parent)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[31]++;
      // This is a simple call?  Example: "foo();".
      return CallSiteType.SIMPLE_CALL;

    } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[32]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[60]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (128)) == 0 || true) &&
 ((NodeUtil.isExprAssign(grandParent)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((NodeUtil.isVarOrSimpleAssignLhs(callNode, parent)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((parent.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((NodeUtil.isConstantName(parent.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 4) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 4) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[33]++;
      // This is a simple assignment.  Example: "x = foo();"
      return CallSiteType.SIMPLE_ASSIGNMENT;

    } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[34]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[61]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (128)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((NodeUtil.isConstantName(parent)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((grandParent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((grandParent.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[35]++;
      // This is a var declaration.  Example: "var x = foo();"
      // TODO(johnlenz): Should we be checking for constants on the
      // left-hand-side of the assignments and handling them as EXPRESSION?
      return CallSiteType.VAR_DECL_SIMPLE_ASSIGNMENT;

    } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[36]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[62]++;
      Node expressionRoot = ExpressionDecomposer.findExpressionRoot(callNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[63]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((expressionRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[37]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[64]++;
        ExpressionDecomposer decomposer = new ExpressionDecomposer(
            compiler, safeNameIdSupplier, knownConstants);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[65]++;
        DecompositionType type = decomposer.canExposeExpression(
            callNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[66]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((type == DecompositionType.MOVABLE) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[39]++;
          return CallSiteType.EXPRESSION;

        } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[40]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[67]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((type == DecompositionType.DECOMPOSABLE) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[41]++;
          return CallSiteType.DECOMPOSABLE_EXPRESSION;

        } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[42]++;
          Preconditions.checkState(type == DecompositionType.UNDECOMPOSABLE);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[68]++;
        }
}

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[38]++;}
    }
}
}

    return CallSiteType.UNSUPPORTED;
  }

  private ExpressionDecomposer getDecomposer() {
    return new ExpressionDecomposer(
        compiler, safeNameIdSupplier, knownConstants);
  }

  /**
   * If required, rewrite the statement containing the call expression.
   * @see ExpressionDecomposer#canExposeExpression
   */
  void maybePrepareCall(Node callNode) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[69]++;
    CallSiteType callSiteType = classifyCallSite(callNode);
    callSiteType.prepare(this, callNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[70]++;
  }

  /**
   * Inline a function which fulfills the requirements of
   * canInlineReferenceAsStatementBlock into the call site, replacing the
   * parent expression.
   */
  private Node inlineFunction(
      Node callNode, Node fnNode, String fnName) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[71]++;
    Node parent = callNode.getParent();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[72]++;
    Node grandParent = parent.getParent();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[73]++;

    // TODO(johnlenz): Consider storing the callSite classification in the
    // reference object and passing it in here.
    CallSiteType callSiteType = classifyCallSite(callNode);
    Preconditions.checkArgument(callSiteType != CallSiteType.UNSUPPORTED);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[74]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[75]++;

    boolean isCallInLoop = NodeUtil.isWithinLoop(callNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[76]++;

    // Store the name for the result. This will be used to
    // replace "return expr" statements with "resultName = expr"
    // to replace
    String resultName = null;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[77]++;
    boolean needsDefaultReturnResult = true;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[78]++;
    switch (callSiteType) {
      case SIMPLE_ASSIGNMENT:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[43]++;
        resultName = parent.getFirstChild().getString();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[79]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[80]++;
        break;

      case VAR_DECL_SIMPLE_ASSIGNMENT:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[44]++;
        resultName = parent.getString();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[81]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[82]++;
        break;

      case SIMPLE_CALL:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[45]++;
        resultName = null;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[83]++;  // "foo()" doesn't need a result.
        needsDefaultReturnResult = false;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[84]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[85]++;
        break;

      case EXPRESSION:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[46]++;
        throw new IllegalStateException(
            "Movable expressions must be moved before inlining.");

      case DECOMPOSABLE_EXPRESSION:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[47]++;
        throw new IllegalStateException(
            "Decomposable expressions must be decomposed before inlining.");

      default:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[48]++;
        throw new IllegalStateException("Unexpected call site type.");
    }
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[86]++;

    FunctionToBlockMutator mutator = new FunctionToBlockMutator(
        compiler, this.safeNameIdSupplier);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[87]++;

    Node newBlock = mutator.mutate(
        fnName, fnNode, callNode, resultName,
        needsDefaultReturnResult, isCallInLoop);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[88]++;

    // TODO(nicksantos): Create a common mutation function that
    // can replace either a VAR name assignment, assignment expression or
    // a EXPR_RESULT.
    Node greatGrandParent = grandParent.getParent();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[89]++;
    switch (callSiteType) {
      case VAR_DECL_SIMPLE_ASSIGNMENT:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[49]++;
        // Remove the call from the name node.
        parent.removeChild(parent.getFirstChild());
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[90]++;
        Preconditions.checkState(parent.getFirstChild() == null);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[91]++;
        // Add the call, after the VAR.
        greatGrandParent.addChildAfter(newBlock, grandParent);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[92]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[93]++;
        break;

      case SIMPLE_ASSIGNMENT:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[50]++;
        // The assignment is now part of the inline function so
        // replace it completely.
        Preconditions.checkState(grandParent.isExprResult());
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[94]++;
        greatGrandParent.replaceChild(grandParent, newBlock);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[95]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[96]++;
        break;

      case SIMPLE_CALL:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[51]++;
        // If nothing is looking at the result just replace the call.
        Preconditions.checkState(parent.isExprResult());
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[97]++;
        grandParent.replaceChild(parent, newBlock);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[98]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[99]++;
        break;

      default:
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[52]++;
        throw new IllegalStateException("Unexpected call site type.");
    }

    return newBlock;
  }

  /**
   * Checks if the given function matches the criteria for an inlinable
   * function, and if so, adds it to our set of inlinable functions.
   */
  boolean isDirectCallNodeReplacementPossible(Node fnNode) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[100]++;
    // Only inline single-statement functions
    Node block = NodeUtil.getFunctionBody(fnNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[101]++;
int CodeCoverConditionCoverageHelper_C22;

    // Check if this function is suitable for direct replacement of a CALL node:
    // a function that consists of single return that returns an expression.
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((block.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[53]++;
      // special case empty functions.
      return true;

    } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[54]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[102]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((block.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[55]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[103]++;
int CodeCoverConditionCoverageHelper_C24;
      // Only inline functions that return something.
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((block.getFirstChild().isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((block.getFirstChild().getFirstChild() != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[57]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[58]++;}

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[56]++;}
}

    return false;
  }

  enum CanInlineResult {
    YES,
    AFTER_PREPARATION,
    NO
  }

  /**
   * Determines whether a function can be inlined at a particular call site.
   * There are several criteria that the function and reference must hold in
   * order for the functions to be inlined:
   * - It must be a simple call, or assignment, or var initialization.
   * <pre>
   *    f();
   *    a = foo();
   *    var a = foo();
   * </pre>
   */
  private CanInlineResult canInlineReferenceAsStatementBlock(
      NodeTraversal t, Node callNode, Node fnNode, Set<String> namesToAlias) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[104]++;
    CallSiteType callSiteType = classifyCallSite(callNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[105]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((callSiteType == CallSiteType.UNSUPPORTED) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[59]++;
      return CanInlineResult.NO;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[60]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[106]++;
int CodeCoverConditionCoverageHelper_C26;

    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (32)) == 0 || true) &&
 ((allowDecomposition) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((callSiteType == CallSiteType.DECOMPOSABLE_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((callSiteType == CallSiteType.EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[61]++;
      return CanInlineResult.NO;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[62]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[107]++;
int CodeCoverConditionCoverageHelper_C27;

    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((callMeetsBlockInliningRequirements(
            t, callNode, fnNode, namesToAlias)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[63]++;
      return CanInlineResult.NO;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[64]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[108]++;
int CodeCoverConditionCoverageHelper_C28;

    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((callSiteType == CallSiteType.DECOMPOSABLE_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((callSiteType == CallSiteType.EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[65]++;
      return CanInlineResult.AFTER_PREPARATION;

    } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[66]++;
      return CanInlineResult.YES;
    }
  }

  /**
   * Determines whether a function can be inlined at a particular call site.
   * - Don't inline if the calling function contains an inner function and
   * inlining would introduce new globals.
   */
  private boolean callMeetsBlockInliningRequirements(
      NodeTraversal t, Node callNode, final Node fnNode,
      Set<String> namesToAlias) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[109]++;
    final boolean assumeMinimumCapture = this.assumeMinimumCapture;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[110]++;

    // Note: functions that contain function definitions are filtered out
    // in isCandidateFunction.

    // TODO(johnlenz): Determining if the called function contains VARs
    // or if the caller contains inner functions accounts for 20% of the
    // run-time cost of this pass.

    // Don't inline functions with var declarations into a scope with inner
    // functions as the new vars would leak into the inner function and
    // cause memory leaks.
    boolean fnContainsVars = NodeUtil.has(
        NodeUtil.getFunctionBody(fnNode),
        new NodeUtil.MatchDeclaration(),
        new NodeUtil.MatchShallowStatement());
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[111]++;
    boolean forbidTemps = false;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[112]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[67]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[113]++;
      Node fnCaller = t.getScopeRoot();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[114]++;
      Node fnCallerBody = fnCaller.getLastChild();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[115]++;

      // Don't allow any new vars into a scope that contains eval or one
      // that contains functions (excluding the function being inlined).
      Predicate<Node> match = new Predicate<Node>(){
        @Override
        public boolean apply(Node n) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[116]++;
int CodeCoverConditionCoverageHelper_C30;
          if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[69]++;
            return n.getString().equals("eval");

          } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[70]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[117]++;
int CodeCoverConditionCoverageHelper_C31;
          if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((assumeMinimumCapture) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[71]++;
            return n != fnNode;

          } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[72]++;}
          return false;
        }
      };
      forbidTemps = NodeUtil.has(fnCallerBody,
          match, NodeUtil.MATCH_NOT_FUNCTION);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[118]++;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[68]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[119]++;
int CodeCoverConditionCoverageHelper_C32;

    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((fnContainsVars) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((forbidTemps) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[73]++;
      return false;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[74]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[120]++;
int CodeCoverConditionCoverageHelper_C33;

    // If the caller contains functions or evals, verify we aren't adding any
    // additional VAR declarations because aliasing is needed.
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((forbidTemps) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[75]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[121]++;
      Map<String, Node> args =
          FunctionArgumentInjector.getFunctionCallParameterMap(
              fnNode, callNode, this.safeNameIdSupplier);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[122]++;
      boolean hasArgs = !args.isEmpty();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[123]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((hasArgs) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[77]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[124]++;
        // Limit the inlining
        Set<String> allNamesToAlias = Sets.newHashSet(namesToAlias);
        FunctionArgumentInjector.maybeAddTempsForCallArguments(
            fnNode, args, allNamesToAlias, compiler.getCodingConvention());
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[125]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[126]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((allNamesToAlias.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[79]++;
          return false;

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[80]++;}

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[78]++;}

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[76]++;}

    return true;
  }

  /**
   * Determines whether a function can be inlined at a particular call site.
   * There are several criteria that the function and reference must hold in
   * order for the functions to be inlined:
   * 1) If a call's arguments have side effects,
   * the corresponding argument in the function must only be referenced once.
   * For instance, this will not be inlined:
   * <pre>
   *     function foo(a) { return a + a }
   *     x = foo(i++);
   * </pre>
   */
  private CanInlineResult canInlineReferenceDirectly(
      Node callNode, Node fnNode) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[127]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((isDirectCallNodeReplacementPossible(fnNode)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[81]++;
      return CanInlineResult.NO;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[82]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[128]++;

    Node block = fnNode.getLastChild();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[129]++;

    // CALL NODE: [ NAME, ARG1, ARG2, ... ]
    Node cArg = callNode.getFirstChild().getNext();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[130]++;
int CodeCoverConditionCoverageHelper_C37;

    // Functions called via 'call' and 'apply' have a this-object as
    // the first parameter, but this is not part of the called function's
    // parameter list.
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((callNode.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[83]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[131]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[85]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[132]++;
int CodeCoverConditionCoverageHelper_C39;
        // TODO(johnlenz): Support replace this with a value.
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((cArg == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((cArg.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[87]++;
          return CanInlineResult.NO;

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[88]++;}
        cArg = cArg.getNext();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[133]++;

      } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[86]++;
        // ".apply" call should be filtered before this.
        Preconditions.checkState(!NodeUtil.isFunctionObjectApply(callNode));
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[134]++;
      }

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[84]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[135]++;

    // FUNCTION NODE -> LP NODE: [ ARG1, ARG2, ... ]
    Node fnParam = NodeUtil.getFunctionParameters(fnNode).getFirstChild();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[136]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[1]++;


int CodeCoverConditionCoverageHelper_C40;
    while ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((cArg != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((fnParam != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[1]--;
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[2]--;
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[3]++;
}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[137]++;
int CodeCoverConditionCoverageHelper_C41;
      // For each named parameter check if a mutable argument use more than one.
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((fnParam != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[89]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[138]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((cArg != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[91]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[139]++;
int CodeCoverConditionCoverageHelper_C43;
          // Check for arguments that are evaluated more than once.
          // Note: Unlike block inlining, there it is not possible that a
          // parameter reference will be in a loop.
          if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((NodeUtil.mayEffectMutableState(cArg, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((NodeUtil.getNameReferenceCount(
                  block, fnParam.getString()) > 1) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[93]++;
            return CanInlineResult.NO;

          } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[94]++;}

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[92]++;}

        // Move to the next name.
        fnParam = fnParam.getNext();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[140]++;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[90]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[141]++;
int CodeCoverConditionCoverageHelper_C44;

      // For every call argument check for side-effects, even if there
      // isn't a named parameter to match.
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((cArg != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[95]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[142]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(cArg, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[97]++;
          return CanInlineResult.NO;

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[98]++;}
        cArg = cArg.getNext();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[143]++;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[96]++;}
    }

    return CanInlineResult.YES;
  }

  /**
   * Determine if inlining the function is likely to reduce the code size.
   * @param namesToAlias
   */
  boolean inliningLowersCost(
      JSModule fnModule, Node fnNode, Collection<? extends Reference> refs,
      Set<String> namesToAlias, boolean isRemovable, boolean referencesThis) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[144]++;
    int referenceCount = refs.size();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[145]++;
int CodeCoverConditionCoverageHelper_C46;
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((referenceCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[99]++;
      return true;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[100]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[146]++;

    int referencesUsingBlockInlining = 0;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[147]++;

    boolean checkModules = isRemovable && fnModule != null;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[148]++;
    JSModuleGraph moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[149]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[4]++;



    for (Reference ref : refs) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[4]--;
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[5]--;
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.loops[6]++;
}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[150]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((ref.mode == InliningMode.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[101]++;
        referencesUsingBlockInlining++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[151]++;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[102]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[152]++;
int CodeCoverConditionCoverageHelper_C48;

      // Check if any of the references cross the module boundaries.
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((checkModules) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((ref.module != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[103]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[153]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((ref.module != fnModule) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(ref.module, fnModule)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[105]++;
          // Calculate the cost as if the function were non-removable,
          // if it still lowers the cost inline it.
          isRemovable = false;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[154]++;
          checkModules = false;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[155]++;
  // no need to check additional modules.
        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[106]++;}

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[104]++;}
    }
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[156]++;

    int referencesUsingDirectInlining = referenceCount -
        referencesUsingBlockInlining;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[157]++;
int CodeCoverConditionCoverageHelper_C50;

    // Don't bother calculating the cost of function for simple functions where
    // possible.
    // However, when inlining a complex function, even a single reference may be
    // larger than the original function if there are many returns (resulting
    // in additional assignments) or many parameters that need to be aliased
    // so use the cost estimating.
    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (32)) == 0 || true) &&
 ((referenceCount == 1) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((isRemovable) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((referencesUsingDirectInlining == 1) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[107]++;
      return true;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[108]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[158]++;

    int callCost = estimateCallCost(fnNode, referencesThis);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[159]++;
    int overallCallCost = callCost * referenceCount;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[160]++;

    int costDeltaDirect = inlineCostDelta(
        fnNode, namesToAlias, InliningMode.DIRECT);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[161]++;
    int costDeltaBlock = inlineCostDelta(
        fnNode, namesToAlias, InliningMode.BLOCK);

    return doesLowerCost(fnNode, overallCallCost,
        referencesUsingDirectInlining, costDeltaDirect,
        referencesUsingBlockInlining, costDeltaBlock,
        isRemovable);
  }

  /**
   * @return Whether inlining will lower cost.
   */
  private boolean doesLowerCost(
      Node fnNode, int callCost,
      int directInlines, int costDeltaDirect,
      int blockInlines, int costDeltaBlock,
      boolean removable) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[162]++;

    // Determine the threshold value for this inequality:
    //     inline_cost < call_cost
    // But solve it for the function declaration size so the size of it
    // is only calculated once and terminated early if possible.

    int fnInstanceCount = directInlines + blockInlines - (removable ? 1 : 0);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[163]++;
int CodeCoverConditionCoverageHelper_C51;
    // Prevent division by zero.
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((fnInstanceCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[109]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[164]++;
int CodeCoverConditionCoverageHelper_C52;
      // Special case single reference function that are being block inlined:
      // If the cost of the inline is greater than the function definition size,
      // don't inline.
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((blockInlines > 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((costDeltaBlock > 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[111]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[112]++;}
      return true;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[110]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[165]++;

    int costDelta = (directInlines * costDeltaDirect) +
        (blockInlines * costDeltaBlock);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[166]++;
    int threshold = (callCost - costDelta) / fnInstanceCount;

    return InlineCostEstimator.getCost(fnNode, threshold + 1) <= threshold;
  }

  /**
   * Gets an estimate of the cost in characters of making the function call:
   * the sum of the identifiers and the separators.
   * @param referencesThis
   */
  private static int estimateCallCost(Node fnNode, boolean referencesThis) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[167]++;
    Node argsNode = NodeUtil.getFunctionParameters(fnNode);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[168]++;
    int numArgs = argsNode.getChildCount();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[169]++;

    int callCost = NAME_COST_ESTIMATE + PAREN_COST;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[170]++;
int CodeCoverConditionCoverageHelper_C53;
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((numArgs > 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[113]++;
      callCost += (numArgs * NAME_COST_ESTIMATE) + ((numArgs - 1) * COMMA_COST);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[171]++;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[114]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[172]++;
int CodeCoverConditionCoverageHelper_C54;

    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((referencesThis) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[115]++;
      // TODO(johnlenz): Update this if we start supporting inlining
      // other functions that reference this.
      // The only functions that reference this that are currently inlined
      // are those that are called via ".call" with an explicit "this".
      callCost += 5 + 5;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[173]++;
  // ".call" + "this,"
    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[116]++;}

    return callCost;
  }

  /**
   * @return The difference between the function definition cost and
   *     inline cost.
   */
  private static int inlineCostDelta(
      Node fnNode, Set<String> namesToAlias, InliningMode mode) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[174]++;
    // The part of the function that is never inlined:
    //    "function xx(xx,xx){}" (15 + (param count * 3) -1;
    int paramCount = NodeUtil.getFunctionParameters(fnNode).getChildCount();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[175]++;
    int commaCount = (paramCount > 1) ? paramCount - 1 : 0;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[176]++;
    int costDeltaFunctionOverhead = 15 + commaCount +
        (paramCount * InlineCostEstimator.ESTIMATED_IDENTIFIER_COST);
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[177]++;

    Node block = fnNode.getLastChild();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[178]++;
int CodeCoverConditionCoverageHelper_C55;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((block.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[117]++;
      // Assume the inline cost is zero for empty functions.
      return -costDeltaFunctionOverhead;

    } else {
  CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[118]++;}
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[179]++;
int CodeCoverConditionCoverageHelper_C56;

    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((mode == InliningMode.DIRECT) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[119]++;
      // The part of the function that is inlined using direct inlining:
      //    "return " (7)
      return -(costDeltaFunctionOverhead + 7);

    } else {
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.branches[120]++;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[180]++;
      int aliasCount = namesToAlias.size();
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[181]++;

      // Originally, we estimated purely base on the function code size, relying
      // on later optimizations. But that did not produce good results, so here
      // we try to estimate the something closer to the actual inlined coded.

      // NOTE 1: Result overhead is only if there is an assignment, but
      // getting that information would require some refactoring.
      // NOTE 2: The aliasing overhead is currently an under-estimate,
      // as some parameters are aliased because of the parameters used.
      // Perhaps we should just assume all parameters will be aliased?
      final int INLINE_BLOCK_OVERHEAD = 4;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[182]++; // "X:{}"
      final int PER_RETURN_OVERHEAD = 2;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[183]++;   // "return" --> "break X"
      final int PER_RETURN_RESULT_OVERHEAD = 3;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[184]++; // "XX="
      final int PER_ALIAS_OVERHEAD = 3;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[185]++; // "XX="

      // TODO(johnlenz): Counting the number of returns is relatively expensive
      //   this information should be determined during the traversal and
      //   cached.
      int returnCount = NodeUtil.getNodeTypeReferenceCount(
          block, Token.RETURN, new NodeUtil.MatchShallowStatement());
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[186]++;
      int resultCount = (returnCount > 0) ? returnCount - 1 : 0;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[187]++;
      int baseOverhead = (returnCount > 0) ? INLINE_BLOCK_OVERHEAD : 0;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[188]++;

      int overhead = baseOverhead
          + returnCount * PER_RETURN_OVERHEAD
          + resultCount * PER_RETURN_RESULT_OVERHEAD
          + aliasCount * PER_ALIAS_OVERHEAD;

      return (overhead - costDeltaFunctionOverhead);
    }
  }

  /**
   * Store the names of known constants to be used when classifying call-sites
   * in expressions.
   */
  public void setKnownConstants(Set<String> knownConstants) {
    // This is only expected to be set once. The same set should be used
    // when evaluating call-sites and inlining calls.
    Preconditions.checkState(this.knownConstants.isEmpty());
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[189]++;
    this.knownConstants = knownConstants;
CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5.statements[190]++;
  }
}

class CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5 ());
  }
    public static long[] statements = new long[191];
    public static long[] branches = new long[121];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[57];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.FunctionInjector.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,2,1,1,1,1,2,1,1,1,1,3,3,1,1,1,1,1,2,1,3,1,2,1,1,2,2,1,1,1,1,1,1,2,2,1,1,2,1,1,1,1,2,2,3,1,2,1,1,1,1};
    for (int i = 1; i <= 56; i++) {
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

  public CodeCoverCoverageCounter$1miz189ry4jmd6wqox2aj940ng13fwdb5 () {
    super("com.google.javascript.jscomp.FunctionInjector.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 190; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 120; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 56; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.FunctionInjector.java");
      for (int i = 1; i <= 190; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 120; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 56; i++) {
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

