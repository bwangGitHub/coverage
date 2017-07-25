/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * Return statement.  Node type is {@link Token#RETURN}.<p>
 *
 * <pre><i>ReturnStatement</i> :
 *      <b>return</b> [<i>no LineTerminator here</i>] [Expression] ;</pre>
 */
public class ReturnStatement extends AstNode {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.ping();
  }


    private AstNode returnValue;

    {
        type = Token.RETURN;
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[1]++;
    }

    public ReturnStatement() {
    }

    public ReturnStatement(int pos) {
        super(pos);
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[2]++;
    }

    public ReturnStatement(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[3]++;
    }

    public ReturnStatement(int pos, int len, AstNode returnValue) {
        super(pos, len);
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[4]++;
        setReturnValue(returnValue);
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[5]++;
    }

    /**
     * Returns return value, {@code null} if return value is void
     */
    public AstNode getReturnValue() {
        return returnValue;
    }

    /**
     * Sets return value expression, and sets its parent to this node.
     * Can be {@code null}.
     */
    public void setReturnValue(AstNode returnValue) {
        this.returnValue = returnValue;
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[6]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((returnValue != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.branches[1]++;
            returnValue.setParent(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[8]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.branches[2]++;}
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[9]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[10]++;
        sb.append("return");
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[11]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((returnValue != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.branches[3]++;
            sb.append(" ");
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[13]++;
            sb.append(returnValue.toSource(0));
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[14]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.branches[4]++;}
        sb.append(";\n");
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[15]++;
        return sb.toString();
    }

    /**
     * Visits this node, then the return value if specified.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((returnValue != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.branches[5]++;
            returnValue.visit(v);
CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.statements[17]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ReturnStatement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpuw7zyft7qoje8g7hpink0cnaaisbiht () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ReturnStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ReturnStatement.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

