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
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A directed graph using linked list within nodes to store edge information.
 * <p>
 * This implementation favors directed graph operations inherited from <code>
 * DirectedGraph</code>.
 * Operations from <code>Graph</code> would tends to be slower.
 *
 *
 * @param <N> Value type that the graph node stores.
 * @param <E> Value type that the graph edge stores.
 */
public class LinkedDirectedGraph<N, E>
    extends DiGraph<N, E> implements GraphvizGraph {
  static {
    CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.ping();
  }

  protected final Map<N, LinkedDirectedGraphNode<N, E>> nodes =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[1]++;
  }

  @Override
  public SubGraph<N, E> newSubGraph() {
    return new SimpleSubGraph<N, E>(this);
  }

  public static <N, E> LinkedDirectedGraph<N, E> createWithoutAnnotations() {
    return new LinkedDirectedGraph<N, E>(false, false);
  }

  public static <N, E> LinkedDirectedGraph<N, E> createWithNodeAnnotations() {
    return new LinkedDirectedGraph<N, E>(true, false);
  }

  public static <N, E> LinkedDirectedGraph<N, E> createWithEdgeAnnotations() {
    return new LinkedDirectedGraph<N, E>(false, true);
  }

  public static <N, E> LinkedDirectedGraph<N, E> create() {
    return new LinkedDirectedGraph<N, E>(true, true);
  }

  private final boolean useNodeAnnotations;
  private final boolean useEdgeAnnotations;

  protected LinkedDirectedGraph(
      boolean useNodeAnnotations, boolean useEdgeAnnotations) {
    this.useNodeAnnotations = useNodeAnnotations;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[2]++;
    this.useEdgeAnnotations = useEdgeAnnotations;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[3]++;
  }

  @Override
  public void connect(N srcValue, E edgeValue, N destValue) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[4]++;
    LinkedDirectedGraphNode<N, E> src = getNodeOrFail(srcValue);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[5]++;
    LinkedDirectedGraphNode<N, E> dest = getNodeOrFail(destValue);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[6]++;
    LinkedDirectedGraphEdge<N, E> edge =
        useEdgeAnnotations ?
        new AnnotatedLinkedDirectedGraphEdge<N, E>(src, edgeValue, dest) :
        new LinkedDirectedGraphEdge<N, E>(src, edgeValue, dest);
    src.getOutEdges().add(edge);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[7]++;
    dest.getInEdges().add(edge);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[8]++;
  }

  @Override
  public void disconnect(N n1, N n2) {
    disconnectInDirection(n1, n2);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[9]++;
    disconnectInDirection(n2, n1);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[10]++;
  }

  @Override
  public void disconnectInDirection(N srcValue, N destValue) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[11]++;
    LinkedDirectedGraphNode<N, E> src = getNodeOrFail(srcValue);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[12]++;
    LinkedDirectedGraphNode<N, E> dest = getNodeOrFail(destValue);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[1]++;


    for (DiGraphEdge<?, E> edge : getDirectedGraphEdges(srcValue, destValue)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[1]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[2]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[3]++;
}
      src.getOutEdges().remove(edge);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[14]++;
      dest.getInEdges().remove(edge);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[15]++;
    }
  }

  @Override
  public Iterable<DiGraphNode<N, E>> getDirectedGraphNodes() {
    return Collections.<DiGraphNode<N, E>>unmodifiableCollection(
        nodes.values());
  }

  @Override
  public DiGraphNode<N, E> getDirectedGraphNode(N nodeValue) {
    return nodes.get(nodeValue);
  }

  @Override
  public GraphNode<N, E> getNode(N nodeValue) {
    return getDirectedGraphNode(nodeValue);
  }

  @Override
  public List<DiGraphEdge<N, E>> getInEdges(N nodeValue) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[16]++;
    LinkedDirectedGraphNode<N, E> node = getNodeOrFail(nodeValue);
    return Collections.<DiGraphEdge<N, E>>unmodifiableList(node.getInEdges());
  }

  @Override
  public List<DiGraphEdge<N, E>> getOutEdges(N nodeValue) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[17]++;
    LinkedDirectedGraphNode<N, E> node = getNodeOrFail(nodeValue);
    return Collections.<DiGraphEdge<N, E>>unmodifiableList(node.getOutEdges());
  }

  @Override
  public DiGraphNode<N, E> createDirectedGraphNode(N nodeValue) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[18]++;
    LinkedDirectedGraphNode<N, E> node = nodes.get(nodeValue);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[1]++;
      node = useNodeAnnotations ?
          new AnnotatedLinkedDirectedGraphNode<N, E>(nodeValue) :
          new LinkedDirectedGraphNode<N, E>(nodeValue);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[20]++;
      nodes.put(nodeValue, node);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[21]++;

    } else {
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[2]++;}
    return node;
  }

  @Override
  public List<GraphEdge<N, E>> getEdges(N n1, N n2) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[22]++;
    // Since this is a method from a generic graph, edges from both
    // directions must be added to the returning list.
    List<DiGraphEdge<N, E>> forwardEdges = getDirectedGraphEdges(n1, n2);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[23]++;
    List<DiGraphEdge<N, E>> backwardEdges = getDirectedGraphEdges(n2, n1);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[24]++;
    int totalSize = forwardEdges.size() + backwardEdges.size();
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[25]++;
    List<GraphEdge<N, E>> edges = Lists.newArrayListWithCapacity(totalSize);
    edges.addAll(forwardEdges);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[26]++;
    edges.addAll(backwardEdges);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[27]++;
    return edges;
  }

  @Override
  public GraphEdge<N, E> getFirstEdge(N n1, N n2) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[28]++;
    DiGraphNode<N, E> dNode1 = getNodeOrFail(n1);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[29]++;
    DiGraphNode<N, E> dNode2 = getNodeOrFail(n2);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[30]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[4]++;


    for (DiGraphEdge<N, E> outEdge : dNode1.getOutEdges()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[4]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[5]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[6]++;
}
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[31]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((outEdge.getDestination() == dNode2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[3]++;
        return outEdge;

      } else {
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[4]++;}
    }
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[32]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[7]++;


    for (DiGraphEdge<N, E> outEdge : dNode2.getOutEdges()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[7]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[8]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[9]++;
}
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[33]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((outEdge.getDestination() == dNode1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[5]++;
        return outEdge;

      } else {
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[6]++;}
    }
    return null;
  }

  @Override
  public GraphNode<N, E> createNode(N value) {
    return createDirectedGraphNode(value);
  }

  @Override
  public List<DiGraphEdge<N, E>> getDirectedGraphEdges(N n1, N n2) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[34]++;
    DiGraphNode<N, E> dNode1 = getNodeOrFail(n1);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[35]++;
    DiGraphNode<N, E> dNode2 = getNodeOrFail(n2);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[36]++;
    List<DiGraphEdge<N, E>> edges = Lists.newArrayList();
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[37]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[10]++;


    for (DiGraphEdge<N, E> outEdge : dNode1.getOutEdges()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[10]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[11]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[12]++;
}
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[38]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((outEdge.getDestination() == dNode2) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[7]++;
        edges.add(outEdge);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[39]++;

      } else {
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[8]++;}
    }
    return edges;
  }

  @Override
  public boolean isConnectedInDirection(N n1, N n2) {
    return isConnectedInDirection(n1, Predicates.<E>alwaysTrue(), n2);
  }

  @Override
  public boolean isConnectedInDirection(N n1, E edgeValue, N n2) {
    return isConnectedInDirection(n1, Predicates.equalTo(edgeValue), n2);
  }

  private boolean isConnectedInDirection(N n1, Predicate<E> edgeMatcher, N n2) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[40]++;
    // Verify the nodes.
    DiGraphNode<N, E> dNode1 = getNodeOrFail(n1);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[41]++;
    DiGraphNode<N, E> dNode2 = getNodeOrFail(n2);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[42]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[13]++;


    for (DiGraphEdge<N, E> outEdge : dNode1.getOutEdges()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[13]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[14]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[15]++;
}
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[43]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((outEdge.getDestination() == dNode2) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((edgeMatcher.apply(outEdge.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[9]++;
        return true;

      } else {
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[10]++;}
    }

    return false;
  }

  @Override
  public List<DiGraphNode<N, E>> getDirectedPredNodes(N nodeValue) {
    return getDirectedPredNodes(nodes.get(nodeValue));
  }

  @Override
  public List<DiGraphNode<N, E>> getDirectedSuccNodes(N nodeValue) {
    return getDirectedSuccNodes(nodes.get(nodeValue));
  }

  @Override
  public List<DiGraphNode<N, E>> getDirectedPredNodes(
      DiGraphNode<N, E> dNode) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[44]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((dNode == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[11]++;
      throw new IllegalArgumentException(dNode + " is null");

    } else {
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[12]++;}
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[45]++;
    List<DiGraphNode<N, E>> nodeList = Lists.newArrayList();
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[46]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[16]++;


    for (DiGraphEdge<N, E> edge : dNode.getInEdges()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[16]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[17]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[18]++;
}
      nodeList.add(edge.getSource());
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[47]++;
    }
    return nodeList;
  }

  @Override
  public List<DiGraphNode<N, E>> getDirectedSuccNodes(
      DiGraphNode<N, E> dNode) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[48]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((dNode == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[13]++;
      throw new IllegalArgumentException(dNode + " is null");

    } else {
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.branches[14]++;}
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[49]++;
    List<DiGraphNode<N, E>> nodeList = Lists.newArrayList();
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[50]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[19]++;


    for (DiGraphEdge<N, E> edge : dNode.getOutEdges()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[19]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[20]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[21]++;
}
      nodeList.add(edge.getDestination());
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[51]++;
    }
    return nodeList;
  }

  @Override
  public List<GraphvizEdge> getGraphvizEdges() {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[52]++;
    List<GraphvizEdge> edgeList = Lists.newArrayList();
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[53]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[22]++;


    for (LinkedDirectedGraphNode<N, E> node : nodes.values()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[22]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[23]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[24]++;
}
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[54]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[25]++;


      for (DiGraphEdge<N, E> edge : node.getOutEdges()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[25]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[26]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[27]++;
}
        edgeList.add((LinkedDirectedGraphEdge<N, E>) edge);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[55]++;
      }
    }
    return edgeList;
  }

  @Override
  public List<GraphvizNode> getGraphvizNodes() {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[56]++;
    List<GraphvizNode> nodeList =
        Lists.newArrayListWithCapacity(nodes.size());
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[57]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[28]++;


    for (LinkedDirectedGraphNode<N, E> node : nodes.values()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[28]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[29]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[30]++;
}
      nodeList.add(node);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[58]++;
    }
    return nodeList;
  }

  @Override
  public String getName() {
    return "LinkedGraph";
  }

  @Override
  public boolean isDirected() {
    return true;
  }

  @Override
  public Collection<GraphNode<N, E>> getNodes() {
    return Collections.<GraphNode<N, E>>unmodifiableCollection(nodes.values());
  }

  @Override
  public List<GraphNode<N, E>> getNeighborNodes(N value) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[59]++;
    DiGraphNode<N, E> node = getDirectedGraphNode(value);
    return getNeighborNodes(node);
  }

  public List<GraphNode<N, E>> getNeighborNodes(DiGraphNode<N, E> node) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[60]++;
    List<GraphNode<N, E>> result = Lists.newArrayList();
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[61]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[31]++;


