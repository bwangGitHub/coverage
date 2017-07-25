/*
 * Copyright 2007 The Closure Compiler Authors.
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

import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.graph.GraphvizGraph;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;
import com.google.javascript.jscomp.graph.GraphvizGraph.GraphvizEdge;
import com.google.javascript.jscomp.graph.GraphvizGraph.GraphvizNode;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.JSType;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>DotFormatter prints out a dot file of the Abstract Syntax Tree.
 * For a detailed description of the dot format and visualization tool refer
 * to <a href="http://www.graphviz.org">Graphviz</a>.</p>
 * <p>Typical usage of this class</p>
 * <code>System.out.println(new DotFormatter().toDot(<i>node</i>));</code>
 * <p>This class is <b>not</b> thread safe and should not be used without proper
 * external synchronization.</p>
 *
 */
public class DotFormatter {
  static {
    CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.ping();
  }

  private static final String INDENT = "  ";
  static {
    CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[1]++;
  }
  private static final String ARROW = " -> ";
  static {
    CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[2]++;
  }
  private static final String LINE = " -- ";
  static {
    CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[3]++;
  }

  // stores the current assignment of node to keys
  private HashMap<Node, Integer> assignments = new HashMap<Node, Integer>();
  {
    CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[4]++;
  }

  // key count in order to assign a unique key to each node
  private int keyCount = 0;
  {
    CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[5]++;
  }

  // the builder used to generate the dot diagram
  private Appendable builder;

  private final ControlFlowGraph<Node> cfg;

  private final boolean printAnnotations;

  /** For Testing Only */
  private DotFormatter() {
    this.builder = new StringBuilder();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[6]++;
    this.cfg = null;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[7]++;
    this.printAnnotations = false;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[8]++;
  }

  private DotFormatter(Node n, ControlFlowGraph<Node> cfg,
      Appendable builder, boolean printAnnotations) throws IOException {
    this.cfg = cfg;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[9]++;
    this.builder = builder;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[10]++;
    this.printAnnotations = printAnnotations;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[11]++;

    formatPreamble();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[12]++;
    traverseNodes(n);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[13]++;
    formatConclusion();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[14]++;
  }

  /**
   * Converts an AST to dot representation.
   * @param n the root of the AST described in the dot formatted string
   * @return the dot representation of the AST
   */
  public static String toDot(Node n) throws IOException  {
    return toDot(n, null);
  }

  /**
   * Converts an AST to dot representation.
   * @param n the root of the AST described in the dot formatted string
   * @param inCFG Control Flow Graph.
   * @param printAnnotations print annotations.
   * @return the dot representation of the AST
   */
  static String toDot(
      Node n, ControlFlowGraph<Node> inCFG, boolean printAnnotations)
      throws IOException  {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[15]++;
    StringBuilder builder = new StringBuilder();
    new DotFormatter(n, inCFG, builder, printAnnotations);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[16]++;
    return builder.toString();
  }

  /**
   * Converts an AST to dot representation.
   * @param n the root of the AST described in the dot formatted string
   * @param inCFG Control Flow Graph.
   * @return the dot representation of the AST
   */
  static String toDot(Node n, ControlFlowGraph<Node> inCFG)
      throws IOException  {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[17]++;
    StringBuilder builder = new StringBuilder();
    new DotFormatter(n, inCFG, builder, false);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[18]++;
    return builder.toString();
  }

  /**
   * Converts an AST to dot representation and appends it to the given buffer.
   * @param n the root of the AST described in the dot formatted string
   * @param inCFG Control Flow Graph.
   * @param builder A place to dump the graph.
   */
  static void appendDot(Node n, ControlFlowGraph<Node> inCFG,
      Appendable builder) throws IOException {
    new DotFormatter(n, inCFG, builder, false);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[19]++;
  }

  /**
   * Creates a DotFormatter purely for testing DotFormatter's internal methods.
   */
  static DotFormatter newInstanceForTesting() {
    return new DotFormatter();
  }

