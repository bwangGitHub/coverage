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

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ControlFlowGraph.Branch;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.graph.DiGraph.DiGraphEdge;
import com.google.javascript.rhino.Node;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Chain calls to functions that return this.
 *
 */
class ChainCalls implements CompilerPass {
  static {
    CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.ping();
  }

  private final AbstractCompiler compiler;
  private final Set<Node> badFunctionNodes = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[1]++;
  }
  private final Set<Node> goodFunctionNodes = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[2]++;
  }
  private final List<CallSite> callSites = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[3]++;
  }
  private SimpleDefinitionFinder defFinder;
  private GatherFunctions gatherFunctions = new GatherFunctions();
  {
    CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[4]++;
  }

  ChainCalls(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[5]++;
  }

  @Override
  public void process(Node externs, Node root) {
    defFinder = new SimpleDefinitionFinder(compiler);
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[6]++;
    defFinder.process(externs, root);
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[7]++;

    NodeTraversal.traverse(compiler, root, new GatherCallSites());
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[8]++;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[1]++;



    for (CallSite callSite : callSites) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[1]--;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[2]--;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[3]++;
}
      callSite.parent.removeChild(callSite.n);
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[10]++;
      callSite.n.removeChild(callSite.callNode);
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[11]++;
      callSite.nextGetPropNode.replaceChild(callSite.nextGetPropFirstChildNode,
                                            callSite.callNode);
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[12]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[13]++;
    }
  }

  /**
   * Determines whether a function always returns this.
   */
  private class GatherFunctions implements ScopedCallback {
    @Override
    public void enterScope(NodeTraversal t) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[14]++;
      ControlFlowGraph<Node> cfg = t.getControlFlowGraph();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[4]++;



      for (DiGraphEdge<Node, Branch> s : cfg.getImplicitReturn().getInEdges()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[4]--;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[5]--;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[6]++;
}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[16]++;
        Node exitNode = s.getSource().getValue();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((exitNode.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((exitNode.getFirstChild() == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((exitNode.getFirstChild().isThis()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[1]++;
          badFunctionNodes.add(t.getScopeRoot());
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[18]++;
          return;

        } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[2]++;}
      }

      goodFunctionNodes.add(t.getScopeRoot());
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[19]++;
    }

    @Override
    public void exitScope(NodeTraversal t) {
    }

    @Override
    public boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
                                  Node parent) {
      return true;
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
    }
  }

  private class GatherCallSites extends AbstractPostOrderCallback {
    /**
     * If the function call returns this and the next statement has the same
     * target expression, record the call site.
     */
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[3]++;
        return;

      } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[4]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[21]++;

      Node callNode = n.getFirstChild();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((callNode.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[5]++;
        return;

      } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[6]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[23]++;

      Node getPropNode = callNode.getFirstChild();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((getPropNode.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[7]++;
        return;

      } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[8]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[25]++;

      Node getPropFirstChildNode = getPropNode.getFirstChild();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[26]++;

      Collection<Definition> definitions =
          defFinder.getDefinitionsReferencedAt(getPropNode);
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[27]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((definitions == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[9]++;
        return;

      } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[10]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[28]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[7]++;


      for (Definition definition : definitions) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[7]--;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[8]--;
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.loops[9]++;
}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[29]++;
        Node rValue = definition.getRValue();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((rValue == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[11]++;
          return;

        } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[12]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[31]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((badFunctionNodes.contains(rValue)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[13]++;
          return;

        } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[14]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((goodFunctionNodes.contains(rValue)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[15]++;
          NodeTraversal.traverse(compiler, rValue, gatherFunctions);
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[33]++;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((badFunctionNodes.contains(rValue)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[17]++;
            return;

          } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[16]++;}
      }
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[35]++;

      Node nextNode = n.getNext();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[36]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((nextNode == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((nextNode.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[19]++;
        return;

      } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[20]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[37]++;

      Node nextCallNode = nextNode.getFirstChild();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[38]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((nextCallNode.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[21]++;
        return;

      } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[22]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[39]++;

      Node nextGetPropNode = nextCallNode.getFirstChild();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[40]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((nextGetPropNode.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[23]++;
        return;

      } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[24]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[41]++;

      Node nextGetPropFirstChildNode = nextGetPropNode.getFirstChild();
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[42]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((compiler.areNodesEqualForInlining(
              nextGetPropFirstChildNode, getPropFirstChildNode)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[25]++;
        return;

      } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[26]++;}
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[43]++;
int CodeCoverConditionCoverageHelper_C14;

      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((NodeUtil.mayEffectMutableState(getPropFirstChildNode)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[27]++;
        return;

      } else {
  CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.branches[28]++;}

      // We can't chain immediately as it we wouldn't recognize further
      // opportunities to chain.
      callSites.add(new CallSite(parent, n, callNode, nextGetPropNode,
                                 nextGetPropFirstChildNode));
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[44]++;
    }
  }

  /** Records a call site to chain. */
  private static class CallSite {
    final Node parent;
    final Node n;
    final Node callNode;
    final Node nextGetPropNode;
    final Node nextGetPropFirstChildNode;

    CallSite(Node parent, Node n, Node callNode, Node nextGetPropNode,
             Node nextGetPropFirstChildNode) {
      this.parent = parent;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[45]++;
      this.n = n;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[46]++;
      this.callNode = callNode;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[47]++;
      this.nextGetPropNode = nextGetPropNode;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[48]++;
      this.nextGetPropFirstChildNode = nextGetPropFirstChildNode;
CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh.statements[49]++;
    }
  }
}

class CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh ());
  }
    public static long[] statements = new long[50];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[15];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ChainCalls.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,1,1,1,1,1,1,1,2,1,1,1,1};
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

  public CodeCoverCoverageCounter$k7azn8kx2rowvy9iedogdwh () {
    super("com.google.javascript.jscomp.ChainCalls.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 49; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 28; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.ChainCalls.java");
      for (int i = 1; i <= 49; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 28; i++) {
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

