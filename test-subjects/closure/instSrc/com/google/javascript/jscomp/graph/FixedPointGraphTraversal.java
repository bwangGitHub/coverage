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

package com.google.javascript.jscomp.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.graph.DiGraph;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * A utility class for doing fixed-point computations. We traverse
 * the edges over the given directed graph until the graph reaches
 * a steady state.
 *
 * @author nicksantos@google.com (Nick Santos)
 *
 * @param <N> Value type that the graph node stores.
 * @param <E> Value type that the graph edge stores.
 */
public final class FixedPointGraphTraversal<N, E> {
  static {
    CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.ping();
  }

  // TODO(nicksantos): Generalize the algorithm for undirected graphs, if we
  // need it.

  private final EdgeCallback<N, E> callback;

  public static final String NON_HALTING_ERROR_MSG =
    "Fixed point computation not halting";
  static {
    CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[1]++;
  }

  /**
   * Create a new traversal.
   * @param callback A callback for updating the state of the graph each
   *     time an edge is traversed.
   */
  public FixedPointGraphTraversal(EdgeCallback<N, E> callback) {
    this.callback = callback;
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[2]++;
  }

  /**
   * Helper method for creating new traversals.
   */
  public static <NODE, EDGE> FixedPointGraphTraversal<NODE, EDGE> newTraversal(
      EdgeCallback<NODE, EDGE> callback) {
    return new FixedPointGraphTraversal<NODE, EDGE>(callback);
  }

  /**
   * Compute a fixed point for the given graph.
   * @param graph The graph to traverse.
   */
  public void computeFixedPoint(DiGraph<N, E> graph) {
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[3]++;
    Set<N> nodes = Sets.newHashSet();
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[1]++;


    for (DiGraphNode<N, E> node : graph.getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[1]--;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[2]--;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[3]++;
}
      nodes.add(node.getValue());
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[5]++;
    }
    computeFixedPoint(graph, nodes);
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[6]++;
  }

  /**
   * Compute a fixed point for the given graph, entering from the given node.
   * @param graph The graph to traverse.
   * @param entry The node to begin traversing from.
   */
  public void computeFixedPoint(DiGraph<N, E> graph, N entry) {
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[7]++;
    Set<N> entrySet = Sets.newHashSet();
    entrySet.add(entry);
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[8]++;
    computeFixedPoint(graph, entrySet);
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[9]++;
  }

  /**
   * Compute a fixed point for the given graph, entering from the given nodes.
   * @param graph The graph to traverse.
   * @param entrySet The nodes to begin traversing from.
   */
  public void computeFixedPoint(DiGraph<N, E> graph, Set<N> entrySet) {
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[10]++;
    int cycleCount = 0;
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[11]++;
    long nodeCount = graph.getNodes().size();
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[12]++;

    // Choose a bail-out heuristically in case the computation
    // doesn't converge.
    long maxIterations = Math.max(nodeCount * nodeCount * nodeCount, 100);
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[13]++;

    // Use a LinkedHashSet, so that the traversal is deterministic.
    LinkedHashSet<DiGraphNode<N, E>> workSet =
        Sets.newLinkedHashSet();
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[4]++;


    for (N n : entrySet) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[4]--;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[5]--;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[6]++;
}
      workSet.add(graph.getDirectedGraphNode(n));
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[15]++;
    }
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[16]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[7]++;


int CodeCoverConditionCoverageHelper_C1;
    for (;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((workSet.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cycleCount < maxIterations) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false); cycleCount++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[7]--;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[8]--;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[9]++;
}
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[17]++;
      // For every out edge in the workSet, traverse that edge. If that
      // edge updates the state of the graph, then add the destination
      // node to the resultSet, so that we can update all of its out edges
      // on the next iteration.
      DiGraphNode<N, E> source = workSet.iterator().next();
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[18]++;
      N sourceValue = source.getValue();

      workSet.remove(source);
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[19]++;
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[20]++;

      List<DiGraphEdge<N, E>> outEdges = source.getOutEdges();
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[21]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[10]++;


      for (DiGraphEdge<N, E> edge : outEdges) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[10]--;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[11]--;
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.loops[12]++;
}
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[22]++;
        N destNode = edge.getDestination().getValue();
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[23]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((callback.traverseEdge(sourceValue, edge.getValue(), destNode)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.branches[1]++;
          workSet.add(edge.getDestination());
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[24]++;

        } else {
  CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.branches[2]++;}
      }
    }

    Preconditions.checkState(cycleCount != maxIterations,
        NON_HALTING_ERROR_MSG);
CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9.statements[25]++;
  }

  public static interface EdgeCallback<Node, Edge> {
    /**
     * Update the state of the destination node when the given edge
     * is traversed. For the fixed-point computation to work, only the
     * destination node may be modified. The source node and the edge must
     * not be modified.
     *
     * @param source The start node.
     * @param e The edge.
     * @param destination The end node.
     * @return Whether the state of the destination node changed.
     */
    boolean traverseEdge(Node source, Edge e, Node destination);
  }
}

class CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9 ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.graph.FixedPointGraphTraversal.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1};
    for (int i = 1; i <= 2; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$6bp3vg21oh3rzpuzeq9jzzwgz4bgiupg798skfre9u1y9 () {
    super("com.google.javascript.jscomp.graph.FixedPointGraphTraversal.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.graph.FixedPointGraphTraversal.java");
      for (int i = 1; i <= 25; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

