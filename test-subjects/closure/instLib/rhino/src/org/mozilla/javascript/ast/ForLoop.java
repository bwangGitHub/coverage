/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * C-style for-loop statement.
 * Node type is {@link Token#FOR}.<p>
 *
 * <pre><b>for</b> ( ExpressionNoInopt; Expressionopt ; Expressionopt ) Statement</pre>
 * <pre><b>for</b> ( <b>var</b> VariableDeclarationListNoIn; Expressionopt ; Expressionopt ) Statement</pre>
 */
public class ForLoop extends Loop {
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.ping();
  }


    private AstNode initializer;
    private AstNode condition;
    private AstNode increment;

    {
        type = Token.FOR;
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[1]++;
    }

    public ForLoop() {
    }

    public ForLoop(int pos) {
        super(pos);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[2]++;
    }

    public ForLoop(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[3]++;
    }

    /**
     * Returns loop initializer variable declaration list.
     * This is either a {@link VariableDeclaration}, an
     * {@link Assignment}, or an {@link InfixExpression} of
     * type COMMA that chains multiple variable assignments.
     */
    public AstNode getInitializer() {
        return initializer;
    }

    /**
     * Sets loop initializer expression, and sets its parent
     * to this node.  Virtually any expression can be in the initializer,
     * so no error-checking is done other than a {@code null}-check.
     * @param initializer loop initializer.  Pass an
     * {@link EmptyExpression} if the initializer is not specified.
     * @throws IllegalArgumentException if condition is {@code null}
     */
    public void setInitializer(AstNode initializer) {
        assertNotNull(initializer);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[4]++;
        this.initializer = initializer;
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[5]++;
        initializer.setParent(this);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[6]++;
    }

    /**
     * Returns loop condition
     */
    public AstNode getCondition() {
        return condition;
    }

    /**
     * Sets loop condition, and sets its parent to this node.
     * @param condition loop condition.  Pass an {@link EmptyExpression}
     * if the condition is missing.
     * @throws IllegalArgumentException} if condition is {@code null}
     */
    public void setCondition(AstNode condition) {
        assertNotNull(condition);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[7]++;
        this.condition = condition;
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[8]++;
        condition.setParent(this);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[9]++;
    }

    /**
     * Returns loop increment expression
     */
    public AstNode getIncrement() {
        return increment;
    }

    /**
     * Sets loop increment expression, and sets its parent to
     * this node.
     * @param increment loop increment expression.  Pass an
     * {@link EmptyExpression} if increment is {@code null}.
     * @throws IllegalArgumentException} if increment is {@code null}
     */
    public void setIncrement(AstNode increment) {
        assertNotNull(increment);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[10]++;
        this.increment = increment;
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[11]++;
        increment.setParent(this);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[12]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[13]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[14]++;
        sb.append("for (");
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[15]++;
        sb.append(initializer.toSource(0));
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[16]++;
        sb.append("; ");
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[17]++;
        sb.append(condition.toSource(0));
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[18]++;
        sb.append("; ");
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[19]++;
        sb.append(increment.toSource(0));
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[20]++;
        sb.append(") ");
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[21]++;
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[22]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((body.getType() == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.branches[1]++;
            sb.append(body.toSource(depth).trim()).append("\n");
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[23]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.branches[2]++;
            sb.append("\n").append(body.toSource(depth+1));
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[24]++;
        }
        return sb.toString();
    }

    /**
     * Visits this node, the initializer expression, the loop condition
     * expression, the increment expression, and then the loop body.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[25]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.branches[3]++;
            initializer.visit(v);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[26]++;
            condition.visit(v);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[27]++;
            increment.visit(v);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[28]++;
            body.visit(v);
CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.statements[29]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx.branches[4]++;}
    }
}

class CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ForLoop.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
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

  public CodeCoverCoverageCounter$di175yxae5e37vt07sxg8uxl9rssyji8gx () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ForLoop.java");
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ForLoop.java");
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

