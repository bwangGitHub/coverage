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
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * Converts property accesses from quoted string syntax to dot syntax, where
 * possible. Dot syntax is more compact and avoids an object allocation in
 * IE 6.
 *
 */
class ConvertToDottedProperties extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.ping();
  }


  private final AbstractCompiler compiler;

  ConvertToDottedProperties(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[1]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[2]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[3]++;
    switch (n.getType()) {
      case Token.GETTER_DEF:
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.branches[1]++;
      case Token.SETTER_DEF:
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.branches[2]++;
      case Token.STRING_KEY:
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.branches[3]++;
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((NodeUtil.isValidPropertyName(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.branches[4]++;
          n.putBooleanProp(Node.QUOTED_PROP, false);
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[5]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[6]++;

        } else {
  CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.branches[5]++;}
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[7]++;
        break;

      case Token.GETELEM:
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.branches[6]++;
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[8]++;
        Node left = n.getFirstChild();
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[9]++;
        Node right = left.getNext();
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((right.isString()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((NodeUtil.isValidPropertyName(right.getString())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.branches[7]++;
          n.removeChild(left);
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[11]++;
          n.removeChild(right);
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[12]++;
          parent.replaceChild(n, IR.getprop(left, right));
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[13]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[14]++;

        } else {
  CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.branches[8]++;}
CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.statements[15]++;
        break; default : CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5.branches[9]++;
    }
  }
}

class CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5 ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[10];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ConvertToDottedProperties.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$172q9rjrs0t7nxj2ctjtfmqb2e84zaxouhljju42vn4f4v5 () {
    super("com.google.javascript.jscomp.ConvertToDottedProperties.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 9; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ConvertToDottedProperties.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 9; i++) {
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

