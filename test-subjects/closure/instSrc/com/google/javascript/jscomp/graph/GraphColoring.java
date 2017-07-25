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
import com.google.javascript.jscomp.graph.Annotation;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.jscomp.graph.SubGraph;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Annotates the graph with a color in a way that no connected node will have
 * the same color. Nodes of the same color cab then be partitioned together and
 * be represented by a super node. This class will merely annotate the nodes
 * with a color using {@link GraphNode#setAnnotation(Annotation)} and provide
 * a node to super node mapping with {@link #getPartitionSuperNode(Object)}. The
 * give graph itself will not be modified.
 *
 * <p>This algorithm is <b>NOT</b> deterministic by default. Passes that
 * requires deterministic output should provide a {@code Comparator} in the
 * constructor as a tie-breaker. This tie-break will be used when deciding
 * which node should be colored first when multiple nodes have the same degree.
 *
 * @param <N> Value type that the graph node stores.
 * @param <E> Value type that the graph edge stores.
 *
 */
public abstract class GraphColoring<N, E> {
  static {
    CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.ping();
  }

  // Maps a color (represented by an integer) to a variable. If, for example,
  // the color 5 is mapped to "foo". Then any other variables colored with the
  // color 5 will now use the name "foo".
  protected N[] colorToNodeMap;
  protected final AdjacencyGraph<N, E> graph;

  public GraphColoring(AdjacencyGraph<N, E> graph) {
    this.graph = graph;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[1]++;
  }

  /**
   * Annotates the graph with {@link Color} objects using
   * {@link GraphNode#setAnnotation(Annotation)}.
   *
   * @return The number of unique colors need.
   */
  public abstract int color();

  /**
   * Using the coloring as partitions, finds the node that represents that
   * partition as the super node. The first to retrieve its partition will
   * become the super node.
   */
  public N getPartitionSuperNode(N node) {
    Preconditions.checkNotNull(colorToNodeMap,
        "No coloring founded. color() should be called first.");
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[2]++;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[3]++;
    Color color = graph.getNode(node).getAnnotation();
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[4]++;
    N headNode = colorToNodeMap[color.value];
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((headNode == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.branches[1]++;
      colorToNodeMap[color.value] = node;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[6]++;
      return node;

    } else {
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.branches[2]++;
      return headNode;
    }
  }

  public AdjacencyGraph<N, E> getGraph() {
    return graph;
  }

  public static class Color implements Annotation {
    int value = 0;
  {
    CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[7]++;
  }

    Color(int value) {
      this.value = value;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[8]++;
    }

    @Override
    public boolean equals(Object other) {
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((other instanceof Color) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.branches[3]++;
        return false;

      } else {
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.branches[4]++;
        return value == ((Color) other).value;
      }
    }

    @Override
    public int hashCode() {
      return value;
    }
  }

  /**
   * Greedily assign nodes with high degree unique colors.
   */
  public static class GreedyGraphColoring<N, E> extends GraphColoring<N, E> {

    private final Comparator<N> tieBreaker;
    public GreedyGraphColoring(AdjacencyGraph<N, E> graph) {
      this(graph, null);
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[10]++;
    }

    /**
     * @param tieBreaker In case of a tie between two nodes of the same degree,
     *     this comparator will determine which node should be colored first.
     */
    public GreedyGraphColoring(
        AdjacencyGraph<N, E> graph, Comparator<N> tieBreaker) {
      super(graph);
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[11]++;
      this.tieBreaker = tieBreaker;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[12]++;
    }

    @Override
    public int color() {
      graph.clearNodeAnnotations();
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[13]++;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[14]++;
      List<GraphNode<N, E>> worklist = Lists.newArrayList(graph.getNodes());

      // Sort nodes by degree.
      Collections.sort(worklist, new Comparator<GraphNode<N, E>>() {
        @Override
        public int compare(GraphNode<N, E> o1, GraphNode<N, E> o2) {
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[16]++;
          int result = graph.getWeight(o2.getValue())
              - graph.getWeight(o1.getValue());
          return result == 0 && tieBreaker != null ?
              tieBreaker.compare(o1.getValue(), o2.getValue()) : result;
        }
      });
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[15]++;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[17]++;

      // Idea: From the highest to lowest degree, assign any uncolored node with
      // a unique color if none of its neighbor has been assigned that color.
      int count = 0;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
      do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[1]--;
  CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[2]--;
  CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[3]++;
}
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[19]++;
        Color color = new Color(count);
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[20]++;
        SubGraph<N, E> subgraph = graph.newSubGraph();
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[21]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
        for (Iterator<GraphNode<N, E>> i = worklist.iterator();(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[4]--;
  CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[5]--;
  CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.loops[6]++;
}
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[22]++;
          GraphNode<N, E> node = i.next();
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((subgraph.isIndependentOf(node.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.branches[5]++;
            subgraph.addNode(node.getValue());
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[24]++;
            node.setAnnotation(color);
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[25]++;
            i.remove();
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[26]++;

          } else {
  CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.branches[6]++;}
        }
        count++;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[27]++;
      } while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((worklist.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false));
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[28]++;
      @SuppressWarnings("unchecked")
      N[] map = (N[]) new Object[count];
      colorToNodeMap = map;
CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x.statements[29]++;
      return count;
    }
  }
}

class CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.graph.GraphColoring.java";
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$5xw5pih91gdsgje63fk0bbrs2z4x () {
    super("com.google.javascript.jscomp.graph.GraphColoring.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 29; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.graph.GraphColoring.java");
      for (int i = 1; i <= 29; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
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
      for (int i = 1; i <= 2; i++) {
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

