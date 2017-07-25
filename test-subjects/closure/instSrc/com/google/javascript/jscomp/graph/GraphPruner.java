/*
 * Copyright 2011 The Closure Compiler Authors.
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

import com.google.common.base.Predicate;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;

/**
 * Prunes a graph, creating a new graph with nodes removed.
 *
 * If a node is removed from the graph, any paths through that node
 * will be replaced with edges. In other words, if A and B are nodes
 * in the original graph and the pruned graph, then there exists a path
 * from A -> B in the original graph iff there's a path from A -> B
 * in the pruned graph.
 *
 * We do not make any guarantees about what edges are in the pruned graph.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
public class GraphPruner<N, E> {
  static {
    CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.ping();
  }

  private final DiGraph<N, E> graph;

  public GraphPruner(DiGraph<N, E> graph) {
    this.graph = graph;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[1]++;
  }

  public LinkedDirectedGraph<N, E> prune(Predicate<N> keep) {
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[2]++;
    LinkedDirectedGraph<N, E> workGraph = cloneGraph(graph);
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[1]++;



    // Create a work graph where all nodes with a path between them have
    // an edge.
    for (DiGraphNode<N, E> node : workGraph.getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[1]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[2]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[3]++;
}
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[4]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[4]++;


      for (DiGraphEdge<N, E> inEdge : node.getInEdges()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[4]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[5]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[6]++;
}
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[5]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[7]++;


        for (DiGraphEdge<N, E> outEdge : node.getOutEdges()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[7]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[8]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[9]++;
}
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[6]++;
          N source = inEdge.getSource().getValue();
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[7]++;
          N dest = outEdge.getDestination().getValue();
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
          if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((workGraph.isConnectedInDirection(source, dest)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.branches[1]++;
            workGraph.connect(source, outEdge.getValue(), dest);
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[9]++;

          } else {
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.branches[2]++;}
        }
      }
    }
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[10]++;


    // Build a complete subgraph of workGraph.
    LinkedDirectedGraph<N, E> resultGraph =
        LinkedDirectedGraph.create();
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[11]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[10]++;


    for (DiGraphNode<N, E> node : workGraph.getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[10]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[11]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[12]++;
}
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((keep.apply(node.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.branches[3]++;
        resultGraph.createNode(node.getValue());
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[13]++;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[14]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[13]++;



        for (DiGraphEdge<N, E> outEdge : node.getOutEdges()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[13]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[14]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[15]++;
}
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[15]++;
          N source = node.getValue();
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[16]++;
          N dest = outEdge.getDestination().getValue();
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
          if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((keep.apply(dest)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.branches[5]++;
            resultGraph.createNode(dest);
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[18]++;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((source != dest) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((resultGraph.isConnectedInDirection(source, dest)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.branches[7]++;
              resultGraph.connect(source, outEdge.getValue(), dest);
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[20]++;

            } else {
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.branches[8]++;}

          } else {
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.branches[6]++;}
        }

      } else {
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.branches[4]++;}
    }

    return resultGraph;
  }

  private static <N, E> LinkedDirectedGraph<N, E> cloneGraph(
      DiGraph<N, E> graph) {
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[21]++;
    LinkedDirectedGraph<N, E> newGraph = LinkedDirectedGraph.create();
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[22]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[16]++;


    for (DiGraphNode<N, E> node : graph.getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[16]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[17]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[18]++;
}
      newGraph.createNode(node.getValue());
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[23]++;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[24]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[19]++;



      for (DiGraphEdge<N, E> outEdge : node.getOutEdges()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[19]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[20]--;
  CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.loops[21]++;
}
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[25]++;
        N dest = outEdge.getDestination().getValue();
        newGraph.createNode(dest);
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[26]++;
        newGraph.connect(node.getValue(), outEdge.getValue(), dest);
CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h.statements[27]++;
      }
    }

    return newGraph;
  }
}

class CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.graph.GraphPruner.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$489un4bxixrw0tezvum81nn8h () {
    super("com.google.javascript.jscomp.graph.GraphPruner.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.graph.GraphPruner.java");
      for (int i = 1; i <= 27; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

