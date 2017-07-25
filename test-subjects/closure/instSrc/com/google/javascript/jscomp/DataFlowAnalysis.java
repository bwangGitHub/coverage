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

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.Annotation;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;
import com.google.javascript.jscomp.graph.LatticeElement;
import com.google.javascript.rhino.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A framework to help writing static program analysis. A subclass of
 * this framework should specify how a single node changes the state
 * of a program. This class finds a safe estimate (a fixed-point) for
 * the whole program. The proven facts about the program will be
 * annotated with
 * {@link com.google.javascript.jscomp.graph.GraphNode#setAnnotation} to the
 * given control flow graph's nodes in form of {@link LatticeElement}
 * after calling {@link #analyze()}.
 *
 * <p>As a guideline, the following is a list of behaviors that any analysis
 * can take:
 * <ol>
 * <li>Flow Direction: Is the analysis a forward or backward analysis?
 * <li>Lattice Elements: How does the analysis represent the state of the
 * program at any given point?
 * <li>JOIN Operation: Given two incoming paths and a lattice state value, what
 * can the compiler conclude at the join point?
 * <li>Flow Equations: How does an instruction modify the state of program in
 * terms of lattice values?
 * <li>Initial Entry Value: What can the compiler assume at the beginning of the
 * program?
 * <li>Initial Estimate: What can the compiler assume at each point of the
 * program? (What is the BOTTOM value of the lattice) By definition this lattice
 * JOIN {@code x} for any {@code x} must also be {@code x}.
 * </ol>
 * To make these behaviors known to the framework, the following steps must be
 * taken.
 * <ol>
 * <li>Flow Direction: Implement {@link #isForward()}.
 * <li>Lattice Elements: Implement {@link LatticeElement}.
 * <li>JOIN Operation: Implement
 *    {@link JoinOp#apply}.
 * <li>Flow Equations: Implement
 * {@link #flowThrough(Object, LatticeElement)}.
 * <li>Initial Entry Value: Implement {@link #createEntryLattice()}.
 * <li>Initial Estimate: Implement {@link #createInitialEstimateLattice()}.
 * </ol>
 *
 * <p>Upon execution of the {@link #analyze()} method, nodes of the input
 * control flow graph will be annotated with a {@link FlowState} object that
 * represents maximum fixed point solution. Any previous annotations at the
 * nodes of the control flow graph will be lost.
 *
 *
 * @param <N> The control flow graph's node value type.
 * @param <L> Lattice element type.
 */
abstract class DataFlowAnalysis<N, L extends LatticeElement> {
  static {
    CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.ping();
  }


  private final ControlFlowGraph<N> cfg;
  final JoinOp<L> joinOp;
  protected final Set<DiGraphNode<N, Branch>> orderedWorkSet;

  /*
   * Feel free to increase this to a reasonable number if you are finding that
   * more and more passes need more than 200000 steps before finding a
   * fixed-point. If you just have a special case, consider calling
   * {@link #analyse(int)} instead.
   */
  public static final int MAX_STEPS = 200000;
  static {
    CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[1]++;
  }

  /**
   * Constructs a data flow analysis.
   *
   * <p>Typical usage
   * <pre>
   * DataFlowAnalysis dfa = ...
   * dfa.analyze();
   * </pre>
   *
   * {@link #analyze()} annotates the result to the control flow graph by
   * means of {@link DiGraphNode#setAnnotation} without any
   * modification of the graph itself. Additional calls to {@link #analyze()}
   * recomputes the analysis which can be useful if the control flow graph
   * has been modified.
   *
   * @param targetCfg The control flow graph object that this object performs
   *     on. Modification of the graph requires a separate call to
   *     {@link #analyze()}.
   *
   * @see #analyze()
   */
  DataFlowAnalysis(ControlFlowGraph<N> targetCfg, JoinOp<L> joinOp) {
    this.cfg = targetCfg;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[2]++;
    this.joinOp = joinOp;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[3]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[4]++;
    Comparator<DiGraphNode<N, Branch>> nodeComparator =
      cfg.getOptionalNodeComparator(isForward());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nodeComparator != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[1]++;
      this.orderedWorkSet = Sets.newTreeSet(nodeComparator);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[6]++;

    } else {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[2]++;
      this.orderedWorkSet = Sets.newLinkedHashSet();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[7]++;
    }
  }

  /**
   * Returns the control flow graph that this analysis was performed on.
   * Modifications can be done on this graph, however, the only time that the
   * annotations are correct is after {@link #analyze()} is called and before
   * the graph has been modified.
   */
  final ControlFlowGraph<N> getCfg() {
    return cfg;
  }

  /**
   * Returns the lattice element at the exit point.
   */
  L getExitLatticeElement() {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[8]++;
    DiGraphNode<N, Branch> node = getCfg().getImplicitReturn();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[9]++;
    FlowState<L> state = node.getAnnotation();
    return state.getIn();
  }

  @SuppressWarnings("unchecked")
  protected L join(L latticeA, L latticeB) {
    return joinOp.apply(Lists.<L>newArrayList(latticeA, latticeB));
  }

  /**
   * Checks whether the analysis is a forward flow analysis or backward flow
   * analysis.
   *
   * @return {@code true} if it is a forward analysis.
   */
  abstract boolean isForward();

  /**
   * Computes the output state for a given node and input state.
   *
   * @param node The node.
   * @param input Input lattice that should be read-only.
   * @return Output lattice.
   */
  abstract L flowThrough(N node, L input);

  /**
   * Finds a fixed-point solution using at most {@link #MAX_STEPS}
   * iterations.
   *
   * @see #analyze(int)
   */
  final void analyze() {
    analyze(MAX_STEPS);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[10]++;
  }

  /**
   * Finds a fixed-point solution. The function has the side effect of replacing
   * the existing node annotations with the computed solutions using {@link
   * com.google.javascript.jscomp.graph.GraphNode#setAnnotation(Annotation)}.
   *
   * <p>Initially, each node's input and output flow state contains the value
   * given by {@link #createInitialEstimateLattice()} (with the exception of the
   * entry node of the graph which takes on the {@link #createEntryLattice()}
   * value. Each node will use the output state of its predecessor and compute a
   * output state according to the instruction. At that time, any nodes that
   * depends on the node's newly modified output value will need to recompute
   * their output state again. Each step will perform a computation at one node
   * until no extra computation will modify any existing output state anymore.
   *
   * @param maxSteps Max number of iterations before the method stops and throw
   *        a {@link MaxIterationsExceededException}. This will prevent the
   *        analysis from going into a infinite loop.
   */
  final void analyze(int maxSteps) {
    initialize();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[11]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[12]++;
    int step = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
    while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((orderedWorkSet.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[1]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[2]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[3]++;
}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((step > maxSteps) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[3]++;
        throw new MaxIterationsExceededException(
          "Analysis did not terminate after " + maxSteps + " iterations");

      } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[4]++;}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[15]++;
      DiGraphNode<N, Branch> curNode = orderedWorkSet.iterator().next();
      orderedWorkSet.remove(curNode);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[16]++;
      joinInputs(curNode);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[17]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((flow(curNode)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[5]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[19]++;
        // If there is a change in the current node, we want to grab the list
        // of nodes that this node affects.
        List<DiGraphNode<N, Branch>> nextNodes = isForward() ?
            cfg.getDirectedSuccNodes(curNode) :
            cfg.getDirectedPredNodes(curNode);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[20]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[4]++;


        for (DiGraphNode<N, Branch> nextNode : nextNodes) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[4]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[5]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[6]++;
}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((nextNode != cfg.getImplicitReturn()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[7]++;
            orderedWorkSet.add(nextNode);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[22]++;

          } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[8]++;}
        }

      } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[6]++;}
      step++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[23]++;
    }
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isForward()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[9]++;
      joinInputs(getCfg().getImplicitReturn());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[25]++;

    } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[10]++;}
  }

  /**
   * Gets the state of the initial estimation at each node.
   *
   * @return Initial state.
   */
  abstract L createInitialEstimateLattice();

  /**
   * Gets the incoming state of the entry node.
   *
   * @return Entry state.
   */
  abstract L createEntryLattice();

  /**
   * Initializes the work list and the control flow graph.
   */
  protected void initialize() {
    // TODO(user): Calling clear doesn't deallocate the memory in a
    // LinkedHashSet. Consider creating a new work set if we plan to repeatedly
    // call analyze.
    orderedWorkSet.clear();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[26]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[27]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[7]++;


    for (DiGraphNode<N, Branch> node : cfg.getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[7]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[8]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[9]++;
}
      node.setAnnotation(new FlowState<L>(createInitialEstimateLattice(),
          createInitialEstimateLattice()));
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[28]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((node != cfg.getImplicitReturn()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[11]++;
        orderedWorkSet.add(node);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[30]++;

      } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[12]++;}
    }
  }

  /**
   * Performs a single flow through a node.
   *
   * @return {@code true} if the flow state differs from the previous state.
   */
  protected boolean flow(DiGraphNode<N, Branch> node) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[31]++;
    FlowState<L> state = node.getAnnotation();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isForward()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[13]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[33]++;
      L outBefore = state.out;
      state.out = flowThrough(node.getValue(), state.in);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[34]++;
      return !outBefore.equals(state.out);

    } else {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[14]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[35]++;
      L inBefore = state.in;
      state.in = flowThrough(node.getValue(), state.out);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[36]++;
      return !inBefore.equals(state.in);
    }
  }

  /**
   * Computes the new flow state at a given node's entry by merging the
   * output (input) lattice of the node's predecessor (successor).
   *
   * @param node Node to compute new join.
   */
  protected void joinInputs(DiGraphNode<N, Branch> node) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[37]++;
    FlowState<L> state = node.getAnnotation();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[38]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isForward()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[15]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[39]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((cfg.getEntry() == node) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[17]++;
        state.setIn(createEntryLattice());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[40]++;

      } else {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[18]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[41]++;
        List<DiGraphNode<N, Branch>> inNodes = cfg.getDirectedPredNodes(node);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((inNodes.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[19]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[43]++;
          FlowState<L> inNodeState = inNodes.get(0).getAnnotation();
          state.setIn(inNodeState.getOut());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[44]++;

        } else {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[20]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[45]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((inNodes.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[21]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[46]++;
          List<L> values = new ArrayList<L>(inNodes.size());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[47]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[10]++;


          for (DiGraphNode<N, Branch> currentNode : inNodes) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[10]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[11]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[12]++;
}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[48]++;
            FlowState<L> currentNodeState = currentNode.getAnnotation();
            values.add(currentNodeState.getOut());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[49]++;
          }
          state.setIn(joinOp.apply(values));
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[50]++;

        } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[22]++;}
}
      }

    } else {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[16]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[51]++;
      List<DiGraphNode<N, Branch>> inNodes = cfg.getDirectedSuccNodes(node);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((inNodes.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[23]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[53]++;
        DiGraphNode<N, Branch> inNode = inNodes.get(0);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((inNode == cfg.getImplicitReturn()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[25]++;
          state.setOut(createEntryLattice());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[55]++;

        } else {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[26]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[56]++;
          FlowState<L> inNodeState = inNode.getAnnotation();
          state.setOut(inNodeState.getIn());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[57]++;
        }

      } else {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[24]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[58]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((inNodes.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[27]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[59]++;
        List<L> values = new ArrayList<L>(inNodes.size());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[60]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[13]++;


        for (DiGraphNode<N, Branch> currentNode : inNodes) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[13]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[14]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[15]++;
}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[61]++;
          FlowState<L> currentNodeState = currentNode.getAnnotation();
          values.add(currentNodeState.getIn());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[62]++;
        }
        state.setOut(joinOp.apply(values));
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[63]++;

      } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[28]++;}
}
    }
  }

  /**
   * The in and out states of a node.
   *
   * @param <L> Input and output lattice element type.
   */
  static class FlowState<L extends LatticeElement> implements Annotation {
    private L in;
    private L out;

    /**
     * Private constructor. No other classes should create new states.
     *
     * @param inState Input.
     * @param outState Output.
     */
    private FlowState(L inState, L outState) {
      Preconditions.checkNotNull(inState);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[64]++;
      Preconditions.checkNotNull(outState);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[65]++;
      this.in = inState;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[66]++;
      this.out = outState;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[67]++;
    }

    L getIn() {
      return in;
    }

    void setIn(L in) {
      Preconditions.checkNotNull(in);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[68]++;
      this.in = in;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[69]++;
    }

    L getOut() {
      return out;
    }

    void setOut(L out) {
      Preconditions.checkNotNull(out);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[70]++;
      this.out = out;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[71]++;
    }

    @Override
    public String toString() {
      return String.format("IN: %s OUT: %s", in, out);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(in, out);
    }
  }

  /**
   * The exception to be thrown if the analysis has been running for a long
   * number of iterations. Chances are the analysis is not monotonic, a
   * fixed-point cannot be found and it is currently stuck in an infinite loop.
   */
  static class MaxIterationsExceededException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    MaxIterationsExceededException(String msg) {
      super(msg);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[72]++;
    }
  }

  abstract static class BranchedForwardDataFlowAnalysis
      <N, L extends LatticeElement> extends DataFlowAnalysis<N, L> {

    @Override
    protected void initialize() {
      orderedWorkSet.clear();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[73]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[74]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[16]++;


      for (DiGraphNode<N, Branch> node : getCfg().getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[16]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[17]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[18]++;
}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[75]++;
        int outEdgeCount = getCfg().getOutEdges(node.getValue()).size();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[76]++;
        List<L> outLattices = Lists.newArrayList();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[77]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[19]++;


int CodeCoverConditionCoverageHelper_C16;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i < outEdgeCount) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[19]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[20]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[21]++;
}
          outLattices.add(createInitialEstimateLattice());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[78]++;
        }
        node.setAnnotation(new BranchedFlowState<L>(
            createInitialEstimateLattice(), outLattices));
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[79]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[80]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((node != getCfg().getImplicitReturn()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[29]++;
          orderedWorkSet.add(node);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[81]++;

        } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[30]++;}
      }
    }

    BranchedForwardDataFlowAnalysis(ControlFlowGraph<N> targetCfg,
                                    JoinOp<L> joinOp) {
      super(targetCfg, joinOp);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[82]++;
    }

    /**
     * Returns the lattice element at the exit point. Needs to be overridden
     * because we use a BranchedFlowState instead of a FlowState; ugh.
     */
    @Override
    L getExitLatticeElement() {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[83]++;
      DiGraphNode<N, Branch> node = getCfg().getImplicitReturn();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[84]++;
      BranchedFlowState<L> state = node.getAnnotation();
      return state.getIn();
    }

    @Override
    final boolean isForward() {
      return true;
    }

    /**
     * The branched flow function maps a single lattice to a list of output
     * lattices.
     *
     * <p>Each outgoing edge of a node will have a corresponding output lattice
     * in the ordered returned by
     * {@link com.google.javascript.jscomp.graph.DiGraph#getOutEdges(Object)}
     * in the returned list.
     *
     * @return A list of output values depending on the edge's branch type.
     */
    abstract List<L> branchedFlowThrough(N node, L input);

    @Override
    protected final boolean flow(DiGraphNode<N, Branch> node) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[85]++;
      BranchedFlowState<L> state = node.getAnnotation();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[86]++;
      List<L> outBefore = state.out;
      state.out = branchedFlowThrough(node.getValue(), state.in);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[87]++;
      Preconditions.checkState(outBefore.size() == state.out.size());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[88]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[89]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[22]++;


