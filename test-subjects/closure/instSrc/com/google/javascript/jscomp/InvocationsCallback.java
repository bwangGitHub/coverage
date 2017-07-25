/*
 * Copyright 2007 The Closure Compiler Authors.
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

import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.Node;

/**
 * Traversal callback that finds method invocations of the form
 *
 * <pre>
 * call
 *   getprop
 *     ...
 *     string
 *   ...
 * </pre>
 *
 * and invokes a method defined by subclasses for processing these invocations.
 *
 */
abstract class InvocationsCallback extends AbstractPostOrderCallback {
  static {
    CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.ping();
  }


  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.branches[1]++;
      return;

    } else {
  CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.branches[2]++;}
CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.statements[2]++;

    Node function = n.getFirstChild();
CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.statements[3]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((function.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.branches[4]++;}
CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.statements[4]++;

    Node nameNode = function.getFirstChild().getNext();
CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;

    // Don't care about numerical or variable indexes
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nameNode.isString()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.branches[5]++;
      return;

    } else {
  CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.branches[6]++;}

    visit(t, n, parent, nameNode.getString());
CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01.statements[6]++;
  }

  /**
   * Called for each callnode that is a method invocation.
   *
   * @param callNode node of type call
   * @param parent parent of callNode
   * @param callName name of method invoked by first child of call
   */
  abstract void visit(NodeTraversal t, Node callNode, Node parent,
      String callName);
}

class CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01 ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.InvocationsCallback.java";
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

  public CodeCoverCoverageCounter$gx9xn8ygp482i43p4obpk6ymxt5f5t1yjra01 () {
    super("com.google.javascript.jscomp.InvocationsCallback.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.InvocationsCallback.java");
      for (int i = 1; i <= 6; i++) {
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

