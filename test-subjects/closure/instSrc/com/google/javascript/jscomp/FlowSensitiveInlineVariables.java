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
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ControlFlowGraph.AbstractCfgNodeTraversalCallback;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.MustBeReachingVariableDef.Definition;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.AbstractShallowCallback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Inline variables when possible. Using the information from
 * {@link MaybeReachingVariableUse} and {@link MustBeReachingVariableDef},
 * this pass attempts to inline a variable by placing the value at the
 * definition where the variable is used. The basic requirements for inlining
 * are the following:
 *
 * <ul>
 * <li> There is exactly one reaching definition at the use of that variable
 * </li>
 * <li> There is exactly one use for that definition of the variable
 * </li>
 * </ul>
 *
 * <p>Other requirements can be found in {@link Candidate#canInline}. Currently
 * this pass does not operate on the global scope due to compilation time.
 *
 */
class FlowSensitiveInlineVariables extends AbstractPostOrderCallback
    implements CompilerPass, ScopedCallback {
  static {
    CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.ping();
  }


  /**
   * Implementation:
   *
   * This pass first perform a traversal to gather a list of Candidates that
   * could be inlined using {@link GatherCandiates}.
   *
   * The second step involves verifying that each candidate is actually safe
   * to inline with {@link Candidate#canInline(Scope)} and finally perform
   * inlining using {@link Candidate#inlineVariable()}.
   *
   * The reason for the delayed evaluation of the candidates is because we
   * need two separate dataflow result.
   */
  private final AbstractCompiler compiler;
  private final Set<Var> inlinedNewDependencies = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[1]++;
  }

  // These two pieces of data is persistent in the whole execution of enter
  // scope.
  private ControlFlowGraph<Node> cfg;
  private List<Candidate> candidates;
  private MustBeReachingVariableDef reachingDef;
  private MaybeReachingVariableUse reachingUses;

  private static final Predicate<Node> SIDE_EFFECT_PREDICATE =
    new Predicate<Node>() {
      @Override
      public boolean apply(Node n) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        // When the node is null it means, we reached the implicit return
        // where the function returns (possibly without an return statement)
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[1]++;
          return false;

        } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[2]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;

        // TODO(user): We only care about calls to functions that
        // passes one of the dependent variable to a non-side-effect free
        // function.
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((NodeUtil.functionCallHasSideEffects(n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[3]++;
          return true;

        } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[4]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[4]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((n.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.constructorCallHasSideEffects(n)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[5]++;
          return true;

        } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[6]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[5]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isDelProp()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[7]++;
          return true;

        } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[8]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;

        for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[1]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[2]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[3]++;
}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[7]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((ControlFlowGraph.isEnteringNewCfgNode(c)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((apply(c)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[9]++;
            return true;

          } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[10]++;}
        }
        return false;
      }
  };
  static {
    CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[8]++;
  }

  public FlowSensitiveInlineVariables(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[9]++;
  }

  @Override
  public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[10]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[11]++;
      return;
 // Don't even brother. All global variables are likely escaped.
    } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[12]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[11]++;
