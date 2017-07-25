/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node representing the ternary operator.  Node type is
 * {@link Token#HOOK}.
 *
 * <pre><i>ConditionalExpression</i> :
 *        LogicalORExpression
 *        LogicalORExpression ? AssignmentExpression
 *                            : AssignmentExpression</pre>
 *
 * <i>ConditionalExpressionNoIn</i> :
 *        LogicalORExpressionNoIn
 *        LogicalORExpressionNoIn ? AssignmentExpression
 *                                : AssignmentExpressionNoIn</pre>
 */
public class ConditionalExpression extends AstNode {
  static {
    CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.ping();
  }


    private AstNode testExpression;
    private AstNode trueExpression;
    private AstNode falseExpression;
    private int questionMarkPosition = -1;
  {
    CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[1]++;
  }
    private int colonPosition = -1;
  {
    CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[2]++;
  }

    {
        type = Token.HOOK;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[3]++;
    }

    public ConditionalExpression() {
    }

    public ConditionalExpression(int pos) {
        super(pos);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[4]++;
    }

    public ConditionalExpression(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[5]++;
    }

    /**
     * Returns test expression
     */
    public AstNode getTestExpression() {
        return testExpression;
    }

    /**
     * Sets test expression, and sets its parent.
     * @param testExpression test expression
     * @throws IllegalArgumentException if testExpression is {@code null}
     */
    public void setTestExpression(AstNode testExpression) {
        assertNotNull(testExpression);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[6]++;
        this.testExpression = testExpression;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[7]++;
        testExpression.setParent(this);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[8]++;
    }

    /**
     * Returns expression to evaluate if test is true
     */
    public AstNode getTrueExpression() {
        return trueExpression;
    }

    /**
     * Sets expression to evaluate if test is true, and
     * sets its parent to this node.
     * @param trueExpression expression to evaluate if test is true
     * @throws IllegalArgumentException if expression is {@code null}
     */
    public void setTrueExpression(AstNode trueExpression) {
        assertNotNull(trueExpression);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[9]++;
        this.trueExpression = trueExpression;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[10]++;
        trueExpression.setParent(this);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[11]++;
    }

    /**
     * Returns expression to evaluate if test is false
     */
    public AstNode getFalseExpression() {
        return falseExpression;
    }

    /**
     * Sets expression to evaluate if test is false, and sets its
     * parent to this node.
     * @param falseExpression expression to evaluate if test is false
     * @throws IllegalArgumentException if {@code falseExpression}
     * is {@code null}
     */
    public void setFalseExpression(AstNode falseExpression) {
        assertNotNull(falseExpression);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[12]++;
        this.falseExpression = falseExpression;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[13]++;
        falseExpression.setParent(this);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[14]++;
    }

    /**
     * Returns position of ? token
     */
    public int getQuestionMarkPosition() {
        return questionMarkPosition;
    }

    /**
     * Sets position of ? token
     * @param questionMarkPosition position of ? token
     */
    public void setQuestionMarkPosition(int questionMarkPosition) {
        this.questionMarkPosition = questionMarkPosition;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[15]++;
    }

    /**
     * Returns position of : token
     */
    public int getColonPosition() {
        return colonPosition;
    }

    /**
     * Sets position of : token
     * @param colonPosition position of : token
     */
    public void setColonPosition(int colonPosition) {
        this.colonPosition = colonPosition;
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[16]++;
    }

    @Override
    public boolean hasSideEffects() {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((testExpression == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((trueExpression == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((falseExpression == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.branches[1]++; codeBug();
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[18]++;
} else {
  CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.branches[2]++;}
        return trueExpression.hasSideEffects()
               && falseExpression.hasSideEffects();
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[19]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[20]++;
        sb.append(testExpression.toSource(depth));
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[21]++;
        sb.append(" ? ");
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[22]++;
        sb.append(trueExpression.toSource(0));
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[23]++;
        sb.append(" : ");
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[24]++;
        sb.append(falseExpression.toSource(0));
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[25]++;
        return sb.toString();
    }

    /**
     * Visits this node, then the test-expression, the true-expression,
     * and the false-expression.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.branches[3]++;
            testExpression.visit(v);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[27]++;
            trueExpression.visit(v);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[28]++;
            falseExpression.visit(v);
CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.statements[29]++;

        } else {
  CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d.branches[4]++;}
    }
}

class CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ConditionalExpression.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1};
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

  public CodeCoverCoverageCounter$41o4hx3ypdgwbkwrq485d7jc862pqh9k5sg24q07zq5yntwn98cyey5d () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ConditionalExpression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 29; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ConditionalExpression.java");
      for (int i = 1; i <= 29; i++) {
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

