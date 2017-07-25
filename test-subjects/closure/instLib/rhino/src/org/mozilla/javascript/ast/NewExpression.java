/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * New expression. Node type is {@link Token#NEW}.<p>
 *
 * <pre><i>NewExpression</i> :
 *      MemberExpression
 *      <b>new</b> NewExpression</pre>
 *
 * This node is a subtype of {@link FunctionCall}, mostly for internal code
 * sharing.  Structurally a {@code NewExpression} node is very similar to a
 * {@code FunctionCall}, so it made a certain amount of sense.
 */
public class NewExpression extends FunctionCall {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.ping();
  }


    private ObjectLiteral initializer;

    {
        type = Token.NEW;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[1]++;
    }

    public NewExpression() {
    }

    public NewExpression(int pos) {
        super(pos);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[2]++;
    }

    public NewExpression(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[3]++;
    }

    /**
     * Returns initializer object, if any.
     * @return extra initializer object-literal expression, or {@code null} if
     * not specified.
     */
    public ObjectLiteral getInitializer() {
      return initializer;
    }

    /**
     * Sets initializer object.  Rhino supports an experimental syntax
     * of the form {@code new expr [ ( arglist ) ] [initializer]},
     * in which initializer is an object literal that is used to set
     * additional properties on the newly-created {@code expr} object.
     *
     * @param initializer extra initializer object.
     * Can be {@code null}.
     */
    public void setInitializer(ObjectLiteral initializer) {
      this.initializer = initializer;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[4]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((initializer != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[1]++;
          initializer.setParent(this);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[6]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[2]++;}
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[7]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[8]++;
        sb.append("new ");
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[9]++;
        sb.append(target.toSource(0));
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[10]++;
        sb.append("(");
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[11]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((arguments != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[3]++;
            printList(arguments, sb);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[13]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[4]++;}
        sb.append(")");
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[14]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((initializer != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[5]++;
            sb.append(" ");
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[16]++;
            sb.append(initializer.toSource(0));
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[17]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[6]++;}
        return sb.toString();
    }

    /**
     * Visits this node, the target, and each argument.  If there is
     * a trailing initializer node, visits that last.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[18]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[7]++;
            target.visit(v);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[19]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[20]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.loops[1]++;


            for (AstNode arg : getArguments()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.loops[1]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.loops[2]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.loops[3]++;
}
                arg.visit(v);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[21]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((initializer != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[9]++;
                initializer.visit(v);
CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.statements[23]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[10]++;}

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h.branches[8]++;}
    }
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-NewExpression.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$11f1r6z5fa12k16lcp3i8dihz5lufazz5s1v4c6n4s4h () {
    super("org.mozilla.javascript.ast.RHINO-SRC-NewExpression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-NewExpression.java");
      for (int i = 1; i <= 23; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

