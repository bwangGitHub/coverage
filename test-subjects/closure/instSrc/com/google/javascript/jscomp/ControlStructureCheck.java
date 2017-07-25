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

import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * Check for usage of 'with'.
 *
 */
class ControlStructureCheck implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.ping();
  }


  private final AbstractCompiler compiler;

  static final DiagnosticType USE_OF_WITH = DiagnosticType.warning(
      "JSC_USE_OF_WITH",
      "The use of the 'with' structure should be avoided.");
  static {
    CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[1]++;
  }

  ControlStructureCheck(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
    check(root);
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[3]++;
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
    check(scriptRoot);
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[4]++;
  }

  /**
   * Reports errors for any invalid use of control structures.
   *
   * @param node Current node to check.
   */
  private void check(Node node) {
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[5]++;
    switch (node.getType()) {
      case Token.WITH:
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.branches[1]++;
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[6]++;
        JSDocInfo info = node.getJSDocInfo();
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[7]++;
        boolean allowWith =
            info != null && info.getSuppressions().contains("with");
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((allowWith) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.branches[2]++;
          report(node, USE_OF_WITH);
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[9]++;

        } else {
  CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.branches[3]++;}
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[10]++;
        break; default : CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.branches[4]++;
    }
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;

    for (Node bChild = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((bChild != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.loops[1]--;
  CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.loops[2]--;
  CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.loops[3]++;
}
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[12]++;
      Node next = bChild.getNext();
      check(bChild);
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[13]++;
      bChild = next;
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[14]++;
    }
  }

  private void report(Node n, DiagnosticType error) {
    compiler.report(JSError.make(n.getSourceFileName(), n, error));
CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp.statements[15]++;
  }
}

class CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ControlStructureCheck.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$ltxymlgmxp8pp3pmf0yidpdphm55dhpf2f93r7mp () {
    super("com.google.javascript.jscomp.ControlStructureCheck.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ControlStructureCheck.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

