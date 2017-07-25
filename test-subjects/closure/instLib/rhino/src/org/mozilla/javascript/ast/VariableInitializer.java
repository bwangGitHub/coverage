/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * A variable declaration or initializer, part of a {@link VariableDeclaration}
 * expression.  The variable "target" can be a simple name or a destructuring
 * form.  The initializer, if present, can be any expression.<p>
 *
 * Node type is one of {@link Token#VAR}, {@link Token#CONST}, or
 * {@link Token#LET}.<p>
 */
public class VariableInitializer extends AstNode {
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.ping();
  }


    private AstNode target;
    private AstNode initializer;

    {
        type = Token.VAR;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[1]++;
    }

    /**
     * Sets the node type.
     * @throws IllegalArgumentException if {@code nodeType} is not one of
     * {@link Token#VAR}, {@link Token#CONST}, or {@link Token#LET}
     */
    public void setNodeType(int nodeType) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((nodeType != Token.VAR) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((nodeType != Token.CONST) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((nodeType != Token.LET) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[1]++;
            throw new IllegalArgumentException("invalid node type");
} else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[2]++;}
        setType(nodeType);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[3]++;
    }

    public VariableInitializer() {
    }

    public VariableInitializer(int pos) {
        super(pos);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[4]++;
    }

    public VariableInitializer(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[5]++;
    }


    /**
     * Returns true if this is a destructuring assignment.  If so, the
     * initializer must be non-{@code null}.
     * @return {@code true} if the {@code target} field is a destructuring form
     * (an {@link ArrayLiteral} or {@link ObjectLiteral} node)
     */
    public boolean isDestructuring() {
        return !(target instanceof Name);
    }

    /**
     * Returns the variable name or destructuring form
     */
    public AstNode getTarget() {
        return target;
    }

    /**
     * Sets the variable name or destructuring form, and sets
     * its parent to this node.
     * @throws IllegalArgumentException if target is {@code null}
     */
    public void setTarget(AstNode target) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
        // Don't throw exception if target is an "invalid" node type.
        // See mozilla/js/tests/js1_7/block/regress-350279.js
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((target == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[3]++;
            throw new IllegalArgumentException("invalid target arg");
} else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[4]++;}
        this.target = target;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[7]++;
        target.setParent(this);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[8]++;
    }

    /**
     * Returns the initial value, or {@code null} if not provided
     */
    public AstNode getInitializer() {
        return initializer;
    }

    /**
     * Sets the initial value expression, and sets its parent to this node.
     * @param initializer the initial value.  May be {@code null}.
     */
    public void setInitializer(AstNode initializer) {
        this.initializer = initializer;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[9]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((initializer != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[5]++;
            initializer.setParent(this);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[11]++;
} else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[6]++;}
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[12]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[13]++;
        sb.append(target.toSource(0));
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[14]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((initializer != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[7]++;
            sb.append (" = ");
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[16]++;
            sb.append(initializer.toSource(0));
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[17]++;

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[8]++;}
        return sb.toString();
    }

    /**
     * Visits this node, then the target expression, then the initializer
     * expression if present.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[9]++;
            target.visit(v);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[19]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((initializer != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[11]++;
                initializer.visit(v);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.statements[21]++;

            } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl.branches[10]++;}
    }
}

class CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-VariableInitializer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$2vpd6pkpsoj3gojyhfumoxhtwgprfdjbn146p9z78ozlmmxjogcbl () {
    super("org.mozilla.javascript.ast.RHINO-SRC-VariableInitializer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-VariableInitializer.java");
      for (int i = 1; i <= 21; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

