/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for let statements and expressions.
 * Node type is {@link Token#LET} or {@link Token#LETEXPR}.<p>
 *
 * <pre> <i>LetStatement</i>:
 *     <b>let</b> ( VariableDeclarationList ) Block
 * <i>LetExpression</i>:
 *     <b>let</b> ( VariableDeclarationList ) Expression</pre>
 *
 * Note that standalone let-statements with no parens or body block,
 * such as {@code let x=6, y=7;}, are represented as a
 * {@link VariableDeclaration} node of type {@code Token.LET},
 * wrapped with an {@link ExpressionStatement}.<p>
 */
public class LetNode extends Scope {
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.ping();
  }


    private VariableDeclaration variables;
    private AstNode body;
    private int lp = -1;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[1]++;
  }
    private int rp = -1;
  {
    CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[2]++;
  }

    {
        type = Token.LETEXPR;
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[3]++;
    }

    public LetNode() {
    }

    public LetNode(int pos) {
        super(pos);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[4]++;
    }

    public LetNode(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[5]++;
    }

    /**
     * Returns variable list
     */
    public VariableDeclaration getVariables() {
        return variables;
    }

    /**
     * Sets variable list.  Sets list parent to this node.
     * @throws IllegalArgumentException if variables is {@code null}
     */
    public void setVariables(VariableDeclaration variables) {
        assertNotNull(variables);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[6]++;
        this.variables = variables;
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[7]++;
        variables.setParent(this);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[8]++;
    }

    /**
     * Returns body statement or expression.  Body is {@code null} if the
     * form of the let statement is similar to a VariableDeclaration, with no
     * curly-brace.  (This form is used to define let-bound variables in the
     * scope of the current block.)<p>
     *
     * @return the body form
     */
    public AstNode getBody() {
        return body;
    }

    /**
     * Sets body statement or expression.  Also sets the body parent to this
     * node.
     * @param body the body statement or expression.  May be
     * {@code null}.
     */
    public void setBody(AstNode body) {
        this.body = body;
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[9]++;
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((body != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.branches[1]++;
            body.setParent(this);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[11]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.branches[2]++;}
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
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[12]++;
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
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[13]++;
    }

    /**
     * Sets both paren positions
     */
    public void setParens(int lp, int rp) {
        this.lp = lp;
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[14]++;
        this.rp = rp;
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[15]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[16]++;
        String pad = makeIndent(depth);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[17]++;
        StringBuilder sb = new StringBuilder();
        sb.append(pad);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[18]++;
        sb.append("let (");
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[19]++;
        printList(variables.getVariables(), sb);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[20]++;
        sb.append(") ");
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[21]++;
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((body != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.branches[3]++;
            sb.append(body.toSource(depth));
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[23]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.branches[4]++;}
        return sb.toString();
    }

    /**
     * Visits this node, the variable list, and if present, the body
     * expression or statement.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.branches[5]++;
            variables.visit(v);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[25]++;
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((body != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.branches[7]++;
                body.visit(v);
CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.statements[27]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-LetNode.java";
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

  public CodeCoverCoverageCounter$di175yxae5e37vt0e8qb3qox6wxwblp84h () {
    super("org.mozilla.javascript.ast.RHINO-SRC-LetNode.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-LetNode.java");
      for (int i = 1; i <= 27; i++) {
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

