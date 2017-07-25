/*
 * Copyright 2012 The Closure Compiler Authors.
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
import com.google.javascript.rhino.Token;

/**
 * Checks for common errors, such as misplaced semicolons:
 * <pre>
 * if (x); act_now();
 * </pre>
 *  or comparison against NaN:
 * <pre>
 * if (x === NaN) act();
 * </pre>
 * and generates warnings.
 *
 * @author johnlenz@google.com (John Lenz)
 */
final class CheckSuspiciousCode extends AbstractPostOrderCallback {
  static {
    CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.ping();
  }


  static final DiagnosticType SUSPICIOUS_SEMICOLON = DiagnosticType.warning(
      "JSC_SUSPICIOUS_SEMICOLON",
      "If this if/for/while really shouldn't have a body, use {}");
  static {
    CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[1]++;
  }

  static final DiagnosticType SUSPICIOUS_COMPARISON_WITH_NAN =
      DiagnosticType.warning(
          "JSC_SUSPICIOUS_NAN",
          "Comparison again NaN is always false. Did you mean isNaN()?");
  static {
    CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[2]++;
  }

  CheckSuspiciousCode() {
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
    checkMissingSemicolon(t, n);
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[3]++;
    checkNaN(t, n);
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[4]++;
  }

  private void checkMissingSemicolon(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[5]++;
    switch (n.getType()) {
      case Token.IF:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[1]++;
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[6]++;
        Node trueCase = n.getFirstChild().getNext();
        reportIfWasEmpty(t, trueCase);
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[7]++;
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[8]++;
        Node elseCase = trueCase.getNext();
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((elseCase != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[2]++;
          reportIfWasEmpty(t, elseCase);
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[10]++;

        } else {
  CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[3]++;}
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[11]++;
        break;

      case Token.WHILE:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[4]++;
      case Token.FOR:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[5]++;
        reportIfWasEmpty(t, NodeUtil.getLoopCodeBlock(n));
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[12]++;
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[13]++;
        break; default : CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[6]++;
    }
  }

  private void reportIfWasEmpty(NodeTraversal t, Node block) {
    Preconditions.checkState(block.isBlock());
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[14]++;
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;

    // A semicolon is distinguished from a block without children by
    // annotating it with EMPTY_BLOCK.  Blocks without children are
    // usually intentional, especially with loops.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((block.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((block.wasEmptyNode()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[7]++;
        t.getCompiler().report(
            t.makeError(block, SUSPICIOUS_SEMICOLON));
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[16]++;

    } else {
  CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[8]++;}
  }

  private void checkNaN(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[17]++;
    switch (n.getType()) {
      case Token.EQ:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[9]++;
      case Token.GE:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[10]++;
      case Token.GT:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[11]++;
      case Token.LE:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[12]++;
      case Token.LT:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[13]++;
      case Token.NE:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[14]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[15]++;
      case Token.SHNE:
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[16]++;
        reportIfNaN(t, n.getFirstChild());
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[18]++;
        reportIfNaN(t, n.getLastChild());
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[19]++; default : CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[17]++;
    }
  }

  private void reportIfNaN(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.isNaN(n)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[18]++;
      t.getCompiler().report(
          t.makeError(n.getParent(), SUSPICIOUS_COMPARISON_WITH_NAN));
CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.statements[21]++;

    } else {
  CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl.branches[19]++;}
  }
}

class CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[20];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CheckSuspiciousCode.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$fjanrymgmnbh1z6cryqz3vhu5q1cc7otpqkbl () {
    super("com.google.javascript.jscomp.CheckSuspiciousCode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 19; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CheckSuspiciousCode.java");
      for (int i = 1; i <= 21; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 19; i++) {
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

