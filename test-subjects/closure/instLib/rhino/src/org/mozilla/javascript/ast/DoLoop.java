/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * Do statement.  Node type is {@link Token#DO}.<p>
 *
 * <pre><i>DoLoop</i>:
 * <b>do</b> Statement <b>while</b> <b>(</b> Expression <b>)</b> <b>;</b></pre>
 */
public class DoLoop extends Loop {
  static {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.ping();
  }


    private AstNode condition;
    private int whilePosition = -1;
  {
    CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[1]++;
  }

    {
        type = Token.DO;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[2]++;
    }

    public DoLoop() {
    }

    public DoLoop(int pos) {
        super(pos);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[3]++;
    }

    public DoLoop(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[4]++;
    }

    /**
     * Returns loop condition
     */
    public AstNode getCondition() {
        return condition;
    }

    /**
     * Sets loop condition, and sets its parent to this node.
     * @throws IllegalArgumentException if condition is null
     */
    public void setCondition(AstNode condition) {
        assertNotNull(condition);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[5]++;
        this.condition = condition;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[6]++;
        condition.setParent(this);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[7]++;
    }

    /**
     * Returns source position of "while" keyword
     */
    public int getWhilePosition() {
        return whilePosition;
    }

    /**
     * Sets source position of "while" keyword
     */
    public void setWhilePosition(int whilePosition) {
        this.whilePosition = whilePosition;
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[8]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[9]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[10]++;
        sb.append("do ");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[11]++;
        sb.append(body.toSource(depth).trim());
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[12]++;
        sb.append(" while (");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[13]++;
        sb.append(condition.toSource(0));
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[14]++;
        sb.append(");\n");
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[15]++;
        return sb.toString();
    }

    /**
     * Visits this node, the body, and then the while-expression.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.branches[1]++;
            body.visit(v);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[17]++;
            condition.visit(v);
CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.statements[18]++;

        } else {
  CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd.branches[2]++;}
    }
}

class CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-DoLoop.java";
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

  public CodeCoverCoverageCounter$1wcjkiz20v4sksnrl1kd3k314p8x49wtd () {
    super("org.mozilla.javascript.ast.RHINO-SRC-DoLoop.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-DoLoop.java");
      for (int i = 1; i <= 18; i++) {
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

