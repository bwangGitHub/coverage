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

package com.google.javascript.jscomp;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphNode;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * This is a compiler pass that computes a control flow graph.
 *
 */
final class ControlFlowAnalysis implements Callback, CompilerPass {
  static {
    CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.ping();
  }


  /**
   * Based roughly on the first few pages of
   *
   * "Declarative Intraprocedural Flow Analysis of Java Source Code by
   * Nilsson-Nyman, Hedin, Magnusson & Ekman",
   *
   * this pass computes the control flow graph from the AST. However, a full
   * attribute grammar is not necessary. We will compute the flow edges with a
   * single post order traversal. The "follow()" of a given node will be
   * computed recursively in a demand driven fashion.
   *
   * As of this moment, we are not performing any inter-procedural analysis
   * within our framework.
   */

  private final AbstractCompiler compiler;

  private ControlFlowGraph<Node> cfg;

  private Map<Node, Integer> astPosition;

  // TODO(nicksantos): should these be node annotations?
  private Map<DiGraphNode<Node, Branch>, Integer> nodePriorities;

  // We order CFG nodes by by looking at the AST positions.
  // CFG nodes that come first lexically should be visited first, because
  // they will often be executed first in the source program.
  private final Comparator<DiGraphNode<Node, Branch>> priorityComparator =
      new Comparator<DiGraphNode<Node, Branch>>() {
    @Override
    public int compare(
        DiGraphNode<Node, Branch> a, DiGraphNode<Node, Branch> b) {
      return astPosition.get(a.getValue()) - astPosition.get(b.getValue());
    }
  };
  {
    CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[1]++;
  }

  private int astPositionCounter;
  private int priorityCounter;

  private final boolean shouldTraverseFunctions;
  private final boolean edgeAnnotations;

  // We need to store where we started, in case we aren't doing a flow analysis
  // for the whole scope. This happens, for example, when running type inference
  // on only the externs.
  private Node root;

  /*
   * This stack captures the structure of nested TRY blocks. The top of the
   * stack is the inner most TRY block. A FUNCTION node in this stack implies
   * that the handler is determined by the caller of the function at runtime.
   */
  private final Deque<Node> exceptionHandler = new ArrayDeque<Node>();
  {
    CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[2]++;
  }

  /*
   * This map is used to handle the follow of FINALLY. For example:
   *
   * while(x) {
   *  try {
   *    try {
   *      break;
   *    } catch (a) {
   *    } finally {
   *      foo();
   *    }
   *    fooFollow();
   *  } catch (b) {
   *  } finally {
   *    bar();
   *  }
   *  barFollow();
   * }
   * END();
   *
   * In this case finallyMap will contain a map from:
   *    first FINALLY -> bar()
   *    second FINALLY -> END()
   *
   * When we are connecting foo() and bar() to to their respective follow, we
   * must also look up this map and connect:
   *   foo() -> bar()
   *   bar() -> END
   */
  private final Multimap<Node, Node> finallyMap = HashMultimap.create();
  {
    CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[3]++;
  }

  /**
   * Constructor.
   *
   * @param compiler Compiler instance.
   * @param shouldTraverseFunctions Whether functions should be traversed (true
   *    by default).
   * @param edgeAnnotations Whether to allow edge annotations. By default,
   *    only node annotations are allowed.
   */
  ControlFlowAnalysis(AbstractCompiler compiler,
      boolean shouldTraverseFunctions, boolean edgeAnnotations) {
    this.compiler = compiler;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[4]++;
    this.shouldTraverseFunctions = shouldTraverseFunctions;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[5]++;
    this.edgeAnnotations = edgeAnnotations;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[6]++;
  }

  ControlFlowGraph<Node> getCfg() {
    return cfg;
  }

  @Override
  public void process(Node externs, Node root) {
    this.root = root;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[7]++;
    astPositionCounter = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[8]++;
    astPosition = Maps.newHashMap();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[9]++;
    nodePriorities = Maps.newHashMap();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[10]++;
    cfg = new AstControlFlowGraph(computeFallThrough(root), nodePriorities,
                                  edgeAnnotations);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[11]++;
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[12]++;
    astPosition.put(null, ++astPositionCounter);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[13]++; // the implicit return is last.

    // Now, generate the priority of nodes by doing a depth-first
    // search on the CFG.
    priorityCounter = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[14]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[15]++;
    DiGraphNode<Node, Branch> entry = cfg.getEntry();
    prioritizeFromEntryNode(entry);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[16]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((shouldTraverseFunctions) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[1]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[1]++;


      // If we're traversing inner functions, we need to rank the
      // priority of them too.
      for (DiGraphNode<Node, Branch> candidate : cfg.getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[1]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[2]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[3]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[19]++;
        Node value = candidate.getValue();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[3]++;
          Preconditions.checkState(
              !nodePriorities.containsKey(candidate) || candidate == entry);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[21]++;
          prioritizeFromEntryNode(candidate);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[22]++;

        } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[4]++;}
      }

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[2]++;}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[23]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[4]++;



    // At this point, all reachable nodes have been given a priority, but
    // unreachable nodes have not been given a priority. Put them last.
    // Presumably, it doesn't really matter what priority they get, since
    // this shouldn't happen in real code.
    for (DiGraphNode<Node, Branch> candidate : cfg.getDirectedGraphNodes()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[4]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[5]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[6]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nodePriorities.containsKey(candidate)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[5]++;
        nodePriorities.put(candidate, ++priorityCounter);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[25]++;

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[6]++;}
    }

    // Again, the implicit return node is always last.
    nodePriorities.put(cfg.getImplicitReturn(), ++priorityCounter);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[26]++;
  }

  /**
   * Given an entry node, find all the nodes reachable from that node
   * and prioritize them.
   */
  private void prioritizeFromEntryNode(DiGraphNode<Node, Branch> entry) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[27]++;
    PriorityQueue<DiGraphNode<Node, Branch>> worklist =
        new PriorityQueue<DiGraphNode<Node, Branch>>(10, priorityComparator);
    worklist.add(entry);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[28]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[29]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[7]++;


