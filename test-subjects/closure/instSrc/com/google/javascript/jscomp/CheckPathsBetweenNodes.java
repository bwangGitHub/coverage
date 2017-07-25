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

import com.google.common.base.Predicate;
import com.google.javascript.jscomp.graph.Annotation;
import com.google.javascript.jscomp.graph.DiGraph;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;

/**
 * See constructor, {@link #CheckPathsBetweenNodes(DiGraph,
 * DiGraphNode, DiGraphNode, Predicate, Predicate)}, for a
 * description of this algorithm.
 *
 *
 * @param <N> The node type.
 * @param <E> The edge type.
 */
class CheckPathsBetweenNodes<N, E> {
  static {
    CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.ping();
  }


  private final Predicate<N> nodePredicate;
  private final Predicate<DiGraphEdge<N, E>> edgePredicate;
  private final boolean inclusive;

  // This algorithm works in two stages. First, the depth-first search (DFS)
  // tree is calculated with A as the root. During when constructing the DFS
  // tree, back edges are recorded. A back edge is a non-tree edge (X -> Y)
  // where X is an descendant of Y in the DFS tree. The second step does a
  // recursive traversal of the graph. Back edges are ignored during the
  // recursive traversal, thus no cycles are encountered. Any recursive branch
  // that encounters B without yet satisfying the predicate represents a path
  // from the entry node to the exit without any nodes that satisfy the
  // predicate.
  //
  // The implementation of discoverBackEdges follows the DFS-Visit algorithm in
  // "Introduction to Algorithms" by Cormen, Leiseron, Rivest, and Stein, 2nd
  // ed., on page 541. The calculation of back edges is described on page 546.

  // A non-tree edge in the DFS that connects a node to one of its ancestors.
  private static final Annotation BACK_EDGE = new Annotation() {};
  static {
    CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[1]++;
  }
  private static final Annotation VISITED_EDGE = new Annotation() {};
  static {
    CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[2]++;
  }

  // Not yet visited.
  private static final Annotation WHITE = null;
  static {
    CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[3]++;
  }
  // Being visited.
  private static final Annotation GRAY = new Annotation() {};
  static {
    CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[4]++;
  }
  // Finished visiting.
  private static final Annotation BLACK = new Annotation() {};
  static {
    CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[5]++;
  }

  private final DiGraph<N, E> graph;
  private final DiGraphNode<N, E> start;
  private final DiGraphNode<N, E> end;

  /**
   * Given a graph G with nodes A and B, this algorithm determines if all paths
   * from A to B contain at least one node satisfying a given predicate.
   *
   * Note that nodePredicate is not necessarily called for all nodes in G nor is
   * edgePredicate called for all edges in G.
   *
   * @param graph Graph G to analyze.
   * @param a The node A.
   * @param b The node B.
   * @param nodePredicate Predicate which at least one node on each path from an
   *     A node to B (inclusive) must match.
   * @param edgePredicate Edges to consider as part of the graph. Edges in
   *     graph that don't match edgePredicate will be ignored.
   * @param inclusive Includes node A and B in the test for the node predicate.
   */
  CheckPathsBetweenNodes(DiGraph<N, E> graph, DiGraphNode<N, E> a,
      DiGraphNode<N, E> b, Predicate<N> nodePredicate,
      Predicate<DiGraphEdge<N, E>> edgePredicate, boolean inclusive) {
    this.graph = graph;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[6]++;
    this.start = a;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[7]++;
    this.end = b;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[8]++;
    this.nodePredicate = nodePredicate;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[9]++;
    this.edgePredicate = edgePredicate;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[10]++;
    this.inclusive = inclusive;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[11]++;
  }

  /**
   * Inclusive check.
   */
  CheckPathsBetweenNodes(DiGraph<N, E> graph, DiGraphNode<N, E> a,
      DiGraphNode<N, E> b, Predicate<N> nodePredicate,
      Predicate<DiGraphEdge<N, E>> edgePredicate) {
    this(graph, a, b, nodePredicate, edgePredicate, true);
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[12]++;
  }


  /**
   * @return true iff all paths contain at least one node that satisfy the
   *     predicate
   */
  public boolean allPathsSatisfyPredicate() {
    setUp();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[13]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[14]++;
    boolean result = checkAllPathsWithoutBackEdges(start, end);
    tearDown();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[15]++;
    return result;
  }

  /**
   * @return true iff some paths contain at least one node that satisfy the
   *     predicate
   */
  public boolean somePathsSatisfyPredicate() {
    setUp();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[16]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[17]++;
    boolean result = checkSomePathsWithoutBackEdges(start, end);
    tearDown();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[18]++;
    return result;
  }

