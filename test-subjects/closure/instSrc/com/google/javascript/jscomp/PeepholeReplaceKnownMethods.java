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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.List;
import java.util.Locale;

/**
 * Just to fold known methods when they are called with constants.
 *
 */
class PeepholeReplaceKnownMethods extends AbstractPeepholeOptimization{
  static {
    CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.ping();
  }


  // The LOCALE independent "locale"
  private static final Locale ROOT_LOCALE = new Locale("");
  static {
    CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[1]++;
  }
  private final boolean late;

  /**
   * @param late When late is true, this mean we are currently running after
   * most of the other optimizations. In this case we avoid changes that make
   * the code larger (but otherwise easier to analyze - such as using string
   * splitting).
   */
  PeepholeReplaceKnownMethods(boolean late) {
    this.late = late;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[2]++;
  }

  @Override
  Node optimizeSubtree(Node subtree) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((subtree.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false) ){
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[1]++;
      return tryFoldKnownMethods(subtree);

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[2]++;}
    return subtree;
  }

  private Node tryFoldKnownMethods(Node subtree) {
    // For now we only support string methods .join(),
    // .indexOf(), .substring() and .substr()
    // and numeric methods parseInt() and parseFloat().

    subtree = tryFoldArrayJoin(subtree);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[4]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((subtree.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[3]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[6]++;
      Node callTarget = subtree.getFirstChild();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((callTarget == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[5]++;
        return subtree;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[6]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;

      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(callTarget)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[7]++;
        subtree = tryFoldKnownStringMethods(subtree);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[9]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[8]++;
        subtree = tryFoldKnownNumericMethods(subtree);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[10]++;
      }

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[4]++;}

    return subtree;
  }

  /**
   * Try to evaluate known String methods
   *    .indexOf(), .substr(), .substring()
   */
  private Node tryFoldKnownStringMethods(Node subtree) {
    Preconditions.checkArgument(subtree.isCall());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[11]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[12]++;

    // check if this is a call on a string method
    // then dispatch to specific folding method.
    Node callTarget = subtree.getFirstChild();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((callTarget == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[9]++;
      return subtree;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[10]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(callTarget)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[11]++;
      return subtree;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[12]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[15]++;

    Node stringNode = callTarget.getFirstChild();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[16]++;
    Node functionName = stringNode.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && ((!
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((stringNode.isString()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
) || (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((functionName.isString()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[13]++;
      return subtree;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[14]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[18]++;

    String functionNameString = functionName.getString();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[19]++;
    Node firstArg = callTarget.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[20]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((functionNameString.equals("split")) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[15]++;
      subtree = tryFoldStringSplit(subtree, stringNode, firstArg);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[21]++;

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[16]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[22]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((firstArg == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[17]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((functionNameString.equals("toLowerCase")) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[19]++;
        subtree = tryFoldStringToLowerCase(subtree, stringNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[24]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[20]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[25]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((functionNameString.equals("toUpperCase")) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[21]++;
        subtree = tryFoldStringToUpperCase(subtree, stringNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[26]++;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[22]++;}
}
      return subtree;

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[18]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[27]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(firstArg)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[23]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[28]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((functionNameString.equals("indexOf")) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((functionNameString.equals("lastIndexOf")) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[25]++;
        subtree = tryFoldStringIndexOf(subtree, functionNameString,
            stringNode, firstArg);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[29]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[26]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[30]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((functionNameString.equals("substr")) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[27]++;
        subtree = tryFoldStringSubstr(subtree, stringNode, firstArg);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[31]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[28]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[32]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((functionNameString.equals("substring")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[29]++;
        subtree = tryFoldStringSubstring(subtree, stringNode, firstArg);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[33]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[30]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[34]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((functionNameString.equals("charAt")) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[31]++;
        subtree = tryFoldStringCharAt(subtree, stringNode, firstArg);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[35]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[32]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[36]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((functionNameString.equals("charCodeAt")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[33]++;
        subtree = tryFoldStringCharCodeAt(subtree, stringNode, firstArg);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[37]++;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[34]++;}
}
}
}
}

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[24]++;}
}
}

    return subtree;
  }

  /**
   * Try to evaluate known Numeric methods
   *    .parseInt(), parseFloat()
   */
  private Node tryFoldKnownNumericMethods(Node subtree) {
    Preconditions.checkArgument(subtree.isCall());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[38]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[39]++;
int CodeCoverConditionCoverageHelper_C18;

    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isASTNormalized()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[35]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[40]++;
      // check if this is a call on a string method
      // then dispatch to specific folding method.
      Node callTarget = subtree.getFirstChild();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[41]++;
int CodeCoverConditionCoverageHelper_C19;

      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((callTarget.isName()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[37]++;
        return subtree;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[38]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[42]++;

      String functionNameString = callTarget.getString();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[43]++;
      Node firstArgument = callTarget.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[44]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((firstArgument != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((firstArgument.isString()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((firstArgument.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[39]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[45]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((functionNameString.equals("parseInt")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((functionNameString.equals("parseFloat")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[41]++;
          subtree = tryFoldParseNumber(subtree, functionNameString,
              firstArgument);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[46]++;

        } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[42]++;}

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[40]++;}

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[36]++;}
    return subtree;
  }

  /**
   * @return The lowered string Node.
   */
  private Node tryFoldStringToLowerCase(Node subtree, Node stringNode) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[47]++;
    // From Rhino, NativeString.java. See ECMA 15.5.4.11
    String lowered = stringNode.getString().toLowerCase(ROOT_LOCALE);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[48]++;
    Node replacement = IR.string(lowered);
    subtree.getParent().replaceChild(subtree, replacement);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[49]++;
    reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[50]++;
    return replacement;
  }

  /**
   * @return The upped string Node.
   */
  private Node tryFoldStringToUpperCase(Node subtree, Node stringNode) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[51]++;
    // From Rhino, NativeString.java. See ECMA 15.5.4.12
    String upped = stringNode.getString().toUpperCase(ROOT_LOCALE);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[52]++;
    Node replacement = IR.string(upped);
    subtree.getParent().replaceChild(subtree, replacement);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[53]++;
    reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[54]++;
    return replacement;
  }

  /**
   * @param input string representation of a number
   * @return string with leading and trailing zeros removed
   */
  private String normalizeNumericString(String input) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[55]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((input == null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((input.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[43]++;
      return input;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[44]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[56]++;

    int startIndex = 0, endIndex = input.length() - 1;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[57]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[1]++;


int CodeCoverConditionCoverageHelper_C23;

    // Remove leading zeros
    while ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (32)) == 0 || true) &&
 ((startIndex < input.length()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((input.charAt(startIndex) == '0') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((input.charAt(startIndex) != '.') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 3) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[1]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[2]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[3]++;
}
      startIndex++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[58]++;
    }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[59]++;
int CodeCoverConditionCoverageHelper_C24;

    // Remove trailing zeros only after the decimal
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((input.indexOf('.') >= 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[45]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[60]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[4]++;


int CodeCoverConditionCoverageHelper_C25;
      while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((endIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((input.charAt(endIndex) == '0') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[4]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[5]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[6]++;
}
        endIndex--;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[61]++;
      }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[62]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((input.charAt(endIndex) == '.') && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[47]++;
        endIndex--;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[63]++;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[48]++;}

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[46]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[64]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((startIndex >= endIndex) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[49]++;
      return input;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[50]++;}

    return input.substring(startIndex, endIndex + 1);
  }

  /**
   * Try to evaluate parseInt, parseFloat:
   *     parseInt("1") -> 1
   *     parseInt("1", 10) -> 1
   *     parseFloat("1.11") -> 1.11
   */
  private Node tryFoldParseNumber(
      Node n, String functionName, Node firstArg) {
    Preconditions.checkArgument(n.isCall());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[65]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[66]++;

    boolean isParseInt = functionName.equals("parseInt");
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[67]++;
    Node secondArg = firstArg.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[68]++;

    // Second argument is only used as the radix for parseInt
    int radix = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[69]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((secondArg != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[51]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[70]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((isParseInt) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[53]++;
        return n;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[54]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[71]++;
int CodeCoverConditionCoverageHelper_C30;

      // Third-argument and non-numeric second arg are problematic. Discard.
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((secondArg.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((secondArg.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[55]++;
        return n;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[56]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[72]++;
        double tmpRadix = secondArg.getDouble();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[73]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((tmpRadix != (int)tmpRadix) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[57]++;
          return n;
} else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[58]++;}
        radix = (int)tmpRadix;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[74]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[75]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (32)) == 0 || true) &&
 ((radix < 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((radix == 1) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((radix > 36) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[59]++;
          return n;

        } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[60]++;}
      }

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[52]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[76]++;

    // stringVal must be a valid string.
    String stringVal = null;
    Double checkVal;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[77]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((firstArg.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[61]++;
      checkVal = NodeUtil.getNumberValue(firstArg);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[78]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[79]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C34 |= (32)) == 0 || true) &&
 ((radix == 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((radix == 10) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((isParseInt) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 3) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 3) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[63]++;
        //Convert a numeric first argument to a different base
        stringVal = String.valueOf(checkVal.intValue());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[80]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[64]++;
        // If parseFloat is called with a numeric argument,
        // replace it with just the number.
        // If parseInt is called with a numeric first argument and the radix
        // is 10 or omitted, just replace it with the number
        Node numericNode;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[81]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((isParseInt) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[65]++;
          numericNode = IR.number(checkVal.intValue());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[82]++;

        } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[66]++;
          numericNode = IR.number(checkVal);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[83]++;
        }
        n.getParent().replaceChild(n, numericNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[84]++;
        reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[85]++;
        return numericNode;
      }

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[62]++;
      stringVal = NodeUtil.getStringValue(firstArg);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[86]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[87]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((stringVal == null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[67]++;
        return n;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[68]++;}

      //Check that the string is in a format we can recognize
      checkVal = NodeUtil.getStringNumberValue(stringVal);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[88]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[89]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((checkVal == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[69]++;
        return n;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[70]++;}

      stringVal = NodeUtil.trimJsWhiteSpace(stringVal);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[90]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[91]++;
int CodeCoverConditionCoverageHelper_C38;
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((stringVal.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[71]++;
        return n;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[72]++;}
    }

    Node newNode;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[92]++;
int CodeCoverConditionCoverageHelper_C39;
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((stringVal.equals("0")) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[73]++;
      // Special case for parseInt("0") or parseFloat("0")
      newNode = IR.number(0);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[93]++;

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[74]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[94]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((isParseInt) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[75]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[95]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((radix == 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((radix == 16) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[77]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[96]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((stringVal.length() > 1) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((stringVal.substring(0, 2).equalsIgnoreCase("0x")) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[79]++;
          radix = 16;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[97]++;
          stringVal = stringVal.substring(2);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[98]++;

        } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[80]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[99]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((radix == 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[81]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[100]++;
int CodeCoverConditionCoverageHelper_C44;
          // if a radix is not specified or is 0 and the most
          // significant digit is "0", the string will parse
          // with a radix of 8 on some browsers, so leave
          // this case alone. This check does not apply in
          // script mode ECMA5 or greater
          if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((isEcmaScript5OrGreater()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((stringVal.substring(0, 1).equals("0")) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[83]++;
            return n;

          } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[84]++;}

          radix = 10;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[101]++;

        } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[82]++;}
}

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[78]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[102]++;
      int newVal = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[103]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
        newVal = Integer.parseInt(stringVal, radix);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[104]++;
      } catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[86]++;
        return n;
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[85]++;
}
  }

      newNode = IR.number(newVal);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[105]++;

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[76]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[106]++;
      String normalizedNewVal = "0";
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[107]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
      try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[108]++;
        double newVal = Double.parseDouble(stringVal);
        newNode = IR.number(newVal);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[109]++;
        normalizedNewVal = normalizeNumericString(String.valueOf(newVal));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[110]++;
      }
      catch(NumberFormatException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[88]++;
        return n;
      } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[87]++;
}
  }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[111]++;
int CodeCoverConditionCoverageHelper_C45;
      // Make sure that the parsed number matches the original string
      // This prevents rounding differences between the Java implementation
      // and native script.
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((normalizeNumericString(stringVal).equals(normalizedNewVal)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[89]++;
        return n;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[90]++;}
    }
}

    n.getParent().replaceChild(n, newNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[112]++;

    reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[113]++;

    return newNode;
  }

  /**
   * Try to evaluate String.indexOf/lastIndexOf:
   *     "abcdef".indexOf("bc") -> 1
   *     "abcdefbc".indexOf("bc", 3) -> 6
   */
  private Node tryFoldStringIndexOf(
      Node n, String functionName, Node lstringNode, Node firstArg) {
    Preconditions.checkArgument(n.isCall());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[114]++;
    Preconditions.checkArgument(lstringNode.isString());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[115]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[116]++;

    String lstring = NodeUtil.getStringValue(lstringNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[117]++;
    boolean isIndexOf = functionName.equals("indexOf");
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[118]++;
    Node secondArg = firstArg.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[119]++;
    String searchValue = NodeUtil.getStringValue(firstArg);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[120]++;
int CodeCoverConditionCoverageHelper_C46;
    // searchValue must be a valid string.
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((searchValue == null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[91]++;
      return n;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[92]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[121]++;
    int fromIndex = isIndexOf ? 0 : lstring.length();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[122]++;
int CodeCoverConditionCoverageHelper_C47;
    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((secondArg != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[93]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[123]++;
int CodeCoverConditionCoverageHelper_C48;
      // Third-argument and non-numeric second arg are problematic. Discard.
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((secondArg.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((secondArg.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[95]++;
        return n;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[96]++;
        fromIndex = (int) secondArg.getDouble();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[124]++;
      }

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[94]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[125]++;
    int indexVal = isIndexOf ? lstring.indexOf(searchValue, fromIndex)
                             : lstring.lastIndexOf(searchValue, fromIndex);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[126]++;
    Node newNode = IR.number(indexVal);
    n.getParent().replaceChild(n, newNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[127]++;

    reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[128]++;

    return newNode;
  }

  /**
   * Try to fold an array join: ['a', 'b', 'c'].join('') -> 'abc';
   */
  private Node tryFoldArrayJoin(Node n) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[129]++;
    Node callTarget = n.getFirstChild();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[130]++;
int CodeCoverConditionCoverageHelper_C49;

    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((callTarget == null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((callTarget.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[97]++;
      return n;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[98]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[131]++;

    Node right = callTarget.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[132]++;
int CodeCoverConditionCoverageHelper_C50;
    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((right != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[99]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[133]++;
int CodeCoverConditionCoverageHelper_C51;
      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((right.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(right)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[101]++;
        return n;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[102]++;}

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[100]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[134]++;

    Node arrayNode = callTarget.getFirstChild();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[135]++;
    Node functionName = arrayNode.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[136]++;
int CodeCoverConditionCoverageHelper_C52;

    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((arrayNode.isArrayLit()) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((functionName.getString().equals("join")) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[103]++;
      return n;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[104]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[137]++;
int CodeCoverConditionCoverageHelper_C53;

    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (32)) == 0 || true) &&
 ((right != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((right.isString()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((",".equals(right.getString())) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[105]++;
      // "," is the default, it doesn't need to be explicit
      n.removeChild(right);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[138]++;
      reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[139]++;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[106]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[140]++;

    String joinString = (right == null) ? "," : NodeUtil.getStringValue(right);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[141]++;
    List<Node> arrayFoldedChildren = Lists.newLinkedList();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[142]++;
    StringBuilder sb = null;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[143]++;
    int foldedSize = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[144]++;
    Node prev = null;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[145]++;
    Node elem = arrayNode.getFirstChild();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[146]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[7]++;


int CodeCoverConditionCoverageHelper_C54;
    // Merges adjacent String nodes.
    while ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((elem != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[7]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[8]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[9]++;
}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[147]++;
int CodeCoverConditionCoverageHelper_C55;
      if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(elem)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((elem.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[107]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[148]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((sb == null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[109]++;
          sb = new StringBuilder();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[149]++;

        } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[110]++;
          sb.append(joinString);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[150]++;
        }
        sb.append(NodeUtil.getArrayElementStringValue(elem));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[151]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[108]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[152]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[111]++;
          Preconditions.checkNotNull(prev);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[153]++;
          // + 2 for the quotes.
          foldedSize += sb.length() + 2;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[154]++;
          arrayFoldedChildren.add(
              IR.string(sb.toString()).copyInformationFrom(prev));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[155]++;
          sb = null;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[156]++;

        } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[112]++;}
        foldedSize += InlineCostEstimator.getCost(elem);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[157]++;
        arrayFoldedChildren.add(elem);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[158]++;
      }
      prev = elem;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[159]++;
      elem = elem.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[160]++;
    }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[161]++;
int CodeCoverConditionCoverageHelper_C58;

    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[113]++;
      Preconditions.checkNotNull(prev);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[162]++;
      // + 2 for the quotes.
      foldedSize += sb.length() + 2;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[163]++;
      arrayFoldedChildren.add(
          IR.string(sb.toString()).copyInformationFrom(prev));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[164]++;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[114]++;}
    // one for each comma.
    foldedSize += arrayFoldedChildren.size() - 1;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[165]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[166]++;

    int originalSize = InlineCostEstimator.getCost(n);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[167]++;
    switch (arrayFoldedChildren.size()) {
      case 0:
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[115]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[168]++;
        Node emptyStringNode = IR.string("");
        n.getParent().replaceChild(n, emptyStringNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[169]++;
        reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[170]++;
        return emptyStringNode;
      case 1:
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[116]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[171]++;
        Node foldedStringNode = arrayFoldedChildren.remove(0);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[172]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((foldedSize > originalSize) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[117]++;
          return n;

        } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[118]++;}
        arrayNode.detachChildren();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[173]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[174]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((foldedStringNode.isString()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[119]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[175]++;
          // If the Node is not a string literal, ensure that
          // it is coerced to a string.
          Node replacement = IR.add(
              IR.string("").srcref(n),
              foldedStringNode);
          foldedStringNode = replacement;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[176]++;

        } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[120]++;}
        n.getParent().replaceChild(n, foldedStringNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[177]++;
        reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[178]++;
        return foldedStringNode;
      default:
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[121]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[179]++;
int CodeCoverConditionCoverageHelper_C61;
        // No folding could actually be performed.
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((arrayFoldedChildren.size() == arrayNode.getChildCount()) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[122]++;
          return n;

        } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[123]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[180]++;
        int kJoinOverhead = "[].join()".length();
        foldedSize += kJoinOverhead;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[181]++;
        foldedSize += (right != null) ? InlineCostEstimator.getCost(right) : 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[182]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[183]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((foldedSize > originalSize) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[124]++;
          return n;

        } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[125]++;}
        arrayNode.detachChildren();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[184]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[185]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[10]++;


        for (Node node : arrayFoldedChildren) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[10]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[11]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[12]++;
}
          arrayNode.addChildToBack(node);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[186]++;
        }
        reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[187]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[188]++;
        break;
    }

    return n;
  }

  /**
   * Try to fold .substr() calls on strings
   */
  private Node tryFoldStringSubstr(Node n, Node stringNode, Node arg1) {
    Preconditions.checkArgument(n.isCall());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[189]++;
    Preconditions.checkArgument(stringNode.isString());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[190]++;

    int start, length;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[191]++;
    String stringAsString = stringNode.getString();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[192]++;
int CodeCoverConditionCoverageHelper_C63;

    // TODO(nicksantos): We really need a NodeUtil.getNumberValue
    // function.
    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((arg1 != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((arg1.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[126]++;
      start = (int) arg1.getDouble();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[193]++;

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[127]++;
      return n;
    }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[194]++;

    Node arg2 = arg1.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[195]++;
int CodeCoverConditionCoverageHelper_C64;
    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((arg2 != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[128]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[196]++;
int CodeCoverConditionCoverageHelper_C65;
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((arg2.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[130]++;
        length = (int) arg2.getDouble();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[197]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[131]++;
        return n;
      }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[198]++;
int CodeCoverConditionCoverageHelper_C66;

      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((arg2.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[132]++;
        // If we got more args than we expected, bail out.
        return n;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[133]++;}

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[129]++;
      // parameter 2 not passed
      length = stringAsString.length() - start;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[199]++;
    }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[200]++;
int CodeCoverConditionCoverageHelper_C67;

    // Don't handle these cases. The specification actually does
    // specify the behavior in some of these cases, but we haven't
    // done a thorough investigation that it is correctly implemented
    // in all browsers.
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (32)) == 0 || true) &&
 (((start + length) > stringAsString.length()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((length < 0) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 3) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 3) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[134]++;
      return n;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[135]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[201]++;

    String result = stringAsString.substring(start, start + length);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[202]++;
    Node resultNode = IR.string(result);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[203]++;

    Node parent = n.getParent();
    parent.replaceChild(n, resultNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[204]++;
    reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[205]++;
    return resultNode;
  }

  /**
   * Try to fold .substring() calls on strings
   */
  private Node tryFoldStringSubstring(Node n, Node stringNode, Node arg1) {
    Preconditions.checkArgument(n.isCall());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[206]++;
    Preconditions.checkArgument(stringNode.isString());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[207]++;

    int start, end;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[208]++;
    String stringAsString = stringNode.getString();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[209]++;
int CodeCoverConditionCoverageHelper_C68;

    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (8)) == 0 || true) &&
 ((arg1 != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((arg1.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[136]++;
      start = (int) arg1.getDouble();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[210]++;

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[137]++;
      return n;
    }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[211]++;

    Node arg2 = arg1.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[212]++;
int CodeCoverConditionCoverageHelper_C69;
    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((arg2 != null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[138]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[213]++;
int CodeCoverConditionCoverageHelper_C70;
      if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((arg2.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[140]++;
        end = (int) arg2.getDouble();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[214]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[141]++;
        return n;
      }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[215]++;
int CodeCoverConditionCoverageHelper_C71;

      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((arg2.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[142]++;
        // If we got more args than we expected, bail out.
        return n;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[143]++;}

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[139]++;
      // parameter 2 not passed
      end = stringAsString.length();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[216]++;
    }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[217]++;
int CodeCoverConditionCoverageHelper_C72;

    // Don't handle these cases. The specification actually does
    // specify the behavior in some of these cases, but we haven't
    // done a thorough investigation that it is correctly implemented
    // in all browsers.
    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C72 |= (128)) == 0 || true) &&
 ((end > stringAsString.length()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (64)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C72 |= (32)) == 0 || true) &&
 ((start > stringAsString.length()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((start < 0) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 4) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 4) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[144]++;
      return n;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[145]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[218]++;

    String result = stringAsString.substring(start, end);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[219]++;
    Node resultNode = IR.string(result);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[220]++;

    Node parent = n.getParent();
    parent.replaceChild(n, resultNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[221]++;
    reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[222]++;
    return resultNode;
  }

  /**
   * Try to fold .charAt() calls on strings
   */
  private Node tryFoldStringCharAt(Node n, Node stringNode, Node arg1) {
    Preconditions.checkArgument(n.isCall());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[223]++;
    Preconditions.checkArgument(stringNode.isString());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[224]++;

    int index;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[225]++;
    String stringAsString = stringNode.getString();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[226]++;
int CodeCoverConditionCoverageHelper_C73;

    if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (32)) == 0 || true) &&
 ((arg1 != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C73 |= (8)) == 0 || true) &&
 ((arg1.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((arg1.getNext() == null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 3) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 3) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[146]++;
      index = (int) arg1.getDouble();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[227]++;

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[147]++;
      return n;
    }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[228]++;
int CodeCoverConditionCoverageHelper_C74;

    if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((stringAsString.length() <= index) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[148]++;
      // http://es5.github.com/#x15.5.4.4 says "" is returned when index is
      // out of bounds but we bail.
      return n;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[149]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[229]++;

    Node resultNode = IR.string(
        stringAsString.substring(index, index + 1));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[230]++;
    Node parent = n.getParent();
    parent.replaceChild(n, resultNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[231]++;
    reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[232]++;
    return resultNode;
  }

  /**
   * Try to fold .charCodeAt() calls on strings
   */
  private Node tryFoldStringCharCodeAt(Node n, Node stringNode, Node arg1) {
    Preconditions.checkArgument(n.isCall());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[233]++;
    Preconditions.checkArgument(stringNode.isString());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[234]++;

    int index;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[235]++;
    String stringAsString = stringNode.getString();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[236]++;
int CodeCoverConditionCoverageHelper_C75;

    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (32)) == 0 || true) &&
 ((arg1 != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((arg1.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((arg1.getNext() == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 3) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 3) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[150]++;
      index = (int) arg1.getDouble();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[237]++;

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[151]++;
      return n;
    }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[238]++;
int CodeCoverConditionCoverageHelper_C76;

    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (8)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((stringAsString.length() <= index) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[152]++;
      // http://es5.github.com/#x15.5.4.5 says NaN is returned when index is
      // out of bounds but we bail.
      return n;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[153]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[239]++;

    Node resultNode = IR.number(stringAsString.charAt(index));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[240]++;
    Node parent = n.getParent();
    parent.replaceChild(n, resultNode);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[241]++;
    reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[242]++;
    return resultNode;
  }

  /**
   * Support function for jsSplit, find the first occurrence of
   * separator within stringValue starting at startIndex.
   */
  private int jsSplitMatch(String stringValue, int startIndex,
      String separator) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[243]++;
int CodeCoverConditionCoverageHelper_C77;

    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((startIndex + separator.length() > stringValue.length()) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[154]++;
      return -1;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[155]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[244]++;

    int matchIndex = stringValue.indexOf(separator, startIndex);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[245]++;
int CodeCoverConditionCoverageHelper_C78;

    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((matchIndex < 0) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[156]++;
      return -1;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[157]++;}

    return matchIndex;
  }

  /**
   * Implement the JS String.split method using a string separator.
   */
  private String[] jsSplit(String stringValue, String separator, int limit) {
    Preconditions.checkArgument(limit >= 0);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[246]++;
    Preconditions.checkArgument(stringValue != null);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[247]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[248]++;
int CodeCoverConditionCoverageHelper_C79;

    // For limits of 0, return an empty array
    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((limit == 0) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[158]++;
      return new String[0];

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[159]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[249]++;
int CodeCoverConditionCoverageHelper_C80;

    // If a separator is not specified, return the entire string as
    // the only element of an array.
    if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((separator == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[160]++;
      return new String[] {stringValue};

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[161]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[250]++;

    List<String> splitStrings = Lists.newArrayList();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[251]++;
int CodeCoverConditionCoverageHelper_C81;

    // If an empty string is specified for the separator, split apart each
    // character of the string.
    if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((separator.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[162]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[252]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[13]++;


int CodeCoverConditionCoverageHelper_C82;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((i < stringValue.length()) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((i < limit) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[13]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[14]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[15]++;
}
        splitStrings.add(stringValue.substring(i, i + 1));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[253]++;
      }

    } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[163]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[254]++;
      int startIndex = 0, matchIndex;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[255]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[16]++;


      while ((matchIndex =
          jsSplitMatch(stringValue, startIndex, separator)) >= 0 && splitStrings.size() < limit) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[16]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[17]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[18]++;
}
        splitStrings.add(stringValue.substring(startIndex, matchIndex));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[256]++;

        startIndex = matchIndex + separator.length();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[257]++;
      }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[258]++;
int CodeCoverConditionCoverageHelper_C84;

      if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((splitStrings.size() < limit) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[164]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[259]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((startIndex < stringValue.length()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[166]++;
          splitStrings.add(stringValue.substring(startIndex));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[260]++;

        } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[167]++;
          splitStrings.add("");
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[261]++;
        }

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[165]++;}
    }

    return splitStrings.toArray(new String[splitStrings.size()]);
  }

  /**
   * Try to fold .split() calls on strings
   */
  private Node tryFoldStringSplit(Node n, Node stringNode, Node arg1) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[262]++;
int CodeCoverConditionCoverageHelper_C86;
    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((late) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[168]++;
      return n;

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[169]++;}

    Preconditions.checkArgument(n.isCall());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[263]++;
    Preconditions.checkArgument(stringNode.isString());
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[264]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[265]++;

    String separator = null;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[266]++;
    String stringValue = stringNode.getString();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[267]++;

    // Maximum number of possible splits
    int limit = stringValue.length() + 1;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[268]++;
int CodeCoverConditionCoverageHelper_C87;

    if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((arg1 != null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[170]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[269]++;
int CodeCoverConditionCoverageHelper_C88;
      if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((arg1.isString()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[172]++;
        separator = arg1.getString();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[270]++;

      } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[173]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[271]++;
int CodeCoverConditionCoverageHelper_C89; if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((arg1.isNull()) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[174]++;
        return n;

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[175]++;}
}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[272]++;

      Node arg2 = arg1.getNext();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[273]++;
int CodeCoverConditionCoverageHelper_C90;
      if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((arg2 != null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[176]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[274]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((arg2.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[178]++;
          limit = Math.min((int) arg2.getDouble(), limit);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[275]++;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[276]++;
int CodeCoverConditionCoverageHelper_C92;
          if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((limit < 0) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[180]++;
            return n;

          } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[181]++;}

        } else {
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[179]++;
          return n;
        }

      } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[177]++;}

    } else {
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.branches[171]++;}
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[277]++;

    // Split the string and convert the returned array into JS nodes
    String[] stringArray = jsSplit(stringValue, separator, limit);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[278]++;
    Node arrayOfStrings = IR.arraylit();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[279]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[19]++;


int CodeCoverConditionCoverageHelper_C93;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((i < stringArray.length) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[19]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[20]--;
  CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.loops[21]++;
}
      arrayOfStrings.addChildToBack(
          IR.string(stringArray[i]).srcref(stringNode));
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[280]++;
    }
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[281]++;

    Node parent = n.getParent();
    parent.replaceChild(n, arrayOfStrings);
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[282]++;
    reportCodeChange();
CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl.statements[283]++;
    return arrayOfStrings;
  }
}

class CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl ());
  }
    public static long[] statements = new long[284];
    public static long[] branches = new long[182];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[94];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PeepholeReplaceKnownMethods.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,3,2,2,3,1,2,1,1,1,1,2,1,3,1,3,1,1,1,1,1,1,2,2,1,2,1,1,1,2,2,1,2,2,3,1,2,1,1,1,1,1,1,1,2,1,1,1,3,2,1,1,1,3,3,2,3,2,1,1,1,1,1,2,0,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 93; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$204wme0wlfhyy7p7megvz1onm0nxk6bzjfzobyu1trceeflbvl () {
    super("com.google.javascript.jscomp.PeepholeReplaceKnownMethods.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 283; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 181; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 93; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PeepholeReplaceKnownMethods.java");
      for (int i = 1; i <= 283; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 181; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 93; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

