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
 * A list of one or more var, const or let declarations.
 * Node type is {@link Token#VAR}, {@link Token#CONST} or
 * {@link Token#LET}.<p>
 *
 * If the node is for {@code var} or {@code const}, the node position
 * is the beginning of the {@code var} or {@code const} keyword.
 * For {@code let} declarations, the node position coincides with the
 * first {@link VariableInitializer} child.<p>
 *
 * A standalone variable declaration in a statement context returns {@code true}
 * from its {@link #isStatement()} method.
 */
public class VariableDeclaration extends AstNode {
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.ping();
  }


    private List<VariableInitializer> variables = new ArrayList<VariableInitializer>();
  {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[1]++;
  }
    private boolean isStatement;

    {
        type = Token.VAR;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[2]++;
    }

    public VariableDeclaration() {
    }

    public VariableDeclaration(int pos) {
        super(pos);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[3]++;
    }

    public VariableDeclaration(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[4]++;
    }

    /**
     * Returns variable list.  Never {@code null}.
     */
    public List<VariableInitializer> getVariables() {
        return variables;
    }

    /**
     * Sets variable list
     * @throws IllegalArgumentException if variables list is {@code null}
     */
    public void setVariables(List<VariableInitializer> variables) {
        assertNotNull(variables);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[5]++;
        this.variables.clear();
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[6]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[1]++;


        for (VariableInitializer vi : variables) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[1]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[2]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[3]++;
}
            addVariable(vi);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[8]++;
        }
    }

    /**
     * Adds a variable initializer node to the child list.
     * Sets initializer node's parent to this node.
     * @throws IllegalArgumentException if v is {@code null}
     */
    public void addVariable(VariableInitializer v) {
        assertNotNull(v);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[9]++;
        variables.add(v);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[10]++;
        v.setParent(this);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[11]++;
    }

    /**
     * Sets the node type and returns this node.
     * @throws IllegalArgumentException if {@code declType} is invalid
     */
    @Override
    public org.mozilla.javascript.Node setType(int type) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((type != Token.VAR) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((type != Token.CONST) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type != Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.branches[1]++;
            throw new IllegalArgumentException("invalid decl type: " + type);
} else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.branches[2]++;}
        return super.setType(type);
    }

    /**
     * Returns true if this is a {@code var} (not
     * {@code const} or {@code let}) declaration.
     * @return true if {@code declType} is {@link Token#VAR}
     */
    public boolean isVar() {
        return type == Token.VAR;
    }

    /**
     * Returns true if this is a {@link Token#CONST} declaration.
     */
    public boolean isConst() {
        return type == Token.CONST;
    }

    /**
     * Returns true if this is a {@link Token#LET} declaration.
     */
    public boolean isLet() {
        return type == Token.LET;
    }

    /**
     * Returns true if this node represents a statement.
     */
    public boolean isStatement() {
        return isStatement;
    }

    /**
     * Set or unset the statement flag.
     */
    public void setIsStatement(boolean isStatement) {
        this.isStatement = isStatement;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[13]++;
    }

    private String declTypeName() {
        return Token.typeToName(type).toLowerCase();
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[14]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[15]++;
        sb.append(declTypeName());
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[16]++;
        sb.append(" ");
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[17]++;
        printList(variables, sb);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[18]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isStatement()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.branches[3]++;
            sb.append(";\n");
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[20]++;

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.branches[4]++;}
        return sb.toString();
    }

    /**
     * Visits this node, then each {@link VariableInitializer} child.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.branches[5]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[22]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[4]++;


            for (AstNode var : variables) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[4]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[5]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.loops[6]++;
}
                var.visit(v);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.statements[23]++;
            }

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-VariableDeclaration.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1};
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprf2szjdhltucsw981csfikn23l () {
    super("org.mozilla.javascript.ast.RHINO-SRC-VariableDeclaration.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
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
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-VariableDeclaration.java");
      for (int i = 1; i <= 23; i++) {
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
      for (int i = 1; i <= 2; i++) {
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

