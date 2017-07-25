/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * Throw statement.  Node type is {@link Token#THROW}.<p>
 *
 * <pre><i>ThrowStatement</i> :
 *      <b>throw</b> [<i>no LineTerminator here</i>] Expression ;</pre>
 */
public class ThrowStatement extends AstNode {
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.ping();
  }


    private AstNode expression;

    {
        type = Token.THROW;
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[1]++;
    }

    public ThrowStatement() {
    }

    public ThrowStatement(int pos) {
        super(pos);
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[2]++;
    }

    public ThrowStatement(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[3]++;
    }

    public ThrowStatement(AstNode expr) {
        setExpression(expr);
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[4]++;
    }

    public ThrowStatement(int pos, AstNode expr) {
        super(pos, expr.getLength());
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[5]++;
        setExpression(expr);
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[6]++;
    }

    public ThrowStatement(int pos, int len, AstNode expr) {
        super(pos, len);
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[7]++;
        setExpression(expr);
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[8]++;
    }

    /**
     * Returns the expression being thrown
     */
    public AstNode getExpression() {
        return expression;
    }

    /**
     * Sets the expression being thrown, and sets its parent
     * to this node.
     * @throws IllegalArgumentException} if expression is {@code null}
     */
    public void setExpression(AstNode expression) {
        assertNotNull(expression);
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[9]++;
        this.expression = expression;
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[10]++;
        expression.setParent(this);
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[11]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[12]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[13]++;
        sb.append("throw");
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[14]++;
        sb.append(" ");
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[15]++;
        sb.append(expression.toSource(0));
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[16]++;
        sb.append(";\n");
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[17]++;
        return sb.toString();
    }

    /**
     * Visits this node, then the thrown expression.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[18]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.branches[1]++;
            expression.visit(v);
CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.statements[19]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35.branches[2]++;}
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35 ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ThrowStatement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevxug8woow95gux9cjxizf7k1a6jz35 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ThrowStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ThrowStatement.java");
      for (int i = 1; i <= 19; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
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

