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
import com.google.javascript.rhino.Node;

/**
 * A simple pass to ensure that all AST nodes have line numbers,
 * an that the line numbers are monotonically increasing.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class LineNumberCheck implements Callback, CompilerPass {
  static {
    CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.ping();
  }


  static final DiagnosticType MISSING_LINE_INFO = DiagnosticType.error(
      "JSC_MISSING_LINE_INFO",
      "No source location information associated with {0}.\n" +
      "Most likely a Node has been created with settings the source file " +
      "and line/column location.  Usually this is done using " +
      "Node.copyInformationFrom and supplying a Node from the source AST.");
  static {
    CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private boolean requiresLineNumbers = false;
  {
    CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[2]++;
  }

  LineNumberCheck(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[3]++;
  }

  public void setCheckSubTree(Node root) {
    requiresLineNumbers = true;
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[4]++;

    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[5]++;
  }

  @Override
  public void process(Node externs, Node root) {
    requiresLineNumbers = false;
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[6]++;

    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[7]++;
  }

  @Override
  public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
    // Each JavaScript file is rooted in a script node, so we'll only
    // have line number information inside the script node.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.branches[1]++;
      requiresLineNumbers = true;
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[9]++;

    } else {
  CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.branches[2]++;}
    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.branches[3]++;
      requiresLineNumbers = false;
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[11]++;

    } else {
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.branches[4]++;
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[12]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((requiresLineNumbers) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.branches[5]++;
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.getLineno() == -1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.branches[7]++;
        // The tree version of the node is really the best diagnostic
        // info we have to offer here.
        compiler.report(
            t.makeError(n, MISSING_LINE_INFO,
                n.toStringTree()));
CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.statements[14]++;

      } else {
  CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.branches[8]++;}

    } else {
  CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd.branches[6]++;}
}
  }
}

class CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.LineNumberCheck.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$8xbs1j3b7b1hytmnnq6z62hrqwysdpd () {
    super("com.google.javascript.jscomp.LineNumberCheck.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.LineNumberCheck.java");
      for (int i = 1; i <= 14; i++) {
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

