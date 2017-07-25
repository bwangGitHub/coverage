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
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.Node;

/**
 * A pass that looks for assignments to properties of an object or array
 * immediately following its creation using the abbreviated syntax.
 * <p>
 * E.g. {@code var a = [];a[0] = 0} is optimized to {@code var a = [0]} and
 * similarly for the object constructor.
 *
 */
public class PeepholeCollectPropertyAssignments
    extends AbstractPeepholeOptimization {
  static {
    CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.ping();
  }


  @Override
  Node optimizeSubtree(Node subtree) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((subtree.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((subtree.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[1]++;
      return subtree;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[2]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[2]++;

    boolean codeChanged = false;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;

    // Look for variable declarations or simple assignments
    // and start processing there.
    for (Node child = subtree.getFirstChild();(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); child = child.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[1]--;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[2]--;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[3]++;
}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[4]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((child.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(child)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[3]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[5]++;
        continue;

      } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[4]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[6]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isPropertyAssignmentToName(child.getNext())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[5]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[7]++;
        // Quick check to see if there's anything to collapse.
        continue;

      } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[6]++;}

      Preconditions.checkState(child.hasOneChild());
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[8]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[9]++;
      Node name = getName(child);
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((name.isName()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[7]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[11]++;
        // The assignment target is not a simple name.
        continue;

      } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[8]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[12]++;
      Node value = getValue(child);
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isInterestingValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[9]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[14]++;
        // No initializer or not an Object or Array literal.
        continue;

      } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[10]++;}

      Node propertyCandidate;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[4]++;


      while ((propertyCandidate = child.getNext()) != null) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[4]--;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[5]--;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[6]++;
}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[16]++;
int CodeCoverConditionCoverageHelper_C8;
        // This does not infinitely loop because collectProperty always
        // removes propertyCandidate from its parent when it returns true.
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((collectProperty(propertyCandidate, name.getString(), value)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[11]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[17]++;
          break;

        } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[12]++;}
        codeChanged = true;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[18]++;
      }
    }
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[19]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((codeChanged) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[13]++;
      reportCodeChange();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[20]++;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[14]++;}
    return subtree;
  }

  private Node getName(Node n) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[21]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[15]++;
      return n.getFirstChild();

    } else {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[16]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[22]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(n)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[17]++;
      return n.getFirstChild().getFirstChild();

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[18]++;}
}
    throw new IllegalStateException();
  }

  private Node getValue(Node n) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[23]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[19]++;
      return n.getFirstChild().getFirstChild();

    } else {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[20]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[24]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(n)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[21]++;
      return n.getFirstChild().getLastChild();

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[22]++;}
}
    throw new IllegalStateException();
  }

  boolean isInterestingValue(Node n) {
    return n.isObjectLit() || n.isArrayLit();
  }

  private boolean isPropertyAssignmentToName(Node propertyCandidate) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[25]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((propertyCandidate == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[23]++; return false;
 } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[24]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[26]++;
int CodeCoverConditionCoverageHelper_C15;
    // Must be an assignment...
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(propertyCandidate)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[25]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[26]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[27]++;

    Node expr = propertyCandidate.getFirstChild();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[28]++;

    // to a property...
    Node lhs = expr.getFirstChild();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[29]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(lhs)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[27]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[28]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[30]++;

    // of a variable.
    Node obj = lhs.getFirstChild();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[31]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((obj.isName()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[29]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[30]++;}

    return true;
  }

  private boolean collectProperty(
      Node propertyCandidate, String name, Node value) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[32]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((isPropertyAssignmentToName(propertyCandidate)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[31]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[32]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[33]++;

    Node lhs = propertyCandidate.getFirstChild().getFirstChild();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[34]++;
int CodeCoverConditionCoverageHelper_C19;
    // Must be an assignment to the recent variable...
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((name.equals(lhs.getFirstChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[33]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[34]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[35]++;

    Node rhs = lhs.getNext();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[36]++;
int CodeCoverConditionCoverageHelper_C20;
    // with a value that cannot change the values of the variables,
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((mayHaveSideEffects(rhs)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((NodeUtil.canBeSideEffected(rhs)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[35]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[36]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[37]++;
int CodeCoverConditionCoverageHelper_C21;
    // and does not have a reference to a variable initialized after it.
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((NodeUtil.isLiteralValue(rhs, true)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((mightContainForwardReference(rhs, name)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[37]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[38]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[38]++;

    switch (value.getType()) {
      case Token.ARRAYLIT:
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[39]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[39]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((collectArrayProperty(value, propertyCandidate)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[40]++;
          return false;

        } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[41]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[40]++;
        break;
      case Token.OBJECTLIT:
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[42]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[41]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((collectObjectProperty(value, propertyCandidate)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[43]++;
          return false;

        } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[44]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[42]++;
        break;
      default:
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[45]++;
        throw new IllegalStateException();
    }
    return true;
  }


  private boolean collectArrayProperty(
      Node arrayLiteral, Node propertyCandidate) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[43]++;
    Node assignment = propertyCandidate.getFirstChild();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[44]++;
    final int sizeOfArrayAtStart = arrayLiteral.getChildCount();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[45]++;
    int maxIndexAssigned = sizeOfArrayAtStart - 1;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[46]++;

    Node lhs = assignment.getFirstChild();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[47]++;
    Node rhs = lhs.getNext();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[48]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((lhs.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[46]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[47]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[49]++;
    Node obj = lhs.getFirstChild();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[50]++;
    Node property = obj.getNext();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[51]++;
int CodeCoverConditionCoverageHelper_C25;
    // The left hand side must have a numeric index
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((property.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[48]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[49]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[52]++;
    // that is a valid array index
    double dindex = property.getDouble();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[53]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C26 |= (32)) == 0 || true) &&
 ((dindex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (16)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((Double.isInfinite(dindex)) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((dindex > 0x7fffffffL) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 3) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[50]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[51]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[54]++;
    int index = (int) dindex;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[55]++;
int CodeCoverConditionCoverageHelper_C27;
    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((dindex != index) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[52]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[53]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[56]++;
int CodeCoverConditionCoverageHelper_C28;
    // that would not make the array so sparse that they take more space
    // when rendered than x[9]=1.
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((maxIndexAssigned + 4 < index) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[54]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[55]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[57]++;
int CodeCoverConditionCoverageHelper_C29;
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((index > maxIndexAssigned) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[56]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[58]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[7]++;


int CodeCoverConditionCoverageHelper_C30;
      while ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((maxIndexAssigned < index - 1) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[7]--;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[8]--;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[9]++;
}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[59]++;
        // Pad the array if it is sparse.
        // So if array is [0] and integer 3 is assigned at index is 2, then
        // we want to produce [0,,2].
        Node emptyNode = IR.empty().srcref(arrayLiteral);
        arrayLiteral.addChildToBack(emptyNode);
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[60]++;
        ++maxIndexAssigned;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[61]++;
      }
      arrayLiteral.addChildToBack(rhs.detachFromParent());
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[62]++;

    } else {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[57]++;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[63]++;
      // An out of order assignment.  Allow it if it's a hole.
      Node currentValue = arrayLiteral.getChildAtIndex(index);
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[64]++;
int CodeCoverConditionCoverageHelper_C31;
      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((currentValue.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[58]++;
        // We've already collected a value for this index.
        return false;

      } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[59]++;}
      arrayLiteral.replaceChild(currentValue, rhs.detachFromParent());
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[65]++;
    }

    propertyCandidate.detachFromParent();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[66]++;
    return true;
  }

  private boolean collectObjectProperty(
      Node objectLiteral, Node propertyCandidate) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[67]++;
    Node assignment = propertyCandidate.getFirstChild();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[68]++;
    Node lhs = assignment.getFirstChild(), rhs = lhs.getNext();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[69]++;
    Node obj = lhs.getFirstChild();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[70]++;
    Node property = obj.getNext();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[71]++;
int CodeCoverConditionCoverageHelper_C32;

    // The property must be statically known.
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (32)) == 0 || true) &&
 ((lhs.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (16)) == 0 || true)))
 && (!
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((property.isString()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((property.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[60]++;
      return false;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[61]++;}

    String propertyName;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[72]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((property.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[62]++;
      propertyName = NodeUtil.getStringValue(property);
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[73]++;

    } else {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[63]++;
      propertyName = property.getString();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[74]++;
    }
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[75]++;

    Node newProperty = IR.stringKey(propertyName)
        .copyInformationFrom(property);
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[76]++;
int CodeCoverConditionCoverageHelper_C34;
    // Preserve the quotedness of a property reference
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((lhs.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[64]++;
      newProperty.setQuotedString();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[77]++;

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[65]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[78]++;
    Node newValue = rhs.detachFromParent();
    newProperty.addChildToBack(newValue);
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[79]++;
    objectLiteral.addChildToBack(newProperty);
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[80]++;

    propertyCandidate.detachFromParent();
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[81]++;
    return true;
  }


  private static boolean mightContainForwardReference(
      Node node, String varName) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[82]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[66]++;
      return varName.equals(node.getString());

    } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[67]++;}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[83]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[10]++;


int CodeCoverConditionCoverageHelper_C36;
    for (Node child = node.getFirstChild();(((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false);
         child = child.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[10]--;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[11]--;
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.loops[12]++;
}
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.statements[84]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((mightContainForwardReference(child, varName)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[68]++;
        return true;

      } else {
  CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht.branches[69]++;}
    }
    return false;
  }

}

class CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht ());
  }
    public static long[] statements = new long[85];
    public static long[] branches = new long[70];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[38];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PeepholeCollectPropertyAssignments.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,2,1,1,2,0,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,3,1,1,1,1,1,3,1,1,1,1,1};
    for (int i = 1; i <= 37; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$13hp9i316o9tv23l9lvx1vwe4ihmwbzd7tkhadls9gl9wy1viznfmf2vmjiht () {
    super("com.google.javascript.jscomp.PeepholeCollectPropertyAssignments.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 84; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 69; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 37; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PeepholeCollectPropertyAssignments.java");
      for (int i = 1; i <= 84; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 69; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 37; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

