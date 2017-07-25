/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * While statement.  Node type is {@link Token#WHILE}.<p>
 *
 * <pre><i>WhileStatement</i>:
 *     <b>while</b> <b>(</b> Expression <b>)</b> Statement</pre>
 */
public class WhileLoop extends Loop {
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.ping();
  }


    private AstNode condition;

    {
        type = Token.WHILE;
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[1]++;
    }

    public WhileLoop() {
    }

    public WhileLoop(int pos) {
        super(pos);
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[2]++;
    }

    public WhileLoop(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[3]++;
    }

    /**
     * Returns loop condition
     */
    public AstNode getCondition() {
        return condition;
    }

    /**
     * Sets loop condition
     * @throws IllegalArgumentException} if condition is {@code null}
     */
    public void setCondition(AstNode condition) {
        assertNotNull(condition);
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[4]++;
        this.condition = condition;
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[5]++;
        condition.setParent(this);
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[6]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[7]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[8]++;
        sb.append("while (");
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[9]++;
        sb.append(condition.toSource(0));
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[10]++;
        sb.append(") ");
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[11]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((body.getType() == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.branches[1]++;
            sb.append(body.toSource(depth).trim());
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[13]++;
            sb.append("\n");
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[14]++;

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.branches[2]++;
            sb.append("\n").append(body.toSource(depth+1));
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[15]++;
        }
        return sb.toString();
    }

    /**
     * Visits this node, the condition, then the body.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.branches[3]++;
            condition.visit(v);
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[17]++;
            body.visit(v);
CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.statements[18]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9.branches[4]++;}
    }
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9 ());
  }
    public static long[] statements = new long[19];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-WhileLoop.java";
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

  public CodeCoverCoverageCounter$iypomt0ag7yuozzh9loyxgrhr9vkt1imy7vq9 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-WhileLoop.java");
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-WhileLoop.java");
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

