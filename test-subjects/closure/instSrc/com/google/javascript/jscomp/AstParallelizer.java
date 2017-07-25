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

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.List;

/**
 * Breaks up the AST at different levels for parallel analysis and
 * optimizations. In all cases, the subtrees are detached from the original
 * source tree and are replaced by place-holders for the reverse process.
 * Although this class breaks the AST into independent subtrees and make tree
 * transformation safe, it is still up to individual passes to preserve proper
 * semantics when analyzing the subtrees.
 *
 */
class AstParallelizer {
  static {
    CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.ping();
  }


  public static final String TEMP_NAME = "JSC_TMP_PLACE_HOLDER";
  static {
    CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[1]++;
  }

  private final Predicate<Node> shouldSplit;

  private final Supplier<Node> placeHolderProvider;

  private final List<Node> forest;

  private final Node root;

  private final boolean includeRoot;

  // Maps to place holder to the original function.
  private final List<DetachPoint> detachPointList;

  /**
   * Constructor.
   *
   * @param shouldSplit Specify at which node it should split the subtree.
   * @param shouldTraverse Specify when to stop looking for subtree to split.
   *     This is <b>very</b> important for performance as we do not want to
   *     traverse too much just looking for subtree.
   * @param placeHolderProvider Specify what type of node should be place as
   *     a temporary place holder for where the subtree is detached.
   * @param root The AST itself.
   * @param includeRoot Should we include the root inside the forest returned
   *     by {{@link #split()}.
   */
  public AstParallelizer(
      Predicate<Node> shouldSplit,
      Predicate<Node> shouldTraverse,
      Supplier<Node> placeHolderProvider,
      Node root,
      boolean includeRoot) {
    this.shouldSplit = shouldSplit;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[2]++;
    this.placeHolderProvider = placeHolderProvider;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[3]++;
    this.root = root;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[4]++;
    this.includeRoot = includeRoot;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[5]++;
    this.forest = Lists.newLinkedList();
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[6]++;
    this.detachPointList = Lists.newLinkedList();
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[7]++;
  }

  public static AstParallelizer createNewFunctionLevelAstParallelizer(
      Node root, boolean globalPass) {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[8]++;

    // Split at function level.
    Predicate<Node> shouldSplit = new Predicate<Node>() {
      @Override
      public boolean apply(Node input) {
        return input.isFunction();
      }
    };
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[9]++;

    // Always traverse until it finds a split point.
    Predicate<Node> shouldTraverse = new Predicate<Node>() {
      @Override
      public boolean apply(Node ignored) {
        return true;
      }
    };
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[10]++;

    // Use a function declaration of the same name.
    Supplier<Node> placeHolders = new Supplier<Node>() {
      @Override
      public Node get() {
        return IR.function(IR.name(TEMP_NAME), IR.paramList(), IR.block());
      }
    };
    return new AstParallelizer(
        shouldSplit, shouldTraverse, placeHolders, root, globalPass);
  }

  public static AstParallelizer createNewFileLevelAstParallelizer(Node root) {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[11]++;

    // Split at every node that has a file name prop.
    Predicate<Node> shouldSplit = new Predicate<Node>() {
      @Override
      public boolean apply(Node input) {
        return input.getSourceFileName() != null;
      }
    };
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[12]++;

    // Use a string as place holder.
    Supplier<Node> placeHolders = new Supplier<Node>() {
      @Override
      public Node get() {
        return NodeUtil.newExpr(IR.string(TEMP_NAME));
      }
    };
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[13]++;

    // Only traverse blocks.
    Predicate<Node> shouldTraverse = new Predicate<Node>() {
      @Override
      public boolean apply(Node n) {
        return n.isBlock();
      }
    };
    return new AstParallelizer(
        shouldSplit, shouldTraverse, placeHolders, root, false);
  }

  /**
   * Remembers the split point for use in {@link #join()}.
   */
  private void recordSplitPoint(Node placeHolder, Node before, Node original) {
    detachPointList.add(new DetachPoint(placeHolder, before, original));
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[14]++;
  }

  /**
   * Splits the AST into subtree at different levels. The subtrees itself are
   * usually not valid JavaScript but they are all subtrees of some valid
   * JavaScript.
   */
  public List<Node> split() {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((includeRoot) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[1]++;
      forest.add(root);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[16]++;

    } else {
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[2]++;}
    split(root);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[17]++;
    return forest;
  }

  private void split(Node n) {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[18]++;
    Node c = n.getFirstChild();
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[19]++;
    Node before = null;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[20]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
    while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[1]--;
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[2]--;
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[3]++;
}
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[21]++;
      Node next = c.getNext();
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((shouldSplit.apply(c)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[3]++;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[23]++;
        Node placeHolder = placeHolderProvider.get();
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((before == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[5]++;
          forest.add(n.removeFirstChild());
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[25]++;
          n.addChildToFront(placeHolder);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[26]++;

        } else {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[6]++;
          n.addChildAfter(placeHolder, c);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[27]++;
          n.removeChildAfter(before);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[28]++;
          forest.add(c);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[29]++;
        }
        recordSplitPoint(placeHolder, before, c);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[30]++;
        before = placeHolder;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[31]++;

      } else {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[4]++;
        split(c);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[32]++;
        before = c;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[33]++;
      }
      c = next;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[34]++;
    }
  }

  /**
   * Reverse the splitting done by {@link #split()}.
   */
  public void join() {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[35]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
    // Revert in a reverse order to undo the detachment.
    while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((detachPointList.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[4]--;
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[5]--;
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.loops[6]++;
}
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[36]++;
      DetachPoint entry = detachPointList.remove(detachPointList.size() - 1);
      entry.reattach();
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[37]++;
    }
  }

  /**
   * A class to map the place holder to the original subtree for re-attachment.
   * Normally a Map from Node -> Node is sufficient, however, if we also
   * remember the node before the place holder, we can avoid using
   * {@link Node#replaceChild(Node, Node)} which requires a linear search of
   * the before node. Maybe someday we should get a prev pointer for this
   * purpose.
   */
  private static class DetachPoint {

    // The place holder to remember where the original node was.
    private Node placeHolder;

    // The node before the place holder and the original, null if
    private Node before;

    // The root of the subtree to be temporary detached.
    private Node original;

    private DetachPoint(Node placeHolder, Node before, Node original) {
      this.placeHolder = placeHolder;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[38]++;
      this.before = before;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[39]++;
      this.original = original;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[40]++;
    }

    public void reattach() {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[41]++;
int CodeCoverConditionCoverageHelper_C6;
      // If the place-holder no longer has a parent, this implies the function
      // has been removed from the AST.
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((placeHolder.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[7]++;
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((before == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[9]++;
          placeHolder.getParent().addChildrenToFront(original);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[43]++;
          placeHolder.getParent().removeChildAfter(original);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[44]++;

        } else {
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[10]++;
          placeHolder.getParent().addChildAfter(original, before);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[45]++;
          placeHolder.getParent().removeChildAfter(original);
CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.statements[46]++;
        }

      } else {
  CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275.branches[8]++;}
    }
  }
}

class CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275 ());
  }
    public static long[] statements = new long[47];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AstParallelizer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$7n8g2htm8zgbp1px4tji0kfyraph275 () {
    super("com.google.javascript.jscomp.AstParallelizer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 46; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AstParallelizer.java");
      for (int i = 1; i <= 46; i++) {
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
    for (int i = 1; i <= 7; i++) {
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

