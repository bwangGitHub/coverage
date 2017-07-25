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

import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.jscomp.graph.LinkedDirectedGraph;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Comparator;

/**
 * Control flow graph.
 *
 *
 * @param <N> The instruction type of the control flow graph.
 */
class ControlFlowGraph<N> extends
    LinkedDirectedGraph<N, ControlFlowGraph.Branch> {
  static {
    CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.ping();
  }


  /**
   * A special node marked by the node value key null to a singleton
   * "return" when control is transferred outside of the current control flow
   * graph.
   */
  private final DiGraphNode<N, ControlFlowGraph.Branch> implicitReturn;

  private final DiGraphNode<N, ControlFlowGraph.Branch> entry;

  /**
   * Constructor.
   */
  ControlFlowGraph(
      N entry, boolean nodeAnnotations, boolean edgeAnnotations) {
    super(nodeAnnotations, edgeAnnotations);
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.statements[1]++;
    implicitReturn = createDirectedGraphNode(null);
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.statements[2]++;
    this.entry = createDirectedGraphNode(entry);
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.statements[3]++;
  }

  /**
   * Gets the implicit return node.
   *
   * @return Return node.
   */
  public DiGraphNode<N, ControlFlowGraph.Branch> getImplicitReturn() {
    return implicitReturn;
  }

  /**
   * Gets the entry point of the control flow graph. In general, this should be
   * the beginning of the global script or beginning of a function.
   *
   * @return The entry point.
   */
  public DiGraphNode<N, ControlFlowGraph.Branch> getEntry() {
    return entry;
  }

  /**
   * Checks whether node is the implicit return.
   *
   * @param node Node.
   * @return True if the node is the implicit return.
   */
  public boolean isImplicitReturn(
      DiGraphNode<N, ControlFlowGraph.Branch> node) {
    return node == implicitReturn;
  }

  /**
   * Connects the node to the explicit return.
   *
   * @param srcValue Node.
   * @param edgeValue Edge.
   */
  public void connectToImplicitReturn(N srcValue, Branch edgeValue) {
    super.connect(srcValue, edgeValue, null);
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.statements[4]++;
  }

  /**
   * Gets a comparator for the nodes. The default implementation returns
   * {@code null}. See {@link ControlFlowGraph#getOptionalNodeComparator}.
   * @param isForward Whether the comparator sorts the nodes in the direction of
   *    the flow.
   * @return a comparator or null (in particular, if not overridden)
   */
  public Comparator<DiGraphNode<N, Branch>> getOptionalNodeComparator(
      boolean isForward) {
    return null;
  }

  /**
   * The edge object for the control flow graph.
   */
  public static enum Branch {
    /** Edge is taken if the condition is true. */
    ON_TRUE,
    /** Edge is taken if the condition is false. */
    ON_FALSE,
    /** Unconditional branch. */
    UNCOND,
    /**
     * Exception-handling code paths.
     * Conflates two kind of control flow passing:
     * - An exception is thrown, and falls into a catch or finally block
     * - During exception handling, a finally block finishes and control
     *   passes to the next finally block.
     * In theory, we need 2 different edge types. In practice, we
     * can just treat them as "the edges we can't really optimize".
     */
    ON_EX,
    /** Possible folded-away template */
    SYN_BLOCK;

    public boolean isConditional() {
      return this == ON_TRUE || this == ON_FALSE;
    }
  }

  /**
   * Abstract callback to visit a control flow graph node without going into
   * subtrees of the node that are also represented by other
   * control flow graph nodes.
   *
   * <p>For example, traversing an IF node as root will visit the two subtrees
   * pointed by the {@link ControlFlowGraph.Branch#ON_TRUE} and
   * {@link ControlFlowGraph.Branch#ON_FALSE} edges.
   */
  public abstract static class AbstractCfgNodeTraversalCallback implements
      Callback {
    @Override
    public final boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
        Node parent) {
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[1]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[2]++;}
      return !isEnteringNewCfgNode(n);
    }
  }

  /**
   * @return True if n should be represented by a new CFG node in the control
   * flow graph.
   */
  public static boolean isEnteringNewCfgNode(Node n) {
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.statements[6]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.statements[7]++;
    switch (parent.getType()) {
      case Token.BLOCK:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[3]++;
      case Token.SCRIPT:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[4]++;
      case Token.TRY:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[5]++;
        return true;
      case Token.FUNCTION:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[6]++;
        // A function node represents the start of a function where the name
        // bleeds into the local scope and parameters are assigned
        // to the formal argument names. The node includes the name of the
        // function and the LP list since we assume the whole set up process
        // is atomic without change in control flow. The next change of
        // control is going into the function's body, represented by the second
        // child.
        return n != parent.getFirstChild().getNext();
      case Token.WHILE:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[7]++;
      case Token.DO:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[8]++;
      case Token.IF:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[9]++;
        // These control structures are represented by a node that holds the
        // condition. Each of them is a branch node based on its condition.
        return NodeUtil.getConditionExpression(parent) != n;

      case Token.FOR:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[10]++;
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        // The FOR(;;) node differs from other control structures in that
        // it has an initialization and an increment statement. Those
        // two statements have corresponding CFG nodes to represent them.
        // The FOR node only represents the condition check for each iteration.
        // That way the following:
        // for(var x = 0; x < 10; x++) { } has a graph that is isomorphic to
        // var x = 0; while(x<10) {  x++; }
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(parent)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[11]++;
          // TODO(user): Investigate how we should handle the case where
          // we have a very complex expression inside the FOR-IN header.
          return n != parent.getFirstChild();

        } else {
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[12]++;
          return NodeUtil.getConditionExpression(parent) != n;
        }
      case Token.SWITCH:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[13]++;
      case Token.CASE:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[14]++;
      case Token.CATCH:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[15]++;
      case Token.WITH:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[16]++;
        return n != parent.getFirstChild();
      default:
CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481.branches[17]++;
        return false;
    }
  }
}

class CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481 ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[18];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ControlFlowGraph.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
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

  public CodeCoverCoverageCounter$1k0k7j5t1arde4w0yit7v7qmjnbiuc481 () {
    super("com.google.javascript.jscomp.ControlFlowGraph.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 17; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.ControlFlowGraph.java");
      for (int i = 1; i <= 8; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 17; i++) {
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

