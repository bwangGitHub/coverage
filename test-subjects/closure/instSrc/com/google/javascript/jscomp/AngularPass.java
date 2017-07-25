/*
 * Copyright 2012 The Closure Compiler Authors.
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
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;

import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Compiler pass for AngularJS-specific needs. Generates {@code $inject} \
 * properties for functions (class constructors, wrappers, etc) annotated with
 * @ngInject.
 *
 * <p>For example, the following code:</p>
 * <pre>{@code
 *
 * /** @ngInject * /
 * function Controller(dependency1, dependency2) {
 *   // do something
 * }
 *
 * }</pre>
 *
 * <p>will be transformed into:
 * <pre>{@code
 *
 * function Controller(dependency1, dependency2) {
 *   // do something
 * }
 * Controller.$inject = ['dependency1', 'dependency2'];
 *
 * }</pre>
 *
 * <p> This pass also supports assignments of function expressions to variables
 * like:
 * <pre>{@code
 *
 * /** @ngInject * /
 * var filter = function(a, b) {};
 *
 * var ns = {};
 * /** @ngInject * /
 * ns.method = function(a,b,c) {};
 *
 * /** @ngInject * /
 * var shorthand = ns.method2 = function(a,b,c,) {}
 *
 * }</pre>
 */
class AngularPass extends AbstractPostOrderCallback implements CompilerPass {
  static {
    CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.ping();
  }

  final AbstractCompiler compiler;

  /** Nodes annotated with @ngInject */
  private List<NodeContext> injectables = new ArrayList<NodeContext>();
  {
    CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[1]++;
  }

  public AngularPass(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[2]++;
  }

  public static final String INJECT_PROPERTY_NAME = "$inject";
  static {
    CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[3]++;
  }

  static final DiagnosticType INJECT_IN_NON_GLOBAL_OR_BLOCK_ERROR =
      DiagnosticType.error("JSC_INJECT_IN_NON_GLOBAL_OR_BLOCK_ERROR",
          "@ngInject only applies to functions defined in blocks or " +
          "global scope.");
  static {
    CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[4]++;
  }

  static final DiagnosticType INJECT_NON_FUNCTION_ERROR =
      DiagnosticType.error("JSC_INJECT_NON_FUNCTION_ERROR",
          "@ngInject can only be used when defining a function or " +
          "assigning a function expression.");
  static {
    CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[5]++;
  }

  static final DiagnosticType FUNCTION_NAME_ERROR =
      DiagnosticType.error("JSC_FUNCTION_NAME_ERROR",
          "Unable to determine target function name for @ngInject.");
  static {
    CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[6]++;
  }

