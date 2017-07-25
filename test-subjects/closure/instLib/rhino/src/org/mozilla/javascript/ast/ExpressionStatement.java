/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node representing an expression in a statement context.  The node type is
 * {@link Token#EXPR_VOID} if inside a function, or else
 * {@link Token#EXPR_RESULT} if inside a script.
 */
public class ExpressionStatement extends AstNode {
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.ping();
  }


    private AstNode expr;

    {
        type = Token.EXPR_VOID;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[1]++;
    }

    /**
     * Called by the parser to set node type to EXPR_RESULT
     * if this node is not within a Function.
     */
    public void setHasResult() {
        type = Token.EXPR_RESULT;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[2]++;
    }

    public ExpressionStatement() {
    }

    /**
     * Constructs a new {@code ExpressionStatement} wrapping
     * the specified expression.  Sets this node's position to the
     * position of the wrapped node, and sets the wrapped node's
     * position to zero.  Sets this node's length to the length of
     * the wrapped node.
     * @param expr the wrapped expression
     * @param hasResult {@code true} if this expression has side
     * effects.  If true, sets node type to EXPR_RESULT, else to EXPR_VOID.
     */
    public ExpressionStatement(AstNode expr, boolean hasResult) {
        this(expr);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[3]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((hasResult) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.branches[1]++; setHasResult();
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[5]++;
} else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.branches[2]++;}
    }

    /**
     * Constructs a new {@code ExpressionStatement} wrapping
     * the specified expression.  Sets this node's position to the
     * position of the wrapped node, and sets the wrapped node's
     * position to zero.  Sets this node's length to the length of
     * the wrapped node.
     * @param expr the wrapped expression
     */
    public ExpressionStatement(AstNode expr) {
        this(expr.getPosition(), expr.getLength(), expr);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[6]++;
    }

    public ExpressionStatement(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[7]++;
    }

    /**
     * Constructs a new {@code ExpressionStatement}
     * @param expr the wrapped {@link AstNode}.
     * The {@code ExpressionStatement}'s bounds are set to those of expr,
     * and expr's parent is set to this node.
     * @throws IllegalArgumentException if {@code expr} is null
     */
    public ExpressionStatement(int pos, int len, AstNode expr) {
        super(pos, len);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[8]++;
        setExpression(expr);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[9]++;
    }

    /**
     * Returns the wrapped expression
     */
    public AstNode getExpression() {
        return expr;
    }

    /**
     * Sets the wrapped expression, and sets its parent to this node.
     * @throws IllegalArgumentException} if expression is {@code null}
     */
    public void setExpression(AstNode expression) {
        assertNotNull(expression);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[10]++;
        expr = expression;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[11]++;
        expression.setParent(this);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[12]++;
        setLineno(expression.getLineno());
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[13]++;
    }

    /**
     * Returns true if this node has side effects
     * @throws IllegalStateException if expression has not yet
     * been set.
     */
    @Override
    public boolean hasSideEffects() {
        return type == Token.EXPR_RESULT || expr.hasSideEffects();
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[14]++;
        StringBuilder sb = new StringBuilder();
        sb.append(expr.toSource(depth));
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[15]++;
        sb.append(";\n");
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[16]++;
        return sb.toString();
    }

    /**
     * Visits this node, then the wrapped statement.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.branches[3]++;
            expr.visit(v);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.statements[18]++;

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt.branches[4]++;}
    }
}

class CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ExpressionStatement.java";
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

  public CodeCoverCoverageCounter$2vpd6pkpsoj3gojydjjkikvyul7e83dwehx5by6we5ne2ifzmhxlt () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ExpressionStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ExpressionStatement.java");
      for (int i = 1; i <= 18; i++) {
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

