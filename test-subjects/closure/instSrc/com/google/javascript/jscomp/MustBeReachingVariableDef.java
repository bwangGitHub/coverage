/*
 * Copyright 2008 The Closure Compiler Authors.
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
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ControlFlowGraph.AbstractCfgNodeTraversalCallback;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.jscomp.graph.LatticeElement;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * Computes reaching definition for all use of each variables.
 *
 * A definition of {@code A} in {@code A = foo()} is a reaching definition of
 * the use of {@code A} in {@code alert(A)} if all paths from entry node must
 * reaches that definition and it is the last definition before the use.
 *
 */
final class MustBeReachingVariableDef extends
    DataFlowAnalysis<Node, MustBeReachingVariableDef.MustDef> {
  static {
    CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.ping();
  }


  // The scope of the function that we are analyzing.
  private final Scope jsScope;
  private final AbstractCompiler compiler;
  private final Set<Var> escaped;

  MustBeReachingVariableDef(
      ControlFlowGraph<Node> cfg, Scope jsScope, AbstractCompiler compiler) {
    super(cfg, new MustDefJoin());
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[1]++;
    this.jsScope = jsScope;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[2]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[3]++;
    this.escaped = Sets.newHashSet();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[4]++;
    computeEscaped(jsScope, escaped, compiler);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[5]++;
  }

  /**
   * Abstraction of a local variable definition. It represents the node which
   * a local variable is defined as well as a set of other local variables that
   * this definition reads from. For example N: a = b + foo.bar(c). The
   * definition node will be N, the depending set would be {b,c}.
   */
  static class Definition {
    final Node node;
    final Set<Var> depends = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[6]++;
  }
    private boolean unknownDependencies = false;
  {
    CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[7]++;
  }

    Definition(Node node) {
      this.node = node;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[8]++;
    }

    @Override
    public boolean equals(Object other) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((other instanceof Definition) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[1]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[2]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[10]++;
      Definition otherDef = (Definition) other;
      // If the var has the same definition node we can assume they have the
      // same depends set.
      return otherDef.node == node;
    }
  }

  /**
   * Must reaching definition lattice representation. It captures a product
   * lattice for each local (non-escaped) variable. The sub-lattice is
   * a n + 2 element lattice with all the {@link Definition} in the program,
   * TOP and BOTTOM.
   *
   * <p>Since this is a Must-Define analysis, BOTTOM represents the case where
   * there might be more than one reaching definition for the variable.
   *
   *
   *           (TOP)
   *       /   |   |      \
   *     N1    N2  N3 ....Nn
   *      \    |   |      /
   *          (BOTTOM)
   *
   */
  static final class MustDef implements LatticeElement {

    // TODO(user): Use bit vector instead of maps might get better
    // performance. Change it after this is tested to be fully functional.

    // When a Var "A" = "TOP", "A" does not exist in reachingDef's keySet.
    // When a Var "A" = Node N, "A" maps to that node.
    // When a Var "A" = "BOTTOM", "A" maps to null.
    final Map<Var, Definition> reachingDef;

    public MustDef() {
      reachingDef = Maps.newHashMap();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[11]++;
    }

    public MustDef(Iterator<Var> vars) {
      this();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[12]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[13]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
      while((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((vars.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[1]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[2]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[3]++;
}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[14]++;
        Var var = vars.next();
        // Every variable in the scope is defined once in the beginning of the
        // function: all the declared variables are undefined, all functions
        // have been assigned and all arguments has its value from the caller.
        reachingDef.put(var, new Definition(var.scope.getRootNode()));
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[15]++;
      }
    }

    /**
     * Copy constructor.
     *
     * @param other The constructed object is a replicated copy of this element.
     */
    public MustDef(MustDef other) {
      reachingDef = Maps.newHashMap(other.reachingDef);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[16]++;
    }

    @Override
    public boolean equals(Object other) {
      return (other instanceof MustDef) &&
          ((MustDef) other).reachingDef.equals(this.reachingDef);
    }
  }

  private static class MustDefJoin extends JoinOp.BinaryJoinOp<MustDef> {
    @Override
    public MustDef apply(MustDef a, MustDef b) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[17]++;
      MustDef result = new MustDef();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[18]++;
      Map<Var, Definition> resultMap = result.reachingDef;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[19]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[4]++;



      // Take the join of all variables that are not TOP in this.
      for (Map.Entry<Var, Definition> varEntry : a.reachingDef.entrySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[4]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[5]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[6]++;
}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[20]++;
        Var var = varEntry.getKey();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[21]++;
        Definition aDef = varEntry.getValue();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((aDef == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[3]++;
          // "a" is BOTTOM implies that the variable has more than one possible
          // definition. We set the join of this to be BOTTOM regardless of what
          // "b" might be.
          resultMap.put(var, null);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[23]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[24]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[4]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((b.reachingDef.containsKey(var)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[5]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[26]++;
          Definition bDef = b.reachingDef.get(var);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;

          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((aDef.equals(bDef)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[7]++;
            resultMap.put(var, aDef);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[28]++;

          } else {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[8]++;
            resultMap.put(var, null);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[29]++;
          }

        } else {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[6]++;
          resultMap.put(var, aDef);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[30]++;
        }
      }
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[31]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[7]++;



      // Take the join of all variables that are not TOP in other but it is TOP
      // in this.
      for (Map.Entry<Var, Definition> entry : b.reachingDef.entrySet()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[7]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[8]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[9]++;
}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[32]++;
        Var var = entry.getKey();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[33]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((a.reachingDef.containsKey(var)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[9]++;
          resultMap.put(var, entry.getValue());
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[34]++;

        } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[10]++;}
      }
      return result;
    }
  }

  @Override
  boolean isForward() {
    return true;
  }

  @Override
  MustDef createEntryLattice() {
    return new MustDef(jsScope.getVars());
  }

  @Override
  MustDef createInitialEstimateLattice() {
    return new MustDef();
  }

  @Override
  MustDef flowThrough(Node n, MustDef input) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[35]++;
    // TODO(user): We are doing a straight copy from input to output. There
    // might be some opportunities to cut down overhead.
    MustDef output = new MustDef(input);
    // TODO(user): This must know about ON_EX edges but it should handle
    // it better than what we did in liveness. Because we are in a forward mode,
    // we can used the branched forward analysis.
    computeMustDef(n, n, output, false);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[36]++;
    return output;
  }

  /**
   * @param n The node in question.
   * @param cfgNode The node to add
   * @param conditional true if the definition is not always executed.
   */
  private void computeMustDef(
      Node n, Node cfgNode, MustDef output, boolean conditional) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[37]++;
    switch (n.getType()) {

      case Token.BLOCK:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[11]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[12]++;
        return;

      case Token.WHILE:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[13]++;
      case Token.DO:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[14]++;
      case Token.IF:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[15]++;
        computeMustDef(
            NodeUtil.getConditionExpression(n), cfgNode, output, conditional);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[38]++;
        return;

      case Token.FOR:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[16]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[17]++;
          computeMustDef(
              NodeUtil.getConditionExpression(n), cfgNode, output, conditional);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[40]++;

        } else {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[18]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[41]++;
          // for(x in y) {...}
          Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[42]++;
          Node rhs = lhs.getNext();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[43]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((lhs.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[19]++;
            lhs = lhs.getLastChild();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[44]++;
 // for(var x in y) {...}
          } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[20]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[21]++;
            addToDefIfLocal(lhs.getString(), cfgNode, rhs, output);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[46]++;

          } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[22]++;}
        }
        return;

      case Token.AND:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[23]++;
      case Token.OR:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[24]++;
        computeMustDef(n.getFirstChild(), cfgNode, output, conditional);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[47]++;
        computeMustDef(n.getLastChild(), cfgNode, output, true);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[48]++;
        return;

      case Token.HOOK:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[25]++;
        computeMustDef(n.getFirstChild(), cfgNode, output, conditional);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[49]++;
        computeMustDef(n.getFirstChild().getNext(), cfgNode, output, true);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[50]++;
        computeMustDef(n.getLastChild(), cfgNode, output, true);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[51]++;
        return;

      case Token.VAR:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[26]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[52]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[10]++;


int CodeCoverConditionCoverageHelper_C10;
        for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[10]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[11]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[12]++;
}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[53]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((c.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[27]++;
            computeMustDef(c.getFirstChild(), cfgNode, output, conditional);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[54]++;
            addToDefIfLocal(c.getString(), conditional ? null : cfgNode,
                c.getFirstChild(), output);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[55]++;

          } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[28]++;}
        }
        return;

      default:
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[29]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(n)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[30]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[57]++;
int CodeCoverConditionCoverageHelper_C13;
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[32]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[58]++;
            Node name = n.getFirstChild();
            computeMustDef(name.getNext(), cfgNode, output, conditional);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[59]++;
            addToDefIfLocal(name.getString(), conditional ? null : cfgNode,
              n.getLastChild(), output);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[60]++;
            return;

          } else {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[33]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[61]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(n.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[34]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[62]++;
            // Treat all assignments to arguments as redefining the
            // parameters itself.
            Node obj = n.getFirstChild().getFirstChild();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[63]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((obj.isName()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 (("arguments".equals(obj.getString())) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[36]++;
              // TODO(user): More accuracy can be introduced
              // i.e. We know exactly what arguments[x] is if x is a constant
              // number.
              escapeParameters(output);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[64]++;

            } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[37]++;}

          } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[35]++;}
}

        } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[31]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[65]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 (("arguments".equals(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[38]++;
          escapeParameters(output);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[66]++;

        } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[39]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[67]++;
int CodeCoverConditionCoverageHelper_C17;

        // DEC and INC actually defines the variable.
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((n.isDec()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((n.isInc()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[40]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[68]++;
          Node target = n.getFirstChild();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[69]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((target.isName()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[42]++;
            addToDefIfLocal(target.getString(),
                conditional ? null : cfgNode, null, output);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[70]++;
            return;

          } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[43]++;}

        } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[41]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[71]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[13]++;


int CodeCoverConditionCoverageHelper_C19;

        for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[13]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[14]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[15]++;
}
          computeMustDef(c, cfgNode, output, conditional);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[72]++;
        }
    }
  }

  /**
   * Set the variable lattice for the given name to the node value in the def
   * lattice. Do nothing if the variable name is one of the escaped variable.
   *
   * @param node The CFG node where the definition should be record to.
   *     {@code null} if this is a conditional define.
   */
  private void addToDefIfLocal( String name, @Nullable Node node,
      @Nullable Node rValue, MustDef def) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[73]++;
    Var var = jsScope.getVar(name);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[74]++;
int CodeCoverConditionCoverageHelper_C20;

    // var might be null because the variable might be defined in the extern
    // that we might not traverse.
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((var.scope != jsScope) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[44]++;
      return;

    } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[45]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[75]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[16]++;



    for (Var other : def.reachingDef.keySet()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[16]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[17]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[18]++;
}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[76]++;
      Definition otherDef = def.reachingDef.get(other);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[77]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((otherDef == null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[46]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[78]++;
        continue;

      } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[47]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[79]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((otherDef.depends.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[48]++;
        def.reachingDef.put(other, null);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[80]++;

      } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[49]++;}
    }
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[81]++;
int CodeCoverConditionCoverageHelper_C23;

    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((escaped.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[50]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[82]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((node == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[52]++;
        def.reachingDef.put(var, null);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[83]++;

      } else {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[53]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[84]++;
        Definition definition = new Definition(node);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[85]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[54]++;
          computeDependence(definition, rValue);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[86]++;

        } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[55]++;}
        def.reachingDef.put(var, definition);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[87]++;
      }

    } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[51]++;}
  }

  private void escapeParameters(MustDef output) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[88]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[19]++;


