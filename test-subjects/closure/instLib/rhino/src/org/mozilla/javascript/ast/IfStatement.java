/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * If-else statement.  Node type is {@link Token#IF}.<p>
 *
 * <pre><i>IfStatement</i> :
 *       <b>if</b> ( Expression ) Statement <b>else</b> Statement
 *       <b>if</b> ( Expression ) Statement</pre>
 */
public class IfStatement extends AstNode {
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.ping();
  }


    private AstNode condition;
    private AstNode thenPart;
    private int elsePosition = -1;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[1]++;
  }
    private AstNode elsePart;
    private int lp = -1;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[2]++;
  }
    private int rp = -1;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[3]++;
  }

    {
        type = Token.IF;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[4]++;
    }

    public IfStatement() {
    }

    public IfStatement(int pos) {
        super(pos);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[5]++;
    }

    public IfStatement(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[6]++;
    }

    /**
     * Returns if condition
     */
    public AstNode getCondition() {
        return condition;
    }

    /**
     * Sets if condition.
     * @throws IllegalArgumentException if {@code condition} is {@code null}.
     */
    public void setCondition(AstNode condition) {
        assertNotNull(condition);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[7]++;
        this.condition = condition;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[8]++;
        condition.setParent(this);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[9]++;
    }

    /**
     * Returns statement to execute if condition is true
     */
    public AstNode getThenPart() {
        return thenPart;
    }

    /**
     * Sets statement to execute if condition is true
     * @throws IllegalArgumentException if thenPart is {@code null}
     */
    public void setThenPart(AstNode thenPart) {
        assertNotNull(thenPart);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[10]++;
        this.thenPart = thenPart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[11]++;
        thenPart.setParent(this);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[12]++;
    }

    /**
     * Returns statement to execute if condition is false
     */
    public AstNode getElsePart() {
        return elsePart;
    }

    /**
     * Sets statement to execute if condition is false
     * @param elsePart statement to execute if condition is false.
     * Can be {@code null}.
     */
    public void setElsePart(AstNode elsePart) {
        this.elsePart = elsePart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[13]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((elsePart != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[1]++;
            elsePart.setParent(this);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[15]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[2]++;}
    }

    /**
     * Returns position of "else" keyword, or -1
     */
    public int getElsePosition() {
        return elsePosition;
    }

    /**
     * Sets position of "else" keyword, -1 if not present
     */
    public void setElsePosition(int elsePosition) {
        this.elsePosition = elsePosition;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[16]++;
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
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[17]++;
    }

    /**
     * Returns right paren position, -1 if missing
     */
    public int getRp() {
        return rp;
    }

    /**
     * Sets right paren position, -1 if missing
     */
    public void setRp(int rp) {
        this.rp = rp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[18]++;
    }

    /**
     * Sets both paren positions
     */
    public void setParens(int lp, int rp) {
        this.lp = lp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[19]++;
        this.rp = rp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[20]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[21]++;
        String pad = makeIndent(depth);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[22]++;
        StringBuilder sb = new StringBuilder(32);
        sb.append(pad);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[23]++;
        sb.append("if (");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[24]++;
        sb.append(condition.toSource(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[25]++;
        sb.append(") ");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[26]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[27]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((thenPart.getType() != Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[3]++;
            sb.append("\n").append(makeIndent(depth + 1));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[28]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[4]++;}
        sb.append(thenPart.toSource(depth).trim());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[29]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[30]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((elsePart != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[5]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((thenPart.getType() != Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[7]++;
                sb.append("\n").append(pad).append("else ");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[32]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[8]++;
                sb.append(" else ");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[33]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[34]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((elsePart.getType() != Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((elsePart.getType() != Token.IF) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[9]++;
                sb.append("\n").append(makeIndent(depth + 1));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[35]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[10]++;}
            sb.append(elsePart.toSource(depth).trim());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[36]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[6]++;}
        sb.append("\n");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[37]++;
        return sb.toString();
    }

    /**
     * Visits this node, the condition, the then-part, and
     * if supplied, the else-part.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[11]++;
            condition.visit(v);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[39]++;
            thenPart.visit(v);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[40]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((elsePart != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[13]++;
                elsePart.visit(v);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.statements[42]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[14]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t.branches[12]++;}
    }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-IfStatement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuucf6sd91nvbj55m6na64p5t () {
    super("org.mozilla.javascript.ast.RHINO-SRC-IfStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-IfStatement.java");
      for (int i = 1; i <= 42; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