int CodeCoverConditionCoverageHelper_C18;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < outBefore.size()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[22]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[23]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[24]++;
}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[90]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((outBefore.get(i).equals(state.out.get(i))) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[31]++;
          return true;

        } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[32]++;}
      }
      return false;
    }

    @Override
    protected void joinInputs(DiGraphNode<N, Branch> node) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[91]++;
      BranchedFlowState<L> state = node.getAnnotation();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[92]++;
      List<DiGraphNode<N, Branch>> predNodes =
          getCfg().getDirectedPredNodes(node);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[93]++;
      List<L> values = new ArrayList<L>(predNodes.size());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[94]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[25]++;



      for (DiGraphNode<N, Branch> predNode : predNodes) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[25]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[26]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[27]++;
}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[95]++;
        BranchedFlowState<L> predNodeState = predNode.getAnnotation();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[96]++;

        L in = predNodeState.out.get(
            getCfg().getDirectedSuccNodes(predNode).indexOf(node));

        values.add(in);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[97]++;
      }
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[98]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((getCfg().getEntry() == node) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[33]++;
        state.setIn(createEntryLattice());
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[99]++;

      } else {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[34]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[100]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((values.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[35]++;
        state.setIn(joinOp.apply(values));
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[101]++;

      } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[36]++;}
}
    }
  }

  /**
   * The in and out states of a node.
   *
   * @param <L> Input and output lattice element type.
   */
  static class BranchedFlowState<L extends LatticeElement>
      implements Annotation {
    private L in;
    private List<L> out;

    /**
     * Private constructor. No other classes should create new states.
     *
     * @param inState Input.
     * @param outState Output.
     */
    private BranchedFlowState(L inState, List<L> outState) {
      Preconditions.checkNotNull(inState);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[102]++;
      Preconditions.checkNotNull(outState);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[103]++;
      this.in = inState;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[104]++;
      this.out = outState;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[105]++;
    }

    L getIn() {
      return in;
    }

    void setIn(L in) {
      Preconditions.checkNotNull(in);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[106]++;
      this.in = in;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[107]++;
    }

    List<L> getOut() {
      return out;
    }

    void setOut(List<L> out) {
      Preconditions.checkNotNull(out);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[108]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[109]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[28]++;


      for (L item : out) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[28]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[29]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[30]++;
}
        Preconditions.checkNotNull(item);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[110]++;
      }
      this.out = out;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[111]++;
    }

    @Override
    public String toString() {
      return String.format("IN: %s OUT: %s", in, out);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(in, out);
    }
  }

  /**
   * Compute set of escaped variables. When a variable is escaped in a
   * dataflow analysis, it can be reference outside of the code that we are
   * analyzing. A variable is escaped if any of the following is true:
   *
   * <p><ol>
   * <li>It is defined as the exception name in CATCH clause so it became a
   * variable local not to our definition of scope.</li>
   * <li>Exported variables as they can be needed after the script terminates.
   * </li>
   * <li>Names of named functions because in JavaScript, <i>function foo(){}</i>
   * does not kill <i>foo</i> in the dataflow.</li>
   */
  static void computeEscaped(final Scope jsScope, final Set<Var> escaped,
      AbstractCompiler compiler) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[112]++;
    // TODO(user): Very good place to store this information somewhere.
    AbstractPostOrderCallback finder = new AbstractPostOrderCallback() {
      @Override
      public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[113]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (32)) == 0 || true) &&
 ((jsScope == t.getScope()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[37]++;
          return;

        } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[38]++;}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[114]++;
        String name = n.getString();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[115]++;
        Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[116]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((var != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((var.scope == jsScope) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[39]++;
          escaped.add(jsScope.getVar(name));
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[117]++;

        } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[40]++;}
      }
    };
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[118]++;

    NodeTraversal t = new NodeTraversal(compiler, finder);
    t.traverseAtScope(jsScope);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[119]++;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[120]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[31]++;


int CodeCoverConditionCoverageHelper_C24;

    // 1: Remove the exception name in CATCH which technically isn't local to
    //    begin with.
    for (Iterator<Var> i = jsScope.getVars();(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false);) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[31]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[32]--;
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.loops[33]++;
}
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[121]++;
      Var var = i.next();
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[122]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((var.getParentNode().isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isExported(var.getName())) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[41]++;
        escaped.add(var);
CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.statements[123]++;

      } else {
  CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp.branches[42]++;}
    }
  }
}

class CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp ());
  }
    public static long[] statements = new long[124];
    public static long[] branches = new long[43];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[26];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.DataFlowAnalysis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,2,1,2};
    for (int i = 1; i <= 25; i++) {
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

  public CodeCoverCoverageCounter$1kstyr2x6rdyb2t5bkpxlki25trej6gyp () {
    super("com.google.javascript.jscomp.DataFlowAnalysis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 123; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 42; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 25; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.DataFlowAnalysis.java");
      for (int i = 1; i <= 123; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 42; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 25; i++) {
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

