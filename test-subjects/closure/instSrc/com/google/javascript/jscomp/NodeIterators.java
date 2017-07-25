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
import com.google.common.collect.Lists;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * A package for common iteration patterns.
 *
 * All iterators are forward, post-order traversals unless otherwise noted.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class NodeIterators {
  static {
    CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.ping();
  }


  private NodeIterators() {} /* all static */

  /**
   * Traverses the local scope, skipping all function nodes.
   */
  static class FunctionlessLocalScope implements Iterator<Node> {
    private final Stack<Node> ancestors = new Stack<Node>();
  {
    CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[1]++;
  }

    /**
     * @param ancestors The ancestors of the point where iteration will start,
     *     beginning with the deepest ancestor. The start node will not be
     *     exposed in the iteration.
     */
    FunctionlessLocalScope(Node ... ancestors) {
      Preconditions.checkArgument(ancestors.length > 0);
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[2]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[3]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[1]++;



      for (Node n : ancestors) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[1]--;
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[2]--;
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[3]++;
}
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[1]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[5]++;
          break;

        } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[2]++;}

        this.ancestors.add(0, n);
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[6]++;
      }
    }

    @Override
    public boolean hasNext() {
      // Check if the current node has any nodes after it.
      return !(ancestors.size() == 1 && ancestors.peek().getNext() == null);
    }

    @Override
    public Node next() {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[7]++;
      Node current = ancestors.pop();
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((current.getNext() == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[3]++;
        current = ancestors.peek();
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[9]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;

        // If this is a function node, skip it.
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((current.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[5]++;
          return next();

        } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[6]++;}

      } else {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[4]++;
        current = current.getNext();
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[11]++;
        ancestors.push(current);
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[12]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;

        // If this is a function node, skip it.
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((current.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[7]++;
          return next();

        } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[8]++;}
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;

        while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((current.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[4]--;
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[5]--;
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.loops[6]++;
}
          current = current.getFirstChild();
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[15]++;
          ancestors.push(current);
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[16]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;

          // If this is a function node, skip it.
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((current.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[9]++;
            return next();

          } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[10]++;}
        }
      }

      return current;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Gets the node most recently returned by next().
     */
    protected Node current() {
      return ancestors.peek();
    }

    /**
     * Gets the parent of the node most recently returned by next().
     */
    protected Node currentParent() {
      return ancestors.size() >= 2 ?
          ancestors.get(ancestors.size() - 2) : null;
    }

    /**
     * Gets the ancestors of the current node, with the deepest node first.
     * Only exposed for testing purposes.
     */
    List<Node> currentAncestors() {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[18]++;
      List<Node> list = Lists.newArrayList(ancestors);
      Collections.reverse(list);
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[19]++;
      return list;
    }
  }

  /**
   * An iterator to help with variable inlining. Given a variable declaration,
   * find all the nodes in post-order where the variable is guaranteed to
   * retain its original value.
   *
   * Consider:
   * <pre>
   * var X = 1;
   * var Y = 3; // X is still 1
   * if (Y) {
   *   // X is still 1
   * } else {
   *   X = 5;
   * }
   * // X may not be 1
   * </pre>
   * In the above example, the iterator will iterate past the declaration of
   * Y and into the first block of the IF branch, and will stop at the
   * assignment {@code X = 5}.
   */
  static class LocalVarMotion implements Iterator<Node> {
    private final boolean valueHasSideEffects;
    private final FunctionlessLocalScope iterator;
    private final String varName;
    private Node lookAhead;

    /**
     * @return Create a LocalVarMotion for use with moving a value assigned
     * at a variable declaration.
     */
    static LocalVarMotion forVar(
        Node name, Node var, Node block) {
      Preconditions.checkArgument(var.isVar());
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[20]++;
      Preconditions.checkArgument(NodeUtil.isStatement(var));
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[21]++;
      // The FunctionlessLocalScope must start at "name" as this may be used
      // before the Normalize pass, and thus the VAR node may define multiple
      // names and the "name" node may have siblings.  The actual assigned
      // value is skipped as it is a child of name.
      return new LocalVarMotion(
          name, new FunctionlessLocalScope(name, var, block));
    }

    /**
     * @return Create a LocalVarMotion for use with moving a value assigned
     * as part of a simple assignment expression ("a = b;").
     */
    static LocalVarMotion forAssign(
        Node name, Node assign, Node expr, Node block) {
      Preconditions.checkArgument(assign.isAssign());
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[22]++;
      Preconditions.checkArgument(expr.isExprResult());
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[23]++;
      // The FunctionlessLocalScope must start at "assign", to skip the value
      // assigned to "name" (which would be its sibling).
      return new LocalVarMotion(
          name, new FunctionlessLocalScope(assign, expr, block));
    }

    /**
     * @param iterator The iterator to use while inspecting the node
     *     beginning with the deepest ancestor.
     */
    private LocalVarMotion(Node nameNode, FunctionlessLocalScope iterator) {
      Preconditions.checkArgument(nameNode.isName());
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[24]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[25]++;
      Node valueNode = NodeUtil.getAssignedValue(nameNode);
      this.varName = nameNode.getString();
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[26]++;
      this.valueHasSideEffects = valueNode != null &&
          NodeUtil.mayHaveSideEffects(valueNode);
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[27]++;
      this.iterator = iterator;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[28]++;
      advanceLookAhead(true);
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[29]++;
    }

    @Override
    public boolean hasNext() {
      return lookAhead != null;
    }

    @Override
    public Node next() {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[30]++;
      Node next = lookAhead;
      advanceLookAhead(false);
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[31]++;
      return next;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Not implemented");
    }

    private void advanceLookAhead(boolean atStart) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((atStart) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[11]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[33]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((lookAhead == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[13]++;
          return;

        } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[14]++;}
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[34]++;

        // Don't advance past a reference to the variable that we're trying
        // to inline.
        Node curNode = iterator.current();
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[35]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((curNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((varName.equals(curNode.getString())) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[15]++;
          lookAhead = null;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[36]++;
          return;

        } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[16]++;}

      } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[12]++;}
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[37]++;
int CodeCoverConditionCoverageHelper_C10;

      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((iterator.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[17]++;
        lookAhead = null;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[38]++;
        return;

      } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[18]++;}
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[39]++;

      Node nextNode = iterator.next();
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[40]++;
      Node nextParent = iterator.currentParent();
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[41]++;
      int type = nextNode.getType();
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;

      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((valueHasSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[19]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[43]++;
        // Reject anything that might read state
        boolean readsState = false;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[44]++;
int CodeCoverConditionCoverageHelper_C12;

        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C12 |= (128)) == 0 || true) &&
 ((nextNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((varName.equals(nextNode.getString())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((nextNode.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((nextNode.isGetElem()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 4) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 4) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[21]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[45]++;
int CodeCoverConditionCoverageHelper_C13;

          // If this is a simple assign, we'll be ok.
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((nextParent == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarOrSimpleAssignLhs(nextNode, nextParent)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[23]++;
            readsState = true;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[46]++;

          } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[24]++;}


        } else {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[22]++;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[47]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((nextNode.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((nextNode.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[25]++;
          // This isn't really an important case. In most cases when we use
          // CALL or NEW, we're invoking it on a NAME or a GETPROP. And in the
          // few cases where we're not, it's because we have an anonymous
          // function that escapes the variable we're worried about. But we
          // include this for completeness.
          readsState = true;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[48]++;

        } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[26]++;}
}
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[49]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((readsState) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[27]++;
          lookAhead = null;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[50]++;
          return;

        } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[28]++;}

      } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[20]++;}
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[51]++;
int CodeCoverConditionCoverageHelper_C16;

      // Reject anything that might modify relevant state. We assume that
      // nobody relies on variables being undeclared, which will break
      // constructions like:
      //   var a = b;
      //   var b = 3;
      //   alert(a);
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (128)) == 0 || true) &&
 ((NodeUtil.nodeTypeMayHaveSideEffects(nextNode)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (32)) == 0 || true) &&
 ((type != Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((type == Token.NAME) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((nextParent.isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 4) || true)) || (CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 4) && false)) {
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[29]++;
        lookAhead = null;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[52]++;
        return;

      } else {
  CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.branches[30]++;}

      lookAhead = nextNode;
CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5.statements[53]++;
    }
  }
}

class CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5 ());
  }
    public static long[] statements = new long[54];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[17];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NodeIterators.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,1,1,3,2,2,1,3};
    for (int i = 1; i <= 16; i++) {
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

  public CodeCoverCoverageCounter$6itbn7vdy7842lswlyb4mh6ua3j5 () {
    super("com.google.javascript.jscomp.NodeIterators.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 53; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 16; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NodeIterators.java");
      for (int i = 1; i <= 53; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 16; i++) {
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

