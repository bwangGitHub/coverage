/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node representing unary operators such as {@code ++},
 * {@code ~}, {@code typeof} and {@code delete}.  The type field
 * is set to the appropriate Token type for the operator.  The node length spans
 * from the operator to the end of the operand (for prefix operators) or from
 * the start of the operand to the operator (for postfix).<p>
 *
 * The {@code default xml namespace = &lt;expr&gt;} statement in E4X
 * (JavaScript 1.6) is represented as a {@code UnaryExpression} of node
 * type {@link Token#DEFAULTNAMESPACE}, wrapped with an
 * {@link ExpressionStatement}.
 */
public class UnaryExpression extends AstNode {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.ping();
  }


    private AstNode operand;
    private boolean isPostfix;

    public UnaryExpression() {
    }

    public UnaryExpression(int pos) {
        super(pos);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[1]++;
    }

    /**
     * Constructs a new postfix UnaryExpression
     */
    public UnaryExpression(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[2]++;
    }

    /**
     * Constructs a new prefix UnaryExpression.
     */
    public UnaryExpression(int operator, int operatorPosition,
                           AstNode operand) {
        this(operator, operatorPosition, operand, false);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[3]++;
    }

    /**
     * Constructs a new UnaryExpression with the specified operator
     * and operand.  It sets the parent of the operand, and sets its own bounds
     * to encompass the operator and operand.
     * @param operator the node type
     * @param operatorPosition the absolute position of the operator.
     * @param operand the operand expression
     * @param postFix true if the operator follows the operand.  Int
     * @throws IllegalArgumentException} if {@code operand} is {@code null}
     */
    public UnaryExpression(int operator, int operatorPosition,
                           AstNode operand, boolean postFix) {
        assertNotNull(operand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[4]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[5]++;
        int beg = postFix ? operand.getPosition() : operatorPosition;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[6]++;
        // JavaScript only has ++ and -- postfix operators, so length is 2
        int end = postFix
                  ? operatorPosition + 2
                  : operand.getPosition() + operand.getLength();
        setBounds(beg, end);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[7]++;
        setOperator(operator);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[8]++;
        setOperand(operand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[9]++;
        isPostfix = postFix;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[10]++;
    }

    /**
     * Returns operator token &ndash; alias for {@link #getType}
     */
    public int getOperator() {
        return type;
    }

    /**
     * Sets operator &ndash; same as {@link #setType}, but throws an
     * exception if the operator is invalid
     * @throws IllegalArgumentException if operator is not a valid
     * Token code
     */
    public void setOperator(int operator) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((Token.isValidToken(operator)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[1]++;
            throw new IllegalArgumentException("Invalid token: " + operator);
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[2]++;}
        setType(operator);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[12]++;
    }

    public AstNode getOperand() {
        return operand;
    }

    /**
     * Sets the operand, and sets its parent to be this node.
     * @throws IllegalArgumentException} if {@code operand} is {@code null}
     */
    public void setOperand(AstNode operand) {
        assertNotNull(operand);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[13]++;
        this.operand = operand;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[14]++;
        operand.setParent(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[15]++;
    }

    /**
     * Returns whether the operator is postfix
     */
    public boolean isPostfix() {
        return isPostfix;
    }

    /**
     * Returns whether the operator is prefix
     */
    public boolean isPrefix() {
        return !isPostfix;
    }

    /**
     * Sets whether the operator is postfix
     */
    public void setIsPostfix(boolean isPostfix) {
        this.isPostfix = isPostfix;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[16]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[17]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[18]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[19]++;
        int type = getType();
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isPostfix) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[3]++;
            sb.append(operatorToString(type));
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[21]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((type == Token.TYPEOF) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((type == Token.DELPROP) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((type == Token.VOID) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[5]++;
                sb.append(" ");
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[23]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[6]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[4]++;}
        sb.append(operand.toSource());
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[24]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isPostfix) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[7]++;
            sb.append(operatorToString(type));
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[26]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[8]++;}
        return sb.toString();
    }

    /**
     * Visits this node, then the operand.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[9]++;
            operand.visit(v);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.statements[28]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl.branches[10]++;}
    }
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-UnaryExpression.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpv8zfsrci0nugotqvahyxf4m1jbnhkrl () {
    super("org.mozilla.javascript.ast.RHINO-SRC-UnaryExpression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-UnaryExpression.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

