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

import com.google.common.base.Predicate;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.jscomp.graph.GraphReachability;
import com.google.javascript.jscomp.graph.GraphReachability.EdgeTuple;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.TernaryValue;

/**
 * Use {@link ControlFlowGraph} and {@link GraphReachability} to inform user
 * about unreachable code.
 *
 */
class CheckUnreachableCode implements ScopedCallback {
  static {
    CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.ping();
  }


  static final DiagnosticType UNREACHABLE_CODE = DiagnosticType.error(
      "JSC_UNREACHABLE_CODE", "unreachable code");
  static {
    CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final CheckLevel level;

  CheckUnreachableCode(AbstractCompiler compiler, CheckLevel level) {
    this.compiler = compiler;
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[2]++;
    this.level = level;
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[3]++;
  }

  @Override
  public void enterScope(NodeTraversal t) {
    initScope(t.getControlFlowGraph());
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[4]++;
  }

  @Override
  public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[5]++;
    GraphNode<Node, Branch> gNode = t.getControlFlowGraph().getNode(n);
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((gNode != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((gNode.getAnnotation() != GraphReachability.REACHABLE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[1]++;
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;

      // Only report error when there are some line number informations.
      // There are synthetic nodes with no line number informations, nodes
      // introduce by other passes (although not likely since this pass should
      // be executed early) or some rhino bug.
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((n.getLineno() != -1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((n.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isBreak()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) || true)) || (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) && false)) {
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[3]++;
        compiler.report(t.makeError(n, level, UNREACHABLE_CODE));
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[8]++;
        // From now on, we are going to assume the user fixed the error and not
        // give more warning related to code section reachable from this node.
        new GraphReachability<Node, ControlFlowGraph.Branch>(
            t.getControlFlowGraph()).recompute(n);
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[9]++;

        // Saves time by not traversing children.
        return false;

      } else {
  CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[4]++;}

    } else {
  CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[2]++;}
    return true;
  }

  private void initScope(ControlFlowGraph<Node> controlFlowGraph) {
    new GraphReachability<Node, ControlFlowGraph.Branch>(
        controlFlowGraph, new ReachablePredicate()).compute(
            controlFlowGraph.getEntry().getValue());
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[10]++;
  }

  @Override
  public void exitScope(NodeTraversal t) {
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
  }

  private final class ReachablePredicate implements
      Predicate<EdgeTuple<Node, ControlFlowGraph.Branch>> {

    @Override
    public boolean apply(EdgeTuple<Node, Branch> input) {
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[11]++;
      Branch branch = input.edge;
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((branch.isConditional()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[5]++;
        return true;

      } else {
  CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[6]++;}
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[13]++;
      Node predecessor = input.sourceNode;
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[14]++;
      Node condition = NodeUtil.getConditionExpression(predecessor);
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;

      // TODO(user): Handle more complicated expression like true == true,
      // etc....
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((condition != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[7]++;
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[16]++;
        TernaryValue val = NodeUtil.getImpureBooleanValue(condition);
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((val != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[9]++;
          return val.toBoolean(true) == (branch == Branch.ON_TRUE);

        } else {
  CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[10]++;}

      } else {
  CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt.branches[8]++;}
      return true;
    }
  }
}

class CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckUnreachableCode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,3,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$32h7t2u7qhcgd51xxh548rt05uz7a070x7xo3tt () {
    super("com.google.javascript.jscomp.CheckUnreachableCode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
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
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckUnreachableCode.java");
      for (int i = 1; i <= 17; i++) {
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

