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
 * AST node for a JavaScript 1.7 Array comprehension.
 * Node type is {@link Token#ARRAYCOMP}.<p>
 */
public class ArrayComprehension extends Scope {
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.ping();
  }


    private AstNode result;
    private List<ArrayComprehensionLoop> loops =
        new ArrayList<ArrayComprehensionLoop>();
  {
    CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[1]++;
  }
    private AstNode filter;
    private int ifPosition = -1;
  {
    CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[2]++;
  }
    private int lp = -1;
  {
    CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[3]++;
  }
    private int rp = -1;
  {
    CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[4]++;
  }

    {
        type = Token.ARRAYCOMP;
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[5]++;
    }

    public ArrayComprehension() {
    }

    public ArrayComprehension(int pos) {
        super(pos);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[6]++;
    }

    public ArrayComprehension(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[7]++;
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
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[8]++;
        this.result = result;
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[9]++;
        result.setParent(this);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[10]++;
    }

    /**
     * Returns loop list
     */
    public List<ArrayComprehensionLoop> getLoops() {
        return loops;
    }

    /**
     * Sets loop list
     * @throws IllegalArgumentException if loops is {@code null}
     */
    public void setLoops(List<ArrayComprehensionLoop> loops) {
        assertNotNull(loops);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[11]++;
        this.loops.clear();
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[12]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[1]++;


        for (ArrayComprehensionLoop acl : loops) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[1]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[2]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[3]++;
}
            addLoop(acl);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[14]++;
        }
    }

    /**
     * Adds a child loop node, and sets its parent to this node.
     * @throws IllegalArgumentException if acl is {@code null}
     */
    public void addLoop(ArrayComprehensionLoop acl) {
        assertNotNull(acl);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[15]++;
        loops.add(acl);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[16]++;
        acl.setParent(this);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[17]++;
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
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[18]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((filter != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.branches[1]++;
            filter.setParent(this);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[20]++;
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.branches[2]++;}
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
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[21]++;
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
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[22]++;
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
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[23]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[24]++;
        StringBuilder sb = new StringBuilder(250);
        sb.append("[");
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[25]++;
        sb.append(result.toSource(0));
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[26]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[27]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[4]++;


        for (ArrayComprehensionLoop loop : loops) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[4]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[5]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[6]++;
}
            sb.append(loop.toSource(0));
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[28]++;
        }
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[29]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((filter != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.branches[3]++;
            sb.append(" if (");
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[30]++;
            sb.append(filter.toSource(0));
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[31]++;
            sb.append(")");
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[32]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.branches[4]++;}
        sb.append("]");
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[33]++;
        return sb.toString();
    }

    /**
     * Visits this node, the result expression, the loops, and the optional
     * filter.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[34]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.branches[5]++;
            return;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.branches[6]++;}
        result.visit(v);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[35]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[36]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[7]++;


        for (ArrayComprehensionLoop loop : loops) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[7]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[8]--;
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.loops[9]++;
}
            loop.visit(v);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[37]++;
        }
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[38]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((filter != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.branches[7]++;
            filter.visit(v);
CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.statements[39]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl.branches[8]++;}
    }
}

class CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-ArrayComprehension.java";
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

  public CodeCoverCoverageCounter$el0607z4lg6oa7b0nd6rjyu2sivus1ixoaxz6dht3qscox94fvl () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ArrayComprehension.java");
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ArrayComprehension.java");
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

