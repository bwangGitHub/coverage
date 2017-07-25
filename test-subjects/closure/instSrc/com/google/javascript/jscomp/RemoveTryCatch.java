/*
 * Copyright 2006 The Closure Compiler Authors.
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
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.HashSet;
import java.util.Set;

/**
 * Removes try catch finally blocks from a parse tree for easier debugging
 * (these statements impact both debugging in IE and sometimes even in FF).
 *
 */
class RemoveTryCatch implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.ping();
  }

  private final AbstractCompiler compiler;
  private final Set<Node> tryNodesContainingReturnStatements;

  RemoveTryCatch(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[1]++;
    this.tryNodesContainingReturnStatements = new HashSet<Node>();
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[2]++;
  }

  /**
   * Do all processing on the root node.
   */
  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, new RemoveTryCatchCode());
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[3]++;
  }

  private class RemoveTryCatchCode extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[4]++;
      switch (n.getType()) {
        case Token.TRY:
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[1]++;
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[5]++;
          // Ignore the try statement if it has the @preserveTry annotation
          // (for expected exceptions).
          JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
          if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((info.shouldPreserveTry()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[2]++;
            return;

          } else {
  CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[3]++;}
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[7]++;

          Node tryBlock = n.getFirstChild();
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[8]++;
          Node catchBlock = tryBlock.getNext();
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[9]++;  // may be null or empty
          Node finallyBlock = catchBlock != null ? catchBlock.getNext() : null;
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;

          // Ignore the try statement if it has a finally part and the try
          // block contains an early return.
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((finallyBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((tryNodesContainingReturnStatements.contains(n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[4]++;
            return;

          } else {
  CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[5]++;}
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;

          // Redeclare vars declared in the catch node to be removed.
          if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((catchBlock.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[6]++;
            NodeUtil.redeclareVarsInsideBranch(catchBlock);
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[12]++;

          } else {
  CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[7]++;}

          // Disconnect the try/catch/finally nodes from the parent
          // and each other.
          n.detachChildren();
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[13]++;

          // try node
          Node block;
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(parent)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[8]++;
            block = IR.block();
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[15]++;
            parent.replaceChild(n, block);
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[16]++;
            block.addChildToFront(tryBlock);
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[17]++;

          } else {
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[9]++;
            parent.replaceChild(n, tryBlock);
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[18]++;
            block = parent;
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[19]++;
          }
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;

          // finally node
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((finallyBlock != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[10]++;
            block.addChildAfter(finallyBlock, tryBlock);
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[21]++;

          } else {
  CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[11]++;}
          compiler.reportCodeChange();
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[22]++;
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[23]++;
          break;

        case Token.RETURN:
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[12]++;
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[24]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
          for (Node anc = parent;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((anc != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((anc.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false);
               anc = anc.getParent()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.loops[1]--;
  CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.loops[2]--;
  CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.loops[3]++;
}
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[25]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((anc.isTry()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[13]++;
              tryNodesContainingReturnStatements.add(anc);
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[26]++;
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[27]++;
              break;

            } else {
  CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[14]++;}
          }
CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.statements[28]++;
          break; default : CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1.branches[15]++;
      }
    }
  }
}

class CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1 ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[16];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RemoveTryCatch.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,2,1};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$1cq4a6dubb1dhczz59ywx9r6hrn4o1 () {
    super("com.google.javascript.jscomp.RemoveTryCatch.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 15; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.RemoveTryCatch.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 15; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

