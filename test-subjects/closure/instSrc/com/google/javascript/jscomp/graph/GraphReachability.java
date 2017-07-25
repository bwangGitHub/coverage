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
import com.google.common.base.Predicate;
import com.google.javascript.jscomp.graph.FixedPointGraphTraversal.EdgeCallback;

/**
 * Computes all the reachable nodes. Upon execution of {@link #compute(Object)},
 * the graph nodes will be annotated with {@link #REACHABLE} if it is reachable
 * from the specified entry node.
 *
 * @see GraphNode#getAnnotation()
 */
public class GraphReachability<N, E> implements EdgeCallback<N, E> {
  static {
    CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.ping();
  }


  // TODO(user): This should work for undirected graphs when
  // FixedPointGraphTraversal accepts them.
  private final DiGraph<N, E> graph;

  private final Predicate<EdgeTuple<N, E>> edgePredicate;

  public GraphReachability(DiGraph<N, E> graph) {
    this(graph, null);
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[1]++;
  }

  /**
   * @param graph The graph.
   * @param edgePredicate Given the predecessor P of the a node S and the edge
   *      coming from P to S, this predicate should return true if S is
   *      reachable from P using the edge.
   */
  public GraphReachability(DiGraph<N, E> graph,
                           Predicate<EdgeTuple<N, E>> edgePredicate) {
    this.graph = graph;
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[2]++;
    this.edgePredicate = edgePredicate;
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[3]++;
  }

  public void compute(N entry) {
    graph.clearNodeAnnotations();
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[4]++;
    graph.getNode(entry).setAnnotation(REACHABLE);
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[5]++;
    FixedPointGraphTraversal.newTraversal(this)
        .computeFixedPoint(graph, entry);
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[6]++;
  }

  public void recompute(N reachableNode) {
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[7]++;
    GraphNode<N, E> newReachable = graph.getNode(reachableNode);
    Preconditions.checkState(newReachable.getAnnotation() != REACHABLE);
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[8]++;
    newReachable.setAnnotation(REACHABLE);
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[9]++;
    FixedPointGraphTraversal.newTraversal(this)
        .computeFixedPoint(graph, reachableNode);
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[10]++;
  }

  @Override
  public boolean traverseEdge(N source, E e, N destination) {
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((graph.getNode(source).getAnnotation() == REACHABLE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((edgePredicate == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((edgePredicate.apply(new EdgeTuple<N, E>(source, e, destination))) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.branches[1]++;
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[12]++;
      GraphNode<N, E> destNode = graph.getNode(destination);
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((destNode.getAnnotation() != REACHABLE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.branches[3]++;
        destNode.setAnnotation(REACHABLE);
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[14]++;
        return true;

      } else {
  CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.branches[4]++;}

    } else {
  CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.branches[2]++;}
    return false;
  }

  public static final Annotation REACHABLE = new Annotation() {};
  static {
    CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[15]++;
  }

  /**
   * Represents Source Node, Edge and Destination Node.
   */
  public static final class EdgeTuple<N, E> {
    public final N sourceNode;
    public final E edge;
    public final N destNode;
    public EdgeTuple(N sourceNode, E edge, N destNode) {
      this.sourceNode = sourceNode;
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[16]++;
      this.edge = edge;
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[17]++;
      this.destNode = destNode;
CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh.statements[18]++;
    }
  }
}

class CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.graph.GraphReachability.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$bq118sakdadmmwvzi5es1ev4sayk869kkh () {
    super("com.google.javascript.jscomp.graph.GraphReachability.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.graph.GraphReachability.java");
      for (int i = 1; i <= 18; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
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
      for (int i = 1; i <= 0; i++) {
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

