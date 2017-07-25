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
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.head.ScriptRuntime;
import com.google.javascript.rhino.jstype.TernaryValue;

/**
 * Peephole optimization to fold constants (e.g. x + 1 + 7 --> x + 8).
 *
 */
class PeepholeFoldConstants extends AbstractPeepholeOptimization {
  static {
    CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.ping();
  }


  // TODO(johnlenz): optimizations should not be emiting errors. Move these to
  // a check pass.
  static final DiagnosticType INVALID_GETELEM_INDEX_ERROR =
      DiagnosticType.warning(
          "JSC_INVALID_GETELEM_INDEX_ERROR",
          "Array index not integer: {0}");
  static {
    CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[1]++;
  }

  static final DiagnosticType INDEX_OUT_OF_BOUNDS_ERROR =
      DiagnosticType.warning(
          "JSC_INDEX_OUT_OF_BOUNDS_ERROR",
          "Array index out of bounds: {0}");
  static {
    CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[2]++;
  }

  static final DiagnosticType NEGATING_A_NON_NUMBER_ERROR =
      DiagnosticType.warning(
          "JSC_NEGATING_A_NON_NUMBER_ERROR",
          "Can't negate non-numeric value: {0}");
  static {
    CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[3]++;
  }

  static final DiagnosticType BITWISE_OPERAND_OUT_OF_RANGE =
      DiagnosticType.warning(
          "JSC_BITWISE_OPERAND_OUT_OF_RANGE",
          "Operand out of range, bitwise operation will lose information: {0}");
  static {
    CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[4]++;
  }

  static final DiagnosticType SHIFT_AMOUNT_OUT_OF_BOUNDS =
      DiagnosticType.warning(
          "JSC_SHIFT_AMOUNT_OUT_OF_BOUNDS",
          "Shift amount out of bounds: {0}");
  static {
    CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[5]++;
  }

  static final DiagnosticType FRACTIONAL_BITWISE_OPERAND =
      DiagnosticType.warning(
          "JSC_FRACTIONAL_BITWISE_OPERAND",
          "Fractional bitwise operand: {0}");
  static {
    CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[6]++;
  }

  private static final double MAX_FOLD_NUMBER = Math.pow(2, 53);
  static {
    CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[7]++;
  }

  private final boolean late;

  /**
   * @param late When late is false, this mean we are currently running before
   * most of the other optimizations. In this case we would avoid optimizations
   * that would make the code harder to analyze. When this is true, we would
   * do anything to minimize for size.
   */
  PeepholeFoldConstants(boolean late) {
    this.late = late;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[8]++;
  }

