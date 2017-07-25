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
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * Tries to fuse all the statements in a block into a one statement by using
 * COMMAs.
 *
 * Because COMMAs has the lowest precedence, we never need to insert
 * extra () around. Once we have only one statement in a block, we can then
 * eliminate a pair of {}'s. Further more, we can also fold a single
 * statement IF into && or create further opportunities for all the other
 * goodies in {@link PeepholeSubstituteAlternateSyntax}.
 *
 */
public class StatementFusion extends AbstractPeepholeOptimization {
  static {
    CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.ping();
  }


  @Override
  Node optimizeSubtree(Node n) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
    // The block of a function body always need { }.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((n.getParent().isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((canFuseIntoOneStatement(n)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[1]++;
      fuseIntoOneStatement(n);
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[2]++;
      reportCodeChange();
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[3]++;

    } else {
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[2]++;}
    return n;
  }

  private boolean canFuseIntoOneStatement(Node block) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
    // Fold only statement block. NOT scripts block.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((block.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[3]++;
      return false;

    } else {
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[4]++;}
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;

    // Nothing to do here.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((block.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((block.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[5]++;
      return false;

    } else {
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[6]++;}
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[6]++;

    Node last = block.getLastChild();
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;

    for (Node c = block.getFirstChild();(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[1]--;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[2]--;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[3]++;
}
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[8]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((c.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c != last) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[7]++;
        return false;

      } else {
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[8]++;}
    }
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[9]++;

    // TODO(user): Support more control statement for fusion.
    // FOR
    switch(last.getType()) {
      case Token.IF:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[9]++;
      case Token.THROW:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[10]++;
      case Token.SWITCH:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[11]++;
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[12]++;
        return true;
      case Token.RETURN:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[13]++;
        // We don't want to add a new return value.
        return last.hasChildren();
      case Token.FOR:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[14]++;
        return NodeUtil.isForIn(last) &&
            // Avoid cases where we have for(var x = foo() in a) { ....
            !mayHaveSideEffects(last.getFirstChild()); default : CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[15]++;
    }

    return false;
  }

  private void fuseIntoOneStatement(Node block) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[10]++;
    Node cur = block.removeFirstChild();
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[11]++;

    // Starts building a tree.
    Node commaTree = cur.removeFirstChild();
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[12]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;


    while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((block.hasMoreThanOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[4]--;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[5]--;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[6]++;
}
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[13]++;
      Node next = block.removeFirstChild().removeFirstChild();
      commaTree = fuseExpressionIntoExpression(commaTree, next);
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[14]++;
    }

    Preconditions.checkState(block.hasOneChild());
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[15]++;
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[16]++;
    Node last = block.getLastChild();
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[17]++;

    // Now we are just left with two statements. The comma tree of the first
    // n - 1 statements (which can be used in an expression) and the last
    // statement. We perform specific fusion based on the last statement's type.
    switch(last.getType()) {
      case Token.IF:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[16]++;
      case Token.RETURN:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[17]++;
      case Token.THROW:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[18]++;
      case Token.SWITCH:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[19]++;
      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[20]++;
        fuseExpresssonIntoFirstChild(commaTree, last);
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[18]++;
        return;
      case Token.FOR:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[21]++;
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(last)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[22]++;
          fuseExpresssonIntoSecondChild(commaTree, last);
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[20]++;

        } else {
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[23]++;}
        return ;
      default:
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[24]++;
        throw new IllegalStateException("Statement fusion missing.");
    }
  }

  // exp1, exp1
  private static Node fuseExpressionIntoExpression(Node exp1, Node exp2) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[21]++;
    Node comma = new Node(Token.COMMA, exp1);
    comma.copyInformationFrom(exp2);
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[22]++;
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;

    // We can just join the new comma expression with another comma but
    // lets keep all the comma's in a straight line. That way we can use
    // tree comparison.
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((exp2.isComma()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[25]++;
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[24]++;
      Node leftMostChild = exp2;
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[25]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[7]++;


int CodeCoverConditionCoverageHelper_C9;
      while((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((leftMostChild.isComma()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[7]--;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[8]--;
  CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.loops[9]++;
}
        leftMostChild = leftMostChild.getFirstChild();
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[26]++;
      }
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[27]++;
      Node parent = leftMostChild.getParent();
      comma.addChildToBack(leftMostChild.detachFromParent());
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[28]++;
      parent.addChildToFront(comma);
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[29]++;
      return exp2;

    } else {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.branches[26]++;
      comma.addChildToBack(exp2);
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[30]++;
      return comma;
    }
  }

  private static void fuseExpresssonIntoFirstChild(Node exp, Node stmt) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[31]++;
    Node val = stmt.removeFirstChild();
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[32]++;
    Node comma = fuseExpressionIntoExpression(exp, val);
    stmt.addChildToFront(comma);
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[33]++;
  }

  private static void fuseExpresssonIntoSecondChild(Node exp, Node stmt) {
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[34]++;
    Node val = stmt.removeChildAfter(stmt.getFirstChild());
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[35]++;
    Node comma = fuseExpressionIntoExpression(exp, val);
    stmt.addChildAfter(comma, stmt.getFirstChild());
CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81.statements[36]++;
  }
}

class CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81 ());
  }
    public static long[] statements = new long[37];
    public static long[] branches = new long[27];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.StatementFusion.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,2,1,2,1,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$9qxyqcy46bflz9k88qflr1nubz0no81 () {
    super("com.google.javascript.jscomp.StatementFusion.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 36; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 26; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.StatementFusion.java");
      for (int i = 1; i <= 36; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 26; i++) {
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

