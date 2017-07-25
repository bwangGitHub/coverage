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
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.jscomp.graph.LatticeElement;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.BitSet;
import java.util.List;
import java.util.Set;

/**
 * Compute the "liveness" of all local variables. A variable is "live" at a
 * point of a program if the value it is currently holding might be read later.
 * Otherwise, the variable is considered "dead" if we know for sure that it will
 * no longer be read. Dead variables are candidates for dead assignment
 * elimination and variable name sharing. The worst case safe assumption is to
 * assume that all variables are live. In that case, we will have no opportunity
 * for optimizations. This is especially the case within a TRY block when an
 * assignment is not guaranteed to take place. We bail out by assuming that
 * all variables are live.
 * <p>
 * Due to the possibility of inner functions and closures, certain "local"
 * variables can escape the function. These variables will be considered as
 * global and they can be retrieved with {@link #getEscapedLocals()}.
 *
 */
class LiveVariablesAnalysis extends
    DataFlowAnalysis<Node, LiveVariablesAnalysis.LiveVariableLattice> {
  static {
    CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.ping();
  }


  // 100 = ((# of original Power Rangers) ^
  //        (# years of Warren Harding in office)) *
  //       (# of Ninja Turtles)
  static final int MAX_VARIABLES_TO_ANALYZE = 100;
  static {
    CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[1]++;
  }

  public static final String ARGUMENT_ARRAY_ALIAS = "arguments";
  static {
    CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[2]++;
  }

  private static class LiveVariableJoinOp
      implements JoinOp<LiveVariableLattice> {
    @Override
    public LiveVariableLattice apply(List<LiveVariableLattice> in) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[3]++;
      LiveVariableLattice result = new LiveVariableLattice(in.get(0));
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
      for (int i = 1;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < in.size()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[1]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[2]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[3]++;
}
        result.liveSet.or(in.get(i).liveSet);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[5]++;
      }
      return result;
    }
  }

  /**
   * The lattice that stores the liveness of all local variables at a given
   * point in the program. The whole lattice is the power set of all local
   * variables and a variable is live if it is in the set.
   */
  static class LiveVariableLattice implements LatticeElement {
    private final BitSet liveSet;

    /**
     * @param numVars Number of all local variables.
     */
    private LiveVariableLattice(int numVars) {
      this.liveSet = new BitSet(numVars);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[6]++;
    }

    private LiveVariableLattice(LiveVariableLattice other) {
      Preconditions.checkNotNull(other);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[7]++;
      this.liveSet = (BitSet) other.liveSet.clone();
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[8]++;
    }

    @Override
    public boolean equals(Object other) {
      Preconditions.checkNotNull(other);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[9]++;
      return (other instanceof LiveVariableLattice) &&
          this.liveSet.equals(((LiveVariableLattice) other).liveSet);
    }

    public boolean isLive(Var v) {
      Preconditions.checkNotNull(v);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[10]++;
      return liveSet.get(v.index);
    }

    public boolean isLive(int index) {
      return liveSet.get(index);
    }

    @Override
    public String toString() {
      return liveSet.toString();
    }

    @Override
    public int hashCode() {
      return liveSet.hashCode();
    }
  }

  // The scope of the function that we are analyzing.
  private final Scope jsScope;
  private final Set<Var> escaped;

  LiveVariablesAnalysis(ControlFlowGraph<Node> cfg, Scope jsScope,
      AbstractCompiler compiler) {
    super(cfg, new LiveVariableJoinOp());
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[11]++;
    this.jsScope = jsScope;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[12]++;
    this.escaped = Sets.newHashSet();
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[13]++;
    computeEscaped(jsScope, escaped, compiler);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[14]++;
  }

  public Set<Var> getEscapedLocals() {
    return escaped;
  }

  public int getVarIndex(String var) {
    return jsScope.getVar(var).index;
  }

  @Override
  boolean isForward() {
    return false;
  }

  @Override
  LiveVariableLattice createEntryLattice() {
    return new LiveVariableLattice(jsScope.getVarCount());
  }

  @Override
  LiveVariableLattice createInitialEstimateLattice() {
    return new LiveVariableLattice(jsScope.getVarCount());
  }

  @Override
  LiveVariableLattice flowThrough(Node node, LiveVariableLattice input) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[15]++;
    final BitSet gen = new BitSet(input.liveSet.size());
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[16]++;
    final BitSet kill = new BitSet(input.liveSet.size());
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[17]++;

    // Make kills conditional if the node can end abruptly by an exception.
    boolean conditional = false;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[18]++;
    List<DiGraphEdge<Node, Branch>> edgeList = getCfg().getOutEdges(node);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[19]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[4]++;


    for (DiGraphEdge<Node, Branch> edge : edgeList) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[4]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[5]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[6]++;
}
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Branch.ON_EX.equals(edge.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[1]++;
        conditional = true;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[21]++;

      } else {
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[2]++;}
    }
    computeGenKill(node, gen, kill, conditional);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[22]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[23]++;
    LiveVariableLattice result = new LiveVariableLattice(input);
    // L_in = L_out - Kill + Gen
    result.liveSet.andNot(kill);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[24]++;
    result.liveSet.or(gen);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[25]++;
    return result;
  }

  /**
   * Computes the GEN and KILL set.
   *
   * @param n Root node.
   * @param gen Local variables that are live because of the instruction at
   *        {@code n} will be added to this set.
   * @param kill Local variables that are killed because of the instruction at
   *        {@code n} will be added to this set.
   * @param conditional {@code true} if any assignments encountered are
   *        conditionally executed. These assignments might not kill a variable.
   */
  private void computeGenKill(Node n, BitSet gen, BitSet kill,
      boolean conditional) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[26]++;

    switch (n.getType()) {
      case Token.SCRIPT:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[3]++;
      case Token.BLOCK:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[4]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[5]++;
        return;

      case Token.WHILE:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[6]++;
      case Token.DO:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[7]++;
      case Token.IF:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[8]++;
        computeGenKill(NodeUtil.getConditionExpression(n), gen, kill,
            conditional);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[27]++;
        return;

      case Token.FOR:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[9]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.isForIn(n)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[10]++;
          computeGenKill(NodeUtil.getConditionExpression(n), gen, kill,
              conditional);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[29]++;

        } else {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[11]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[30]++;
          // for(x in y) {...}
          Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[31]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((lhs.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[12]++;
            // for(var x in y) {...}
            lhs = lhs.getLastChild();
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[32]++;

          } else {
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[13]++;}
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;

          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[14]++;
            addToSetIfLocal(lhs, kill);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[34]++;
            addToSetIfLocal(lhs, gen);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[35]++;

          } else {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[15]++;
            computeGenKill(lhs, gen, kill, conditional);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[36]++;
          }

          // rhs is executed only once so we don't go into it every loop.
        }
        return;

      case Token.VAR:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[16]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[37]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
        for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[7]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[8]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[9]++;
}
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[17]++;
            computeGenKill(c.getFirstChild(), gen, kill, conditional);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[39]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((conditional) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[19]++;
              addToSetIfLocal(c, kill);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[41]++;

            } else {
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[20]++;}

          } else {
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[18]++;}
        }
        return;

      case Token.AND:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[21]++;
      case Token.OR:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[22]++;
        computeGenKill(n.getFirstChild(), gen, kill, conditional);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[42]++;
        // May short circuit.
        computeGenKill(n.getLastChild(), gen, kill, true);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[43]++;
        return;

      case Token.HOOK:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[23]++;
        computeGenKill(n.getFirstChild(), gen, kill, conditional);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[44]++;
        // Assume both sides are conditional.
        computeGenKill(n.getFirstChild().getNext(), gen, kill, true);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[45]++;
        computeGenKill(n.getLastChild(), gen, kill, true);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[46]++;
        return;

      case Token.NAME:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[24]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isArgumentsName(n)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[25]++;
          markAllParametersEscaped();
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[48]++;

        } else {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[26]++;
          addToSetIfLocal(n, gen);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[49]++;
        }
        return;

      default:
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[27]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[50]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(n)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[28]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[51]++;
          Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[52]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((conditional) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[30]++;
            addToSetIfLocal(lhs, kill);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[53]++;

          } else {
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[31]++;}
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[54]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[32]++;
            // assignments such as a += 1 reads a.
            addToSetIfLocal(lhs, gen);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[55]++;

          } else {
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[33]++;}
          computeGenKill(lhs.getNext(), gen, kill, conditional);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[56]++;

        } else {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[29]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[57]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[10]++;


int CodeCoverConditionCoverageHelper_C13;
          for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[10]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[11]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[12]++;
}
            computeGenKill(c, gen, kill, conditional);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[58]++;
          }
        }
        return;
    }
  }

  private void addToSetIfLocal(Node node, BitSet set) {
    Preconditions.checkState(node.isName());
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[59]++;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[60]++;
    String name = node.getString();
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[61]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((jsScope.isDeclared(name, false)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[34]++;
      return;

    } else {
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[35]++;}
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[62]++;
    Var var = jsScope.getVar(name);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[63]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((escaped.contains(var)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[36]++;
      set.set(var.index);
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[64]++;

    } else {
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[37]++;}
  }

  /**
   * Give up computing liveness of formal parameter by putting all the parameter
   * names in the escaped set.
   */
  void markAllParametersEscaped() {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[65]++;
    Node lp = jsScope.getRootNode().getFirstChild().getNext();
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[66]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[13]++;


int CodeCoverConditionCoverageHelper_C16;
    for(Node arg = lp.getFirstChild();(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((arg != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); arg = arg.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[13]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[14]--;
  CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.loops[15]++;
}
      escaped.add(jsScope.getVar(arg.getString()));
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[67]++;
    }
  }

  private boolean isArgumentsName(Node n) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.statements[68]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((n.getString().equals(ARGUMENT_ARRAY_ALIAS)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((jsScope.isDeclared(ARGUMENT_ARRAY_ALIAS, false)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) || true)) || (CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) && false)) {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[38]++;
      return false;

    } else {
CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht.branches[39]++;
      return true;
    }
  }
}

class CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht ());
  }
    public static long[] statements = new long[69];
    public static long[] branches = new long[40];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.LiveVariablesAnalysis.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,3};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$oqkbszl9lhhf08d5f9do4tprnme6q8volniqjiht () {
    super("com.google.javascript.jscomp.LiveVariablesAnalysis.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 68; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 39; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.LiveVariablesAnalysis.java");
      for (int i = 1; i <= 68; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 39; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

