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
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

/**
 * The goal with this pass is to reverse the simplifications done in the
 * normalization pass that are not handled by other passes (such as
 * CollapseVariableDeclarations) to avoid making the resulting code larger.
 *
 * Currently this pass only does one thing pushing statements into for-loop
 * initializer. This:
 *   var a = 0; for(;a<0;a++) {}
 * becomes:
 *   for(var a = 0;a<0;a++) {}
 *
 * @author johnlenz@google.com (johnlenz)
 */
class Denormalize implements CompilerPass, Callback {
  static {
    CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.ping();
  }


  private final AbstractCompiler compiler;

  Denormalize(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[1]++;
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[2]++;
  }

  @Override
  public boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
    return true;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
    maybeCollapseIntoForStatements(n, parent);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[3]++;
  }

  /**
   * Collapse VARs and EXPR_RESULT node into FOR loop initializers where
   * possible.
   */
  private void maybeCollapseIntoForStatements(Node n, Node parent) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    // Only SCRIPT, BLOCK, and LABELs can have FORs that can be collapsed into.
    // LABELs are not supported here.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(parent)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[1]++;
      return;

    } else {
  CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[2]++;}
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;

    // Is the current node something that can be in a for loop initializer?
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((n.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[4]++;}
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[6]++;

    // Is the next statement a valid FOR?
    Node nextSibling = n.getNext();
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[7]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((nextSibling == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[5]++;
      return;

    } else {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[6]++;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[8]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(nextSibling)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[7]++;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[9]++;
      Node forNode = nextSibling;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[10]++;
      Node forVar = forNode.getFirstChild();
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[11]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((forVar.isName()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 3) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[9]++;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[12]++;
        Node name = n.getFirstChild();
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[13]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((name.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((forVar.getString().equals(name.getString())) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[11]++;
          // OK, the names match, and the var declaration does not have an
          // initializer. Move it into the loop.
          parent.removeChild(n);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[14]++;
          forNode.replaceChild(forVar, n);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[15]++;
          compiler.reportCodeChange();
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[16]++;

        } else {
  CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[12]++;}

      } else {
  CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[10]++;}

    } else {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[8]++;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[17]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((nextSibling.isFor()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((nextSibling.getFirstChild().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[13]++;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[18]++;
int CodeCoverConditionCoverageHelper_C8;

      // Does the current node contain an in operator?  If so, embedding
      // the expression in a for loop can cause some JavaScript parsers (such
      // as the PlayStation 3's browser based on Access's NetFront
      // browser) to fail to parse the code.
      // See bug 1778863 for details.
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.containsType(n, Token.IN)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[15]++;
        return;

      } else {
  CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[16]++;}
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[19]++;

      // Move the current node into the FOR loop initializer.
      Node forNode = nextSibling;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[20]++;
      Node oldInitializer = forNode.getFirstChild();
      parent.removeChild(n);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[21]++;

      Node newInitializer;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[22]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[17]++;
        newInitializer = n;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[23]++;

      } else {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[18]++;
        // Extract the expression from EXPR_RESULT node.
        Preconditions.checkState(n.hasOneChild());
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[24]++;
        newInitializer = n.getFirstChild();
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[25]++;
        n.removeChild(newInitializer);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[26]++;
      }

      forNode.replaceChild(oldInitializer, newInitializer);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[27]++;

      compiler.reportCodeChange();
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[28]++;

    } else {
  CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[14]++;}
}
}
  }

  static class StripConstantAnnotations
      extends AbstractPostOrderCallback
      implements CompilerPass {
    private AbstractCompiler compiler;

    StripConstantAnnotations(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[29]++;
    }

    @Override
    public void process(Node externs, Node js) {
      NodeTraversal.traverse(compiler, externs, this);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[30]++;
      NodeTraversal.traverse(compiler, js, this);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[31]++;
    }

    @Override
    public void visit(NodeTraversal t, Node node, Node parent) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((node.isString()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((node.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[19]++;
        node.removeProp(Node.IS_CONSTANT_NAME);
CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.statements[33]++;

      } else {
  CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych.branches[20]++;}
    }
  }
}

class CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych ());
  }
    public static long[] statements = new long[34];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.Denormalize.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,3,2,2,1,1,3};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$41rshagp8i60shht2nfwtuych () {
    super("com.google.javascript.jscomp.Denormalize.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 33; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.Denormalize.java");
      for (int i = 1; i <= 33; i++) {
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
    for (int i = 1; i <= 10; i++) {
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

