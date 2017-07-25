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

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Set;

/**
 * Uses {@link SimpleDefinitionFinder} to determine if a function has been
 * aliased or exposed to .call() or .apply().
 *
 * @author dcc@google.com (Devin Coughlin)
 */
class SimpleFunctionAliasAnalysis {
  static {
    CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.ping();
  }

  private Set<Node> aliasedFunctions;

  private Set<Node> functionsExposedToCallOrApply;

  /**
   * Returns true if the function is aliased.
   *
   * Must only be called after {@link #analyze(SimpleDefinitionFinder)}
   * has been called.
   */
  public boolean isAliased(Node functionNode) {
    Preconditions.checkNotNull(aliasedFunctions);
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[1]++;
    Preconditions.checkArgument(functionNode.isFunction());
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[2]++;

    return aliasedFunctions.contains(functionNode);
  }

  /**
   * Returns true if the function ever exposed to .call() or .apply().
   *
   * Must only be called after {@link #analyze(SimpleDefinitionFinder)}
   * has been called.
   */
  public boolean isExposedToCallOrApply(Node functionNode) {
    Preconditions.checkNotNull(functionsExposedToCallOrApply);
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[3]++;
    Preconditions.checkArgument(functionNode.isFunction());
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[4]++;

    return functionsExposedToCallOrApply.contains(functionNode);
  }

  /**
   * Uses the provided {@link SimpleDefinitionFinder} to determine
   * which functions are aliased or exposed to .call() or .apply().
   */
  public void analyze(SimpleDefinitionFinder finder) {
    Preconditions.checkState(aliasedFunctions == null);
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[5]++;

    aliasedFunctions = Sets.newHashSet();
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[6]++;
    functionsExposedToCallOrApply = Sets.newHashSet();
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[7]++;
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[1]++;



    for (DefinitionSite definitionSite : finder.getDefinitionSites()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[1]--;
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[2]--;
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[3]++;
}
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[9]++;
      Definition definition = definitionSite.definition;
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;

      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((definition.isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[1]++;
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[11]++;
        Node rValue = definition.getRValue();
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((rValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[3]++;
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[4]++;


          // rValue is a Token.FUNCTION from a definition

          for (UseSite useSite : finder.getUseSites(definition)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[4]--;
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[5]--;
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.loops[6]++;
}
            updateFunctionForUse(rValue, useSite.node);
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[14]++;
          }

        } else {
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[4]++;}

      } else {
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[2]++;}
    }
  }

  /**
   * Updates alias and exposure information based a site where the function is
   * used.
   *
   * Note: this method may be called multiple times per Function, each time
   * with a different useNode.
   */
  private void updateFunctionForUse(Node function, Node useNode) {
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[15]++;
    Node useParent = useNode.getParent();
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[16]++;
    int parentType = useParent.getType();
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((parentType == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((parentType == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((useParent.getFirstChild() == useNode) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) || true)) || (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 3) && false)) {
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[5]++;

      // Regular call sites don't count as aliases
    } else {
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[6]++;
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[18]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(useParent)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[7]++;
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
      // GET{PROP,ELEM} don't count as aliases
      // but we have to check for using them in .call and .apply.

      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((useParent.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[9]++;
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[20]++;
        Node gramps = useParent.getParent();
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[21]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectApply(gramps)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionObjectCall(gramps)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[11]++;
          functionsExposedToCallOrApply.add(function);
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[22]++;

        } else {
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[12]++;}

      } else {
  CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[10]++;}

    } else {
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.branches[8]++;
      aliasedFunctions.add(function);
CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl.statements[23]++;
    }
}
  }
}

class CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SimpleFunctionAliasAnalysis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,3,1,1,2};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$22ubhgilepy6ro1o6bhfpvky4d9x2m1gxpmldns5n5bj12apnl () {
    super("com.google.javascript.jscomp.SimpleFunctionAliasAnalysis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SimpleFunctionAliasAnalysis.java");
      for (int i = 1; i <= 23; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