int CodeCoverConditionCoverageHelper_C8;

    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((LiveVariablesAnalysis.MAX_VARIABLES_TO_ANALYZE <
        t.getScope().getVarCount()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[13]++;
      return;

    } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[14]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[12]++;

    // Compute the forward reaching definition.
    ControlFlowAnalysis cfa = new ControlFlowAnalysis(compiler, false, true);
    // Process the body of the function.
    Preconditions.checkState(t.getScopeRoot().isFunction());
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[13]++;
    cfa.process(null, t.getScopeRoot().getLastChild());
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[14]++;
    cfg = cfa.getCfg();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[15]++;
    reachingDef = new MustBeReachingVariableDef(cfg, t.getScope(), compiler);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[16]++;
    reachingDef.analyze();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[17]++;
    candidates = Lists.newLinkedList();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[18]++;

    // Using the forward reaching definition search to find all the inline
    // candidates
    new NodeTraversal(compiler, new GatherCandiates()).traverse(
        t.getScopeRoot().getLastChild());
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[19]++;

    // Compute the backward reaching use. The CFG can be reused.
    reachingUses = new MaybeReachingVariableUse(cfg, t.getScope(), compiler);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[20]++;
    reachingUses.analyze();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[21]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[22]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[4]++;


    for (Candidate c : candidates) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[4]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[5]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[6]++;
}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[23]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((c.canInline(t.getScope())) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[15]++;
        c.inlineVariable();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[24]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[25]++;
int CodeCoverConditionCoverageHelper_C10;

        // If definition c has dependencies, then inlining it may have
        // introduced new dependencies for our other inlining candidates.
        //
        // MustBeReachingVariableDef uses this dependency graph in its
        // analysis, so some of these candidates may no longer be valid.
        // We keep track of when the variable dependency graph changed
        // so that we can back off appropriately.
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((c.defMetadata.depends.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[17]++;
          inlinedNewDependencies.add(t.getScope().getVar(c.varName));
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[26]++;

        } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[18]++;}

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[16]++;}
    }
  }

  @Override
  public void exitScope(NodeTraversal t) {}

  @Override
  public void process(Node externs, Node root) {
    (new NodeTraversal(compiler, this)).traverseRoots(externs, root);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[27]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
    // TODO(user): While the helpers do a subtree traversal on the AST, the
    // compiler pass itself only traverse the AST to look for function
    // declarations to perform dataflow analysis on. We could combine
    // the traversal in DataFlowAnalysis's computeEscaped later to save some
    // time.
  }

  /**
   * Gathers a list of possible candidates for inlining based only on
   * information from {@link MustBeReachingVariableDef}. The list will be stored
   * in {@code candidates} and the validity of each inlining Candidate should
   * be later verified with {@link Candidate#canInline(Scope)} when
   * {@link MaybeReachingVariableUse} has been performed.
   */
  private class GatherCandiates extends AbstractShallowCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[28]++;
      DiGraphNode<Node, Branch> graphNode = cfg.getDirectedGraphNode(n);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[29]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((graphNode == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[19]++;
        // Not a CFG node.
        return;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[20]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[30]++;
      final Node cfgNode = n;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[31]++;
      AbstractCfgNodeTraversalCallback gatherCb =
          new AbstractCfgNodeTraversalCallback() {

        @Override
        public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[32]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[21]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[33]++;
int CodeCoverConditionCoverageHelper_C13;

            // n.getParent() isn't null. This just the case where n is the root
            // node that gatherCb started at.
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[23]++;
              return;

            } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[24]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[34]++;
int CodeCoverConditionCoverageHelper_C14;

            // Make sure that the name node is purely a read.
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C14 |= (8192)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4096)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2048)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1024)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C14 |= (512)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (128)) == 0 || true) &&
 ((parent.isInc()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((parent.isDec()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((parent.isParamList()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((parent.isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 7) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 7) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[25]++;
              return;

            } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[26]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[35]++;

            String name = n.getString();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[36]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(name)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[27]++;
              return;

            } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[28]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[37]++;

            Definition def = reachingDef.getDef(name, cfgNode);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[38]++;
int CodeCoverConditionCoverageHelper_C16;
            // TODO(nicksantos): We need to add some notion of @const outer
            // scope vars. We can inline those just fine.
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((def != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((reachingDef.dependsOnOuterScopeVars(def)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[29]++;
              candidates.add(new Candidate(name, def, n, cfgNode));
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[39]++;

            } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[30]++;}

          } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[22]++;}
        }
      };

      NodeTraversal.traverse(compiler, cfgNode, gatherCb);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[40]++;
    }
  }

  /**
   * Models the connection between a definition and a use of that definition.
   */
  private class Candidate {

    // Name of the variable.
    private final String varName;

    // Nodes related to the definition.
    private Node def;
    private final Definition defMetadata;

    // Nodes related to the use.
    private final Node use;
    private final Node useCfgNode;

    // Number of uses of the variable within the CFG node that represented the
    // use in the CFG.
    private int numUseWithinUseCfgNode;

    Candidate(String varName, Definition defMetadata,
        Node use, Node useCfgNode) {
      Preconditions.checkArgument(use.isName());
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[41]++;
      this.varName = varName;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[42]++;
      this.defMetadata = defMetadata;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[43]++;
      this.use = use;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[44]++;
      this.useCfgNode = useCfgNode;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[45]++;
    }

    private Node getDefCfgNode() {
      return defMetadata.node;
    }

    private boolean canInline(final Scope scope) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[46]++;
int CodeCoverConditionCoverageHelper_C17;
      // Cannot inline a parameter.
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((getDefCfgNode().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[31]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[32]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[47]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[7]++;



      // If one of our dependencies has been inlined, then our dependency
      // graph is wrong. Re-computing it would take another CFG computation,
      // so we just back off for now.
      for (Var dependency : defMetadata.depends) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[7]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[8]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[9]++;
}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[48]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((inlinedNewDependencies.contains(dependency)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[33]++;
          return false;

        } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[34]++;}
      }

      getDefinition(getDefCfgNode());
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[49]++;
      getNumUseInUseCfgNode(useCfgNode);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[50]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[51]++;
int CodeCoverConditionCoverageHelper_C19;

      // Definition was not found.
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((def == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[35]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[36]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[52]++;
int CodeCoverConditionCoverageHelper_C20;

      // Check that the assignment isn't used as a R-Value.
      // TODO(user): Certain cases we can still inline.
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((def.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(def.getParent())) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[37]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[38]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[53]++;
int CodeCoverConditionCoverageHelper_C21;

      // The right of the definition has side effect:
      // Example, for x:
      // x = readProp(b), modifyProp(b); print(x);
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((checkRightOf(def, getDefCfgNode(), SIDE_EFFECT_PREDICATE)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[39]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[40]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[54]++;
int CodeCoverConditionCoverageHelper_C22;

      // Similar check as the above but this time, all the sub-expressions
      // left of the use of the variable.
      // x = readProp(b); modifyProp(b), print(x);
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((checkLeftOf(use, useCfgNode, SIDE_EFFECT_PREDICATE)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[41]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[42]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[55]++;
int CodeCoverConditionCoverageHelper_C23;

      // TODO(user): Side-effect is OK sometimes. As long as there are no
      // side-effect function down all paths to the use. Once we have all the
      // side-effect analysis tool.
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((NodeUtil.mayHaveSideEffects(def.getLastChild(), compiler)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[43]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[44]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[56]++;
int CodeCoverConditionCoverageHelper_C24;

      // TODO(user): We could inline all the uses if the expression is short.

      // Finally we have to make sure that there are no more than one use
      // in the program and in the CFG node. Even when it is semantically
      // correctly inlining twice increases code size.
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((numUseWithinUseCfgNode != 1) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[45]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[46]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[57]++;
int CodeCoverConditionCoverageHelper_C25;

      // Make sure that the name is not within a loop
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((NodeUtil.isWithinLoop(use)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[47]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[48]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[58]++;


      Collection<Node> uses = reachingUses.getUses(varName, getDefCfgNode());
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[59]++;
int CodeCoverConditionCoverageHelper_C26;

      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((uses.size() != 1) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[49]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[50]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[60]++;
int CodeCoverConditionCoverageHelper_C27;

      // We give up inlining stuff with R-Value that has:
      // 1) GETPROP, GETELEM,
      // 2) anything that creates a new object.
      // 3) a direct reference to a catch expression.
      // Example:
      // var x = a.b.c; j.c = 1; print(x);
      // Inlining print(a.b.c) is not safe consider j and be alias to a.b.
      // TODO(user): We could get more accuracy by looking more in-detail
      // what j is and what x is trying to into to.
      // TODO(johnlenz): rework catch expression handling when we
      // have lexical scope support so catch expressions don't
      // need to be special cased.
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((NodeUtil.has(def.getLastChild(),
          new Predicate<Node>() {
              @Override
              public boolean apply(Node input) {
                switch (input.getType()) {
                  case Token.GETELEM:
                  case Token.GETPROP:
                  case Token.ARRAYLIT:
                  case Token.OBJECTLIT:
                  case Token.REGEXP:
                  case Token.NEW:
                    return true;
                  case Token.NAME:
                    Var var = scope.getOwnSlot(input.getString());
                    if (var != null
                        && var.getParentNode().isCatch()) {
                      return true;
                    }
                }
                return false;
              }
          },
          new Predicate<Node>() {
              @Override
              public boolean apply(Node input) {
                // Recurse if the node is not a function.
                return !input.isFunction();
              }
          })) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[51]++;
        return false;

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[52]++;}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[61]++;
int CodeCoverConditionCoverageHelper_C28;

      // We can skip the side effect check along the paths of two nodes if
      // they are just next to each other.
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(getDefCfgNode().getParent())) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((getDefCfgNode().getNext() != useCfgNode) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[53]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[62]++;
        // Similar side effect check as above but this time the side effect is
        // else where along the path.
        // x = readProp(b); while(modifyProp(b)) {}; print(x);
        CheckPathsBetweenNodes<Node, ControlFlowGraph.Branch>
          pathCheck = new CheckPathsBetweenNodes<Node, ControlFlowGraph.Branch>(
                 cfg,
                 cfg.getDirectedGraphNode(getDefCfgNode()),
                 cfg.getDirectedGraphNode(useCfgNode),
                 SIDE_EFFECT_PREDICATE,
                 Predicates.
                     <DiGraphEdge<Node, ControlFlowGraph.Branch>>alwaysTrue(),
                 false);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[63]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((pathCheck.somePathsSatisfyPredicate()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[55]++;
          return false;

        } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[56]++;}

      } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[54]++;}

      return true;
    }

    /**
     * Actual transformation.
     */
    private void inlineVariable() {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[64]++;
      Node defParent = def.getParent();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[65]++;
      Node useParent = use.getParent();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[66]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((def.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[57]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[67]++;
        Node rhs = def.getLastChild();
        rhs.detachFromParent();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[68]++;
        // Oh yes! I have grandparent to remove this.
        Preconditions.checkState(defParent.isExprResult());
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[69]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[70]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[10]++;


int CodeCoverConditionCoverageHelper_C31;
        while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((defParent.getParent().isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[10]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[11]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[12]++;
}
          defParent = defParent.getParent();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[71]++;
        }
        defParent.detachFromParent();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[72]++;
        useParent.replaceChild(use, rhs);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[73]++;

      } else {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[58]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[74]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((defParent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[59]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[75]++;
        Node rhs = def.getLastChild();
        def.removeChild(rhs);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[76]++;
        useParent.replaceChild(use, rhs);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[77]++;

      } else {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[60]++;
        Preconditions.checkState(false, "No other definitions can be inlined.");
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[78]++;
      }
}
      compiler.reportCodeChange();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[79]++;
    }

    /**
     * Set the def node
     *
     * @param n A node that has a corresponding CFG node in the CFG.
     */
    private void getDefinition(Node n) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[80]++;
      AbstractCfgNodeTraversalCallback gatherCb =
        new AbstractCfgNodeTraversalCallback() {

        @Override
        public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[81]++;
          switch (n.getType()) {
            case Token.NAME:
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[61]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[82]++;
int CodeCoverConditionCoverageHelper_C33;
              if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((n.getString().equals(varName)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[62]++;
                def = n;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[83]++;

              } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[63]++;}
              return;

            case Token.ASSIGN:
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[64]++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[84]++;
              Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[85]++;
int CodeCoverConditionCoverageHelper_C34;
              if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((lhs.getString().equals(varName)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[65]++;
                def = n;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[86]++;

              } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[66]++;}
              return; default : CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[67]++;
          }
        }
      };
      NodeTraversal.traverse(compiler, n, gatherCb);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[87]++;
    }

    /**
     * Computes the number of uses of the variable varName and store it in
     * numUseWithinUseCfgNode.
     */
    private void getNumUseInUseCfgNode(Node n) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[88]++;

      AbstractCfgNodeTraversalCallback gatherCb =
          new AbstractCfgNodeTraversalCallback() {

        @Override
        public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[89]++;
int CodeCoverConditionCoverageHelper_C35;
          if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (128)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (32)) == 0 || true) &&
 ((n.getString().equals(varName)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (16)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 4) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 4) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[68]++;
            numUseWithinUseCfgNode++;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[90]++;

          } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[69]++;}
        }
      };

      NodeTraversal.traverse(compiler, n, gatherCb);
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[91]++;
    }
  }

  /**
   * Given an expression by its root and sub-expression n, return true if there
   * the predicate is true for some expression on the right of n.
   *
   * Example:
   *
   * NotChecked(), NotChecked(), n, Checked(), Checked();
   */
  private static boolean checkRightOf(
      Node n, Node expressionRoot, Predicate<Node> predicate) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[92]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[13]++;


int CodeCoverConditionCoverageHelper_C36;
    for (Node p = n;(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((p != expressionRoot) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false); p = p.getParent()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[13]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[14]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[15]++;
}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[93]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[16]++;


int CodeCoverConditionCoverageHelper_C37;
      for (Node cur = p.getNext();(((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false); cur = cur.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[16]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[17]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[18]++;
}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[94]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((predicate.apply(cur)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[70]++;
          return true;

        } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[71]++;}
      }
    }
    return false;
  }

  /**
   * Given an expression by its root and sub-expression n, return true if there
   * the predicate is true for some expression on the left of n.
   *
   * Example:
   *
   * Checked(), Checked(), n, NotChecked(), NotChecked();
   */
  private static boolean checkLeftOf(
      Node n, Node expressionRoot, Predicate<Node> predicate) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[95]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[19]++;


int CodeCoverConditionCoverageHelper_C39;
    for (Node p = n.getParent();(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((p != expressionRoot) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); p = p.getParent()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[19]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[20]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[21]++;
}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[96]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[22]++;


int CodeCoverConditionCoverageHelper_C40;
      for (Node cur = p.getParent().getFirstChild();(((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((cur != p) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false);
          cur = cur.getNext()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[22]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[23]--;
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.loops[24]++;
}
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.statements[97]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((predicate.apply(cur)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[72]++;
          return true;

        } else {
  CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl.branches[73]++;}
      }
    }
    return false;
  }
}

class CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl ());
  }
    public static long[] statements = new long[98];
    public static long[] branches = new long[74];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[42];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.FlowSensitiveInlineVariables.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,1,1,2,1,1,1,1,1,1,1,3,1,2,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,2,2,3,1,1,1,1,1,1};
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
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$chc7rebmfepwv57bvlknr8l33x1miasd5es3ijy2hcv81q8m2jl () {
    super("com.google.javascript.jscomp.FlowSensitiveInlineVariables.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 97; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 73; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 41; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.FlowSensitiveInlineVariables.java");
      for (int i = 1; i <= 97; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 73; i++) {
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

