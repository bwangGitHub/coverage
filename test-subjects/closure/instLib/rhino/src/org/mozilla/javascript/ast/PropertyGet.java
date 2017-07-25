/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node for the '.' operator.  Node type is {@link Token#GETPROP}.
 */
public class PropertyGet extends InfixExpression {
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.ping();
  }


    {
        type = Token.GETPROP;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[1]++;
    }

    public PropertyGet() {
    }

    public PropertyGet(int pos) {
        super(pos);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[2]++;
    }

    public PropertyGet(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[3]++;
    }

    public PropertyGet(int pos, int len, AstNode target, Name property) {
        super(pos, len, target, property);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[4]++;
    }

    /**
     * Constructor.  Updates bounds to include left ({@code target}) and
     * right ({@code property}) nodes.
     */
    public PropertyGet(AstNode target, Name property) {
        super(target, property);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[5]++;
    }

    public PropertyGet(AstNode target, Name property, int dotPosition) {
        super(Token.GETPROP, target, property, dotPosition);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[6]++;
    }

    /**
     * Returns the object on which the property is being fetched.
     * Should never be {@code null}.
     */
    public AstNode getTarget() {
        return getLeft();
    }

    /**
     * Sets target object, and sets its parent to this node.
     * @param target expression evaluating to the object upon which
     * to do the property lookup
     * @throws IllegalArgumentException} if {@code target} is {@code null}
     */
    public void setTarget(AstNode target) {
        setLeft(target);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[7]++;
    }

    /**
     * Returns the property being accessed.
     */
    public Name getProperty() {
        return (Name)getRight();
    }

    /**
     * Sets the property being accessed, and sets its parent to this node.
     * @throws IllegalArgumentException} if {@code property} is {@code null}
     */
    public void setProperty(Name property) {
        setRight(property);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[8]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[9]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[10]++;
        sb.append(getLeft().toSource(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[11]++;
        sb.append(".");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[12]++;
        sb.append(getRight().toSource(0));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[13]++;
        return sb.toString();
    }

    /**
     * Visits this node, the target expression, and the property name.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.branches[1]++;
            getTarget().visit(v);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[15]++;
            getProperty().visit(v);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.statements[16]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t.branches[2]++;}
    }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-PropertyGet.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
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

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9id9v243le2ja9s4895ptm72o9t () {
    super("org.mozilla.javascript.ast.RHINO-SRC-PropertyGet.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-PropertyGet.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
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

