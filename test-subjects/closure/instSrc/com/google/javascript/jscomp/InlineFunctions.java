/*
 * Copyright 2005 The Closure Compiler Authors.
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
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.FunctionInjector.CanInlineResult;
import com.google.javascript.jscomp.FunctionInjector.InliningMode;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



/**
 * Inlines functions that are divided into two types: "direct call node
 * replacement" (aka "direct") and as a block of statements (aka block).
 * Function that can be inlined "directly" functions consist of a single
 * return statement, everything else is must be inlined as a "block". These
 * functions must meet these general requirements:
 * - it is not recursive
 * - the function does not contain another function -- these may be
 *   intentional to to limit the scope of closures.
 * - function is called only once OR the size of the inline function is smaller
 *   than the call itself.
 * - the function name is not referenced in any other manner
 *
 * "directly" inlined functions must meet these additional requirements:
 * - consists of a single return statement
 *
 */
class InlineFunctions implements SpecializationAwareCompilerPass {
  static {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.ping();
  }


  // TODO(nicksantos): This needs to be completely rewritten to use scopes
  // to do variable lookups. Right now, it assumes that all functions are
  // uniquely named variables. There's currently a stopgap scope-check
  // to ensure that this doesn't produce invalid code. But in the long run,
  // this needs a major refactor.
  private final Map<String, FunctionState> fns = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[1]++;
  }
  private final Map<Node, String> anonFns = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[2]++;
  }

  private final AbstractCompiler compiler;

  private final FunctionInjector injector;

  private final boolean blockFunctionInliningEnabled;
  private final boolean inlineGlobalFunctions;
  private final boolean inlineLocalFunctions;
  private final boolean assumeMinimumCapture;

  private SpecializeModule.SpecializationState specializationState;

  InlineFunctions(AbstractCompiler compiler,
      Supplier<String> safeNameIdSupplier,
      boolean inlineGlobalFunctions,
      boolean inlineLocalFunctions,
      boolean blockFunctionInliningEnabled,
      boolean assumeStrictThis,
      boolean assumeMinimumCapture) {
    Preconditions.checkArgument(compiler != null);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[3]++;
    Preconditions.checkArgument(safeNameIdSupplier != null);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[4]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[5]++;

    this.inlineGlobalFunctions = inlineGlobalFunctions;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[6]++;
    this.inlineLocalFunctions = inlineLocalFunctions;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[7]++;
    this.blockFunctionInliningEnabled = blockFunctionInliningEnabled;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[8]++;
    this.assumeMinimumCapture = assumeMinimumCapture;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[9]++;

    this.injector = new FunctionInjector(
        compiler, safeNameIdSupplier,
        true, assumeStrictThis, assumeMinimumCapture);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[10]++;
  }

  FunctionState getOrCreateFunctionState(String fnName) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[11]++;
    FunctionState fs = fns.get(fnName);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((fs == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[1]++;
      fs = new FunctionState();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[13]++;
      fns.put(fnName, fs);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[14]++;

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[2]++;}
    return fs;
  }

  @Override
  public void enableSpecialization(SpecializeModule.SpecializationState
      specializationState) {
    this.specializationState = specializationState;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[15]++;
  }

  @Override
  public void process(Node externs, Node root) {
    Preconditions.checkState(compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[16]++;

    NodeTraversal.traverse(compiler, root, new FindCandidateFunctions());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[17]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[18]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((fns.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[3]++;
      return;
  // Nothing left to do.
    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[4]++;}
    NodeTraversal.traverse(compiler, root,
       new FindCandidatesReferences(fns, anonFns));
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[19]++;
    trimCanidatesNotMeetingMinimumRequirements();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[20]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((fns.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[5]++;
      return;
  // Nothing left to do.
    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[6]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[22]++;

    // Store the set of function names eligible for inlining and use this to
    // prevent function names from being moved into temporaries during
    // expression decomposition. If this movement were allowed it would prevent
    // the Inline callback from finding the function calls.
    //
    // This pass already assumes these are constants, so this is safe for anyone
    // using function inlining.
    //
    Set<String> fnNames = Sets.newHashSet(fns.keySet());
    injector.setKnownConstants(fnNames);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[23]++;

    trimCanidatesUsingOnCost();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[24]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((fns.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[7]++;
      return;
  // Nothing left to do.
    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[8]++;}
    resolveInlineConflicts();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[26]++;
    decomposeExpressions();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[27]++;
    NodeTraversal.traverse(compiler, root,
        new CallVisitor(
            fns, anonFns, new Inline(injector, specializationState)));
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[28]++;

    removeInlinedFunctions();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[29]++;
  }

  /**
   * Find functions that might be inlined.
   */
  private class FindCandidateFunctions implements Callback {
    private int callsSeen = 0;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[30]++;
  }

    @Override
    public boolean shouldTraverse(
        NodeTraversal nodeTraversal, Node n, Node parent) {
      // Don't traverse into function bodies
      // if we aren't inlining local functions.
      return inlineLocalFunctions || nodeTraversal.inGlobalScope();
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C5 |= (128)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((inlineGlobalFunctions) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
) || (!
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((inlineLocalFunctions) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 4) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 4) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[9]++;
        findNamedFunctions(t, n, parent);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[32]++;

        findFunctionExpressions(t, n);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[33]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[10]++;}
    }

    public void findNamedFunctions(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatement(n)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[11]++;
        // There aren't any interesting functions here.
        return;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[12]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[35]++;

      switch (n.getType()) {
        // Functions expressions in the form of:
        //   var fooFn = function(x) { return ... }
        case Token.VAR:
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[13]++;
          Preconditions.checkState(n.hasOneChild());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[36]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[37]++;
          Node nameNode = n.getFirstChild();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((nameNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((nameNode.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((nameNode.getFirstChild().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[14]++;
            maybeAddFunction(new FunctionVar(n), t.getModule());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[39]++;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[15]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[40]++;
          break;

        // Named functions
        // function Foo(x) { return ... }
        case Token.FUNCTION:
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[16]++;
          Preconditions.checkState(NodeUtil.isStatementBlock(parent)
              || parent.isLabel());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[41]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[17]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[43]++;
            Function fn = new NamedFunction(n);
            maybeAddFunction(fn, t.getModule());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[44]++;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[18]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[45]++;
          break; default : CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[19]++;
      }
    }

    /**
     * Find function expressions that are called directly in the form of
     *   (function(a,b,...){...})(a,b,...)
     * or
     *   (function(a,b,...){...}).call(this,a,b, ...)
     */
    public void findFunctionExpressions(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[46]++;
      switch (n.getType()) {
        // Functions expressions in the form of:
        //   (function(){})();
        case Token.CALL:
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[20]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[47]++;
          Node fnNode = null;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[48]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[21]++;
            fnNode = n.getFirstChild();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[49]++;

          } else {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[22]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[50]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(n)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[23]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[51]++;
            Node fnIdentifingNode = n.getFirstChild().getFirstChild();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[52]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((fnIdentifingNode.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[25]++;
              fnNode = fnIdentifingNode;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[53]++;

            } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[26]++;}

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[24]++;}
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[54]++;
int CodeCoverConditionCoverageHelper_C12;

          // If a interesting function was discovered, add it.
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((fnNode != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[27]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[55]++;
            Function fn = new FunctionExpression(fnNode, callsSeen++);
            maybeAddFunction(fn, t.getModule());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[56]++;
            anonFns.put(fnNode, fn.getName());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[57]++;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[28]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[58]++;
          break; default : CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[29]++;
      }
    }
  }

  /**
   * Updates the FunctionState object for the given function. Checks if the
   * given function matches the criteria for an inlinable function.
   */
  private void maybeAddFunction(Function fn, JSModule module) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[59]++;
    String name = fn.getName();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[60]++;
    FunctionState fs = getOrCreateFunctionState(name);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[61]++;
int CodeCoverConditionCoverageHelper_C13;

    // TODO(johnlenz): Maybe "smarten" FunctionState by adding this logic
    // to it?

    // If the function has multiple definitions, don't inline it.
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((fs.hasExistingFunctionDefinition()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[30]++;
      fs.setInline(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[62]++;

    } else {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[31]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[63]++;
int CodeCoverConditionCoverageHelper_C14;
      // verify the function hasn't already been marked as "don't inline"
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((fs.canInline()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[32]++;
        // store it for use when inlining.
        fs.setFn(fn);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[64]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[65]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((injector.isDirectCallNodeReplacementPossible(
            fn.getFunctionNode())) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[34]++;
          fs.inlineDirectly(true);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[66]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[35]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[67]++;
int CodeCoverConditionCoverageHelper_C16;

        // verify the function meets all the requirements.
        // TODO(johnlenz): Minimum requirement checks are about 5% of the
        // run-time cost of this pass.
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isCandidateFunction(fn)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[36]++;
          // It doesn't meet the requirements.
          fs.setInline(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[68]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[37]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[69]++;
int CodeCoverConditionCoverageHelper_C17;

        // Set the module and gather names that need temporaries.
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((fs.canInline()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[38]++;
          fs.setModule(module);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[70]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[71]++;

          Node fnNode = fn.getFunctionNode();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[72]++;
          Set<String> namesToAlias =
              FunctionArgumentInjector.findModifiedParameters(fnNode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[73]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((namesToAlias.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[40]++;
            fs.inlineDirectly(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[74]++;
            fs.setNamesToAlias(namesToAlias);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[75]++;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[41]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[76]++;

          Node block = NodeUtil.getFunctionBody(fnNode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[77]++;
int CodeCoverConditionCoverageHelper_C19;
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((NodeUtil.referencesThis(block)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[42]++;
            fs.setReferencesThis(true);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[78]++;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[43]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[79]++;
int CodeCoverConditionCoverageHelper_C20;

          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((NodeUtil.containsFunction(block)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[44]++;
            fs.setHasInnerFunctions(true);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[80]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[81]++;
int CodeCoverConditionCoverageHelper_C21;
            // If there are inner functions, we can inline into global scope
            // if there are no local vars or named functions.
            // TODO(johnlenz): this can be improved by looking at the possible
            // values for locals.  If there are simple values, or constants
            // we could still inline.
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((assumeMinimumCapture) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((hasLocalNames(fnNode)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[46]++;
              fs.setInline(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[82]++;

            } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[47]++;}

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[45]++;}

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[39]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[83]++;
int CodeCoverConditionCoverageHelper_C22;

        // Check if block inlining is allowed.
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((fs.canInline()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((fs.canInlineDirectly()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[48]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[84]++;
int CodeCoverConditionCoverageHelper_C23;
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((blockFunctionInliningEnabled) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[50]++;
            fs.setInline(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[85]++;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[51]++;}

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[49]++;}

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[33]++;}
    }
  }

  /**
   * @param fnNode The function to inspect.
   * @return Whether the function has parameters, var, or function declarations.
   */
  private boolean hasLocalNames(Node fnNode) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[86]++;
    Node block = NodeUtil.getFunctionBody(fnNode);
    return NodeUtil.getFunctionParameters(fnNode).hasChildren()
        || NodeUtil.has(
             block,
             new NodeUtil.MatchDeclaration(),
             new NodeUtil.MatchShallowStatement());
  }

  /**
   * Returns the function the traversal is currently traversing, or null
   * if in the global scope.
   */
  private static Node getContainingFunction(NodeTraversal t) {
    return (t.inGlobalScope()) ? null : t.getScopeRoot();
  }

  /**
   * Checks if the given function matches the criteria for an inlinable
   * function.
   */
  private boolean isCandidateFunction(Function fn) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[87]++;
    // Don't inline exported functions.
    String fnName = fn.getName();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[88]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(fnName)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[52]++;
      // TODO(johnlenz): Should we allow internal references to be inlined?
      // An exported name can be replaced externally, any inlined instance
      // would not reflect this change.
      // To allow inlining we need to be able to distinguish between exports
      // that are used in a read-only fashion and those that can be replaced
      // by external definitions.
      return false;

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[53]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[89]++;
int CodeCoverConditionCoverageHelper_C25;

    // Don't inline this special function
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((RenameProperties.RENAME_PROPERTY_FUNCTION_NAME.equals(fnName)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[54]++;
      return false;

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[55]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[90]++;
int CodeCoverConditionCoverageHelper_C26;

    // Don't inline if we are specializing and the function can't be fixed up
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((specializationState != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((specializationState.canFixupFunction(fn.getFunctionNode())) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[56]++;
      return false;

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[57]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[91]++;

    Node fnNode = fn.getFunctionNode();
    return injector.doesFunctionMeetMinimumRequirements(fnName, fnNode);
  }

  /**
   * @see CallVisitor
   */
  private interface CallVisitorCallback {
    public void visitCallSite(
        NodeTraversal t, Node callNode, Node parent, FunctionState fs);
  }

  /**
   * Visit call sites for functions in functionMap.
   */
  private static class CallVisitor extends AbstractPostOrderCallback {

    protected CallVisitorCallback callback;
    private Map<String, FunctionState> functionMap;
    private Map<Node, String> anonFunctionMap;

    CallVisitor(Map<String, FunctionState> fns,
                Map<Node, String> anonFns,
                CallVisitorCallback callback) {
      this.functionMap = fns;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[92]++;
      this.anonFunctionMap = anonFns;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[93]++;
      this.callback = callback;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[94]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[95]++;
      switch (n.getType()) {
        // Function calls
        case Token.CALL:
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[58]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[96]++;
          Node child = n.getFirstChild();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[97]++;
          String name = null;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[98]++;
int CodeCoverConditionCoverageHelper_C27;
          // NOTE: The normalization pass insures that local names do not
          // collide with global names.
          if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((child.isName()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[59]++;
            name = child.getString();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[99]++;

          } else {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[60]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[100]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((child.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[61]++;
            name = anonFunctionMap.get(child);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[101]++;

          } else {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[62]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[102]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(n)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[63]++;
            Preconditions.checkState(NodeUtil.isGet(child));
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[103]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[104]++;
            Node fnIdentifingNode = child.getFirstChild();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[105]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((fnIdentifingNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[65]++;
              name = fnIdentifingNode.getString();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[106]++;

            } else {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[66]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[107]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((fnIdentifingNode.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[67]++;
              name = anonFunctionMap.get(fnIdentifingNode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[108]++;

            } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[68]++;}
}

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[64]++;}
}
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[109]++;
int CodeCoverConditionCoverageHelper_C32;

          if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[69]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[110]++;
            FunctionState fs = functionMap.get(name);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[111]++;
int CodeCoverConditionCoverageHelper_C33;
            // Only visit call-sites for functions that can be inlined.
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((fs != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[71]++;
              callback.visitCallSite(t, n, parent, fs);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[112]++;

            } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[72]++;}

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[70]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[113]++;
          break; default : CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[73]++;
      }
    }
  }

  /**
   * @return Whether the name is used in a way that might be a candidate
   *   for inlining.
   */
  static boolean isCandidateUsage(Node name) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[114]++;
    Node parent = name.getParent();
    Preconditions.checkState(name.isName());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[115]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[116]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[74]++;
      // This is a declaration.  Duplicate declarations are handle during
      // function candidate gathering.
      return true;

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[75]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[117]++;
int CodeCoverConditionCoverageHelper_C35;

    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((parent.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == name) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[76]++;
      // This is a normal reference to the function.
      return true;

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[77]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[118]++;
int CodeCoverConditionCoverageHelper_C36;

    // Check for a ".call" to the named function:
    //   CALL
    //     GETPROP/GETELEM
    //       NAME
    //       STRING == "call"
    //     This-Value
    //     Function-parameter-1
    //     ...
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (128)) == 0 || true) &&
 ((NodeUtil.isGet(parent)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (32)) == 0 || true) &&
 ((name == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((name.getNext().isString()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((name.getNext().getString().equals("call")) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 4) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 4) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[78]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[119]++;
      Node gramps = name.getAncestor(2);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[120]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((gramps.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((gramps.getFirstChild() == parent) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[80]++;
        // Yep, a ".call".
        return true;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[81]++;}

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[79]++;}
    return false;
  }

  /**
   * Find references to functions that are inlinable.
   */
  private class FindCandidatesReferences
      extends CallVisitor
      implements CallVisitorCallback {
    FindCandidatesReferences(
        Map<String, FunctionState> fns,
        Map<Node, String> anonFns) {
      super(fns, anonFns, null);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[121]++;
      this.callback = this;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[122]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
      super.visit(t, n, parent);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[123]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[124]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[82]++;
        checkNameUsage(n, parent);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[125]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[83]++;}
    }

    @Override
    public void visitCallSite(
        NodeTraversal t, Node callNode, Node parent, FunctionState fs) {
      maybeAddReference(t, fs, callNode, t.getModule());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[126]++;
    }

    void maybeAddReference(NodeTraversal t, FunctionState fs,
        Node callNode, JSModule module) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[127]++;
int CodeCoverConditionCoverageHelper_C39;
      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((fs.canInline()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[84]++;
        return;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[85]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[128]++;

      boolean referenceAdded = false;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[129]++;
      InliningMode mode = fs.canInlineDirectly()
           ? InliningMode.DIRECT : InliningMode.BLOCK;
      referenceAdded = maybeAddReferenceUsingMode(
          t, fs, callNode, module, mode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[130]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[131]++;
int CodeCoverConditionCoverageHelper_C40;
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (32)) == 0 || true) &&
 ((referenceAdded) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((mode == InliningMode.DIRECT) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((blockFunctionInliningEnabled) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 3) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[86]++;
        // This reference can not be directly inlined, see if
        // block replacement inlining is possible.
        mode = InliningMode.BLOCK;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[132]++;
        referenceAdded = maybeAddReferenceUsingMode(
            t, fs, callNode, module, mode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[133]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[87]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[134]++;
int CodeCoverConditionCoverageHelper_C41;

      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((referenceAdded) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[88]++;
        // Don't try to remove a function if we can't inline all
        // the references.
        fs.setRemove(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[135]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[89]++;}
    }

    private boolean maybeAddReferenceUsingMode(
        NodeTraversal t, FunctionState fs, Node callNode,
        JSModule module, InliningMode mode) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[136]++;
int CodeCoverConditionCoverageHelper_C42;

      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((specializationState != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[90]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[137]++;
        // If we're specializing, make sure we can fixup
        // the containing function before inlining
        Node containingFunction = getContainingFunction(t);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[138]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((containingFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((specializationState.canFixupFunction(
            containingFunction)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[92]++;
            return false;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[93]++;}

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[91]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[139]++;

      CanInlineResult result = injector.canInlineReferenceToFunction(
          t, callNode, fs.getFn().getFunctionNode(),
          fs.getNamesToAlias(), mode, fs.getReferencesThis(),
          fs.hasInnerFunctions());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[140]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((result != CanInlineResult.NO) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[94]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[141]++;
        // Yeah!
        boolean decompose =
          (result == CanInlineResult.AFTER_PREPARATION);
        fs.addReference(new Reference(callNode, module, mode, decompose));
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[142]++;
        return true;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[95]++;}

      return false;
    }

    /**
     * Find functions that can be inlined.
     */
    private void checkNameUsage(Node n, Node parent) {
      Preconditions.checkState(n.isName());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[143]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[144]++;
int CodeCoverConditionCoverageHelper_C45;

      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((isCandidateUsage(n)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[96]++;
        return;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[97]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[145]++;

      // Other refs to a function name remove its candidacy for inlining
      String name = n.getString();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[146]++;
      FunctionState fs = fns.get(name);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[147]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((fs == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[98]++;
        return;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[99]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[148]++;
int CodeCoverConditionCoverageHelper_C47;

      // Unlike normal call/new parameters, references passed to
      // JSCompiler_ObjectPropertyString are not aliases of a value, but
      // a reference to the name itself, as such the value of the name is
      // unknown and can not be inlined.
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((parent.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[100]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[149]++;
        Node target = parent.getFirstChild();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[150]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((target.isName()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((target.getString().equals(
            ObjectPropertyStringPreprocess.EXTERN_OBJECT_PROPERTY_STRING)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[102]++;
          // This method is going to be replaced so don't inline it anywhere.
          fs.setInline(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[151]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[103]++;}

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[101]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[152]++;
int CodeCoverConditionCoverageHelper_C49;

      // If the name is being assigned to it can not be inlined.
      if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[104]++;
        // e.g. bar = something; <== we can't inline "bar"
        // so mark the function as uninlinable.
        // TODO(johnlenz): Should we just remove it from fns here?
        fs.setInline(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[153]++;

      } else {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[105]++;
        // e.g. var fn = bar; <== we can't inline "bar"
        // As this reference can't be inlined mark the function as
        // unremovable.
        fs.setRemove(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[154]++;
      }
    }
  }

  /**
   * Inline functions at the call sites.
   */
  private static class Inline implements CallVisitorCallback {
    private final FunctionInjector injector;
    private final SpecializeModule.SpecializationState specializationState;

    Inline(FunctionInjector injector,
        SpecializeModule.SpecializationState specializationState) {
      this.injector = injector;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[155]++;
      this.specializationState = specializationState;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[156]++;
    }

    @Override
    public void visitCallSite(
        NodeTraversal t, Node callNode, Node parent, FunctionState fs) {
      Preconditions.checkState(fs.hasExistingFunctionDefinition());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[157]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[158]++;
int CodeCoverConditionCoverageHelper_C50;
      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((fs.canInline()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[106]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[159]++;
        Reference ref = fs.getReference(callNode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[160]++;
int CodeCoverConditionCoverageHelper_C51;
        // There are two cases ref can be null: if the call site was introduce
        // because it was part of a function that was inlined during this pass
        // or if the call site was trimmed from the list of references because
        // the function couldn't be inlined at this location.
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((ref != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[108]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[161]++;
int CodeCoverConditionCoverageHelper_C52;
          if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((specializationState != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[110]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[162]++;
            Node containingFunction = getContainingFunction(t);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[163]++;
int CodeCoverConditionCoverageHelper_C53;

            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((containingFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[112]++;
              // Report that the function was specialized so that
              // {@link SpecializeModule} can fix it up.
              specializationState.reportSpecializedFunction(containingFunction);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[164]++;

            } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[113]++;}

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[111]++;}

          inlineFunction(t, callNode, fs, ref.mode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[165]++;
          // Keep track of references that have been inlined so that
          // we can verify that none have been missed.
          ref.inlined = true;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[166]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[109]++;}

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[107]++;}
    }

    /**
     * Inline a function into the call site.
     */
    private void inlineFunction(
        NodeTraversal t, Node callNode, FunctionState fs, InliningMode mode) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[167]++;
      Function fn = fs.getFn();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[168]++;
      String fnName = fn.getName();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[169]++;
      Node fnNode = fs.getSafeFnNode();

      injector.inline(callNode, fnName, fnNode, mode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[170]++;
      t.getCompiler().reportCodeChange();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[171]++;
      t.getCompiler().addToDebugLog("Inlined function: " + fn.getName());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[172]++;
    }
  }

  /**
   * Remove entries that aren't a valid inline candidates, from the list of
   * encountered names.
   */
  private void trimCanidatesNotMeetingMinimumRequirements() {
   Iterator<Entry<String, FunctionState>> i;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[173]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[1]++;


int CodeCoverConditionCoverageHelper_C54;
   for (i = fns.entrySet().iterator();(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[1]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[2]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[3]++;
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[174]++;
     FunctionState fs = i.next().getValue();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[175]++;
int CodeCoverConditionCoverageHelper_C55;
     if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((fs.hasExistingFunctionDefinition()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((fs.canInline()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[114]++;
       i.remove();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[176]++;

     } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[115]++;}
   }
  }

  /**
   * Remove entries from the list of candidates that can't be inlined.
   */
  void trimCanidatesUsingOnCost() {
    Iterator<Entry<String, FunctionState>> i;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[177]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[4]++;


int CodeCoverConditionCoverageHelper_C56;
    for (i = fns.entrySet().iterator();(((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[4]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[5]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[6]++;
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[178]++;
      FunctionState fs = i.next().getValue();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[179]++;
int CodeCoverConditionCoverageHelper_C57;
      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((fs.hasReferences()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[116]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[180]++;
        // Only inline function if it decreases the code size.
        boolean lowersCost = mimimizeCost(fs);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[181]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((lowersCost) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[118]++;
          // It shouldn't be inlined; remove it from the list.
          i.remove();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[182]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[119]++;}

      } else {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[117]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[183]++;
int CodeCoverConditionCoverageHelper_C59; if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((fs.canRemove()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[120]++;
        // Don't bother tracking functions without references that can't be
        // removed.
        i.remove();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[184]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[121]++;}
}
    }
  }

  /**
   * Determines if the function is worth inlining and potentially
   * trims references that increase the cost.
   * @return Whether inlining the references lowers the overall cost.
   */
  private boolean mimimizeCost(FunctionState fs) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[185]++;
int CodeCoverConditionCoverageHelper_C60;
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((inliningLowersCost(fs)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[122]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[186]++;
int CodeCoverConditionCoverageHelper_C61;
      // Try again without Block inlining references
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((fs.hasBlockInliningReferences()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[124]++;
        fs.setRemove(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[187]++;
        fs.removeBlockInliningReferences();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[188]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[189]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((fs.hasReferences()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((inliningLowersCost(fs)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[126]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[127]++;}

      } else {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[125]++;
        return false;
      }

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[123]++;}
    return true;
  }

  /**
   * @return Whether inlining the function reduces code size.
   */
  private boolean inliningLowersCost(FunctionState fs) {
    return injector.inliningLowersCost(
        fs.getModule(),
        fs.getFn().getFunctionNode(),
        fs.getReferences(),
        fs.getNamesToAlias(),
        fs.canRemove(),
        fs.getReferencesThis());
  }


  /**
   * Size base inlining calculations are thrown off when a function that is
   * being inlined also contains calls to functions that are slated for
   * inlining.
   *
   * Specifically, a clone of the FUNCTION node tree is used when the function
   * is inlined. Calls in this new tree are not included in the list of function
   * references so they won't be inlined (which is what we want). Here we mark
   * those functions as non-removable (as they will have new references in the
   * cloned node trees).
   *
   * This prevents a function that would only be inlined because it is
   * referenced once from being inlined into multiple call sites because
   * the calling function has been inlined in multiple locations or the
   * function being removed while there are still references.
   */
  private void resolveInlineConflicts() {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[190]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[7]++;


    for (FunctionState fs : fns.values()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[7]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[8]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[9]++;
}
      resolveInlineConflictsForFunction(fs);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[191]++;
    }
  }

  /**
   * @see #resolveInlineConflicts
   */
  private void resolveInlineConflictsForFunction(FunctionState fs) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[192]++;
int CodeCoverConditionCoverageHelper_C63;
    // Functions that aren't referenced don't cause conflicts.
    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((fs.hasReferences()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((fs.canInline()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[128]++;
      return;

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[129]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[193]++;

    Node fnNode = fs.getFn().getFunctionNode();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[194]++;
    Set<String> names = findCalledFunctions(fnNode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[195]++;
int CodeCoverConditionCoverageHelper_C64;
    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((names.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[130]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[196]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[10]++;


      // Prevent the removal of the referenced functions.
      for (String name : names) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[10]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[11]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[12]++;
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[197]++;
        FunctionState fsCalled = fns.get(name);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[198]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((fsCalled != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((fsCalled.canRemove()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[132]++;
          fsCalled.setRemove(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[199]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[200]++;
int CodeCoverConditionCoverageHelper_C66;
          // For functions that can no longer be removed, check if they should
          // still be inlined.
          if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((mimimizeCost(fsCalled)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[134]++;
            // It can't be inlined remove it from the list.
            fsCalled.setInline(false);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[201]++;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[135]++;}

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[133]++;}
      }

      // Make a copy of the Node, so it isn't changed by other inlines.
      fs.setSafeFnNode(fs.getFn().getFunctionNode().cloneTree());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[202]++;

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[131]++;}
  }

  /**
   * This functions that may be called directly.
   */
  private Set<String> findCalledFunctions(Node node) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[203]++;
    Set<String> changed = Sets.newHashSet();
    findCalledFunctions(NodeUtil.getFunctionBody(node), changed);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[204]++;
    return changed;
  }

  /**
   * @see #findCalledFunctions(Node)
   */
  private void findCalledFunctions(
      Node node, Set<String> changed) {
    Preconditions.checkArgument(changed != null);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[205]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[206]++;
int CodeCoverConditionCoverageHelper_C67;
    // For each referenced function, add a new reference
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[136]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[207]++;
int CodeCoverConditionCoverageHelper_C68;
      if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((isCandidateUsage(node)) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[138]++;
        changed.add(node.getString());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[208]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[139]++;}

    } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[137]++;}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[209]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[13]++;


int CodeCoverConditionCoverageHelper_C69;

    for (Node c = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[13]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[14]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[15]++;
}
      findCalledFunctions(c, changed);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[210]++;
    }
  }

  /**
   * For any call-site that needs it, prepare the call-site for inlining
   * by rewriting the containing expression.
   */
  private void decomposeExpressions() {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[211]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[16]++;


    for (FunctionState fs : fns.values()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[16]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[17]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[18]++;
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[212]++;
int CodeCoverConditionCoverageHelper_C70;
      if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((fs.canInline()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[140]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[213]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[19]++;


        for (Reference ref : fs.getReferences()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[19]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[20]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[21]++;
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[214]++;
int CodeCoverConditionCoverageHelper_C71;
          if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((ref.requiresDecomposition) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[142]++;
            injector.maybePrepareCall(ref.callNode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[215]++;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[143]++;}
        }

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[141]++;}
    }
  }

  /**
   * Removed inlined functions that no longer have any references.
   */
  void removeInlinedFunctions() {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[216]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[22]++;


    for (FunctionState fs : fns.values()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[22]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[23]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[24]++;
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[217]++;
int CodeCoverConditionCoverageHelper_C72;
      if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((fs.canRemove()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[144]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[218]++;
        Function fn = fs.getFn();
        Preconditions.checkState(fs.canInline());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[219]++;
        Preconditions.checkState(fn != null);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[220]++;
        verifyAllReferencesInlined(fs);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[221]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[222]++;
int CodeCoverConditionCoverageHelper_C73;

        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((specializationState != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[146]++;
          specializationState.reportRemovedFunction(
              fn.getFunctionNode(), fn.getDeclaringBlock());
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[223]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[147]++;}

        fn.remove();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[224]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[225]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[145]++;}
    }
  }

  /**
   * Sanity check to verify, that expression rewriting didn't
   * make a call inaccessible.
   */
  void verifyAllReferencesInlined(FunctionState fs) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[226]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[25]++;


    for (Reference ref : fs.getReferences()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[25]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[26]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[27]++;
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[227]++;
int CodeCoverConditionCoverageHelper_C74;
      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((ref.inlined) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[148]++;
        throw new IllegalStateException("Call site missed.\n call: "
            + ref.callNode.toStringTree() + "\n parent:  "
            + ref.callNode.getParent().toStringTree());

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[149]++;}
    }
  }

  /**
   * Use to track the decisions that have been make about a function.
   */
  private static class FunctionState {
    private Function fn = null;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[228]++;
  }
    private Node safeFnNode = null;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[229]++;
  }
    private boolean inline = true;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[230]++;
  }
    private boolean remove = true;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[231]++;
  }
    private boolean inlineDirectly = false;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[232]++;
  }
    private boolean referencesThis = false;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[233]++;
  }
    private boolean hasInnerFunctions = false;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[234]++;
  }
    private Map<Node, Reference> references = null;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[235]++;
  }
    private JSModule module = null;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[236]++;
  }
    private Set<String> namesToAlias = null;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[237]++;
  }

    boolean hasExistingFunctionDefinition() {
      return (fn != null);
    }

    public void setReferencesThis(boolean referencesThis) {
      this.referencesThis = referencesThis;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[238]++;
    }

    public boolean getReferencesThis() {
      return this.referencesThis;
    }

    public void setHasInnerFunctions(boolean hasInnerFunctions) {
      this.hasInnerFunctions = hasInnerFunctions;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[239]++;
    }


    public boolean hasInnerFunctions() {
      return hasInnerFunctions;
    }

    void removeBlockInliningReferences() {
      Iterator<Entry<Node, Reference>> i;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[240]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[28]++;


int CodeCoverConditionCoverageHelper_C75;
      for (i = getReferencesInternal().entrySet().iterator();(((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false);) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[28]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[29]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[30]++;
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[241]++;
        Entry<Node, Reference> entry = i.next();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[242]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((entry.getValue().mode == InliningMode.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[150]++;
          i.remove();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[243]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[151]++;}
      }
    }

    public boolean hasBlockInliningReferences() {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[244]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[31]++;


      for (Reference r : getReferencesInternal().values()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[31]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[32]--;
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.loops[33]++;
}
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[245]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((r.mode == InliningMode.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[152]++;
          return true;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[153]++;}
      }
      return false;
    }

    public Function getFn() {
      return fn;
    }

    public void setFn(Function fn) {
      Preconditions.checkState(this.fn == null);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[246]++;
      this.fn = fn;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[247]++;
    }

    public Node getSafeFnNode() {
      return (safeFnNode != null) ? safeFnNode : fn.getFunctionNode();
    }

    public void setSafeFnNode(Node safeFnNode) {
      this.safeFnNode = safeFnNode;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[248]++;
    }

    public boolean canInline() {
      return inline;
    }

    public void setInline(boolean inline) {
      this.inline = inline;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[249]++;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[250]++;
int CodeCoverConditionCoverageHelper_C78;
      if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((inline == false) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[154]++;
        // No need to keep references to function that can't be inlined.
        references = null;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[251]++;
        // Don't remove functions that we aren't inlining.
        remove = false;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[252]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[155]++;}
    }

    public boolean canRemove() {
      return remove;
    }

    public void setRemove(boolean remove) {
      this.remove = remove;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[253]++;
    }

    public boolean canInlineDirectly() {
      return inlineDirectly;
    }

    public void inlineDirectly(boolean directReplacement) {
      this.inlineDirectly = directReplacement;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[254]++;
    }

    public boolean hasReferences() {
      return (references != null && !references.isEmpty());
    }

    private Map<Node, Reference> getReferencesInternal() {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[255]++;
int CodeCoverConditionCoverageHelper_C79;
      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((references == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[156]++;
        return Collections.emptyMap();

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[157]++;}
      return references;
    }

    public void addReference(Reference ref) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[256]++;
int CodeCoverConditionCoverageHelper_C80;
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((references == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[158]++;
        references = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[257]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[159]++;}
      references.put(ref.callNode, ref);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[258]++;
    }

    public Collection<Reference> getReferences() {
      return getReferencesInternal().values();
    }

    public Reference getReference(Node n) {
      return getReferencesInternal().get(n);
    }

    public Set<String> getNamesToAlias() {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[259]++;
int CodeCoverConditionCoverageHelper_C81;
      if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((namesToAlias == null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[160]++;
        return Collections.emptySet();

      } else {
  CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.branches[161]++;}
      return Collections.unmodifiableSet(namesToAlias);
    }

    public void setNamesToAlias(Set<String> names) {
      namesToAlias = names;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[260]++;
    }

    public void setModule(JSModule module) {
      this.module = module;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[261]++;
    }

    public JSModule getModule() {
      return module;
    }
  }

  /**
   * Interface for dealing with function declarations and function
   * expressions equally
   */
  private static interface Function {
    /** Gets the name of the function */
    public String getName();

    /** Gets the function node */
    public Node getFunctionNode();

    /** Removes itself from the JavaScript */
    public void remove();

    public Node getDeclaringBlock();
  }

  /** NamedFunction implementation of the Function interface */
  private static class NamedFunction implements Function {
    private final Node fn;

    public NamedFunction(Node fn) {
      this.fn = fn;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[262]++;
    }

    @Override
    public String getName() {
      return fn.getFirstChild().getString();
    }

    @Override
    public Node getFunctionNode() {
      return fn;
    }

    @Override
    public void remove() {
      NodeUtil.removeChild(fn.getParent(), fn);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[263]++;
    }

    @Override
    public Node getDeclaringBlock() {
      return fn.getParent();
    }
  }

  /** FunctionVar implementation of the Function interface */
  private static class FunctionVar implements Function {
    private final Node var;

    public FunctionVar(Node var) {
      this.var = var;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[264]++;
    }

    @Override
    public String getName() {
      return var.getFirstChild().getString();
    }

    @Override
    public Node getFunctionNode() {
      return var.getFirstChild().getFirstChild();
    }

    @Override
    public void remove() {
      NodeUtil.removeChild(var.getParent(), var);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[265]++;
    }

    @Override
    public Node getDeclaringBlock() {
      return var.getParent();
    }
  }

  /** FunctionExpression implementation of the Function interface */
  private static class FunctionExpression implements Function {
    private final Node fn;
    private final String fakeName;

    public FunctionExpression(Node fn, int index) {
      this.fn = fn;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[266]++;
      // A number is not a valid function JavaScript identifier
      // so we don't need to worry about collisions.
      this.fakeName = String.valueOf(index);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[267]++;
    }

    @Override
    public String getName() {
      return fakeName;
    }

    @Override
    public Node getFunctionNode() {
      return fn;
    }

    @Override
    public void remove() {
      // Nothing to do. The function is removed with the call.
    }

    @Override
    public Node getDeclaringBlock() {
      return null;
    }

  }

  class Reference extends FunctionInjector.Reference {
    final boolean requiresDecomposition;
    boolean inlined = false;
  {
    CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[268]++;
  }
    Reference(
        Node callNode, JSModule module, InliningMode mode, boolean decompose) {
      super(callNode, module, mode);
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[269]++;
      this.requiresDecomposition = decompose;
CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t.statements[270]++;
    }
  }
}

class CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t ());
  }
    public static long[] statements = new long[271];
    public static long[] branches = new long[162];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[82];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.InlineFunctions.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,3,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,2,1,1,1,1,1,1,1,2,2,3,2,1,1,3,1,1,2,1,1,1,1,2,2,1,1,1,1,1,2,1,1,1,1,1,1,2,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 81; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[34];

  public CodeCoverCoverageCounter$8kskhxcvkethhatkuj8mbv3obyev61t () {
    super("com.google.javascript.jscomp.InlineFunctions.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 270; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 161; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 81; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.InlineFunctions.java");
      for (int i = 1; i <= 270; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 161; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 81; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 11; i++) {
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

