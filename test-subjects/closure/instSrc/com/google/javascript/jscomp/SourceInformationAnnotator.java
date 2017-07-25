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
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * Annotates nodes with information from their original input file
 * before the compiler performs work that changes this information (such
 * as its original location, its original name, etc).
 *
 * Information saved:
 *
 * - Annotates all NAME nodes with an ORIGINALNAME_PROP indicating its original
 *   name.
 *
 * - Annotates all string GET_PROP nodes with an ORIGINALNAME_PROP.
 *
 * - Annotates all OBJECT_LITERAL unquoted string key nodes with an
 *   ORIGINALNAME_PROP.
 *
 * - Annotates all FUNCTION nodes with an ORIGINALNAME_PROP indicating its
 *   nearest original name.
 *
 */
class SourceInformationAnnotator extends
  NodeTraversal.AbstractPostOrderCallback {
  static {
    CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.ping();
  }

  private final String sourceFile;
  private final boolean doSanityChecks;

  public SourceInformationAnnotator(
      String sourceFile, boolean doSanityChecks) {
    this.sourceFile = sourceFile;
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[1]++;
    this.doSanityChecks = doSanityChecks;
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[2]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
    // Verify the source file is annotated.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((doSanityChecks) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sourceFile != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[1]++;
      Preconditions.checkState(sourceFile.equals(
          n.getSourceFileName()));
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[4]++;

    } else {
  CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[2]++;}
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[5]++;

    // Annotate the original name.
    switch (n.getType()) {
      case Token.GETPROP:
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[3]++;
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[6]++;
        Node propNode = n.getLastChild();
        setOriginalName(n, propNode.getString());
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[7]++;
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[8]++;
        break;

      case Token.FUNCTION:
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[4]++;
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[9]++;
        String functionName = NodeUtil.getNearestFunctionName(n);
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((functionName != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[5]++;
          setOriginalName(n, functionName);
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[11]++;

        } else {
  CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[6]++;}
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[12]++;
        break;

      case Token.NAME:
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[7]++;
        setOriginalName(n, n.getString());
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[13]++;
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[14]++;
        break;

      case Token.OBJECTLIT:
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[8]++;
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
        for (Node key = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false);
             key = key.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.loops[1]--;
  CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.loops[2]--;
  CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.loops[3]++;
}
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
           // We only want keys were unquoted.
           if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((key.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[9]++;
             setOriginalName(key, key.getString());
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[17]++;

           } else {
  CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[10]++;}
         }
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[18]++;
        break; default : CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[11]++;
    }
  }

  void setOriginalName(Node n, String name) {
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((name.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.getProp(Node.ORIGINALNAME_PROP) == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[12]++;
      n.putProp(Node.ORIGINALNAME_PROP, name);
CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.statements[20]++;

    } else {
  CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h.branches[13]++;}
  }
}

class CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h ());
  }
    public static long[] statements = new long[21];
    public static long[] branches = new long[14];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SourceInformationAnnotator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,2};
    for (int i = 1; i <= 5; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$aizuc4lyr4ndj9dfrkhfk6d6vpzwx7jvrb2s4v15zkqk4j8h () {
    super("com.google.javascript.jscomp.SourceInformationAnnotator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 20; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 13; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SourceInformationAnnotator.java");
      for (int i = 1; i <= 20; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 13; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