  private void setUp() {
    graph.pushNodeAnnotations();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[19]++;
    graph.pushEdgeAnnotations();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[20]++;
    discoverBackEdges(this.start);
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[21]++;
  }

  private void tearDown() {
    graph.popNodeAnnotations();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[22]++;
    graph.popEdgeAnnotations();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[23]++;
  }

  private void discoverBackEdges(DiGraphNode<N, E> u) {
    u.setAnnotation(GRAY);
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[24]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[25]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[1]++;


    for (DiGraphEdge<N, E> e : u.getOutEdges()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[1]--;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[2]--;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[3]++;
}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[26]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((ignoreEdge(e)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[1]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[27]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[2]++;}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[28]++;
      DiGraphNode<N, E> v = e.getDestination();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[29]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((v.getAnnotation() == WHITE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[3]++;
        discoverBackEdges(v);
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[30]++;

      } else {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[4]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[31]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.getAnnotation() == GRAY) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[5]++;
        e.setAnnotation(BACK_EDGE);
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[32]++;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[6]++;}
}
    }
    u.setAnnotation(BLACK);
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[33]++;
  }

  private boolean ignoreEdge(DiGraphEdge<N, E> e) {
    return !edgePredicate.apply(e);
  }

  /**
   * Verify that all non-looping paths from {@code a} to {@code b} pass
   * through at least one node where {@code nodePredicate} is true.
   */
  private boolean checkAllPathsWithoutBackEdges(DiGraphNode<N, E> a,
      DiGraphNode<N, E> b) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[34]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (128)) == 0 || true) &&
 ((nodePredicate.apply(a.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((inclusive) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((a != start) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((a != end) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 4) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[7]++;
      return true;

    } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[8]++;}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((a == b) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[9]++;
      return false;

    } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[10]++;}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[36]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[4]++;


    for (DiGraphEdge<N, E> e : a.getOutEdges()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[4]--;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[5]--;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[6]++;
}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[37]++;
int CodeCoverConditionCoverageHelper_C6;
      // Once we visited that edge once, we no longer need to
      // re-visit it again.
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((e.getAnnotation() == VISITED_EDGE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[11]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[38]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[12]++;}
      e.setAnnotation(VISITED_EDGE);
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[39]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[40]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((ignoreEdge(e)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[13]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[41]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[14]++;}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((e.getAnnotation() == BACK_EDGE) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[15]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[43]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[16]++;}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[44]++;

      DiGraphNode<N, E> next = e.getDestination();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((checkAllPathsWithoutBackEdges(next, b)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[17]++;
        return false;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[18]++;}
    }
    return true;
  }

  /**
   * Verify that some non-looping paths from {@code a} to {@code b} pass
   * through at least one node where {@code nodePredicate} is true.
   */
  private boolean checkSomePathsWithoutBackEdges(DiGraphNode<N, E> a,
      DiGraphNode<N, E> b) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[46]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (128)) == 0 || true) &&
 ((nodePredicate.apply(a.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (64)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((inclusive) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((a != start) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((a != end) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[19]++;
      return true;

    } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[20]++;}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((a == b) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[21]++;
      return false;

    } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[22]++;}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[48]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[7]++;


    for (DiGraphEdge<N, E> e : a.getOutEdges()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[7]--;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[8]--;
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.loops[9]++;
}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;
      // Once we visited that edge once, we no longer need to
      // re-visit it again.
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((e.getAnnotation() == VISITED_EDGE) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[23]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[50]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[24]++;}
      e.setAnnotation(VISITED_EDGE);
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[51]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;

      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((ignoreEdge(e)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[25]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[53]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[26]++;}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((e.getAnnotation() == BACK_EDGE) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[27]++;
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[55]++;
        continue;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[28]++;}
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[56]++;

      DiGraphNode<N, E> next = e.getDestination();
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((checkSomePathsWithoutBackEdges(next, b)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[29]++;
        return true;

      } else {
  CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl.branches[30]++;}
    }
    return false;
  }
}

class CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckPathsBetweenNodes.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1};
    for (int i = 1; i <= 15; i++) {
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

  public CodeCoverCoverageCounter$4b6nidkgroeo5z32v6iquknm9qpghqd6qcgh7wf5nl () {
    super("com.google.javascript.jscomp.CheckPathsBetweenNodes.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckPathsBetweenNodes.java");
      for (int i = 1; i <= 57; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
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

