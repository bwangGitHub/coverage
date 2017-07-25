/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Try/catch/finally statement.  Node type is {@link Token#TRY}.<p>
 *
 * <pre><i>TryStatement</i> :
 *        <b>try</b> Block Catch
 *        <b>try</b> Block Finally
 *        <b>try</b> Block Catch Finally
 * <i>Catch</i> :
 *        <b>catch</b> ( <i><b>Identifier</b></i> ) Block
 * <i>Finally</i> :
 *        <b>finally</b> Block</pre>
 */
public class TryStatement extends AstNode {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.ping();
  }


    private static final List<CatchClause> NO_CATCHES =
        Collections.unmodifiableList(new ArrayList<CatchClause>());
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[1]++;
  }

    private AstNode tryBlock;
    private List<CatchClause> catchClauses;
    private AstNode finallyBlock;
    private int finallyPosition = -1;
  {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[2]++;
  }

    {
        type = Token.TRY;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[3]++;
    }

    public TryStatement() {
    }

    public TryStatement(int pos) {
        super(pos);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[4]++;
    }

    public TryStatement(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[5]++;
    }

    public AstNode getTryBlock() {
        return tryBlock;
    }

    /**
     * Sets try block.  Also sets its parent to this node.
     * @throws IllegalArgumentException} if {@code tryBlock} is {@code null}
     */
    public void setTryBlock(AstNode tryBlock) {
        assertNotNull(tryBlock);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[6]++;
        this.tryBlock = tryBlock;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[7]++;
        tryBlock.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[8]++;
    }

    /**
     * Returns list of {@link CatchClause} nodes.  If there are no catch
     * clauses, returns an immutable empty list.
     */
    public List<CatchClause> getCatchClauses() {
        return catchClauses != null ? catchClauses : NO_CATCHES;
    }

    /**
     * Sets list of {@link CatchClause} nodes.  Also sets their parents
     * to this node.  May be {@code null}.  Replaces any existing catch
     * clauses for this node.
     */
    public void setCatchClauses(List<CatchClause> catchClauses) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((catchClauses == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[1]++;
            this.catchClauses = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[10]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[2]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.catchClauses != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[3]++;
                this.catchClauses.clear();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[12]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[4]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[1]++;


            for (CatchClause cc : catchClauses) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[3]++;
}
                addCatchClause(cc);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[14]++;
            }
        }
    }

    /**
     * Add a catch-clause to the end of the list, and sets its parent to
     * this node.
     * @throws IllegalArgumentException} if {@code clause} is {@code null}
     */
    public void addCatchClause(CatchClause clause) {
        assertNotNull(clause);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[15]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((catchClauses == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[5]++;
            catchClauses = new ArrayList<CatchClause>();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[17]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[6]++;}
        catchClauses.add(clause);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[18]++;
        clause.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[19]++;
    }

    /**
     * Returns finally block, or {@code null} if not present
     */
    public AstNode getFinallyBlock() {
        return finallyBlock;
    }

    /**
     * Sets finally block, and sets its parent to this node.
     * May be {@code null}.
     */
    public void setFinallyBlock(AstNode finallyBlock) {
        this.finallyBlock = finallyBlock;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[20]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((finallyBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[7]++;
            finallyBlock.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[22]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[8]++;}
    }

    /**
     * Returns position of {@code finally} keyword, if present, or -1
     */
    public int getFinallyPosition() {
        return finallyPosition;
    }

    /**
     * Sets position of {@code finally} keyword, if present, or -1
     */
    public void setFinallyPosition(int finallyPosition) {
        this.finallyPosition = finallyPosition;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[23]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[24]++;
        StringBuilder sb = new StringBuilder(250);
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[25]++;
        sb.append("try ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[26]++;
        sb.append(tryBlock.toSource(depth).trim());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[27]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[28]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[4]++;


        for (CatchClause cc : getCatchClauses()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[6]++;
}
            sb.append(cc.toSource(depth));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[29]++;
        }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[30]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((finallyBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[9]++;
            sb.append(" finally ");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[31]++;
            sb.append(finallyBlock.toSource(depth));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[32]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[10]++;}
        return sb.toString();
    }

    /**
     * Visits this node, then the try-block, then any catch clauses,
     * and then any finally block.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[11]++;
            tryBlock.visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[34]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[35]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[7]++;


            for (CatchClause cc : getCatchClauses()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[7]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[8]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.loops[9]++;
}
                cc.visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[36]++;
            }
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[37]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((finallyBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[13]++;
                finallyBlock.visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.statements[38]++;

            } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[14]++;}

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t.branches[12]++;}
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t ());
  }
    public static long[] statements = new long[39];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-TryStatement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmo2rdri4zo07tiqfpki6jrttu1t () {
    super("org.mozilla.javascript.ast.RHINO-SRC-TryStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 38; i++) {
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
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-TryStatement.java");
      for (int i = 1; i <= 38; i++) {
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
      for (int i = 1; i <= 3; i++) {
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

