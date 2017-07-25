/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * With statement.  Node type is {@link Token#WITH}.<p>
 *
 * <pre><i>WithStatement</i> :
 *      <b>with</b> ( Expression ) Statement ;</pre>
 */
public class WithStatement extends AstNode {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.ping();
  }


    private AstNode expression;
    private AstNode statement;
    private int lp = -1;
  {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[1]++;
  }
    private int rp = -1;
  {
    CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[2]++;
  }

    {
        type = Token.WITH;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[3]++;
    }

    public WithStatement() {
    }

    public WithStatement(int pos) {
        super(pos);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[4]++;
    }

    public WithStatement(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[5]++;
    }

    /**
     * Returns object expression
     */
    public AstNode getExpression() {
        return expression;
    }

    /**
     * Sets object expression (and its parent link)
     * @throws IllegalArgumentException} if expression is {@code null}
     */
    public void setExpression(AstNode expression) {
        assertNotNull(expression);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[6]++;
        this.expression = expression;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[7]++;
        expression.setParent(this);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[8]++;
    }

    /**
     * Returns the statement or block
     */
    public AstNode getStatement() {
        return statement;
    }

    /**
     * Sets the statement (and sets its parent link)
     * @throws IllegalArgumentException} if statement is {@code null}
     */
    public void setStatement(AstNode statement) {
        assertNotNull(statement);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[9]++;
        this.statement = statement;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[10]++;
        statement.setParent(this);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[11]++;
    }

    /**
     * Returns left paren offset
     */
    public int getLp() {
      return lp;
    }

    /**
     * Sets left paren offset
     */
    public void setLp(int lp) {
      this.lp = lp;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[12]++;
    }

    /**
     * Returns right paren offset
     */
    public int getRp() {
      return rp;
    }

    /**
     * Sets right paren offset
     */
    public void setRp(int rp) {
      this.rp = rp;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[13]++;
    }

    /**
     * Sets both paren positions
     */
    public void setParens(int lp, int rp) {
        this.lp = lp;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[14]++;
        this.rp = rp;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[15]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[16]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[17]++;
        sb.append("with (");
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[18]++;
        sb.append(expression.toSource(0));
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[19]++;
        sb.append(") ");
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[20]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[21]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((statement.getType() == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.branches[1]++;
            sb.append(statement.toSource(depth).trim());
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[22]++;
            sb.append("\n");
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[23]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.branches[2]++;
            sb.append("\n").append(statement.toSource(depth + 1));
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[24]++;
        }
        return sb.toString();
    }

    /**
     * Visits this node, then the with-object, then the body statement.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[25]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.branches[3]++;
            expression.visit(v);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[26]++;
            statement.visit(v);
CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.statements[27]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5.branches[4]++;}
    }
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5 ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-WithStatement.java";
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

  public CodeCoverCoverageCounter$11f1r6z5fa12k16ldg35p0bczp5ynsignklyt9phz1b5 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-WithStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-WithStatement.java");
      for (int i = 1; i <= 27; i++) {
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

