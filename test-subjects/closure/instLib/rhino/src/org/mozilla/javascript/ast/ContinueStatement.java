/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * A continue statement.
 * Node type is {@link Token#CONTINUE}.<p>
 *
 * <pre><i>ContinueStatement</i> :
 *   <b>continue</b> [<i>no LineTerminator here</i>] [Identifier] ;</pre>
 */
public class ContinueStatement extends Jump {
  static {
    CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.ping();
  }


    private Name label;
    private Loop target;

    {
        type = Token.CONTINUE;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[1]++;
    }

    public ContinueStatement() {
    }

    public ContinueStatement(int pos) {
        this(pos, -1);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[2]++;
    }

    public ContinueStatement(int pos, int len) {
        // can't call super (Jump) for historical reasons
        position = pos;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[3]++;
        length = len;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[4]++;
    }

    public ContinueStatement(Name label) {
        setLabel(label);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[5]++;
    }

    public ContinueStatement(int pos, Name label) {
        this(pos);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[6]++;
        setLabel(label);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[7]++;
    }

    public ContinueStatement(int pos, int len, Name label) {
        this(pos, len);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[8]++;
        setLabel(label);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[9]++;
    }

    /**
     * Returns continue target
     */
    public Loop getTarget() {
        return target;
    }

    /**
     * Sets continue target.  Does NOT set the parent of the target node:
     * the target node is an ancestor of this node.
     * @param target continue target
     * @throws IllegalArgumentException if target is {@code null}
     */
    public void setTarget(Loop target) {
        assertNotNull(target);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[10]++;
        this.target = target;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[11]++;
        setJumpStatement(target);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[12]++;
    }

    /**
     * Returns the intended label of this continue statement
     * @return the continue label.  Will be {@code null} if the statement
     * consisted only of the keyword "continue".
     */
    public Name getLabel() {
        return label;
    }

    /**
     * Sets the intended label of this continue statement.
     * Only applies if the statement was of the form "continue &lt;label&gt;".
     * @param label the continue label, or {@code null} if not present.
     */
    public void setLabel(Name label) {
        this.label = label;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[13]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.branches[1]++;
            label.setParent(this);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[15]++;
} else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.branches[2]++;}
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[16]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[17]++;
        sb.append("continue");
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[18]++;
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.branches[3]++;
            sb.append(" ");
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[20]++;
            sb.append(label.toSource(0));
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[21]++;

        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.branches[4]++;}
        sb.append(";\n");
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[22]++;
        return sb.toString();
    }

    /**
     * Visits this node, then visits the label if non-{@code null}.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((label != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.branches[5]++;
            label.visit(v);
CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.statements[24]++;

        } else {
  CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd ());
  }
    public static long[] statements = new long[25];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ContinueStatement.java";
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

  public CodeCoverCoverageCounter$21tu3emdl0l6sfnhjd1b02lg86cs5qh7jvf0cfn3kdmmcplbhd () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ContinueStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 24; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ContinueStatement.java");
      for (int i = 1; i <= 24; i++) {
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

