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

import java.util.List;

/**
 * A graph that can be dumped to a Graphviz DOT file.
 * <p>
 * An object which can be visualized as a graph should implement this interface.
 * The <code>DotFormatter.toDot</code> function can be used to get a
 * visualization of the object for debugging purpose.
 *
 */
public interface GraphvizGraph {

  /**
   * Name of the graph.
   *
   * @return Name of the graph.
   */
  String getName();

  /**
   * Graph type.
   *
   * @return True if the graph is a directed graph.
   */
  boolean isDirected();

  /**
   * Retrieve a list of nodes in the graph.
   *
   * @return A list of nodes in the graph.
   */
  List<GraphvizNode> getGraphvizNodes();

  /**
   * Retrieve a list of edges in the graph.
   *
   * @return A list of edges in the graph.
   */
  List<GraphvizEdge> getGraphvizEdges();


  /**
   * A Graphviz node.
   */
  interface GraphvizNode {

    /**
     * Retrieves the unique ID.
     *
     * @return A the unique ID of the node.
     */
    String getId();

    /**
     * Retrieves color of the node.
     *
     * @return The color of the node.
     */
    String getColor();

    /**
     * Retrieves the label of the node.
     *
     * @return Label of the node.
     */
    String getLabel();
  }


  /**
   * A Graphviz edge.
   */
  interface GraphvizEdge {

    /**
     * Get the first node in the edge. In a directed node, this will be the
     * source node.
     *
     * @return First node in the edge.
     */
    String getNode1Id();

    /**
     * Get the second node in the edge. In a directed node, this will be the
     * destination node.
     *
     * @return First node in the edge.
     */
    String getNode2Id();

    /**
     * Retrieves color of the edge.
     *
     * @return The color of the edge.
     */
    String getColor();

    /**
     * Retrieves the label of the edge.
     *
     * @return Label of the edge.
     */
    String getLabel();
  }
}

class CodeCoverCoverageCounter$5xw5pih9cby89hx7fm84xve8po4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5xw5pih9cby89hx7fm84xve8po4h ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$5xw5pih9cby89hx7fm84xve8po4h () {
    super("com.google.javascript.jscomp.graph.GraphvizGraph.java");
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
    log.startNamedSection("com.google.javascript.jscomp.graph.GraphvizGraph.java");
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

