/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.Token;

/**
 */
public class GeneratorExpression extends Scope {
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.ping();
  }

    
    private AstNode result;
    private List<GeneratorExpressionLoop> loops =
        new ArrayList<GeneratorExpressionLoop>();
  {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[1]++;
  }
    private AstNode filter;
    private int ifPosition = -1;
  {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[2]++;
  }
    private int lp = -1;
  {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[3]++;
  }
    private int rp = -1;
  {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[4]++;
  }
    
    {
        type = Token.GENEXPR;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[5]++;
    }

    public GeneratorExpression() {
    }

    public GeneratorExpression(int pos) {
        super(pos);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[6]++;
    }

    public GeneratorExpression(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[7]++;
    }
    
    /**
     * Returns result expression node (just after opening bracket)
     */
    public AstNode getResult() {
        return result;
    }

    /**
     * Sets result expression, and sets its parent to this node.
     * @throws IllegalArgumentException if result is {@code null}
     */
    public void setResult(AstNode result) {
        assertNotNull(result);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[8]++;
        this.result = result;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[9]++;
        result.setParent(this);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[10]++;
    }

    /**
     * Returns loop list
     */
    public List<GeneratorExpressionLoop> getLoops() {
        return loops;
    }

    /**
     * Sets loop list
     * @throws IllegalArgumentException if loops is {@code null}
     */
    public void setLoops(List<GeneratorExpressionLoop> loops) {
        assertNotNull(loops);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[11]++;
        this.loops.clear();
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[12]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[1]++;


        for (GeneratorExpressionLoop acl : loops) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[1]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[2]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[3]++;
}
            addLoop(acl);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[14]++;
        }
    }

    /**
     * Adds a child loop node, and sets its parent to this node.
     * @throws IllegalArgumentException if acl is {@code null}
     */
    public void addLoop(GeneratorExpressionLoop acl) {
        assertNotNull(acl);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[15]++;
        loops.add(acl);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[16]++;
        acl.setParent(this);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[17]++;
    }
    
    /**
     * Returns filter expression, or {@code null} if not present
     */
    public AstNode getFilter() {
        return filter;
    }

    /**
     * Sets filter expression, and sets its parent to this node.
     * Can be {@code null}.
     */
    public void setFilter(AstNode filter) {
        this.filter = filter;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[18]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((filter != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.branches[1]++;
            filter.setParent(this);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[20]++;
} else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.branches[2]++;}
    }

    /**
     * Returns position of 'if' keyword, -1 if not present
     */
    public int getIfPosition() {
        return ifPosition;
    }

    /**
     * Sets position of 'if' keyword
     */
    public void setIfPosition(int ifPosition) {
        this.ifPosition = ifPosition;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[21]++;
    }

    /**
     * Returns filter left paren position, or -1 if no filter
     */
    public int getFilterLp() {
        return lp;
    }

    /**
     * Sets filter left paren position, or -1 if no filter
     */
    public void setFilterLp(int lp) {
        this.lp = lp;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[22]++;
    }

    /**
     * Returns filter right paren position, or -1 if no filter
     */
    public int getFilterRp() {
        return rp;
    }

    /**
     * Sets filter right paren position, or -1 if no filter
     */
    public void setFilterRp(int rp) {
        this.rp = rp;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[23]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[24]++;
        StringBuilder sb = new StringBuilder(250);
        sb.append("(");
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[25]++;
        sb.append(result.toSource(0));
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[26]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[27]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[4]++;


        for (GeneratorExpressionLoop loop : loops) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[4]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[5]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[6]++;
}
            sb.append(loop.toSource(0));
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[28]++;
        }
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[29]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((filter != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.branches[3]++;
            sb.append(" if (");
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[30]++;
            sb.append(filter.toSource(0));
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[31]++;
            sb.append(")");
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[32]++;

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.branches[4]++;}
        sb.append(")");
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[33]++;
        return sb.toString();
    }

    /**
     * Visits this node, the result expression, the loops, and the optional
     * filter.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[34]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.branches[5]++;
            return;

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.branches[6]++;}
        result.visit(v);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[35]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[36]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[7]++;


        for (GeneratorExpressionLoop loop : loops) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[7]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[8]--;
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.loops[9]++;
}
            loop.visit(v);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[37]++;
        }
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[38]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((filter != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.branches[7]++;
            filter.visit(v);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.statements[39]++;

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x.branches[8]++;}
    }
}

class CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-GeneratorExpression.java";
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$2vpd6pkpsoj3gojydzisfv4zmhswqablyjfjdx6m74go27lp6gb4x () {
    super("org.mozilla.javascript.ast.RHINO-SRC-GeneratorExpression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
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
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-GeneratorExpression.java");
      for (int i = 1; i <= 39; i++) {
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

