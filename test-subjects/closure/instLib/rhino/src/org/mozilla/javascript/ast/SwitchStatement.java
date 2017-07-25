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
 * Switch statement AST node type.
 * Node type is {@link Token#SWITCH}.<p>
 *
 * <pre><i>SwitchStatement</i> :
 *        <b>switch</b> ( Expression ) CaseBlock
 * <i>CaseBlock</i> :
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
public class SwitchStatement extends Jump {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.ping();
  }


    private static final List<SwitchCase> NO_CASES =
        Collections.unmodifiableList(new ArrayList<SwitchCase>());
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[1]++;
  }

    private AstNode expression;
    private List<SwitchCase> cases;
    private int lp = -1;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[2]++;
  }
    private int rp = -1;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[3]++;
  }

    {
        type = Token.SWITCH;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[4]++;
    }

    public SwitchStatement() {
    }

    public SwitchStatement(int pos) {
        // can't call super (Jump) for historical reasons
        position = pos;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[5]++;
    }

    public SwitchStatement(int pos, int len) {
        position = pos;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[6]++;
        length = len;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[7]++;
    }

    /**
     * Returns the switch discriminant expression
     */
    public AstNode getExpression() {
        return expression;
    }

    /**
     * Sets the switch discriminant expression, and sets its parent
     * to this node.
     * @throws IllegalArgumentException} if expression is {@code null}
     */
    public void setExpression(AstNode expression) {
        assertNotNull(expression);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[8]++;
        this.expression = expression;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[9]++;
        expression.setParent(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[10]++;
    }

    /**
     * Returns case statement list.  If there are no cases,
     * returns an immutable empty list.
     */
    public List<SwitchCase> getCases() {
        return cases != null ? cases : NO_CASES;
    }

    /**
     * Sets case statement list, and sets the parent of each child
     * case to this node.
     * @param cases list, which may be {@code null} to remove all the cases
     */
    public void setCases(List<SwitchCase> cases) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cases == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.branches[1]++;
            this.cases = null;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[12]++;

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.branches[2]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.cases != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.branches[3]++;
                this.cases.clear();
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[14]++;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.branches[4]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[1]++;


            for (SwitchCase sc : cases) { 
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[3]++;
}
                addCase(sc);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[16]++;
  }
        }
    }

    /**
     * Adds a switch case statement to the end of the list.
     * @throws IllegalArgumentException} if switchCase is {@code null}
     */
    public void addCase(SwitchCase switchCase) {
        assertNotNull(switchCase);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[17]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((cases == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.branches[5]++;
            cases = new ArrayList<SwitchCase>();
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[19]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.branches[6]++;}
        cases.add(switchCase);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[20]++;
        switchCase.setParent(this);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[21]++;
    }

    /**
     * Returns left paren position, -1 if missing
     */
    public int getLp() {
        return lp;
    }

    /**
     * Sets left paren position
     */
    public void setLp(int lp) {
        this.lp = lp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[22]++;
    }

    /**
     * Returns right paren position, -1 if missing
     */
    public int getRp() {
        return rp;
    }

    /**
     * Sets right paren position
     */
    public void setRp(int rp) {
        this.rp = rp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[23]++;
    }

    /**
     * Sets both paren positions
     */
    public void setParens(int lp, int rp) {
        this.lp = lp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[24]++;
        this.rp = rp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[25]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[26]++;
        String pad = makeIndent(depth);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[27]++;
        StringBuilder sb = new StringBuilder();
        sb.append(pad);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[28]++;
        sb.append("switch (");
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[29]++;
        sb.append(expression.toSource(0));
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[30]++;
        sb.append(") {\n");
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[31]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[32]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[4]++;


        for (SwitchCase sc : cases) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[4]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[5]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[6]++;
}
            sb.append(sc.toSource(depth + 1));
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[33]++;
        }
        sb.append(pad);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[34]++;
        sb.append("}\n");
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[35]++;
        return sb.toString();
    }

    /**
     * Visits this node, then the switch-expression, then the cases
     * in lexical order.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[36]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.branches[7]++;
            expression.visit(v);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[37]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[38]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[7]++;


            for (SwitchCase sc: getCases()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[7]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[8]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.loops[9]++;
}
                sc.visit(v);
CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.statements[39]++;
            }

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl.branches[8]++;}
    }
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-SwitchStatement.java";
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpv0q06jlce0v5huw4hb6w5xgti1vgsbl () {
    super("org.mozilla.javascript.ast.RHINO-SRC-SwitchStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
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
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-SwitchStatement.java");
      for (int i = 1; i <= 39; i++) {
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

