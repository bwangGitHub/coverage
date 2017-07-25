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

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.javascript.jscomp.CodingConvention.Bind;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.TernaryValue;

import java.util.regex.Pattern;

/**
 * A peephole optimization that minimizes code by simplifying conditional
 * expressions, replacing IFs with HOOKs, replacing object constructors
 * with literals, and simplifying returns.
 *
 */
class PeepholeSubstituteAlternateSyntax
  extends AbstractPeepholeOptimization {
  static {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.ping();
  }


  private static final int AND_PRECEDENCE = NodeUtil.precedence(Token.AND);
  static {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[1]++;
  }
  private static final int OR_PRECEDENCE = NodeUtil.precedence(Token.OR);
  static {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[2]++;
  }
  private static final int NOT_PRECEDENCE = NodeUtil.precedence(Token.NOT);
  static {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[3]++;
  }
  private static final CodeGenerator REGEXP_ESCAPER =
      CodeGenerator.forCostEstimation(
          null /* blow up if we try to produce code */);
  static {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[4]++;
  }

  private final boolean late;

  private final int STRING_SPLIT_OVERHEAD = ".split('.')".length();
  {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[5]++;
  }

  static final DiagnosticType INVALID_REGULAR_EXPRESSION_FLAGS =
    DiagnosticType.warning(
        "JSC_INVALID_REGULAR_EXPRESSION_FLAGS",
        "Invalid flags to RegExp constructor: {0}");
  static {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[6]++;
  }

  static final Predicate<Node> DONT_TRAVERSE_FUNCTIONS_PREDICATE
      = new Predicate<Node>() {
    @Override
    public boolean apply(Node input) {
      return !input.isFunction();
    }
  };
  static {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[7]++;
  }

  /**
   * @param late When late is false, this mean we are currently running before
   * most of the other optimizations. In this case we would avoid optimizations
   * that would make the code harder to analyze (such as using string splitting,
   * merging statements with commas, etc). When this is true, we would
   * do anything to minimize for size.
   */
  PeepholeSubstituteAlternateSyntax(boolean late) {
    this.late = late;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[8]++;
  }

  /**
   * Tries apply our various peephole minimizations on the passed in node.
   */
  @Override
  @SuppressWarnings("fallthrough")
  public Node optimizeSubtree(Node node) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[9]++;
    switch(node.getType()) {
      case Token.RETURN:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[1]++; {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[10]++;
        Node result = tryRemoveRedundantExit(node);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((result != node) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[2]++;
          return result;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[3]++;}
        result = tryReplaceExitWithBreak(node);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[12]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((result != node) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[4]++;
          return result;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[5]++;}
        return tryReduceReturn(node);
      }

      case Token.THROW:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[6]++; {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[14]++;
        Node result = tryRemoveRedundantExit(node);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((result != node) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[7]++;
          return result;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[8]++;}
        return tryReplaceExitWithBreak(node);
      }

      // TODO(johnlenz): Maybe remove redundant BREAK and CONTINUE. Overlaps
      // with MinimizeExitPoints.

      case Token.NOT:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[9]++;
        tryMinimizeCondition(node.getFirstChild());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[16]++;
        return tryMinimizeNot(node);

      case Token.IF:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[10]++;
        tryMinimizeCondition(node.getFirstChild());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[17]++;
        return tryMinimizeIf(node);

      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[11]++;
        tryMinimizeCondition(node.getFirstChild());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[18]++;
        return node;

      case Token.HOOK:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[12]++;
        tryMinimizeCondition(node.getFirstChild());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[19]++;
        return node;

      case Token.WHILE:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[13]++;
      case Token.DO:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[14]++;
        tryMinimizeCondition(NodeUtil.getConditionExpression(node));
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[20]++;
        return node;

      case Token.FOR:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[15]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(node)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[16]++;
          tryJoinForCondition(node);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[22]++;
          tryMinimizeCondition(NodeUtil.getConditionExpression(node));
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[23]++;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[17]++;}
        return node;

      case Token.TRUE:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[18]++;
      case Token.FALSE:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[19]++;
        return reduceTrueFalse(node);

      case Token.NEW:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[20]++;
        node = tryFoldStandardConstructors(node);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[24]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((node.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[21]++;
          return node;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[22]++;}
        // Fall through on purpose because tryFoldStandardConstructors() may
        // convert a NEW node into a CALL node
      case Token.CALL:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[23]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[26]++;
        Node result =  tryFoldLiteralConstructor(node);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((result == node) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[24]++;
          result = tryFoldSimpleFunctionCall(node);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[28]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((result == node) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[26]++;
            result = tryFoldImmediateCallToBoundFunction(node);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[30]++;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[27]++;}

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[25]++;}
        return result;

      case Token.COMMA:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[28]++;
        return trySplitComma(node);

      case Token.NAME:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[29]++;
        return tryReplaceUndefined(node);

      case Token.BLOCK:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[30]++;
        return tryReplaceIf(node);

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[31]++;
        return tryMinimizeArrayLiteral(node);

      default:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[32]++;
        return node; //Nothing changed
    }
  }

  private void tryJoinForCondition(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((late) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[33]++;
      return;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[34]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[32]++;

    Node block = n.getLastChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[33]++;
    Node maybeIf = block.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((maybeIf != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((maybeIf.isIf()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[35]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[35]++;
      Node maybeBreak = maybeIf.getChildAtIndex(1).getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((maybeBreak != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((maybeBreak.isBreak()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((maybeBreak.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[37]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;

        // Preserve the IF ELSE expression is there is one.
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((maybeIf.getChildCount() == 3) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[39]++;
          block.replaceChild(maybeIf,
              maybeIf.getLastChild().detachFromParent());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[38]++;

        } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[40]++;
          block.removeFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[39]++;
        }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[40]++;

        Node ifCondition = maybeIf.removeFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[41]++;
        Node fixedIfCondition = IR.not(ifCondition)
            .srcref(ifCondition);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[42]++;

        // OK, join the IF expression with the FOR expression
        Node forCondition = NodeUtil.getConditionExpression(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[43]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((forCondition.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[41]++;
          n.replaceChild(forCondition, fixedIfCondition);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[44]++;

        } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[42]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[45]++;
          Node replacement = new Node(Token.AND);
          n.replaceChild(forCondition, replacement);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[46]++;
          replacement.addChildToBack(forCondition);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[47]++;
          replacement.addChildToBack(fixedIfCondition);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[48]++;
        }

        reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[49]++;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[38]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[36]++;}
  }

  private Node tryFoldSimpleFunctionCall(Node n) {
    Preconditions.checkState(n.isCall());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[50]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[51]++;
    Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((callTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((callTarget.isName()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((callTarget.getString().equals("String")) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[43]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[53]++;
      // Fold String(a) to '' + (a) on immutable literals,
      // which allows further optimizations
      //
      // We can't do this in the general case, because String(a) has
      // slightly different semantics than '' + (a). See
      // http://code.google.com/p/closure-compiler/issues/detail?id=759
      Node value = callTarget.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((value.getNext() == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[45]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[55]++;
        Node addition = IR.add(
            IR.string("").srcref(callTarget),
            value.detachFromParent());
        n.getParent().replaceChild(n, addition);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[56]++;
        reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[57]++;
        return addition;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[46]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[44]++;}
    return n;
  }

  private Node tryFoldImmediateCallToBoundFunction(Node n) {
    // Rewriting "(fn.bind(a,b))()" to "fn.call(a,b)" makes it inlinable
    Preconditions.checkState(n.isCall());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[58]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[59]++;
    Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[60]++;
    Bind bind = getCodingConvention().describeFunctionBind(callTarget, false);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[61]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((bind != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[47]++;
      // replace the call target
      bind.target.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[62]++;
      n.replaceChild(callTarget, bind.target);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[63]++;
      callTarget = bind.target;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[64]++;

      // push the parameters
      addParameterAfter(bind.parameters, callTarget);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[65]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[66]++;
int CodeCoverConditionCoverageHelper_C16;

      // add the this value before the parameters if necessary
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((bind.thisValue != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((NodeUtil.isUndefined(bind.thisValue)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[49]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[67]++;
        // rewrite from "fn(a, b)" to "fn.call(thisValue, a, b)"
        Node newCallTarget = IR.getprop(
            callTarget.cloneTree(),
            IR.string("call").srcref(callTarget));
        n.replaceChild(callTarget, newCallTarget);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[68]++;
        n.addChildAfter(bind.thisValue.cloneTree(), newCallTarget);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[69]++;
        n.putBooleanProp(Node.FREE_CALL, false);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[70]++;

      } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[50]++;
        n.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[71]++;
      }
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[72]++;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[48]++;}
    return n;
  }

  private void addParameterAfter(Node parameterList, Node after) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[73]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((parameterList != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[51]++;
      // push the last parameter to the head of the list first.
      addParameterAfter(parameterList.getNext(), after);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[74]++;
      after.getParent().addChildAfter(parameterList.cloneTree(), after);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[75]++;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[52]++;}
  }

  private Node trySplitComma(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[76]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((late) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[53]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[54]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[77]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[78]++;
    Node left = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[79]++;
    Node right = n.getLastChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[80]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((parent.getParent().isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[55]++;
      // split comma
      n.detachChildren();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[81]++;
      // Replace the original expression with the left operand.
      parent.replaceChild(n, left);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[82]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[83]++;
      // Add the right expression afterward.
      Node newStatement = IR.exprResult(right);
      newStatement.copyInformationFrom(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[84]++;

      //This modifies outside the subtree, which is not
      //desirable in a peephole optimization.
      parent.getParent().addChildAfter(newStatement, parent);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[85]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[86]++;
      return left;

    } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[56]++;
      return n;
    }
  }

  /**
   * Use "return x?1:2;" in place of "if(x)return 1;return 2;"
   */
  private Node tryReplaceIf(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[87]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[1]++;


int CodeCoverConditionCoverageHelper_C20;

    for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); child = child.getNext()){
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[1]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[2]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[3]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[88]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((child.isIf()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)){
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[57]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[89]++;
        Node cond = child.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[90]++;
        Node thenBranch = cond.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[91]++;
        Node elseBranch = thenBranch.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[92]++;
        Node nextNode = child.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[93]++;
int CodeCoverConditionCoverageHelper_C22;

        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (128)) == 0 || true) &&
 ((nextNode != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (32)) == 0 || true) &&
 ((elseBranch == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((isReturnBlock(thenBranch)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((nextNode.isIf()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 4) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 4) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[59]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[94]++;
          Node nextCond = nextNode.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[95]++;
          Node nextThen = nextCond.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[96]++;
          Node nextElse = nextThen.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[97]++;
int CodeCoverConditionCoverageHelper_C23;
          if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((thenBranch.isEquivalentToTyped(nextThen)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[61]++;
            // Transform
            //   if (x) return 1; if (y) return 1;
            // to
            //   if (x||y) return 1;
            child.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[98]++;
            child.detachChildren();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[99]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[100]++;
            Node newCond = new Node(Token.OR, cond);
            nextNode.replaceChild(nextCond, newCond);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[101]++;
            newCond.addChildToBack(nextCond);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[102]++;
            reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[103]++;

          } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[62]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[104]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((nextElse != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((thenBranch.isEquivalentToTyped(nextElse)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[63]++;
            // Transform
            //   if (x) return 1; if (y) foo() else return 1;
            // to
            //   if (!x&&y) foo() else return 1;
            child.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[105]++;
            child.detachChildren();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[106]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[107]++;
            Node newCond = new Node(Token.AND,
                IR.not(cond).srcref(cond));
            nextNode.replaceChild(nextCond, newCond);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[108]++;
            newCond.addChildToBack(nextCond);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[109]++;
            reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[110]++;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[64]++;}
}

        } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[60]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[111]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (128)) == 0 || true) &&
 ((nextNode != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((elseBranch == null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((isReturnBlock(thenBranch)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isReturnExpression(nextNode)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 4) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 4) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[65]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[112]++;
          Node thenExpr = null;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[113]++;
int CodeCoverConditionCoverageHelper_C26;
          // if(x)return; return 1 -> return x?void 0:1
          if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((isReturnExpressBlock(thenBranch)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[67]++;
            thenExpr = getBlockReturnExpression(thenBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[114]++;
            thenExpr.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[115]++;

          } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[68]++;
            thenExpr = NodeUtil.newUndefinedNode(child);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[116]++;
          }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[117]++;

          Node elseExpr = nextNode.getFirstChild();

          cond.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[118]++;
          elseExpr.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[119]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[120]++;

          Node returnNode = IR.returnNode(
                                IR.hook(cond, thenExpr, elseExpr)
                                    .srcref(child));
          n.replaceChild(child, returnNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[121]++;
          n.removeChild(nextNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[122]++;
          reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[123]++;

        } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[66]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[124]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((elseBranch != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((statementMustExitParent(thenBranch)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[69]++;
          child.removeChild(elseBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[125]++;
          n.addChildAfter(elseBranch, child);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[126]++;
          reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[127]++;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[70]++;}
}
}

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[58]++;}
    }
    return n;
  }

  private boolean statementMustExitParent(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[128]++;
    switch (n.getType()) {
      case Token.THROW:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[71]++;
      case Token.RETURN:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[72]++;
        return true;
      case Token.BLOCK:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[73]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[129]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[74]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[130]++;
          Node child = n.getLastChild();
          return statementMustExitParent(child);

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[75]++;}
        return false;
      // TODO(johnlenz): handle TRY/FINALLY
      case Token.FUNCTION:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[76]++;
      default:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[77]++;
        return false;
    }
  }

  /**
   * Use "void 0" in place of "undefined"
   */
  private Node tryReplaceUndefined(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[131]++;
int CodeCoverConditionCoverageHelper_C29;
    // TODO(johnlenz): consider doing this as a normalization.
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (32)) == 0 || true) &&
 ((isASTNormalized()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((NodeUtil.isUndefined(n)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((NodeUtil.isLValue(n)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[78]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[132]++;
      Node replacement = NodeUtil.newUndefinedNode(n);
      n.getParent().replaceChild(n, replacement);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[133]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[134]++;
      return replacement;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[79]++;}
    return n;
  }

  /**
   * Reduce "return undefined" or "return void 0" to simply "return".
   *
   * @return The original node, maybe simplified.
   */
  private Node tryReduceReturn(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[135]++;
    Node result = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[136]++;
int CodeCoverConditionCoverageHelper_C30;

    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[80]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[137]++;
      switch (result.getType()) {
        case Token.VOID:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[82]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[138]++;
          Node operand = result.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[139]++;
int CodeCoverConditionCoverageHelper_C31;
          if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(operand)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[83]++;
            n.removeFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[140]++;
            reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[141]++;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[84]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[142]++;
          break;
        case Token.NAME:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[85]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[143]++;
          String name = result.getString();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[144]++;
int CodeCoverConditionCoverageHelper_C32;
          if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((name.equals("undefined")) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[86]++;
            n.removeFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[145]++;
            reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[146]++;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[87]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[147]++;
          break; default : CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[88]++;
      }

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[81]++;}

    return n;
  }

  /**
   * Replace duplicate exits in control structures.  If the node following
   * the exit node expression has the same effect as exit node, the node can
   * be replaced or removed.
   * For example:
   *   "while (a) {return f()} return f();" ==> "while (a) {break} return f();"
   *   "while (a) {throw 'ow'} throw 'ow';" ==> "while (a) {break} throw 'ow';"
   *
   * @param n An follow control exit expression (a THROW or RETURN node)
   * @return The replacement for n, or the original if no change was made.
   */
  private Node tryReplaceExitWithBreak(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[148]++;
    Node result = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[149]++;

    // Find the enclosing control structure, if any, that a "break" would exit
    // from.
    Node breakTarget = n;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[150]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[4]++;


int CodeCoverConditionCoverageHelper_C33;
    for (;(((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((ControlFlowAnalysis.isBreakTarget(breakTarget, null /* no label */)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false);
        breakTarget = breakTarget.getParent()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[4]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[5]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[6]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[151]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((breakTarget.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((breakTarget.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[89]++;
        // No break target.
        return n;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[90]++;}
    }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[152]++;

    Node follow = ControlFlowAnalysis.computeFollowNode(breakTarget);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[153]++;

    // Skip pass all the finally blocks because both the break and return will
    // also trigger all the finally blocks. However, the order of execution is
    // slightly changed. Consider:
    //
    // return a() -> finally { b() } -> return a()
    //
    // which would call a() first. However, changing the first return to a
    // break will result in calling b().

    Node prefinallyFollows = follow;
    follow = skipFinallyNodes(follow);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[154]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[155]++;
int CodeCoverConditionCoverageHelper_C35;

    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((prefinallyFollows != follow) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[91]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[156]++;
int CodeCoverConditionCoverageHelper_C36;
      // There were finally clauses
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((isPure(result)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[93]++;
        // Can't defer the exit
        return n;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[94]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[92]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[157]++;
int CodeCoverConditionCoverageHelper_C37;

    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (32)) == 0 || true) &&
 ((follow == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((n.isThrow()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[95]++;
      // Can't complete remove a throw here or a return with a result.
      return n;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[96]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[158]++;
int CodeCoverConditionCoverageHelper_C38;

    // When follow is null, this mean the follow of a break target is the
    // end of a function. This means a break is same as return.
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((follow == null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((areMatchingExits(n, follow)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[97]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[159]++;
      Node replacement = IR.breakNode();
      n.getParent().replaceChild(n, replacement);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[160]++;
      this.reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[161]++;
      return replacement;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[98]++;}

    return n;
  }

  /**
   * Remove duplicate exits.  If the node following the exit node expression
   * has the same effect as exit node, the node can be removed.
   * For example:
   *   "if (a) {return f()} return f();" ==> "if (a) {} return f();"
   *   "if (a) {throw 'ow'} throw 'ow';" ==> "if (a) {} throw 'ow';"
   *
   * @param n An follow control exit expression (a THROW or RETURN node)
   * @return The replacement for n, or the original if no change was made.
   */
  private Node tryRemoveRedundantExit(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[162]++;
    Node exitExpr = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[163]++;

    Node follow = ControlFlowAnalysis.computeFollowNode(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[164]++;

    // Skip pass all the finally blocks because both the fall through and return
    // will also trigger all the finally blocks.
    Node prefinallyFollows = follow;
    follow = skipFinallyNodes(follow);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[165]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[166]++;
int CodeCoverConditionCoverageHelper_C39;
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((prefinallyFollows != follow) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[99]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[167]++;
int CodeCoverConditionCoverageHelper_C40;
      // There were finally clauses
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((isPure(exitExpr)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[101]++;
        // Can't replace the return
        return n;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[102]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[100]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[168]++;
int CodeCoverConditionCoverageHelper_C41;

    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (32)) == 0 || true) &&
 ((follow == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((n.isThrow()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((exitExpr != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[103]++;
      // Can't complete remove a throw here or a return with a result.
      return n;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[104]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[169]++;
int CodeCoverConditionCoverageHelper_C42;

    // When follow is null, this mean the follow of a break target is the
    // end of a function. This means a break is same as return.
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((follow == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((areMatchingExits(n, follow)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[105]++;
      n.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[170]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[171]++;
      return null;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[106]++;}

    return n;
  }

  /**
   * @return Whether the expression does not produces and can not be affected
   * by side-effects.
   */
  boolean isPure(Node n) {
    return n == null
        || (!NodeUtil.canBeSideEffected(n)
            && !mayHaveSideEffects(n));
  }

  /**
   * @return n or the node following any following finally nodes.
   */
  Node skipFinallyNodes(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[172]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[7]++;


int CodeCoverConditionCoverageHelper_C43;
    while ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (8)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((NodeUtil.isTryFinallyNode(n.getParent(), n)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 2) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[7]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[8]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[9]++;
}
      n = ControlFlowAnalysis.computeFollowNode(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[173]++;
    }
    return n;
  }

  /**
   * Check whether one exit can be replaced with another. Verify:
   * 1) They are identical expressions
   * 2) If an exception is possible that the statements, the original
   * and the potential replacement are in the same exception handler.
   */
  boolean areMatchingExits(Node nodeThis, Node nodeThat) {
    return nodeThis.isEquivalentTo(nodeThat)
        && (!isExceptionPossible(nodeThis)
            || getExceptionHandler(nodeThis) == getExceptionHandler(nodeThat));
  }

  boolean isExceptionPossible(Node n) {
    // TODO(johnlenz): maybe use ControlFlowAnalysis.mayThrowException?
    Preconditions.checkState(n.isReturn()
        || n.isThrow());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[174]++;
    return n.isThrow()
        || (n.hasChildren()
            && !NodeUtil.isLiteralValue(n.getLastChild(), true));
  }

  Node getExceptionHandler(Node n) {
    return ControlFlowAnalysis.getExceptionHandler(n);
  }

  /**
   * Try to minimize NOT nodes such as !(x==y).
   *
   * Returns the replacement for n or the original if no change was made
   */
  private Node tryMinimizeNot(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[175]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[176]++;

    Node notChild = n.getFirstChild();
    // negative operator of the current one : == -> != for instance.
    int complementOperator;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[177]++;
    switch (notChild.getType()) {
      case Token.EQ:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[107]++;
        complementOperator = Token.NE;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[178]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[179]++;
        break;
      case Token.NE:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[108]++;
        complementOperator = Token.EQ;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[180]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[181]++;
        break;
      case Token.SHEQ:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[109]++;
        complementOperator = Token.SHNE;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[182]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[183]++;
        break;
      case Token.SHNE:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[110]++;
        complementOperator = Token.SHEQ;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[184]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[185]++;
        break;
      // GT, GE, LT, LE are not handled in this because !(x<NaN) != x>=NaN.
      default:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[111]++;
        return n;
    }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[186]++;
    Node newOperator = n.removeFirstChild();
    newOperator.setType(complementOperator);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[187]++;
    parent.replaceChild(n, newOperator);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[188]++;
    reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[189]++;
    return newOperator;
  }

  /**
   * Try turning IF nodes into smaller HOOKs
   *
   * Returns the replacement for n or the original if no replacement was
   * necessary.
   */
  private Node tryMinimizeIf(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[190]++;

    Node parent = n.getParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[191]++;

    Node cond = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[192]++;
int CodeCoverConditionCoverageHelper_C44;

    /* If the condition is a literal, we'll let other
     * optimizations try to remove useless code.
     */
    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(cond, true)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[112]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[113]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[193]++;

    Node thenBranch = cond.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[194]++;
    Node elseBranch = thenBranch.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[195]++;
int CodeCoverConditionCoverageHelper_C45;

    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((elseBranch == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[114]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[196]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((isFoldableExpressBlock(thenBranch)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[116]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[197]++;
        Node expr = getBlockExpression(thenBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[198]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((late) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((isPropertyAssignmentInExpression(expr)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[118]++;
          // Keep opportunities for CollapseProperties such as
          // a.longIdentifier || a.longIdentifier = ... -> var a = ...;
          // until CollapseProperties has been run.
          return n;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[119]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[199]++;
int CodeCoverConditionCoverageHelper_C48;

        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((cond.isNot()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[120]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[200]++;
int CodeCoverConditionCoverageHelper_C49;
          // if(!x)bar(); -> x||bar();
          if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((isLowerPrecedenceInExpression(cond, OR_PRECEDENCE)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((isLowerPrecedenceInExpression(expr.getFirstChild(),
                  OR_PRECEDENCE)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[122]++;
            // It's not okay to add two sets of parentheses.
            return n;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[123]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[201]++;

          Node or = IR.or(
              cond.removeFirstChild(),
              expr.removeFirstChild()).srcref(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[202]++;
          Node newExpr = NodeUtil.newExpr(or);
          parent.replaceChild(n, newExpr);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[203]++;
          reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[204]++;

          return newExpr;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[121]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[205]++;
int CodeCoverConditionCoverageHelper_C50;

        // if(x)foo(); -> x&&foo();
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((isLowerPrecedenceInExpression(cond, AND_PRECEDENCE)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((isLowerPrecedenceInExpression(expr.getFirstChild(),
                AND_PRECEDENCE)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[124]++;
          // One additional set of parentheses is worth the change even if
          // there is no immediate code size win. However, two extra pair of
          // {}, we would have to think twice. (unless we know for sure the
          // we can further optimize its parent.
          return n;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[125]++;}

        n.removeChild(cond);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[206]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[207]++;
        Node and = IR.and(cond, expr.removeFirstChild()).srcref(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[208]++;
        Node newExpr = NodeUtil.newExpr(and);
        parent.replaceChild(n, newExpr);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[209]++;
        reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[210]++;

        return newExpr;

      } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[117]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[211]++;
int CodeCoverConditionCoverageHelper_C51;

        // Try to combine two IF-ELSE
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(thenBranch)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((thenBranch.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[126]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[212]++;
          Node innerIf = thenBranch.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[213]++;
int CodeCoverConditionCoverageHelper_C52;

          if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((innerIf.isIf()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[128]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[214]++;
            Node innerCond = innerIf.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[215]++;
            Node innerThenBranch = innerCond.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[216]++;
            Node innerElseBranch = innerThenBranch.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[217]++;
int CodeCoverConditionCoverageHelper_C53;

            if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (32)) == 0 || true) &&
 ((innerElseBranch == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (16)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((isLowerPrecedenceInExpression(cond, AND_PRECEDENCE)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((isLowerPrecedenceInExpression(innerCond, AND_PRECEDENCE)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[130]++;
              n.detachChildren();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[218]++;
              n.addChildToBack(
                  IR.and(
                      cond,
                      innerCond.detachFromParent())
                      .srcref(cond));
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[219]++;
              n.addChildrenToBack(innerThenBranch.detachFromParent());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[220]++;
              reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[221]++;
              // Not worth trying to fold the current IF-ELSE into && because
              // the inner IF-ELSE wasn't able to be folded into && anyways.
              return n;

            } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[131]++;}

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[129]++;}

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[127]++;}
      }

      return n;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[115]++;}

    /* TODO(dcc) This modifies the siblings of n, which is undesirable for a
     * peephole optimization. This should probably get moved to another pass.
     */
    tryRemoveRepeatedStatements(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[222]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[223]++;
int CodeCoverConditionCoverageHelper_C54;

    // if(!x)foo();else bar(); -> if(x)bar();else foo();
    // An additional set of curly braces isn't worth it.
    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((cond.isNot()) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((consumesDanglingElse(elseBranch)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[132]++;
      n.replaceChild(cond, cond.removeFirstChild());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[224]++;
      n.removeChild(thenBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[225]++;
      n.addChildToBack(thenBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[226]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[227]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[133]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[228]++;
int CodeCoverConditionCoverageHelper_C55;

    // if(x)return 1;else return 2; -> return x?1:2;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((isReturnExpressBlock(thenBranch)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((isReturnExpressBlock(elseBranch)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[134]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[229]++;
      Node thenExpr = getBlockReturnExpression(thenBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[230]++;
      Node elseExpr = getBlockReturnExpression(elseBranch);
      n.removeChild(cond);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[231]++;
      thenExpr.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[232]++;
      elseExpr.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[233]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[234]++;

      // note - we ignore any cases with "return;", technically this
      // can be converted to "return undefined;" or some variant, but
      // that does not help code size.
      Node returnNode = IR.returnNode(
                            IR.hook(cond, thenExpr, elseExpr)
                                .srcref(n));
      parent.replaceChild(n, returnNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[235]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[236]++;
      return returnNode;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[135]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[237]++;

    boolean thenBranchIsExpressionBlock = isFoldableExpressBlock(thenBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[238]++;
    boolean elseBranchIsExpressionBlock = isFoldableExpressBlock(elseBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[239]++;
int CodeCoverConditionCoverageHelper_C56;

    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((thenBranchIsExpressionBlock) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((elseBranchIsExpressionBlock) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[136]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[240]++;
      Node thenOp = getBlockExpression(thenBranch).getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[241]++;
      Node elseOp = getBlockExpression(elseBranch).getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[242]++;
int CodeCoverConditionCoverageHelper_C57;
      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((thenOp.getType() == elseOp.getType()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[138]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[243]++;
int CodeCoverConditionCoverageHelper_C58;
        // if(x)a=1;else a=2; -> a=x?1:2;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(thenOp)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[140]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[244]++;
          Node lhs = thenOp.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[245]++;
int CodeCoverConditionCoverageHelper_C59;
          if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((areNodesEqualForInlining(lhs, elseOp.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((mayEffectMutableState(lhs)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[142]++;

            n.removeChild(cond);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[246]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[247]++;
            Node assignName = thenOp.removeFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[248]++;
            Node thenExpr = thenOp.removeFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[249]++;
            Node elseExpr = elseOp.getLastChild();
            elseOp.removeChild(elseExpr);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[250]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[251]++;

            Node hookNode = IR.hook(cond, thenExpr, elseExpr).srcref(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[252]++;
            Node assign = new Node(thenOp.getType(), assignName, hookNode)
                              .srcref(thenOp);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[253]++;
            Node expr = NodeUtil.newExpr(assign);
            parent.replaceChild(n, expr);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[254]++;
            reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[255]++;

            return expr;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[143]++;}

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[141]++;}

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[139]++;}
      // if(x)foo();else bar(); -> x?foo():bar()
      n.removeChild(cond);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[256]++;
      thenOp.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[257]++;
      elseOp.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[258]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[259]++;
      Node expr = IR.exprResult(
          IR.hook(cond, thenOp, elseOp).srcref(n));
      parent.replaceChild(n, expr);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[260]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[261]++;
      return expr;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[137]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[262]++;

    boolean thenBranchIsVar = isVarBlock(thenBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[263]++;
    boolean elseBranchIsVar = isVarBlock(elseBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[264]++;
int CodeCoverConditionCoverageHelper_C60;

    // if(x)var y=1;else y=2  ->  var y=x?1:2
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (32)) == 0 || true) &&
 ((thenBranchIsVar) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C60 |= (8)) == 0 || true) &&
 ((elseBranchIsExpressionBlock) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((getBlockExpression(elseBranch).getFirstChild().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[144]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[265]++;

      Node var = getBlockVar(thenBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[266]++;
      Node elseAssign = getBlockExpression(elseBranch).getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[267]++;

      Node name1 = var.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[268]++;
      Node maybeName2 = elseAssign.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[269]++;
int CodeCoverConditionCoverageHelper_C61;

      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (32)) == 0 || true) &&
 ((name1.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((maybeName2.isName()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((name1.getString().equals(maybeName2.getString())) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[146]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[270]++;
        Node thenExpr = name1.removeChildren();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[271]++;
        Node elseExpr = elseAssign.getLastChild().detachFromParent();
        cond.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[272]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[273]++;
        Node hookNode = IR.hook(cond, thenExpr, elseExpr)
                            .srcref(n);
        var.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[274]++;
        name1.addChildrenToBack(hookNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[275]++;
        parent.replaceChild(n, var);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[276]++;
        reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[277]++;
        return var;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[147]++;}


    // if(x)y=1;else var y=2  ->  var y=x?1:2
    } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[145]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[278]++;
int CodeCoverConditionCoverageHelper_C62; if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (32)) == 0 || true) &&
 ((elseBranchIsVar) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((thenBranchIsExpressionBlock) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((getBlockExpression(thenBranch).getFirstChild().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[148]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[279]++;

      Node var = getBlockVar(elseBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[280]++;
      Node thenAssign = getBlockExpression(thenBranch).getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[281]++;

      Node maybeName1 = thenAssign.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[282]++;
      Node name2 = var.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[283]++;
int CodeCoverConditionCoverageHelper_C63;

      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (32)) == 0 || true) &&
 ((name2.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((maybeName1.isName()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((maybeName1.getString().equals(name2.getString())) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[150]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[284]++;
        Node thenExpr = thenAssign.getLastChild().detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[285]++;
        Node elseExpr = name2.removeChildren();
        cond.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[286]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[287]++;
        Node hookNode = IR.hook(cond, thenExpr, elseExpr)
                            .srcref(n);
        var.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[288]++;
        name2.addChildrenToBack(hookNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[289]++;
        parent.replaceChild(n, var);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[290]++;
        reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[291]++;

        return var;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[151]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[149]++;}
}

    return n;
  }

  /**
   * Try to remove duplicate statements from IF blocks. For example:
   *
   * if (a) {
   *   x = 1;
   *   return true;
   * } else {
   *   x = 2;
   *   return true;
   * }
   *
   * becomes:
   *
   * if (a) {
   *   x = 1;
   * } else {
   *   x = 2;
   * }
   * return true;
   *
   * @param n The IF node to examine.
   */
  private void tryRemoveRepeatedStatements(Node n) {
    Preconditions.checkState(n.isIf());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[292]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[293]++;

    Node parent = n.getParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[294]++;
int CodeCoverConditionCoverageHelper_C64;
    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(parent)) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[152]++;
      // If the immediate parent is something like a label, we
      // can't move the statement, so bail.
      return;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[153]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[295]++;

    Node cond = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[296]++;
    Node trueBranch = cond.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[297]++;
    Node falseBranch = trueBranch.getNext();
    Preconditions.checkNotNull(trueBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[298]++;
    Preconditions.checkNotNull(falseBranch);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[299]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[300]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[10]++;



    while (true) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[10]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[11]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[12]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[301]++;
      Node lastTrue = trueBranch.getLastChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[302]++;
      Node lastFalse = falseBranch.getLastChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[303]++;
int CodeCoverConditionCoverageHelper_C66;
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (32)) == 0 || true) &&
 ((lastTrue == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((lastFalse == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((areNodesEqualForInlining(lastTrue, lastFalse)) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[154]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[304]++;
        break;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[155]++;}
      lastTrue.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[305]++;
      lastFalse.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[306]++;
      parent.addChildAfter(lastTrue, n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[307]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[308]++;
    }
  }

  /**
   * @return Whether the node is a block with a single statement that is
   *     an expression.
   */
  private boolean isFoldableExpressBlock(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[309]++;
int CodeCoverConditionCoverageHelper_C67;
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[156]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[310]++;
int CodeCoverConditionCoverageHelper_C68;
      if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((n.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[158]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[311]++;
        Node maybeExpr = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[312]++;
int CodeCoverConditionCoverageHelper_C69;
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((maybeExpr.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[160]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[313]++;
int CodeCoverConditionCoverageHelper_C70;
          // IE has a bug where event handlers behave differently when
          // their return value is used vs. when their return value is in
          // an EXPR_RESULT. It's pretty freaking weird. See:
          // http://code.google.com/p/closure-compiler/issues/detail?id=291
          // We try to detect this case, and not fold EXPR_RESULTs
          // into other expressions.
          if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((maybeExpr.getFirstChild().isCall()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[162]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[314]++;
            Node calledFn = maybeExpr.getFirstChild().getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[315]++;
int CodeCoverConditionCoverageHelper_C71;

            // We only have to worry about methods with an implicit 'this'
            // param, or this doesn't happen.
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((calledFn.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[164]++;
              return false;

            } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[165]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[316]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((calledFn.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((calledFn.getLastChild().getString().startsWith("on")) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[166]++;
              return false;

            } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[167]++;}
}

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[163]++;}

          return true;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[161]++;}
        return false;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[159]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[157]++;}

    return false;
  }

  /**
   * @return The expression node.
   */
  private Node getBlockExpression(Node n) {
    Preconditions.checkState(isFoldableExpressBlock(n));
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[317]++;
    return n.getFirstChild();
  }

  /**
   * @return Whether the node is a block with a single statement that is
   *     an return with or without an expression.
   */
  private boolean isReturnBlock(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[318]++;
int CodeCoverConditionCoverageHelper_C73;
    if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[168]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[319]++;
int CodeCoverConditionCoverageHelper_C74;
      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((n.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[170]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[320]++;
        Node first = n.getFirstChild();
        return first.isReturn();

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[171]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[169]++;}

    return false;
  }

  /**
   * @return Whether the node is a block with a single statement that is
   *     an return.
   */
  private boolean isReturnExpressBlock(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[321]++;
int CodeCoverConditionCoverageHelper_C75;
    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[172]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[322]++;
int CodeCoverConditionCoverageHelper_C76;
      if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((n.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[174]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[323]++;
        Node first = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[324]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((first.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[176]++;
          return first.hasOneChild();

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[177]++;}

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[175]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[173]++;}

    return false;
  }

  /**
   * @return Whether the node is a single return statement.
   */
  private boolean isReturnExpression(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[325]++;
int CodeCoverConditionCoverageHelper_C78;
    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((n.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[178]++;
      return n.hasOneChild();

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[179]++;}
    return false;
  }

  /**
   * @return The expression that is part of the return.
   */
  private Node getBlockReturnExpression(Node n) {
    Preconditions.checkState(isReturnExpressBlock(n));
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[326]++;
    return n.getFirstChild().getFirstChild();
  }

  /**
   * @return Whether the node is a block with a single statement that is
   *     a VAR declaration of a single variable.
   */
  private boolean isVarBlock(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[327]++;
int CodeCoverConditionCoverageHelper_C79;
    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[180]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[328]++;
int CodeCoverConditionCoverageHelper_C80;
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((n.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[182]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[329]++;
        Node first = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[330]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((first.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[184]++;
          return first.hasOneChild();

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[185]++;}

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[183]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[181]++;}

    return false;
  }

  /**
   * @return The var node.
   */
  private Node getBlockVar(Node n) {
    Preconditions.checkState(isVarBlock(n));
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[331]++;
    return n.getFirstChild();
  }

  /**
   * Does a statement consume a 'dangling else'? A statement consumes
   * a 'dangling else' if an 'else' token following the statement
   * would be considered by the parser to be part of the statement.
   */
  private boolean consumesDanglingElse(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[332]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[13]++;


    while (true) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[13]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[14]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[15]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[333]++;
      switch (n.getType()) {
        case Token.IF:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[186]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[334]++;
int CodeCoverConditionCoverageHelper_C83;
          if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((n.getChildCount() < 3) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[187]++;
            return true;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[188]++;}
          // This IF node has no else clause.
          n = n.getLastChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[335]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[336]++;
          continue;
        case Token.WITH:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[189]++;
        case Token.WHILE:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[190]++;
        case Token.FOR:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[191]++;
          n = n.getLastChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[337]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[338]++;
          continue;
        default:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[192]++;
          return false;
      }
    }
  }

  /**
   * Does the expression contain an operator with lower precedence than
   * the argument?
   */
  private boolean isLowerPrecedenceInExpression(Node n,
      final int precedence) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[339]++;
    Predicate<Node> isLowerPrecedencePredicate = new Predicate<Node>() {
      @Override
      public boolean apply(Node input) {
        return NodeUtil.precedence(input.getType()) < precedence;
      }
    };

    return NodeUtil.has(n, isLowerPrecedencePredicate,
        DONT_TRAVERSE_FUNCTIONS_PREDICATE);
  }

  /**
   * Whether the node type has lower precedence than "precedence"
   */
  private boolean isLowerPrecedence(Node n, final int precedence) {
    return NodeUtil.precedence(n.getType()) < precedence;
  }

  /**
   * Whether the node type has higher precedence than "precedence"
   */
  private boolean isHigherPrecedence(Node n, final int precedence) {
    return NodeUtil.precedence(n.getType()) > precedence;
  }
  /**
   * Does the expression contain a property assignment?
   */
  private boolean isPropertyAssignmentInExpression(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[340]++;
    Predicate<Node> isPropertyAssignmentInExpressionPredicate =
        new Predicate<Node>() {
      @Override
      public boolean apply(Node input) {
        return (input.isGetProp() &&
            input.getParent().isAssign());
      }
    };

    return NodeUtil.has(n, isPropertyAssignmentInExpressionPredicate,
        DONT_TRAVERSE_FUNCTIONS_PREDICATE);
  }

  /**
   * Try to minimize conditions expressions, as there are additional
   * assumptions that can be made when it is known that the final result
   * is a boolean.
   *
   * The following transformations are done recursively:
   *   !(x||y) --> !x&&!y
   *   !(x&&y) --> !x||!y
   *   !!x     --> x
   * Thus:
   *   !(x&&!y) --> !x||!!y --> !x||y
   *
   *   Returns the replacement for n, or the original if no change was made
   */
  private Node tryMinimizeCondition(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[341]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[342]++;

    switch (n.getType()) {
      case Token.NOT:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[193]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[343]++;
        Node first = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[344]++;
        switch (first.getType()) {
          case Token.NOT:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[194]++; {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[345]++;
              Node newRoot = first.removeFirstChild();
              parent.replaceChild(n, newRoot);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[346]++;
              reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[347]++;
              // No need to traverse, tryMinimizeCondition is called on the
              // NOT children are handled below.
              return newRoot;
            }
          case Token.AND:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[195]++;
          case Token.OR:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[196]++; {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[348]++;
              // !(!x && !y) --> x || y
              // !(!x || !y) --> x && y
              // !(!x && y) --> x || !y
              // !(!x || y) --> x && !y
              // !(x && !y) --> !x || y
              // !(x || !y) --> !x && y
              // !(x && y) --> !x || !y
              // !(x || y) --> !x && !y
              Node leftParent = first.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[349]++;
              Node rightParent = first.getLastChild();
              Node left, right;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[350]++;
int CodeCoverConditionCoverageHelper_C84;

              // Check special case when such transformation cannot reduce
              // due to the added ()
              // It only occurs when both of expressions are not NOT expressions
              if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((leftParent.isNot()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((rightParent.isNot()) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[197]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[351]++;
                // If an expression has higher precedence than && or ||,
                // but lower precedence than NOT, an additional () is needed
                // Thus we do not preceed
                int op_precedence = NodeUtil.precedence(first.getType());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[352]++;
int CodeCoverConditionCoverageHelper_C85;
                if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C85 |= (128)) == 0 || true) &&
 ((isLowerPrecedence(leftParent, NOT_PRECEDENCE)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C85 |= (32)) == 0 || true) &&
 ((isHigherPrecedence(leftParent, op_precedence)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((isLowerPrecedence(rightParent, NOT_PRECEDENCE)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((isHigherPrecedence(rightParent, op_precedence)) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 4) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 4) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[199]++;
                  return n;

                } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[200]++;}

              } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[198]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[353]++;
int CodeCoverConditionCoverageHelper_C86;

              if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((leftParent.isNot()) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[201]++;
                left = leftParent.removeFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[354]++;

              } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[202]++;
                leftParent.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[355]++;
                left = IR.not(leftParent).srcref(leftParent);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[356]++;
              }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[357]++;
int CodeCoverConditionCoverageHelper_C87;
              if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((rightParent.isNot()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[203]++;
                right = rightParent.removeFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[358]++;

              } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[204]++;
                rightParent.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[359]++;
                right = IR.not(rightParent).srcref(rightParent);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[360]++;
              }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[361]++;

              int newOp = (first.isAnd()) ? Token.OR : Token.AND;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[362]++;
              Node newRoot = new Node(newOp, left, right);
              parent.replaceChild(n, newRoot);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[363]++;
              reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[364]++;
              // No need to traverse, tryMinimizeCondition is called on the
              // AND and OR children below.
              return newRoot;
            }

           default:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[205]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[365]++;
             TernaryValue nVal = NodeUtil.getPureBooleanValue(first);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[366]++;
int CodeCoverConditionCoverageHelper_C88;
             if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((nVal != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[206]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[367]++;
               boolean result = nVal.not().toBoolean(true);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[368]++;
               int equivalentResult = result ? 1 : 0;
               return maybeReplaceChildWithNumber(n, parent, equivalentResult);

             } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[207]++;}
        }
        // No need to traverse, tryMinimizeCondition is called on the NOT
        // children in the general case in the main post-order traversal.
        return n;

      case Token.OR:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[208]++;
      case Token.AND:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[209]++; {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[369]++;
        Node left = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[370]++;
        Node right = n.getLastChild();

        // Because the expression is in a boolean context minimize
        // the children, this can't be done in the general case.
        left = tryMinimizeCondition(left);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[371]++;
        right = tryMinimizeCondition(right);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[372]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[373]++;

        // Remove useless conditionals
        // Handle four cases:
        //   x || false --> x
        //   x || true  --> true
        //   x && true --> x
        //   x && false  --> false
        TernaryValue rightVal = NodeUtil.getPureBooleanValue(right);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[374]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((NodeUtil.getPureBooleanValue(right) != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[210]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[375]++;
          int type = n.getType();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[376]++;
          Node replacement = null;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[377]++;
          boolean rval = rightVal.toBoolean(true);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[378]++;
int CodeCoverConditionCoverageHelper_C90;

          // (x || FALSE) => x
          // (x && TRUE) => x
          if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (128)) == 0 || true) &&
 ((type == Token.OR) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C90 |= (32)) == 0 || true) &&
 ((rval) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C90 |= (8)) == 0 || true) &&
 ((type == Token.AND) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((rval) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 4) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 4) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[212]++;
            replacement = left;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[379]++;

          } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[213]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[380]++;
int CodeCoverConditionCoverageHelper_C91; if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((mayHaveSideEffects(left)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[214]++;
            replacement = right;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[381]++;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[215]++;}
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[382]++;
int CodeCoverConditionCoverageHelper_C92;

          if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((replacement != null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[216]++;
            n.detachChildren();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[383]++;
            parent.replaceChild(n, replacement);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[384]++;
            reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[385]++;
            return replacement;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[217]++;}

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[211]++;}
        return n;
      }

      case Token.HOOK:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[218]++; {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[386]++;
        Node condition = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[387]++;
        Node trueNode = n.getFirstChild().getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[388]++;
        Node falseNode = n.getLastChild();

        // Because the expression is in a boolean context minimize
        // the result children, this can't be done in the general case.
        // The condition is handled in the general case in #optimizeSubtree
        trueNode = tryMinimizeCondition(trueNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[389]++;
        falseNode = tryMinimizeCondition(falseNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[390]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[391]++;

        // Handle four cases:
        //   x ? true : false --> x
        //   x ? false : true --> !x
        //   x ? true : y     --> x || y
        //   x ? y : false    --> x && y
        Node replacement = null;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[392]++;
        TernaryValue trueNodeVal = NodeUtil.getPureBooleanValue(trueNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[393]++;
        TernaryValue falseNodeVal = NodeUtil.getPureBooleanValue(falseNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[394]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (8)) == 0 || true) &&
 ((trueNodeVal == TernaryValue.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((falseNodeVal == TernaryValue.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[219]++;
          // Remove useless conditionals, keep the condition
          condition.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[395]++;
          replacement = condition;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[396]++;

        } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[220]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[397]++;
int CodeCoverConditionCoverageHelper_C94; if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((trueNodeVal == TernaryValue.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((falseNodeVal == TernaryValue.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[221]++;
          // Remove useless conditionals, keep the condition
          condition.detachFromParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[398]++;
          replacement = IR.not(condition);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[399]++;

        } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[222]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[400]++;
int CodeCoverConditionCoverageHelper_C95; if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((trueNodeVal == TernaryValue.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[223]++;
          // Remove useless true case.
          n.detachChildren();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[401]++;
          replacement = IR.or(condition, falseNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[402]++;

        } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[224]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[403]++;
int CodeCoverConditionCoverageHelper_C96; if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((falseNodeVal == TernaryValue.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[225]++;
          // Remove useless false case
          n.detachChildren();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[404]++;
          replacement = IR.and(condition, trueNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[405]++;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[226]++;}
}
}
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[406]++;
int CodeCoverConditionCoverageHelper_C97;

        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((replacement != null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[227]++;
          parent.replaceChild(n, replacement);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[407]++;
          n = replacement;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[408]++;
          reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[409]++;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[228]++;}

        return n;
      }

      default:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[229]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[410]++;
        // while(true) --> while(1)
        TernaryValue nVal = NodeUtil.getPureBooleanValue(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[411]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((nVal != TernaryValue.UNKNOWN) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[230]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[412]++;
          boolean result = nVal.toBoolean(true);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[413]++;
          int equivalentResult = result ? 1 : 0;
          return maybeReplaceChildWithNumber(n, parent, equivalentResult);

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[231]++;}
        // We can't do anything else currently.
        return n;
    }
  }

  /**
   * Replaces a node with a number node if the new number node is not equivalent
   * to the current node.
   *
   * Returns the replacement for n if it was replaced, otherwise returns n.
   */
  private Node maybeReplaceChildWithNumber(Node n, Node parent, int num) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[414]++;
    Node newNode = IR.number(num);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[415]++;
int CodeCoverConditionCoverageHelper_C99;
    if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((newNode.isEquivalentTo(n)) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[232]++;
      parent.replaceChild(n, newNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[416]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[417]++;

      return newNode;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[233]++;}

    return n;
  }

  private static final ImmutableSet<String> STANDARD_OBJECT_CONSTRUCTORS =
    // String, Number, and Boolean functions return non-object types, whereas
    // new String, new Number, and new Boolean return object types, so don't
    // include them here.
    ImmutableSet.of(
      "Object",
      "Array",
      "RegExp",
      "Error"
      );
  static {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[418]++;
  }

  /**
   * Fold "new Object()" to "Object()".
   */
  private Node tryFoldStandardConstructors(Node n) {
    Preconditions.checkState(n.isNew());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[419]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[420]++;
int CodeCoverConditionCoverageHelper_C100;

    // If name normalization has been run then we know that
    // new Object() does in fact refer to what we think it is
    // and not some custom-defined Object().
    if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((isASTNormalized()) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[234]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[421]++;
int CodeCoverConditionCoverageHelper_C101;
      if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[236]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[422]++;
        String className = n.getFirstChild().getString();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[423]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((STANDARD_OBJECT_CONSTRUCTORS.contains(className)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[238]++;
          n.setType(Token.CALL);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[424]++;
          n.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[425]++;
          reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[426]++;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[239]++;}

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[237]++;}

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[235]++;}

    return n;
  }

  /**
   * Replaces a new Array or Object node with an object literal, unless the
   * call to Array or Object is to a local function with the same name.
   */
  private Node tryFoldLiteralConstructor(Node n) {
    Preconditions.checkArgument(n.isCall()
        || n.isNew());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[427]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[428]++;

    Node constructorNameNode = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[429]++;

    Node newLiteralNode = null;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[430]++;
int CodeCoverConditionCoverageHelper_C103;

    // We require the AST to be normalized to ensure that, say,
    // Object() really refers to the built-in Object constructor
    // and not a user-defined constructor with the same name.

    if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (8)) == 0 || true) &&
 ((isASTNormalized()) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((Token.NAME == constructorNameNode.getType()) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[240]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[431]++;

      String className = constructorNameNode.getString();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[432]++;
int CodeCoverConditionCoverageHelper_C104;

      if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 (("RegExp".equals(className)) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[242]++;
        // "RegExp("boo", "g")" --> /boo/g
        return tryFoldRegularExpressionConstructor(n);

      } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[243]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[433]++;
        boolean constructorHasArgs = constructorNameNode.getNext() != null;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[434]++;
int CodeCoverConditionCoverageHelper_C105;

        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (8)) == 0 || true) &&
 (("Object".equals(className)) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((constructorHasArgs) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[244]++;
          // "Object()" --> "{}"
          newLiteralNode = IR.objectlit();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[435]++;

        } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[245]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[436]++;
int CodeCoverConditionCoverageHelper_C106; if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 (("Array".equals(className)) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[246]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[437]++;
          // "Array(arg0, arg1, ...)" --> "[arg0, arg1, ...]"
          Node arg0 = constructorNameNode.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[438]++;
          FoldArrayAction action = isSafeToFoldArrayConstructor(arg0);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[439]++;
int CodeCoverConditionCoverageHelper_C107;

          if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (8)) == 0 || true) &&
 ((action == FoldArrayAction.SAFE_TO_FOLD_WITH_ARGS) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((action == FoldArrayAction.SAFE_TO_FOLD_WITHOUT_ARGS) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[248]++;
            newLiteralNode = IR.arraylit();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[440]++;
            n.removeChildren();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[441]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[442]++;
int CodeCoverConditionCoverageHelper_C108;
            if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((action == FoldArrayAction.SAFE_TO_FOLD_WITH_ARGS) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[250]++;
              newLiteralNode.addChildrenToFront(arg0);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[443]++;

            } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[251]++;}

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[249]++;}

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[247]++;}
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[444]++;
int CodeCoverConditionCoverageHelper_C109;

        if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((newLiteralNode != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[252]++;
          n.getParent().replaceChild(n, newLiteralNode);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[445]++;
          reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[446]++;
          return newLiteralNode;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[253]++;}
      }

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[241]++;}
    return n;
  }

  private static enum FoldArrayAction {
    NOT_SAFE_TO_FOLD, SAFE_TO_FOLD_WITH_ARGS, SAFE_TO_FOLD_WITHOUT_ARGS}

  /**
   * Checks if it is safe to fold Array() constructor into []. It can be
   * obviously done, if the initial constructor has either no arguments or
   * at least two. The remaining case may be unsafe since Array(number)
   * actually reserves memory for an empty array which contains number elements.
   */
  private FoldArrayAction isSafeToFoldArrayConstructor(Node arg) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[447]++;
    FoldArrayAction action = FoldArrayAction.NOT_SAFE_TO_FOLD;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[448]++;
int CodeCoverConditionCoverageHelper_C110;

    if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((arg == null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[254]++;
      action = FoldArrayAction.SAFE_TO_FOLD_WITHOUT_ARGS;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[449]++;

    } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[255]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[450]++;
int CodeCoverConditionCoverageHelper_C111; if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((arg.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[256]++;
      action = FoldArrayAction.SAFE_TO_FOLD_WITH_ARGS;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[451]++;

    } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[257]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[452]++;
      switch (arg.getType()) {
        case Token.STRING:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[258]++;
          // "Array('a')" --> "['a']"
          action = FoldArrayAction.SAFE_TO_FOLD_WITH_ARGS;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[453]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[454]++;
          break;
        case Token.NUMBER:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[259]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[455]++;
int CodeCoverConditionCoverageHelper_C112;
          // "Array(0)" --> "[]"
          if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((arg.getDouble() == 0) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[260]++;
            action = FoldArrayAction.SAFE_TO_FOLD_WITHOUT_ARGS;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[456]++;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[261]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[457]++;
          break;
        case Token.ARRAYLIT:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[262]++;
          // "Array([args])" --> "[[args]]"
          action = FoldArrayAction.SAFE_TO_FOLD_WITH_ARGS;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[458]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[459]++;
          break;
        default:
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[263]++;
      }
    }
}
    return action;
  }

  private Node tryFoldRegularExpressionConstructor(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[460]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[461]++;
    Node constructor = n.getFirstChild();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[462]++;
    Node pattern = constructor.getNext();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[463]++;  // e.g.  ^foobar$
    Node flags = null != pattern ? pattern.getNext() : null;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[464]++;
int CodeCoverConditionCoverageHelper_C113;  // e.g. gi

    if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (32)) == 0 || true) &&
 ((null == pattern) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C113 |= (8)) == 0 || true) &&
 ((null != flags) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((null != flags.getNext()) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 3) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 3) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[264]++;
      // too few or too many arguments
      return n;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[265]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[465]++;
int CodeCoverConditionCoverageHelper_C114;

    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (8192)) == 0 || true) &&
 ((// is pattern folded
        pattern.isString()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (4096)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C114 |= (2048)) == 0 || true) &&
 (("".equals(pattern.getString())) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1024)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C114 |= (512)) == 0 || true) &&
 ((pattern.getString().length() < 100) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (256)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C114 |= (128)) == 0 || true) &&
 ((null == flags) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C114 |= (32)) == 0 || true) &&
 ((flags.isString()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C114 |= (8)) == 0 || true) &&
 ((isEcmaScript5OrGreater()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((containsUnicodeEscape(pattern.getString())) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 7) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 7) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[266]++;

      // Make sure that / is escaped, so that it will fit safely in /brackets/
      // and make sure that no LineTerminatorCharacters appear literally inside
      // the pattern.
      // pattern is a string value with \\ and similar already escaped
      pattern = makeForwardSlashBracketSafe(pattern);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[466]++;

      Node regexLiteral;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[467]++;
int CodeCoverConditionCoverageHelper_C115;
      if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (8)) == 0 || true) &&
 ((null == flags) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 (("".equals(flags.getString())) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[268]++;
        // fold to /foobar/
        regexLiteral = IR.regexp(pattern);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[468]++;

      } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[269]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[469]++;
int CodeCoverConditionCoverageHelper_C116;
        // fold to /foobar/gi
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((areValidRegexpFlags(flags.getString())) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[270]++;
          report(INVALID_REGULAR_EXPRESSION_FLAGS, flags);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[470]++;
          return n;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[271]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[471]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((areSafeFlagsToFold(flags.getString())) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[272]++;
          return n;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[273]++;}
        n.removeChild(flags);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[472]++;
        regexLiteral = IR.regexp(pattern, flags);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[473]++;
      }

      parent.replaceChild(n, regexLiteral);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[474]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[475]++;
      return regexLiteral;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[267]++;}

    return n;
  }

  private Node reduceTrueFalse(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[476]++;
int CodeCoverConditionCoverageHelper_C118;
    if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((late) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[274]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[477]++;
      Node not = IR.not(IR.number(n.isTrue() ? 0 : 1));
      not.copyInformationFromForTree(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[478]++;
      n.getParent().replaceChild(n, not);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[479]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[480]++;
      return not;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[275]++;}
    return n;
  }

  private Node tryMinimizeArrayLiteral(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[481]++;
    boolean allStrings = true;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[482]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[16]++;


int CodeCoverConditionCoverageHelper_C119;
    for (Node cur = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false); cur = cur.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[16]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[17]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[18]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[483]++;
int CodeCoverConditionCoverageHelper_C120;
      if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((cur.isString()) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[276]++;
        allStrings = false;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[484]++;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[277]++;}
    }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[485]++;
int CodeCoverConditionCoverageHelper_C121;

    if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((allStrings) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[278]++;
      return tryMinimizeStringArrayLiteral(n);

    } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[279]++;
      return n;
    }
  }

  private Node tryMinimizeStringArrayLiteral(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[486]++;
int CodeCoverConditionCoverageHelper_C122;
    if((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((late) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[280]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[281]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[487]++;

    int numElements = n.getChildCount();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[488]++;
    // We save two bytes per element.
    int saving = numElements * 2 - STRING_SPLIT_OVERHEAD;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[489]++;
int CodeCoverConditionCoverageHelper_C123;
    if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((saving <= 0) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[282]++;
      return n;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[283]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[490]++;

    String[] strings = new String[n.getChildCount()];
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[491]++;
    int idx = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[492]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[19]++;


int CodeCoverConditionCoverageHelper_C124;
    for (Node cur = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((cur != null) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false); cur = cur.getNext()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[19]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[20]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[21]++;
}
      strings[idx++] = cur.getString();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[493]++;
    }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[494]++;

    // These delimiters are chars that appears a lot in the program therefore
    // probably have a small Huffman encoding.
    String delimiter = pickDelimiter(strings);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[495]++;
int CodeCoverConditionCoverageHelper_C125;
    if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((delimiter != null) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[284]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[496]++;
      String template = Joiner.on(delimiter).join(strings);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[497]++;
      Node call = IR.call(
          IR.getprop(
              IR.string(template),
              IR.string("split")),
          IR.string("" + delimiter));
      call.copyInformationFromForTree(n);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[498]++;
      n.getParent().replaceChild(n, call);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[499]++;
      reportCodeChange();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[500]++;
      return call;

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[285]++;}
    return n;
  }

  /**
   * Find a delimiter that does not occur in the given strings
   * @param strings The strings that must be separated.
   * @return a delimiter string or null
   */
  private String pickDelimiter(String[] strings) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[501]++;
    boolean allLength1 = true;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[502]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[22]++;


    for (String s : strings) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[22]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[23]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[24]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[503]++;
int CodeCoverConditionCoverageHelper_C126;
      if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((s.length() != 1) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[286]++;
        allLength1 = false;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[504]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[505]++;
        break;

      } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[287]++;}
    }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[506]++;
int CodeCoverConditionCoverageHelper_C127;

    if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((allLength1) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[288]++;
      return "";

    } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[289]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[507]++;

    String[] delimiters = new String[]{" ", ";", ",", "{", "}", null};
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[508]++;
    int i = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[509]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[25]++;


int CodeCoverConditionCoverageHelper_C128;
    NEXT_DELIMITER: for (;(((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((delimiters[i] != null) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[25]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[26]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[27]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[510]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[28]++;


      for (String cur : strings) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[28]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[29]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[30]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[511]++;
int CodeCoverConditionCoverageHelper_C129;
        if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((cur.contains(delimiters[i])) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[290]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[512]++;
          continue NEXT_DELIMITER;

        } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[291]++;}
      }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[513]++;
      break;
    }
    return delimiters[i];
  }

  private static final Pattern REGEXP_FLAGS_RE = Pattern.compile("^[gmi]*$");
  static {
    CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[514]++;
  }

  /**
   * are the given flags valid regular expression flags?
   * JavaScript recognizes several suffix flags for regular expressions,
   * 'g' - global replace, 'i' - case insensitive, 'm' - multi-line.
   * They are case insensitive, and JavaScript does not recognize the extended
   * syntax mode, single-line mode, or expression replacement mode from Perl 5.
   */
  private static boolean areValidRegexpFlags(String flags) {
    return REGEXP_FLAGS_RE.matcher(flags).matches();
  }

  /**
   * are the given flags safe to fold?
   * We don't fold the regular expression if global ('g') flag is on,
   * because in this case it isn't really a constant: its 'lastIndex'
   * property contains the state of last execution, so replacing
   * 'new RegExp('foobar','g')' with '/foobar/g' may change the behavior of
   * the program if the RegExp is used inside a loop, for example.
   * <p>
   * ECMAScript 5 explicitly disallows pooling of regular expression literals so
   * in ECMAScript 5, {@code /foo/g} and {@code new RegExp('foo', 'g')} are
   * equivalent.
   * From section 7.8.5:
   * "Then each time the literal is evaluated, a new object is created as if by
   * the expression new RegExp(Pattern, Flags) where RegExp is the standard
   * built-in constructor with that name."
   */
  private boolean areSafeFlagsToFold(String flags) {
    return isEcmaScript5OrGreater() || flags.indexOf('g') < 0;
  }

  /**
   * returns a string node that can safely be rendered inside /brackets/.
   */
  private static Node makeForwardSlashBracketSafe(Node n) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[515]++;
    String s = n.getString();
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[516]++;
    // sb contains everything in s[0:pos]
    StringBuilder sb = null;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[517]++;
    int pos = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[518]++;
    boolean isEscaped = false, inCharset = false;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[519]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[31]++;


int CodeCoverConditionCoverageHelper_C130;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((i < s.length()) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[31]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[32]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[33]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[520]++;
      char ch = s.charAt(i);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[521]++;
      switch (ch) {
        case '\\':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[292]++;
          isEscaped = !isEscaped;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[522]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[523]++;
          continue;
        case '/':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[293]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[524]++;
int CodeCoverConditionCoverageHelper_C131;
          // Escape a literal forward slash if it is not already escaped and is
          // not inside a character set.
          //     new RegExp('/') -> /\//
          // but the following do not need extra escaping
          //     new RegExp('\\/') -> /\//
          //     new RegExp('[/]') -> /[/]/
          if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C131 |= (8)) == 0 || true) &&
 ((isEscaped) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((inCharset) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 2) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[294]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[525]++;
int CodeCoverConditionCoverageHelper_C132;
            if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((null == sb) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[296]++; sb = new StringBuilder(s.length() + 16);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[526]++;
 } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[297]++;}
            sb.append(s, pos, i).append('\\');
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[527]++;
            pos = i;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[528]++;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[295]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[529]++;
          break;
        case '[':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[298]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[530]++;
int CodeCoverConditionCoverageHelper_C133;
          if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((isEscaped) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[299]++;
            inCharset = true;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[531]++;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[300]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[532]++;
          break;
        case ']':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[301]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[533]++;
int CodeCoverConditionCoverageHelper_C134;
          if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((isEscaped) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[302]++;
            inCharset = false;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[534]++;

          } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[303]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[535]++;
          break;
        case '\r':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[304]++; case '\n':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[305]++; case '\u2028':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[306]++; case '\u2029':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[307]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[536]++;
int CodeCoverConditionCoverageHelper_C135;
          // LineTerminators cannot appear raw inside a regular
          // expression literal.
          // They can't appear legally in a quoted string, but when
          // the quoted string from
          //     new RegExp('\n')
          // reaches here, the quoting has been removed.
          // Requote just these code-points.
          if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((null == sb) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[308]++; sb = new StringBuilder(s.length() + 16);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[537]++;
 } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[309]++;}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[538]++;
int CodeCoverConditionCoverageHelper_C136;
          if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((isEscaped) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[310]++;
            sb.append(s, pos, i - 1);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[539]++;

          } else {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[311]++;
            sb.append(s, pos, i);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[540]++;
          }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[541]++;
          switch (ch) {
            case '\r':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[312]++; sb.append("\\r");
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[542]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[543]++; break;
            case '\n':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[313]++; sb.append("\\n");
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[544]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[545]++; break;
            case '\u2028':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[314]++; sb.append("\\u2028");
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[546]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[547]++; break;
            case '\u2029':
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[315]++; sb.append("\\u2029");
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[548]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[549]++; break; default : CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[316]++;
          }
          pos = i + 1;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[550]++;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[551]++;
          break; default : CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[317]++;
      }
      isEscaped = false;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[552]++;
    }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[553]++;
int CodeCoverConditionCoverageHelper_C137;

    if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((null == sb) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[318]++; return n.cloneTree();
 } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[319]++;}

    sb.append(s, pos, s.length());
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[554]++;
    return IR.string(sb.toString()).srcref(n);
  }

  /**
   * true if the JavaScript string would contain a Unicode escape when written
   * out as the body of a regular expression literal.
   */
  static boolean containsUnicodeEscape(String s) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[555]++;
    String esc = REGEXP_ESCAPER.regexpEscape(s);
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[556]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[34]++;


    for (int i = -1;(i = esc.indexOf("\\u", i + 1)) >= 0;) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[34]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[35]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[36]++;
}
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[557]++;
      int nSlashes = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[558]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[37]++;


int CodeCoverConditionCoverageHelper_C139;
      while ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (8)) == 0 || true) &&
 ((i - nSlashes > 0) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 (('\\' == esc.charAt(i - nSlashes - 1)) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 2) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 2) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[37]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[38]--;
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.loops[39]++;
}
        ++nSlashes;
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[559]++;
      }
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.statements[560]++;
int CodeCoverConditionCoverageHelper_C140;
      // if there are an even number of slashes before the \ u then it is a
      // Unicode literal.
      if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((0 == (nSlashes & 1)) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[320]++; return true;
 } else {
  CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt.branches[321]++;}
    }
    return false;
  }
}

class CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt ());
  }
    public static long[] statements = new long[561];
    public static long[] branches = new long[322];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[141];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PeepholeSubstituteAlternateSyntax.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,3,1,1,3,3,1,2,1,1,2,1,1,3,1,2,3,1,2,1,3,1,1,1,1,2,1,1,3,2,1,1,3,2,2,1,1,1,2,1,2,2,2,1,3,2,2,2,1,1,2,3,3,3,3,1,0,3,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,0,1,2,3,1,1,1,1,3,1,1,2,2,1,1,1,1,1,1,1,1,2,1,2,1,2,1,1,1,1,1,3,3,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,0,2,1};
    for (int i = 1; i <= 140; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$5jxd5csur1drfrm5a8qdelwpfbs5rsuxgmivyp94mrztiyoinzenm5fkndt () {
    super("com.google.javascript.jscomp.PeepholeSubstituteAlternateSyntax.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 560; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 321; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 140; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PeepholeSubstituteAlternateSyntax.java");
      for (int i = 1; i <= 560; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 321; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 140; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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

