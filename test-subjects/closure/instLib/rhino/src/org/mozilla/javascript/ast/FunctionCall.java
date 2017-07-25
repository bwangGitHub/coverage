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
 * AST node for a function call.  Node type is {@link Token#CALL}.<p>
 */
public class FunctionCall extends AstNode {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.ping();
  }


    protected static final List<AstNode> NO_ARGS =
        Collections.unmodifiableList(new ArrayList<AstNode>());
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[1]++;
  }

    protected AstNode target;
    protected List<AstNode> arguments;
    protected int lp = -1;
  {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[2]++;
  }
    protected int rp = -1;
  {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[3]++;
  }

    {
        type = Token.CALL;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[4]++;
    }

    public FunctionCall() {
    }

    public FunctionCall(int pos) {
        super(pos);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[5]++;
    }

    public FunctionCall(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[6]++;
    }

    /**
     * Returns node evaluating to the function to call
     */
    public AstNode getTarget() {
        return target;
    }

    /**
     * Sets node evaluating to the function to call, and sets
     * its parent to this node.
     * @param target node evaluating to the function to call.
     * @throws IllegalArgumentException} if target is {@code null}
     */
    public void setTarget(AstNode target) {
        assertNotNull(target);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[7]++;
        this.target = target;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[8]++;
        target.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[9]++;
    }

    /**
     * Returns function argument list
     * @return function argument list, or an empty immutable list if
     *         there are no arguments.
     */
    public List<AstNode> getArguments() {
        return arguments != null ? arguments : NO_ARGS;
    }

    /**
     * Sets function argument list
     * @param arguments function argument list.  Can be {@code null},
     *        in which case any existing args are removed.
     */
    public void setArguments(List<AstNode> arguments) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((arguments == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[1]++;
            this.arguments = null;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[11]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[2]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.arguments != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[3]++;
                this.arguments.clear();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[13]++;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[4]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[1]++;


            for (AstNode arg : arguments) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[3]++;
}
                addArgument(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[15]++;
            }
        }
    }

    /**
     * Adds an argument to the list, and sets its parent to this node.
     * @param arg the argument node to add to the list
     * @throws IllegalArgumentException} if arg is {@code null}
     */
    public void addArgument(AstNode arg) {
        assertNotNull(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[16]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((arguments == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[5]++;
            arguments = new ArrayList<AstNode>();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[18]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[6]++;}
        arguments.add(arg);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[19]++;
        arg.setParent(this);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[20]++;
    }

    /**
     * Returns left paren position, -1 if missing
     */
    public int getLp() {
        return lp;
    }

    /**
     * Sets left paren position
     * @param lp left paren position
     */
    public void setLp(int lp) {
        this.lp = lp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[21]++;
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
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[22]++;
    }

    /**
     * Sets both paren positions
     */
    public void setParens(int lp, int rp) {
        this.lp = lp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[23]++;
        this.rp = rp;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[24]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[25]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[26]++;
        sb.append(target.toSource(0));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[27]++;
        sb.append("(");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[28]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[29]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((arguments != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[7]++;
            printList(arguments, sb);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[30]++;

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[8]++;}
        sb.append(")");
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[31]++;
        return sb.toString();
    }

    /**
     * Visits this node, the target object, and the arguments.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[32]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[9]++;
            target.visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[33]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[34]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[4]++;


            for (AstNode arg : getArguments()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[4]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[5]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.loops[6]++;
}
                arg.visit(v);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.statements[35]++;
            }

        } else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9.branches[10]++;}
    }
}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9 ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-FunctionCall.java";
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmi6r5h2xq623co8gpa65ntimau9 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-FunctionCall.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
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
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-FunctionCall.java");
      for (int i = 1; i <= 35; i++) {
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

