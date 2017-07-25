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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * A generic undirected graph.
 *
 * @param <N> Value type that the graph node stores.
 * @param <E> Value type that the graph edge stores.
 */
public abstract class UndiGraph<N, E> extends Graph<N, E> {
  static {
    CodeCoverCoverageCounter$3lmgzdzgi55zz2qjbv4jfl.ping();
  }


  /**
   * Gets an immutable collection of all the nodes in this graph.
   */
  abstract Collection<UndiGraphNode<N, E>> getUndirectedGraphNodes();

  abstract UndiGraphNode<N, E> createUndirectedGraphNode(N nodeValue);

  public abstract UndiGraphNode<N, E> getUndirectedGraphNode(N nodeValue);

  abstract List<UndiGraphEdge<N, E>> getUndirectedGraphEdges(N n1, N n2);

  /**
   * A generic undirected graph node.
   *
   * @param <N> Value type that the graph node stores.
   * @param <E> Value type that the graph edge stores.
   */
  public static interface UndiGraphNode<N, E> extends GraphNode<N, E> {
    public List<UndiGraphEdge<N, E>> getNeighborEdges();
    public Iterator<UndiGraphEdge<N, E>> getNeighborEdgesIterator();
  }

  /**
   * A generic undirected graph edge.
   *
   * @param <N> Value type that the graph node stores.
   * @param <E> Value type that the graph edge stores.
   */
  public static interface UndiGraphEdge<N, E> extends GraphEdge<N, E> {
  }
}

class CodeCoverCoverageCounter$3lmgzdzgi55zz2qjbv4jfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3lmgzdzgi55zz2qjbv4jfl ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$3lmgzdzgi55zz2qjbv4jfl () {
    super("com.google.javascript.jscomp.graph.UndiGraph.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.graph.UndiGraph.java");
      for (int i = 1; i <= -1; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

