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
import com.google.common.collect.Lists;
import com.google.javascript.rhino.Node;

import java.util.ArrayList;

/**
 * A compiler pass to run various peephole optimizations (e.g. constant folding,
 * some useless code removal, some minimizations).
 *
 * @author dcc@google.com (Devin Coughlin)
 * @author acleung@google.com (Alan Leung)(
 */
class PeepholeOptimizationsPass
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.ping();
  }

  private AbstractCompiler compiler;
  
  // Use an array here for faster iteration compared to ImmutableSet
  private final AbstractPeepholeOptimization[] peepholeOptimizations;

  // Track whether the a scope has been modified so that it can be revisited
  // immediately.
  private StateStack traversalState = new StateStack();
  {
    CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[1]++;
  }

  private boolean retraverseOnChange = true;
  {
    CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[2]++;
  }

  static private class ScopeState {
    boolean changed;
    boolean traverseChildScopes;
    ScopeState() {
      reset();
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[3]++;
    }

    void reset() {
      changed = false;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[4]++;
      traverseChildScopes = true;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[5]++;
    }
  }

  static private class StateStack {
    private ArrayList<ScopeState> states = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[6]++;
  }
    private int currentDepth = 0;
  {
    CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[7]++;
  }

    StateStack() {
      states.add(new ScopeState());
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[8]++;
    }

    ScopeState peek() {
      return states.get(currentDepth);
    }

    void push() {
      currentDepth++;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[9]++;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((states.size() <= currentDepth) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[1]++;
        states.add(new ScopeState());
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[11]++;

      } else {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[2]++;
        states.get(currentDepth).reset();
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[12]++;
      }
    }

    void pop() {
      currentDepth--;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[13]++;
    }
  }

  private class PeepholeChangeHandler implements CodeChangeHandler {
    @Override
    public void reportChange() {
      traversalState.peek().changed = true;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[14]++;
    }
  }

  /**
   * Creates a peephole optimization pass that runs the given
   * optimizations.
   */
  PeepholeOptimizationsPass(AbstractCompiler compiler,
      AbstractPeepholeOptimization... optimizations) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[15]++;
    this.peepholeOptimizations = optimizations;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[16]++;
  }
  
  PeepholeOptimizationsPass setRetraverseOnChange(boolean retraverse) {
    this.retraverseOnChange = retraverse;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[17]++;
    return this;
  }

  public AbstractCompiler getCompiler() {
    return compiler;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[18]++;
    PeepholeChangeHandler handler = new PeepholeChangeHandler();
    compiler.addChangeHandler(handler);
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[19]++;
    beginTraversal();
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[20]++;
    traverse(root);
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[21]++;
    endTraversal();
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[22]++;
    compiler.removeChangeHandler(handler);
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[23]++;
  }

  private void traverse(Node node) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;
    // The goal here is to avoid retraversing
    // the entire AST to catch newly created opportunities.
    // So we track whether a "unit of code" has changed,
    // and revisit immediately.
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((shouldVisit(node)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[4]++;}
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[25]++;

    int visits = 0;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[26]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
    do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[1]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[2]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[3]++;
}
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[27]++;
      Node c = node.getFirstChild();
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[28]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
      while((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[4]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[5]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[6]++;
}
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[29]++;
        Node next = c.getNext();
        traverse(c);
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[30]++;
        c = next;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[31]++;
      }

      visit(node);
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[32]++;
      visits++;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[33]++;

      Preconditions.checkState(visits < 10000, "too many interations");
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[34]++;
    } while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((shouldRetraverse(node)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false));

    exitNode(node);
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[35]++;
  }

  private boolean shouldRetraverse(Node node) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[36]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (128)) == 0 || true) &&
 ((retraverseOnChange) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (32)) == 0 || true) &&
 ((node.getParent() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((node.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 4) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 4) && false)) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[5]++;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[37]++;
      ScopeState state = traversalState.peek();
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((state.changed) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[7]++;
        // prepare to re-visit the scope:
        // when revisiting, only visit the immediate scope
        // this reduces the cost of getting to a fixed
        // point in global scope.
        state.changed = false;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[39]++;
        state.traverseChildScopes = false;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[40]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[8]++;}

    } else {
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[6]++;}
    return false;
  }

  private boolean shouldVisit(Node node) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((node.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[9]++;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[42]++;
      ScopeState previous = traversalState.peek();
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[43]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((previous.traverseChildScopes) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[11]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[12]++;}
      traversalState.push();
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[44]++;

    } else {
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[10]++;}
    return true;
  }

  private void exitNode(Node node) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((node.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[13]++;
      traversalState.pop();
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[46]++;

    } else {
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[14]++;}
  }

  public void visit(Node n) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[47]++;
    Node currentVersionOfNode = n;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[48]++;
    boolean somethingChanged = false;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[49]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;

    do {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[7]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[8]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[9]++;
}
      somethingChanged = false;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[50]++;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[51]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[10]++;


      for (AbstractPeepholeOptimization optimization : peepholeOptimizations) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[10]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[11]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[12]++;
}
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[52]++;
        Node newVersionOfNode =
            optimization.optimizeSubtree(currentVersionOfNode);
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;

        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((newVersionOfNode != currentVersionOfNode) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[15]++;
          somethingChanged = true;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[54]++;

          currentVersionOfNode = newVersionOfNode;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[55]++;

        } else {
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[16]++;}
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((currentVersionOfNode == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[17]++;
          return;

        } else {
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.branches[18]++;}
      }
    } while((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((somethingChanged) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false));
  }

  /**
   * Make sure that all the optimizations have the current traversal so they
   * can report errors.
   */
  private void beginTraversal() {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[57]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[13]++;


    for (AbstractPeepholeOptimization optimization : peepholeOptimizations) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[13]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[14]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[15]++;
}
      optimization.beginTraversal(compiler);
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[58]++;
    }
  }

  private void endTraversal() {
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[59]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[16]++;


    for (AbstractPeepholeOptimization optimization : peepholeOptimizations) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[16]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[17]--;
  CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.loops[18]++;
}
      optimization.endTraversal(compiler);
CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881.statements[60]++;
    }
  }
}

class CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881 ());
  }
    public static long[] statements = new long[61];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PeepholeOptimizationsPass.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,3,1,2,1,2,1,1,1};
    for (int i = 1; i <= 12; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$1fcrv4kjjr8ohsz1gpa54zahyd7qarjfcu1nh9ot2for881 () {
    super("com.google.javascript.jscomp.PeepholeOptimizationsPass.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 60; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PeepholeOptimizationsPass.java");
      for (int i = 1; i <= 60; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

