/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Switch-case AST node type.  The switch case is always part of a
 * switch statement.
 * Node type is {@link Token#CASE}.<p>
 *
 * <pre><i>CaseBlock</i> :
 *        { [CaseClauses] }
 *        { [CaseClauses] DefaultClause [CaseClauses] }
 * <i>CaseClauses</i> :
 *        CaseClause
 *        CaseClauses CaseClause
 * <i>CaseClause</i> :
 *        <b>case</b> Expression : [StatementList]
 * <i>DefaultClause</i> :
 *        <b>default</b> : [StatementList]</pre>
 */
public class SwitchCase extends AstNode {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.ping();
  }


    private AstNode expression;
    private List<AstNode> statements;

    {
        type = Token.CASE;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[1]++;
    }

    public SwitchCase() {
    }

    public SwitchCase(int pos) {
        super(pos);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[2]++;
    }

    public SwitchCase(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[3]++;
    }

    /**
     * Returns the case expression, {@code null} for default case
     */
    public AstNode getExpression() {
        return expression;
    }

    /**
     * Sets the case expression, {@code null} for default case.
     * Note that for empty fall-through cases, they still have
     * a case expression.  In {@code case 0: case 1: break;} the
     * first case has an {@code expression} that is a
     * {@link NumberLiteral} with value {@code 0}.
     */
    public void setExpression(AstNode expression) {
        this.expression = expression;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[4]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((expression != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[1]++;
            expression.setParent(this);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[6]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[2]++;}
    }

    /**
     * Return true if this is a default case.
     * @return true if {@link #getExpression} would return {@code null}
     */
    public boolean isDefault() {
        return expression == null;
    }

    /**
     * Returns statement list, which may be {@code null}.
     */
    public List<AstNode> getStatements() {
        return statements;
    }

    /**
     * Sets statement list.  May be {@code null}.  Replaces any existing
     * statements.  Each element in the list has its parent set to this node.
     */
    public void setStatements(List<AstNode> statements) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.statements != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[3]++;
            this.statements.clear();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[8]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[4]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[1]++;


        for (AstNode s : statements) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[3]++;
}
            addStatement(s);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[10]++;
        }
    }

    /**
     * Adds a statement to the end of the statement list.
     * Sets the parent of the new statement to this node, updates
     * its start offset to be relative to this node, and sets the
     * length of this node to include the new child.
     *
     * @param statement a child statement
     * @throws IllegalArgumentException} if statement is {@code null}
     */
    public void addStatement(AstNode statement) {
        assertNotNull(statement);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[11]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((statements == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[5]++;
            statements = new ArrayList<AstNode>();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[13]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[6]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[14]++;
        int end = statement.getPosition() + statement.getLength();
        this.setLength(end - this.getPosition());
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[15]++;
        statements.add(statement);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[16]++;
        statement.setParent(this);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[17]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[18]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[19]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((expression == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[7]++;
            sb.append("default:\n");
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[21]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[8]++;
            sb.append("case ");
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[22]++;
            sb.append(expression.toSource(0));
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[23]++;
            sb.append(":\n");
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[24]++;
        }
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((statements != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[9]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[4]++;


            for (AstNode s : statements) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[6]++;
}
                sb.append(s.toSource(depth+1));
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[27]++;
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[10]++;}
        return sb.toString();
    }

    /**
     * Visits this node, then the case expression if present, then
     * each statement (if any are specified).
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[11]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((expression != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[13]++;
                expression.visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[30]++;

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[14]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((statements != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[15]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[32]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[7]++;


                for (AstNode s : statements) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[7]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[8]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.loops[9]++;
}
                    s.visit(v);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.statements[33]++;
                }

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[16]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp.branches[12]++;}
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp ());
  }
    public static long[] statements = new long[34];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-SwitchCase.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw9lrbetxhbr4zbf0n87gneqp () {
    super("org.mozilla.javascript.ast.RHINO-SRC-SwitchCase.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 33; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-SwitchCase.java");
      for (int i = 1; i <= 33; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

