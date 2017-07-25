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
 * A labeled statement.  A statement can have more than one label.  In
 * this AST representation, all labels for a statement are collapsed into
 * the "labels" list of a single {@link LabeledStatement} node. <p>
 *
 * Node type is {@link Token#EXPR_VOID}. <p>
 */
public class LabeledStatement extends AstNode {
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.ping();
  }


    private List<Label> labels = new ArrayList<Label>();
  {
    CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[1]++;
  }  // always at least 1
    private AstNode statement;

    {
        type = Token.EXPR_VOID;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[2]++;
    }

    public LabeledStatement() {
    }

    public LabeledStatement(int pos) {
        super(pos);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[3]++;
    }

    public LabeledStatement(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[4]++;
    }

    /**
     * Returns label list
     */
    public List<Label> getLabels() {
        return labels;
    }

    /**
     * Sets label list, setting the parent of each label
     * in the list.  Replaces any existing labels.
     * @throws IllegalArgumentException} if labels is {@code null}
     */
    public void setLabels(List<Label> labels) {
        assertNotNull(labels);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[5]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this.labels != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.branches[1]++;
            this.labels.clear();
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[7]++;
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.branches[2]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[1]++;


        for (Label l : labels) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[1]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[2]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[3]++;
}
            addLabel(l);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[9]++;
        }
    }

    /**
     * Adds a label and sets its parent to this node.
     * @throws IllegalArgumentException} if label is {@code null}
     */
    public void addLabel(Label label) {
        assertNotNull(label);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[10]++;
        labels.add(label);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[11]++;
        label.setParent(this);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[12]++;
    }

    /**
     * Returns the labeled statement
     */
    public AstNode getStatement() {
        return statement;
    }

    /**
     * Returns label with specified name from the label list for
     * this labeled statement.  Returns {@code null} if there is no
     * label with that name in the list.
     */
    public Label getLabelByName(String name) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[4]++;


        for (Label label : labels) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[4]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[5]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[6]++;
}
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((name.equals(label.getName())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.branches[3]++;
                return label;

            } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.branches[4]++;}
        }
        return null;
    }

    /**
     * Sets the labeled statement, and sets its parent to this node.
     * @throws IllegalArgumentException if {@code statement} is {@code null}
     */
    public void setStatement(AstNode statement) {
        assertNotNull(statement);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[15]++;
        this.statement = statement;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[16]++;
        statement.setParent(this);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[17]++;
    }

    public Label getFirstLabel() {
        return labels.get(0);
    }

    @Override
    public boolean hasSideEffects() {
        // just to avoid the default case for EXPR_VOID in AstNode
        return true;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[18]++;
        StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[19]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[7]++;


        for (Label label : labels) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[7]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[8]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[9]++;
}
            sb.append(label.toSource(depth));
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[20]++;  // prints newline
        }
        sb.append(statement.toSource(depth + 1));
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[21]++;
        return sb.toString();
    }

    /**
     * Visits this node, then each label in the label-list, and finally the
     * statement.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.branches[5]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[23]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[10]++;


            for (AstNode label : labels) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[10]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[11]--;
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.loops[12]++;
}
                label.visit(v);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[24]++;
            }
            statement.visit(v);
CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.statements[25]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1.branches[6]++;}
    }
}

class CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1 ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-LabeledStatement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
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
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$adralqrs9n89mg8nqp8kmup8rw4gfyuyv996xytypxs0h5k1 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-LabeledStatement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
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
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-LabeledStatement.java");
      for (int i = 1; i <= 25; i++) {
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
      for (int i = 1; i <= 4; i++) {
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

