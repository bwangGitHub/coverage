/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for keyword literals:  currently, {@code this},
 * {@code null}, {@code true}, {@code false}, and {@code debugger}.
 * Node type is one of
 * {@link Token#THIS},
 * {@link Token#NULL},
 * {@link Token#TRUE},
 * {@link Token#FALSE}, or
 * {@link Token#DEBUGGER}.
 */
public class KeywordLiteral extends AstNode {
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.ping();
  }


    public KeywordLiteral() {
    }

    public KeywordLiteral(int pos) {
        super(pos);
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[1]++;
    }

    public KeywordLiteral(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[2]++;
    }

    /**
     * Constructs a new KeywordLiteral
     * @param nodeType the token type
     */
    public KeywordLiteral(int pos, int len, int nodeType) {
        super(pos, len);
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[3]++;
        setType(nodeType);
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[4]++;
    }

    /**
     * Sets node token type
     * @throws IllegalArgumentException if {@code nodeType} is unsupported
     */
    @Override
    public KeywordLiteral setType(int nodeType) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (512)) == 0 || true) &&
 ((nodeType == Token.THIS) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((nodeType == Token.NULL) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((nodeType == Token.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((nodeType == Token.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nodeType == Token.DEBUGGER) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 5) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 5) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.branches[1]++;
            throw new IllegalArgumentException("Invalid node type: "
                                               + nodeType);
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.branches[2]++;}
        type = nodeType;
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[6]++;
        return this;
    }

    /**
     * Returns true if the token type is {@link Token#TRUE} or
     * {@link Token#FALSE}.
     */
    public boolean isBooleanLiteral() {
        return type == Token.TRUE || type == Token.FALSE;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[7]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[8]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[9]++;
        switch (getType()) {
        case Token.THIS:
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.branches[3]++;
            sb.append("this");
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[10]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[11]++;
            break;
        case Token.NULL:
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.branches[4]++;
            sb.append("null");
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[12]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[13]++;
            break;
        case Token.TRUE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.branches[5]++;
            sb.append("true");
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[14]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[15]++;
            break;
        case Token.FALSE:
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.branches[6]++;
            sb.append("false");
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[16]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[17]++;
            break;
        case Token.DEBUGGER:
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.branches[7]++;
            sb.append("debugger;\n");
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[18]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[19]++;
            break;
        default:
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.branches[8]++;
            throw new IllegalStateException("Invalid keyword literal type: "
                                            + getType());
        }
        return sb.toString();
    }

    /**
     * Visits this node.  There are no children to visit.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl.statements[20]++;
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-KeywordLiteral.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3};
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevsilrlgaeqper7ho2pcv58mabezsrl () {
    super("org.mozilla.javascript.ast.RHINO-SRC-KeywordLiteral.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-KeywordLiteral.java");
      for (int i = 1; i <= 20; i++) {
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

