/*
 * Copyright 2011 The Closure Compiler Authors.
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

import com.google.javascript.rhino.Node;

/**
 * Reorder constant expression hoping for a better compression.
 * ex. x === 0 -> 0 === x
 * After reordering, expressions like 0 === x and 0 === y may have higher
 * compression together than their original counterparts.
 *
 */
class ReorderConstantExpression extends AbstractPeepholeOptimization {
  static {
    CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.ping();
  }


  // TODO(user): Rename this pass to PeepholeReorderConstantExpression
  // to follow our naming convention.
  @Override
  Node optimizeSubtree(Node subtree) {
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
    // if the operator is symmetric
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((NodeUtil.isSymmetricOperation(subtree)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((NodeUtil.isRelationalOperation(subtree)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.branches[1]++;
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[2]++;
int CodeCoverConditionCoverageHelper_C2;
      // right value is immutable and left is not
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(subtree.getLastChild())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(subtree.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.branches[3]++;
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[3]++;
int CodeCoverConditionCoverageHelper_C3;

        // if relational, get the inverse operator.
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.isRelationalOperation(subtree)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)){
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.branches[5]++;
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[4]++;
          int inverseOperator = NodeUtil.getInverseOperator(subtree.getType());
          subtree.setType(inverseOperator);
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[5]++;

        } else {
  CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.branches[6]++;}
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[6]++;

        // swap them
        Node firstNode = subtree.getFirstChild().detachFromParent();
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[7]++;
        Node lastNode = subtree.getLastChild().detachFromParent();

        subtree.addChildrenToFront(lastNode);
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[8]++;
        subtree.addChildrenToBack(firstNode);
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[9]++;
        reportCodeChange();
CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.statements[10]++;

      } else {
  CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.branches[4]++;}

    } else {
  CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5.branches[2]++;}
    return subtree;
  }
}

class CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5 ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ReorderConstantExpression.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1};
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

  public CodeCoverCoverageCounter$1gmrounq3x83yazi1ln0mnlovf0bxrxd5j7hw05971c6fz5 () {
    super("com.google.javascript.jscomp.ReorderConstantExpression.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.ReorderConstantExpression.java");
      for (int i = 1; i <= 10; i++) {
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

