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

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CodingConvention.AssertionFunctionSpec;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.Node;
import java.util.List;
import java.util.Set;

/**
 * <p>Compiler pass that removes Closure-specific code patterns.</p>
 *
 * <p>Currently does the following:</p>
 *
 * <ul>
 *   <li> Instead of setting abstract methods to a function that throws an
 *        informative error, this pass allows some binary size reduction by
 *        removing these methods altogether for production builds.</li>
 *   <li> Remove calls to assertion functions (like goog.asserts.assert).
 *        If the return value of the assertion function is used, then
 *        the first argument (the asserted value) will be directly inlined.
 *        Otherwise, the entire call will be removed. It is well-known that
 *        this is not provably safe, much like the equivalent assert
 *        statement in Java.</li>
 * </ul>
 *
 * @author robbyw@google.com (Robby Walker)
 */
final class ClosureCodeRemoval implements CompilerPass {
  static {
    CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.ping();
  }


  /** Reference to the JS compiler */
  private final AbstractCompiler compiler;

  /** Name used to denote an abstract function */
  static final String ABSTRACT_METHOD_NAME = "goog.abstractMethod";
  static {
    CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[1]++;
  }

  private final boolean removeAbstractMethods;
  private final boolean removeAssertionCalls;

  /**
   * List of names referenced in successive generations of finding referenced
   * nodes.
   */
  private final List<RemovableAssignment> abstractMethodAssignmentNodes =
      Lists.newArrayList();
  {
    CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[2]++;
  }

  /**
   * List of assertion functions.
   */
  private final List<Node> assertionCalls = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[3]++;
  }


  /**
   * Utility class to track a node and its parent.
   */
  private class RemovableAssignment {
    /**
     * The node
     */
    final Node node;

    /**
     * Its parent
     */
    final Node parent;

    /**
     * Full chain of ASSIGN ancestors
     */
    final List<Node> assignAncestors = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[4]++;
  }

    /**
     * The last ancestor
     */
    final Node lastAncestor;

    /**
     * Data structure for information about a removable assignment.
     *
     * @param nameNode The LHS
     * @param assignNode The parent ASSIGN node
     * @param traversal Access to further levels, assumed to start at 1
     */
    public RemovableAssignment(Node nameNode, Node assignNode,
        NodeTraversal traversal) {
      this.node = nameNode;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[5]++;
      this.parent = assignNode;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[6]++;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[7]++;

      Node ancestor = assignNode;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[8]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
      do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[1]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[2]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[3]++;
}
        ancestor = ancestor.getParent();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[9]++;
        assignAncestors.add(ancestor);
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[10]++;
      } while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((ancestor.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((ancestor.getFirstChild().isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false));
      lastAncestor = ancestor.getParent();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[11]++;
    }

    /**
     * Remove this node.
     */
    public void remove() {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[12]++;
      Node rhs = node.getNext();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[13]++;
      Node last = parent;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[4]++;


      for (Node ancestor : assignAncestors) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[4]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[5]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[6]++;
}
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[15]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((ancestor.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[1]++;
          lastAncestor.removeChild(ancestor);
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[16]++;

        } else {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[2]++;
          rhs.detachFromParent();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[17]++;
          ancestor.replaceChild(last, rhs);
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[18]++;
        }
        last = ancestor;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[19]++;
      }
      compiler.reportCodeChange();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[20]++;
    }
  }

  /**
   * Identifies all assignments of the abstract method to a variable.
   */
  private class FindAbstractMethods extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[3]++;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[22]++;
        Node nameNode = n.getFirstChild();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[23]++;
        Node valueNode = n.getLastChild();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[24]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((nameNode.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((valueNode.isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ABSTRACT_METHOD_NAME.equals(valueNode.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) && false)) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[5]++;
          abstractMethodAssignmentNodes.add(new RemovableAssignment(
              n.getFirstChild(), n, t));
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[25]++;

        } else {
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[6]++;}

      } else {
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[4]++;}
    }
  }


  /**
   * Identifies all assertion calls.
   */
  private class FindAssertionCalls extends AbstractPostOrderCallback {
    Set<String> assertionNames = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[26]++;
  }

    FindAssertionCalls() {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[27]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[7]++;


      for (AssertionFunctionSpec spec :
               compiler.getCodingConvention().getAssertionFunctions()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[7]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[8]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[9]++;
}
        assertionNames.add(spec.getFunctionName());
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[28]++;
      }
    }


    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[29]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[7]++;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[30]++;
        String fnName = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((assertionNames.contains(fnName)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[9]++;
          assertionCalls.add(n);
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[32]++;

        } else {
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[10]++;}

      } else {
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[8]++;}
    }
  }


  /**
   * Creates a Closure code remover.
   *
   * @param compiler The AbstractCompiler
   * @param removeAbstractMethods Remove declarations of abstract methods.
   * @param removeAssertionCalls Remove calls to goog.assert functions.
   */
  ClosureCodeRemoval(AbstractCompiler compiler, boolean removeAbstractMethods,
                     boolean removeAssertionCalls) {
    this.compiler = compiler;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[33]++;
    this.removeAbstractMethods = removeAbstractMethods;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[34]++;
    this.removeAssertionCalls = removeAssertionCalls;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[35]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[36]++;
    List<Callback> passes = Lists.newArrayList();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[37]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((removeAbstractMethods) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[11]++;
      passes.add(new FindAbstractMethods());
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[38]++;

    } else {
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[12]++;}
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[39]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((removeAssertionCalls) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[13]++;
      passes.add(new FindAssertionCalls());
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[40]++;

    } else {
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[14]++;}
    CombinedCompilerPass.traverse(compiler, root, passes);
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[41]++;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[42]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[10]++;



    for (RemovableAssignment assignment : abstractMethodAssignmentNodes) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[10]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[11]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[12]++;
}
      assignment.remove();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[43]++;
    }
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[44]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[13]++;



    for (Node call : assertionCalls) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[13]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[14]--;
  CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.loops[15]++;
}
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[45]++;
      // If the assertion is an expression, just strip the whole thing.
      Node parent = call.getParent();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[15]++;
        parent.getParent().removeChild(parent);
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[47]++;

      } else {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[16]++;
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[48]++;
        // Otherwise, replace the assertion with its first argument,
        // which is the return value of the assertion.
        Node firstArg = call.getFirstChild().getNext();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[49]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((firstArg == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[17]++;
          parent.replaceChild(call, NodeUtil.newUndefinedNode(call));
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[50]++;

        } else {
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.branches[18]++;
          parent.replaceChild(call, firstArg.detachFromParent());
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[51]++;
        }
      }
      compiler.reportCodeChange();
CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td.statements[52]++;
    }
  }
}

class CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td ());
  }
    public static long[] statements = new long[53];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ClosureCodeRemoval.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,3,1,1,1,1,1,1};
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
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$26o3ktk5tf8qhlhriy898djbn15ulp5dd0td () {
    super("com.google.javascript.jscomp.ClosureCodeRemoval.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 52; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ClosureCodeRemoval.java");
      for (int i = 1; i <= 52; i++) {
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
    for (int i = 1; i <= 10; i++) {
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

