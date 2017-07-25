/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

/**
 * AST node representing a label.  It is a distinct node type so it can
 * record its length and position for code-processing tools.
 * Node type is {@link Token#LABEL}.<p>
 */
public class Label extends Jump {
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.ping();
  }


    private String name;

    {
        type = Token.LABEL;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[1]++;
    }

    public Label() {
    }

    public Label(int pos) {
        this(pos, -1);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[2]++;
    }

    public Label(int pos, int len) {
        // can't call super (Jump) for historical reasons
        position = pos;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[3]++;
        length = len;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[4]++;
    }

    public Label(int pos, int len, String name) {
        this(pos, len);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[5]++;
        setName(name);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[6]++;
    }

    /**
     * Returns the label text
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the label text
     * @throws IllegalArgumentException if name is {@code null} or the
     * empty string.
     */
    public void setName(String name) {
        name = name == null ? null : name.trim();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[7]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((name == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (("".equals(name)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.branches[1]++;
            throw new IllegalArgumentException("invalid label name");
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.branches[2]++;}
        this.name = name;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[9]++;
    }

    @Override
    public String toSource(int depth) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[10]++;
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(depth));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[11]++;
        sb.append(name);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[12]++;
        sb.append(":\n");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[13]++;
        return sb.toString();
    }

    /**
     * Visits this label.  There are no children to visit.
     */
    @Override
    public void visit(NodeVisitor v) {
        v.visit(this);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl.statements[14]++;
    }
}

class CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl ());
  }
    public static long[] statements = new long[15];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-Label.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2};
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

  public CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bke54up0ihy1dbvl () {
    super("org.mozilla.javascript.ast.RHINO-SRC-Label.java");
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
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-Label.java");
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

