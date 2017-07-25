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
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.GraphNode;
import com.google.javascript.jscomp.graph.LatticeElement;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Computes "may be" reaching use for all definitions of each variables.
 *
 * A use of {@code A} in {@code alert(A)} is a "may be" reaching use of
 * the definition of {@code A} at {@code A = foo()} if at least one path from
 * the use node reaches that definition and it is the last definition before
 * the use on that path.
 *
 */
class MaybeReachingVariableUse extends
    DataFlowAnalysis<Node, MaybeReachingVariableUse.ReachingUses> {
  static {
    CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.ping();
  }


  // The scope of the function that we are analyzing.
  private final Scope jsScope;
  private final Set<Var> escaped;

  MaybeReachingVariableUse(
      ControlFlowGraph<Node> cfg, Scope jsScope, AbstractCompiler compiler) {
    super(cfg, new ReachingUsesJoinOp());
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[1]++;
    this.jsScope = jsScope;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[2]++;
    this.escaped = Sets.newHashSet();
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[3]++;

    // TODO(user): Maybe compute it somewhere else and re-use the escape
    // local set here.
    computeEscaped(jsScope, escaped, compiler);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[4]++;
  }

  /**
   * May use definition lattice representation. It captures a product
   * lattice for each local (non-escaped) variable. The sub-lattice is
   * a n + 2 power set element lattice with all the Nodes in the program,
   * TOP and BOTTOM. This is better explained with an example:
   *
   * Consider: A sub-lattice element representing the variable A represented
   * by { N_4, N_5} where N_x is a Node in the program. This implies at
   * that particular point in the program the content of A is "upward exposed"
   * at point N_4 and N_5.
   *
   * Example:
   *
   * A = 1;
   * ...
   * N_3:
   * N_4: print(A);
   * N_5: y = A;
   * N_6: A = 1;
   * N_7: print(A);
   *
   * At N_3, reads of A in {N_4, N_5} are said to be upward exposed.
   */
  static final class ReachingUses implements LatticeElement {
    final Multimap<Var, Node> mayUseMap;

    public ReachingUses() {
      mayUseMap = HashMultimap.create();
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[5]++;
    }

    /**
     * Copy constructor.
     *
     * @param other The constructed object is a replicated copy of this element.
     */
    public ReachingUses(ReachingUses other) {
      mayUseMap = HashMultimap.create(other.mayUseMap);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[6]++;
    }

    @Override
    public boolean equals(Object other) {
      return (other instanceof ReachingUses) &&
          ((ReachingUses) other).mayUseMap.equals(this.mayUseMap);
    }

    @Override
    public int hashCode() {
      return mayUseMap.hashCode();
    }
  }

  /**
   * The join is a simple union because of the "may be" nature of the analysis.
   *
   * Consider: A = 1; if (x) { A = 2 }; alert(A);
   *
   * The read of A "may be" exposed to A = 1 in the beginning.
   */
  private static class ReachingUsesJoinOp implements JoinOp<ReachingUses> {
    @Override
    public ReachingUses apply(List<ReachingUses> from) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[7]++;
      ReachingUses result = new ReachingUses();
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[1]++;


      for (ReachingUses uses : from) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[1]--;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[2]--;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[3]++;
}
        result.mayUseMap.putAll(uses.mayUseMap);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[9]++;
      }
      return result;
    }
  }

  @Override
  boolean isForward() {
    return false;
  }

  @Override
  ReachingUses createEntryLattice() {
    return new ReachingUses();
  }

  @Override
  ReachingUses createInitialEstimateLattice() {
    return new ReachingUses();
  }

  @Override
  ReachingUses flowThrough(Node n, ReachingUses input) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[10]++;
    ReachingUses output = new ReachingUses(input);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[11]++;

    // If there's an ON_EX edge, this cfgNode may or may not get executed.
    // We can express this concisely by just pretending this happens in
    // a conditional.
    boolean conditional = hasExceptionHandler(n);
    computeMayUse(n, n, output, conditional);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[12]++;

    return output;
  }

  private boolean hasExceptionHandler(Node cfgNode) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[13]++;
    List<DiGraphEdge<Node, Branch>> branchEdges = getCfg().getOutEdges(cfgNode);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[4]++;


    for (DiGraphEdge<Node, Branch> edge : branchEdges) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[4]--;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[5]--;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[6]++;
}
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[15]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((edge.getValue() == Branch.ON_EX) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[1]++;
        return true;

      } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[2]++;}
    }
    return false;
  }

  private void computeMayUse(
      Node n, Node cfgNode, ReachingUses output, boolean conditional) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[16]++;
    switch (n.getType()) {

      case Token.BLOCK:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[3]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[4]++;
        return;

      case Token.NAME:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[5]++;
        addToUseIfLocal(n.getString(), cfgNode, output);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[17]++;
        return;

      case Token.WHILE:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[6]++;
      case Token.DO:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[7]++;
      case Token.IF:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[8]++;
        computeMayUse(
            NodeUtil.getConditionExpression(n), cfgNode, output, conditional);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[18]++;
        return;

      case Token.FOR:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[9]++;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(n)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[10]++;
          computeMayUse(
              NodeUtil.getConditionExpression(n), cfgNode, output, conditional);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[20]++;

        } else {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[11]++;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[21]++;
          // for(x in y) {...}
          Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[22]++;
          Node rhs = lhs.getNext();
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
          if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((lhs.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[12]++;
            lhs = lhs.getLastChild();
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[24]++;
 // for(var x in y) {...}
          } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[13]++;}
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((conditional) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[14]++;
            removeFromUseIfLocal(lhs.getString(), output);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[26]++;

          } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[15]++;}
          computeMayUse(rhs, cfgNode, output, conditional);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[27]++;
        }
        return;

      case Token.AND:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[16]++;
      case Token.OR:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[17]++;
        computeMayUse(n.getLastChild(), cfgNode, output, true);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[28]++;
        computeMayUse(n.getFirstChild(), cfgNode, output, conditional);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[29]++;
        return;

      case Token.HOOK:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[18]++;
        computeMayUse(n.getLastChild(), cfgNode, output, true);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[30]++;
        computeMayUse(n.getFirstChild().getNext(), cfgNode, output, true);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[31]++;
        computeMayUse(n.getFirstChild(), cfgNode, output, conditional);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[32]++;
        return;

      case Token.VAR:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[19]++;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[33]++;
        Node varName = n.getFirstChild();
        Preconditions.checkState(n.hasChildren(), "AST should be normalized");
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[34]++;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((varName.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[20]++;
          computeMayUse(varName.getFirstChild(), cfgNode, output, conditional);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[36]++;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[37]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((conditional) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[22]++;
            removeFromUseIfLocal(varName.getString(), output);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[38]++;

          } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[23]++;}

        } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[21]++;}
        return;

      default:
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[24]++;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[25]++;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[40]++;
          Node name = n.getFirstChild();
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((conditional) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[27]++;
            removeFromUseIfLocal(name.getString(), output);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[42]++;

          } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[28]++;}
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[43]++;
int CodeCoverConditionCoverageHelper_C9;

          // In case of a += "Hello". There is a read of a.
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[29]++;
            addToUseIfLocal(name.getString(), cfgNode, output);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[44]++;

          } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[30]++;}

          computeMayUse(name.getNext(), cfgNode, output, conditional);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[45]++;

        } else {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[26]++;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[46]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;
          /*
           * We want to traverse in reverse order because we want the LAST
           * definition in the sub-tree....
           * But we have no better way to traverse in reverse other :'(
           */
          for (Node c = n.getLastChild();(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); c = n.getChildBefore(c)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[7]--;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[8]--;
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.loops[9]++;
}
            computeMayUse(c, cfgNode, output, conditional);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[47]++;
          }
        }
    }
  }

  /**
   * Sets the variable for the given name to the node value in the upward
   * exposed lattice. Do nothing if the variable name is one of the escaped
   * variable.
   */
  private void addToUseIfLocal(String name, Node node, ReachingUses use) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[48]++;
    Var var = jsScope.getVar(name);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((var.scope != jsScope) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[31]++;
      return;

    } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[32]++;}
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((escaped.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[33]++;
      use.mayUseMap.put(var, node);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[51]++;

    } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[34]++;}
  }

  /**
   * Removes the variable for the given name from the node value in the upward
   * exposed lattice. Do nothing if the variable name is one of the escaped
   * variable.
   */
  private void removeFromUseIfLocal(String name, ReachingUses use) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[52]++;
    Var var = jsScope.getVar(name);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[53]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((var.scope != jsScope) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[35]++;
      return;

    } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[36]++;}
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[54]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((escaped.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[37]++;
      use.mayUseMap.removeAll(var);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[55]++;

    } else {
  CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.branches[38]++;}
  }

  /**
   * Gets a list of nodes that may be using the value assigned to {@code name}
   * in {@code defNode}. {@code defNode} must be one of the control flow graph
   * nodes.
   *
   * @param name name of the variable. It can only be names of local variable
   *     that are not function parameters, escaped variables or variables
   *     declared in catch.
   * @param defNode The list of upward exposed use for the variable.
   */
  Collection<Node> getUses(String name, Node defNode) {
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[56]++;
    GraphNode<Node, Branch> n = getCfg().getNode(defNode);
    Preconditions.checkNotNull(n);
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[57]++;
CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d.statements[58]++;
    FlowState<ReachingUses> state = n.getAnnotation();
    return state.getOut().mayUseMap.get(jsScope.getVar(name));
  }
}

class CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d ());
  }
    public static long[] statements = new long[59];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.MaybeReachingVariableUse.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,2,1,1,1,2,1,2,1};
    for (int i = 1; i <= 14; i++) {
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

  public CodeCoverCoverageCounter$6y8dw11zf5s2atvsru2686o3kz3sa4usov0odexcj7e5d () {
    super("com.google.javascript.jscomp.MaybeReachingVariableUse.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 58; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 14; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.MaybeReachingVariableUse.java");
      for (int i = 1; i <= 58; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 14; i++) {
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

