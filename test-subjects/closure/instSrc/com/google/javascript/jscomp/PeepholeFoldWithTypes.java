/*
 * Copyright 2010 The Closure Compiler Authors.
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
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.JSType;

/**
 * Performs type-aware peephole optimizations.
 *
 * These peephole optimizations are in their own class because
 * type information may not always be available (such as during pre-processing)
 * or may not be turned on.
 *
 * Currently only Token.TYPEOF is folded -- in the future it may be possible to
 * fold Token.INSTANCEOF as well. Another possibility is folding when
 * non-nullable objects are used in Boolean logic, such as:
 * "if (x) {" or "(!x) ? a : b" or "x && foo()"
 *
 * TODO(dcc): Support folding Token.INSTANCEOF and non-nullable objects
 * in Boolean logic.
 *
 * @author dcc@google.com (Devin Coughlin)
 */
class PeepholeFoldWithTypes extends AbstractPeepholeOptimization {
  static {
    CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.ping();
  }


  @Override
  Node optimizeSubtree(Node subtree) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[1]++;
    switch (subtree.getType()) {
      case Token.TYPEOF:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[1]++;
        return tryFoldTypeof(subtree);
      default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[2]++;
        return subtree;
    }
  }

  /**
   * Folds "typeof expression" based on the JSType of "expression" if the
   * expression has no side effects.
   *
   * <p>E.g.,
   * <pre>
   * var x = 6;
   * if (typeof(x) == "number") {
   * }
   * </pre>
   * folds to
   * <pre>
   * var x = 6;
   * if ("number" == "number") {
   * }
   * </pre>
   *
   * <p>This method doesn't fold literal values -- we leave that to
   * PeepholeFoldConstants.
   */
  private Node tryFoldTypeof(Node typeofNode) {
    Preconditions.checkArgument(typeofNode.isTypeOf());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[2]++;
    Preconditions.checkArgument(typeofNode.getFirstChild() != null);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[3]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[4]++;

    Node argumentNode = typeofNode.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;

    // We'll let PeepholeFoldConstants handle folding literals
    // and we can't remove arguments with possible side effects.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(argumentNode, true)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(argumentNode)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[3]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[6]++;
      JSType argumentType = argumentNode.getJSType();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[7]++;

      String typeName = null;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;

      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((argumentType != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[5]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        // typeof null is "object" in JavaScript
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((argumentType.isObject()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((argumentType.isNullType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[7]++;
          typeName = "object";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[10]++;

        } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[8]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[11]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((argumentType.isStringValueType()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[9]++;
          typeName = "string";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[12]++;

        } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[10]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[13]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((argumentType.isNumberValueType()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[11]++;
          typeName = "number";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[14]++;

        } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[12]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[15]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((argumentType.isBooleanValueType()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[13]++;
          typeName = "boolean";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[16]++;

        } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[14]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[17]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((argumentType.isVoidType()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[15]++;
           typeName = "undefined";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[18]++;

        } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[16]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[19]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((argumentType.isUnionType()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[17]++;
          // TODO(dcc): We don't handle union types, for now,
          // but could support, say, unions of different object types
          // in the future.
          typeName = null;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[20]++;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[18]++;}
}
}
}
}
}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((typeName != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[19]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[22]++;
          Node newNode = IR.string(typeName);
          typeofNode.getParent().replaceChild(typeofNode, newNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[23]++;
          reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.statements[24]++;

          return newNode;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[20]++;}

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[6]++;}

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl.branches[4]++;}
    return typeofNode;
  }
}

class CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl ());
  }
    public static long[] statements = new long[25];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PeepholeFoldWithTypes.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,2,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7cig2rrdikpl6nz4apxwa0rl () {
    super("com.google.javascript.jscomp.PeepholeFoldWithTypes.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 24; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PeepholeFoldWithTypes.java");
      for (int i = 1; i <= 24; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 9; i++) {
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