int CodeCoverConditionCoverageHelper_C8;
    for (Iterator<GraphNode<N, E>> i =
      ((LinkedDirectedGraphNode<N, E>) node).neighborIterator();(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false);) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[31]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[32]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[33]++;
}
      result.add(i.next());
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[62]++;
    }
    return result;
  }

  @Override
  public Iterator<GraphNode<N, E>> getNeighborNodesIterator(N value) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[63]++;
    LinkedDirectedGraphNode<N, E> node = nodes.get(value);
    Preconditions.checkNotNull(node);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[64]++;
    return node.neighborIterator();
  }

  @Override
  public List<GraphEdge<N, E>> getEdges() {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[65]++;
    List<GraphEdge<N, E>> result = Lists.newArrayList();
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[66]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[34]++;


    for (DiGraphNode<N, E> node : nodes.values()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[34]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[35]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[36]++;
}
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[67]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[37]++;


      for (DiGraphEdge<N, E> edge : node.getOutEdges()) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[37]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[38]--;
  CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.loops[39]++;
}
        result.add(edge);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[68]++;
      }
    }
    return Collections.unmodifiableList(result);
  }

  @Override
  public int getNodeDegree(N value) {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[69]++;
    DiGraphNode<N, E> node = getNodeOrFail(value);
    return node.getInEdges().size() + node.getOutEdges().size();
  }

  /**
   * A directed graph node that stores outgoing edges and incoming edges as an
   * list within the node itself.
   */
  static class LinkedDirectedGraphNode<N, E> implements DiGraphNode<N, E>,
      GraphvizNode {

    List<DiGraphEdge<N, E>> inEdgeList = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[70]++;
  }
    List<DiGraphEdge<N, E>> outEdgeList =
        Lists.newArrayList();
  {
    CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[71]++;
  }

    protected final N value;

    /**
     * Constructor
     *
     * @param nodeValue Node's value.
     */
    LinkedDirectedGraphNode(N nodeValue) {
      this.value = nodeValue;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[72]++;
    }

    @Override
    public N getValue() {
      return value;
    }

    @Override
    public <A extends Annotation> A getAnnotation() {
      throw new UnsupportedOperationException(
          "Graph initialized with node annotations turned off");
    }

    @Override
    public void setAnnotation(Annotation data) {
      throw new UnsupportedOperationException(
          "Graph initialized with node annotations turned off");
    }

    @Override
    public String getColor() {
      return "white";
    }

    @Override
    public String getId() {
      return "LDN" + hashCode();
    }

    @Override
    public String getLabel() {
      return value != null ? value.toString() : "null";
    }

    @Override
    public String toString() {
      return getLabel();
    }

    @Override
    public List<DiGraphEdge<N, E>> getInEdges() {
      return inEdgeList;
    }

    @Override
    public List<DiGraphEdge<N, E>> getOutEdges() {
      return outEdgeList;
    }

    private Iterator<GraphNode<N, E>> neighborIterator() {
      return new NeighborIterator();
    }

    private class NeighborIterator implements Iterator<GraphNode<N, E>> {

      private final Iterator<DiGraphEdge<N, E>> in = inEdgeList.iterator();
  {
    CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[73]++;
  }
      private final Iterator<DiGraphEdge<N, E>> out = outEdgeList.iterator();
  {
    CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[74]++;
  }

      @Override
      public boolean hasNext() {
        return in.hasNext() || out.hasNext();
      }

      @Override
      public GraphNode<N, E> next() {
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[75]++;
        boolean isOut = !in.hasNext();
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[76]++;
        Iterator<DiGraphEdge<N, E>> curIterator =  isOut ? out : in;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[77]++;
        DiGraphEdge<N, E> s = curIterator.next();
        return isOut ? s.getDestination() : s.getSource();
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("Remove not supported.");
      }
    }
  }

  /**
   * A directed graph node with annotations.
   */
  static class AnnotatedLinkedDirectedGraphNode<N, E>
      extends LinkedDirectedGraphNode<N, E> {

    protected Annotation annotation;

    /**
     * @param nodeValue Node's value.
     */
    AnnotatedLinkedDirectedGraphNode(N nodeValue) {
      super(nodeValue);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[78]++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends Annotation> A getAnnotation() {
      return (A) annotation;
    }

    @Override
    public void setAnnotation(Annotation data) {
      annotation = data;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[79]++;
    }
  }

  /**
   * A directed graph edge that stores the source and destination nodes at each
   * edge.
   */
  static class LinkedDirectedGraphEdge<N, E> implements DiGraphEdge<N, E>,
      GraphvizEdge {

    private DiGraphNode<N, E> sourceNode;

    private DiGraphNode<N, E> destNode;

    protected final E value;

    /**
     * Constructor.
     *
     * @param edgeValue Edge Value.
     */
    LinkedDirectedGraphEdge(DiGraphNode<N, E> sourceNode,
        E edgeValue, DiGraphNode<N, E> destNode) {
      this.value = edgeValue;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[80]++;
      this.sourceNode = sourceNode;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[81]++;
      this.destNode = destNode;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[82]++;
    }

    @Override
    public DiGraphNode<N, E> getSource() {
      return sourceNode;
    }

    @Override
    public DiGraphNode<N, E> getDestination() {
      return destNode;
    }

    @Override
    public void setDestination(DiGraphNode<N, E> node) {
      destNode = node;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[83]++;
    }

    @Override
    public void setSource(DiGraphNode<N, E> node) {
      sourceNode = node;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[84]++;
    }

    @Override
    public E getValue() {
      return value;
    }

    @Override
    public <A extends Annotation> A getAnnotation() {
      throw new UnsupportedOperationException(
          "Graph initialized with edge annotations turned off");
    }

    @Override
    public void setAnnotation(Annotation data) {
      throw new UnsupportedOperationException(
          "Graph initialized with edge annotations turned off");
    }

    @Override
    public String getColor() {
      return "black";
    }

    @Override
    public String getLabel() {
      return value != null ? value.toString() : "null";
    }

    @Override
    public String getNode1Id() {
      return ((LinkedDirectedGraphNode<N, E>) sourceNode).getId();
    }

    @Override
    public String getNode2Id() {
      return ((LinkedDirectedGraphNode<N, E>) destNode).getId();
    }

    @Override
    public String toString() {
      return sourceNode.toString() + " -> " + destNode.toString();
    }

    @Override
    public GraphNode<N, E> getNodeA() {
      return sourceNode;
    }

    @Override
    public GraphNode<N, E> getNodeB() {
      return destNode;
    }
  }

  /**
   * A directed graph edge that stores the source and destination nodes at each
   * edge.
   */
  static class AnnotatedLinkedDirectedGraphEdge<N, E>
      extends LinkedDirectedGraphEdge<N, E> {

    protected Annotation annotation;

    /**
     * Constructor.
     *
     * @param edgeValue Edge Value.
     */
    AnnotatedLinkedDirectedGraphEdge(DiGraphNode<N, E> sourceNode,
        E edgeValue, DiGraphNode<N, E> destNode) {
      super(sourceNode, edgeValue, destNode);
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[85]++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends Annotation> A getAnnotation() {
      return (A) annotation;
    }

    @Override
    public void setAnnotation(Annotation data) {
      annotation = data;
CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap.statements[86]++;
    }
  }
}

class CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap ());
  }
    public static long[] statements = new long[87];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.graph.LinkedDirectedGraph.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 8; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$hm05tvl9jt4popk0dhv4ij4kedapcwrq31qap () {
    super("com.google.javascript.jscomp.graph.LinkedDirectedGraph.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 86; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.graph.LinkedDirectedGraph.java");
      for (int i = 1; i <= 86; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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