int CodeCoverConditionCoverageHelper_C26;
    for (Iterator<Var> i = jsScope.getVars();(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false);) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[19]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[20]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[21]++;
}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[89]++;
      Var v = i.next();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[90]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((isParameter(v)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[56]++;
        // Assume we no longer know where the parameter comes from
        // anymore.
        output.reachingDef.put(v, null);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[91]++;

      } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[57]++;}
    }
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[92]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[22]++;



    // Also, assume we no longer know anything that depends on a parameter.
    for (Entry<Var, Definition> pair: output.reachingDef.entrySet()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[22]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[23]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[24]++;
}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[93]++;
      Definition value = pair.getValue();
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[94]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[58]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[95]++;
        continue;

      } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[59]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[96]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[25]++;


      for (Var dep : value.depends) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[25]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[26]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[27]++;
}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[97]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((isParameter(dep)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[60]++;
          output.reachingDef.put(pair.getKey(), null);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[98]++;

        } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[61]++;}
      }
    }
  }

  private boolean isParameter(Var v) {
    return v.getParentNode().isParamList();
  }

  /**
   * Computes all the local variables that rValue reads from and store that
   * in the def's depends set.
   */
  private void computeDependence(final Definition def, Node rValue) {
    NodeTraversal.traverse(compiler, rValue,
        new AbstractCfgNodeTraversalCallback() {
      @Override
      public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[100]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[62]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[101]++;
          Var dep = jsScope.getVar(n.getString());
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[102]++;
int CodeCoverConditionCoverageHelper_C31;
          if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((dep == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[64]++;
            def.unknownDependencies = true;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[103]++;

          } else {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[65]++;
            def.depends.add(dep);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[104]++;
          }

        } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[63]++;}
      }
    });
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[99]++;
  }

  /**
   * Gets the must reaching definition of a given node.
   *
   * @param name name of the variable. It can only be names of local variable
   *     that are not function parameters, escaped variables or variables
   *     declared in catch.
   * @param useNode the location of the use where the definition reaches.
   */
  Definition getDef(String name, Node useNode) {
    Preconditions.checkArgument(getCfg().hasNode(useNode));
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[105]++;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[106]++;
    GraphNode<Node, Branch> n = getCfg().getNode(useNode);
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[107]++;
    FlowState<MustDef> state = n.getAnnotation();
    return state.getIn().reachingDef.get(jsScope.getVar(name));
  }

  Node getDefNode(String name, Node useNode) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[108]++;
    Definition def = getDef(name, useNode);
    return def == null ? null : def.node;
  }

  boolean dependsOnOuterScopeVars(Definition def) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[109]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((def.unknownDependencies) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[66]++;
      return true;

    } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[67]++;}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[110]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[28]++;



    for (Var s : def.depends) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[28]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[29]--;
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.loops[30]++;
}
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.statements[111]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((s.scope != jsScope) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[68]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x.branches[69]++;}
    }
    return false;
  }
}

class CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x ());
  }
    public static long[] statements = new long[112];
    public static long[] branches = new long[70];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[34];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.MustBeReachingVariableDef.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 33; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$1dh88egeaalta4i8jcc71h1ib0mipxqf3kpoyiv14nb1u8x () {
    super("com.google.javascript.jscomp.MustBeReachingVariableDef.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 111; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 69; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.MustBeReachingVariableDef.java");
      for (int i = 1; i <= 111; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 69; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

