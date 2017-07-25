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

import com.google.common.collect.Lists;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;

/**
 * <p>Compiler pass that converts all calls to:
 *   goog.object.create(key1, val1, key2, val2, ...) where all of the keys
 *   are literals into object literals.</p>
 *
 * @author agrieve@google.com (Andrew Grieve)
 */
final class ClosureOptimizePrimitives implements CompilerPass {
  static {
    CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.ping();
  }


  /** Reference to the JS compiler */
  private final AbstractCompiler compiler;

  /**
   * Identifies all calls to goog.object.create.
   */
  private class FindObjectCreateCalls extends AbstractPostOrderCallback {
    List<Node> callNodes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[1]++;
  }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[1]++;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[3]++;
        String fnName = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 (("goog$object$create".equals(fnName)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 (("goog.object.create".equals(fnName)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[3]++;
          callNodes.add(n);
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[5]++;

        } else {
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[4]++;}

      } else {
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[2]++;}
    }
  }

  /**
   * @param compiler The AbstractCompiler
   */
  ClosureOptimizePrimitives(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[6]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[7]++;
    FindObjectCreateCalls pass = new FindObjectCreateCalls();
    NodeTraversal.traverse(compiler, root, pass);
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[8]++;
    processObjectCreateCalls(pass.callNodes);
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[9]++;
  }

  /**
   * Converts all of the given call nodes to object literals that are safe to
   * do so.
   */
  private void processObjectCreateCalls(List<Node> callNodes) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[1]++;


    for (Node callNode : callNodes) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[1]--;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[2]--;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[3]++;
}
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[11]++;
      Node curParam = callNode.getFirstChild().getNext();
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((canOptimizeObjectCreate(curParam)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[5]++;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[13]++;
        Node objNode = IR.objectlit().srcref(callNode);
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
        while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((curParam != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[4]--;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[5]--;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[6]++;
}
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[15]++;
          Node keyNode = curParam;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[16]++;
          Node valueNode = curParam.getNext();
          curParam = valueNode.getNext();
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[17]++;

          callNode.removeChild(keyNode);
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[18]++;
          callNode.removeChild(valueNode);
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[19]++;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;

          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((keyNode.isString()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[7]++;
            keyNode = IR.string(NodeUtil.getStringValue(keyNode))
                .srcref(keyNode);
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[21]++;

          } else {
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[8]++;}
          keyNode.setType(Token.STRING_KEY);
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[22]++;
          keyNode.setQuotedString();
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[23]++;
          objNode.addChildToBack(IR.propdef(keyNode, valueNode));
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[24]++;
        }
        callNode.getParent().replaceChild(callNode, objNode);
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[25]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[26]++;

      } else {
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[6]++;}
    }
  }

  /**
   * Returns whether the given call to goog.object.create can be converted to an
   * object literal.
   */
  private boolean canOptimizeObjectCreate(Node firstParam) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[27]++;
    Node curParam = firstParam;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[28]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
    while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((curParam != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[7]--;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[8]--;
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.loops[9]++;
}
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
      // All keys must be strings or numbers.
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((curParam.isString()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((curParam.isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[9]++;
        return false;

      } else {
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[10]++;}
      curParam = curParam.getNext();
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[30]++;
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;

      // Check for an odd number of parameters.
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((curParam == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[11]++;
        return false;

      } else {
  CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.branches[12]++;}
      curParam = curParam.getNext();
CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl.statements[32]++;
    }
    return true;
  }
}

class CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl ());
  }
    public static long[] statements = new long[33];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ClosureOptimizePrimitives.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,2,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$172gkyrkuyjl2xcvsmkd14oxm1ktnjp4f3b411kxtcpjnfl () {
    super("com.google.javascript.jscomp.ClosureOptimizePrimitives.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 32; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ClosureOptimizePrimitives.java");
      for (int i = 1; i <= 32; i++) {
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
    for (int i = 1; i <= 8; i++) {
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

