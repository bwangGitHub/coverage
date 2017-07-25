/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

/**
 */
public class GeneratorExpressionLoop extends ForInLoop {
  static {
    CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.ping();
  }

    
    public GeneratorExpressionLoop() {
    }

    public GeneratorExpressionLoop(int pos) {
        super(pos);
CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.statements[1]++;
    }

    public GeneratorExpressionLoop(int pos, int len) {
        super(pos, len);
CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.statements[2]++;
    }
    
    /**
     * Returns whether the loop is a for-each loop
     */
    @Override
    public boolean isForEach() {
        return false;
    }

    /**
     * Sets whether the loop is a for-each loop
     */
    @Override
    public void setIsForEach(boolean isForEach) {
        throw new UnsupportedOperationException("this node type does not support for each");
    }

    @Override
    public String toSource(int depth) {
        return makeIndent(depth)
                + " for "
                + (isForEach()?"each ":"")
                + "("
                + iterator.toSource(0)
                + " in "
                + iteratedObject.toSource(0)
                + ")";
    }

    /**
     * Visits the iterator expression and the iterated object expression.
     * There is no body-expression for this loop type.
     */
    @Override
    public void visit(NodeVisitor v) {
CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((v.visit(this)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.branches[1]++;
            iterator.visit(v);
CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.statements[4]++;
            iteratedObject.visit(v);
CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.statements[5]++;

        } else {
  CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh.branches[2]++;}
    }
}

class CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-GeneratorExpressionLoop.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$5om8e3xv4a889jua21jolkmh1cvb5ac3ji02fa5bm0gixhkho6iignrilwh () {
    super("org.mozilla.javascript.ast.RHINO-SRC-GeneratorExpressionLoop.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-GeneratorExpressionLoop.java");
      for (int i = 1; i <= 5; i++) {
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