int CodeCoverConditionCoverageHelper_C4;

    while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((worklist.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[7]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[8]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[9]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[30]++;
      DiGraphNode<Node, Branch> current = worklist.remove();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((nodePriorities.containsKey(current)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[7]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[32]++;
        continue;

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[8]++;}

      nodePriorities.put(current, ++priorityCounter);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[33]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[34]++;

      List<DiGraphNode<Node, Branch>> successors =
          cfg.getDirectedSuccNodes(current);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[35]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[10]++;


      for (DiGraphNode<Node, Branch> candidate : successors) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[10]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[11]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[12]++;
}
        worklist.add(candidate);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[36]++;
      }
    }
  }

  @Override
  public boolean shouldTraverse(
      NodeTraversal nodeTraversal, Node n, Node parent) {
    astPosition.put(n, astPositionCounter++);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[37]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[38]++;

    switch (n.getType()) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[9]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((shouldTraverseFunctions) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n == cfg.getEntry().getValue()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[10]++;
          exceptionHandler.push(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[40]++;
          return true;

        } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[11]++;}
        return false;
      case Token.TRY:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[12]++;
        exceptionHandler.push(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[41]++;
        return true; default : CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[13]++;
    }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;

    /*
     * We are going to stop the traversal depending on what the node's parent
     * is.
     *
     * We are only interested in adding edges between nodes that change control
     * flow. The most obvious ones are loops and IF-ELSE's. A statement
     * transfers control to its next sibling.
     *
     * In case of an expression tree, there is no control flow within the tree
     * even when there are short circuited operators and conditionals. When we
     * are doing data flow analysis, we will simply synthesize lattices up the
     * expression tree by finding the meet at each expression node.
     *
     * For example: within a Token.SWITCH, the expression in question does not
     * change the control flow and need not to be considered.
     */
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[14]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[43]++;
      switch (parent.getType()) {
        case Token.FOR:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[16]++;
          // Only traverse the body of the for loop.
          return n == parent.getLastChild();

        // Skip the conditions.
        case Token.IF:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[17]++;
        case Token.WHILE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[18]++;
        case Token.WITH:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[19]++;
          return n != parent.getFirstChild();
        case Token.DO:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[20]++;
          return n != parent.getFirstChild().getNext();
        // Only traverse the body of the cases
        case Token.SWITCH:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[21]++;
        case Token.CASE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[22]++;
        case Token.CATCH:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[23]++;
        case Token.LABEL:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[24]++;
          return n != parent.getFirstChild();
        case Token.FUNCTION:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[25]++;
          return n == parent.getFirstChild().getNext().getNext();
        case Token.CONTINUE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[26]++;
        case Token.BREAK:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[27]++;
        case Token.EXPR_RESULT:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[28]++;
        case Token.VAR:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[29]++;
        case Token.RETURN:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[30]++;
        case Token.THROW:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[31]++;
          return false;
        case Token.TRY:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[32]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[44]++;
int CodeCoverConditionCoverageHelper_C8;
          /* Just before we are about to visit the second child of the TRY node,
           * we know that we will be visiting either the CATCH or the FINALLY.
           * In other words, we know that the post order traversal of the TRY
           * block has been finished, no more exceptions can be caught by the
           * handler at this TRY block and should be taken out of the stack.
           */
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((n == parent.getFirstChild().getNext()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[33]++;
            Preconditions.checkState(exceptionHandler.peek() == parent);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[45]++;
            exceptionHandler.pop();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[46]++;

          } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[34]++;} default : CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[35]++;
      }

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[15]++;}
    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[47]++;
    switch (n.getType()) {
      case Token.IF:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[36]++;
        handleIf(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[48]++;
        return;
      case Token.WHILE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[37]++;
        handleWhile(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[49]++;
        return;
      case Token.DO:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[38]++;
        handleDo(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[50]++;
        return;
      case Token.FOR:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[39]++;
        handleFor(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[51]++;
        return;
      case Token.SWITCH:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[40]++;
        handleSwitch(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[52]++;
        return;
      case Token.CASE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[41]++;
        handleCase(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[53]++;
        return;
      case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[42]++;
        handleDefault(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[54]++;
        return;
      case Token.BLOCK:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[43]++;
      case Token.SCRIPT:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[44]++;
        handleStmtList(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[55]++;
        return;
      case Token.FUNCTION:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[45]++;
        handleFunction(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[56]++;
        return;
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[46]++;
        handleExpr(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[57]++;
        return;
      case Token.THROW:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[47]++;
        handleThrow(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[58]++;
        return;
      case Token.TRY:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[48]++;
        handleTry(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[59]++;
        return;
      case Token.CATCH:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[49]++;
        handleCatch(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[60]++;
        return;
      case Token.BREAK:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[50]++;
        handleBreak(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[61]++;
        return;
      case Token.CONTINUE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[51]++;
        handleContinue(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[62]++;
        return;
      case Token.RETURN:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[52]++;
        handleReturn(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[63]++;
        return;
      case Token.WITH:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[53]++;
        handleWith(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[64]++;
        return;
      case Token.LABEL:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[54]++;
        return;
      default:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[55]++;
        handleStmt(n);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[65]++;
        return;
    }
  }

  private void handleIf(Node node) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[66]++;
    Node thenBlock = node.getFirstChild().getNext();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[67]++;
    Node elseBlock = thenBlock.getNext();
    createEdge(node, Branch.ON_TRUE, computeFallThrough(thenBlock));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[68]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[69]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((elseBlock == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[56]++;
      createEdge(node, Branch.ON_FALSE,
          computeFollowNode(node, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[70]++;
 // not taken branch
    } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[57]++;
      createEdge(node, Branch.ON_FALSE, computeFallThrough(elseBlock));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[71]++;
    }
    connectToPossibleExceptionHandler(
        node, NodeUtil.getConditionExpression(node));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[72]++;
  }

  private void handleWhile(Node node) {
    // Control goes to the first statement if the condition evaluates to true.
    createEdge(node, Branch.ON_TRUE,
        computeFallThrough(node.getFirstChild().getNext()));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[73]++;

    // Control goes to the follow() if the condition evaluates to false.
    createEdge(node, Branch.ON_FALSE,
        computeFollowNode(node, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[74]++;
    connectToPossibleExceptionHandler(
        node, NodeUtil.getConditionExpression(node));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[75]++;
  }

  private void handleDo(Node node) {
    // The first edge can be the initial iteration as well as the iterations
    // after.
    createEdge(node, Branch.ON_TRUE, computeFallThrough(node.getFirstChild()));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[76]++;
    // The edge that leaves the do loop if the condition fails.
    createEdge(node, Branch.ON_FALSE,
        computeFollowNode(node, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[77]++;
    connectToPossibleExceptionHandler(
        node, NodeUtil.getConditionExpression(node));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[78]++;
  }

  private void handleFor(Node forNode) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[79]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((forNode.getChildCount() == 4) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[58]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[80]++;
      // We have for (init; cond; iter) { body }
      Node init = forNode.getFirstChild();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[81]++;
      Node cond = init.getNext();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[82]++;
      Node iter = cond.getNext();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[83]++;
      Node body = iter.getNext();
      // After initialization, we transfer to the FOR which is in charge of
      // checking the condition (for the first time).
      createEdge(init, Branch.UNCOND, forNode);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[84]++;
      // The edge that transfer control to the beginning of the loop body.
      createEdge(forNode, Branch.ON_TRUE, computeFallThrough(body));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[85]++;
      // The edge to end of the loop.
      createEdge(forNode, Branch.ON_FALSE,
          computeFollowNode(forNode, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[86]++;
      // The end of the body will have a unconditional branch to our iter
      // (handled by calling computeFollowNode of the last instruction of the
      // body. Our iter will jump to the forNode again to another condition
      // check.
      createEdge(iter, Branch.UNCOND, forNode);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[87]++;
      connectToPossibleExceptionHandler(init, init);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[88]++;
      connectToPossibleExceptionHandler(forNode, cond);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[89]++;
      connectToPossibleExceptionHandler(iter, iter);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[90]++;

    } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[59]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[91]++;
      // We have for (item in collection) { body }
      Node item = forNode.getFirstChild();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[92]++;
      Node collection = item.getNext();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[93]++;
      Node body = collection.getNext();
      // The collection behaves like init.
      createEdge(collection, Branch.UNCOND, forNode);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[94]++;
      // The edge that transfer control to the beginning of the loop body.
      createEdge(forNode, Branch.ON_TRUE, computeFallThrough(body));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[95]++;
      // The edge to end of the loop.
      createEdge(forNode, Branch.ON_FALSE,
          computeFollowNode(forNode, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[96]++;
      connectToPossibleExceptionHandler(forNode, collection);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[97]++;
    }
  }

  private void handleSwitch(Node node) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[98]++;
    // Transfer to the first non-DEFAULT CASE. if there are none, transfer
    // to the DEFAULT or the EMPTY node.
    Node next = getNextSiblingOfType(
        node.getFirstChild().getNext(), Token.CASE, Token.EMPTY);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[99]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[60]++; // Has at least one CASE or EMPTY
      createEdge(node, Branch.UNCOND, next);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[100]++;

    } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[61]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[101]++;
int CodeCoverConditionCoverageHelper_C12; // Has no CASE but possibly a DEFAULT
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((node.getFirstChild().getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[62]++;
        createEdge(node, Branch.UNCOND, node.getFirstChild().getNext());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[102]++;

      } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[63]++; // No CASE, no DEFAULT
        createEdge(node, Branch.UNCOND, computeFollowNode(node, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[103]++;
      }
    }
    connectToPossibleExceptionHandler(node, node.getFirstChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[104]++;
  }

  private void handleCase(Node node) {
    // Case is a bit tricky....First it goes into the body if condition is true.
    createEdge(node, Branch.ON_TRUE,
        node.getFirstChild().getNext());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[105]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[106]++;
    // Look for the next CASE, skipping over DEFAULT.
    Node next = getNextSiblingOfType(node.getNext(), Token.CASE);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[107]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[64]++; // Found a CASE
      Preconditions.checkState(next.isCase());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[108]++;
      createEdge(node, Branch.ON_FALSE, next);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[109]++;

    } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[65]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[110]++; // No more CASE found, go back and search for a DEFAULT.
      Node parent = node.getParent();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[111]++;
      Node deflt = getNextSiblingOfType(
        parent.getFirstChild().getNext(), Token.DEFAULT_CASE);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[112]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((deflt != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[66]++; // Has a DEFAULT
        createEdge(node, Branch.ON_FALSE, deflt);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[113]++;

      } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[67]++; // No DEFAULT found, go to the follow of the SWITCH.
        createEdge(node, Branch.ON_FALSE, computeFollowNode(node, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[114]++;
      }
    }
    connectToPossibleExceptionHandler(node, node.getFirstChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[115]++;
  }

  private void handleDefault(Node node) {
    // Directly goes to the body. It should not transfer to the next case.
    createEdge(node, Branch.UNCOND, node.getFirstChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[116]++;
  }

  private void handleWith(Node node) {
    // Directly goes to the body. It should not transfer to the next case.
    createEdge(node, Branch.UNCOND, node.getLastChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[117]++;
    connectToPossibleExceptionHandler(node, node.getFirstChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[118]++;
  }

  private void handleStmtList(Node node) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[119]++;
    Node parent = node.getParent();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[120]++;
int CodeCoverConditionCoverageHelper_C15;
    // Special case, don't add a block of empty CATCH block to the graph.
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (512)) == 0 || true) &&
 ((node.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (128)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((parent.isTry()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((NodeUtil.getCatchBlock(parent) == node) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((NodeUtil.hasCatchHandler(node)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 5) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 5) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[68]++;
      return;

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[69]++;}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[121]++;

    // A block transfer control to its first child if it is not empty.
    Node child = node.getFirstChild();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[122]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[13]++;


int CodeCoverConditionCoverageHelper_C16;

    // Function declarations are skipped since control doesn't go into that
    // function (unless it is called)
    while ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((child.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[13]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[14]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[15]++;
}
      child = child.getNext();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[123]++;
    }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[124]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[70]++;
      createEdge(node, Branch.UNCOND, computeFallThrough(child));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[125]++;

    } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[71]++;
      createEdge(node, Branch.UNCOND, computeFollowNode(node, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[126]++;
    }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[127]++;
int CodeCoverConditionCoverageHelper_C18;

    // Synthetic blocks
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[72]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[128]++;
      switch (parent.getType()) {
        case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[74]++;
        case Token.CASE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[75]++;
        case Token.TRY:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[76]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[129]++;
          break;
        default:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[77]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[130]++;
int CodeCoverConditionCoverageHelper_C19;
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((node.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((node.isSyntheticBlock()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[78]++;
            createEdge(node, Branch.SYN_BLOCK, computeFollowNode(node, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[131]++;

          } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[79]++;}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[132]++;
          break;
      }

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[73]++;}
  }

  private void handleFunction(Node node) {
    // A block transfer control to its first child if it is not empty.
    Preconditions.checkState(node.getChildCount() >= 3);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[133]++;
    createEdge(node, Branch.UNCOND,
        computeFallThrough(node.getFirstChild().getNext().getNext()));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[134]++;
    Preconditions.checkState(exceptionHandler.peek() == node);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[135]++;
    exceptionHandler.pop();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[136]++;
  }

  private void handleExpr(Node node) {
    createEdge(node, Branch.UNCOND, computeFollowNode(node, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[137]++;
    connectToPossibleExceptionHandler(node, node);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[138]++;
  }

  private void handleThrow(Node node) {
    connectToPossibleExceptionHandler(node, node);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[139]++;
  }

  private void handleTry(Node node) {
    createEdge(node, Branch.UNCOND, node.getFirstChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[140]++;
  }

  private void handleCatch(Node node) {
    createEdge(node, Branch.UNCOND, node.getLastChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[141]++;
  }

  private void handleBreak(Node node) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[142]++;
    String label = null;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[143]++;
int CodeCoverConditionCoverageHelper_C20;
    // See if it is a break with label.
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((node.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[80]++;
      label = node.getFirstChild().getString();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[144]++;

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[81]++;}
    Node cur;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[145]++;
    Node previous = null;
    Node lastJump;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[146]++;
    Node parent = node.getParent();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[147]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[16]++;


int CodeCoverConditionCoverageHelper_C21;
    /*
     * Continuously look up the ancestor tree for the BREAK target or the target
     * with the corresponding label and connect to it. If along the path we
     * discover a FINALLY, we will connect the BREAK to that FINALLY. From then
     * on, we will just record the control flow changes in the finallyMap. This
     * is due to the fact that we need to connect any node that leaves its own
     * FINALLY block to the outer FINALLY or the BREAK's target but those nodes
     * are not known yet due to the way we traverse the nodes.
     */
    for (cur = node, lastJump = node;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((isBreakTarget(cur, label)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false);
        cur = parent, parent = parent.getParent()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[16]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[17]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[18]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[148]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (32)) == 0 || true) &&
 ((cur.isTry()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((NodeUtil.hasFinally(cur)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((cur.getLastChild() != previous) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[82]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[149]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((lastJump == node) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[84]++;
          createEdge(lastJump, Branch.UNCOND, computeFallThrough(
              cur.getLastChild()));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[150]++;

        } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[85]++;
          finallyMap.put(lastJump, computeFallThrough(cur.getLastChild()));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[151]++;
        }
        lastJump = cur;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[152]++;

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[83]++;}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[153]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[86]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[154]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((compiler.isIdeMode()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[88]++;
          // In IDE mode, we expect that the data flow graph may
          // not be well-formed.
          return;

        } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[89]++;
          throw new IllegalStateException("Cannot find break target.");
        }

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[87]++;}
      previous = cur;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[155]++;
    }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[156]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((lastJump == node) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[90]++;
      createEdge(lastJump, Branch.UNCOND, computeFollowNode(cur, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[157]++;

    } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[91]++;
      finallyMap.put(lastJump, computeFollowNode(cur, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[158]++;
    }
  }

  private void handleContinue(Node node) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[159]++;
    String label = null;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[160]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((node.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[92]++;
      label = node.getFirstChild().getString();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[161]++;

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[93]++;}
    Node cur;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[162]++;
    Node previous = null;
    Node lastJump;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[163]++;

    // Similar to handBreak's logic with a few minor variation.
    Node parent = node.getParent();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[164]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[19]++;


int CodeCoverConditionCoverageHelper_C28;
    for (cur = node, lastJump = node;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((isContinueTarget(cur, parent, label)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false);
        cur = parent, parent = parent.getParent()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[19]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[20]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[21]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[165]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (32)) == 0 || true) &&
 ((cur.isTry()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((NodeUtil.hasFinally(cur)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((cur.getLastChild() != previous) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[94]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[166]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((lastJump == node) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[96]++;
          createEdge(lastJump, Branch.UNCOND, cur.getLastChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[167]++;

        } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[97]++;
          finallyMap.put(lastJump, computeFallThrough(cur.getLastChild()));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[168]++;
        }
        lastJump = cur;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[169]++;

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[95]++;}
      Preconditions.checkState(parent != null, "Cannot find continue target.");
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[170]++;
      previous = cur;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[171]++;
    }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[172]++;
    Node iter = cur;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[173]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((cur.getChildCount() == 4) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[98]++;
      iter = cur.getFirstChild().getNext().getNext();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[174]++;

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[99]++;}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[175]++;
int CodeCoverConditionCoverageHelper_C32;

    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((lastJump == node) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[100]++;
      createEdge(node, Branch.UNCOND, iter);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[176]++;

    } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[101]++;
      finallyMap.put(lastJump, iter);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[177]++;
    }
  }

  private void handleReturn(Node node) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[178]++;
    Node lastJump = null;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[179]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[22]++;


int CodeCoverConditionCoverageHelper_C33;
    for (Iterator<Node> iter = exceptionHandler.iterator();(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((iter.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false);) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[22]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[23]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[24]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[180]++;
      Node curHandler = iter.next();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[181]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((curHandler.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[102]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[182]++;
        break;

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[103]++;}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[183]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((NodeUtil.hasFinally(curHandler)) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[104]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[184]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((lastJump == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[106]++;
          createEdge(node, Branch.UNCOND, curHandler.getLastChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[185]++;

        } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[107]++;
          finallyMap.put(lastJump,
              computeFallThrough(curHandler.getLastChild()));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[186]++;
        }
        lastJump = curHandler;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[187]++;

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[105]++;}
    }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[188]++;
int CodeCoverConditionCoverageHelper_C37;

    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((node.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[108]++;
      connectToPossibleExceptionHandler(node, node.getFirstChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[189]++;

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[109]++;}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[190]++;
int CodeCoverConditionCoverageHelper_C38;

    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((lastJump == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[110]++;
      createEdge(node, Branch.UNCOND, null);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[191]++;

    } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[111]++;
      finallyMap.put(lastJump, null);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[192]++;
    }
  }

  private void handleStmt(Node node) {
    // Simply transfer to the next line.
    createEdge(node, Branch.UNCOND, computeFollowNode(node, this));
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[193]++;
    connectToPossibleExceptionHandler(node, node);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[194]++;
  }

  static Node computeFollowNode(Node node, ControlFlowAnalysis cfa) {
    return computeFollowNode(node, node, cfa);
  }

  static Node computeFollowNode(Node node) {
    return computeFollowNode(node, node, null);
  }

  /**
   * Computes the follow() node of a given node and its parent. There is a side
   * effect when calling this function. If this function computed an edge that
   * exists a FINALLY, it'll attempt to connect the fromNode to the outer
   * FINALLY according to the finallyMap.
   *
   * @param fromNode The original source node since {@code node} is changed
   *        during recursion.
   * @param node The node that follow() should compute.
   */
  private static Node computeFollowNode(
      Node fromNode, Node node, ControlFlowAnalysis cfa) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[195]++;
    /*
     * This is the case where:
     *
     * 1. Parent is null implies that we are transferring control to the end of
     * the script.
     *
     * 2. Parent is a function implies that we are transferring control back to
     * the caller of the function.
     *
     * 3. If the node is a return statement, we should also transfer control
     * back to the caller of the function.
     *
     * 4. If the node is root then we have reached the end of what we have been
     * asked to traverse.
     *
     * In all cases we should transfer control to a "symbolic return" node.
     * This will make life easier for DFAs.
     */
    Node parent = node.getParent();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[196]++;
int CodeCoverConditionCoverageHelper_C39;
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (128)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C39 |= (32)) == 0 || true) &&
 ((parent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((cfa != null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((node == cfa.root) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 4) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 4) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[112]++;
      return null;

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[113]++;}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[197]++;

    // If we are just before a IF/WHILE/DO/FOR:
    switch (parent.getType()) {
      // The follow() of any of the path from IF would be what follows IF.
      case Token.IF:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[114]++;
        return computeFollowNode(fromNode, parent, cfa);
      case Token.CASE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[115]++;
      case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[116]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[198]++;
int CodeCoverConditionCoverageHelper_C40;
        // After the body of a CASE, the control goes to the body of the next
        // case, without having to go to the case condition.
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((parent.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[117]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[199]++;
int CodeCoverConditionCoverageHelper_C41;
          if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((parent.getNext().isCase()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[119]++;
            return parent.getNext().getFirstChild().getNext();

          } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[120]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[200]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((parent.getNext().isDefaultCase()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[121]++;
            return parent.getNext().getFirstChild();

          } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[122]++;
            Preconditions.checkState(false, "Not reachable");
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[201]++;
          }
}

        } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[118]++;
          return computeFollowNode(fromNode, parent, cfa);
        }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[202]++;
        break;
      case Token.FOR:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[123]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[203]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(parent)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[124]++;
          return parent;

        } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[125]++;
          return parent.getFirstChild().getNext().getNext();
        }
      case Token.WHILE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[126]++;
      case Token.DO:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[127]++;
        return parent;
      case Token.TRY:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[128]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[204]++;
int CodeCoverConditionCoverageHelper_C44;
        // If we are coming out of the TRY block...
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((parent.getFirstChild() == node) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[129]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[205]++;
int CodeCoverConditionCoverageHelper_C45;
          if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((NodeUtil.hasFinally(parent)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[131]++; // and have FINALLY block.
            return computeFallThrough(parent.getLastChild());

          } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[132]++; // and have no FINALLY.
            return computeFollowNode(fromNode, parent, cfa);
          }

        // CATCH block.
        } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[130]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[206]++;
int CodeCoverConditionCoverageHelper_C46; if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((NodeUtil.getCatchBlock(parent) == node) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)){
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[133]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[207]++;
int CodeCoverConditionCoverageHelper_C47;
          if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((NodeUtil.hasFinally(parent)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[135]++; // and have FINALLY block.
            return computeFallThrough(node.getNext());

          } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[136]++;
            return computeFollowNode(fromNode, parent, cfa);
          }

        // If we are coming out of the FINALLY block...
        } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[134]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[208]++;
int CodeCoverConditionCoverageHelper_C48; if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((parent.getLastChild() == node) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)){
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[137]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[209]++;
int CodeCoverConditionCoverageHelper_C49;
          if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((cfa != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[139]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[210]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[25]++;


            for (Node finallyNode : cfa.finallyMap.get(parent)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[25]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[26]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[27]++;
}
              cfa.createEdge(fromNode, Branch.ON_EX, finallyNode);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[211]++;
            }

          } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[140]++;}
          return computeFollowNode(fromNode, parent, cfa);

        } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[138]++;}
}
} default : CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[141]++;
    }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[212]++;

    // Now that we are done with the special cases follow should be its
    // immediate sibling, unless its sibling is a function
    Node nextSibling = node.getNext();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[213]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[28]++;


int CodeCoverConditionCoverageHelper_C50;

    // Skip function declarations because control doesn't get pass into it.
    while ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((nextSibling != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((nextSibling.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[28]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[29]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[30]++;
}
      nextSibling = nextSibling.getNext();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[214]++;
    }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[215]++;
int CodeCoverConditionCoverageHelper_C51;

    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((nextSibling != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[142]++;
      return computeFallThrough(nextSibling);

    } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[143]++;
      // If there are no more siblings, control is transferred up the AST.
      return computeFollowNode(fromNode, parent, cfa);
    }
  }

  /**
   * Computes the destination node of n when we want to fallthrough into the
   * subtree of n. We don't always create a CFG edge into n itself because of
   * DOs and FORs.
   */
  static Node computeFallThrough(Node n) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[216]++;
    switch (n.getType()) {
      case Token.DO:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[144]++;
        return computeFallThrough(n.getFirstChild());
      case Token.FOR:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[145]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[217]++;
int CodeCoverConditionCoverageHelper_C52;
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(n)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[146]++;
          return n.getFirstChild().getNext();

        } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[147]++;}
        return computeFallThrough(n.getFirstChild());
      case Token.LABEL:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[148]++;
        return computeFallThrough(n.getLastChild());
      default:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[149]++;
        return n;
    }
  }

  /**
   * Connects the two nodes in the control flow graph.
   *
   * @param fromNode Source.
   * @param toNode Destination.
   */
  private void createEdge(Node fromNode, ControlFlowGraph.Branch branch,
      Node toNode) {
    cfg.createNode(fromNode);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[218]++;
    cfg.createNode(toNode);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[219]++;
    cfg.connectIfNotFound(fromNode, branch, toNode);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[220]++;
  }

  /**
   * Connects cfgNode to the proper CATCH block if target subtree might throw
   * an exception. If there are FINALLY blocks reached before a CATCH, it will
   * make the corresponding entry in finallyMap.
   */
  private void connectToPossibleExceptionHandler(Node cfgNode, Node target) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[221]++;
int CodeCoverConditionCoverageHelper_C53;
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((mayThrowException(target)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((exceptionHandler.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[150]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[222]++;
      Node lastJump = cfgNode;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[223]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[31]++;


      for (Node handler : exceptionHandler) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[31]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[32]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[33]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[224]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((handler.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[152]++;
          return;

        } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[153]++;}
        Preconditions.checkState(handler.isTry());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[225]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[226]++;
        Node catchBlock = NodeUtil.getCatchBlock(handler);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[227]++;
int CodeCoverConditionCoverageHelper_C55;

        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((NodeUtil.hasCatchHandler(catchBlock)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[154]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[228]++;
int CodeCoverConditionCoverageHelper_C56; // No catch but a FINALLY.
          if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((lastJump == cfgNode) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[156]++;
            createEdge(cfgNode, Branch.ON_EX, handler.getLastChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[229]++;

          } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[157]++;
            finallyMap.put(lastJump, handler.getLastChild());
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[230]++;
          }

        } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[155]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[231]++;
int CodeCoverConditionCoverageHelper_C57; // Has a catch.
          if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((lastJump == cfgNode) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[158]++;
            createEdge(cfgNode, Branch.ON_EX, catchBlock);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[232]++;
            return;

          } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[159]++;
            finallyMap.put(lastJump, catchBlock);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[233]++;
          }
        }
        lastJump = handler;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[234]++;
      }

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[151]++;}
  }

  /**
   * Get the next sibling (including itself) of one of the given types.
   */
  private static Node getNextSiblingOfType(Node first, int ... types) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[235]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[34]++;


int CodeCoverConditionCoverageHelper_C58;
    for (Node c = first;(((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[34]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[35]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[36]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[236]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[37]++;


      for (int type : types) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[37]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[38]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[39]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[237]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((c.getType() == type) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[160]++;
          return c;

        } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[161]++;}
      }
    }
    return null;
  }

  /**
   * Checks if target is actually the break target of labeled continue. The
   * label can be null if it is an unlabeled break.
   */
  public static boolean isBreakTarget(Node target, String label) {
    return isBreakStructure(target, label != null) &&
      matchLabel(target.getParent(), label);
  }

  /**
   * Checks if target is actually the continue target of labeled continue. The
   * label can be null if it is an unlabeled continue.
   */
  private static boolean isContinueTarget(
      Node target, Node parent, String label) {
    return isContinueStructure(target) && matchLabel(parent, label);
  }
  /**
   * Check if label is actually referencing the target control structure. If
   * label is null, it always returns true.
   */
  private static boolean matchLabel(Node target, String label) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[238]++;
int CodeCoverConditionCoverageHelper_C60;
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((label == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[162]++;
      return true;

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[163]++;}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[239]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[40]++;


int CodeCoverConditionCoverageHelper_C61;
    while ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((target.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[40]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[41]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[42]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[240]++;
int CodeCoverConditionCoverageHelper_C62;
      if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((target.getFirstChild().getString().equals(label)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[164]++;
        return true;

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[165]++;}
      target = target.getParent();
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[241]++;
    }
    return false;
  }

  /**
   * Determines if the subtree might throw an exception.
   */
  public static boolean mayThrowException(Node n) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[242]++;
    switch (n.getType()) {
      case Token.CALL:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[166]++;
      case Token.GETPROP:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[167]++;
      case Token.GETELEM:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[168]++;
      case Token.THROW:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[169]++;
      case Token.NEW:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[170]++;
      case Token.ASSIGN:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[171]++;
      case Token.INC:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[172]++;
      case Token.DEC:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[173]++;
      case Token.INSTANCEOF:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[174]++;
        return true;
      case Token.FUNCTION:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[175]++;
        return false; default : CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[176]++;
    }
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[243]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[43]++;


int CodeCoverConditionCoverageHelper_C63;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[43]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[44]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[45]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[244]++;
int CodeCoverConditionCoverageHelper_C64;
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((ControlFlowGraph.isEnteringNewCfgNode(c)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((mayThrowException(c)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[177]++;
        return true;

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[178]++;}
    }
    return false;
  }

  /**
   * Determines whether the given node can be terminated with a BREAK node.
   */
  static boolean isBreakStructure(Node n, boolean labeled) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[245]++;
    switch (n.getType()) {
      case Token.FOR:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[179]++;
      case Token.DO:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[180]++;
      case Token.WHILE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[181]++;
      case Token.SWITCH:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[182]++;
        return true;
      case Token.BLOCK:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[183]++;
      case Token.IF:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[184]++;
      case Token.TRY:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[185]++;
        return labeled;
      default:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[186]++;
        return false;
    }
  }

  /**
   * Determines whether the given node can be advanced with a CONTINUE node.
   */
  static boolean isContinueStructure(Node n) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[246]++;
    switch (n.getType()) {
      case Token.FOR:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[187]++;
      case Token.DO:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[188]++;
      case Token.WHILE:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[189]++;
        return true;
      default:
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[190]++;
        return false;
    }
  }

  /**
   * Get the TRY block with a CATCH that would be run if n throws an exception.
   * @return The CATCH node or null if it there isn't a CATCH before the
   *     the function terminates.
   */
  static Node getExceptionHandler(Node n) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[247]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[46]++;


int CodeCoverConditionCoverageHelper_C65;
    for (Node cur = n;(((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((cur.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((cur.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false);
        cur = cur.getParent()) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[46]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[47]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[48]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[248]++;
      Node catchNode = getCatchHandlerForBlock(cur);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[249]++;
int CodeCoverConditionCoverageHelper_C66;
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((catchNode != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[191]++;
        return catchNode;

      } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[192]++;}
    }
    return null;
  }

  /**
   * Locate the catch BLOCK given the first block in a TRY.
   * @return The CATCH node or null there is no catch handler.
   */
  static Node getCatchHandlerForBlock(Node block) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[250]++;
int CodeCoverConditionCoverageHelper_C67;
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (32)) == 0 || true) &&
 ((block.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((block.getParent().isTry()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((block.getParent().getFirstChild() == block) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 3) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 3) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[193]++;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[251]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[49]++;


int CodeCoverConditionCoverageHelper_C68;
      for (Node s = block.getNext();(((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((s != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false); s = s.getNext()) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[49]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[50]--;
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.loops[51]++;
}
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[252]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((NodeUtil.hasCatchHandler(s)) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[195]++;
          return s.getFirstChild();

        } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[196]++;}
      }

    } else {
  CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[194]++;}
    return null;
  }

  /**
   * A {@link ControlFlowGraph} which provides a node comparator based on the
   * pre-order traversal of the AST.
   */
  private static class AstControlFlowGraph extends ControlFlowGraph<Node> {
    private final Map<DiGraphNode<Node, Branch>, Integer> priorities;

    /**
     * Constructor.
     * @param entry The entry node.
     * @param priorities The map from nodes to position in the AST (to be
     *    filled by the {@link ControlFlowAnalysis#shouldTraverse}).
     */
    private AstControlFlowGraph(Node entry,
        Map<DiGraphNode<Node, Branch>, Integer> priorities,
        boolean edgeAnnotations) {
      super(entry,
          true /* node annotations */, edgeAnnotations);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[253]++;
      this.priorities = priorities;
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[254]++;
    }

    @Override
    /**
     * Returns a node comparator based on the pre-order traversal of the AST.
     * @param isForward x 'before' y in the pre-order traversal implies
     * x 'less than' y (if true) and x 'greater than' y (if false).
     */
    public Comparator<DiGraphNode<Node, Branch>> getOptionalNodeComparator(
        boolean isForward) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[255]++;
int CodeCoverConditionCoverageHelper_C70;
      if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((isForward) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[197]++;
        return new Comparator<DiGraphNode<Node, Branch>>() {
          @Override
          public int compare(
              DiGraphNode<Node, Branch> n1, DiGraphNode<Node, Branch> n2) {
            return getPosition(n1) - getPosition(n2);
          }
        };

      } else {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.branches[198]++;
        return new Comparator<DiGraphNode<Node, Branch>>() {
          @Override
          public int compare(
              DiGraphNode<Node, Branch> n1, DiGraphNode<Node, Branch> n2) {
            return getPosition(n2) - getPosition(n1);
          }
        };
      }
    }

    /**
     * Gets the pre-order traversal position of the given node.
     * @return An arbitrary counter used for comparing positions.
     */
    private int getPosition(DiGraphNode<Node, Branch> n) {
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[256]++;
      Integer priority = priorities.get(n);
      Preconditions.checkNotNull(priority);
CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l.statements[257]++;
      return priority;
    }
  }
}

class CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l ());
  }
    public static long[] statements = new long[258];
    public static long[] branches = new long[199];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[71];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ControlFlowAnalysis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,2,1,1,1,1,1,1,1,1,3,2,1,1,2,1,1,3,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,2,1,3,1,1,1};
    for (int i = 1; i <= 70; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[52];

  public CodeCoverCoverageCounter$fjivao7ipw2af14r05pcnql6r3wol499wxq3l () {
    super("com.google.javascript.jscomp.ControlFlowAnalysis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 257; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 198; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 70; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 51; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ControlFlowAnalysis.java");
      for (int i = 1; i <= 257; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 198; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 70; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 17; i++) {
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

