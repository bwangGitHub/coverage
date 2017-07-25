/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node representing an infix (binary operator) expression.
 * The operator is the node's {@link Token} type.
 */
public class InfixExpression extends AstNode {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.ping();
  }


    protected AstNode left;
    protected AstNode right;
    protected int operatorPosition = -1;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[1]++;
  }

    public InfixExpression() {
    }

    public InfixExpression(int pos) {
        super(pos);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[2]++;
    }

    public InfixExpression(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[3]++;
    }

    public InfixExpression(int pos, int len,
                           AstNode left,
                           AstNode right) {
        super(pos, len);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[4]++;
        setLeft(left);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[5]++;
        setRight(right);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[6]++;
    }

    /**
     * Constructs a new {@code InfixExpression}.  Updates bounds to include
     * left and right nodes.
     */
    public InfixExpression(AstNode left, AstNode right) {
        setLeftAndRight(left, right);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[7]++;
    }

    /**
     * Constructs a new {@code InfixExpression}.
     * @param operatorPos the <em>absolute</em> position of the operator
     */
    public InfixExpression(int operator, AstNode left,
                           AstNode right, int operatorPos) {
        setType(operator);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[8]++;
        setOperatorPosition(operatorPos - left.getPosition());
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[9]++;
        setLeftAndRight(left, right);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[10]++;
    }

    public void setLeftAndRight(AstNode left, AstNode right) {
        assertNotNull(left);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[11]++;
        assertNotNull(right);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[12]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[13]++;
        // compute our bounds while children have absolute positions
        int beg = left.getPosition();
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[14]++;
        int end = right.getPosition() + right.getLength();
        setBounds(beg, end);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[15]++;

        // line number should agree with source position
        setLineno(left.getLineno());
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[16]++;

        // this updates their positions to be parent-relative
        setLeft(left);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[17]++;
        setRight(right);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[18]++;
    }

    /**
     * Returns operator token &ndash; alias for {@link #getType}
     */
    public int getOperator() {
        return getType();
    }

    /**
     * Sets operator token &ndash; like {@link #setType}, but throws
     * an exception if the operator is invalid.
     * @throws IllegalArgumentException if operator is not a valid token
     * code
     */
    public void setOperator(int operator) {
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((Token.isValidToken(operator)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.branches[1]++;
            throw new IllegalArgumentException("Invalid token: " + operator);
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.branches[2]++;}
        setType(operator);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[20]++;
    }

    /**
     * Returns the left-hand side of the expression
     */
    public AstNode getLeft() {
        return left;
    }

    /**
     * Sets the left-hand side of the expression, and sets its
     * parent to this node.
     * @param left the left-hand side of the expression
     * @throws IllegalArgumentException} if left is {@code null}
     */
    public void setLeft(AstNode left) {
        assertNotNull(left);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[21]++;
        this.left = left;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[22]++;
        // line number should agree with source position
        setLineno(left.getLineno());
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[23]++;
        left.setParent(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[24]++;
    }

    /**
     * Returns the right-hand side of the expression
     * @return the right-hand side.  It's usually an
     * {@link AstNode} node, but can also be a {@link FunctionNode}
     * representing Function expressions.
     */
    public AstNode getRight() {
        return right;
    }

    /**
     * Sets the right-hand side of the expression, and sets its parent to this
     * node.
     * @throws IllegalArgumentException} if right is {@code null}
     */
    public void setRight(AstNode right) {
        assertNotNull(right);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[25]++;
        this.right = right;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[26]++;
        right.setParent(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[27]++;
    }

    /**
     * Returns relative offset of operator token
     */
    public int getOperatorPosition() {
        return operatorPosition;
    }

    /**
     * Sets operator token's relative offset
     * @param operatorPosition offset in parent of operator token
     */
    public void setOperatorPosition(int operatorPosition) {
        this.operatorPosition = operatorPosition;
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[28]++;
    }

    @Override
    public boolean hasSideEffects() {
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[29]++;
        // the null-checks are for malformed expressions in IDE-mode
        switch (getType()) {
          case Token.COMMA:
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.branches[3]++;
              return right != null && right.hasSideEffects();
          case Token.AND:
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.branches[4]++;
          case Token.OR:
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.branches[5]++;
              return left != null && left.hasSideEffects()
                      || (right != null && right.hasSideEffects());
          default:
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.branches[6]++;
              return super.hasSideEffects();
        }
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[30]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[31]++;
        sb.append(left.toSource());
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[32]++;
        sb.append(" ");
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[33]++;
        sb.append(operatorToString(getType()));
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[34]++;
        sb.append(" ");
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[35]++;
        sb.append(right.toSource());
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[36]++;
        return sb.toString();
    }

    /**
     * Visits this node, the left operand, and the right operand.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[37]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.branches[7]++;
            left.visit(v);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[38]++;
            right.visit(v);
CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.statements[39]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p.branches[8]++;}
    }
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-InfixExpression.java";
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

  public CodeCoverCoverageCounter$1gk5ffks5utulpwptuisx06z5fefdbsl498q1m9c0lnc02p () {
    super("org.mozilla.javascript.ast.RHINO-SRC-InfixExpression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-InfixExpression.java");
      for (int i = 1; i <= 39; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
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