  @Override
  Node optimizeSubtree(Node subtree) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[9]++;
    switch(subtree.getType()) {
      case Token.NEW:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[1]++;
        return tryFoldCtorCall(subtree);

      case Token.TYPEOF:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[2]++;
        return tryFoldTypeof(subtree);

      case Token.NOT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[3]++;
      case Token.POS:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[4]++;
      case Token.NEG:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[5]++;
      case Token.BITNOT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[6]++;
        tryReduceOperandsForOp(subtree);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[10]++;
        return tryFoldUnaryOperator(subtree);

      case Token.VOID:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[7]++;
        return tryReduceVoid(subtree);

      default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[8]++;
        tryReduceOperandsForOp(subtree);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[11]++;
        return tryFoldBinaryOperator(subtree);
    }
  }

  private Node tryFoldBinaryOperator(Node subtree) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[12]++;
    Node left = subtree.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((left == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[9]++;
      return subtree;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[10]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[14]++;

    Node right = left.getNext();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((right == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[11]++;
      return subtree;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[12]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[16]++;

    // If we've reached here, node is truly a binary operator.
    switch(subtree.getType()) {
      case Token.GETPROP:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[13]++;
        return tryFoldGetProp(subtree, left, right);

      case Token.GETELEM:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[14]++;
        return tryFoldGetElem(subtree, left, right);

      case Token.INSTANCEOF:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[15]++;
        return tryFoldInstanceof(subtree, left, right);

      case Token.AND:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[16]++;
      case Token.OR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[17]++;
        return tryFoldAndOr(subtree, left, right);

      case Token.LSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[18]++;
      case Token.RSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[19]++;
      case Token.URSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[20]++;
        return tryFoldShift(subtree, left, right);

      case Token.ASSIGN:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[21]++;
        return tryFoldAssign(subtree, left, right);

      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[22]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[23]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[24]++;
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[25]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[26]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[27]++;
      case Token.ASSIGN_ADD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[28]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[29]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[30]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[31]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[32]++;
        return tryUnfoldAssignOp(subtree, left, right);

      case Token.ADD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[33]++;
        return tryFoldAdd(subtree, left, right);

      case Token.SUB:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[34]++;
      case Token.DIV:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[35]++;
      case Token.MOD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[36]++;
        return tryFoldArithmeticOp(subtree, left, right);

      case Token.MUL:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[37]++;
      case Token.BITAND:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[38]++;
      case Token.BITOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[39]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[40]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[17]++;
        Node result = tryFoldArithmeticOp(subtree, left, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((result != subtree) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[41]++;
          return result;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[42]++;}
        return tryFoldLeftChildOp(subtree, left, right);

      case Token.LT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[43]++;
      case Token.GT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[44]++;
      case Token.LE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[45]++;
      case Token.GE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[46]++;
      case Token.EQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[47]++;
      case Token.NE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[48]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[49]++;
      case Token.SHNE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[50]++;
        return tryFoldComparison(subtree, left, right);

      default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[51]++;
        return subtree;
    }
  }

  private Node tryReduceVoid(Node n) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[19]++;
    Node child = n.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((child.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((child.getDouble() != 0.0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[52]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[21]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[54]++;
        n.replaceChild(child, IR.number(0));
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[22]++;
        reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[23]++;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[55]++;}

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[53]++;}
    return n;
  }

  private void tryReduceOperandsForOp(Node n) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[24]++;
    switch (n.getType()) {
      case Token.ADD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[56]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[25]++;
        Node left = n.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[26]++;
        Node right = n.getLastChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((NodeUtil.mayBeString(left)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.mayBeString(right)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[57]++;
          tryConvertOperandsToNumber(n);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[28]++;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[58]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[29]++;
        break;
      case Token.ASSIGN_BITOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[59]++;
      case Token.ASSIGN_BITXOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[60]++;
      case Token.ASSIGN_BITAND:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[61]++;
        // TODO(johnlenz): convert these to integers.
      case Token.ASSIGN_LSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[62]++;
      case Token.ASSIGN_RSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[63]++;
      case Token.ASSIGN_URSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[64]++;
      case Token.ASSIGN_SUB:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[65]++;
      case Token.ASSIGN_MUL:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[66]++;
      case Token.ASSIGN_MOD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[67]++;
      case Token.ASSIGN_DIV:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[68]++;
        tryConvertToNumber(n.getLastChild());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[30]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[31]++;
        break;
      case Token.BITNOT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[69]++;
      case Token.BITOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[70]++;
      case Token.BITXOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[71]++;
      case Token.BITAND:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[72]++;
      case Token.LSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[73]++;
      case Token.RSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[74]++;
      case Token.URSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[75]++;
      case Token.SUB:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[76]++;
      case Token.MUL:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[77]++;
      case Token.MOD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[78]++;
      case Token.DIV:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[79]++;
      case Token.POS:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[80]++;
      case Token.NEG:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[81]++;
        tryConvertOperandsToNumber(n);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[32]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[33]++;
        break; default : CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[82]++;
    }
  }

  private void tryConvertOperandsToNumber(Node n) {
    Node next;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[34]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); c = next) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[1]--;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[2]--;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[3]++;
}
      next = c.getNext();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[35]++;
      tryConvertToNumber(c);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[36]++;
    }
  }

  private void tryConvertToNumber(Node n) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[37]++;
    switch (n.getType()) {
      case Token.NUMBER:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[83]++;
        // Nothing to do
        return;
      case Token.AND:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[84]++;
      case Token.OR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[85]++;
      case Token.COMMA:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[86]++;
        tryConvertToNumber(n.getLastChild());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[38]++;
        return;
      case Token.HOOK:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[87]++;
        tryConvertToNumber(n.getChildAtIndex(1));
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[39]++;
        tryConvertToNumber(n.getLastChild());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[40]++;
        return;
      case Token.NAME:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[88]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.isUndefined(n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[89]++;
          return;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[90]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[42]++;
        break; default : CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[91]++;
    }
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[43]++;

    Double result = NodeUtil.getNumberValue(n);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[92]++;
      return;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[93]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[45]++;

    double value = result;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[46]++;

    Node replacement = NodeUtil.numberNode(value, n);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((replacement.isEquivalentTo(n)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[94]++;
      return;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[95]++;}

    n.getParent().replaceChild(n, replacement);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[48]++;
    reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[49]++;
  }

  /**
   * Folds 'typeof(foo)' if foo is a literal, e.g.
   * typeof("bar") --> "string"
   * typeof(6) --> "number"
   */
  private Node tryFoldTypeof(Node originalTypeofNode) {
    Preconditions.checkArgument(originalTypeofNode.isTypeOf());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[50]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[51]++;

    Node argumentNode = originalTypeofNode.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[52]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((argumentNode == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(argumentNode, true)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[96]++;
      return originalTypeofNode;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[97]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[53]++;

    String typeNameString = null;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[54]++;

    switch (argumentNode.getType()) {
      case Token.FUNCTION:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[98]++;
        typeNameString = "function";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[55]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[56]++;
        break;
      case Token.STRING:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[99]++;
        typeNameString = "string";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[57]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[58]++;
        break;
      case Token.NUMBER:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[100]++;
        typeNameString = "number";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[59]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[60]++;
        break;
      case Token.TRUE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[101]++;
      case Token.FALSE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[102]++;
        typeNameString = "boolean";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[61]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[62]++;
        break;
      case Token.NULL:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[103]++;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[104]++;
      case Token.ARRAYLIT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[105]++;
        typeNameString = "object";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[63]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[64]++;
        break;
      case Token.VOID:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[106]++;
        typeNameString = "undefined";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[65]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[66]++;
        break;
      case Token.NAME:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[107]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[67]++;
int CodeCoverConditionCoverageHelper_C12;
        // We assume here that programs don't change the value of the
        // keyword undefined to something other than the value undefined.
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 (("undefined".equals(argumentNode.getString())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[108]++;
          typeNameString = "undefined";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[68]++;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[109]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[69]++;
        break; default : CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[110]++;
    }
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[70]++;
int CodeCoverConditionCoverageHelper_C13;

    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((typeNameString != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[111]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[71]++;
      Node newNode = IR.string(typeNameString);
      originalTypeofNode.getParent().replaceChild(originalTypeofNode, newNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[72]++;
      reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[73]++;

      return newNode;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[112]++;}

    return originalTypeofNode;
  }

  private Node tryFoldUnaryOperator(Node n) {
    Preconditions.checkState(n.hasOneChild());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[74]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[75]++;

    Node left = n.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[76]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[77]++;
int CodeCoverConditionCoverageHelper_C14;

    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((left == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[113]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[114]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[78]++;

    TernaryValue leftVal = NodeUtil.getPureBooleanValue(left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[79]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((leftVal == TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[115]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[116]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[80]++;

    switch (n.getType()) {
      case Token.NOT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[117]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[81]++;
int CodeCoverConditionCoverageHelper_C16;
        // Don't fold !0 and !1 back to false.
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((late) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((left.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[118]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[82]++;
          double numValue = left.getDouble();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[83]++;
int CodeCoverConditionCoverageHelper_C17;
          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((numValue == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((numValue == 1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[120]++;
            return n;

          } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[121]++;}

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[119]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[84]++;
        Node replacementNode = NodeUtil.booleanNode(!leftVal.toBoolean(true));
        parent.replaceChild(n, replacementNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[85]++;
        reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[86]++;
        return replacementNode;
      case Token.POS:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[122]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[87]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((NodeUtil.isNumericResult(left)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[123]++;
          // POS does nothing to numeric values.
          parent.replaceChild(n, left.detachFromParent());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[88]++;
          reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[89]++;
          return left;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[124]++;}
        return n;
      case Token.NEG:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[125]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[90]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((left.isName()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[126]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[91]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((left.getString().equals("Infinity")) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[128]++;
            // "-Infinity" is valid and a literal, don't modify it.
            return n;

          } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[129]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[92]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((left.getString().equals("NaN")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[130]++;
            // "-NaN" is "NaN".
            n.removeChild(left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[93]++;
            parent.replaceChild(n, left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[94]++;
            reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[95]++;
            return left;

          } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[131]++;}
}

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[127]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[96]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((left.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[132]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[97]++;
          double negNum = -left.getDouble();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[98]++;

          Node negNumNode = IR.number(negNum);
          parent.replaceChild(n, negNumNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[99]++;
          reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[100]++;
          return negNumNode;

        } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[133]++;
          // left is not a number node, so do not replace, but warn the
          // user because they can't be doing anything good
          report(NEGATING_A_NON_NUMBER_ERROR, left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[101]++;
          return n;
        }
      case Token.BITNOT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[134]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[102]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[103]++;
          double val = left.getDouble();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[104]++;
int CodeCoverConditionCoverageHelper_C23;
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((val >= Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((val <= Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[136]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[105]++;
            int intVal = (int) val;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[106]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((intVal == val) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[138]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[107]++;
              Node notIntValNode = IR.number(~intVal);
              parent.replaceChild(n, notIntValNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[108]++;
              reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[109]++;
              return notIntValNode;

            } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[139]++;
              report(FRACTIONAL_BITWISE_OPERAND, left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[110]++;
              return n;
            }

          } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[137]++;
            report(BITWISE_OPERAND_OUT_OF_RANGE, left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[111]++;
            return n;
          }
        } catch (UnsupportedOperationException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[140]++;
          // left is not a number node, so do not replace, but warn the
          // user because they can't be doing anything good
          report(NEGATING_A_NON_NUMBER_ERROR, left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[112]++;
          return n;
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[135]++;
}
  }
        default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[141]++;
          return n;
    }
  }

  /**
   * Try to fold {@code left instanceof right} into {@code true}
   * or {@code false}.
   */
  private Node tryFoldInstanceof(Node n, Node left, Node right) {
    Preconditions.checkArgument(n.isInstanceOf());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[113]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[114]++;
int CodeCoverConditionCoverageHelper_C25;

    // TODO(johnlenz) Use type information if available to fold
    // instanceof.
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(left, true)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(right)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[142]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[115]++;

      Node replacementNode = null;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[116]++;
int CodeCoverConditionCoverageHelper_C26;

      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(left)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[144]++;
        // Non-object types are never instances.
        replacementNode = IR.falseNode();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[117]++;

      } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[145]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[118]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((right.isName()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 (("Object".equals(right.getString())) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[146]++;
        replacementNode = IR.trueNode();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[119]++;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[147]++;}
}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[120]++;
int CodeCoverConditionCoverageHelper_C28;

      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((replacementNode != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[148]++;
        n.getParent().replaceChild(n, replacementNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[121]++;
        reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[122]++;
        return replacementNode;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[149]++;}

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[143]++;}

    return n;
  }

  private Node tryFoldAssign(Node n, Node left, Node right) {
    Preconditions.checkArgument(n.isAssign());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[123]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[124]++;
int CodeCoverConditionCoverageHelper_C29;

    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((late) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[150]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[151]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[125]++;
int CodeCoverConditionCoverageHelper_C30;

    // Tries to convert x = x + y -> x += y;
    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((right.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((right.getFirstChild().getNext() != right.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[152]++;
      // RHS must have two children.
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[153]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[126]++;
int CodeCoverConditionCoverageHelper_C31;

    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(left)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[154]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[155]++;}

    Node newRight;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[127]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((areNodesEqualForInlining(left, right.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[156]++;
      newRight = right.getLastChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[128]++;

    } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[157]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[129]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((NodeUtil.isCommutative(right.getType())) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((areNodesEqualForInlining(left, right.getLastChild())) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[158]++;
      newRight = right.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[130]++;

    } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[159]++;
      return n;
    }
}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[131]++;

    int newType = -1;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[132]++;
    switch (right.getType()) {
      case Token.ADD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[160]++;
        newType = Token.ASSIGN_ADD;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[133]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[134]++;
        break;
      case Token.BITAND:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[161]++;
        newType = Token.ASSIGN_BITAND;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[135]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[136]++;
        break;
      case Token.BITOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[162]++;
        newType = Token.ASSIGN_BITOR;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[137]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[138]++;
        break;
      case Token.BITXOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[163]++;
        newType = Token.ASSIGN_BITXOR;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[139]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[140]++;
        break;
      case Token.DIV:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[164]++;
        newType = Token.ASSIGN_DIV;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[141]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[142]++;
        break;
      case Token.LSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[165]++;
        newType = Token.ASSIGN_LSH;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[143]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[144]++;
        break;
      case Token.MOD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[166]++;
        newType = Token.ASSIGN_MOD;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[145]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[146]++;
        break;
      case Token.MUL:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[167]++;
        newType = Token.ASSIGN_MUL;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[147]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[148]++;
        break;
      case Token.RSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[168]++;
        newType = Token.ASSIGN_RSH;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[149]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[150]++;
        break;
      case Token.SUB:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[169]++;
        newType = Token.ASSIGN_SUB;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[151]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[152]++;
        break;
      case Token.URSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[170]++;
        newType = Token.ASSIGN_URSH;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[153]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[154]++;
        break;
      default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[171]++;
        return n;
    }
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[155]++;

    Node newNode = new Node(newType,
        left.detachFromParent(), newRight.detachFromParent());
    n.getParent().replaceChild(n, newNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[156]++;

    reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[157]++;

    return newNode;
  }

  private Node tryUnfoldAssignOp(Node n, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[158]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((late) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[172]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[173]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[159]++;
int CodeCoverConditionCoverageHelper_C35;

    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((n.getFirstChild().getNext() != n.getLastChild()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[174]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[175]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[160]++;
int CodeCoverConditionCoverageHelper_C36;

    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(left)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[176]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[177]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[161]++;

    // Tries to convert x += y -> x = x + y;
    int op = NodeUtil.getOpFromAssignmentOp(n);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[162]++;
    Node replacement = IR.assign(left.detachFromParent(),
        new Node(op, left.cloneTree(), right.detachFromParent())
            .srcref(n));
    n.getParent().replaceChild(n, replacement);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[163]++;
    reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[164]++;

    return replacement;
  }

  /**
   * Try to fold a AND/OR node.
   */
  private Node tryFoldAndOr(Node n, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[165]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[166]++;

    Node result = null;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[167]++;

    int type = n.getType();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[168]++;

    TernaryValue leftVal = NodeUtil.getImpureBooleanValue(left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[169]++;
int CodeCoverConditionCoverageHelper_C37;

    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((leftVal != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[178]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[170]++;
      boolean lval = leftVal.toBoolean(true);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[171]++;
int CodeCoverConditionCoverageHelper_C38;

      // (TRUE || x) => TRUE (also, (3 || x) => 3)
      // (FALSE && x) => FALSE
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (128)) == 0 || true) &&
 ((lval) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (32)) == 0 || true) &&
 ((type == Token.OR) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((lval) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((type == Token.AND) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 4) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 4) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[180]++;
        result = left;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[172]++;


      } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[181]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[173]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(left)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[182]++;
        // (FALSE || x) => x
        // (TRUE && x) => x
        result = right;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[174]++;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[183]++;}
}

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[179]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[175]++;
int CodeCoverConditionCoverageHelper_C40;

    // Note: Right hand side folding is handled by
    // PeepholeSubstituteAlternateSyntax#tryMinimizeCondition

    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[184]++;
      // Fold it!
      n.removeChild(result);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[176]++;
      parent.replaceChild(n, result);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[177]++;
      reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[178]++;

      return result;

    } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[185]++;
      return n;
    }
  }

  /**
   * Expressions such as [foo() + 'a' + 'b'] generate parse trees
   * where no node has two const children ((foo() + 'a') + 'b'), so
   * tryFoldAdd() won't fold it -- tryFoldLeftChildAdd() will (for Strings).
   * Specifically, it folds Add expressions where:
   *  - The left child is also and add expression
   *  - The right child is a constant value
   *  - The left child's right child is a STRING constant.
   */
  private Node tryFoldChildAddString(Node n, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[179]++;
int CodeCoverConditionCoverageHelper_C41;

    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(right, false)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((left.isAdd()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[186]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[180]++;

      Node ll = left.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[181]++;
      Node lr = ll.getNext();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[182]++;
int CodeCoverConditionCoverageHelper_C42;

      // Left's right child MUST be a string. We would not want to fold
      // foo() + 2 + 'a' because we don't know what foo() will return, and
      // therefore we don't know if left is a string concat, or a numeric add.
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((lr.isString()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[188]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[183]++;
        String leftString = NodeUtil.getStringValue(lr);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[184]++;
        String rightString = NodeUtil.getStringValue(right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[185]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((leftString != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((rightString != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[190]++;
          left.removeChild(ll);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[186]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[187]++;
          String result = leftString + rightString;
          n.replaceChild(left, ll);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[188]++;
          n.replaceChild(right, IR.string(result));
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[189]++;
          reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[190]++;
          return n;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[191]++;}

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[189]++;}

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[187]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[191]++;
int CodeCoverConditionCoverageHelper_C44;

    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(left, false)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((right.isAdd()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[192]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[192]++;

      Node rl = right.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[193]++;
      Node rr = right.getLastChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[194]++;
int CodeCoverConditionCoverageHelper_C45;

      // Left's right child MUST be a string. We would not want to fold
      // foo() + 2 + 'a' because we don't know what foo() will return, and
      // therefore we don't know if left is a string concat, or a numeric add.
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((rl.isString()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[194]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[195]++;
        String leftString = NodeUtil.getStringValue(left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[196]++;
        String rightString = NodeUtil.getStringValue(rl);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[197]++;
int CodeCoverConditionCoverageHelper_C46;
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((leftString != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((rightString != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[196]++;
          right.removeChild(rr);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[198]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[199]++;
          String result = leftString + rightString;
          n.replaceChild(right, rr);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[200]++;
          n.replaceChild(left, IR.string(result));
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[201]++;
          reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[202]++;
          return n;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[197]++;}

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[195]++;}

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[193]++;}

    return n;
  }

  /**
   * Try to fold an ADD node with constant operands
   */
  private Node tryFoldAddConstantString(Node n, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[203]++;
int CodeCoverConditionCoverageHelper_C47;
    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((left.isString()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((right.isString()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[198]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[204]++;
      // Add strings.
      String leftString = NodeUtil.getStringValue(left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[205]++;
      String rightString = NodeUtil.getStringValue(right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[206]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((leftString != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((rightString != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[200]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[207]++;
        Node newStringNode = IR.string(leftString + rightString);
        n.getParent().replaceChild(n, newStringNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[208]++;
        reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[209]++;
        return newStringNode;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[201]++;}

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[199]++;}



    return n;
  }

  /**
   * Try to fold arithmetic binary operators
   */
  private Node tryFoldArithmeticOp(Node n, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[210]++;
    Node result = performArithmeticOp(n.getType(), left, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[211]++;
int CodeCoverConditionCoverageHelper_C49;
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[202]++;
      result.copyInformationFromForTree(n);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[212]++;
      n.getParent().replaceChild(n, result);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[213]++;
      reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[214]++;
      return result;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[203]++;}
    return n;
  }

  /**
   * Try to fold arithmetic binary operators
   */
  private Node performArithmeticOp(int opType, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[215]++;
int CodeCoverConditionCoverageHelper_C50;
    // Unlike other operations, ADD operands are not always converted
    // to Number.
    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (32)) == 0 || true) &&
 ((opType == Token.ADD) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((NodeUtil.mayBeString(left, false)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((NodeUtil.mayBeString(right, false)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[204]++;
      return null;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[205]++;}

    double result;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[216]++;

    // TODO(johnlenz): Handle NaN with unknown value. BIT ops convert NaN
    // to zero so this is a little awkward here.

    Double lValObj = NodeUtil.getNumberValue(left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[217]++;
int CodeCoverConditionCoverageHelper_C51;
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((lValObj == null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[206]++;
      return null;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[207]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[218]++;
    Double rValObj = NodeUtil.getNumberValue(right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[219]++;
int CodeCoverConditionCoverageHelper_C52;
    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((rValObj == null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[208]++;
      return null;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[209]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[220]++;

    double lval = lValObj;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[221]++;
    double rval = rValObj;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[222]++;

    switch (opType) {
      case Token.BITAND:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[210]++;
        result = ScriptRuntime.toInt32(lval) & ScriptRuntime.toInt32(rval);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[223]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[224]++;
        break;
      case Token.BITOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[211]++;
        result = ScriptRuntime.toInt32(lval) | ScriptRuntime.toInt32(rval);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[225]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[226]++;
        break;
      case Token.BITXOR:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[212]++;
        result = ScriptRuntime.toInt32(lval) ^ ScriptRuntime.toInt32(rval);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[227]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[228]++;
        break;
      case Token.ADD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[213]++;
        result = lval + rval;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[229]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[230]++;
        break;
      case Token.SUB:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[214]++;
        result = lval - rval;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[231]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[232]++;
        break;
      case Token.MUL:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[215]++;
        result = lval * rval;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[233]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[234]++;
        break;
      case Token.MOD:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[216]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[235]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((rval == 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[217]++;
          return null;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[218]++;}
        result = lval % rval;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[236]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[237]++;
        break;
      case Token.DIV:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[219]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[238]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((rval == 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[220]++;
          return null;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[221]++;}
        result = lval / rval;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[239]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[240]++;
        break;
      default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[222]++;
        throw new Error("Unexpected arithmetic operator");
    }
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[241]++;
int CodeCoverConditionCoverageHelper_C55;

    // TODO(johnlenz): consider removing the result length check.
    // length of the left and right value plus 1 byte for the operator.
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C55 |= (512)) == 0 || true) &&
 ((String.valueOf(result).length() <=
        String.valueOf(lval).length() + String.valueOf(rval).length() + 1) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C55 |= (128)) == 0 || true) &&
 ((Math.abs(result) <= MAX_FOLD_NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (64)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C55 |= (32)) == 0 || true) &&
 ((Double.isNaN(result)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((result == Double.POSITIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((result == Double.NEGATIVE_INFINITY) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 5) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 5) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[223]++;
      return NodeUtil.numberNode(result, null);

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[224]++;}
    return null;
  }

  /**
   * Expressions such as [foo() * 10 * 20] generate parse trees
   * where no node has two const children ((foo() * 10) * 20), so
   * performArithmeticOp() won't fold it -- tryFoldLeftChildOp() will.
   * Specifically, it folds associative expressions where:
   *  - The left child is also an associative expression of the same time.
   *  - The right child is a constant NUMBER constant.
   *  - The left child's right child is a NUMBER constant.
   */
  private Node tryFoldLeftChildOp(Node n, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[242]++;
    int opType = n.getType();
    Preconditions.checkState(
        (NodeUtil.isAssociative(opType) && NodeUtil.isCommutative(opType))
        || n.isAdd());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[243]++;

    Preconditions.checkState(
        !n.isAdd()|| !NodeUtil.mayBeString(n));
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[244]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[245]++;

    // Use getNumberValue to handle constants like "NaN" and "Infinity"
    // other values are converted to numbers elsewhere.
    Double rightValObj = NodeUtil.getNumberValue(right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[246]++;
int CodeCoverConditionCoverageHelper_C56;
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((rightValObj != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((left.getType() == opType) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[225]++;
      Preconditions.checkState(left.getChildCount() == 2);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[247]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[248]++;

      Node ll = left.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[249]++;
      Node lr = ll.getNext();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[250]++;

      Node valueToCombine = ll;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[251]++;
      Node replacement = performArithmeticOp(opType, valueToCombine, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[252]++;
int CodeCoverConditionCoverageHelper_C57;
      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((replacement == null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[227]++;
        valueToCombine = lr;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[253]++;
        replacement = performArithmeticOp(opType, valueToCombine, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[254]++;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[228]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[255]++;
int CodeCoverConditionCoverageHelper_C58;
      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((replacement != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[229]++;
        // Remove the child that has been combined
        left.removeChild(valueToCombine);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[256]++;
        // Replace the left op with the remaining child.
        n.replaceChild(left, left.removeFirstChild());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[257]++;
        // New "-Infinity" node need location info explicitly
        // added.
        replacement.copyInformationFromForTree(right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[258]++;
        n.replaceChild(right, replacement);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[259]++;
        reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[260]++;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[230]++;}

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[226]++;}

    return n;
  }

  private Node tryFoldAdd(Node node, Node left, Node right) {
    Preconditions.checkArgument(node.isAdd());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[261]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[262]++;
int CodeCoverConditionCoverageHelper_C59;

    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((NodeUtil.mayBeString(node, true)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[231]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[263]++;
int CodeCoverConditionCoverageHelper_C60;
      if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(left, false)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(right, false)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[233]++;
        // '6' + 7
        return tryFoldAddConstantString(node, left, right);

      } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[234]++;
        // a + 7 or 6 + a
        return tryFoldChildAddString(node, left, right);
      }

    } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[232]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[264]++;
      // Try arithmetic add
      Node result = tryFoldArithmeticOp(node, left, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[265]++;
int CodeCoverConditionCoverageHelper_C61;
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((result != node) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[235]++;
        return result;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[236]++;}
      return tryFoldLeftChildOp(node, left, right);
    }
  }

  /**
   * Try to fold shift operations
   */
  private Node tryFoldShift(Node n, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[266]++;
int CodeCoverConditionCoverageHelper_C62;
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((left.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((right.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[237]++;

      double result;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[267]++;
      double lval = left.getDouble();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[268]++;
      double rval = right.getDouble();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[269]++;
int CodeCoverConditionCoverageHelper_C63;

      // check ranges.  We do not do anything that would clip the double to
      // a 32-bit range, since the user likely does not intend that.
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((lval >= Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((lval <= Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[239]++;
        report(BITWISE_OPERAND_OUT_OF_RANGE, left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[270]++;
        return n;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[240]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[271]++;
int CodeCoverConditionCoverageHelper_C64;

      // only the lower 5 bits are used when shifting, so don't do anything
      // if the shift amount is outside [0,32)
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((rval >= 0) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((rval < 32) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[241]++;
        report(SHIFT_AMOUNT_OUT_OF_BOUNDS, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[272]++;
        return n;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[242]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[273]++;

      // Convert the numbers to ints
      int lvalInt = (int) lval;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[274]++;
int CodeCoverConditionCoverageHelper_C65;
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((lvalInt != lval) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[243]++;
        report(FRACTIONAL_BITWISE_OPERAND, left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[275]++;
        return n;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[244]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[276]++;

      int rvalInt = (int) rval;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[277]++;
int CodeCoverConditionCoverageHelper_C66;
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((rvalInt != rval) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[245]++;
        report(FRACTIONAL_BITWISE_OPERAND, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[278]++;
        return n;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[246]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[279]++;

      switch (n.getType()) {
        case Token.LSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[247]++;
          result = lvalInt << rvalInt;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[280]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[281]++;
          break;
        case Token.RSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[248]++;
          result = lvalInt >> rvalInt;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[282]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[283]++;
          break;
        case Token.URSH:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[249]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[284]++;
          // JavaScript handles zero shifts on signed numbers differently than
          // Java as an Java int can not represent the unsigned 32-bit number
          // where JavaScript can so use a long here.
          long lvalLong = lvalInt & 0xffffffffL;
          result = lvalLong >>> rvalInt;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[285]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[286]++;
          break;
        default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[250]++;
          throw new AssertionError("Unknown shift operator: " +
              Token.name(n.getType()));
      }
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[287]++;

      Node newNumber = IR.number(result);
      n.getParent().replaceChild(n, newNumber);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[288]++;
      reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[289]++;

      return newNumber;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[238]++;}

    return n;
  }

  /**
   * Try to fold comparison nodes, e.g ==
   */
  @SuppressWarnings("fallthrough")
  private Node tryFoldComparison(Node n, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[290]++;
    TernaryValue result = evaluateComparison(n.getType(), left, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[291]++;
int CodeCoverConditionCoverageHelper_C67;
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((result == TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[251]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[252]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[292]++;

    Node newNode = NodeUtil.booleanNode(result.toBoolean(true));
    n.getParent().replaceChild(n, newNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[293]++;
    reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[294]++;

    return newNode;
  }

  static TernaryValue evaluateComparison(int op, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[295]++;
    boolean leftLiteral = NodeUtil.isLiteralValue(left, true);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[296]++;
    boolean rightLiteral = NodeUtil.isLiteralValue(right, true);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[297]++;
int CodeCoverConditionCoverageHelper_C68;

    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((leftLiteral) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((rightLiteral) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[253]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[298]++;
int CodeCoverConditionCoverageHelper_C69;
      // We only handle literal operands for LT and GT.
      if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((op != Token.GT) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((op != Token.LT) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[255]++;
        return TernaryValue.UNKNOWN;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[256]++;}

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[254]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[299]++;

    boolean undefinedRight = NodeUtil.isUndefined(right) && rightLiteral;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[300]++;
    boolean nullRight = right.isNull();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[301]++;
    int lhType = getNormalizedNodeType(left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[302]++;
    int rhType = getNormalizedNodeType(right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[303]++;
    switch (lhType) {
      case Token.VOID:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[257]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[304]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((leftLiteral) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[258]++;
          return TernaryValue.UNKNOWN;

        } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[259]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[305]++;
int CodeCoverConditionCoverageHelper_C71; if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((rightLiteral) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[260]++;
          return TernaryValue.UNKNOWN;

        } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[261]++;
          return TernaryValue.forBoolean(compareToUndefined(right, op));
        }
}

      case Token.NULL:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[262]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[306]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((rightLiteral) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((isEqualityOp(op)) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[263]++;
          return TernaryValue.forBoolean(compareToNull(right, op));

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[264]++;}
        // fallthrough
      case Token.TRUE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[265]++;
      case Token.FALSE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[266]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[307]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((undefinedRight) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[267]++;
          return TernaryValue.forBoolean(compareToUndefined(left, op));

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[268]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[308]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (32)) == 0 || true) &&
 ((rhType != Token.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((rhType != Token.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((rhType != Token.NULL) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 3) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 3) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[269]++;
          return TernaryValue.UNKNOWN;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[270]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[309]++;
        switch (op) {
          case Token.SHEQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[271]++;
          case Token.EQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[272]++;
            return TernaryValue.forBoolean(lhType == rhType);

          case Token.SHNE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[273]++;
          case Token.NE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[274]++;
            return TernaryValue.forBoolean(lhType != rhType);

          case Token.GE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[275]++;
          case Token.LE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[276]++;
          case Token.GT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[277]++;
          case Token.LT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[278]++;
            return compareAsNumbers(op, left, right); default : CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[279]++;
        }
        return TernaryValue.UNKNOWN;

      case Token.THIS:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[280]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[310]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((right.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[281]++;
          return TernaryValue.UNKNOWN;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[282]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[311]++;
        switch (op) {
          case Token.SHEQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[283]++;
          case Token.EQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[284]++;
            return TernaryValue.TRUE;

          case Token.SHNE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[285]++;
          case Token.NE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[286]++;
            return TernaryValue.FALSE; default : CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[287]++;
        }

        // We can only handle == and != here.
        // GT, LT, GE, LE depend on the type of "this" and how it will
        // be converted to number.  The results are different depending on
        // whether it is a string, NaN or other number value.
        return TernaryValue.UNKNOWN;

      case Token.STRING:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[288]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[312]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((undefinedRight) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[289]++;
          return TernaryValue.forBoolean(compareToUndefined(left, op));

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[290]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[313]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((nullRight) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((isEqualityOp(op)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[291]++;
          return TernaryValue.forBoolean(compareToNull(left, op));

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[292]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[314]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((Token.STRING != right.getType()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[293]++;
          return TernaryValue.UNKNOWN;
  // Only eval if they are the same type
        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[294]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[315]++;

        switch (op) {
          case Token.SHEQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[295]++;
          case Token.EQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[296]++;
            return areStringsEqual(left.getString(), right.getString());

          case Token.SHNE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[297]++;
          case Token.NE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[298]++;
            return areStringsEqual(left.getString(), right.getString()).not(); default : CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[299]++;
        }

        return TernaryValue.UNKNOWN;

      case Token.NUMBER:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[300]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[316]++;
int CodeCoverConditionCoverageHelper_C79;
        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((undefinedRight) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[301]++;
          return TernaryValue.forBoolean(compareToUndefined(left, op));

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[302]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[317]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (8)) == 0 || true) &&
 ((nullRight) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((isEqualityOp(op)) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[303]++;
          return TernaryValue.forBoolean(compareToNull(left, op));

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[304]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[318]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((Token.NUMBER != right.getType()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[305]++;
          return TernaryValue.UNKNOWN;
  // Only eval if they are the same type
        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[306]++;}
        return compareAsNumbers(op, left, right);

      case Token.NAME:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[307]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[319]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((leftLiteral) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((undefinedRight) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[308]++;
          return TernaryValue.forBoolean(compareToUndefined(left, op));

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[309]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[320]++;
int CodeCoverConditionCoverageHelper_C83;

        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((rightLiteral) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[310]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[321]++;
          boolean undefinedLeft = (left.getString().equals("undefined"));
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[322]++;
int CodeCoverConditionCoverageHelper_C84;
          if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((undefinedLeft) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[312]++;
            return TernaryValue.forBoolean(compareToUndefined(right, op));

          } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[313]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[323]++;
int CodeCoverConditionCoverageHelper_C85;
          if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (32)) == 0 || true) &&
 ((leftLiteral) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((nullRight) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((isEqualityOp(op)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 3) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 3) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[314]++;
            return TernaryValue.forBoolean(compareToNull(left, op));

          } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[315]++;}

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[311]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[324]++;
int CodeCoverConditionCoverageHelper_C86;

        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((Token.NAME != right.getType()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[316]++;
          return TernaryValue.UNKNOWN;
  // Only eval if they are the same type
        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[317]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[325]++;
        String ln = left.getString();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[326]++;
        String rn = right.getString();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[327]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((ln.equals(rn)) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[318]++;
          return TernaryValue.UNKNOWN;
  // Not the same value name.
        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[319]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[328]++;

        switch (op) {
          // If we knew the named value wouldn't be NaN, it would be nice
          // to handle EQ,NE,LE,GE,SHEQ, and SHNE.
          case Token.LT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[320]++;
          case Token.GT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[321]++;
            return TernaryValue.FALSE; default : CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[322]++;
        }

        return TernaryValue.UNKNOWN;  // don't handle that op

      case Token.NEG:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[323]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[329]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((leftLiteral) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[324]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[330]++;
int CodeCoverConditionCoverageHelper_C89;
          if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((undefinedRight) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[326]++;
            return TernaryValue.forBoolean(compareToUndefined(left, op));

          } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[327]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[331]++;
int CodeCoverConditionCoverageHelper_C90;
          if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (8)) == 0 || true) &&
 ((nullRight) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((isEqualityOp(op)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[328]++;
            return TernaryValue.forBoolean(compareToNull(left, op));

          } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[329]++;}

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[325]++;}
        // Nothing else for now.
        return TernaryValue.UNKNOWN;

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[330]++;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[331]++;
      case Token.REGEXP:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[332]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[333]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[332]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((leftLiteral) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[334]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[333]++;
int CodeCoverConditionCoverageHelper_C92;
          if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((undefinedRight) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[336]++;
            return TernaryValue.forBoolean(compareToUndefined(left, op));

          } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[337]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[334]++;
int CodeCoverConditionCoverageHelper_C93;
          if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (8)) == 0 || true) &&
 ((nullRight) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((isEqualityOp(op)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[338]++;
            return TernaryValue.forBoolean(compareToNull(left, op));

          } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[339]++;}

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[335]++;}
        // ignore the rest for now.
        return TernaryValue.UNKNOWN;

      default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[340]++;
        // assert, this should cover all consts
        return TernaryValue.UNKNOWN;
    }
  }

  /** Returns whether two JS strings are equal. */
  private static TernaryValue areStringsEqual(String a, String b) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[335]++;
int CodeCoverConditionCoverageHelper_C94;
    // In JS, browsers parse \v differently. So do not consider strings
    // equal if one contains \v.
    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((a.indexOf('\u000B') != -1) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((b.indexOf('\u000B') != -1) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[341]++;
      return TernaryValue.UNKNOWN;

    } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[342]++;
      return a.equals(b) ? TernaryValue.TRUE : TernaryValue.FALSE;
    }
  }

  /**
   * @return Translate NOT expressions into TRUE or FALSE when possible.
   */
  private static int getNormalizedNodeType(Node n) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[336]++;
    int type = n.getType();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[337]++;
int CodeCoverConditionCoverageHelper_C95;
    if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((type == Token.NOT) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[343]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[338]++;
      TernaryValue value = NodeUtil.getPureBooleanValue(n);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[339]++;
      switch (value) {
        case TRUE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[345]++;
          return Token.TRUE;
        case FALSE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[346]++;
          return Token.FALSE;
        case UNKNOWN:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[347]++;
          return type; default : CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[348]++;
      }

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[344]++;}
    return type;
  }

  /**
   * The result of the comparison, or UNKNOWN if the
   * result could not be determined.
   */
  private static TernaryValue compareAsNumbers(int op, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[340]++;
    Double leftValue = NodeUtil.getNumberValue(left);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[341]++;
int CodeCoverConditionCoverageHelper_C96;
    if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((leftValue == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[349]++;
      return TernaryValue.UNKNOWN;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[350]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[342]++;
    Double rightValue = NodeUtil.getNumberValue(right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[343]++;
int CodeCoverConditionCoverageHelper_C97;
    if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((rightValue == null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[351]++;
      return TernaryValue.UNKNOWN;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[352]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[344]++;

    double lv = leftValue;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[345]++;
    double rv = rightValue;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[346]++;

    switch (op) {
      case Token.SHEQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[353]++;
      case Token.EQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[354]++;
        Preconditions.checkState(
            left.isNumber() && right.isNumber());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[347]++;
        return TernaryValue.forBoolean(lv == rv);
      case Token.SHNE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[355]++;
      case Token.NE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[356]++;
        Preconditions.checkState(
            left.isNumber() && right.isNumber());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[348]++;
        return TernaryValue.forBoolean(lv != rv);
      case Token.LE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[357]++;
        return TernaryValue.forBoolean(lv <= rv);
      case Token.LT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[358]++;
        return TernaryValue.forBoolean(lv <  rv);
      case Token.GE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[359]++;
        return TernaryValue.forBoolean(lv >= rv);
      case Token.GT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[360]++;
        return TernaryValue.forBoolean(lv >  rv);
      default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[361]++;
        return TernaryValue.UNKNOWN;  // don't handle that op
    }
  }

  /**
   * @param value The value to compare to "undefined"
   * @param op The boolean op to compare with
   * @return Whether the boolean op is true or false
   */
  private static boolean compareToUndefined(Node value, int op) {
    Preconditions.checkState(NodeUtil.isLiteralValue(value, true));
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[349]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[350]++;
    boolean valueUndefined = NodeUtil.isUndefined(value);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[351]++;
    boolean valueNull = (Token.NULL == value.getType());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[352]++;
    boolean equivalent = valueUndefined || valueNull;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[353]++;
    switch (op) {
      case Token.EQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[362]++;
        // undefined is only equal to null or an undefined value
        return equivalent;
      case Token.NE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[363]++;
        return !equivalent;
      case Token.SHEQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[364]++;
        return valueUndefined;
      case Token.SHNE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[365]++;
        return !valueUndefined;
      case Token.LT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[366]++;
      case Token.GT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[367]++;
      case Token.LE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[368]++;
      case Token.GE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[369]++;
        return false;
      default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[370]++;
        throw new IllegalStateException("unexpected.");
    }
  }

  private static boolean isEqualityOp(int op) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[354]++;
    switch (op) {
      case Token.EQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[371]++;
      case Token.NE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[372]++;
      case Token.SHEQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[373]++;
      case Token.SHNE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[374]++;
        return true; default : CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[375]++;
    }
    return false;
  }

  /**
   * @param value The value to compare to "null"
   * @param op The boolean op to compare with
   * @return Whether the boolean op is true or false
   */
  private static boolean compareToNull(Node value, int op) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[355]++;
    boolean valueUndefined = NodeUtil.isUndefined(value);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[356]++;
    boolean valueNull = (Token.NULL == value.getType());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[357]++;
    boolean equivalent = valueUndefined || valueNull;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[358]++;
    switch (op) {
      case Token.EQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[376]++;
        // undefined is only equal to null or an undefined value
        return equivalent;
      case Token.NE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[377]++;
        return !equivalent;
      case Token.SHEQ:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[378]++;
        return valueNull;
      case Token.SHNE:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[379]++;
        return !valueNull;
      default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[380]++;
        throw new IllegalStateException("unexpected.");
    }
  }

  /**
   * Try to fold away unnecessary object instantiation.
   * e.g. this[new String('eval')] -> this.eval
   */
  private Node tryFoldCtorCall(Node n) {
    Preconditions.checkArgument(n.isNew());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[359]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[360]++;
int CodeCoverConditionCoverageHelper_C98;

    // we can remove this for GETELEM calls (anywhere else?)
    if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((inForcedStringContext(n)) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[381]++;
      return tryFoldInForcedStringContext(n);

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[382]++;}
    return n;
  }

  /** Returns whether this node must be coerced to a string. */
  private boolean inForcedStringContext(Node n) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[361]++;
int CodeCoverConditionCoverageHelper_C99;
    if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (8)) == 0 || true) &&
 ((n.getParent().isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((n.getParent().getLastChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[383]++;
      return true;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[384]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[362]++;
int CodeCoverConditionCoverageHelper_C100;

    // we can fold in the case "" + new String("")
    if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((n.getParent().isAdd()) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[385]++;
      return true;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[386]++;}
    return false;
  }

  private Node tryFoldInForcedStringContext(Node n) {
    // For now, we only know how to fold ctors.
    Preconditions.checkArgument(n.isNew());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[363]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[364]++;

    Node objectType = n.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[365]++;
int CodeCoverConditionCoverageHelper_C101;
    if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((objectType.isName()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[387]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[388]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[366]++;
int CodeCoverConditionCoverageHelper_C102;

    if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((objectType.getString().equals("String")) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[389]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[367]++;
      Node value = objectType.getNext();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[368]++;
      String stringValue = null;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[369]++;
int CodeCoverConditionCoverageHelper_C103;
      if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[391]++;
        stringValue = "";
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[370]++;

      } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[392]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[371]++;
int CodeCoverConditionCoverageHelper_C104;
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[393]++;
          return n;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[394]++;}

        stringValue = NodeUtil.getStringValue(value);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[372]++;
      }
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[373]++;
int CodeCoverConditionCoverageHelper_C105;

      if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((stringValue == null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[395]++;
        return n;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[396]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[374]++;

      Node parent = n.getParent();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[375]++;
      Node newString = IR.string(stringValue);

      parent.replaceChild(n, newString);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[376]++;
      newString.copyInformationFrom(parent);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[377]++;
      reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[378]++;

      return newString;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[390]++;}
    return n;
  }

  /**
   * Try to fold array-element. e.g [1, 2, 3][10];
   */
  private Node tryFoldGetElem(Node n, Node left, Node right) {
    Preconditions.checkArgument(n.isGetElem());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[379]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[380]++;
int CodeCoverConditionCoverageHelper_C106;

    if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((left.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[397]++;
      return tryFoldObjectPropAccess(n, left, right);

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[398]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[381]++;
int CodeCoverConditionCoverageHelper_C107;

    if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((left.isArrayLit()) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[399]++;
      return tryFoldArrayAccess(n, left, right);

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[400]++;}
    return n;
  }

  /**
   * Try to fold array-length. e.g [1, 2, 3].length ==> 3, [x, y].length ==> 2
   */
  private Node tryFoldGetProp(Node n, Node left, Node right) {
    Preconditions.checkArgument(n.isGetProp());
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[382]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[383]++;
int CodeCoverConditionCoverageHelper_C108;

    if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((left.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[401]++;
      return tryFoldObjectPropAccess(n, left, right);

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[402]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[384]++;
int CodeCoverConditionCoverageHelper_C109;

    if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (8)) == 0 || true) &&
 ((right.isString()) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((right.getString().equals("length")) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[403]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[385]++;
      int knownLength = -1;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[386]++;
      switch (left.getType()) {
        case Token.ARRAYLIT:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[405]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[387]++;
int CodeCoverConditionCoverageHelper_C110;
          if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(left)) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[406]++;
            // Nope, can't fold this, without handling the side-effects.
            return n;

          } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[407]++;}
          knownLength = left.getChildCount();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[388]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[389]++;
          break;
        case Token.STRING:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[408]++;
          knownLength = left.getString().length();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[390]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[391]++;
          break;
        default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[409]++;
          // Not a foldable case, forget it.
          return n;
      }

      Preconditions.checkState(knownLength != -1);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[392]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[393]++;
      Node lengthNode = IR.number(knownLength);
      n.getParent().replaceChild(n, lengthNode);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[394]++;
      reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[395]++;

      return lengthNode;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[404]++;}

    return n;
  }

  private boolean isAssignmentTarget(Node n) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[396]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[397]++;
int CodeCoverConditionCoverageHelper_C111;
    if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C111 |= (128)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(parent)) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C111 |= (32)) == 0 || true) &&
 ((parent.getFirstChild() == n) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (16)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C111 |= (8)) == 0 || true) &&
 ((parent.isInc()) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((parent.isDec()) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 4) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 4) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[410]++;
      // If GETPROP/GETELEM is used as assignment target the object literal is
      // acting as a temporary we can't fold it here:
      //    "{a:x}.a += 1" is not "x += 1"
      return true;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[411]++;}
    return false;
  }

  private Node tryFoldArrayAccess(Node n, Node left, Node right) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[398]++;
int CodeCoverConditionCoverageHelper_C112;
    // If GETPROP/GETELEM is used as assignment target the array literal is
    // acting as a temporary we can't fold it here:
    //    "[][0] += 1"
    if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((isAssignmentTarget(n)) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[412]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[413]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[399]++;
int CodeCoverConditionCoverageHelper_C113;

    if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((right.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[414]++;
      // Sometimes people like to use complex expressions to index into
      // arrays, or strings to index into array methods.
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[415]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[400]++;

    double index = right.getDouble();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[401]++;
    int intIndex = (int) index;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[402]++;
int CodeCoverConditionCoverageHelper_C114;
    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((intIndex != index) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[416]++;
      report(INVALID_GETELEM_INDEX_ERROR, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[403]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[417]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[404]++;
int CodeCoverConditionCoverageHelper_C115;

    if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((intIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[418]++;
      report(INDEX_OUT_OF_BOUNDS_ERROR, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[405]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[419]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[406]++;

    Node current = left.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[407]++;
    Node elem = null;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[408]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[4]++;


int CodeCoverConditionCoverageHelper_C116;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[4]--;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[5]--;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[6]++;
}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[409]++;
int CodeCoverConditionCoverageHelper_C117;
      if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((i != intIndex) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[420]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[410]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(current)) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[422]++;
          return n;

        } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[423]++;}

      } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[421]++;
        elem = current;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[411]++;
      }

      current = current.getNext();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[412]++;
    }
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[413]++;
int CodeCoverConditionCoverageHelper_C119;

    if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((elem == null) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[424]++;
      report(INDEX_OUT_OF_BOUNDS_ERROR, right);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[414]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[425]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[415]++;
int CodeCoverConditionCoverageHelper_C120;

    if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((elem.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[426]++;
      elem = NodeUtil.newUndefinedNode(elem);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[416]++;

    } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[427]++;
      left.removeChild(elem);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[417]++;
    }

    // Replace the entire GETELEM with the value
    n.getParent().replaceChild(n, elem);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[418]++;
    reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[419]++;
    return elem;
  }

  private Node tryFoldObjectPropAccess(Node n, Node left, Node right) {
    Preconditions.checkArgument(NodeUtil.isGet(n));
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[420]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[421]++;
int CodeCoverConditionCoverageHelper_C121;

    if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C121 |= (8)) == 0 || true) &&
 ((left.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((right.isString()) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[428]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[429]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[422]++;
int CodeCoverConditionCoverageHelper_C122;

    if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((isAssignmentTarget(n)) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[430]++;
      // If GETPROP/GETELEM is used as assignment target the object literal is
      // acting as a temporary we can't fold it here:
      //    "{a:x}.a += 1" is not "x += 1"
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[431]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[423]++;

    // find the last definition in the object literal
    Node key = null;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[424]++;
    Node value = null;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[425]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[7]++;


int CodeCoverConditionCoverageHelper_C123;
    for (Node c = left.getFirstChild();(((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[7]--;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[8]--;
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.loops[9]++;
}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[426]++;
int CodeCoverConditionCoverageHelper_C124;
      if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((c.getString().equals(right.getString())) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[432]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[427]++;
        switch (c.getType()) {
          case Token.SETTER_DEF:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[434]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[428]++;
            continue;
          case Token.GETTER_DEF:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[435]++;
          case Token.STRING_KEY:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[436]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[429]++;
int CodeCoverConditionCoverageHelper_C125;
            if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(value)) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[437]++;
              // The previously found value had side-effects
              return n;

            } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[438]++;}
            key = c;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[430]++;
            value = key.getFirstChild();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[431]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[432]++;
            break;
          default:
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[439]++;
            throw new IllegalStateException();
        }

      } else {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[433]++;
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[433]++;
int CodeCoverConditionCoverageHelper_C126; if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(c.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[440]++;
        // We don't handle the side-effects here as they might need a temporary
        // or need to be reordered.
        return n;

      } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[441]++;}
}
    }
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[434]++;
int CodeCoverConditionCoverageHelper_C127;

    // Didn't find a definition of the name in the object literal, it might
    // be coming from the Object prototype
    if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[442]++;
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[443]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[435]++;
int CodeCoverConditionCoverageHelper_C128;

    if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (8)) == 0 || true) &&
 ((value.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((NodeUtil.referencesThis(value)) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 2) && false)) {
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[444]++;
      // 'this' may refer to the object we are trying to remove
      return n;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[445]++;}
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[436]++;

    Node replacement = value.detachFromParent();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[437]++;
int CodeCoverConditionCoverageHelper_C129;
    if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((key.isGetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)){
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[446]++;
      replacement = IR.call(replacement);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[438]++;
      replacement.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[439]++;

    } else {
  CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.branches[447]++;}

    n.getParent().replaceChild(n, replacement);
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[440]++;
    reportCodeChange();
CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9.statements[441]++;
    return n;
  }
}

class CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9 ());
  }
    public static long[] statements = new long[442];
    public static long[] branches = new long[448];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[130];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PeepholeFoldConstants.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,2,1,1,1,1,2,1,1,1,1,2,2,1,1,1,1,1,2,1,2,1,2,1,1,2,1,1,2,1,2,1,1,3,1,1,2,1,2,2,1,2,2,2,1,3,1,1,1,1,3,2,1,1,1,2,1,2,2,2,1,1,1,2,2,1,1,2,1,3,1,1,2,1,1,2,1,2,1,1,3,1,1,1,1,2,1,1,2,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,2,1,3,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,2,1};
    for (int i = 1; i <= 129; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$q0zy9qo6v3qhxy0p7chlrlfec1wpmujce5wuo9y9 () {
    super("com.google.javascript.jscomp.PeepholeFoldConstants.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 441; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 447; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 129; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PeepholeFoldConstants.java");
      for (int i = 1; i <= 441; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 447; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 129; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

