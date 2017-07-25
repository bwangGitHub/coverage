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

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ControlFlowGraph.AbstractCfgNodeTraversalCallback;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.DataFlowAnalysis.FlowState;
import com.google.javascript.jscomp.LiveVariablesAnalysis.LiveVariableLattice;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;
import com.google.javascript.jscomp.graph.GraphColoring;
import com.google.javascript.jscomp.graph.GraphColoring.GreedyGraphColoring;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.jscomp.graph.LinkedUndirectedGraph;
import com.google.javascript.jscomp.graph.UndiGraph;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.Set;

/**
 * Reuse variable names if possible.
 *
 * <p>For example, from <code>var x = 1; print(x); var y = 2; print(y); </code>
 * to <code>var x = 1; print(x); x = 2; print(x)</code>. The benefits are
 * slightly shorter code because of the removed <code>var<code> declaration,
 * less unique variables in hope for better renaming, and finally better gzip
 * compression.
 *
 * <p>The pass operates similar to a typical register allocator found in an
 * optimizing compiler by first computing live ranges with
 * {@link LiveVariablesAnalysis} and a variable interference graph. Then it uses
 * graph coloring in {@link GraphColoring} to determine which two variables can
 * be merge together safely.
 *
 */
class CoalesceVariableNames extends AbstractPostOrderCallback implements
    CompilerPass, ScopedCallback {
  static {
    CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.ping();
  }


  private final AbstractCompiler compiler;
  private final Deque<GraphColoring<Var, Void>> colorings;
  private final boolean usePseudoNames;

  private static final Comparator<Var> coloringTieBreaker =
      new Comparator<Var>() {
    @Override
    public int compare(Var v1, Var v2) {
      return v1.index - v2.index;
    }
  };
  static {
    CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[1]++;
  }

  /**
   * @param usePseudoNames For debug purposes, when merging variable foo and bar
   * to foo, rename both variable to foo_bar.
   */
  CoalesceVariableNames(AbstractCompiler compiler, boolean usePseudoNames) {
    Preconditions.checkState(!compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[2]++;

    this.compiler = compiler;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[3]++;
    colorings = Lists.newLinkedList();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[4]++;
    this.usePseudoNames = usePseudoNames;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[5]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[6]++;
  }

  private static boolean shouldOptimizeScope(Scope scope) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    // TODO(user): We CAN do this in the global scope, just need to be
    // careful when something is exported. Liveness uses bit-vector for live
    // sets so I don't see compilation time will be a problem for running this
    // pass in the global scope.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((scope.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[1]++;
      return false;

    } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[2]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((LiveVariablesAnalysis.MAX_VARIABLES_TO_ANALYZE <
        scope.getVarCount()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[3]++;
      return false;

    } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[4]++;}

    return true;
  }

  @Override
  public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[9]++;
    Scope scope = t.getScope();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((shouldOptimizeScope(scope)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[5]++;
      return;

    } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[6]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[11]++;

    ControlFlowGraph<Node> cfg = t.getControlFlowGraph();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[12]++;
    LiveVariablesAnalysis liveness =
        new LiveVariablesAnalysis(cfg, scope, compiler);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
    // If the function has exactly 2 params, mark them as escaped. This is
    // a work-around for an IE bug where it throws an exception if you
    // write to the parameters of the callback in a sort(). See:
    // http://code.google.com/p/closure-compiler/issues/detail?id=58
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((scope.getRootNode().getFirstChild().getNext().getChildCount() == 2) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[7]++;
      liveness.markAllParametersEscaped();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[14]++;

    } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[8]++;}
    liveness.analyze();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[15]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[16]++;

    UndiGraph<Var, Void> interferenceGraph =
        computeVariableNamesInterferenceGraph(
            t, cfg, liveness.getEscapedLocals());
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[17]++;

    GraphColoring<Var, Void> coloring =
        new GreedyGraphColoring<Var, Void>(interferenceGraph,
            coloringTieBreaker);

    coloring.color();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[18]++;
    colorings.push(coloring);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[19]++;
  }

  @Override
  public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((shouldOptimizeScope(t.getScope())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[9]++;
      return;

    } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[10]++;}
    colorings.pop();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[21]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[22]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((colorings.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[11]++;
      // Don't rename named functions.
      return;

    } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[12]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[23]++;
    Var var = t.getScope().getVar(n.getString());
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[24]++;
    GraphNode<Var, ?> vNode = colorings.peek().getGraph().getNode(var);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((vNode == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[13]++;
      // This is not a local.
      return;

    } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[14]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[26]++;
    Var coalescedVar = colorings.peek().getPartitionSuperNode(var);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[27]++;
int CodeCoverConditionCoverageHelper_C8;

    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((usePseudoNames) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[15]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((vNode.getValue().equals(coalescedVar)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[17]++;
        // The coalesced name is itself, nothing to do.
        return;

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[18]++;}

      // Rename.
      n.setString(coalescedVar.name);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[29]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[30]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[31]++;
int CodeCoverConditionCoverageHelper_C10;

      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[19]++;
        removeVarDeclaration(n);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[32]++;

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[20]++;}

    } else {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[16]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[33]++;
      // This code block is slow but since usePseudoName is for debugging,
      // we should not sacrifice performance for non-debugging compilation to
      // make this fast.
      String pseudoName = null;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[34]++;
      Set<String> allMergedNames = Sets.newTreeSet();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[35]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;
      for (Iterator<Var> i = t.getScope().getVars();(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[1]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[2]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[3]++;
}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[36]++;
        Var iVar = i.next();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[37]++;
int CodeCoverConditionCoverageHelper_C12;

        // Look for all the variables that can be merged (in the graph by now)
        // and it is merged with the current coalescedVar.
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((colorings.peek().getGraph().getNode(iVar) != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((coalescedVar.equals(colorings.peek().getPartitionSuperNode(iVar))) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[21]++;
          allMergedNames.add(iVar.name);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[38]++;

        } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[22]++;}
      }
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[39]++;
int CodeCoverConditionCoverageHelper_C13;

      // Keep its original name.
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((allMergedNames.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[23]++;
        return;

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[24]++;}

      pseudoName = Joiner.on("_").join(allMergedNames);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[40]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[41]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[4]++;


int CodeCoverConditionCoverageHelper_C14;

      while ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((t.getScope().isDeclared(pseudoName, true)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[4]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[5]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[6]++;
}
        pseudoName += "$";
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[42]++;
      }

      n.setString(pseudoName);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[43]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[44]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[45]++;
int CodeCoverConditionCoverageHelper_C15;

      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((vNode.getValue().equals(coalescedVar)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[25]++;
        removeVarDeclaration(n);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[46]++;

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[26]++;}
    }
  }

  private UndiGraph<Var, Void> computeVariableNamesInterferenceGraph(
      NodeTraversal t, ControlFlowGraph<Node> cfg, Set<Var> escaped) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[47]++;
    UndiGraph<Var, Void> interferenceGraph =
        LinkedUndirectedGraph.create();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[48]++;
    Scope scope = t.getScope();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[49]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[7]++;


int CodeCoverConditionCoverageHelper_C16;

    // First create a node for each non-escaped variable.
    for (Iterator<Var> i = scope.getVars();(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false);) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[7]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[8]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[9]++;
}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[50]++;
      Var v = i.next();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[51]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((escaped.contains(v)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[27]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[52]++;
int CodeCoverConditionCoverageHelper_C18;

        // TODO(user): In theory, we CAN coalesce function names just like
        // any variables. Our Liveness analysis captures this just like it as
        // described in the specification. However, we saw some zipped and
        // and unzipped size increase after this. We are not totally sure why
        // that is but, for now, we will respect the dead functions and not play
        // around with it.
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((v.getParentNode().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[29]++;
          interferenceGraph.createNode(v);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[53]++;

        } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[30]++;}

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[28]++;}
    }
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[54]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;

    // Go through each variable and try to connect them.
    for (Iterator<Var> i1 = scope.getVars();(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i1.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false);) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[10]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[11]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[12]++;
}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[55]++;
      Var v1 = i1.next();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[56]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[13]++;


int CodeCoverConditionCoverageHelper_C20;

      NEXT_VAR_PAIR:
      for (Iterator<Var> i2 = scope.getVars();(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i2.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false);) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[13]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[14]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[15]++;
}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[57]++;
        Var v2 = i2.next();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[58]++;
int CodeCoverConditionCoverageHelper_C21;

        // Skip duplicate pairs.
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((v1.index >= v2.index) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[31]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[59]++;
          continue;

        } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[32]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[60]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((interferenceGraph.hasNode(v1)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((interferenceGraph.hasNode(v2)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[33]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[61]++;
          // Skip nodes that were not added. They are globals and escaped
          // locals. Also avoid merging a variable with itself.
          continue NEXT_VAR_PAIR;

        } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[34]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[62]++;
int CodeCoverConditionCoverageHelper_C23;

        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((v1.getParentNode().isParamList()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((v2.getParentNode().isParamList()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[35]++;
          interferenceGraph.connectIfNotFound(v1, null, v2);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[63]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[64]++;
          continue NEXT_VAR_PAIR;

        } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[36]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[65]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[16]++;



        // Go through every CFG node in the program and look at
        // this variable pair. If they are both live at the same
        // time, add an edge between them and continue to the next pair.
        NEXT_CROSS_CFG_NODE:
        for (DiGraphNode<Node, Branch> cfgNode : cfg.getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[16]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[17]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[18]++;
}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[66]++;
int CodeCoverConditionCoverageHelper_C24;
          if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((cfg.isImplicitReturn(cfgNode)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[37]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[67]++;
            continue NEXT_CROSS_CFG_NODE;

          } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[38]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[68]++;

          FlowState<LiveVariableLattice> state = cfgNode.getAnnotation();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[69]++;
int CodeCoverConditionCoverageHelper_C25;
          // Check the live states and add edge when possible.
          if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C25 |= (128)) == 0 || true) &&
 ((state.getIn().isLive(v1)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((state.getIn().isLive(v2)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((state.getOut().isLive(v1)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((state.getOut().isLive(v2)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 4) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 4) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[39]++;
            interferenceGraph.connectIfNotFound(v1, null, v2);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[70]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[71]++;
            continue NEXT_VAR_PAIR;

          } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[40]++;}
        }
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[72]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[19]++;



        // v1 and v2 might not have an edge between them! woohoo. there's
        // one last sanity check that we have to do: we have to check
        // if there's a collision *within* the cfg node.
        NEXT_INTRA_CFG_NODE:
        for (DiGraphNode<Node, Branch> cfgNode : cfg.getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[19]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[20]--;
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.loops[21]++;
}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[73]++;
int CodeCoverConditionCoverageHelper_C26;
          if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((cfg.isImplicitReturn(cfgNode)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[41]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[74]++;
            continue NEXT_INTRA_CFG_NODE;

          } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[42]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[75]++;

          FlowState<LiveVariableLattice> state = cfgNode.getAnnotation();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[76]++;
          boolean v1OutLive = state.getOut().isLive(v1);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[77]++;
          boolean v2OutLive = state.getOut().isLive(v2);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[78]++;
          CombinedLiveRangeChecker checker = new CombinedLiveRangeChecker(
              new LiveRangeChecker(v1, v2OutLive ? null : v2),
              new LiveRangeChecker(v2, v1OutLive ? null : v1));
          NodeTraversal.traverse(
              compiler,
              cfgNode.getValue(),
              checker);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[79]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[80]++;
int CodeCoverConditionCoverageHelper_C27;
          if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((checker.connectIfCrossed(interferenceGraph)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[43]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[81]++;
            continue NEXT_VAR_PAIR;

          } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[44]++;}
        }
      }
    }
    return interferenceGraph;
  }

  /**
   * A simple wrapper calls to call two AbstractCfgNodeTraversalCallback
   * callback during the same traversal.  Both traversals must have the same
   * "shouldTraverse" conditions.
   */
  private static class CombinedLiveRangeChecker
      extends AbstractCfgNodeTraversalCallback {

    private final LiveRangeChecker callback1;
    private final LiveRangeChecker callback2;

    CombinedLiveRangeChecker(
        LiveRangeChecker callback1,
        LiveRangeChecker callback2) {
      this.callback1 = callback1;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[82]++;
      this.callback2 = callback2;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[83]++;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[84]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((LiveRangeChecker.shouldVisit(n)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[45]++;
        callback1.visit(t, n, parent);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[85]++;
        callback2.visit(t, n, parent);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[86]++;

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[46]++;}
    }

    boolean connectIfCrossed(UndiGraph<Var, Void> interferenceGraph) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[87]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((callback1.crossed) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((callback2.crossed) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[47]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[88]++;
        Var v1 = callback1.getDef();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[89]++;
        Var v2 = callback2.getDef();
        interferenceGraph.connectIfNotFound(v1, null, v2);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[90]++;
        return true;

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[48]++;}
      return false;
    }
  }

  /**
   * Tries to remove variable declaration if the variable has been coalesced
   * with another variable that has already been declared.
   */
  private void removeVarDeclaration(Node name) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[91]++;
    Node var = name.getParent();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[92]++;
    Node parent = var.getParent();
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[93]++;
int CodeCoverConditionCoverageHelper_C30;

    // Special case when we are in FOR-IN loop.
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(parent)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[49]++;
      var.removeChild(name);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[94]++;
      parent.replaceChild(var, name);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[95]++;

    } else {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[50]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[96]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((var.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[51]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[97]++;
int CodeCoverConditionCoverageHelper_C32;
      // The removal is easy when there is only one variable in the VAR node.
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((name.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[53]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[98]++;
        Node value = name.removeFirstChild();
        var.removeChild(name);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[99]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[100]++;
        Node assign = IR.assign(name, value).srcref(name);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[101]++;
int CodeCoverConditionCoverageHelper_C33;

        // We don't need to wrapped it with EXPR node if it is within a FOR.
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((parent.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[55]++;
          assign = NodeUtil.newExpr(assign);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[102]++;

        } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[56]++;}
        parent.replaceChild(var, assign);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[103]++;


      } else {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[54]++;
        // In a FOR( ; ; ) node, we must replace it with an EMPTY or else it
        // becomes a FOR-IN node.
        NodeUtil.removeChild(parent, var);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[104]++;
      }

    } else {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[52]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[105]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((name.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[57]++;
        var.removeChild(name);
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[106]++;

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[58]++;}
      // We are going to leave duplicated declaration otherwise.
    }
}
  }

  private static class LiveRangeChecker
      extends AbstractCfgNodeTraversalCallback {
    boolean defFound = false;
  {
    CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[107]++;
  }
    boolean crossed = false;
  {
    CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[108]++;
  }
    private final Var def;
    private final Var use;

    public LiveRangeChecker(Var def, Var use) {
      this.def = def;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[109]++;
      this.use = use;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[110]++;
    }

    Var getDef() {
      return def;
    }

    /**
     * @return Whether any LiveRangeChecker would be interested in the node.
     */
    public static boolean shouldVisit(Node n) {
      return (n.isName()
        || (n.hasChildren() && n.getFirstChild().isName()));
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[111]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((defFound) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((isAssignTo(def, n, parent)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[59]++;
        defFound = true;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[112]++;

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[60]++;}
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[113]++;
int CodeCoverConditionCoverageHelper_C36;

      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (32)) == 0 || true) &&
 ((defFound) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((use == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((isReadFrom(use, n)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[61]++;
        crossed = true;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[114]++;

      } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[62]++;}
    }

    private static boolean isAssignTo(Var var, Node n, Node parent) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[115]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (32)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((var.getName().equals(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[63]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[116]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((parent.isParamList()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[65]++;
          // In a function declaration, the formal parameters are assigned.
          return true;

        } else {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[66]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[117]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[67]++;
          // If this is a VAR declaration, if the name node has a child, we are
          // assigning to that name.
          return n.hasChildren();

        } else {
  CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[68]++;}
}
        return false;
 // Definitely a read.
      } else {
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.branches[64]++;
CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip.statements[118]++;
        // Lastly, any assignmentOP is also an assign.
        Node name = n.getFirstChild();
        return name != null && name.isName() &&
          var.getName().equals(name.getString()) &&
          NodeUtil.isAssignmentOp(n);
      }
    }

    private static boolean isReadFrom(Var var, Node name) {
      return name != null && name.isName() &&
          var.getName().equals(name.getString()) &&
          !NodeUtil.isVarOrSimpleAssignLhs(name, name.getParent());
    }
  }
}

class CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip ());
  }
    public static long[] statements = new long[119];
    public static long[] branches = new long[69];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[40];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CoalesceVariableNames.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,3,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,2,2,1,3,1,1,1,2,1,1,1,1,1,2,3,3,1,1};
    for (int i = 1; i <= 39; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$ltxvmgz7fzcoonilpoes1fzh85j00jjnyejwccip () {
    super("com.google.javascript.jscomp.CoalesceVariableNames.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 118; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 68; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 39; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CoalesceVariableNames.java");
      for (int i = 1; i <= 118; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 68; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 39; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

