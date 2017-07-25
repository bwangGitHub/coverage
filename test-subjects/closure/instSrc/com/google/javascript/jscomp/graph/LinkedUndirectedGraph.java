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
 * An undirected graph using linked list within nodes to store edge
 * information.
 *
 *
 * @param <N> Value type that the graph node stores.
 * @param <E> Value type that the graph edge stores.
 */
public class LinkedUndirectedGraph<N, E>
    extends UndiGraph<N, E> implements GraphvizGraph {
  static {
    CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.ping();
  }

  protected final Map<N, LinkedUndirectedGraphNode<N, E>> nodes =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[1]++;
  }

  @Override
  public SubGraph<N, E> newSubGraph() {
    return new SimpleSubGraph<N, E>(this);
  }

  public static <N, E> LinkedUndirectedGraph<N, E> createWithoutAnnotations() {
    return new LinkedUndirectedGraph<N, E>(false, false);
  }

  public static <N, E> LinkedUndirectedGraph<N, E> createWithNodeAnnotations() {
    return new LinkedUndirectedGraph<N, E>(true, false);
  }

  public static <N, E> LinkedUndirectedGraph<N, E> createWithEdgeAnnotations() {
    return new LinkedUndirectedGraph<N, E>(false, true);
  }

  public static <N, E> LinkedUndirectedGraph<N, E> create() {
    return new LinkedUndirectedGraph<N, E>(true, true);
  }

  private final boolean useNodeAnnotations;
  private final boolean useEdgeAnnotations;

  protected LinkedUndirectedGraph(
      boolean useNodeAnnotations, boolean useEdgeAnnotations) {
    this.useNodeAnnotations = useNodeAnnotations;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[2]++;
    this.useEdgeAnnotations = useEdgeAnnotations;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[3]++;
  }

  @Override
  public void connect(N srcValue, E edgeValue, N destValue) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[4]++;
    LinkedUndirectedGraphNode<N, E> src = getNodeOrFail(srcValue);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[5]++;
    LinkedUndirectedGraphNode<N, E> dest = getNodeOrFail(destValue);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[6]++;
    LinkedUndirectedGraphEdge<N, E> edge =
        useEdgeAnnotations ?
        new AnnotatedLinkedUndirectedGraphEdge<N, E>(src, edgeValue, dest) :
        new LinkedUndirectedGraphEdge<N, E>(src, edgeValue, dest);
    src.getNeighborEdges().add(edge);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[7]++;
    dest.getNeighborEdges().add(edge);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[8]++;
  }

  @Override
  public void disconnect(N srcValue, N destValue) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[9]++;
    LinkedUndirectedGraphNode<N, E> src = getNodeOrFail(srcValue);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[10]++;
    LinkedUndirectedGraphNode<N, E> dest = getNodeOrFail(destValue);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[1]++;


    for (UndiGraphEdge<N, E> edge :
      getUndirectedGraphEdges(srcValue, destValue)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[1]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[2]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[3]++;
}
      src.getNeighborEdges().remove(edge);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[12]++;
      dest.getNeighborEdges().remove(edge);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[13]++;
    }
  }

  @Override
  public UndiGraphNode<N, E> createUndirectedGraphNode(
      N nodeValue) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[14]++;
    LinkedUndirectedGraphNode<N, E> node = nodes.get(nodeValue);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[1]++;
      node = useNodeAnnotations ?
          new AnnotatedLinkedUndirectedGraphNode<N, E>(nodeValue) :
          new LinkedUndirectedGraphNode<N, E>(nodeValue);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[16]++;
      nodes.put(nodeValue, node);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[17]++;

    } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[2]++;}
    return node;
  }

  @Override
  public List<GraphNode<N, E>> getNeighborNodes(N value) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[18]++;
    List<GraphNode<N, E>> nodeList = Lists.newArrayList();
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[19]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
    for (Iterator<GraphNode<N, E>> i = getNeighborNodesIterator(value);(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[4]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[5]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[6]++;
}
      nodeList.add(i.next());
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[20]++;
    }
    return nodeList;
  }

  @Override
  public Iterator<GraphNode<N, E>> getNeighborNodesIterator(N value) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[21]++;
    UndiGraphNode<N, E> uNode = getUndirectedGraphNode(value);
    Preconditions.checkNotNull(uNode, "%s should be in the graph.", value);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[22]++;
    return ((LinkedUndirectedGraphNode<N, E>) uNode).neighborIterator();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<UndiGraphEdge<N, E>> getUndirectedGraphEdges(N n1, N n2) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[23]++;
    UndiGraphNode<N, E> dNode1 = nodes.get(n1);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dNode1 == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[3]++;
      return null;

    } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[4]++;}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[25]++;
    UndiGraphNode<N, E> dNode2 = nodes.get(n2);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((dNode2 == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[5]++;
      return null;

    } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[6]++;}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[27]++;
    List<UndiGraphEdge<N, E>> edges = Lists.newArrayList();
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[28]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[7]++;


    for (UndiGraphEdge<N, E> outEdge : dNode1.getNeighborEdges()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[7]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[8]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[9]++;
}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((outEdge.getNodeA() == dNode2) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((outEdge.getNodeB() == dNode2) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[7]++;
        edges.add(outEdge);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[30]++;

      } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[8]++;}
    }
    return edges;
  }

  @Override
  public UndiGraphNode<N, E> getUndirectedGraphNode(N nodeValue) {
    return nodes.get(nodeValue);
  }

  @Override
  public Collection<UndiGraphNode<N, E>> getUndirectedGraphNodes() {
    return Collections.<UndiGraphNode<N, E>>unmodifiableCollection(
        nodes.values());
  }

  @Override
  public GraphNode<N, E> createNode(N value) {
    return createUndirectedGraphNode(value);
  }

  @Override
  public List<GraphEdge<N, E>> getEdges(N n1, N n2) {
    return Collections.<GraphEdge<N, E>>unmodifiableList(
        getUndirectedGraphEdges(n1, n2));
  }

  @Override
  public GraphEdge<N, E> getFirstEdge(N n1, N n2) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[31]++;
    UndiGraphNode<N, E> dNode1 = getNodeOrFail(n1);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[32]++;
    UndiGraphNode<N, E> dNode2 = getNodeOrFail(n2);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[33]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[10]++;


    for (UndiGraphEdge<N, E> outEdge : dNode1.getNeighborEdges()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[10]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[11]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[12]++;
}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((outEdge.getNodeA() == dNode2) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((outEdge.getNodeB() == dNode2) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[9]++;
        return outEdge;

      } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[10]++;}
    }
    return null;
  }

  @Override
  public GraphNode<N, E> getNode(N value) {
    return getUndirectedGraphNode(value);
  }

  @Override
  public boolean isConnected(N n1, N n2) {
    return isConnected(n1, Predicates.<E>alwaysTrue(), n2);
  }

  @Override
  public boolean isConnected(N n1, E e, N n2) {
    return isConnected(n1, Predicates.<E>equalTo(e), n2);
  }

  private boolean isConnected(N n1, Predicate<E> edgePredicate, N n2) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[35]++;
    UndiGraphNode<N, E> dNode1 = nodes.get(n1);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((dNode1 == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[11]++;
      return false;

    } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[12]++;}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[37]++;
    UndiGraphNode<N, E> dNode2 = nodes.get(n2);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((dNode2 == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[13]++;
      return false;

    } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[14]++;}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[39]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[13]++;


    for (UndiGraphEdge<N, E> outEdge : dNode1.getNeighborEdges()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[13]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[14]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[15]++;
}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[40]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C9 |= (128)) == 0 || true) &&
 ((outEdge.getNodeA() == dNode1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((outEdge.getNodeB() == dNode2) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((outEdge.getNodeA() == dNode2) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((outEdge.getNodeB() == dNode1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 4) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[15]++;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((edgePredicate.apply(outEdge.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[17]++;
          return true;

        } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[18]++;}

      } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[16]++;}
    }
    return false;
  }

  @Override
  public List<GraphvizEdge> getGraphvizEdges() {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[42]++;
    List<GraphvizEdge> edgeList = Lists.newArrayList();
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[43]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[16]++;


    for (LinkedUndirectedGraphNode<N, E> node : nodes.values()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[16]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[17]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[18]++;
}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[44]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[19]++;


      for (UndiGraphEdge<N, E> edge : node.getNeighborEdges()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[19]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[20]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[21]++;
}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((edge.getNodeA() == node) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[19]++;
          edgeList.add((GraphvizEdge) edge);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[46]++;

        } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[20]++;}
      }
    }
    return edgeList;
  }

  @Override
  public String getName() {
    return "LinkedUndirectedGraph";
  }

  @Override
  public List<GraphvizNode> getGraphvizNodes() {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[47]++;
    List<GraphvizNode> nodeList =
        Lists.newArrayListWithCapacity(nodes.size());
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[48]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[22]++;


    for (LinkedUndirectedGraphNode<N, E> node : nodes.values()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[22]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[23]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[24]++;
}
      nodeList.add(node);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[49]++;
    }
    return nodeList;
  }

  @Override
  public boolean isDirected() {
    return false;
  }

  @Override
  public Collection<GraphNode<N, E>> getNodes() {
    return Collections.<GraphNode<N, E>> unmodifiableCollection(nodes.values());
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<GraphEdge<N, E>> getEdges() {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[50]++;
    List<GraphEdge<N, E>> result = Lists.newArrayList();
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[51]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[25]++;


    for (LinkedUndirectedGraphNode<N, E> node : nodes.values()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[25]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[26]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[27]++;
}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[52]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[28]++;


      for (UndiGraphEdge<N, E> edge : node.getNeighborEdges()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[28]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[29]--;
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.loops[30]++;
}
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((edge.getNodeA() == node) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[21]++;
          result.add(edge);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[54]++;

        } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[22]++;}
      }
    }
    return result;
  }

  @Override
  public int getNodeDegree(N value) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[55]++;
    UndiGraphNode<N, E> uNode = getUndirectedGraphNode(value);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[56]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((uNode == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[23]++;
      throw new IllegalArgumentException(value + " not found in graph");

    } else {
  CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[24]++;}
    return uNode.getNeighborEdges().size();
  }

  /**
   * An undirected graph node that stores outgoing edges and incoming edges as
   * an list within the node itself.
   */
  static class LinkedUndirectedGraphNode<N, E> implements UndiGraphNode<N, E>,
      GraphvizNode {

    private List<UndiGraphEdge<N, E>> neighborList =
      Lists.newArrayList();
  {
    CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[57]++;
  }
    private final N value;

    LinkedUndirectedGraphNode(N nodeValue) {
      this.value = nodeValue;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[58]++;
    }

    @Override
    public List<UndiGraphEdge<N, E>> getNeighborEdges() {
      return neighborList;
    }

    @Override
    public Iterator<UndiGraphEdge<N, E>> getNeighborEdgesIterator() {
      return neighborList.iterator();
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
    public N getValue() {
      return value;
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

    public Iterator<GraphNode<N, E>> neighborIterator() {
      return new NeighborIterator();
    }

    private class NeighborIterator implements Iterator<GraphNode<N, E>> {

      private final Iterator<UndiGraphEdge<N, E>> edgeIterator =
          neighborList.iterator();
  {
    CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[59]++;
  }

      @Override
      public boolean hasNext() {
        return edgeIterator.hasNext();
      }

      @Override
      public GraphNode<N, E> next() {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[60]++;
        UndiGraphEdge<N, E> edge = edgeIterator.next();
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[61]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((edge.getNodeA() == LinkedUndirectedGraphNode.this) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[25]++;
          return edge.getNodeB();

        } else {
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.branches[26]++;
          return edge.getNodeA();
        }
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("Remove not supported.");
      }
    }
  }

  /**
   * An undirected graph node with annotations.
   */
  static class AnnotatedLinkedUndirectedGraphNode<N, E>
      extends LinkedUndirectedGraphNode<N, E> {

    protected Annotation annotation;

    AnnotatedLinkedUndirectedGraphNode(N nodeValue) {
      super(nodeValue);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[62]++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends Annotation> A getAnnotation() {
      return (A) annotation;
    }

    @Override
    public void setAnnotation(Annotation data) {
      annotation = data;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[63]++;
    }
  }

  /**
   * An undirected graph edge that stores two nodes at each edge.
   */
  static class LinkedUndirectedGraphEdge<N, E> implements UndiGraphEdge<N, E>,
      GraphvizEdge {

    private UndiGraphNode<N, E> nodeA;
    private UndiGraphNode<N, E> nodeB;
    protected final E value;

    LinkedUndirectedGraphEdge(UndiGraphNode<N, E> nodeA, E edgeValue,
        UndiGraphNode<N, E> nodeB) {
      this.value = edgeValue;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[64]++;
      this.nodeA = nodeA;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[65]++;
      this.nodeB = nodeB;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[66]++;
    }

    @Override
    public E getValue() {
      return value;
    }

    @Override
    public GraphNode<N, E> getNodeA() {
      return nodeA;
    }

    @Override
    public GraphNode<N, E> getNodeB() {
      return nodeB;
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

    @SuppressWarnings("unchecked")
    @Override
    public String getNode1Id() {
      return ((LinkedUndirectedGraphNode<N, E>) nodeA).getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getNode2Id() {
      return ((LinkedUndirectedGraphNode<N, E>) nodeB).getId();
    }

    @Override
    public String toString() {
      return nodeA.toString() + " -- " + nodeB.toString();
    }
  }

  /**
   * An annotated undirected graph edge..
   */
  static class AnnotatedLinkedUndirectedGraphEdge<N, E>
      extends LinkedUndirectedGraphEdge<N, E> {

    protected Annotation annotation;

    AnnotatedLinkedUndirectedGraphEdge(
        UndiGraphNode<N, E> nodeA, E edgeValue,
        UndiGraphNode<N, E> nodeB) {
      super(nodeA, edgeValue, nodeB);
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[67]++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <A extends Annotation> A getAnnotation() {
      return (A) annotation;
    }

    @Override
    public void setAnnotation(Annotation data) {
      annotation = data;
CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69.statements[68]++;
    }
  }
}

class CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69 ());
  }
    public static long[] statements = new long[69];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.graph.LinkedUndirectedGraph.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,2,1,1,3,1,1,1,1,1};
    for (int i = 1; i <= 14; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$oqk9yt2d4bp3qysz2a8l9ldirlywzw48g3eehk69 () {
    super("com.google.javascript.jscomp.graph.LinkedUndirectedGraph.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 68; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.graph.LinkedUndirectedGraph.java");
      for (int i = 1; i <= 68; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

