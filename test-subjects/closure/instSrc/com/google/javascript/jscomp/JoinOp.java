/*
 * Copyright 2010 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.javascript.jscomp.graph.LatticeElement;

import java.util.List;

/**
 * Defines a way join a list of LatticeElements.
 */
interface JoinOp<L extends LatticeElement> extends Function<List<L>, L> {

  /**
   * An implementation of {@code JoinOp} that makes it easy to join to
   * lattice elements at a time.
   */
  static abstract class BinaryJoinOp<L extends LatticeElement>
      implements JoinOp<L> {
    @Override
    public final L apply(List<L> values) {
      Preconditions.checkArgument(!values.isEmpty());
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.statements[1]++;
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.statements[2]++;
      int size = values.size();
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((size == 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.branches[1]++;
        return values.get(0);

      } else {
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.branches[2]++;
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.statements[4]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((size == 2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.branches[3]++;
        return apply(values.get(0), values.get(1));

      } else {
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.branches[4]++;
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.statements[5]++;
        int mid = computeMidPoint(size);
        return apply(
            apply(values.subList(0, mid)),
            apply(values.subList(mid, size)));
      }
}
    }

    /**
     * Creates a new lattice that will be the join of two input lattices.
     *
     * @return The join of {@code latticeA} and {@code latticeB}.
     */
    abstract L apply(L latticeA, L latticeB);

    /**
     * Finds the midpoint of a list. The function will favor two lists of
     * even length instead of two lists of the same odd length. The list
     * must be at least length two.
     *
     * @param size Size of the list.
     */
    static int computeMidPoint(int size) {
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.statements[6]++;
      int midpoint = size >>> 1;
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((size > 4) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.branches[5]++;
        /* Any list longer than 4 should prefer an even split point
         * over the true midpoint, so that [0,6] splits at 2, not 3. */
        midpoint &= -2;
CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.statements[8]++;
 // (0xfffffffe) clears low bit so midpoint is even
      } else {
  CodeCoverCoverageCounter$bb1k025w4qv1rd7j5.branches[6]++;}
      return midpoint;
    }
  }
}

class CodeCoverCoverageCounter$bb1k025w4qv1rd7j5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$bb1k025w4qv1rd7j5 ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.JoinOp.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$bb1k025w4qv1rd7j5 () {
    super("com.google.javascript.jscomp.JoinOp.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.JoinOp.java");
      for (int i = 1; i <= 8; i++) {
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

