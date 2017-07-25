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

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Collection;
import java.util.List;

/**
 * Reduces the size of common function expressions.
 *
 * This pass will rewrite:
 *
 * C.prototype.getA = function() { return this.a_ };
 * C.prototype.setA = function(newValue) { this.a_ = newValue };
 *
 * as:
 *
 * C.prototype.getA = JSCompiler_get("a_);
 * C.prototype.setA = JSCompiler_set("a_);
 *
 * if by doing so we will save bytes, after the helper functions are
 * added and renaming is done.
 *
 */
class FunctionRewriter implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.ping();
  }

  private final AbstractCompiler compiler;
  // Safety margin used to avoid growing simple programs by a few bytes.
  // Selected arbitrarily.
  private static final int SAVINGS_THRESHOLD = 16;
  static {
    CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[1]++;
  }

  FunctionRewriter(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[2]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[3]++;
    List<Reducer> reducers = ImmutableList.of(new ReturnConstantReducer(),
                                              new GetterReducer(),
                                              new SetterReducer(),
                                              new EmptyFunctionReducer(),
                                              new IdentityReducer());
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[4]++;

    Multimap<Reducer, Reduction> reductionMap = HashMultimap.create();

    // Accumulate possible reductions in the reduction multi-map.  They
    // will be applied in the loop below.
    NodeTraversal.traverse(compiler, root,
                           new ReductionGatherer(reducers, reductionMap));
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[5]++;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[1]++;



    // Apply reductions iff they will provide some savings.
    for (Reducer reducer : reducers) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[1]--;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[2]--;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[3]++;
}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[7]++;
      Collection<Reduction> reductions = reductionMap.get(reducer);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((reductions.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[1]++;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[9]++;
        continue;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[2]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[10]++;

      Node helperCode = parseHelperCode(reducer);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((helperCode == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[3]++;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[12]++;
        continue;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[4]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[13]++;

      int helperCodeCost = InlineCostEstimator.getCost(helperCode);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[14]++;

      // Estimate savings
      int savings = 0;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[4]++;


      for (Reduction reduction : reductions) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[4]--;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[5]--;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[6]++;
}
        savings += reduction.estimateSavings();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[16]++;
      }
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;

      // Compare estimated savings against the helper cost.  Apply
      // reductions if doing so will result in some savings.
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((savings > (helperCodeCost + SAVINGS_THRESHOLD)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[5]++;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[18]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[7]++;


        for (Reduction reduction : reductions) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[7]--;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[8]--;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[9]++;
}
          reduction.apply();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[19]++;
        }
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[20]++;

        Node addingRoot = compiler.getNodeForCodeInsertion(null);
        addingRoot.addChildrenToFront(helperCode);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[21]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[22]++;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[6]++;}
    }
  }

  /**
   * Parse helper code needed by a reducer.
   *
   * @return Helper code root.  If parse fails, return null.
   */
  public Node parseHelperCode(Reducer reducer) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[23]++;
    Node root = compiler.parseSyntheticCode(
        reducer.getClass().toString() + ":helper", reducer.getHelperSource());
    return (root != null) ? root.removeFirstChild() : null;
  }

  private static boolean isReduceableFunctionExpression(Node n) {
    return NodeUtil.isFunctionExpression(n)
        && !NodeUtil.isGetOrSetKey(n.getParent());
  }

  /**
   * Information needed to apply a reduction.
   */
  private class Reduction {
    private final Node parent;
    private final Node oldChild;
    private final Node newChild;

    Reduction(Node parent, Node oldChild, Node newChild) {
      this.parent = parent;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[24]++;
      this.oldChild = oldChild;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[25]++;
      this.newChild = newChild;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[26]++;
    }

    /**
     * Apply the reduction by replacing the old child with the new child.
     */
    void apply() {
      parent.replaceChild(oldChild, newChild);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[27]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[28]++;
    }

    /**
     * Estimate number of bytes saved by applying this reduction.
     */
    int estimateSavings() {
      return InlineCostEstimator.getCost(oldChild) -
          InlineCostEstimator.getCost(newChild);
    }
  }

  /**
   * Gathers a list of reductions to apply later by doing an in-order
   * AST traversal.  If a suitable reduction is found, stop traversal
   * in that branch.
   */
  private class ReductionGatherer implements Callback {
    private final List<Reducer> reducers;
    private final Multimap<Reducer, Reduction> reductions;

    /**
     * @param reducers List of reducers to apply during traversal.
     * @param reductions Reducer -> Reduction multimap,
     *                   populated during traversal.
     */
    ReductionGatherer(List<Reducer> reducers,
                      Multimap<Reducer, Reduction> reductions) {
      this.reducers = reducers;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[29]++;
      this.reductions = reductions;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[30]++;
    }

    @Override
    public boolean shouldTraverse(NodeTraversal raversal,
                                  Node node,
                                  Node parent) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[31]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[10]++;


      for (Reducer reducer : reducers) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[10]--;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[11]--;
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.loops[12]++;
}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[32]++;
        Node replacement = reducer.reduce(node);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[33]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((replacement != node) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[7]++;
          reductions.put(reducer, new Reduction(parent, node, replacement));
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[34]++;
          return false;

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[8]++;}
      }
      return true;
    }


    @Override
    public void visit(NodeTraversal traversal, Node node, Node parent) {
    }
  }

  /**
   * Interface implemented by the strength-reduction optimizers below.
   */
  abstract static class Reducer {
    /**
     * @return JS source for helper methods used by this reduction.
     */
    abstract String getHelperSource();

    /**
     * @return root of the reduced subtree if a reduction was applied;
     *         otherwise returns the node argument.
     */
    abstract Node reduce(Node node);

    /**
     * Builds a method call based on the the given method name,
     * argument and history.
     *
     * @param methodName Method to call.
     * @param argumentNode Method argument.
     */
    protected final Node buildCallNode(String methodName, Node argumentNode,
                                       Node srcref) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[35]++;
      Node call = IR.call(IR.name(methodName)).srcref(srcref);
      call.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[36]++;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[37]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((argumentNode != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[9]++;
        call.addChildToBack(argumentNode.cloneTree());
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[38]++;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[10]++;}
      return call;
    }
  }

  /**
   * Reduces return immutable constant literal methods declarations
   * with calls to a constant return method factory.
   *
   * Example:
   *   a.prototype.b = function() {}
   * is reduced to:
   *   a.prototype.b = emptyFn();
   */
  private static class EmptyFunctionReducer extends Reducer {
    static final String FACTORY_METHOD_NAME = "JSCompiler_emptyFn";
    static final String HELPER_SOURCE =
        "function " + FACTORY_METHOD_NAME + "() {" +
        "  return function() {}" +
        "}";

    @Override
    public String getHelperSource() {
      return HELPER_SOURCE;
    }

    @Override
    public Node reduce(Node node) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[39]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isEmptyFunctionExpression(node)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[11]++;
        return buildCallNode(FACTORY_METHOD_NAME, null, node);

      } else {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[12]++;
        return node;
      }
    }
  }

  /**
   * Base class for reducers that match functions that contain a
   * single return statement.
   */
  abstract static class SingleReturnStatementReducer extends Reducer {

    /**
     * @return function return value node if function body contains a
     * single return statement.  Otherwise, null.
     */
    protected final Node maybeGetSingleReturnRValue(Node functionNode) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[40]++;
      Node body = functionNode.getLastChild();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[41]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((body.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[13]++;
        return null;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[14]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[42]++;

      Node statement = body.getFirstChild();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[43]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((statement.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[15]++;
        return statement.getFirstChild();

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[16]++;}
      return null;
    }
  }

  /**
   * Reduces property getter method declarations with calls to a
   * getter method factory.
   *
   * Example:
   *   a.prototype.b = function(a) {return a}
   * is reduced to:
   *   a.prototype.b = getter(a);
   */
  private static class IdentityReducer extends SingleReturnStatementReducer {
    static final String FACTORY_METHOD_NAME = "JSCompiler_identityFn";
    static final String HELPER_SOURCE =
        "function " + FACTORY_METHOD_NAME + "() {" +
        "  return function(" + FACTORY_METHOD_NAME + "_value) {" +
             "return " + FACTORY_METHOD_NAME + "_value}" +
        "}";

    @Override
    public String getHelperSource() {
      return HELPER_SOURCE;
    }

    @Override
    public Node reduce(Node node) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isReduceableFunctionExpression(node)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[17]++;
        return node;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[18]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;

      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isIdentityFunction(node)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[19]++;
        return buildCallNode(FACTORY_METHOD_NAME, null, node);

      } else {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[20]++;
        return node;
      }
    }

    /**
     * Checks if the function matches the pattern:
     *   function(<value>, <rest>) {return <value>}
     *
     * @return Whether the function matches the pattern.
     */
    private boolean isIdentityFunction(Node functionNode) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[46]++;
      Node argList = functionNode.getFirstChild().getNext();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[47]++;
      Node paramNode = argList.getFirstChild();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[48]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((paramNode == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[21]++;
        return false;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[22]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[49]++;

      Node value = maybeGetSingleReturnRValue(functionNode);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (32)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((value.isName()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((value.getString().equals(paramNode.getString())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 3) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[23]++;
        return true;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[24]++;}
      return false;
    }
  }

  /**
   * Reduces return immutable constant literal methods declarations
   * with calls to a constant return method factory.
   *
   * Example:
   *   a.prototype.b = function() {return 10}
   * is reduced to:
   *   a.prototype.b = returnconst(10);
   */
  private static class ReturnConstantReducer
      extends SingleReturnStatementReducer {
    static final String FACTORY_METHOD_NAME = "JSCompiler_returnArg";
    static final String HELPER_SOURCE =
        "function " + FACTORY_METHOD_NAME +
        "(" + FACTORY_METHOD_NAME + "_value) {" +
        "  return function() {return " + FACTORY_METHOD_NAME + "_value}" +
        "}";

    @Override
    public String getHelperSource() {
      return HELPER_SOURCE;
    }

    @Override
    public Node reduce(Node node) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isReduceableFunctionExpression(node)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[25]++;
        return node;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[26]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[52]++;

      Node valueNode = getValueNode(node);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((valueNode != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[27]++;
        return buildCallNode(FACTORY_METHOD_NAME, valueNode, node);

      } else {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[28]++;
        return node;
      }
    }

    /**
     * Checks if the function matches the pattern:
     *   function(<args>) {return <immutable value>}
     * and returns <immutable value> if a match is found.
     *
     * @return the immutable value node; or null.
     */
    private Node getValueNode(Node functionNode) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[54]++;
      Node value = maybeGetSingleReturnRValue(functionNode);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[55]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[29]++;
        return value;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[30]++;}
      return null;
    }
  }

  /**
   * Reduces property getter method declarations with calls to a
   * getter method factory.
   *
   * Example:
   *   a.prototype.b = function() {return this.b_}
   * is reduced to:
   *   a.prototype.b = getter("b_");
   */
  private static class GetterReducer extends SingleReturnStatementReducer {
    static final String FACTORY_METHOD_NAME = "JSCompiler_get";
    static final String HELPER_SOURCE =
        "function " + FACTORY_METHOD_NAME + "(" +
        FACTORY_METHOD_NAME + "_name) {" +
        "  return function() {return this[" + FACTORY_METHOD_NAME + "_name]}" +
        "}";

    @Override
    public String getHelperSource() {
      return HELPER_SOURCE;
    }

    @Override
    public Node reduce(Node node) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[56]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((isReduceableFunctionExpression(node)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[31]++;
        return node;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[32]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[57]++;

      Node propName = getGetPropertyName(node);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[58]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((propName != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[33]++;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[59]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((propName.isString()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[35]++;
          throw new IllegalStateException(
              "Expected STRING, got " + Token.name(propName.getType()));

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[36]++;}

        return buildCallNode(FACTORY_METHOD_NAME, propName, node);

      } else {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[34]++;
        return node;
      }
    }

    /**
     * Checks if the function matches the pattern:
     *   function(<args>) {return this.<name>}
     * and returns <name> if a match is found.
     *
     * @return STRING node that is the RHS of a this property get; or null.
     */
    private Node getGetPropertyName(Node functionNode) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[60]++;
      Node value = maybeGetSingleReturnRValue(functionNode);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[61]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((value.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((value.getFirstChild().isThis()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[37]++;
        return value.getLastChild();

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[38]++;}
      return null;
    }
  }

  /**
   * Reduces property setter method declarations with calls to a
   * setter method factory.
   *
   * Example:
   *   a.prototype.setB = function(value) {this.b_ = value}
   * reduces to:
   *   a.prototype.setB = getter("b_");
   */
  private static class SetterReducer extends Reducer {
    static final String FACTORY_METHOD_NAME = "JSCompiler_set";
    static final String HELPER_SOURCE =
        "function " + FACTORY_METHOD_NAME + "(" +
        FACTORY_METHOD_NAME + "_name) {" +
        "  return function(" + FACTORY_METHOD_NAME + "_value) {" +
        "this[" + FACTORY_METHOD_NAME + "_name] = " +
        FACTORY_METHOD_NAME + "_value}" +
        "}";

    @Override
    public String getHelperSource() {
      return HELPER_SOURCE;
    }

    @Override
    public Node reduce(Node node) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[62]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((isReduceableFunctionExpression(node)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[39]++;
        return node;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[40]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[63]++;

      Node propName = getSetPropertyName(node);
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[64]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((propName != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[41]++;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[65]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((propName.isString()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[43]++;
          throw new IllegalStateException(
              "Expected STRING, got " + Token.name(propName.getType()));

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[44]++;}

        return buildCallNode(FACTORY_METHOD_NAME, propName, node);

      } else {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[42]++;
        return node;
      }
    }

    /**
     * Checks if the function matches the pattern:
     *   function(<value>, <rest>) {this.<name> = <value>}
     * and returns <name> if a match is found.
     *
     * @return STRING node that is the RHS of a this property get; or null.
     */
    private Node getSetPropertyName(Node functionNode) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[66]++;
      Node body = functionNode.getLastChild();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[67]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((body.hasOneChild()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[45]++;
        return null;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[46]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[68]++;

      Node argList = functionNode.getFirstChild().getNext();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[69]++;
      Node paramNode = argList.getFirstChild();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[70]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((paramNode == null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[47]++;
        return null;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[48]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[71]++;

      Node statement = body.getFirstChild();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[72]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((NodeUtil.isExprAssign(statement)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[49]++;
        return null;

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[50]++;}
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[73]++;

      Node assign = statement.getFirstChild();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[74]++;
      Node lhs = assign.getFirstChild();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[75]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((lhs.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((lhs.getFirstChild().isThis()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[51]++;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[76]++;
        Node rhs = assign.getLastChild();
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[77]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((rhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((rhs.getString().equals(paramNode.getString())) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[53]++;
CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.statements[78]++;
          Node propertyName = lhs.getLastChild();
          return propertyName;

        } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[54]++;}

      } else {
  CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl.branches[52]++;}
      return null;
    }
  }
}

class CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl ());
  }
    public static long[] statements = new long[79];
    public static long[] branches = new long[55];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.FunctionRewriter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,3,1,1,2,1,1,1,3,1,1,1,1,1,1,2,2};
    for (int i = 1; i <= 27; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$1miz189ry4jmd8tlrklzmo981az426yjl () {
    super("com.google.javascript.jscomp.FunctionRewriter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 78; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 54; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.FunctionRewriter.java");
      for (int i = 1; i <= 78; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 54; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

