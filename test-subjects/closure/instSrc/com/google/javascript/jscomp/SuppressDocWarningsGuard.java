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

import com.google.common.collect.Maps;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Map;

/**
 * Filters warnings based on in-code {@code @suppress} annotations.
 * @author nicksantos@google.com (Nick Santos)
 */
class SuppressDocWarningsGuard extends WarningsGuard {
  static {
    CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.ping();
  }

  private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[1]++;
  }

  /** Warnings guards for each suppressible warnings group, indexed by name. */
  private final Map<String, DiagnosticGroupWarningsGuard> suppressors =
      Maps.newHashMap();
  {
    CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[2]++;
  }

  /**
   * The suppressible groups, indexed by name.
   */
  SuppressDocWarningsGuard(Map<String, DiagnosticGroup> suppressibleGroups) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[1]++;


    for (Map.Entry<String, DiagnosticGroup> entry :
             suppressibleGroups.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[1]--;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[2]--;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[3]++;
}
      suppressors.put(
          entry.getKey(),
          new DiagnosticGroupWarningsGuard(
              entry.getValue(),
              CheckLevel.OFF));
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[4]++;
    }
  }

  @Override
  public CheckLevel level(JSError error) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[5]++;
    Node node = error.node;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((node != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[1]++;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[7]++;
      boolean visitedFunction = false;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[8]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
      for (Node current = node;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false);
           current = current.getParent()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[4]--;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[5]--;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[6]++;
}
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[9]++;
        int type = current.getType();
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[10]++;
        JSDocInfo info = null;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((type == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[3]++;
          info = NodeUtil.getBestJSDocInfo(current);
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[12]++;
          visitedFunction = true;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[13]++;

        } else {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[4]++;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[14]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((type == Token.SCRIPT) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[5]++;
          info = current.getJSDocInfo();
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[15]++;

        } else {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[6]++;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[16]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((current.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((current.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[7]++;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[17]++;
          // There's one edge case we're worried about:
          // if the warning points to an assigment to a function, we
          // want the suppressions on that function to apply.
          // It's OK if we double-count some cases.
          Node rhs = NodeUtil.getRValueOfLValue(current.getFirstChild());
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[18]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((rhs != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[9]++;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[19]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((rhs.isCast()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[11]++;
              rhs = rhs.getFirstChild();
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[20]++;

            } else {
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[12]++;}
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;

            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((rhs.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((visitedFunction) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[13]++;
              info = NodeUtil.getBestJSDocInfo(current);
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[22]++;

            } else {
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[14]++;}

          } else {
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[10]++;}

        } else {
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[8]++;}
}
}
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[23]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[15]++;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[24]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[7]++;


          for (String suppressor : info.getSuppressions()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[7]--;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[8]--;
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.loops[9]++;
}
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[25]++;
            WarningsGuard guard = suppressors.get(suppressor);
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;

            // Some @suppress tags are for other tools, and
            // may not have a warnings guard.
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((guard != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[17]++;
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[27]++;
              CheckLevel newLevel = guard.level(error);
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.statements[28]++;
int CodeCoverConditionCoverageHelper_C11;
              if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((newLevel != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[19]++;
                return newLevel;

              } else {
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[20]++;}

            } else {
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[18]++;}
          }

        } else {
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[16]++;}
      }

    } else {
  CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5.branches[2]++;}
    return null;
  }

  @Override
  public int getPriority() {
    // Happens after path-based filtering, but before other times
    // of filtering.
    return WarningsGuard.Priority.SUPPRESS_DOC.value;
  }
}

class CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5 ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SuppressDocWarningsGuard.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,2,1,1,1};
    for (int i = 1; i <= 11; i++) {
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

  public CodeCoverCoverageCounter$7hvyo5zxw0nqak3zmud5t5m5f5h0jmehi8in86yxyobj5 () {
    super("com.google.javascript.jscomp.SuppressDocWarningsGuard.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SuppressDocWarningsGuard.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
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