  @Override
  public void process(Node externs, Node root) {
    // Traverses AST looking for nodes annotated with @ngInject.
    NodeTraversal.traverse(compiler, root, this);
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[7]++;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[8]++;
    CodingConvention convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[9]++;
    boolean codeChanged = false;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[1]++;


    // iterates through annotated nodes adding $inject property to elements.
    for (NodeContext entry : injectables) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[1]--;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[2]--;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[3]++;
}
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[11]++;
      String name = entry.getName();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[12]++;
      Node fn = entry.getFunctionNode();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[13]++;
      List<Node> dependencies = createDependenciesList(fn);
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
      // skips entry if it does have any dependencies.
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((dependencies.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[1]++;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[15]++;
        continue;

      } else {
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[2]++;}
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[16]++;
      Node dependenciesArray = IR.arraylit(dependencies.toArray(
          new Node[dependencies.size()]));
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[17]++;
      // creates `something.$inject = ['param1', 'param2']` node.
      Node statement = IR.exprResult(
          IR.assign(
              NodeUtil.newQualifiedNameNode(convention,
                  name + "." + INJECT_PROPERTY_NAME),
              dependenciesArray
          )
      );
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[18]++;
      // adds `something.$inject = [...]` node after the annotated node.
      Node target = entry.getTarget();
      target.getParent().addChildAfter(statement, target);
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[19]++;
      codeChanged = true;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[20]++;
    }
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((codeChanged) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[3]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[22]++;

    } else {
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[4]++;}
  }

  /**
   * Given a FUNCTION node returns array of STRING nodes representing function
   * parameters.
   * @param n the FUNCTION node.
   * @return STRING nodes.
   */
  private List<Node> createDependenciesList(Node n) {
    Preconditions.checkArgument(n.isFunction());
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[23]++;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[24]++;
    Node params = NodeUtil.getFunctionParameters(n);
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((params != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[5]++;
      return createStringsFromParamList(params);

    } else {
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[6]++;}
    return Lists.newArrayList();
  }

  /**
   * Given a PARAM_LIST node creates an array of corresponding STRING nodes.
   * @param params PARAM_LIST node.
   * @return array of STRING nodes.
   */
  private List<Node> createStringsFromParamList(Node params) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[26]++;
    Node param = params.getFirstChild();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[27]++;
    ArrayList<Node> names = Lists.newArrayList();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[28]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
    while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((param != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((param.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[4]--;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[5]--;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[6]++;
}
      names.add(IR.string(param.getString()).srcref(param));
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[29]++;
      param = param.getNext();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[30]++;
    }
    return names;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[31]++;
    JSDocInfo docInfo = n.getJSDocInfo();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[32]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((docInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((docInfo.isNgInject()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[7]++;
      addNode(n, t);
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[33]++;

    } else {
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[8]++;}
  }

  /**
   * Add node to the list of injectables.
   * @param n node to add.
   * @param t node traversal instance.
   */
  private void addNode(Node n, NodeTraversal t) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[34]++;
    Node target = null;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[35]++;
    Node fn = null;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[36]++;
    String name = null;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[37]++;

    switch (n.getType()) {
      // handles assignment cases like:
      // a = function() {}
      // a = b = c = function() {}
      case Token.ASSIGN:
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[9]++;
        name = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[38]++;
        // last node of chained assignment.
        fn = n;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[39]++;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[40]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
        while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((fn.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[7]--;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[8]--;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[9]++;
}
          fn = fn.getLastChild();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[41]++;
        }
        target = n.getParent();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[42]++;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[43]++;
        break;

      // handles function case:
      // function fnName() {}
      case Token.FUNCTION:
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[10]++;
        name = NodeUtil.getFunctionName(n);
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[44]++;
        fn = n;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[45]++;
        target = n;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[46]++;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[47]++;
        break;

      // handles var declaration cases like:
      // var a = function() {}
      // var a = b = function() {}
      case Token.VAR:
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[11]++;
        name = n.getFirstChild().getString();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[48]++;
        // looks for a function node.
        fn = getDeclarationRValue(n);
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[49]++;
        target = n;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[50]++;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[51]++;
        break; default : CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[12]++;
    }
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[52]++;
int CodeCoverConditionCoverageHelper_C7;
    // checks that the declaration took place in a block or in a global scope.
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((target.getParent().isScript()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((target.getParent().isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[13]++;
      compiler.report(t.makeError(n, INJECT_IN_NON_GLOBAL_OR_BLOCK_ERROR));
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[53]++;
      return;

    } else {
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[14]++;}
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[54]++;
int CodeCoverConditionCoverageHelper_C8;
    // checks that it is a function declaration.
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((fn == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((fn.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[15]++;
      compiler.report(t.makeError(n, INJECT_NON_FUNCTION_ERROR));
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[55]++;
      return;

    } else {
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[16]++;}
    // checks that name is present, which must always be the case unless the
    // compiler allowed a syntax error or a dangling anonymous function
    // expression.
    Preconditions.checkNotNull(name);
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[56]++;
    // registers the node.
    injectables.add(new NodeContext(name, n, fn, target));
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[57]++;
  }

  /**
   * Given a VAR node (variable declaration) returns the node of initial value.
   *
   * <pre>{@code
   * var x;  // null
   * var y = "value"; // STRING "value" node
   * var z = x = y = function() {}; // FUNCTION node
   * }</pre>
   * @param n VAR node.
   * @return the assigned intial value, or the rightmost rvalue of an assignment
   * chain, or null.
   */
  private Node getDeclarationRValue(Node n) {
    Preconditions.checkNotNull(n);
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[58]++;
    Preconditions.checkArgument(n.isVar());
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[59]++;
    n = n.getFirstChild().getFirstChild();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[60]++;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[61]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[17]++;
      return null;

    } else {
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.branches[18]++;}
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[62]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[10]++;


int CodeCoverConditionCoverageHelper_C10;
    while ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[10]--;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[11]--;
  CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.loops[12]++;
}
      n = n.getLastChild();
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[63]++;
    }
    return n;
  }

  class NodeContext {
    /** Name of the function/object. */
    private String name;
    /** Node jsDoc is attached to. */
    private Node node;
    /** Function node */
    private Node functionNode;
    /** Node after which to inject the new code */
    private Node target;

    public NodeContext(String name, Node node, Node functionNode, Node target) {
      this.name = name;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[64]++;
      this.node = node;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[65]++;
      this.functionNode = functionNode;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[66]++;
      this.target = target;
CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip.statements[67]++;
    }

    /**
     * @return the name.
     */
    public String getName() {
      return name;
    }

    /**
     * @return the node.
     */
    public Node getNode() {
      return node;
    }

    /**
     * @return the context.
     */
    public Node getFunctionNode() {
      return functionNode;
    }

    /**
     * @return the context.
     */
    public Node getTarget() {
      return target;
    }
  }
}

class CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip ());
  }
    public static long[] statements = new long[68];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AngularPass.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,2,1,2,2,1,1};
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
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$3vgaujve4gfp7yk3tknief0ip () {
    super("com.google.javascript.jscomp.AngularPass.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 67; i++) {
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
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AngularPass.java");
      for (int i = 1; i <= 67; i++) {
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