  private void traverseNodes(Node parent) throws IOException {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[20]++;
    // key
    int keyParent = key(parent);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[21]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

    // edges
    for (Node child = parent.getFirstChild();(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false);
        child = child.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[1]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[2]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[3]++;
}
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[22]++;
      int keyChild = key(child);
      builder.append(INDENT);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[23]++;
      builder.append(formatNodeName(keyParent));
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[24]++;
      builder.append(ARROW);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[25]++;
      builder.append(formatNodeName(keyChild));
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[26]++;
      builder.append(" [weight=1];\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[27]++;

      traverseNodes(child);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[28]++;
    }
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[29]++;
int CodeCoverConditionCoverageHelper_C2;

    // Flow Edges
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((cfg != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cfg.hasNode(parent)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[1]++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[30]++;
      List<DiGraphEdge<Node, Branch>> outEdges =
        cfg.getOutEdges(parent);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[31]++;
      String[] edgeList = new String[outEdges.size()];
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[32]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < edgeList.length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[4]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[5]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[6]++;
}
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[33]++;
        DiGraphEdge<Node, ControlFlowGraph.Branch> edge = outEdges.get(i);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[34]++;
        DiGraphNode<Node, Branch> succ = edge.getDestination();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[35]++;

        String toNode = null;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((succ == cfg.getImplicitReturn()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[3]++;
          toNode = "RETURN";
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[37]++;

        } else {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[4]++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[38]++;
          int keySucc = key(succ.getValue());
          toNode = formatNodeName(keySucc);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[39]++;
        }

        edgeList[i] = formatNodeName(keyParent) + ARROW + toNode + " [label=\""
          + edge.getValue().toString() + "\", " + "fontcolor=\"red\", " +
          "weight=0.01, color=\"red\"];\n";
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[40]++;
      }

      Arrays.sort(edgeList);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[41]++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[42]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[7]++;


int CodeCoverConditionCoverageHelper_C5;

      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < edgeList.length) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[7]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[8]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[9]++;
}
          builder.append(INDENT);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[43]++;
          builder.append(edgeList[i]);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[44]++;
      }

    } else {
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[2]++;}
  }

  int key(Node n) throws IOException {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[45]++;
    Integer key = assignments.get(n);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[46]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[5]++;
      key = keyCount++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[47]++;
      assignments.put(n, key);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[48]++;
      builder.append(INDENT);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[49]++;
      builder.append(formatNodeName(key));
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[50]++;
      builder.append(" [label=\"");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[51]++;
      builder.append(name(n));
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[52]++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[53]++;
      JSType type = n.getJSType();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[54]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((type != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[7]++;
        builder.append(" : ");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[55]++;
        builder.append(type.toString());
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[56]++;

      } else {
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[8]++;}
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[57]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((printAnnotations) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((cfg != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((cfg.hasNode(n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false)) {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[9]++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[58]++;
        Object annotation = cfg.getNode(n).getAnnotation();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[59]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((annotation != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[11]++;
          builder.append("\\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[60]++;
          builder.append(annotation.toString());
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[61]++;

        } else {
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[12]++;}

      } else {
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[10]++;}
      builder.append("\"");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[62]++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[63]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[13]++;
        builder.append(" color=\"green\"");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[64]++;

      } else {
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[14]++;}
      builder.append("];\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[65]++;

    } else {
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[6]++;}
    return key;
  }

  private String name(Node n) {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[66]++;
    int type = n.getType();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[67]++;
    switch (type) {
      case Token.VOID:
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[15]++;
        return "VOID";

      default:
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.branches[16]++;
        return Token.name(type);
    }
  }

  private String formatNodeName(Integer key) {
    return "node" + key;
  }

  private void formatPreamble() throws IOException {
    builder.append("digraph AST {\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[68]++;
    builder.append(INDENT);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[69]++;
    builder.append("node [color=lightblue2, style=filled];\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[70]++;
  }

  private void formatConclusion() throws IOException {
    builder.append("}\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[71]++;
  }

  /**
   * Outputs a string in DOT format that presents the graph.
   *
   * @param graph Input graph.
   * @return A string in Dot format that presents the graph.
   */
  public static String toDot(GraphvizGraph graph) {
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[72]++;
    StringBuilder builder = new StringBuilder ();
    builder.append(graph.isDirected() ? "digraph" : "graph");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[73]++;
    builder.append(INDENT);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[74]++;
    builder.append(graph.getName());
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[75]++;
    builder.append(" {\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[76]++;
    builder.append(INDENT);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[77]++;
    builder.append("node [color=lightblue2, style=filled];\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[78]++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[79]++;

    final String edgeSymbol = graph.isDirected() ? ARROW : LINE;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[80]++;

    List<GraphvizNode> nodes = graph.getGraphvizNodes();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[81]++;

    String[] nodeNames = new String[nodes.size()];
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[82]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[10]++;


int CodeCoverConditionCoverageHelper_C11;

    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < nodeNames.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[10]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[11]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[12]++;
}
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[83]++;
      GraphvizNode gNode = nodes.get(i);
      nodeNames[i] = gNode.getId() + " [label=\"" + gNode.getLabel() +
          "\" color=\"" + gNode.getColor() + "\"]";
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[84]++;
    }

    // We sort the nodes so we get a deterministic output every time regardless
    // of the implementation of the graph data structure.
    Arrays.sort(nodeNames);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[85]++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[86]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[13]++;



    for (String nodeName : nodeNames) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[13]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[14]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[15]++;
}
      builder.append(INDENT);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[87]++;
      builder.append(nodeName);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[88]++;
      builder.append(";\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[89]++;
    }
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[90]++;

    List<GraphvizEdge> edges = graph.getGraphvizEdges();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[91]++;

    String[] edgeNames = new String[edges.size()];
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[92]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[16]++;


int CodeCoverConditionCoverageHelper_C12;

    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i < edgeNames.length) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[16]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[17]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[18]++;
}
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[93]++;
      GraphvizEdge edge = edges.get(i);
      edgeNames[i] = edge.getNode1Id() + edgeSymbol + edge.getNode2Id();
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[94]++;
    }

    // Again, we sort the edges as well.
    Arrays.sort(edgeNames);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[95]++;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[96]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[19]++;



    for (String edgeName : edgeNames) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[19]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[20]--;
  CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.loops[21]++;
}
      builder.append(INDENT);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[97]++;
      builder.append(edgeName);
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[98]++;
      builder.append(";\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[99]++;
    }

    builder.append("}\n");
CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x.statements[100]++;
    return builder.toString();
  }
}

class CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x ());
  }
    public static long[] statements = new long[101];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.DotFormatter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,3,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$st6zjg2gkmy8br93qroox7hg0x () {
    super("com.google.javascript.jscomp.DotFormatter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 100; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.DotFormatter.java");
      for (int i = 1; i <= 100; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

