/*
 * Copyright 2004 The Closure Compiler Authors.
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

import java.util.*;

/**
 * Verifies that constants are only assigned a value once.
 * e.g. var XX = 5;
 * XX = 3;    // error!
 * XX++;      // error!
 *
 */
class ConstCheck extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.ping();
  }


  static final DiagnosticType CONST_REASSIGNED_VALUE_ERROR =
      DiagnosticType.error(
          "JSC_CONSTANT_REASSIGNED_VALUE_ERROR",
          "constant {0} assigned a value more than once");
  static {
    CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[1]++;
  }

  private final AbstractCompiler compiler;
  private final Set<Scope.Var> initializedConstants;

  /**
   * Creates an instance.
   */
  public ConstCheck(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[2]++;
    this.initializedConstants = new HashSet<Scope.Var>();
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[3]++;
  }

  @Override
  public void process(Node externs, Node root) {
    Preconditions.checkState(compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[4]++;
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[5]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[6]++;
    switch (n.getType()) {
      case Token.NAME:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[1]++;
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[2]++;
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[8]++;
          String name = n.getString();
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[9]++;
          Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isConstant(var)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[4]++;
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((initializedConstants.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[6]++;
              reportError(t, n, name);
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[12]++;

            } else {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[7]++;
              initializedConstants.add(var);
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[13]++;
            }

          } else {
  CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[5]++;}

        } else {
  CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[3]++;}
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[14]++;
        break;

      case Token.ASSIGN:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[8]++;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[9]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[10]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[11]++;
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[12]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[13]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[14]++;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[15]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[16]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[17]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[18]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[19]++; {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[15]++;
        Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[20]++;
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[17]++;
          String name = lhs.getString();
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[18]++;
          Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isConstant(var)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[22]++;
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((initializedConstants.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[24]++;
              reportError(t, n, name);
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[21]++;

            } else {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[25]++;
              initializedConstants.add(var);
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[22]++;
            }

          } else {
  CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[23]++;}

        } else {
  CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[21]++;}
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[23]++;
        break;
      }

      case Token.INC:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[26]++;
      case Token.DEC:
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[27]++; {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[24]++;
        Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[28]++;
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[26]++;
          String name = lhs.getString();
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[27]++;
          Scope.Var var = t.getScope().getVar(name);
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isConstant(var)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[30]++;
            reportError(t, n, name);
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[29]++;

          } else {
  CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[31]++;}

        } else {
  CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[29]++;}
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[30]++;
        break;
      } default : CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.branches[32]++;
    }
  }

  /**
   * Gets whether a variable is a constant initialized to a literal value at
   * the point where it is declared.
   */
  private boolean isConstant(Scope.Var var) {
    return var != null && var.isConst();
  }

  /**
   * Reports a reassigned constant error.
   */
  void reportError(NodeTraversal t, Node n, String name) {
    compiler.report(t.makeError(n, CONST_REASSIGNED_VALUE_ERROR, name));
CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9.statements[31]++;
  }
}

class CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9 ());
  }
    public static long[] statements = new long[32];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ConstCheck.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$k7losa3niakt9hwtyytl5y9 () {
    super("com.google.javascript.jscomp.ConstCheck.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 31; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ConstCheck.java");
      for (int i = 1; i <= 31; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

