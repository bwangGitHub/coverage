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
package com.google.javascript.jscomp;

import com.google.javascript.jscomp.DiagnosticType;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.Node;

/**
 * {@link CheckDebuggerStatement} checks for the presence of the "debugger"
 * statement in JavaScript code. It is appropriate to use this statement while
 * developing JavaScript; however, it is generally undesirable to include it in
 * production code.
 *
 * @author bolinfest@google.com (Michael Bolin)
 */
class CheckDebuggerStatement extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.ping();
  }


  static final DiagnosticType DEBUGGER_STATEMENT_PRESENT =
    DiagnosticType.disabled("JSC_DEBUGGER_STATEMENT_PRESENT",
        "Using the debugger statement can halt your application if the user " +
        "has a JavaScript debugger running.");
  static {
    CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.statements[1]++;
  }

  private final AbstractCompiler compiler;

  public CheckDebuggerStatement(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.statements[3]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isDebugger()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.branches[1]++;
      t.report(n, DEBUGGER_STATEMENT_PRESENT);
CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.statements[5]++;

    } else {
  CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5.branches[2]++;}
  }
}

class CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5 ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckDebuggerStatement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$4b6nidkgppm5nj6uh0gh7czgoy53zfjy2bfdj2ahr5 () {
    super("com.google.javascript.jscomp.CheckDebuggerStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckDebuggerStatement.java");
      for (int i = 1; i <= 5; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

