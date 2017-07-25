/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * A break statement.  Node type is {@link Token#BREAK}.<p>
 *
 * <pre><i>BreakStatement</i> :
 *   <b>break</b> [<i>no LineTerminator here</i>] [Identifier] ;</pre>
 */
public class BreakStatement extends Jump {
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.ping();
  }


    private Name breakLabel;
    private AstNode target;

    {
        type = Token.BREAK;
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[1]++;
    }

    public BreakStatement() {
    }

    public BreakStatement(int pos) {
        // can't call super (Jump) for historical reasons
        position = pos;
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[2]++;
    }

    public BreakStatement(int pos, int len) {
        position = pos;
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[3]++;
        length = len;
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[4]++;
    }

    /**
     * Returns the intended label of this break statement
     * @return the break label.  {@code null} if the source code did
     * not specify a specific break label via "break &lt;target&gt;".
     */
    public Name getBreakLabel() {
        return breakLabel;
    }

    /**
     * Sets the intended label of this break statement, e.g.  'foo'
     * in "break foo". Also sets the parent of the label to this node.
     * @param label the break label, or {@code null} if the statement is
     * just the "break" keyword by itself.
     */
    public void setBreakLabel(Name label) {
        breakLabel = label;
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[5]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.branches[1]++;
            label.setParent(this);
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[7]++;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.branches[2]++;}
    }

    /**
     * Returns the statement to break to
     * @return the break target.  Only {@code null} if the source
     * code has an error in it.
     */
    public AstNode getBreakTarget() {
        return target;
    }

    /**
     * Sets the statement to break to.
     * @param target the statement to break to
     * @throws IllegalArgumentException if target is {@code null}
     */
    public void setBreakTarget(Jump target) {
        assertNotNull(target);
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[8]++;
        this.target = target;
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[9]++;
        setJumpStatement(target);
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[10]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[11]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[12]++;
        sb.append("break");
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[13]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((breakLabel != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.branches[3]++;
            sb.append(" ");
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[15]++;
            sb.append(breakLabel.toSource(0));
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[16]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.branches[4]++;}
        sb.append(";\n");
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[17]++;
        return sb.toString();
    }

    /**
     * Visits this node, then visits the break label if non-{@code null}.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((breakLabel != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.branches[5]++;
            breakLabel.visit(v);
CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.statements[19]++;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx ());
  }
    public static long[] statements = new long[20];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-BreakStatement.java";
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevn82v9h1yj3n8s38uuvjce3sagkcwx () {
    super("org.mozilla.javascript.ast.RHINO-SRC-BreakStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 19; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-BreakStatement.java");
      for (int i = 1; i <= 19; i++) {
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

