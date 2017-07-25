/*
 * Copyright 2009 The Closure Compiler Authors.
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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;

/**
 * Proxy that provides a high level interface that compiler passes can
 * use to replace or remove sections of the AST.
 *
 */
class AstChangeProxy {
  static {
    CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.ping();
  }


  /**
   * Interface used to notify client code about changes done by
   * AstChangeProxy.
   */
  interface ChangeListener {

    /**
     * Notifies clients about node removals.
     */
    void nodeRemoved(Node node);
  }

  private final List<ChangeListener> listeners;

  AstChangeProxy() {
    listeners = Lists.newArrayList();
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[1]++;
  }

  /**
   * Registers a change listener.
   */
  final void registerListener(ChangeListener listener) {
    listeners.add(listener);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[2]++;
  }

  /**
   * Unregisters a change listener.
   */
  final void unregisterListener(ChangeListener listener) {
    listeners.remove(listener);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[3]++;
  }

  /**
   * Notifies listeners about a removal.
   */
  private void notifyOfRemoval(Node node) {
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[1]++;


    for (ChangeListener listener : listeners) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[1]--;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[2]--;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[3]++;
}
      listener.nodeRemoved(node);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[5]++;
    }
  }

  /**
   * Removes a node from the parent's child list.
   */
  final void removeChild(Node parent, Node node) {
    parent.removeChild(node);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[6]++;

    notifyOfRemoval(node);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[7]++;
  }

  /**
   * Replaces a node from the parent's child list.
   */
  final void replaceWith(Node parent, Node node, Node replacement) {
    replaceWith(parent, node, Lists.newArrayList(replacement));
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[8]++;
  }

  /**
   * Replaces a node with the provided list.
   */
  final void replaceWith(Node parent, Node node, List<Node> replacements) {
    Preconditions.checkNotNull(replacements, "\"replacements\" is null.");
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[9]++;
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[10]++;

    int size = replacements.size();
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((size == 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node.isEquivalentTo(replacements.get(0))) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.branches[1]++;
      // trees are equal... don't replace
      return;

    } else {
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.branches[2]++;}
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[12]++;

    int parentType = parent.getType();

    Preconditions.checkState(size == 1 ||
        parentType == Token.BLOCK ||
        parentType == Token.SCRIPT ||
        parentType == Token.LABEL);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[13]++;
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[14]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((parentType == Token.LABEL) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((size != 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.branches[3]++;
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[15]++;
      Node block = IR.block();
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[16]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[4]++;


      for (Node newChild : replacements) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[4]--;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[5]--;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[6]++;
}
        newChild.copyInformationFrom(node);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[17]++;
        block.addChildToBack(newChild);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[18]++;
      }
      parent.replaceChild(node, block);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[19]++;

    } else {
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.branches[4]++;
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[20]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[7]++;


      for (Node newChild : replacements) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[7]--;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[8]--;
  CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.loops[9]++;
}
        newChild.copyInformationFrom(node);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[21]++;
        parent.addChildBefore(newChild, node);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[22]++;
      }
      parent.removeChild(node);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[23]++;
    }
    notifyOfRemoval(node);
CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1.statements[24]++;
  }
}

class CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1 ());
  }
    public static long[] statements = new long[25];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AstChangeProxy.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$12pdibv2trxsu7md17myseovi46ag1 () {
    super("com.google.javascript.jscomp.AstChangeProxy.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 24; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AstChangeProxy.java");
      for (int i = 1; i <= 24; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

