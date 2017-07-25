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
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.AbstractCompiler.LifeCycleStage;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collection;
import java.util.List;

/**
 * Optimize function calls and function signatures.
 *
 * <ul>
 * <li>Removes optional parameters if no caller specifies it as argument.</li>
 * <li>Removes arguments at call site to function that
 *     ignores the parameter.</li>
 * <li>Inline a parameter if the function is always called with that constant.
 *     </li>
 * </ul>
 *
 */
class OptimizeParameters
    implements CompilerPass, OptimizeCalls.CallGraphCompilerPass {
  static {
    CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.ping();
  }


  private final AbstractCompiler compiler;
  private List<Node> removedNodes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[1]++;
  }

  OptimizeParameters(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[2]++;
  }

  @Override
  @VisibleForTesting
  public void process(Node externs, Node root) {
    Preconditions.checkState(
        compiler.getLifeCycleStage() == LifeCycleStage.NORMALIZED);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[3]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[4]++;
    SimpleDefinitionFinder defFinder = new SimpleDefinitionFinder(compiler);
    defFinder.process(externs, root);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[5]++;
    process(externs, root, defFinder);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[6]++;
  }

  @Override
  public void process(
      Node externs, Node root, SimpleDefinitionFinder definitions) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[1]++;


    for (DefinitionSite defSite : definitions.getDefinitionSites()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[1]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[2]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[3]++;
}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((canChangeSignature(defSite, definitions)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[1]++;
        tryEliminateConstantArgs(defSite, definitions);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[9]++;
        tryEliminateOptionalArgs(defSite, definitions);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[10]++;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[2]++;}
    }
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[11]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[4]++;



    // Remove any references or definitions that have been removed to keep it
    // in a consistent state for the next pass.
    for (Node n : removedNodes) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[4]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[5]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[6]++;
}
      definitions.removeReferences(n);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[12]++;
    }
  }

  /**
   * @return Whether the definitionSite represents a function whose call
   *      signature can be modified.
   */
  private boolean canChangeSignature(
      DefinitionSite definitionSite, SimpleDefinitionFinder defFinder) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[13]++;
    Definition definition = definitionSite.definition;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((definitionSite.inExterns) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[3]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[4]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[15]++;

    // Only functions may be rewritten.
    // Functions that access "arguments" are not eligible since
    // rewrite changes the structure of this object.
    Node rValue = definition.getRValue();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((rValue == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((rValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarArgsFunction(rValue)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[5]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[6]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;

    // TODO(johnlenz): support rewriting methods defined as part of
    // object literals (they are generally problematic because they may be
    // maps of functions use in for-in expressions, etc).
    // Be conservative, don't try to optimize any declaration that isn't as
    // simple function declaration or assignment.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((SimpleDefinitionFinder.isSimpleFunctionDeclaration(rValue)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[7]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[8]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;

    // Assume an exported method result is used.
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((defFinder.canModifyDefinition(definition)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[9]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[10]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[19]++;

    Collection<UseSite> useSites = defFinder.getUseSites(definition);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((useSites.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[11]++;
      return false;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[12]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[21]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[7]++;



    for (UseSite site : useSites) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[7]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[8]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[9]++;
}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
      // Any non-call reference maybe introducing an alias. Don't try to
      // change the function signature, if all the aliases can't also be
      // changed.
      // TODO(johnlenz): Support .call signature changes.
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((SimpleDefinitionFinder.isCallOrNewSite(site)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[13]++;
        return false;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[14]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[23]++;

      // TODO(johnlenz): support specialization

      // Multiple definitions prevent rewrite.
      // TODO(johnlenz): Allow rewrite all definitions are valid.
      Node nameNode = site.node;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[24]++;
      Collection<Definition> singleSiteDefinitions =
          defFinder.getDefinitionsReferencedAt(nameNode);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[25]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((singleSiteDefinitions.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[15]++;
        return false;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[16]++;}
      Preconditions.checkState(!singleSiteDefinitions.isEmpty());
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[26]++;
      Preconditions.checkState(singleSiteDefinitions.contains(definition));
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[27]++;
    }

    return true;
  }

  /**
   * Removes any optional parameters if no callers specifies it as an argument.
   */
  private void tryEliminateOptionalArgs(
      DefinitionSite defSite, SimpleDefinitionFinder defFinder) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[28]++;
    // Count the maximum number of arguments passed into this function all
    // all points of the program.
    int maxArgs = -1;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[29]++;

    Definition definition = defSite.definition;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[30]++;
    Collection<UseSite> useSites = defFinder.getUseSites(definition);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[31]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[10]++;


    for (UseSite site : useSites) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[10]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[11]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[12]++;
}
      Preconditions.checkState(SimpleDefinitionFinder.isCallOrNewSite(site));
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[32]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[33]++;
      Node call = site.node.getParent();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[34]++;

      int numArgs = call.getChildCount() - 1;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((numArgs > maxArgs) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[17]++;
        maxArgs = numArgs;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[36]++;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[18]++;}
    }

    eliminateParamsAfter(definition.getRValue(), maxArgs);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[37]++;
  }

  /**
   * Eliminate parameters if they are always constant.
   *
   * function foo(a, b) {...}
   * foo(1,2);
   * foo(1,3)
   * becomes
   * function foo(b) { var a = 1 ... }
   * foo(2);
   * foo(3);
   */
  private void tryEliminateConstantArgs(
      DefinitionSite defSite, SimpleDefinitionFinder defFinder) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[38]++;

    List<Parameter> parameters = Lists.newArrayList();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[39]++;
    boolean firstCall = true;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[40]++;

    // Build a list of parameters to remove
    Definition definition = defSite.definition;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[41]++;
    Collection<UseSite> useSites = defFinder.getUseSites(definition);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[42]++;
    boolean continueLooking = false;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[43]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[13]++;


    for (UseSite site : useSites) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[13]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[14]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[15]++;
}
      Preconditions.checkState(SimpleDefinitionFinder.isCallOrNewSite(site));
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[44]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[45]++;
      Node call = site.node.getParent();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[46]++;

      Node cur = call.getFirstChild();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((firstCall) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[19]++;
        // Use the first call to construct a list of parameters of the
        // function.
        continueLooking = buildParameterList(parameters, cur, site.scope);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[48]++;
        firstCall = false;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[49]++;

      } else {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[20]++;
        continueLooking= findFixedParameters(parameters, cur);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[50]++;
      }
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[51]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((continueLooking) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[21]++;
        return;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[22]++;}
    }

    continueLooking = adjustForSideEffects(parameters);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[52]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((continueLooking) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[23]++;
      return;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[24]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[54]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[16]++;



    // Remove the constant parameters in all the calls
    for (UseSite site : useSites) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[16]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[17]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[18]++;
}
      Preconditions.checkState(SimpleDefinitionFinder.isCallOrNewSite(site));
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[55]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[56]++;
      Node call = site.node.getParent();

      optimizeCallSite(defFinder, parameters, call);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[57]++;
    }
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[58]++;

    // Remove the constant parameters in the definitions and add it as a local
    // variable.
    Node function = definition.getRValue();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[59]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((function.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[25]++;
      optimizeFunctionDefinition(parameters, function);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[60]++;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[26]++;}
  }

  /**
   * Adjust the parameters to move based on the side-effects seen.
   * @return Whether there are any movable parameters.
   */
  private boolean adjustForSideEffects(List<Parameter> parameters) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[61]++;
    // If a parameter is moved, that has side-effect no parameters that
    // can be effected by side-effects can be left.

    // A parameter can be moved if it can't be side-effected (immutable),
    // or there are no following side-effects, that aren't moved.

    boolean anyMovable = false;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[62]++;
    boolean seenUnmovableSideEffects = false;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[63]++;
    boolean seenUnmoveableSideEfffected = false;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[64]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[19]++;


int CodeCoverConditionCoverageHelper_C14;
    for (int i = parameters.size() - 1;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i--) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[19]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[20]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[21]++;
}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[65]++;
      Parameter current = parameters.get(i);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[66]++;
int CodeCoverConditionCoverageHelper_C15;

      // Preserve side-effect ordering, don't move this parameter if:
      // * the current parameter has side-effects and a following
      // parameters that will not be move can be effected.
      // * the current parameter can be effected and a following
      // parameter that will not be moved has side-effects

      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (512)) == 0 || true) &&
 ((current.shouldRemove) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (256)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C15 |= (128)) == 0 || true) &&
 ((seenUnmovableSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((current.canBeSideEffected()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((seenUnmoveableSideEfffected) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((current.hasSideEffects()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 5) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 5) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[27]++;
        current.shouldRemove = false;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[67]++;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[28]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[68]++;
int CodeCoverConditionCoverageHelper_C16;

      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((current.shouldRemove) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[29]++;
        anyMovable = true;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[69]++;

      } else {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[30]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((current.canBeSideEffected) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[31]++;
          seenUnmoveableSideEfffected = true;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[71]++;

        } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[32]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[72]++;
int CodeCoverConditionCoverageHelper_C18;

        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((current.hasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[33]++;
          seenUnmovableSideEffects = true;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[73]++;

        } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[34]++;}
      }
    }
    return anyMovable;
  }

  /**
   * Determine which parameters use the same expression.
   * @return Whether any parameter was found that can be updated.
   */
  private boolean findFixedParameters(List<Parameter> parameters, Node cur) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[74]++;
    boolean anyMovable = false;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[75]++;
    int index = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[76]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[22]++;


    while ((cur = cur.getNext()) != null) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[22]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[23]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[24]++;
}
      Parameter p;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[77]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((index >= parameters.size()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[35]++;
        p = new Parameter(cur, false);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[78]++;
        parameters.add(p);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[79]++;

      } else {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[36]++;
        p = parameters.get(index);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[80]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[81]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((p.shouldRemove()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[37]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[82]++;
          Node value = p.getArg();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[83]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((cur.isEquivalentTo(value)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[39]++;
            p.setShouldRemove(false);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[84]++;

          } else {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[40]++;
            anyMovable = true;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[85]++;
          }

        } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[38]++;}
      }

      setParameterSideEffectInfo(p, cur);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[86]++;
      index++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[87]++;
    }
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[88]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[25]++;


int CodeCoverConditionCoverageHelper_C23;

    for (;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((index < parameters.size()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[25]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[26]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[27]++;
}
      parameters.get(index).setShouldRemove(false);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[89]++;
    }

    return anyMovable;
  }

  /**
   * @return Whether any parameter was movable.
   */
  private boolean buildParameterList(
      List<Parameter> parameters, Node cur, Scope s) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[90]++;
    boolean anyMovable = false;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[91]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[28]++;


    while ((cur = cur.getNext()) != null) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[28]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[29]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[30]++;
}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[92]++;
      boolean movable = isMovableValue(cur, s);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[93]++;
      Parameter p = new Parameter(cur, movable);
      setParameterSideEffectInfo(p, cur);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[94]++;
      parameters.add(p);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[95]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[96]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((movable) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[41]++;
        anyMovable = true;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[97]++;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[42]++;}
    }
    return anyMovable;
  }

  private void setParameterSideEffectInfo(Parameter p, Node value) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[98]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((p.hasSideEffects()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[43]++;
      p.setHasSideEffects(NodeUtil.mayHaveSideEffects(value, compiler));
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[99]++;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[44]++;}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[100]++;
int CodeCoverConditionCoverageHelper_C27;

    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((p.canBeSideEffected()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[45]++;
      p.setCanBeSideEffected(NodeUtil.canBeSideEffected(value));
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[101]++;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[46]++;}
  }


  /**
   * @return Whether the expression can be safely moved to another function
   *   in another scope.
   */
  private boolean isMovableValue(Node n, Scope s) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[102]++;
    // Things that can change value or are inaccessible can't be moved, these
    // are "this", "arguments", local names, and functions that capture local
    // values.
    switch (n.getType()) {
      case Token.THIS:
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[47]++;
        return false;
      case Token.FUNCTION:
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[48]++;
        // Don't move function closures.
        // TODO(johnlenz): Closure that only contain global reference can be
        // moved.
        return false;
      case Token.NAME:
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[49]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[103]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n.getString().equals("arguments")) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[50]++;
          return false;

        } else {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[51]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[104]++;
          Var v = s.getVar(n.getString());
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[105]++;
int CodeCoverConditionCoverageHelper_C29;
          // Make sure that the variable is global. A caught exception, while
          // it is in the global scope object in the compiler, it is not a
          // global variable.
          if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (32)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((v.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((v.nameNode.getParent().isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[52]++;
            return false;

          } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[53]++;}
        }
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[106]++;
        break; default : CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[54]++;
    }
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[107]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[31]++;


int CodeCoverConditionCoverageHelper_C30;

    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[31]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[32]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[33]++;
}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[108]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isMovableValue(c, s)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[55]++;
        return false;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[56]++;}
    }
    return true;
  }

  private void optimizeFunctionDefinition(List<Parameter> parameters,
      Node function) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[109]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[34]++;


