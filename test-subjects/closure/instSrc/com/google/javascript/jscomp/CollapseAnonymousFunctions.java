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
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.Node;

/**
 * Collapses anonymous function expressions into named function declarations,
 * i.e. the following:
 *
 * <pre>
 * var f = function()
 * <pre>
 *
 * becomes:
 *
 * <pre>function f()</pre>
 *
 * This reduces the generated code size but changes the semantics because f
 * will be defined before its definition is reached.
 *
 */
class CollapseAnonymousFunctions implements CompilerPass {
  static {
    CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.ping();
  }

  private final AbstractCompiler compiler;

  public CollapseAnonymousFunctions(AbstractCompiler compiler) {
    Preconditions.checkArgument(compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[1]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, new Callback());
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[3]++;
  }

  private class Callback extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[1]++;
        return;

      } else {
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[2]++;}
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[5]++;

      // It is only safe to collapse anonymous functions that appear
      // at top-level blocks.  In other cases the difference between
      // variable and function declarations can lead to problems or
      // expose subtle bugs in browser implementation as function
      // definitions are added to scopes before the start of execution.

      Node grandparent = parent.getParent();
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C2 |= (128)) == 0 || true) &&
 ((parent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((grandparent != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((grandparent.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parent.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 4) || true)) || (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 4) && false)) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[3]++;
        return;

      } else {
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[4]++;}

      // Need to store the next name in case the current name is removed from
      // the linked list.
      Preconditions.checkState(n.hasOneChild());
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[7]++;
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[8]++;
      Node name = n.getFirstChild();
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[9]++;
      Node value = name.getFirstChild();
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((value.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isRecursiveFunction(value)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[5]++;
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[11]++;
        Node fnName = value.getFirstChild();
        fnName.setString(name.getString());
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[12]++;
        NodeUtil.copyNameAnnotations(name, fnName);
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[13]++;
        name.removeChild(value);
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[14]++;
        parent.replaceChild(n, value);
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[15]++;
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;

        // Renormalize the code.
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isHoistedFunctionDeclaration(value)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[7]++;
          parent.addChildToFront(value.detachFromParent());
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[17]++;

        } else {
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[8]++;}

        compiler.reportCodeChange();
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[18]++;

      } else {
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[6]++;}
    }

    private boolean isRecursiveFunction(Node function) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[19]++;
      Node name = function.getFirstChild();
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((name.getString().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[9]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[10]++;}
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[21]++;
      Node args = name.getNext();
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[22]++;
      Node body = args.getNext();
      return containsName(body, name.getString());
    }

    private boolean containsName(Node n, String name) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[23]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.getString().equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[11]++;
        return true;

      } else {
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[12]++;}
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[24]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.loops[1]++;



      for (Node child : n.children()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.loops[1]--;
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.loops[2]--;
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.loops[3]++;
}
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((containsName(child, name)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[13]++;
          return true;

        } else {
  CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p.branches[14]++;}
      }
      return false;
    }
  }
}

class CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CollapseAnonymousFunctions.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,3,3,2,1,2,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$8ibemuinjen2r1omiwh4qr0ky66w46ntn9nknwqsn1qofb6p () {
    super("com.google.javascript.jscomp.CollapseAnonymousFunctions.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CollapseAnonymousFunctions.java");
      for (int i = 1; i <= 25; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

