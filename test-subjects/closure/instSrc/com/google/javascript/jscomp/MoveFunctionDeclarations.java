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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.Node;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Moves top-level function declarations to the top.
 *
 * Enable this pass if a try catch block wraps the output after compilation,
 * and the output runs on Firefox because function declarations are only
 * defined when reached inside a try catch block on Firefox.
 *
 * On Firefox, this code works:
 *
 * var g = f;
 * function f() {}
 *
 * but this code does not work:
 *
 * try {
 *   var g = f;
 *   function f() {}
 * } catch(e) {}
 *
 */
class MoveFunctionDeclarations implements Callback, CompilerPass {
  static {
    CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.ping();
  }

  private final AbstractCompiler compiler;
  private final Map<JSModule, List<Node>> functions;

  MoveFunctionDeclarations(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[1]++;
    functions = Maps.newHashMap();
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[3]++;
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[1]++;


    for (Entry<JSModule, List<Node>> entry : functions.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[1]--;
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[2]--;
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[3]++;
}
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[5]++;
      JSModule module = entry.getKey();
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[6]++;
      Node addingRoot = compiler.getNodeForCodeInsertion(module);
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[7]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[4]++;


      for (Node n : Lists.reverse(entry.getValue())) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[4]--;
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[5]--;
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.loops[6]++;
}
        addingRoot.addChildToFront(n);
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[8]++;
      }
    }
  }

  @Override
  public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[9]++;
    Node gramps = n.getAncestor(2);
    return gramps == null || !gramps.isScript();
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.branches[1]++;
      return;

    } else {
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.branches[2]++;}
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.branches[3]++;
      parent.removeChild(n);
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[12]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[13]++;
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[14]++;

      JSModule module = t.getModule();
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[15]++;
      List<Node> moduleFunctions = functions.get(module);
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((moduleFunctions == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.branches[5]++;
        moduleFunctions = Lists.newArrayList();
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[17]++;
        functions.put(module, moduleFunctions);
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[18]++;

      } else {
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.branches[6]++;}
      moduleFunctions.add(n);
CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.statements[19]++;

    } else {
  CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81.branches[4]++;}
  }
}

class CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81 ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.MoveFunctionDeclarations.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$6yeqw5oiso00ak5belozrwepzybbzi30wfg4pvfp53o81 () {
    super("com.google.javascript.jscomp.MoveFunctionDeclarations.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.MoveFunctionDeclarations.java");
      for (int i = 1; i <= 19; i++) {
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
    for (int i = 1; i <= 3; i++) {
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