int CodeCoverConditionCoverageHelper_C32;
    for (int index = parameters.size() - 1;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); index--) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[34]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[35]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[36]++;
}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[110]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((parameters.get(index).shouldRemove()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[57]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[111]++;
        Node paramName = eliminateFunctionParamAt(function, index);
        addVariableToFunction(function, paramName,
            parameters.get(index).getArg());
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[112]++;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[58]++;}
    }
  }

  private void optimizeCallSite(
      SimpleDefinitionFinder defFinder, List<Parameter> parameters, Node call) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[113]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[37]++;


int CodeCoverConditionCoverageHelper_C34;
    for (int index = parameters.size() - 1;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((index >= 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); index--) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[37]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[38]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[39]++;
}
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[114]++;
      Parameter p = parameters.get(index);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[115]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((p.shouldRemove()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[59]++;
        eliminateCallParamAt(defFinder, p, call, index);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[116]++;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[60]++;}
    }
  }

  /**
   * Simple container class that keeps tracks of a parameter and whether it
   * should be removed.
   */
  private static class Parameter {
    private final Node arg;
    private boolean shouldRemove;
    private boolean hasSideEffects;
    private boolean canBeSideEffected;

    public Parameter(Node arg, boolean shouldRemove) {
      this.shouldRemove = shouldRemove;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[117]++;
      this.arg = arg;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[118]++;
    }

    public Node getArg() {
      return arg;
    }

    public boolean shouldRemove() {
      return shouldRemove;
    }

    public void setShouldRemove(boolean value) {
      shouldRemove = value;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[119]++;
    }

    public void setHasSideEffects(boolean hasSideEffects) {
      this.hasSideEffects = hasSideEffects;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[120]++;
    }

    public boolean hasSideEffects() {
      return hasSideEffects;
    }

    public void setCanBeSideEffected(boolean canBeSideEffected) {
      this.canBeSideEffected = canBeSideEffected;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[121]++;
    }

    public boolean canBeSideEffected() {
      return canBeSideEffected;
    }
  }

  /**
   * Adds a variable to the top of a function block.
   * @param function A function node.
   * @param varName The name of the variable.
   * @param value The initial value of the variable.
   */
  private void addVariableToFunction(Node function, Node varName, Node value) {
    Preconditions.checkArgument(function.isFunction(),
        "Node must be a function.");
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[122]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[123]++;

    Node block = function.getLastChild();
    Preconditions.checkArgument(block.isBlock(),
        "Node must be a block.");
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[124]++;

    Preconditions.checkState(value.getParent() == null);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[125]++;
    Node stmt;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[126]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((varName != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[61]++;
      stmt = NodeUtil.newVarNode(varName.getString(), value);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[127]++;

    } else {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[62]++;
      stmt = IR.exprResult(value);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[128]++;
    }
    block.addChildToFront(stmt);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[129]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[130]++;
  }

  /**
   * Removes all formal parameters starting at argIndex.
   * @return true if a parameter has been removed.
   */
  private boolean eliminateParamsAfter(Node function, int argIndex) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[131]++;
    Node formalArgPtr = function.getFirstChild().getNext().getFirstChild();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[132]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[40]++;


int CodeCoverConditionCoverageHelper_C37;
    while ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((argIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((formalArgPtr != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[40]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[41]--;
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.loops[42]++;
}
      formalArgPtr = formalArgPtr.getNext();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[133]++;
      argIndex--;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[134]++;
    }

    return eliminateParamsAfter(function, formalArgPtr);
  }

  private boolean eliminateParamsAfter(Node fnNode, Node argNode) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[135]++;
int CodeCoverConditionCoverageHelper_C38;
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((argNode != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[63]++;
      // Keep the args in the same order, do the last first.
      eliminateParamsAfter(fnNode, argNode.getNext());
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[136]++;
      argNode.detachFromParent();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[137]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[138]++;
      Node var = IR.var(argNode).copyInformationFrom(argNode);
      fnNode.getLastChild().addChildrenToFront(var);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[139]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[140]++;
      return true;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[64]++;}
    return false;
  }

  /**
   * Eliminates the parameter from a function definition.
   * @param function The function node
   * @param argIndex The index of the the argument to remove.
   * @return The Node of the argument removed.
   */
  private Node eliminateFunctionParamAt(Node function, int argIndex) {
    Preconditions.checkArgument(function.isFunction(),
        "Node must be a function.");
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[141]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[142]++;

    Node formalArgPtr = NodeUtil.getArgumentForFunction(
        function, argIndex);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[143]++;
int CodeCoverConditionCoverageHelper_C39;

    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((formalArgPtr != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[65]++;
      function.getFirstChild().getNext().removeChild(formalArgPtr);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[144]++;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[66]++;}
    return formalArgPtr;
  }

  /**
   * Eliminates the parameter from a function call.
   * @param defFinder
   * @param p
   * @param call The function call node
   * @param argIndex The index of the the argument to remove.
   * @return The Node of the argument removed.
   */
  private Node eliminateCallParamAt(
      SimpleDefinitionFinder defFinder, Parameter p, Node call, int argIndex) {
    Preconditions.checkArgument(
        NodeUtil.isCallOrNew(call), "Node must be a call or new.");
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[145]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[146]++;

    Node formalArgPtr = NodeUtil.getArgumentForCallOrNew(
        call, argIndex);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[147]++;
int CodeCoverConditionCoverageHelper_C40;

    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((formalArgPtr != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[67]++;
      call.removeChild(formalArgPtr);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[148]++;
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[149]++;
int CodeCoverConditionCoverageHelper_C41;
      // The value in the parameter object is the one that is being moved into
      // function definition leave that one's references.  For everything else,
      // remove any references.
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((p.getArg() != formalArgPtr) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[69]++;
        removedNodes.add(formalArgPtr);
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[150]++;

      } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[70]++;}
      compiler.reportCodeChange();
CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.statements[151]++;

    } else {
  CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h.branches[68]++;}
    return formalArgPtr;
  }
}

class CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h ());
  }
    public static long[] statements = new long[152];
    public static long[] branches = new long[71];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[42];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.OptimizeParameters.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,0,1,1,1,1,0,1,1,1,1,3,1,1,1,1,1,1,1,2,1,1,1,1};
    for (int i = 1; i <= 41; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[43];

  public CodeCoverCoverageCounter$2kotauzaqal43d8b8zve1u7jmjn2xldrq44h () {
    super("com.google.javascript.jscomp.OptimizeParameters.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 151; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 70; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 41; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 42; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.OptimizeParameters.java");
      for (int i = 1; i <= 151; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 70; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 41; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 14; i++) {
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

