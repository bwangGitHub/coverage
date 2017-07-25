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
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.Node;
import java.util.Set;

/**
 * External references of the form: "window['xx']" indicate names that must
 * be reserved when variable renaming to avoid conflicts.
 *
 * @author johnlenz@google.com (John Lenz)
 */
class GatherRawExports extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.ping();
  }


  private final AbstractCompiler compiler;

  private static final String GLOBAL_THIS_NAMES[] = { "window", "top" };
  static {
    CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[1]++;
  }

  private final Set<String> exportedVariables = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[2]++;
  }

  GatherRawExports(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[3]++;
  }

  @Override
  public void process(Node externs, Node root) {
    Preconditions.checkState(compiler.getLifeCycleStage().isNormalized());
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[4]++;
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[5]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[6]++;
    Node sibling = n.getNext();
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((sibling != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((sibling.isString()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(parent)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[1]++;
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
      // TODO(johnlenz): Should we warn if we see a property name that
      // hasn't been exported?
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isGlobalThisObject(t, n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[3]++;
        exportedVariables.add(sibling.getString());
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[9]++;

      } else {
  CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[4]++;}

    } else {
  CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[2]++;}
  }

  private boolean isGlobalThisObject(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[5]++;
      return t.inGlobalScope();

    } else {
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[6]++;
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[11]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[7]++;
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[12]++;
      String varName = n.getString();
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[13]++;
      int items = GLOBAL_THIS_NAMES.length;
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < items) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.loops[1]--;
  CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.loops[2]--;
  CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.loops[3]++;
}
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((varName.equals(GLOBAL_THIS_NAMES[i])) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[9]++;
          return true;

        } else {
  CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[10]++;}
      }

    } else {
  CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd.branches[8]++;}
}
    return false;
  }

  public Set<String> getExportedVariableNames() {
    return exportedVariables;
  }
}

class CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.GatherRawExports.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,1,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$1najk6jrswqs4lg6tksaavp4kiy6hzjhd () {
    super("com.google.javascript.jscomp.GatherRawExports.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.GatherRawExports.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
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

