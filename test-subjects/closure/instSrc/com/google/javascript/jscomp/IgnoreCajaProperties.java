/*
 * Copyright 2009 The Closure Compiler Authors.
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

import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;

/**
 * Caja is a system that rewrites web content (JavaScript, CSS, HTML)
 * into web content that is safe to inline directly into a page.
 * The rewritten ("cajoled") code runs in the presence of a JS library
 * that adds some properties to Object.prototype.  Because JS does not
 * yet (until ES5) allow programmers to mark properties as DontEnum,
 * for..in loops will see unexpected properties.
 *
 * This pass adds a conditional to for..in loops that filters out these
 * properties.
 *
 */

class IgnoreCajaProperties implements CompilerPass {
  static {
    CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.ping();
  }


  final AbstractCompiler compiler;

  // Counts the number of temporary variables introduced.
  int counter;

  public IgnoreCajaProperties(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[1]++;
    this.counter = 0;
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, new Traversal());
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[3]++;
  }

  private class Traversal extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
      // Look for a for..in loop.
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((n.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.getChildCount() == 3) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.branches[1]++;
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[5]++;
        Node body = n.getLastChild();
        n.removeChild(body);
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[6]++;
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[7]++;
        Node key = n.getFirstChild();
        n.removeChild(key);
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[8]++;
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[9]++;
        Node tmp = IR.name(
            "JSCompiler_IgnoreCajaProperties_" + counter++);
        n.addChildToFront(IR.var(tmp));
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[10]++;
        Node ifBody;
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;

        // Construct the body of the if statement.
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((key.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.branches[3]++;
          // for (var key in x) { body; }
          // =>
          // for (var tmp in x) {
          //   if (!tmp.match(/___$/)) {
          //     var key;
          //     key = tmp;
          //     body;
          //   }
          // }
          ifBody = IR.block(
              key,
              IR.exprResult(
                  IR.assign(
                      key.getFirstChild().cloneNode(),
                      tmp.cloneTree())),
              body);
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[12]++;

        } else {
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.branches[4]++;
          // for (key in x) { body; }
          // =>
          // for (var tmp in x) {
          //   if (!tmp.match(/___$/)) {
          //     key = tmp;
          //     body;
          //   }
          // }
          ifBody = IR.block(
              IR.exprResult(
                  IR.assign(
                      key,
                      tmp.cloneTree())),
              body);
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[13]++;
        }
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[14]++;

        // Construct the new body of the for loop.
        Node newBody = IR.block(
            IR.ifNode(
                IR.not(
                    IR.call(
                        IR.getprop(
                            tmp.cloneTree(),
                            IR.string("match")),
                        IR.regexp(
                            IR.string("___$")))),
                ifBody));
        n.addChildToBack(newBody);
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[15]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.statements[16]++;

      } else {
  CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1.branches[2]++;}
    }
  }
}

class CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1 ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.IgnoreCajaProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1};
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

  public CodeCoverCoverageCounter$3cb0b12uo5l730t1cojzz235b5b0yroqvwn8ig1 () {
    super("com.google.javascript.jscomp.IgnoreCajaProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
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
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.IgnoreCajaProperties.java");
      for (int i = 1; i <= 16; i++) {
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

