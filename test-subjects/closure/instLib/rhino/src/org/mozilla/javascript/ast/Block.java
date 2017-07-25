/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

/**
 * A block statement delimited by curly braces.  The node position is the
 * position of the open-curly, and the length extends to the position of
 * the close-curly.  Node type is {@link Token#BLOCK}.
 *
 * <pre><i>Block</i> :
 *     <b>{</b> Statement* <b>}</b></pre>
 */
public class Block extends AstNode {
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.ping();
  }


    {
        this.type = Token.BLOCK;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[1]++;
    }

    public Block() {
    }

    public Block(int pos) {
        super(pos);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[2]++;
    }

    public Block(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[3]++;
    }

    /**
     * Alias for {@link #addChild}.
     */
    public void addStatement(AstNode statement) {
        addChild(statement);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[4]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[5]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[6]++;
        sb.append("{\n");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[7]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[1]++;


        for (Node kid : this) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[1]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[2]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[3]++;
}
            sb.append(((AstNode)kid).toSource(depth+1));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[9]++;
        }
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[10]++;
        sb.append("}\n");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[11]++;
        return sb.toString();
    }

    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[12]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[1]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[4]++;


            for (Node kid : this) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[4]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[5]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[6]++;
}
                ((AstNode)kid).visit(v);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[14]++;
            }

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[2]++;}
    }
}

class CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5 ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-Block.java";
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-Block.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 14; i++) {
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
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-Block.java");
      for (int i = 1; i <= 14; i++) {
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

