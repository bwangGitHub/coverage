/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Node for the root of a parse tree.  It contains the statements and functions
 * in the script, and a list of {@link Comment} nodes associated with the script
 * as a whole.  Node type is {@link Token#SCRIPT}. <p>
 *
 * Note that the tree itself does not store errors.  To collect the parse errors
 * and warnings, pass an {@link org.mozilla.javascript.ErrorReporter} to the
 * {@link org.mozilla.javascript.Parser} via the
 * {@link org.mozilla.javascript.CompilerEnvirons}.
 */
public class AstRoot extends ScriptNode {
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.ping();
  }


    private SortedSet<Comment> comments;
    private boolean inStrictMode;

    {
        type = Token.SCRIPT;
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[1]++;
    }

    public AstRoot() {
    }

    public AstRoot(int pos) {
        super(pos);
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[2]++;
    }

    /**
     * Returns comment set
     * @return comment set, sorted by start position. Can be {@code null}.
     */
    public SortedSet<Comment> getComments() {
        return comments;
    }

    /**
     * Sets comment list, and updates the parent of each entry to point
     * to this node.  Replaces any existing comments.
     * @param comments comment list.  can be {@code null}.
     */
    public void setComments(SortedSet<Comment> comments) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((comments == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[1]++;
            this.comments = null;
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[4]++;

        } else {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[2]++;
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((this.comments != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[3]++;
                this.comments.clear();
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[6]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[4]++;}
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[1]++;


            for (Comment c : comments) { 
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[1]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[2]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[3]++;
}
                addComment(c);
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[8]++;
  }
        }
    }

    /**
     * Add a comment to the comment set.
     * @param comment the comment node.
     * @throws IllegalArgumentException if comment is {@code null}
     */
    public void addComment(Comment comment) {
        assertNotNull(comment);
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[9]++;
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((comments == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[5]++;
            comments = new TreeSet<Comment>(new AstNode.PositionComparator());
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[11]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[6]++;}
        comments.add(comment);
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[12]++;
        comment.setParent(this);
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[13]++;
    }

    public void setInStrictMode(boolean inStrictMode) {
        this.inStrictMode = inStrictMode;
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[14]++;
    }

    public boolean isInStrictMode() {
        return inStrictMode;
    }

    /**
     * Visits the comment nodes in the order they appear in the source code.
     * The comments are not visited by the {@link #visit} function - you must
     * use this function to visit them.
     * @param visitor the callback object.  It is passed each comment node.
     * The return value is ignored.
     */
    public void visitComments(NodeVisitor visitor) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((comments != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[7]++;
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[16]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[4]++;


            for (Comment c : comments) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[4]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[5]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[6]++;
}
                visitor.visit(c);
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[17]++;
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[8]++;}
    }

    /**
     * Visits the AST nodes, then the comment nodes.
     * This method is equivalent to calling {@link #visit}, then
     * {@link #visitComments}.  The return value
     * is ignored while visiting comment nodes.
     * @param visitor the callback object.
     */
    public void visitAll(NodeVisitor visitor) {
        visit(visitor);
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[18]++;
        visitComments(visitor);
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[19]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[20]++;
        StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[21]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[7]++;


        for (Node node : this) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[7]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[8]--;
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.loops[9]++;
}
            sb.append(((AstNode)node).toSource(depth));
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[22]++;
        }
        return sb.toString();
    }

    /**
     * A debug-printer that includes comments (at the end).
     */
    @Override
    public String debugPrint() {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[23]++;
        DebugPrintVisitor dpv = new DebugPrintVisitor(new StringBuilder(1000));
        visitAll(dpv);
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[24]++;
        return dpv.toString();
    }

    /**
     * Debugging function to check that the parser has set the parent
     * link for every node in the tree.
     * @throws IllegalStateException if a parent link is missing
     */
    public void checkParentLinks() {
        this.visit(new NodeVisitor() {
            public boolean visit(AstNode node) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[26]++;
                int type = node.getType();
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((type == Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[9]++;
                    return true;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[10]++;}
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((node.getParent() == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[11]++;
                    throw new IllegalStateException
                            ("No parent for node: " + node
                             + "\n" + node.toSource(0));
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.branches[12]++;}
                return true;
            }
        });
CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35.statements[25]++;
    }
}

class CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35 ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-AstRoot.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$di175yxae5e37vt02f3saslgitlemwmn35 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-AstRoot.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
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
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-AstRoot.java");
      for (int i = 1; i <= 28; i++) {
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

