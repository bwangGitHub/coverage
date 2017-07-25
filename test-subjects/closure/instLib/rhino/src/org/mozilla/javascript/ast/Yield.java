/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for JavaScript 1.7 {@code yield} expression or statement.
 * Node type is {@link Token#YIELD}.<p>
 *
 * <pre><i>Yield</i> :
 *   <b>yield</b> [<i>no LineTerminator here</i>] [non-paren Expression] ;</pre>
 */
public class Yield extends AstNode {
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.ping();
  }


    private AstNode value;

    {
        type = Token.YIELD;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[1]++;
    }

    public Yield() {
    }

    public Yield(int pos) {
        super(pos);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[2]++;
    }

    public Yield(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[3]++;
    }

    public Yield(int pos, int len, AstNode value) {
        super(pos, len);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[4]++;
        setValue(value);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[5]++;
    }

    /**
     * Returns yielded expression, {@code null} if none
     */
    public AstNode getValue() {
        return value;
    }

    /**
     * Sets yielded expression, and sets its parent to this node.
     * @param expr the value to yield. Can be {@code null}.
     */
    public void setValue(AstNode expr) {
        this.value = expr;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[6]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((expr != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.branches[1]++;
            expr.setParent(this);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[8]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.branches[2]++;}
    }

    @Override
    public String toSource(int depth) {
        return value == null
                ? "yield"
                : "yield " + value.toSource(0);
    }

    /**
     * Visits this node, and if present, the yielded value.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.branches[3]++;
            value.visit(v);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.statements[10]++;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch.branches[4]++;}
    }
}

class CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-Yield.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2};
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

  public CodeCoverCoverageCounter$9m0i2o1yzvm0oa3buewdyc8xd9yy2ch () {
    super("org.mozilla.javascript.ast.RHINO-SRC-Yield.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-Yield.java");
      for (int i = 1; i <= 10; i++) {
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

