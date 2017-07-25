/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * Node representing a catch-clause of a try-statement.
 * Node type is {@link Token#CATCH}.
 *
 * <pre><i>CatchClause</i> :
 *        <b>catch</b> ( <i><b>Identifier</b></i> [<b>if</b> Expression] ) Block</pre>
 */
public class CatchClause extends AstNode {
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.ping();
  }


    private Name varName;
    private AstNode catchCondition;
    private Block body;
    private int ifPosition = -1;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[1]++;
  }
    private int lp = -1;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[2]++;
  }
    private int rp = -1;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[3]++;
  }

    {
        type = Token.CATCH;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[4]++;
    }

    public CatchClause() {
    }

    public CatchClause(int pos) {
        super(pos);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[5]++;
    }

    public CatchClause(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[6]++;
    }

    /**
     * Returns catch variable node
     * @return catch variable
     */
    public Name getVarName() {
        return varName;
    }

    /**
     * Sets catch variable node, and sets its parent to this node.
     * @param varName catch variable
     * @throws IllegalArgumentException if varName is {@code null}
     */
    public void setVarName(Name varName) {
        assertNotNull(varName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[7]++;
        this.varName = varName;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[8]++;
        varName.setParent(this);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[9]++;
    }

    /**
     * Returns catch condition node, if present
     * @return catch condition node, {@code null} if not present
     */
    public AstNode getCatchCondition() {
        return catchCondition;
    }

    /**
     * Sets catch condition node, and sets its parent to this node.
     * @param catchCondition catch condition node.  Can be {@code null}.
     */
    public void setCatchCondition(AstNode catchCondition) {
        this.catchCondition = catchCondition;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[10]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((catchCondition != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.branches[1]++;
            catchCondition.setParent(this);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[12]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.branches[2]++;}
    }

    /**
     * Returns catch body
     */
    public Block getBody() {
        return body;
    }

    /**
     * Sets catch body, and sets its parent to this node.
     * @throws IllegalArgumentException if body is {@code null}
     */
    public void setBody(Block body) {
        assertNotNull(body);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[13]++;
        this.body = body;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[14]++;
        body.setParent(this);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[15]++;
    }

    /**
     * Returns left paren position
     */
    public int getLp() {
        return lp;
    }

    /**
     * Sets left paren position
     */
    public void setLp(int lp) {
        this.lp = lp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[16]++;
    }

    /**
     * Returns right paren position
     */
    public int getRp() {
        return rp;
    }

    /**
     * Sets right paren position
     */
    public void setRp(int rp) {
        this.rp = rp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[17]++;
    }

    /**
     * Sets both paren positions
     */
    public void setParens(int lp, int rp) {
        this.lp = lp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[18]++;
        this.rp = rp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[19]++;
    }

    /**
     * Returns position of "if" keyword
     * @return position of "if" keyword, if present, or -1
     */
    public int getIfPosition() {
        return ifPosition;
    }

    /**
     * Sets position of "if" keyword
     * @param ifPosition position of "if" keyword, if present, or -1
     */
    public void setIfPosition(int ifPosition) {
        this.ifPosition = ifPosition;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[20]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[21]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[22]++;
        sb.append("catch (");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[23]++;
        sb.append(varName.toSource(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[24]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[25]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((catchCondition != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.branches[3]++;
            sb.append(" if ");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[26]++;
            sb.append(catchCondition.toSource(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[27]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.branches[4]++;}
        sb.append(") ");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[28]++;
        sb.append(body.toSource(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[29]++;
        return sb.toString();
    }

    /**
     * Visits this node, the catch var name node, the condition if
     * non-{@code null}, and the catch body.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[30]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.branches[5]++;
            varName.visit(v);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[31]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[32]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((catchCondition != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.branches[7]++;
                catchCondition.visit(v);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[33]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.branches[8]++;}
            body.visit(v);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.statements[34]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5 ());
  }
    public static long[] statements = new long[35];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-CatchClause.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9ici0iz12jyyfw017f5vumr6ov5 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-CatchClause.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 34; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-CatchClause.java");
      for (int i = 1; i <= 34; i++) {
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
    for (int i = 1; i <= 4; i++) {
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

