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
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * The base generic class for graph-like data structure and algorithms in
 * the compiler.
 * <p>
 * Nodes and edges in the graph can store a piece of data that this graph is
 * used to represent. For example, a variable interference graph might store a
 * variable in the node. This piece of data can be accessed with
 * {@link GraphNode#getValue} and {@link GraphEdge#getValue}.
 * <p>
 * Algorithms and analysis can annotate information on the nodes and edges
 * using {@link GraphNode#getValue} and {@link GraphEdge#getValue}. For example,
 * a graph coloring algorithm can store the color as an annotation. If multiple
 * analyses are required, it is up to the user of the analysis to save the
 * annotated solution between passes.
 * <p>
 * We implemented our own graph data structure (as opposed to using
 * <code>com.google.common.graph</code>) for two reasons. First, aside from
 * the node's label value, we would like to annotate information on the nodes
 * and edges. Using a map to annotate would introduce too much overhead during
 * fix point analysis. Also, <code>com.google.common.graph</code> does not
 * support labeling of edges. Secondly, not using another external package would
 * limit our dependencies.
 * <p>
 * TODO(user): All functionality for removing nodes and edges.
 *
 *
 * @param <N> Value type that the graph node stores.
 * @param <E> Value type that the graph edge stores.
 */
public abstract class Graph<N, E> implements AdjacencyGraph<N, E> {
  static {
    CodeCoverCoverageCounter$1ixxblotpklmm0kh.ping();
  }

  /**
   * Pseudo typedef for a pair of annotations. Record of an object's
   * annotation at some state.
   */
  private static final class AnnotationState {
    private final Annotatable first;
    private final Annotation second;

    public AnnotationState(Annotatable annotatable, Annotation annotation) {
      this.first = annotatable;
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[1]++;
      this.second = annotation;
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[2]++;
    }
  }

  /**
   * Pseudo typedef for ArrayList<AnnotationState>. Record of a collection of
   * objects' annotations at some state.
   */
  private static class GraphAnnotationState extends ArrayList<AnnotationState> {
    private static final long serialVersionUID = 1L;

    public GraphAnnotationState(int size) {
      super(size);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[3]++;
    }
  }

  /**
   * Used by {@link #pushNodeAnnotations()} and {@link #popNodeAnnotations()}.
   */
  private Deque<GraphAnnotationState> nodeAnnotationStack;

  /**
   * Used by {@link #pushEdgeAnnotations()} and {@link #popEdgeAnnotations()}.
   */
  private Deque<GraphAnnotationState> edgeAnnotationStack;

  /**
   * Connects two nodes in the graph with an edge.
   *
   * @param n1 First node.
   * @param edge The edge.
   * @param n2 Second node.
   */
  public abstract void connect(N n1, E edge, N n2);

  /**
   * Disconnects two nodes in the graph by removing all edges between them.
   *
   * @param n1 First node.
   * @param n2 Second node.
   */
  public abstract void disconnect(N n1, N n2);

  /**
   * Connects two nodes in the graph with an edge if such edge does not already
   * exists between the nodes.
   *
   * @param n1 First node.
   * @param edge The edge.
   * @param n2 Second node.
   */
  public final void connectIfNotFound(N n1, E edge, N n2) {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((isConnected(n1, edge, n2)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[1]++;
      connect(n1, edge, n2);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[5]++;

    } else {
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[2]++;}
  }

  /**
   * Gets a node from the graph given a value. New nodes are created if that
   * value has not been assigned a graph node. Values equality are compared
   * using <code>Object.equals</code>.
   *
   * @param value The node's value.
   * @return The corresponding node in the graph.
   */
  public abstract GraphNode<N, E> createNode(N value);

  /** Gets an immutable list of all nodes. */
  @Override
  public abstract Collection<GraphNode<N, E>> getNodes();

  /** Gets an immutable list of all edges. */
  public abstract List<GraphEdge<N, E>> getEdges();

  /**
   * Gets the degree of a node.
   *
   * @param value The node's value.
   * @return The degree of the node.
   */
  public abstract int getNodeDegree(N value);

  @Override
  public int getWeight(N value) {
    return getNodeDegree(value);
  }

  /**
   * Gets the neighboring nodes.
   *
   * @param value The node's value.
   * @return A list of neighboring nodes.
   */
  public abstract List<GraphNode<N, E>> getNeighborNodes(N value);

  public abstract Iterator<GraphNode<N, E>> getNeighborNodesIterator(N value);

  /**
   * Retrieves an edge from the graph.
   *
   * @param n1 Node one.
   * @param n2 Node two.
   * @return The list of edges between those two values in the graph.
   */
  public abstract List<GraphEdge<N, E>> getEdges(N n1, N n2);

  /**
   * Retrieves any edge from the graph.
   *
   * @param n1 Node one.
   * @param n2 Node two.
   * @return The first edges between those two values in the graph. null if
   *    there are none.
   */
  public abstract GraphEdge<N, E> getFirstEdge(N n1, N n2);

  /**
   * Checks whether the node exists in the graph ({@link #createNode(Object)}
   * has been called with that value).
   *
   * @param n Node.
   * @return <code>true</code> if it exist.
   */
  public final boolean hasNode(N n) {
    return getNode(n) != null;
  }

  /**
   * Checks whether two nodes in the graph are connected.
   *
   * @param n1 Node 1.
   * @param n2 Node 2.
   * @return <code>true</code> if the two nodes are connected.
   */
  public abstract boolean isConnected(N n1, N n2);

  /**
   * Checks whether two nodes in the graph are connected by the given
   * edge type.
   *
   * @param n1 Node 1.
   * @param e The edge type.
   * @param n2 Node 2.
   */
  public abstract boolean isConnected(N n1, E e, N n2);

  /**
   * Gets the node of the specified type, or throws an
   * IllegalArgumentException.
   */
  @SuppressWarnings("unchecked")
  <T extends GraphNode<N, E>> T getNodeOrFail(N val) {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[6]++;
    T node = (T) getNode(val);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[3]++;
      throw new IllegalArgumentException(val + " does not exist in graph");

    } else {
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[4]++;}
    return node;
  }

  @Override
  public final void clearNodeAnnotations() {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[1]++;


    for (GraphNode<N, E> n : getNodes()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[1]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[2]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[3]++;
}
      n.setAnnotation(null);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[9]++;
    }
  }

  /** Makes each edge's annotation null. */
  public final void clearEdgeAnnotations() {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[10]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[4]++;


    for (GraphEdge<N, E> e : getEdges()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[4]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[5]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[6]++;
}
      e.setAnnotation(null);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[11]++;
    }
  }

  /**
   * Pushes nodes' annotation values. Restored with
   * {@link #popNodeAnnotations()}. Nodes' annotation values are cleared.
   */
  public final void pushNodeAnnotations() {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nodeAnnotationStack == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[5]++;
      nodeAnnotationStack = Lists.newLinkedList();
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[13]++;

    } else {
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[6]++;}
    pushAnnotations(nodeAnnotationStack, getNodes());
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[14]++;
  }

  /**
   * Restores nodes' annotation values to state before last
   * {@link #pushNodeAnnotations()}.
   */
  public final void popNodeAnnotations() {
    Preconditions.checkNotNull(nodeAnnotationStack,
        "Popping node annotations without pushing.");
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[15]++;
    popAnnotations(nodeAnnotationStack);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[16]++;
  }

  /**
   * Pushes edges' annotation values. Restored with
   * {@link #popEdgeAnnotations()}. Edges' annotation values are cleared.
   */
  public final void pushEdgeAnnotations() {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((edgeAnnotationStack == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[7]++;
      edgeAnnotationStack = Lists.newLinkedList();
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[18]++;

    } else {
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[8]++;}
    pushAnnotations(edgeAnnotationStack, getEdges());
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[19]++;
  }

  /**
   * Restores edges' annotation values to state before last
   * {@link #pushEdgeAnnotations()}.
   */
  public final void popEdgeAnnotations() {
    Preconditions.checkNotNull(edgeAnnotationStack,
        "Popping edge annotations without pushing.");
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[20]++;
    popAnnotations(edgeAnnotationStack);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[21]++;
  }

  /**
   * A generic edge.
   *
   * @param <N> Value type that the graph node stores.
   * @param <E> Value type that the graph edge stores.
   */
  public interface GraphEdge<N, E> extends Annotatable {
    /**
     * Retrieves the edge's value.
     *
     * @return The value.
     */
    E getValue();

    GraphNode<N, E> getNodeA();

    GraphNode<N, E> getNodeB();
  }

  /**
   * A simple implementation of SubGraph that calculates adjacency by iterating
   * over a node's neighbors.
   */
  class SimpleSubGraph<N, E> implements SubGraph<N, E> {
    private Graph<N, E> graph;
    private List<GraphNode<N, E>> nodes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[22]++;
  }

    SimpleSubGraph(Graph<N, E> graph) {
      this.graph = graph;
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[23]++;
    }

    @Override
    public boolean isIndependentOf(N value) {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[24]++;
      GraphNode<N, E> node = graph.getNode(value);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[25]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[7]++;


      for (GraphNode<N, E> n : nodes) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[7]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[8]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[9]++;
}
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((graph.getNeighborNodes(n.getValue()).contains(node)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1ixxblotpklmm0kh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[9]++;
          return false;

        } else {
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.branches[10]++;}
      }
      return true;
    }

    @Override
    public void addNode(N value) {
      nodes.add(graph.getNodeOrFail(value));
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[27]++;
    }
  }

  /**
   * Pushes a new list on stack and stores nodes annotations in the new list.
   * Clears objects' annotations as well.
   */
  private static void pushAnnotations(
      Deque<GraphAnnotationState> stack,
      Collection<? extends Annotatable> haveAnnotations) {
    stack.push(new GraphAnnotationState(haveAnnotations.size()));
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[28]++;
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[29]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[10]++;


    for (Annotatable h : haveAnnotations) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[10]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[11]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[12]++;
}
      stack.peek().add(new AnnotationState(h, h.getAnnotation()));
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[30]++;
      h.setAnnotation(null);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[31]++;
    }
  }

  /**
   * Restores the node annotations on the top of stack and pops stack.
   */
  private static void popAnnotations(Deque<GraphAnnotationState> stack) {
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[32]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[13]++;


    for (AnnotationState as : stack.pop()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[13]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[14]--;
  CodeCoverCoverageCounter$1ixxblotpklmm0kh.loops[15]++;
}
      as.first.setAnnotation(as.second);
CodeCoverCoverageCounter$1ixxblotpklmm0kh.statements[33]++;
    }
  }
}

class CodeCoverCoverageCounter$1ixxblotpklmm0kh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1ixxblotpklmm0kh ());
  }
    public static long[] statements = new long[34];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.graph.Graph.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$1ixxblotpklmm0kh () {
    super("com.google.javascript.jscomp.graph.Graph.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 33; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.graph.Graph.java");
      for (int i = 1; i <= 33; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

